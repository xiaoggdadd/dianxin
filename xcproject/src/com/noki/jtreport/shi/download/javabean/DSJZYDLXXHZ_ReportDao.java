package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 地市基站用电量信息汇总表.xls（表一）
 * @author 王海2012-01
 *
 */
public class DSJZYDLXXHZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) throws Exception {
		System.out.println("子类");
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
			String shiName = "";
			String shiNameSql = "select distinct v.shiname from Allinfo_View v where v.shi = '"+power+"' ";
			ResultSet shiNameResult = stmt.executeQuery(shiNameSql);
			if(shiNameResult.next()){
				shiName = shiNameResult.getString(1);
			}
			sheet.addCell(new Label(0,3,shiName));		//添加城市名称
			for(int i = 3;i<25;i++){					//行
				Integer countJiZHan = null;
				Double SumDuShu = 0.0;
				for(int r = 2;r<18;r++){				//列
					String num = i-3>=9?String.valueOf(i-2):0+""+(i-2);
					System.out.println("i--"+i+"num--"+num);
					String sql = "";
					String content = "";
					ResultSet rs = null;
					
					switch(r){
					case 2:
						sql = "select count(*) from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and z.jnglmk = '0' and z.bgsign = '1'  and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1'and z.id=d.zdid and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							countJiZHan = Integer.valueOf(content);
						}
						break;
					case 3:
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08'";
						sql = "select sum(a.blhdl)from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and z.jnglmk = '0' and z.bgsign = '1'  and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1'and z.id=d.zdid and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;

						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current = rs.getDouble(1);
							};
							
						}
						SumDuShu = current;
						content = current+"";
						break;
					case 4:
						if(null!=countJiZHan&&null!=SumDuShu)
							if(0==countJiZHan)
								content = "0.0";
							else
								content = decimalFormat.format(SumDuShu/countJiZHan)+"";
						break;
					case 5:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, dianbiao d,zhandian z where a.ammeterid_fk =d.dbid and  z.jnglmk = '0' and z.bgsign = '1'  and z.xuni='0' and z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'  and z.jztype = 'zdlx08' and z.xlx = 'jzlx"+num+"' and z.id=d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '1') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
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
						
					case 6:
						//sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx08'and v.xlx = 'jzlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '1' and v.bgsign = '1'  and v.xuni='0'";
						sql = "select count(*) from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '1' and z.bgsign = '1'  and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1'and  z.id=d.zdid and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							countJiZHan = Integer.valueOf(content);
						}
						break;
					case 7:
						sql = "select sum(a.blhdl)from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '1' and z.bgsign = '1'  and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1'and  z.id=d.zdid and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '1' and v.bgsign = '1')and v1.shi = '"+power+"'and e.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current1 = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current1 = rs.getDouble(1);
							};
						}
						SumDuShu = current1;
						content = current1+"";
						break;
					case 8:
						if(null!=countJiZHan&&null!=SumDuShu)
							if(0==countJiZHan)
								content = "0.0";
							else
								content = decimalFormat.format(SumDuShu/countJiZHan)+"";
						break;
					case 9:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, dianbiao d,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '1' and z.bgsign = '1'  and z.xuni='0' and z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'  and z.jztype = 'zdlx08' and z.xlx = 'jzlx"+num+"' and z.id=d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '1' and v.bgsign = '1') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						String sumMoney1="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney1=rs.getString(1);
							};
						}				
						content = sumMoney1;
						break;
					case 10:
						//sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx08'and v.xlx = 'jzlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '0' and v.bgsign = '0'  and v.xuni='0'";
						sql = "select count(*) from ammeterdegrees a,dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '0' and z.bgsign = '0'  and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1' and d.zdid=z.id and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							countJiZHan = Integer.valueOf(content);
						}
						break;
					case 11:
						sql = "select sum(a.blhdl)from ammeterdegrees a,dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '0' and z.bgsign = '0'  and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1' and d.zdid=z.id and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '0')and v1.shi = '"+power+"'and e.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current2 = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current2 = rs.getDouble(1);
							};
						}
						SumDuShu = current2;
						content = current2+"";
						break;
					case 12:
						if(null!=countJiZHan&&null!=SumDuShu)
							if(0==countJiZHan)
								content = "0.0";
							else
								
								content = decimalFormat.format(SumDuShu/countJiZHan)+"";
						break;
					case 13:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, dianbiao d,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '0' and z.bgsign = '0' and z.xuni='0' and z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1' and  z.jztype = 'zdlx08' and z.xlx = 'jzlx"+num+"' and z.id=d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '0' and v.bgsign = '0') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						String sumMoney2="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney2=rs.getString(1);
							};
						}				
						content = sumMoney2;
						break;	
					case 14:
						//sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx08'and v.xlx = 'jzlx"+num+"' and v.shi = '"+power+"' and v.jnglmk = '1' and v.bgsign = '0' and v.xuni='0'";
						sql = "select count(*) from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and z.jnglmk = '1' and z.bgsign = '0' and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1'and  z.id=d.zdid and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							countJiZHan = Integer.valueOf(content);
						}
						break;
					case 15:
						sql = "select sum(a.blhdl)from ammeterdegrees a, dianbiao d,electricfees e,zhandian z where a.ammeterid_fk=d.dbid and z.jnglmk = '1' and z.bgsign = '0' and z.xuni='0' and z.shi = '"+power+"'and e.manualauditstatus >= '1'and  z.id=d.zdid and z.xlx = 'jzlx"+num+"' and z.jztype = 'zdlx08' and a.ammeterdegreeid=e.ammeterdegreeid_fk "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '1' and v.bgsign = '0')and v1.shi = '"+power+"'and e.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current3 = 0d;
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								current3 = rs.getDouble(1);
							};
						}
						SumDuShu = current3;
						content = current3+"";
						break;
					case 16:
						if(null!=countJiZHan&&null!=SumDuShu)
							if(0==countJiZHan)
								content = "0.0";
							else
								content = decimalFormat.format(SumDuShu/countJiZHan)+"";
						break;
					case 17:
						sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, dianbiao d,zhandian z where a.ammeterid_fk=d.dbid and  z.jnglmk = '1' and z.bgsign = '0' and z.xuni='0' and z.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1'  and z.jztype = 'zdlx08' and z.xlx = 'jzlx"+num+"' and z.id=d.zdid "+str;
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select sum(e.actualpay)  from ammeterdegrees a, electricfees e, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '1' and v.bgsign = '0') and v1.shi = '" + power + "' and e.ammeterdegreeid_fk = a.ammeterdegreeid and e.manualauditstatus >= '1' and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx" + num + "' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
							
						rs = stmt.executeQuery(sql);
						String sumMoney3="0.0";
						if(rs.next()){
							if(null!=rs.getString(1)&&rs.getString(1)!=""){
								sumMoney3=rs.getString(1);
							};
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
