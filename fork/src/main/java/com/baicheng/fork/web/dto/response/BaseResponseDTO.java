package com.baicheng.fork.web.dto.response;

import com.baicheng.domain.Domain;

/**
 * 请求响应基础信息
 *
 * @author mabaoyu
 */
@SuppressWarnings("serial")
public class BaseResponseDTO extends Domain {

	/**
	 * 成功
	 */
	public static final Integer SUCCESS = 100000;
	/**
	 * 失败
	 */
	public static final Integer FAILURE = -1;
	/**
	 * 部分成功
	 */
	public static final Integer PART = 1;
	/**
	 * 登录异常
	 */
	public static final Integer EXCEPTION_LOGIN = -2;

	public static final String DEFAULT_MESSAGE = "success";

	/**
	 * 返回编码
	 */
	protected Integer code = SUCCESS;

	/**
	 * 返回消息
	 */
	protected String message;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFailure(String message) {
		this.code = FAILURE;
		this.message = message;
	}

	public BaseResponseDTO(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BaseResponseDTO() {
		super();
	}

	public void setBaseReply(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public void setFailWallReplay(String msg) {
		this.code = FAILURE;
		this.message = msg;
	}

	public static BaseResponseDTO getDefaultSuccessReply() {
		return new BaseResponseDTO(BaseResponseDTO.SUCCESS, DEFAULT_MESSAGE);
	}
}
