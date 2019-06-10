package com.support.monitor.agent.core.interceptor.supper.async;

import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.InterceptContext;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;
import lombok.Getter;

import java.util.Objects;

/**
 * 方法拦截器
 * <p>异步获取当前活跃span只是根据当前的Object中来获取</p>
 * <p>
 * 1.异步的传递目前只有构造器传递
 * </p>
 *
 * @author 江浩
 */
@Getter
public abstract class AbstractAsyncMethodAroundInterceptor extends AbstractMethodAroundInterceptor {

    public AbstractAsyncMethodAroundInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    @Override
    public void before(InterceptContext interceptContext) {
        if (Objects.isNull(interceptContext)) {
            return;
        }
        Object object = interceptContext.getTarget();
        if (Objects.isNull(object)) {
            return;
        }
        if (object instanceof EnhancedDefine) {
            EnhancedDefine enhancedDefine = (EnhancedDefine) object;
            SofaTracerSpan sofaTracerSpan = enhancedDefine.getEnhancedInstanceTraceContext();
            super.currentTracerSpan(sofaTracerSpan, interceptContext);
        }

    }
}
