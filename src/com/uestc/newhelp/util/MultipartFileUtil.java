package com.uestc.newhelp.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtil {
	public static String getType(MultipartFile multipartFile) {
		String originalFileName=multipartFile.getOriginalFilename();
		String type=originalFileName.substring(originalFileName.lastIndexOf("."));
		return type;
	}
	public static String storeMultipartFile(MultipartFile multipartFile,String parent,String child) throws IOException {
		File file=new File(parent, child);
		FileUtils.copyToFile(multipartFile.getInputStream(), file);
		return file.getPath();
	}
}
