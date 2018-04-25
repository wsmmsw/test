package com.baicheng.fork.web.joint.linktour.responsedto;

import com.baicheng.fork.domain.joint.linktour.LTStock;
import java.util.List;

import com.baicheng.domain.Domain;

/**
 * 库存和价格返回dto
 * 
 * @author wsm 2018年4月23日上午10:51:15
 */
public class LTStockResponseDTO extends Domain {
	private List<LTStock> stocks;
	private String currency;  // 报价对应的货币

	public List<LTStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<LTStock> stocks) {
		this.stocks = stocks;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
