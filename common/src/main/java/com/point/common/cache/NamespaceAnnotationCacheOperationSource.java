package com.point.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.stereotype.Component;

/**
 * 命名空间注解缓存
 */
@Component
public class NamespaceAnnotationCacheOperationSource extends AnnotationCacheOperationSource {

    /**
     * 构造函数
     * @param parser 解析器
     */
    public NamespaceAnnotationCacheOperationSource(@Autowired NamespaceCacheAnnotationParser parser) {

        super(parser);
    }

}
