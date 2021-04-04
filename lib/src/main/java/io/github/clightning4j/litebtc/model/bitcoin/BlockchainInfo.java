package io.github.clightning4j.litebtc.model.bitcoin;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class BlockchainInfo {
    private String chain;
    private BigInteger blocks;
    private BigInteger headers;
    private String bestblockhash;
    private BigInteger difficulty;
    private BigInteger mediantime;
    private Long verificationprogress;
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

    public BigInteger getBlocks() {
        return blocks;
    }

    public BigInteger getHeaders() {
        return headers;
    }

    public String getBestblockhash() {
        return bestblockhash;
    }

    public BigInteger getDifficulty() {
        return difficulty;
    }

    public BigInteger getMediantime() {
        return mediantime;
    }

    public Long getVerificationprogress() {
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
