package com.baicheng.fork.web.dto.response;

import com.baicheng.domain.Domain;

/**
 * @author SongPengpeng
 * @date 2017/8/9.
 */
public class BaseResponseData extends Domain {

	private Integer totalCount;// 总记录数
	private Integer pageSize;// 每页数量
	private Integer totalPage;// 总页数
	private Integer pageNow;// 当前页数

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

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

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}
