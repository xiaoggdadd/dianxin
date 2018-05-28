package com.noki.jtreport.sheng.download.javabean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * ����10�������豸�����۱���
 * @author Administrator
 *
 */
public class JNSBHPJBB_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		// TODO Auto-generated method stub
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
			newSheet1.setColumnView(2, 10);
			newSheet1.setColumnView(3, 10);
			newSheet1.setColumnView(4, 10);
			newSheet1.setColumnView(5, 10);
			newSheet1.setColumnView(6, 10);
			newSheet1.setColumnView(7, 10);
			newSheet1.setColumnView(8, 10);
			newSheet1.setColumnView(9, 10);
			newSheet1.setColumnView(10, 10);
			newSheet1.setColumnView(11, 10);
			newSheet1.setColumnView(12, 10);
			newSheet1.setColumnView(13, 10);
			newSheet1.setColumnView(14, 10);
			newSheet1.setColumnView(15, 10);
			newSheet1.setColumnView(16, 10);
			newSheet1.setColumnView(17, 10);
			
			//�ϲ���Ԫ�񣬲�����ʽ����ʼ�У���ʼ�У������У������У� 
			newSheet1.mergeCells(0, 0, 16, 0); 
			newSheet1.mergeCells(0, 1, 0, 2);
			newSheet1.mergeCells(1, 1, 1, 2);
			newSheet1.mergeCells(2, 1, 2, 2);
			newSheet1.mergeCells(3, 1, 3, 2);
			newSheet1.mergeCells(5, 1, 7, 1);
			newSheet1.mergeCells(8, 1, 10, 1);
			newSheet1.mergeCells(11, 1, 14, 1);
			newSheet1.mergeCells(15, 1, 16, 1);
			
			
			ReportCopyOperate.copyCells(bookSheet1, newSheet1, 0, 0, 16, 26, 0, 0);
			//ReportCopyOperate.copyCells(bookSheet2, newSheet2, 0, 0, 10, 300, 0, 0);
		}
		
/*		//��������Ĭ������0
		List<List<Double>> dataTable = new ArrayList<List<Double>>();
		for(int r = 0;r<74;r++){
			List<Double> dataRow = new ArrayList<Double>();
			for(int i = 0;i<3;i++){
				dataRow.add(0d);
			}
			dataTable.add(dataRow);
		}
		*/

		
		for(Integer j = 3;j<13;j++){ //��
			//List<Double> dataRow = dataTable.get(j-3);
			for(Integer k = 0;k<17;k++){
				//Double dataTableRowValue = dataRow.get(k-5);
				WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
				WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
				 jxl.write.Label label = new jxl.write.Label (k, j, "",wcf);
				// System.out.println("����ֵ"+number);
				 sheet.addCell(label);
				
				//Label label = new Label(k, j, dataTableRowValue+"");
				//sheet.addCell(label);
			}
		}
		
		
		
		
		books.add(0,newBook);
	
		
	/*	for(WritableWorkbook book:books){
			startRow = endRow;
			endRow += 1;
			if(flag++ == 0)continue;//��һ����������
			System.out.println(endRow+"---"+startRow);
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 3, 16, 3, 0, startRow);//ֻ�����˵�һ������
			
		}
		for(int i=3;i<endRow;i++){
			 jxl.write.Number number = new jxl.write.Number (0,i,i-2);
			 sheet.addCell(number);
		}*/
		//----------------------ͳ��----------------------
		//ReportCopyOperate.copyCells(sheet,sheet, 0, 3, 15, 3, 0, endRow);
		/*Label heji = new Label(0,endRow,"�ϼ�");
		sheet.addCell(heji);
		DecimalFormat mat=new DecimalFormat("0.00");
		for(int i = 1;i<16;i++){//���б���
			double lx1=0;
			for(int r = 3;r<endRow+1;r++){//���б���
				String leixing = sheet.getCell(1, r).getContents();//��ȡ���еڶ��е����ݣ��������ĸ�����
				String content = "";
				double yb=100.00;
				if(r<endRow){//ͳ��֮ǰ������
					content = sheet.getCell(i, r).getContents();//��ȡ�õ�Ԫ������
					if(i==7&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/100.00);
					}
					if(i==8&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/yb);
					}
					if(i==14&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/yb);
					}
					if(i==15&&!content.equals("")){
						int ss=content.indexOf("%");
	        		     String shu=content.substring(0, ss);
	        		     content=mat.format(Double.parseDouble(shu)/yb);
					}
					lx1 += content.equals("")?0:Double.valueOf(content);
					System.out.println("lx1"+lx1);
				}
				else{//׼��ͳ��
					content += lx1;
					System.out.println("content"+content);
				    //content=mat.format(Double.parseDouble(content));
					if(i==7&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					if(i==8&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					if(i==14&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					if(i==15&&!content.equals("")){
						content=mat.format((Double.parseDouble(content)*yb)/(endRow-3))+"%";
					}
					boolean dd=content.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
					if(content!=null&&content!=""&&dd==true){
						double con=Double.parseDouble(content);
						 jxl.write.Number number = new jxl.write.Number (i, r, con,wcf);
						 sheet.addCell(number);
					}else{
						sheet.addCell(new Label(i, r, content,wcf));
					}
					
					//Label cell = new Label(i,r,content,wcf);
					//sheet.addCell(cell);
				}
			}
		}*/
	}
}
