package com.point.common.cache;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Cacheable
public @interface NamespaceCacheable {

    String namespace();

    @AliasFor(annotation = Cacheable.class, attribute = "value")
    String[] value() default {};

    @AliasFor(annotation = Cacheable.class, attribute = "cacheNames")
    String[] cacheNames() default {};

    @AliasFor(annotation = Cacheable.class, attribute = "key")
    String key() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "keyGenerator")
    String keyGenerator() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "condition")
    String condition() default "";

    @AliasFor(annotation = Cacheable.class, attribute = "unless")
    String unless() default "";
}
