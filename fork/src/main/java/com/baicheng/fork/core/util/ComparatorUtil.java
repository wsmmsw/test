package com.baicheng.fork.core.util;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

/**
 * 集合排序工具类
 * 
 * @author mabaoyu
 */
public class ComparatorUtil implements Comparator<Object> {

	private String order;// 排序规则

	private String attribute; // 属性名称

	@SuppressWarnings("unchecked")
	private Comparator<Object> comp = new ComparableComparator();

	public ComparatorUtil(String attrib, String order) {
		this.attribute = attrib;
		this.order = order;
	}

	public ComparatorUtil(String attrib) {
		this.attribute = attrib;
	}

	@Override
	public int compare(Object o1, Object o2) {

		if (o1 == null) {
			return 1;
		} else if (o2 == null) {
			return -1;
		}
		try {
			Object ret1 = PropertyUtils.getProperty(o1, this.attribute);
			Object ret2 = PropertyUtils.getProperty(o2, this.attribute);
			if ("asc".equals(this.order)) {
				return this.comp.compare(ret2, ret1);
			} else {
				return this.comp.compare(ret1, ret2);
			}

		} catch (Exception e) {
			return 0;
		}
	}

}