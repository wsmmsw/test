package com.baicheng.fork.domain.extend;

import com.baicheng.domain.Domain;

/**
 * 提单页目的地城市结构
 *
 * @author wsm 2018年1月24日下午4:32:15
 */
public class OrderDestCity extends Domain {
	/** 城市id */
	private long id;
	/** 城市名称 */
	private String nameCn;
	/** 国家id */
	private long countryId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

}
