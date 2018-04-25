package com.baicheng.fork.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.domain.crm.meta.WebCountry;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.core.generic.GenericServiceImpl;
import com.baicheng.fork.web.dao.WebCountryMapper;
import com.baicheng.fork.web.service.WebCountryService;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月28日 下午3:06:51
 */
@Service
public class WebCountryServiceImpl extends GenericServiceImpl<WebCountry, Integer> implements WebCountryService {

	@Resource
	private WebCountryMapper webCountryMapper;

	@Override
	public List<WebCountry> selectCountryNameList() {
		return webCountryMapper.selectCountryNameList();
	}

	@Override
	public GenericDao<WebCountry, Integer> getDao() {
		return webCountryMapper;
	}

	@Override
	public List<WebCountry> selectCountryByContinentId(Long id) {
		return webCountryMapper.selectCountryByContinentId(id);
	}

}
