package com.baicheng.fork.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baicheng.fork.core.util.JSONPUtils;
import com.baicheng.fork.domain.user.Permission;
import com.baicheng.fork.domain.user.User;
import com.baicheng.fork.web.constants.ControllerConst;
import com.baicheng.fork.web.constants.PermitConstants;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.dto.response.MenuPermitResponseDTO;
import com.baicheng.fork.web.dto.response.MenuPermitResponseData;
import com.baicheng.fork.web.service.PermissionService;

/**
 * @author mabaoyu
 * @date：2017年4月17日 下午6:30:39
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {

	@Resource
	private PermissionService permissionService;

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/menu", method = { RequestMethod.GET, RequestMethod.POST })
	public void menu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute(ControllerConst.SESSION_USER_INFO);
		String uType = (String) subject.getSession().getAttribute("uType");
		MenuPermitResponseDTO reply = new MenuPermitResponseDTO(BaseResponseDTO.SUCCESS,
				BaseResponseDTO.DEFAULT_MESSAGE);
		MenuPermitResponseData data = new MenuPermitResponseData();
		if (user != null && uType != null) {
			String permitStr = (String) subject.getSession().getAttribute("permission");
			if (permitStr == null || "".equals(permitStr)) {
				List<Permission> list = permissionService.selectByUserId(user.getId());
				StringBuilder buff = new StringBuilder();
				for (Permission p : list) {
					if (p != null) {
						buff.append(p.getPermissionSign());
						buff.append(",");
					}
				}
				permitStr = buff.toString();
				subject.getSession().setAttribute("permission", permitStr);
			}
			initPermitData(data, permitStr);
			reply.setData(data);
		} else {
			reply.setFailure("userId or uType is null");
		}
		JSONPUtils.jsonp(reply);
	}

	public void initPermitData(MenuPermitResponseData data, String permitStr) {
		if (permitStr.contains(PermitConstants.CRM_MEMBER)) {
			data.setOneLevelMember(1);
		}
		if (permitStr.contains(PermitConstants.CRM_SMS)) {
			data.setOneLevelSms(1);
		}
		if (permitStr.contains(PermitConstants.CRM_SYSTEM)) {
			data.setOneLevelSystem(1);
		}
		if (permitStr.contains(PermitConstants.CRM_PERSIONAL)) {
			data.setOneLevelPersional(1);
		}
		if (permitStr.contains(PermitConstants.MEMBER_ADD)) {
			data.setTwoLevelAddGroup(1);
		}
		if (permitStr.contains(PermitConstants.MEMBER_LIST)) {
			data.setTwoLevelListCustomer(1);
		}
		if (permitStr.contains(PermitConstants.SMS_SEND)) {
			data.setTwoLevelSendSms(1);
		}
		if (permitStr.contains(PermitConstants.SMS_LOG)) {
			data.setTwoLevelListSms(1);
		}
		if (permitStr.contains(PermitConstants.SYSTEM_USER)) {
			data.setTwoLevelUserManage(1);
		}
		if (permitStr.contains(PermitConstants.SYSTEM_ROLE)) {
			data.setTwoLevelRoleManage(1);
		}
		if (permitStr.contains(PermitConstants.SYSTEM_PERMIT)) {
			data.setTwoLevelPermitManage(1);
		}
		if (permitStr.contains(PermitConstants.PERSIONAL_PASSWORD)) {
			data.setTwoLevelPassword(1);
		}
		if (permitStr.contains(PermitConstants.SMS_TASK)) {
			data.setTwoLevelAddTask(1);
		}
		if (permitStr.contains(PermitConstants.SMS_TASK_LIST)) {
			data.setTwoLevelListTask(1);
		}
		if (permitStr.contains(PermitConstants.CRM_BLACK)) {
			data.setOneLevelBlack(1);
		}
		if (permitStr.contains(PermitConstants.BLACK_ADD)) {
			data.setTwoLevelAddBlack(1);
		}
		if (permitStr.contains(PermitConstants.BLACK_LIST)) {
			data.setTwoLevelListBlack(1);
		}
		if (permitStr.contains(PermitConstants.CRM_EMAIL)) {
			data.setOneLevelEmail(1);
		}
		if (permitStr.contains(PermitConstants.EMAIL_TASK)) {
			data.setTwoLevelEmailTask(1);
		}
		if (permitStr.contains(PermitConstants.EMAIL_TASK_LIST)) {
			data.setTwoLevelEmailTaskList(1);
		}
		if (permitStr.contains(PermitConstants.EMAIL_TEMPLATE)) {
			data.setTwoLevelEmailTemplate(1);
		}
		if (permitStr.contains(PermitConstants.EMAIL_TEMPLATE_LIST)) {
			data.setTwoLevelEmailTemplateList(1);
		}
		if (permitStr.contains(PermitConstants.EMAIL_SEND)) {
			data.setTwoLevelEmailSend(1);
		}
		if (permitStr.contains(PermitConstants.EMAIL_LOG)) {
			data.setTwoLevelEmailLog(1);
			;
		}
		if (permitStr.contains(PermitConstants.EMAIL_CHANNEL_SET)) {
			data.setTwoLevelEmailChannel(1);
		}
		if (permitStr.contains(PermitConstants.CRM_PUSH)) {
			data.setOneLevelPush(1);
		}
		if (permitStr.contains(PermitConstants.PUSH_SCRM)) {
			data.setTwoLevelPushScrm(1);
		}
		if (permitStr.contains(PermitConstants.PUSH_SMY)) {
			data.setTwoLevelPushSmy(1);
		}
		if (permitStr.contains(PermitConstants.SMS_CHANNEL)) {
			data.setTwoLevelSmsChannel(1);
		}
		// 数据统计
		if (permitStr.contains(PermitConstants.CRM_STATISTICS)) {
			data.setOneLevelStatistics(1);
		}
		if (permitStr.contains(PermitConstants.STATISTICS_HEADMAP)) {
			data.setTwoLevelStatisticsHeadmap(1);
		}
		if (permitStr.contains(PermitConstants.STATISTICS_PROD)) {
			data.setTwoLevelStatisticsProd(1);
		}
	}

}
