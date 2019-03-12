package com.gb.chrom.utils;

/**
 * <p>
 * @author Summer
 * 
 * Created by 2018年4月23日
 * @since
 */
public class CommonUtils {
	
	/**
	 * 数字转字符，并格式化长度，不够前面补 <code>0</code> 。
	 * 
	 * @param number
	 * @param length
	 * @return
	 */
	public static String formatNumber(Number number, int length) {
		String string = String.valueOf(number);

		if (string.length() == length) {
			return string;
		} else if (string.length() > length) {
			return string.substring(string.length() - length);
		}
		StringBuilder builder = new StringBuilder();
		builder.append("%0").append(length).append("d");
		return new StringBuilder().append(String.format(builder.toString(), number)).toString();
	}

}
