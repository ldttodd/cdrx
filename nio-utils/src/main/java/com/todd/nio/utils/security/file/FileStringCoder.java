package com.todd.nio.utils.security.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileStringCoder {
	
	/** 
     * 文本文件转换为指定编码的字符串 
     * 
     * @param file         文本文件 
     * @param encoding 编码类型 
     * @return 转换后的字符串 
	 * @throws IOException 
     */ 
	public static String file2String(File file) throws IOException { 
    	InputStream in = null;
    	byte[] data = null;
    	// 读取图片字节数组
    	in = new FileInputStream(file);
    	data = new byte[in.available()];
    	in.read(data);
    	in.close();
    	// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
    	return encoder.encode(data).trim();// 返回Base64编码过的字节数组字符串
    }
	/** 
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！) 
     * 
     * @param res            原字符串 
     * @param filePath 文件路径 
     * @return 成功标记 
	 * @throws IOException 
     */ 
	public static boolean string2File(String res, String filePath) throws IOException {
    	BASE64Decoder de = new BASE64Decoder();
    	byte[] bt =de.decodeBuffer(res); 
    	FileOutputStream out = new FileOutputStream(filePath);
    	out.write(bt);
    	out.flush();
    	out.close();
    	return true;
    }
    
    public static byte[] file2Byte(File file){
    	byte[] buffer = null;  
        try {  
            FileInputStream fis = new FileInputStream(file);  
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);  
            byte[] b = new byte[1024];  
            int n;  
            while ((n = fis.read(b)) != -1) {  
                bos.write(b, 0, n);  
            }  
            fis.close();  
            bos.close();  
            buffer = bos.toByteArray();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return buffer; 
    }
}
