/**
 * A (another) Lite RPC wrapper for Bitcoin Core RPC 1.0, that permitted to have flexibility into
 * making the request with different versions of Bitcoin Core without lost compatibility during the
 * update.
 *
 * <p>Copyright (C) 2021 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * <p>This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with this program; if
 * not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */
package io.github.clightning4j.litebtc;

import io.github.clightning4j.litebtc.exceptions.BitcoinCoreException;
import io.github.clightning4j.litebtc.exceptions.LiteBitcoinRPCException;
import io.github.clightning4j.litebtc.exceptions.UtilsExceptions;
import io.github.clightning4j.litebtc.model.generic.Configuration;
import io.github.clightning4j.litebtc.model.generic.Parameters;
import io.github.clightning4j.litebtc.utils.gson.JsonConverter;
import io.github.clightning4j.litebtc.utils.okhttp.HttpFactory;

public class LiteBitcoinRPC {

  private Configuration configuration;
  private JsonConverter converter;

  public LiteBitcoinRPC() {
    converter = new JsonConverter();
  }

  public LiteBitcoinRPC(Configuration configuration) {
    this();
    this.configuration = configuration;
    HttpFactory.getInstance().configureHttpClient(configuration);
  }

  public LiteBitcoinRPC(String user, String pass, String url) {
    this();
    this.configuration = new Configuration(user, pass, url);
    HttpFactory.getInstance().configureHttpClient(configuration);
  }

  public LiteBitcoinRPC(String cookiePath) {
    this();
    this.configuration = new Configuration(cookiePath);
    HttpFactory.getInstance().configureHttpClient(configuration);
  }

  public <T> T makeBitcoinRequest(Parameters parameters, Class<T> clazz)
      throws LiteBitcoinRPCException, BitcoinCoreException {
    if (parameters == null) {
      throw new LiteBitcoinRPCException("Function argument null");
    }
    try {
      T result = HttpFactory.getInstance().makeRequest(parameters);
      String resultStr = converter.serialization(result);
      return (T) converter.deserialization(resultStr, clazz);
    } catch (UtilsExceptions utilsExceptions) {
      throw new LiteBitcoinRPCException(utilsExceptions.getCause());
    }
  }

  public <T> T makeBitcoinRequest(String parameter, Class<T> clazz)
      throws LiteBitcoinRPCException, BitcoinCoreException {
    Parameters parameters = new Parameters(parameter);
    return this.makeBitcoinRequest(parameters, clazz);
  }
}
