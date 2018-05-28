package com.ptac.app.provinceapply.qyzt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;
import com.noki.zdqxkz.util.ZdqxkzUtil;
import com.ptac.app.provinceapply.qyzt.bean.QyztBean;

/**
 * @author lijing
 * @see 省申请启用状态审核实现层
 */
public class QyztDaoImp implements QyztDao {

	@Override
	public List<QyztBean> query(String string,String shengzt,String loginId) {
		
		List<QyztBean> list = new ArrayList<QyztBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(Z.SHI) SHI,RNDIQU(Z.XIAN) XIAN,Z.JZNAME JZNAME,Q.OLDQYZT OLDQYZT,Q.NEWQYZT NEWQYZT,RNDIQU(Z.XIAOQU) XIAOQU,"
				 + " Z.ID ZDID,Q.ID ID,(CASE WHEN DBMS_LOB.GETLENGTH(FJNR) IS NOT NULL OR DBMS_LOB.GETLENGTH(FJNR)>0 THEN '1' ELSE '0' END) FJNR"
				 + " FROM QSKZ Q,ZHANDIAN Z WHERE Q.ZDID = Z.ID AND Q.OLDQYZT <> Q.NEWQYZT AND Q.FLGG IS NOT NULL AND Q.QXPDBZ='2' AND Q.SHISHBZ='1' "+ string
				 + " AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))");
		
