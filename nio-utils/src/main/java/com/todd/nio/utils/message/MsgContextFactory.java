package com.todd.nio.utils.message;

import com.todd.nio.utils.message.codeinfo.MsgCode;
/**
 * 数据总线 工厂类
 * 
 *                       
 * @Filename MessageContextFactory.java
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
public class MsgContextFactory {
	
	private MsgContextFactory() {
	}
	/**
	 * 创建简单的MessageContext，复制原有的s和m
	 * @param in
	 * @return
	 */
	public static MsgContext createSimpleText(MsgContext in){
		MsgContext mc =  new MsgContext();
		mc.setService(in.getService());
		mc.setMethod(in.getMethod());
		return mc;
	}
	public static MsgContext createSimpleText(MsgContext in,String messagecode ){
		MsgContext mc =  new MsgContext();
		mc.setService(in.getService());
		mc.setMethod(in.getMethod());
		mc.setCode(messagecode);
		return mc;
	}
	
	public static MsgContext createSucText(MsgContext in,String message){
		MsgContext mc =  new MsgContext();
		mc.setService(in.getService());
		mc.setMethod(mc.getMethod());
		mc.setCode(MsgCode.SUCCESS);
		mc.setMsg(message);
		return mc;
	}
	
	public static MsgContext createErrorContext(String msg) {
		MsgContext mc = new MsgContext();
		mc.setCode(MsgCode.ERROR_0);
		mc.setMsg(msg);
		return mc;
	}
	
	public static MsgContext createCustomContext(String code, String msg, String service,
														String method, MsgMap paras,
														MsgMap result) {
		MsgContext mc = new MsgContext();
		mc.setCode(code);
		mc.setMsg(msg);
		mc.setService(service);
		mc.setMethod(method);
		mc.setParas(paras);
		mc.setResult(result);
		return mc;
	}
	
	public static MsgContext copy(MsgContext in){
		MsgContext out = new MsgContext();
		out.setMsg(in.getMsg());
		out.setCode(in.getCode());
		out.setService(in.getService());
		out.setFiles(in.getFiles());
		out.setKey(in.getKey());
		out.setParas(in.getParas());
		out.setResult(in.getResult());
		out.setStringFiles(in.getStringFiles());
		return out;
	}
	
}
