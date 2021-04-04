package io.github.clightning4j.litebtc.utils;

import okhttp3.OkHttpClient;

public class HttpFactory {

    private static HttpFactory SINGLETON;

    public static HttpFactory getInstance() {
        if (SINGLETON == null)
            SINGLETON = new HttpFactory();
      return SINGLETON;
    }

    OkHttpClient client;

    private HttpFactory() {
        client = new OkHttpClient();
    }

    public void configureHttpClient() {}

    public void makeRequest() {}
}
