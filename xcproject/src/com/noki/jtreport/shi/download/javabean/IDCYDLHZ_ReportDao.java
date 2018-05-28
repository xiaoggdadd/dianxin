package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * IDC用电量汇总表.xls（表四）
 * @author Administrator
 *
 */
public class IDCYDLHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) {
		// TODO Auto-generated method stub
		String power=getAccount().getShi();
		WritableSheet sheet=book.getSheet(0);
		String month = getMonth();
		String accountmonth=getAccountmonth();
		String str="";
		if(!"".equals(month)&&null!=month){
			str=" and to_char(a.endmonth,'yyyy-mm')='"+month+"'";
		}
		if(!"".equals(accountmonth)&&null!=accountmonth){
			str=str+" and to_char(e.accountmonth,'yyyy-mm')='"+accountmonth+"'";
		}
		String gongsi="山东联通公司";
		try {
			
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			Label gongsiLabel = new Label(0,3,gongsi);
			gongsiLabel.setCellFormat(cellFormat);
			sheet.addCell(gongsiLabel);
			
			String shiName="";
			String shinameSql="select distinct shiname from Allinfo_View where shi='"+power+"'";
			ResultSet res=stmt.executeQuery(shinameSql);
			if(res.next()){
				
				shiName=res.getString(1);
			}
			Label shiNameLabel = new Label(1,3,shiName);
			shiNameLabel.setCellFormat(cellFormat);
			sheet.addCell(shiNameLabel);
			
			List<String> jznameList = new ArrayList<String>();//所有的IDC机房
			String sql_jzname="select jzname from zhandian where shi='"+power+"'and jztype='zdlx01' and xuni='0'";
			ResultSet jzNameSet=stmt.executeQuery(sql_jzname);
			while(jzNameSet.next()){
				jznameList.add(jzNameSet.getString(1));
			}
			
			
			Calendar calendar = null;
			Date date = null;
			String currentDate = null;
			
			
			for(int i=3;i<jznameList.size()+3;i++){                //行
				
				for(int r=2;r<31;r++){                   //列
					String sql="";
					String content="";
					ResultSet rs=null;
					switch(r){
					case 2:
						content = jznameList.get(i-3);
						break;
					case 3:
						sql="select i.name from zhandiankz k,zhandian z,indexs i where shi='"+power+"' and jztype='zdlx01' and k.txj=i.code and z.id=k.zdid and z.jzname='"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						
						break;
					case 4:
						sql="select i.name from zhandiankz k,zhandian z,indexs i where shi='"+power+"' and jztype='zdlx01' and k.ydlx=i.code and z.id=k.zdid and z.jzname='"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 5:
						sql="select area from zhandian where shi='"+power+"'and jztype='zdlx01'and jnglmk='是' and xuni='0' and jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 6:
						sql="select k.ysymj from zhandian z,zhandiankz k where z.shi='"+power+"'and z.jztype='zdlx01'and z.jnglmk='是' and z.xuni='0' and z.id=k.zdid and z.jzname = '"+jznameList.get(i-3)+"'";
						System.out.println("--"+sql);
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 7:
						sql="select k.jggs from zhandian z,zhandiankz k where z.shi='"+power+"'and z.jztype='zdlx01'and z.jnglmk='是' and z.xuni='0' and z.id=k.zdid and z.jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 8:
						sql="select k.ysygs from zhandian z,zhandiankz k where z.shi='"+power+"'and z.jztype='zdlx01'and z.jnglmk='是' and z.xuni='0' and z.id=k.zdid and z.jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 9:
						sql="select k.ugs from zhandiankz k,zhandian z where shi='"+power+"' and jztype='zdlx01' and z.jnglmk='是' and z.xuni='0' and z.id=k.zdid and z.jzname='"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 10:
						sql="select k.ysyugs from zhandiankz k,zhandian z where shi='"+power+"' and jztype='zdlx01' and z.jnglmk='是' and z.xuni='0'  and z.id=k.zdid and z.jzname='"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 11:
						
						sql="select sum(a.blhdl) as degree  from Allinfo_View v,Ammeterdegrees a,electricfees e where v.shi='"+power+"' and v.dbid=a.ammeterid_fk and a.AMMETERDEGREEID=e.AMMETERDEGREEID_FK and v.jztype='zdlx01' and v.jnglmk='是' and v.xuni='0' and v.jzname ='"+jznameList.get(i-3)+"' and e.manualauditstatus >= '1' "+str;
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 15:
						sql="select sum(e.ACTUALPAY) as frees  from Allinfo_View v,Ammeterdegrees a,electricfees e where v.shi='"+power+"' and v.dbid=a.ammeterid_fk and a.AMMETERDEGREEID=e.AMMETERDEGREEID_FK and  v.jztype='zdlx01' and v.jnglmk='是' and v.jzname ='"+jznameList.get(i-3)+"' and e.manualauditstatus >= '1' "+str;
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 16:
						sql="select area from zhandian where shi='"+power+"'and jztype='zdlx01'and jnglmk='否' and jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 17:
						sql="select k.ysymj from zhandian z,zhandiankz k where z.shi='"+power+"'and z.jztype='zdlx01'and z.jnglmk='否' and z.id=k.zdid and z.jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 18:
						sql="select k.jggs from zhandian z,zhandiankz k where z.shi='"+power+"'and z.jztype='zdlx01'and z.jnglmk='否' and z.id=k.zdid and z.jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 19:
						sql="select k.ysygs from zhandian z,zhandiankz k where z.shi='"+power+"'and z.jztype='zdlx01'and z.jnglmk='否' and z.id=k.zdid and z.jzname = '"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 20:
						sql="select k.ugs from zhandiankz k,zhandian z where shi='"+power+"' and jztype='zdlx01' and z.jnglmk='否' and z.xuni='0' and z.id=k.zdid and z.jzname='"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 21:
						sql="select k.ysyugs from zhandiankz k,zhandian z where shi='"+power+"' and jztype='zdlx01' and z.jnglmk='否' and z.xuni='0'  and z.id=k.zdid and z.jzname='"+jznameList.get(i-3)+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 22:
					   System.out.println();
						sql="select sum(a.blhdl) as degree  from Allinfo_View v,Ammeterdegrees a,electricfees e where v.shi='"+power+"' and v.dbid=a.ammeterid_fk and v.jztype='zdlx01' and a.AMMETERDEGREEID=e.AMMETERDEGREEID_FK and v.jnglmk='否' and v.xuni='0'  and v.jzname ='"+jznameList.get(i-3)+"' and e.manualauditstatus >= '1' and to_char(a.endmonth,'yyyy-mm')='"+month+"'";
						 System.out.println("$%%"+sql);
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 26:
						sql="select sum(e.ACTUALPAY) as frees  from Allinfo_View v,Ammeterdegrees a,electricfees e where v.shi='"+power+"' and v.dbid=a.ammeterid_fk and a.AMMETERDEGREEID=e.AMMETERDEGREEID_FK and  v.jztype='zdlx01' and v.jnglmk='否' and  v.xuni='0' and v.jzname ='"+jznameList.get(i-3)+"' and e.manualauditstatus >= '1' and to_char(a.endmonth,'yyyy-mm')='"+month+"'";
						 System.out.println("@%"+sql);
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 27:
					
						content = month;
						break;
					case 28:
						calendar = Calendar.getInstance();
						date = calendar.getTime();
						currentDate = DateFormat.getDateInstance().format(date);//格式化当前日期
						content = currentDate;
						break;
					case 29:
						sql = "select d.deptname from account a,department d where a.bumen = d.deptcode and a.shi='"+power+"'";
						rs=stmt.executeQuery(sql);
						while(rs.next()){
							content = rs.getString(1);
						}
						break;
					case 30:
						content = getAccount().getName();
						break;
					}
					Label currentCell = new Label(r,i,content);
					currentCell.setCellFormat(cellFormat);
					
					sheet.addCell(currentCell);
					
				}
				
			}
			sheet.mergeCells(0, 3, 0, 2+jznameList.size());
			sheet.mergeCells(1, 3, 1, 2+jznameList.size());
			sheet.mergeCells(27, 3, 27, 2+jznameList.size());
			sheet.mergeCells(28, 3, 28, 2+jznameList.size());
			sheet.mergeCells(29, 3, 29, 2+jznameList.size());
			sheet.mergeCells(30, 3, 30, 2+jznameList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
