package com.baicheng.fork.core.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 集合工具类
 *
 * @author mabaoyu
 * @date：2017年3月23日 下午1:46:44
 */
public class CollectionUtils {

	/**
	 * 序列化 list 集合
	 *
	 * @param list
	 * @return
	 */
	public static byte[] serializeList(List<?> list) {

		if (isEmpty(list)) {
			return null;
		}
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			for (Object obj : list) {
				oos.writeObject(obj);
			}
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(oos);
			close(baos);
		}
		return bytes;
	}

	/**
	 * 反序列化 list 集合
	 *
	 * @param bytes
	 * @return
	 */
	public static List<?> unserializeList(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		List<Object> list = new ArrayList<Object>();
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			while (bais.available() > 0) {
				Object obj = (Object) ois.readObject();
				if (obj == null) {
					break;
				}
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(bais);
			close(ois);
		}
		return list;
	}

	public static boolean isEmpty(Collection<?> c) {
		if (c == null || c.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 关闭io流对象
	 *
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param collection
	 * @param <T>
	 * @return
	 */
	public static int getSize(Collection collection) {
		if (collection == null || collection.isEmpty()) {
			return 0;
		}
		return collection.size();
	}

	/**
	 * @param list
	 * @param fromIndex 开始索引
	 * @param length 子列表长度
	 * @param <T>
	 * @return
	 * @author SongPengpeng 截取List的子列表，如果fromIndex或length非法，则返回原始List.
	 */
	public static <T> List<T> subList(List<T> list, int fromIndex, int length) {
		if (list == null || list.isEmpty() || (fromIndex < 0 || length < 1)) {
			return list;
		}

		int lastIndex = list.size() - 1;
		int toIndex = fromIndex + length;

		if (fromIndex > lastIndex) {
			return null;
		}

		if (toIndex >= lastIndex) {
			List<T> subList = list.subList(fromIndex, lastIndex);
			subList.add(list.get(lastIndex));
			return subList;
		}
		return list.subList(fromIndex, toIndex);
	}

}
