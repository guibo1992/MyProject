package com.gb.chrom.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.gb.chrom.web.ShiroAuthenticationFilter;

import net.sf.ehcache.CacheManager;

/**
 * @author Summer
 *
 *         2017年11月10日
 */
@Configuration
public class ShiroConfig {

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager shiroCacheManager(CacheManager cacheManager) {
		EhCacheManager cacheManger = new EhCacheManager();
		// use spring share cacheManager
		cacheManger.setCacheManager(cacheManager);
		// cacheManger.setCacheManagerConfigFile("classpath:ehcache.xml");

		return cacheManger;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	@Bean
	public HashedCredentialsMatcher retryLimitCredentialsMatcher(EhCacheManager shiroCacheManager) {
		RetryLimitCredentialsMatcher credentialsMatcher = new RetryLimitCredentialsMatcher(shiroCacheManager);
		credentialsMatcher.setHashAlgorithmName("SHA-256");
		credentialsMatcher.setHashIterations(2);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public ShiroRealm shiroRealm(HashedCredentialsMatcher retryLimitCredentialsMatcher) {
		ShiroRealm realm = new ShiroRealm();
		realm.setCredentialsMatcher(retryLimitCredentialsMatcher);
		return realm;
	}

	@Bean
	public CachingSessionDAO sessionDAO(EhCacheManager shiroCacheManager) {
		EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
		sessionDAO.setCacheManager(shiroCacheManager);
		return sessionDAO;
	}

	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie("_SCP_JSESSIONID_");
		cookie.setMaxAge(-1);
		return cookie;
	}

	@Bean
	public SessionManager sessionManager(CachingSessionDAO sessionDAO, SimpleCookie sessionIdCookie) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(-1);
		sessionManager.setSessionDAO(sessionDAO);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(sessionIdCookie);
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		// sessionManager.setSessionValidationScheduler(new QuartzSessionValidationScheduler());
		return sessionManager;
	}

	@Bean
	public SessionValidationScheduler sessionValidationScheduler(ValidatingSessionManager sessionManager) {
		return new QuartzSessionValidationScheduler(sessionManager);
	}

	@Bean
	public SecurityManager securityManager(ShiroRealm shiroRealm, EhCacheManager shiroCacheManager, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(shiroRealm);
		securityManager.setSessionManager(sessionManager);
		securityManager.setCacheManager(shiroCacheManager);
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("authc", new ShiroAuthenticationFilter());
		//拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/**/*.ico", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index.html");

		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

}
