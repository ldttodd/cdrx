package com.todd.nio.utils.message;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import com.todd.nio.utils.security.file.FileStringCoder;

import sun.misc.BASE64Encoder;

@SuppressWarnings("serial")
public class Base64StringFile implements Serializable{
	
	public Base64StringFile() {
	}
	
	public Base64StringFile(String name,String fileContentType,byte[] bytes) {
		this.name = name;
		this.fileContentType = fileContentType;
		this.bytes = bytes;
		this.inputStream = new ByteArrayInputStream(bytes);
		this.fileString = new BASE64Encoder().encode(bytes);
	}
	public Base64StringFile(String name,String fileContentType,File file) throws FileNotFoundException {
		this.name = name;
		this.fileContentType = fileContentType;
		this.inputStream = new FileInputStream(file);
		this.bytes = FileStringCoder.file2Byte(file);
		this.fileString = new BASE64Encoder().encode(bytes);
	}
	
	private String name;
	private String fileContentType;
	private byte[] bytes;
	private String fileString;
	private InputStream inputStream;
	/**
	 * 文件复制
	 * @param toFile
	 * @throws IOException 
	 */
	public void transferTo(File toFile) throws IOException{
		FileStringCoder.string2File(fileString, toFile.getPath());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFileString() {
		return fileString;
	}
	public void setFileString(String fileString) {
		this.fileString = fileString;
	}
}
