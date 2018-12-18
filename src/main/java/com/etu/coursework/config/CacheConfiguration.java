package com.etu.coursework.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.etu.coursework.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.etu.coursework.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Category.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Product.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Product.class.getName() + ".categories", jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Customer.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Customer.class.getName() + ".wishLists", jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Customer.class.getName() + ".addresses", jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.Address.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.WishList.class.getName(), jcacheConfiguration);
            cm.createCache(com.etu.coursework.domain.WishList.class.getName() + ".products", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
