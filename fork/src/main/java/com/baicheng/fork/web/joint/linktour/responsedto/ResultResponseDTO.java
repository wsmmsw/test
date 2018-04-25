package com.baicheng.fork.web.joint.linktour.responsedto;

import java.util.List;

import com.baicheng.fork.domain.joint.linktour.LTTicketProduct;

/**
 * 调用api返回结果的dto
 * 
 * @author wsm 2018年4月25日上午10:06:16
 */
public class ResultResponseDTO {
	/** 成功的门票类产品 */
	private List<LTTicketProduct> successList;
	/** 失败的门票类产品 */
	private List<LTTicketProduct> failureList;

	public List<LTTicketProduct> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<LTTicketProduct> successList) {
		this.successList = successList;
	}

	public List<LTTicketProduct> getFailureList() {
		return failureList;
	}

	public void setFailureList(List<LTTicketProduct> failureList) {
		this.failureList = failureList;
	}

}
