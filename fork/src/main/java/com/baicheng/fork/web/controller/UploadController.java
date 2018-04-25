package com.baicheng.fork.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baicheng.fork.core.util.JSONPUtils;
import com.baicheng.fork.core.util.JsonUtil;
import com.baicheng.fork.core.util.upload.BaichengUploadAPI;
import com.baicheng.fork.core.util.upload.UploadAPI;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.dto.response.UploadFileResponseDTO;

import sun.misc.BASE64Decoder;

/**
 * @author wsm
 * @date 2017年5月10日下午2:53:36 文件上传 文件可以是图片，文件等类型
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger LOGGER = Logger.getLogger(UploadController.class.getName());

	/**
	 * 通用文件上传接口.
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.GET, RequestMethod.POST })
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UploadFileResponseDTO responseDTO = new UploadFileResponseDTO();
		LOGGER.info("---uploadFile start---");
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile fileData = null;
			if (multipartRequest.getFiles("fileData").size() > 0) {
				fileData = multipartRequest.getFiles("fileData").get(0);
				if (fileData != null) {
					InputStream inputStream = fileData.getInputStream();
					// 获得原文件名
					String fileName = fileData.getOriginalFilename();
					UploadAPI api = new BaichengUploadAPI();
					String resultUrl = api.upload(inputStream, fileName);
					if (resultUrl != null && !"".equals(resultUrl)) {
						responseDTO.setCode(BaseResponseDTO.SUCCESS);
						responseDTO.setMessage("success");
						responseDTO.setResultUrl(resultUrl);
					} else {
						responseDTO.setCode(BaseResponseDTO.FAILURE);
						responseDTO.setMessage("没有返回链接");
					}
				} else {
					responseDTO.setCode(BaseResponseDTO.FAILURE);
					responseDTO.setMessage("没有获取到文件");
				}
			} else {
				responseDTO.setCode(BaseResponseDTO.FAILURE);
				responseDTO.setMessage("没有获取到文件");
			}
			// 解决跨域问题增加的参数
			response.setHeader("Access-Control-Allow-Origin", "*");
			JSONPUtils.jsonp(responseDTO);
			LOGGER.info("---responseDTO---" + JsonUtil.bean2json(responseDTO));
		} catch (Exception e) {
			LOGGER.error("---upload---上传文件异常", e);
			responseDTO.setCode(BaseResponseDTO.FAILURE);
			responseDTO.setMessage("上传文件异常");
			response.setHeader("Access-Control-Allow-Origin", "*");
			JSONPUtils.jsonp(responseDTO);
			e.printStackTrace();
		}
	}

	/**
	 * froala插件的图片上传接口.
	 *
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/froala/image", method = { RequestMethod.POST })
	@ResponseBody
	public String froalaUploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = null;
			Map<Object, Object> responseData = null;
			if (multipartRequest.getFiles("file").size() > 0) {
				file = multipartRequest.getFiles("file").get(0);
				if (file != null) {
					InputStream inputStream = file.getInputStream();
					// 获得原文件名
					String fileName = file.getOriginalFilename();
					UploadAPI api = new BaichengUploadAPI();
					String resultUrl = api.upload(inputStream, fileName);
					responseData = new HashMap<>();
					// TODO:mock
					resultUrl = "https://img8.byecity.com" +
							".cn/fs/brs/files/qita/2017-03-21/thumb/thumb_qita_1490086243_3448.jpg";
					responseData.put("link", resultUrl);
				}
			}
			response.setHeader("Access-Control-Allow-Origin", "*");

			return JsonUtil.bean2json(responseData);
		} catch (Exception e) {
			LOGGER.error("---upload---上传文件异常", e);
			return "{}";
		}
	}

	@RequestMapping(value = "/fileUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public void imgUpload(String fileType, Integer type, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setHeader("Access-Control-Allow-Origin", "*"); // 第二个参数填写允许跨域的域名称
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		UploadFileResponseDTO reply = new UploadFileResponseDTO();
		reply.setCode(BaseResponseDTO.SUCCESS);
		reply.setMessage(BaseResponseDTO.DEFAULT_MESSAGE);
		// System.out.println(request.getParameter("fileBase64"));
		String base64 = request.getParameter("fileBase64").split("base64,")[1];
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decoderBytes = decoder.decodeBuffer(base64);
		ByteArrayInputStream is = new ByteArrayInputStream(decoderBytes);
		UploadAPI api = new BaichengUploadAPI();
		String url = api.upload(is, System.currentTimeMillis() + fileType);

		reply.setResultUrl(url);
		if (type != null && type.equals(1)) {
			response.getWriter().print(reply.getResultUrl());
		} else {
			JSONPUtils.jsonp(reply);
		}
	}

}
