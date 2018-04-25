package com.baicheng.fork.web.dto.response;

import java.util.List;

import com.baicheng.domain.crm.meta.WebProvince;

/**
 * @author mabaoyu
 * @date：2017年4月12日 上午10:12:03
 */
@SuppressWarnings("serial")
public class ProvinceResponseDTO extends BaseResponseDTO {

	private List<WebProvince> data;

	public ProvinceResponseDTO(Integer code, String message) {
		super(code, message);
	}

	public List<WebProvince> getData() {
		return data;
	}

	public void setData(List<WebProvince> data) {
		this.data = data;
	}

}
