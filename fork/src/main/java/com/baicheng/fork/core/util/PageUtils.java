package com.baicheng.fork.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具类
 *
 * @author mabaoyu
 * @date：2017年3月15日 下午6:42:55
 */
public class PageUtils {

	/**
	 * 获得当前页码
	 *
	 * @param request
	 * @return
	 */
	public static int getPageNow(HttpServletRequest request) {
		String pageS = "1";
		if (request.getParameter("pagenow") != null || "".equals(request.getParameter("pagenow"))) {
			pageS = request.getParameter("pagenow").toString();
		}
		return Integer.parseInt(pageS);
	}

	/**
	 * 获得显示条数
	 *
	 * @param request
	 * @return
	 */
	public static int getPageSize(HttpServletRequest request) {
		String pageS = "15";
		if (request.getParameter("pagesize") != null || "".equals(request.getParameter("pagesize"))) {
			pageS = request.getParameter("pagesize").toString();
		}
		return Integer.parseInt(pageS);
	}

	public static int getPageSizeMin(HttpServletRequest request) {
		String pageS = "15";
		String ss = request.getParameter("status");
		if (ss.equals("true")) {
			pageS = "999999999";
		} else if (request.getParameter("pagesize") != null || "".equals(request.getParameter("pagesize"))) {
			pageS = request.getParameter("pagesize").toString();
		}
		return Integer.parseInt(pageS);
	}

	/**
	 * 根据总记录数和每页记录数，计算总页数
	 *
	 * @param recordsSize
	 * @param pageSize
	 * @return
	 */
	public static int getTotalPages(int recordsSize, int pageSize) {
		return recordsSize % pageSize == 0 ? recordsSize / pageSize : recordsSize / pageSize + 1;
	}

	/**
	 * 计算当前页的开始索引(索引从0开始).
	 *
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public static int getPageNowIndex(int pageNow, int pageSize) {
		return (pageNow - 1) * pageSize;
	}

	/**
	 * 计算下页的开始索引(索引从0开始).
	 *
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public static int getNextPageIndex(int pageNow, int pageSize) {
		return pageNow * pageSize;
	}

	/**
	 * 计算上页的开始索引(索引从0开始).
	 *
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public static int getPrePageIndex(int pageNow, int pageSize) {
		return (pageNow + 1) * pageSize;
	}
}
