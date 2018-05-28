package com.noki.jtreport.sheng.download.javabean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * �������������ܼ��ż���Ӧ�����.xls����ʮ����
 * @author ����
 *
 */
public class JRWJFJNJPJSYYQK_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception{
		InputStream is = new FileInputStream(getUrl());	//��ȡģ����
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//��ʱ�ļ�·��
		getTmpPathList().add(0,tmpPath);//����ʱ·��������ӵ�ǰ�ļ���·��
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//��ģ�����һ��WorkBook

		WritableSheet sheet = newBook.getSheet(0);
		
		//����Ӧ���е�����ճ����ʡ��ģ����
		for(int i=0;i<books.size();i++){
			String shiName = books.get(i).getSheet(0).getCell(2,1).getContents();
			for(int j = 3;j < 20;j ++){
				String shiName2 = sheet.getCell(j,1).getContents();
				if(shiName2.equals(shiName)){//�ҵ���Ӧ�ĳ�������
					ReportCopyOperate.copyCells(books.get(i).getSheet(0), sheet, 2, 1, 2, 41, j, 1);
					break;
				}
			}
		}
		
		//��ʼ�ϼ�
		for(int i = 2;i < 42;i ++){
			int currentRowNumber = 0;
			for(int j = 3;j < 20;j ++){
				String content = sheet.getCell(j,i).getContents();
				if(!content.equals("")) currentRowNumber += Integer.valueOf(content);
			}
			WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
			WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
			wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
			Label label = new Label(2,1,"ȫʡ����",wcf);
		    sheet.addCell(label);
			 jxl.write.Number number = new jxl.write.Number (2,i,currentRowNumber,wcf);
			 sheet.addCell(number);
			
		}
		
		books.add(0,newBook);
	}
}
