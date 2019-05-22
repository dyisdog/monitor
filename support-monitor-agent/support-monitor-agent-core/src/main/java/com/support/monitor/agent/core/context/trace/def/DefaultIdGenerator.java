package com.support.monitor.agent.core.context.trace.def;

import com.support.monitor.agent.core.context.trace.IdGenerator;

import java.util.UUID;

/**
 * 默认ID生成器
 *
 * @author 江浩
 */
public class DefaultIdGenerator implements IdGenerator {

    @Override
    public String transactionId() {

        //TODO
        //default
        return uuid();
    }

    @Override
    public String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
