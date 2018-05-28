package com.noki.jtreport.sheng.download.javabean;

import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 通信机房环境温度管理节能情况汇总表.xls（表六）
 * @author Administrator
 *
 */
public class TXJFHJWDGLJNQKHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		
//		WritableWorkbook toBook = books.get(0);
//		WritableSheet sheet = toBook.getSheet(0);
//		Integer insertRow = sheet.getRows()-2;//从哪一行开始插入数据
//		
//		
//		for(int i = 1;i<books.size();i++){
//			WritableWorkbook book = books.get(i);
//			WritableSheet currentSheet = book.getSheet(0);
//			Integer rows = currentSheet.getRows()-5;
//			
//			int flag = insertRow-1;
//			for(int r = 0;r<rows;r++){
//				currentSheet.insertRow(flag);
//				flag ++;
//			}
//			
//			ReportCopyOperate.copyCells(currentSheet, sheet, 0, 3, 12, 3+rows, 0, insertRow);
//			
//			insertRow += rows;
//		}
		
		WritableWorkbook toBook = books.get(0);
		WritableSheet sheet = toBook.getSheet(0);
		Integer startRow = sheet.getRows();//从哪一行开始插入数据
		System.out.println("startRow"+startRow);
		
		//sheet.removeRow(sheet.getRows()-1);
		//sheet.removeRow(sheet.getRows()-1);
		System.out.println("books.size()"+books.size());
		for(int i = 1;i<books.size();i++){
			WritableWorkbook book = books.get(i);
			WritableSheet currentSheet = book.getSheet(0);
			Integer rows = currentSheet.getRows()-2;
			System.out.println("rows"+rows);
			
			if(i==books.size()-1)
				ReportCopyOperate.copyCells(currentSheet, sheet, 0, 3, 12, 3+rows, 0, startRow);
			else
				ReportCopyOperate.copyCells(currentSheet, sheet, 0, 3, 12, 2+rows, 0, startRow);
			
			startRow += rows;
		}
		for(int i = 3;i<startRow-2;i++){
			Label label = new Label(0,i,(i-2)+"");
			sheet.addCell(label);
		}
	}
}