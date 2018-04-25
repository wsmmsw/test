package com.baicheng.fork.web.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.domain.user.UserExample;

/**
 * 用户Dao接口
 *
 * @author darkangel
 * @since 2015-12-29
 *
 */
public interface UserMapper extends GenericDao<User, Long> {

	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	@Override
	int deleteByPrimaryKey(Long id);

	int insert(User record);

	@Override
	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	@Override
	User selectByPrimaryKey(Long id);

	int updateByExampleSelective(
			@Param("record") User record,
			@Param("example") UserExample example);

	int updateByExample(
			@Param("record") User record,
			@Param("example") UserExample example);

	@Override
	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	/**
	 * 用户登录验证查询
	 *
	 * @param record
	 * @return
	 */
	User authentication(
			@Param("record") User record);

	/**
	 * 分页条件查询
	 *
	 * @param page
	 * @param example
	 * @return
	 */
	List<HashMap<String, Object>> selectByExampleAndPage(
			@Param("page") Page<User> page,
			@Param("example") UserExample example);

	List<User> selectOneEntry(
			@Param("page") Page<User> page,
			@Param("example") UserExample example);

	User selectById(Long id);

	User selectByTaskId(@Param("taskId") Long taskId);

	List<User> selectAll();

	String getUserName(@Param("id") Long id);

}
