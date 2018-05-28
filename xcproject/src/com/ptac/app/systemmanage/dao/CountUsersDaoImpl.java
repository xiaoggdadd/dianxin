package com.ptac.app.systemmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.ptac.app.systemmanage.bean.CountUsers;
import com.ptac.app.systemmanage.bean.CountUsersInfo;

public class CountUsersDaoImpl implements CountUsersDao {
	DataBase db = new DataBase();
	Connection conn = null;
	Statement sta = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public List<CountUsers> queryCountUsers(String whereStr, String loginId) {

		List<CountUsers> list = new ArrayList<CountUsers>();
		StringBuffer sqlcount = new StringBuffer();

		try {
			db.connectDb();	 
			conn = db.getConnection();
			sta = conn.createStatement();
			
			sqlcount.append(" select shi, "
		       +" (select distinct agname from d_area_grade where agcode = a.shi) as ZWSHI, "
		       +" count(rolename) as COUNT, "
		       +" count(case when a.rolename like '%管理员%' then 1  else null end) as GUANLY, "
		       +" count(case when a.rolename like '%业务岗%' then 1 else null end) as YEWG, "
		       +" count(case when a.rolename like '%市审核%' then 1 else null end) as SHISHH, "
		       +" count(case when a.rolename like '%市分析%' then 1 else null end) as SHIFX, "
		       +" count(case when a.rolename like '%市录入%' then 1 else null end) as SHILR, "
		       +" count(case when a.rolename like '%市决策%' then 1 else null end) as SHIJC, "
		       +" count(case when a.rolename like '%市系统%' then 1 else null end) as SHIXT, "
		       +" count(case when a.rolename like '%市财务%' then 1 else null end) as SHICW, "
		       +" count(case when a.rolename like '%省决策%' then 1 else null end) as SHENGJC, "
		       +" count(case when a.rolename like '%省分析%' then 1 else null end) as SHENGFX, "
		       +" count(case when a.rolename like '%省系统%' then 1 else null end) as SHENGXT, "
		       +" count(case when a.rolename like '%省财务%' then 1 else null end) as SHENGCW, "
		       +" count(case when a.rolename like '%区县录入%' then 1 else null end) as QXLR, "
		       +" count(case when a.rolename like '%区县管理%' then 1 else null end) as QXGL, "
		       +" count(case when a.rolename like '%区县财务%' then 1 else null end) as QXCW, "
		       +" count(case when a.rolename like '%区县分析%' then 1 else null end) as QXFX "
		       +" from account a "
		       +" where 1=1 "
		       + whereStr
		       +" group by shi order by shi ");

			rs = sta.executeQuery(sqlcount.toString());
			while (rs.next()) {
				CountUsers bean = new CountUsers();
				String shi = rs.getString("SHI") != null ? rs.getString("SHI") : "0";
				String zwshi = rs.getString("ZWSHI") != null ? rs.getString("ZWSHI") : "0";
				
				if("".equals(shi)||"0".equals(shi)){
					bean.setZwshi("不明");
				}else{
					bean.setZwshi(zwshi);
				}
				bean.setShi(shi);
				
				bean.setCount(rs.getString("COUNT") != null ? rs.getString("COUNT") : "0");
				bean.setGuanly(rs.getString("GUANLY") != null ? rs.getString("GUANLY") : "0");
				bean.setYewg(rs.getString("YEWG") != null ? rs.getString("YEWG") : "0");
				bean.setShishh(rs.getString("SHISHH") != null ? rs.getString("SHISHH") : "0");
				bean.setShifx(rs.getString("SHIFX") != null ? rs.getString("SHIFX") : "0");
				bean.setShilr(rs.getString("SHILR") != null ? rs.getString("SHILR") : "0");
				bean.setShijc(rs.getString("SHIJC") != null ? rs.getString("SHIJC") : "0");
				bean.setShixt(rs.getString("SHIXT") != null ? rs.getString("SHIXT") : "0");
				bean.setShicw(rs.getString("SHICW") != null ? rs.getString("SHICW") : "0");
				bean.setShengjc(rs.getString("SHENGJC") != null ? rs.getString("SHENGJC") : "0");
				bean.setShengfx(rs.getString("SHENGFX") != null ? rs.getString("SHENGFX") : "0");
				bean.setShengxt(rs.getString("SHENGXT") != null ? rs.getString("SHENGXT") : "0");
				bean.setShengcw(rs.getString("SHENGCW") != null ? rs.getString("SHENGCW") : "0");
				bean.setQxlr(rs.getString("QXLR") != null ? rs.getString("QXLR") : "0");
				bean.setQxgl(rs.getString("QXGL") != null ? rs.getString("QXGL") : "0");
				bean.setQxcw(rs.getString("QXCW") != null ? rs.getString("QXCW") : "0");
				bean.setQxfx(rs.getString("QXFX") != null ? rs.getString("QXFX") : "0");
				
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
	}
	
	
	public List<CountUsersInfo> queryInfo(String whereStr){
		List<CountUsersInfo> list = new ArrayList<CountUsersInfo>();
		StringBuffer sql = new StringBuffer();
		try {
			db.connectDb();
			conn = db.getConnection();
			sta = conn.createStatement();
			sql.append(" select shi, "
					 +" (select distinct agname from d_area_grade where agcode = a.shi) as ZWSHI, "
					 +" NAME, ROLENAME, ACCOUNTNAME, MOBILE, TEL, EMAIL "
					 +" from account a " 
					 +" where 1=1 " 
					 + whereStr
					 +" order by name");
			rs = sta.executeQuery(sql.toString());
			while(rs.next()){
				CountUsersInfo bean = new CountUsersInfo();
				bean.setName(rs.getString("NAME") != null? rs.getString("NAME"):"");
				bean.setRolename(rs.getString("ROLENAME") != null ? rs.getString("ROLENAME") : "");
				bean.setAccountname(rs.getString("ACCOUNTNAME") != null ? rs.getString("ACCOUNTNAME") : "");
				bean.setMobile(rs.getString("MOBILE") != null ? rs.getString("MOBILE") : "");
				bean.setTel(rs.getString("TEL") != null ? rs.getString("TEL") : "");
				bean.setEmail(rs.getString("EMAIL") != null ? rs.getString("EMAIL") : "");
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				db.rollback();
			} catch (DbException e1) {
				e1.printStackTrace();
			}
		} finally {
			db.free(rs,sta,conn);
		}
		return list;
	}
}
