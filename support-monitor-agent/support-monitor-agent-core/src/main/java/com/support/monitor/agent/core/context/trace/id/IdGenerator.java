package com.support.monitor.agent.core.context.trace.id;

/**
 * ID生成器
 *
 * @author 江浩
 */
public interface IdGenerator {

    /**
     * ID
     *
     * @return : java.lang.String
     * @author 江浩
     */
    String transactionId();

    /**
     * UUID
     *
     * @return : java.lang.String
     * @author 江浩
     */
    String uuid();
}
