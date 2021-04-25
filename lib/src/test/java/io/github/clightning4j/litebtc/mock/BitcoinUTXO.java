package io.github.clightning4j.litebtc.mock;

import com.google.gson.annotations.SerializedName;

public class BitcoinUTXO {

  @SerializedName("bestblock")
  private String bestBlock;

  public String getBestBlock() {
    return bestBlock;
  }
}
