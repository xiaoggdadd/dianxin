package com.noki.map.servlet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class SelZhandianBean {

	//¼ìË÷
	public  String getshiData(String zhanDianId ,String userName) {
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct t.agcode, t.agname from d_area_grade t " +
				" where (t.agcode in (select substr(t.agcode, 0, 5) from per_area t where  t.accountname ='" +userName+"') or " +
						"t.agcode in (select substr(t.agcode, 0, 7) from per_area t where  t.accountname ='" +userName+"') or " +
						"t.agcode in (select t.agcode from per_area t where  t.accountname ='" +userName+"')) " +
								"and t.fagcode ='"+zhanDianId+"'");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
		            for (int n = 1; n <= rsmd.getColumnCount(); n++) {
	     				   if(null!=rs.getString(n)){
	     					   if (n < rsmd.getColumnCount()){
	     						  ret.append(rs.getString(n)+"|");
	     					   }else{
	     						  ret.append(rs.getString(n)+",");
	     					   }
	    					   
	    				   }else{
	    					   ret.append("&nbsp"+"|");
	    				   }
		            }
                }                
			}catch (Exception de) {
	            de.printStackTrace();
	        }finally {
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

	
	//¼ìË÷Õ¾µã
	public  String getZhandianData(String shi,String xian,String xiaoqu,String zdmc) {
		StringBuffer ret = new StringBuffer();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select  t.longitude,t.latitude ,t.address, t.jzname, (case when t.xian is not null then (select distinct agname from d_area_grade where agcode = t.xian) else  ''end) || (case when t.xiaoqu is not null then (select distinct agname from d_area_grade where agcode = t.xiaoqu) else  '' end) as PROPERTY,(select t.name from indexs t where t.code = t.jztype and t.type = 'ZDLX') as JZTYPE ,t.ID,t.sheng,t.shi,t.xian,t.xiaoqu from zhandian t ," +
				"(select distinct agcode from per_area where  agcode='");
		if ("".equals(shi) && "".equals(xian) && "".equals(xiaoqu)){
			sql.append(137+"') p where 1=1  and t.sheng = p.agcode and t.sheng = '137'");
		}else if ("".equals(xian) && "".equals(xiaoqu)){
			sql.append(shi+"') p where 1=1 and t.shi = p.agcode and t.shi = '"+shi+"'");
		}else if ( "".equals(xiaoqu)){
			sql.append(xian+"') p where 1=1  and t.xian = p.agcode and t.xian = '"+xian+"'");
		}else{
			sql.append(xiaoqu+"') p where 1=1  and t.xiaoqu = p.agcode and t.xiaoqu = '"+xiaoqu+"'");
		}
		if(!"".equals(zdmc)){
			
			sql.append(" and T.JZNAME = '"+zdmc+"'");
		}
//		if (!"".equals(biaogan)){
//			sql.append(" and t.BGSIGN = '"+biaogan+"'");
//		}
//		if (!"".equals(fangwuleixing)){
//			sql.append(" and t.YFLX = '"+fangwuleixing+"'");
//		}
	    //sql.append(" and t.jzcode is not null ");
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 System.out.println("+++:"+sql);
				 rs = db.queryAll(sql.toString());
				 
                 ResultSetMetaData rsmd = rs.getMetaData();
               while (rs.next()) {
            	   String agcode = "";
            	   String quyu= "";
            	   if (!"".equals(rs.getString("shi"))){
            		   String tempquyu=getquyu(rs.getString("shi"));
            		   quyu = tempquyu;
            	   }
            	   if (!"".equals(rs.getString("xian"))){
            		   String tempquyu=getquyu(rs.getString("xian"));
            		   quyu = tempquyu;
            	   }
            	   if (!"".equals(rs.getString("xiaoqu"))){
            		   String tempquyu=getquyu(rs.getString("xiaoqu"));
            		   quyu = tempquyu;
            	   }
            	      //System.out.println("OOOOOOO"+rsmd.getColumnCount());
	               for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	            	   if (i==3){
	            		   agcode = quyu;
	            	   }else{
	            		   agcode = rs.getString(i);
	            	   }
            		   
            		   if (i == rsmd.getColumnCount()){
            			   ret.append(agcode+",");
            		   }else{
            			   ret.append(agcode+"|");
            		   }
            		   //System.out.println("++++++last"+ret);
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
		//System.out.println("map"+ ret.toString());
		return ret.toString();
	}
	
	public String getLocality(String shi,String xian,String xiaoqu,String zdmc){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select  t.longitude,t.latitude ,t.jzname from zhandian t where 1=1");
		if ("".equals(shi) && "".equals(xian) && "".equals(xiaoqu)){
			sql.append(" and t.sheng = '137'  and t.shi  is  null");
		}else if ("".equals(xian) && "".equals(xiaoqu)){
			sql.append("  and t.shi = '"+shi+"'");
		}else if ( "".equals(xiaoqu)){
			sql.append(" and t.xian = '"+xian+"'");
		}else{
			sql.append(" and t.xiaoqu = '"+xiaoqu+"'");
		}
		if(!"".equals(zdmc)){
			sql.append(" and T.JZNAME = '"+zdmc+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				  System.out.println("----0000"+sql);
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
		System.out.println("mapXY"+ ret.toString());
		return ret.toString();
	} 
	
	public String getquyu(String shi){
		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.agname from d_area_grade t where 1=1");
		if (!"".equals(shi) ){
			sql.append(" and t.agcode ='"+shi+"'");
		}
        DataBase db = new DataBase();
        ResultSet rs = null;
		if (sql.length()>0){
			try{
				 db.connectDb();
				 rs = db.queryAll(sql.toString());
               while (rs.next()) {
            	   String agcode = rs.getString("agname");
            	   ret.append(agcode);
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