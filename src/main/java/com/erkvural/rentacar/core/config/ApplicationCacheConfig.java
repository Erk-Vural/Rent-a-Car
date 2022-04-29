package com.erkvural.rentacar.core.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@EnableCaching
@Configuration
public class ApplicationCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("carRentals"),
                new ConcurrentMapCache("cars"),
                new ConcurrentMapCache("carDamages"),
                new ConcurrentMapCache("carMaintenances"),
                new ConcurrentMapCache("brands"),
                new ConcurrentMapCache("colors"),
                new ConcurrentMapCache("cities"),
                new ConcurrentMapCache("additionalServices"),
                new ConcurrentMapCache("invoices"),
                new ConcurrentMapCache("cardInfos"),
                new ConcurrentMapCache("payments")
        ));
        return cacheManager;
    }
}
