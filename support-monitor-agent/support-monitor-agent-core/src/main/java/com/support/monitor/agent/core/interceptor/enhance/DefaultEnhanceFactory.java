package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptPoint;
import com.support.monitor.agent.core.interceptor.MethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.StaticMethodsInterceptPoint;
import com.support.monitor.agent.core.interceptor.callable.ConstructorInterceptCallable;
import com.support.monitor.agent.core.interceptor.callable.MethodsInterceptWithOverrideArgsCallable;
import com.support.monitor.agent.core.interceptor.callable.OverrideCallable;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.List;

import static net.bytebuddy.jar.asm.Opcodes.ACC_PRIVATE;
import static net.bytebuddy.jar.asm.Opcodes.ACC_VOLATILE;
import static net.bytebuddy.matcher.ElementMatchers.isStatic;

/**
 * 默认method delegation构建工厂
 *
 * @author
 */
@Slf4j
public class DefaultEnhanceFactory implements EnhanceFactory {


    private static final String ENHANCE_CLASS_FIELD_NAME = "_$ENHANCE_CLASS_FIELD_NAME";

    private InterceptorFactory interceptorFactory;

    public DefaultEnhanceFactory(InterceptorFactory interceptorFactory) {
        this.interceptorFactory = interceptorFactory;
    }


    @Override
    public DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, List<EnhanceContext> enhanceContexts) {
        builder = enhanceSource(builder);
        for (EnhanceContext enhanceContext : enhanceContexts) {
            //chain handler
            if (enhanceContext.isMethodsInterceptPoint()) {
                builder = enhanceMethods(builder, (MethodsInterceptPoint) enhanceContext.getInterceptPoint());
            } else if (enhanceContext.isConstructorInterceptPoint()) {
                builder = enhanceConstructor(builder, (ConstructorInterceptPoint) enhanceContext.getInterceptPoint());
            } else if (enhanceContext.isStaticMethodsInterceptPoint()) {
                builder = enhanceStaticMethods(builder, (StaticMethodsInterceptPoint) enhanceContext.getInterceptPoint());
            }
        }
        return builder;
    }

    @Override
    public DynamicType.Builder<?> enhanceConstructor(DynamicType.Builder<?> builder, ConstructorInterceptPoint constructorInterceptPoint) {

        MethodDelegation methodDelegation = constructorDelegation(constructorInterceptPoint.getConstructorInterceptor());

        return builder.constructor(constructorInterceptPoint.getConstructorMatcher()).intercept(SuperMethodCall.INSTANCE
                .andThen(methodDelegation)
        );

    }


    @Override
    public DynamicType.Builder<?> enhanceStaticMethods(DynamicType.Builder<?> builder, StaticMethodsInterceptPoint interceptPoint) {
        return builder;
    }

    @Override
    public DynamicType.Builder<?> enhanceMethods(DynamicType.Builder<?> builder, MethodsInterceptPoint methodsInterceptPoint) {
        MethodDelegation methodDelegation = methodsWithOverrideArgsDelegation(methodsInterceptPoint.getMethodsInterceptor());
        return builder.method(ElementMatchers.not(isStatic()).and(methodsInterceptPoint.getMethodsMatcher())).intercept(methodDelegation);
    }


    /**
     * resource enhance
     *
     * @param builder
     */
    private DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> enhanceSource(DynamicType.Builder<?> builder) {

        return builder.defineField(ENHANCE_CLASS_FIELD_NAME, TraceContext.class, ACC_PRIVATE | ACC_VOLATILE)
                .implement(EnhancedDefine.class)
                .intercept(FieldAccessor.ofField(ENHANCE_CLASS_FIELD_NAME));
    }


    private MethodDelegation constructorDelegation(Class<? extends ConstructorInterceptor> constructorInterceptor) {
        ConstructorInterceptor aroundInterceptor = interceptorFactory.newConstructorInterceptor(constructorInterceptor);
        return
                MethodDelegation.withDefaultConfiguration()
                        .to(new ConstructorInterceptCallable(aroundInterceptor));
    }

    private MethodDelegation methodsWithOverrideArgsDelegation(Class<? extends MethodsAroundInterceptor> aroundInterceptorClass) {
        MethodsAroundInterceptor aroundInterceptor = interceptorFactory.newMethodsInterceptor(aroundInterceptorClass);
        return MethodDelegation.withDefaultConfiguration()
                .withBinders(
                        Morph.Binder.install(OverrideCallable.class)
                )
                .to(new MethodsInterceptWithOverrideArgsCallable(aroundInterceptor));
    }

}
