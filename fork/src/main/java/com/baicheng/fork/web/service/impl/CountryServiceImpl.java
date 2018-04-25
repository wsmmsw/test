package com.baicheng.fork.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.domain.crm.meta.WebCountry;
import com.baicheng.fork.web.dao.CountryMapper;
import com.baicheng.fork.web.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	@Resource
	CountryMapper countryMapper;

	@Override
	public List<WebCountry> getHotCountryList() {
		return countryMapper.getHotCountryList();
	}

	@Override
	public Map<Long, String> getCountryMap() {
		Map<Long, String> mapCountryCache = new HashMap<>();
		List<WebCountry> countryList = countryMapper.getCountryList();
		if (countryList != null && countryList.size() > 0) {
			for (WebCountry country : countryList) {
				if (country != null) {
					mapCountryCache.put(country.getId(), country.getNameCn());
				}
			}
		}
		return mapCountryCache;
	}

}
