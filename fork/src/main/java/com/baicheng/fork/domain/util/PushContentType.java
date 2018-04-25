package com.baicheng.fork.domain.util;

import com.baicheng.domain.Domain;

/**
 * 推送条目类型的领域模型
 *
 * @author SongPengpeng
 * @date 2017/12/25
 */
public class PushContentType extends Domain {
	/** ID */
	private Long id;
	/** 类型的父级ID */
	private Integer parentId;
	/** 类型 */
	private Integer type;
	/** 类型名称 */
	private String typeName;
	/** 类型对应的推送消息的类型 */
	private Integer pushMsgType;
	/** 类型的序号 */
	private Integer seqNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName == null ? null : typeName.trim();
	}

	public Integer getPushMsgType() {
		return pushMsgType;
	}

	public void setPushMsgType(Integer pushMsgType) {
		this.pushMsgType = pushMsgType;
	}

	public Integer getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(Integer seqNum) {
		this.seqNum = seqNum;
	}
}