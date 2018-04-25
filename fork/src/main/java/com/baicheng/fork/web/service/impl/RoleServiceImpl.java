package com.baicheng.fork.web.service.impl;

import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.core.generic.GenericServiceImpl;
import com.baicheng.fork.core.util.DateUtils;
import com.baicheng.fork.domain.user.Role;
import com.baicheng.fork.domain.user.RoleExample;
import com.baicheng.fork.domain.user.RolePermission;
import com.baicheng.fork.domain.user.RolePermissionExample;
import com.baicheng.fork.domain.user.RolePermissionExample.Criteria;
import com.baicheng.fork.web.dao.RoleMapper;
import com.baicheng.fork.web.dao.RolePermissionMapper;
import com.baicheng.fork.web.service.RoleService;

/**
 * 角色Service实现类
 *
 * @author darkangel
 * @since 2015-12-29
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

	@Resource
	private RoleMapper roleMapper;
	@Resource
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public GenericDao<Role, Long> getDao() {
		return this.roleMapper;
	}

	@Override
	public List<Role> selectRolesByUserId(Long userId) {
		return this.roleMapper.selectRolesByUserId(userId);
	}

	@Override
	public List<Role> selectRolesByBUserId(Long userId) {
		return this.roleMapper.selectRolesByBUserId(userId);
	}

	@Override
	public List<Role> getAllRole(Page<Role> page, RoleExample example) {
		final List<Role> list = this.roleMapper.selectByExampleAndPage(page, example);
		return list;
	}

	@Override
	public int deleteRole(long id) {
		return this.roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Role selectRolesByNameOrSign(Role role) {
		final List<Role> list = this.roleMapper.selectRolesByNameOrSign(role);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Object[]> selectRolesWithPermission(Page<Role> page, RoleExample example) {
		List<Object[]> rdata = new ArrayList<Object[]>();
		List<HashMap<String, Object>> list = this.roleMapper.selectRolesWithPermission(page, example);
		Map<String, Object[]> map = new LinkedHashMap<String, Object[]>();
		for (HashMap<String, Object> item : list) {
			String id = item.get("id").toString();
			if (map.get(id) == null) {
				Role role = new Role();
				role.setId(new Long(item.get("id").toString()));
				role.setDescription(item.get("description") == null ? null : item.get("description").toString().trim());
				role.setRoleName(item.get("role_name") == null ? null : item.get("role_name").toString().trim());
				role.setRoleSign(item.get("role_sign") == null ? null : item.get("role_sign").toString().trim());
				role.setCdate(DateUtils.stringToDate(item.get("cdate").toString().trim()));
				role.setCman(new Long(item.get("cman").toString().trim()));
				role.setLdate(
						item.get("ldate") == null ? null : DateUtils.stringToDate(item.get("ldate").toString().trim()));
				role.setLman(item.get("lman") == null ? null : new Long(item.get("lman").toString().trim()));
				List<Object[]> permitList = new ArrayList<Object[]>();
				Object[] permitArray = { item.get("pid") == null ? null : new Long(item.get("pid").toString().trim()),
						item.get("pname") == null ? null : item.get("pname").toString().trim() };
				permitList.add(permitArray);
				Object[] mapItem = { role, permitList };
				map.put(id, mapItem);
			} else {
				Object[] mapItem = map.get(id);
				@SuppressWarnings("unchecked")
				List<Object[]> permitList = (List<Object[]>) mapItem[1];
				Object[] permitArray = { item.get("pid") == null ? null : new Long(item.get("pid").toString().trim()),
						item.get("pname") == null ? null : item.get("pname").toString().trim() };
				permitList.add(permitArray);
			}
		}
		for (Entry<String, Object[]> entry : map.entrySet()) {
			Object[] mapItem = entry.getValue();
			rdata.add(mapItem);
		}
		return rdata;
	}

	@Override
	public int insert(Role role) {
		int rdata = this.roleMapper.insertSelective(role);
		String[] permits = role.getPermissionIds();
		if (permits != null) {
			for (String item : permits) {
				RolePermission rp = new RolePermission();
				rp.setPermissionId(new Long(item));
				rp.setRoleId(role.getId());
				this.rolePermissionMapper.insertSelective(rp);
			}
		}
		return rdata;
	}

	@Override
	public int update(Role model) {
		// TODO Auto-generated method stub
		int rdata = super.update(model);
		RolePermissionExample rpe = new RolePermissionExample();
		Criteria cc = rpe.createCriteria();
		cc.andRoleIdEqualTo(model.getId());
		this.rolePermissionMapper.deleteByExample(rpe);
		String[] permits = model.getPermissionIds();
		if (permits != null) {
			for (String item : permits) {
				RolePermission rp = new RolePermission();
				rp.setPermissionId(new Long(item));
				rp.setRoleId(model.getId());
				this.rolePermissionMapper.insertSelective(rp);
			}
		}
		return rdata;
	}

}
