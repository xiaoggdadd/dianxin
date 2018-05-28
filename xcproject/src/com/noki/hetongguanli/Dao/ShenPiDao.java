package com.noki.hetongguanli.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.hetongguanli.javabean.ShenPiClass;
import com.noki.page.NPageBean;

public class ShenPiDao {
	/**
	 * 查询合同审核表各种方法
	 * @param city（查询）
	 * @return list(返回查询数据集合)
	 *  
	 */
	//计算TBL_HETONG_SHENPI表全部内容共计多少页，
	//没有固定条件，附带模糊查询，用于审批页面分页
	public int SelectCount(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		int a = 0; 		//初始化共多少列
		int b = 5;		//初始化每页条数
		int c = 0;		//初始化共有几页
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			if(rs.next()){
				a = rs.getInt(1);
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
		//计算是否余列
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//返回共有几页
		return c; 
	}
	//计算TBL_HETONG_SHENPI表全部内容共计多少页，
	//固定条件为已审核，附带模糊查询，用于告警页面分页
	public int SelectCountZhuangTai(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		int a = 0; 		//初始化共多少列
		int b = 5;		//初始化每页条数
		int c = 0;		//初始化共有几页
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			if(rs.next()){
				a = rs.getInt(1);
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
		//计算是否余列
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//返回共有几页
		return c; 
	}
	
	//遍历TBL_HETONG_SHENPI表全部内容根据甲方，乙方，以及合同名称，合同状态进行分条件模糊查询，
	//没有固定条件（此方法用于审核）
	public ArrayList<ShenPiClass> SelectHeTong(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		System.out.println(curpage);
		//计算应跳转的内容
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			System.out.println(sql.toString());
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//遍历TBL_HETONG_SHENPI表全部内容根据甲方，乙方，以及合同名称，合同状态进行分条件模糊查询，
	//条件为已经审核（此方法用于告警）
	public ArrayList<ShenPiClass> SelectHeTongGaoJing(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		System.out.println(curpage);
		//计算应跳转的内容
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			System.out.println(sql.toString());
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	/**
	 * 下面方法在合同审核与告警页面暂未使用
	 * 勿删，合同维护管理页面使用下面方法
	 */
	//遍历TBL_HETONG_SHENPI表全部内容
	public ArrayList<ShenPiClass> Select() {
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//根据合同甲方进行模糊查询
	public ArrayList<ShenPiClass> SelectPartya(String partya) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where PARTYA like '%"+partya+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//根据合同乙方进行模糊查询
	public ArrayList<ShenPiClass> SelectPartyb(String partyb) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where PARTYB like '%"+partyb+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//根据合同名称进行模糊查询
	public ArrayList<ShenPiClass> SelectContractname(String contractname) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where CONTRACTNAME like '%"+contractname+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//根据关键字进行模糊查询
	public ArrayList<ShenPiClass> SelectKeyword(String keyword) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where PARTYA like '%"+keyword+"%' or PARTYB like '%"+keyword+"%' or contractname like '%"+keyword+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//根据表ID查询单行数据
	public ArrayList<ShenPiClass> SelectID(String ID) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where ID  = "+ID+"";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//修改审批状态
	public int update(String id,String zt) {
		String sql = "UPDATE TBL_HETONG_SHENPI SET ZHUANGTAI = "+zt+"  WHERE ID = "+id+"";	
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			rs = db.update(sql);
				if(rs>0){
					System.out.println(rs);
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
	//删除两个表的该ID的合同行
	public int delete(String id) {
		String sql = "DELETE FROM TBL_HETONG_SHENPI WHERE ID = "+id+"";		
		DataBase db = new DataBase();
		int rs = 0;
		try {
			db.connectDb();
			
			rs = db.update(sql);
			
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
	//根据表的开始时间进行模糊查询
	public ArrayList<ShenPiClass> SelectStarttime(String starttime) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where STARTTIME like '"+starttime+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	//根据表的结束时间进行模糊查询
	public ArrayList<ShenPiClass> SelectEndtime(String endtime) {
		
		ArrayList<ShenPiClass> al = null;
		String sql = "select * from TBL_HETONG_SHENPI where ENDTIME like '"+endtime+"%'";
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				al.add(shenpi);
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
	/**
	 * 重写按照时间查询分页
	 */
	//距离合同时间结束还有一周的结果集
	public ArrayList<ShenPiClass> SelectTime1(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//获取一周后的时间
        String xianzaiDate = "";   //现在日期
        String yizhouhouDate = ""; //七天后日期
        Date date = new Date();//当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间格式
        xianzaiDate = sdf.format(date); //当前日期转为String
		try {
			yizhouhouDate = sdf.format(getDate(xianzaiDate, 7));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(yizhouhouDate);
		//获取时间段的合同
        
		//计算应跳转的内容
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+yizhouhouDate+"' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//距离合同时间结束还有二周的结果集
	public ArrayList<ShenPiClass> SelectTime2(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//获取一周后的时间
        String xianzaiDate = "";   //现在日期
        String erzhouhouDate = ""; //十四天后日期
        Date date = new Date();//当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间格式
        xianzaiDate = sdf.format(date); //当前日期转为String
		try {
			erzhouhouDate = sdf.format(getDate(xianzaiDate, 14));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(erzhouhouDate);
		//获取时间段的合同
        
		//计算应跳转的内容
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+erzhouhouDate+"' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	//距离合同时间结束还有三周的结果集
	public ArrayList<ShenPiClass> SelectTime3(int curpage,String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//获取一周后的时间
        String xianzaiDate = "";   //现在日期
        String sanzhouhouDate = ""; //二十一天后日期
        Date date = new Date();//当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间格式
        xianzaiDate = sdf.format(date); //当前日期转为String
		try {
			sanzhouhouDate = sdf.format(getDate(xianzaiDate, 21));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(sanzhouhouDate);
		//获取时间段的合同
        
		//计算应跳转的内容
		int x = curpage * 5;		
		int y = curpage * 5 - 4;
		ArrayList<ShenPiClass> al = null;
		
		String sql = "SELECT  *  FROM (SELECT ROWNUM rm,ID,ZHUANGTAI,BEIZHU,STARTTIME,ENDTIME,PARTYA,PARTYB,CONTRACTNAME,PROJECTAMONNT,CONTRACTDETAIL FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+sanzhouhouDate+"' AND ROWNUM <= "+x+"";
			
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		sql = sql+") WHERE rm >= "+y+"";
		
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			al = new ArrayList<ShenPiClass>();
			while(rs.next()){
				ShenPiClass shenpi = new ShenPiClass();
				shenpi.setId(rs.getInt("ID"));
				shenpi.setZhuangtai(rs.getString("ZHUANGTAI"));
				shenpi.setBeizhu(rs.getString("BEIZHU"));
				shenpi.setStarttime(rs.getString("STARTTIME"));
				shenpi.setEndtime(rs.getString("ENDTIME"));
				shenpi.setPartya(rs.getString("PARTYA"));
				shenpi.setPartyb(rs.getString("PARTYB"));
				shenpi.setContractname(rs.getString("CONTRACTNAME"));
				shenpi.setProjectamonnt(rs.getString("PROJECTAMONNT"));
				shenpi.setContractdetail(rs.getString("CONTRACTDETAIL"));
				al.add(shenpi);
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
	
	//计算TBL_HETONG_SHENPI表全部内容共计多少页，
	//固定条件为已审核,一周内的时间，附带模糊查询，用于告警页面分页
	public int SelectCountSelectTime1(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//获取一周后的时间
        String xianzaiDate = "";   //现在日期
        String yizhouhouDate = ""; //七天后日期
        Date date = new Date();//当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间格式
        xianzaiDate = sdf.format(date); //当前日期转为String
		try {
			yizhouhouDate = sdf.format(getDate(xianzaiDate, 7));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(yizhouhouDate);
		int a = 0; 		//初始化共多少列
		int b = 5;		//初始化每页条数
		int c = 0;		//初始化共有几页
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+yizhouhouDate+"'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			if(rs.next()){
				a = rs.getInt(1);
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
		//计算是否余列
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//返回共有几页
		return c; 
	}
	//计算TBL_HETONG_SHENPI表全部内容共计多少页，
	//固定条件为已审核,二周内的时间，附带模糊查询，用于告警页面分页
	public int SelectCountSelectTime2(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//获取一周后的时间
        String xianzaiDate = "";   //现在日期
        String erzhouhouDate = ""; //七天后日期
        Date date = new Date();//当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间格式
        xianzaiDate = sdf.format(date); //当前日期转为String
		try {
			erzhouhouDate = sdf.format(getDate(xianzaiDate, 14));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(erzhouhouDate);
		int a = 0; 		//初始化共多少列
		int b = 5;		//初始化每页条数
		int c = 0;		//初始化共有几页
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+erzhouhouDate+"'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			if(rs.next()){
				a = rs.getInt(1);
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
		//计算是否余列
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//返回共有几页
		return c; 
	}
	//计算TBL_HETONG_SHENPI表全部内容共计多少页，
	//固定条件为已审核,一周内的时间，附带模糊查询，用于告警页面分页
	public int SelectCountSelectTime3(String partya,String partyb,String contractName,String ZhuangTai,String keyword) {
		//获取一周后的时间
        String xianzaiDate = "";   //现在日期
        String sanzhouhouDate = ""; //七天后日期
        Date date = new Date();//当前日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间格式
        xianzaiDate = sdf.format(date); //当前日期转为String
		try {
			sanzhouhouDate = sdf.format(getDate(xianzaiDate, 7));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(sanzhouhouDate);
		int a = 0; 		//初始化共多少列
		int b = 5;		//初始化每页条数
		int c = 0;		//初始化共有几页
		String sql = "SELECT  COUNT(0)  FROM TBL_HETONG_SHENPI WHERE 1=1 AND ZHUANGTAI = '1' AND ENDTIME between '"+xianzaiDate+"' and '"+sanzhouhouDate+"'";
		
		if(partya != null && !partya.equals("") && !partya.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+partya+"%'";
			
		}else if(partyb != null && !partyb.equals("") && !partyb.equals("0")){
			
			sql = sql+" AND PARTYB LIKE '%"+partyb+"%'";
			
		}else if(contractName != null && !contractName.equals("") && !contractName.equals("0")){
			
			sql = sql+" AND CONTRACTNAME LIKE '%"+contractName+"%'";
			
		}else if(ZhuangTai != null && !ZhuangTai.equals("")){
			
			sql = sql+" AND ZHUANGTAI = '"+ZhuangTai+"'";
			
		}else if(keyword != null && !keyword.equals("") && !keyword.equals("0")){
			
			sql = sql+" AND PARTYA LIKE '%"+keyword+"%' or PARTYB LIKE '%"+keyword+"%' or contractname LIKE '%"+keyword+"%'";
			
		}
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(sql);
			if(rs.next()){
				a = rs.getInt(1);
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
		//计算是否余列
		int x = a % b;

		if(x != 0){
			c = a / b + 1;
		}else{
			c = a / b;
		}
		//返回共有几页
		return c; 
	}
	
	/**   
	* 根据输入的日期字符串 和 提前天数 ，
	* 获得 指定日期提前几天的日期对象
	* @param dateString 日期对象 ，格式如 yyyy-MM-dd
	* @param lazyDays 倒推的天数
	* @return 指定日期倒推指定天数后的日期对象
	* @throws ParseException
	*/
		public static Date getDate(String dateString , int beforeDays) throws ParseException{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear+beforeDays );
			return cal.getTime();
	}
}
