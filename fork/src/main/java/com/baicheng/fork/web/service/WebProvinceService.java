package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.domain.crm.meta.ContactProvince;
import com.baicheng.domain.crm.meta.WebProvince;
import com.baicheng.fork.core.generic.GenericService;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月12日 上午9:47:59
 */
public interface WebProvinceService extends GenericService<WebProvince, Long>, CRMService {

	List<WebProvince> selectAllProvince();

	// 查询全部的省和市数据
	List<ContactProvince> getAllProvinceAndCityList();

}
