package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * IDC机房节能技术应用情况汇总表.xls（表七）
 * @author Administrator
 *
 */
public class IDCJFJNJSYYQKHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) {
		// TODO Auto-generated method stub
		String power=getAccount().getShi();
		WritableSheet sheet=book.getSheet(0);
		try {   
			    String shengName="山东省";
			    //sheet.addCell(new Label(0,2,shengName));            //省
			
			
				String shiName="";
				String shiNameql="select distinct shiname from Allinfo_View where shi='"+power+"' ";
				ResultSet shinameResult=stmt.executeQuery(shiNameql);
			    if(shinameResult.next()){
			    	shiName=shinameResult.getString(1);
			    }
			    //sheet.addCell(new Label(1,2,shiName));             //城市
			    String sql1="select count(v.jzname) from Allinfo_View v where shi='"+power+"' and jztype='zdlx01' and jnglmk='是'";
			    ResultSet res=stmt.executeQuery(sql1);
			    int count=0;
			    if(res.next())
			    {
			     count=res.getInt(1);
			     }
//			    sheet.mergeCells(0, 2, 0, count+1);
//			    sheet.mergeCells(1, 2, 1, count+1);
			    List list=new ArrayList();
			    ResultSet rs1 = null;
			    String sql_name="select v.jzname from Allinfo_View v where v.shi='"+power+"' and v.jztype='zdlx01' and v.jnglmk='是'";
				rs1=stmt.executeQuery(sql_name);
				while(rs1.next()){
					list.add(rs1.getString(1));
				}
			for(int i=2;i<list.size()+2;i++){              //行
				
				for(int r=0;r<12;r++){          //列333
					String sql="";
					ResultSet rs = null;
					String content = "";
					
					switch(r){
					case 0:
						content="山东省";
						break;
					case 1:
						content=shiName;
						break;
					case 2:
						content=list.get(i-2).toString();
						break;
					case 3:
						sql="select i.name from zhandiankz k,zhandian z,indexs i where shi='"+power+"' and jztype='zdlx01' and k.txj=i.code and z.id=k.zdid and z.jzname='"+list.get(i-2)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						
						break;
					case 4:
						sql="select i.name from zhandiankz k,zhandian z,indexs i where shi='"+power+"' and jztype='zdlx01' and k.jnjslx=i.code and z.id=k.zdid and z.jzname='"+list.get(i-2)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
							
						}
						System.out.println("--"+content);
						break;
					}
					Label label = new Label(r,i,content);
					sheet.addCell(label);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
