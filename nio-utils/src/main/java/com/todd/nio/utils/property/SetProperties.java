package com.todd.nio.utils.property;

import java.io.IOException;
import java.util.Properties;
/**
 * 服务端/客户端 设置信息
 *                       
 * @Filename SetProperties.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年3月18日</li>
 *
 */
public class SetProperties {
	private static Properties	props	= new Properties();
	
	private SetProperties() {
		
	}
	
	static {
		try { 
			props.load(SetProperties.class.getClassLoader().getResourceAsStream(
				"resources/set.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			try {
				throw new IOException("set.properties文件加载错误。");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static String get(String key){
		return props.getProperty(key,null);
	}
	
	public static int getInt(String key){
		return Integer.valueOf(props.getProperty(key,"0"));
	}
	
	public static Boolean getBoolean(String key) {
		return Boolean.valueOf(props.getProperty(key));
	}
}
