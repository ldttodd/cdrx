package com.todd.nio.utils.message.codeinfo;

public final class MsgCode {
	/**
	 * 服务器server端返回错误
	 */
	public static final String	ERROR_0	= "000000";
	/**
	 * 服务组件内部错误
	 */
	public static final String	ERROR_1	= "000001";
	/**
	 * 服务组件数据库错误
	 */
	public static final String	ERROR_2	= "000002";
	/**
	 * 服务组件未知错误
	 */
	public static final String	ERROR_3	= "000003";
	
	/**
	 * <p>service主动抛出业务错误，<p>
	 * 例如登陆账号密码不匹配<p>
	 */
	public static final String	ERROR_4	= "000004";
	/**
	 * 服务器返回成功
	 */
	public static final String	SUCCESS	= "100000";
	
}
