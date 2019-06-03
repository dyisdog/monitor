package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.TraceContext;
import com.support.monitor.agent.core.interceptor.ConstructorInterceptor;
import com.support.monitor.agent.core.interceptor.enhance.EnhancedDefine;
import lombok.Getter;

import java.util.Objects;

/**
 * 构造器调用注入当前SpanRecorder
 *
 * @author 江浩
 */
@Getter
public class AbstractConstructorInterceptor implements ConstructorInterceptor {

    protected TraceContext traceContext;

    public AbstractConstructorInterceptor(TraceContext traceContext) {
        this.traceContext = traceContext;
    }

    @Override
    public void onConstruct(EnhancedDefine enhancedDefine, Object[] allArguments) {

        if (!Objects.isNull(traceContext)) {
            enhancedDefine.setEnhancedInstanceTraceContext(traceContext);
        }

    }
}
