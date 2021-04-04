package io.github.clightning4j.litebtc.utils.okhttp;

import com.google.gson.reflect.TypeToken;
import io.github.clightning4j.litebtc.exceptions.UtilsExceptions;
import io.github.clightning4j.litebtc.model.generic.Configuration;
import io.github.clightning4j.litebtc.model.generic.Parameters;
import io.github.clightning4j.litebtc.utils.gson.JsonConverter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpFactory {

  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpFactory.class);

  private static HttpFactory SINGLETON;

  public static HttpFactory getInstance() {
    if (SINGLETON == null) SINGLETON = new HttpFactory();
    return SINGLETON;
  }

  private OkHttpClient.Builder builder;
  private OkHttpClient client;
  private JsonConverter converter;
  private Configuration configuration;

  private HttpFactory() {
    this.builder = new OkHttpClient.Builder();
    this.converter = new JsonConverter();
  }

  public void configureHttpClient(Configuration configuration) {
    builder.authenticator(new BitcoinAuthenticator(configuration));
    this.configuration = configuration;
    this.client =
        builder
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();
    LOGGER.debug("Http client configured");
  }

  public <T> T makeRequest(Parameters parameters) throws UtilsExceptions {
    if (parameters == null) {
      LOGGER.error("Parameters object null");
      throw new UtilsExceptions("Parameters object null");
    }
    if (client == null) {
      LOGGER.error("Http client not initialized, please call configureHttpClient");
      throw new RuntimeException("Http client not initialized, please call configureHttpClient");
    }
    String jsonBody = converter.serialization(parameters);
    LOGGER.debug("JSON body:\n" + jsonBody);
    Request request =
        new Request.Builder()
            .url(configuration.getUrl())
            .post(RequestBody.create(jsonBody, MEDIA_TYPE))
            .build();
    try {
      Response response = this.client.newCall(request).execute();
      if (!response.isSuccessful()) {
        LOGGER.error("Request error with code: " + response.code());
        throw new UtilsExceptions("Request error: " + response.code());
      }
      ResponseBody body = response.body();
      if (body != null) {
        String responseStr = body.string();
        LOGGER.debug("Response from bitcoind\n" + responseStr);
        Type type = new TypeToken<T>() {}.getType();
        return (T) converter.deserialization(responseStr, type.getClass());
      }
      LOGGER.error("body response null");
      throw new UtilsExceptions("body response null");
    } catch (IOException e) {
      LOGGER.error("Exception in makeRequest" + e.getLocalizedMessage());
      throw new UtilsExceptions(e.getCause());
    }
  }
}
