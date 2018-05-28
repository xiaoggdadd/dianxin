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
 * @see ����վ������վ������м���ѯʵ�ֲ�
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
				 +" FROM ZHANDIAN WHERE jzname like '%����%'" + whereStr
				 +" AND (XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by xian");
		
		System.out.println("����վ������վ�����ʡ����ѯ��"+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String xian = rs.getString(1) != null ? rs.getString(1) : "";//����
				String wzhs = rs.getString(2) != null ? rs.getString(2) : "0";//�������վ������
				String wgl = rs.getString(3) != null ? rs.getString(3) : "0";//δ����վ�����
				String ygl = rs.getString(4) != null ? rs.getString(4) : "0";//�ѹ�����վ�����
				String town = rs.getString(5) != null ? rs.getString(5) : "";//���ر���
			
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
				 +" FROM ZHANDIAN z WHERE z.jzname like '%����%' and z.xmh is not null" 
				 +" and z.xian = '"+xian+"'");
		
		System.out.println("����վ������վ������м���ϸ��ѯ��"+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//����
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//վ������
				String id = rs.getString(3) != null ? rs.getString(3) : "";//վ��ID
				String zdlx = rs.getString(4) != null ? rs.getString(4) : "";//վ������
				String xmh = rs.getString(5) != null ? rs.getString(5) : "";//����ID��
				String jzname = rs.getString(6) != null ? rs.getString(6) : "";//��վ������
				String zdid = rs.getString(7) != null ? rs.getString(7) : "";//��վ��ID
				String stationtype = rs.getString(8) != null ? rs.getString(8) : "";//��վ������
				
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
				 +" FROM ZHANDIAN z WHERE z.jzname like '%����%' and z.xmh is null" 
				 +" and z.xian = '"+xian+"'");
		
		System.out.println("����վ������վ������м���ϸ��ѯ��"+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//����
				String zdname = rs.getString(2) != null ? rs.getString(2) : "";//վ������
				String id = rs.getString(3) != null ? rs.getString(3) : "";//վ��ID
				String zdlx = rs.getString(4) != null ? rs.getString(4) : "";//վ������
				String xmh = rs.getString(5) != null ? rs.getString(5) : "";//����ID��
				String jzname = rs.getString(6) != null ? rs.getString(6) : "";//��վ������
				String zdid = rs.getString(7) != null ? rs.getString(7) : "";//��վ��ID
				String stationtype = rs.getString(8) != null ? rs.getString(8) : "";//��վ������
				
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
