package com.support.monitor.sdk;

import java.lang.annotation.*;

/**
 * 标注追踪类跟方法
 *
 * @author 江浩
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Trace {
}
