package com.uestc.newhelp.util;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;


public class POIUtil {
	//单元格全部用文本格式处理
	public static String getStringCellValue(Row row,int index) throws IllegalStateException{
		//获取到某行指定位置的单元格
		Cell cell=row.getCell(index);
		//如果该单元格为空 则默认返回无
		if(cell==null) {
			return "无";
		}
		//将单元格上的数据以字符串的形式读入
		return row.getCell(index).getStringCellValue().trim();
	}
	
	//map里每个键值对放的是行号和一行的数据,templatePath是写入的模板
	public static byte[] getExcelBytes(Map<Integer, Object[]> rowMap,String templatePath) throws IOException {
		File file=new File(templatePath);
		//获取工作簿
		XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(file));
		//只向第一个sheet写入
		Sheet sheet=workbook.getSheetAt(0);
		//用于格式化日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//略过表头,从第二行开始
		for (int i = 1; i <=rowMap.size(); i++) {
			Row row=sheet.createRow(i);
			Object[] rowValues=rowMap.get(i);
			//将该行数据写入
			for (int j = 0; j < rowValues.length; j++) {
				Cell cell=row.createCell(j);
				cell.setCellType(CellType.STRING);
				if(rowValues[j] instanceof Date) {
					String date=sdf.format(rowValues[j]);
					cell.setCellValue(date);
				}else {
					cell.setCellValue(String.valueOf(rowValues[j]));
				}
				
			}
		}
		//获得字节数组输出流,方便获得二进制文件数组
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		//将excel文件以二进制的形式写入输出流
		workbook.write(byteArrayOutputStream);
		workbook.close();
		//将字节数组返回
		return byteArrayOutputStream.toByteArray();
	}
	
	//将两个docx文件合并
	public static void mergeDocx(XWPFDocument srcDocument,XWPFDocument appendDocument) throws Exception{
		//获取源和追加docx的CTBody
		CTBody srcBody=srcDocument.getDocument().getBody();
		CTBody appendBody=appendDocument.getDocument().getBody();
		//获取两个CTBody的xml字符串
		String srcString=srcBody.xmlText();
		String appendString=appendBody.xmlText();
		//获取源xml字符串的头标签 主体 和尾标签
		String prefix=srcString.substring(0,srcString.indexOf(">")+1);
		String mainPart=srcString.substring(srcString.indexOf(">")+1,srcString.lastIndexOf("<"));
		String suffix=srcString.substring(srcString.lastIndexOf("<"));
		String append=appendString.substring(appendString.indexOf(">")+1,appendString.lastIndexOf("<"));
		//将源docx里的xml头和主体后拼接追加docx的主体再拼接源docx的尾标签
		CTBody newBody=CTBody.Factory.parse(prefix+mainPart+append+suffix);
		srcBody.set(newBody);
	}
}
