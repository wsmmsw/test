package com.baicheng.fork.web.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.util.PageUtils;
import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.PermissionExample;
import com.baicheng.fork.domain.user.PermissionExample.Criteria;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.web.constants.ControllerConst;
import com.baicheng.fork.web.service.PermissionService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class PermitController {

	protected final Log log = LogFactory.getLog(getClass());

	@Resource
	private PermissionService permissionService;

	/**
	 * 权限列表
	 */
	@RequestMapping(value = "/listPermit", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listPermit(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("admin/listPermit");
		int pageNow = PageUtils.getPageNow(request);
		int num = PageUtils.getPageSize(request);
		Page<Permission> page = new Page<Permission>(pageNow, num);
		PermissionExample ue = new PermissionExample();
		ue.setOrderByClause(" id desc");
		Criteria cc = ue.createCriteria();
		boolean where = false;
		if (request.getParameter("sch_permissionName") != null
				&& !"".equals(request.getParameter("sch_permissionName").trim())) {
			cc.andPermissionNameLike(request.getParameter("sch_permissionName").trim());
			where = true;
		}
		if (!where) {
			ue.setOredCriteria(null);
		}
		List<Permission> permitList = permissionService.selectByExample(page, ue);
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		String uType = (String) subject.getSession().getAttribute("uType");
		view.addObject("userId", user.getId());
		view.addObject("uType", uType);
		view.addObject("permitList", permitList);
		view.addObject("page", page);
		return view;
	}

	/**
	 * 添加权限
	 *
	 * @param response
	 * @param permission
	 */
	@RequestMapping(value = "/addPermit", method = { RequestMethod.GET, RequestMethod.POST })
	public void addPermit(HttpServletResponse response, Permission permission) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		permissionService.insert(permission);
		resultMap.put("msg", "操作成功！");
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		try {
			response.getWriter().print(jsonObject.toString());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/updPermit", method = { RequestMethod.GET, RequestMethod.POST })
	public void updPermit(HttpServletResponse response, Permission permission) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Permission oldModal = permissionService.selectById(permission.getId());
		oldModal.setDescription(permission.getDescription());
		oldModal.setPermissionName(permission.getPermissionName());
		oldModal.setPermissionSign(permission.getPermissionSign());
		permissionService.update(oldModal);
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
	 * 查询列表Json方式
	 *
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/listPermitWithJson", method = { RequestMethod.GET, RequestMethod.POST })
	public void listPermitWithJson(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		JSONArray jArray = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageNow = PageUtils.getPageNow(request);
		int num = PageUtils.getPageSize(request);
		Page<Permission> page = new Page<Permission>(pageNow, num);
		PermissionExample ue = new PermissionExample();
		ue.setOrderByClause(" id desc");
		Criteria cc = ue.createCriteria();
		boolean where = false;
		if (request.getParameter("sch_permissionName") != null
				&& !"".equals(request.getParameter("sch_permissionName").trim())) {
			cc.andPermissionNameLike("%" + request.getParameter("sch_permissionName").trim() + "%");
			where = true;
		}
		if (!where) {
			ue.setOredCriteria(null);
		}
		List<Permission> permitList = permissionService.selectByExample(page, ue);
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for (Permission item : permitList) {
			Map<String, Object> sub = new LinkedHashMap<String, Object>();
			sub.put("id", item.getId());
			sub.put("col1", item.getPermissionName());
			sub.put("col2", item.getPermissionSign());
			sub.put("col3", item.getDescription());
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

	@RequestMapping(value = "/showPermitUpdateDialog", method = { RequestMethod.GET, RequestMethod.POST })
	public void showPermitUpdateDialog(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String pid = request.getParameter("pid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (pid != null && !"".equals(pid.trim())) {
			Permission oldModal = permissionService.selectById(new Long(pid));
			resultMap.put("description", oldModal.getDescription());
			resultMap.put("permissionName", oldModal.getPermissionName());
			resultMap.put("permissionSign", oldModal.getPermissionSign());
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

}
