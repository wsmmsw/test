package com.baicheng.fork.domain.user;

import java.util.Date;

import com.baicheng.domain.Domain;

/**
 * 角色模型
 */
public class Role extends Domain {

	private Long id;

	private String roleName;

	private String roleSign;

	private String description;

	private Date cdate;
	private Date ldate;

	private Long cman;
	private Long lman;

	private Long bid;

	private int type;

	private String permissions;

	private String[] permissionIds;

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getRoleSign() {
		return roleSign;
	}

	public void setRoleSign(String roleSign) {
		this.roleSign = roleSign == null ? null : roleSign.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleSign=" + roleSign + ", description=" + description
				+ "]";
	}

}
