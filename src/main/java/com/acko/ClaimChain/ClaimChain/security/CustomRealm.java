package com.acko.ClaimChain.ClaimChain.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

//@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private CacheManager cacheManager;

    public CustomRealm() {
        setCredentialsMatcher(new SimpleCredentialsMatcher());
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        String password = new String(userToken.getPassword());

        Cache<String, String> userCache = cacheManager.getCache("userCache");
        String cachedPassword = userCache.get(username);

        if (cachedPassword == null || !cachedPassword.equals(password)) {
            throw new AuthenticationException("Invalid username or password");
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        Cache<String, Set<String>> roleCache = cacheManager.getCache("roleCache");
        Set<String> roles = roleCache.get(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }
}