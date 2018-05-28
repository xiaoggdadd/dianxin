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
 * ���ܼ��ż�����.xls����ʮһ��
 * @author Administrator
 *
 */
/*public class JNJPYGZJB_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		excelCombining(books);
		WritableSheet sheet = books.get(0).getSheet(0);
		beautifyExcel(sheet,0);
		beautifyExcel(sheet,1);
	}
	*//**
	 * ���ϵ�Ԫ��
	 * @param books �������ϴ��ı���
	 * @throws Exception
	 *//*
	private void excelCombining(List<WritableWorkbook> books) throws Exception {
		WritableSheet firstBookSheet = books.get(0).getSheet(0);//��ȡ�����ϵ�������¼��������������ܵ���
		Integer cursor = 2;//�����α�
		String key1 = firstBookSheet.getCell(0, cursor).getContents().trim()+firstBookSheet.getCell(1, cursor).getContents().trim();//��ȡ�������ݵĵ�һ���������ݣ���ΪΨһ��ʶ
		while(true){
			String key2 = firstBookSheet.getCell(0, cursor+1).getContents().trim()+firstBookSheet.getCell(1, cursor+1).getContents().trim();//��ȡ��һ�����ݵĵ�һ���������ݣ���Ϊ��key1�ıȽ϶���
			
			if(firstBookSheet.getCell(2, cursor+1).getContents().trim().equals(""))firstBookSheet.removeRow(cursor+1);//��������е�����Ϊ�գ���û����Ŀ���ƣ���ɾ������
			if(key1.equals(key2))cursor ++;//���߱ȽϽ����ͬ���α�����
			else{
				for(int i = 1;i< books.size();i++){//��������excel
					WritableSheet currentSheet = books.get(i).getSheet(0);
					int rows = currentSheet.getRows();
					for(int r = 2;r<rows;r++){
						String key3 = currentSheet.getCell(0, r).getContents().trim()+currentSheet.getCell(1, r).getContents().trim();//��ȡÿ��excel�ĸ������ݣ���key1���бȽ�
						String projectName = currentSheet.getCell(2, r).getContents().trim();
						if(key3.equals(key1)&&!projectName.equals("")){//���key3��key1�ȽϽ����ͬ�����Ҹ������ݵĵ����У�����Ŀ���Ʒǿ�
							cursor ++;//�α�����
							firstBookSheet.insertRow(cursor);//������excel�еĵ�ǰ�в����µ�һ��
							ReportCopyOperate.copyCells(currentSheet, firstBookSheet, 0, r, 6, r, 0, cursor);//��������
						}
					}
				}
				key1 = key2;//�Ƚ϶������ı�
			}
			if(key2.equals(""))break;//�����һ�����ݵ�һ��Ϊ�գ���˵�����˸�excel��ĩβ���ݣ�����ѭ��
		}
	}
	*//**
	 * ����excel���ϲ���Ԫ��
	 * @param sheet ���ϲ���sheet����
	 * @param column �ϲ�����һ��
	 * @throws Exception
	 *//*
	private void beautifyExcel(WritableSheet sheet,Integer column) throws Exception{
		Integer rows = sheet.getRows();
		List<Integer> separater = new ArrayList<Integer>();//������Щ��������ͬ�ģ�����ͬ�����ݵ���β�кŷ�������
		String key1 = sheet.getCell(column, 2).getContents();
		for(int i = 3;i<rows;i++){
			String key2 = sheet.getCell(column, i).getContents();
			if(!key1.equals(key2)){
				separater.add(i);
				key1 = key2;
			}
		}
		Integer startRow = 2;
		for(Integer i:separater){
			sheet.mergeCells(column, startRow, column, i-1);
			startRow = i;
		}
	}
} */

public class JNJPYGZJB_ReportDao extends ReportDao {
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
			newSheet1.setColumnView(1, 10);//���õ�Ԫ��Ŀ��
			newSheet1.setColumnView(2, 20);
			newSheet1.setColumnView(3, 15);
			newSheet1.setColumnView(4, 15);
			newSheet1.setColumnView(5, 15);
			newSheet1.setColumnView(6, 15);
			newSheet1.setColumnView(7, 40);
			
			newSheet1.mergeCells(0, 0, 8, 0); 
			newSheet1.mergeCells(0, 1, 8, 1);
			newSheet1.mergeCells(1, 2, 3, 2);
			newSheet1.mergeCells(1, 3, 3, 3);
			newSheet1.mergeCells(1, 4, 3, 4);
			newSheet1.mergeCells(1, 5, 3, 5);
			newSheet1.mergeCells(1, 6, 3, 6);
			newSheet1.mergeCells(1, 7, 3, 8);
			newSheet1.mergeCells(1, 9, 3, 9);
			
		 //�ϲ���Ԫ�񣬲�����ʽ����ʼ�У���ʼ�У������У������У� 
			newSheet1.mergeCells(1, 10, 1, 13); 
			newSheet1.mergeCells(1, 14, 1, 15); 
			newSheet1.mergeCells(1, 16, 3, 16);
			newSheet1.mergeCells(1, 17, 3, 17);
			newSheet1.mergeCells(1, 18, 3, 18);
			newSheet1.mergeCells(1, 19, 3, 19);
			newSheet1.mergeCells(1,20, 3, 20);
			
