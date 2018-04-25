package com.baicheng.fork.core.util;

import java.util.*;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.baicheng.fork.core.util.endec.MDUtil;

public class SignUtil {

	private static final Log log = LogFactory.getLog(SignUtil.class);

	/**
	 * 获取支付所需签名
	 *
	 * @param ticket
	 * @param timeStamp
	 * @param card_id
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String getPayCustomSign(Map<String, Object> bizObj, String key) throws Exception {
		String bizString = FormatBizQueryParaMap(bizObj);
		log.info("bizString:::" + bizString);
		return sign(bizString, key);
	}

	public static String myFormat(Map<String, Object> paraMap) {
		StringBuilder sb = new StringBuilder();
		sb.append("appid=" + paraMap.get("appid"));
		// sb.append("&body="+URLEncoder.encode(paraMap.get("body").toString(),
		// "utf-8").toString());
		sb.append("&device_info=WEB");
		sb.append("&mch_id=" + paraMap.get("mch_id"));
		sb.append("&nonce_str=" + paraMap.get("nonce_str"));
		return sb.toString();
	}

	public static String FormatBizQueryParaMap(Map<String, Object> paraMap) throws Exception {

		String buff = "";
		try {
			List infoIds = new ArrayList(paraMap.entrySet());
			Collections.sort(infoIds, new Comparator() {
				@Override
				public int compare(Object o1, Object o2) {
					// TODO Auto-generated method stub
					Map.Entry<String, Object> aa = (Map.Entry<String, Object>) o1;
					Map.Entry<String, Object> bb = (Map.Entry<String, Object>) o2;
					return (aa.getKey()).compareTo(bb.getKey());
				}
			});

			for (int i = 0; i < infoIds.size(); i++) {
				Map.Entry<String, Object> item = (Map.Entry<String, Object>) infoIds.get(i);
				// System.out.println(item.getKey());
				if (item.getKey() != "") {
					String key = item.getKey();
					String val = item.getValue().toString();
					buff += key + "=" + val + "&";
				}
			}

			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return buff;
	}

	public static String sign(String content, String key) throws Exception {
		String signStr = "";
		signStr = content + "&key=" + key;
		log.info("withkey:::" + signStr);
		return MDUtil.GetMD5Uper(signStr, MDUtil.BIT_32, "UTF-8");
	}

	public static String getDigest(String myinfo) {
		String returnData = new String();
		try {
			java.security.MessageDigest alga = java.security.MessageDigest.getInstance("MD5");
			alga.update(myinfo.getBytes());
			byte[] digesta = alga.digest();
			returnData = byte2hex(digesta);
		} catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("非法摘要算法");
			returnData = null;
		}
		return returnData;
	}

	/**
	 * 二行制转字符串
	 */
	private static String byte2hex(byte[] b) { // 二行制转字符串
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			// if (n<b.length-1) hs=hs+":";
		}
		return hs;
	}

	public static String ArrayToXml(Map arr) throws Exception {
		String xml = "<xml>";
		Iterator iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();

			// if ("body".equals(key) || "attach".equals(key)) {
			// xml += "<" + key + ">" + URLEncoder.encode(val, "UTF-8").toString() + "</" +
			// key + ">";
			// } else {
			// xml += "<" + key + ">" + val + "</" + key + ">";
			// }
			xml += "<" + key + ">" + val + "</" + key + ">";
		}
		xml += "</xml>";
		return xml;
	}

	public static boolean IsNumeric(String str) {
		if (str.matches("\\d *")) {
			return true;
		} else {
			return false;
		}
	}

}
