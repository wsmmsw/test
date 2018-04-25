package com.baicheng.fork.web.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CRMToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 7490060135267215953L;
	private String uType;

	public CRMToken(String username, char[] password, boolean rememberMe, String host, String uType) {
		super(username, password, rememberMe, host);
		this.uType = uType;
	}

	public String getuType() {
		return uType;
	}

	public void setuType(String uType) {
		this.uType = uType;
	}

}
