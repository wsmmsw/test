package com.baicheng.fork.web.security;

/**
 * 角色标识配置类, <br>
 * 与 role_info 角色表中的 role_sign 字段 相对应 <br>
 * 使用:
 * <p>
 * 
 * <pre>
 * &#064;RequiresRoles(value = RoleSign.ADMIN)
 * public String admin() {
 * 	return &quot;拥有admin角色,能访问&quot;;
 * }
 * </pre>
 */
public class RoleSign {

	/**
	 * 超级管理员 标识
	 */
	public static final String SUPER_ADMIN = "super_admin";
	/**
	 * 普通后台管理员(平台) 标识
	 */
	public static final String ADMIN = "admin";

	/**
	 * 客户经理 标识
	 */
	public static final String CONSULTANT = "consultant";

	/**
	 * VIP客户 标识
	 */
	public static final String VIP_USER = "vip_user";

	/**
	 * 商家 标识
	 */
	public static final String MERCHANT = "merchant";

	/**
	 * 普通客户 标识
	 */
	public static final String MEMBER = "member";
	/**
	 * 添加更多...
	 */

}
