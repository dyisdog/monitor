package com.support.monitor.agent.collect;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Morph;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 方法拦截委托实现
 *
 * @author 江浩
 */
public class AgentMethodInterceptor {

    private static Logger LOG = LoggerFactory.getLogger(AgentMethodInterceptor.class);

    @RuntimeType
    public static Object intercept(
            @Origin Class<?> clazz,
            @Origin Method method,
            @AllArguments Object[] allArguments,
            @Morph OverrideCallable callable) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = null;
        //TODO
        try {
            result = callable.call(ArrayUtils.add(allArguments, "tid:xxxxxx"));
        } catch (Exception e) {
            LOG.error("agent interceptor exception: {}->{}({})", clazz.getName(), method.getName(), allArguments);
            throw e;
        }
        LOG.info("agent interceptor: {} use {} ms", format(clazz, method, allArguments), stopWatch.getTime());
        return result;
    }

    private static String format(Class<?> clazz, Method method, Object[] allArguments) {
        return String.format("%s.%s(%s)", clazz.getName(), method.getName(), StringUtils.join(allArguments, ","));
    }


}