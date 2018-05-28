package com.noki.jtreport.sheng.download.javabean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;



/**
 * ����8��ͨ�Ż������ܼ���Ӧ����Ϣͳ��.xls
 * @author ����
 *
 */
public class TXJFJNJSYYXXTJ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		
		InputStream is = new FileInputStream(getUrl());	//��ȡģ����
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//��ʱ�ļ�·��
		getTmpPathList().add(0,tmpPath);//����ʱ·��������ӵ�ǰ�ļ���·��
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//��ģ�����һ��WorkBook
		//newBook.removeSheet(1);

		WritableSheet sheet = newBook.getSheet(0);
		
		Integer index = 3;
		for(WritableWorkbook book:books){
			
			WritableSheet bookSheet1 = book.getSheet(0);
			
			WritableSheet newSheet1 = newBook.createSheet(bookSheet1.getName()+index, index++);
			newSheet1.setColumnView(1, 30);
			newSheet1.setColumnView(2, 30);
			newSheet1.setColumnView(3, 30);
			newSheet1.setColumnView(4, 25);
			newSheet1.setColumnView(5, 25);
			newSheet1.mergeCells(0, 0, 1, 0); 
			newSheet1.mergeCells(0, 2, 0, 17); //�ϲ���Ԫ�񣬲�����ʽ����ʼ�У���ʼ�У������У������У� 
			newSheet1.mergeCells(4, 2, 4, 17); 
			newSheet1.mergeCells(5, 2, 5, 17); 
			ReportCopyOperate.copyCells(bookSheet1, newSheet1, 0, 0, 5, 17, 0, 0);
			
			


		}
		//���Ĭ������0
		List<List<Double>> dataTable = new ArrayList<List<Double>>();
		for(int r = 0;r<16;r++){
			List<Double> dataRow = new ArrayList<Double>();
			for(int i = 0;i<4;i++){
				dataRow.add(0d);
			}
			dataTable.add(dataRow);
		}
		
		for(Integer i = 1;i<newBook.getNumberOfSheets();i++){
			WritableSheet currentSheet = newBook.getSheet(i);
			for(Integer j = 2;j<18;j++){
				List<Double> dataRow = dataTable.get(j-2);
				for(Integer k = 2;k<6;k++){
					Double currntValue = 0d;
					String content = currentSheet.getCell(k, j).getContents();
					if(content != null && !"".equals(content)){
						if(content.contains("%")){
							int ss=content.indexOf("%");
							String con=content.substring(0, ss);
							currntValue = Double.valueOf(con);
						}else{
							currntValue = Double.valueOf(content);
						}
						
					}
					
					Double dataTableRowValue = dataRow.get(k-2);
					dataTableRowValue += currntValue;
					dataRow.set(k-2, dataTableRowValue);
				}
			}
		}
		WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
		WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
		wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
	
		for(Integer j = 2;j<18;j++){
			List<Double> dataRow = dataTable.get(j-2);
			for(Integer k = 2;k<6;k++){
				Double dataTableRowValue = dataRow.get(k-2);
				DecimalFormat fat=new DecimalFormat("0.00");
				 Label label=null;
				if(k==5){
					System.out.println("dataTableRowValue"+dataTableRowValue+"  newBook.getNumberOfSheets()��"+(newBook.getNumberOfSheets()-2));
					label = new Label(k, j, fat.format(dataTableRowValue/(newBook.getNumberOfSheets()-3))+"%",wcf);
					sheet.addCell(label);
				}
				else{
					
					jxl.write.Number number = new jxl.write.Number (k, j, dataTableRowValue,wcf);
					 sheet.addCell(number);
				}
				
			}
		}
	/*	int aa=newBook.getNumberOfSheets();
		for(Integer i=aa;i>3;i--){
			newBook.removeSheet(i);
			
		}*/
		
	
		
	//�ڶ������������ݵĻ���
		Integer startRow = 0;
		Integer endRow =4 ;
		//WritableWorkbook firstBook = books.get(0);//��ȡ�׸�������Ϊ��������Ļ��ܴ�
		WritableSheet sheet1 = newBook.getSheet(1);
		
		int flag = 0;
		for(WritableWorkbook book:books){
			Integer tmp = book.getSheet(1).getRows()-4;//��ǰsheet�����ݵ�����
			System.out.println(book.getSheet(1).getRows());
			startRow = endRow;
			System.out.println("-"+tmp);
			endRow += tmp;
			//if(flag++ == 0)continue;//��һ����������
			System.out.println("-"+tmp+"-"+startRow+"-"+endRow);
			ReportCopyOperate.copyCells(book.getSheet(1),sheet1, 0, 4, 31, tmp+4, 0,startRow );
		}
		
		books.add(0,newBook);	
	}
}
