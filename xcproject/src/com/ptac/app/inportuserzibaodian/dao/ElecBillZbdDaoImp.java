package com.ptac.app.inportuserzibaodian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.electricmanage.bargainbill.bean.ZhurenPanduanBean;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.BasicInfo;
import com.ptac.app.electricmanage.electricitybill.bean.DomainOther;
import com.ptac.app.electricmanage.electricitybill.bean.DomainType;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;

/**
 * @update WangYiXiao 2014-4-14 增加结算周期
 *
 */
public class ElecBillZbdDaoImp implements ElecBillZbdDao {

	@SuppressWarnings("unchecked")
	@Override
	public String addDegreeFees(List ammeterdegreeid,String dbid, ElectricityInfo elecInfo,
			Share share, String uuid, String bzw, String dfms, String dfbz,
			String dlms, String dlbz, String bl,String f,ZhurenPanduanBean zpd,String jszq,String edhdl,String qsdbdl) {
		

		String msg = "保存电费单失败！请重试或与管理员联系！";
		
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String sql2 = "INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,FLOATPAY,MEMO,"
				+ "ACCOUNTMONTH,ACTUALPAY,NOTETYPEID,INPUTOPERATOR,"
				+ "PAYOPERATOR,PJJE,DFUUID,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,NETWORKDF,"
				+ "MANUALAUDITSTATUS,CITYAUDIT,ENTRYPENSONNEL,ENTRYTIME,"
				+ "ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DDDF,DFBZW,LIUCHENGHAO"
				+",COUNTYAUDITSTATUS,CITYZRAUDITSTATUS,JSZQ,EDHDL,QSDBDL,COUNTYAUDITTIME,CITYAUDITTIME,CITYZRAUDITTIME,ZLFH,JLFH,PROPERTYCODE,STATIONTYPECODE,DFZFLXCODE,GDFSCODE"+
						") VALUES("
				+ "'"
				+ammeterdegreeid.get(0)+ "','"
				+ elecInfo.getUnitprice()
				+ "','"
				+ elecInfo.getFloatpay()
				+ "','"
				+ elecInfo.getMemo1()
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
				+f
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
				+ "',''" 
				+",'"+zpd.getCountyauditstatus()
				+"','"+zpd.getCityzrauditstatus()
				+"' "
				+",'"
				+jszq
				+"','"
				+edhdl
				+"','"
				+qsdbdl
				+"',"
				+zpd.getCountyaudittime()
				+","
				+zpd.getCityaudittime()
				+","
				+zpd.getCityzraudittime()
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
		
		System.out.println("新增电费sql：" + sql2);

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		
		try {
		   db.connectDb();
		   conn = db.getConnection();
		   
		   
		   ps2 = conn.prepareStatement(sql2.toString());
		  
		   rs2 = ps2.executeQuery();
		  
		   db.commit();
		   msg = "保存成功！";
		  
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(rs2, ps2, conn);
		}
		return msg;
	}

	public String addAm(String dbid,ElectricityInfo elecInfo,Share share,String uuid,String bzw,String dfms,String dfbz,String dlms,String dlbz,String bl,String f,ZhurenPanduanBean zpb,String hbzq,String bzz,String scb){
		

		String msg = "保存电费单失败！请重试或与管理员联系！";
		
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";

		String scdf = elecInfo.getScdf()==null?"":elecInfo.getScdf();
		String scdl = elecInfo.getScdl()==null?"":elecInfo.getScdl();
		String lastunitprice = elecInfo.getLastunitprice()==null?"":elecInfo.getLastunitprice();
		
		
		String sql1 = "INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,ACTUALDEGREE,LASTDATETIME,"
			+ "THISDATETIME,FLOATDEGREE,"
			+ "BLHDL,MEMO,"
			+ "UUID,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION,"
			+ "MANUALAUDITSTATUS,DEDHDL,INPUTOPERATOR, FLAG,"
			+ "ENTRYPENSONNEL,ENTRYTIME,"
			+ "NETWORKHDL,ADMINISTRATIVEHDL,"
			+ "MARKETHDL,INFORMATIZATIONHDL,BUILDHDL,DDDF,DLBZW," 
			+"countyflag,cityflag,CSDB,LASTELECFEES,LASTELECDEGREE,LASTUNITPRICE," 
			+"DBYDL,BEILV,HBZQ,BZZ,SCB"
			+")"
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
			+f
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
			+ bzw + "'," 
			+"'"+zpb.getCountyauditstatus()
			+"','"+zpb.getCityzrauditstatus()
			+"','"+zpb.getCsdb()
			+"','"
			+scdf
			+"','"
			+scdl
			+"','"
			+lastunitprice
			+"',"
			+elecInfo.getDbydl()
			+","
			+elecInfo.getBeilv()
			+","
			+bzz
			+","
			+hbzq
			+","
			+scb
			+")";

		System.out.println("电量:"+sql1);

		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		
		try {
		   db.connectDb();
		   conn = db.getConnection();
		   ps1 = conn.prepareStatement(sql1.toString());
		   
		   rs1 =  ps1.executeQuery();
		   
		  
		   msg = "保存成功！";
		   db.commit();
		  
		} catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		}finally{
			db.free(rs1, ps1, conn);
		}
		return msg;
	}
	
	/**
	 * @see 合同单结束时间
	 * @param dbid
	 * @return
	 * @author WangYiXiao 2014-12-10
	 */
	@Override
	public String getHtEndTime(String dbid) {
		Logger logger = Logger.getLogger(ElecBillZbdDaoImp.class);
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
	@Override
	public synchronized boolean getOut(String dbid){
		Logger logger = Logger.getLogger(ElecBillZbdDaoImp.class);
		boolean flag = false;
		String sql = "SELECT ZD.XMH FROM ZHANDIAN ZD,DIANBIAO DB WHERE ZD.ID = DB.ZDID  AND ZD.PROPERTY = 'zdsx04' AND ZD.JZNAME LIKE '%回收%' AND DB.DBID = '"+dbid+"'" ;
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
	@Override
	public AssistInfo ass(String dbid) {
		return null;
	}

	@Override
	public BasicInfo bas(String dbid) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList checkMh(String loginId, String jzname, String str) {
		return null;
	}

	@Override
	public ElectricityInfo elec(String dbid) {
		return null;
	}

	@Override
	public ElectricityInfo elec1(String zdcode) {
		return null;
	}

	@Override
	public ArrayList<DomainOther> getDomainOther(String dbid) {
		return null;
	}

	@Override
	public DomainType getDomainType(String dbid) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList queryElectric(String whereStr, String loginId) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList share1(String dbid) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList share2(String dbid) {
		return null;
	}

}
