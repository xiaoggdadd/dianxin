package com.noki.mobi.flow.javabean;

import java.util.ArrayList;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.util.Query;

public class FlowBean {
    public FlowBean() {
    }

    /**
     * ���������Ϣ
     * @param name String
     * @param flowDesc String
     * @return String
     */
    public synchronized String addFlow(String name, String flowType, String flowDesc) {
        String msg = "���������Ϣʧ�ܣ�";
        DataBase db = new DataBase();
        String sql = "INSERT INTO S_FLOW_INFO(FLOW_ID,FLOW_NAME,FLOW_TYPES,FLOW_DESC) VALUES(seq_flow.nextval,'" + name +
                     "','" + flowType +
                     "','" +
                     flowDesc +"')";
        try {
            db.connectDb();
            if (sameName(name, db)) {
                db.update(sql);
                return "���������Ϣ�ɹ���";
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
     * ������̲�����Ϣ
     * @param name String
     * @param flowDesc String
     * @return String
     */
    public synchronized String addStep(String flowId, String actionId,int no) {
        String msg = "������̲�����Ϣʧ�ܣ�";
        DataBase db = new DataBase();
        String sql = "INSERT INTO S_STEP_INFO(STEP_ID,FLOW_ID,ACTION_ID,STEP_ORDER_NO) VALUES(seq_flow.nextval,'" + flowId +
                     "','" +
                     actionId +"','" +
                     no +"')";
        try {
            db.connectDb();
            db.update(sql);
            return "������̲�����Ϣ�ɹ���";
           

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
     *��������ʹ�õ�����ѡ��
     */
    @SuppressWarnings("unchecked")
	public synchronized ArrayList getAllFlowsfujian(String num) {
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
        sql +="SELECT F.FLOW_ID AS FLOWID,F.FLOW_NAME AS FLOWNAME,F.FLOW_DESC AS FLOWDESC FROM S_FLOW_INFO F " +
		  "where F.FLOW_TYPES="+num+"";
       System.out.println("sss"+sql);
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
     * ��������flow
     * ������������
     * @return ArrayList�޸ģ�����������ѯ
     */

    @SuppressWarnings("unchecked")
	public synchronized ArrayList getAllFlows(String num,String name) {
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
        sql +="SELECT F.FLOW_ID AS FLOWID,F.FLOW_NAME AS FLOWNAME,F.FLOW_DESC AS FLOWDESC FROM S_FLOW_INFO F " +
		  "where F.FLOW_TYPES="+num+"  and F.FLOW_NAME  like '"+name+"' ORDER BY F.FLOW_ID";
       System.out.println("sss"+sql);
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
    public synchronized ArrayList getAllFlows2(String num,String name) {
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
        sql +="SELECT F.FLOW_ID AS FLOWID,F.FLOW_NAME AS FLOWNAME,F.FLOW_DESC AS FLOWDESC FROM S_FLOW_INFO F " +
		  "where F.FLOW_TYPES="+num+"  and F.FLOW_NAME not like '"+name+"' ORDER BY F.FLOW_ID";
     
        System.out.println("sss2"+sql);
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
  
    //��ѯҳ����ʾ
    public synchronized ArrayList getAllFlow() {
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
        sql +="SELECT F.FLOW_ID AS FLOWID,F.FLOW_NAME AS FLOWNAME,F.FLOW_DESC AS FLOWDESC FROM S_FLOW_INFO F " +"ORDER BY F.FLOW_ID";
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
     * ͨ������id��ȡ���в���
     * @param FlowId
     * @return
     */
    public static ArrayList getStepByFlowId(String flowId) {
        ArrayList list = new ArrayList();
        DataBase db = new DataBase();
        String sql = "";
        sql +="SELECT S.STEP_ORDER_NO,A.ACTION_NAME AS ACTIONNAME,F.FLOW_NAME AS FLOWNAME,F.FLOW_DESC AS FLOWDESC" +
          " FROM S_STEP_INFO S "+
		  "LEFT JOIN S_ACTION_INFO A ON S.ACTION_ID=A.ACTION_ID " +
		  "LEFT JOIN S_FLOW_INFO F ON S.FLOW_ID=F.FLOW_ID  " +
		  "WHERE S.FLOW_ID ="+flowId +
		  "ORDER BY S.STEP_ORDER_NO";
        ResultSet rs = null;
        System.out.println(sql);
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
     * ɾ�����̲���
     * @param flowId String
     * @return String
     */
    public synchronized String delFlowSteps(String flowId) {
        String msg = "";
        DataBase db = new DataBase();
        try {
            db.connectDb();
            String sql = "";
            sql+="DELETE FROM S_STEP_INFO WHERE FLOW_ID="+flowId;
            db.update(sql);
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
     * ɾ������
     * @param flowId String[]
     * @return String
     */
    public synchronized String delFlows(String[] flowId) {
        String msg = "", msg2 = "";
        int len = flowId.length;

        DataBase db = new DataBase();
        try {
            db.connectDb();

            int k = 0;
            for (int i = 0; i < len; i++) {
                if (validateDelFlow(flowId[i], db)) {
                    msg2 += getFlowName(flowId[i], db) + ", ";
                    flowId[i] = "0";
                    k++;
                }
            }
            String[] sql = new String[len - k];
            int j = 0;
            if (k < len) {
                if (k == 0) {
                    for (int i = 0; i < len; i++) {
                        sql[i] = "DELETE FROM S_FLOW_INFO WHERE FLOW_ID=" + flowId[i];
                    }
                    db.updateBatch(sql);
                    return "��ѡ��������Ѿ�ȫ��ɾ����";
                }
                if (k > 0) {
                    for (int i = 0; i < len; i++) {
                        if (!flowId[i].equals("0")) {
                            sql[j++] = "DELETE FROM S_FLOW_INFO WHERE FLOW_ID=" +
                            flowId[i];
                        }
                    }
                    db.updateBatch(sql);
                    return "��ѡ���������, ��ɫ " + msg2 + " �й�����ϵ����ɾ����������Ѿ�ɾ����";

                }
            } else {
                return "��ѡ��ɾ���������й����Ĳ��裬����ɾ����";
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
     * �ж�������û�й����Ĳ���
     * @param flowId String
     * @param db DataBase
     * @return boolean
     */
    private boolean validateDelFlow(String flowId, DataBase db) {
        boolean sign = false;
        String sql = "SELECT STEP_ID FROM S_STEP_INFO WHERE ','||FLOW_ID||',' like  '%,"+flowId+",%'";
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
     * �ڲ���������������id������������
     * @param flowId String
     * @param db DataBase
     * @return String
     */
    private String getFlowName(String flowId, DataBase db) {
        String name = "";
        ResultSet rs = null;
        try {
            rs = db.queryAll("SELECT FLOW_NAME FROM S_FLOW_INFO WHERE FLOW_ID=" + flowId);
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
     * �޸�������Ϣ
     * @param flowId String
     * @param name String
     * @param flowDesc String
     * @return String
     */
    public synchronized String modifyFlow(String flowId, String name,String flowTypes,
                                          String flowDesc) {
        String msg = "�޸�������Ϣʧ�ܣ�";
        String sql = "UPDATE S_FLOW_INFO SET FLOW_NAME='" + name + "',FLOW_DESC='" + flowDesc +
         "',FLOW_TYPES='" + flowTypes +
                     "' WHERE FLOW_ID=" + flowId;
        System.out.println(sql);
        DataBase db = new DataBase();
        try {
            db.connectDb();
            if (sameName(name, db,flowId)) {
                db.update(sql);
                return "�޸�������Ϣ�ɹ���";
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
     * �ж������������Ƿ��Ѵ���
     * @param name String
     * @return boolean
     */
    private boolean sameName(String name, DataBase db,String flowid) {
        boolean sign = true;
        String sql = "SELECT FLOW_ID FROM S_FLOW_INFO WHERE FLOW_NAME='" + name + "' and FLOW_ID!="+flowid;
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
        String sql = "SELECT FLOW_ID FROM S_FLOW_INFO WHERE FLOW_NAME='" + name + "'";
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


    public static String getFlowName(String flowId) {
        String name = "";
        DataBase db = new DataBase();
        ResultSet rs = null;
        try {
            db.connectDb();
            rs = db.queryAll("SELECT FLOW_NAME,FLOW_DESC,FLOW_TYPES FROM S_FLOW_INFO WHERE FLOW_ID=" + flowId);
           System.out.println("iiii"+rs);
            while (rs.next()) {
                return rs.getString(1)+"AA"+(rs.getString(2)!=null?rs.getString(2):"")+"BB"+rs.getString(3);
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

    /**
	 * ����ҵ�����Ͳ�ѯ��Ӧ����
	 * @param flowtype ҵ������
	 * @return
	 */
	public synchronized ArrayList getFlow(String flowtype){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select flow_id flowid, flow_name flowname from s_flow_info where flow_type='"+flowtype+"'");
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * ��ȡ���輰���������
	 * @param flowtype ����ҵ������
	 * @param dept ��¼�û�����
	 * @param step ����˳���
	 * @return
	 */
	public synchronized ArrayList getAction(String flowtype, String step){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.action_id actionid, a.action_name actionname, f.flow_id flowid, f.flow_name flowname, r.role_id roleid from s_action_info a " +
				"left join s_step_info s on a.action_id = s.action_id " +
				"left join s_flow_info f on f.flow_id = s.flow_id " +
				"left join s_action_role r on a.action_id = r.action_id " +
				"where f.flow_type='"+flowtype+"' and s.step_order_no='"+step+"'");
		System.out.println("������-�����ѯ ss"+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ��ȡ���輰���������
	 * @param flowid ����ҵ��id
	 * @param dept ��¼�û�����
	 * @param step ����˳���
	 * @return
	 */
	public synchronized ArrayList getActionByFlowid(String flowid, String step){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.action_id actionid, a.action_name actionname, f.flow_id flowid, f.flow_name flowname, r.role_id roleid from s_action_info a " +
				"left join s_step_info s on a.action_id = s.action_id " +
				"left join s_flow_info f on f.flow_id = s.flow_id " +
				"left join s_action_role r on a.action_id = r.action_id " +
				"where f.flow_id='"+flowid+"' and s.step_order_no='"+step+"'");
		System.out.println("������-�����ѯ11"+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * �޸��������ֳ�Ӫҵ���칫��������
	 * @param flowid
	 * @param step
	 * @param ydsxx
	 * @return
	 */
	public synchronized ArrayList getActionByFlowid(String flowid, String step,String roleName){
		System.out.println("�����޸�֮����������̣�����");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.action_id actionid, a.action_name actionname, f.flow_id flowid, f.flow_name flowname, r.role_id roleid from s_action_info a " +
				"left join s_step_info s on a.action_id = s.action_id " +
				"left join s_flow_info f on f.flow_id = s.flow_id " +
				"left join s_action_role r on a.action_id = r.action_id " +" left join role ro on ro.roleid = r.role_id "+
				"where f.flow_id='"+flowid+"' and s.step_order_no='"+step+"'" + " and ro.name like'"+roleName+"'");
		System.out.println("������-�����ѯ3 "+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * ��ȡ���̲���ִ����
	 * @param deptNo ���ű���
	 * @param agcode ����
	 * @param role �û���ɫ
	 * @return
	 */
	public synchronized ArrayList getAccountByDeptNoAndRoleId(String deptNo, String agcode, String roleId){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.accountid, a.name from account a left " +
				"join per_area p on a.accountid = p.accountid " +
				"where a.delsign=1 and p.agcode = '"+agcode+"' and a.bumen='"+deptNo+"' and instr(a.roleid, '"+roleId+"')>0 ");
		System.out.println("������-ִ���˲�ѯ 3"+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public synchronized ArrayList getAccountByDeptNoAndRoleIds(String deptNo, String agcode, String roleName){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.accountid, a.name from account a left " +
				"join per_area p on a.accountid = p.accountid " +
				"where a.delsign=1 and p.agcode = '"+agcode+"' and a.bumen='"+deptNo+"' and a.rolename like'"+roleName+"'");
		System.out.println("������-ִ���˲�ѯ 2"+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ��ѯ���̲����ɫ
	 * @param stepId
	 * @param deptNo ����
	 * @return
	 */
	public synchronized ArrayList getRole(String stepId, String deptNo){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select ac.roleid,ar.role_id roleId, ai.action_id actionid, ai.action_name actionname from s_step_info s  " +
				"left join s_action_role ar on ar.action_id = s.action_id " +
				"left join account ac on ac.roleid like lpad(ar.role_id, 100, '%') or ac.roleid like rpad(ar.role_id, 10, '%')" +
				"left join s_action_info ai on ai.action_id = ar.action_id " +
				"where s.step_id='"+stepId+"' and ac.bumen = '"+deptNo+"' ");
		System.out.println("�������-��ɫ��ѯ "+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 *  ��ѯ���̲���ִ����
	 * @param stepId 
	 * @param agcode ����
	 * @param rolename ��ɫ����
	 * @return
	 */
	public synchronized ArrayList getAccountLikeRolename(String stepId, String agcode, String rolename){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct(ac.accountid), ac.name from s_action_role ar  " +
				"left join account ac on ac.roleid like lpad(ar.role_id, 100, '%') or ac.roleid like rpad(ar.role_id, 10, '%')" +
				"left join per_area p on p.accountid = ac.accountid " +
				"left join s_step_info s on s.action_id = ar.action_id " +
				"where ac.delsign=1 and s.step_id='"+stepId+"' and p.agcode = '"+agcode+"' and ac.rolename like'%"+rolename+"%'");
		System.out.println("�������-ִ���˲�ѯ "+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * ���ݹ������̵�ǰִ���˲�ѯ��һ��ִ���ˣ����ã�
	 * @param accountId
	 * @param taskId
	 * @param taskType
	 * @param agcode
	 * @param rolename
	 * @return
	 */
	public synchronized ArrayList getAccountLikeRolename(String accountId, String taskId, String taskType, String agcode, String rolename){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select ac.accountid, ac.name from s_workflow w  " +
				"left join s_step_info s on s.step_id = w.next_no" +
				"left join s_action_role ar on ar.action_id = s.action_id " +
				"left join account ac on ac.roleid = to_char(ar.role_id) " +
				"left join per_area p on p.accountid = ac.accountid " +
				"where w.auditorid='"+accountId+"' and w.task_id = '"+taskId+"' and w.task_type='"+taskType+"'" +
						" and pa.agcode='"+agcode+"' and ac.rolename like'%"+rolename+"%' ");
		System.out.println("������-ִ���˲�ѯ "+ sql);
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
}
