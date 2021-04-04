package io.github.clightning4j.litebtc.exceptions;

public class LiteBitcoinRPCException extends Exception {
  public LiteBitcoinRPCException() {}

  public LiteBitcoinRPCException(String message) {
    super(message);
  }

  public LiteBitcoinRPCException(String message, Throwable cause) {
    super(message, cause);
  }

  public LiteBitcoinRPCException(Throwable cause) {
    super(cause);
  }

  public LiteBitcoinRPCException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
