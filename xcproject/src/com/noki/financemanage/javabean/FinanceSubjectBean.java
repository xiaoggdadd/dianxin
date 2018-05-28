package com.noki.financemanage.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;



public class FinanceSubjectBean {
	public  String getFinanceSubject(String fkmcode,String maxnum,String minnum) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow from df_kjkm  where 1=1 ");
		System.out.println(fkmcode+"111111");
		
		if(fkmcode!=null){
			maxSql.append(" and fkmcode='"+fkmcode+"'");
		}
		
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select kmcode,kmname,fkmcode,kmgrade from df_kjkm  where 1=1 ");
		if(fkmcode!=null){
			sql.append(" and fkmcode='"+fkmcode+"'");
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
				 System.out.println(maxSql.toString()+"321");////////////////////////////
				 maxRs = db.queryAll(maxSql.toString());
				 String maxRow = "";
	              while (maxRs.next()) {
	            	  maxRow = maxRs.getString("maxrow");
	              }
	              System.out.println(sql.toString()+"2222222222222");
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("kmcode")+"|");
            	   ret.append(rs.getString("kmname")+"|");
            	   ret.append(rs.getString("kmgrade")+"|");
            	   ret.append(rs.getString("fkmcode")+"|");
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

	//检索下拉框
	public  String getData(String fkmcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		System.out.println(fkmcode+"11111");
		sql.append("select kmcode,kmname,fkmcode,kmgrade from df_kjkm  where 1=1 ");
		if(fkmcode!=null){
			sql.append(" and fkmcode='"+fkmcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println(sql.toString()+"123");/////////////////////////////////////////////////////
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {

            	   ret.append(rs.getString("kmcode")+"|");
            	   ret.append(rs.getString("kmname")+"|");
            	   ret.append(rs.getString("kmgrade")+"|");
            	   ret.append(rs.getString("fkmcode")+",");
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
	//检索下拉框 老的方法在用
	public  String getData1(String fkmcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		System.out.println(fkmcode+"11111");
		sql.append("select kmcode,kmname,fkmcode,kmgrade from df_kjkm  where 1=1 ");
		if(fkmcode!=null){
			sql.append(" and fkmcode='"+fkmcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println(sql.toString()+"123");/////////////////////////////////////////////////////
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {

            	   ret.append(rs.getString("kmcode")+"|");
            	   ret.append(rs.getString("kmname")+"|");
            	   ret.append(rs.getString("kmgrade")+"|");
            	   ret.append(rs.getString("fkmcode")+",");
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
	//add 检索
	public  String getAddData(String fkmcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select kmcode,kmname,fkmcode,kmgrade,id from df_kjkm where 1=1 ");
		if(fkmcode!=null){
			sql.append(" and kmcode='"+fkmcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   ret.append(rs.getString("kmcode")+"|");
            	   ret.append(rs.getString("kmname")+"|");
            	   ret.append(rs.getString("fkmcode")+"|");
            	   ret.append(rs.getString("kmgrade")+"|");
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
	
	//插入
	public String insertData(FinanceSubjectFromBean bean){
		String ret = "";
		String sql = "select t.* from df_kjkm t where 1=1 ";
		if (!"".equals(bean.getKmcode())){
			sql = sql+"and t.kmcode = '"+bean.getKmcode()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into df_kjkm(kmcode,kmname,fkmcode,kmgrade )values( ";
				insertSql = insertSql+"'"+bean.getKmcode()+"','"+bean.getKmname()+"','"+bean.getFkmcode()+"','"+bean.getKmgrade()+"')";
				db.update(insertSql);
			}else{
				String updateSql = "update df_kjkm set kmname='"
				+bean.getKmname()+"',kmgrade='"+bean.getKmgrade()+"', fkmcode = '"+bean.getFkmcode()+"' where 1=1 ";
				updateSql = updateSql+" and  kmcode ='" + bean.getKmcode()+"'";
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
	//删除
	public String delData(String kmCode){
		String ret = "";
		String sql = "delete from df_kjkm t  where 1=1 ";
		if (!"".equals(kmCode)){
			sql = sql+"and t.kmcode = '"+kmCode+"'";
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
	//更新
	public String updateData(FinanceSubjectFromBean bean){
	String ret = "";
	String sql = "select t.* from df_kjkm t where 1=1 ";
	if (!"".equals(bean.getKmcode())){
		sql = sql+"and t.id = "+bean.getId()+"";
	}		
	DataBase db = new DataBase();
	ResultSet rs = null;
	try{
		db.connectDb();
		rs = db.queryAll(sql);
		if (!rs.next()){
			String insertSql= " insert into df_kjkm(kmcode,kmname,fkmcode,kmgrade )values( ";
			insertSql = insertSql+"'"+bean.getKmcode()+"','"+bean.getKmname()+"','"+bean.getFkmcode()+"','"+bean.getKmgrade()+"')";
			db.update(insertSql);
		}else{
			String updateSql = "update df_kjkm set kmname='"
			+bean.getKmname()+"',kmgrade='"+bean.getKmgrade()+"',kmcode='"+bean.getKmcode()+"', fkmcode = '"+bean.getFkmcode()+"' where 1=1 ";
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
