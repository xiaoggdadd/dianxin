package com.noki.mobi.common;

import com.noki.database.DbException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import java.sql.SQLException;

public class CommonAjaxBean {
    public CommonAjaxBean() {
    }
    public synchronized String getChildrenArea_shi(String pid) {
           StringBuffer list = new StringBuffer();
           list.append("<response>");
           String sql =
               "select cityid,citynm from d_city where sfid="+pid+" order by citynm";

           DataBase db = new DataBase();
           ResultSet rs = null;

           try {
             db.connectDb();

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
 public synchronized String getChildrenArea_xian(String pid) {
           StringBuffer list = new StringBuffer();
           list.append("<response>");
           String sql =
               "select qxid,qxnm from d_qx where cityid="+pid+" order by qxnm";

           DataBase db = new DataBase();
           ResultSet rs = null;

           try {
             db.connectDb();

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

}
