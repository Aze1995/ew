package com.ew.component.shiro;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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

import com.ew.common.Constant.DefaultConst;
import com.ew.common.utils.LoginUserUtil;
import com.ew.common.vo.LoginUser;
import com.ew.modules.system.entity.User;
import com.ew.modules.system.service.IMenuService;
import com.ew.modules.system.service.IRoleMenuService;
import com.ew.modules.system.service.IUserService;
import com.ew.modules.system.vo.MenuVo;

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
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserInfo user = principals.oneByType(UserInfo.class);
		Long roleId = user.getUser().getRoleId();
		List<MenuVo> menus = null;
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermission(DefaultConst.MENU_PERMISSIONS);
		if (ShiroUtil.isAdmin()) {
			info.addRole("admin");
	        info.addStringPermission("*:*:*");//管理员用户-开放所有权限
	        menus = menuService.findAll();
		}else {
			menus = roleMenuService.findMenuByRoleId(roleId);
		    info.addRole(String.valueOf(roleId));//角色名称-角色Id
		    addStringPermission(menus, info);//设置登入用户角色权限
		}
		user.setMenus(menus);
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
        LoginUserUtil.setLoginUser(getLoginUser(user));//初始化系统登入用户茜
        ByteSource salt = ByteSource.Util.bytes("");
		return new SimpleAuthenticationInfo(new UserInfo(user), user.getPassword(), salt, getName());
	}
	
	/**
	 * 设置登入用户角色权限
	 * @param menus		菜单列表
	 * @param info		授权信息
	 */
	public void addStringPermission(List<MenuVo> menus,SimpleAuthorizationInfo info) {
		for (MenuVo menuVo : menus) {
			info.addStringPermission(menuVo.getPerms());//设置登入用户角色权限	
			if (!CollectionUtils.isEmpty(menuVo.getMenu())) {
				addStringPermission(menuVo.getMenu(), info);
			}
		}
	}

	/**
	 * 组装登入信息
	 * @param user
	 * @return
	 */
	LoginUser getLoginUser(User user) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(user.getUserId());
		loginUser.setNickname(user.getNickname());
		loginUser.setUserId(user.getUserId());
		return loginUser;
	}
	
}

