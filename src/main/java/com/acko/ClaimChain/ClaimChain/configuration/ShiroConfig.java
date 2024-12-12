package com.acko.ClaimChain.ClaimChain.configuration;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.event.EventBus;
import org.apache.shiro.event.support.DefaultEventBus;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroWebConfiguration;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


//@Configuration
public class ShiroConfig extends ShiroWebConfiguration {

    @Bean
    public String sessionIdCookieName() {
        return "sessionId"; // Explicitly provide a value
    }

    @Bean
    public EventBus eventBus() {
        return new DefaultEventBus();
    }

    @Bean
    @Lazy
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public SecurityManager securityManager(Realm realm, EhCacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
        chainDefinition.addPathDefinition("/user/**", "authc, roles[user]");
        chainDefinition.addPathDefinition("/insurer/**", "authc, roles[insurer]");
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    @Bean
    @Lazy
    public SimpleCookie sessionIdCookie() {
        SimpleCookie cookie = new SimpleCookie("sessionId");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }
}
