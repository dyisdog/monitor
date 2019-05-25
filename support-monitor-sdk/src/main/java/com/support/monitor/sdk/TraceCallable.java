package com.support.monitor.sdk;

import java.util.concurrent.Callable;

/**
 * java.util.concurrent.Callable
 *
 * @author 江浩
 */
public class TraceCallable<V> implements Callable<V> {
    final Callable<V> callable;

    public static <V> TraceCallable of(Callable<V> callable) {
        return new TraceCallable<V>(callable);
    }

    public TraceCallable(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {
        return callable.call();
    }
}
