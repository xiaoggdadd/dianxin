package com.noki.participateinfo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class ParticipateinfoBean {
	public  String getSelData(String fagcode,String xmzrr,String max,String min) {
 		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		//取最大值
		StringBuffer maxSql = new StringBuffer();
		maxSql.append("select count(*) as maxrow  from cyrxx t  where 1=1 ");
		if(fagcode!=null&& !"".equals(fagcode)){
			maxSql.append(" and xmmch like'"+fagcode+"%'");
		}
		if(xmzrr!=null&& !"".equals(xmzrr)){
			maxSql.append(" and cyrmch ='"+xmzrr+"'");
		}
		sql.append("select *from (select a1.*,rownum rwn from (");
		sql.append("select t.cyrmch,t.cyrms,t.gsmm,t.zzms,t.cysj,t.xmmch from cyrxx t where 1=1 ");
		if(fagcode!=null&& !"".equals(fagcode)){
			sql.append(" and xmmch like'"+fagcode+"%'");
		}
		if(xmzrr!=null&& !"".equals(xmzrr)){
			sql.append(" and cyrmch ='"+xmzrr+"'");
		}
		sql.append(" ) a1  where 1=1  ");
		if (max!=null&&!"".equals(max)){
           sql.append(" and rownum<="+max);
		}
		sql.append(") where 1=1 ");
		if (min!=null&&!"".equals(min)){
			sql.append(" and rwn>="+min);
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
               while (rs.next()) {
            	   ret.append(rs.getString("xmmch")+"|");
            	   ret.append(rs.getString("cyrmch")+"|");
            	   ret.append(rs.getString("cyrms")+"|");
            	   ret.append(rs.getString("gsmm")+"|");
            	   ret.append(rs.getString("zzms")+"|");
            	   ret.append(rs.getString("cysj")+"|");
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
	
	//插入或者更新纪录
	public String insertData(ParticipateinfoViewBean bean){
		String ret = "";
		String sql = "select t.*from cyrxx t where 1=1 ";
		if (!"".equals(bean.getXmmch())){
			sql = sql+"and t.xmmch = '"+bean.getXmmch()+"'";
		}		
		DataBase db = new DataBase();
		ResultSet rs = null;
		try{
			db.connectDb();
			rs = db.queryAll(sql);
			if (!rs.next()){
				String insertSql= " insert into cyrxx (cyrmch,cyrms,gsmm,zzms,cysj,xmmch)values( ";
				insertSql = insertSql+"'"+bean.getCyrmch()+"','"+bean.getCyrms()+"','"+bean.getGsmm()+"','"+bean.getZzms()
				+"','"+bean.getCysj()+"','"+bean.getXmmch()+"')";
				db.update(insertSql);
			}else{
				String updateSql = "update cyrxx set cyrmch='"
				+bean.getCyrmch()+"',cyrms='"+bean.getCyrms()+"', gsmm = '"+bean.getGsmm()+"', zzms = '"+bean.getZzms()
				+"', cysj = '"+bean.getCysj()+"' where 1=1 ";
				updateSql = updateSql+" and  xmmch ='" + bean.getXmmch()+"'";
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
  //更新画面显示检索
  public String updateSel(String xmmch){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.cyrmch,t.cyrms,t.gsmm,t.zzms,t.cysj,t.xmmch from cyrxx t where 1=1 ");
		if(xmmch!=null&& !"".equals(xmmch)){
			sql.append(" and xmmch ='"+xmmch+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("xmmch")+"|");
            	   ret.append(rs.getString("cyrmch")+"|");
            	   ret.append(rs.getString("cyrms")+"|");
            	   ret.append(rs.getString("gsmm")+"|");
            	   ret.append(rs.getString("zzms")+"|");
            	   ret.append(rs.getString("cysj")+",");
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
	public String delData(String xmmch){
		String ret = "";
		String sql = "delete from cyrxx t  where 1=1 ";
		if (!"".equals(xmmch)){
			sql = sql+"and t.xmmch = '"+xmmch+"'";
		}		
		DataBase db = new DataBase();
		try{
			db.connectDb();
			db.update(sql.toString());

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
	//下拉框检索
	public String optionSele(){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.xmmch,t.xmtype,t.jfmch,t.xmfzr,t.xmms,t.kssj,t.sjjssj,t.sgdw,t.jldw,t.cyrs," +
		"t.yjjssj from xiangmuxinxi t where t.xmmch NOT in (select xmmch from cyrxx ) ");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   ret.append(rs.getString("xmmch")+"|");
            	   ret.append(rs.getString("xmtype")+"|");
            	   ret.append(rs.getString("jfmch")+"|");
            	   ret.append(rs.getString("xmfzr")+"|");
            	   ret.append(rs.getString("xmms")+"|");
            	   ret.append(rs.getString("kssj")+"|");
            	   ret.append(rs.getString("sjjssj")+"|");
            	   ret.append(rs.getString("sgdw")+"|");
            	   ret.append(rs.getString("jldw")+"|");
            	   ret.append(rs.getString("cyrs")+"|");
            	   ret.append(rs.getString("yjjssj")+",");
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
