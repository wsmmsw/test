package com.baicheng.fork.web.controller;

import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.util.DateUtils;
import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.Role;
import com.baicheng.fork.domain.user.RoleExample;
import com.baicheng.fork.domain.user.RoleExample.Criteria;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.web.constants.ControllerConst;
import com.baicheng.fork.web.service.PermissionService;
import com.baicheng.fork.web.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 视图控制器,返回jsp视图给前端，本控制器主要管理平台维护人员所操作页面的方法
 *
 * @author darkangel
 * @since 2015-12-29
 *
 */
@Controller
@RequestMapping("/admin")
public class RoleController {

	protected final Log log = LogFactory.getLog(getClass());

	protected final String PEIMIIT_ROLE_ADD = "role:create";
	protected final String PEIMIIT_ROLE_UPD = "role:update";

	@Resource
	RoleService roleService;
	@Resource
	PermissionService permissionService;

	/**
	 * 平台角色列表
	 */
	@RequestMapping(value = "/listRole", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listRole(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("admin/listRole");
		int pageNow = getPageNow(request);
		int num = getPageSize(request);
		String type = request.getParameter("type");
		Page<Role> page = new Page<Role>(pageNow, num);
		RoleExample re = new RoleExample();
		re.setOrderByClause(" cdate,ldate desc");
		Criteria cc = re.createCriteria();
		if (request.getParameter("sch_roleName") != null && !"".equals(request.getParameter("sch_roleName").trim())) {
			cc.andRoleNameLike("%" + request.getParameter("sch_roleName").trim() + "%");
		}
		cc.andTypeEqualTo(Integer.valueOf(type));
		List<Role> roleList = this.roleService.getAllRole(page, re);
		for (Role item : roleList) {
			List<Permission> permissions = this.permissionService.selectPermissionsByRoleId(item.getId());
			String per = "";
			for (Permission subItem : permissions) {
				if (per.length() > 50) {
					per = per + " ...";
					break;
				}
				per += subItem.getPermissionName().trim() + ",";
			}
			if (!"".equals(per)) {
				per = per.substring(0, per.lastIndexOf(","));
			}
			item.setPermissions(per);
		}
		List<Permission> permissionList = null;

		permissionList = this.permissionService.selectList();

		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		String uType = (String) subject.getSession().getAttribute("uType");
		view.addObject("userId", user.getId());
		view.addObject("uType", uType);
		view.addObject("roleList", roleList);
		view.addObject("permissionList", permissionList);
		view.addObject("page", page);
		return view;
	}

	/**
	 * 添加角色
	 */
	@RequestMapping(value = "/addRole", method = { RequestMethod.GET, RequestMethod.POST })
	public void addRole(HttpServletResponse response, Role role) throws IOException {
		response.setCharacterEncoding("utf-8");
		// response.setContentType("application/json");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Role oldRole = this.roleService.selectRolesByNameOrSign(role);
		if (oldRole != null) {
			resultMap.put("msg", "已经存在该角色！");
		}
		Subject currentUser = SecurityUtils.getSubject();
		User doMan = (User) currentUser.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		role.setCdate(new Date());
		role.setCman(doMan.getId());
		role.setType(1);
		this.roleService.insert(role);
		resultMap.put("msg", "操作成功！");
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		try {
			response.getWriter().print(jsonObject.toString());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改角色
	 */
	@RequestMapping(value = "/updRole", method = { RequestMethod.GET, RequestMethod.POST })
	public void updRole(HttpServletResponse response, Role role) throws IOException {
		response.setCharacterEncoding("utf-8");
		// response.setContentType("application/json");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Role oldRole = this.roleService.selectById(role.getId());
		Subject currentUser = SecurityUtils.getSubject();
		User doMan = (User) currentUser.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		oldRole.setLdate(new Date());
		oldRole.setLman(doMan.getId());
		oldRole.setDescription(role.getDescription());
		oldRole.setRoleName(role.getRoleName());
		oldRole.setRoleSign(role.getRoleSign());
		oldRole.setPermissionIds(role.getPermissionIds());
		this.roleService.update(oldRole);
		resultMap.put("msg", "操作成功！");
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		try {
			response.getWriter().print(jsonObject.toString());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询的json方式
	 *
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/listRoleWithJson", method = { RequestMethod.GET, RequestMethod.POST })
	public void listRoleWithJson(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		// response.setContentType("application/json");
		response.setContentType("text/html");
		String type = request.getParameter("type");
		JSONArray jArray = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageNow = getPageNow(request);
		int num = getPageSize(request);
		Page<Role> page = new Page<Role>(pageNow, num);
		RoleExample re = new RoleExample();
		re.setOrderByClause(" cdate,ldate desc");
		Criteria cc = re.createCriteria();

		boolean where = false;
		if (request.getParameter("sch_roleName") != null && !"".equals(request.getParameter("sch_roleName").trim())) {
			cc.andRoleNameLike("%" + request.getParameter("sch_roleName").trim() + "%");
			where = true;
		}
		cc.andTypeEqualTo(Integer.valueOf(type));

		List<Role> roleList = this.roleService.getAllRole(page, re);
		// List<Object[]> oRoleList =
		// roleService.selectRolesWithPermission(page, re);
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		//// for (Object[] item : oRoleList) {
		// Role role = (Role) item[0];
		// List<Object[]> permitList = (List<Object[]>) item[1];
		// String permissions = "";
		// for (Object[] pItem : permitList) {
		// permissions += pItem[1].toString().trim() + ",";
		// }
		// if (!"".equals(permissions))
		// permissions = permissions.substring(0, permissions.lastIndexOf(","));
		for (Role item : roleList) {
			List<Permission> permissions = this.permissionService.selectPermissionsByRoleId(item.getId());
			String per = "";
			for (Permission subItem : permissions) {
				per += subItem.getPermissionName().trim() + ",";
			}
			if (!"".equals(per)) {
				per = per.substring(0, per.lastIndexOf(","));
			}
			Map<String, Object> sub = new LinkedHashMap<String, Object>();
			sub.put("id", item.getId());
			sub.put("col1", item.getRoleName());
			sub.put("col2", item.getRoleSign());
			sub.put("col3", item.getDescription());
			sub.put("col4", per);
			sub.put("col5", DateUtils.dateTimeToString(item.getCdate()));
			rows.add(sub);
		}
		jArray = JSONArray.fromObject(rows);
		resultMap.put("pageNow", pageNow);
		resultMap.put("pageCount", page.getTotalPages());
		resultMap.put("pageSize", num);
		resultMap.put("total", page.getTotalCount());
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		jsonObject.put("rows", jArray);
		try {
			response.getWriter().print(jsonObject.toString());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得修改窗口的数据
	 *
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/showRoleUpdateDialog", method = { RequestMethod.GET, RequestMethod.POST })
	public void showRoleUpdateDialog(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String rid = request.getParameter("rid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (rid != null && !"".equals(rid.trim())) {
			List<Permission> permissionList = null;
			// String type = request.getParameter("type");
			permissionList = this.permissionService.selectList();
			Role oldRole = this.roleService.selectById(new Long(rid));
			List<Permission> permissions = this.permissionService.selectPermissionsByRoleId(oldRole.getId());
			StringBuilder sb = new StringBuilder();
			for (Permission perItem : permissionList) {
				sb.append("<label class='checkbox-inline'><input type='checkbox' name='permissionIds' value='"
						+ perItem.getId() + "' ");
				for (Permission subItem : permissions) {
					if (subItem.getId().longValue() == perItem.getId().longValue()) {
						sb.append(" checked");
						break;
					}
				}
				sb.append("/> " + perItem.getPermissionName() + "</label>");
			}
			resultMap.put("roleName", oldRole.getRoleName());
			resultMap.put("roleSign", oldRole.getRoleSign());
			resultMap.put("description", oldRole.getDescription());
			resultMap.put("selectPermission", sb.toString());
			resultMap.put("error", "");
		} else {
			resultMap.put("error", "参数错误");
		}
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		try {
			response.getWriter().print(jsonObject.toString());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得当前页码
	 *
	 * @param request
	 * @return
	 */
	private int getPageNow(HttpServletRequest request) {
		String pageS = "1";
		if (request.getParameter("pagenow") != null || "".equals(request.getParameter("pagenow"))) {
			pageS = request.getParameter("pagenow").toString();
		}
		return Integer.parseInt(pageS);
	}

	/**
	 * 获得显示条数
	 *
	 * @param request
	 * @return
	 */
	private int getPageSize(HttpServletRequest request) {
		String pageS = "15";
		if (request.getParameter("pagesize") != null || "".equals(request.getParameter("pagesize"))) {
			pageS = request.getParameter("pagesize").toString();
		}
		return Integer.parseInt(pageS);
	}

	/**
	 * 判断当前用户是否拥有操作次功能的权利
	 *
	 * @param permission
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isEnableDoThis(String permission) {
		boolean rdata = false;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated() && currentUser.isPermitted(permission)) {
				rdata = true;
			}
		} catch (AuthenticationException e) {
			this.log.error(e);
		}
		return rdata;
	}

}
