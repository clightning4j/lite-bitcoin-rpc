package io.github.clightning4j.litebtc.exceptions;

public class BitcoinCoreException extends Exception {
  private int code;

  public BitcoinCoreException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
