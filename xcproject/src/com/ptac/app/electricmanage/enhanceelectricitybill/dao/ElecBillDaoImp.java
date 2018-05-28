package com.ptac.app.electricmanage.enhanceelectricitybill.dao;

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
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author WangYiXiao 2014-4-15
 * @see 加强版电费单dao层实现类
 */
public class ElecBillDaoImp implements ElecBillDao {

	/**
	 * @author lijing
	 * @see 站点名称的模糊查询
	 * @param loginId String 权限,jzname String 进行模糊查询,str String 过滤条件
	 * @return list 返回一个站点名称的list
	 * @update WangYiXiao 2014-7-4  sql去掉电费支付类型，使合同，插卡的单子可以添加
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
						+ "Z.ID = D.ZDID AND Z.SHSIGN = '1' AND Z.PROVAUDITSTATUS = '1' AND Z.QYZT='1' AND Z.XUNI = '0' AND Z.CAIJI = '0' AND D.DBYT = 'dbyt01'  AND D.DBQYZT='1' "
						+ str
						//+ " AND (D.DFZFLX = 'dfzflx01' OR D.DFZFLX = 'dfzflx03') " 
						+" AND XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"
						+ loginId + "')");

		if (!"请输入".equals(jzname)) {
			sql.append(" AND JZNAME LIKE '%" + zdmc + "%'");
		}

		sql.append(" ORDER BY JZNAME");
		
		ResultSet rs = null;
		
		try {
			System.out.println("模糊查询站点:"+sql);
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
	 * @see 电费单页面基本信息的查询
	 * 
	 * @param dbid String 过滤条件
	 * 
	 * @return bean 返回一个BasicInfo的bean
	 * 
	 * @update WangYiXiao 2014-4-9 删除pue值
	 */
	@Override
	public BasicInfo bas(String dbid) {
		BasicInfo bean = new BasicInfo();
		StringBuffer sql1 = new StringBuffer();
		sql1.append("SELECT (SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.SHI) ||"
						+ "(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.XIAN) ||"
						+ "(SELECT A.AGNAME FROM D_AREA_GRADE A WHERE A.AGCODE=ZD.XIAOQU) AREA,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') STATIONTYPE,ZD.STATIONTYPE STATIONTYPECODE,"
						+ "ZD.JZNAME JZNAME,ZD.SHI SHICODE,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.PROPERTY AND TYPE = 'ZDSX') PROPERTY,ZD.PROPERTY PROPERTYCODE,"
						+ "(SELECT IND.NAME FROM INDEXS IND WHERE IND.CODE = ZDINFO.FKZQ AND TYPE = 'FKZQ') FKZQ,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE=D.DFZFLX AND TYPE='dfzflx')AS DFZFLX,D.DFZFLX DFZFLXCODE,"
						+ "D.BEILV,"
						+ "(SELECT IND.NAME FROM INDEXS IND WHERE IND.CODE = D.LINELOSSTYPE AND TYPE = 'xslx') XSLX,D.LINELOSSTYPE LINELOSSTYPECODE,"
						+ "D.LINELOSSVALUE XSZ,D.DBID,D.BGSIGN BB,ZD.EDHDL EDHDL,D.CSDS,TO_CHAR(D.CSSYTIME,'yyyy-mm-dd') CSSYTIME,D.DANJIA,ZD.QSDBDL,D.DBDS,D.XGBZW,ZD.ZLFH,ZD.JLFH,ZD.SCB,ZD.ZYBYQLX,"
						+ "(SELECT NAME FROM INDEXS WHERE CODE = ZD.GDFS AND TYPE = 'GDFS') GDFS,ZD.GDFS GDFSCODE,D.CHANGEVALUE CHANGEVALUE,"
						+ "FF.JFZB,FF.JFZ,FF.BFZB,FF.BFZ,FF.BPZB,FF.BPZ,FF.BGZB,FF.BGZ"
						+ " FROM ZDDFINFO  ZDINFO, DIANBIAO D,ZHANDIAN ZD,D_AREA_GRADE DAG,(SELECT DAG.AGCODE AGCODE,FGP.JFZB,FGP.JFZ,FGP.BFZB,FGP.BFZ,FGP.BPZB,FGP.BPZ,FGP.BGZB,FGP.BGZ FROM D_AREA_GRADE DAG , FGP WHERE DAG.AGCODE = FGP.CITY) FF" 
						+ " WHERE d.zdid=zd.id and zd.xian=dag.agcode "
						+ " and zd.id =zdinfo.zdid(+) and ZD.SHI=FF.AGCODE(+) and d.dbid = '"
						+ dbid
						+ "' and d.dbyt='dbyt01'");
		System.out.println("基本信息sql1" + sql1);

		double jfzb = 0.00;
		double jfz = 0.0000;
		double bfzb = 0.00;
		double bfz = 0.0000;
		double bpzb = 0.00;
		double bpz = 0.0000;
		double bgzb = 0.00;
		double bgz = 0.0000;

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
				bean.setStationtypecode(rs.getString("stationtypecode") != null ? rs
						.getString("stationtypecode") : "");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME")
						: "");
				bean.setProperty(rs.getString("property") != null ? rs
						.getString("property") : "");
				bean.setPropertycode(rs.getString("propertycode") != null ? rs
						.getString("propertycode") : "");
				bean.setFkzq(rs.getString("fkzq") != null ? rs
						.getString("fkzq") : "");
				bean.setDfzflx(rs.getString("dfzflx") != null ? rs
						.getString("dfzflx") : "");
				bean.setDfzflxcode(rs.getString("dfzflxcode") != null ? rs
						.getString("dfzflxcode") : "");
				bean.setBeilv(rs.getString("beilv") != null ? rs
						.getString("beilv") : "");
				bean.setLinelosstype(rs.getString("xslx") != null ? rs
						.getString("xslx") : "");
				bean.setLinelosstypecode(rs.getString("LINELOSSTYPECODE") != null ? rs
						.getString("LINELOSSTYPECODE") : "");
				bean.setLinelossvalue(rs.getString("xsz") != null ? rs
						.getString("xsz") : "0");
				bean.setDbid(rs.getString("dbid") != null ? rs
						.getString("dbid") : "");
				bean.setShifou("");//胡波站点判断去掉
//				bean.setShifou(rs.getString("bb") != null ? rs.getString("bb")
//						: "");//胡波站点判断去掉
				bean.setEdhdl(rs.getString("edhdl") != null ? rs
						.getString("edhdl").trim() : "");
				bean.setCsds(rs.getString("csds") != null ? rs
						.getString("csds") : "");
				bean.setCssytime(rs.getString("cssytime") != null ? rs
						.getString("cssytime") : "");
				bean.setDianfei(rs.getString("danjia") != null ? Format.str4(rs
						.getString("danjia").trim()) : "");
				bean.setQsdbdl(rs.getString("qsdbdl") !=null ? rs.getString("qsdbdl").trim() : "0.00");
				bean.setShi(rs.getString("shicode") !=null ? rs.getString("shicode").trim() : "");
				bean.setZlfh(rs.getString("zlfh") != null ?  rs.getString("zlfh") : "0.00");
				bean.setJlfh(rs.getString("jlfh") != null ?  rs.getString("jlfh") : "0.00");
				bean.setZybyqlx(rs.getString("zybyqlx") != null ?  rs.getString("zybyqlx") : "");
				bean.setGdfs(rs.getString("gdfs") != null ? rs.getString("gdfs") : "");
				bean.setGdfscode(rs.getString("gdfscode") != null ? rs.getString("gdfscode") : "");
				bean.setChangevalue(rs.getString("changevalue")!=null ? rs.getString("changevalue"):"0");
				bean.setDbds(rs.getString("dbds") != null ?  rs.getString("dbds") : "");
				bean.setXgbzw(rs.getString("xgbzw") != null ?  rs.getString("xgbzw") : "");
				bean.setZdlxbm(rs.getString("STATIONTYPECODE") != null ?  rs.getString("STATIONTYPECODE") : "");
				bean.setScb(rs.getString("SCB")!=null?rs.getString("SCB"):"");
				jfzb = rs.getDouble("jfzb");
				jfz = rs.getDouble("jfz");
				bfzb = rs.getDouble("bfzb");
				bfz = rs.getDouble("bfz");
				bpzb = rs.getDouble("bpzb");
				bpz = rs.getDouble("bpz");
				bgzb = rs.getDouble("bgzb");
				bgz = rs.getDouble("bgz");
				bean.setJfzb(Format.str2(String.valueOf(jfzb*100)));
				bean.setJfz(Format.str4(String.valueOf(jfz)));
				bean.setBfzb(Format.str2(String.valueOf(bfzb*100)));
				bean.setBfz(Format.str4(String.valueOf(bfz)));
				bean.setBpzb(Format.str2(String.valueOf(bpzb*100)));
				bean.setBpz(Format.str4(String.valueOf(bpz)));
				bean.setBgzb(Format.str2(String.valueOf(bgzb*100)));
				bean.setBgz(Format.str4(String.valueOf(bgz)));
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
	 * @see 电费单页面辅助信息的查询
	 * 
	 * @param dbid String 过滤条件
	 * 
	 * @return bean 返回一个AssistInfo的bean1
	 * 
	 * @update WangYiXiao 2014.4.9  删除上次录入时间
	 */
	@Override
	public AssistInfo ass(String dbid) {
		StringBuffer sql = new StringBuffer();
		AssistInfo bean1 = new AssistInfo();
		sql.append("SELECT S.FLOATDEGREE,S.DBYDL,S.BLHDL YDL,E.ACTUALPAY ACTUALPAY,E.UNITPRICE UNITPRICE,"
						+ "ZD.ZLFH ZLFH,ZD.JLFH JLFH,ZD.PUE PUE,ZD.QSDBDL QSDBDL"
						+ " FROM(SELECT  MAX(T.THISDATETIME) LASTDATETIME,"
						+ "T.AMMETERID_FK  FROM DIANDUVIEW T,DIANFEIVIEW D WHERE T.AMMETERDEGREEID=D.AMMETERDEGREEID_FK "
						+ "AND D.MANUALAUDITSTATUS='2' AND AMMETERID_FK = '"
						+ dbid
						+ "' GROUP BY T.AMMETERID_FK) A,"
						+ "DIANDUVIEW S,DIANFEIVIEW E,DIANBIAO DB,ZHANDIAN ZD "
						+ "WHERE S.AMMETERID_FK=A.AMMETERID_FK AND S.THISDATETIME=A.LASTDATETIME "
						+ "AND S.AMMETERDEGREEID = E.AMMETERDEGREEID_FK AND S.AMMETERID_FK = DB.DBID  AND DB.ZDID = ZD.ID");

		System.out.println("辅助信息sql:"+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			
			db.connectDb();
			rs = db.queryAll(sql.toString());

			if (rs.next() == true) {
				bean1.setLastfloatdegree(Format.str2(rs.getString("floatdegree") != null ? rs
						.getString("floatdegree") : "0"));
				bean1.setLastactualdegree(Format.str2(rs.getString("DBYDL") != null ? rs
						.getString("DBYDL") : "0"));
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
				bean1.setUnitprice(rs.getString("unitprice")!=null?Format.str4(rs.getString("unitprice")):"");
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
	 * @see 电费单页面电费信息的查询
	 * 
	 * @param dbid String 过滤条件
	 * 
	 * @return bean 返回一个ElectricityInfo的bean1
	 * 
	 * @update WangYiXiao 2014.4.9  设置一下当前时间给报账月份
	 */
	@Override
	public ElectricityInfo elec(String dbid) {
		ElectricityInfo bean2 = new ElectricityInfo();
		 //设置一下当前时间给报账月份
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

		System.out.println("电费信息查询sql:");

		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());

			if (rs.next() == true) {
				bean2.setLastdegree(rs.getString("lastdegree") != null ? rs.getString("lastdegree") : "");

				String lastadtime = rs.getString("lastdatetime") != null ? rs.getString("lastdatetime") : "";
				bean2.setLasttime(lastadtime);// 用于增值税站点判断 页面上的 本次抄表时间 不加1天
				//取出最大的本次抄表时间 当做本次的上次抄表时间： 上次抄表时间+1
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
			} else{//没有交过费
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
	                    //rightNow.add(Calendar.YEAR,-1);//日期减1年
	                    //rightNow.add(Calendar.MONTH,3);//日期加3个月
	                    rightNow.add(Calendar.DAY_OF_YEAR,0);//日期加0天
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
	 * @see 电费单页面插入的字段信息
	 * 
	 * @param
	 * 
	 * @return msgS String 返回一条信息,查看是否保存成功
	 */
	@Override
	public String addDegreeFees(String dbid, ElectricityInfo elecInfo,
			Share share, String uuid, String bzw, String dfms, String dfbz,
			String dlms, String dlbz,String bl,String qsbl,String flag,String qxzr,String cityzr,String jszq,String edhdl,String qsdbdl,String xgbzw,String hbzq,String bzz,String scb) {

		String msg = "保存电费单失败！请重试或与管理员联系！";
		
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String countyaudittime = "1".equals(qxzr) ? s : "NULL";//区县主任审核时间
		String cityaudittime = "1".equals(flag) ? s : "NULL";//市级审核时间
		String cityzraudittime = "1".equals(cityzr) ? s : "NULL";//市级主任审核时间
		
		String scdf = elecInfo.getScdf() == null?"" : elecInfo.getScdf();
		String scdl = elecInfo.getScdl() == null?"" : elecInfo.getScdl();
		String lastunitprice = elecInfo.getLastunitprice() == null?"" : elecInfo.getLastunitprice();
				
		String sql1 = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,"
				+ "THISDATETIME,FLOATDEGREE,"
				+ "BLHDL,MEMO,"
				+ "UUID,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,"
				+ "MANUALAUDITSTATUS,DEDHDL,INPUTOPERATOR, FLAG,"
				+ "ENTRYPENSONNEL,ENTRYTIME,"
				+ "NETWORKHDL,ADMINISTRATIVEHDL,"
				+ "MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,DDDF,DLBZW,CSDB,COUNTYFLAG,CITYFLAG,LASTELECFEES,LASTELECDEGREE,LASTUNITPRICE,PJDL," 
				+ "TBTZSQ,HBZQ,BZZ,SCB,BEILV,DBYDL)"
				+ " VALUES('"
				+ dbid
				+ "','"
				+ elecInfo.getLastdegree()
				+ "','"
				+ elecInfo.getThisdegree()
				+ "',TO_DATE('"
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
				+ elecInfo.getPjdl()
				+ ",'"
				+ elecInfo.getTbtzsq()
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

		System.out.println("新增电量sql："+sql1);
		String sql2 = "INSERT INTO electricfees(UNITPRICE,FLOATPAY,MEMO,"
			+ "ACCOUNTMONTH,ACTUALPAY,NOTETYPEID,INPUTOPERATOR,"
			+ "PAYOPERATOR,PJJE,DFUUID,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,NETWORKDF,"
			+ "MANUALAUDITSTATUS,CITYAUDIT,ENTRYPENSONNEL,ENTRYTIME,"
			+ "ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,DFBZW,LIUCHENGHAO,KPTIME,COUNTYAUDITSTATUS,CITYZRAUDITSTATUS,JSZQ,EDHDL,QSDBDL,YZLIUCHENGHAO,COUNTYAUDITTIME,CITYAUDITTIME,CITYZRAUDITTIME,YDDF," 
			+ "JFZB,BFZB,BGZB,BPZB,STATIONTYPECODE,PROPERTYCODE,DFZFLXCODE,GDFSCODE,ZLFH,JLFH,BEILV,CHANGEVALUE,LINELOSSTYPE,LINELOSSVALUE,ACTUALLINELOSSVALUE) VALUES(" 
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
			+elecInfo.getYddf()
			+","
			+elecInfo.getJfzb()
			+","
			+elecInfo.getBfzb()
			+","
			+elecInfo.getBgzb()
			+","
			+elecInfo.getBpzb()
			+",'"
			+elecInfo.getStationtypecode()
			+"','"
			+elecInfo.getPropertycode()
			+"','"
			+elecInfo.getDfzflxcode()
			+"','"
			+elecInfo.getGdfscode()
			+"','"
			+elecInfo.getZlfh()
			+"','"
			+elecInfo.getJlfh()
			+"','"
			+elecInfo.getBeilv()
			+"','"
			+elecInfo.getChangevalue()
			+"','"
			+elecInfo.getLinelosstype()
			+"','"
			+elecInfo.getLinelossvalue()
			+"','"
			+elecInfo.getActuallinelossvalue()
			+"')";
		System.out.println("新增电费sql："+sql2);

		String sql3=" update dianbiao d set d.xgbzw='2' where d.dbid='"+dbid+"'";//修改电表读数标志位

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try {
		   db.connectDb();
		   conn = db.getConnection();
		   ps = conn.prepareStatement(sql1.toString());
		   ps1 = conn.prepareStatement(sql2.toString());
		   ps2 = conn.prepareStatement(sql3);
		   if("1".equals(xgbzw)){
			   System.out.println("修改电表读数标志位："+sql3.toString());
			   ps2.executeUpdate();
		   }
		   rs =  ps.executeQuery();
		   rs1 =  ps1.executeQuery();
		 
		   msg = "保存电费单成功！";
		  
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
	 * @see 电费单页面获取六条分摊
	 * 
	 * @param dbid String 过滤条件
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
			System.out.println("分摊专业占比查询:");
			
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
	 * @see 电费单页面获取分摊详细信息
	 * 
	 * @param dbid String 过滤条件
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
			
			System.out.println("分摊专业占比详细查询:");
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
	 * @see 电费单页面获取管理电表的最近一次的抄表读数
	 * 
	 * @param zdcode String 过滤条件
	 * 
	 * @return bean 返回一个ElectricityInfo的bean
	 */
	@Override
	public ElectricityInfo elec1(String zdcode) {
		ElectricityInfo bean = new ElectricityInfo();
		StringBuffer sql = new StringBuffer();
		String dbid = "";
		// to_number(t.thisdegree) 转换成数字
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
		sql.append("SELECT AM.THISDEGREE,TO_CHAR(AM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,AM.LASTDEGREE,TO_CHAR(AM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME,AM.MANUALAUDITNAME,AM.INPUTOPERATOR,AM.AMMETERID_FK FROM ZHANDIAN Z,  DIANBIAO D, AMMETERDEGREES AM, "
						+ "(SELECT MAX(T.THISDATETIME) THISDATETIME,T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK ='"
						+ dbid
						+ "'  GROUP BY AMMETERID_FK) DD"
						+ " WHERE Z.ID = D.ZDID AND D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID  AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1' ");

		try {
			System.out.println("获取最近日期电表读数:"+sql);
			
			db.connectDb();
			rs = db.queryAll(sql.toString());
			if (rs.next() == true) {
				bean.setGldlbz(true);
				bean.setThisdatetime(rs.getString("thisdatetime") != null ? rs
						.getString("thisdatetime") : "");
				bean.setThisdegree(rs.getString("thisdegree") != null ? rs
						.getString("thisdegree") : "");
				bean.setLastdegree(rs.getString("lastdegree") != null ? rs
						.getString("lastdegree") : "");
				bean.setLastdatetime(rs.getString("lastdatetime") != null ? rs
						.getString("lastdatetime") : "");
				bean.setManualauditname(rs.getString("manualauditname") != null ? rs
						.getString("manualauditname"):"");
				bean.setDlinputoperator(rs.getString("inputoperator") != null ? rs
						.getString("inputoperator"):"");
			}else{
				bean.setGldlbz(false);
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
			System.out.println("分摊专业占比查询:");
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
			System.out.println("分摊专业占比详细查询:");
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
	 * @see 电费单页面的查询
	 * 
	 * @param whereStr String 获取过滤条件,loginId String 获取登录人的ID
	 * 
	 * @return list 返回结果集到Servlet,传回页面
	 * 
	 * @updte Wangyixiao 2014-8-7 tbtzsq=1
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
					+ "where zd.id=am.zdid and am.dbid=ad.ammeterid_fk and zd.qyzt='1' and ad.tbtzsq = '1' "
					+ "and ad.ammeterdegreeid=ef.ammeterdegreeid_fk and (ef.manualauditstatus='0' or ef.manualauditstatus = '-2' or ef.manualauditstatus is null) "
					+ whereStr
					+ " "
					+ "and exists (select t.agcode from per_area t where t.agcode=zd.xiaoqu and t.accountid = '"
					+ loginId
					+ "')"
					+ str
					+ " ORDER BY szdq, ef.PAYDATETIME DESC";
			System.out.println("电费单查询："+sql);
			
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
	 * @see 电费单页面负责站点条件
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
	 * @see 电费支付类型为预支类型则查询流程号
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
			System.out.println("预支流程号：");
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

		System.out.println("上次单价sql:"+sql.toString());
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

	@Override
	public Map<String,String> getValue(String itemllag) {
		
		String sql = "SELECT PC.ITEMNAME, PC.ITEMVALUE FROM PERMISSION_CONFIGURATION PC WHERE PC.ITEMLLAG='"+itemllag+"'";
		
		String key = null;
		String value = null;
		System.out.println("加强流程配置值sql:"+sql);
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
	
	public String getAverageUnitPrice(String dbid ,String str){
		StringBuffer sql = new StringBuffer("SELECT CE.").append(str)
		.append(" FROM ZHANDIAN ZD ,DIANBIAO DB,STANDARDUNITPRICE CE WHERE ZD.ID = DB.ZDID  AND CE.CITY=ZD.SHI AND DB.DBID = '")
		.append(dbid).append("'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		Connection conn = null;
		Statement st =null;
		String averageunitprice = "";
		
		try {
			conn = db.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs.next()){
				averageunitprice = rs.getString(str)==null?"0":Format.str4(rs.getString(str));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, conn);
		}
		return averageunitprice;
	}
	
	/**
	 * @see 电费支付类型为预支类型则查询流程号
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-3-5
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
			logger.info("合同单结束时间:"+sql);
			while(rs.next()){
				endtime = rs.getString(1);
			}
		} catch (Exception e) {
			logger.error("获取合同单结束时间失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return endtime;
	}


	/**
	 * @param dbid 电表id
	 * @return
	 * @author WangYiXiao 2014-12-08
	 */
	public synchronized boolean getOut(String dbid){
		Logger logger = Logger.getLogger(ElecBillDaoImp.class);
		boolean flag = false;
		String sql = "SELECT ZD.XMH FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID  AND ZD.PROPERTY = 'zdsx04' AND ZD.JZNAME LIKE '%回收%' AND DB.DBID = '"+dbid+"'";
		DataBase db = new DataBase();
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			logger.info("判断是否是外租回收站点且未与站点ID相关联sql:"+sql);
			if(rs.next()){//是外租回收站点
				String xmh = rs.getString("XMH")==null?"":rs.getString("XMH").trim();
				if("".equals(xmh)){//项目号为空
				flag = true;//不相关联
				}
			}
		} catch (Exception e) {
			logger.error("判断是否是外租回收站点且未与站点ID相关联失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return flag;
	}

}
