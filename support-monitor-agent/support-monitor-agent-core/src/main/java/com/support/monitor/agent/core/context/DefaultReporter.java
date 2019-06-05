package com.support.monitor.agent.core.context;

import com.alipay.common.tracer.core.context.span.SofaTracerSpanContext;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.support.monitor.agent.core.config.AgentConfig;
import com.support.monitor.commons.binder.transfer.TransferDefine;
import com.support.monitor.commons.binder.transfer.TransmitObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 默认的上报器
 * <p>以插件的方式加载传输器</p>
 * <p>TODO 上报数据需要一个缓冲区</p>
 *
 * @author 江浩
 */
@Slf4j
public class DefaultReporter implements Reporter {

    private TransferDefine transferDefine;

    private AgentConfig agentConfig;

    public DefaultReporter(TransferDefine transferDefine, AgentConfig agentConfig) {
        this.transferDefine = transferDefine;
        this.agentConfig = agentConfig;
    }

    @Override
    public String getReporterType() {
        return null;
    }

    @Override
    public void report(SofaTracerSpan sofaTracerSpan) {

        if (Objects.isNull(this.transferDefine)) {
            log.info("transferDefine is empty, span: {}", sofaTracerSpan);
            return;
        }

        if (Objects.isNull(sofaTracerSpan)) {
            return;
        }

        SofaTracerSpanContext sofaTracerSpanContext = sofaTracerSpan.getSofaTracerSpanContext();
        TransmitObject transmitObject = new TransmitObject()
                .setOperationName(sofaTracerSpan.getOperationName())
                .setTraceId(sofaTracerSpanContext.getTraceId())
                .setParentId(sofaTracerSpanContext.getParentId())
                .setSpanId(sofaTracerSpanContext.getSpanId())
                .setBizBaggage(sofaTracerSpanContext.getBizBaggage())
                .setSysBaggage(sofaTracerSpanContext.getSysBaggage())
                .setSampled(sofaTracerSpanContext.isSampled());

        //step1 setting config
        this.transferDefine.connection(this.agentConfig.getConfig());
        this.transferDefine.transmit(transmitObject);

    }

    @Override
    public void close() {

    }
}
