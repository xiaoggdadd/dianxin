package com.ptac.app.provinceapply.gdfs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.noki.zdqxkz.util.ZdqxkzUtil;
import com.ptac.app.provinceapply.gdfs.bean.GdfBean;

/**
 * @author lijing
 * @see 省申请供电方式审核实现层
 */
public class GdfDaoImp implements GdfDao {

	@Override
	public List<GdfBean> query(String string,String loginId,String shengzt) {
		
		List<GdfBean> list = new ArrayList<GdfBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT RNDIQU(Z.SHI) SHI,RNDIQU(Z.XIAN) XIAN,Z.JZNAME JZNAME,rtname(Q.OLDGDFS) OLDGDFS,rtname(Q.NEWGDFS) NEWGDFS,RNDIQU(Z.XIAOQU) XIAOQU,"
				 + " Z.ID ZDID,Q.ID ID,(CASE WHEN DBMS_LOB.GETLENGTH(FJNR) IS NOT NULL OR DBMS_LOB.GETLENGTH(FJNR)>0 THEN '1' ELSE '0' END) FJNR"
				 + " FROM QSKZ Q,ZHANDIAN Z WHERE Q.ZDID = Z.ID AND Q.OLDGDFS <> Q.NEWGDFS AND Q.FLGG IS NOT NULL AND Q.QXPDBZ='2' AND Q.SHISHBZ='1' "+ string
				 + " AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+ loginId + "'))");
		
		System.out.println("省申请供电方式审核信息查询："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				GdfBean bean = new GdfBean();
				String city = rs.getString("SHI")==null?"":rs.getString("SHI");//市
				String xian = rs.getString("XIAN")==null?"":rs.getString("XIAN");//区县
				String zdname = rs.getString("JZNAME")==null?"":rs.getString("JZNAME");//站点名称
				String oldg = rs.getString("OLDGDFS")==null?"":rs.getString("OLDGDFS");//申请前
				String newg = rs.getString("NEWGDFS")==null?"":rs.getString("NEWGDFS");//申请后
				String xiaoqu = rs.getString("XIAOQU")==null?"":rs.getString("XIAOQU");//乡镇
				String zdid = rs.getString("ZDID")==null?"":rs.getString("ZDID");//站点ID
				String id = rs.getString("ID")==null?"":rs.getString("ID");
				Boolean fjnr = rs.getString("FJNR")==null?Boolean.FALSE:("1".equals(rs.getString("FJNR"))?Boolean.TRUE:Boolean.FALSE);
				
				bean.setCity(city);
				bean.setXian(xian);
				bean.setZdname(zdname);
				bean.setOldg(oldg);
				bean.setNewg(newg);
				bean.setXiaoqu(xiaoqu);
				bean.setZdid(zdid);
				bean.setState(shengzt);
				bean.setId(id);
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
	public List<GdfBean> queryCheck(String id) {
		
		List<GdfBean> list = new ArrayList<GdfBean>();
		DataBase db = new DataBase();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT FLGG,BFTG,BFBTG,NEWGDFS,ID,ZDID,OLDGDFS FROM QSKZ WHERE ID IN (" + id+")");
		
		System.out.println("状态："+sql);
		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while(rs.next()){
				GdfBean bean = new GdfBean();
				String flgg = rs.getString("FLGG")==null?"":rs.getString("FLGG");;//省级审核更改字段
				String bftg = rs.getString("BFTG")==null?"":rs.getString("BFTG");//省级部分通过字段
				String bfbtg = rs.getString("BFBTG")==null?"":rs.getString("BFBTG");//省级部分不通过字段
				String newg = rs.getString("NEWGDFS")==null?"":rs.getString("NEWGDFS");
				String zdid = rs.getString("ZDID")==null?"":rs.getString("ZDID");
				String id1 = rs.getString("ID")==null?"":rs.getString("ID");
				String oldg = rs.getString("OLDGDFS")==null?"":rs.getString("OLDGDFS");
				
				bean.setFlgg(flgg);
				bean.setBftg(bftg);
				bean.setBfbtg(bfbtg);
				bean.setNewg(newg);
				bean.setZdid(zdid);
				bean.setId(id1);
				bean.setOldg(oldg);
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
	public String check(List<GdfBean> list, String personnal, String cause,
			String bz) {
		String msg = "供电方式审核信息失败！";
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
				GdfBean bean =list.get(i);
				String flgg = bean.getFlgg();
				String bftg = bean.getBftg();
				String bfbtg = bean.getBfbtg();
				String newg = bean.getNewg();
				String oldg = bean.getOldg();
				String zid = bean.getZdid();
				String qid = bean.getId();
				ZdqxkzUtil un = new ZdqxkzUtil();
				
				if(bz.equals("0")){
					bftg = un.getFlbz(bftg, "5");
					bfbtg = un.getFlbz(bfbtg, "5");
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
					
					sql1.append(" UPDATE ZHANDIAN SET GDFS='"+oldg+"'" + " WHERE ID = (" + zid + ")");
					
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
					ps1 = conn.prepareStatement(sql1.toString());
					ps1.executeUpdate();
					
				}else if(bz.equals("1")){
					bftg = un.getBftg(bftg, "5");
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
					
					sql1.append(" UPDATE ZHANDIAN SET GDFS='"+newg+"'" + " WHERE ID = (" + zid + ")");
					
					conn = db.getConnection();
					ps = conn.prepareStatement(sql.toString());
					ps.executeUpdate();
					ps1 = conn.prepareStatement(sql1.toString());
					ps1.executeUpdate();
				}else if(bz.equals("2")){
					bfbtg = un.getBftg(bfbtg, "5");
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
			
			if (bz.equals("1")) {
				msg = "省申请供电方式审核通过！";
			} else if (bz.equals("0")) {
				msg = "省申请供电方式审核取消通过！";
			} else {
				msg = "省申请供电方式审核不通过！";
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
}
