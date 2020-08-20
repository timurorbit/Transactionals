package com.timplant.services.transaction;

import org.jsr107.ri.annotations.DefaultCacheResolver;

import javax.cache.CacheManager;
import javax.cache.annotation.CacheMethodDetails;
import javax.cache.annotation.CacheResolver;
import javax.cache.annotation.CacheResolverFactory;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import java.lang.annotation.Annotation;

public class AppCacheResolverFactory implements CacheResolverFactory {

    @Inject
    CacheManager cacheManager;

    @Override
    public CacheResolver getCacheResolver(CacheMethodDetails<? extends Annotation> cacheMethodDetails) {
        return new DefaultCacheResolver(cacheManager.getCache(cacheMethodDetails.getCacheName()));
    }

    @Override
    public CacheResolver getExceptionCacheResolver(CacheMethodDetails<CacheResult> cacheMethodDetails) {
        return null;
    }
}
