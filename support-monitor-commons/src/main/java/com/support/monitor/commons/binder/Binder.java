package com.support.monitor.commons.binder;

import java.util.concurrent.atomic.AtomicReference;

public interface Binder<T> {

    AtomicReference<T> get();

    void remove();
}
