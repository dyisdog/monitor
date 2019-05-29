package com.support.monitor.agent.core.context.trace.id;

import com.support.monitor.agent.core.context.trace.Depth;

/**
 * 胸口疼 * 传递的ID
 *
 * @author 江浩
 */
public interface TraceId {

    /**
     * 唯一标识ID
     *
     * @return
     */
    String id();

    /**
     * 链路执行的开始时间
     *
     * @return : long
     * @author 江浩
     */
    long getStartTime();

    /**
     * traceId 传递深度
     * <p>
     * 该深度对于远程透传并没有太大意义？
     * </p>
     *
     * @return : Depth
     * @author 江浩
     */
    Depth getDepth();


}
