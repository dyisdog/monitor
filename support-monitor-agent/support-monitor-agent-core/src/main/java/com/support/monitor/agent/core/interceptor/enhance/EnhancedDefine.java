package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.TraceContext;

/**
 * 增强，相当于目标添加 该接口
 *
 * @author 江浩
 */
public interface EnhancedDefine {

    /**
     * 给拦截目标设置属性
     *
     * @param traceContext :
     * @return : void
     * @author 江浩
     */
    void setEnhancedInstanceTraceContext(TraceContext traceContext);

    /**
     * 获取目标对象异步trace属性
     *
     * @return
     */
    TraceContext getEnhancedInstanceTraceContext();

}
