package com.support.monitor.agent.core.context.trace.id;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 默认ID生成器
 *
 * @author 江浩
 */
public class DefaultIdGenerator implements IdGenerator {

    private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    @Override
    public String transactionId() {
        //TODO 分布式ID

        //return String.valueOf(Math.abs(threadLocalRandom.nextLong()));
        return uuid();
    }

    @Override
    public String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
