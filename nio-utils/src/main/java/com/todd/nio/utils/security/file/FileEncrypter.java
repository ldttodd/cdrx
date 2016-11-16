package com.todd.nio.utils.security.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.todd.nio.utils.security.MD5;

/**
 * 文件名：fileencrypter.java jdk：1.40以上 说明：文件加密 加密方法：三重des加密
 *                       
 * @Filename Fileencrypter.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年12月11日</li>
 *
 */
public class FileEncrypter {
	public FileEncrypter() {
	}
	
	private String get128Key(String key) {
		String p1 = MD5.toMd5(key);
		String p2 = MD5.toMd5(key + p1);
		String p3 = MD5.toMd5(key + p1 + p2);
		String p4 = MD5.toMd5(key + p1 + p2 + p3);
		return p1.concat(p2).concat(p3).concat(p4);
	}
	static final int BUFFER = 1024*5;  
	/**
	 * 文件流加密
	 * @param fileIn 源文件
	 * @param sKey 加密key
	 * @param fileOut 加密文件储存位置
	 * @param deleteSource 是否删除源文件
	 */
	@SuppressWarnings("unused")
	public synchronized void encrypt(File fileIn, String fileOut, String sKey, boolean deleteSource) {
		try {
			sKey = get128Key(sKey);
			byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
			byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
			byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));
			
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fileIn));
			int count;
			byte data[] = new byte[BUFFER];  
			byte[] bytOut;
			FileOutputStream fos = new FileOutputStream(fileOut);
			while ((count = fis.read(data, 0, BUFFER)) != -1) {  
				//加密
				bytOut = encryptByDES(encryptByDES(encryptByDES(data, bytK1), bytK2), bytK3);
                for (int i = 0; i < data.length; i++) {
    				fos.write((int) bytOut[i]);
    			}
            }  
			
			fis.close();
			fos.close();
			if (deleteSource) {
				fileIn.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param fileIn 源文件
	 * @param fileOut 输出文件
	 * @param sKey 秘钥
	 * @param deleteSource 是否删除源文件
	 */
	@SuppressWarnings("unused")
	public void decrypt(File fileIn, File fileOut, String sKey, boolean deleteSource) {
		try {
			sKey = get128Key(sKey);
			byte[] bytK1 = getKeyByStr(sKey.substring(0, 16));
			byte[] bytK2 = getKeyByStr(sKey.substring(16, 32));
			byte[] bytK3 = getKeyByStr(sKey.substring(32, 48));
			
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fileIn));
			int count;
			byte data[] = new byte[BUFFER];  
			byte[] bytOut;
			
			FileOutputStream fos = new FileOutputStream(fileOut);
			while ((count = fis.read(data, 0, BUFFER)) != -1) {  
				//加密
				bytOut = decryptByDES(decryptByDES(decryptByDES(data, bytK3), bytK2), bytK1);
				fos.write(bytOut);
				fos.flush();
//                for (int i = 0; i < data.length; i++) {
//    				fos.write((int) bytOut[i]);
//    			}
            }
			fis.close();
			fos.close();
			if (deleteSource) {
				fileIn.deleteOnExit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("解密错误！");
		}
	}
	
	/**
	 * 用DES方法加密输入的字节 bytKey需为8字节长，是加密的密码
	 */
	private byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance("DES/ECB/NoPadding");
		cip.init(Cipher.ENCRYPT_MODE, sk);
		return cip.doFinal(bytP);
	}
	
	/**
	 * 用DES方法解密输入的字节 bytKey需为8字节长，是解密的密码
	 */
	private byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {
		DESKeySpec desKS = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance("DES/ECB/NoPadding");
		cip.init(Cipher.DECRYPT_MODE, sk);
		return cip.doFinal(bytE);
	}
	
	/**
	 * 输入密码的字符形式，返回字节数组形式。 如输入字符串：AD67EA2F3BE6E5AD
	 * 返回字节数组：{173,103,234,47,59,230,229,173}
	 */
	private byte[] getKeyByStr(String str) {
		byte[] bRet = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			Integer itg = new Integer(16 * getChrInt(str.charAt(2 * i))
										+ getChrInt(str.charAt(2 * i + 1)));
			bRet[i] = itg.byteValue();
		}
		return bRet;
	}
	
	/**
	 * 计算一个16进制字符的10进制值 输入：0-F
	 */
	private int getChrInt(char chr) {
		int iRet = 0;
		if (chr == "0".charAt(0))
			iRet = 0;
		if (chr == "1".charAt(0))
			iRet = 1;
		if (chr == "2".charAt(0))
			iRet = 2;
		if (chr == "3".charAt(0))
			iRet = 3;
		if (chr == "4".charAt(0))
			iRet = 4;
		if (chr == "5".charAt(0))
			iRet = 5;
		if (chr == "6".charAt(0))
			iRet = 6;
		if (chr == "7".charAt(0))
			iRet = 7;
		if (chr == "8".charAt(0))
			iRet = 8;
		if (chr == "9".charAt(0))
			iRet = 9;
		if (chr == "A".charAt(0))
			iRet = 10;
		if (chr == "B".charAt(0))
			iRet = 11;
		if (chr == "C".charAt(0))
			iRet = 12;
		if (chr == "D".charAt(0))
			iRet = 13;
		if (chr == "E".charAt(0))
			iRet = 14;
		if (chr == "F".charAt(0))
			iRet = 15;
		return iRet;
	}
	
}
