package com.baicheng.fork.web.service.impl;

import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.core.generic.GenericServiceImpl;
import com.baicheng.fork.core.util.DateUtils;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.domain.user.UserExample;
import com.baicheng.fork.domain.user.UserRole;
import com.baicheng.fork.domain.user.UserRoleExample;
import com.baicheng.fork.web.dao.UserMapper;
import com.baicheng.fork.web.dao.UserRoleMapper;
import com.baicheng.fork.web.service.UserService;

/**
 * 用户Service实现类
 *
 * @author darkangel
 * @since 2015-12-29
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public int insert(User model) {
		int aa = this.userMapper.insertSelective(model);
		if (model.getRoleIds() != null && model.getRoleIds().length > 0) {
			for (String item : model.getRoleIds()) {
				UserRole ur = new UserRole();
				ur.setUserId(model.getId());
				ur.setRoleId(new Long(item));
				this.userRoleMapper.insertSelective(ur);
			}
		}
		return aa;
	}

	@Override
	public int update(User model) {
		int aa = this.userMapper.updateByPrimaryKeySelective(model);
		if (model.getRoleIds() != null && model.getRoleIds().length > 0) {
			UserRoleExample ur = new UserRoleExample();
			UserRoleExample.Criteria cc = ur.createCriteria();
			cc.andUserIdEqualTo(model.getId());
			this.userRoleMapper.deleteByExample(ur);
			for (String item : model.getRoleIds()) {
				UserRole uu = new UserRole();
				uu.setUserId(model.getId());
				uu.setRoleId(new Long(item));
				this.userRoleMapper.insertSelective(uu);
			}
		}
		return aa;
	}

	@Override
	public int delete(Long id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User authentication(User user) {
		User u = this.userMapper.authentication(user);
		if ("正常".equals(u.getState())) {
			return u;
		} else {
			return null;
		}
	}

	@Override
	public User selectById(Long id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public GenericDao<User, Long> getDao() {
		return this.userMapper;
	}

	@Override
	public User selectByUsername(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		final List<User> list = this.userMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Object[]> getAllUser(Page<User> page, UserExample example) {
		List<Object[]> rdata = new ArrayList<Object[]>();
		final List<HashMap<String, Object>> list = this.userMapper.selectByExampleAndPage(page, example);
		Map<String, Object[]> map = new LinkedHashMap<String, Object[]>();
		for (HashMap<String, Object> item : list) {
			String id = item.get("id").toString();
			if (map.get(id) == null) {
				User user = new User();
				user.setCman(new Long(item.get("cman").toString().trim()));
				user.setCreateTime(DateUtils.stringToDate(item.get("create_time").toString().trim()));
				user.setId(new Long(item.get("id").toString()));
				user.setLdate(
						item.get("ldate") == null ? null : DateUtils.stringToDate(item.get("ldate").toString().trim()));
				user.setLman(item.get("lman") == null ? null : new Long(item.get("lman").toString().trim()));
				user.setPortrait(item.get("lman") == null ? null : item.get("lman").toString().trim());
				user.setPwd(item.get("pwd").toString().trim());
				user.setState(item.get("state").toString().trim());
				user.setTruename(item.get("truename") == null ? null : item.get("truename").toString().trim());
				user.setUsername(item.get("username").toString().trim());
				List<Object[]> roleList = new ArrayList<Object[]>();
				Object[] roleArray = { item.get("rid") == null ? null : new Long(item.get("rid").toString().trim()),
						item.get("role_name") == null ? null : item.get("role_name").toString().trim() };
				roleList.add(roleArray);
				Object[] mapItem = { user, roleList };
				map.put(id, mapItem);
			} else {
				Object[] mapItem = map.get(id);
				@SuppressWarnings("unchecked")
				List<Object[]> roleList = (List<Object[]>) mapItem[1];
				Object[] roleArray = { item.get("rid") == null ? null : new Long(item.get("rid").toString().trim()),
						item.get("role_name") == null ? null : item.get("role_name").toString().trim() };
				roleList.add(roleArray);
			}
		}
		for (Entry<String, Object[]> entry : map.entrySet()) {
			Object[] mapItem = entry.getValue();
			rdata.add(mapItem);
		}
		return rdata;
	}

	@Override
	public List<User> getUserWithExampleAndPage(Page<User> page, UserExample example) {
		return this.userMapper.selectOneEntry(page, example);
	}

	@Override
	public User selectByTaskId(Long taskId) {
		return this.userMapper.selectByTaskId(taskId);
	}

	@Override
	public List<User> selectAll() {
		return this.userMapper.selectAll();
	}

}
