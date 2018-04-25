package com.baicheng.fork.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baicheng.domain.crm.meta.ContactCity;
import com.baicheng.domain.crm.meta.ContactProvince;
import com.baicheng.domain.crm.meta.WebProvince;
import com.baicheng.fork.core.generic.GenericDao;
import com.baicheng.fork.core.generic.GenericServiceImpl;
import com.baicheng.fork.web.dao.WebProvinceMapper;
import com.baicheng.fork.web.service.WebProvinceService;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月12日 上午9:53:33
 */
@Service
public class WebProvinceServiceImpl extends GenericServiceImpl<WebProvince, Long> implements WebProvinceService {

	@Resource
	private WebProvinceMapper webProvinceMapper;

	@Override
	public List<WebProvince> selectAllProvince() {
		return webProvinceMapper.selectAllProvince();
	}

	@Override
	public GenericDao<WebProvince, Long> getDao() {
		return webProvinceMapper;
	}

	@Override
	public List<ContactProvince> getAllProvinceAndCityList() {
		// 1:获取省份
		List<ContactProvince> provinceList = null;
		provinceList = webProvinceMapper.getProvinceList();
		if (provinceList != null && provinceList.size() > 0) {
			// 2：根据省id获取市
			for (ContactProvince province : provinceList) {
				if (province != null) {
					province.setNameCn(province.getNameCn().replace("省", ""));
					province.setNameCn(province.getNameCn().replace("自治区", ""));
					List<ContactCity> cityList = webProvinceMapper.getCityListByProvinceId(province.getId());
					if (cityList != null && cityList.size() > 0) {
						for (ContactCity city : cityList) {
							if (city != null) {
								city.setNameCn(city.getNameCn().replace("市", ""));
							}
						}
					}
					province.setCities(cityList);
				}
			}
		}
		return provinceList;
	}

}
