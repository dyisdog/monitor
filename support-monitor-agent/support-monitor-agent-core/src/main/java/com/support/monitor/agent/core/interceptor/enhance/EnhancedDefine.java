package com.support.monitor.agent.core.interceptor.enhance;

import com.support.monitor.agent.core.context.trace.AsyncTraceContext;

/**
 * 增强，相当于目标添加 该接口
 *
 * @author 江浩
 */
public interface EnhancedDefine {

    void setEnhancedInstanceTraceContext(AsyncTraceContext asyncTraceContext);

    AsyncTraceContext getEnhancedInstanceTraceContext();

}
