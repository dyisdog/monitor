package com.monitor.agent.core.test;

import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

public class RuleTest {

    @Test
    public void classTest() {
        try {
            Class<?> aClass = ClassUtils.getClass("com.support.monitor.plugins.demo.interceptor.ServiceInterceptor");

            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
