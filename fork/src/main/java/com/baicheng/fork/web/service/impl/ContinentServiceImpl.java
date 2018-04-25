package com.baicheng.fork.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.domain.crm.meta.WebContinent;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.core.generic.GenericServiceImpl;
import com.baicheng.fork.web.dao.ContinentMapper;
import com.baicheng.fork.web.service.ContinentService;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月12日 下午5:25:23
 */
@Service
public class ContinentServiceImpl extends GenericServiceImpl<WebContinent, Integer> implements ContinentService {

	@Resource
	private ContinentMapper continentMapper;

	@Override
	public GenericDao<WebContinent, Integer> getDao() {
		return continentMapper;
	}

	@Override
	public List<WebContinent> selectAll() {
		return continentMapper.selectAll();
	}

}
