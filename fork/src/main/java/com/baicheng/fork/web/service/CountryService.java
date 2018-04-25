package com.baicheng.fork.web.service;

import java.util.List;
import java.util.Map;

import com.baicheng.domain.crm.meta.WebCountry;

public interface CountryService extends CRMService {

	// 获取热门国家
	List<WebCountry> getHotCountryList();

	// 将国家封装成map
	Map<Long, String> getCountryMap();

}
