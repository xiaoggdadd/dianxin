package com.ptac.app.relationquery.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.noki.database.DataBase;
import com.ptac.app.relationquery.bean.RelationQueryBean;

/**
 * @author lijing
 * @see 外租站点与主站点关联市级查询实现层
 */
public class TownRelationDaoImp implements TownRelationDao {

	@Override
	public List<RelationQueryBean> getTownRelation(String whereStr,
			String loginId) {

		List<RelationQueryBean> list = new ArrayList<RelationQueryBean>();
		StringBuffer sql = new StringBuffer();;
		DataBase db = new DataBase();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		sql.append(" SELECT rndiqu(xian), COUNT(id), count(decode(xmh, '', id)), count(xmh), xian" 
				 +" FROM ZHANDIAN WHERE jzname like '%回收%'" + whereStr
				 +" AND (XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by xian");
		
		System.out.println("外租站点与主站点关联省级查询："+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String xian = rs.getString(1) != null ? rs.getString(1) : "";//区县
				String wzhs = rs.getString(2) != null ? rs.getString(2) : "0";//外租回收站点数量
				String wgl = rs.getString(3) != null ? rs.getString(3) : "0";//未关联站点个数
				String ygl = rs.getString(4) != null ? rs.getString(4) : "0";//已关联主站点个数
				String town = rs.getString(5) != null ? rs.getString(5) : "";//区县编码
			
				bean.setXian(xian);
				bean.setWzhs(wzhs);
				bean.setWgl(wgl);
				bean.setYgl(ygl);
				bean.setTown(town);
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

	@Override
	public List<RelationQueryBean> getXiangQing1(String xian) {

		List<RelationQueryBean> list = new ArrayList<RelationQueryBean>();
		StringBuffer sql = new StringBuffer();;
		DataBase db = new DataBase();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		sql.append(" SELECT rndiqu(z.shi) shi,z.jzname,z.id,rtname(z.stationtype),z.xmh," 
				 +" (select zd.jzname from zhandian zd where zd.id = z.xmh) a,"
				 +" (select zd.id from zhandian zd where zd.id = z.xmh) b,"
				 +" (select rtname(zd.stationtype) from zhandian zd where zd.id = z.xmh) c"
				 +" FROM ZHANDIAN z WHERE z.jzname like '%回收%' and z.xmh is not null" 
				 +" and z.xian = '"+xian+"'");
		
		System.out.println("外租站点与主站点关联市级详细查询："+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//站点名称
				String id = rs.getString(3) != null ? rs.getString(3) : "";//站点ID
				String zdlx = rs.getString(4) != null ? rs.getString(4) : "";//站点类型
				String xmh = rs.getString(5) != null ? rs.getString(5) : "";//关联ID号
				String jzname = rs.getString(6) != null ? rs.getString(6) : "";//主站点名称
				String zdid = rs.getString(7) != null ? rs.getString(7) : "";//主站点ID
				String stationtype = rs.getString(8) != null ? rs.getString(8) : "";//主站点类型
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setId(id);
				bean.setZdlx(zdlx);
				bean.setXmh(xmh);
				bean.setJzname(jzname);
				bean.setZdid(zdid);
				bean.setStationtype(stationtype);
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}
	
	@Override
	public List<RelationQueryBean> getXiangQing2(String xian) {

		List<RelationQueryBean> list = new ArrayList<RelationQueryBean>();
		StringBuffer sql = new StringBuffer();;
		DataBase db = new DataBase();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		sql.append(" SELECT rndiqu(z.shi) shi,z.jzname,z.id,rtname(z.stationtype),z.xmh," 
				 +" (select zd.jzname from zhandian zd where zd.id = z.xmh) a,"
				 +" (select zd.id from zhandian zd where zd.id = z.xmh) b,"
				 +" (select rtname(zd.stationtype) from zhandian zd where zd.id = z.xmh) c"
				 +" FROM ZHANDIAN z WHERE z.jzname like '%回收%' and z.xmh is null" 
				 +" and z.xian = '"+xian+"'");
		
		System.out.println("外租站点与主站点关联市级详细查询："+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//站点名称
				String id = rs.getString(3) != null ? rs.getString(3) : "";//站点ID
				String zdlx = rs.getString(4) != null ? rs.getString(4) : "";//站点类型
				String xmh = rs.getString(5) != null ? rs.getString(5) : "";//关联ID号
				String jzname = rs.getString(6) != null ? rs.getString(6) : "";//主站点名称
				String zdid = rs.getString(7) != null ? rs.getString(7) : "";//主站点ID
				String stationtype = rs.getString(8) != null ? rs.getString(8) : "";//主站点类型
				
				bean.setCity(city);
				bean.setZdname(zdname);
				bean.setId(id);
				bean.setZdlx(zdlx);
				bean.setXmh(xmh);
				bean.setJzname(jzname);
				bean.setZdid(zdid);
				bean.setStationtype(stationtype);
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, ps, conn);
		}
		return list;
	}

}
