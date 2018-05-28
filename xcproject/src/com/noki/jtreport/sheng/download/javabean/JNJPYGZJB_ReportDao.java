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
 * 节能减排季报表.xls（表十一）
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
	 * 整合单元格
	 * @param books 各地市上传的报表
	 * @throws Exception
	 *//*
	private void excelCombining(List<WritableWorkbook> books) throws Exception {
		WritableSheet firstBookSheet = books.get(0).getSheet(0);//获取报表集合的首条记录，其他报表需汇总到此
		Integer cursor = 2;//定义游标
		String key1 = firstBookSheet.getCell(0, cursor).getContents().trim()+firstBookSheet.getCell(1, cursor).getContents().trim();//获取首条数据的第一、二列数据，作为唯一标识
		while(true){
			String key2 = firstBookSheet.getCell(0, cursor+1).getContents().trim()+firstBookSheet.getCell(1, cursor+1).getContents().trim();//获取下一条数据的第一、二列数据，作为与key1的比较对象
			
			if(firstBookSheet.getCell(2, cursor+1).getContents().trim().equals(""))firstBookSheet.removeRow(cursor+1);//如果第三列的数据为空，即没有项目名称，则删除该行
			if(key1.equals(key2))cursor ++;//两者比较结果相同，游标下移
			else{
				for(int i = 1;i< books.size();i++){//遍历其他excel
					WritableSheet currentSheet = books.get(i).getSheet(0);
					int rows = currentSheet.getRows();
					for(int r = 2;r<rows;r++){
						String key3 = currentSheet.getCell(0, r).getContents().trim()+currentSheet.getCell(1, r).getContents().trim();//获取每个excel的各条数据，与key1进行比较
						String projectName = currentSheet.getCell(2, r).getContents().trim();
						if(key3.equals(key1)&&!projectName.equals("")){//如果key3与key1比较结果相同，并且该条数据的第三列，即项目名称非空
							cursor ++;//游标下移
							firstBookSheet.insertRow(cursor);//在首条excel中的当前行插入新的一行
							ReportCopyOperate.copyCells(currentSheet, firstBookSheet, 0, r, 6, r, 0, cursor);//拷贝该行
						}
					}
				}
				key1 = key2;//比较对象发生改变
			}
			if(key2.equals(""))break;//如果下一条数据第一列为空，则说明到了该excel的末尾数据，跳出循环
		}
	}
	*//**
	 * 美化excel，合并单元格
	 * @param sheet 待合并的sheet对象
	 * @param column 合并的哪一列
	 * @throws Exception
	 *//*
	private void beautifyExcel(WritableSheet sheet,Integer column) throws Exception{
		Integer rows = sheet.getRows();
		List<Integer> separater = new ArrayList<Integer>();//定义哪些数据是相同的，把相同的数据的首尾行号放入其中
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
			newSheet1.setColumnView(1, 10);//设置单元格的宽度
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
			
		 //合并单元格，参数格式（开始列，开始行，结束列，结束行） 
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
		
		//都设成填充默认数据0
		List<List<Double>> dataTable = new ArrayList<List<Double>>();
		for(int r = 0;r<74;r++){
			List<Double> dataRow = new ArrayList<Double>();
			for(int i = 0;i<3;i++){
				dataRow.add(0d);
			}
			dataTable.add(dataRow);
		}
		
		for(Integer i = 1;i<newBook.getNumberOfSheets();i ++){
			//System.out.println("i值："+i);
			//System.out.println("newBook.getNumberOfSheets():"+newBook.getNumberOfSheets());
			WritableSheet currentSheet = newBook.getSheet(i);
			for(Integer j = 3;j<77;j++){  //行
				System.out.println("j值："+j);
				//System.out.println("单元格默认值："+ dataTable.get(j-3)); //怎么会变呢  在哪里赋的值？？？？
				
				List<Double> dataRow = dataTable.get(j-3);
				for(Integer k = 5;k<8;k++){ //外面循环执行一次 里面执行6次（ 根据情况）列
					//System.out.println("k值："+k);
					Double currntValue = 0d;
					String content = currentSheet.getCell(k, j).getContents();//获取
					//System.out.println("单元格值"+content);
					if(content != null && !"".equals(content)){
						currntValue = Double.valueOf(content);
					}
					
					Double dataTableRowValue = dataRow.get(k-5);
					//System.out.println("合计值之前的数值"+dataTableRowValue);
					dataTableRowValue += currntValue;    //如果单元格为空就显示0.0
					//System.out.println("合计值"+dataTableRowValue);
					dataRow.set(k-5, dataTableRowValue);//把表里的值取出来放到list里和后面的表进行合计
				}
			}
		}
		
		for(Integer j = 3;j<77;j++){ //行
			List<Double> dataRow = dataTable.get(j-3);
			for(Integer k = 5;k<8;k++){
				Double dataTableRowValue = dataRow.get(k-5);
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
