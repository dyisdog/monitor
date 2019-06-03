//package com.support.monitor.plugins.http.httpclient;
//
//import com.support.monitor.agent.core.context.trace.ClientAttach;
//import lombok.Getter;
//import org.apache.http.HttpRequest;
//
//import java.util.Objects;
//
///**
// * httpClient header set
// *
// * @author 江浩
// */
//@Getter
//public class HttpClientHeader implements ClientAttach<HttpRequest> {
//    private HttpRequest httpRequest;
//
//    public HttpClientHeader(HttpRequest httpRequest) {
//        this.httpRequest = httpRequest;
//    }
//
//
//    @Override
//    public void setAttach(HttpRequest attach, String key, String value) {
//        if (!Objects.isNull(attach)) {
//            attach.setHeader(key, value);
//        }
//    }
//
//    @Override
//    public HttpRequest getProvider() {
//        return this.httpRequest;
//    }
//}
