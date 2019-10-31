package com.point.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.SpringCacheAnnotationParser;
import org.springframework.cache.interceptor.CacheOperation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;

@Component
public class NamespaceCacheAnnotationParser extends SpringCacheAnnotationParser {

    /**
     * 缓存命名空间管理器
     */
    @Autowired
    private CacheNamespaceManager cacheNamespaceManager;

    @Override
    public Collection<CacheOperation> parseCacheAnnotations(Method method) {

        NamespaceCacheable cacheable = method.getAnnotation(NamespaceCacheable.class);
        if (cacheable != null) {
            String namespace = cacheable.namespace();
            String[] cacheNames = cacheable.cacheNames();
            for (String cache : cacheNames) {
                cacheNamespaceManager.putCache(namespace, cache);
            }
            String[] value = cacheable.value();
            for (String cache : value) {
                cacheNamespaceManager.putCache(namespace, cache);
            }
        }
        return super.parseCacheAnnotations(method);
    }
}