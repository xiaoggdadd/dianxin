package com.ptac.app.checkstandard.provincesend.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.checkstandard.provincesend.bean.DetailBean;
import com.ptac.app.checkstandard.provincesend.bean.ProvinceSendBean;
import com.ptac.app.inportuserzibaodian.util.Format;

public class ProvinceSendDaoImpl implements ProvinceSendDao {
	private Logger logger = Logger.getLogger(ProvinceSendDaoImpl.class);
	private DataBase db;
	private Connection connc;
	private ResultSet rs;
	private Statement st;

	@Override
	public List<ProvinceSendBean> selectProvinceSend(String wherestr1,
			String wherestr2,String accountid) {
		List<ProvinceSendBean> list = new ArrayList<ProvinceSendBean>();
		StringBuffer sql = new StringBuffer("SELECT AA.CSID CSID,RNDIQU(AA.SHI) SHI, AA.JZNAME, AA.SCB, AA.JYSCB, AA.ZLFH, AA.JLFH, AA.EDHDL,RTNAME(AA.PROPERTY) PROPERTY,AA.ID ZDID,RNDIQU(AA.XIAN) XIAN,RNDIQU(AA.XIAOQU) XIAOQU,AA.BL"
				+ " FROM (SELECT CS.ID CSID,ZD.SHI,ZD.JZNAME,ZD.SCB,CS.JYSCB,ZD.ZLFH,ZD.JLFH,ZD.EDHDL,ZD.PROPERTY,ZD.ID,ZD.XIAN,ZD.XIAOQU,ROUND((ZD.SCB - CS.JYSCB) / CS.JYSCB * 100, 2) BL"
				+ " FROM ZHANDIAN ZD, CHECKSTANDARD CS WHERE ZD.ID = CS.ZDID AND ZD.XUNI = '0' AND ZD.CAIJI = '0' AND ZD.SHSIGN = '1' AND ZD.PROVAUDITSTATUS = '1' AND ZD.QYZT = '1'")
		.append(wherestr1).append(" AND (ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+accountid+"'))) AA WHERE 1=1 ").append(wherestr2).append(" ORDER BY AA.XIAOQU");
		
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			logger.info("核实标杆--省级手动更新及派单查询:"+sql.toString());
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				ProvinceSendBean bean = new ProvinceSendBean();
				bean.setBgid(rs.getString("CSID"));
				bean.setCity(rs.getString("SHI"));
				bean.setZdname(rs.getString("JZNAME"));
				bean.setScb(Format.str2(rs.getString("SCB")));
				bean.setJyscb(Format.str2(rs.getString("JYSCB")));
				bean.setZlfh(Format.str2(rs.getString("ZLFH")));
				bean.setJlfh(Format.str2(rs.getString("JLFH")));
				bean.setBdb(Format.str2(rs.getString("EDHDL")));
				bean.setProperty(rs.getString("PROPERTY"));
				bean.setZdid(rs.getString("ZDID"));
				bean.setQuxian(rs.getString("XIAN"));
				bean.setXiaoqu(rs.getString("XIAOQU"));
				bean.setXcbl(rs.getString("BL"));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("核实标杆--省级手动更新及派单查询失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		} 
		return list;
	}

	@Override
	public List<DetailBean> getDetail(String zdid) {
		List<DetailBean> list = new ArrayList<DetailBean>();
		StringBuffer sql = new StringBuffer("SELECT ZD.JZNAME,TO_CHAR(AM.LASTDATETIME,'YYYY-MM-DD') LASTDATETIME,TO_CHAR(AM.THISDATETIME,'YYYY-MM-DD') THISDATETIME,AM.LASTDEGREE,AM.THISDEGREE,AM.BEILV,AM.DBYDL,EF.ACTUALPAY,AM.BLHDL,TO_CHAR(EF.ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH,EF.CITYZRAUDITSTATUS"
				+" FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND ZD.ID = '")
				.append(zdid).append("' ORDER BY EF.ACCOUNTMONTH DESC NULLS LAST");
		db = new DataBase();
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			logger.info("核实标杆--省级手动更新及派单超链接详情查询:"+sql.toString());
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				DetailBean bean = new DetailBean();
				bean.setZdname(rs.getString("JZNAME"));
				bean.setLasttime(rs.getString("LASTDATETIME"));
				bean.setThistime(rs.getString("THISDATETIME"));
				bean.setLastdegree(rs.getString("LASTDEGREE"));
				bean.setThisdegree(rs.getString("THISDEGREE"));
				bean.setBeilv(rs.getString("BEILV"));
				bean.setDbydl(rs.getString("DBYDL"));
				bean.setBzdf(rs.getString("ACTUALPAY"));
				bean.setBzdl(rs.getString("BLHDL"));
				bean.setAccountmonth(rs.getString("ACCOUNTMONTH"));
				bean.setCityzrauditstatus(rs.getString("CITYZRAUDITSTATUS"));
				list.add(bean);
			}
		} catch (Exception e) {
			logger.error("核实标杆--省级手动更新及派单超链接详情查询失败:"+e.getMessage());
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		} 
		return list;
	}

	@Override
	public String paiDan(String[] bgid, String personnal) {
		String msg = "省级派单失败!";
		StringBuffer buffer = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		int size = bgid.length;
		db = new DataBase();
		try {
			connc = db.getConnection();
			connc.setAutoCommit(false);
			st = connc.createStatement();
			for (int i = 1; i <= size; i++) {
				buffer.append(bgid[i-1]).append(",");
				if(i%100==0||i==size){
					sql.append("UPDATE CHECKSTANDARD CD SET CD.PROVINCEPD='1',CD.PROVINCETIME=SYSDATE,CD.PROVINCEPERSON='")
					.append(personnal).append("' WHERE CD.ID IN(").append(buffer.substring(0, buffer.length()-1)).append(")");
					System.out.println(sql.toString());
				st.addBatch(sql.toString());
					buffer.delete(0, buffer.length());
					sql.delete(0, sql.length());
				}
			}
			logger.info("核实标杆--省级派单:"+sql.toString());
			int[] n = st.executeBatch();
			connc.commit();
			msg = "省级派单成功!";
		}catch (Exception e) {
			logger.error("核实标杆--省级派单失败:"+e.getMessage());
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				connc.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(rs, st, connc);
		}
		return msg;
	}

}
