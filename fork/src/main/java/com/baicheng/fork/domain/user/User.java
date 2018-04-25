package com.baicheng.fork.domain.user;

import java.util.Date;

import com.baicheng.domain.Domain;

/**
 * 平台用户模型
 */
public class User extends Domain {

	private Long id;
	private String username;
	private String pwd;
	private String truename;
	private String state;
	private Date createTime;
	private Date ldate;
	private Long cman;
	private Long lman;
	private String portrait;// avarta
	private String roleNames;
	private String[] roleIds;

	public User() {
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public User(String username, String password) {
		this.username = username;
		this.pwd = password;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public Long getId() {
		return id;
	}

	public Date getLdate() {
		return ldate;
	}

	public void setLdate(Date ldate) {
		this.ldate = ldate;
	}

	public Long getLman() {
		return lman;
	}

	public void setLman(Long lman) {
		this.lman = lman;
	}

	public Long getCman() {
		return cman;
	}

	public void setCman(Long cman) {
		this.cman = cman;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwd=" + pwd + ", state=" + state
				+ ", createTime=" + createTime + "]";
	}

}
