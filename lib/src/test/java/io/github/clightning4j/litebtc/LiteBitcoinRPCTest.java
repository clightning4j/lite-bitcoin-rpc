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
import io.github.clightning4j.litebtc.mock.BitcoinEstimateFee;
import io.github.clightning4j.litebtc.mock.BitcoinUTXO;
import io.github.clightning4j.litebtc.model.bitcoin.BlockchainInfo;
import io.github.clightning4j.litebtc.model.generic.Parameters;
import junit.framework.TestCase;
import org.junit.Test;

public class LiteBitcoinRPCTest {

  private LiteBitcoinRPC bitcoinRPC;

  public LiteBitcoinRPCTest() {
    this.bitcoinRPC = new LiteBitcoinRPC("sandbox", "sandbox", "http://127.0.0.1:18333/");
  }

  @Test
  public void getBlockchainInfo() {
    try {
      BlockchainInfo info =
          bitcoinRPC.makeBitcoinRequest("getblockchaininfo", BlockchainInfo.class);
      TestCase.assertEquals(info.getChain(), "regtest");
    } catch (Exception e) {
      e.printStackTrace();
      TestCase.fail(e.getLocalizedMessage());
    }
  }

  @Test
  public void estimateFeeRateWithError() {
    Parameters parameters = new Parameters("estimatesmartfee");
    parameters.addParameter("conf_target", 6);
    try {
      BitcoinEstimateFee feee = bitcoinRPC.makeBitcoinRequest(parameters, BitcoinEstimateFee.class);
      TestCase.assertFalse(feee.getErrors().isEmpty());
    } catch (LiteBitcoinRPCException | BitcoinCoreException e) {
      TestCase.fail(e.getMessage());
    }
  }

  @Test(expected = BitcoinCoreException.class)
  public void getUTXOWithError() throws BitcoinCoreException, LiteBitcoinRPCException {
    Parameters parameters = new Parameters("gettxout");
    parameters.addParameter("txid", "txid");
    parameters.addParameter("n", 0);
    bitcoinRPC.makeBitcoinRequest(parameters, BitcoinUTXO.class);
    TestCase.fail("Expected BitcoinCoreException but we not receive it");
  }

  @Test
  public void getUTXOWithErrorWithMessage() throws LiteBitcoinRPCException {
    Parameters parameters = new Parameters("gettxout");
    parameters.addParameter("txid", "txid");
    parameters.addParameter("n", 0);
    try {
      bitcoinRPC.makeBitcoinRequest(parameters, BitcoinUTXO.class);
    } catch (BitcoinCoreException bitcoinCoreException) {
      TestCase.assertEquals(-8, bitcoinCoreException.getCode());
      TestCase.assertEquals(
          "txid must be of length 64 (not 4, for 'txid')", bitcoinCoreException.getMessage());
    }
    TestCase.fail("Expected BitcoinCoreException but we not receive it");
  }
}
