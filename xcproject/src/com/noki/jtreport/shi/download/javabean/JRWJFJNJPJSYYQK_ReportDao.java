package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;

import com.noki.database.DbException;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * �������������ܼ��ż���Ӧ�����.xls����ʮ����
 * @author ����
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
				if(shiName.indexOf(colName)<0){//û���ҵ���Ӧ�ĳ�������
					sheet.removeColumn(flag);
				}
				else{
					flag ++;
				}
			}
		}
	}
}
