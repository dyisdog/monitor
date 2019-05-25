package com.support.monitor.plugins.spring.annotation;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.spring.annotation.interceptor.SpringBeanMethodInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class SpringBeanPlugin extends AbstractPluginDefine {
    @Override
    public void init() {

        pointName("bean");
        pointClass(isAnnotatedWith(named("org.springframework.stereotype.Service")));
        pointMethod(any(),
                SpringBeanMethodInterceptor.class);

    }
}
