package com.baicheng.fork.core.util;

import java.util.Date;

/**
 * 订单流水号生成
 * 
 * @return
 */
public class CodeGenerator {

	private static int i = 0;

	/**
	 * @param bName 商户名
	 * @param pType 商品类型
	 * @return : String
	 */
	public static String genOrdId16(String bName, long pType) {
		if (bName == null) {
			return null;
		}
		String bCode = PinYin2Abbreviation.cn2py(bName);
		String typeCode = switchPType(pType);
		int index = (int) (Math.random() * 9000 + 1000);
		String now = DateUtils.dateToString6(new Date());
		String orderNum = bCode + typeCode + now + index;
		return orderNum;
	}

	private static String switchPType(long type) {
		switch (Long.toString(type)) {
		case "1":
			return "V";
		case "2":
			return "D";
		case "3":
			return "T";
		}
		return "";
	}

}
