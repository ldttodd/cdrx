package com.todd.nio.utils.property;

import java.util.HashMap;

import com.todd.nio.utils.state.SimpleServerState;
/**
 * 服务端状态单例类
 * <pre>
 * 使用于c/s nio模式
 * </pre>
 *                       
 * @Filename ServerProprty.java
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
public class ServerProprty extends HashMap<Object, Object> {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= -8296026085017952419L;
	
	public static int sizes() {
		return util.size();
	}
	/**
	 * 保存自定义key
	 * @param channel
	 * @param serverState
	 */
	public static void saveKey(Object channel, SimpleServerState serverState) {
		util.put(channel, serverState);
	}
	
	public static void removeKey(Object channel){
		util.remove(channel);
	}
	
	private ServerProprty() {
	}
	
	private static ServerProprty	util	= new ServerProprty();
	
	public static ServerProprty getInstance() {
		return util;
	}
	
}
