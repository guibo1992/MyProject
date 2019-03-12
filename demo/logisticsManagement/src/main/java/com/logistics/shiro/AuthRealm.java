package com.logistics.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.logistics.entity.WlAdmin;
import com.logistics.service.WlAdminService;

public class AuthRealm extends AuthorizingRealm{
	
	@Autowired
	private WlAdminService wlAdminService; 
	
	//认证身份
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal(); 
	    System.out.println("登录用户名:"+userName);
	    WlAdmin wlAdmin = wlAdminService.selectByName(userName);
	    System.out.println("00000000000000000000000000000"+wlAdminService.getRole(userName));;
	    System.out.println("---------------"+wlAdminService.getPermission(userName));
		/*UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		String uName = uToken.getUsername();
		WlAdmin wlAdmin = wlAdminService.selectByName(uName);*/
		AuthenticationInfo info = new SimpleAuthenticationInfo(wlAdmin, wlAdmin.getWlPwd(),this.getClass().getName());
		return info;
	} 

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		//principal.getPrimaryPrincipal()取得的是对象 需要强转成用户类型
		WlAdmin wlAdmin =(WlAdmin)principal.getPrimaryPrincipal();
		String userName=wlAdmin.getWlName();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(wlAdminService.getRole(userName));
		info.setStringPermissions(wlAdminService.getPermission(userName));
		System.out.println("-------------:"+wlAdminService.getRole(userName));
		System.out.println("-------------:"+wlAdminService.getPermission(userName));
		return info;
	}

}
