package com.baicheng.fork.web.dto.request;

import com.baicheng.domain.Domain;

/**
 * @author SongPengpeng
 * @date 2017/8/9.
 */
public class BaseRequestDTO extends Domain {

	/**
	 * 每页默认大小
	 */
	public static final Integer PAGE_SIZE = 10;

	/**
	 * 当前默认页
	 */
	public static final Integer CURRENT_PAGE = 1;

	/**
	 * 页大小
	 */
	private Integer pageSize = PAGE_SIZE;

	/**
	 * 当前页
	 */
	private Integer pageNow = CURRENT_PAGE;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
}
