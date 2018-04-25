package com.baicheng.fork.domain.joint.linktour;

import com.baicheng.domain.Domain;

/**
 * 领拓卖家结构
 * 
 * @author wsm 2018年4月20日下午5:20:18
 */
public class LTSeller extends Domain{

	private Long id; // 卖家的PARTNER ID号
	private String code; // 卖家的PARTNER CODE
	private String name; // 卖家的名称
	// private String short; //卖家的简称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
