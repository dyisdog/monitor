package com.support.monitor.agent.core.context;

public interface RemoteTransmission<H> {

    void transmission(H handler, String key, String value);
}
