package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * ���ܼ��Ź�����Ч����.xls����ʮ����
 * @author ����
 *
 */
public class JNJPGZCX_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book)throws Exception {
		String shiName = "";
		String shiNameSql = "select distinct v.shiname from Allinfo_View v where v.shi = '"+getAccount().getShi()+"' ";
		ResultSet shiNameResult = stmt.executeQuery(shiNameSql);
		if(shiNameResult.next()){
			shiName = shiNameResult.getString(1);
		}
		
		WritableSheet sheet = book.getSheet(0);
		String sheetName = shiName+sheet.getName().substring(3);
		sheet.setName(sheetName);
	}
}
