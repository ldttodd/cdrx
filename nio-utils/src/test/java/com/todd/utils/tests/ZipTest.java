package com.todd.utils.tests;

import java.io.File;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.junit.Test;

import com.todd.nio.utils.security.file.FileEncrypter;
import com.todd.nio.utils.security.file.ZipFileUtils;

public class ZipTest {
	@Test
	public void run() throws Exception{
	}
	public void run2() throws Exception{
		//数组加密
		String key = "12345678123456781234567812345678";
		System.out.println(key.getBytes());
		String enStr="abcdefg";
		DESKeySpec desKS = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance("DES");
		cip.init(Cipher.ENCRYPT_MODE, sk);
		byte[] enbt = cip.doFinal(enStr.getBytes());
		System.out.println(enbt);
		System.out.println("===加密完成====");
		//解密
		cip.init(Cipher.DECRYPT_MODE, sk);
		byte[] denbt = cip.doFinal(enbt);
		System.out.println(new String(denbt));
	}
	
	public void run1() throws IOException{
		Long start1=System.currentTimeMillis();
		//压缩
		ZipFileUtils z = new ZipFileUtils("F://Temp//tmp//12.zip","gbk");
		z.compress("F:\\Temp\\tmp\\ds\\");
		Long ysend=System.currentTimeMillis();
		System.out.println("压缩耗时"+((ysend-start1)/1000)+" sec");
		//解压
		start1=System.currentTimeMillis();
		ZipFileUtils.unzip("F://Temp//tmp//12.zip", "F://Temp//tmp//12","gbk");
		ysend=System.currentTimeMillis();
		System.out.println("解压缩耗时"+((ysend-start1)/1000)+" sec");
		//加密
		String sKey="123123123";
		FileEncrypter in = new FileEncrypter();
		start1=System.currentTimeMillis();
		in.encrypt(new File("F://Temp//tmp//12.zip"), "F://Temp//tmp//in.zip", sKey, false);
		ysend=System.currentTimeMillis();
		System.out.println("加密耗时"+((ysend-start1)/1000)+" sec");
		
		start1=System.currentTimeMillis();
		in.decrypt(new File("F://Temp//tmp//in.zip"), new File("F://Temp//tmp//un.zip"), sKey, false);
		ysend=System.currentTimeMillis();
		System.out.println("解密耗时"+((ysend-start1)/1000)+" sec");
	}
	
}
