package com.todd.nio.utils.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("serial")
public class StringUploadFiles extends HashMap<String, LinkedList<Base64StringFile>> {
	
	public StringUploadFiles() {
		super();
	}
	public StringUploadFiles(Map<? extends String, ? extends LinkedList<Base64StringFile>> m) {
		this.putAll(m);
	}
	
	public void addFile(String name,LinkedList<Base64StringFile> list){
		put(name, list);
	}
	public void addFile(String name,Base64StringFile obj){
		LinkedList<Base64StringFile> list = new LinkedList<Base64StringFile>();
		list.add(obj);
		this.put(name, list);
	}
	
	public LinkedList<Base64StringFile> getByKey(String key){
		return (LinkedList<Base64StringFile>)super.get(key);
	}
	/**
	 * 获取第一个数据对象
	 * @param name
	 * @return
	 */
	public Base64StringFile getFirst(String name){
		LinkedList<Base64StringFile> list =super.get(name);
		return (list!=null && list.size()>0)?list.get(0):null;
	}
	
	public boolean isEmpty(){
		return this.equals(null)?true:this.size()<1;
	}
	
}
