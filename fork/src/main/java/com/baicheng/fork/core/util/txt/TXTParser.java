package com.baicheng.fork.core.util.txt;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TXT 文件解析器
 * 
 * @author Administrator
 *
 */
public class TXTParser {

	public static final String TYPE_CHARSET = "UTF-8";

	/**
	 * TXT文件导入
	 * 
	 * @param inputStream
	 * @param title 列标题
	 * @param splitRow 行分割符
	 * @param splitColumn 列分割符
	 * @param startRow 开始行
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> readeTxtFile(InputStream inputStream, String[] title,
			String splitColumn, String splitRow, int startRow) throws Exception {
		List<Map<String, String>> resultList = null;
		try {
			// 解析txt文件
			resultList = convertTxtInputStream(inputStream, title, splitColumn, splitRow, startRow);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return resultList;
	}

	/**
	 * 文件导出
	 * 
	 * @param os
	 * @param title 列标题
	 * @param splitRow 行分割符
	 * @param splitColumn 列分割符
	 * @param data 数据
	 * @throws Exception
	 */
	public static void writeTxtFile(OutputStream os, String[] title, String splitColumn,
			String splitRow, List<Map<String, String>> data) throws Exception {

		BufferedWriter in = null;
		OutputStreamWriter outWrite = null;
		try {
			// 向文件写入内容(输出流)
			String titleRow = "";
			int length = 0;
			if (null == splitRow) {
				splitRow = "";
			}
			if (title != null && title.length > 0) {
				length = title.length;
				for (String string : title) {
					titleRow += string + "  ";
				}
				outWrite = new OutputStreamWriter(os, TYPE_CHARSET);
				in = new BufferedWriter(outWrite);
				if (in != null) {
					// 输出标题行
					in.write(titleRow);
					// 新建一行
					in.newLine();
					// TODO 遍历传入的参数拼接数据进行导出
					Map<String, String> map = null;
					StringBuffer stringBuffer = null;
					if (data != null && data.size() > 0) {
						for (int i = 0; i < data.size(); i++) {
							stringBuffer = new StringBuffer();
							map = data.get(i);
							for (int j = 0; j < title.length; j++) {
								if (j == length - 1) {
									stringBuffer.append(map.get(title[j]));
								} else {
									stringBuffer.append(map.get(title[j])).append(splitColumn);
								}
							}
							in.write(stringBuffer.append(splitRow).toString());
							in.newLine();
						}
					}

				} else {
					throw new Exception("创建读文件流异常");
				}
			}
			// in.flush();
		} catch (Exception e) {
			// 异常抛出在调用层接收展示错误信息
			throw new Exception(e.getMessage());
		} finally {
			in.close();
			outWrite.close();
		}
	}

	// 解析文件
	private static List<Map<String, String>> convertTxtInputStream(InputStream inputStream, String[] title,
			String splitColumn,
			String splitRow, int startRow) throws Exception {
		List<Map<String, String>> resultList = null;
		InputStreamReader read = null;
		try {
			// 考虑到编码格式
			String encoding = TYPE_CHARSET;
			// 解析txt文件
			resultList = new ArrayList<Map<String, String>>();
			Map<String, String> map = null;
			read = new InputStreamReader(inputStream, encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			int start = 1;
			if (title != null && title.length > 0) {
				if (isNotNull(splitColumn)) {
					if (isNull(splitRow)) {
						while ((lineTxt = bufferedReader.readLine()) != null) {
							if (start >= startRow) {
								String[] columnArray = lineTxt.split(splitColumn);
								/**
								 * 判断列和列标题长度 1：长度相等解析数据 2：长度不相等数据有问题
								 */
								if (columnArray.length == title.length) {
									map = new HashMap<String, String>();
									for (int i = 0; i < title.length; i++) {
										map.put(title[i], columnArray[i]);
									}
									resultList.add(map);
								} else {
									throw new Exception("标题和列数不匹配");
								}
							}
							start++;
						}
					} else {
						while ((lineTxt = bufferedReader.readLine()) != null) {
							if (start >= startRow) {
								String[] rowArray = lineTxt.split(splitRow);
								for (String string : rowArray) {
									String[] columnArray = string.split(splitColumn);
									if (columnArray.length == title.length) {
										map = new HashMap<String, String>();
										for (int i = 0; i < title.length; i++) {
											map.put(title[i], columnArray[i]);
										}
										resultList.add(map);
									} else {
										throw new Exception("标题和列数不匹配");
									}
								}
							}
							start++;
						}
					}
				} else {
					while ((lineTxt = bufferedReader.readLine()) != null) {
						if (start >= startRow) {
							map = new HashMap<String, String>();
							map.put(title[0], lineTxt);
							resultList.add(map);
						}
						start++;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
			read.close();
		}
		return resultList;
	}

	private static boolean isNotNull(String s) {
		if (s != null && !"".equals(s)) {
			return true;
		}
		return false;
	}

	private static boolean isNull(String s) {
		if (null == s || "".equals(s)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\123.txt");
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			String[] title = { "姓名" };
			String spiltColumn = ",";
			String splitRow = ";";
			// List<Map<String, String>> resuList =
			// readeTxtFile(inputStream,title,splitRow,spiltColumn,2);
			OutputStream os = new FileOutputStream(file);
			List<Map<String, String>> data = new ArrayList();
			Map<String, String> map = null;
			for (int i = 0; i < 10; i++) {
				map = new HashMap<String, String>();
				map.put("姓名", "xm" + i);
				// map.put("mobile", "mobile"+i);
				data.add(map);
			}
			writeTxtFile(os, title, spiltColumn, null, data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
