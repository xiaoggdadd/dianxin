package com.noki.jtreport.sheng.download.javabean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import com.noki.util.CTime;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;




/**
 * 接入网机房用电量台账.xls（表十）
 * @author 王海
 *
 */
public class JRWJFYDLTZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		Integer startRow = 0;
		Integer endRow = 4;
		WritableWorkbook firstBook = books.get(0);//获取首个报表，作为其他报表的汇总处
		WritableSheet sheet = firstBook.getSheet(0);
	

		int flag = 0;
		for(WritableWorkbook book:books){
			Integer tmp = book.getSheet(0).getRows()-4;//当前sheet中数据的条数
			startRow = endRow;
			endRow += tmp;
			if(flag++ == 0)continue;//第一个报表跳过
			String content = book.getSheet(0).getCell(13,4).getContents();
			String content2 = book.getSheet(0).getCell(15,4).getContents();
			System.out.println(content+"----------------"+content2);
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 4, 31, tmp+4, 0, startRow);
		}
		for(int i=4;i<sheet.getRows();i++){
			
		    String contents=sheet.getWritableCell(13,i).getContents();
		    String contents1=sheet.getWritableCell(15,i).getContents();
		    String contents2=sheet.getWritableCell(26,i).getContents();
		     
		   
		     CTime util=new CTime();
		     
		    if(null==contents||contents.equals("")||contents.equals(" ")){
		    	break;
		    }else{
			String content=util.formatRealDate(new Date(contents));
			contents1=util.formatRealDate(new Date(contents1));
			contents2=util.formatRealDate(new Date(contents2));
			sheet.addCell(new Label(13,i,content));
			sheet.addCell(new Label(15,i,contents1));
			sheet.addCell(new Label(26,i,contents2));
			
		    }
		}
	}
}
