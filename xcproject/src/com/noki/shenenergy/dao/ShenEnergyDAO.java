package com.noki.shenenergy.dao;

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
		String sql = "select * from SHENCONSUMEPTIONENERGY where 1=1";
		if (city != null) {
			sql = sql + " and CON_SHEN='" + city+"'";
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
				energys.setCon_id(rs.getInt("CON_ID"));
				energys.setCon_diese(rs.getString("CON_DIESE"));
				energys.setCon_gaoline(rs.getString("CON_GASOLINE"));
				energys.setCon_city(rs.getString("CON_SHEN"));
				energys.setCon_naturalgas(rs.getString("CON_NATURALGAS"));
				energys.setCon_power(rs.getString("CON_POWER"));
				energys.setCon_water(rs.getString("CON_WATER"));
				energys.setCon_yearmonth(rs.getString("CON_YEARMONTH"));
				energys.setAgcode(rs.getString("AGCODE"));
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
		String sql = "select CON_SHEN from SHENCONSUMEPTIONENERGY group by CON_SHEN";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			list = new ArrayList();
			while(rs.next()){
				ShenDisplayBean disbean = new ShenDisplayBean();
				disbean.setArear(rs.getString("CON_SHEN"));
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
		String sql = "select distinct substr(t.con_yearmonth,1,4) from SHENCONSUMEPTIONENERGY t ";
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
