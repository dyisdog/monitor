package com.support.monitor.plugins.spring.annotation;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.spring.annotation.interceptor.SpringWebMvcMethodInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

public class SpringWebMvcPlugin extends AbstractPluginDefine {

    @Override
    public void init() {
        pointName("mvc");
        pointClass(
                isAnnotatedWith(named(Controller.class.getName()))
                        .or(isAnnotatedWith(named(RestController.class.getName())))
        );

        pointMethod(
                isAnnotatedWith(named(GetMapping.class.getName()))
                , SpringWebMvcMethodInterceptor.class);

        pointMethod(
                isAnnotatedWith(named(PostMapping.class.getName()))
                , SpringWebMvcMethodInterceptor.class);

        pointMethod(
                isAnnotatedWith(named(PutMapping.class.getName()))
                , SpringWebMvcMethodInterceptor.class);

        pointMethod(
                isAnnotatedWith(named(DeleteMapping.class.getName()))
                , SpringWebMvcMethodInterceptor.class);

        pointMethod(
                isAnnotatedWith(named(PatchMapping.class.getName()))
                , SpringWebMvcMethodInterceptor.class);
    }


}
