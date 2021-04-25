package io.github.clightning4j.litebtc.mock;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BitcoinEstimateFee {

    @SerializedName("feerate")
    private Long feeRate;
    private List<String> errors = new ArrayList<>();

    public Long getFeeRate() {
        return feeRate;
    }

    public List<String> getErrors() {
        return errors;
    }
}
