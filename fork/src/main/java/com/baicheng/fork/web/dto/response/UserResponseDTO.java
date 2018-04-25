package com.baicheng.fork.web.dto.response;

/**
 * @author mabaoyu
 * @date：2017年4月17日 下午4:33:54
 */
public class UserResponseDTO extends BaseResponseDTO {

	private UserResponseData data;

	public UserResponseDTO() {
	}

	public UserResponseDTO(Integer code, String message) {
		super(code, message);
	}

	public UserResponseData getData() {
		return data;
	}

	public void setData(UserResponseData data) {
		this.data = data;
	}

}
