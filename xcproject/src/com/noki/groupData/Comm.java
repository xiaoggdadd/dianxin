package com.noki.groupData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class Comm {

 //拷贝模板·生成数据
 public String writeFile(HashMap dataMap,ArrayList cityCodeList,String exmplePath,String copyfile){
		FileInputStream in = null;
		HSSFWorkbook workbook = null;
		try {
			in = new FileInputStream(exmplePath);//将excel文件转为输入流
			POIFSFileSystem fs = new POIFSFileSystem(in);//构建POIFSFileSystem类对象，用输入流构建
			workbook = new HSSFWorkbook(fs);//创建个workbook，根据POIFSFileSystem对象
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		
	 HSSFCellStyle style = workbook.createCellStyle();
	 style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	 style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	 style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	 HSSFSheet sheet = workbook.getSheetAt(0);	
	 

	 for(int i=0;i<cityCodeList.size();i++){
		 GroupDataViewBean Bean = (GroupDataViewBean)dataMap.get(cityCodeList.get(i));
		 HSSFRow row =sheet.createRow(i+3);
		//地市
	     HSSFCell Cell0 = row.createCell(0);
	     Cell0.setCellValue(Bean.getShi());
	     Cell0.setCellStyle(style);
	    //物理站址数
	     HSSFCell Cell1 = row.createCell(1);
	     Cell1.setCellValue(Bean.getZhanshu());
	     Cell1.setCellStyle(style);
		 //逻辑站数
	     HSSFCell Cell2 = row.createCell(2);
	     Cell2.setCellValue(Bean.getLjzhanshu());
	     Cell2.setCellStyle(style);
		 //载频个数
	     HSSFCell Cell3 = row.createCell(3);
	     Cell3.setCellValue(Bean.getZpgs());
	     Cell3.setCellStyle(style);
		 //基站用电量·本年累计
	     HSSFCell Cell4 = row.createCell(4);
	     Cell4.setCellValue(Bean.getDllj());
	     Cell4.setCellStyle(style);
		 //基站用电量·同比
	     HSSFCell Cell5 = row.createCell(5);
	     Cell5.setCellValue(Bean.getDltb());
	     Cell5.setCellStyle(style);
		 //基站用电量·本月
	     HSSFCell Cell6 = row.createCell(6);
	     Cell6.setCellValue(Bean.getDlby());
	     Cell6.setCellStyle(style);
		 //基站电费·本年累计
	     HSSFCell Cell7 = row.createCell(7);
	     Cell7.setCellValue(Bean.getDflj());
	     Cell7.setCellStyle(style);
		 //基站电费·同比
	     HSSFCell Cell8 = row.createCell(8);
	     Cell8.setCellValue(Bean.getDftb());
	     Cell8.setCellStyle(style);
		 //基站电费·本月
	     HSSFCell Cell9 = row.createCell(9);
	     Cell9.setCellValue(Bean.getDfby());
	     Cell9.setCellStyle(style);
		 //每载频用电量·本年累计
	     HSSFCell Cell10 = row.createCell(10);
	     Cell10.setCellValue(Bean.getZpdllj());
	     Cell10.setCellStyle(style);
		 //基站电费·同比
	     HSSFCell Cell11 = row.createCell(11);
	     Cell11.setCellValue(Bean.getZpdltb());
	     Cell11.setCellStyle(style);
		 //基站电费·本月
	     HSSFCell Cell12 = row.createCell(12);
	     Cell12.setCellValue(Bean.getZpdlby());
	     Cell12.setCellStyle(style);
	     
		 //每载频电费·本年累计
	     HSSFCell Cell13 = row.createCell(13);
	     Cell13.setCellValue(Bean.getZpdflj());
	     Cell13.setCellStyle(style);
		 //每载频电费·同比
	     HSSFCell Cell14 = row.createCell(14);
	     Cell14.setCellValue(Bean.getZpdftb());
	     Cell14.setCellStyle(style);
		 //每载频电费·本月
	     HSSFCell Cell15 = row.createCell(15);
	     Cell15.setCellValue(Bean.getZpdfby());
	     Cell15.setCellStyle(style);
	 }
	FileOutputStream out = null;
		try {
			out = new FileOutputStream(copyfile);
			workbook.write(out);
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		} 
	 }
	 return "ok";
 }
 //
 
}
