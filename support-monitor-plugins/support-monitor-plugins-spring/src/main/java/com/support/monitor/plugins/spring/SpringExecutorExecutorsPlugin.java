package com.support.monitor.plugins.spring;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.spring.interceptor.SpringPluginMethodInterceptor;

import java.util.concurrent.Callable;

import static net.bytebuddy.matcher.ElementMatchers.*;


/**
 * @author 江浩
 */
public class SpringExecutorExecutorsPlugin extends AbstractPluginDefine {


    @Override
    public void init(PluginDefineBuilder defineBuilder) {
        defineBuilder
                .pointName("spring-executor")
                .pointClass(named("org.springframework.scheduling.concurrent.ConcurrentTaskExecutor")
                        .or(named("org.springframework.core.task.SimpleAsyncTaskExecutor"))
                        .or(named("org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor"))
                        .or(named("org.springframework.core.task.support.TaskExecutorAdapter"))
                        .or(named("org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor"))
                        .or(named("org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler"))
                        .or(named("org.springframework.jca.work.WorkManagerTaskExecutor"))
                        .or(named("org.springframework.scheduling.commonj.WorkManagerTaskExecutor"))
                )
                .pointMethod(named("execute").and(takesArguments(1))
                                .and(takesArgument(0, Runnable.class).or(takesArgument(0, Callable.class)))
                        , SpringPluginMethodInterceptor.class)

                .pointMethod(named("submit"), SpringPluginMethodInterceptor.class);
    }
}
