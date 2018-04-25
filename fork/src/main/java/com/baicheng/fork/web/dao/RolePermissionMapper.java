package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.domain.user.RolePermission;
import com.baicheng.fork.domain.user.RolePermissionExample;

public interface RolePermissionMapper extends GenericDao<RolePermission, Long> {

	int countByExample(RolePermissionExample example);

	int deleteByExample(RolePermissionExample example);

	int deleteByPrimaryKey(Long id);

	int insert(RolePermission record);

	int insertSelective(RolePermission record);

	List<RolePermission> selectByExample(RolePermissionExample example);

	RolePermission selectByPrimaryKey(Long id);

	int updateByExampleSelective(
			@Param("record") RolePermission record,
			@Param("example") RolePermissionExample example);

	int updateByExample(
			@Param("record") RolePermission record,
			@Param("example") RolePermissionExample example);

	int updateByPrimaryKeySelective(RolePermission record);

	int updateByPrimaryKey(RolePermission record);
}
