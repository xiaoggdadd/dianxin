package com.noki.mobi.sys.javabean;

import java.util.ArrayList;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class RoleBean {
    public RoleBean() {
    }

    /**
     * 添加角色信息
     * @param name String
     * @param memo String
     * @return String
     */
    public synchronized String addRole(String name, String memo,String fenzu) {
        String msg = "添加角色信息失败！";
        DataBase db = new DataBase();
        String sql = "INSERT INTO ROLE(NAME,MEMO,FIXED,FENZU) VALUES('" + name +
                     "','" +
                     memo + "','0','"+fenzu+"')";
        try {
            db.connectDb();
            if (sameName(name, db)) {
                db.update(sql);
                return "添加角色信息成功！";
            } else {
                return "角色名称 " + name + " 已经存在，请另选择一个名称！";
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

        return msg;
    }

    /**
     * 返回所有role
     * sign==0 返回所有角色
     * sign==1 不包含管理员权限角色
     * 都不包括超级管理员 roleid=1
     * @param sign int
     * @return ArrayList
     */

    public synchronized ArrayList getAllRole(int sign) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        String sql = "";
       // sql +="SELECT R.ROLEID,R.NAME,R.MEMO,R.FIXED,F.NAME  AS FZNAME,R.FENZU FROM ROLE R,FENZU F WHERE R.FENZU=F.ID ORDER BY ROLEID";
        /*
             if (sign == 0) {
               sql += "SELECT ROLEID,NAME,MEMO,FIXED FROM ROLE WHERE ROLEID NOT IN(4,5,6) ORDER BY ROLEID";//不包括促销员与业务员
             }
             else if (sign == 1) {
               sql +=
         "SELECT ROLEID,NAME,MEMO,FIXED FROM ROLE WHERE ROLEID!=1 ORDER BY ROLEID";
             }
         */
        if (sign == 0) {
        	sql +="SELECT R.ROLEID,R.NAME,R.MEMO,R.FIXED,F.NAME  AS FZNAME,R.FENZU FROM ROLE R,FENZU F " +
        		  "WHERE R.FENZU=F.ID  ORDER BY ROLEID";
        }else{
        	sql +="SELECT R.ROLEID,R.NAME,R.MEMO,R.FIXED,F.NAME  AS FZNAME,R.FENZU FROM ROLE R,FENZU F " +
  		          "WHERE R.FENZU=F.ID AND R.FIXED=0 ORDER BY ROLEID";
        }
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql);
            ResultSetMetaData rmd = rs.getMetaData();
            int count = rmd.getColumnCount();
            list.add(new Integer(count));
            for (int i = 1; i <= count; i++) {
                list.add(rmd.getColumnName(i).toUpperCase());
            } while (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    list.add(rs.getString(i));
                }
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

        return list;
    }

    /**
     * 返回所有role
     * sign==0 返回所有角色
     * sign==1 不包含管理员权限角色
     * 都不包括超级管理员 roleid=1
     * @param sign int
     * @return ArrayList
     */

    public synchronized ArrayList getAllRoles(String actionId) {
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer();
        sql.setLength(0);
        sql.append("SELECT R.ROLEID,R.NAME,R.MEMO,R.FIXED,R.FENZU ");
        sql.append(
            ",CASE WHEN R.ROLEID IN (SELECT S.ROLE_ID FROM S_ACTION_ROLE S WHERE S.ACTION_ID=" +
            actionId + ") THEN '1' ELSE '0' END CHECKED ");
        sql.append(
            " FROM ROLE R  ORDER BY R.ROLEID");
        DataBase db = new DataBase();

        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll(sql.toString());
            ResultSetMetaData rmd = rs.getMetaData();
            int count = rmd.getColumnCount();
            list.add(new Integer(count));
            for (int i = 1; i <= count; i++) {
                list.add(rmd.getColumnName(i).toUpperCase());
            } while (rs.next()) {
                for (int i = 1; i <= count; i++) {
                    list.add(rs.getString(i));
                }
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

        return list;
    }
    
    /**
     * 删除角色
     * @param roleId String[]
     * @return String
     */
    public synchronized String delRoles(String[] roleId) {
        String msg = "", msg2 = "";
        int len = roleId.length;

        DataBase db = new DataBase();
        try {
            db.connectDb();

            int k = 0;
            for (int i = 0; i < len; i++) {
                if (validateDelRole(roleId[i], db)) {
                    msg2 += getRoleName(roleId[i], db) + ", ";
                    roleId[i] = "0";
                    k++;
                }
            }
            String[] sql = new String[len - k];
            int j = 0;
            if (k < len) {
                if (k == 0) {
                    for (int i = 0; i < len; i++) {
                        sql[i] = "DELETE FROM ROLE WHERE ROLEID=" + roleId[i];
                    }
                    db.updateBatch(sql);
                    return "您选择的角色已经全部删除！";
                }
                if (k > 0) {
                    for (int i = 0; i < len; i++) {
                        if (!roleId[i].equals("0")) {
                            sql[j++] = "DELETE FROM ROLE WHERE ROLEID=" +
                                       roleId[i];
                        }
                    }
                    db.updateBatch(sql);
                    return "您选择的角色中, 角色 " + msg2 + " 有关联关系不能删除，其余的已经删除！";

                }
            } else {
                return "您选择删除的角色有关联的账户，不能删除！";
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

        return msg;
    }

    /**
     * 判断角色有没有关联的账户
     * @param roleId String
     * @param db DataBase
     * @return boolean
     */
    private boolean validateDelRole(String roleId, DataBase db) {
        boolean sign = false;
        String sql = "SELECT ACCOUNTID FROM ACCOUNT WHERE ','||ROLEID||',' like  '%,"+roleId+",%'";
        System.out.println(sql);
        ResultSet rs = null;
        try {
            rs = db.queryAll(sql);
            if (rs.next()) {
                sign = true;
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (DbException de) {
            de.printStackTrace();
        }
        return sign;
    }

    /**
     * 内部方法，根据角色id返回角色名称
     * @param roleId String
     * @param db DataBase
     * @return String
     */
    private String getRoleName(String roleId, DataBase db) {
        String name = "";
        ResultSet rs = null;
        try {
            rs = db.queryAll("SELECT NAME FROM ROLE WHERE ROLEID=" + roleId);
            while (rs.next()) {
                return rs.getString(1);
            }
            rs.close();
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return name;
    }

    /**
     * 修改角色信息
     * @param roleId String
     * @param name String
     * @param memo String
     * @return String
     */
    public synchronized String modifyRole(String roleId, String name,
                                          String memo,String fenzu) {
        String msg = "修改角色信息失败！";
        String sql = "UPDATE ROLE SET NAME='" + name + "',MEMO='" + memo +
                     "',fenzu='"+fenzu+"' WHERE ROLEID=" + roleId;
        System.out.println(sql);
        DataBase db = new DataBase();
        try {
            db.connectDb();
            if (sameName(name, db,roleId)) {
                db.update(sql);
                return "修改角色信息成功！";
            } else {
                return "角色名称 " + name + " 已经存在，请另选择一个名称！";
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

        return msg;
    }

    /**
     * 判断新角色名称是否已存在
     * @param name String
     * @return boolean
     */
    private boolean sameName(String name, DataBase db,String roleid) {
        boolean sign = true;
        String sql = "SELECT ROLEID FROM ROLE WHERE NAME='" + name + "' and roleid!="+roleid;
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return false;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return sign;
    }
    private boolean sameName(String name, DataBase db) {
        boolean sign = true;
        String sql = "SELECT ROLEID FROM ROLE WHERE NAME='" + name + "'";
        try {
            ResultSet rs = db.queryAll(sql);
            while (rs.next()) {
                return false;
            }
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return sign;
    }


    public static String getRoleName(String roleId) {
        String name = "";
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll("SELECT NAME,memo FROM ROLE WHERE ROLEID=" + roleId);
            while (rs.next()) {
                return rs.getString(1)+"AA"+(rs.getString(2)!=null?rs.getString(2):"");
            }
            rs.close();
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

        return name;
    }


}