			newSheet1.mergeCells(1, 21, 1, 24); 
			newSheet1.mergeCells(1, 25, 1, 26);
			newSheet1.mergeCells(1, 27, 3, 27);
			newSheet1.mergeCells(1, 28, 3, 28);
			newSheet1.mergeCells(1, 29, 3, 29);
			newSheet1.mergeCells(1, 30, 3, 30);
			
			newSheet1.mergeCells(1, 31, 3, 32);
			newSheet1.mergeCells(1, 33, 3, 33);
			newSheet1.mergeCells(1, 34, 3, 34);
			
			newSheet1.mergeCells(1, 35, 3, 36);
			newSheet1.mergeCells(1, 37, 3, 37);
			newSheet1.mergeCells(1, 38, 3, 38);
			
			newSheet1.mergeCells(1, 39, 3, 40);
			newSheet1.mergeCells(1, 41, 3, 41);
			newSheet1.mergeCells(1, 42, 3, 42);
			newSheet1.mergeCells(1, 43, 3, 44);
			newSheet1.mergeCells(1, 45, 3, 46);
			newSheet1.mergeCells(1, 47, 3, 48);
			newSheet1.mergeCells(1, 49, 3, 49);
			newSheet1.mergeCells(1, 50, 3, 50);
			newSheet1.mergeCells(1, 51, 3, 51);
			newSheet1.mergeCells(1, 52, 3, 52);
			newSheet1.mergeCells(1, 53, 3, 53);
			newSheet1.mergeCells(1, 54, 3, 54);
			newSheet1.mergeCells(1, 55, 3, 55);
			newSheet1.mergeCells(1, 56, 3, 56);
			newSheet1.mergeCells(1, 57, 3, 57);
			newSheet1.mergeCells(1, 58, 3, 58);
			newSheet1.mergeCells(1, 59, 3, 59);
			newSheet1.mergeCells(1, 60, 3, 60);
			newSheet1.mergeCells(1, 61, 3, 61);
			newSheet1.mergeCells(1, 62, 3, 62);
			newSheet1.mergeCells(1, 63, 3, 63);
			newSheet1.mergeCells(1, 64, 3, 64);
			newSheet1.mergeCells(1, 65, 3, 65);
			newSheet1.mergeCells(1, 66, 3, 66);
			newSheet1.mergeCells(1, 67, 3, 67);
			newSheet1.mergeCells(1, 68, 3, 68);
			newSheet1.mergeCells(1, 69, 3, 69);
			newSheet1.mergeCells(1, 70, 3, 70);
			newSheet1.mergeCells(1, 71, 3, 71);
			newSheet1.mergeCells(1, 72, 3, 72);
			newSheet1.mergeCells(1, 73, 3, 73);
			newSheet1.mergeCells(1, 74, 3, 74);
			newSheet1.mergeCells(1, 75, 3, 75);
			newSheet1.mergeCells(1, 76, 3, 76);
			
			ReportCopyOperate.copyCells(bookSheet1, newSheet1, 0, 0, 8, 76, 0, 0);
			//ReportCopyOperate.copyCells(bookSheet2, newSheet2, 0, 0, 10, 300, 0, 0);
		}
		
		//��������Ĭ������0
		List<List<Double>> dataTable = new ArrayList<List<Double>>();
		for(int r = 0;r<74;r++){
			List<Double> dataRow = new ArrayList<Double>();
			for(int i = 0;i<3;i++){
				dataRow.add(0d);
			}
			dataTable.add(dataRow);
		}
		
		for(Integer i = 1;i<newBook.getNumberOfSheets();i ++){
			//System.out.println("iֵ��"+i);
			//System.out.println("newBook.getNumberOfSheets():"+newBook.getNumberOfSheets());
			WritableSheet currentSheet = newBook.getSheet(i);
			for(Integer j = 3;j<77;j++){  //��
				System.out.println("jֵ��"+j);
				//System.out.println("��Ԫ��Ĭ��ֵ��"+ dataTable.get(j-3)); //��ô�����  �����︳��ֵ��������
				
				List<Double> dataRow = dataTable.get(j-3);
				for(Integer k = 5;k<8;k++){ //����ѭ��ִ��һ�� ����ִ��6�Σ� �����������
					//System.out.println("kֵ��"+k);
					Double currntValue = 0d;
					String content = currentSheet.getCell(k, j).getContents();//��ȡ
					//System.out.println("��Ԫ��ֵ"+content);
					if(content != null && !"".equals(content)){
						currntValue = Double.valueOf(content);
					}
					
					Double dataTableRowValue = dataRow.get(k-5);
					//System.out.println("�ϼ�ֵ֮ǰ����ֵ"+dataTableRowValue);
					dataTableRowValue += currntValue;    //�����Ԫ��Ϊ�վ���ʾ0.0
					//System.out.println("�ϼ�ֵ"+dataTableRowValue);
					dataRow.set(k-5, dataTableRowValue);//�ѱ����ֵȡ�����ŵ�list��ͺ���ı���кϼ�
				}
			}
		}
		
		for(Integer j = 3;j<77;j++){ //��
			List<Double> dataRow = dataTable.get(j-3);
			for(Integer k = 5;k<8;k++){
				Double dataTableRowValue = dataRow.get(k-5);
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
