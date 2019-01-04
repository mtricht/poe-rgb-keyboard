package com.swordbeta.exilergb.Player;

import javafx.util.Pair;

public class CurrentTotalPair extends Pair<Integer, Integer> {
    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public CurrentTotalPair(Integer key, Integer value) {
        super(key, value);
    }

    public Integer getCurrent() {
        return this.getKey();
    }

    public Integer getTotal() {
        return this.getValue();
    }
}
