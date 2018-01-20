package com.uestc.newhelp.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlOptions;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

import com.uestc.newhelp.entity.ArchiveStudent;

public class mergeWordTest {
	@Test
	public void merge() throws Exception {
		InputStream in1 = null;
		InputStream in2 = null;
		OPCPackage src1Package = null;
		OPCPackage src2Package = null;

		OutputStream dest = new FileOutputStream("d:\\dest.docx");
		try {
		in1 = new FileInputStream("学生基本信息.docx");
		in2 = new FileInputStream("周联系简易记录表.docx");
		src1Package = OPCPackage.open(in1);
		src2Package = OPCPackage.open(in2);
		} catch (Exception e) {
		e.printStackTrace();	
		}

		XWPFDocument src1Document = new XWPFDocument(src1Package); 
		CTBody src1Body = src1Document.getDocument().getBody();
		XWPFDocument src2Document = new XWPFDocument(src2Package);
		CTBody src2Body = src2Document.getDocument().getBody(); 
		appendBody(src1Body, src2Body);
		src1Document.write(dest);
		
		src1Document.close();
		src2Document.close();
		}

		private static void appendBody(CTBody src, CTBody append) throws Exception {
		XmlOptions optionsOuter = new XmlOptions();
		optionsOuter.setSaveOuter();
		String appendString = append.xmlText();
		String srcString = src.xmlText();
		String prefix = srcString.substring(0,srcString.indexOf(">")+1);
		String mainPart = srcString.substring(srcString.indexOf(">")+1,srcString.lastIndexOf("<"));
		String sufix = srcString.substring( srcString.lastIndexOf("<") );
		String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
		CTBody makeBody = CTBody.Factory.parse(prefix+mainPart+addPart+sufix);
		src.set(makeBody);
	}
	
	@Test
	public void test() throws Exception {
		ArchiveStudent archiveStudent=new ArchiveStudent();
		archiveStudent.setArchiveId(1l);
		archiveStudent.setStudentId(1l);
				//从磁盘读取模板文件
				FileInputStream inputStream=new FileInputStream("学生基本信息.docx");
				
				//打开学生帮扶记录薄封面docx 获取读写权限
				OPCPackage opcPackage = OPCPackage.open(inputStream);
				
				//通过opcPackage来创建XWPFDocument工作文档
				XWPFDocument document=new XWPFDocument(opcPackage);
				
				//获取所有表格
				List<XWPFTable> tables=document.getTables();
				
				//遍历所有表格
				for (XWPFTable table : tables) {
					//获取表格的每一行
					List<XWPFTableRow> rows=table.getRows();
					
					//遍历所有行
					for (XWPFTableRow row : rows) {
						//获取每行的每个格子
						List<XWPFTableCell> cells=row.getTableCells();
						//遍历每个格子
						for (int i = 0; i < cells.size(); i++) {
							XWPFTableCell cell=cells.get(i);
							//获取该格子的字符串
							String cellString=cell.getText();
							System.out.println(cellString);
							
						}
					}
				}
				document.close();
	}
}
