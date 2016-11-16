package com.todd.nio.utils.state;

import java.io.Serializable;

/**
 * 通用状态类
 *                       
 * @Filename SimpleState.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年1月21日</li>
 *
 */
public class SimpleServerState implements Serializable {
	
	/** Comment for <code>serialVersionUID</code> */
	private static final long	serialVersionUID	= -5463890786527506043L;
	
	public SimpleServerState() {
	}
	public SimpleServerState(String account,String key) {
		this.account=account;
		this.key=key;
	}
	
	private String	account;//账号
	private String	key;//密匙
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
}
