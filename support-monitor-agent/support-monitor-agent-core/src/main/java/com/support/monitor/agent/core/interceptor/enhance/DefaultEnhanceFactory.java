package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.AroundInterceptor;
import com.support.monitor.agent.core.interceptor.InterceptorFactory;
import com.support.monitor.agent.core.interceptor.delegation.MethodsInterceptWithOverrideArgs;
import com.support.monitor.agent.core.interceptor.delegation.OverrideCallable;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Morph;

import static net.bytebuddy.jar.asm.Opcodes.ACC_PRIVATE;
import static net.bytebuddy.jar.asm.Opcodes.ACC_VOLATILE;

/**
 * 默认method delegation构建工厂
 *
 * @author
 */
public class DefaultEnhanceFactory implements EnhanceFactory {


    private static final String ENHANCE_CLASS_FIELD_NAME = "_$ENHANCE_CLASS_FIELD_NAME";

    private InterceptorFactory interceptorFactory;

    public DefaultEnhanceFactory(InterceptorFactory interceptorFactory) {
        this.interceptorFactory = interceptorFactory;
    }

    @Override
    public DynamicType.Builder<?> enhance(DynamicType.Builder<?> builder, EnhanceContext enhanceContext) {
        //static method ?
        // method ?
        builder = enhanceSource(builder);

        builder = enhanceMethod(builder, enhanceContext);

        return builder;
    }

    /**
     * method enhance
     *
     * @param builder
     * @param enhanceContext
     */
    private DynamicType.Builder<?> enhanceMethod(DynamicType.Builder<?> builder, EnhanceContext enhanceContext) {
        //TODO
        MethodDelegation methodDelegation = methodsWithOverrideArgsDelegation(enhanceContext.getInterceptorClass());
        return builder.method(enhanceContext.getMethodDescription()).intercept(methodDelegation);
    }

    /**
     * resource enhance
     *
     * @param builder
     */
    private DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> enhanceSource(DynamicType.Builder<?> builder) {
        return builder.defineField(ENHANCE_CLASS_FIELD_NAME, Object.class, ACC_PRIVATE | ACC_VOLATILE)
                .implement(EnhancedInstance.class)
                .intercept(FieldAccessor.ofField(ENHANCE_CLASS_FIELD_NAME));
    }


    public MethodDelegation methodsWithOverrideArgsDelegation(Class<? extends AroundInterceptor> aroundInterceptorClass) {

        AroundInterceptor aroundInterceptor = interceptorFactory.newInterceptor(aroundInterceptorClass);

        return MethodDelegation.withDefaultConfiguration()
                // 覆写参数
                .withBinders(
                        Morph.Binder.install(OverrideCallable.class)
                )
                .to(new MethodsInterceptWithOverrideArgs(aroundInterceptor));
    }
}
