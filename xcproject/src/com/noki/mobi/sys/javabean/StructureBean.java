package com.noki.mobi.sys.javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.noki.database.DbException;
import com.noki.database.DataBase;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class StructureBean {
    public StructureBean() {
    }

    public synchronized int addStructure(String parentid, String[] dwqc,
                                         String[] dwjc,
                                         String[] des) {
        int sign = 0;
        DataBase db = null;
        ResultSet rs = null;
        int count = dwqc.length;
        try {
            db = new DataBase();
            db.connectDb();
            String parentpath = "", pp2 = "";
            rs = db.queryAlls(
                    "select depth,orderid,parentpath from structure where classid=" +
                    parentid);
            int parentDepth = 0, parentOrderid = 0;
            if (rs.next()) {
                parentDepth = rs.getInt(1);
                parentOrderid = rs.getInt(2);
                parentpath = rs.getString(3);
                pp2 = parentpath + parentid + ",";
            }
            String s =
                    "select max(orderid) from structure where parentpath like '" +
                    pp2 + "%'";
            rs = db.queryAlls(s);

            int maxorder = 0;

            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    maxorder = parentOrderid + 1;
                } else {
                    maxorder = rs.getInt(1) + 1;
                }
            } else {

                maxorder = parentOrderid + 1;
            }
            String[] sql = new String[count];
            db.setAutoCommit(false);
            db.update("update structure set orderid=orderid+" + count +
                      " where orderid>=" + maxorder);
            for (int i = 0; i < count; i++) {
                sql[i] = "insert into structure(classname,full_name,parentid,depth,orderid,department,readme,rootid,parentpath) values('" +
                         dwjc[i] + "','" + dwqc[i] + "'," + parentid + "," +
                         (parentDepth + 1) + "," + (maxorder + i) + ",'" +
                         des[i] + "','',1,'" + pp2 + "')";

                db.update(sql[i]);
            }

            db.commit();
            db.setAutoCommit(true);
            sign = 1;
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }

    public synchronized int delStructure(String id) {
        int sign = 0;
        DataBase db = null;
        ResultSet rs = null;

        try {
            db = new DataBase();
            db.connectDb();
            int k = this.validateDelStruct(id);
            if(k==1){
                db.setAutoCommit(false);
                db.update("update structure set orderid=orderid-1 where orderid>(select orderid from structure where classid=" +
                          id + ")");
                db.update("delete from structure where rootid=2 and classid=" +
                          id);
                sign = db.update("delete from structure where classid=" + id);
                db.commit();
                db.setAutoCommit(true);
            }else{
                return k;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }

    public synchronized int upStructure(String id) {
        int sign = 0;
        DataBase db = null;
        ResultSet rs = null;

        try {
            db = new DataBase();
            db.connectDb();
            String sql = "select orderid,classid,parentpath from structure where parentid=(select parentid from structure where classid=" +
                         id +
                         ") and orderid<(select orderid from structure where classid=" +
                         id + ") order by orderid desc limit 1";

            rs = db.queryAll(sql);
            int pre_classid = 0, classid = 0;
            int pre_orderid = 0, orderid = 0;
            String prepath = "", path = "";
            int premaxorder = 0, preminorder = 0, maxorder = 0, minorder = 0;
            if (rs.next()) {
                pre_orderid = rs.getInt(1);
                pre_classid = rs.getInt(2);
                prepath = rs.getString(3) + "," + pre_classid;
                preminorder = pre_orderid;
                String s2 =
                        "SELECT ORDERID,PARENTPATH FROM STRUCTURE WHERE CLASSID=" +
                        id;
                rs = db.queryAll(s2);
                if (rs.next()) {
                    minorder = rs.getInt(1);
                    path = rs.getString(2) + "," + id;
                }

                String s3 =
                        "select max(orderid) from structure where parentpath like '" +
                        path + "%'";

                rs = db.queryAll(s3);
                if (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        maxorder = minorder;
                    } else {
                        maxorder = rs.getInt(1);
                    }
                    // minorder = rs.getInt(2);
                }

                String s4 =
                        "select max(orderid) from structure where parentpath like '" +
                        prepath + "%'";
                rs = db.queryAll(s4);
                if (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        premaxorder = preminorder;
                    } else {
                        premaxorder = rs.getInt(1);
                    }
                    //preminorder = rs.getInt(2);
                }

                int chae = minorder - preminorder;
                int jia = maxorder - chae - preminorder + 1;

                db.setAutoCommit(false);

                db.update("update structure set orderid=" + pre_orderid +
                          " where classid=" + id);

                db.update(
                        "update structure set orderid=orderid-" + chae +
                        " where parentpath like'" + path + "%'");
                db.update("update structure set orderid=orderid+" + jia +
                          " where classid=" + pre_classid);
                db.update("update structure set orderid =orderid+" + jia +
                          " where parentpath like '" + prepath + "%'");
                db.commit();
                db.setAutoCommit(true);
                sign = 1;
            } else {

            }

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }

    public synchronized int downStructure(String id) {
        int sign = 0;
        DataBase db = null;
        ResultSet rs = null;

        try {
            db = new DataBase();
            db.connectDb();
            String sql = "select orderid,classid,parentpath from structure where parentid=(select parentid from structure where classid=" +
                         id +
                         ") and orderid>(select orderid from structure where classid=" +
                         id + ") order by orderid limit 1";

            rs = db.queryAll(sql);
            int pre_classid = 0, classid = 0;
            int pre_orderid = 0, orderid = 0;
            String prepath = "", path = "";
            int premaxorder = 0, preminorder = 0, maxorder = 0, minorder = 0;
            if (rs.next()) {
                pre_orderid = rs.getInt(1);
                pre_classid = rs.getInt(2);
                prepath = rs.getString(3) + "," + pre_classid;
                preminorder = pre_orderid;
                String s2 =
                        "SELECT ORDERID,PARENTPATH FROM STRUCTURE WHERE CLASSID=" +
                        id;
                rs = db.queryAll(s2);
                if (rs.next()) {
                    minorder = rs.getInt(1);
                    path = rs.getString(2) + "," + id;
                }

                String s3 =
                        "select max(orderid) from structure where parentpath like '" +
                        path + "%'";

                rs = db.queryAll(s3);
                if (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        maxorder = minorder;
                    } else {
                        maxorder = rs.getInt(1);
                    }
                    // minorder = rs.getInt(2);
                }

                String s4 =
                        "select max(orderid) from structure where parentpath like '" +
                        prepath + "%'";
                rs = db.queryAll(s4);
                if (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        premaxorder = preminorder;
                    } else {
                        premaxorder = rs.getInt(1);
                    }
                    //preminorder = rs.getInt(2);
                }

                int chae = preminorder - minorder;
                int jia = premaxorder - chae - minorder + 1;

                db.setAutoCommit(false);

                db.update("update structure set orderid=" + minorder +
                          " where classid=" + pre_classid);
                db.update("update structure set orderid =orderid-" + chae +
                          " where parentpath like '" + prepath + "%'");

                db.update("update structure set orderid=orderid+" + jia +
                          " where classid=" + id);

                db.update(
                        "update structure set orderid=orderid+" + jia +
                        " where parentpath like'" + path + "%'");

                db.commit();
                db.setAutoCommit(true);
                sign = 1;
            } else {

            }

        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;

    }


    public synchronized int modifyStructure(StructureForm form,String tablename) {
        int sign = 0;
        DataBase db = null;

        try {
            db = new DataBase();
            db.connectDb();
            StringBuffer sql = new StringBuffer();
            sql.append("update "+tablename+" set full_name='" + form.getDwqc() +
                       "',classname='" + form.getDwjc() + "',principal='" + form.getFzr() +
                       "',super_no='" + form.getSuperno() + "',phone='" + form.getTel() + "'");
            sql.append(",fax='" + form.getFax() + "',email='" + form.getEmail() + "',address='" +
                       form.getAddress() + "',postalcode='" + form.getZipcode() + "',department='" +
                       form.getJgsz() + "',readme='" + form.getDepinfo() + "'");
            sql.append(" where classid=" + form.getClassid());
            //System.out.println(sql.toString());
            sign = db.update(sql.toString());

        } catch (DbException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }

    public synchronized boolean validateModifyStructName(String classid,String newname) {
        boolean sign = false;
        DataBase db = null;

        try {
            db = new DataBase();
            db.connectDb();
            StringBuffer sql = new StringBuffer();
            sql.append("select classid from structure where classname="+newname+" and classid!="+classid);

             ResultSet rs = db.queryAll(sql.toString());
             if(rs.next()){
                return true;
             }


        } catch (DbException de) {
            de.printStackTrace();
        }catch(SQLException se){
            se.printStackTrace();
        }
        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }

    private synchronized int validateDelStruct(String classid) {
        int sign = 1;
        DataBase db = null;

        try {
            db = new DataBase();
            db.connectDb();
            StringBuffer sql = new StringBuffer();
            sql.append("select classid from structure where parentid="+classid);

             ResultSet rs = db.queryAll(sql.toString());
             if(rs.next()){
                return 2;
             }
             sql.setLength(0);
             sql.append("select accepter_id from accepter where classid="+classid+" limit 1");
             rs = db.queryAll(sql.toString());
             if(rs.next()){
                 return 3;
             }


        } catch (DbException de) {
            de.printStackTrace();
        }catch(SQLException se){
            se.printStackTrace();
        }
        finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }

    public synchronized int addStructure_csbm(String parentid, String[] dwqc) {
            int sign = 0;
            DataBase db = null;
            ResultSet rs = null;
            int count = dwqc.length;
            try {
                db = new DataBase();
                db.connectDb();
                String parentpath = "", pp2 = "";
                rs = db.queryAlls(
                        "select depth,orderid,parentpath from structure_csbm where classid=" +
                        parentid);
                int parentDepth = 0, parentOrderid = 0;
                if (rs.next()) {
                    parentDepth = rs.getInt(1);
                    parentOrderid = rs.getInt(2);
                    parentpath = rs.getString(3);
                    pp2 = parentpath + "," + parentid;
                }
                String s =
                        "select max(orderid) from structure_csbm where parentpath like '" +
                        pp2 + "%'";
                rs = db.queryAlls(s);

                int maxorder = 0;

                if (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        maxorder = parentOrderid + 1;
                    } else {
                        maxorder = rs.getInt(1) + 1;
                    }
                } else {

                    maxorder = parentOrderid + 1;
                }
                String[] sql = new String[count];
                db.setAutoCommit(false);
                db.update("update structure_csbm set orderid=orderid+" + count +
                          " where orderid>=" + maxorder);
                for (int i = 0; i < count; i++) {
                    sql[i] = "insert into structure_csbm(classname,full_name,parentid,depth,orderid,department,readme,rootid,parentpath) values('" +
                             dwqc[i] + "','" + dwqc[i] + "'," + parentid + "," +
                             (parentDepth + 1) + "," + (maxorder + i) + ",'','',1,'" + pp2 + "')";

                    db.update(sql[i]);
                }

                db.commit();
                db.setAutoCommit(true);
                sign = 1;
            } catch (DbException de) {
                de.printStackTrace();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    db.close();
                } catch (DbException de) {
                    de.printStackTrace();
                }
            }
            return sign;
    }

    public synchronized int delStructure_csbm(String id) {
        int sign = 0;
        DataBase db = null;
        ResultSet rs = null;

        try {
            db = new DataBase();
            db.connectDb();
            int k = this.validateDelStruct_csbm(id);
            if(k==1){
                db.setAutoCommit(false);
                db.update("update structure_csbm set orderid=orderid-1 where orderid>(select orderid from structure_csbm where classid=" +
                          id + ")");
                db.update("delete from structure_csbm where rootid=2 and classid=" +
                          id);
                sign = db.update("delete from structure_csbm where classid=" + id);
                db.commit();
                db.setAutoCommit(true);
            }else{
                return k;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } finally {
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }
        }
        return sign;
    }
    private synchronized int validateDelStruct_csbm(String classid) {
            int sign = 1;
            DataBase db = null;

            try {
                db = new DataBase();
                db.connectDb();
                StringBuffer sql = new StringBuffer();
                sql.append("select classid from structure_csbm where parentid="+classid);

                 ResultSet rs = db.queryAll(sql.toString());
                 if(rs.next()){
                    return 2;
                 }



            } catch (DbException de) {
                de.printStackTrace();
            }catch(SQLException se){
                se.printStackTrace();
            }
            finally {
                try {
                    db.close();
                } catch (DbException de) {
                    de.printStackTrace();
                }
            }
            return sign;
    }


}
