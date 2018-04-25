package com.baicheng.fork.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baicheng.domain.crm.meta.WebCity;
import com.baicheng.domain.crm.meta.WebCountry;
import com.baicheng.fork.core.util.JsonUtil;
import com.baicheng.fork.domain.extend.OrderDestCity;
import com.baicheng.fork.web.constants.CacheConstants;
import com.baicheng.fork.web.dao.WebCityMapper;
import com.baicheng.fork.web.dao.WebCountryMapper;
import com.baicheng.fork.web.service.WebCityService;
import com.google.gson.reflect.TypeToken;
import com.nicetrip.redis.client.CacheClientShard;
import com.nicetrip.redis.client.KeyConstants;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月22日 下午3:44:44
 */
@Service
public class WebCityServiceImpl implements WebCityService {

	private static final Logger LOGGER = Logger.getLogger(WebCityServiceImpl.class);
	@Resource
	private WebCityMapper webCityMapper;
	@Resource
	private WebCountryMapper webCountryMapper;
	@Resource
	private CacheClientShard cacheClientShard;

	@Override
	public List<WebCity> getCityList() {
		return webCityMapper.getCityNameList();
	}

	@Override
	public Map<Long, WebCity> getCitysMap() {
		Map<Long, WebCity> citysMap = null;
		try {
			// 1:先从缓存获取
			String citsMapStr = cacheClientShard.get(CacheConstants.FORK_WEB_CITIES_MAP);
			if (citsMapStr != null && !"".equals(citsMapStr)) {
				citysMap = JsonUtil.json2bean(citsMapStr, new TypeToken<Map<Long, WebCity>>() {
				}.getType());
			} else {
				// 2:缓存没有从数据库查询，放入缓存
				List<WebCity> citys = webCityMapper.getCityList();
				if (citys != null && citys.size() > 0) {
					citysMap = new HashMap<Long, WebCity>(citys.size());
					for (WebCity city : citys) {
						if (city != null) {
							citysMap.put(city.getId(), city);
						}
					}
					String cityMapStr = JsonUtil.bean2json(citysMap);
					cacheClientShard.set(CacheConstants.FORK_WEB_CITIES_MAP,
							cityMapStr, KeyConstants.SECONDS_1_DAY);
				}
			}
		} catch (Exception e) {
			LOGGER.error("###getCitysMap exception", e);
			e.printStackTrace();
		}
		return citysMap;
	}

	@Override
	public Map<Long, List<OrderDestCity>> orderDestCitysMakeCache() {
		try {
			// 查询商品和poi对应的城市数据，查询语句和H5，PC签证提单页目的地城市一样
			List<OrderDestCity> orderDestCities = webCityMapper.selectOrderDestCity();
			if (orderDestCities != null && orderDestCities.size() > 0) {
				Map<Long, List<OrderDestCity>> map = new HashMap<>();
				for (OrderDestCity destCity : orderDestCities) {
					if (destCity != null) {
						long countryId = destCity.getCountryId();
						if (map.containsKey(countryId)) {
							// 如果这个国家下已经有城市数据，在原有的基础上新增
							List<OrderDestCity> cities = map.get(countryId);
							cities.add(destCity);
						} else {
							// 如果这个国家下没有城市，新添加数据
							List<OrderDestCity> cities = new ArrayList<>();
							cities.add(destCity);
							map.put(countryId, cities);
						}
					}
				}

				// 固定拼接其他城市
				OrderDestCity destCity = new OrderDestCity();
				destCity.setId(-1);
				destCity.setNameCn("其他城市");
				for (Map.Entry<Long, List<OrderDestCity>> entry : map.entrySet()) {
					List<OrderDestCity> cities = entry.getValue();
					cities.add(destCity);
				}
				String jsonMap = JsonUtil.bean2json(map);
				cacheClientShard.set(CacheConstants.FORK_WEB_COUNTRY_CITIES_MAP, jsonMap);
			}
		} catch (Exception e) {
			LOGGER.error("###########getOrderDestCitys exception", e);
			e.printStackTrace();
		}
		return null;
	}

}
