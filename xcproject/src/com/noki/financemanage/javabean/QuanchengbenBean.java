package com.noki.financemanage.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class QuanchengbenBean {
	public  String getQuanchengben(String fqcbcode,String maxnum,String minnum) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow from df_qcb  where 1=1 ");
		System.out.println(fqcbcode+"111111");
		
		if(fqcbcode!=null){
			maxSql.append(" and fqcbcode='"+fqcbcode+"'");
		}
		
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade from df_qcb  where 1=1 ");
		if(fqcbcode!=null){
			sql.append(" and fqcbcode='"+fqcbcode+"'");
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
            	   ret.append(rs.getString("qcbcode")+"|");
            	   ret.append(rs.getString("qcbname")+"|");
            	   ret.append(rs.getString("qcbgrade")+"|");
            	   ret.append(rs.getString("fqcbcode")+"|");
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
	public  String getData(String fqcbcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		System.out.println(fqcbcode+"11111");
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade from df_qcb  where 1=1 ");
		if(fqcbcode!=null){
			sql.append(" and fqcbcode='"+fqcbcode+"'");
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

            	   ret.append(rs.getString("qcbcode")+"|");
            	   ret.append(rs.getString("qcbname")+"|");
            	   ret.append(rs.getString("qcbgrade")+"|");
            	   ret.append(rs.getString("fqcbcode")+",");
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
	//检索下拉框  老的方法在用
	public  String getData1(String fqcbcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		System.out.println(fqcbcode+"11111");
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade from df_qcb  where 1=1 ");
		if(fqcbcode!=null){
			sql.append(" and fqcbcode='"+fqcbcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println("全成本下拉框"+sql.toString());/////////////////////////////////////////////////////
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {

            	   ret.append(rs.getString("qcbcode")+"|");
            	   ret.append(rs.getString("qcbname")+"|");
            	   ret.append(rs.getString("qcbgrade")+"|");
            	   ret.append(rs.getString("fqcbcode")+",");
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
	public  String getAddData(String fqcbcode) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade,id from df_qcb where 1=1 ");
		if(fqcbcode!=null){
			sql.append(" and qcbcode='"+fqcbcode+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 System.out.println("11111111"+sql.toString());
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   ret.append(rs.getString("qcbcode")+"|");
            	   ret.append(rs.getString("qcbname")+"|");
            	   ret.append(rs.getString("fqcbcode")+"|");
            	   ret.append(rs.getString("qcbgrade")+"|");
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
	public String insertData(QuanchengbenFromBean bean){
		String ret = "";
		String sql = "select t.* from df_qcb t where 1=1 ";
		if (!"".equals(bean.getQcbcode())){
			sql = sql+"and t.qcbcode = '"+bean.getQcbcode()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into df_qcb(qcbcode,qcbname,fqcbcode,qcbgrade )values( ";
				insertSql = insertSql+"'"+bean.getQcbcode()+"','"+bean.getQcbname()+"','"+bean.getFqcbcode()+"','"+bean.getQcbgrade()+"')";
				db.update(insertSql);
			}else{
				String updateSql = "update df_qcb set qcbname='"
				+bean.getQcbname()+"',qcbgrade='"+bean.getQcbgrade()+"', fqcbcode = '"+bean.getFqcbcode()+"' where 1=1 ";
				updateSql = updateSql+" and  qcbcode ='" + bean.getQcbcode()+"'";
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
	public String delData(String qcbCode){
		String ret = "";
		String sql = "delete from df_qcb t  where 1=1 ";
		if (!"".equals(qcbCode)){
			sql = sql+"and t.qcbcode = '"+qcbCode+"'";
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
	public String updateData(	QuanchengbenFromBean bean){
		System.out.println(bean.getFqcbcode());
		String ret = "";
		String sql = "select t.* from df_qcb t where 1=1 ";
		if (!"".equals(bean.getQcbcode())){
			sql = sql+"and t.id = "+bean.getId()+"";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into df_qcb(qcbcode,qcbname,fqcbcode,qcbgrade )values( ";
				insertSql = insertSql+"'"+bean.getQcbcode()+"','"+bean.getQcbname()+"','"+bean.getFqcbcode()+"','"+bean.getQcbgrade()+"')";
				db.update(insertSql);
			}else{
				String updateSql = "update df_qcb set qcbname='"
				+bean.getQcbname()+"',qcbgrade='"+bean.getQcbgrade()+"',qcbcode='"+bean.getQcbcode()+"', fqcbcode = '"+bean.getFqcbcode()+"' where 1=1 ";
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
