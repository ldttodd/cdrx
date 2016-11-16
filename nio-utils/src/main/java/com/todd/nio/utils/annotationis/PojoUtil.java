package com.todd.nio.utils.annotationis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.todd.nio.utils.message.MsgMap;
import com.todd.nio.utils.property.StringUtil;
/**
 *  
 *                       
 * @Filename PojoUtil.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2016年1月16日</li>
 *
 */
public class PojoUtil {
	
	/**
	 * jsonArray 转对象
	 * @param jsonArrayString
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> invoke(String jsonArrayString,Class<T> clazz){
		List<T> list = new ArrayList<T>();
		JSONArray ar = JSONArray.parseArray(jsonArrayString);
		for(Object obj:ar){
			JSONObject jobj = (JSONObject)obj;
			list.add(JSONObject.parseObject(jobj.toJSONString(), clazz));
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Object> T invoke(MsgMap map,Class<T> clazz,String fixBefore){
		try {
			Object obj = clazz.newInstance();
			Field[] fds = obj.getClass().getDeclaredFields();
			for(Field fd:fds){
				try {
						
						String key=StringUtil.isNotEmpty(fixBefore)?fd.getName():(fixBefore+"."+fd.getName());
						Object fdVal=map.get(key);
						if(fdVal!=null){
							Method setter = obj.getClass().getMethod("set"+(fd.getName().substring(0, 1).toUpperCase())+fd.getName().substring(1), fd.getType());
							setter.invoke(obj, cast(fd.getType(), fdVal));
						}
				} catch (Exception e) {
				}
			}
			return (T)obj;
		}catch (Exception e) {
			return null;
		}
	}
	
	private static Object cast(Class<?> clazz,Object objVal){
		if("java.lang.Integer".equals(clazz.getName())){
			return Integer.valueOf(StringUtil.isNotEmpty(objVal)?"0":(String)objVal);
		}
		if("java.lang.String".equals(clazz.getName())){
			return (String)objVal;
		}
		if("java.math.BigDecimal".equals(clazz.getName())){
			return BigDecimal.valueOf(StringUtil.isNotEmpty(objVal)?new Long(0):Long.valueOf((String)objVal));
		}
		return objVal;
	}
	
}
