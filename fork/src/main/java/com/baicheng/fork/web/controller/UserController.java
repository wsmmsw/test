package com.baicheng.fork.web.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baicheng.fork.core.util.JSONPUtils;
import com.baicheng.fork.core.util.StringUtils;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.web.constants.ControllerConst;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.dto.response.UserResponseDTO;
import com.baicheng.fork.web.dto.response.UserResponseData;
import com.baicheng.fork.web.security.CRMToken;
import com.baicheng.fork.web.service.RoleService;
import com.baicheng.fork.web.service.UserService;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	/**
	 * index页面
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/front/page/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "index";
	}

	/**
	 * 登录
	 *
	 * @param user
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
		model.addAttribute("randd", UUID.randomUUID());
		try {
			Subject subject = SecurityUtils.getSubject();
			// 已登陆,则跳到首页
			final User tbuser = (User) subject.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
			String username = request.getParameter("username");
			if (subject.isAuthenticated() && tbuser.getUsername().equals(username)) {
				return "redirect:/";
			}
			if (result.hasErrors()) {
				model.addAttribute("error", "参数错误！");
				return "login";
			}
			// 检查验证码
			String rand = (String) request.getSession().getAttribute("rand");
			String randForm = request.getParameter("rand");
			if (StringUtils.hasLength(rand)) {
				if (!rand.equalsIgnoreCase(randForm)) {
					model.addAttribute("error", "验证码不正确");
					return "login";
				}
				// 身份验证通过，shiro中登录
				String host = request.getLocalName();
				char[] pwd = user.getPwd().toCharArray();
				subject.login(new CRMToken(user.getUsername(), pwd, false, host, request.getParameter("uType")));
				// 验证成功在Session中保存用户信息utype=0 表示平台账户登录，否则表示商户账户，前者是user表，后者是t_buser表
				final User authUserInfo = userService.selectByUsername(user.getUsername());
				subject.getSession().setAttribute(ControllerConst.SESSION_USER_INFO, authUserInfo);
				subject.getSession().setAttribute("uType", "0");
				subject.getSession().setTimeout(86400000);
				// subject.getSession().setAttribute(arg0, arg1);
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
			// 身份认证失败
			model.addAttribute("error", "用户名或密码错误 ！");
			return "login";
		}
		return "redirect:/web/front/page/index";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String uType = (String) session.getAttribute("uType");
		session.removeAttribute(ControllerConst.SESSION_USER_INFO);
		session.removeAttribute("uType");
		if (session.getAttribute("bizInfo") != null) {
			session.removeAttribute("bizInfo");
		}
		if (session.getAttribute("nationL") != null) {
			session.removeAttribute("nationL");
		}
		// 权限删除
		if (session.getAttribute("permission") != null) {
			session.removeAttribute("permission");
		}
		// 登出操作
		subject.logout();
		if ("0".equals(uType)) {
			return "redirect:/web/page/login";
		} else {
			return "redirect:/web/page/login";
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/userInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public void userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserResponseDTO reply = new UserResponseDTO(BaseResponseDTO.SUCCESS, BaseResponseDTO.DEFAULT_MESSAGE);
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User) session.getAttribute(ControllerConst.SESSION_USER_INFO);
		UserResponseData data = new UserResponseData();
		if (user != null) {
			data.setId(user.getId());
			data.setName(user.getTruename());
			data.setPortrait(user.getPortrait());
			reply.setData(data);
		} else {
			reply.setFailure("userId or uType is null");
		}
		JSONPUtils.jsonp(reply);
	}

}
