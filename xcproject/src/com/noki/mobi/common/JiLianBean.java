package com.noki.mobi.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class JiLianBean {
	public synchronized String getFenzu_Role(String pid) {
        StringBuffer list = new StringBuffer();
        list.append("<response>");
        String sql =
            "select roleid,name from role where fenzu="+pid;

        DataBase db = new DataBase();
        ResultSet rs = null;

        try {
          db.connectDb();
          System.out.println(sql);
          rs = db.queryAll(sql);
          while(rs.next()){
              list.append("<res>"+rs.getString(1)+"</res>");
              list.append("<res>"+rs.getString(2)+"</res>");
          }
          list.append("</response>");
        }

        catch (DbException de) {
          de.printStackTrace();
        } catch (SQLException de) {
          de.printStackTrace();
        }

        finally {
          if (rs != null) {
            try {
              rs.close();
            }
            catch (SQLException se) {
              se.printStackTrace();
            }
          }
          try {
            db.close();
          }
          catch (DbException de) {
            de.printStackTrace();
          }

        }

        return list.toString();
      }
	
	public String getMenu_permission(String pid,String roleid) {
	       StringBuffer pdata = new StringBuffer();
	       pdata.append("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
	       DataBase db = new DataBase();
	       try {

	           db.connectDb();
	           ResultSet rs = null;
	           StringBuffer sql = new StringBuffer();
	           sql.append(" select rightid,name  from right where rightid like '"+pid.substring(0,2)+"%' and block='0' order by rightid");
	           System.out.println("--"+sql.toString());
	           rs = db.queryAlls(sql.toString());;
	           while (rs.next()) {
	        	   pdata.append("<tr>");
	              //pdata.append("<a href='javascript:addwd(\""+rs.getString(1)+"\")'>"+rs.getString(1)+"</a></br>");
	        	   pdata.append("<td width=\"20%\" align=\"right\">"+rs.getString(2)+"&nbsp;&nbsp;&nbsp;&nbsp;</td>");
	        	   pdata.append("<td  width=\"80%\" align=\"left\">");
	        	   pdata.append(this.getMenu_permission_detail(db, rs.getString(1), roleid));
	        	   pdata.append("</td>");
	        	   pdata.append("</tr>");
	           }
	           pdata.append("</table>");
	       } catch (DbException de) {
	           de.printStackTrace();
	       } catch (SQLException de) {
	           de.printStackTrace();
	       } finally {
	           try {
	               db.close();
	           } catch (DbException de) {
	               de.printStackTrace();
	           }

	       }
	       return pdata.toString();
	   }
	
	private String getMenu_permission_detail(DataBase db,String rightid,String roleid) throws DbException,SQLException{
	       StringBuffer pdata = new StringBuffer();
	      

	           db.connectDb();
	           ResultSet rs = null;
	           StringBuffer sql = new StringBuffer();
	           sql.append(" select r.permission from role_permission r where r.mid='"+rightid+"' and r.roleid="+roleid);
	           System.out.println("--"+sql.toString());
	           rs = db.queryAlls(sql.toString());;
	           while (rs.next()) {
	        	   pdata.append(rs.getString(1));
	           }
	           rs = db.queryAll("select name,biaoshi from biaoshi order by orderid");
	           StringBuffer s2 = new StringBuffer();
	           int j = 0;
	           while(rs.next()){
	        	   if(j==0){
	        		   s2.append("<input type=\"checkbox\" name=\"itemSelected\" value='' onClick=\"ccrp('"+rs.getString(2).trim()+"','"+roleid+"','"+rightid+"',this.checked)\"");
	        		   if(pdata.toString().indexOf(rs.getString(2).trim())>-1){
	        			   s2.append(" checked=\"checked\"");
	        		   }
	        		   s2.append(" />&nbsp;"+rs.getString(1));
	        	   }else{
	        		   s2.append("&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"checkbox\" name=\"itemSelected\" value='' onClick=\"ccrp('"+rs.getString(2).trim()+"','"+roleid+"','"+rightid+"',this.checked)\" ");
	        		   if(pdata.toString().indexOf(rs.getString(2).trim())>-1){
	        			   s2.append(" checked=\"checked\"");
	        		   }
	        		   s2.append(" />&nbsp;"+rs.getString(1));
	        	   }
	        	   j++;
	           }
	         
	       System.out.println(s2.toString());
	       return s2.toString();
	   }
	
	public synchronized String changeRP(String permission,String roleid,String rightid,String ad) {
        StringBuffer list = new StringBuffer();
        list.append("no");

        DataBase db = new DataBase();
        StringBuffer sql = new StringBuffer();
        if(ad.equals("0")){
        	sql.append("delete from role_permission where permission='"+permission+"' and roleid="+roleid);
        	sql.append(" and mid='"+rightid+"'");
        }else{
        	sql.append("insert into role_permission(roleid,permission,mid)");
        	sql.append(" values("+roleid+",'"+permission+"','"+rightid+"')");
        }
        System.out.println(sql.toString());
        try {
          db.connectDb();
         
          if(db.update(sql.toString())==1){
        	  list.append("ok");
          }
        
        }

        catch (DbException de) {
          de.printStackTrace();
        } 

        finally {
         
          try {
            db.close();
          }
          catch (DbException de) {
            de.printStackTrace();
          }

        }

        return list.toString();
      }

}
