package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.PermissionExample;

/**
 * 权限 Dao 接口
 *
 * @author darkangel
 * @since 2015年12月30日
 *
 */
public interface PermissionMapper extends GenericDao<Permission, Long> {

	int countByExample(PermissionExample example);

	int deleteByExample(PermissionExample example);

	@Override
	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	@Override
	int insertSelective(Permission record);

	List<Permission> selectByExample(
			@Param("page") Page<Permission> page,
			@Param("example") PermissionExample example);

	@Override
	Permission selectByPrimaryKey(Long id);

	int updateByExampleSelective(
			@Param("record") Permission record,
			@Param("example") PermissionExample example);

	int updateByExample(
			@Param("record") Permission record,
			@Param("example") PermissionExample example);

	@Override
	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	/**
	 * 通过角色id 查询角色 拥有的权限
	 *
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(Long roleId);

	List<Permission> selectList();

	List<Permission> selectListByType(@Param("type") int type);

	List<Permission> selectPermissionByBuserId(@Param("buserId") long buserId);

	List<Permission> selectByUserId(@Param("userId") Long userId);

}
