package com.noki.jizhan.daoru;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.noki.chaobiaorenyuan.bean.ZhanDian;
import com.noki.common.oss.DateUtils;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.model.YspzClass;
import com.noki.page.NPageBean;

public class YspzDao {
	//鎵ц鏂版坊鏂规硶
	public int addYC(YspzClass yc) {
		int rs = 0;
		//灏嗗瓧绗︿覆杞崲涓篋ATE绫诲瀷
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//灏嗘湀浠借浆鎹负DATE
		Date Inserttime = null;
		Date Updatetime = null;
		try {
			Inserttime = sdf1.parse(yc.getInserttime()); //褰曞叆鏃堕棿
			Updatetime = sdf1.parse(yc.getUpdatetime()); //淇敼鏃堕棿
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sql = "INSERT INTO YSPZ (SHI,xian,xiaoqu,MONTH,MONEY,INSERTTIME,UPDATETIME,ENTRYPERSON)" +
					 "VALUES ("+yc.getShi()+","+yc.getXian()+","+yc.getXiaoqu()+",'"+yc.getMonth()+"',"+yc.getMoney()+",to_date('"+DateUtils.toStringLong(Inserttime)+"', 'yyyy-MM-dd hh24:mi:ss'),to_date('"+DateUtils.toStringLong(Updatetime)+"', 'yyyy-MM-dd hh24:mi:ss'),'"+yc.getEntryperson()+"')";		
		DataBase db = new DataBase();
		try {
			System.out.println("新添"+sql);
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;
	}
	//删除方法
	public int delectYC(String id) {
		// TODO Auto-generated method stub
		int rs = 0;
		
		String sql = "UPDATE YSPZ SET STATE = 1 WHERE YID = "+id+"";
		
		DataBase db = new DataBase();
		try {
			System.out.println("删除"+sql);
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;	
	}
	//根据ID查询
	public ArrayList<YspzClass> SelectYspz(String id) {
		ArrayList<YspzClass> al = null;
		String sql = "SELECT * FROM YSPZ WHERE YID = "+id+"";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<YspzClass>();
			while(rs.next()){
				YspzClass cbr = new YspzClass();
				cbr.setShi(rs.getString("SHI"));	//市
				cbr.setXian(rs.getString("XIAN"));	//县
				cbr.setXiaoqu(rs.getString("XIAOQU"));//县
				cbr.setMonth(rs.getString("MONTH"));//月份
				cbr.setMoney(rs.getString("MONEY"));//金额
				al.add(cbr);
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
		return al;
	}
	//修改方法
	public int updateYC(YspzClass yc,String id) {
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date Updatetime = null;
		try {
			Updatetime = sdf1.parse(yc.getUpdatetime()); //淇敼鏃堕棿
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rs = 0;
		
		String sql = "UPDATE YSPZ SET SHI = '"+yc.getShi()+"',XIAN = '"+yc.getXian()+"',XIAOQU = '"+yc.getXiaoqu()+"',MONTH = '"+yc.getMonth()+"',MONEY = '"+yc.getMoney()+"',UPDATETIME = to_date('"+DateUtils.toStringLong(Updatetime)+"', 'yyyy-MM-dd hh24:mi:ss') WHERE YID = "+id+"";
		
		DataBase db = new DataBase();
		try {
			System.out.println("修改"+sql);
			db.connectDb();
			rs = db.update(sql);
			if(rs>0){
				rs = 1;
			}else{
				rs = 0;
			}
		} catch (Exception de) {
			de.printStackTrace();
		} finally {
			
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return rs;	
	}
}
