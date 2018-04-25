package com.baicheng.fork.web.joint.linktour.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baicheng.fork.domain.joint.linktour.LTBaseProduct;
import com.baicheng.fork.domain.joint.linktour.LTUser;
import com.baicheng.fork.web.joint.linktour.LinkTourCategoryConstants;
import com.baicheng.fork.web.joint.linktour.LinkTourConstants;
import com.baicheng.fork.web.joint.linktour.responsedto.LTProductListResponseDTO;
import com.baicheng.fork.web.joint.linktour.responsedto.LTStockResponseDTO;
import com.baicheng.utils.JsonUtil;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestGet;
import com.nicetrip.freetrip.http.NTResponse;

/**
 * 
 * @author wsm 2018年4月23日下午5:08:12
 */
public class LTBaseProductHandler {
	private static final Logger LOGGER = Logger.getLogger(LTBaseProductHandler.class.getName());

	// http param key
	public static final String TOKEN_KEY = "X-Token";
	public static final String PAGE_KEY = "page";
	public static final String LIMIT_KEY = "limit";
	public static final String CATEGORY_KEY = "category";
	public static final String VERSION_KEY = "X-Version";

	// http param value
	public static final int PAGE_VALUE = 1;
	public static final int LIMIT_VALUE = 50;
	public static final String CATEGORY_VALUE = LinkTourCategoryConstants.TICKET_TYPE;
	public static final String VERSION_VALUE = "20170901";

	// result map key
	public static final String RESULT_CODE_KEY = "code";
	public static final String RESULT_VALUE_KEY = "resultValue";

	// result map value
	public static final String SUCCESS_CODE_VALUE = "100000";
	public static final String FAILURE_CODE_VALUE = "-1";

