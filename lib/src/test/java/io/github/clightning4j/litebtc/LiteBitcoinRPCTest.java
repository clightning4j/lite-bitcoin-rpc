package io.github.clightning4j.litebtc;

import io.github.clightning4j.litebtc.model.BlockchainInfo;
import junit.framework.TestCase;
import org.junit.Test;

public class LiteBitcoinRPCTest {

  private LiteBitcoinRPC bitcoinRPC;

  public LiteBitcoinRPCTest() {
    // vincent:2390967d2d58fcccddc7ad6488a0082e$58ab7e42ac7338c50c340f859aa0e8986ffb9beb2c77aeaf1e9e89585a8181cf
    this.bitcoinRPC =
        new LiteBitcoinRPC(
            "vincent",
            "2390967d2d58fcccddc7ad6488a0082e$58ab7e42ac7338c50c340f859aa0e8986ffb9beb2c77aeaf1e9e89585a8181cf",
            "http://localhost:8332");
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
