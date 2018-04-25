package com.baicheng.fork.web.joint.linktour.responsedto;

import java.util.List;

import com.baicheng.fork.domain.joint.linktour.LTBaseProduct;
import com.baicheng.domain.Domain;

/**
 * 领拓商品列表返回的dto
 * 
 * @author wsm 2018年4月23日上午9:44:42
 */
public class LTProductListResponseDTO extends Domain {
	private List<LTBaseProduct> products;
	private Integer count;// 总产品数量
	private String currency; // 当前报价的币种

	public List<LTBaseProduct> getProducts() {
		return products;
	}

	public void setProducts(List<LTBaseProduct> products) {
		this.products = products;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
