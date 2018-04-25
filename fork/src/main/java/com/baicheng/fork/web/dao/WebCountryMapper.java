package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.domain.crm.meta.WebCountry;
import com.baicheng.fork.core.generic.GenericDao;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月28日 下午3:03:30
 */
public interface WebCountryMapper extends GenericDao<WebCountry, Integer> {

	/**
	 * 获取国家名称列表
	 */
	List<WebCountry> selectCountryNameList();

	List<WebCountry> selectCountryByContinentId(Long id);

	/**
	 * 根据国家id获取国家列表
	 * 
	 * @param countryIds
	 * @return
	 */
	List<WebCountry> selectCountryByCondtion(@Param("countryIds") long[] countryIds);
}
