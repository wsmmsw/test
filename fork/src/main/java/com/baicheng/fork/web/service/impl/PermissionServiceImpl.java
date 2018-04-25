package com.baicheng.fork.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.core.generic.GenericServiceImpl;
import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.PermissionExample;
import com.baicheng.fork.web.dao.PermissionMapper;
import com.baicheng.fork.web.service.PermissionService;

/**
 * 权限Service实现类
 *
 * @author darkangel
 * @since 2015-12-29
 */
@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

	@Resource
	private PermissionMapper permissionMapper;

	@Override
	public GenericDao<Permission, Long> getDao() {
		return permissionMapper;
	}

	@Override
	public List<Permission> selectPermissionsByRoleId(Long roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public List<Permission> selectByExample(Page<Permission> page, PermissionExample example) {
		return permissionMapper.selectByExample(page, example);
	}

	@Override
	public List<Permission> selectList() {
		return permissionMapper.selectList();
	}

	@Override
	public List<Permission> selectListByType(int type) {
		// TODO 自动生成的方法存根
		return permissionMapper.selectListByType(type);
	}

	@Override
	public List<Permission> selectPermissionByBuserId(long buserId) {
		return permissionMapper.selectPermissionByBuserId(buserId);

	}

	@Override
	public List<Permission> selectByUserId(Long userId) {
		return permissionMapper.selectByUserId(userId);
	}

}
