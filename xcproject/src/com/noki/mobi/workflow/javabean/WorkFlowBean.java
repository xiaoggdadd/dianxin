package com.noki.mobi.workflow.javabean;

import java.util.ArrayList;
import java.util.Date;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class WorkFlowBean {
	public WorkFlowBean() {
	}

	private int allPage;

	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}

	public int getAllPage() {
		return this.allPage;
	}

	/**
	 * 通过用户id返回所有待办任务
	 * 
	 * @param roleId
	 * @return
	 */
	public synchronized ArrayList getAllFlow(String accountId) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		sql += "select w.* ,ai.action_name as actionname,f.flow_name as flowname from s_workflow w "
				+ "left join S_STEP_INFO s on w.current_no = s.step_id "
				+ "left join S_ACTION_INFO ai on ai.action_id =s.action_id "
				+ "left join S_FLOW_INFO f on f.flow_id =w.flow_id "
				+ "where w.auditorid ='" + accountId + "'";
		System.out.println("===" + sql);
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			list.add(new Integer(count));
			for (int i = 1; i <= count; i++) {
				list.add(rmd.getColumnName(i).toUpperCase());
			}
			while (rs.next()) {
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
	 * 通过业务id查询
	 * 
	 * @param roleId
	 * @return
	 */
	public synchronized ArrayList getByTaskId(String taskId) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		sql += "select w.* ,ai.action_name as actionname,ai2.action_name as nextactionname,f.flow_name as flowname from s_workflow w "
				+ "inner join S_STEP_INFO s on w.current_no = s.step_id "
				+ "inner join S_ACTION_INFO ai on ai.action_id =s.action_id "
				+ "inner join S_STEP_INFO s2 on w.NEXT_NO = s2.step_id "
				+ "inner join S_ACTION_INFO ai2 on ai2.action_id =s2.action_id "
				+ "inner join S_FLOW_INFO f on f.flow_id =s.flow_id "
				+ "where w.TASK_ID ='" + taskId + "'";
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			list.add(new Integer(count));
			for (int i = 1; i <= count; i++) {
				list.add(rmd.getColumnName(i).toUpperCase());
			}
			while (rs.next()) {
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
	 * 通过步骤id查询执行人
	 * 
	 * @param stepId
	 * @return
	 */
	public synchronized ArrayList getAccountByActionId(String stepId,
			String taskId, String agcode, String tasktype) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		sql += "select distinct(ac.accountid ),ac.name "
				+ "from account ac,S_ACTION_ROLE ar,S_STEP_INFO s,per_area p  "
				+ "where ac.delsign=1 and ac.roleid like '%' || ar.role_id || '%' "
				+ "and ar.action_id=s.ACTION_ID and '" + agcode
				+ "' in p.AGCODE and ac.accountid=p.accountid  and s.STEP_ID="
				+ stepId + " ";
		System.out.println("getAccountByActionId=" + sql);
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			list.add(new Integer(count));
			for (int i = 1; i <= count; i++) {
				list.add(rmd.getColumnName(i).toUpperCase());
			}
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					list.add(rs.getString(i));
				}
			}
		} catch (Exception de) {
			de.printStackTrace();
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
	 * 通过步骤id查询执行人
	 * 
	 * @param stepId
	 * @return
	 */
	public synchronized ArrayList getAccountByActionId2(String stepId,
			String agcode) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		sql += "select distinct(ac.accountid ),ac.name "
				+ "from account ac,S_ACTION_ROLE ar,S_STEP_INFO s,per_area p  "
				+ "where ac.delsign=1 and ac.roleid in to_char(ar.ROLE_ID) "
				+ "and ar.action_id=s.ACTION_ID and '" + agcode
				+ "' in p.AGCODE and ac.accountid=p.accountid  and s.STEP_ID="
				+ stepId + " ";
		System.out.println("getAccountByActionId2=" + sql);
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			list.add(new Integer(count));
			for (int i = 1; i <= count; i++) {
				list.add(rmd.getColumnName(i).toUpperCase());
			}
			while (rs.next()) {
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
	 * 获取审批记录
	 * 
	 * @param workFlowId
	 * @return
	 */
	public synchronized ArrayList getApproverByWorkFlowId(String workFlowId) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		sql += "select ap.* ,ac.name as accountname from S_APPROVER ap inner join account ac on ac.accountid=ap.auditorid where ap.workflow_id='"
				+ workFlowId + "' order by ap.checktime desc";
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			list.add(new Integer(count));
			for (int i = 1; i <= count; i++) {
				list.add(rmd.getColumnName(i).toUpperCase());
			}
			while (rs.next()) {
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
	 * 添加审核信息
	 * 
	 * @param name
	 *            String
	 * @param flowDesc
	 *            String
	 * @return String
	 */
	public synchronized String addApp(String auditorid, Date checktime,
			String state, String opinion, String stepid, String workflowid) {
		String msg = "添加信息失败！";
		DataBase db = new DataBase();
		String sql = "INSERT INTO S_APPROVER(ID,AUDITORID,CHECKTIME,STATE,OPINION,STEP_ID,WORKFLOW_ID) "
				+ "VALUES(seq_flow.nextval,'"
				+ auditorid
				+ "',sysdate,"
				+ state
				+ ",'"
				+ opinion
				+ "','"
				+ stepid
				+ "','"
				+ workflowid
				+ "')";
		System.out.println(sql);
		try {
			db.connectDb();
			db.update(sql);
			return "添加信息成功！";

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

	public synchronized String getActionName(String stepId) {
		DataBase db = new DataBase();
		String nextStep = "";
		ResultSet rs = null;
		try {
			String sql = "select ACTION_NAME as actionname  from S_ACTION_INFO where ACTION_ID=(select ACTION_ID from S_STEP_INFO where STEP_ID="
					+ stepId + ")";
			rs = db.queryAll(sql);
			while (rs.next()) {
				return rs.getString(1);
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

		return nextStep;
	}

	/**
	 * 添加流程信息
	 * 
	 * @param flowId
	 * @param taskId
	 * @param currentNo
	 * @param nextNo
	 * @param applyuserId
	 * @param taskType
	 * @param auditorId
	 * @return
	 */

	public synchronized int addWorkFlow(String flowId, String taskId,
			String currentNo, String nextNo, String applyuserId,
			String taskType, String auditorId) {
		// String msg = "添加流程信息失败！";
		int ret = 0;
		DataBase db = new DataBase();
		String DBLC_TYPE = "";
		if (taskType.indexOf(",") != -1) {
			String[] type = taskType.split(",");
			taskType = type[0];
			DBLC_TYPE = type[1];
		}

		String sql = "INSERT INTO S_WORKFLOW(ID,FLOW_ID,TASK_ID,CURRENT_NO,NEXT_NO,APPLYUSER_ID,TASK_TYPE,AUDITORID,DBLC_TYPE,APPLYTIME) "
				+ "VALUES(seq_flow.nextval,'"
				+ flowId
				+ "','"
				+ taskId
				+ "','"
				+ currentNo
				+ "','"
				+ nextNo
				+ "','"
				+ applyuserId
				+ "','"
				+ taskType
				+ "','"
				+ auditorId
				+ "','"
				+ DBLC_TYPE
				+ "',sysdate)";
		System.out.println("添加流程信息：" + sql);
		try {
			db.connectDb();
			ret = db.update(sql.toString(), new String[] { "ID" });
			return ret;

		} catch (DbException de) {
			de.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * 获取当前流程的所有步骤
	 * 
	 * @param flowId
	 * @param stepId
	 * @param db
	 * @return
	 */
	public synchronized ArrayList getLastSteps(String workFlowId, String stepId) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		String sql = "";
		sql += "select ss.step_id,sa.action_name actionname from s_step_info ss inner join S_ACTION_INFO sa on ss.action_id=sa.action_id where ss.flow_id='"
				+ workFlowId
				+ "' and ss.step_order_no <(select ss2.step_order_no from s_step_info ss2 where ss2.step_id='"
				+ stepId + "')";
		System.out.println("sql=" + sql);
		ResultSet rs = null;
		try {
			db.connectDb();
			rs = db.queryAll(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int count = rmd.getColumnCount();
			// list.add(new Integer(count));
			// for (int i = 1; i <= count; i++) {
			// list.add(rmd.getColumnName(i).toUpperCase());
			// }
			while (rs.next()) {
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
	 * 获取流程步骤
	 * 
	 * @param flowId
	 * @param stepId
	 * @param db
	 * @return
	 */
	public synchronized String getNextStep(String flowId, String stepNo) {
		DataBase db = new DataBase();
		String nextStep = "";
		ResultSet rs = null;
		try {
			String sql = "select s1.step_id from s_step_info s1 where s1.flow_id='"
					+ flowId + "' and s1.STEP_ORDER_NO='" + stepNo + "'";
			rs = db.queryAll(sql);
			System.out.println("======" + sql);
			while (rs.next()) {
				return rs.getString(1);
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
		return nextStep;
	}

	/**
	 * 获取审核人
	 * 
	 * @param workFlowId
	 * @param stepId
	 * @param db
	 * @return
	 */
	public synchronized String getAuditor(String workFlowId, String stepId,
			DataBase db) {
		String nextStep = "";
		ResultSet rs = null;
		try {
			String sql = "select auditorid from s_approver where workflow_id='"
					+ workFlowId + "' and step_id='" + stepId
					+ "' and state=0 order by checktime desc";

			rs = db.queryAll(sql);
			while (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return nextStep;
	}

	public synchronized String getAuditorName(String accountId, DataBase db) {
		String nextStep = "";
		ResultSet rs = null;
		try {
			String sql = "select name from account where accountid='"
					+ accountId + "' ";

			rs = db.queryAll(sql);
			while (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return nextStep;
	}

	/**
	 * 获取步骤名称
	 * @param stepId
	 * @param db
	 * @return
	 */
	public synchronized String getActionName(String stepId, DataBase db) {
		String actionname = "";
		ResultSet rs = null;
		try {
			String sql = "select t2.action_name from s_step_info t1 left join s_action_info t2 on t1.action_id = t2.action_id " +
					"where t1.step_id='"+stepId+"'";
			
			rs = db.queryAll(sql);
			while (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return actionname;
	}

	/**
	 * 获取步骤序号
	 * 
	 * @param stepId
	 * @param db
	 * @return
	 */
	public synchronized String getStepNo(String stepId, DataBase db) {
		String nextStep = "";
		ResultSet rs = null;
		try {
			String sql = "select step_order_no from s_step_info where step_id='"
					+ stepId + "' ";
			rs = db.queryAll(sql);
			while (rs.next()) {
				return rs.getString(1);
			}
			rs.close();
		} catch (DbException de) {
			de.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return nextStep;
	}

	/**
	 * 通过流程名称获取id
	 * 
	 * @param flowName
	 * @param db
	 * @return
	 */
	public synchronized String getFlowId(String flowName) {
		DataBase db = new DataBase();
		String flowId = "";
		ResultSet rs = null;
		try {
			String sql = "select FLOW_ID from S_FLOW_INFO where FLOW_NAME='"
					+ flowName + "' ";
			rs = db.queryAll(sql);
			while (rs.next()) {
				return rs.getString(1);
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

		return flowId;
	}

	/**
	 * 非铁塔报账审核列表
	 * 
	 * @param
	 * @return
	 */
	public synchronized ArrayList getPageData(int start, String taskType,
			String accountId, String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		DataBase db = new DataBase();
		ResultSet rs = null;
		// StringBuffer strall = new StringBuffer();
		// strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		// strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select w.* ,e.STARTTIME STARTTIME, e.BGPLL,e.ENDTIME ENDTIME,e.ENTRYTIMEOLD,e.SQRJYDL,e.RJYDL,"
					+ "to_char(e.DIANLIANG,'fm9999999990.00') DIANLIANG,to_char(e.ALLMONEY,'fm9999999990.00') ALLMONEY,"
					+ "(select a.name from account a where a.accountid=e.ENTRYPENSONNEL)  ENTRYPENSONNEL,"
					+ "(select z.jzname from zhandian z where z.id=d.zdid) zdname,"
					+ "(select d.dbname  from DIANBIAO d  where d.id = e.dianbiaoid) DBNAME,"
					+ "(select ai.action_name from S_ACTION_INFO ai where s.ACTION_ID=ai.action_id ) currentaction,"
					+ "e.price PRICE,e.BGDL BGDL,"
					+ "(select e2.RJYDL from ELECTRICFEES e2 where "
					+ "e2.dianbiaoid=e.dianbiaoid and e2.CREATEDATE=("
					+ "select  concat(substr(e.CREATEDATE,0, 4)-1,substr(e.CREATEDATE,-3)) "
					+ "from ELECTRICFEES e) and e2.CREATEDATE!=null ) QNRJ,e.PUEZHI PUEZHI "
					+ "from s_workflow w "
					+ "left join ELECTRICFEES e on  w.task_id=e.ELECTRICFEE_ID "
					+ "left join account a on a.accountid = e.ENTRYPENSONNEL "
					+ "left join  dianbiao d on e.dianbiaoid=d.id "
					+ " left join  zhandian z on z.id = d.zdid "

					+ "left join S_STEP_INFO s on  w.current_no=s.STEP_ID  "
					+ "where nvl(d.id,'0') <>'0' and d.ssf='2' and w.task_type='"
					+ taskType
					+ "' and w.auditorid ='"
					+ accountId
					+ "'  "
					+ whereStr
					+ "  ORDER BY e.entrytimeold desc, e.DIANBIAOID,e.STARTTIME");
			System.out.println("非铁塔报账审核：" + s2);
//添加判断条件 dianbiao d.ssf='2' 非铁塔报账审核
			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("非铁塔报账审核查询分页" + s3);
			ResultSet rs3 = null;
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
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
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	/**
	 * 铁塔报账审核列表
	 * 
	 * @param
	 * @return
	 */
	public synchronized ArrayList getTietaPageData(int start, String taskType,
			String accountId, String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		DataBase db = new DataBase();
		ResultSet rs = null;
		// StringBuffer strall = new StringBuffer();
		// strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		// strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select w.* ,e.BGPLL BGPLL,e.STARTTIME STARTTIME,e.ENDTIME ENDTIME,e.ENTRYTIMEOLD,e.SQRJYDL,e.RJYDL,"
					+ "to_char(e.DIANLIANG,'fm9999999990.00') DIANLIANG,to_char(e.ALLMONEY,'fm9999999990.00') ALLMONEY,"
					+ "(select a.name from account a where a.accountid=e.ENTRYPENSONNEL)  ENTRYPENSONNEL,"
					+ "(select z.jzname from zhandian z where z.id=d.zdid) zdname,"
					+ "(select d.dbname  from DIANBIAO d  where d.id = e.dianbiaoid) DBNAME,"
					+ "(select ai.action_name from S_ACTION_INFO ai where s.ACTION_ID=ai.action_id ) currentaction,"
					+ "e.price PRICE,e.BGDL BGDL,"
					+ "(select e2.RJYDL from ELECTRICFEES e2 where "
					+ "e2.dianbiaoid=e.dianbiaoid and e2.CREATEDATE=("
					+ "select  concat(substr(e.CREATEDATE,0, 4)-1,substr(e.CREATEDATE,-3)) "
					+ "from ELECTRICFEES e) and e2.CREATEDATE!=null ) QNRJ,e.PUEZHI PUEZHI "
					+ "from s_workflow w "
					+ "left join ELECTRICFEES e on  w.task_id=e.ELECTRICFEE_ID "
					+ "left join account a on a.accountid = e.ENTRYPENSONNEL "
					+ "left join  dianbiao d on e.dianbiaoid=d.id "
					+ " left join  zhandian z on z.id = d.zdid "

					+ "left join S_STEP_INFO s on  w.current_no=s.STEP_ID  "
					+ "where nvl(d.id,'0') <>'0' and d.ssf='1' and w.task_type='"
					+ taskType
					+ "' and w.auditorid ='"
					+ accountId
					+ "'  "
					+ whereStr
					+ "  ORDER BY  e.entrytimeold desc, e.DIANBIAOID,e.STARTTIME");
			System.out.println("铁塔报账审核：" + s2);
//添加判断条件 dianbiao d.ssf='1' 铁塔报账审核
			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("铁塔报账审核查询分页" + s3);
			ResultSet rs3 = null;
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
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
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	/**
	 * 报账审核列表
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int getACCOUNTrenname(String rolename) throws SQLException {
		int l = 0,j = 0;
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer s3 = new StringBuffer();
			s3.append("select distinct position from ACCOUNT t");
			System.out.println("角色职位查询去重复：" + s3);
			rs = db.queryAll(s3.toString());
			Query query = new Query();
			list = query.query(rs);
		} catch (DbException de) {
			de.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			if (rolename == list.get(i)) {
				j = 1;
			} else {
				j = 2;
			}
		}
		if(j!=1){
			l=0;
		}
		if(rolename.indexOf("市")>-1){
			if(rolename.indexOf("管理")>-1){
				l=1;
			}else{
				l=2;
			}
		}else if(rolename.indexOf("县")>-1){
			l=0;
		}
		return l;

	}

	/**
	 * 电表审核列表
	 * 
	 * @param
	 * @return
	 */
	public synchronized ArrayList getDBData(int start, String taskType,
			String accountId, String whereStr) {
		ArrayList list = new ArrayList();
		CTime ct = new CTime();

		DataBase db = new DataBase();
		ResultSet rs = null;
		// StringBuffer strall = new StringBuffer();
		// strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		// strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select w.* ,a.name,z.jzname,d.wlbm,d.dbbm,d.dbname,ai.action_name as currentaction,"
					+ "(select  ag.agname from d_area_grade ag where ag.agcode =z.xian) as xian,"
					+ "(select  ag.agname from d_area_grade ag where ag.agcode =z.shi) as shi"
					+ "  from s_workflow w "
					+ "left join S_STEP_INFO s on w.current_no=s.STEP_ID "
					+ "left join s_action_info ai on s.action_id=ai.action_id "
					+ "left join dianbiao d on  w.task_id=d.id  "
					+ "left join zhandian z on d.zdid=z.id  "
					+ "left join account a  on w.applyuser_id=a.accountid "
					+ "where   w.auditorid ='"
					+ accountId
					+ "' and w.task_type='" + taskType + "' " + whereStr + " ");
			System.out.println("电表审核：" + s2);

			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("电表审核查询分页" + s3);
			ResultSet rs3 = null;
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
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
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}

	/**
	 * 办公营业厅审核列表
	 * 
	 * @param
	 * @return
	 */
	public synchronized ArrayList getZDData(int start, String taskType,
			String accountId, String shi, String xian, String xiaoqu,
			String stationName,String whereStr) {
		
		ArrayList list = new ArrayList();
		CTime ct = new CTime();
		StringBuffer cxtj = new StringBuffer();// StringBuffer线程安全的可变字符序列主要操作是
		// append 和 insert 方法
		if (!xiaoqu.equals("0")) {
			cxtj.append(" and z.xiaoqu='" + xiaoqu + "'");
		} else if (!xian.equals("0")) {
			cxtj.append(" and z.xian='" + xian + "'");
		} else if (!shi.equals("0")) {
			cxtj.append(" and z.shi='" + shi + "'");
		}
		if (!stationName.equals("")) {
			cxtj.append(" and z.station_full_name like '%" + stationName + "%'");
		}

		DataBase db = new DataBase();
		ResultSet rs = null;
		// StringBuffer strall = new StringBuffer();
		// strall.append("select z.STATION_NO from zhandian_new z where 1=1 "+whereStr);
		// strall.append(cxtj.toString());
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			StringBuffer s3 = new StringBuffer();
			s2.append("select w.* ,a.name,z.station_full_name,z.ascription_unit,z.full_station_code,"
					+ "(select name from indexs where code = z.stationtype) stationtype,"
					+ "(select agname from d_area_grade where agcode = z.xian ) xian,"
					+ "(select agname from d_area_grade where agcode = z.shi ) shi,"
					+ "(select ai.action_name from S_ACTION_INFO ai where s.ACTION_ID=ai.action_id ) currentaction  "
					+ "from s_workflow w "
					+ "left join ZHANDIAN z on  w.task_id=z.ID "
					+ "left join S_STEP_INFO s on  w.current_no=s.STEP_ID "
					+ "left join account a  on w.applyuser_id=a.accountid "
					+ "where w.task_type='"
					+ taskType
					+ "' and w.auditorid ='"
					+ accountId
					+ "' "
					+ cxtj.toString()
					+ whereStr
					+ "   ORDER BY w.applytime ");
			System.out.println("办公营业厅审核审核：" + s2);

			s3.append("select count(*)  from (" + s2 + ")");
			System.out.println("办公营业厅审核查询分页" + s3);
			ResultSet rs3 = null;
			rs3 = db.queryAll(s3.toString());
			if (rs3.next()) {
				this.setAllPage((rs3.getInt(1) + 9) / 10);
			}
			NPageBean nbean = new NPageBean();
			rs = db.queryAll(nbean.getQueryStr(start, s2.toString()));
			Query query = new Query();
			list = query.query(rs);
			rs3.close();
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
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
}
