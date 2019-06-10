package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.spring.interceptor.SpringPluginMethodInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * spring mvc
 *
 * @author 江浩
 */
public class SpringWebMvcPlugin extends AbstractPluginDefine {

    @Override
    public void init(PluginDefineBuilder defineBuilder) {

        defineBuilder.pointName("spring-web-mvc")
                .pointClass(isAnnotatedWith(named(Controller.class.getName()))
                        .or(isAnnotatedWith(named(RestController.class.getName()))))
                .pointMethod(isAnnotatedWith(named(GetMapping.class.getName()))
                        , SpringPluginMethodInterceptor.class)
                .pointMethod(
                        isAnnotatedWith(named(PostMapping.class.getName()))
                        , SpringPluginMethodInterceptor.class)
                .pointMethod(
                        isAnnotatedWith(named(PutMapping.class.getName()))
                        , SpringPluginMethodInterceptor.class)
                .pointMethod(
                        isAnnotatedWith(named(DeleteMapping.class.getName()))
                        , SpringPluginMethodInterceptor.class)
                .pointMethod(
                        isAnnotatedWith(named(PatchMapping.class.getName()))
                        , SpringPluginMethodInterceptor.class);
    }


}
