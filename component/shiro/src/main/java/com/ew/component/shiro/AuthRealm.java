package com.ew.component.shiro;

import java.util.List;

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

import com.ew.modules.system.entity.Menu;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IRoleMenuService;
import com.ew.modules.system.service.IUserService;

/**
 * 系统用户身份验证 Realm
 * @author Mr`Huang
 * @Date 2020-11-4 13:52:15
 */
public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleMenuService roleMenuService; 
	
	/**
	 * 授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = principals.oneByType(User.class);
		Long roleId = user.getRoleId();
		List<Menu> menus = roleMenuService.findMenuByRoleId(roleId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	    info.addRole(String.valueOf(user.getRoleId()));//角色名称-角色Id
	    menus.forEach(m->{
	    	info.addStringPermission(m.getPerms());//设置登入用户角色权限	    	
	    });
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
            throw new UnknownAccountException();//用户不存在-用户名密码错误
        }
        String loginPassword = new String(token.getPassword());
        if (!user.getPassword().equals(loginPassword)) {
        	throw new UnknownAccountException();//密码不正确-用户名密码错误
		}
        ByteSource salt = ByteSource.Util.bytes("");
		return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
	}

}

