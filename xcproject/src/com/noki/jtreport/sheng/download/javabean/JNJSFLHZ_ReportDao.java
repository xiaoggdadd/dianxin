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
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 节能技术分类汇总表.xls（表十四）
 * @author 王海
 *
 */
public class JNJSFLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		InputStream is = new FileInputStream(getUrl());	//获取模板流
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//临时文件路径
		getTmpPathList().add(0,tmpPath);//向临时路径集这添加当前文件的路径
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//从模板深复制一个WorkBook
		newBook.removeSheet(1);

		WritableSheet sheet = newBook.getSheet(0);
		
		Integer index = 1;
		for(WritableWorkbook book:books){
			
			WritableSheet bookSheet1 = book.getSheet(0);
			
			WritableSheet newSheet1 = newBook.createSheet(bookSheet1.getName()+index, index++);
			
			ReportCopyOperate.copyCells(bookSheet1, newSheet1, 0, 0, 7, 20, 0, 0);
		}
		//填充默认数据0
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
						currntValue = Double.valueOf(content);
					}
					
					Double dataTableRowValue = dataRow.get(k-2);
					dataTableRowValue += currntValue;
					dataRow.set(k-2, dataTableRowValue);
				}
			}
		}
		
		for(Integer j = 2;j<18;j++){
			List<Double> dataRow = dataTable.get(j-2);
			for(Integer k = 2;k<6;k++){
				Double dataTableRowValue = dataRow.get(k-2);
				DecimalFormat fat=new DecimalFormat("0.00");
				 Label label=null;
				if(k==5){
					label = new Label(k, j, fat.format(100*dataTableRowValue/(newBook.getNumberOfSheets()-2))+"%");
				}
				else{
				   label = new Label(k, j, dataTableRowValue+"");
				}
				
				sheet.addCell(label);
			}
		}
		books.add(0,newBook);
	}
}
