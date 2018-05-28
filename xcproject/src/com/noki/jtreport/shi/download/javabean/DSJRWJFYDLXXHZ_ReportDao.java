package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 地市接入网机房用电量信息汇总.xls（表十五）
 * @author 王海2012-01
 *
 */
public class DSJRWJFYDLXXHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) throws Exception {
		String month = getMonth();
		String accountmonth=getAccountmonth();
		String str="";
		if(!"".equals(month)&&null!=month){
			str=" and to_char(a.endmonth,'yyyy-mm')='"+month+"'";
		}
		if(!"".equals(accountmonth)&&null!=accountmonth){
			str=str+" and to_char(e.accountmonth,'yyyy-mm')='"+accountmonth+"'";
		}
		String power = getAccount().getShi();
		WritableSheet sheet = book.getSheet(0);
		DecimalFormat decimalFormat=new DecimalFormat("0.0");
			for(int i = 3;i<16;i++){					//行
				Integer countJiZHan = null;
				Double SumDuShu = null;
				for(int r = 2;r<22;r++){				//列
					String num = i-3>=9?String.valueOf(i-2):0+""+(i-2);
					System.out.println("i--"+i+"num--"+num);
					String sql = "";
					String content = "";
					ResultSet rs = null;
					
					switch(r){
					case 2:
						sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx07'and v.jrwtype = 'jrwlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '0' and v.bgsign = '1'  and v.xuni='0'";
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							 
						}
						break;
					case 3:
						content="0";
						break;
					case 4:
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08'";
						sql = "select sum(a.blhdl)from ammeterdegrees a,dianbiao d,electricfees e,zhandian z where  z.shi = '"+power+"'and e.manualauditstatus >= '1' and d.dbid = a.ammeterid_fk   and z.jnglmk = '0'  and z.bgsign = '1' and z.xuni = '0' and z.id = d.zdid and z.jrwtype = 'jrwlx"+num+"' and z.jztype = 'zdlx07' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;

						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current = 0d;
						if(rs.next()){
							current = rs.getDouble(1);
						}
						SumDuShu = current;
						content = current+"";
						break;
					case 5:
						content="0";
						break;
						
					case 6:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e,dianbiao d,zhandian z where  z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'   and z.jnglmk = '0'  and z.bgsign = '1' and z.xuni = '0' and d.dbid = a.ammeterid_fk and z.jztype = 'zdlx07' and z.jrwtype = 'jrwlx"+num+"' and z.id = d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus = '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						System.out.println("++++++++++++++++++++++++++++++");
						String sumMoney="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney=rs.getString(1);
							};
							System.out.println("++++++++++++++++++++++++++++++"+sumMoney);
						}				
						content = sumMoney;
						break;
					case 7:
						sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx07'and v.jrwtype = 'jrwlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '1' and v.bgsign = '1'  and v.xuni='0'";
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							 
						}
						break;
					case 8:
						content="0";
						break;
					case 9:
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx07'";
						sql = "select to_number(a.blhdl)from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where  z.shi = '"+power+"'and e.manualauditstatus >= '1'  and z.jnglmk = '1'  and z.bgsign = '1' and z.xuni = '0' and d.dbid = a.ammeterid_fk and z.id = d.zdid and z.jrwtype = 'jrwlx"+num+"' and z.jztype = 'zdlx07' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;

						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx07' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current1 = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current1 += rs.getDouble(1);
							}
							
						}
						SumDuShu = current1;
						content = current1+"";
						break;
					case 10:
						content="0";
						break;
						
					case 11:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, dianbiao d ,zhandian z where   z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'  and z.jnglmk = '1'  and z.bgsign = '1' and z.xuni = '0'  and d.dbid = a.ammeterid_fk and z.jztype = 'zdlx07' and z.jrwtype = 'jrwlx"+num+"' and z.id = d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus = '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						System.out.println("++++++++++++++++++++++++++++++");
						String sumMoney1="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney1=rs.getString(1);
							};
							System.out.println("++++++++++++++++++++++++++++++"+sumMoney1);
						}				
						content = sumMoney1;
						break;
					case 12:
						sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx07'and v.jrwtype = 'jrwlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '0' and v.bgsign = '0'  and v.xuni='0'";
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							 
						}
						break;
					case 13:
						content="0";
						break;
					case 14:
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx07'";
						sql = "select sum(a.blhdl)from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where  z.shi = '"+power+"'and e.manualauditstatus >= '1'  and z.jnglmk = '0'  and z.bgsign = '0' and z.xuni = '0' and d.dbid = a.ammeterid_fk and z.id = d.zdid and z.jrwtype = 'jrwlx"+num+"' and z.jztype = 'zdlx07' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;

						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx07' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current2 = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current2 += rs.getDouble(1);
							}
						}
						SumDuShu = current2;
						content = current2+"";
						break;
					case 15:
						content="0";
						break;
						
					case 16:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, dianbiao d,zhandian z where  z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'   and z.jnglmk = '0'  and z.bgsign = '0' and z.xuni = '0' and d.dbid = a.ammeterid_fk and z.jztype = 'zdlx07' and z.jrwtype = 'jrwlx"+num+"' and z.id = d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus = '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						System.out.println("++++++++++++++++++++++++++++++");
						String sumMoney2="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney2=rs.getString(1);
							};
							System.out.println("++++++++++++++++++++++++++++++"+sumMoney2);
						}				
						content = sumMoney2;
						break;
					case 17:
						sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx07'and v.jrwtype = 'jrwlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '1' and v.bgsign = '0'  and v.xuni='0'";
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							 
						}
						break;
					case 18:
						content="0";
						break;
					case 19:
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx07'";
						sql = "select sum(a.blhdl)from ammeterdegrees a,dianbiao d,electricfees e,zhandian z where   z.shi = '"+power+"'and e.manualauditstatus >= '1'  and z.jnglmk = '1'  and z.bgsign = '0' and z.xuni = '0' and d.dbid = a.ammeterid_fk and z.id = d.zdid and z.jrwtype = 'jrwlx"+num+"' and z.jztype = 'zdlx07' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;

						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus = '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx07' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current3 = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current3 += rs.getDouble(1);
							}
						}
						SumDuShu = current3;
						content = current3+"";
						break;
					case 20:
						content="0";
						break;
						
					case 21:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e,dianbiao d,zhandian z where  z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'    and z.jnglmk = '1'  and z.bgsign = '0' and z.xuni = '0' and d.dbid = a.ammeterid_fk and z.jztype = 'zdlx07' and z.jrwtype = 'jrwlx"+num+"' and z.id = d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, (select * from allinfo_view where rownum < 90000) v1 where a.ammeterid_fk in (select v.dbid from (select * from allinfo_view where rownum < 90000) v where v.jnglmk = '0' and v.bgsign = '1') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus = '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						System.out.println("++++++++++++++++++++++++++++++");
						String sumMoney3="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney3=rs.getString(1);
							};
							System.out.println("++++++++++++++++++++++++++++++"+sumMoney3);
						}				
						content = sumMoney3;
						break;
					}
					jxl.write.Number currentCell = new jxl.write.Number(r,i,Double.valueOf(content));
					sheet.addCell(currentCell);
					System.out.println(sql);
				}
			}
	}
}
