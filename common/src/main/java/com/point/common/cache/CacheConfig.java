package com.point.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * 缓存配置
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    /**
     * 命名空间缓存注解
     */
    @Autowired
    private NamespaceAnnotationCacheOperationSource annotationCacheOperationSource;

    @Bean
    @Role(2)
    @Qualifier("beanFactoryCacheOperationSourceAdvisor")
    public BeanFactoryCacheOperationSourceAdvisor beanFactoryCacheOperationSourceAdvisor() {
        BeanFactoryCacheOperationSourceAdvisor advisor =  new BeanFactoryCacheOperationSourceAdvisor();
        advisor.setCacheOperationSource(annotationCacheOperationSource);
        advisor.setAdviceBeanName("cacheInterceptor");
        return advisor;
    }
}
