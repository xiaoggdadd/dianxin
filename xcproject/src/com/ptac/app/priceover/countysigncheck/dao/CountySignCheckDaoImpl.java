package com.ptac.app.priceover.countysigncheck.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.CTime;
import com.noki.util.Query;
import com.ptac.app.inportuserzibaodian.util.Format;
import com.ptac.app.priceover.countysigncheck.bean.ChaXunBean;
import com.ptac.app.priceover.countysigncheck.bean.CheckBean;

public class CountySignCheckDaoImpl implements CountySignCheckDao {
	private Logger logger = Logger.getLogger(CountySignCheckDaoImpl.class);
	private DataBase db;
	private Connection connc;
	private ResultSet rs;
	private Statement st;
	@Override
	public boolean selectNoSign(String loginid) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer("SELECT PE.ID FROM PRICEPROCEDURE PE WHERE PE.CITYPD = '1'"
			+" AND PE.COUNTYSIGN = '0' AND (PE.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='").append(loginid).append("'))");
		db = new DataBase();
		try {
		connc = db.getConnection();
		st = connc.createStatement();
		logger.info("单价超标--区县签收查询是否有未签收的单子:"+sql.toString());
		rs = st.executeQuery(sql.toString());
			flag = rs.next()?true:false;
		}catch (DbException e1){
			logger.error("单价超标--区县签收查询是否有未签收的单子 数据库连接出错:"+e1.getMessage());
			e1.printStackTrace();
		}catch (SQLException e) {
			logger.error("单价超标--区县签收查询是否有未签收的单子sql执行出错:"+e.getMessage());
			e.printStackTrace();
		} finally{
			db.free(rs, st, connc);
		}
		return flag;
	}
	@Override
	public boolean sign(String loginid, String loginName) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer("UPDATE PRICEPROCEDURE P SET P.COUNTYSIGN = '1',P.COUNTYSIGNPERSON = '")
		.append(loginName).append("',P.COUNTYSIGNTIME = SYSDATE"
		+" WHERE EXISTS (SELECT 1 FROM (SELECT PE.ID PEID FROM PRICEPROCEDURE PE"
          +" WHERE PE.CITYPD = '1' AND PE.COUNTYSIGN = '0' AND (PE.XIAOQU IN"
          +" (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '").append(loginid).append("')) ) AA WHERE P.ID = AA.PEID)");
		db = new DataBase();
		try {
		connc = db.getConnection();
		st = connc.createStatement();
		logger.info("单价超标--区县签收:"+sql.toString());
		int n = st.executeUpdate(sql.toString());
			flag = n > 0 ? true : false;
		}catch (Exception e) {
			logger.error("单价超标--区县签收出错:"+e.getMessage());
			e.printStackTrace();
		} finally{
			db.free(null, st, connc);
		}
		return flag;
	}
	@Override
	public List<ChaXunBean> chaxun(String wherestr, String loginid) {
		List<ChaXunBean> list = new ArrayList<ChaXunBean>();
		StringBuffer sql = new StringBuffer("SELECT PE.ID PID, RNDIQU(PE.SHI) SHI,PE.JZNAME ZDNAME,PE.PLD PLD,PE.BZPRICE,PE.SJPRICE,"
       +"PE.ACTUALPAY,PE.BLHDL,TO_CHAR(PE.ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH,RTNAME(PE.PROPERTY) PROPERTY,RTNAME(PE.STATIONTYPE) STATIONTYPE,RTNAME(PE.GDFS) GDFS,PE.ZDID ZDID,"
       +"RNDIQU(PE.XIAN) XIAN,RNDIQU(PE.XIAOQU) XIAOQU,PE.COUNTYCHECK COUNTYCHECK,PE.CITYAUDIT CITYAUDIT FROM PRICEPROCEDURE PE WHERE PE.CITYPD = '1' AND (PE.CITYAUDIT='0' OR PE.CITYAUDIT='2') ").append(wherestr).append(" AND (PE.XIAOQU IN"
          +" (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '").append(loginid).append("'))");
		
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			logger.info("单价超标--区县签收及派单查询:"+sql.toString());
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				ChaXunBean bean = new ChaXunBean();
				bean.setPid(rs.getString("PID"));
				bean.setCity(rs.getString("SHI"));
				bean.setZdname(rs.getString("ZDNAME"));
				bean.setPld(Format.str2(rs.getString("PLD")));
				bean.setStandardprice(Format.str4(rs.getString("BZPRICE")));
				bean.setActualprice(Format.str4(rs.getString("SJPRICE")));
				bean.setBzdf(Format.str2(rs.getString("ACTUALPAY")));
				bean.setBzdl(Format.str2(rs.getString("BLHDL")));
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH"));
				bean.setProperty(rs.getString("PROPERTY"));
				bean.setStationtype(rs.getString("STATIONTYPE"));
				bean.setGdfs(rs.getString("GDFS"));
				bean.setZdid(rs.getString("ZDID"));
				bean.setXian(rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU"));
				bean.setCountycheck(rs.getString("COUNTYCHECK"));
				bean.setState(rs.getString("CITYAUDIT"));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("单价超标--区县签收及派单查询失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		} 
		return list;
	}
	@Override
	public CheckBean check(String pid) {
		CheckBean bean = new CheckBean();
		StringBuffer sql = new StringBuffer("SELECT PE.ID PEID,PE.JZNAME ZDNAME,PE.ZDID,RTNAME(PE.PROPERTY) PROPERTY,RTNAME(PE.STATIONTYPE) STATIONTYPE,"
				+"RNDIQU(PE.SHI) SHI,RNDIQU(PE.XIAN) XIAN,RNDIQU(PE.XIAOQU) XIAOQU,TO_CHAR(PE.ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH,"
				+"PE.ACTUALPAY,PE.BLHDL,PE.SJPRICE,PE.BZPRICE,PE.PLD,RTNAME(PE.GDFS) GDFS,PE.GDFS GDFSCODE,PE.YZYDLX,PE.YZHDJCDJ,PE.HZDJ,PE.ZGDJCDJ,"
				+"PE.JFDLZB,PE.PDLZB,PE.GFDLZB,PE.GDLZB,PE.BYQRL,PE.BL,PE.YDSX,PE.QSDBDL,PE.XBSL,PE.XBSDL,PE.GLFSFXS,PE.MSSFXS"
				+" FROM PRICEPROCEDURE PE WHERE PE.ID = '").append(pid).append("'");
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			logger.info("单价超标--区县签收及派单核实内容查询:"+sql.toString());
			rs = st.executeQuery(sql.toString());
			if(rs.next()){
				bean.setPeid(rs.getString("PEID")==null?"":rs.getString("PEID"));
				bean.setZdname(rs.getString("ZDNAME")==null?"":rs.getString("ZDNAME"));
				bean.setZdid(rs.getString("ZDID")==null?"":rs.getString("ZDID"));
				bean.setProperty(rs.getString("PROPERTY")==null?"":rs.getString("PROPERTY"));
				bean.setStationtype(rs.getString("STATIONTYPE")==null?"":rs.getString("STATIONTYPE"));
				bean.setCity(rs.getString("SHI")==null?"":rs.getString("SHI"));
				bean.setXian(rs.getString("XIAN")==null?"":rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU")==null?"":rs.getString("XIAOQU"));
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH")==null?"":rs.getString("ACCOUNTMONTH"));
				bean.setBzdf(rs.getString("ACTUALPAY")==null?"":rs.getString("ACTUALPAY"));
				bean.setBzdl(rs.getString("BLHDL")==null?"":rs.getString("BLHDL"));
				bean.setSjprice(rs.getString("SJPRICE")==null?"":Format.str4(rs.getString("SJPRICE")));
				bean.setBzprice(rs.getString("BZPRICE")==null?"":Format.str4(rs.getString("BZPRICE")));
				bean.setPld(rs.getString("PLD")==null?"":rs.getString("PLD"));
				bean.setGdfs(rs.getString("GDFS")==null?"":rs.getString("GDFS"));
				bean.setGdfscode(rs.getString("GDFSCODE")==null?"":rs.getString("GDFSCODE"));
				bean.setYzydlx(rs.getString("YZYDLX")==null?"":rs.getString("YZYDLX"));
				bean.setYzhdjcdj(rs.getString("YZHDJCDJ")==null?"":rs.getString("YZHDJCDJ"));
				bean.setHzprice(rs.getString("HZDJ")==null?"":rs.getString("HZDJ"));
				bean.setZgdjcdj(rs.getString("ZGDJCDJ")==null?"":rs.getString("ZGDJCDJ"));
				bean.setJfdlzb(rs.getString("JFDLZB")==null?"":rs.getString("JFDLZB"));
				bean.setPdlzb(rs.getString("PDLZB")==null?"":rs.getString("PDLZB"));
				bean.setGfdlzb(rs.getString("GFDLZB")==null?"":rs.getString("GFDLZB"));
				bean.setGdlzb(rs.getString("GDLZB")==null?"":rs.getString("GDLZB"));
				bean.setByqrl(rs.getString("BYQRL")==null?"":rs.getString("BYQRL"));
				bean.setBeilv(rs.getString("BL")==null?"":rs.getString("BL"));
				bean.setYdsx(rs.getString("YDSX")==null?"":rs.getString("YDSX"));
				bean.setSdb(Format.str_d(rs.getString("QSDBDL"))==0?"0.01":rs.getString("QSDBDL"));
				bean.setXbsl(rs.getString("XBSL")==null?"0":rs.getString("XBSL"));
				bean.setXbsdl(rs.getString("XBSDL")==null?"":rs.getString("XBSDL"));
				bean.setGlfsfxs(rs.getString("GLFSFXS")==null?"":rs.getString("GLFSFXS"));
				bean.setMssfxs(rs.getString("MSSFXS")==null?"":rs.getString("MSSFXS"));

			}
		} catch (Exception e) {
			logger.error("单价超标--区县签收及派单核实内容查询失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		} 
		return bean;
	}
	@Override
	public String commit(CheckBean bean, String accountid, String personnal) {
		String msg = "单价核实信息提交失败!请重试!";
		int n=0;
		StringBuffer sql = new StringBuffer("UPDATE PRICEPROCEDURE PE SET PE.CITYAUDIT='0',PE.PROVINCEAUDIT='0',PE.YZYDLX='")
		.append(bean.getYzydlx()).append("',PE.YZHDJCDJ='").append(bean.getYzhdjcdj())
		.append("',").append("PE.HZDJ='").append(bean.getHzprice()).append("',").append("PE.JFDLZB='").append(bean.getJfdlzb())
		.append("',").append("PE.PDLZB='").append(bean.getPdlzb()).append("',").append("PE.ZGDJCDJ='").append(bean.getZgdjcdj())
		.append("',").append("PE.GFDLZB='").append(bean.getGfdlzb()).append("',").append("PE.GDLZB='").append(bean.getGdlzb())
		.append("',").append("PE.BYQRL='").append(bean.getByqrl()).append("',").append("PE.BL='").append(bean.getBeilv())
		.append("',").append("PE.YDSX='").append(bean.getYdsx()).append("',").append("PE.XBSL='").append(bean.getXbsl())
		.append("',").append("PE.XBSDL='").append(bean.getXbsdl()).append("',").append("PE.GLFSFXS='").append(bean.getGlfsfxs())
		.append("',").append("PE.MSSFXS='").append(bean.getMssfxs()).append("',").append("PE.COUNTYCHECK='1'").append(",")
		.append("PE.COUNTYCHECKTIME=SYSDATE").append(",").append("PE.COUNTYCHECKPERSON='").append(personnal).append("'")
		.append(" WHERE PE.ID='").append(bean.getPeid()).append("'");
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			logger.info("单价超标--区县签收及派单核实内容提交:"+sql.toString());
			n = st.executeUpdate(sql.toString());
		} catch(Exception e){
			e.printStackTrace();
			logger.info("单价超标--区县签收及派单核实内容提交失败:"+e.getMessage());
		}finally{
			db.free(null, st, connc);
		}
		return n>0?"单价核实信息提交成功!":msg;
	}

	public synchronized ArrayList getPageDataModelpriceover(String id,String loginid) {
	    ArrayList list = new ArrayList();
	    String sql="SELECT RNDIQU(PE.SHI) SHI, RNDIQU(PE.XIAN) XIAN, RNDIQU(PE.XIAOQU) XIAOQU,PE.ZDID,PE.JZNAME FROM PRICEPROCEDURE PE WHERE "
	    	+ " PE.ID = '"+id+"' AND PE.XIAOQU IN(SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID='"+loginid + "')";
	    db = new DataBase();
	    try {				
				connc = db.getConnection();
				st = connc.createStatement();  
				logger.info("下载模板信息查询:"+sql);
				rs = st.executeQuery(sql);
		      Query query=new Query();
		      list = query.query(rs);
		 } catch (Exception de) {
			de.printStackTrace();
			logger.error("下载模板信息查询失败:"+de.getMessage());
		}finally {
			db.free(rs, st, connc);
		}
		return list;
	}
}
