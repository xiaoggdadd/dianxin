package com.noki.zhandian;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;


public class ZhanDianDao {
		public List<ZhandianBean> getZD(String whereStr){
			String sql = "select zd.id,zd.jzname,d.agname,zd.address,zd.fzr,i.name,z.name,y.name,db.beilv," +
					"dd.SKDWMC,a.name,to_char(min(am.lastdatetime),'yyyy-mm-dd'),to_char(max(am.thisdatetime),'yyyy-mm-dd')," +
					"count(el.electricfee_id),sum(el.actualpay),to_char(min(el.paydatetime),'yyyy-mm-dd'),to_char(max(el.paydatetime),'yyyy-mm-dd') from zhandian zd,dianbiao db," +
					"dianduview am,dianfeiview  el,d_area_grade d,indexs i,indexs z,indexs y," +
					"zddfinfo dd,indexs a where zd.id = db.zdid and db.dbid = am.ammeterid_fk " +
					"and am.ammeterdegreeid = el.ammeterdegreeid_fk and a.code = dd.fkfs and " +
					"d.agcode = zd.xian and i.code = zd.stationtype and z.code = zd.gdfs " +
					"and y.code = zd.gsf and zd.id = dd.zdid and zd.qyzt = '1' "+whereStr+" " +
					"group by zd.id,zd.jzname,d.agname,zd.address,zd.fzr,i.name,z.name,y.name,db.beilv,dd.SKDWMC,a.name";
			List list = null;
			DataBase db = new DataBase();
			ResultSet rs = null;
			ResultSet rs1 = null;
			try{
				db.connectDb();
				System.out.println("站点一键查询"+sql);
				rs = db.queryAll(sql);
				list = new ArrayList();
				while(rs.next()){
					ZhandianBean zb = new ZhandianBean();
					zb.setZhandianID(rs.getString(1));
					zb.setZdname(rs.getString(2));
					zb.setXian(rs.getString(3));
					zb.setAddress(rs.getString(4));
					zb.setFzr(rs.getString(5));
					zb.setZdtype(rs.getString(6));
					zb.setGdfs(rs.getString(7));
					zb.setZdgs(rs.getString(8));
					zb.setDbbl(rs.getString(9));
					zb.setSkdw(rs.getString(10));
					zb.setJffs(rs.getString(11));
					zb.setBegin(rs.getString(12));
					zb.setEnd(rs.getString(13));
					zb.setJfcs(rs.getString(14));
					zb.setDfz(rs.getString(15));
					//zb.setDfz(rs.getDouble(15));
					zb.setScjf(rs.getString(16));
					zb.setZhjf(rs.getString(17));
					list.add(zb);
					}
				return list;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
				try {
					db.close();
				} catch (DbException de) {
					de.printStackTrace();
				}
			}
			return null;	
		}
	public List<ZDFeiyongBean> getFYHZ(String zdid){
			String sql = "select zd.id,to_char(el.accountmonth,'yyyy-mm'),el.actualpay,am.blhdl,(am.thisdatetime - am.lastdatetime) as days, am.ammeterdegreeid" +
					" from zhandian zd,dianbiao db,dianduview am,dianfeiview el" +
					" where zd.id=db.zdid and db.dbid=am.ammeterid_fk and am.ammeterdegreeid=el.ammeterdegreeid_fk and zd.id='"+zdid+"' order by el.accountmonth desc";
			List list = null;
			DataBase db = new DataBase();
			ResultSet rs = null;
		try{
			db.connectDb();
			System.out.println("费用汇总"+sql);
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				ZDFeiyongBean zdf = new ZDFeiyongBean();
				zdf.setTime(rs.getString(2));
				zdf.setSjje(rs.getDouble(3));
				zdf.setSjds(rs.getDouble(4));
				zdf.setDays(rs.getInt(5));
				zdf.setAmid(rs.getString(6));
				list.add(zdf);
				}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;	
		}
	public List<FeiyongBean> getFYXX(String whereStr){
		String sql = "select to_char(el.paydatetime,'yyyy-mm-dd'),to_char(am.lastdatetime,'yyyy-mm-dd'),to_char(am.thisdatetime,'yyyy-mm-dd'),am.lastdegree,am.thisdegree,am.blhdl,el.actualpay " +
				"from zhandian zd,dianbiao db,dianduview am,dianfeiview el " +
				"where zd.id=db.zdid and db.dbid=am.ammeterid_fk and am.ammeterdegreeid=el.ammeterdegreeid_fk "+whereStr;
		List list = null;
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			System.out.println("费用详细"+sql);
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				FeiyongBean fy = new FeiyongBean();
				fy.setTime(rs.getString(1));
				fy.setBegin(rs.getString(2));
				fy.setEnd(rs.getString(3));
				fy.setBegins(rs.getDouble(4));
				fy.setEnds(rs.getDouble(5));
				fy.setDians(rs.getDouble(6));
				fy.setJinez(rs.getDouble(7));		
				list.add(fy);
				}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return null;	
	}
}
