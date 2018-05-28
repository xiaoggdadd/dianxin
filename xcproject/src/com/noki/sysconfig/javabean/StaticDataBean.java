package com.noki.sysconfig.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.energy.javabean.DisplayBean;

public class StaticDataBean {
   //检索小分类
	public  String getZhandianData(String staticType,String maxnum,String minnum) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow from indexs t where 1=1 ");
		if (staticType!=null &&!"".equals(staticType)){
			maxSql.append("  and t.type = '"+staticType +"'");
		}
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select t.code,t.name,t.indexs2 from indexs t where 1=1 ");
		if (staticType!=null &&!"".equals(staticType)){
			sql.append("  and t.type = '"+staticType +"'");
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
				 maxRs = db.queryAll(maxSql.toString());
				 String maxRow = "";
	              while (maxRs.next()) {
	            	  maxRow = maxRs.getString("maxrow");
	              }
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   ret.append(rs.getString("code")+"|");
            	   ret.append(rs.getString("name")+"|");
            	   ret.append(rs.getString("indexs2")+"|");
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
	//插入更新
	public void insertData(StaticDataViewBean bean){		
		String sql = "select * from indexs t where 1=1";
		if (!"".equals(bean.getCode())){
			sql = sql+"and t.code = '"+bean.getCode()+"'";
		}
			
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into indexs t (t.id,t.code,t.name,t.type,t.indexs1,t.indexs2)values( ";
				insertSql = insertSql+"(select max(id) from indexs )+1,"+"'"+bean.getCode()+"','"+bean.getName()+"','"+bean.getType()+"','"
				+bean.getIndexs1()+"','"+bean.getIndexs2()+"')";
				db.update(insertSql);
			}else{
				String updateSql = "update indexs set name='"
				+bean.getName()+"',indexs2 = '"+bean.getIndexs2()+"' where 1=1 " +
						"and code= '"+bean.getCode()+"'";
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
		}
	
	//检索大分类
	public  String getStaticData(String typeVal) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.indexs1,t.type from indexs t where 1=1 ");
		if (typeVal!=null){
			sql.append(" and type = '"+typeVal+"'");
		}
		sql.append(" group by t.type ,t.indexs1");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
                 System.out.println("静态数据大分类："+sql.toString());
               while (rs.next()) {
            	   String agcode = "";
	               for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            		   agcode = rs.getString(i);
            		   if (i == rsmd.getColumnCount()){
            			   ret.append(agcode+",");
            		   }else{
            			   ret.append(agcode+"|");
            		   }
            	   }
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
	
	
	//根据id得到数据
	
	public  String getDateById(int id) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.code,t.name,t.indexs1,t.indexs2 from indexs t where 1=1 ");
		sql.append(" and code= "+id);
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   String agcode = "";
	               for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            		   agcode = rs.getString(i);
            		   if (i == rsmd.getColumnCount()){
            			   ret.append(agcode+",");
            		   }else{
            			   ret.append(agcode+"|");
            		   }
            	   }
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
	
	
	
	//删除
	public  void delDate(String id,String type) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from indexs where 1=1");
		sql.append(" and code= '"+id+"'");
		sql.append(" and type= '"+type+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 db.update(sql.toString());
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
	}
	
	/**
	 * 得到最大code
	 * @param id
	 */
	public  String getMaxCode() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from indexs where id =(select max(id) from indexs)");
		DataBase db = new DataBase();
		ResultSet rs = null;
		String code ="";
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			while(rs.next()){
				StaticDataViewBean disbean = new StaticDataViewBean();
			    code = rs.getString("CODE");
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
		return code;
	}
	//检索大分类
	public  String getModfyStaticData(String typeVal,String code) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.indexs1,t.type,t.code,t.name,t.indexs2 from indexs t where 1=1 ");
		if (typeVal!=null){
			sql.append(" and type = '"+typeVal+"'");
		}
		if (code!=null){
			sql.append(" and code = '"+code+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   String agcode = "";
	               for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            		   agcode = rs.getString(i);
            		   if (i == rsmd.getColumnCount()){
            			   ret.append(agcode+",");
            		   }else{
            			   ret.append(agcode+"|");
            		   }
            	   }
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
}
