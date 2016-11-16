package com.todd.nio.utils.invokes;

import java.sql.SQLException;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import com.todd.nio.utils.message.MsgContext;
import com.todd.nio.utils.message.codeinfo.MsgCode;

public class ExceptionInvokes {
	public static void invokes(MsgContext out, Exception ex) {
		if (ex instanceof NoSuchMethodException || ex instanceof ClassNotFoundException
			|| ex instanceof NoSuchBeanDefinitionException) {
			out.setCode(MsgCode.ERROR_4);
			out.setMsg("没有此权限.");
		} else {
			Throwable e = ex.getCause();
			if (e instanceof SQLException) {
				out.setCode(MsgCode.ERROR_2);
				out.setMsg("内部数据库解析出错.");
			} else if (e instanceof RuntimeException) {
				out.setCode(MsgCode.ERROR_4);
				out.setMsg("内部错误.");
			} else {
				out.setCode(MsgCode.ERROR_4);
				out.setMsg("未知错误");
			}
		}
	}
}
