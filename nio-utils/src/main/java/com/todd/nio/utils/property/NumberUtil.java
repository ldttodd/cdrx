package com.todd.nio.utils.property;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class NumberUtil {
	/**
	 * @param obj
	 * @return
	 */
	public static Integer toInteger(Object obj) {
		try {
			if (!StringUtil.isNotEmpty(obj)) {
				return Integer.valueOf(obj.toString());
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	public static BigDecimal toBigDecimal(Object obj) {
		try {
			if (!StringUtil.isNotEmpty(obj)) {
				BigDecimal b = new BigDecimal(obj.toString());
				return new BigDecimal(new DecimalFormat("0.000").format(b)).setScale(2,BigDecimal.ROUND_DOWN);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 生成六位随机数
	 * @return
	 */
	public static String randomSix() {
		final int max = 999999, min = 100000;
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		int s = tmp % (max - min + 1) + min;
		return String.valueOf(s);
	}
	/**
	 * 精度计算：乘 X
	 * @param b1 因数1
	 * @param b2 因数2
	 * @return
	 */
	public static BigDecimal multiply(BigDecimal b1,BigDecimal b2){
		return toBigDecimal(new DecimalFormat("0.00000").format(b1.multiply(b2).setScale(2,BigDecimal.ROUND_DOWN)));
	}
	/**
	 * 减法精度运算，保留到小数点后两位
	 * @param b1 被减数
	 * @param b2 减数
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal b1,BigDecimal b2){
		return toBigDecimal(b1.subtract(b2));
	}
	
	/**
	 * 求和
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static BigDecimal add(BigDecimal b1,BigDecimal b2){
		return toBigDecimal(b1.add(b2));
	}
	
	public static BigDecimal formatTwo(BigDecimal b1){
		return toBigDecimal(b1);
	}
	
	public static void main(String[] args) {
//		BigDecimal b1 = toBigDecimal(1);
//		BigDecimal b2 = toBigDecimal(19.8);
//		BigDecimal b3 = toBigDecimal(45.8);
//		BigDecimal b = new BigDecimal(0);
//		System.out.println(b);
//		b = b.add(b1);
//		System.out.println(b);
//		b = b.add(b2);
//		System.out.println(b);
//		b = b.add(b3);
//		System.out.println(b);
		System.out.println(toBigDecimal(66.6));
		
	}
}
