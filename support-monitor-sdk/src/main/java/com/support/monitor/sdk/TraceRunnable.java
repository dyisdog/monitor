package com.support.monitor.sdk;

/**
 * java.lang.Thread
 *
 * @author 江浩
 */
public class TraceRunnable implements Runnable {
    final Runnable runnable;

    public TraceRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public static TraceRunnable of(Runnable runnable) {
        return new TraceRunnable(runnable);
    }


    @Override
    public void run() {
        this.runnable.run();
    }
}
