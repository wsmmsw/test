package com.baicheng.fork.web.dto.response;

import java.util.List;

import com.baicheng.domain.crm.meta.WebCountry;

/**
 * @author mabaoyu
 * @date：2017年4月12日 下午6:28:33
 */
public class CountryResponseData {

	private String name;

	private List<WebCountry> countryList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WebCountry> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<WebCountry> countryList) {
		this.countryList = countryList;
	}

}
