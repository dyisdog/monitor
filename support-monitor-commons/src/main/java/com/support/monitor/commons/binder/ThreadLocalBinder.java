package com.support.monitor.commons.binder;

import java.util.concurrent.atomic.AtomicReference;

/**
 * thread local
 *
 * @author 江浩
 */
public class ThreadLocalBinder<T> implements Binder<T> {


    private final ThreadLocal<AtomicReference<T>> threadLocal = new NamedThreadLocal<AtomicReference<T>>("ThreadLocalBinder") {
        @Override
        protected AtomicReference<T> initialValue() {
            return new AtomicReference<>();
        }
    };

    @Override
    public AtomicReference<T> get() {
        return threadLocal.get();
    }

    @Override
    public void remove() {

    }
}
