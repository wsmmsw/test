package com.baicheng.fork.web.dao;

import java.util.List;

import com.baicheng.domain.crm.meta.WebCountry;

/**
 * web_country
 */
public interface CountryMapper {

	// 获取热门国家数据
	List<WebCountry> getHotCountryList();

	// 获取全部国家数据
	List<WebCountry> getCountryList();

}
