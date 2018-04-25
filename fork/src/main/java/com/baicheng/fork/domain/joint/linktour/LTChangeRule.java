package com.baicheng.fork.domain.joint.linktour;

/**
 * 退改规则
 * 
 * @author wsm 2018年4月23日下午3:12:59
 */
public class LTChangeRule {

	private String range; // 一个退款的时间区间
	private String fee; // 商家收取订单总金额的百分比作为取消费用
	private String memo; // 说明

	public String getange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
