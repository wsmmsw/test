package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.domain.crm.meta.WebPOI;
import com.baicheng.fork.core.generic.GenericDao;

/**
 * 
 * @author Seamus
 * 
 */
public interface WebPoiMapper extends GenericDao<WebPOI, Integer> {

	List<WebPOI> getPois(@Param("mainType") int mainType);

	List<WebPOI> getPoisWithCity(@Param("mainType") int mainType);

	List<WebPOI> getPoisWithCountry(@Param("mainType") int mainType);

}
