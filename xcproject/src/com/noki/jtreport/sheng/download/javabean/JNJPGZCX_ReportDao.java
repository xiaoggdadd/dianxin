package com.noki.jtreport.sheng.download.javabean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * ���ܼ��Ź�����Ч����.xls����ʮ����
 * @author ����
 *
 */
public class JNJPGZCX_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception  {
		InputStream is = new FileInputStream(getUrl());	//��ȡģ����
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//��ʱ�ļ�·��
		getTmpPathList().add(0,tmpPath);//����ʱ·��������ӵ�ǰ�ļ���·��
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//��ģ�����һ��WorkBook
		newBook.removeSheet(1);

		WritableSheet sheet = newBook.getSheet(0);
		
		Integer index = 1;
		for(WritableWorkbook book:books){
			
			WritableSheet bookSheet1 = book.getSheet(0);
			//WritableSheet bookSheet2 = book.getSheet(1);
			
			WritableSheet newSheet1 = newBook.createSheet(bookSheet1.getName()+index, index++);//����sheet
			//WritableSheet newSheet2 = newBook.createSheet(bookSheet2.getName()+index, index++);
			newSheet1.setColumnView(1, 20);//���õ�Ԫ��Ŀ��
			newSheet1.setColumnView(2, 20);
			newSheet1.setColumnView(3, 20);
			newSheet1.setColumnView(4, 20);
			newSheet1.setColumnView(5, 20);
			newSheet1.setColumnView(6, 20);
			newSheet1.setColumnView(7, 20);
			newSheet1.setColumnView(8, 20);
			
			newSheet1.mergeCells(0, 0, 3, 0); 
			newSheet1.mergeCells(0, 1, 0, 2); //�ϲ���Ԫ�񣬲�����ʽ����ʼ�У���ʼ�У������У������У� 
			newSheet1.mergeCells(1, 1, 1, 2); 
			newSheet1.mergeCells(2, 1, 3, 1); 
			newSheet1.mergeCells(4, 1, 5, 1); 
			newSheet1.mergeCells(6, 1, 7, 1); 
			newSheet1.mergeCells(0, 3, 0, 6);
			newSheet1.mergeCells(0, 7, 0, 9);
			ReportCopyOperate.copyCells(bookSheet1, newSheet1, 0, 0, 7, 10, 0, 0);
			//ReportCopyOperate.copyCells(bookSheet2, newSheet2, 0, 0, 10, 300, 0, 0);
		}
		
		//��������Ĭ������0
		List<List<Double>> dataTable = new ArrayList<List<Double>>();
		for(int r = 0;r<8;r++){
			List<Double> dataRow = new ArrayList<Double>();
			for(int i = 0;i<6;i++){
				dataRow.add(0d);
			}
			dataTable.add(dataRow);
		}
		
		for(Integer i = 1;i<newBook.getNumberOfSheets();i ++){
			//System.out.println("iֵ��"+i);
			//System.out.println("newBook.getNumberOfSheets():"+newBook.getNumberOfSheets());
			WritableSheet currentSheet = newBook.getSheet(i);
			for(Integer j = 3;j<11;j++){  //��
				//System.out.println("jֵ��"+j);
				//System.out.println("��Ԫ��Ĭ��ֵ��"+ dataTable.get(j-3)); //��ô�����  �����︳��ֵ��������
				List<Double> dataRow = dataTable.get(j-3);
				for(Integer k = 2;k<8;k++){ //����ѭ��ִ��һ�� ����ִ��6�Σ� �����������
					//System.out.println("kֵ��"+k);
					Double currntValue = 0d;
					String content = currentSheet.getCell(k, j).getContents();//��ȡ
					//System.out.println("��Ԫ��ֵ"+content);
					if(content != null && !"".equals(content)){
						currntValue = Double.valueOf(content);
					}
					
					Double dataTableRowValue = dataRow.get(k-2);
					//System.out.println("�ϼ�ֵ֮ǰ����ֵ"+dataTableRowValue);
					dataTableRowValue += currntValue;    //�����Ԫ��Ϊ�վ���ʾ0.0
					//System.out.println("�ϼ�ֵ"+dataTableRowValue);
					dataRow.set(k-2, dataTableRowValue);//�ѱ����ֵȡ�����ŵ�list��ͺ���ı���кϼ�
				}
			}
		}
		
		for(Integer j = 3;j<11;j++){ //��
			List<Double> dataRow = dataTable.get(j-3);
			for(Integer k = 2;k<8;k++){
				Double dataTableRowValue = dataRow.get(k-2);
				WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
				WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
				 jxl.write.Number number = new jxl.write.Number (k, j, dataTableRowValue,wcf);
				// System.out.println("����ֵ"+number);
				 sheet.addCell(number);
				
				//Label label = new Label(k, j, dataTableRowValue+"");
				//sheet.addCell(label);
			}
		}
		
		
		
		
		books.add(0,newBook);
	}
}
