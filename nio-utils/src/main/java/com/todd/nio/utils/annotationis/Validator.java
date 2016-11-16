package com.todd.nio.utils.annotationis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.todd.nio.utils.message.MsgContext;
import com.todd.nio.utils.message.codeinfo.MsgCode;
import com.todd.nio.utils.property.StringUtil;

public class Validator {
	public static MsgContext valiDate(MsgContext in,Class<?> clazz) {
		MsgContext out = new MsgContext(in);
		try {
			Object obj = clazz.newInstance();
			Method m = obj.getClass().getMethod("fromMap", new Class[] { Map.class });
			m.invoke(obj, new Object[] { in.paras() } );
			List<FieldError> errors = Validator.valiDate(obj);
			if(errors.size()>0){
				out.setCode(MsgCode.ERROR_4);
				out.setMsg(Errors.toStr(errors));
				out.setParas(null);
				out.setResult(null);
				return out;
			}
			out.setCode(MsgCode.SUCCESS);
		} catch (Exception e) {
			out.setCode(MsgCode.ERROR_0);
			out.setMsg("内部错误");
			out.setParas(null);
			out.setResult(null);
		}
		return out;
		
	}
	
	/**
	 * 验证注解字段是否为空，如果该字段为手机号，则验证手机号是否为移动手机号
	 * @param obj
	 * @return
	 */
	public static List<FieldError> valiDate(Object obj){
		List<FieldError> errors=new ArrayList<FieldError>();
		Class<?> clzz=obj.getClass();
		Field[] fields=clzz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations=field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation instanceof Required){
					Required myAnnotation=(Required) annotation;
					try {
						field.setAccessible(true);
						Object value=field.get(obj);
						if(StringUtil.isNotEmpty(value))
							errors.add(new FieldError("CM100011", field.getName()+":"+myAnnotation.value()));
						/*else{
							if(isValiMobile(field.getName())){
								if(!StringUtil.isMobile(value.toString()))
									errors.add(new FieldError("CM100012", "非移动手机号"));
							}
						}*/
						field.setAccessible(false);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return errors;
	}
	
	/*private static boolean isValiMobile(String filedName){
		return filedName.equals("MSISDN")
				||filedName.equals("toUserMSISDN")
				||filedName.equals("receiveNo")
				||filedName.equals("reqUserMSISDN")
				||filedName.equals("mobile")?true:false;
	}*/
}
