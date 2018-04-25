package com.baicheng.fork.web.joint.linktour.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baicheng.fork.domain.joint.linktour.LTBaseProduct;
import com.baicheng.fork.domain.joint.linktour.LTSku;
import com.baicheng.fork.domain.joint.linktour.LTStock;
import com.baicheng.fork.domain.joint.linktour.LTTicketProduct;
import com.baicheng.fork.web.joint.linktour.LinkTourCategoryConstants;
import com.baicheng.fork.web.joint.linktour.responsedto.ResultResponseDTO;
import com.baicheng.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import com.nicetrip.freetrip.http.NTRequest;
import com.nicetrip.freetrip.http.NTRequestGet;
import com.nicetrip.freetrip.http.NTResponse;

/**
 * 调用接口获取领拓产品信息
 * 
 * @author wsm 2018年4月18日下午6:22:24
 */
public class LTTicketProductHandler extends LTBaseProductHandler {

	private static final Logger LOGGER = Logger.getLogger(LTTicketProductHandler.class.getName());

	/**
	 * 获取门票商品信息
	 * 
	 * @return
	 */
	public ResultResponseDTO getProductListByCategory() {

		ResultResponseDTO resultResponseDTO = new ResultResponseDTO();
		// 成功门票类产品
		List<LTTicketProduct> successList = null;
		// 失败的门票了产品
		List<LTTicketProduct> failureList = new ArrayList<>();
		try {
			// 1:获取产品列表
			List<LTBaseProduct> ltProducts = getBaseProductList(LinkTourCategoryConstants.TICKET_TYPE);
			if (ltProducts == null || ltProducts.size() <= 0) {
				return null;
			}
			int size = ltProducts.size();
			successList = new ArrayList<>(size);
			// 2:获取每一个产品的详情
			for (LTBaseProduct baseProduct : ltProducts) {
				try {
					if (baseProduct != null) {
						LTTicketProduct productDetail = new LTTicketProduct();
						long pId = baseProduct.getPid();
						productDetail.setPid(pId);

						// 产品详情处理
						Map<String, String> detailMap = getProductDetail(pId);
						if (detailMap.get(RESULT_CODE_KEY).equals(FAILURE_CODE_VALUE)) {
							failureList.add(productDetail);
							continue;
						}
						productDetail = JsonUtil.json2bean(detailMap.get(RESULT_VALUE_KEY), LTTicketProduct.class);

						// 产品sku处理
						Map<String, String> skuMap = getSkuList(pId);
						if (skuMap.get(RESULT_CODE_KEY).equals(FAILURE_CODE_VALUE)) {
							failureList.add(productDetail);
							continue;
						}
						List<LTSku> skuList = JsonUtil.json2bean(skuMap.get(RESULT_VALUE_KEY),
								new TypeToken<ArrayList<LTSku>>() {
								}.getType());
						productDetail.setSkus(skuList);

						// 产品库存处理
						Map<String, String> stockMap = getStockList(pId);
						if (stockMap.get(RESULT_CODE_KEY).equals(FAILURE_CODE_VALUE)) {
							failureList.add(productDetail);
							continue;
						}
						List<LTStock> ltStockList = JsonUtil.json2bean(stockMap.get(RESULT_VALUE_KEY),
								new TypeToken<ArrayList<LTStock>>() {
								}.getType());
						productDetail.setStocks(ltStockList);
						successList.add(productDetail);
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}

			}

			// 处理最终返回结果
			resultResponseDTO.setSuccessList(successList);
			resultResponseDTO.setFailureList(failureList);
		} catch (Exception e) {
			LOGGER.error("########## 获取领拓产品数据异常" + e.getMessage(), e);
			e.printStackTrace();
		}
		return resultResponseDTO;

	}

	public static void main(String[] args) {
		String loginUrl = "http://api.demo.linktour.com/markets/3635/sku";
		NTRequest request = new NTRequestGet(loginUrl);
		request.addHeader("X-Token", "dbe87d95099bb3d83c5c9b46cdfce129");
		request.addHeader(VERSION_KEY, VERSION_VALUE);
		NTResponse response = request.execute();

		System.out.println(response.getHttpCode());
		// 3:处理返回结果
		String loginResult = response.getEntity();
		System.out.println(loginResult);

	}

}
