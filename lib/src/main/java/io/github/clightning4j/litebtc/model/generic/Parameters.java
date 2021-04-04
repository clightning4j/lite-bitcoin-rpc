package io.github.clightning4j.litebtc.model.generic;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Parameters {

  @SerializedName("jsonrpc")
  private String rpcversio = "1.0";
  private int id = (int) ((Math.random() % 100 ) + 1);
  private String method;
  private List<Object> params;

  public Parameters(String method) {
    this.method = method;
    this.params = new ArrayList<>();
  }

  public void addParameter(String name, Object value) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name argument is invalid: " + name);
    }
    this.params.add(value);
  }
}
