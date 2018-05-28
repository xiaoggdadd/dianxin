package com.noki.chaobiaorenyuan.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.chaobiaorenyuan.bean.WenJian;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class uploadDao {
	//获取文档保存路径
	public String filebaocun(){
		String lujing = "";
		String sql = "SELECT FILEBAOCUN FROM TBL_APP_IMAGEPATH";
		System.out.println("获取保存文件的路径sql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				lujing = rs.getString("FILEBAOCUN");
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
		return lujing;
	}
	//执行新添文件方法（将文件路径等内容填入数据库）
	public int addWJ(WenJian wj) {
		int rs = 0;
		String sql = "INSERT INTO TBL_DIANBIAO_WENJIAN (SCR_ID,SCR_NAME,SC_TIME,LUJING,YUAN_NAME,XIAN_NAME,LIUCHENG_ID,LIUCHENG_NAME,DIANBIAO_ID,DIANBIAO_NAME)" +
					 "VALUES ('"+wj.getSCR_ID()+"','"+wj.getSCR_NAME()+"','"+wj.getSC_TIME()+"','"+wj.getLUJING()+"','"+wj.getYUAN_NAME()+"','"+wj.getXIAN_NAME()+"','"+wj.getLIUCHENG_ID()+"','"+wj.getLIUCHENG_NAME()+"','"+wj.getDIANBIAO_ID()+"','"+wj.getDIANBIAO_NAME()+"')";		
		DataBase db = new DataBase();
		System.out.println("文件数据库添加sql："+sql);
		try {
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
	//根据电表ID遍历电表文件
	public ArrayList<WenJian> Selectwj(String dianbiaoId,String dianbiaoName,String scrname,String wenjianming,String liuchengId) {
		
		ArrayList<WenJian> al = null;
		String sql = "SELECT ID,SCR_ID,SCR_NAME,SC_TIME,LUJING,YUAN_NAME,XIAN_NAME,LIUCHENG_ID,LIUCHENG_NAME,DIANBIAO_ID,DIANBIAO_NAME" +
				" FROM TBL_DIANBIAO_WENJIAN WHERE 1=1";	
		if(dianbiaoId != null && !dianbiaoId.equals("") && !dianbiaoId.equals("0")){ 	//电表ID
			sql = sql+" AND DIANBIAO_ID = "+dianbiaoId+"";	//不能模糊
		}
		if(dianbiaoName != null && !dianbiaoName.equals("") && !dianbiaoName.equals("0")){ //电表名称
			sql = sql+" AND DIANBIAO_NAME = '"+dianbiaoName+"'";//不能模糊
		}
//		if(liuchengId != null && !liuchengId.equals("") && !liuchengId.equals("0")){ 	//流程ID
//			sql = sql+" AND LIUCHENG_ID = "+liuchengId+"";	//不能模糊
//		}
		if(scrname != null && !scrname.equals("") && !scrname.equals("0")){				//上传人名称
			sql = sql+" AND SCR_NAME like '%"+scrname+"%'";
		}
		if(wenjianming != null && !wenjianming.equals("") && !wenjianming.equals("0")){	//文件名称
			sql = sql+" AND YUAN_NAME like '%"+wenjianming+"%'"; 
		}
		if(liuchengId != null && !liuchengId.equals("") && !liuchengId.equals("0")){	//流程名称
			sql = sql+" AND LIUCHENG_ID  =  '"+liuchengId+"'";	//流程名称改为唯一标示流程的ID
		}
		DataBase db = new DataBase();
		System.out.println("文件数据库查询sql："+sql);
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			al = new ArrayList<WenJian>();
			while(rs.next()){
				WenJian wj = new WenJian();
				wj.setID(rs.getString("ID"));
				wj.setSCR_ID(rs.getString("SCR_ID"));
				wj.setSCR_NAME(rs.getString("SCR_NAME"));
				wj.setSC_TIME(rs.getString("SC_TIME"));
				wj.setLUJING(rs.getString("LUJING"));
				wj.setYUAN_NAME(rs.getString("YUAN_NAME"));
				wj.setXIAN_NAME(rs.getString("XIAN_NAME"));
				wj.setLIUCHENG_ID(rs.getString("LIUCHENG_ID"));
				wj.setLIUCHENG_NAME(rs.getString("LIUCHENG_NAME"));
				wj.setDIANBIAO_ID(rs.getString("DIANBIAO_ID"));
				wj.setDIANBIAO_NAME(rs.getString("DIANBIAO_NAME"));
				al.add(wj);
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
	//执行删除方法
	public int delect(String wjid) {
		int rs = 0;
		String sql = "DELETE FROM TBL_DIANBIAO_WENJIAN  WHERE ID = "+wjid+"";
		DataBase db = new DataBase();
		System.out.println("文件数据库删除sql："+sql);
		try {
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
	//根据流程ID获取流程名称
	public String fileliucheng(String FLOW_ID){
		String liuchengName = "";
		String sql = "SELECT FLOW_NAME FROM S_FLOW_INFO WHERE FLOW_ID = "+FLOW_ID+"";
		System.out.println("根据流程ID获取流程名称sql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				liuchengName = rs.getString("FLOW_NAME");
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
		return liuchengName;
	}
	//根据用户ID获取用户姓名
	public String account_name(String ID){
		String accountName = "";
		String sql = "SELECT NAME FROM ACCOUNT WHERE ACCOUNTID = '"+ID+"'";
		System.out.println("根据用户ID获取用户名称sql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				accountName = rs.getString("NAME");
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
		return accountName;
	}
	//根据上传人，电表ID，上传时间，选择的对应流程类型查询是否有上传的文件
	public boolean Whether(String SCRID,String DianBiaoID,String SCTIME,String LiuChengID){
		boolean result = false;
		String sql = "SELECT  COUNT(0) FROM TBL_DIANBIAO_WENJIAN WHERE 1 = 1 AND SC_TIME LIKE  '"+SCTIME+"%' AND SCR_ID = '"+SCRID+"' AND DIANBIAO_ID = '"+DianBiaoID+"' AND LIUCHENG_ID = '"+LiuChengID+"'";
		System.out.println("验证是否上传文件sql："+sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			while(rs.next()){
				int i = rs.getInt(1);
				System.out.println("查询是否上传对应文件返回的数值："+i);
				if(i>0){
					result = true;
					System.out.println("返回数值大于零");
				}
				System.out.println("返回数值等于零");
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
		return result;
	}
}
