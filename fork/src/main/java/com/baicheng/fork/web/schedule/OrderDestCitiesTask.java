package com.baicheng.fork.web.schedule;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.baicheng.fork.web.service.WebCityService;

/**
 * 提单页 目的地城市
 * 
 * @author wsm 2018年1月24日下午4:23:55
 */
@Component("orderDestCitysTask")
@Lazy(false)
public class OrderDestCitiesTask {

	@Resource
	private WebCityService webCityService;
	private static final Logger LOGGER = Logger.getLogger(OrderDestCitiesTask.class);

	/**
	 * 订单提单页城市数据缓存到redis
	 */
	public void orderDestCitysMakeCache() {
		try {
			this.webCityService.orderDestCitysMakeCache();
		} catch (Exception e) {
			LOGGER.error("########## orderDestCitysInCache 异常", e);
		}
	}

}
