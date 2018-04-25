package com.baicheng.fork.core.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baicheng.fork.core.entity.RequestThreadLocal;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;

/**
 * @author mabaoyu
 * @date：2017年3月15日 上午11:00:59
 */
public class JSONPUtils {

	/**
	 * 处理跨域访问
	 */
	public static void jsonp(BaseResponseDTO responseDTO) throws IOException {
		HttpServletRequest request = RequestThreadLocal.getRequest();
		HttpServletResponse response = RequestThreadLocal.getResponse();

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		String callBackParam = request.getParameter("jsonp");
		String json = JsonUtil.bean2json(responseDTO);
		String result = json;
		if (callBackParam != null && !callBackParam.equals("")) {
			result = callBackParam + "(" + json + ")";
		}
		response.getWriter().print(result);
	}

}
