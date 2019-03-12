package com.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.User;
import com.service.UserService;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 为当前用户授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String userName = (String) principal.getPrimaryPrincipal();
        System.out.println("--------------" + userName);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /*
         * info.setRoles(userService.getRoles(userName));
         * info.setStringPermissions(userService.getPermissions(userName));
         */

        Set<String> roles = userService.getRoles(userName);
        Set<String> permissions = userService.getPermissions(userName);

        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 验证当前登录用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        System.out.println("aaaaaaaaaaaa:" + userName);
        User user = userService.getByUserName(userName);
        if (user != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                    getName());
            System.out.println("bbbbbbb");
            return authcInfo;
        } else {
            System.out.println("ccccccc");
            return null;
        }

    }

}
