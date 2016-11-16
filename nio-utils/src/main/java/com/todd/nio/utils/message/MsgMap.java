package com.todd.nio.utils.message;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.todd.nio.utils.property.StringUtil;
/**
 * 自定义hash map，用于传递参数或结果集
 *                       
 * @Filename MessageMap.java
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
public class MsgMap extends HashMap<String, Object>{

	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= 4996891491665555233L;
	public MsgMap() {
		super();
	}
	public MsgMap(Map<? extends String, ? extends Object> m) {
		this.putAll(m);
	}
	
	public String getString(String key) {
		Object o = get(key);
		return StringUtil.isEmpty(o)?null:String.valueOf(o);
	}
	
	@Override
	public String toString() {
		try {
			String s = JSONObject.toJSONString(this);
			s = s.replaceAll("\\{", "");
			s = s.replaceAll("}", "");
			s = s.replaceAll("\"", "");
			s = s.replaceAll(",", "-");
			return s;
		} catch (Exception e) {
			return null;
		}
		
	}
	
}
