package io.github.clightning4j.litebtc.utils.okhttp;

import com.google.gson.reflect.TypeToken;
import io.github.clightning4j.litebtc.exceptions.BitcoinCoreException;
import io.github.clightning4j.litebtc.exceptions.UtilsExceptions;
import io.github.clightning4j.litebtc.model.generic.Configuration;
import io.github.clightning4j.litebtc.model.generic.Parameters;
import io.github.clightning4j.litebtc.model.generic.ResponseWrapper;
import io.github.clightning4j.litebtc.utils.gson.JsonConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
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
            .connectTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)
            .build();
    LOGGER.debug("Http client configured");
  }

  public <T> T makeRequest(Parameters parameters) throws UtilsExceptions, BitcoinCoreException {
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
      ResponseBody body = response.body();
      if (!response.isSuccessful()) {
        String message = "";
        LOGGER.error("Request error with code: " + response.code());
        if (body != null) {
          message = body.string();
          LOGGER.debug("Response Body\n" + message);
        }
        throw new UtilsExceptions("Request error: " + response.code() + "Body: " + message);
      }
      if (body != null) {
        String responseStr = body.string();
        LOGGER.debug("Response from bitcoind\n" + responseStr);
        Type type = new TypeToken<ResponseWrapper<T>>() {}.getType();
        ResponseWrapper<T> wrapper =
            (ResponseWrapper<T>) converter.deserialization(responseStr, type);
        if (wrapper.getError() != null) {
          throw new BitcoinCoreException(wrapper.getError());
        }
        String result = converter.serialization(wrapper.getResult());
        LOGGER.error("Result conversion is: \n" + result);
        return wrapper.getResult();
      }
      LOGGER.error("body response null");
      throw new UtilsExceptions("body response null");
    } catch (IOException e) {
      LOGGER.error("Exception during the request request: " + e.getLocalizedMessage());
      throw new UtilsExceptions(e.getCause());
    }
  }

  public <T> T makeRequestTest(Parameters parameters) throws UtilsExceptions {
    if (parameters == null) {
      LOGGER.error("Parameters object null");
      throw new UtilsExceptions("Parameters object null");
    }
    Authenticator.setDefault(
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(
                configuration.getUser(), configuration.getPass().toCharArray());
          }
        });

    try {
      HttpURLConnection httpcon =
          (HttpURLConnection) new URL(configuration.getUrl()).openConnection();
      httpcon.setDoOutput(true);
      httpcon.setRequestProperty("Content-Type", "application/json");
      httpcon.setRequestProperty("Accept", "application/json");
      httpcon.setRequestMethod("POST");
      httpcon.connect();

      OutputStream stream = httpcon.getOutputStream();
      String jsonBody = converter.serialization(parameters);
      LOGGER.debug("JSON body:\n" + jsonBody);
      stream.write(jsonBody.getBytes());
      int code = httpcon.getResponseCode();
      boolean isError = code >= 400 && code <= 500;
      String text = "";
      if (isError) {
        LOGGER.error("Eroror with code: " + code);
        throw new UtilsExceptions("error");
      } else {
        InputStream inputStream = httpcon.getInputStream();
        if (inputStream != null) {
          text = new String(inputStream.readAllBytes());
        }
      }
      LOGGER.error("Response from bitcoind\n" + text);
      Type type = new TypeToken<T>() {}.getType();
      return (T) converter.deserialization(text, type.getClass());
    } catch (IOException e) {
      LOGGER.error("Exception in makeRequest" + e.getLocalizedMessage());
      throw new UtilsExceptions(e.getCause());
    }
  }
}
