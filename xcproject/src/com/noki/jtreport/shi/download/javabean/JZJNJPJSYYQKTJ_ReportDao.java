package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.noki.jtreport.sheng.download.javabean.ReportCopyOperate;

/**
 * 基站节能减排技术应用情况统计表.xls（表八）
 * @author Administrator
 *
 */
public class JZJNJPJSYYQKTJ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) throws Exception {
		String shiCode = getAccount().getShi();
		String sql = "select t.agname from d_area_grade t where t.agcode = '"+shiCode+"'";
		ResultSet rs = null;
		
		rs = db.queryAll(sql);
		if(rs.next()){
			String shiName = rs.getString(1);
			WritableSheet sheet = book.getSheet(0);
			int flag = 3;
			
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
