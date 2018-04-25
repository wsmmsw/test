package com.baicheng.fork.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baicheng.domain.crm.meta.WebProvince;
import com.baicheng.fork.core.util.JSONPUtils;
import com.baicheng.fork.web.constants.CacheConstants;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.dto.response.ProvinceResponseDTO;
import com.baicheng.fork.web.service.WebProvinceService;
import com.nicetrip.redis.client.CacheClientShard;
import com.nicetrip.redis.client.KeyConstants;

import net.sf.json.JSONArray;

/**
 * @author mabaoyu
 * @date：2017年4月12日 上午9:57:59
 */

@Controller
@RequestMapping("/province")
public class WebProvinceController {

	@Resource
	private CacheClientShard cacheClientShard;
	@Resource
	private WebProvinceService webProvinceService;

	/**
	 * 查询省列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listProvince", method = { RequestMethod.GET, RequestMethod.POST })
	public void listProvince(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProvinceResponseDTO reply = new ProvinceResponseDTO(BaseResponseDTO.SUCCESS, BaseResponseDTO.DEFAULT_MESSAGE);
		List<WebProvince> data = null;
		String cacheList = cacheClientShard.get(CacheConstants.FORK_PROVINCE_KEY);
		if (StringUtils.isNotEmpty(cacheList)) {
			JSONArray jsonArray = JSONArray.fromObject(cacheList);
			data = (List<WebProvince>) JSONArray.toCollection(jsonArray, WebProvince.class);
		} else {
			data = webProvinceService.selectAllProvince();
			JSONArray cache = JSONArray.fromObject(data);
			cacheClientShard.set(CacheConstants.FORK_PROVINCE_KEY, cache.toString(), KeyConstants.SECONDS_1_DAY);
		}
		reply.setData(data);
		JSONPUtils.jsonp(reply);
	}

	@RequestMapping(value = "/clearProvinceCache", method = { RequestMethod.GET, RequestMethod.POST })
	public void clearProvinceCache(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		cacheClientShard.del(CacheConstants.FORK_PROVINCE_KEY);
		reponse.getWriter().print("success");
	}

}
