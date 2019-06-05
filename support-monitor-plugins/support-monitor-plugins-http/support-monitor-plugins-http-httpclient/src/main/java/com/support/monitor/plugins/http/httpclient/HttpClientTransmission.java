package com.support.monitor.plugins.http.httpclient;

import com.support.monitor.agent.core.context.RemoteTransmission;
import org.apache.http.HttpRequest;

public class HttpClientTransmission implements RemoteTransmission<HttpRequest> {
    @Override
    public void transmission(HttpRequest handler, String key, String value) {
        handler.setHeader(key, value);
    }
}
