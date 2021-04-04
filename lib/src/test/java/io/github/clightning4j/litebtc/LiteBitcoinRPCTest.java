package io.github.clightning4j.litebtc;

import io.github.clightning4j.litebtc.model.bitcoin.BlockchainInfo;
import junit.framework.TestCase;
import org.junit.Test;

public class LiteBitcoinRPCTest {

  private LiteBitcoinRPC bitcoinRPC;

  public LiteBitcoinRPCTest() {
    this.bitcoinRPC =
        new LiteBitcoinRPC(
            "vincent",
            "vincent",
            "http://127.0.0.1:8332/");
  }

  @Test
  public void getBlockchainInfo() {
    try {
      BlockchainInfo info = bitcoinRPC.makeBitcoinRequest("getblockchaininfo");
      TestCase.assertEquals(info.getChain(), "regtest");
    } catch (Exception e) {
      e.printStackTrace();
      TestCase.fail(e.getLocalizedMessage());
    }
  }
}
