package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.span.SofaTracerSpan;

public interface ActiveTracerSpan {

    /**
     * 添加span信息
     *
     * @param sofaTracerSpan :
     * @return : void
     * @author 江浩
     */
    void addLast(SofaTracerSpan sofaTracerSpan);

    Integer size();

    /**
     * 获取最后一个，但是不会删除
     *
     * @return : com.alipay.common.tracer.core.span.SofaTracerSpan
     * @author 江浩
     */
    SofaTracerSpan getLast();

    /**
     * 停止最后一个信息
     *
     * @return : com.alipay.common.tracer.core.span.SofaTracerSpan
     * @author 江浩
     */
    SofaTracerSpan stopLast();
}
