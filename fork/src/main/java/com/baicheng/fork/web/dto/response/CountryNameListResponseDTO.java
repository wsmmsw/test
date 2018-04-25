package com.baicheng.fork.web.dto.response;

import java.util.List;

/**
 * @author mabaoyu
 * @date：2017年3月28日 下午3:11:40
 */
public class CountryNameListResponseDTO extends BaseResponseDTO {

	private List<CountryResponseData> data;

	public CountryNameListResponseDTO() {
	}

	public CountryNameListResponseDTO(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public List<CountryResponseData> getData() {
		return data;
	}

	public void setData(List<CountryResponseData> data) {
		this.data = data;
	}

}