	/**
	 * 调用api分页获取产品列表
	 * 
	 * @return
	 */
	protected static List<LTBaseProduct> getBaseProductList(String category) {

		try {
			/**
			 * 调用逻辑 1：第一次调用获取总条数 2：根据总条数分页 3：分页获取数据
			 */
			List<LTBaseProduct> resultProductList = new ArrayList<>();
			Map<String, Integer> countMap = new HashMap<>(1);
			convertFristPageProduct(category, resultProductList, countMap);
			convertNextPageProduct(category, resultProductList, countMap);
			return resultProductList;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取产品详情 返回map 结构 map key是code 100000标识接口返回成功 -1标识接口返回失败 map
	 * key是resultValue 返回的结构值，返回失败值为null
	 * 
	 * @return
	 */
	protected Map<String, String> getProductDetail(long pId) {
		Map<String, String> resultMap = getDefalutResultMap();
		try {
			LTTokenHandler tokenHandler = new LTTokenHandler();
			LTUser ltUser = tokenHandler.getLTUser();
			if (ltUser == null || StringUtils.isEmpty(ltUser.getToken())) {
				return resultMap;
			}

			String challengeUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.PRODUCT_REQUEST_TYPE, pId);
			NTRequest request = new NTRequestGet(challengeUrl);
			request.addHeader(LTBaseProductHandler.TOKEN_KEY, ltUser.getToken());
			request.addHeader(VERSION_KEY, VERSION_VALUE);

			NTResponse response = request.execute();
			if (response == null || response.getHttpCode() != LinkTourConstants.SUCCESS_HTTP_CODE) {
				LOGGER.error("###### 获取产品id :" + pId + " 详情失败");
				return resultMap;
			}

			// 接口返回成功
			resultMap.put(RESULT_CODE_KEY, SUCCESS_CODE_VALUE);
			resultMap.put(RESULT_VALUE_KEY, response.getEntity());
		} catch (Exception e) {
			LOGGER.error("######## 获取产品详情异常，产品id ：" + pId + "异常信息 : " + e.getMessage(), e);
			e.printStackTrace();
		}

		return resultMap;

	}

	/**
	 * 根据产品id获取sku信息 返回map 结构 map key是code 100000标识接口返回成功 -1标识接口返回失败 map
	 * key是resultValue 返回的结构值，返回失败值为null
	 * 
	 * @return
	 */
	protected Map<String, String> getSkuList(long pId) {
		Map<String, String> resultMap = getDefalutResultMap();
		try {
			LTTokenHandler tokenHandler = new LTTokenHandler();
			LTUser ltUser = tokenHandler.getLTUser();
			if (ltUser == null || StringUtils.isEmpty(ltUser.getToken())) {
				return resultMap;
			}

			String challengeUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.PRODUCT_SKU_REQUEST_TYPE, pId);
			NTRequest request = new NTRequestGet(challengeUrl);
			request.addHeader(TOKEN_KEY, ltUser.getToken());
			request.addHeader(VERSION_KEY, VERSION_VALUE);

			NTResponse response = request.execute();

			if (response == null || response.getHttpCode() != LinkTourConstants.SUCCESS_HTTP_CODE) {
				LOGGER.error("###### 获取产品id :" + pId + " sku失败");
				return resultMap;
			}

			// 接口返回成功
			resultMap.put(RESULT_CODE_KEY, SUCCESS_CODE_VALUE);
			resultMap.put(RESULT_VALUE_KEY, response.getEntity());
			// ltSkuList = JsonUtil.json2bean(result, new
			// TypeToken<ArrayList<LTSku>>() {
			// }.getType());
		} catch (Exception e) {
			LOGGER.error("######## 获取sku异常，产品id ：" + pId + "异常信息 ： " + e.getMessage(), e);
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 根据产品id获取价格和库存信息 返回map 结构 map key是code 100000标识接口返回成功 -1标识接口返回失败 map
	 * key是resultValue 返回的结构值，返回失败值为null
	 * 
	 * @return
	 */
	protected Map<String, String> getStockList(long pId) {
		Map<String, String> resultMap = getDefalutResultMap();
		try {
			LTTokenHandler tokenHandler = new LTTokenHandler();
			LTUser ltUser = tokenHandler.getLTUser();
			if (ltUser == null || StringUtils.isEmpty(ltUser.getToken())) {
				return resultMap;
			}

			String challengeUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.PRODUCT_STOCK_PRICE_REQUEST_TYPE, pId);
			NTRequest request = new NTRequestGet(challengeUrl);
			request.addHeader(TOKEN_KEY, ltUser.getToken());
			request.addHeader(VERSION_KEY, VERSION_VALUE);

			NTResponse response = request.execute();

			if (response == null || response.getHttpCode() != LinkTourConstants.SUCCESS_HTTP_CODE) {
				LOGGER.error("###### 获取产品id :" + pId + " 库存失败");
				return resultMap;
			}

			// 接口返回成功
			String result = response.getEntity();
			LTStockResponseDTO responseDTO = JsonUtil.json2bean(result, LTStockResponseDTO.class);
			resultMap.put(RESULT_CODE_KEY, SUCCESS_CODE_VALUE);
			resultMap.put(RESULT_VALUE_KEY, JsonUtil.bean2json(responseDTO.getStocks()));
			// ltStockList = responseDTO.getStocks();

		} catch (Exception e) {
			LOGGER.error("######## 获取库存数据异常，产品id ：" + pId + "异常信息：" + e.getMessage(), e);
			e.printStackTrace();
		}

		return resultMap;
	}

	/**
	 * 获取总数量和第一页的产品信息
	 * 
	 * @param category
	 * @param resultProductList
	 * @return
	 */
	private static void convertFristPageProduct(String category, List<LTBaseProduct> resultProductList,
			Map<String, Integer> countMap) {
		try {
			LTTokenHandler tokenHandler = new LTTokenHandler();
			LTUser ltUser = tokenHandler.getLTUser();
			if (ltUser == null || StringUtils.isEmpty(ltUser.getToken())) {
				return;
			}
			String productListUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.PRODUCT_LIST_REQUEST_TYPE, -1);
			NTRequest request = new NTRequestGet(productListUrl);

			// 1：第一次调用获取总条数和第一页产品数据
			request.addHeader(TOKEN_KEY, ltUser.getToken());
			request.addHeader(VERSION_KEY, VERSION_VALUE);
			request.addUrlParam(PAGE_KEY, PAGE_VALUE);
			request.addUrlParam(LIMIT_KEY, LIMIT_VALUE);
			request.addUrlParam(CATEGORY_KEY, category);

			int retry = 0;
			while (retry < 3) {
				// 接口返回数据
				NTResponse response = request.execute();
				if (response != null && response.getHttpCode() == LinkTourConstants.SUCCESS_HTTP_CODE) {
					// 解析返回参数
					String result = response.getEntity();
					LTProductListResponseDTO responseDTO = JsonUtil.json2bean(result, LTProductListResponseDTO.class);
					if (responseDTO != null) {
						// 总条数
						int count = responseDTO.getCount();
						List<LTBaseProduct> tempProductList = responseDTO.getProducts();
						if (count > 0 && tempProductList != null && tempProductList.size() > 0) {
							countMap.put("count", count);
							resultProductList.addAll(tempProductList);
						}
					}
					break;
				}
				retry++;
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	/**
	 * 获取下一页产品数据
	 * 
	 * @param category
	 * @param resultProductList
	 * @param countMap
	 */
	private static void convertNextPageProduct(String category, List<LTBaseProduct> resultProductList,
			Map<String, Integer> countMap) {
		try {
			Integer count = countMap.get("count");
			if (count == null || count <= 0) {
				return;
			}

			// 1：根据总条数分页 调用
			int page = (int) Math.ceil(count * 1.0 / LIMIT_VALUE);

			// 2：分页获取数据
			LTTokenHandler tokenHandler = new LTTokenHandler();
			String productListUrl = LTUrlHandler.getBaseUrl(LinkTourConstants.PRODUCT_LIST_REQUEST_TYPE, -1);
			NTRequest request = new NTRequestGet(productListUrl);
			for (int i = 2; i <= page; i++) {
				LTUser ltUser = tokenHandler.getLTUser();
				if (ltUser == null || StringUtils.isEmpty(ltUser.getToken())) {
					break;
				}
				request.addHeader(TOKEN_KEY, ltUser.getToken());
				request.addHeader(VERSION_KEY, VERSION_VALUE);
				request.addUrlParam(PAGE_KEY, i);
				request.addUrlParam(LIMIT_KEY, LIMIT_VALUE);
				request.addUrlParam(CATEGORY_KEY, category);

				NTResponse response = null;
				// 如果失败重试
				int retry = 0;
				while (retry < 3) {
					response = request.execute();
					if (response != null && response.getHttpCode() == LinkTourConstants.SUCCESS_HTTP_CODE) {
						String result = response.getEntity();
						LTProductListResponseDTO responseDTO = JsonUtil.json2bean(result,
								LTProductListResponseDTO.class);
						if (responseDTO != null && responseDTO.getProducts() != null
								&& responseDTO.getProducts().size() > 0) {
							resultProductList.addAll(responseDTO.getProducts());
						}
						break;
					}
					retry++;
					Thread.sleep(1000);
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}

	}

	/**
	 * 调用API接口返回结果对应的map
	 * 
	 * @return
	 */
	private Map<String, String> getDefalutResultMap() {
		Map<String, String> map = new HashMap<>(2);
		map.put(RESULT_CODE_KEY, FAILURE_CODE_VALUE);
		return map;
	}

	public static void main(String[] args) {
		getBaseProductList(LinkTourCategoryConstants.TICKET_TYPE);
	}

}
