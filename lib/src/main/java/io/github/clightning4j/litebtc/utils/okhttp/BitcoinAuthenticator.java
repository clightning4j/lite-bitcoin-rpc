package io.github.clightning4j.litebtc.utils.okhttp;

import io.github.clightning4j.litebtc.model.generic.Configuration;
import java.io.IOException;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BitcoinAuthenticator implements Authenticator {

  private Configuration configuration;

  public BitcoinAuthenticator(Configuration configuration) {
    this.configuration = configuration;
  }

  @Nullable
  @Override
  public Request authenticate(@Nullable Route route, @NotNull Response response)
      throws IOException {
    String credential = Credentials.basic(configuration.getUser(), configuration.getPass());
    return response.request().newBuilder().header("Authorization", credential).build();
  }
}
