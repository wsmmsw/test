package com.baicheng.fork.web.joint.linktour.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baicheng.fork.domain.joint.linktour.LTBaseProduct;
import com.baicheng.fork.domain.joint.linktour.LTSku;
import com.baicheng.fork.domain.joint.linktour.LTStock;
import com.baicheng.fork.domain.joint.linktour.LTTicketProduct;
import com.baicheng.fork.web.joint.linktour.LinkTourCategoryConstants;
import com.baicheng.utils.JsonUtil;

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
	public List<LTTicketProduct> getProductListByCategory() {
		List<LTTicketProduct> resultLTProductList = null;
		try {
			// 1:获取产品列表
			List<LTBaseProduct> ltProducts = getBaseProductList(LinkTourCategoryConstants.TICKET_TYPE);
			if (ltProducts == null || ltProducts.size() <= 0) {
				return null;
			}
			int size = ltProducts.size();
			resultLTProductList = new ArrayList<>(size);
			LTTicketProduct productDetail = null;
			for (LTBaseProduct baseProduct : ltProducts) {
				try {
					if (baseProduct != null) {
						long pId = baseProduct.getPid();
						String detailStr = getProductDetail(pId);
						if (StringUtils.isEmpty(detailStr)) {
							continue;
						}

						productDetail = JsonUtil.json2bean(detailStr, LTTicketProduct.class);
						List<LTSku> skuList = getSkuList(pId);
						List<LTStock> stockList = getStockList(pId);
						productDetail.setSkus(skuList);
						productDetail.setStocks(stockList);
						resultLTProductList.add(productDetail);
					}
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}

			}
		} catch (Exception e) {
			LOGGER.error("########## 获取领拓产品数据异常"+e.getMessage(), e);
			e.printStackTrace();
		}
		return resultLTProductList;

	}

}
