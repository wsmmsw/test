package com.baicheng.fork.domain.joint.linktour;

import com.baicheng.domain.Domain;

/**
 * 领拓 库存价格结构
 * 
 * @author wsm 2018年4月20日下午5:45:05
 */
public class LTStock extends Domain {

	// 当前库存的SKU编码
	private String sku;
	// 当前SKU对应的库存日期
	private String date;
	// 当前SKU对应的售价
	private Double price;
	// 值是 " ??? "，则表示，产品不提供库存，需要现询，（也就是说卖方没有库存，订单需要二次确认）
	private String stock;
	private String currency;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
