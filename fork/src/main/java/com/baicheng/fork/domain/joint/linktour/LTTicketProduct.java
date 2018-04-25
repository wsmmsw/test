package com.baicheng.fork.domain.joint.linktour;

/**
 * 领拓的门票产品信息结构
 * 
 * @author wsm 2018年4月20日下午4:07:38
 */
public class LTTicketProduct extends LTBaseProduct {

	private LTTicketContent contents;

	public LTTicketContent getContents() {
		return contents;
	}

	public void setContents(LTTicketContent contents) {
		this.contents = contents;
	}

}
