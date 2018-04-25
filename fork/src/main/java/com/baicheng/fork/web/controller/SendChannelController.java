package com.baicheng.fork.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baicheng.fork.core.util.JsonUtil;
import com.baicheng.fork.domain.util.EmailChannel;
import com.baicheng.fork.domain.util.SMSChannel;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.service.EmailChannelService;
import com.baicheng.fork.web.service.SMSChannelService;

/**
 * 邮件、短信通道控制器.
 * 
 * @author SongPengpeng
 * @date 2017/5/15.
 */
@Controller
@RequestMapping("/channel")
public class SendChannelController {

	public static final Logger LOGGER = Logger.getLogger(SendChannelController.class);

	@Resource
	private EmailChannelService emailChannelService;
	@Resource
	private SMSChannelService smsChannelService;

	/**
	 * 通道页面action.
	 *
	 * @return
	 */
	@RequestMapping(value = "/email/view", method = { RequestMethod.GET })
	public ModelAndView emailChannelView() {
		ModelAndView modelAndView = new ModelAndView("/util/email/channel");
		List<EmailChannel> channels = emailChannelService.getList(-1, -1);
		modelAndView.addObject("channels", channels);
		return modelAndView;
	}

	/**
	 * 更新邮件发送通道.
	 *
	 * @param channelId
	 */
	@ResponseBody
	@RequestMapping(value = "/email/update", method = RequestMethod.GET)
	public String updateEmailChannel(@RequestParam("channelId") long channelId) throws IOException {
		BaseResponseDTO dto = new BaseResponseDTO();
		try {
			emailChannelService.updateChannel(channelId);
			dto.setCode(BaseResponseDTO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			dto.setCode(BaseResponseDTO.FAILURE);
		}
		return JsonUtil.bean2json(dto);
	}

	/**
	 * 查询当前邮件通道.
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/email/currentChannel", method = { RequestMethod.GET, RequestMethod.POST })
	public void getCurrentEmailChannel(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		EmailChannel emailChannel = emailChannelService.getCurrentChannel();
		String data = JsonUtil.bean2json(emailChannel);
		try {
			response.getWriter().print(data);
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通道页面.
	 *
	 * @return
	 */
	@RequestMapping(value = "/sms/view", method = { RequestMethod.GET })
	public ModelAndView SMSChannelView() {
		ModelAndView modelAndView = new ModelAndView("/util/sms/channel");
		List<SMSChannel> channels = smsChannelService.getList(0, 0);
		modelAndView.addObject("channels", channels);
		return modelAndView;
	}

	/**
	 * 更新发送通道.
	 *
	 * @param channelId
	 */
	@ResponseBody
	@RequestMapping(value = "/sms/update", method = RequestMethod.GET)
	public String updateSMSChannel(long channelId) throws IOException {
		BaseResponseDTO dto = new BaseResponseDTO();
		try {
			smsChannelService.update(channelId);
			dto.setCode(BaseResponseDTO.SUCCESS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			dto.setCode(BaseResponseDTO.FAILURE);
		}
		return JsonUtil.bean2json(dto);
	}

}
