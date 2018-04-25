package com.baicheng.fork.web.service;

import java.util.List;
import java.util.Map;

import com.baicheng.domain.crm.meta.WebCity;
import com.baicheng.fork.domain.extend.OrderDestCity;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月22日 下午3:42:58
 */
public interface WebCityService extends CRMService {

	/**
	 * 获取城市名称列表
	 * 
	 * @return 城市名称列表
	 */
	List<WebCity> getCityList();

	/**
	 * 1：redis获取city数据map结构 key城市id value城市对象 2：redis没有从数据库获取封装成map之后放入redis
	 */
	Map<Long, WebCity> getCitysMap();

	/**
	 * 获取签证提单页目的地城市数据 为了防止依赖其他项目所以自己查询城市数据 城市数据包含：商品城市，poi城市 map key：国家id value
	 * 城市列表数据 查询到的城市放入redis缓存
	 * 
	 * @return
	 */
	Map<Long, List<OrderDestCity>> orderDestCitysMakeCache();

}
