package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericService;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.domain.user.UserExample;

/**
 * 用户 业务 接口
 *
 * @author darkangel
 * @since 2015-12-29
 *
 */
public interface UserService extends GenericService<User, Long>, CRMService {

	User authentication(User user);

	User selectByUsername(String username);

	List<User> getUserWithExampleAndPage(Page<User> page, UserExample example);

	List<Object[]> getAllUser(Page<User> page, UserExample example);

	User selectByTaskId(Long taskId);

	List<User> selectAll();

}