		System.out.println("省申请启用状态审核信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				QyztBean bean = new QyztBean();
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//市
				String xian = rs.getString("XIAN")==null?"":rs.getString("XIAN");//区县
				String zdname = rs.getString("JZNAME")==null?"":rs.getString("JZNAME");//站点名称
				String oldqyzt = rs.getString("OLDQYZT")==null?"":rs.getString("OLDQYZT");//申请前
				String newqyzt = rs.getString("NEWQYZT")==null?"":rs.getString("NEWQYZT");//申请后
				String xiaoqu = rs.getString("XIAOQU")==null?"":rs.getString("XIAOQU");//乡镇
				String zdid = rs.getString("ZDID")==null?"":rs.getString("ZDID");//站点ID
				String id = rs.getString("ID")==null?"":rs.getString("ID");
				boolean fjnr = rs.getString("FJNR").equals("0")?false:true;

				bean.setCity(city);
				bean.setXian(xian);
				bean.setZdname(zdname);
				
				if("0".equals(oldqyzt)){
					bean.setOldqyzt("未启用");
				}else if("1".equals(oldqyzt)){
					bean.setOldqyzt("启用");
				}
				
				if("0".equals(newqyzt)){
					bean.setNewqyzt("未启用");
				}else if("1".equals(newqyzt)){
					bean.setNewqyzt("启用");
				}
				
				bean.setXiaoqu(xiaoqu);
				bean.setZdid(zdid);
				bean.setId(id);
				bean.setState(shengzt);
				bean.setFjnr(fjnr);
				
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
	public boolean CheckFj(String string) {
			Logger logger = Logger.getLogger(QyztDaoImp.class);
			boolean flag = false;
			DataBase db = new DataBase();
			Connection conn = null;
			Statement st = null;
			ResultSet rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT Q.FJNR FROM QSKZ Q,ZHANDIAN Z WHERE Q.ZDID = Z.ID " + string + " AND Q.FJNR IS NOT NULL");
			db = new DataBase();
			try {
				conn = db.getConnection();
				st = conn.createStatement();
				logger.info("判断是否存在附件:"+sql.toString());
				rs = st.executeQuery(sql.toString());
				flag = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("判断是否存在附件失败:"+e.getMessage());
			}finally{
				db.free(rs, st, conn);
			}
				return flag;		
		}

	@Override
	public String check(List<QyztBean> list,String personnal,String cause,String string) {
		String msg = "启用状态审核信息失败！";
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql1 = new StringBuffer();
		
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			for(int i=0; i<list.size(); i++){
				QyztBean bean =list.get(i);
				String flgg = bean.getFlgg();
				String bftg = bean.getBftg();
				String bfbtg = bean.getBfbtg();
				String newqyzt = bean.getNewqyzt();
				String oldqyzt = bean.getOldqyzt();
				String zid = bean.getZdid();
				String qid = bean.getId();
				ZdqxkzUtil un = new ZdqxkzUtil();
				
				if(string.equals("0")){
					bftg = un.getFlbz(bftg, "8");
					bfbtg = un.getFlbz(bfbtg, "8");
					boolean c = un.checkKong(bftg);
					String sb = "";
					if(c==true){
						boolean d = un.checkKong(bfbtg);
						sb = d==true?"0":"4";
					}else{
						boolean d = un.checkKong(bfbtg);
						sb = d==true?"3":"5";
					}
					sql.append(" UPDATE QSKZ SET SHENGSHSJ=sysdate " 
							+ "," + "SHENGSHR='" + personnal + "'," +"BFTG = '"+bftg+ "'," +"BFBTG = '"+bfbtg+"',SHENGSHBZ='"+sb+"'"
							+ " WHERE ID = (" + qid + ")");
					
					sql1.append(" UPDATE ZHANDIAN SET QYZT='"+oldqyzt+"'" + " WHERE ID = (" + zid + ")");
					
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
					ps1 = conn.prepareStatement(sql1.toString());
					ps1.executeUpdate();
					
				}else if(string.equals("1")){
					bftg = un.getBftg(bftg, "8");
					boolean a = un.getShengbz(flgg, bftg);
					String sb = "";
					if(a==true){
						sb = "1";
					}else{
						sb = un.checkKong(bfbtg)?"3":"5";
					}
					sql.append(" UPDATE QSKZ SET SHENGSHSJ=sysdate " 
							+ "," + "SHENGSHR='" + personnal + "'," +"BFTG = '"+bftg+"',SHENGSHBZ='"+sb+"'"
							+ " WHERE ID = (" + qid + ")");
					
					sql1.append(" UPDATE ZHANDIAN SET QYZT='"+newqyzt+"'" + " WHERE ID = (" + zid + ")");
					
					conn = db.getConnection();
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
					ps1 = conn.prepareStatement(sql1.toString());
					ps1.executeUpdate();
				}else if(string.equals("2")){
					bfbtg = un.getBftg(bfbtg, "8");
					boolean a = un.getShengbz(flgg, bfbtg);
					String sb = "";
					if(a==true){
						sb = "2";
					}else{
						boolean b = un.checkKong(bftg);
						sb = b==true?"4":"5";
					}
					
					sql.append(" UPDATE QSKZ SET SHENGSHSJ=sysdate,QXLY='" + cause + "',SHENGSHR='" + personnal + "',BFBTG = '"+bfbtg+"',SHENGSHBZ='"+sb+"'"
							 + " WHERE ID = " + qid);
					
					conn = db.getConnection();
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
				}
				sql.setLength(0);
				sql1.setLength(0);
			}
			conn.commit();
			
			if (string.equals("1")) {
				msg = "省申请启用状态审核通过！";
			} else if (string.equals("0")) {
				msg = "省申请启用状态审核取消通过！";
			} else {
				msg = "省申请启用状态审核不通过！";
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.free(null, ps, conn);
		}
		return msg;
	}

	@Override
	public List<QyztBean> queryCheck(String id) {
		
		List<QyztBean> list = new ArrayList<QyztBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT FLGG,BFTG,BFBTG,NEWQYZT,ID,ZDID,OLDQYZT FROM QSKZ WHERE ID IN (" + id+")");
		
		System.out.println("状态："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				QyztBean bean = new QyztBean();
				String flgg = rs.getString("FLGG")==null?"":rs.getString("FLGG");;//省级审核更改字段
				String bftg = rs.getString("BFTG")==null?"":rs.getString("BFTG");//省级部分通过字段
				String bfbtg = rs.getString("BFBTG")==null?"":rs.getString("BFBTG");//省级部分不通过字段
				String newqyzt = rs.getString("NEWQYZT")==null?"":rs.getString("NEWQYZT");
				String zdid = rs.getString("ZDID")==null?"":rs.getString("ZDID");
				String id1 = rs.getString("ID")==null?"":rs.getString("ID");
				String oldqyzt = rs.getString("OLDQYZT")==null?"":rs.getString("OLDQYZT");
				
				bean.setFlgg(flgg);
				bean.setBftg(bftg);
				bean.setBfbtg(bfbtg);
				bean.setNewqyzt(newqyzt);
				bean.setZdid(zdid);
				bean.setId(id1);
				bean.setOldqyzt(oldqyzt);
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
}
