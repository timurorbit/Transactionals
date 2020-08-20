package com.timplant.services.transaction;

import javax.cache.annotation.CacheInvocationParameter;
import javax.cache.annotation.CacheKeyGenerator;
import javax.cache.annotation.CacheKeyInvocationContext;
import javax.cache.annotation.GeneratedCacheKey;
import java.lang.annotation.Annotation;

public class CacheKeyGeneratorFactory implements CacheKeyGenerator {
    @Override
    public GeneratedCacheKey generateCacheKey(CacheKeyInvocationContext<? extends Annotation> cacheKeyInvocationContext) {
        final CacheInvocationParameter[] allParameters = cacheKeyInvocationContext.getAllParameters();
        for (CacheInvocationParameter parameter : allParameters) {
            if (StringCacheKey.class.equals(parameter.getRawType())) {
                return (StringCacheKey)parameter.getValue();
            }
        }
        return null;
    }
}
