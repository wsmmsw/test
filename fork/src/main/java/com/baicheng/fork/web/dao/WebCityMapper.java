package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.domain.crm.meta.WebCity;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.domain.extend.OrderDestCity;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月22日 下午3:35:17
 */
public interface WebCityMapper extends GenericDao<WebCity, Integer> {

	/**
	 * 获取城市名称列表
	 * 
	 * @return 名称列表
	 */
	List<WebCity> getCityNameList();

	/**
	 * 获取城市id，名字，经纬度
	 * 
	 * @return
	 */
	List<WebCity> getCityList();

	/**
	 * 根据国家id获取城市列表
	 * 
	 * @param countryId
	 * @param isEnable 是否启用 -1不限制，1启用，0不启用
	 * @param hot 是否热门 -1 不限制，1热门，0非热门
	 * @return
	 */
	List<WebCity> selectCityListByCondition(
			@Param("countryId") long countryId,
			@Param("isEnable") int isEnable,
			@Param("hot") int hot);

	/**
	 * 签证提单页城市查询 包含商品城市和poi城市
	 * 
	 * @return
	 */
	List<OrderDestCity> selectOrderDestCity();
}
