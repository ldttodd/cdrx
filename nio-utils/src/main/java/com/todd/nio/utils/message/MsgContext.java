package com.todd.nio.utils.message;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.todd.nio.utils.annotationis.Required;
import com.todd.nio.utils.property.StringUtil;
import com.todd.nio.utils.state.SimpleClientState;

/**
 * nio传递的主体对象
 *                       
 * @Filename MessageContext.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年1月22日</li>
 *
 */
@SuppressWarnings("serial")
public class MsgContext implements Serializable {
	
	//服务代码
	private String				code;
	//通知消息
	private String				msg;
	//请求服务名称
	@Required
	private String				service;
	//请求服务方法
	@Required
	private String				method;
	//通讯token，只用于C/S
	private String				key		= SimpleClientState.getKey();
	//参数 hash
	private MsgMap				paras	= new MsgMap();
	//结果 hash
	private MsgMap				result	= new MsgMap();
	//分发页面名称 ex: view/index = ..../view/index.jsp
	private String				page;
	
	private UploadFiles			files;
	
	private StringUploadFiles StringFiles;
	
	private HttpServletRequest	request;
	private HttpServletResponse	response;
	
	public void setResult(MsgMap result) {
		this.result = result;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * 根据别名获取结果集中的某个值
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getResultByName(String name) {
		return result == null ? null : (T) (result.get(name));
	}
	
	/**
	 * 获取参数值
	 * @param name
	 * @return
	 */
	public String getPara(String name) {
		return paras == null ? null : (StringUtil.isEmpty(paras.get(name)) ? null : paras.get(name)
			.toString());
	}
	/**
	 * 获取泛型
	 * @param name
	 * @return
	 */
	public String[] getParaArray(String name) {
		return paras == null ? null : (StringUtil.isEmpty(paras.get(name)) ? null : (String[])paras.get(name));
	}
	
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
	
	public MsgContext() {
	}
	
	public MsgContext(MsgContext in) {
		this.service = in.getService();
		this.method = in.getMethod();
	}
	
	public MsgContext(String service, String method) {
		this.service = service;
		this.method = method;
	}
	
	/**
	 * <p>
	 * 错误代码</br>
	 * 参考com.todd.nio.utils.message.codeinfo.MessageCode
	 * </p>
	 * @return
	 */
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 服务bean name
	 * @return
	 */
	public String getService() {
		return service;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	/**
	 * 服务方法name
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * 获取参数队列
	 * @return
	 */
	public MsgMap getParas() {
		return paras == null ? new MsgMap() : paras;
	}
	
	public MsgMap paras() {
		return paras == null ? new MsgMap() : paras;
	}
	
	public void setParas(MsgMap paras) {
		this.paras = paras;
	}
	
	/**
	 * 获取结果集队列
	 * @return
	 */
	public MsgMap getResult() {
		return result == null ? new MsgMap() : result;
		
	}
	
	public void setResult(Map<String, Object> result) {
		this.result = new MsgMap(result);
	}
	
	public void addResult(String key, Object value) {
		if (result == null) {
			result = new MsgMap();
		}
		result.put(key, value);
	}
	
	public void addPara(String key, Object value) {
		if (paras == null) {
			paras = new MsgMap();
		}
		paras.put(key, value);
	}
	
	/**
	 * <p>
	 * 该参数只使用与C/S结构,为服务端和客户端的签名密匙串</br>
	 * service jar包中不判断该值的存在与否,请前置拦截校验
	 * </p>
	 * @return
	 */
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public UploadFiles getFiles() {
		return files;
	}
	
	public void setFiles(UploadFiles files) {
		this.files = files;
	}
	
	public String getPage() {
		
		return (!StringUtil.isNotEmpty(page)) ? page : ((!StringUtil
			.isNotEmpty(getResultByName("page"))) ? (String) getResultByName("page") : null);
	}
	
	public void setPage(String page) {
		addResult("page", page);
		this.page = page;
	}

	public StringUploadFiles getStringFiles() {
		return StringFiles;
	}

	public void setStringFiles(StringUploadFiles stringFiles) {
		StringFiles = stringFiles;
	}

	
}
