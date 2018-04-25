package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.fork.domain.joint.linktour.LTTicketProduct;

/**
 * 领拓产品处理
 * @author wsm
 * 2018年4月24日下午1:40:41
 */
public interface LTProductService {
	
	/**
	 * 获取领拓门票产品列表
	 */
	public List<LTTicketProduct> getTicketProductList();
	
	/**
	 * 产品增量更新
	 * @param param
	 */
	public void incrementalUpdate(String param);
	

}
