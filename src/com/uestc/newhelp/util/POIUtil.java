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


public class POIUtil {
	//��Ԫ��ȫ�����ı���ʽ����
	public static String getStringCellValue(Row row,int index) throws IllegalStateException{
		//��ȡ��ĳ��ָ��λ�õĵ�Ԫ��
		Cell cell=row.getCell(index);
		//����õ�Ԫ��Ϊ�� ��Ĭ�Ϸ�����
		if(cell==null) {
			return "��";
		}
		//����Ԫ���ϵ��������ַ�������ʽ����
		return row.getCell(index).getStringCellValue();
	}
	
	//map��ÿ����ֵ�Էŵ����кź�һ�е�����,templatePath��д���ģ��
	public static byte[] getExcelBytes(Map<Integer, Object[]> rowMap,String templatePath) throws IOException {
		File file=new File(templatePath);
		//��ȡ������
		XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(file));
		//ֻ���һ��sheetд��
		Sheet sheet=workbook.getSheetAt(0);
		//���ڸ�ʽ������
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//�Թ���ͷ,�ӵڶ��п�ʼ
		for (int i = 1; i <=rowMap.size(); i++) {
			Row row=sheet.createRow(i);
			Object[] rowValues=rowMap.get(i);
			//����������д��
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
		//����ֽ����������,�����ö������ļ�����
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		//��excel�ļ��Զ����Ƶ���ʽд�������
		workbook.write(byteArrayOutputStream);
		workbook.close();
		//���ֽ����鷵��
		return byteArrayOutputStream.toByteArray();
	}
}
