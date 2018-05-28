package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;

import com.noki.database.DbException;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 接入网机房节能减排技术应用情况.xls（表十二）
 * @author 王海
 *
 */
public class JRWJFJNJPJSYYQK_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) throws Exception {
		String shiCode = getAccount().getShi();
		String sql = "select t.agname from d_area_grade t where t.agcode = '"+shiCode+"'";
		ResultSet rs = null;
		
		rs = db.queryAll(sql);
		if(rs.next()){
			String shiName = rs.getString(1);
			WritableSheet sheet = book.getSheet(0);
			int flag = 2;
			
			for(int r = 3;r<21;r++){
				String colName = sheet.getCell(flag, 1).getContents();
				if(shiName.indexOf(colName)<0){//没有找到对应的城市名称
					sheet.removeColumn(flag);
				}
				else{
					flag ++;
				}
			}
		}
	}
}
