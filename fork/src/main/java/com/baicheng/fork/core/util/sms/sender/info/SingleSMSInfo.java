package com.baicheng.fork.core.util.sms.sender.info;

public class SingleSMSInfo extends BaseSMSInfo {

	// 节点ID
	private Integer nodeID;
	// 具体短息编号 ID
	private Integer branchID;
	// 手机号码
	private String mobile;
	private String[] params;

	public Integer getNodeID() {
		return nodeID;
	}

	public void setNodeID(Integer nodeID) {
		this.nodeID = nodeID;
	}

	public Integer getBranchID() {
		return branchID;
	}

	public void setBranchID(Integer branchID) {
		this.branchID = branchID;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}
