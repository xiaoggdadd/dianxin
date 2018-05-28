package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 采集基站用电量信息汇总表.xls（表十七）
 * @author llx
 *
 */
public class CJJZYDLXXHZB_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book) throws Exception {
		
		String month = getMonth();
		
		String power = getAccount().getAccountId();
		
		WritableSheet sheet = book.getSheet(0);
		DecimalFormat decimalFormat=new DecimalFormat("0.00");
		
			for(int i = 3;i<25;i++){					//行
				Integer countJiZHan = null;
				Double SumDuShu = null;
				for(int r = 2;r<8;r++){				//列
					String num = i-3>=9?String.valueOf(i-2):0+""+(i-2);
					System.out.println("i--"+i+"num--"+num);
					String sql = "";
					String content = "";
					ResultSet rs = null;
					System.out.println("rrrrrr"+r);
					switch(r){
					case 2:
						sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx08'and v.xlx = 'jzlx"+num+"' and ((v.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+power+"'))) and v.jnglmk = '否' and v.bgsign = '1'  and v.xuni='0' and v.caiji='1'";
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							countJiZHan = Integer.valueOf(content);
						}
						break;
					case 3:
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '否' and v.bgsign = '1')and v1.shi = '"+power+"'and a.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08'";
						sql = "select to_number(sum(a.actualdegree))from ammeterdegrees a, allinfo_view v1,zhandian z where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '否' and v.bgsign = '1'  and v.xuni='0')  and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+power+"'))) and v1.dbid = a.ammeterid_fk and v1.zdcode = z.zdcode and z.xlx = 'jzlx"+num+"' and v1.jztype = 'zdlx08' and z.caiji='1' and to_char(a.thisdatetime,'yyyy-mm-dd')<='"+month+"-31"+"'  and to_char(a.thisdatetime,'yyyy-mm-dd')>='"+month+"-01"+"'";

						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '否' and v.bgsign = '1')and v1.shi = '"+power+"'and a.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current = 0d;
						if(rs.next()){
							current += rs.getDouble(1);
							System.out.println("--"+rs.getDouble(1));
						}
						System.out.println("---"+current);
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
						sql = "select count(v.id) from zhandian v where v.jztype = 'zdlx08'and v.xlx = 'jzlx"+num+"' and ((v.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+power+"'))) and v.jnglmk = '是' and v.bgsign = '1' and caiji='1' and v.xuni='0'";
						rs = stmt.executeQuery(sql);
						if(rs.next()){
							content = rs.getInt(1)+"";
							countJiZHan = Integer.valueOf(content);
						}
						break;
					case 6:
						sql = "select to_number(sum(a.actualdegree))from ammeterdegrees a, allinfo_view v1,zhandian z where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '是' and v.bgsign = '1'  and v.xuni='0')and ((z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+power+"'))) and v1.dbid = a.ammeterid_fk and v1.zdcode = z.zdcode and z.xlx = 'jzlx"+num+"' and v1.jztype = 'zdlx08' and caiji='1' and to_char(a.thisdatetime,'yyyy-mm-dd')<='"+month+"-31"+"'  and to_char(a.thisdatetime,'yyyy-mm-dd')>='"+month+"-01"+"'";
						//下边这句加上了时间限制条件，为真正的生产环境所使用
						//sql = "select to_number(a.actualdegree)from ammeterdegrees a, allinfo_view v1 where a.ammeterid_fk in (select v.dbid from allinfo_view v where v.jnglmk = '是' and v.bgsign = '1')and v1.shi = '"+power+"'and a.manualauditstatus >= '1'and v1.dbid = a.ammeterid_fk and v1.jztype = 'zdlx08' and to_date(a.inputdatetime, 'yyyy-mm-dd hh24:mi:ss') between(select sysdate - 120 from dual) and (select sysdate - 90 from dual)";
						rs = stmt.executeQuery(sql);
						Double current1 = 0d;
						if(rs.next()){
							current1 = rs.getDouble(1);
						}
						SumDuShu = current1;
						content = current1+"";
						break;
					case 7:
						if(null!=countJiZHan&&null!=SumDuShu)
							if(0==countJiZHan)
								content = "0.0";
							else
								content = decimalFormat.format(SumDuShu/countJiZHan)+"";
						break;
					}
					jxl.write.Number currentCell = new jxl.write.Number(r,i,Double.valueOf(content));
					sheet.addCell(currentCell);
					System.out.println(sql);
				}
			}
	}
}
