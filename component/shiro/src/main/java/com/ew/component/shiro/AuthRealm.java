package com.ew.component.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统用户身份验证 Realm
 * @author Mr`Huang
 * @Date 2020-11-4 13:52:15
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	
	/**
	 * 授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.err.println("doGetAuthorizationInfo"+principals);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	    info.addRole("admin");
        info.addStringPermission("a:f:s");
        return info;
	}

	/**
	 * 认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		User user = userService.queryUserInfoBy(token.getUsername());
	      // 判断用户名是否存在
        if (user == null) {
        	log.info("用户名有误");
            throw new UnknownAccountException();//用户不存在-用户名密码错误
        }
        String loginPassword = new String(token.getPassword());
        if (!user.getPassword().equals(loginPassword)) {
        	log.info("密码错误");
        	throw new UnknownAccountException();//密码不正确-用户名密码错误
		}
        log.info("登入成功");
        ByteSource salt = ByteSource.Util.bytes("");
		return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
	}

}

