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
		in1 = new FileInputStream("ѧ��������Ϣ.docx");
		in2 = new FileInputStream("����ϵ���׼�¼��.docx");
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
				//�Ӵ��̶�ȡģ���ļ�
				FileInputStream inputStream=new FileInputStream("ѧ��������Ϣ.docx");
				
				//��ѧ�������¼������docx ��ȡ��дȨ��
				OPCPackage opcPackage = OPCPackage.open(inputStream);
				
				//ͨ��opcPackage������XWPFDocument�����ĵ�
				XWPFDocument document=new XWPFDocument(opcPackage);
				
				//��ȡ���б��
				List<XWPFTable> tables=document.getTables();
				
				//�������б��
				for (XWPFTable table : tables) {
					//��ȡ����ÿһ��
					List<XWPFTableRow> rows=table.getRows();
					
					//����������
					for (XWPFTableRow row : rows) {
						//��ȡÿ�е�ÿ������
						List<XWPFTableCell> cells=row.getTableCells();
						//����ÿ������
						for (int i = 0; i < cells.size(); i++) {
							XWPFTableCell cell=cells.get(i);
							//��ȡ�ø��ӵ��ַ���
							String cellString=cell.getText();
							System.out.println(cellString);
							
						}
					}
				}
				document.close();
	}
}
