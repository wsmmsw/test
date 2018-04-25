package com.baicheng.fork.web.dto.response;

/**
 * @author mabaoyu
 * @date：2017年4月17日 下午6:54:11
 */
public class MenuPermitResponseDTO extends BaseResponseDTO {

	private MenuPermitResponseData data;

	public MenuPermitResponseDTO() {
	}

	public MenuPermitResponseDTO(Integer code, String message) {
		super(code, message);
	}

	public MenuPermitResponseData getData() {
		return data;
	}

	public void setData(MenuPermitResponseData data) {
		this.data = data;
	}

}
