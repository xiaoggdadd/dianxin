package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 通信机房环境温度管理节能情况汇总表.xls（表六）
 * @author Administrator
 *
 */
public class TXJFHJWDGLJNQKHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) {
		String power = getAccount().getShi();
		WritableSheet sheet = book.getSheet(0);
		
		try {
			String shiName = "";
			String shiNameSql = "select distinct v.shiname from Allinfo_View v where v.shi = '"+power+"' ";
			ResultSet shiNameResult = stmt.executeQuery(shiNameSql);
			if(shiNameResult.next()){
				shiName = shiNameResult.getString(1);
			}
			
			String sql = "select z.jzname from zhandian z where z.jztype = 'zdlx12' and z.xuni='0' and z.shi = '"+power+"'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(sql+"**************************");
			List<String> list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			
			for(int i = 3;i<list.size()+3;i++){
				sheet.insertRow(i);
				for(int r = 0;r<5;r++){
					String content = "";
					switch(r){
					case 0:
						content = ""+(i-2);
						break;
					case 1:
						content = "山东省";
						break;
					case 2:
						content = shiName;
						break;
					case 3:
						content = list.get(i-3);
						break;
					case 4:
			            sql="select i.name from indexs i,zhandian z,zhandiankz k where z.id=k.zdid and k.hjlx=i.code and shi='"+power+"' and z.jzname='"+list.get(i-3)+"'";
			              rs=stmt.executeQuery(sql);
			              System.out.println("--"+sql);
			            while(rs.next()){
			                content=rs.getString(1);
			              }
			            break;
					}
					Label label = new Label(r,i,content);
					
					sheet.addCell(label);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}