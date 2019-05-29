package com.support.monitor.agent.core.context.trace;

/**
 * remote quest header
 * <p>
 * 应该需要封装更多的信息TODO
 * 1.第一个版本传递ID过去能使链路打通
 * </p>
 *
 * @author 江浩
 */
public enum Header {

    TRACE_ID("Remote-Trace-Id"),

    TRACE_DEPTH("Remote-Trace-Depth"),

    ;

    private String key;

    Header(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
