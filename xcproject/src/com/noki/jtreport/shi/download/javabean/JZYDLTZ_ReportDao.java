package com.noki.jtreport.shi.download.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.*;

import com.noki.jtreport.shi.JZYDLTZbean;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *  基站用电量台账.xls
 * @author 
 *
 */
public class JZYDLTZ_ReportDao extends ReportDao{
	@Override
	protected void doQuery(WritableWorkbook book) throws Exception{
		
	 WritableSheet sheet=book.getSheet(0);
	 List<JZYDLTZbean> list=new ArrayList();
	 String month=getMonth();
	 String accountmonth=getAccountmonth();
//	 if("".equals(month)||null==month){
//		 month=accountmonth;
//	 }
	 String aa="";
	 String aaa="";
	 String aaaa="";
	 String year="";
	 if(!"".equals(month)&&null!=month){
		 year=month.substring(0, 4);
	 }else{
		 year=accountmonth.substring(0, 4);
	 }
		year=year+"-01";
	 
	 if(!"".equals(month)&&null!=month){
		 aa=" AND TO_CHAR(DL.ENDMONTH,'yyyy-mm')='"+month+"'";
		 aaa=" AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')<='"+month+"' ";
		 aaaa=" AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')>='"+year+"' AND TO_CHAR(AA.ENDMONTH,'yyyy-mm')<='"+month+"'";
	 }
	 if(!"".equals(accountmonth)&&null!=accountmonth){
		 aa+=" AND TO_CHAR(E.ACCOUNTMONTH,'yyyy-mm')='"+accountmonth+"'";
		 aaa+=" AND TO_CAHR(EE.ACCOUNTMONTH,'yyyy-mm')<='"+accountmonth+"'";
		 aaaa+=" AND TO_CHAR(EE.ACCOUNTMONTH,'yyyy-mm')>='"+year+"' AND TO_CHAR(EE.ACCOUNTMONTH,'yyyy-mm')<='"+accountmonth+"'";
	 }
	
	 String power=getAccount().getShi();
	 DecimalFormat dlmat=new DecimalFormat("0.0");
	 DecimalFormat dfmat=new DecimalFormat("0.00");
     String sql="SELECT Z.JZNAME, (SELECT I.NAME   FROM INDEXS I  WHERE I.CODE = Z.STATIONTYPE AND I.TYPE = 'stationtype')AS STATIONTYPE," +
     		"(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = Z.SHI)AS SHI," +
     		"(SELECT DA.AGNAME FROM D_AREA_GRADE DA WHERE DA.AGCODE = Z.XIAN)AS XIAN, " +
     		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = Z.XLX AND I.TYPE = 'XLX')AS XLX, " +
     		"(SELECT I.NAME FROM INDEXS I WHERE I.CODE = Z.ZDCQ AND I.TYPE = 'ZDCQ')AS ZDCQ," +
     		" (CASE WHEN Z.BGSIGN = 1 THEN  '是'  ELSE '否'END)AS BG, TO_CHAR(DL.LASTDATETIME,'yyyy-mm-dd'),DL.LASTDEGREE," +
     		"TO_CHAR(DL.THISDATETIME,'yyyy-mm-dd'),DL.THISDEGREE,Z.EDHDL,DL.BLHDL,Z.DIANFEI,E.ACTUALPAY,(SELECT I.NAME FROM INDEXS I WHERE I.CODE = ZD.FKZQ AND I.TYPE = 'FKZQ')AS FKZQ, " +
     		"TO_CHAR(E.PAYDATETIME,'yyyy-mm-dd'),  E.MEMO,Z.ZDCODE,HZ1.ACTUAL,HZ2.ACTUAL  " +
     		"FROM ZHANDIAN       Z,ZDDFINFO       ZD,DIANBIAO       D, DIANDUVIEW DL,DIANFEIVIEW   E,(SELECT ZZ.ID, SUM(EE.ACTUALPAY) AS ACTUAL FROM ZHANDIAN ZZ, DIANBIAO DD, DIANDUVIEW AA, DIANFEIVIEW EE " +
     		"WHERE ZZ.ID = DD.ZDID AND DD.DBID = AA.AMMETERID_FK  AND AA.AMMETERDEGREEID = EE.AMMETERDEGREEID_FK  AND ZZ.SHI = '"+power+"' "+aaa+" GROUP BY ZZ.ID) HZ1," +
     		"(SELECT ZZ.ID, SUM(EE.ACTUALPAY) AS ACTUAL FROM ZHANDIAN ZZ, DIANBIAO DD, AMMETERDEGREES AA, ELECTRICFEES EE " +
     		"WHERE ZZ.ID = DD.ZDID  AND DD.DBID = AA.AMMETERID_FK AND AA.AMMETERDEGREEID = EE.AMMETERDEGREEID_FK AND ZZ.SHI = '"+power+"' "+aaaa+" GROUP BY ZZ.ID) HZ2 " +
     		"WHERE Z.ID = D.ZDID AND Z.ID = ZD.ID(+)  AND D.DBID = DL.AMMETERID_FK " +
     		"AND DL.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.SHI='"+power+"' "+aa+" AND Z.JZTYPE = 'zdlx08' AND HZ1.ID = Z.ID AND HZ2.ID = Z.ID " +
     		"ORDER BY Z.XIAN, Z.JZNAME";	
        ResultSet rs=null;
        try {
        	System.out.println("--"+sql.toString());
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				JZYDLTZbean bean=new JZYDLTZbean();
				bean.setJzname(rs.getString(1));
				bean.setZdlx(rs.getString(2));
				bean.setShi(rs.getString(3));
				bean.setXian(rs.getString(4));
				bean.setJzlx(rs.getString(5));
				bean.setZdcq(rs.getString(6));
				bean.setBg(rs.getString(7));
				bean.setLasttime(rs.getString(8));
				bean.setLastdegree(rs.getString(9));
				bean.setThistime(rs.getString(10));
				bean.setThisdegee(rs.getString(11));
				bean.setEdhdl(rs.getString(12));
				bean.setBlhdl(rs.getString(13));
				bean.setDanjia(rs.getString(14));
				bean.setActualpay(rs.getString(15));
				bean.setFkzq(rs.getString(16));
				bean.setPaydatetime(rs.getString(17));
				bean.setMemo(rs.getString(18));
				bean.setZdcode(rs.getString(19));
				bean.setHdl1(rs.getString(20));
				bean.setHdl2(rs.getString(21));
				list.add(bean);
				
			}
			
