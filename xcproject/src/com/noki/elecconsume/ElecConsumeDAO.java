package com.noki.elecconsume;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.shenenergy.javabean.ShenDisplayBean;
import com.noki.shenenergy.javabean.ShenEnergy;
import com.noki.util.Query;

/**
 * 电消耗dao类
 * @author xuwangyu
 *
 */
public class ElecConsumeDAO {

	/**
	 * 查询耗能数据
	 * @param city（查询的城市）
	 * @param year(查询的年份)
	 * @return list(返回查询数据集合)
	 * 
	 */
	public List toGetDate(String city,String year) {
		List list = null;
		String sql = "select * from ELECCONSUME where 1=1";
		if (city != null) {
			sql = sql + " and AGCODE='" + city+"'";
		}
		if(year != null){
			sql = sql + " and YEAR in(" + year+")";
		}
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				Elecconsume elecconsume = new Elecconsume();
				elecconsume.setId(rs.getInt("ID"));
				elecconsume.setProvince(rs.getString("PROVINCE"));
				elecconsume.setProducthouse(rs.getString("PRODUCTHOUSE"));
				elecconsume.setCommadroit(rs.getString("COMMADROIT"));
				elecconsume.setBasestage(rs.getString("BASESTAGE"));
				elecconsume.setDatacenter(rs.getString("DATACENTER"));
				elecconsume.setConnlimit(rs.getString("CONNLIMIT"));
				elecconsume.setOutroom(rs.getString("OUTROOM"));
				elecconsume.setNotproducthouse(rs.getString("NOTPRODUCTHOUSE"));
				elecconsume.setManagerhouse(rs.getString("MANAGERHOUSE"));
				elecconsume.setChannelhouse(rs.getString("CHANNELHOUSE"));
				elecconsume.setOther(rs.getString("OTHER"));
				elecconsume.setAgcode(rs.getString("AGCODE"));
				elecconsume.setYear(rs.getString("YEAR"));
				list.add(elecconsume);
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
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
		return list;
	}
	
	/**
	 * 初始化下拉列表数据
	 * @return list(城市列表)
	 */
	public List toGetProvince(){
		List list = null;
		String sql = "select PROVINCE,AGCODE from ELECCONSUME group by PROVINCE,AGCODE";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				Elecconsume disbean = new Elecconsume();
				disbean.setProvince(rs.getString("PROVINCE"));
				disbean.setAgcode(rs.getString("AGCODE"));
				list.add(disbean);
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
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
		return list;
	}
	
	
	//初始化年
	public List getYear(){
		List list = null;
		String sql = "select distinct t.YEAR from ELECCONSUME t ";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				String yearmonth =rs.getString(1);
				list.add(yearmonth);
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
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
		return list;
	}  

}
