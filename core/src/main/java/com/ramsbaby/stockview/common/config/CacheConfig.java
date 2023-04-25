package com.ramsbaby.stockview.common.config;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : RAMSBABY
 * @date : 2023-04-25 오후 10:10:16
 */
@Configuration
@EnableCaching
public class CacheConfig {

    public static final String STOCK_INFO_CACHE_NAME = "stockInfoCache";

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(STOCK_INFO_CACHE_NAME,
                    CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build().asMap(), false);
            }
        };
    }
}
