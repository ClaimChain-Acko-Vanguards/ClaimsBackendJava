package com.acko.ClaimChain.ClaimChain.service;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private CacheManager cacheManager;

    public void saveUserCredentials(String username, String password) {
        Cache<String, String> userCache = cacheManager.getCache("userCache");
        userCache.put(username, password);
    }

    public void saveUserRoles(String username, Set<String> roles) {
        Cache<String, Set<String>> roleCache = cacheManager.getCache("roleCache");
        roleCache.put(username, roles);
    }
}