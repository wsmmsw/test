package com.baicheng.fork.core.util;

import java.util.Comparator;
import java.util.Date;

public class DateSortClass implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		Object[] user0 = (Object[]) arg0;
		Object[] user1 = (Object[]) arg1;
		Date item0 = (Date) user0[1];
		Date item1 = (Date) user1[1];
		int flag = item1.compareTo(item0);
		return flag;
	}

}
