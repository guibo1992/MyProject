package com.gb.chrom.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gb.chrom.core.service.LoginService;
import com.gb.chrom.core.service.UserService;
import com.gb.chrom.model.User;

/**
 * @author Summer
 *
 * @date 2016年3月5日
 */
public class ShiroRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService<User> loginService;
	
	/**
	 * 链接权限的实现
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = userService.queryUser((String) principals.getPrimaryPrincipal());
		authorizationInfo.addRole(User.TYPE_OF_ADMIN == user.getType() ? "administrator" : (String) principals.getPrimaryPrincipal());
		
		return authorizationInfo;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		if (StringUtils.isEmpty(token.getUsername())) {
			logger.debug("current token's username is null.");
			throw new AuthenticationException();
		}
		logger.info("Found user login: {}", token.getUsername());

		User user = loginService.login(token.getUsername());
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		ByteSource salt = ByteSource.Util.bytes(user.getSalt());
		authenticationInfo.setCredentialsSalt(salt);
		//System.out.println("=================");
		//System.out.println(authenticationInfo);
		return authenticationInfo;
	}

}
