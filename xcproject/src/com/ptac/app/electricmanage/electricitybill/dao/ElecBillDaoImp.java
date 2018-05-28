package com.ptac.app.electricmanage.electricitybill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.BasicInfo;

import com.ptac.app.electricmanage.electricitybill.bean.DomainOther;
import com.ptac.app.electricmanage.electricitybill.bean.DomainType;

import com.ptac.app.electricmanage.electricitybill.bean.ElecQuery;

import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author lijing
 * @see ��ѵ�dao��ʵ����
 */
public class ElecBillDaoImp implements ElecBillDao {

	/**
	 * @author lijing
	 * @see վ�����Ƶ�ģ����ѯ
	 * @param loginId String Ȩ��,jzname String ����ģ����ѯ,str String ��������
	 * @return list ����һ��վ�����Ƶ�list
	 */
	@Override
	public ArrayList checkMh(String loginId, String jzname, String str) {
		DataBase db = new DataBase();
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		String zdmc = jzname;
		if (zdmc.contains("?")) {
			StringBuffer zdmc1 = new StringBuffer(zdmc);
			zdmc = zdmc1.substring(0, zdmc1.lastIndexOf("?"));
		}
		sql.append("SELECT JZNAME,d.dbid,d.dbname,z.zdcode FROM ZHANDIAN Z, DIANBIAO D WHERE  "
						+ "Z.ID = D.ZDID AND Z.SHSIGN = '1' AND Z.PROVAUDITSTATUS='1'  AND Z.QYZT='1' AND Z.XUNI = '0' AND Z.CAIJI = '0' AND D.DBYT = 'dbyt01'  AND D.DBQYZT='1' "
						+ str
					//	+ "AND (D.DFZFLX = 'dfzflx01' OR D.DFZFLX = 'dfzflx03') " +
						+"AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"
						+ loginId + "')");

		if (!"������".equals(jzname)) {
			sql.append(" AND JZNAME LIKE '%" + zdmc + "%'");
		}

		sql.append(" ORDER BY JZNAME");
		
		ResultSet rs = null;
		
		try {
			System.out.println("ģ����ѯվ��:"+sql);
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
			
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ�������Ϣ�Ĳ�ѯ
	 * 
	 * @param dbid String ��������
	 * 
	 * @return bean ����һ��BasicInfo��bean
	 * 
	 * @update WangYiXiao 2014-4-9 ɾ��pueֵ
	 */
	@Override
	public BasicInfo bas(String dbid) {
		BasicInfo bean = new BasicInfo();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("SELECT (SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.SHI) ||"
						+ "(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.XIAN) ||"
						+ "(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.XIAOQU) AREA,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') STATIONTYPE,"
						+ "ZD.JZNAME JZNAME,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.JZTYPE AND TYPE = 'ZDLX') JZTYPE,"
						+ "(SELECT IND.NAME FROM INDEXS IND WHERE IND.CODE = ZDINFO.FKZQ AND TYPE = 'FKZQ') FKZQ,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE=D.DFZFLX AND TYPE='dfzflx')AS DFZFLX,D.DFZFLX DFZFLXA,"
						+ "D.BEILV,"
						+ "(SELECT IND.NAME FROM INDEXS IND WHERE IND.CODE = D.LINELOSSTYPE AND TYPE = 'xslx') XSLX,"
						+ "D.LINELOSSVALUE XSZ,D.DBID,D.BGSIGN BB,ZD.EDHDL EDHDL,D.CSDS,TO_CHAR(D.CSSYTIME,'yyyy-mm-dd') CSSYTIME,D.DANJIA,ZD.QSDBDL,ZD.ZLFH,ZD.JLFH,ZD.SHI,D.DBDS,D.XGBZW,ZD.STATIONTYPE AS ZDLXBM,ZD.GDFS, " 
						+"(SELECT NAME FROM INDEXS WHERE CODE = ZD.GDFS AND TYPE = 'GDFS') GDFSNAME, ZD.PROPERTY PROPERTYCODE,ZD.SCB SCB "
						+ " FROM ZDDFINFO  ZDINFO, DIANBIAO D,ZHANDIAN ZD,D_AREA_GRADE DAG"
						+ " WHERE d.zdid=zd.id and zd.xian=dag.agcode "
						+ " and zd.id =zdinfo.zdid(+) and d.dbid = '"
						+ dbid
						+ "' and d.dbyt='dbyt01'");

		System.out.println("������Ϣsql1:"+sql1);

		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql1.toString());

			while (rs.next()) {
				bean.setArea(rs.getString("area") != null ? rs
						.getString("area") : "");
				bean.setStationtype(rs.getString("stationtype") != null ? rs
						.getString("stationtype") : "");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME")
						: "");
				bean.setJztype(rs.getString("jztype") != null ? rs
						.getString("jztype") : "");
				bean.setFkzq(rs.getString("fkzq") != null ? rs
						.getString("fkzq") : "");
				bean.setDfzflx(rs.getString("dfzflx") != null ? rs
						.getString("dfzflx") : "");
				bean.setDfzflxcode(rs.getString("dfzflxa")!=null ? rs.getString("dfzflxa"):"");
				bean.setBeilv(rs.getString("beilv") != null ? rs
						.getString("beilv") : "");
				bean.setLinelosstype(rs.getString("xslx") != null ? rs
						.getString("xslx") : "");
				bean.setLinelossvalue(rs.getString("xsz") != null ? rs
						.getString("xsz") : "");
				bean.setDbid(rs.getString("dbid") != null ? rs
						.getString("dbid") : "");
				bean.setShifou(rs.getString("bb") != null ? rs.getString("bb")
						: "");
				bean.setEdhdl(rs.getString("edhdl") != null ? rs
						.getString("edhdl").trim() : "");
				bean.setCsds(rs.getString("csds") != null ? rs
						.getString("csds") : "");
				bean.setCssytime(rs.getString("cssytime") != null ? rs
						.getString("cssytime") : "");
				bean.setDianfei(rs.getString("danjia") != null ? rs
						.getString("danjia").trim() : "");
				bean.setQsdbdl(rs.getString("qsdbdl") !=null ? rs.getString("qsdbdl").trim() : "0.00");
				bean.setZlfh(rs.getString("zlfh") != null ?  rs.getString("zlfh") : "0.00");
				bean.setJlfh(rs.getString("jlfh") != null ?  rs.getString("jlfh") : "0.00");
				bean.setShi(rs.getString("shi") != null ?  rs.getString("shi") : "");
				bean.setDbds(rs.getString("dbds") != null ?  rs.getString("dbds") : "");
				bean.setXgbzw(rs.getString("xgbzw") != null ?  rs.getString("xgbzw") : "");
				bean.setZdlxbm(rs.getString("zdlxbm") != null ?  rs.getString("zdlxbm") : "");
				bean.setGdfs(rs.getString("gdfs") != null ?  rs.getString("gdfs") : "");
				bean.setGdfsname(rs.getString("gdfsname") != null ?  rs.getString("gdfsname") : "");
				bean.setPropertycode(rs.getString("PROPERTYCODE")!= null ?  rs.getString("PROPERTYCODE") : "");
				bean.setScb(rs.getString("SCB")!= null ?  rs.getString("SCB") : "");
			}
			
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ�渨����Ϣ�Ĳ�ѯ
	 * 
	 * @param dbid String ��������
	 * 
	 * @return bean ����һ��AssistInfo��bean1
	 * 
	 * @update WangYiXiao 2014.4.9  ɾ���ϴ�¼��ʱ��
	 */
	@Override
	public AssistInfo ass(String dbid) {
		StringBuffer sql = new StringBuffer();
		AssistInfo bean1 = new AssistInfo();
		sql.append("SELECT S.BLHDL YDL,E.ACTUALPAY ACTUALPAY,E.UNITPRICE UNITPRICE,"
						+ "ZD.ZLFH ZLFH,ZD.JLFH JLFH,ZD.PUE PUE,ZD.QSDBDL QSDBDL"
						+ " FROM(SELECT MAX(T.THISDATETIME) LASTDATETIME,"
						+ "T.AMMETERID_FK  FROM DIANDUVIEW T,DIANFEIVIEW D WHERE T.AMMETERDEGREEID=D.AMMETERDEGREEID_FK "
						+ "AND D.CITYAUDIT = '1' AND D.CITYZRAUDITSTATUS = '1' AND AMMETERID_FK = '"
						+ dbid
						+ "' GROUP BY T.AMMETERID_FK) A,"
						+ "DIANDUVIEW S,DIANFEIVIEW E,DIANBIAO DB,ZHANDIAN ZD "
						+ "WHERE S.AMMETERID_FK=A.AMMETERID_FK AND S.THISDATETIME=A.LASTDATETIME "
						+ "AND S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID");

		System.out.println("������Ϣsql:"+sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			
			db.connectDb();
			rs = db.queryAll(sql.toString());

			if (rs.next() == true) {
				bean1.setBlhdl(Format.str2(rs.getString("ydl") != null ? rs
						.getString("ydl") : "0"));
				bean1.setActualpay(Format
						.str_d(rs.getString("actualpay") != null ? rs
								.getString("actualpay") : "0.00"));
				bean1.setZlfh(Format.str_d(rs.getString("zlfh") != null ? rs
						.getString("zlfh") : "0"));
				bean1.setJlfh(Format.str_d(rs.getString("jlfh") != null ? rs
						.getString("jlfh") : "0"));
				bean1.setPue(Format.str_d(rs.getString("pue") != null ? rs
						.getString("pue") : "0"));
				bean1.setQsdbdl(Format
						.str_d(rs.getString("qsdbdl") != null ? rs
								.getString("qsdbdl") : "0"));
				bean1.setUnitprice(rs.getString("unitprice"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return bean1;
	}

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ������Ϣ�Ĳ�ѯ
	 * 
	 * @param dbid String ��������
	 * 
	 * @return bean ����һ��ElectricityInfo��bean1
	 * 
	 * @update WangYiXiao 2014.4.9  ����һ�µ�ǰʱ��������·�
	 * @update zhouxue  2014.7.23   ���bean�����ֶ�lasttime :������ֵ˰վ���ж�   �ϴγ���ʱ�䲻��1��
	 */
	@Override
	public ElectricityInfo elec(String dbid) {
		ElectricityInfo bean2 = new ElectricityInfo();
		 //����һ�µ�ǰʱ��������·�
        Date today = new Date(); 
        int tyear = 1900 + today.getYear();
        int tmonth = today.getMonth() + 1;
       String month = String.valueOf(tmonth);
       if(tmonth < 10){
     	 month="0" + tmonth;
        }
        String accountmonth = tyear + "-" + month;
        bean2.setAccountmonth(accountmonth);
        
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT S.THISDEGREE AS LASTDEGREE,TO_CHAR(S.THISDATETIME,'yyyy-mm-dd') AS LASTDATETIME,"
						+ "S.ACTUALDEGREE ACTUALDEGREE,E.ACTUALPAY ACTUALPAY,E.UNITPRICE,S.BLHDL BLHDL"
						+ " FROM(SELECT MAX(T.THISDATETIME) LASTDATETIME,"
						+ "T.AMMETERID_FK  FROM DIANDUVIEW T,DIANFEIVIEW D WHERE T.AMMETERDEGREEID=D.AMMETERDEGREEID_FK "
						+ "AND D.CITYAUDIT = '1' AND D.CITYZRAUDITSTATUS = '1' AND AMMETERID_FK = '"
						+ dbid
						+ "' GROUP BY T.AMMETERID_FK) A,"
						+ "DIANDUVIEW S,DIANFEIVIEW E,DIANBIAO DB,ZHANDIAN ZD"
						+ " WHERE S.AMMETERID_FK=A.AMMETERID_FK AND S.THISDATETIME=A.LASTDATETIME"
						+ " AND S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID");

		System.out.println("�����Ϣ��ѯsql:"+sql.toString());

		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			//���������
			if (rs.next() == true) {
				bean2.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");

				String lastadtime = rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "";
				bean2.setLasttime(lastadtime);// ������ֵ˰վ���ж� ҳ���ϵ� ���γ���ʱ�� ����1��
				
				//ȡ�����ı��γ���ʱ�� �������ε��ϴγ���ʱ�䣺 �ϴγ���ʱ��+1
				if (!"".equals(lastadtime) && lastadtime != null) {
					Calendar c = Calendar.getInstance();
					Date dt = null;
					try {
						dt = new SimpleDateFormat("yyyy-MM-dd").parse(lastadtime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					c.setTime(dt);
					int day = c.get(Calendar.DATE);
					c.set(Calendar.DATE, day + 1);
					String lastadtime1 = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

					bean2.setLastdatetime(lastadtime1);
					bean2.setActualdegree((rs.getString("actualdegree") != null ? rs.getString("actualdegree"): ""));
					bean2.setActualpay(Format.str_d(rs.getString("actualpay") != null ? rs.getString("actualpay") : ""));
					bean2.setUnitprice(rs.getString("unitprice") != null ? rs.getString("unitprice") : "");
					bean2.setBlhdl(rs.getString("blhdl") != null ? rs.getString("blhdl") : "");
				}
			} else{//û�н�����
				ElectricityInfo beancs = new ElectricityInfo();
		    	  beancs= bean2.getChuShiDuShuDegree(dbid);
		    	    bean2.setLastdegree(beancs.getCsds() != null ? beancs.getCsds() : "");
		    	    String lastadtime=beancs.getCssytime();
		    	    if(!"".equals(lastadtime)&&lastadtime!=null){
		    	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	                    String str=lastadtime;
	                    Date dt=sdf.parse(str);
	                    Calendar rightNow = Calendar.getInstance();
	                    rightNow.setTime(dt);
	                    //rightNow.add(Calendar.YEAR,-1);//���ڼ�1��
	                    //rightNow.add(Calendar.MONTH,3);//���ڼ�3����
	                    rightNow.add(Calendar.DAY_OF_YEAR,0);//���ڼ�0��
	                    Date dt1=rightNow.getTime();
	                    lastadtime = sdf.format(dt1);
	                    bean2.setLastdatetime(lastadtime);
	                    bean2.setLasttime(lastadtime);
		    	    }
		    	    else{
			        bean2.setLastdatetime(beancs.getCssytime() != null ? beancs.getCssytime() : "");}
			        bean2.setActualdegree("");
			        bean2.setActualpay(Format.str_d("")); 
		      }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return bean2;
	}
	

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ�������ֶ���Ϣ
	 * 
	 * @param
	 * 
	 * @return msgS String ����һ����Ϣ,�鿴�Ƿ񱣴�ɹ�
	 */
	@Override
	public String addDegreeFees(String dbid, ElectricityInfo elecInfo,
			Share share, String uuid, String bzw, String dfms, String dfbz,
			String dlms, String dlbz,String bl,String qsbl,String flag,String qxzr,String cityzr,String jszq,String edhdl,String qsdbdl,String xgbzw,String hbzq,String bzz,String scb) {

		String msg = "�����ѵ�ʧ�ܣ������Ի������Ա��ϵ��";
		
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String countyaudittime = "1".equals(qxzr) ? s : "NULL";//�����������ʱ��
		String cityaudittime = "1".equals(flag) ? s : "NULL";//�м����ʱ��
		String cityzraudittime = "1".equals(cityzr) ? s : "NULL";//�м��������ʱ��
		
		String scdf = elecInfo.getScdf() == null?"" : elecInfo.getScdf();
		String scdl = elecInfo.getScdl() == null?"" : elecInfo.getScdl();
		String lastunitprice = elecInfo.getLastunitprice() == null?"" : elecInfo.getLastunitprice();
				
		String sql1 = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,ACTUALDEGREE,LASTDATETIME,"
				+ "THISDATETIME,FLOATDEGREE,"
				+ "BLHDL,MEMO,"
				+ "UUID,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,"
				+ "MANUALAUDITSTATUS,DEDHDL,INPUTOPERATOR, FLAG,"
				+ "ENTRYPENSONNEL,ENTRYTIME,"
				+ "NETWORKHDL,ADMINISTRATIVEHDL,"
				+ "MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,DDDF,DLBZW,CSDB,COUNTYFLAG,CITYFLAG,LASTELECFEES,LASTELECDEGREE,LASTUNITPRICE,HBZQ,BZZ,SCB,BEILV,DBYDL)"
				+ " VALUES('"
				+ dbid
				+ "','"
				+ elecInfo.getLastdegree()
				+ "','"
				+ elecInfo.getThisdegree()
				+ "','"
				+ elecInfo.getActualdegree()
				+ "',"
				+ " TO_DATE('"
				+ elecInfo.getLastdatetime()
				+ "','yyyy-mm-dd'),"
				+ " TO_DATE('"
				+ elecInfo.getThisdatetime()
				+ "','yyyy-mm-dd'),'"
				+ elecInfo.getFloatdegree()
				+ "','"
				+ elecInfo.getBlhdl()
				+ "',"
				+ " '"
				+ elecInfo.getMemo()
				+ "',"
				+ " '"
				+ uuid
				+ "','"
				+ dlbz
				+ "','"
				+ dlms
				+ "',"
				+ "'0','"
				+ bl
				+"','"
				+elecInfo.getInputoperator()
				+"','"
				+flag
				+"',"
				+"'"
				+elecInfo.getEntrypensonnel()
				+"',"
				+s
				+ ","
				+ "'"
				+ share.getNetworkhdl()
				+ "','"
				+ share.getAdministrativehdl()
				+ "','"
				+ share.getMarkethdl()
				+ "',"
				+ "'"
				+ share.getInformatizationhdl()
				+ "','"
				+ share.getBuildhdl()
				+ "','"
				+ share.getDddl()
				+ "',"
				+ "'"
				+ bzw 
				+ "','" 
				+ qsbl
				+ "','"
				+ qxzr
				+ "','"
				+ cityzr
				+ "','"
				+ scdf
				+ "','"
				+ scdl
				+ "','"
				+ lastunitprice
				+ "',"
				+ hbzq
				+ ","
				+ bzz
				+ ","
				+ scb
				+","
				+elecInfo.getBeilv()
				+","
				+elecInfo.getDbydl()
				+ ")";

		System.out.println("��������sql��"+sql1);
		String sql2 = "INSERT INTO electricfees(UNITPRICE,FLOATPAY,MEMO,"
			+ "ACCOUNTMONTH,ACTUALPAY,NOTETYPEID,INPUTOPERATOR,"
			+ "PAYOPERATOR,PJJE,DFUUID,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,NETWORKDF,"
			+ "MANUALAUDITSTATUS,CITYAUDIT,ENTRYPENSONNEL,ENTRYTIME,"
			+ "ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,DFBZW,LIUCHENGHAO,KPTIME,"
			+ "COUNTYAUDITSTATUS,CITYZRAUDITSTATUS,JSZQ,EDHDL,QSDBDL,YZLIUCHENGHAO,COUNTYAUDITTIME,CITYAUDITTIME,CITYZRAUDITTIME,ZLFH,JLFH,PROPERTYCODE,STATIONTYPECODE,DFZFLXCODE,GDFSCODE) VALUES(" 
			+ "'"
			+ elecInfo.getUnitprice()
			+"'," 
			+ "'"
			+ elecInfo.getFloatpay()+ "','"+ elecInfo.getMemo1()
			+ "',"
			+ "TO_DATE('"
			+ elecInfo.getAccountmonth()
			+ "','yyyy-mm'),'"
			+ elecInfo.getActualpay()
			+ "',"
			+ "'"
			+ elecInfo.getNotetypeid()
			+ "','"
			+ elecInfo.getInputoperator()
			+ "','"
			+ elecInfo.getPayoperator()
			+ "',"
			+ "'"
			+ elecInfo.getPjje()
			+ "','"
			+ uuid
			+ "','"
			+ dfbz
			+ "','"
			+ dfms
			+ "',"
			+ "'"
			+ share.getNetworkdf()
			+ "','0',"
			+"'"
			+flag
			+"','"
			+elecInfo.getEntrypensonnel()
			+"',"
			+s
			+",'"
			+ share.getAdministrativedf()
			+ "','"
			+ share.getMarketdf()
			+ "',"
			+ "'"
			+ share.getInformatizationdf()
			+ "','"
			+ share.getBuilddf()
			+ "','"
			+ share.getDddf()
			+ "',"
			+ "'"
			+ bzw
			+ "','',TO_DATE('"+elecInfo.getKptime()
			+ "','yyyy-mm-dd'),'"
			+ qxzr
			+ "','"
			+ cityzr
			+"',"
			+ jszq
			+ ",'"
			+ edhdl
			+ "','"
			+ qsdbdl
			+ "','"
			+elecInfo.getLiuchenghao()
			+"'," 
			+countyaudittime
			+","
			+cityaudittime
			+","
			+cityzraudittime
			+","
			+elecInfo.getZlfh()
			+","
			+elecInfo.getJlfh()
			+",'"
			+elecInfo.getPropertycode()
			+"','"
			+elecInfo.getStationtypecode()
			+"','"
			+elecInfo.getDfzflxcode()
			+"','"
			+elecInfo.getGdfscode()
			+"')";
		System.out.println("�������sql��"+sql2);
		
		String sql3=" update dianbiao d set d.xgbzw='2' where d.dbid='"+dbid+"'";//�޸ĵ�������־λ
		

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
		   
		   conn = db.getConnection();
		   ps = conn.prepareStatement(sql1.toString());
		   ps1 = conn.prepareStatement(sql2.toString());
		   ps2 = conn.prepareStatement(sql3);
		   if("1".equals(xgbzw)){
			   System.out.println("�޸ĵ�������־λ��"+sql3.toString());
			   ps2.executeUpdate();
		   }
		   rs =  ps.executeQuery();
		   rs1 =  ps1.executeQuery();
		   
		 
		   msg = "�����ѵ��ɹ���";
		  
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(null,ps2,null);
			db.free(rs, ps, null);
			db.free(rs1, ps1, conn);
		}
		return msg;
	}

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ���ȡ������̯
	 * 
	 * @param dbid String ��������
	 * 
	 * @return share ArrayList
	 */
	@Override
	public ArrayList share1(String dbid) {

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT SB.SHUOSHUZHUANYE, SUM(SB.DBILI * SB.XJBILI / 100)dbili FROM SBGL SB WHERE DIANBIAOID='"
						+ dbid + "' GROUP BY SB.SHUOSHUZHUANYE");
		String result = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			System.out.println("��̯רҵռ�Ȳ�ѯ:");
			
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);			
		} catch (Exception de) {
			de.printStackTrace();

		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ���ȡ��̯��ϸ��Ϣ
	 * 
	 * @param dbid String ��������
	 * 
	 * @return share ArrayList
	 */
	@Override
	public ArrayList share2(String dbid) {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT SB.SHUOSHUZHUANYE,SB.DBILI,SB.QCBCODE,SB.KJKMCODE,SB.ZYMXCODE,SB.XJBILI FROM SBGL SB WHERE DIANBIAOID='"
						+ dbid + "'");
		String result = "";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			
			System.out.println("��̯רҵռ����ϸ��ѯ:");
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);

		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ���ȡ����������һ�εĳ������
	 * 
	 * @param zdcode String ��������
	 * 
	 * @return bean ����һ��ElectricityInfo��bean
	 */
	@Override
	public ElectricityInfo elec1(String zdcode) {
		ElectricityInfo bean = new ElectricityInfo();
		StringBuffer sql = new StringBuffer();
		String dbid = "";
		// to_number(t.thisdegree) ת��������
		String sql1 = "SELECT DBID FROM ZHANDIAN Z,DIANBIAO D WHERE Z.ID=D.ZDID AND D.DBYT='dbyt03' AND Z.ZDCODE='"+zdcode+"'";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			
			db.connectDb();
			rs = db.queryAll(sql1.toString());
			if (rs.next() == true) {
				dbid = rs.getString("dbid");
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		sql.append("SELECT AM.THISDEGREE,TO_CHAR(AM.THISDATETIME,'YYYY-MM-DD') THISDATETIME,AM.AMMETERID_FK FROM ZHANDIAN Z,  DIANBIAO D, AMMETERDEGREES AM, "
						+ "(SELECT MAX(T.THISDATETIME) THISDATETIME,T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK ='"
						+ dbid
						+ "'  GROUP BY AMMETERID_FK) DD"
						+ " WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID  AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'");

		try {
			System.out.println("��ȡ������ڹ��������:");
			
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if (rs.next() == true) {
				bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs
						.getString("thisdatetime") : "");
				bean.setThisdegree(rs.getString("thisdegree") != null ? rs
						.getString("thisdegree") : "");
			}

		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}


	@Override
	public DomainType getDomainType(String dbid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		Statement sta = null;
		String domain = "";
		String ftbili = "";
		DomainType bean = new DomainType();
		sql.append("SELECT SB.SHUOSHUZHUANYE, SUM(SB.DBILI * SB.XJBILI / 100)dbili FROM SBGL SB WHERE DIANBIAOID='"
						+ dbid + "' GROUP BY SB.SHUOSHUZHUANYE");
		
		try {
			System.out.println("��̯רҵռ�Ȳ�ѯ:");
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			while(rs.next()){
				domain = rs.getString("SHUOSHUZHUANYE");
				ftbili = rs.getString("dbili");
				if("zylx01".equals(domain)){
					bean.setZylx01(ftbili.toString().trim());
				}
				if("zylx02".equals(domain)){
					bean.setZylx02(ftbili.toString().trim());
				}
				if("zylx03".equals(domain)){
					bean.setZylx03(ftbili.toString().trim());
				}
				if("zylx04".equals(domain)){
					bean.setZylx04(ftbili.toString().trim());
				}
				if("zylx05".equals(domain)){
					bean.setZylx05(ftbili.toString().trim());
				}
				if("zylx06".equals(domain)){
					bean.setZylx06(ftbili.toString().trim());
				}
			}
		} catch (Exception de) {
			de.printStackTrace();

		} finally {
			db.free(rs, sta, conn);
		}
		return bean;
	}

	@Override
	public ArrayList<DomainOther> getDomainOther(String dbid) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SB.SHUOSHUZHUANYE,SB.DBILI,SB.QCBCODE,SB.KJKMCODE,SB.ZYMXCODE,SB.XJBILI FROM SBGL SB WHERE DIANBIAOID='"
				+ dbid + "'");
		DataBase db = new DataBase();
		Connection conn = null;
		Statement sta = null;
		ResultSet rs = null;
		ArrayList<DomainOther> list = new ArrayList<DomainOther>();
		try {
			System.out.println("��̯רҵռ����ϸ��ѯ:");
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			while(rs.next()){
				DomainOther bean = new DomainOther();
				bean.setDomain(rs.getString("SHUOSHUZHUANYE"));
				bean.setDbzb(rs.getString("DBILI"));
				bean.setQcbkm(rs.getString("QCBCODE"));
				bean.setKjkm(rs.getString("KJKMCODE"));
				bean.setZymx(rs.getString("ZYMXCODE"));
				bean.setFtbl(rs.getString("XJBILI"));
				list.add(bean);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, sta, conn);
		}
		return list;
	}


	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ��Ĳ�ѯ
	 * 
	 * @param whereStr String ��ȡ��������,loginId String ��ȡ��¼�˵�ID
	 * 
	 * @return list ���ؽ������Servlet,����ҳ��
	 * @update WangYiXiao 2014-8-8 tbtzsq != 1
	 */
	@Override
	public ArrayList queryElectric(String whereStr, String loginId) {
		
		ArrayList list = new ArrayList();
		String sql = "";
		String str = "";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			str = getFuzeZdid(loginId);

			sql = "select zd.jzname,zd.zdcode,ad.ammeterdegreeid,ef.ELECTRICFEE_ID,ef.UNITPRICE,ef.FLOATPAY,ef.ACTUALPAY,ef.AUTOAUDITSTATUS,"
					+ "(decode(zd.xian,null,'',(select distinct agname from d_area_grade where agcode = zd.xian))||"
					+ "decode(zd.xiaoqu,null,'',(select distinct agname from d_area_grade where agcode = zd.xiaoqu))) as szdq,"
					+ "(select name from indexs where code = zd.STATIONTYPE and type = 'stationtype') stationtype,"
					+ "(select name from indexs where code = ef.notetypeid and type = 'PJLX') NOTETYPEID,ef.NOTENO,ef.PAYOPERATOR,TO_CHAR(ef.PAYDATETIME,'yyyy-mm-dd') PAYDATETIME,ad.AMMETERDEGREEID,ef.autoauditdescription,ef.manualauditstatus "
					+ "from zhandian zd,dianbiao am,dianduview ad,dianfeiview ef  "
					+ "where zd.id=am.zdid and am.dbid=ad.ammeterid_fk and (ad.tbtzsq != '1' or ad.tbtzsq is null) and zd.SHSIGN = '1' AND zd.PROVAUDITSTATUS = '1' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus='0' or ef.manualauditstatus = '-2' or ef.manualauditstatus is null) "
					+ whereStr
					+ " "
					+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId
					+ "')"
					+ str
					+ " ORDER BY szdq, ef.PAYDATETIME DESC";
			System.out.println("��ѵ���ѯ��"+sql);
			
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				ElecQuery bean = new ElecQuery();
				
				String jzname = rs.getString("JZNAME")!= null ? rs.getString("JZNAME") : "";
				String szdq = rs.getString("SZDQ")!= null ? rs.getString("SZDQ") : "";
				String zdcode = rs.getString("ZDCODE")!= null ? rs.getString("ZDCODE") : "";
				String stationtype = rs.getString("STATIONTYPE")!= null ? rs.getString("STATIONTYPE") : "";
				String ammeterdegreeid = rs.getString("AMMETERDEGREEID")!= null ? rs.getString("AMMETERDEGREEID") : "";
				String electricfeeId = rs.getString("ELECTRICFEE_ID")!= null ? rs.getString("ELECTRICFEE_ID") : "";
				String unitprice = rs.getString("UNITPRICE")!= null ? rs.getString("UNITPRICE") : "";
				String floatpay = rs.getString("FLOATPAY")!= null ? rs.getString("FLOATPAY") : "";
				String actualpay = rs.getString("ACTUALPAY")!= null ? rs.getString("ACTUALPAY") : "";
				String notetypeid = rs.getString("NOTETYPEID")!= null ? rs.getString("NOTETYPEID") : "";
				String noteno = rs.getString("NOTENO")!= null ? rs.getString("NOTENO") : "";
				String payoperator = rs.getString("PAYOPERATOR")!= null ? rs.getString("PAYOPERATOR") : "";
				String paydatetime = rs.getString("PAYDATETIME")!= null ? rs.getString("PAYDATETIME") : "";
				String manualauditstatus = rs.getString("MANUALAUDITSTATUS")!= null ? rs.getString("MANUALAUDITSTATUS") : "";
				
				if(actualpay==null||actualpay.equals("")||actualpay.equals("null")||actualpay.equals("o"))actualpay="0";
	            DecimalFormat mat = new DecimalFormat("0.00");
	            actualpay = mat.format(Double.parseDouble(actualpay));
	            
	            if(unitprice==null||unitprice.equals("")||unitprice.equals("null")||unitprice.equals("o")) unitprice="0";
	            DecimalFormat price = new DecimalFormat("0.0000");
	            unitprice = price.format(Double.parseDouble(unitprice));
	            
	            if(floatpay==null||floatpay.equals("")||floatpay.equals("null")||floatpay.equals("o"))floatpay="0";
	            DecimalFormat pay = new DecimalFormat("0.00");
	            floatpay = pay.format(Double.parseDouble(floatpay));
	            
	            bean.setJzname(jzname);
				bean.setArea(szdq);
				bean.setZdcode(zdcode);
				bean.setStationtype(stationtype);
				bean.setAmmeterdegreeid(ammeterdegreeid);
				bean.setElectricfeeId(electricfeeId);
				bean.setUnitprice(unitprice);
				bean.setFloatpay(floatpay);
				bean.setActualpay(Format.str_d(actualpay));
				bean.setNotetypeid(notetypeid);
				bean.setNoteno(noteno);
				bean.setPayoperator(payoperator);
				bean.setPaydatetime(paydatetime);
				bean.setManualauditstatus(manualauditstatus);
				list.add(bean);
				
			}
		}catch (Exception de) {
			de.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return list;
	}
	
	/**
	 * @author lijing
	 * 
	 * @see ��ѵ�ҳ�渺��վ������
	 * 
	 * @param loginid String  
	 * 
	 * @return sql1 String 
	 */
	private String getFuzeZdid(String loginid) {

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = null;
		
		String sql = "select begincode,endcode from per_zhandian where accountid='"
						+ loginid
						+ "' and begincode is not null and endcode is not null";
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			sql1 = new String("");
			while (rs.next()) {
				sql1 = sql1 + " or (zdcode>='" + rs.getString(1)
						+ "' and zdcode <='" + rs.getString(2) + "')";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.free(rs, ps, conn);
		}
		return sql1;
	}
	
	/**
	 * @see ���֧������ΪԤ֧�������ѯ���̺�
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-3-5
	 */
	@Override
	public String getLiuchenghao(String dbid) {
		String liuchenghao = null;
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="SELECT LIUCHENGHAO FROM (SELECT LIUCHENGHAO FROM YUFUFEIVIEW YU WHERE YU.PREPAYMENT_AMMETERID = ? AND YU.FINANCEAUDIT = '2' ORDER BY YU.ENDDATE DESC) WHERE ROWNUM = 1"; 
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dbid);
			rs = ps.executeQuery();
			System.out.println("Ԥ֧���̺ţ�");
			while(rs.next()){
				liuchenghao = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return liuchenghao;
	}
	

	@Override
	public String getLast(String dbid) {
		StringBuffer sql = new StringBuffer();
		String lastunitprice = null;
		sql.append("SELECT E.UNITPRICE UNITPRICE,"
						+ "ZD.ZLFH ZLFH,ZD.JLFH JLFH,ZD.PUE PUE,ZD.QSDBDL QSDBDL"
						+ " FROM(SELECT  MAX(T.THISDATETIME) LASTDATETIME,"
						+ "T.AMMETERID_FK  FROM DIANDUVIEW T,DIANFEIVIEW D WHERE T.AMMETERDEGREEID=D.AMMETERDEGREEID_FK "
						+ "AND D.CITYAUDIT = '1' AND D.CITYZRAUDITSTATUS = '1' AND AMMETERID_FK = '"
						+ dbid
						+ "' GROUP BY T.AMMETERID_FK) A,"
						+ "DIANDUVIEW S,DIANFEIVIEW E,DIANBIAO DB,ZHANDIAN ZD "
						+ "WHERE S.AMMETERID_FK=A.AMMETERID_FK AND S.THISDATETIME=A.LASTDATETIME "
						+ "AND S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID");

		System.out.println("�ϴε���sql:");
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		Statement st =null;
		try {
			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if (rs.next()) {
				lastunitprice = rs.getString("unitprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, conn);
		}
		return lastunitprice;
	}
	public Map<String,String> getValue(String itemllag) {
		
		String sql = "SELECT PC.ITEMNAME, PC.ITEMVALUE FROM PERMISSION_CONFIGURATION PC WHERE PC.ITEMLLAG='"+itemllag+"'";
		
		String key = null;
		String value = null;
		System.out.println("sql:"+sql);
		Map<String,String> map = new HashMap<String,String>();
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		Statement st =null;
		try {
			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				key = rs.getString("ITEMNAME");
				value = rs.getString("ITEMVALUE");
				map.put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, conn);
		}
		return map;
	}
	
	/**
	 * @see ��ͬ������ʱ��
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-12-08
	 */
	@Override
	public String getHtEndTime(String dbid) {
		Logger logger = Logger.getLogger(ElecBillDaoImp.class);
		String endtime = null;
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql ="SELECT TO_CHAR(ENDDATE,'YYYY-MM-DD') FROM (SELECT ENDDATE FROM YUFUFEIVIEW YU WHERE YU.PREPAYMENT_AMMETERID = ? AND YU.FINANCEAUDIT = '2' ORDER BY YU.ENDDATE DESC) WHERE ROWNUM = 1"; 
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, dbid);
			rs = ps.executeQuery();
			logger.info("��ͬ������ʱ��:"+sql);
			while(rs.next()){
				endtime = rs.getString(1);
			}
		} catch (Exception e) {
			logger.error("��ȡ��ͬ������ʱ��ʧ��:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return endtime;
	}


	/**
	 * @param dbid ���id
	 * @return
	 * @author WangYiXiao 2014-12-08
	 */
	public synchronized boolean getOut(String dbid){
		Logger logger = Logger.getLogger(ElecBillDaoImp.class);
		boolean flag = false;
		String sql = "SELECT ZD.XMH FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID  AND ZD.PROPERTY = 'zdsx04' AND ZD.JZNAME LIKE '%����%' AND DB.DBID ='"+dbid+"' ";
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			logger.info("�ж��Ƿ����������վ����δ��վ��ID�����sql:"+sql);
			if(rs.next()){//���������վ��
				String xmh = rs.getString("XMH")==null?"":rs.getString("XMH").trim();
				if("".equals(xmh)){//��Ŀ��Ϊ��
				flag = true;//�������
				}
			}
		} catch (Exception e) {
			logger.error("�ж��Ƿ����������վ����δ��վ��ID�����ʧ��:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return flag;
	}

}
