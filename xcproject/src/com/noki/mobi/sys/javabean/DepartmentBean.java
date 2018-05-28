package com.noki.mobi.sys.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.sysconfig.javabean.localityViewDataBean;



public class DepartmentBean {
	//¼ìË÷
	public  String getDepartment(String fdeptcode,String maxnum,String minnum) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow from department  where 1=1 ");
		if(fdeptcode!=null){
			maxSql.append(" and fdeptcode='"+fdeptcode+"'");
		}
		
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select deptcode,deptname,fdeptcode,deptgrade from department  where 1=1 ");
		if(fdeptcode!=null){
			sql.append(" and fdeptcode='"+fdeptcode+"'");
		}
		
		sql.append(" ) a1  where 1=1  ");
		if (maxnum!=null&&!"".equals(maxnum)){
           sql.append(" and rownum<="+maxnum);
		}
		sql.append(") where 1=1 ");
		if (minnum!=null&&!"".equals(minnum)){
			sql.append(" and rwn>="+minnum);
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
        ResultSet maxRs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println(maxSql.toString());////////////////////////////
				 maxRs = db.queryAll(maxSql.toString());
				 String maxRow = "";
	              while (maxRs.next()) {
	            	  maxRow = maxRs.getString("maxrow");
	              }
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("deptcode")+"|");
            	   ret.append(rs.getString("deptname")+"|");
            	   ret.append(rs.getString("deptgrade")+"|");
            	   ret.append(rs.getString("fdeptcode")+"|");
            	   ret.append(maxRow+"|");
            	   ret.append(rs.getString("rwn")+",");
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
	}

	//¼ìË÷ÏÂÀ­¿ò
	public  String getData(String fdeptcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select deptcode,deptname,fdeptcode,deptgrade from department  where 1=1 ");
		if(fdeptcode!=null){
			sql.append(" and fdeptcode='"+fdeptcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println(sql.toString());/////////////////////////////////////////////////////
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {

            	   ret.append(rs.getString("deptcode")+"|");
            	   ret.append(rs.getString("deptname")+"|");
            	   ret.append(rs.getString("deptgrade")+"|");
            	   ret.append(rs.getString("fdeptcode")+",");
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
	}
	//add ¼ìË÷
	public  String getAddData(String fdeptcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select deptcode,deptname,fdeptcode,deptgrade,id from department  where 1=1 ");
		if(fdeptcode!=null){
			sql.append(" and deptcode='"+fdeptcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   ret.append(rs.getString("deptcode")+"|");
            	   ret.append(rs.getString("deptname")+"|");
            	   ret.append(rs.getString("fdeptcode")+"|");
            	   ret.append(rs.getString("deptgrade")+"|");
            	   ret.append(rs.getInt("id")+",");
                }                
			}catch (Exception de) {
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
		}
		return ret.toString();
	}
	
	//²åÈë
	public String insertData(DepartmentFromBean bean){
		String ret = "";
		String sql = "select t.* from department t where 1=1 ";
		if (!"".equals(bean.getDeptcode())){
			sql = sql+"and t.deptcode = '"+bean.getDeptcode()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into department(deptcode,deptname,fdeptcode,deptgrade )values( ";
				insertSql = insertSql+"'"+bean.getDeptcode()+"','"+bean.getDeptname()+"','"+bean.getFdeptcode()+"','"+bean.getDeptgrade()+"')";
				db.update(insertSql);
			}else{
				String updateSql = "update department set deptname='"
				+bean.getDeptname()+"',deptgrade='"+bean.getDeptgrade()+"', fdeptcode = '"+bean.getFdeptcode()+"' where 1=1 ";
				updateSql = updateSql+" and  deptcode ='" + bean.getDeptcode()+"'";
				db.update(updateSql.toString());
			}	
			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return ret;
	 }
	//É¾³ý
	public String delData(String deptCode){
		String ret = "";
		String sql = "delete from department t  where 1=1 ";
		if (!"".equals(deptCode)){
			sql = sql+"and t.deptcode = '"+deptCode+"'";
		}		
		DataBase db = new DataBase();
		try{
			db.connectDb();
			int ret1 = db.update(sql.toString());

			}catch(Exception e){
			      try {
				        db.rollback();
				      }
				      catch (DbException dee) {
				        dee.printStackTrace();
				      }
				      e.printStackTrace();
			}finally {
			      try {
				        db.close();
				      }
				      catch (Exception de) {
				        de.printStackTrace();
				      }
			}
			return ret;
	 }
	//¸üÐÂ
	public String updateData(DepartmentFromBean bean){
	String ret = "";
	String sql = "select t.* from department t where 1=1 ";
	if (!"".equals(bean.getDeptcode())){
		sql = sql+"and t.id = "+bean.getId()+"";
	}		
	DataBase db = new DataBase();
	ResultSet rs = null;
	try{
		db.connectDb();
		rs = db.queryAll(sql);
		if (!rs.next()){
			String insertSql= " insert into department(deptcode,deptname,fdeptcode,deptgrade )values( ";
			insertSql = insertSql+"'"+bean.getDeptcode()+"','"+bean.getDeptname()+"','"+bean.getFdeptcode()+"','"+bean.getDeptgrade()+"')";
			db.update(insertSql);
		}else{
			String updateSql = "update department set deptname='"
			+bean.getDeptname()+"',deptgrade='"+bean.getDeptgrade()+"',deptcode='"+bean.getDeptcode()+"', fdeptcode = '"+bean.getFdeptcode()+"' where 1=1 ";
			updateSql = updateSql+" and  id =" + bean.getId();
			db.update(updateSql.toString());
		}	
		}catch(Exception e){
		      try {
			        db.rollback();
			      }
			      catch (DbException dee) {
			        dee.printStackTrace();
			      }
			      e.printStackTrace();
		}finally {
		      try {
			        db.close();
			      }
			      catch (Exception de) {
			        de.printStackTrace();
			      }
		}
		return ret;
	}
}
