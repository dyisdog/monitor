package com.support.monitor.transfer.webflux;

import com.alibaba.fastjson.JSONObject;
import com.support.monitor.commons.binder.ConfigHandler;
import com.support.monitor.commons.binder.transfer.TransferDefine;
import com.support.monitor.commons.binder.transfer.TransmitObject;
import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * webFlux传输
 *
 * @author 江浩
 */
@Slf4j
public class WebfluxTransfer implements TransferDefine {

    //使用OkHttp
    private OkHttpClient okHttpClient = new OkHttpClient();

    private Config config;

    private ConfigHandler configHandler;

    @Override
    public void transmit(TransmitObject transmitObject) {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                JSONObject.toJSONString(transmitObject)
        );
        String responseJson = this.postResponseJson(configHandler.getString(Key.ADDRESS, null), requestBody);
    }

    @Override
    public void connection(Config config) {
        this.config = config;
        configHandler = ConfigHandler.builder(this.config);
    }

    @Override
    public void disConnection() {
    }

    public String postResponseJson(String url, RequestBody requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            ResponseBody response = call.execute().body();
            return Objects.isNull(response) ? null : response.string();
        } catch (IOException e) {
            log.info("agent WebfluxTransfer error: {}", e);
            return null;
        }
    }
}
