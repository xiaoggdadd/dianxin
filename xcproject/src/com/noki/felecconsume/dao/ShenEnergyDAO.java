package com.noki.felecconsume.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.felecconsume.bean.ShenDisplayBean;
import com.noki.felecconsume.bean.ShenEnergy;
import com.noki.page.NPageBean;
import com.noki.util.Query;

/**
 * 能源消耗dao类
 * @author xuwangyu
 *
 */
public class ShenEnergyDAO {

	/**
	 * 查询城市耗能数据
	 * @param city（查询的城市）
	 * @return list(返回查询数据集合)
	 * 
	 */
	public List toGetDate(String city) {
		List list = null;
		String sql = "select * from FELECCONSUME where 1=1";
		if (city != null) {
			sql = sql + " and AGCODE='" + city+"'";
		}
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				ShenEnergy energys = new ShenEnergy();
				energys.setId(rs.getInt("ID"));
				energys.setProvince(rs.getString("PROVINCE"));
				energys.setProducthouse(rs.getString("PRODUCTHOUSE"));
				energys.setCommadroit(rs.getString("COMMADROIT"));
				energys.setBasestage(rs.getString("BASESTAGE"));
				energys.setDatacenter(rs.getString("DATACENTER"));
				energys.setConnlimit(rs.getString("CONNLIMIT"));
				energys.setOutroom(rs.getString("OUTROOM"));
				energys.setNotproducthouse(rs.getString("NOTPRODUCTHOUSE"));
				energys.setManagerhouse(rs.getString("MANAGERHOUSE"));
				energys.setChannelhouse(rs.getString("CHANNELHOUSE"));
				energys.setOther(rs.getString("OTHER"));
				energys.setAgcode(rs.getString("AGCODE"));
				energys.setCon_yearmonth(rs.getString("YEAR"));
				list.add(energys);
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
	public List toGetCity(){
		List list = null;
		String sql = "select PROVINCE,AGCODE from FELECCONSUME group by PROVINCE,AGCODE";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				ShenDisplayBean disbean = new ShenDisplayBean();
				disbean.setArear(rs.getString("PROVINCE"));
				disbean.setCode(rs.getString("AGCODE"));
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
		String sql = "select distinct substr(t.year,1,4) from FELECCONSUME t ";
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
