package com.point.common.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义缓存管理器
 */
@Component
public class CacheNamespaceManager  {

    private Map<String, Set<String>> namespaces = new HashMap<>();

    public Set<String> getCaches(String namespace) {
        return namespaces.get(namespace);
    }

    public void putCache(String namespace, String cache) {

        Set<String> caches = namespaces.get(namespace);
        if (caches == null) {
            caches = new HashSet<>();
            namespaces.put(namespace, caches);
        }
        caches.add(cache);
    }

}
