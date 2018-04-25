package com.baicheng.fork.core.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baicheng.fork.core.util.JsonUtil;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;

/**
 * webservice异常
 * 
 * @author mabaoyu
 *
 */
@SuppressWarnings("serial")
public class WSResponseException extends RuntimeException {

	private Map<String, Object> map = new LinkedHashMap<String, Object>(2);

	private static final Logger LOGGER = LoggerFactory.getLogger(WSResponseException.class);

	private BaseResponseDTO baseReply = new BaseResponseDTO();

	public WSResponseException(Throwable cause) {
		super(cause);
	}

	public WSResponseException(String message, Object input, Object output) {
		super(message);
		this.map.put("输入参数", input == null ? input = "" : input);
		this.map.put("输出参数", output == null ? output = "" : output);
	}

	public WSResponseException(BaseResponseDTO br) {
		super(br.getMessage());
		this.baseReply = br;
	}

	public BaseResponseDTO IgnoreAndLog() {
		LOGGER.info(getLocalizedMessage());
		return new BaseResponseDTO(BaseResponseDTO.FAILURE, super.getMessage());
	}

	public static BaseResponseDTO throwsError(BaseResponseDTO br) {
		throw new WSResponseException(br);
	}

	public static BaseResponseDTO throwsError(String message) {
		LOGGER.error(message);
		throw new WSResponseException(new BaseResponseDTO(BaseResponseDTO.FAILURE, message));
	}

	public static BaseResponseDTO throwsError(String message, Integer code) {
		throw new WSResponseException(new BaseResponseDTO(code, message));
	}

	@Override
	public String getLocalizedMessage() {
		String r = "";
		try {
			r = "错误描述:" + super.getMessage() + "," + JsonUtil.bean2json(this.map);
		} catch (Exception e) {
			r = "错误描述:" + super.getMessage() + "," + this.map.toString();
		}
		return r;
	}

	public BaseResponseDTO getBaseReply() {
		return this.baseReply;
	}

}
