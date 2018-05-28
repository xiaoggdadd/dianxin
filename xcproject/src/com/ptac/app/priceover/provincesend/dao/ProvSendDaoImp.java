package com.ptac.app.priceover.provincesend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.priceover.provincesend.bean.ProvSendBean;

/**
 * @author lijing
 * @see 单价超标省派单实现层
 */
public class ProvSendDaoImp implements ProvSendDao {

	@Override
	public List<ProvSendBean> queryExport(String string, String bl) {
		
		List<ProvSendBean> list = new ArrayList<ProvSendBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(SHI),JZNAME,PLD,BZPRICE,SJPRICE,ACTUALPAY,BLHDL,RTNAME(PROPERTY),"
				 + "(SELECT NAME FROM INDEXS WHERE CODE = STATIONTYPE AND TYPE='stationtype') AS STATIONTYPE,"
				 + " rtname(GDFS),ZDID,RNDIQU(XIAN),RNDIQU(XIAOQU),PROVINCEPD,ID,TO_CHAR(ACCOUNTMONTH,'YYYY-MM') ACCOUNTMONTH"
				 + " FROM PRICEPROCEDURE WHERE 1=1 AND CITYSIGN='0' "
				 + string
				 + (bl==""?"":(" AND PLD >="+bl)));
		
		System.out.println("省派单信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				ProvSendBean bean = new ProvSendBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//市
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//站点名称
				String pld = rs.getString(3) != null ? rs.getString(3) : "";//偏离度
				String bzdj = rs.getString(4) != null ? rs.getString(4) : "";//标准单价
				String sjdj = rs.getString(5) != null ? rs.getString(5) : "";//实际单价
				String bzdf = rs.getString(6) != null ? rs.getString(6) : "";//报账电费
				String bzdl = rs.getString(7) != null ? rs.getString(7) : "";//报账电量
				String zdsx = rs.getString(8) != null ? rs.getString(8) : "";//站点属性
				String zdlx = rs.getString(9) != null ? rs.getString(9) : "";//站点类型
				String gdfs = rs.getString(10) != null ? rs.getString(10) : "";//供电方式
				String zdid = rs.getString(11) != null ? rs.getString(11) : "";//站点ID
				String xian = rs.getString(12) != null ? rs.getString(12) : "";//区县
				String xiaoqu = rs.getString(13) != null ? rs.getString(13) : "";//乡镇
				String state = rs.getString(14) != null ? rs.getString(14) : "";//省派单状态
				String id = rs.getString(15) != null ? rs.getString(15) : "";
				String accountmonth = rs.getString(16) != null ? rs.getString(16) : "";
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setPld(pld);
				bean.setBzdj(bzdj);
				bean.setSjdj(sjdj);
				bean.setBzdf(bzdf);
				bean.setBzdl(bzdl);
				bean.setZdsx(zdsx);
				bean.setZdlx(zdlx);
				bean.setGdfs(gdfs);
				bean.setZdid(zdid);
				bean.setXian(xian);
				bean.setXiaoqu(xiaoqu);
				if("0".equals(state)){
					bean.setState("未派单");
				}else if("1".equals(state)){
					bean.setState("已派单");
				}
				bean.setId(id);
				bean.setAccountmonth(accountmonth);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public int pdUpdate(String id, String loginName) {

	 	int msg=0;
		String sql="update PRICEPROCEDURE set PROVINCEPD='1',PROVINCEPERSON='"+loginName+"',PROVINCETIME=sysdate where ID in ("+id+")";
		System.out.println("省派单："+sql.toString());
		DataBase db = new DataBase();
		try {	
			db.connectDb();
			db.update(sql.toString());
			db.commit();
			msg = 1;
		} catch (DbException e) {
			e.printStackTrace();
		}finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
    	return msg;
	}

	@Override
	public List<ProvSendBean> getInfo(String zdid,String bzyf) {
		
		DataBase db = null;
		Connection connc = null;
		Statement st = null;
		ResultSet rs = null;
		List<ProvSendBean> list = new ArrayList<ProvSendBean>();
		db = new DataBase();
		
		String sql = " SELECT ZD.JZNAME,to_char(AM.LASTDATETIME,'yyyy-mm-dd') LASTTIME,to_char( AM.THISDATETIME,'yyyy-mm-dd') THISTIME,AM.LASTDEGREE,AM.THISDEGREE," 
				   + " DB.BEILV,ROUND(AM.DBYDL,2) DBYDL,EF.ACTUALPAY,ROUND(AM.BLHDL,2) BLHDL,to_char( EF.ACCOUNTMONTH,'yyyy-mm') ACCOUNTMONTH" 
			       + " FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK"
			       + " AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK AND EF.MANUALAUDITSTATUS = '2' AND EF.ACTUALPAY>0 AND BLHDL>0 AND ZD.ID = '"+zdid+"'"
			       + (bzyf==""?"":(" AND to_char( EF.ACCOUNTMONTH,'yyyy-mm') = '"+bzyf+"'"))+" ORDER BY ACCOUNTMONTH DESC";
		
		System.out.println("省派单详细信息查询："+sql);
		try {
			connc = db.getConnection();
			st = connc.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				ProvSendBean os = new ProvSendBean();
				String zdname = rs.getString(1) != null ? rs.getString(1) : "";//站点名称
				String lasttime = rs.getString(2) != null ? rs.getString(2) : "";//上次抄表时间
				String thistime = rs.getString(3) != null ? rs.getString(3) : "";//本次抄表时间
				String lastdegree = rs.getString(4) != null ? rs.getString(4) : "";//上次电表读数
				String thisdegree = rs.getString(5) != null ? rs.getString(5) : "";//本次电表读数
				String beilv = rs.getString(6) != null ? rs.getString(6) : "";//倍率
				String dbydl = rs.getString(7) != null ? rs.getString(7) : "";//电表用电量
				String bzdf = rs.getString(8) != null ? rs.getString(8) : "";//报账金额
				String bzdl = rs.getString(9) != null ? rs.getString(9) : "";//报账电量
				String accountmonth = rs.getString(10) != null ? rs.getString(10) : "";//报账月份
				
				os.setZdname(zdname);
				os.setLasttime(lasttime);
				os.setThistime(thistime);
				os.setLastdegree(lastdegree);
				os.setThisdegree(thisdegree);
				os.setBeilv(beilv);
				os.setDbydl(dbydl);
				os.setBzdf(bzdf);
				os.setBzdl(bzdl);
				os.setAccountmonth(accountmonth);
				
				list.add(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, st, connc);
		}
		return list;
	}

}
