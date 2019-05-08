package com.tdt.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 文件常用工具类
 * 
 * @author ack
 *
 */
public class FileUtil {

	/**
	 * properties文件处理
	 * <p>
	 * 绝对路径
	 * 
	 * @param path
	 * @return <code>Properties</code>
	 */
	public static Properties getProperAddr(String path) {
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(path);
			p = getProperAddr(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}

	/**
	 * properties文件处理
	 * <p>
	 * jar包或者classPath
	 * 
	 * @param path
	 * @return
	 */
	public static Properties getLocalProperAddr(String path) {
		InputStream inputStream = FileUtil.class.getClassLoader()
				.getResourceAsStream(path);
		Properties p = getProperAddr(inputStream);
		return p;
	}

	/**
	 * properties文件处理
	 * 
	 * @param in
	 * @return
	 */
	public static Properties getProperAddr(File  file) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	/**
	 * properties文件处理
	 * 
	 * @param in
	 * @return
	 */
	public static Properties getProperAddr(InputStream in) {
		Properties p = new Properties();
		try {
			p.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p;
	}
	
	public static void writeProperties(File file, String keyname,String keyvalue) {
        try {
        	Properties p = getProperAddr(file);
            OutputStream fos = new FileOutputStream(file);
            p.setProperty(keyname, keyvalue);
            p.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }
	
	public static void writeProperties(String file, String keyname,String keyvalue) {
		writeProperties(new File(file), keyname, keyvalue);
	}
	
	/**
	 * 创建新文件
	 * 
	 * @param outputPath
	 * @return
	 */
	public static File createFile(String outputPath) {
		return createFile(outputPath, true);
	}

	/**
	 * @param outputPath
	 *            文件绝对路径
	 * @param b
	 *            true 不在意是否文件已存在 ; false 文件已存在返回null
	 * @return
	 */
	public static File createFile(String outputPath, boolean b) {
		int index = outputPath.lastIndexOf(File.separator);
		File file = null;
		if(-1 == index){
			file = new File(".");
		} else {
			String dir = outputPath.substring(0, index);
			file = new File(dir);
			if (!file.exists()) {
				file.mkdirs();// create dir
			}
		}
		String realName = outputPath.substring(index + 1);
		File realFile = new File(file, realName);
		if (realFile.exists()) {
			if (b) {
				return realFile;
			}
			return null;
		}
		try {
			realFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return realFile;
	}
}
