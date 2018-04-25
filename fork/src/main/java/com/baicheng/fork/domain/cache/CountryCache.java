package com.baicheng.fork.domain.cache;

import java.util.Map;

import com.baicheng.fork.core.util.BeanUtils;
import com.baicheng.fork.web.service.CountryService;

/**
 * Created by wsm on 2017/4/18. 国家数据，用于短信推送的时候通配符的替换
 */
public class CountryCache {

	private Map<Long, String> mapCountryCache;

	private static class LazyHolder {
		private static final CountryCache INSTANCE = new CountryCache();
	}

	private CountryCache() {
		CountryService service = BeanUtils.getInstance().getService(
				CountryService.class);
		if (service != null) {
			mapCountryCache = service.getCountryMap();
		}
	}

	public static final CountryCache getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Map<Long, String> getMapCountryCache() {
		return mapCountryCache;
	}

	public void setMapCountryCache(Map<Long, String> mapCountryCache) {
		this.mapCountryCache = mapCountryCache;
	}
}