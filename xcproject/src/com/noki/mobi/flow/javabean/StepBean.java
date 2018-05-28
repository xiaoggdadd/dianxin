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
     * ����������ɫ
     * @param roleId String
     * @param rightId String[]
     * @return String
     */
    public synchronized String matchActionRole(String actionId, String[] roleId,String rws) {
      String msg = "����������ɫʧ�ܣ�";
      String del = "DELETE FROM S_ACTION_ROLE WHERE ACTION_ID=" + actionId;

      DataBase db = new DataBase();
      try {
        db.connectDb();
        msg = "������ " + getActionName(actionId,db) + " �����ɫʧ��";
        db.setAutoCommit(false);
        db.update(del);
        System.out.println("actionId:"+actionId+" roleId:"+roleId);
        if (roleId != null) {
          String[] sql = new String[roleId.length];
          for (int i = 0; i < roleId.length; i++) {
            sql[i] =
                "INSERT INTO S_ACTION_ROLE(ACTION_ROLE_ID,ACTION_ID,ROLE_ID) VALUES(SEQ_STEP_ACTION.nextval," +
                 actionId + "," + roleId[i] + ")";
           System.out.println("���ܣ�"+roleId[i]);
           System.out.println("sql��"+sql[i]);
          }
        
          
          db.updateBatch(sql);
          System.out.println("���书��Ȩ��sql:"+sql.toString());
        }
        db.commit();
        msg = "������ " + getActionName(actionId,db) + " �����ɫ�ɹ���";
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
     * ��Ӳ�����Ϣ
     * @param name String
     * @param actionDesc String
     * @return String
     */
    public synchronized String addAction(String name, String actionDesc) {
        String msg = "��Ӳ�����Ϣʧ�ܣ�";
        DataBase db = new DataBase();
        String sql = "INSERT INTO S_ACTION_INFO(ACTION_ID,ACTION_NAME,ACTION_DESC) VALUES(seq_action.nextval,'" + name +
                     "','" +
                     actionDesc +"')";
        try {
            db.connectDb();
            if (sameName(name, db)) {
                db.update(sql);
                return "��Ӳ�����Ϣ�ɹ���";
            } else {
                return "�������� " + name + " �Ѿ����ڣ�����ѡ��һ�����ƣ�";
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
     * ��������step
     * �������в���
     * @return ArrayList
     */

    public synchronized ArrayList getAllAction() {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        String sql = "";
       // sql +="SELECT R.ROLEID,R.NAME,R.MEMO,R.FIXED,F.NAME  AS FZNAME,R.FENZU FROM ROLE R,FENZU F WHERE R.FENZU=F.ID ORDER BY ROLEID";
        /*
             if (sign == 0) {
               sql += "SELECT ROLEID,NAME,MEMO,FIXED FROM ROLE WHERE ROLEID NOT IN(4,5,6) ORDER BY ROLEID";//����������Ա��ҵ��Ա
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
     * ɾ������
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
                    return "��ѡ��Ĳ����Ѿ�ȫ��ɾ����";
                }
                if (k > 0) {
                    for (int i = 0; i < len; i++) {
                        if (!actionId[i].equals("0")) {
                            sql[j++] = "DELETE FROM S_ACTION_INFO WHERE ACTION_ID=" +
                            actionId[i];
                        }
                    }
                    db.updateBatch(sql);
                    return "��ѡ��Ĳ�����, ��ɫ " + msg2 + " �й�����ϵ����ɾ����������Ѿ�ɾ����";
                }
            } else {
                return "��ѡ��ɾ���Ĳ����й����Ľ�ɫ������ɾ����";
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
     * �жϲ�����û�й����Ľ�ɫ
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
     * �ڲ����������ݲ���id���ز�������
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
     * �޸Ĳ�����Ϣ
     * @param actionId String
     * @param name String
     * @param actionDesc String
     * @return String
     */
    public synchronized String modifyAction(String actionId, String name,
                                          String actionDesc) {
        String msg = "�޸Ĳ�����Ϣʧ�ܣ�";
        String sql = "UPDATE S_ACTION_INFO SET ACTION_NAME='" + name + "',ACTION_DESC='" + actionDesc +
                     "' WHERE ACTION_ID=" + actionId;
        System.out.println(sql);
        DataBase db = new DataBase();
        try {
            db.connectDb();
            if (sameName(name, db,actionId)) {
                db.update(sql);
                return "�޸Ĳ�����Ϣ�ɹ���";
            } else {
                return "�������� " + name + " �Ѿ����ڣ�����ѡ��һ�����ƣ�";
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
     * �ж��²��������Ƿ��Ѵ���
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
