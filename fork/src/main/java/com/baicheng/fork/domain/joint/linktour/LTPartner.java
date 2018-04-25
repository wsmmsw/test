package com.baicheng.fork.domain.joint.linktour;

import com.baicheng.domain.Domain;

/**
 * 合作伙伴的信息
 * 
 * @author wsm 2018年4月23日下午12:30:01
 */
public class LTPartner extends Domain {

	private String code; // 编码
	private String role; // 角色 IS_SELLER/IS_BUYER
	private String currency; // 报价本币
	private String destinations; // 开通的目的地

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}

}
