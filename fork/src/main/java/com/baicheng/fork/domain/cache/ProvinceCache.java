package com.baicheng.fork.domain.cache;

import java.util.List;

import com.baicheng.domain.crm.meta.ContactProvince;
import com.baicheng.fork.core.util.BeanUtils;
import com.baicheng.fork.web.service.WebProvinceService;

/**
 * 
 * @author wsm 缓存省市数据
 */
public class ProvinceCache {

	private List<ContactProvince> provinces;

	private static class LazyHolder {
		private static final ProvinceCache INSTANCE = new ProvinceCache();
	}

	private ProvinceCache() {
		WebProvinceService service = BeanUtils.getInstance().getService(WebProvinceService.class);
		if (service != null) {
			provinces = service.getAllProvinceAndCityList();
		}
	}

	public static final ProvinceCache getInstance() {
		return LazyHolder.INSTANCE;
	}

	public List<ContactProvince> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<ContactProvince> provinces) {
		this.provinces = provinces;
	}

}
