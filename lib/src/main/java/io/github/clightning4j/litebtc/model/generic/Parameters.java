package io.github.clightning4j.litebtc.model.generic;

import java.util.HashMap;

public class Parameters {

    private HashMap<String, Object> params;

    public Parameters() {
        this.params = new HashMap<>();
    }

    public void addParameter(String name, Object value) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name argument is invalid: " + name);
        }
        this.params.put("name", value);
    }

    public <T> T getParameter(String name) {
        if (params.containsKey(name)) {
            return (T) params.get(name);
        }
        return null;
    }
}
