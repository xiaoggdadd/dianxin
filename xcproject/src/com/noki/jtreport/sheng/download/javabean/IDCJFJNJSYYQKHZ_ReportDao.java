package com.noki.jtreport.sheng.download.javabean;

import java.util.List;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * IDC机房节能技术应用情况汇总表.xls（表七）
 * @author Administrator
 *
 */
public class IDCJFJNJSYYQKHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(List<WritableWorkbook> books)throws Exception {
		Integer startRow = 0;
		Integer endRow = 2;
		WritableWorkbook firstBook = books.get(0);//获取首个报表，作为其他报表的汇总处
		WritableSheet sheet = firstBook.getSheet(0);
		
		int flag = 0;
		for(WritableWorkbook book:books){
			Integer tmp = book.getSheet(0).getRows()-2;//当前sheet中数据的条数
			startRow = endRow;
			endRow += tmp;
			if(flag++ == 0)continue;//第一个报表跳过
			
			ReportCopyOperate.copyCells(book.getSheet(0),sheet, 0, 2, 11, tmp+2, 0, startRow);
		}
	}
}
