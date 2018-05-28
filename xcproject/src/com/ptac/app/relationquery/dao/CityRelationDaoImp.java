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
 * @see 外租站点与主站点关联省级查询实现层
 */
public class CityRelationDaoImp implements CityRelationDao {

	@Override
	public List<RelationQueryBean> getCityRelation(String whereStr,String loginId) {

		List<RelationQueryBean> list = new ArrayList<RelationQueryBean>();
		StringBuffer sql = new StringBuffer();;
		DataBase db = new DataBase();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		sql.append(" SELECT rndiqu(shi) shi, COUNT(id) a, count(decode(xmh, '', id)), count(xmh)" 
				 +" FROM ZHANDIAN WHERE jzname like '%回收%'" + whereStr
				 +" AND (XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+loginId+"')) group by shi");
		
		System.out.println("外租站点与主站点关联省级查询："+sql);		
		try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql.toString());
			
			while(rs.next()){
				RelationQueryBean bean = new RelationQueryBean();
				
				String city = rs.getString(1) != null ? rs.getString(1) : "";//城市
				String wzhs = rs.getString(2) != null ? rs.getString(2) : "0";//外租回收站点数量
				String wgl = rs.getString(3) != null ? rs.getString(3) : "0";//未关联站点个数
				String ygl = rs.getString(4) != null ? rs.getString(4) : "0";//已关联主站点个数
			
				bean.setCity(city);
				bean.setWzhs(wzhs);
				bean.setWgl(wgl);
				bean.setYgl(ygl);
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
