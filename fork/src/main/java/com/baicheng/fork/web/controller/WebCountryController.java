package com.baicheng.fork.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baicheng.domain.crm.meta.WebContinent;
import com.baicheng.domain.crm.meta.WebCountry;
import com.baicheng.fork.core.util.JSONPUtils;
import com.baicheng.fork.web.constants.CacheConstants;
import com.baicheng.fork.web.dto.response.BaseResponseDTO;
import com.baicheng.fork.web.dto.response.CountryNameListResponseDTO;
import com.baicheng.fork.web.dto.response.CountryResponseData;
import com.baicheng.fork.web.service.ContinentService;
import com.baicheng.fork.web.service.WebCountryService;
import com.nicetrip.redis.client.CacheClientShard;
import com.nicetrip.redis.client.KeyConstants;

import net.sf.json.JSONArray;

/**
 * @author mabaoyu
 * 
 * @date：2017年3月28日 下午3:08:38
 */
@Controller
@RequestMapping("/country")
public class WebCountryController {

	private static final Logger LOGGER = Logger.getLogger(WebCountryController.class);

	@Resource
	private WebCountryService webCountryService;
	@Resource
	private ContinentService continentService;
	@Resource
	private CacheClientShard cacheClientShard;

	/**
	 * 查询国家列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listCountry", method = { RequestMethod.GET, RequestMethod.POST })
	public void listCountry(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CountryNameListResponseDTO reply = new CountryNameListResponseDTO(BaseResponseDTO.SUCCESS,
				BaseResponseDTO.DEFAULT_MESSAGE);
		String cache = cacheClientShard.get(CacheConstants.FORK_COUNTRY_LIST);
		List<CountryResponseData> data = null;
		if (StringUtils.isNotBlank(cache)) {
			JSONArray jsonArray = JSONArray.fromObject(cache);
			data = (List<CountryResponseData>) JSONArray.toCollection(jsonArray, CountryResponseData.class);
			for (CountryResponseData continent : data) {
				JSONArray ja = JSONArray.fromObject(continent.getCountryList());
				List<WebCountry> countryList = (List<WebCountry>) JSONArray.toCollection(ja, WebCountry.class);
				continent.setCountryList(countryList);
			}
		} else {
			data = new ArrayList<>();
			try {
				List<WebContinent> continentList = continentService.selectAll();
				for (WebContinent c : continentList) {
					List<WebCountry> list = webCountryService.selectCountryByContinentId(c.getContinentId());
					if (list != null && list.size() > 0) {
						CountryResponseData countryList = new CountryResponseData();
						countryList.setName(c.getNameCn());
						countryList.setCountryList(list);
						data.add(countryList);
					}
				}
				if (data != null && data.size() > 0) {
					JSONArray jsonArray = JSONArray.fromObject(data);
					cacheClientShard.set(CacheConstants.FORK_COUNTRY_LIST, jsonArray.toString(),
							KeyConstants.SECONDS_1_DAY);
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				reply.setCode(BaseResponseDTO.FAILURE);
				reply.setMessage("获取国家列表失败");
			}
		}
		reply.setData(data);
		JSONPUtils.jsonp(reply);
	}

	@RequestMapping(value = "/clearCache", method = { RequestMethod.GET, RequestMethod.POST })
	public void clearCache(HttpServletRequest request, HttpServletResponse response) throws Exception {
		cacheClientShard.del(CacheConstants.FORK_COUNTRY_LIST);
		response.getWriter().print("s");
	}

}
