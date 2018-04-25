package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.domain.crm.meta.ContactCity;
import com.baicheng.domain.crm.meta.ContactProvince;
import com.baicheng.domain.crm.meta.WebProvince;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月12日 上午9:54:38
 */
public interface WebProvinceMapper extends GenericDao<WebProvince, Long> {

	List<WebProvince> selectAllProvince();

	// 获取省信息
	List<ContactProvince> getProvinceList();

	// 根据省id获取城市信息
	List<ContactCity> getCityListByProvinceId(@Param("provinceId") long provinceId);
}
