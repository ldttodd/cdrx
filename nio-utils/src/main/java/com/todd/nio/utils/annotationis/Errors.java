package com.todd.nio.utils.annotationis;

import java.util.List;

public class Errors {
	
	public static String toStr(List<FieldError> errors){
		StringBuffer buf = new StringBuffer();
		for(FieldError f : errors){
			buf.append(f.getDescription()+"; ");
		}
		return buf.toString();
	}
}
