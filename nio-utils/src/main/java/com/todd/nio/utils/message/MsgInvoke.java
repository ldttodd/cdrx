package com.todd.nio.utils.message;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todd.nio.utils.message.MsgContext;
import com.todd.nio.utils.message.MsgMap;

public final class MsgInvoke {
	private MsgInvoke(){
		
	}
	@SuppressWarnings("rawtypes")
	public static MsgContext invoke(HttpServletRequest req){
		MsgContext msg = new MsgContext();
		MsgMap paras = new MsgMap();
		Map<?, ?> m = req.getParameterMap();
		Iterator<?> entries = m.entrySet().iterator();
		Map.Entry entry; 
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			String[] arrs = (String[])entry.getValue();
			paras.put((String) entry.getKey(), arrs.length>1?arrs:arrs[0]);
		}
		msg.setParas(paras);
		msg.setService(paras.getString("s"));
		msg.setMethod(paras.getString("m"));
		msg.setRequest(req);
		return msg;
	}
	
	@SuppressWarnings("rawtypes")
	public static MsgContext invoke(HttpServletRequest req,HttpServletResponse res){
		MsgContext msg = new MsgContext();
		MsgMap paras = new MsgMap();
		Map<?, ?> m = req.getParameterMap();
		Iterator<?> entries = m.entrySet().iterator();
		Map.Entry entry; 
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			String[] arrs = (String[])entry.getValue();
			paras.put((String) entry.getKey(), arrs.length>1?arrs:arrs[0]);
		}
		msg.setParas(paras);
		msg.setService(paras.getString("s"));
		msg.setMethod(paras.getString("m"));
		msg.setRequest(req);
		msg.setResponse(res);
		return msg;
	}
	@SuppressWarnings("rawtypes")
	public static MsgContext invoke(HttpServletRequest req,HttpServletResponse res,boolean boo){
		MsgContext msg = new MsgContext();
		MsgMap paras = new MsgMap();
		Map<?, ?> m = req.getParameterMap();
		Iterator<?> entries = m.entrySet().iterator();
		Map.Entry entry; 
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			String[] arrs = (String[])entry.getValue();
			paras.put((String) entry.getKey(), arrs.length>1?arrs:arrs[0]);
		}
		msg.setParas(paras);
		msg.setService(paras.getString("s"));
		msg.setMethod(paras.getString("m"));
		if(boo){
			msg.setRequest(req);
			msg.setResponse(res);
		}
		return msg;
	}
}
