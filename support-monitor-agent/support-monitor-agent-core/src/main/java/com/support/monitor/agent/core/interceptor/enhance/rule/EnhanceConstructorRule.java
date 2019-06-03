package com.support.monitor.agent.core.interceptor.enhance.rule;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.EnhanceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.callable.ConstructorInterceptCallable;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;

import java.util.Objects;

import static net.bytebuddy.jar.asm.Opcodes.ACC_PRIVATE;
import static net.bytebuddy.jar.asm.Opcodes.ACC_VOLATILE;

/**
 * 构造器增强
 *
 * @author 江浩
 */
public class EnhanceConstructorRule extends AbstractEnhanceRule<ConstructorInterceptor> {


    private static final String ENHANCE_CLASS_FIELD_NAME = "_$ENHANCE_CLASS_FIELD_NAME";

    @Override
    protected DynamicType.Builder<?> enhanceDefine(DynamicType.Builder<?> builder, ConstructorInterceptor interceptPoint, EnhanceContext enhanceContext) {

        //构造器的时候添加一个接口，用于传递上一个活跃的span
        builder = enhanceSourceDefine(builder, interceptPoint, enhanceContext);

        MethodDelegation methodDelegation = constructorDelegation(interceptPoint);
        return builder.constructor(enhanceContext.getMethodDescription()).intercept(SuperMethodCall.INSTANCE
                .andThen(methodDelegation)
        );
    }

    private MethodDelegation constructorDelegation(ConstructorInterceptor constructorInterceptor) {
        return
                MethodDelegation.withDefaultConfiguration()
                        .to(new ConstructorInterceptCallable(constructorInterceptor));
    }


    private DynamicType.Builder<?> enhanceSourceDefine(DynamicType.Builder<?> builder, Object interceptPoint, EnhanceContext enhanceContext) {

        //TODO 有优化的可能不
        TypeList.Generic generics = builder.make().getTypeDescription().getInterfaces();
        if (!Objects.isNull(generics) && !generics.isEmpty()) {
            for (int i = 0; i < generics.size(); i++) {
                if (generics.get(i).getTypeName().equalsIgnoreCase(EnhancedDefine.class.getName())) {
                    return builder;
                }
            }
        }

        //EnhanceDefine handler param of TraceIdRecorder
        return builder.defineField(ENHANCE_CLASS_FIELD_NAME, SofaTracerSpan.class, ACC_PRIVATE | ACC_VOLATILE)
                .implement(EnhancedDefine.class)
                .intercept(FieldAccessor.ofField(ENHANCE_CLASS_FIELD_NAME));

    }


}
