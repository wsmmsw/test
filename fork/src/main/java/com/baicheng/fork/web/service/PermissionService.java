package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericService;
import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.PermissionExample;

/**
 * 权限 业务接口
 *
 * @author darkangel
 * @since 2015-12-29
 *
 */
public interface PermissionService extends GenericService<Permission, Long> {

	/**
	 * 通过角色id 查询角色 拥有的权限
	 *
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(Long roleId);

	List<Permission> selectByExample(Page<Permission> page, PermissionExample example);

	List<Permission> selectListByType(int type);

	List<Permission> selectPermissionByBuserId(long buserId);

	List<Permission> selectByUserId(Long userId);

}
