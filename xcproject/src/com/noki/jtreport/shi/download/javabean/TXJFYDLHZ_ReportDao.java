package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 通信局房用电量汇总表.xls（表五）
 * @author Administrator
 *
 */
public class TXJFYDLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) {
		String power = getAccount().getShi();
		WritableSheet sheet = book.getSheet(0);
		String month=getMonth();
		String accountmonth=getAccountmonth();
		String str="";
		if(!"".equals(month)&&null!=month){
			str=" and to_char(a.endmonth,'yyyy-mm')='"+month+"'";
		}
		if(!"".equals(accountmonth)&&null!=accountmonth){
			str=str+" and to_char(e.accountmonth,'yyyy-mm')='"+accountmonth+"'";
		}
		try {
			//市
			String shiName = ""; 
			String shiNameSql = "select distinct v.shiname from Allinfo_View v where v.shi = '"+power+"' ";
			ResultSet shiNameResult = stmt.executeQuery(shiNameSql);
			if(shiNameResult.next()){
				shiName = shiNameResult.getString(1);
			}
			
			
			List<String> jznameList = new ArrayList<String>();//所有的通讯局房
			String sql_jzname="select jzname from zhandian where shi='"+power+"'and jztype='zdlx12' and xuni='0'";
			ResultSet jzNameSet=stmt.executeQuery(sql_jzname);
			
			while(jzNameSet.next()){
				jznameList.add(jzNameSet.getString(1));
			}
			for(int i = 4;i<jznameList.size()+4;i++){//行
				sheet.insertRow(i);
				for(int r = 0;r<25;r++){ 
					String sql="";
					String content="";
					ResultSet rs=null;//列
					switch(r){
					case 0:
						content = "山东省";
						break;
					case 1:
						content = shiName;
						break;
					case 2:
						content = jznameList.get(i-4);
						break;
					case 3:
						sql="select z.address from zhandian z where shi='"+power+"' and jztype='zdlx12' and z.jzname='"+jznameList.get(i-4)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 4:
						sql="select z.zdcode from zhandian z where shi='"+power+"' and jztype='zdlx12' and z.jzname='"+jznameList.get(i-4)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 5:
						sql="select i.name from zhandian z,indexs i where shi='"+power+"' and jztype='zdlx12' and z.jflx=i.code and z.jzname='"+jznameList.get(i-4)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 6:
						sql="select i.name from zhandiankz k,zhandian z,indexs i where shi='"+power+"' and jztype='zdlx12' and k.ydlx=i.code and z.id=k.zdid and z.jzname='"+jznameList.get(i-4)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 7:
						sql="select z.area from zhandian z where shi='"+power+"' and jztype='zdlx12' and z.jzname='"+jznameList.get(i-4)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 8:
						sql="select k.ysymj from zhandiankz k,zhandian z,indexs i where shi='"+power+"' and jztype='zdlx12' and  z.jzname='"+jznameList.get(i-4)+"' and k.ydlx=i.code and z.id=k.zdid and z.jzname='"+jznameList.get(i-4)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 9:
						sql=" select sum(a.blhdl) from zhandian z, ammeterdegrees a, dianbiao d,electricfees e " +
					" where z.jztype = 'zdlx12' and z.jzname='"+jznameList.get(i-4)+"' and z.shi = '"+power+"' and  z.id = d.zdid and d.dbid= a.ammeterid_fk " +
					"and  e.manualauditstatus >= '1' and z.xuni='0' and d.dbyt='dbyt01' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 10:
						break;
					case 11:
						break;
					case 12:
						break;
					case 13:
						break;
					case 14:
						break;
					case 15:
						break;
					case 16:
						break;
					case 17:
						sql=" select sum(e.actualpay) from zhandian z, ammeterdegrees a, dianbiao d,electricfees e" +
						" where z.jztype = 'zdlx12' and  z.jzname='"+jznameList.get(i-4)+"' and z.shi = '"+power+"'  and z.id = d.zdid   and d.dbid= a.ammeterid_fk " +
						"and a.ammeterdegreeid=e.ammeterdegreeid_fk  and e.manualauditstatus>='1' and z.xuni='0' and d.dbyt='dbyt01' "+str ;
						rs=stmt.executeQuery(sql);
							while(rs.next()){
								content = rs.getString(1);
							}
							break;
					case 18:
						break;
					case 19:
						break;
					case 20:
						content = month;
						break;
					case 21:
						Calendar calendar = Calendar.getInstance();
						String currentDate = DateFormat.getDateInstance().format(calendar.getTime());//格式化当前日期
						content = currentDate;
						break;
					case 22:
						sql = "select d.deptname from account a,department d where a.bumen = d.deptcode and a.shi='"+power+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 23:
						content = getAccount().getName();
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
