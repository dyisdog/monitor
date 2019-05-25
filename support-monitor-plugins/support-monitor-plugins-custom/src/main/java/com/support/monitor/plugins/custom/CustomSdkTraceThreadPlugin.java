package com.support.monitor.plugins.custom;

import com.support.monitor.agent.core.plugin.AbstractPluginDefine;
import com.support.monitor.plugins.custom.interceptor.CustomSdkConstructorInterceptor;
import com.support.monitor.plugins.custom.interceptor.TraceThreadExecuteInterceptor;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 自定义线程拦截
 *
 * @author 江浩
 */
public class CustomSdkTraceThreadPlugin extends AbstractPluginDefine {

    private static final String SKY_RUNNABLE = "org.apache.skywalking.apm.toolkit.trace.RunnableWrapper";

    private static final String SKY_CALLABLE = "org.apache.skywalking.apm.toolkit.trace.CallableWrapper";


//    private static final ElementMatcher.Junction<NamedElement> DEFAULT_ASYNC_TASK_EXECUTOR =
//            named("org.springframework.scheduling.concurrent.ConcurrentTaskExecutor")
//                    .or(named("org.springframework.core.task.SimpleAsyncTaskExecutor"))
//                    .or(named("org.springframework.scheduling.quartz.SimpleThreadPoolTaskExecutor"))
//                    .or(named("org.springframework.core.task.support.TaskExecutorAdapter"))
//                    .or(named("org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor"))
//                    .or(named("org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler"))
//                    .or(named("org.springframework.jca.work.WorkManagerTaskExecutor"));


    @Override
    public void init() {

        pointName("custom-sdk-async");
        pointClass(named(SKY_RUNNABLE));
        pointConstructor(any(), CustomSdkConstructorInterceptor.class);
        pointMethod(named("run"), TraceThreadExecuteInterceptor.class);


    }
}
