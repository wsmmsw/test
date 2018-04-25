package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericService;
import com.baicheng.fork.domain.user.Role;
import com.baicheng.fork.domain.user.RoleExample;

/**
 * 角色 业务接口
 *
 * @author darkangel
 * @since 2015-12-29
 *
 */
public interface RoleService extends GenericService<Role, Long> {

	/**
	 * 通过用户id 查询用户 拥有的角色
	 *
	 * @param userId
	 * @return
	 */
	List<Role> selectRolesByUserId(Long userId);

	List<Role> selectRolesByBUserId(Long userId);

	List<Role> getAllRole(Page<Role> page, RoleExample example);

	Role selectRolesByNameOrSign(Role role);

	List<Object[]> selectRolesWithPermission(Page<Role> page, RoleExample example);

	int deleteRole(long id);

}
