package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.jtreport.shi.JRWJFYDLTZbean;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 接入网机房用电量台账.xls（表十）
 * @author 王海
 *
 */
public class JRWJFYDLTZ_ReportDao extends ReportDao {
	@Override
	protected void doQuery(WritableWorkbook book)throws Exception {
		
		String month=getMonth();
		String accountmonth=getAccountmonth();
//		 if("".equals(month)||null==month){
//			 month=accountmonth;
//		 }
		 String aa="";
		 String aaa="";
		 if(!"".equals(month)&&null!=month){
			 aa=" AND TO_CHAR(DL.ENDMONTH,'yyyy-mm')='"+month+"'";
			 aaa=" AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')<='"+month+"' ";
		 }
		 if(!"".equals(accountmonth)&&null!=accountmonth){
			 aa+=" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')='"+accountmonth+"'";
			 aaa+=" AND TO_CHAR(EE.ACCOUNTMONTH,'yyyy-mm')<='"+accountmonth+"'";
		 }
		String power=getAccount().getShi();
		DecimalFormat dlmat=new DecimalFormat("0.0");
		DecimalFormat dfmat=new DecimalFormat("0.00");
		WritableSheet sheet=book.getSheet(0);
		ResultSet rs=null;
		List<JRWJFYDLTZbean> list=new ArrayList();
		 String sql="SELECT Z.JZNAME, (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE =Z.YFLX AND I.TYPE = 'FWLX')AS FWLX," +
  		"(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = Z.SHI)AS SHI," +
  		"(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = Z.XIAN)AS XIAN, " +
  		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = Z.ZDCQ AND I.TYPE = 'ZDCQ')AS ZDCQ,Z.ZLFH,Z.JNGLMK," +
  		" (CASE WHEN Z.BGSIGN = 1 THEN  '是'  ELSE '否'END)AS BG, TO_CHAR(DL.LASTDATETIME,'yyyy-mm-dd'),DL.LASTDEGREE," +
  		"TO_CHAR(DL.THISDATETIME,'yyyy-mm-dd'),DL.THISDEGREE,DL.BLHDL,Z.DIANFEI,E.ACTUALPAY,(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ')AS FKZQ, " +
  		"TO_CHAR(E.PAYDATETIME,'yyyy-mm-dd'),  E.MEMO,Z.ZDCODE, NVL(CEIL(dl.THISDATETIME - dl.LASTDATETIME), 0) AS SSS,HZ1.ACTUAL  " +
  		"FROM ZHANDIAN       Z,ZDDFINFO       ZD,DIANBIAO       D, DIANDUVIEW DL,DIANFEIVIEW   E,(SELECT ZZ.ID, SUM(EE.ACTUALPAY) AS ACTUAL FROM ZHANDIAN ZZ, DIANBIAO DD, DIANDUVIEW AA, DIANFEIVIEW EE " +
  		"WHERE ZZ.ID = DD.ZDID AND DD.DBID = AA.AMMETERID_FK  AND AA.AMMETERDEGREEID = EE.AMMETERDEGREEID_FK  AND ZZ.SHI = '"+power+"' "+aaa+" GROUP BY ZZ.ID) HZ1 " +
  		"WHERE Z.ID = D.ZDID AND Z.ID = ZD.ID(+)  AND D.DBID = DL.AMMETERID_FK " +
  		"AND DL.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.SHI='"+power+"' "+aa+" AND LENGTH(TO_CHAR(DL.LASTDATETIME,'yyyy-mm-dd')) = 10 AND Z.JZTYPE = 'zdlx07' AND HZ1.ID = Z.ID " +
  		"ORDER BY Z.XIAN, Z.JZNAME";
		 System.out.println("sql"+sql.toString());
		 rs=stmt.executeQuery(sql);
		 while(rs.next()){
			 
			JRWJFYDLTZbean bean=new JRWJFYDLTZbean();
			 bean.setJzname(rs.getString(1));
			 bean.setFangwu(rs.getString(2));
			 bean.setShi(rs.getString(3));
			 bean.setXian(rs.getString(4));
			 bean.setZdcq(rs.getString(5));
			 bean.setZlfh(rs.getString(6));
			 bean.setUseenergy(rs.getString(7));
			 bean.setSightcing(rs.getString(8));
			 bean.setLasttime(rs.getString(9));
			 bean.setLastdegree(rs.getString(10));
		     bean.setThistime(rs.getString(11));
			 bean.setThisdegee(rs.getString(12));
		     bean.setBlhdl(rs.getString(13));
			 bean.setDanjia(rs.getString(14));
			 bean.setActualpay(rs.getString(15));
			 bean.setFkzq(rs.getString(16));
			 bean.setPaydatetime(rs.getString(17));
			 bean.setMemo(rs.getString(18));
			 bean.setZdcode(rs.getString(19));
			 bean.setCountdays(rs.getString(20));
			 bean.setHdl1(rs.getString(21));
			 list.add(bean);
		 }
		 for(int i=4;i<list.size()+4;i++){
			 JRWJFYDLTZbean bean=list.get(i-4);
			 for(int j=0;j<31;j++){
				 String content="";
				 switch(j){
				 
				   case 0:
						content=i-3+"";
						break;
					case 1:
						content=bean.getJzname();
						break;
					case 2:
						content=bean.getFangwu();
						break;
					case 3:
						content=bean.getShi();
						break;
					case 4:
						content=bean.getXian();
						break;
					case 5:
						content=bean.getZdcq();
						break;
					case 6:
						content=bean.getZlfh();
						break;
					case 7:
						break;
					case 8:
						break;
					case 9:
						
						break;
					case 10:
						content=bean.getUseenergy();
						break;
					case 11:
						
						break;
					case 12:
						content=bean.getSightcing();
						break;
					case 13:
						content=bean.getLasttime();
						break;
					case 14:
						content=bean.getLastdegree();
						content=dlmat.format(Double.parseDouble(bean.getLastdegree()));
						break;
					case 15:
						content=bean.getThistime();
						
						break;
					case 16:
						content=bean.getThisdegee();
						content=dlmat.format(Double.parseDouble(bean.getThisdegee()));
						break;
					case 17:
						content=bean.getCountdays();
						break;
					case 18:
						
						break;
					case 19:
						content=bean.getBlhdl();
						content=dlmat.format(Double.parseDouble(content));
						break;
					case 20:
						content=bean.getDanjia();
						break;
					case 21:
						content=bean.getActualpay();
						content=dfmat.format(Double.parseDouble(bean.getActualpay()));
						break;
					case 22:
						//content=bean.getFkzq();
						break;
					case 23:
						//content=bean.getDanjia();
						break;
					case 24:
						//content=bean.getMemo();
						break;
					case 25:
						content=bean.getActualpay();
						content=dfmat.format(Double.parseDouble(bean.getActualpay()));
//						content=bean.getZdcode();
//						String year=month.substring(0, 4);
//						year=year+"-01";
//						System.out.println("year"+year);
//						
//						String sql2="SELECT SUM(E.ACTUALPAY) FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES DL, ELECTRICFEES E WHERE Z.ID = D.ZDID  AND D.DBID = DL.AMMETERID_FK  AND DL.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.ZDCODE = '"+content+"' AND DL.ENDMONTH>='"+year+"' AND DL.ENDMONTH<='"+month+"'";
//						System.out.println("sql2"+sql2.toString());
//						rs=stmt.executeQuery(sql2);
//						while(rs.next()){
//							content=dfmat.format(Double.parseDouble(rs.getString(1)));
//						}
						break;
					case 26:
						content=bean.getPaydatetime();
						break;
					case 27:
//						content=bean.getZdcode();
//						String sql1="SELECT SUM(E.ACTUALPAY) FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES DL, ELECTRICFEES E WHERE Z.ID = D.ZDID  AND D.DBID = DL.AMMETERID_FK  AND DL.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.ZDCODE = '"+content+"' "+aaa;
//						System.out.println("sql1"+sql1.toString());
//						rs=stmt.executeQuery(sql1);
//						while(rs.next()){
//							content=dfmat.format(Double.parseDouble(rs.getString(1)));
//						}
						content=dfmat.format(Double.parseDouble(bean.getHdl1()));
						break;
					case 28:
						content=bean.getFkzq();
						break;
					case 29:
						break;
					case 30:
						content=bean.getMemo();
						break;
						
					}
					Label label = new Label(j,i,content);
					sheet.addCell(label);
				 }
			 }
		 }
		 
	}

