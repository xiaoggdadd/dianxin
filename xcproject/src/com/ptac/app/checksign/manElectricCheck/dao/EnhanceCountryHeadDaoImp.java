package com.ptac.app.checksign.manElectricCheck.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.checksign.manElectricCheck.bean.CountryHeadBean;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanageUtil.Format;

public class EnhanceCountryHeadDaoImp implements EnhanceCountryHeadDao{
	
	DataBase db = new DataBase();
	Connection conn = null;
	Statement sta = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	/**
	 * @update WangYiXiao 2014-4-21 电表用电量 = （本次电表读数-上次电表读数）*倍率
	 * @update zhouxue 2014-05-16 查询语句加默认过滤条件：站点非虚拟，非采集，站点人工审核通过
	 * @update WangyiXiao 2014-8-8 只能查询出加强版的数据 tbtzsq = 1
	 * @update WangYiXiao 2014-8-11去掉tbtzsq = 1 ,sql2可用
	 */
	public List<CountryHeadBean> queryInfo(String whereStr, String cityaudit, String loginId,String whereStr1) {
		List<CountryHeadBean> list = new ArrayList<CountryHeadBean>();//声明结果集

		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		
		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			
		//------区县主任审核sql语句------
			sql1.append(" SELECT DB.DBID, ZD.JZNAME, to_char(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH, AM.BLHDL, AM.ACTUALDEGREE, EF.ACTUALPAY, EF.UNITPRICE, AM.CSDB, AM.DEDHDL, EF.QSDBDL, EF.EDHDL, to_char(AM.LASTDATETIME,'yyyy-mm-dd') LASTDATETIME, to_char(AM.THISDATETIME,'yyyy-mm-dd') THISDATETIME,"
					+" (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, RTNAME(ZD.PROPERTY) AS PROPERTY, EF.COUNTYAUDITSTATUS, EF.DFUUID,AM.FLAG,EF.AUTOAUDITDESCRIPTION," 
					+" ZD.ID,RNDIQU(ZD.XIAN) AS XIAN,DB.DBNAME,RTNAME(DFZFLX) AS DFZFLX,RTNAME(GDFS) AS GDFS,AM.FLOATDEGREE,AM.MEMO AS AMEMO,EF.FLOATPAY,EF.MEMO,EF.JSZQ,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,"
					+" DB.BEILV,EF.NOTETIME,EF.NOTENO,RTNAME (NOTETYPEID) AS NOTETYPEID,EF.PAYDATETIME, EF.AUTOAUDITSTATUS, EF.MANPASSMEMO,AM.LASTELECFEES,AM.LASTELECDEGREE,AM.LASTUNITPRICE,AM.TBTZSQ, " 
					+"AM.AMMETERDEGREEID,EF.ELECTRICFEE_ID,AM.THISDEGREE,AM.LASTDEGREE,AM.LASTFLOATDEGREE,AM.LASTACTUALDEGREE,EF.CHANGEVALUE,EF.ACTUALLINELOSSVALUE,AM.BLHDL/EF.JSZQ BZRJ"
					+" FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF"
					+" WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK " +
					 "AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
					+" AND (EF.MANUALAUDITSTATUS = '1' OR EF.MANUALAUDITSTATUS = '-1')"
					+ whereStr
					+" AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");

			sql2.append(" SELECT DB.DBID, ZD.JZNAME, to_char(EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH, EF.DIANLIANG, EF.MONEY, EF.DANJIA, EF.CSDB as csdb, EF.DEDHDL, EF.QSDBDL, EF.EDHDL, to_char(EF.STARTDATE,'yyyy-mm-dd') STARTDATE, to_char(EF.ENDDATE,'yyyy-mm-dd') ENDDATE,"      
					+" (SELECT NAME FROM INDEXS WHERE CODE = ZD.STATIONTYPE AND TYPE = 'stationtype') AS STATIONTYPE, RTNAME(ZD.PROPERTY) AS PROPERTY, EF.COUNTYAUDITSTATUS, EF.UUID,EF.FLAG,EF.AUTOAUDITDESCRIPTION," 
					+" ZD.ID,RNDIQU(ZD.XIAN) AS XIAN,DB.DBNAME,RTNAME(DFZFLX) AS DFZFLX,RTNAME(GDFS) AS GDFS,EF.JSZQ,EF.MANUALAUDITSTATUS,EF.CITYAUDIT,DB.BEILV,EF.NOTETIME,EF.NOTENO,RTNAME (NOTETYPEID) AS NOTETYPEID,"
					+" EF.PAYDATETIME, EF.AUTOAUDITSTATUS, EF.MANPASSMEMO, EF.AMMETERDEGREEID_FK,EF.ID,EF.LASTYUE,EF.DIANLIANG/EF.JSZQ BZRJ"
					+" FROM ZHANDIAN ZD, DIANBIAO DB, YUFUFEIVIEW EF"
					+" WHERE ZD.ID = DB.ZDID AND DB.DBID = EF.PREPAYMENT_AMMETERID " +
					 "AND ZD.XUNI='0' AND ZD.CAIJI='0' AND ZD.SHSIGN='1' "
					+" AND (EF.MANUALAUDITSTATUS = '1' OR EF.MANUALAUDITSTATUS = '-1')"
					+ whereStr1
					+" AND (ZD.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"'))");
			
			System.out.println("区县主任审核查询"+sql1.toString());//后台打印sql
			System.out.println("区县主任审核查询"+sql2.toString());
			rs = sta.executeQuery(sql1.toString());//运行sql
			while (rs.next()) {
				//------接收查询到的数据(并判断是否为空)------		

				CountryHeadBean bean = new CountryHeadBean();
				bean.setDbid(rs.getString("DBID") != null ? rs.getString("DBID") : "");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH") : "");
				bean.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL") : "");
				String actualdegree = rs.getString("ACTUALDEGREE") != null ? rs.getString("ACTUALDEGREE") : "";
				//bean.setActualdegree(actualdegree);
				String actualpay = rs.getString("ACTUALPAY") != null ? rs.getString("ACTUALPAY") : ""; 
				bean.setActualpay(actualpay);
				String danjia = rs.getString("UNITPRICE") != null ? rs.getString("UNITPRICE") : "";
				bean.setUnitprice(danjia);
				bean.setCshengbl(rs.getString("CSDB") != null ? rs.getString("CSDB") : "");
				bean.setCshibl(rs.getString("DEDHDL") != null ? rs.getString("DEDHDL") : "");
				String qsdbdl = rs.getString("QSDBDL") != null ? rs.getString("QSDBDL") : ""; 
				bean.setQsdbdl(qsdbdl);
				bean.setEdhdl(rs.getString("EDHDL") != null ? rs.getString("EDHDL") : "");
				String lastdatetime = rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME") : "";
				String thisdatetime = rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME") : "";
				bean.setLastdatetime(lastdatetime);
				bean.setThisdatetime(thisdatetime);
				bean.setStationtype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");
				bean.setProperty(rs.getString("PROPERTY") != null ? rs.getString("PROPERTY") : "");
				String countryheadstatus = rs.getString("COUNTYAUDITSTATUS") != null ? rs.getString("COUNTYAUDITSTATUS") : "";
				bean.setCountryheadstatus(countryheadstatus);
				bean.setUuid(rs.getString("DFUUID") != null ? rs.getString("DFUUID") : "");

				if("0".equals(bean.getCountryheadstatus())){
					bean.setCountryheadstatus("未审核");
				}else if("1".equals(bean.getCountryheadstatus())){
					bean.setCountryheadstatus("通过");
				}else if("2".equals(bean.getCountryheadstatus())){
					bean.setCountryheadstatus("不通过");
				}else{
					bean.setCountryheadstatus("");
				}
				bean.setBlhdl(Format.str2(bean.getBlhdl()));
				bean.setActualpay(Format.str2(bean.getActualpay()));
				bean.setQsdbdl(Format.str2(bean.getQsdbdl()));
				bean.setEdhdl(Format.str2(bean.getEdhdl()));
				bean.setUnitprice(Format.str4(bean.getUnitprice()));
				bean.setCshengbl(Format.str2(bean.getCshengbl()));
				bean.setCshibl(Format.str2(bean.getCshibl()));
				bean.setCshengbl("".equals(bean.getCshengbl())? "":bean.getCshengbl()+"%");
				bean.setCshibl("".equals(bean.getCshibl())? "":bean.getCshibl()+"%");
				bean.setFlag(rs.getString("FLAG") != null ? rs.getString("FLAG") : "0");
				bean.setDescription(rs.getString("AUTOAUDITDESCRIPTION") != null ? rs.getString("AUTOAUDITDESCRIPTION") : "");
				
				bean.setZdid(rs.getString("ID") != null ? rs.getString("ID") : "");
				bean.setXian(rs.getString("XIAN") != null ? rs.getString("XIAN") : "");
				bean.setDbname(rs.getString("DBNAME") != null ? rs.getString("DBNAME") : "");
				bean.setDfzflx(rs.getString("DFZFLX") != null ? rs.getString("DFZFLX") : "");
				bean.setGdfs(rs.getString("GDFS") != null ? rs.getString("GDFS") : "");
				bean.setFloatdegree(rs.getString("FLOATDEGREE") != null ? rs.getString("FLOATDEGREE") : "");
				bean.setAmmemo(rs.getString("AMEMO") != null ? rs.getString("AMEMO") : "");//电量调整备注
				bean.setFloatpay(rs.getString("FLOATPAY") != null ? rs.getString("FLOATPAY") : "");
				bean.setEfmemo(rs.getString("MEMO") != null ? rs.getString("MEMO") : "");//电费调整备注
				String zq = rs.getString("JSZQ") != null ? rs.getString("JSZQ") : "";
				bean.setJszq(zq);
				bean.setBiaozhi("dianfei");
				bean.setZflxflag("1");
				bean.setSort("电费");
				
				String manualauditstatus2 = rs.getString("MANUALAUDITSTATUS") != null ? rs.getString("MANUALAUDITSTATUS") : "" ;//人工审核状态(0未通过 -1财务未通过 1 人工通过 2 财务通过 -2 不通过)
				if("0".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工未通过 ");//人工审核状态	
					bean.setManualauditstatus1("财务未通过 ");//财务审核状态	
				}else if("1".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工通过");//人工审核状态	
					bean.setManualauditstatus1("财务未通过 ");//财务审核状态	
				}else if("2".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工通过");//人工审核状态	
					bean.setManualauditstatus1("财务通过");//财务审核状态
				}else if("-1".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工通过");//人工审核状态	
					bean.setManualauditstatus1("财务不通过");//财务审核状态
				}else if("-2".equals(manualauditstatus2)){
					bean.setManualauditstatus1("财务未通过");//财务审核状态
					bean.setManualauditstatus("人工不通过 ");//人工审核状态	
				}else{
					bean.setManualauditstatus("");//人工审核状态	
					bean.setManualauditstatus1("");//财务审核状态
				}
				
				String cityaudit1 = rs.getString("CITYAUDIT") != null ? rs.getString("CITYAUDIT") : "";
				if("0".equals(cityaudit1)){
					bean.setCityaudit("未审核 ");
				}else if("1".equals(cityaudit1)){
					bean.setCityaudit("审核通过");
				}else if("-2".equals(cityaudit1)){
					bean.setCityaudit("不通过");
				}else{
					bean.setCityaudit("");
				}
				
				String beilv = rs.getString("BEILV") != null ? rs.getString("BEILV") : "";
				if("".equals(beilv)){
					beilv = "1";
				}
				bean.setBeilv(beilv);
				bean.setPjsj(rs.getString("NOTETIME") != null ? rs.getString("NOTETIME") : "");
				bean.setPjbh(rs.getString("NOTENO") != null ? rs.getString("NOTENO") : "");
				bean.setPjlx(rs.getString("NOTETYPEID") != null ? rs.getString("NOTETYPEID") : "");
				bean.setJfsj(rs.getString("PAYDATETIME") != null ? rs.getString("PAYDATETIME") : "");
				
				String autoauditstatus = rs.getString("AUTOAUDITSTATUS") != null ? rs.getString("AUTOAUDITSTATUS") : "";
				String manpassmeno = rs.getString("MANPASSMEMO") != null ? rs.getString("MANPASSMEMO") : "";
				if(!"1".equals(autoauditstatus)){
					bean.setManpassmeno(manpassmeno);
				}
				
				if("".equals(zq)||"null".equals(zq)||zq == null){
					zq="0";
				}
				String thisdegree = rs.getString("thisdegree");
				String lastdegree = rs.getString("lastdegree");
				double actdegree = Format.d2(Double.parseDouble(thisdegree) - Double.parseDouble(lastdegree));
				double dbydl = actdegree * Format.str_d(beilv); 
				bean.setDbydl(String.valueOf(dbydl));//电表用电量
				
//				double bzydl = (Format.str_d(zq) + Format.str_d(qsdbdl)) * Format.str_d(beilv);
//				bean.setBzydl(String.valueOf(bzydl));//报账用电量
				
				//超省标电量度
				//超市标电量度
				String csbdld = String.valueOf(Format.d2(Format.str_d(bean.getBlhdl()) - Format.str_d(bean.getQsdbdl())*Format.str_d(bean.getJszq())));
				String cshibdld = String.valueOf(Format.d2(Format.str_d(bean.getBlhdl()) - Format.str_d(bean.getEdhdl())*Format.str_d(bean.getJszq())));
				bean.setCsbdld(csbdld);
				bean.setCshibdld(cshibdld);
				
				double sdbdf = Format.str_d(qsdbdl) * Format.str_d(danjia) * Format.str_d(zq);//省定标电费
				
				double csdbdfe = Format.str_d(actualpay) - sdbdf;
				bean.setCsdbdfe(Format.str2(String.valueOf(csdbdfe)));//超省定标电费额
				double csdbdfjdz = Math.abs(csdbdfe);//超省标电费绝对值
				bean.setCsdbdfjdz(String.valueOf(Format.d2(csdbdfjdz)));
				double csdbdfbz = csdbdfe/sdbdf*100;
				bean.setCsdbdfbz(Format.str2(String.valueOf(csdbdfbz)));//超省定标电费比值
				
				
				double sdbdfe = Format.str_d(bean.getEdhdl()) * Format.str_d(danjia) * Format.str_d(zq);//市定标电费
				double csdbdf = Format.str_d(actualpay) - sdbdfe;
				double cshidbdfjdz = Math.abs(csdbdf);//超市标电费绝对值
				bean.setCshidbdfjdz(String.valueOf(Format.d2(cshidbdfjdz)));
				double cshidbdfbz = csdbdf/sdbdfe*100;
				bean.setCshibdfbl(Format.str2(String.valueOf(cshidbdfbz)));//超市定标电费比值
				
				bean.setLastelecfees(rs.getString("LASTELECFEES") != null ? rs.getString("LASTELECFEES") : "");//上次电费
				bean.setLastelecdegree(rs.getString("LASTELECDEGREE") != null ? rs.getString("LASTELECDEGREE") : "");//上次电量
				bean.setLastunitprice(rs.getString("LASTUNITPRICE") != null ? rs.getString("LASTUNITPRICE") : "");//上次单价
				
				String tbtzsq = rs.getString("TBTZSQ") != null ? rs.getString("TBTZSQ") : "";//特别调整申请
				if("1".equals(tbtzsq)){
					bean.setTbtzsq("有申请，请确认");
				}else if("0".equals(tbtzsq)){
					bean.setTbtzsq("无申请");
				}
				String dlid = rs.getString("AMMETERDEGREEID") != null ? rs.getString("AMMETERDEGREEID") : "";//电量ID
				String electricfee_id = rs.getString("ELECTRICFEE_ID") != null ? rs.getString("ELECTRICFEE_ID") : "";// 电费id
				
				
				bean.setDlid(dlid);
				bean.setElectricfee_id(electricfee_id);
 
				if("0".equals(countryheadstatus)){
					bean.setGdtx("待审核");
				}else if("1".equals(countryheadstatus)){
					if("-2".equals(cityaudit1)){
						bean.setGdtx("上级退单");
					}else if("1".equals(cityaudit1) || "0".equals(cityaudit1)){
						bean.setGdtx("");
					}
				}
				bean.setLastyue("");
				
				//电量调增*倍率、上期电表用电量、上期电量调整*倍率列
				String bv = "";
				if(Format.str_d(bean.getBeilv())==0){//倍率为空 或 为 0
					bv="1";
				}else{
					bv = bean.getBeilv();
				}
				String lastactualdegree = rs.getString("LASTACTUALDEGREE")!=null?rs.getString("LASTACTUALDEGREE"):"0"; //上期电表用电量
				String lastfloatdegree = rs.getString("LASTFLOATDEGREE")!=null?rs.getString("LASTFLOATDEGREE"):"0"; //上期电量调整
				String floatdegreeandbl = String.valueOf(Format.d2(Format.str_d(bean.getFloatdegree())*Format.str_d(bv)));//电量调增*倍率
				String lastfloatdegreeandbl = String.valueOf(Format.d2(Format.str_d(lastfloatdegree)*Format.str_d(bv)));//上期电量调整*倍率列
				bean.setLastfloatdegreeandbl(lastfloatdegreeandbl);//上期电量调整*倍率
				bean.setLastactualdegree(lastactualdegree);//上期电表用电量
				bean.setFloatdegreeandbl(floatdegreeandbl);//电量调整*倍率
				
				/*String sqla = "SELECT ROUND(SUM(AM.THISDEGREE - AM.LASTDEGREE) / SUM(AM.GLZQ),2) GLBRJL FROM DIANBIAO D, AMMETERDEGREES AM, (SELECT TO_CHAR(MAX(TO_DATE((T.THISDATETIME), 'YYYY-MM-DD')), 'YYYY-MM-DD') THISDATETIME,"
		               +"T.AMMETERID_FK FROM AMMETERDEGREES T WHERE T.MANUALAUDITSTATUS = '1' AND T.AMMETERID_FK IN (SELECT DBID FROM ZHANDIAN Z, DIANBIAO D WHERE Z.ID = D.ZDID AND D.DBYT = 'dbyt03' AND Z.ID = '"
		               +bean.getZdid()+"')GROUP BY AMMETERID_FK) DD WHERE D.DBID = AM.AMMETERID_FK AND DD.AMMETERID_FK = D.DBID AND AM.THISDATETIME = DD.THISDATETIME AND AM.MANUALAUDITSTATUS = '1'";
				DataBase dba = new DataBase();
				Connection conna = dba.getConnection();
				Statement staa = conna.createStatement();
				 System.out.println("人工审核电费管理日均电量查询"+sql1.toString());
				ResultSet rsa = staa.executeQuery(sqla);
				String glbrjl="";
				if(rsa.next()){
					glbrjl=rsa.getString("GLBRJL");//管理表日均电量
				}
				dba.free(rsa, staa, conna);*/
				bean.setGlbrjl(Format.str2(""));
				String changevalue = rs.getString("CHANGEVALUE")!=null?rs.getString("CHANGEVALUE"):"0"; //变损值
				String actuallinelossvalue = rs.getString("ACTUALLINELOSSVALUE")!=null?rs.getString("ACTUALLINELOSSVALUE"):"0"; //实际线损值
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				String changeandlineandbl = String.valueOf(Format.d2((Format.str_d(changevalue) + Format.str_d(actuallinelossvalue)) * Format.str_d(bv)));//线变损电量
				bean.setLineandchangeandbl(changeandlineandbl);//线变损电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				list.add(bean);
			}

			rs = sta.executeQuery(sql2.toString());//运行sql
			while (rs.next()){
				CountryHeadBean bean = new CountryHeadBean();
				bean.setGlbrjl("");
				bean.setDbid(rs.getString("DBID") != null ? rs.getString("DBID") : "");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME") : "");
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH") : "");
				String blhdl = rs.getString("DIANLIANG") != null ? rs.getString("DIANLIANG") : "";
				bean.setBlhdl(blhdl);
				String actualpay = rs.getString("MONEY") != null ? rs.getString("MONEY") : "";
				bean.setActualpay(actualpay);
				String danjia = rs.getString("DANJIA") != null ? rs.getString("DANJIA") : "";
				bean.setUnitprice(danjia);
				bean.setCshengbl(rs.getString("CSDB") != null ? rs.getString("CSDB") : "");
				bean.setCshibl(rs.getString("DEDHDL") != null ? rs.getString("DEDHDL") : "");
				String qsdbdl = rs.getString("QSDBDL") != null ? rs.getString("QSDBDL") : "";
				bean.setQsdbdl(qsdbdl);
				bean.setEdhdl(rs.getString("EDHDL") != null ? rs.getString("EDHDL") : "");
				bean.setLastdatetime(rs.getString("STARTDATE") != null ? rs.getString("STARTDATE") : "");
				bean.setThisdatetime(rs.getString("ENDDATE") != null ? rs.getString("ENDDATE") : "");
				bean.setStationtype(rs.getString("STATIONTYPE") != null ? rs.getString("STATIONTYPE") : "");
				bean.setProperty(rs.getString("PROPERTY") != null ? rs.getString("PROPERTY") : "");
				String countryheadstatus1 = rs.getString("COUNTYAUDITSTATUS") != null ? rs.getString("COUNTYAUDITSTATUS") : "";
				bean.setCountryheadstatus(countryheadstatus1);
				bean.setUuid(rs.getString("UUID") != null ? rs.getString("UUID") : "");
				
				bean.setLastyue(rs.getString("LASTYUE") != null ? rs.getString("LASTYUE") : "");

				if("0".equals(bean.getCountryheadstatus())){
					bean.setCountryheadstatus("未审核");
				}else if("1".equals(bean.getCountryheadstatus())){
					bean.setCountryheadstatus("通过");
				}else if("2".equals(bean.getCountryheadstatus())){
					bean.setCountryheadstatus("不通过");
				}else{
					bean.setCountryheadstatus("");
				}
				bean.setBlhdl(Format.str2(bean.getBlhdl()));
				bean.setActualpay(Format.str2(bean.getActualpay()));
				bean.setQsdbdl(Format.str2(bean.getQsdbdl()));
				bean.setEdhdl(Format.str2(bean.getEdhdl()));
				bean.setUnitprice(Format.str4(bean.getUnitprice()));
				bean.setCshengbl(Format.str2(bean.getCshengbl()));
				bean.setCshibl(Format.str2(bean.getCshibl()));
				bean.setCshengbl("".equals(bean.getCshengbl())? "":bean.getCshengbl()+"%");
				bean.setCshibl("".equals(bean.getCshibl())? "":bean.getCshibl()+"%");
				bean.setFlag(rs.getString("FLAG") != null ? rs.getString("FLAG") : "");
				bean.setDescription(rs.getString("AUTOAUDITDESCRIPTION") != null ? rs.getString("AUTOAUDITDESCRIPTION") : "");
				
				
				bean.setZdid(rs.getString("ID") != null ? rs.getString("ID") : "");
				bean.setXian(rs.getString("XIAN") != null ? rs.getString("XIAN") : "");
				bean.setDbname(rs.getString("DBNAME") != null ? rs.getString("DBNAME") : "");
				bean.setDfzflx(rs.getString("DFZFLX") != null ? rs.getString("DFZFLX") : "");
				bean.setGdfs(rs.getString("GDFS") != null ? rs.getString("GDFS") : "");
				bean.setFloatdegree("");
				bean.setAmmemo("");//电量调整备注
				bean.setFloatpay("");
				bean.setEfmemo("");//电费调整备注
				String zq = rs.getString("JSZQ") != null ? rs.getString("JSZQ") : "";
				bean.setJszq(zq);
				bean.setBiaozhi("dianfei");
				bean.setZflxflag("1");
				
				String manualauditstatus2 = rs.getString("MANUALAUDITSTATUS") != null ? rs.getString("MANUALAUDITSTATUS") : "" ;//人工审核状态(0未通过 -1财务未通过 1 人工通过 2 财务通过 -2 不通过)
				if("0".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工未通过 ");//人工审核状态	
					bean.setManualauditstatus1("财务未通过 ");//财务审核状态	
				}else if("1".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工通过");//人工审核状态	
					bean.setManualauditstatus1("财务未通过 ");//财务审核状态	
				}else if("2".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工通过");//人工审核状态	
					bean.setManualauditstatus1("财务通过");//财务审核状态
				}else if("-1".equals(manualauditstatus2)){
					bean.setManualauditstatus("人工通过");//人工审核状态	
					bean.setManualauditstatus1("财务不通过");//财务审核状态
				}else if("-2".equals(manualauditstatus2)){
					bean.setManualauditstatus1("财务未通过");//财务审核状态
					bean.setManualauditstatus("人工不通过 ");//人工审核状态	
				}else{
					bean.setManualauditstatus("");//人工审核状态	
					bean.setManualauditstatus1("");//财务审核状态
				}
				
				String cityaudit1 = rs.getString("CITYAUDIT") != null ? rs.getString("CITYAUDIT") : "";
				if("0".equals(cityaudit1)){
					bean.setCityaudit("未审核 ");
				}else if("1".equals(cityaudit1)){
					bean.setCityaudit("审核通过");
				}else if("-2".equals(cityaudit1)){
					bean.setCityaudit("不通过");
				}else{
					bean.setCityaudit("");
				}
				
				String beilv = rs.getString("BEILV") != null ? rs.getString("BEILV") : "";
				bean.setBeilv(beilv);
				bean.setPjsj(rs.getString("NOTETIME") != null ? rs.getString("NOTETIME") : "");
				bean.setPjbh(rs.getString("NOTENO") != null ? rs.getString("NOTENO") : "");
				bean.setPjlx(rs.getString("NOTETYPEID") != null ? rs.getString("NOTETYPEID") : "");
				bean.setJfsj(rs.getString("PAYDATETIME") != null ? rs.getString("PAYDATETIME") : "");
				
				String autoauditstatus = rs.getString("AUTOAUDITSTATUS") != null ? rs.getString("AUTOAUDITSTATUS") : "";
				String manpassmeno = rs.getString("MANPASSMEMO") != null ? rs.getString("MANPASSMEMO") : "";
				if(!"1".equals(autoauditstatus)){
					bean.setManpassmeno(manpassmeno);
				}
				
				if("".equals(zq)||"null".equals(zq)||zq == null){
					zq="0";
				}
				double dbydl = Format.str_d(blhdl) * Format.str_d(beilv); 
				bean.setDbydl(String.valueOf(dbydl));//电表用电量
				
//				double bzydl = (Format.str_d(zq) + Format.str_d(qsdbdl)) * Format.str_d(beilv);
//				bean.setBzydl(String.valueOf(bzydl));//报账用电量
				
				//超省标电量度
				//超市标电量度
				String csbdld = String.valueOf(Format.d2(Format.str_d(bean.getBlhdl()) - Format.str_d(bean.getQsdbdl())*Format.str_d(bean.getJszq())));
				String cshibdld = String.valueOf(Format.d2(Format.str_d(bean.getBlhdl()) - Format.str_d(bean.getEdhdl())*Format.str_d(bean.getJszq())));
				bean.setCsbdld(csbdld);
				bean.setCshibdld(cshibdld);
				
				
				double sdbdf = Format.str_d(qsdbdl) * Format.str_d(danjia) * Format.str_d(zq);//省定标电费
				
				double csdbdfe = Format.str_d(actualpay) - sdbdf;
				bean.setCsdbdfe(Format.str2(String.valueOf(csdbdfe)));//超省定标电费额
				double csdbdfjdz = Math.abs(csdbdfe);//超省标电费绝对值
				bean.setCsdbdfjdz(String.valueOf(Format.d2(csdbdfjdz)));
				double csdbdfbz = csdbdfe/sdbdf*100;
				bean.setCsdbdfbz(Format.str2(String.valueOf(csdbdfbz)));//超省定标电费比值
				
				double sdbdfe = Format.str_d(bean.getEdhdl()) * Format.str_d(danjia) * Format.str_d(zq);//市定标电费
				double csdbdf = Format.str_d(actualpay) - sdbdfe;
				double cshidbdfjdz = Math.abs(csdbdf);//超市标电费绝对值
				bean.setCshidbdfjdz(String.valueOf(Format.d2(cshidbdfjdz)));
				double cshidbdfbz = csdbdf/sdbdfe*100;
				bean.setCshibdfbl(Format.str2(String.valueOf(cshidbdfbz)));//超市定标电费比值
			
				bean.setBiaozhi("yufufei");
				bean.setZflxflag("2");
				bean.setSort("预付费");
				bean.setLastelecfees("");//上次电费
				bean.setLastelecdegree("");//上次电量
				bean.setLastunitprice("");//上次单价
				
				String dlid1 = rs.getString("AMMETERDEGREEID_FK") != null ? rs.getString("AMMETERDEGREEID_FK") : "";
				String id1 = rs.getString("ID") != null ? rs.getString("ID") : "";
				bean.setTbtzsq("");
				bean.setDlid(dlid1);
				bean.setElectricfee_id(id1);
				
				if("0".equals(countryheadstatus1)){
					bean.setGdtx("待审核");
				}else if("1".equals(countryheadstatus1)){
					if("-2".equals(cityaudit1)){
						bean.setGdtx("上级退单");
					}else if("1".equals(cityaudit1) || "0".equals(cityaudit1)){
						bean.setGdtx("");
					}
				}
				bean.setLastfloatdegreeandbl("");//上期电量调整*倍率
				bean.setLastactualdegree("");//上期电表用电量
				bean.setFloatdegreeandbl("");//电量调整*倍率
				bean.setLineandchangeandbl("");//线变损电量
				String bzrj = rs.getString("BZRJ")!=null?rs.getString("BZRJ"):"0"; //报账日均电量
				bean.setBzrj(Format.str2(bzrj));//报账日均电量
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
			
	}
	
	public List<CountryHeadBean> dfMoreInfo(String dbid, String biaozhi) {
		List<CountryHeadBean> list = new ArrayList<CountryHeadBean>();//声明结果集
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ZD.ID, ZD.JZNAME, DB.DBNAME, EF.ACCOUNTMONTH, AM.BLHDL, EF.ACTUALPAY, AM.LASTDEGREE, AM.THISDEGREE,"
				+" AM.LASTDATETIME, AM.THISDATETIME, AM.FLOATDEGREE, AM.MEMO AS AMMEMO, EF.FLOATPAY, EF.MEMO AS EFMEMO,"
				+" (CASE WHEN ZD.XIAN IS NOT NULL THEN RNDIQU(ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN RNDIQU(ZD.XIAOQU) ELSE '' END) AS SSDQ"
				+" FROM ZHANDIAN ZD,DIANBIAO DB,AMMETERDEGREES AM,ELECTRICFEES EF"
				+" WHERE ZD.ID=DB.ZDID AND DB.DBID=AM.AMMETERID_FK AND AM.AMMETERDEGREEID=EF.AMMETERDEGREEID_FK "
				+" AND DB.DBID = '"+dbid+"'");
		System.out.println("区县主任审核详细信息查询");
		
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());//运行sql
			
			while(rs.next()){

				CountryHeadBean bean = new CountryHeadBean();

				bean.setZdid(rs.getString("ID") != null ? rs.getString("ID"): "");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME"): "");
				bean.setDbname(rs.getString("DBNAME") != null ? rs.getString("DBNAME"): "");
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH"): "");
				bean.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL"): "");
				bean.setActualpay(rs.getString("ACTUALPAY") != null ? rs.getString("ACTUALPAY"): "");
				bean.setLastdegree(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE"): "");
				bean.setThisdegree(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE"): "");
				bean.setLastdatetime(rs.getString("LASTDATETIME") != null ? rs.getString("LASTDATETIME"): "");
				bean.setThisdatetime(rs.getString("THISDATETIME") != null ? rs.getString("THISDATETIME"): "");
				bean.setFloatdegree(rs.getString("FLOATDEGREE") != null ? rs.getString("FLOATDEGREE"): "");
				bean.setAmmemo(rs.getString("AMMEMO") != null ? rs.getString("AMMEMO"): "");
				bean.setFloatpay(rs.getString("FLOATPAY") != null ? rs.getString("FLOATPAY"): "");
				bean.setEfmemo(rs.getString("EFMEMO") != null ? rs.getString("EFMEMO"): "");
				bean.setSsdq(rs.getString("SSDQ") != null ? rs.getString("SSDQ"): "");
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
	
	}
	
	public List<CountryHeadBean> yffMoreInfo(String dbid, String biaozhi) {
		List<CountryHeadBean> list = new ArrayList<CountryHeadBean>();//声明结果集
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ZD.ID, ZD.JZNAME, DB.DBNAME, EF.ACCOUNTMONTH, EF.DIANLIANG, EF.MONEY, EF.STARTDEGREE, EF.STOPDEGREE,"
				+" EF.STARTDATE, EF.ENDDATE,"
				+" (CASE WHEN ZD.XIAN IS NOT NULL THEN RNDIQU(ZD.XIAN) ELSE '' END) || (CASE WHEN ZD.XIAOQU IS NOT NULL THEN RNDIQU(ZD.XIAOQU) ELSE '' END) AS SSDQ"
				+" FROM ZHANDIAN ZD, DIANBIAO DB, YUFUFEIVIEW EF"
				+" WHERE ZD.ID = DB.ZDID AND DB.DBID = EF.PREPAYMENT_AMMETERID"
				+" AND DB.DBID = '"+dbid+"'");
		
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());//运行sql
			
			while(rs.next()){

				CountryHeadBean bean = new CountryHeadBean();

				bean.setZdid(rs.getString("ID") != null ? rs.getString("ID"): "");
				bean.setJzname(rs.getString("JZNAME") != null ? rs.getString("JZNAME"): "");
				bean.setDbname(rs.getString("DBNAME") != null ? rs.getString("DBNAME"): "");
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH") != null ? rs.getString("ACCOUNTMONTH"): "");
				bean.setBlhdl(rs.getString("DIANLIANG") != null ? rs.getString("DIANLIANG"): "");
				bean.setActualpay(rs.getString("MONEY") != null ? rs.getString("MONEY"): "");
				bean.setLastdegree(rs.getString("STARTDEGREE") != null ? rs.getString("STARTDEGREE"): "");
				bean.setThisdegree(rs.getString("STOPDEGREE") != null ? rs.getString("STOPDEGREE"): "");
				bean.setLastdatetime(rs.getString("STARTDATE") != null ? rs.getString("STARTDATE"): "");
				bean.setThisdatetime(rs.getString("ENDDATE") != null ? rs.getString("ENDDATE"): "");
				bean.setSsdq(rs.getString("SSDQ") != null ? rs.getString("SSDQ"): "");
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
	}

	/**
	 * @author lijing
	 * @see 济宁电费审核调整申请详细信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public CountryHeadBean xiangxi(String whereStr) {
		
		CountryHeadBean bean = new CountryHeadBean();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT E.UNITPRICE,E.BEILV,A.MEMO AMEMO,E.MEMO EMEMO, " 
				+"A.FLOATDEGREE,A.BLHDL,E.FLOATPAY, E.ACTUALPAY, A.DBYDL, E.YDDF," 
				+"(SELECT IND.NAME FROM INDEXS IND WHERE IND.CODE=E.LINELOSSTYPE AND IND.TYPE='xslx') LINELOSSTYPE," 
				+"E.LINELOSSVALUE,E.CHANGEVALUE,A.LASTDEGREE,A.THISDEGREE,E.ACTUALLINELOSSVALUE,A.BZZ,E.PROPERTYCODE,E.STATIONTYPECODE"
				+" FROM ELECTRICFEES E, AMMETERDEGREES A"
				+" WHERE  A.AMMETERDEGREEID = E.AMMETERDEGREEID_FK"
				+ whereStr);
		System.out.println("济宁电费审核调整申请详细信息:"+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			rs = sta.executeQuery(sql.toString());
			
			if(rs.next()){
				bean.setUnitprice(rs.getString("UNITPRICE") != null ? rs.getString("UNITPRICE"):"0");//本次单价
				bean.setAmmemo(rs.getString("AMEMO") != null ? rs.getString("AMEMO"): "");//电量备注
				bean.setEfmemo(rs.getString("EMEMO") != null ? rs.getString("EMEMO"): "");//电费备注
				bean.setFloatdegree(rs.getString("FLOATDEGREE") != null ? rs.getString("FLOATDEGREE"): "0");//电量调整
				bean.setFloatpay(rs.getString("FLOATPAY") != null ? rs.getString("FLOATPAY"): "0");//电费调整
				bean.setBeilv(Format.str_d(rs.getString("BEILV"))==0?"1":rs.getString("BEILV"));//倍率
				bean.setBlhdl(rs.getString("BLHDL") != null ? rs.getString("BLHDL"): "0");//报账用电量
				bean.setActualpay(rs.getString("ACTUALPAY") != null ? rs.getString("ACTUALPAY"): "0");//报账电费
				bean.setDbydl(rs.getString("DBYDL") != null ? rs.getString("DBYDL"): "0");//电表用电量
				bean.setYddf(rs.getString("YDDF") != null ? rs.getString("YDDF"): "0");//用电电费
				bean.setFloatdegreeandbl(String.valueOf(Format.d2(Format.str_d(bean.getFloatdegree())*Format.str_d(bean.getBeilv()))));//电量调整*倍率
				bean.setLinelosstype(rs.getString("LINELOSSTYPE") != null ? rs.getString("LINELOSSTYPE"): "");//线损类型
				bean.setLinelossvalue(rs.getString("LINELOSSVALUE") != null ? rs.getString("LINELOSSVALUE"): "");//线损值
				bean.setChangevalue(rs.getString("CHANGEVALUE") != null ? rs.getString("CHANGEVALUE"): "");//变损值
				bean.setLastdegree(rs.getString("LASTDEGREE") != null ? rs.getString("LASTDEGREE"): "");//上次电表读数
				bean.setThisdegree(rs.getString("THISDEGREE") != null ? rs.getString("THISDEGREE"): "");//本次电表读数
				bean.setActuallinelossvalue(rs.getString("ACTUALLINELOSSVALUE") != null ? rs.getString("ACTUALLINELOSSVALUE"): "");//实际线损值
				bean.setBzz(rs.getString("BZZ"));
				bean.setProperty(rs.getString("PROPERTYCODE") != null ? rs.getString("PROPERTYCODE"): "");//站点属性code
				bean.setStationtype(rs.getString("STATIONTYPECODE") != null ? rs.getString("STATIONTYPECODE"): "");//站点类型code
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, sta, conn);
		}
		return bean;
	}

	/**
	 * @author lijing
	 * @see 保存济宁电费审核调整申请信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@Override
	public String save(CountryHeadBean bean,Share share) {
		
		String msg = "调整申请信息修改失败！";
    	
    	String sql1 = "update AMMETERDEGREES SET DBYDL="+bean.getDbydl()+", FLOATDEGREE="+bean.getFloatdegree()+", MEMO='"+bean.getAmmemo()
    				+"', BLHDL='"+bean.getBlhdl()+"', DEDHDL='"+bean.getDedhdl()+"', CSDB='"+bean.getCsdb()+"',BEILV="+bean.getBeilv()+", " 
    				+ "NETWORKHDL="+share.getNetworkhdl()+",INFORMATIZATIONHDL="+share.getInformatizationhdl()+"," +
					"ADMINISTRATIVEHDL="+share.getAdministrativehdl()+",MARKETHDL="+share.getMarkethdl()+"," +
					"BUILDHDL="+share.getBuildhdl()+",DDDF="+share.getDddl()+" " 
					+ "WHERE AMMETERDEGREEID='" + bean.getDlid()+"'";
    	System.out.println("电量调整申请信息sql："+sql1);

    	String sql2 = "update ELECTRICFEES SET FLOATPAY="+bean.getFloatpay()+", MEMO='"+bean.getEfmemo()+"', YDDF="+bean.getYddf() + ", ACTUALPAY='"+bean.getActualpay()+"',"
    				+"BEILV="+bean.getBeilv()+",LINELOSSVALUE="+bean.getLinelossvalue()+",CHANGEVALUE="+bean.getChangevalue()+",ACTUALLINELOSSVALUE="+bean.getActuallinelossvalue()+","
    				+ "NETWORKDF="+share.getNetworkdf()+",INFORMATIZATIONDF="+share.getInformatizationdf()+"," +
					"ADMINISTRATIVEDF="+share.getAdministrativedf()+",MARKETDF="+share.getMarketdf()+",BUILDDF="+share.getBuilddf()+"" +
					",DDDF="+share.getDddf()+" "
					+ "WHERE ELECTRICFEE_ID='" + bean.getElectricfee_id()+"'";
		System.out.println("电费调整申请信息sql："+sql2);

		try {
			db.connectDb();	 
	    	conn = db.getConnection();
	    	ps = conn.prepareStatement(sql1);
	    	
			int count1 = 0;
			count1 = ps.executeUpdate(); //返回对数据库的影响行数
			ps = conn.prepareStatement(sql2);
		   
			int count2 = 0;
			count2 = ps.executeUpdate();
			
			if(count1==1&count2==1){
				msg = "调整申请信息修改成功!!";
			}
			
		}  catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(null,ps,conn);
		}
		return msg;
	}

	/**
	 * @author lijing
	 * @see 济宁电费审核调整申请详细信息(分摊)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@Override
	public Share ftInfo(String dlid, String electricfeeId) {
		
		Share share = new Share();	//声明分摊信息bean

	    StringBuffer sql = new StringBuffer();
	    sql.append("select AD.NETWORKHDL,AD.INFORMATIZATIONHDL,AD.ADMINISTRATIVEHDL,AD.MARKETHDL,AD.BUILDHDL,AD.DDDF,"
	    		 + "EF.NETWORKDF,EF.INFORMATIZATIONDF,EF.ADMINISTRATIVEDF,EF.MARKETDF,EF.BUILDDF,EF.DDDF "
	    		 + "from zhandian z,dianbiao d, ammeterdegrees AD,electricfees EF "
	    		 + "where z.id=d.zdid and d.dbid= ad.AMMETERID_FK and ad.ammeterdegreeid=ef.ammeterdegreeid_fk"
	    		 + " and EF.ELECTRICFEE_ID='"+electricfeeId+"'"
	    		 + " and AD.AMMETERDEGREEID='"+dlid+"'");
    	System.out.println("审核调整申请分摊信息sql:"+sql);

	    try {
	    	db.connectDb();	 
	    	conn = db.getConnection();
	    	sta = conn.createStatement();
	    	rs = sta.executeQuery(sql.toString());
	    	if(rs.next()) {
				
				//电量分摊
				share.setNetworkhdl(rs.getString("NETWORKHDL")!= null ? Double.parseDouble(rs.getString("NETWORKHDL")) : 0);
				share.setAdministrativehdl(rs.getString("ADMINISTRATIVEHDL")!= null ? Double.parseDouble(rs.getString("ADMINISTRATIVEHDL")) : 0);
				share.setMarkethdl(rs.getString("MARKETHDL")!= null ? Double.parseDouble(rs.getString("MARKETHDL")) : 0);
				share.setInformatizationhdl(rs.getString("INFORMATIZATIONHDL")!= null ? Double.parseDouble(rs.getString("INFORMATIZATIONHDL")) : 0);
				share.setBuildhdl(rs.getString("BUILDHDL")!= null ? Double.parseDouble(rs.getString("BUILDHDL")) : 0);
				share.setDddl(rs.getString("DDDF")!= null ? Double.parseDouble(rs.getString("DDDF")) : 0);//代垫电量
				//电费分摊
				share.setNetworkdf(rs.getString("NETWORKDF")!= null ? Double.parseDouble(rs.getString("NETWORKDF")) : 0);
				share.setAdministrativedf(rs.getString("ADMINISTRATIVEDF")!= null ? Double.parseDouble(rs.getString("ADMINISTRATIVEDF")) : 0);
				share.setMarketdf(rs.getString("MARKETDF")!= null ? Double.parseDouble(rs.getString("MARKETDF")) : 0);
				share.setInformatizationdf(rs.getString("INFORMATIZATIONDF")!= null ? Double.parseDouble(rs.getString("INFORMATIZATIONDF")) : 0);
				share.setBuilddf(rs.getString("BUILDDF")!= null ? Double.parseDouble(rs.getString("BUILDDF")) : 0);
				share.setDddf(rs.getString("DDDF")!= null ? Double.parseDouble(rs.getString("DDDF")) : 0);//代垫电费
			}
	    } catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return share;
	}
}
