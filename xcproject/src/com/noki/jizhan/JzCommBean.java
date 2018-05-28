package com.noki.jizhan;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import java.sql.ResultSet;
import com.noki.util.CTime;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
public class JzCommBean {
    public JzCommBean() {
    }

    public synchronized String modifybgStr(String[] id, String bgidstr) {
        //birthday = birthday.length()>0?birthday:null;
        int msg = 0;
        StringBuffer bgn = new StringBuffer();
        StringBuffer bgc = new StringBuffer();
        StringBuffer ids = new StringBuffer("0");
        if(id!=null){
            for(int i = 0;i<id.length;i++){
                ids.append(","+id[i]);
            }
        }
        String retstr = "标杆基站：为进行任何操作";
        StringBuffer sql = new StringBuffer();
        sql.append("");

        //System.out.println(sql.toString());
        DataBase db = new DataBase();
         ResultSet rs=null;
         System.out.println("ids=="+ids.toString());
         System.out.println(id==null);
         System.out.println("bgidstr=="+bgidstr);
        try {
            db.connectDb();
            if (id == null) {
                if (bgidstr.length() == 0) {
                    retstr = "基站标杆：没有进行任何操作";
                } else {
                   rs = db.queryAll(
                            "select jzname,dianfei from zhandian where id in (" +
                            bgidstr.substring(0,bgidstr.length()-1)+")");
                    while (rs.next()) {
                        bgc.append(rs.getString(1)+";");
                    }
                    retstr ="";
                    retstr = "取消标杆的基站是："+bgc.toString();
                }
            }else if (bgidstr.length() == 0) {
                if(id==null){
                    retstr = "基站标杆：没有进行任何操作";
                }else{
                    rs = db.queryAll(
                            "select jzname,dianfei from zhandian where id in (" +
                            ids.toString()+")");
                    while (rs.next()) {
                        bgc.append(rs.getString(1)+";");
                    }
                    retstr ="";
                    retstr = "新增标杆的基站是："+bgc.toString();

                }
            }else if (id != null && bgidstr.length() > 0) {
                retstr ="";
                String sn =","+bgidstr;
                String sc = ","+ids.toString()+",";

                for(int i = 0;i<id.length;i++){
                    if(sn.indexOf(","+id[i]+",")==-1){
                        bgn.append(id[i]+",");//new add
                    }else{
                        System.out.println("id=="+id[i]);
                        sn=sn.replace(","+id[i]+",",",");
                    }
                    System.out.println("sn======="+sn);
                }
                if(bgn.toString().length()>0){
                    System.out.println(bgn.toString());
                    rs = db.queryAll(
                            "select jzname,dianfei from zhandian where id in (" +
                            bgn.toString().substring(0,bgn.toString().length()-1) + ")");
                    bgn.setLength(0);
                    while (rs.next()) {
                        bgn.append(rs.getString(1)+";");
                    }

                    retstr = "新增标杆的基站是：" + bgn.toString();
                }
                if(!sn.equals(",")){
                    sn ="0"+sn+"0";
                    rs = db.queryAll(
                            "select jzname,dianfei from zhandian where id in (" +
                            sn+")");
                    while (rs.next()) {
                        bgc.append(rs.getString(1)+";");
                    }
                    retstr += ";取消标杆的基站是："+bgc.toString();

                }
                if(retstr.length()==0){
                    retstr = "基站标杆：没有进行任何操作";
                }
            }

            msg = 1;
        } catch (SQLException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } catch (DbException de) {
            try {
                db.rollback();
            } catch (DbException dee) {
                dee.printStackTrace();
            }
            de.printStackTrace();
        } finally {
            try {

                db.close();
            } catch (Exception de) {
                de.printStackTrace();
            }
        }

        return retstr;
    }

}
