package com.todd.nio.utils.state;

import com.todd.nio.utils.message.MsgContext;
import com.todd.nio.utils.message.codeinfo.MsgCode;
import com.todd.nio.utils.property.StringUtil;

/**
 * 通用状态类
 *                       
 * @Filename SimpleState.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年1月21日</li>
 *
 */
public class SimpleClientState {
	
	private SimpleClientState() {
	}
	
	/**
	 * 是否登陆
	 */
	private static boolean	isLogin	= false;
	/**
	 * 登陆绑定的密匙串
	 */
	private static String	key		= null;
	/**
	 * 设定登陆
	 * @param ink 服务端指派密匙串
	 */
	public static void logined(String ink) {
		isLogin = true;
		key = ink;
	}
	/**
	 * 是否登陆
	 * @return 
	 * true =已登录<p>
	 * false=未登陆
	 */
	public static boolean isLogin(){
		return isLogin;
	}
	/**
	 * 服务端指派的字符串密匙
	 * @return
	 */
	public static String getKey(){
		return key;
	}
	
	public static boolean isSuccess(MsgContext msg){
		if(!StringUtil.isEmpty(msg.getCode()) && MsgCode.SUCCESS.equals(msg.getCode())){
			return true;
		}else{
			return false;
		}
		
	}
	
}
