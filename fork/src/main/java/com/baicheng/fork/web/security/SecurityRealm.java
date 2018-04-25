package com.baicheng.fork.web.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.Role;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.web.service.PermissionService;
import com.baicheng.fork.web.service.RoleService;
import com.baicheng.fork.web.service.UserService;

/**
 * 用户身份验证,授权 Realm 组件
 *
 * @author darkangel
 * @since 2015-12-29
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = "not ";

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;

	/**
	 * 支持or and not 关键词 不支持and or混用
	 *
	 * @param principals
	 * @param permission
	 * @return
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if (permission.contains(OR_OPERATOR)) {
			String[] permissions = permission.split(OR_OPERATOR);
			for (String orPermission : permissions) {
				if (isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if (permission.contains(AND_OPERATOR)) {
			String[] permissions = permission.split(AND_OPERATOR);
			for (String orPermission : permissions) {
				if (!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	/**
	 * @param principals
	 * @param permission
	 * @return
	 */
	private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
		if (permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}

	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = String.valueOf(principals.getPrimaryPrincipal());

		final User user = userService.selectByUsername(username);
		if (user != null && "正常".equals(user.getState().trim())) {// 平台用户
			final List<Role> roles = roleService.selectRolesByUserId(user.getId());
			for (Role role : roles) {
				// 添加角色签名
				authorizationInfo.addRole(role.getRoleSign());
				final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
				for (Permission permission : permissions) {
					// 添加权限签名
					authorizationInfo.addStringPermission(permission.getPermissionSign());
				}
			}
		}
		return authorizationInfo;
	}

	/**
	 * 用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = String.valueOf(token.getPrincipal());
		String password = new String((char[]) token.getCredentials());
		CRMToken ct = (CRMToken) token;
		if ("0".equals(ct.getuType())) {// 平台人员登录
			// 通过数据库进行验证
			final User authentication = userService.authentication(new User(username, password));
			if (authentication == null) {
				throw new AuthenticationException("用户名或密码错误.");
			}
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
			return authenticationInfo;
		} else {
			throw new AuthenticationException("非法用户访问.");
		}
	}

}
