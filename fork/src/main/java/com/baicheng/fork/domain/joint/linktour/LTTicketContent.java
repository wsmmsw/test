package com.baicheng.fork.domain.joint.linktour;

/**
 * 领拓门票详情内容结构
 * @author wsm
 * 2018年4月23日下午6:28:11
 */
public class LTTicketContent extends LTBaseContent{
	
	private String itinerary; // 行程安排

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
	
}
