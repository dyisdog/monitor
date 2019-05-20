package com.support.monitor.plugins.servlet.interceptor;

import com.support.monitor.agent.core.interceptor.AbstractPluginInterceptor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * javax.Servlet 拦截器
 *
 * @author 江浩
 */
public class JavaxServletPluginInterceptor extends AbstractPluginInterceptor<HttpServletRequest> {

    @Override
    public ElementMatcher<? super TypeDescription> classInterceptor() {
        return ElementMatchers.named("javax.servlet.http.HttpServlet");
    }

    @Override
    public ElementMatcher<? super MethodDescription> methodInterceptor() {
        //指定参数
        return ElementMatchers.isMethod()
                .and(ElementMatchers.takesArguments(2))
                .and(ElementMatchers.takesArgument(0, ElementMatchers.named("javax.servlet.http.HttpServletRequest")))
                .and(ElementMatchers.takesArgument(1, ElementMatchers.named("javax.servlet.http.HttpServletResponse")));
    }

    @Override
    public void beforeMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes) {

        System.out.println("javax.servlet before : " + method);
    }

    @Override
    public Object afterMethod(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Object ret) {
        System.out.println("javax.servlet after : " + method);
        return null;
    }

    @Override
    public void handleMethodException(Class clazz, Method method, Object[] allArguments, Class<?>[] parameterTypes, Throwable t) {

    }

    @Override
    public void write(HttpServletRequest header) {
    }
}
