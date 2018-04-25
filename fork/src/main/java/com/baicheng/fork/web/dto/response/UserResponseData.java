package com.baicheng.fork.web.dto.response;

/**
 * @author mabaoyu
 * @date：2017年4月17日 下午4:46:36
 */
public class UserResponseData extends BaseResponseData {

	private Long id;

	private String name;

	private String portrait;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

}
