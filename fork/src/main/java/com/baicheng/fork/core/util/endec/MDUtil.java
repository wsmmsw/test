package com.baicheng.fork.core.util.endec;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MDUtil {

	private static final Logger LOGGER = Logger.getLogger(MDUtil.class.getName());
	public final static int BIT_16 = 16;
	public final static int BIT_32 = 32;

	/**
	 *
	 * @param plainText
	 * @return
	 */
	public static String GetMD5Code(String plainText) {
		return GetMD5Uper(plainText, BIT_16, "UTF-8");
	}

	/**
	 * md5加密方法
	 *
	 * @param plainText
	 * @param bit
	 * @return String 返回32位md5加密字符串(16位加密取substring(8,24))
	 */
	public static String GetMD5Uper(String plainText, int bit, String charSet) {
		String md5Str = null;
		try {
			// 操作字符串
			StringBuilder buf = new StringBuilder();
			/**
			 * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			 * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
			 *
			 * MessageDigest 对象开始被初始化。 该对象通过使用 update()方法处理数据。 任何时候都可以调用 reset()方法重置摘要。
			 * 一旦所有需要更新的数据都已经被更新了，应该调用digest()方法之一完成哈希计算。
			 *
			 * 对于给定数量的更新数据，digest 方法只能被调用一次。 在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。
			 */
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
			md.update(plainText.getBytes(charSet));
			// 计算出摘要,完成哈希计算。
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				// 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
				buf.append(Integer.toHexString(i));
			}
			if (bit == BIT_32) {
				// 32位的加密
				md5Str = buf.toString().toUpperCase();
			} else {
				// 16位的加密
				md5Str = buf.toString().substring(8, 24).toUpperCase();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("md5Str::::" + md5Str);
		return md5Str;
	}

	/**
	 * md5加密方法
	 *
	 * @param plainText
	 * @param bit
	 * @return String 返回32位md5加密字符串(16位加密取substring(8,24))
	 */
	public static String GetMD5Lower(String plainText, int bit, String charSet) {
		String md5Str = null;
		try {
			// 操作字符串
			StringBuilder buf = new StringBuilder();
			/**
			 * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			 * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
			 *
			 * MessageDigest 对象开始被初始化。 该对象通过使用 update()方法处理数据。 任何时候都可以调用 reset()方法重置摘要。
			 * 一旦所有需要更新的数据都已经被更新了，应该调用digest()方法之一完成哈希计算。
			 *
			 * 对于给定数量的更新数据，digest 方法只能被调用一次。 在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。
			 */
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
			md.update(plainText.getBytes(charSet));
			// 计算出摘要,完成哈希计算。
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				// 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
				buf.append(Integer.toHexString(i));
			}
			if (bit == BIT_32) {
				// 32位的加密
				md5Str = buf.toString().toLowerCase();
			} else {
				// 16位的加密
				md5Str = buf.toString().substring(8, 24).toLowerCase();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		return md5Str;
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		// String str16 = GetMD5Code("hello");
		// System.out.println("1. 不指定位：" + str16);
		// str16 = GetMD5Uper("hello", BIT_16);
		// System.out.println("2. 16位：" + str16);
		// String strTest = GetMD5Uper("hello", -1);
		// System.out.println("3. 任意位：" + strTest);
		// String str32 = GetMD5Uper("hello", BIT_32);
		// System.out.println("4. 32位：" + str32);

		String str = "appid=wx9b03c145cf3a88eb&body=商品描述为空！！"
				+ "&device_info=WEB&mch_id=1307266401&nonce_str=75601948041b4c85b9c9046898e08e88"
				+ "&notify_url=http://test.wanmeixingcheng.com/VisaSvc/rest/front/payNotify"
				+ "&openid=oQKWGtwZq2aArOfSBtp2fSkKxBp8&out_trade_no=xgQWuHiL2016013015583111300"
				+ "&spbill_create_ip=36.110.19.42&total_fee=2&trade_type=JSAPI"
				+ "&key=8h74bs9v3r9s9hgk3H8GD902JHjs43n5";
		String newStr = GetMD5Uper(str, BIT_32, "UTF-8");
		System.out.println(newStr);
	}

}
