package com.baicheng.fork.domain.joint.linktour;

import com.baicheng.domain.Domain;

/**
 * 领拓产品详情基础结构
 * 
 * @author wsm 2018年4月23日下午3:48:40
 */
public class LTBaseContent extends Domain {

	private String detail; // 详情介绍
	private String included; // 费用包含
	private String excluded; // 费用不包含
	private String usability; // 使用方法（TEXT）
	private String attention; // 预订须知（注意事项）（TEXT）

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getIncluded() {
		return included;
	}

	public void setIncluded(String included) {
		this.included = included;
	}

	public String getExcluded() {
		return excluded;
	}

	public void setExcluded(String excluded) {
		this.excluded = excluded;
	}

	public String getUsability() {
		return usability;
	}

	public void setUsability(String usability) {
		this.usability = usability;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

}
