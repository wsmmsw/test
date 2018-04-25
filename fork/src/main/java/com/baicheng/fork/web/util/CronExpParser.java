package com.baicheng.fork.web.util;

import java.text.ParseException;
import java.util.*;

import org.quartz.CronExpression;

import com.nicetrip.freetrip.core.util.DateUtils;

/**
 * @author mabaoyu
 * 
 * @date：2017年4月25日 下午1:45:35
 */
public class CronExpParser {

	public static String translateToChinese(String cronExp) {
		if (cronExp == null || cronExp.length() < 1) {
			return "cron表达式为空";
		}
		try {
			new CronExpression(cronExp);
		} catch (ParseException e) {
			return "时间表达式不正确";
		}
		String[] tmpCorns = cronExp.split(" ");
		StringBuffer sBuffer = new StringBuffer();
		// 解析月
		if (!tmpCorns[4].equals("*")) {
			sBuffer.append(tmpCorns[4]).append("月");
		} else {
			sBuffer.append("每月");
		}
		// 解析周
		if (!tmpCorns[5].equals("*") && !tmpCorns[5].equals("?")) {
			char[] tmpArray = tmpCorns[5].toCharArray();
			for (char tmp : tmpArray) {
				switch (tmp) {
				case '1':
					sBuffer.append("星期天");
					break;
				case '2':
					sBuffer.append("星期一");
					break;
				case '3':
					sBuffer.append("星期二");
					break;
				case '4':
					sBuffer.append("星期三");
					break;
				case '5':
					sBuffer.append("星期四");
					break;
				case '6':
					sBuffer.append("星期五");
					break;
				case '7':
					sBuffer.append("星期六");
					break;
				case '-':
					sBuffer.append("至");
					break;
				default:
					sBuffer.append(tmp);
					break;
				}
			}
		}

		// 解析日
		if (!tmpCorns[3].equals("?")) {
			if (!tmpCorns[3].equals("*")) {
				sBuffer.append(tmpCorns[3]).append("日");
			} else {
				sBuffer.append("每日");
			}
		}

		// 解析时
		if (!tmpCorns[2].equals("*")) {
			sBuffer.append(tmpCorns[2]).append("时");
		} else {
			sBuffer.append("每时");
		}

		// 解析分
		if (!tmpCorns[1].equals("*")) {
			sBuffer.append(tmpCorns[1]).append("分");
		} else {
			sBuffer.append("每分");
		}

		// 解析秒
		if (!tmpCorns[0].equals("*")) {
			sBuffer.append(tmpCorns[0]).append("秒");
		} else {
			sBuffer.append("每秒");
		}

		return sBuffer.toString();
	}

	public static Map<String, Object> cronExpressionTrack(String cronExpr, int runNum) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		List<String> list = new ArrayList<String>();
		try {
			CronExpression ce = new CronExpression(cronExpr);
			Date date = new Date();
			for (int i = 1; i <= runNum; i++) {
				date = ce.getNextValidTimeAfter(date);
				if (date == null) {
					if (i == 1) {
						result.put("code", -1);
						result.put("error", "任务已过期");
					}
					break;
				}
				list.add(DateUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
			}
			result.put("list", list);
		} catch (ParseException e) {
			result.put("code", -1);
			result.put("error", "时间表达式错误");
		}
		return result;
	}

	public static void main(String[] args) throws ParseException, InterruptedException {
		Map<String, Object> result = cronExpressionTrack("0 0 12 * * ? ", 5);
		System.out.println(result.get("list"));
	}

}
