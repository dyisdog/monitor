package com.support.monitor.plugins.spring.annotation;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;

import static net.bytebuddy.matcher.ElementMatchers.isAnnotatedWith;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * spring annotation 5x 版本
 *
 * @author 江浩
 */
public class SpringAnnotationPlugin extends AbstractPluginDefine {

    private static final String SPRING_CONTROLLER = "org.springframework.stereotype.Controller";
    private static final String SPRING_REST_CONTROLLER = "org.springframework.web.bind.annotation.RestController";

    @Override
    public void init() {
        pointName("spring-annotation");
        pointClass(isAnnotatedWith(named(SPRING_CONTROLLER)).or(isAnnotatedWith(named(SPRING_REST_CONTROLLER))));

        pointMethod(isAnnotatedWith(named("org.springframework.web.bind.annotation.GetMapping"))
                        .or(isAnnotatedWith(named("org.springframework.web.bind.annotation.PostMapping")))
                        .or(isAnnotatedWith(named("org.springframework.web.bind.annotation.PutMapping")))
                        .or(isAnnotatedWith(named("org.springframework.web.bind.annotation.DeleteMapping")))
                        .or(isAnnotatedWith(named("org.springframework.web.bind.annotation.PatchMapping"))),
                SpringAnnotationMethodInterceptor.class);
    }
}
