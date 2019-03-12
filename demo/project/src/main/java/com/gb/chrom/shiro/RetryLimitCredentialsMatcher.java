package com.gb.chrom.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {

	private static Logger logger = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);

	private static final String PASSWORD_RETRY_CACHE = "passwordRetryCache";

	private CacheManager cacheManager;

	public RetryLimitCredentialsMatcher() {
	}

	public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = (String) authcToken.getPrincipal();

		// retry count + 1
		Cache<String, AtomicInteger> passwordRetryCache = cacheManager.getCache(PASSWORD_RETRY_CACHE);
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		//限制错误登陆次数5
		if (retryCount.incrementAndGet() > 5) {
			logger.warn("username: {} tried to login more than 5 times in period.", username);
			throw new ExcessiveAttemptsException("Frequent error logging.");
		} else {
			passwordRetryCache.put(username, retryCount);
		}
		// boolean matches = super.doCredentialsMatch(token, info);
		SimpleAuthenticationInfo sai = (SimpleAuthenticationInfo) info;
		boolean matches = passwordsMatch(token, sai.getCredentialsSalt(), (String) info.getCredentials());
		if (matches) {
			passwordRetryCache.remove(username);
			return matches;
		}
		
		throw new IncorrectCredentialsException("incorrect Credentialse exception");
	}

	//判断登陆
	public boolean passwordsMatch(UsernamePasswordToken token, ByteSource credentialsSalt, String storedCredentials) {
		PasswordHash hashed = PasswordHash.getInstance();
		hashed.setCredentialsSalt(CodecSupport.toString(credentialsSalt.getBytes()));
		String password = String.valueOf(token.getPassword());
		//System.out.println(password);
		String computed = hashed.encryptToHex(password, token.getUsername());
		//System.out.println(token.getUsername());
		//System.out.println(computed);
		//System.out.println(StringUtils.equals(storedCredentials, computed));
		//System.out.println(storedCredentials);
		return StringUtils.equals(storedCredentials, computed);
		//return true;
	}

}
