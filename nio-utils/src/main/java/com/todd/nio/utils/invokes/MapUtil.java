package com.todd.nio.utils.invokes;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.todd.nio.utils.property.DateUtils;
import com.todd.nio.utils.property.NumberUtil;
import com.todd.nio.utils.property.StringUtil;

public class MapUtil {
	public static void fromMap(Object obj, Map<?, ?> map) {
		Field[] fds = obj.getClass().getDeclaredFields();
		for (final Field fd : fds) {
			try {
				String mthname = "set" + fd.getName().toUpperCase().substring(0, 1)
									+ fd.getName().substring(1);
				Method mth = obj.getClass().getMethod(mthname, new Class[] { fd.getType() });
				Object mapv = map.get(fd.getName());
				Object para = fd.getType().equals(Integer.class) ? NumberUtil.toInteger(mapv) : (fd
					.getType().equals(String.class) ? StringUtil.toString(mapv) : (fd.getType()
					.equals(BigDecimal.class) ? NumberUtil.toBigDecimal(mapv) : (fd.getType()
					.equals(int.class) ? Integer.valueOf(mapv.toString()) : ((fd.getType().equals(
					double.class) || fd.getType().equals(Double.class)) ? Double.valueOf(mapv
					.toString()) : ((fd.getType().equals(long.class) || fd.getType().equals(
					Long.class)) ? Long.valueOf(mapv.toString()) : (fd.getType().equals(Timestamp.class))?new Timestamp(DateUtils.getTime(mapv.toString())):StringUtil.toString(mapv))))));
				mth.invoke(obj, new Object[] { para });
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
	
	public static Map<?, ?> toMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fds = obj.getClass().getDeclaredFields();
		for (final Field fd : fds) {
			try {
				String mthname = "get" + fd.getName().toUpperCase().substring(0, 1)
									+ fd.getName().substring(1);
				Method mth = obj.getClass().getMethod(mthname, new Class[] {});
				Object val = mth.invoke(obj, new Object[] {});
				map.put(fd.getName(), val);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		return map;
	}
}
