package com.noki.jtreport.shi.download.javabean;

import jxl.write.WritableWorkbook;

/**
 * 固网专业用电量汇总表.xls(表三)
 * @author Administrator
 *
 */
public class GWZYYDLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) {
//		Integer sheetsNum = book.getSheets().length;
//		for(int i = 0;i<sheetsNum;i++){
//			if(i < sheetsNum-1)
//				book.removeSheet(0);
//		}
//		book.getSheet(0).setName("sheet1");
	}
}
