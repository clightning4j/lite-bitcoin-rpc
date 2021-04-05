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
package io.github.clightning4j.litebtc.utils.gson;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.github.clightning4j.litebtc.exceptions.UtilsExceptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;

public class JsonConverter {

  private static final String ENCODING_DEFAULT = "UTF-8";

  private String patternFormat = "dd-MM-yyyy HH:mm:ss";
  private GsonBuilder gsonBuilder;
  private Gson gson;

  public JsonConverter() {
    this.gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gsonBuilder.setDateFormat(patternFormat);
    gsonBuilder.registerTypeAdapter(Date.class, new MyDateTypeAdapter());
    this.gson = gsonBuilder.create();
  }

  public String serialization(Object o) {
    return gson.toJson(o);
  }

  public Object deserialization(InputStream inputStream, Type type) throws UtilsExceptions {
    if (inputStream == null || type == null) {
      throw new IllegalArgumentException("Arguments are/is null");
    }
    Object response;
    JsonReader reader;
    try {
      reader = new JsonReader(new InputStreamReader(inputStream, ENCODING_DEFAULT));
      response = gson.fromJson(reader, type);
    } catch (Exception ex) {
      throw new UtilsExceptions(
          "Exception inside the method deserialization to "
              + this.getClass().getSimpleName()
              + "\nMessage: "
              + ex.getLocalizedMessage(),
          ex.getCause());
    }
    return response;
  }

  public Object deserialization(String jsonForm, Type type) throws UtilsExceptions {
    if ((jsonForm == null || jsonForm.isEmpty()) || type == null) {
      throw new IllegalArgumentException("Arguments are/is null");
    }
    Object response;
    try {
      response = gson.fromJson(jsonForm, type);
    } catch (Exception ex) {
      throw new UtilsExceptions(
          "Exception inside the method deserialization to "
              + this.getClass().getSimpleName()
              + "\nMessage: "
              + ex.getLocalizedMessage(),
          ex.getCause());
    }
    return response;
  }

  protected class MyDateTypeAdapter extends TypeAdapter<Date> {
    @Override
    public void write(JsonWriter out, Date value) throws IOException {
      if (value == null) {
        out.nullValue();
      } else {
        out.value(value.getTime() / 1000);
      }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
      if (in != null) {
        return new Date(in.nextLong() * 1000);
      } else {
        return null;
      }
    }
  }
}
