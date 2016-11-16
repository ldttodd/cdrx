package com.todd.nio.utils.security.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.todd.nio.utils.property.StringUtil;

/**
 * 压缩文件工具类
 *                       
 * @Filename ZipFileUtils.java
 * @Description 
 * @Version 1.0
 * @Author Todd
 * @Email lidetao0@163.com
 * 
 * @History
 *<li>Author: Todd</li>
 *<li>Date: 2015年12月8日</li>
 *
 */
public class ZipFileUtils {
	
	static final int	BUFFER	= 8192;
	
	private File		zipFile;
	private String		encoding;
	
	/**
	 * 
	 * 构建一个<code>ZipFileUtils.java</code>
	 * @param pathName zip File full path
	 * @param encoding
	 */
	public ZipFileUtils(String pathName, String encoding) {
		zipFile = new File(pathName);
		this.encoding = encoding;
	}
	
	public void compress(String srcPathName) {
		File file = new File(srcPathName);
		if (!file.exists())
			throw new RuntimeException(srcPathName + "不存在！");
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
			CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
			ZipOutputStream out = new ZipOutputStream(cos);
			if (!StringUtil.isNotEmpty(encoding)) {
				out.setEncoding(encoding);
			}
			String basedir = "";
			compress(file, out, basedir);
			out.flush();
			out.close();
			fileOutputStream.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			this.compressDirectory(file, out, basedir);
		} else {
			this.compressFile(file, out, basedir);
		}
	}
	
	/** 压缩一个目录 */
	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;
		
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}
	
	/** 压缩一个文件 */
	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			out.flush();
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 
	 * @param archive
	 * @param decompressDir
	 * @param encoding
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void unzip(String archive, String decompressDir, String encoding)
																					throws IOException {
		BufferedInputStream bi;
		ZipFile zf = null;
		if (StringUtil.isNotEmpty(encoding)) {
			zf = new ZipFile(archive);//不支持中文   
		} else {
			zf = new ZipFile(archive, encoding);//支持中文   
		}
		
		Enumeration e = zf.getEntries();
		while (e.hasMoreElements()) {
			ZipEntry ze2 = (ZipEntry) e.nextElement();
			String entryName = ze2.getName();
			String path = decompressDir + "/" + entryName;
			if (ze2.isDirectory()) {
				File decompressDirFile = new File(path);
				if (!decompressDirFile.exists()) {
					decompressDirFile.mkdirs();
				}
			} else {
				String fileDir = path.substring(0, path.lastIndexOf("/"));
				File fileDirFile = new File(fileDir);
				if (!fileDirFile.exists()) {
					fileDirFile.mkdirs();
				}
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
					decompressDir + "/" + entryName));
				
				bi = new BufferedInputStream(zf.getInputStream(ze2));
				byte[] readContent = new byte[1024];
				int readCount = bi.read(readContent);
				while (readCount != -1) {
					bos.write(readContent, 0, readCount);
					readCount = bi.read(readContent);
					bos.flush();
				}
				bi.close();
				bos.close();
			}
		}
		zf.close();
	}
}
