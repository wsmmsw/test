package com.baicheng.fork.domain.joint.linktour;

import com.baicheng.domain.Domain;

/**
 * 领拓登录之后返回的用户信息
 * 
 * @author wsm 2018年4月20日上午10:21:32
 */
public class LTUser extends Domain {
	private Long uid; // 用户id
	private String username; // 用户名
	private String realname; // 真实姓名
	private String role; // 当前用户的角色
	private String mobile; // 手机号
	private String email; // 邮件地址
	private String token;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;

	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
