package com.noki.financemanage.javabean;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;



public class FinanceSubjectBean1 {
	public  String getFinanceSubject1(String fkmcode,String maxnum,String minnum,String sz) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow from qcbdf  where 1=1 ");
		System.out.println(fkmcode+"111111");
		
		if(fkmcode!=null){
			maxSql.append(" and fqcbcode='"+fkmcode+"'");
		}
else{
			
			maxSql.append(" and qcbcode='"+fkmcode+"'");
		}
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade,id from qcbdf where 1=1 "+sz+" ");
		if(fkmcode!=null){
			sql.append(" and fqcbcode='"+fkmcode+"'");
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
				 System.out.println(maxSql.toString()+"321");
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
            	   ret.append(rs.getString("fqcbcode")+"|");
            	   ret.append(rs.getString("qcbgrade")+"|");
            	   ret.append(maxRow+"|");
            	   ret.append(rs.getString("rwn")+"|");
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

	//检索下拉框
	public  String getData1(String fkmcode,String bzw) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		System.out.println(fkmcode+"11111");
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade,id from qcbdf  where 1=1 "+bzw+" ");
		if(fkmcode!=null){
			sql.append(" and fqcbcode='"+fkmcode+"'");
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
            	   ret.append(rs.getString("fqcbcode")+"|");
            	   ret.append(rs.getString("id")+",");
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
	public  String getAddData1(String fkmcode,String id) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		String ss="";
		if(id!=null){
			 ss=" and id='"+id+"'";
		}
		//qcbcode,qcbname,fqcbcode,qcbgrade,id
		sql.append("select qcbcode,qcbname,fqcbcode,qcbgrade,id from qcbdf where 1=1 ");
		if(fkmcode!=null){
			sql.append(" and qcbcode='"+fkmcode+"'"+ss);
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
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
	public String insertData1(QuanchengbenFromBean bean){
		String ret = "";
		String sql = "select t.* from qcbdf t where 1=1 ";
		if (!"".equals(bean.getQcbcode())){
			sql = sql+"and t.qcbcode = '"+bean.getQcbcode()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into df_qcb(qcbcode,qcbname,fqcbcode,qcbgrade,bzw )values( ";
				insertSql = insertSql+"'"+bean.getQcbcode()+"','"+bean.getQcbname()+"','"+bean.getFqcbcode()+"','"+bean.getQcbgrade()+"','"+bean.getBzw()+"')";
				System.out.println("监测点增加科目："+insertSql.toString());
				db.update(insertSql);
				
			}else{
				String updateSql = "update df_qcb set qcbname='"
				+bean.getQcbname()+"',qcbgrade='"+bean.getQcbgrade()+"', fqcbcode = '"+bean.getFqcbcode()+"',bzw='"+bean.getBzw()+"' where 1=1 ";
				updateSql = updateSql+" and  qcbcode ='" + bean.getQcbcode()+"'";
				System.out.println("监测点增加科目："+updateSql.toString());
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
	public String delData1(String kmCode){
		String ret = "";
		String sql = "delete from qcbdf t  where 1=1 ";
		if (!"".equals(kmCode)){
			sql = sql+"and t.id = '"+kmCode+"'";
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
	public String updateData1(QuanchengbenFromBean  bean){
		System.out.println(bean.getFqcbcode());
		String ret = "";
		String sql = "select t.* from qcbdf t where 1=1 ";
		if (!"".equals(bean.getQcbcode())){
			sql = sql+"and t.id = "+bean.getId()+"";
		}
		System.out.println("sql----"+sql.toString());
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into qcbdf(qcbcode,qcbname,fqcbcode,qcbgrade,bzw )values( ";
				insertSql = insertSql+"'"+bean.getQcbcode()+"','"+bean.getQcbname()+"','"+bean.getFqcbcode()+"','"+bean.getQcbgrade()+"','"+bean.getBzw()+"')";
				//db.update(insertSql);
				System.out.println("修改全成本insertSql："+insertSql.toString());
			}else{
				String updateSql = "update qcbdf set qcbname='"
				+bean.getQcbname()+"',qcbgrade='"+bean.getQcbgrade()+"',qcbcode='"+bean.getQcbcode()+"', fqcbcode = '"+bean.getFqcbcode()+"',bzw='"+bean.getBzw()+"' where 1=1 ";
				updateSql = updateSql+" and  id =" + bean.getId();
				db.update(updateSql.toString());
				System.out.println("修改全成本updateSql："+updateSql.toString());
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
