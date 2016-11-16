package com.todd.nio.utils.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class UploadFiles extends HashMap<String, LinkedList<MultipartFile>> {
	
	public UploadFiles() {
		super();
	}
	public UploadFiles(Map<? extends String, ? extends LinkedList<MultipartFile>> m) {
		this.putAll(m);
	}
	
	public void addFile(String name,LinkedList<MultipartFile> list){
		put(name, list);
	}
	public void addFile(String name,MultipartFile obj){
		LinkedList<MultipartFile> list = new LinkedList<MultipartFile>();
		list.add(obj);
		this.put(name, list);
	}
	
	public LinkedList<MultipartFile> getByKey(String key){
		return (LinkedList<MultipartFile>)super.get(key);
	}
	/**
	 * 获取第一个数据对象
	 * @param name
	 * @return
	 */
	public MultipartFile getFirst(String name){
		LinkedList<MultipartFile> list =super.get(name);
		return (list!=null && list.size()>0)?list.get(0):null;
	}
	
	
}
