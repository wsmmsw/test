package com.baicheng.fork.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baicheng.fork.core.util.ApplicationUtils;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.web.constants.ControllerConst;
import com.baicheng.fork.web.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/personal")
public class PersonalController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/changPwd", method = { RequestMethod.GET, RequestMethod.POST })
	public void changPwd(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			Object u = currentUser.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
			if (u instanceof User) {
				User doMan = (User) u;
				if (doMan.getPwd().equals(ApplicationUtils.sha256Hex(request.getParameter("oldPwd")))) {
					doMan.setPwd(ApplicationUtils.sha256Hex(request.getParameter("newPwd")));
					int dd = userService.update(doMan);
					if (dd > 0) {
						resultMap.put("msg", "密码修改成功！");
					} else {
						resultMap.put("msg", "密码修改失败！");
					}
				} else {
					resultMap.put("msg", "原密码错误，请重新输入！");
				}
			}
		} else {
			resultMap.put("msg", "登录超时，请重新登录！");
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
