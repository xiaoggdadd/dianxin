package com.noki.mobi.flow.javabean;

import java.util.ArrayList;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import com.noki.database.DbException;

public class StepBean {
    public StepBean() {
    }

    /**
     * 给步骤分配角色
     * @param roleId String
     * @param rightId String[]
     * @return String
     */
    public synchronized String matchActionRole(String actionId, String[] roleId,String rws) {
      String msg = "给步骤分配角色失败！";
      String del = "DELETE FROM S_ACTION_ROLE WHERE ACTION_ID=" + actionId;

      DataBase db = new DataBase();
      try {
        db.connectDb();
        msg = "给步骤 " + getActionName(actionId,db) + " 分配角色失败";
        db.setAutoCommit(false);
        db.update(del);
        System.out.println("actionId:"+actionId+" roleId:"+roleId);
        if (roleId != null) {
          String[] sql = new String[roleId.length];
          for (int i = 0; i < roleId.length; i++) {
            sql[i] =
                "INSERT INTO S_ACTION_ROLE(ACTION_ROLE_ID,ACTION_ID,ROLE_ID) VALUES(SEQ_STEP_ACTION.nextval," +
                 actionId + "," + roleId[i] + ")";
           System.out.println("功能："+roleId[i]);
           System.out.println("sql："+sql[i]);
          }
        
          
          db.updateBatch(sql);
          System.out.println("分配功能权限sql:"+sql.toString());
        }
        db.commit();
        msg = "给步骤 " + getActionName(actionId,db) + " 分配角色成功！";
      }
      catch (DbException de) {
        try {
          db.rollback();
        }
        catch (DbException dee) {
          dee.printStackTrace();
        }

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

      return msg;
    }
    
    /**
     * 添加步骤信息
     * @param name String
     * @param actionDesc String
     * @return String
     */
    public synchronized String addAction(String name, String actionDesc) {
        String msg = "添加步骤信息失败！";
        DataBase db = new DataBase();
        String sql = "INSERT INTO S_ACTION_INFO(ACTION_ID,ACTION_NAME,ACTION_DESC) VALUES(seq_action.nextval,'" + name +
                     "','" +
                     actionDesc +"')";
        try {
            db.connectDb();
            if (sameName(name, db)) {
                db.update(sql);
                return "添加步骤信息成功！";
            } else {
                return "步骤名称 " + name + " 已经存在，请另选择一个名称！";
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
     * 返回所有step
     * 返回所有步骤
     * @return ArrayList
     */

    public synchronized ArrayList getAllAction() {
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
        sql +="SELECT F.ACTION_ID AS actionId,F.ACTION_NAME AS actionNAME,F.ACTION_DESC AS actionDesc FROM S_ACTION_INFO F " +
		  "ORDER BY F.ACTION_ID";
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
     * 删除步骤
     * @param actionId String[]
     * @return String
     */
    public synchronized String delActions(String[] actionId) {
        String msg = "", msg2 = "";
        int len = actionId.length;
        DataBase db = new DataBase();
        try {
            db.connectDb();
            int k = 0;
            for (int i = 0; i < len; i++) {
                if (validateDelAction(actionId[i], db)) {
                    msg2 += getActionName(actionId[i], db) + ", ";
                    actionId[i] = "0";
                    k++;
                }
            }
            String[] sql = new String[len - k];
            int j = 0;
            if (k < len) {
                if (k == 0) {
                    for (int i = 0; i < len; i++) {
                        sql[i] = "DELETE FROM S_ACTION_INFO WHERE ACTION_ID=" + actionId[i];
                    }
                    db.updateBatch(sql);
                    return "您选择的步骤已经全部删除！";
                }
                if (k > 0) {
                    for (int i = 0; i < len; i++) {
                        if (!actionId[i].equals("0")) {
                            sql[j++] = "DELETE FROM S_ACTION_INFO WHERE ACTION_ID=" +
                            actionId[i];
                        }
                    }
                    db.updateBatch(sql);
                    return "您选择的步骤中, 角色 " + msg2 + " 有关联关系不能删除，其余的已经删除！";
                }
            } else {
                return "您选择删除的步骤有关联的角色，不能删除！";
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
     * 判断步骤有没有关联的角色
     * @param actionId String
     * @param db DataBase
     * @return boolean
     */
    private boolean validateDelAction(String actionId, DataBase db) {
        boolean sign = false;
        String sql = "SELECT ACTION_ROLE_ID FROM S_ACTION_ROLE WHERE ','||ACTION_ID||',' like  '%,"+actionId+",%'";
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
     * 内部方法，根据步骤id返回步骤名称
     * @param actionId String
     * @param db DataBase
     * @return String
     */
    private String getActionName(String actionId, DataBase db) {
        String name = "";
        ResultSet rs = null;
        try {
            rs = db.queryAll("SELECT ACTION_NAME FROM S_ACTION_INFO WHERE ACTION_ID=" + actionId);
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
     * 修改步骤信息
     * @param actionId String
     * @param name String
     * @param actionDesc String
     * @return String
     */
    public synchronized String modifyAction(String actionId, String name,
                                          String actionDesc) {
        String msg = "修改步骤信息失败！";
        String sql = "UPDATE S_ACTION_INFO SET ACTION_NAME='" + name + "',ACTION_DESC='" + actionDesc +
                     "' WHERE ACTION_ID=" + actionId;
        System.out.println(sql);
        DataBase db = new DataBase();
        try {
            db.connectDb();
            if (sameName(name, db,actionId)) {
                db.update(sql);
                return "修改步骤信息成功！";
            } else {
                return "步骤名称 " + name + " 已经存在，请另选择一个名称！";
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
     * 判断新步骤名称是否已存在
     * @param name String
     * @return boolean
     */
    private boolean sameName(String name, DataBase db,String actionId) {
        boolean sign = true;
        String sql = "SELECT ACTION_ID FROM S_ACTION_INFO WHERE ACTION_NAME='" + name + "' and ACTION_ID!="+actionId;
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
        String sql = "SELECT ACTION_ID FROM S_ACTION_INFO WHERE ACTION_NAME='" + name + "'";
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


    public static String getActionName(String actionId) {
        String name = "";
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll("SELECT ACTION_NAME,ACTION_DESC FROM S_ACTION_INFO WHERE ACTION_ID=" + actionId);
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
