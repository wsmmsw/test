package com.baicheng.fork.core.util.math;

/**
 * @author SongPengpeng
 * @date 2017/9/5.
 */
public class MathUtil {

	/**
	 * @param ins
	 * @return
	 */
	public static int MaxInt(int[] ins) {
		int max = ins[0];
		for (int j = 0; j < ins.length; j++) {
			if (max < ins[j]) {
				max = ins[j];
			}
		}
		return max;
	}

	/**
	 * @param ins
	 * @return
	 */
	public static int MinInt(int[] ins) {
		int min = ins[0];
		for (int j = 0; j < ins.length; j++) {
			if (min > ins[j]) {
				min = ins[j];
			}
		}
		return min;
	}

	/**
	 * @param ins
	 * @return
	 */
	public static long MaxLong(long[] ins) {
		long max = ins[0];
		for (int j = 0; j < ins.length; j++) {
			if (max < ins[j]) {
				max = ins[j];
			}
		}
		return max;
	}

	/**
	 * @param ins
	 * @return
	 */
	public static long MinLong(long[] ins) {
		long min = ins[0];
		for (int j = 0; j < ins.length; j++) {
			if (min > ins[j]) {
				min = ins[j];
			}
		}
		return min;
	}

}