			System.out.println("--"+list.toString());
			for(int i=3;i<list.size()+3;i++){
				
				JZYDLTZbean	bean=list.get(i-3);
				for(int j=0;j<26;j++){
					String content="";
					switch(j){
					
					case 0:
						content=i-2+"";
						break;
					case 1:
						content=bean.getJzname();
						break;
					case 2:
						content=bean.getZdlx();
						break;
					case 3:
						content=bean.getShi();
						break;
					case 4:
						content=bean.getXian();
						break;
					case 5:
						content=bean.getJzlx();
						break;
					case 6:
						content=bean.getZdcq();
						break;
					case 7:
						break;
					case 8:
						break;
					case 9:
						content=bean.getBg();
						break;
					case 10:
						content=bean.getLasttime();
						break;
					case 11:
						content=bean.getLastdegree();
						content=dlmat.format(Double.parseDouble(bean.getLastdegree()));
						break;
					case 12:
						content=bean.getThistime();
						break;
					case 13:
						content=bean.getThisdegee();
						content=dlmat.format(Double.parseDouble(bean.getThisdegee()));
						break;
					case 14:
						content=bean.getEdhdl();
						if(null==content ||content.equals("")||content.equals("null"))content="0";
						content=dlmat.format(Double.parseDouble(content));
						break;
					case 15:
						content=bean.getBlhdl();
						content=dlmat.format(Double.parseDouble(content));
						break;
					case 16:
						content=bean.getDanjia();
						break;
					case 17:
						content=bean.getActualpay();
						content=dfmat.format(Double.parseDouble(bean.getActualpay()));
						break;
					case 18:
						content=bean.getFkzq();
						break;
					case 19:
						content=bean.getActualpay();
						content=dfmat.format(Double.parseDouble(bean.getActualpay()));
						break;
					case 20:
						content=bean.getPaydatetime();
						break;
					case 21:
//						content=bean.getZdcode();
//						String sql1="SELECT SUM(E.ACTUALPAY) FROM ZHANDIAN Z, DIANBIAO D, AMMETERDEGREES DL, ELECTRICFEES E WHERE Z.ID = D.ZDID  AND D.DBID = DL.AMMETERID_FK  AND DL.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND Z.ZDCODE = '"+content+"' "+aaa;
//						System.out.println("sql1"+sql1.toString());
//						rs=stmt.executeQuery(sql1);
//						while(rs.next()){
//							content=dfmat.format(Double.parseDouble(rs.getString(1)));
//						}
						content=bean.getHdl1();
						content=dfmat.format(Double.parseDouble(content));
						break;
					case 22:
						content=bean.getFkzq();
						break;
					case 23:
						content=bean.getDanjia();
						break;
					case 24:
						content=bean.getMemo();
						break;
					case 25:
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
						content=bean.getHdl2();
						content=dfmat.format(Double.parseDouble(content));
						break;
					}
					Label label = new Label(j,i,content);
					sheet.addCell(label);
				
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
