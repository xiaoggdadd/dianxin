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
     * 添加流程信息
     * @param name String
     * @param flowDesc String
     * @return String
     */
    public synchronized String addFlow(String name, String flowType, String flowDesc) {
        String msg = "添加流程信息失败！";
        DataBase db = new DataBase();
        String sql = "INSERT INTO S_FLOW_INFO(FLOW_ID,FLOW_NAME,FLOW_TYPES,FLOW_DESC) VALUES(seq_flow.nextval,'" + name +
                     "','" + flowType +
                     "','" +
                     flowDesc +"')";
        try {
            db.connectDb();
            if (sameName(name, db)) {
                db.update(sql);
                return "添加流程信息成功！";
            } else {
                return "流程名称 " + name + " 已经存在，请另选择一个名称！";
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
     * 添加流程步骤信息
     * @param name String
     * @param flowDesc String
     * @return String
     */
    public synchronized String addStep(String flowId, String actionId,int no) {
        String msg = "添加流程步骤信息失败！";
        DataBase db = new DataBase();
        String sql = "INSERT INTO S_STEP_INFO(STEP_ID,FLOW_ID,ACTION_ID,STEP_ORDER_NO) VALUES(seq_flow.nextval,'" + flowId +
                     "','" +
                     actionId +"','" +
                     no +"')";
        try {
            db.connectDb();
            db.update(sql);
            return "添加流程步骤信息成功！";
           

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
     *附件管理使用的流程选择
     */
    @SuppressWarnings("unchecked")
	public synchronized ArrayList getAllFlowsfujian(String num) {
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
     * 返回所有flow
     * 返回所有流程
     * @return ArrayList修改：根据条件查询
     */

    @SuppressWarnings("unchecked")
	public synchronized ArrayList getAllFlows(String num,String name) {
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
               sql += "SELECT ROLEID,NAME,MEMO,FIXED FROM ROLE WHERE ROLEID NOT IN(4,5,6) ORDER BY ROLEID";//不包括促销员与业务员
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
  
    //查询页面显示
    public synchronized ArrayList getAllFlow() {
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
     * 通过流程id获取所有步骤
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
     * 删除流程步骤
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
     * 删除流程
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
                    return "您选择的流程已经全部删除！";
                }
                if (k > 0) {
                    for (int i = 0; i < len; i++) {
                        if (!flowId[i].equals("0")) {
                            sql[j++] = "DELETE FROM S_FLOW_INFO WHERE FLOW_ID=" +
                            flowId[i];
                        }
                    }
                    db.updateBatch(sql);
                    return "您选择的流程中, 角色 " + msg2 + " 有关联关系不能删除，其余的已经删除！";

                }
            } else {
                return "您选择删除的流程有关联的步骤，不能删除！";
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
     * 判断流程有没有关联的步骤
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
     * 内部方法，根据流程id返回流程名称
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
     * 修改流程信息
     * @param flowId String
     * @param name String
     * @param flowDesc String
     * @return String
     */
    public synchronized String modifyFlow(String flowId, String name,String flowTypes,
                                          String flowDesc) {
        String msg = "修改流程信息失败！";
        String sql = "UPDATE S_FLOW_INFO SET FLOW_NAME='" + name + "',FLOW_DESC='" + flowDesc +
         "',FLOW_TYPES='" + flowTypes +
                     "' WHERE FLOW_ID=" + flowId;
        System.out.println(sql);
        DataBase db = new DataBase();
        try {
            db.connectDb();
            if (sameName(name, db,flowId)) {
                db.update(sql);
                return "修改流程信息成功！";
            } else {
                return "流程名称 " + name + " 已经存在，请另选择一个名称！";
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
     * 判断新流程名称是否已存在
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
	 * 根据业务类型查询对应流程
	 * @param flowtype 业务类型
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
	 * 获取步骤及部门审核人
	 * @param flowtype 流程业务类型
	 * @param dept 登录用户部门
	 * @param step 步骤顺序号
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
		System.out.println("电表审核-步骤查询 ss"+ sql);
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
	 * 获取步骤及部门审核人
	 * @param flowid 流程业务id
	 * @param dept 登录用户部门
	 * @param step 步骤顺序号
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
		System.out.println("电表审核-步骤查询11"+ sql);
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
	 * 修改审批（分成营业、办公、生产）
	 * @param flowid
	 * @param step
	 * @param ydsxx
	 * @return
	 */
	public synchronized ArrayList getActionByFlowid(String flowid, String step,String roleName){
		System.out.println("进入修改之后的审批流程！！！");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.action_id actionid, a.action_name actionname, f.flow_id flowid, f.flow_name flowname, r.role_id roleid from s_action_info a " +
				"left join s_step_info s on a.action_id = s.action_id " +
				"left join s_flow_info f on f.flow_id = s.flow_id " +
				"left join s_action_role r on a.action_id = r.action_id " +" left join role ro on ro.roleid = r.role_id "+
				"where f.flow_id='"+flowid+"' and s.step_order_no='"+step+"'" + " and ro.name like'"+roleName+"'");
		System.out.println("电表审核-步骤查询3 "+ sql);
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
	 * 获取流程步骤执行人
	 * @param deptNo 部门编码
	 * @param agcode 地市
	 * @param role 用户角色
	 * @return
	 */
	public synchronized ArrayList getAccountByDeptNoAndRoleId(String deptNo, String agcode, String roleId){
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("select a.accountid, a.name from account a left " +
				"join per_area p on a.accountid = p.accountid " +
				"where a.delsign=1 and p.agcode = '"+agcode+"' and a.bumen='"+deptNo+"' and instr(a.roleid, '"+roleId+"')>0 ");
		System.out.println("电表审核-执行人查询 3"+ sql);
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
		System.out.println("电表审核-执行人查询 2"+ sql);
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
	 * 查询流程步骤角色
	 * @param stepId
	 * @param deptNo 部门
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
		System.out.println("审核流程-角色查询 "+ sql);
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
	 *  查询流程步骤执行人
	 * @param stepId 
	 * @param agcode 地市
	 * @param rolename 角色名称
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
		System.out.println("审核流程-执行人查询 "+ sql);
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
	 * 根据工作流程当前执行人查询下一个执行人（备用）
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
		System.out.println("电表审核-执行人查询 "+ sql);
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
