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

public class Configuration {

  private String user;
  private String pass;
  private String url;
  private String pathBitcoin;
  private String cookiePath;

  public Configuration(String user, String pass, String url) {
    this.user = user;
    this.pass = pass;
    this.url = url;
  }

  public Configuration(String cookiePath) {
    this.cookiePath = cookiePath;
    this.parseCookie();
  }

  private void parseCookie() {}

  // Getter and setter

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }

  public String getPathBitcoin() {
    return pathBitcoin;
  }

  public String getCookiePath() {
    return cookiePath;
  }

  public String getUrl() {
    return url;
  }
}
