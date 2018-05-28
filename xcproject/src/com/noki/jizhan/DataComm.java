package com.noki.jizhan;

import com.noki.database.DbException;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import java.sql.SQLException;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DataComm {
    public DataComm() {
    }

    public synchronized String getFuzeArea(DataBase db, String loginName) throws
            DbException, SQLException {
        StringBuffer s = new StringBuffer();
        ResultSet rs = db.queryAll(
                "select agcode from per_area where accountname='" + loginName +
                "' order by agcode");
        ResultSet rs2 = null;
        while (rs.next()) {
            if (rs.getString(1).length() == 9) {
                s.append("'" + rs.getString(1) + "',");
            } else {
                rs2 = db.queryAll(
                        "select agcode from d_area_grade where agcode like'" +
                        rs.getString(1) + "%'");
                while (rs2.next()) {
                    s.append("'" + rs2.getString(1) + "',");
                }
            }
        }
        s.append("'-1'");
        return s.toString();
    }

    public synchronized String getFuzeZdid(DataBase db, String loginName,
                                           String fzarea,
                                           String jztype) throws
            DbException, SQLException {
        StringBuffer s = new StringBuffer("0");
        ResultSet rs = null;

        rs = db.queryAll(
                "select begincode,endcode from per_zhandian where accountname='" +
                loginName +
                "' and begincode is not null and endcode is not null");
        StringBuffer cxtj = new StringBuffer("(");
        int csnum = 0;
        while (rs.next()) {
            if (csnum == 0) {
                cxtj.append(" (zdcode>='" + rs.getString(1) +
                            "' and zdcode <='" +
                            rs.getString(2) + "')");
            } else {
                cxtj.append(" or ( zdcode>='" + rs.getString(1) +
                            "' and zdcode <='" +
                            rs.getString(2) + "')");
            }
            csnum++;
        }
        if (csnum == 0) {
            return s.toString();
        }
        cxtj.append(")");
        if (fzarea.equals("'0'")) { //没有设置负责区域 返回查询负责站点的查询条件
            s.setLength(0);
            s.append(cxtj.toString());
        } else { //有设置负责区域 返回没有在负责区域内的负责站点id
            StringBuffer s3 = new StringBuffer();
            s3.append("select id from zhandian where ");
            if (cxtj.equals("()")) {
                s3.append(" id not in (");
            } else {
                s3.append(cxtj.toString());
                s3.append(" and id not in (");
            }

            s3.append("select id from zhandian where xiaoqu in (" + fzarea +
                      ") ");
            if (!jztype.equals("0")) {
                s3.append(" and jztype='" + jztype + "'");
            }
            s3.append(")");
            if (!jztype.equals("0")) {
                s3.append(" and jztype='" + jztype + "'");
            }
            System.out.println(s3.toString());
            rs = db.queryAll(s3.toString());
            StringBuffer ids = new StringBuffer();
            while (rs.next()) {

                ids.append("," + rs.getString(1));

            }
            s.append(ids.toString());
        }

        return s.toString();
    }


    public synchronized String getLoginId(DataBase db,String loginName) {
    	 String s = "-1";
         ResultSet rs = null;

         try {
        	 System.out.println("select accountid from account  where accountname='" +loginName +"'");
			rs = db.queryAll("select accountid from account  where accountname='" +loginName +"'");
			if (rs.next()) {
			       s=rs.getString(1);
			 }
         } catch (DbException e) {
			e.printStackTrace();
		 }catch (SQLException e) {
			e.printStackTrace();
		 }
       
         return  s.toString();
    }
    public synchronized String getQueryStr_zd_temp(String fzzdid,
            String querystr) {
        StringBuffer s = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        s2.append("select z.id,z.jzname");
        s2.append(",(case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian) else '' end) as xianname");
        s2.append(",(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu) else '' end) as xiaoquname");
        s2.append(
                ",z.shi,z.xian,z.xiaoqu from zhandian z where ");

        s2.append(" z.id in(" + fzzdid + ")");

        s.append("select * from (");
        s.append(querystr);
        s.append(" union ");
        s.append(s2.toString());
        s.append(" ) order by shi,xian,xiaoqu,jzname");
        System.out.println("站点完整语句：" + s.toString());
        return s.toString();
    }

    public synchronized String getQueryStr_zd_all(String fzzdid,
                                                  String querystr) {
        StringBuffer s = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        s2.append("select z.id from zhandian z where ");

        s2.append(" z.id in(" + fzzdid + ")");

        s.append("select count(*) from (");
        s.append(querystr);
        s.append(" union ");
        s.append(s2.toString());
        s.append(" ) ");
        System.out.println("站点完整语句：" + s.toString());
        return s.toString();
    }


}
