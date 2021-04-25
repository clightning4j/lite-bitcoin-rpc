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
package io.github.clightning4j.litebtc.model.generic;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Parameters {

  @SerializedName("jsonrpc")
  private String rpcversio = "1.0";

  private int id = (int) ((Math.random() % 100) + 1);
  private String method;
  private List<Object> params;

  public Parameters(String method) {
    this.method = method;
    this.params = new ArrayList<>();
  }

  public void addParameter(String name, Object value) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name argument is invalid: " + name);
    }
    this.params.add(value);
  }
}
