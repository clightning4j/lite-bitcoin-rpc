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
package io.github.clightning4j.litebtc.model.bitcoin;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

/** @author */
public class BlockchainInfo {
  private String chain;
  private Long blocks;
  private Long headers;
  private String bestblockhash;
  private Number difficulty;
  private Number mediantime;
  private Number verificationprogress;
  private String chainwork;

  @SerializedName("size_on_disk")
  private Long sizeOnDisk;

  private Boolean pruned;

  @SerializedName("pruneheight")
  private BigInteger pruneHeight;

  @SerializedName("automatic_pruning")
  private Boolean automaticPruning;

  @SerializedName("prune_target_size")
  private BigInteger pruneTargetSize;
  // add softforks
  // add bip9_softforks
  private String warnings;

  public String getChain() {
    return chain;
  }

  public Long getBlocks() {
    return blocks;
  }

  public Long getHeaders() {
    return headers;
  }

  public String getBestblockhash() {
    return bestblockhash;
  }

  public Number getDifficulty() {
    return difficulty;
  }

  public Number getMediantime() {
    return mediantime;
  }

  public Number getVerificationprogress() {
    return verificationprogress;
  }

  public String getChainwork() {
    return chainwork;
  }

  public Long getSizeOnDisk() {
    return sizeOnDisk;
  }

  public Boolean getPruned() {
    return pruned;
  }

  public BigInteger getPruneHeight() {
    return pruneHeight;
  }

  public Boolean getAutomaticPruning() {
    return automaticPruning;
  }

  public BigInteger getPruneTargetSize() {
    return pruneTargetSize;
  }

  public String getWarnings() {
    return warnings;
  }
}
