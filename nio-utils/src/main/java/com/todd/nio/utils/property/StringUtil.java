package com.todd.nio.utils.property;

import java.util.UUID;

import com.todd.nio.utils.message.MsgContext;

/**
 * 字符串工具类
 *                       
 * @Filename StringUtil.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2014年9月3日</li>
 *
 */
public class StringUtil {
	private StringUtil() {
	}
	
	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param obj
	 *            要检查的字符串/对象
	 * 
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isEmpty(Object obj) {
		return ((obj == null) || (obj.toString().length() == 0));
	}
	
	/**
	 * 检查字符串是否为<code>null</code>或空字符串<code>""</code>。
	 * 
	 * <pre>
	 * StringUtil.isNotEmpty(null)      = true
	 * StringUtil.isNotEmpty("")        = true
	 * StringUtil.isNotEmpty(" ")       = true
	 * StringUtil.isNotEmpty("bob")     = false
	 * StringUtil.isNotEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param obj
	 *            要检查的字符串/对象
	 * 
	 * @return 如果为空, 则返回<code>true</code>
	 */
	public static boolean isNotEmpty(Object obj) {
		return ((obj == null) || (obj.toString().length() == 0) || obj.toString()
			.replaceAll(" ", "").length() == 0);
	}
	
	public static String toString(Object obj) {
		return isNotEmpty(obj)?"":String.valueOf(obj);
	}
	
	public static String getOnlyStr() {
		return UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
	}
	public static String getUUID() {
		return UUID.randomUUID().toString().toLowerCase();
	}
	
	public static boolean isBZ(MsgContext inmsg) {
		return inmsg.getMethod().startsWith("bz");
	}
	
	public static String redisKey(MsgContext in) {
		StringBuffer buf = new StringBuffer();
		buf.append(in.getService()).append("_").append(in.getMethod()).append(".")
			.append(in.getParas().toString());
		return buf.toString();
	}
	/**
	 * 验证字符是否属于手机号
	 * @param mobile
	 * @return true / false
	 */
	public static boolean isMobile(String mobile){
		String metc="^(13|15|18|17)\\d{9}$";
		return mobile.matches(metc);
	}
	
	public static String randomStr(int length){
		String s= UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
		if(s.length()<length){
			return s;
		}
		s = s.substring(0,length);
		return s;
	}
	/**
	 * 模糊隐藏身份证号码
	 * @param idc
	 * @return
	 */
	public static String bluIdCard(String idc){
		idc=StringUtil.isEmpty(idc)?"":idc.substring(0,4)+"**********"+idc.substring(idc.length()-4);
		return idc;
	}
	/**
	 * 验证是否数据18位省份证号码
	 * @param idcard
	 * @return
	 */
	public static boolean iSIdCardNumber(String idcard){
		String metc="^[1-9][0-9]{5}[1-9][0-9]{3}((0[0-9])|(1[0-2]))(([0|1|2][0-9])|3[0-1])[0-9]{3}([0-9]|X|x)$";
		return idcard.matches(metc);
	}
}
