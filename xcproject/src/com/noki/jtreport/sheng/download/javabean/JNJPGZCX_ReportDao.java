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
 * 节能减排工作成效报表.xls（表十三）
 * @author 王海
 *
 */
public class JNJPGZCX_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception  {
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
			//WritableSheet bookSheet2 = book.getSheet(1);
			
			WritableSheet newSheet1 = newBook.createSheet(bookSheet1.getName()+index, index++);//创建sheet
			//WritableSheet newSheet2 = newBook.createSheet(bookSheet2.getName()+index, index++);
			newSheet1.setColumnView(1, 20);//设置单元格的宽度
			newSheet1.setColumnView(2, 20);
			newSheet1.setColumnView(3, 20);
			newSheet1.setColumnView(4, 20);
			newSheet1.setColumnView(5, 20);
			newSheet1.setColumnView(6, 20);
			newSheet1.setColumnView(7, 20);
			newSheet1.setColumnView(8, 20);
			
			newSheet1.mergeCells(0, 0, 3, 0); 
			newSheet1.mergeCells(0, 1, 0, 2); //合并单元格，参数格式（开始列，开始行，结束列，结束行） 
			newSheet1.mergeCells(1, 1, 1, 2); 
			newSheet1.mergeCells(2, 1, 3, 1); 
			newSheet1.mergeCells(4, 1, 5, 1); 
			newSheet1.mergeCells(6, 1, 7, 1); 
			newSheet1.mergeCells(0, 3, 0, 6);
			newSheet1.mergeCells(0, 7, 0, 9);
			ReportCopyOperate.copyCells(bookSheet1, newSheet1, 0, 0, 7, 10, 0, 0);
			//ReportCopyOperate.copyCells(bookSheet2, newSheet2, 0, 0, 10, 300, 0, 0);
		}
		
		//都设成填充默认数据0
		List<List<Double>> dataTable = new ArrayList<List<Double>>();
		for(int r = 0;r<8;r++){
			List<Double> dataRow = new ArrayList<Double>();
			for(int i = 0;i<6;i++){
				dataRow.add(0d);
			}
			dataTable.add(dataRow);
		}
		
		for(Integer i = 1;i<newBook.getNumberOfSheets();i ++){
			//System.out.println("i值："+i);
			//System.out.println("newBook.getNumberOfSheets():"+newBook.getNumberOfSheets());
			WritableSheet currentSheet = newBook.getSheet(i);
			for(Integer j = 3;j<11;j++){  //行
				//System.out.println("j值："+j);
				//System.out.println("单元格默认值："+ dataTable.get(j-3)); //怎么会变呢  在哪里赋的值？？？？
				List<Double> dataRow = dataTable.get(j-3);
				for(Integer k = 2;k<8;k++){ //外面循环执行一次 里面执行6次（ 根据情况）列
					//System.out.println("k值："+k);
					Double currntValue = 0d;
					String content = currentSheet.getCell(k, j).getContents();//获取
					//System.out.println("单元格值"+content);
					if(content != null && !"".equals(content)){
						currntValue = Double.valueOf(content);
					}
					
					Double dataTableRowValue = dataRow.get(k-2);
					//System.out.println("合计值之前的数值"+dataTableRowValue);
					dataTableRowValue += currntValue;    //如果单元格为空就显示0.0
					//System.out.println("合计值"+dataTableRowValue);
					dataRow.set(k-2, dataTableRowValue);//把表里的值取出来放到list里和后面的表进行合计
				}
			}
		}
		
		for(Integer j = 3;j<11;j++){ //行
			List<Double> dataRow = dataTable.get(j-3);
			for(Integer k = 2;k<8;k++){
				Double dataTableRowValue = dataRow.get(k-2);
				WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
				WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
				 jxl.write.Number number = new jxl.write.Number (k, j, dataTableRowValue,wcf);
				// System.out.println("最终值"+number);
				 sheet.addCell(number);
				
				//Label label = new Label(k, j, dataTableRowValue+"");
				//sheet.addCell(label);
			}
		}
		
		
		
		
		books.add(0,newBook);
	}
}
