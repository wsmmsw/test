package com.baicheng.fork.domain.joint.linktour;

/**
 * 领拓的门票产品信息结构
 * 
 * @author wsm 2018年4月20日下午4:07:38
 */
public class LTTicketProduct extends LTBaseProduct {

	
	private String type; // 产品的二级类别 PHYSICAL实体票，ETICKET电子票 VOUCHER换票证
	private LTTicketContent contents; // 详情内容

	public LTTicketContent getContents() {
		return contents;
	}

	public void setContents(LTTicketContent contents) {
		this.contents = contents;
	}

}
