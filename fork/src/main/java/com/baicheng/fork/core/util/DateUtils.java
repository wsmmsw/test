package com.baicheng.fork.core.util;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * 各种常用的日期方法
 *
 * @author Administrator
 *
 */
public class DateUtils {

	protected final static Log logger = LogFactory.getLog(DateUtils.class); // 日志输出接口

	// 格式化全日历
	public static String fallFormat(Calendar dd) {
		if (dd == null) {
			return "";
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sd.format(dd.getTime());
	}

	// 格式化日期
	public static String dayFormat(Calendar dd) {
		if (dd == null) {
			return "";
		}
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		return sd.format(dd.getTime());
	}

	// 格式化时间
	public static String timeFormat(Calendar dd) {
		if (dd == null) {
			return "";
		}
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
		return sd.format(dd.getTime());
	}

	// 逆格式化全部日期, 格式yyyy-MM-dd HH:mm:ss
	public static Calendar getCalendar(String dd) {
		Calendar cc = Calendar.getInstance();
		if (dd == null || dd.equals("")) {
			return cc;
		}
		try {
			int n = dd.indexOf("-");
			if (n == -1) {
				return cc;
			}
			int yyyy = Integer.parseInt(dd.substring(0, n));

			int n1 = n + 1;
			n = dd.indexOf("-", n1);
			if (n == -1) {
				return cc;
			}
			int mm = Integer.parseInt(dd.substring(n1, n));

			n1 = n + 1;
			n = dd.indexOf(" ", n1);
			if (n == -1) {
				return cc;
			}
			int dd1 = Integer.parseInt(dd.substring(n1, n));

			n1 = n + 1;
			n = dd.indexOf(":", n1);
			if (n == -1) {
				return cc;
			}
			int hh = Integer.parseInt(dd.substring(n1, n));

			n1 = n + 1;
			n = dd.indexOf(":", n1);
			if (n == -1) {
				return cc;
			}
			int MM = Integer.parseInt(dd.substring(n1, n));

			int ss = Integer.parseInt(dd.substring(n + 1));

			cc.set(yyyy, mm - 1, dd1, hh, MM, ss);
			cc.set(Calendar.MILLISECOND, 0);
		} catch (Exception e) {
			logger.error(e);
		}
		return cc;
	}

	// 逆格式化日期, 格式yyyy-MM-dd
	public static Calendar getDay(String dd) {
		Calendar cc = Calendar.getInstance();
		if (dd == null || dd.equals("")) {
			return cc;
		}
		try {
			int n = dd.indexOf("-");
			if (n == -1) {
				return cc;
			}
			int yyyy = Integer.parseInt(dd.substring(0, n));
			int n1 = n + 1;
			n = dd.indexOf("-", n1);
			if (n == -1) {
				return cc;
			}
			int mm = Integer.parseInt(dd.substring(n1, n));
			int dd1 = Integer.parseInt(dd.substring(n + 1));
			cc.set(yyyy, mm - 1, dd1, 0, 0, 0);
			cc.set(Calendar.MILLISECOND, 0);
		} catch (Exception e) {
			logger.error(e);
		}
		return cc;
	}

	// 逆格式化时间，格式为HH:mm:ss
	public static Calendar getTime(String dd) {
		Calendar cc = Calendar.getInstance();
		if (dd == null || dd.equals("")) {
			return cc;
		}
		try {
			int n = dd.indexOf(":");
			if (n == -1) {
				return cc;
			}
			int hh = Integer.parseInt(dd.substring(0, n));
			int n1 = n + 1;
			n = dd.indexOf(":", n1);
			if (n == -1) {
				return cc;
			}
			int mm = Integer.parseInt(dd.substring(n1, n));
			int ss = Integer.parseInt(dd.substring(n + 1));
			cc.set(2000, 0, 1, hh, mm, ss);
			cc.set(Calendar.MILLISECOND, 0);
		} catch (Exception e) {
			logger.error(e);
		}
		return cc;
	}

	// 比较日期
	public static boolean dayLarge(Calendar d1, Calendar d2) {
		int dd1 = d1.get(Calendar.YEAR) * 366 + d1.get(Calendar.DAY_OF_YEAR);
		int dd2 = d2.get(Calendar.YEAR) * 366 + d2.get(Calendar.DAY_OF_YEAR);
		return dd1 > dd2;
	}

	public static boolean dayLargeEqual(Calendar d1, Calendar d2) {
		int dd1 = d1.get(Calendar.YEAR) * 366 + d1.get(Calendar.DAY_OF_YEAR);
		int dd2 = d2.get(Calendar.YEAR) * 366 + d2.get(Calendar.DAY_OF_YEAR);
		return dd1 >= dd2;
	}

	public static boolean dayEqual(Calendar d1, Calendar d2) {
		int dd1 = d1.get(Calendar.YEAR) * 366 + d1.get(Calendar.DAY_OF_YEAR);
		int dd2 = d2.get(Calendar.YEAR) * 366 + d2.get(Calendar.DAY_OF_YEAR);
		return dd1 == dd2;
	}

	// 比较时间
	public static boolean timeLarge(Calendar d1, Calendar d2) {
		int tt1 = (d1.get(Calendar.HOUR_OF_DAY) * 60 + d1.get(Calendar.MINUTE)) * 60 + d1.get(Calendar.SECOND);
		int tt2 = (d2.get(Calendar.HOUR_OF_DAY) * 60 + d2.get(Calendar.MINUTE)) * 60 + d2.get(Calendar.SECOND);
		return tt1 > tt2;
	}

	public static boolean timeLargeEqual(Calendar d1, Calendar d2) {
		int tt1 = (d1.get(Calendar.HOUR_OF_DAY) * 60 + d1.get(Calendar.MINUTE)) * 60 + d1.get(Calendar.SECOND);
		int tt2 = (d2.get(Calendar.HOUR_OF_DAY) * 60 + d2.get(Calendar.MINUTE)) * 60 + d2.get(Calendar.SECOND);
		return tt1 >= tt2;
	}

	public static boolean timeEqual(Calendar d1, Calendar d2) {
		int tt1 = (d1.get(Calendar.HOUR_OF_DAY) * 60 + d1.get(Calendar.MINUTE)) * 60 + d1.get(Calendar.SECOND);
		int tt2 = (d2.get(Calendar.HOUR_OF_DAY) * 60 + d2.get(Calendar.MINUTE)) * 60 + d2.get(Calendar.SECOND);
		return tt1 == tt2;
	}

	// 比较日历
	public static boolean calendarLarge(Calendar d1, Calendar d2) {
		int dd1 = d1.get(Calendar.YEAR) * 366 + d1.get(Calendar.DAY_OF_YEAR);
		int dd2 = d2.get(Calendar.YEAR) * 366 + d2.get(Calendar.DAY_OF_YEAR);
		if (dd1 > dd2) {
			return true;
		}
		if (dd1 < dd2) {
			return false;
		}
		int tt1 = (d1.get(Calendar.HOUR_OF_DAY) * 60 + d1.get(Calendar.MINUTE)) * 60 + d1.get(Calendar.SECOND);
		int tt2 = (d2.get(Calendar.HOUR_OF_DAY) * 60 + d2.get(Calendar.MINUTE)) * 60 + d2.get(Calendar.SECOND);
		return tt1 > tt2;
	}

	public static boolean calendarLargeEqual(Calendar d1, Calendar d2) {
		int dd1 = d1.get(Calendar.YEAR) * 366 + d1.get(Calendar.DAY_OF_YEAR);
		int dd2 = d2.get(Calendar.YEAR) * 366 + d2.get(Calendar.DAY_OF_YEAR);
		if (dd1 > dd2) {
			return true;
		}
		if (dd1 < dd2) {
			return false;
		}
		int tt1 = (d1.get(Calendar.HOUR_OF_DAY) * 60 + d1.get(Calendar.MINUTE)) * 60 + d1.get(Calendar.SECOND);
		int tt2 = (d2.get(Calendar.HOUR_OF_DAY) * 60 + d2.get(Calendar.MINUTE)) * 60 + d2.get(Calendar.SECOND);
		return tt1 >= tt2;
	}

	public static boolean calendarEqual(Calendar d1, Calendar d2) {
		int dd1 = d1.get(Calendar.YEAR) * 366 + d1.get(Calendar.DAY_OF_YEAR);
		int dd2 = d2.get(Calendar.YEAR) * 366 + d2.get(Calendar.DAY_OF_YEAR);
		if (dd1 > dd2) {
			return false;
		}
		if (dd1 < dd2) {
			return false;
		}
		int tt1 = (d1.get(Calendar.HOUR_OF_DAY) * 60 + d1.get(Calendar.MINUTE)) * 60 + d1.get(Calendar.SECOND);
		int tt2 = (d2.get(Calendar.HOUR_OF_DAY) * 60 + d2.get(Calendar.MINUTE)) * 60 + d2.get(Calendar.SECOND);
		return tt1 == tt2;
	}

	// 格式化字符串
	public static String notNull(String sss) {
		if (sss == null) {
			return "";
		}
		return sss;
	}

	// 格式化整型数
	public static int getInteger(String sss) {
		try {
			int n = Integer.parseInt(sss);
			return n;
		} catch (NumberFormatException e) {
			logger.error(e);
			e.printStackTrace();
			return 0;
		}
	}

	// 格式化long数
	public static long getLong(String sss) {
		try {
			long n = Long.parseLong(sss);
			return n;
		} catch (NumberFormatException e) {
			logger.error(e);
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 格式化日期字符串
	 *
	 * @param t
	 * @return
	 */
	public static String dateFormat(String t) {
		if (t == null || "".equals(t)) {
			return null;
		}
		Long dFormat = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("Asia/Shanghai"));
		try {
			dFormat = formatter.parse(t + " 00:00:00").getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dFormat == null ? null : dFormat + "";
	}

	/**
	 * 将字符串数字显示
	 *
	 * @param t
	 * @return
	 */
	public static String formatDateS(String t) {
		NumberFormat formatter = new DecimalFormat("0.00");
		// if(t==null || )
		Double x = new Double(0.00);
		try {
			x = new Double(new Double(t) / 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return formatter.format(x);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() <= 0;
	}

	/**
	 * 随机码生成器。模式：a1小写字母+除0数字 ；位数8位
	 *
	 * @param num 产生随机码数量；
	 * @return
	 */
	public static String[] genRandomEightStr(int num) {
		return genRandomStr("a1", 8, num);
	}

	/**
	 * 随机码生成器；
	 *
	 * @param rand_pattern 表示随机的字符集，由A(大写字母),a(小写字母),0(数字)或1(数字,除去0)最多三种字符组合,不分顺序；
	 * @param bit 生成的位数；
	 * @param num 产生随机码数量；
	 * @return
	 */
	public static String[] genRandomStr(String rand_pattern, int bit, int num) {
		return genRandomStr(rand_pattern, bit, false, "", num);
	}

	/**
	 * 随机码生成器；
	 *
	 * @param rand_pattern 表示随机的字符集，由A(大写字母),a(小写字母),0(数字)或1(数字,除去0)最多三种字符组合,不分顺序；
	 * @param bit 生成的位数；
	 * @param isPrefix =true时，exStr为前缀，isPrefix=false时，exStr为后缀；
	 * @param exStr 为追加字符串；
	 * @param num 产生随机码数量；
	 * @return
	 */
	public static String[] genRandomStr(String rand_pattern, int bit, boolean isPrefix, String exStr, int num) {
		StringBuilder idStr = null;
		char a[] = null;
		int b[] = null;

		// 解析rand_pattern模式；
		int pl = rand_pattern.length();
		a = new char[pl];
		b = new int[pl];
		for (int j = 0; j < pl; j++) {
			char ch = rand_pattern.charAt(j);
			if (ch == 'a') {
				a[j] = 'a';
				b[j] = 26;
			} else if (ch == 'A') {
				a[j] = 'A';
				b[j] = 26;
			} else if (ch == '0') {
				a[j] = '0';
				b[j] = 10;
			} else if (ch == '1') {
				a[j] = '1';
				b[j] = 9;
			}
		}

		Random rd = new Random();
		String str = null;
		HashMap<String, String> genMap = new HashMap<String, String>();
		String[] resultStr = new String[num];

		for (int n = 0; n < num; n++) {
			do {
				idStr = new StringBuilder();
				for (int i = 0; i < bit; i++) {
					int choice = (int) (rd.nextDouble() * pl);
					char letter = (char) (a[choice] + (int) (rd.nextDouble() * b[choice]));
					idStr.append(letter);
				}

				if (isPrefix) {
					idStr.insert(0, exStr);
				} else {
					idStr.append(exStr);
				}
				str = genMap.get(idStr.toString());

			} while (str != null);
			resultStr[n] = idStr.toString();
			genMap.put(resultStr[n], "");
		}

		return resultStr;
	}

	/**
	 * 获取字符串source中第num次出现的字符regex位置
	 *
	 * @param source
	 * @param regex
	 * @param num
	 * @return
	 */
	public static int getCharacterPosition(String source, String regex, int num) {
		// 这里是获取"/"符号的位置
		Matcher slashMatcher = Pattern.compile(regex).matcher(source);
		int mIdx = 0;
		while (slashMatcher.find()) {
			mIdx++;
			// 当"/"符号第三次出现的位置
			if (mIdx == num) {
				break;
			}
		}
		return slashMatcher.start();
	}

	public static boolean sql_inj(String str) {
		if (str == null) {
			return false;
		}

		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";

		String[] inj_stra = inj_str.split("\\|");

		for (int i = 0; i < inj_stra.length; i++) {

			if (str.indexOf(inj_stra[i]) != -1) {

				return true;

			}

		}

		return false;

	}

	/**
	 * 将全日期转化为字符串
	 *
	 * @param date
	 * @return String
	 */
	public static String dateTimeToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = "";
		if (date != null) {
			str = sdf.format(date);
		}
		return str;
	}

	/**
	 * 将日期转化为字符串
	 *
	 * @param date
	 * @return String
	 */
	public static String dateToString6(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = "";
		if (date != null) {
			str = sdf.format(date);
		}
		return str.substring(2, str.length());
	}

	/**
	 * 将日期转化为字符串
	 *
	 * @param date
	 * @return String
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = "";
		if (date != null) {
			str = sdf.format(date);
		}
		return str;
	}

	/**
	 * 将字符串转化为日期
	 *
	 * @param date
	 * @return String
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat sdf = null;
		if (str.length() == 7) {
			sdf = new SimpleDateFormat("yyyy-MM");
		} else if (str.length() > 7 && str.length() <= 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		Date date = null;
		if (str != null && !"".equals(str)) {
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error(e);
			}
		}

		return date;
	}

	/**
	 * 将全日期转化为字符串（没有分隔符）
	 *
	 * @param date
	 * @return String
	 */
	public static String dateTimeToStringWithoutSplitChar(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = "";
		if (date != null) {
			str = sdf.format(date);
		}
		return str;
	}

	/**
	 * 判断当前用户是否拥有操作次功能的权利
	 *
	 * @param permission
	 * @return
	 */
	public static boolean isEnableDoThis(String permission) {
		boolean rdata = false;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated() && currentUser.isPermitted(permission)) {
				rdata = true;
			}
		} catch (AuthenticationException e) {
			logger.error(e);
		}
		return rdata;
	}

	/**
	 * 格式化数据为人民币符号的货币表达
	 *
	 * @param number
	 * @return
	 */
	public static String numberToCurrency(BigDecimal number) {
		NumberFormat nf = new DecimalFormat("¥##.##");
		String str = nf.format(number);
		return str;
	}

	public static String uploadFile(InputStream stream, String fileName, String uploadPath) {
		// String rData = "";
		// String bucketName = QiniuClient.BUCKET_FT_TEST;
		// QiniuClient client = new QiniuClient(QiniuClient.ACCESS_KEY,
		// QiniuClient.SECRET_KEY);
		// client.setEndPoint(QiniuClient.EP_HTTPS_TEST);
		// if (!client.createConnection()) {
		// System.out.println("Create connection failed!");
		// return null;
		// }
		// try {
		// String strUrl = client.uploadFile(bucketName, uploadPath, stream,
		// stream.available(), fileName);
		// if (strUrl != null) {
		// //System.out.println("Upload file OK. Url = " + client.getEndPoint() + "/" +
		// strUrl);
		// rData = "http://" + client.getEndPoint() + "/" + strUrl;
		// } else {
		// System.out.println("Upload file failed!");
		// }
		// } catch (FileNotFoundException e) {
		// System.out.println(fileName + "file is not exists!");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// client.closeConnection();
		return null;
	}

	/**
	 * 将日期转化为毫秒数
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static long parseDate(String date, String format) {
		long l = 0l;
		try {
			SimpleDateFormat ft = new SimpleDateFormat(format);
			Date dateString = ft.parse(date);
			l = dateString.getTime();
		} catch (Exception e) {
			return l;
		}

		return l;
	}

	/**
	 * 将日期转化为毫秒数
	 *
	 * @param date
	 * @param format
	 * @param timeZone
	 * @return
	 */
	public static long parseDate(String date, String format, long timeZone) {
		long l = 0l;
		try {
			SimpleDateFormat ft = new SimpleDateFormat(format);

			String[] zoneIds = TimeZone.getAvailableIDs((int) timeZone);
			if (zoneIds != null && zoneIds.length > 0) {
				TimeZone zone = TimeZone.getTimeZone(zoneIds[0]);
				ft.setTimeZone(zone);
			}

			Date dateString = ft.parse(date);
			l = dateString.getTime();
		} catch (Exception e) {
			return l;
		}

		return l;
	}
}
