package com.baicheng.fork.web.dao;

import java.util.List;

import com.baicheng.domain.crm.meta.WebContinent;
import com.baicheng.fork.core.generic.GenericDao;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月12日 下午5:22:39
 */
public interface ContinentMapper extends GenericDao<WebContinent, Integer> {

	List<WebContinent> selectAll();

}
