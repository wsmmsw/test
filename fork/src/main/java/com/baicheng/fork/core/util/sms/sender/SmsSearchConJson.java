package com.baicheng.fork.core.util.sms.sender;

/**
 * 调用.net接口SmsSearchConJson参数类
 */
public class SmsSearchConJson {

	private Integer NodeID;
	private Integer BranchId;

	public Integer getNodeID() {
		return this.NodeID;
	}

	public void setNodeID(Integer nodeID) {
		this.NodeID = nodeID;
	}

	public Integer getBranchId() {
		return this.BranchId;
	}

	public void setBranchId(Integer branchId) {
		this.BranchId = branchId;
	}

}
