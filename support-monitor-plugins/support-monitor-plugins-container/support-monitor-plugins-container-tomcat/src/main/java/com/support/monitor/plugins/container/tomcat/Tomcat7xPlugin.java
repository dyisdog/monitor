package com.support.monitor.plugins.container.tomcat;

import com.support.monitor.agent.core.interceptor.supper.AbstractMethodAroundInterceptor;
import com.support.monitor.agent.core.plugin.AbstractPluginDefine;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * tomcat 7x 及以上版本插件支持
 * <p>
 * 容器内插件一般是Trace 传递的检查入口，其他的类型如 tcp类型RPC（dubbo，grpc） 等需要自定义自己的处理方式
 * </p>
 *
 * @author 江浩
 */
public class Tomcat7xPlugin extends AbstractPluginDefine {
    @Override
    public void init() {

        pointName("tomcat7x");

        pointClass(named("org.apache.catalina.core.StandardHostValve"));

        pointMethod(named("invoke").and(takesArguments(2))
                        .and(takesArgument(0, named("org.apache.catalina.connector.Request")))
                        .and(takesArgument(1, named("org.apache.catalina.connector.Response")))
                , AbstractMethodAroundInterceptor.class
        );
    }
}
