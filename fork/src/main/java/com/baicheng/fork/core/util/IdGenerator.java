package com.baicheng.fork.core.util;

import java.util.Date;

/**
 * 不重复编码：日期（当时的完整datetime，到秒）+流水号（不固定位数，自然数增加）
 *
 * @return
 */
public class IdGenerator {

	private static int i = 0;

	/**
	 * @DESCRIPION :每10毫秒可生成100个序列号；优于每毫秒10个序列号
	 * @return : String
	 */
	public synchronized static String genOrdId16(String pCode, String cCode) {
		i = i % 100;
		String index = (i < 10) ? ("0" + i) : "" + i;
		// String ff = pCode.length() > 2 ? pCode.substring(pCode.length() - 2) : pCode;
		String cc = "";
		if (cCode == null || "".equals(cCode)) {
			cc = "";
		} else {
			cc = cCode.length() > 2 ? cCode.substring(cCode.length() - 2) : cCode;
		}
		String now = DateUtils.dateTimeToStringWithoutSplitChar(new Date());
		String orderNum = pCode + cc + now + index;
		i++;
		return orderNum;
	}

	public synchronized static void resetIndex() {
		i = 0;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					System.err.println(genOrdId16("ss", "gg"));

				}
			});
			t.start();
		}
	}

}
