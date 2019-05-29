package com.support.monitor.agent.core.interceptor.supper;

import com.support.monitor.agent.core.context.trace.ClientAttach;
import com.support.monitor.agent.core.context.trace.Header;
import com.support.monitor.agent.core.context.trace.Trace;
import com.support.monitor.agent.core.context.trace.TraceContext;
import com.support.monitor.agent.core.context.trace.recorder.TraceRootRecorder;
import com.support.monitor.commons.binder.utils.Assert;
import lombok.Getter;

/**
 * 远程调用
 *
 * @author 江浩
 */
@Getter
public abstract class AbstractRemoteMethodInterceptor<H> extends AbstractMethodAroundInterceptor {
    public AbstractRemoteMethodInterceptor(TraceContext traceContext) {
        super(traceContext);
    }

    private ClientAttach<H> clientAttach;

    private void defaultAttach(Trace trace, H httpRequest) {
        TraceRootRecorder traceIdRecorder = trace.currentTraceRootRecorder();
        this.clientAttach.setAttach(httpRequest, Header.TRACE_ID.getKey(), traceIdRecorder.getTraceId().id());
        this.clientAttach.setAttach(httpRequest, Header.TRACE_DEPTH.getKey(), traceIdRecorder.getTraceId().getDepth().getDepth().toString());
    }

    public void attachHandle(Trace trace, ClientAttach<H> clientAttach) {
        Assert.requireNonNull(trace, "trace 不能为空");
        this.clientAttach = clientAttach;
        Assert.requireNonNull(this.clientAttach, "clientAttach 不能为空");
        H h = clientAttach.getProvider();
        Assert.requireNonNull(h, "attach provider 不能为空");
        this.defaultAttach(trace, clientAttach.getProvider());

    }

}
