package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.domain.crm.meta.WebContinent;
import com.baicheng.fork.core.generic.GenericService;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月12日 下午5:24:29
 */
public interface ContinentService extends GenericService<WebContinent, Integer> {

	List<WebContinent> selectAll();

}
