package com.baicheng.fork.core.util.sms.sender.info;

public abstract class BaseSMSInfo {

	public static final int TYPE_SINGLE = 0;
	public static final int TYPE_MASS = 1;

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
