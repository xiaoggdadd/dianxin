package com.noki.newfunction.servlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.newfunction.dao.CbzdReportDao1;

public class ScMessageDao {
	 

	 public synchronized int getCount1(String accountid){
	 
		 int count=0;
		 String sql="select count(cb.id) as count from zhandian z,cbzd cb where z.id = cb.zdid and cb.sjpd='1' and cb.dsqs='0' and (z.xiaoqu in (select t.agcode from per_area t where t.accountid='"+accountid+"'))";
	 
		 DataBase db=new DataBase();
			ResultSet rs=null ;
			
			try {
				db.connectDb();
				System.out.println("----->"+sql.toString());
				rs  = db.queryAll(sql);
				while(rs.next()){
				count=rs.getInt(1);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally{
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
			
			return count;
	 }
	 
	 public synchronized int getCount2(String accountid){
		 
		 int count=0;
		 String sql="select count(cc.zdid) as count  from CBZD cc,CBZDXX zz,CBZDXF cb,zhandian z where z.id=cc.zdid and cc.id=zz.wjid and zz.id=cb.bwjid  and zz.sjxf='1'  AND zz.SHIJXF ='0' AND (Z.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+accountid+"'))";
	 
		 DataBase db=new DataBase();
			ResultSet rs=null ;
			
			try {
				db.connectDb();
				System.out.println("__________<>"+sql.toString());
				rs  = db.queryAll(sql);
				while(rs.next()){
				count=rs.getInt(1);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally{
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
			
			return count;
	 }
	 
	 public synchronized int getCount3(String accountid){
		 
		 int count=0;
		 String sql="select count(cb.id) from zhandian z,cbzd cb where z.id = cb.zdid and cb.dspd='1' and cb.qxqs='0'and(z.xiaoqu in (select t.agcode from per_area t where t.accountid='"+accountid+"'))";
	 
		 DataBase db=new DataBase();
			ResultSet rs=null ;
			
			try {
				db.connectDb();
				System.out.println("----->"+sql.toString());
				rs  = db.queryAll(sql);
				while(rs.next()){
				count=rs.getInt(1);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally{
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
			
			return count;
	 }
	 
	 public synchronized int getCount4(String accountid){
		 
		 int count=0;
		 String sql="SELECT COUNT(KZ.ID) FROM ZHANDIAN ZD, QSKZ KZ WHERE ZD.ID = KZ.ZDID AND KZ.QXTJBZ = '1' AND KZ.SHISHBZ = '0' AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+accountid+"')))";
	 
		 DataBase db=new DataBase();
			ResultSet rs=null ;
			
			try {
				db.connectDb();
				System.out.println("---4-->"+sql.toString());
				rs  = db.queryAll(sql);
				while(rs.next()){
				count=rs.getInt(1);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally{
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
			
			return count;
	 }
	 public synchronized int getCount5(String accountid){
		 
		 int count=0;
		 String sql="SELECT COUNT(DISTINCT AMMETERDEGREEID) FROM MANUALDLCHECK_DDV AD, DIANBIAO DB, ZHANDIAN ZD WHERE AD.AMMETERID_FK = DB.DBID AND ZD.QYZT = '1' AND DB.DBQYZT = '1' "
                  +" AND DB.ZDID = ZD.ID AND AD.MANUALAUDITSTATUS <> '1' AND DB.DBYT = 'dbyt03' AND AD.AMUUID IS NULL AND ((ZD.XIAOQU IN (SELECT T.AGCODE FROM PER_AREA T WHERE T.ACCOUNTID = '"+accountid+"')))";
	 
		 DataBase db=new DataBase();
			ResultSet rs=null ;
			
			try {
				db.connectDb();
				System.out.println("----->"+sql.toString());
				rs  = db.queryAll(sql);
				while(rs.next()){
				count=rs.getInt(1);
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}finally{
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
			
			return count;
	 }
}
