package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.spring.interceptor.SpringPluginMethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * spring bean
 *
 * @author 江浩
 */
public class SpringBeanPlugin extends AbstractPluginDefine {
    @Override
    public void init(PluginDefineBuilder defineBuilder) {

        defineBuilder
                .pointName("spring-bean")
                //@Service
                .pointClass(isAnnotatedWith(named(Service.class.getName()))
                        //@Component
                        .or(isAnnotatedWith(named(Component.class.getName())))
                        //@Repository
                        .or(isAnnotatedWith(named(Repository.class.getName())))
                        .or(isAnnotatedWith(named(Bean.class.getName())))
                )
                .pointMethod(any(),
                        SpringPluginMethodInterceptor.class);


    }
}
