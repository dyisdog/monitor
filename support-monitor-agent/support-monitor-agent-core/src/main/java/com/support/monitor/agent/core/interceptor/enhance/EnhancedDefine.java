package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.recorder.TraceIdRecorder;

/**
 * 增强，相当于目标添加 该接口
 *
 * @author 江浩
 */
public interface EnhancedDefine {

    /**
     * 给拦截目标设置属性
     *
     * @param traceIdRecorder :
     * @return : void
     * @author 江浩
     */
    void setEnhancedInstanceTraceIdRecorder(TraceIdRecorder traceIdRecorder);

    /**
     * 获取目标对象异步trace属性
     *
     * @return
     */
    TraceIdRecorder getEnhancedInstanceTraceIdRecorder();

}
