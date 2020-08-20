package com.timplant.services.transaction;

import javax.cache.annotation.GeneratedCacheKey;

public class StringCacheKey implements GeneratedCacheKey {

    private String value;

    public StringCacheKey(String param) {
        this.value = param;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.value.equals(((StringCacheKey)obj).getValue());
    }

    public String getValue() {
        return value;
    }
}
