package com.baicheng.fork.core.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;

@SuppressWarnings("rawtypes")
/**
 * 读取配置文件并排序
 *
 * @author darkangel
 *
 */
public class OrderProperties {

	private ArrayList<String> keyList = new ArrayList<String>(); // 存放键
	private ArrayList<String> valList = new ArrayList<String>(); // 存放值
	private int count = 0;
	Enumeration propertyNames;
	OrderEnumerator orderEnume;

	public OrderProperties() {

	}

	public void load(InputStream fis) throws UnsupportedEncodingException {
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
		setKeyValue(br);
	}

	private void setKeyValue(BufferedReader br) {
		String row;
		try {
			while ((row = br.readLine()) != null) {
				// 跳过空行
				if (row.length() == 0) {
					continue;
				}
				// 跳过注释
				if (row.charAt(0) == '#') {
					continue;
				}
				String nv[] = row.split("=");
				if (nv.length > 1) {
					this.keyList.add(nv[0]);
					this.valList.add(nv[1]);
				} else {
					this.keyList.add(nv[0]);
					this.valList.add("");
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 实现 Enumeration 接口的对象
	public Enumeration propertyNames() {
		this.orderEnume = new OrderEnumerator(this.count, this.keyList, this.valList);
		return this.orderEnume;
	}

	/**
	 * 通过键找到值
	 *
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		int index = this.keyList.indexOf(key);
		if (index > -1) {
			return this.valList.get(index);
		}
		return "";
	}

	@SuppressWarnings("unused")
	class OrderEnumerator implements Enumeration {

		private ArrayList<String> keyList; // 存放键
		private ArrayList<String> valList; // 存放值
		private int count; // 计数器

		public OrderEnumerator(int count, ArrayList<String> keyList, ArrayList<String> valList) {
			this.count = count;
			this.keyList = keyList;
			this.valList = valList;
		}

		/**
		 * 是否还有下一个元素
		 */
		@Override
		public boolean hasMoreElements() {
			// System.out.println(count + "," + keyList.size());
			return this.count < this.keyList.size();
		}

		/**
		 * 返回下个元素的值
		 */
		@Override
		public Object nextElement() {
			return this.keyList.get(this.count++);
		}
	}

	/**
	 * 加载配置文件
	 *
	 * @param filename
	 * @return
	 */
	public static InputStream getInputStream(String filename) {
		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(filename);
		} else {
			stream = OrderProperties.class.getClassLoader().getResourceAsStream(filename);
		}
		return stream;
	}

}
