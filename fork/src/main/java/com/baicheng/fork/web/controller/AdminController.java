package com.baicheng.fork.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.baicheng.fork.core.feature.orm.mybatis.Page;
import com.baicheng.fork.core.util.ApplicationUtils;
import com.baicheng.fork.core.util.DateUtils;
import com.baicheng.fork.core.util.FileTools;
import com.baicheng.fork.core.util.OperFileUtil;
import com.baicheng.fork.core.util.upload.BaichengUploadAPI;
import com.baicheng.fork.core.util.upload.UploadAPI;
import com.baicheng.fork.domain.user.Role;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.domain.user.UserExample;
import com.baicheng.fork.domain.user.UserExample.Criteria;
import com.baicheng.fork.web.constants.ControllerConst;
import com.baicheng.fork.web.service.RoleService;
import com.baicheng.fork.web.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统后台管理
 * <p>
 * 系统账户、配置等后台管理
 * </p>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private final Log log = LogFactory.getLog(getClass());

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	/**
	 * 平台维护人员列表
	 */
	@RequestMapping(value = "/listUser", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listUser(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("admin/listUser");
		int pageNow = getPageNow(request);
		int num = getPageSize(request);
		Page<User> page = new Page<User>(pageNow, num);
		UserExample ue = new UserExample();
		ue.setOrderByClause(" create_time,ldate desc");
		Criteria cc = ue.createCriteria();
		boolean where = false;
		if (request.getParameter("sch_username") != null && !"".equals(request.getParameter("sch_username").trim())) {
			cc.andUsernameLike("%" + request.getParameter("sch_username").trim() + "%");
			where = true;
		}
		if (request.getParameter("sch_truename") != null && !"".equals(request.getParameter("sch_truename").trim())) {
			cc.andTruenameLike("%" + request.getParameter("sch_truename").trim() + "%");
			where = true;
		}
		if (!where) {
			ue.setOredCriteria(null);
		}
		List<User> userList = this.userService.getUserWithExampleAndPage(page, ue);
		Page<Role> rpage = new Page<Role>(1, 2000000);
		List<Role> roleList = this.roleService.getAllRole(rpage, null);
		for (User item : userList) {
			List<Role> roleInfos = this.roleService.selectRolesByUserId(item.getId());
			String roles = "";
			for (Role pItem : roleInfos) {
				if (pItem != null) {
					roles += pItem.getRoleName() + ",";
				}
			}
			if (!"".equals(roles)) {
				roles = roles.substring(0, roles.lastIndexOf(","));
				item.setRoleNames(roles);
			}
		}
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		String uType = (String) subject.getSession().getAttribute("uType");
		view.addObject("userList", userList);
		view.addObject("roleList", roleList);
		view.addObject("page", page);
		view.addObject("userId", user.getId());
		view.addObject("uType", uType);
		return view;
	}

	/**
	 * 添加平台维护人员
	 */
	@RequestMapping(value = "/addUser", method = { RequestMethod.GET, RequestMethod.POST })
	public void addUser(HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User oldUser = this.userService.selectByUsername(request.getParameter("username"));
		if (oldUser != null) {
			resultMap.put("msg", "已经存在该用户！");
		}
		// 转换类型为MultipartHttpServletRequest
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件到map容器中
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		// 上传文件统一放到WEB-INF下的upfiles目录中，这样比较安全
		String uploadPath = OperFileUtil.getRootPath() + File.separator + "upfiles";
		// 所有头像文件放到profile文件夹中
		uploadPath += File.separator + "profile";
		// 生成最终的存放路径
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		uploadPath += File.separator + str;
		File uploadFolder = new File(uploadPath);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		Subject currentUser = SecurityUtils.getSubject();
		User doMan = (User) currentUser.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setCman(doMan.getId());
		user.setCreateTime(new Date());
		user.setPwd(ApplicationUtils.sha256Hex(request.getParameter("pwd")));
		user.setState("正常");
		user.setTruename(request.getParameter("truename"));
		user.setRoleIds(request.getParameterValues("roleIds"));
		for (Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			String fileName = mf.getOriginalFilename();// 获得原文件名
			String newName = OperFileUtil.renameFile(str, fileName);
			File newFile = new File(uploadFolder + File.separator + newName);
			FileCopyUtils.copy(mf.getBytes(), newFile);
			/**
			 * 安全加固，插入服务端验证，通过读取文件头，确认是正常的图片文件
			 */
			try {
				String filetype = FileTools.getFileType(newFile);
				if ("jpg".equals(filetype) || "gif".equals(filetype) || "png".equals(filetype)
						|| "bmp".equals(filetype)) {
					UploadAPI api = new BaichengUploadAPI();
					String portrait = api.upload(mf.getInputStream(), fileName);
					user.setPortrait(portrait);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				newFile.deleteOnExit();
			}
		}
		this.userService.insert(user);
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
	 * 修改账户信息
	 *
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updUser", method = { RequestMethod.GET, RequestMethod.POST })
	public void updUser(HttpServletResponse response, HttpServletRequest request) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 转换类型为MultipartHttpServletRequest
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件到map容器中
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		// 上传文件统一放到WEB-INF下的upfiles目录中，这样比较安全
		String uploadPath = OperFileUtil.getRootPath() + File.separator + "upfiles";
		// 所有头像文件放到profile文件夹中
		uploadPath += File.separator + "profile";
		// 生成最终的存放路径
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		uploadPath += File.separator + str;
		File uploadFolder = new File(uploadPath);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}
		Subject currentUser = SecurityUtils.getSubject();
		User doMan = (User) currentUser.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		User user = this.userService.selectById(new Long(request.getParameter("id")));
		user.setLman(doMan.getId());
		user.setLdate(new Date());
		if (request.getParameter("pwd") != null && !"".equals(request.getParameter("pwd"))) {
			user.setPwd(ApplicationUtils.sha256Hex(request.getParameter("pwd")));
		}
		user.setState(request.getParameter("state"));
		user.setTruename(request.getParameter("truename"));
		user.setRoleIds(request.getParameterValues("roleIds"));
		for (Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			String fileName = mf.getOriginalFilename();// 获得原文件名
			String newName = OperFileUtil.renameFile(str, fileName);
			File newFile = new File(uploadFolder + File.separator + newName);
			FileCopyUtils.copy(mf.getBytes(), newFile);
			/**
			 * 安全加固，插入服务端验证，通过读取文件头，确认是正常的图片文件
			 */
			try {
				String filetype = FileTools.getFileType(newFile);
				if ("jpg".equals(filetype) || "gif".equals(filetype) || "png".equals(filetype)
						|| "bmp".equals(filetype)) {
					UploadAPI api = new BaichengUploadAPI();
					String portrait = api.upload(mf.getInputStream(), fileName);
					user.setPortrait(portrait);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				newFile.deleteOnExit();
			}
		}
		this.userService.update(user);
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
	 * 停用账户
	 *
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/changeUserState", method = { RequestMethod.GET, RequestMethod.POST })
	public void deleteUser(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		User user = this.userService.selectById(new Long(uid));
		Subject currentUser = SecurityUtils.getSubject();
		User doMan = (User) currentUser.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		user.setLman(doMan.getId());
		user.setLdate(new Date());
		if (StringUtils.isBlank(type) || type.equals("0")) {
			user.setState("停用");
		} else {
			user.setState("正常");
		}
		this.userService.update(user);
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
	 * 获得某个用户的资料信息
	 *
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/showUserUpdateDialog", method = { RequestMethod.GET, RequestMethod.POST })
	public void showUserUpdateDialog(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String uid = request.getParameter("uid");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (uid != null && !"".equals(uid.trim())) {
			User user = this.userService.selectById(new Long(uid));
			resultMap.put("truename", user.getTruename());
			resultMap.put("username", user.getUsername());
			resultMap.put("state", user.getState());
			resultMap.put("portrait", user.getPortrait());
			Page<Role> rpage = new Page<Role>(1, 2000000);
			List<Role> roleList = this.roleService.getAllRole(rpage, null);
			List<Role> roleInfos = this.roleService.selectRolesByUserId(new Long(uid));
			StringBuilder sb = new StringBuilder();
			String roles = "";
			for (Role perItem : roleList) {
				sb.append("<label class='checkbox-inline'><input type='checkbox' name='roleIds' value='"
						+ perItem.getId() + "' ");
				for (Role subItem : roleInfos) {
					if (subItem.getId().longValue() == perItem.getId().longValue()) {
						sb.append(" checked");
						roles += subItem.getRoleName() + ",";
						break;
					}
				}
				sb.append("/> " + perItem.getRoleName() + "</label>");
			}
			if (!"".equals(roles)) {
				roles = roles.substring(0, roles.lastIndexOf(","));
			}
			resultMap.put("roleIds", sb.toString());
			resultMap.put("roleNames", roles);
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
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/listUserWithJson")
	public void listUserWithJson(HttpServletResponse response, HttpServletRequest request) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		JSONArray jArray = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageNow = getPageNow(request);
		int num = getPageSize(request);
		Page<User> page = new Page<User>(pageNow, num);
		UserExample ue = new UserExample();
		ue.setOrderByClause(" create_time,ldate desc");
		Criteria cc = ue.createCriteria();
		boolean where = false;
		if (request.getParameter("sch_username") != null && !"".equals(request.getParameter("sch_username").trim())) {
			cc.andUsernameLike(request.getParameter("sch_username").trim());
			where = true;
		}
		if (request.getParameter("sch_truename") != null && !"".equals(request.getParameter("sch_truename").trim())) {
			cc.andTruenameLike(request.getParameter("sch_truename").trim());
			where = true;
		}
		if (!where) {
			ue.setOredCriteria(null);
		}
		// List<Object[]> userList = userService.getAllUser(page, ue);

		List<User> userList = this.userService.getUserWithExampleAndPage(page, ue);
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		for (User item : userList) {
			Map<String, Object> sub = new LinkedHashMap<String, Object>();
			sub.put("id", item.getId());
			sub.put("col1", item.getTruename());
			sub.put("col2", item.getUsername());
			String roles = "";
			List<Role> roleInfos = this.roleService.selectRolesByUserId(item.getId());
			for (Role pItem : roleInfos) {
				if (pItem != null) {
					roles += pItem.getRoleName() + ",";
				}
			}
			if (!"".equals(roles)) {
				roles = roles.substring(0, roles.lastIndexOf(","));
				item.setRoleNames(roles);
			}
			sub.put("col3", roles);
			sub.put("col4", item.getState());
			sub.put("col5", DateUtils.dateTimeToString(item.getCreateTime()));
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
