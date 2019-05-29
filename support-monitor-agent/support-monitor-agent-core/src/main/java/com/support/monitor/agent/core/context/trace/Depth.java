package com.support.monitor.agent.core.context.trace;

/**
 * 深度
 *
 * @author 江浩
 */
public interface Depth {

    /**
     * 获取当前ID
     *
     * @return : java.lang.Long
     * @author 江浩
     */
    Long nextDepth();

    /**
     * 获取当前深度
     *
     * @return : java.lang.Long
     * @author 江浩
     */
    Long getDepth();

}
