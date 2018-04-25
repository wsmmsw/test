package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.domain.crm.meta.WebCountry;
import com.baicheng.fork.core.generic.GenericService;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月28日 下午3:05:35
 */

public interface WebCountryService extends GenericService<WebCountry, Integer> {

	/**
	 * 获取国家名称列表
	 */
	List<WebCountry> selectCountryNameList();

	List<WebCountry> selectCountryByContinentId(Long id);

}
