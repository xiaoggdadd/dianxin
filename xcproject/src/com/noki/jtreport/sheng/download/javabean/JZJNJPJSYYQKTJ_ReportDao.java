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
 * 基站节能减排技术应用情况统计表.xls（表八）
 * @author Administrator
 *
 */
public class JZJNJPJSYYQKTJ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books) throws Exception {
		InputStream is = new FileInputStream(getUrl());	//获取模板流
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//临时文件路径
		getTmpPathList().add(0,tmpPath);//向临时路径集这添加当前文件的路径
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//从模板深复制一个WorkBook

		WritableSheet sheet = newBook.getSheet(0);
		//将相应城市的数据粘贴到省级模板中
		for(int i=0;i<books.size();i++){
			String shiName = books.get(i).getSheet(0).getCell(3,1).getContents();
			for(int j = 4;j < 20;j ++){
				String shiName2 = sheet.getCell(j,1).getContents();
				System.out.println("1111111111:"+shiName2+"222:"+shiName);
				if(shiName2.equals(shiName)){//找到对应的城市名称
					ReportCopyOperate.copyCells(books.get(i).getSheet(0), sheet, 3, 1, 3, 41, j, 1);//复制数据
					break;
				}
			}
		}
		
		//开始合计
		for(int i = 2;i < 42;i ++){
			int currentRowNumber = 0;
			for(int j = 4;j < 20;j ++){
				String content = sheet.getCell(j,i).getContents();
				if(!content.equals("")) currentRowNumber += Integer.valueOf(content);
			}
			    WritableFont   wf   =   new   WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD,false); 
				WritableCellFormat   wcf   =   new   WritableCellFormat(wf); 
				wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); 
				 jxl.write.Number number = new jxl.write.Number (3,i,currentRowNumber,wcf);
				 sheet.addCell(number);
				 Label label = new Label(3,1,"山东汇总",wcf);
				 sheet.addCell(label);

		}
		
		books.add(0,newBook);
	}
}
