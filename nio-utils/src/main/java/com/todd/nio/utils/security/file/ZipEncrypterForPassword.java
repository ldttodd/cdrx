package com.todd.nio.utils.security.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.todd.nio.utils.property.StringUtil;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
/**
 * 文件夹压缩，且只能压缩文件夹
 *                       
 * @Filename ZipEncrypterForPassword.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年12月14日</li>
 *
 */
public class ZipEncrypterForPassword {
	private String password;
	private String directory;
	private ZipFile zip;
	private File file;
	private String rootName="";
	public ZipEncrypterForPassword(String password,String directory) throws Exception {
		file  = new File(directory);
		if(!file.exists()){
			throw new FileNotFoundException("找不到指定文件夹");
		}else if(!file.isDirectory()){
			throw new IOException("必须为一个文件夹");
		}
		this.directory=directory;
		this.password=password;
		String t = file.getPath();
		this.rootName = t.substring(t.lastIndexOf(File.separator)+1);
	}
	
	public void zip(String zipname) throws Exception{
		zip = new ZipFile(zipname);
		invoke(null, arrayToList(file.listFiles()));
	}
	
	private void invoke(String root,List<File> files) throws ZipException{
		for(File f:files){
			if(f.isDirectory()){
				ZipParameters parameters = new ZipParameters();
				if(!StringUtil.isNotEmpty(root)){
					parameters.setRootFolderInZip(root);
				}
		        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
		        parameters.setEncryptFiles(true);
		        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		        parameters.setPassword(password);
		        ArrayList<File> fs= new ArrayList<File>();
		        fs.add(f);
		        zip.addFiles(fs, parameters);
		        String nextroot = getDirName(f.getPath());
		        invoke(nextroot, arrayToList(f.listFiles()));
			}else{
				ZipParameters parameters = new ZipParameters();
				if(!StringUtil.isNotEmpty(root)){
					parameters.setRootFolderInZip(root);
				}
		        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
		        parameters.setEncryptFiles(true);
		        parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		        parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		        parameters.setPassword(password);
		        ArrayList<File> fs= new ArrayList<File>();
		        fs.add(f);
		        zip.addFiles(fs, parameters);
			}
		}
	}
	private List<File> arrayToList(File[] files ){
		List<File> fs = new ArrayList<File>();
		for(File f: files){
			fs.add(f);
		}
		return fs;
	}
	
	private String getDirName(String fullPath){
		String t = fullPath.substring(fullPath.indexOf(rootName)+rootName.length()+1);
		return t;
	}
	
	
	public static void unZip(String zip,String toPath,String password) throws Exception{
		ZipFile zipFile = new ZipFile(zip);
		zipFile.setPassword(password);
		zipFile.extractAll(toPath);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public ZipFile getZip() {
		return zip;
	}

	public void setZip(ZipFile zip) {
		this.zip = zip;
	}
	
	
}
	