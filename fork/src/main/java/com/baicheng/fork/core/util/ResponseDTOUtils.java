package com.baicheng.fork.core.util;

import java.util.List;

import com.baicheng.fork.web.dto.response.BaseResponseData;

/**
 * @author SongPengpeng
 * @date 2017/8/10.
 */
public class ResponseDTOUtils {

	/**
	 * @param dto
	 * @return
	 */
	public <T> T getDTO(Class<T> dto) {
		try {
			return (T) dto.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * @param responseData
	 * @param totalCount
	 * @param pageSize
	 * @param totalPage
	 * @param pageNow
	 */
	public static void setPageInfo(BaseResponseData responseData, int totalCount, int pageSize, int totalPage,
			int pageNow) {
		if (responseData == null) {
			return;
		}
		responseData.setTotalCount(totalCount);
		responseData.setTotalPage(totalPage);
		responseData.setPageSize(pageSize);
		responseData.setPageNow(pageNow);
	}

	/**
	 * @param responseData
	 * @param list
	 * @param pageSize
	 * @param pageNow
	 */
	public static void setPageInfo(BaseResponseData responseData, List list, int pageSize, int pageNow) {
		int totalCount = CollectionUtils.getSize(list);
		int totalPage = PageUtils.getTotalPages(totalCount, pageSize);
		setPageInfo(responseData, totalCount, pageSize, totalPage, pageNow);
	}

}
