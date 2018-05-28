package com.noki.dataedit.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.HashedMap;


import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.dataedit.bean.updateBean;
import com.noki.energy.javabean.Energy;
import com.noki.page.NPageBean;

public class updateDao {
	/**
	 * 查询城市耗能数据
	 * @param hashMap（更新站点数据）
	 * @return String （更新失败的数据，完全成功为SUCCESS）
	 * @author Administrator
	 */
	public String toGetDate(HashMap<String,updateBean> mess) {
		String list = "";
		String sql = "UPDATE ZHANDIAN ZD SET ZD.QYZT=? ,ZD.YFLX=?,ZD.GDFS=?, ZD.ZDCQ=?,ZD.JLFH=?,ZD.ZLFH=?,ZD.KDSBSL=?,ZD.YYSBSL=?,ZD.KT1=?,ZD.KT2=?,ZD.GXXX=?," +
				"ZD.RRU=?,ZD.YDGXSBSL=?,ZD.DXGXSBSL=?,ZD.PROPERTY=?,ZD.STATIONTYPE=?, ZD.QSDBDL=?,ZD.EDHDL=?  WHERE ZD.ID=?";
		
		DataBase db = new DataBase();
		
		Iterator it = mess.entrySet().iterator();
		int temp = -9;
		int sucount = 0;
		int ercount = 0;
		PreparedStatement pre =null;
		StringBuffer remess = new StringBuffer();
		try{
			db.connectDb();
			pre = (PreparedStatement) db.conn.prepareCall(sql);
		}catch(Exception ed){
			ed.printStackTrace();
		}
		
		while(it.hasNext()){
			Map.Entry<String,updateBean> et1 = (Entry) it.next();
			String key = et1.getKey();
			updateBean bean = et1.getValue(); 
			try{
				//pre.setString(0, "a");
           //启用状态有问题
			pre.setString(1, bean.getQyzt());
			pre.setString(2, bean.getYflx());
			pre.setString(3, bean.getGdfs());
			pre.setString(4, bean.getZdcq());
			pre.setDouble(5, bean.getJlfh());
			pre.setDouble(6, bean.getZlfh());
			pre.setDouble(7, bean.getKdsbsl());
			pre.setDouble(8, bean.getYysbsl());
			pre.setString(9, bean.getKtps1());
			pre.setString(10, bean.getKtps2());
			pre.setString(11, bean.getGxxx());
			pre.setDouble(12, bean.getRru());
			pre.setDouble(13, bean.getYdgxsbsl());
			pre.setDouble(14, bean.getDxgxsbsl());
			pre.setString(15, bean.getZdsx());
			pre.setString(16, bean.getZdlx());
			pre.setDouble(17, bean.getQsdbdl());
			pre.setDouble(18, bean.getEdhdl());
			pre.setString(19, bean.getZdid());
			//pre.setString(18, bean.getZdid());
			
			db.setAutoCommit(false);
			temp = pre.executeUpdate();
			if(temp>0){
				sucount++;
				System.out.println("更改=====");
			}else{
				remess.append(bean.getZdid()+"の");
				ercount++;
			}
			db.commit();
			db.setAutoCommit(true);
			}catch(Exception e){
				e.printStackTrace();
				remess.append(bean.getZdid()+"の");
				ercount++;
			}
			
		}
		
			if ( pre!= null) {
				try {
					pre.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		remess.append("い"+sucount+"い"+ercount);
		return remess.toString();
	}
	//局用机房保存数据
	public String toGetDateJyjf(HashMap<String,updateBean> mess) {
		String list = "";
		String sql = "UPDATE ZHANDIAN ZD SET ZD.QYZT=? ,ZD.YFLX=?,ZD.GDFS=?,ZD.JLFH=?,ZD.ZLFH=?," +
				" ZD.PROPERTY=?,ZD.STATIONTYPE=?,ZD.EDHDL=?  WHERE ZD.ID=?";
		
		DataBase db = new DataBase();
		
		Iterator it = mess.entrySet().iterator();
		int temp = -9;
		int sucount = 0;
		int ercount = 0;
		PreparedStatement pre =null;
		StringBuffer remess = new StringBuffer();
		try{
			db.connectDb();
			pre = (PreparedStatement) db.conn.prepareCall(sql);
		}catch(Exception ed){
			ed.printStackTrace();
		}
		
		while(it.hasNext()){
			Map.Entry<String,updateBean> et1 = (Entry) it.next();
			String key = et1.getKey();
			updateBean bean = et1.getValue();
			try{
				//pre.setString(0, "a");
           //启用状态有问题
				System.out.println("房屋类型："+bean.getYflx());
			pre.setString(1, bean.getQyzt());
			pre.setString(2, bean.getYflx());
			pre.setString(3, bean.getGdfs());
			pre.setDouble(4, bean.getJlfh());
			pre.setDouble(5, bean.getZlfh());
			pre.setString(6, bean.getZdsx());
			pre.setString(7, bean.getZdlx());
			pre.setDouble(8, bean.getEdhdl());
			pre.setString(9, bean.getZdid());
			//pre.setString(18, bean.getZdid());
			
			db.setAutoCommit(false);
			temp = pre.executeUpdate();
			if(temp>0){
				sucount++;
				System.out.println("更改=====");
			}else{
				remess.append(bean.getZdid()+"の");
				ercount++;
			}
			db.commit();
			db.setAutoCommit(true);
			}catch(Exception e){
				e.printStackTrace();
				remess.append(bean.getZdid()+"の");
				ercount++;
			}
			
		}
		
			if ( pre!= null) {
				try {
					pre.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		remess.append("い"+sucount+"い"+ercount);
		return remess.toString();
	}
}
