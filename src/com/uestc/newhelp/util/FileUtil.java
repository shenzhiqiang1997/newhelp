package com.uestc.newhelp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {
	public static byte[] getBytes(String pathname) throws IOException {
		byte[] bytes;
		File file=new File(pathname);	
		FileInputStream fileInputStream=new FileInputStream(file);
		bytes=new byte[fileInputStream.available()];
		fileInputStream.read(bytes);
		fileInputStream.close();
		return bytes;
	}
}
