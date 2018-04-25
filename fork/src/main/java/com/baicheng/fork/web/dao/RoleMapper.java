package com.baicheng.fork.web.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.domain.user.Role;
import com.baicheng.fork.domain.user.RoleExample;

/**
 * 角色Dao 接口
 *
 * @author darkangel
 * @since 2015-12-29
 *
 */
public interface RoleMapper extends GenericDao<Role, Long> {

	int countByExample(RoleExample example);

	int deleteByExample(RoleExample example);

	@Override
	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	@Override
	int insertSelective(Role record);

	List<Role> selectByExample(RoleExample example);

	@Override
	Role selectByPrimaryKey(Long id);

	int updateByExampleSelective(
			@Param("record") Role record,
			@Param("example") RoleExample example);

	int updateByExample(
			@Param("record") Role record,
			@Param("example") RoleExample example);

	@Override
	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/**
	 * 通过用户id 查询用户 拥有的角色
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> selectRolesByUserId(Long userId);

	List<Role> selectRolesByBUserId(Long userId);

	List<Role> selectRolesByBUserIdAndType(Long userId, int type);

	List<Role> selectByExampleAndPage(
			@Param("page") Page<Role> page,
			@Param("example") RoleExample example);

	List<Role> selectRolesByNameOrSign(
			@Param("record") Role record);

	List<HashMap<String, Object>> selectRolesWithPermission(
			@Param("page") Page<Role> page,
			@Param("example") RoleExample example);
}
