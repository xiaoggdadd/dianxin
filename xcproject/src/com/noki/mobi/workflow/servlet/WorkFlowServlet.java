package com.noki.mobi.workflow.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.DianBiaoBean;
import com.noki.jizhan.SiteManage;
import com.noki.jizhan.model.ChengbBean;
import com.noki.jizhan.model.DianbBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonBean;
import com.noki.mobi.flow.javabean.FlowBean;
import com.noki.mobi.workflow.javabean.WorkFlowBean;
import com.ptac.app.accounting.AccountingDao;
import com.ptac.app.station.bean.Station;
import com.ptac.app.station.dao.StationDao;
import com.ptac.app.station.dao.StationDaoImpl;
import com.ptac.app.util.Code;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class WorkFlowServlet extends HttpServlet {
	public WorkFlowServlet() {
	}

	private static final String Content_type = "text/html;charset=UTF-8";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		DbLog log = new DbLog();
		Account account = new Account();
		String url = path + "/web/sdttWeb/workFlow/list.jsp", msg = "";
		HttpSession session = req.getSession();
		account = (Account) session.getAttribute("account");
		WorkFlowBean bean = new WorkFlowBean();
		String action = req.getParameter("action");
		SiteManage sm = new SiteManage();
		DataBase db = new DataBase();
		String sql = "";
		String sqll = "";
		String rolename = account.getRoleName();//角色名称
		String roleid = account.getRoleId();//角色id
		if (action.equals("complete")) {
			String type = req.getParameter("type");// 审核状态0：同意1：驳回
			String opinion = req.getParameter("opinion");// 审核意见
			String taskId = req.getParameter("taskId");// 业务id
			String tasktype = req.getParameter("tasktype");// 业务类型
			String id = req.getParameter("workFlowId");// 流程业务 id
			String flowId = req.getParameter("flowId");// 流程 id
			String auditorid = req.getParameter("auditorid");// 下一步审核人
			String currentid = req.getParameter("currentid");// CURRENT_NO当前节点(s_step_info id)
			String nextactionId = req.getParameter("nextactionId");// NEXT_NO 下一节点(s_step_info id)
			String bohui = req.getParameter("bohui");// 驳回执行人0：发起人
			String applyUserId = req.getParameter("applyUserId");// 流程发起人
			String lastaction = req.getParameter("lastaction");// LAST_NO 上一节点(s_step_info id)
			String bohuijd=req.getParameter("bohuijd");
			// 如果审核人不是发起人，审核表添加一条记录
			System.out.println("业务类型："+ tasktype);
			if (!applyUserId.equals(account.getAccountId())) {
				bean.addApp(account.getAccountId(), new Date(), type, opinion,
						currentid, id);
			}
			if (type.equals("0")) {// 同意
				String no = bean.getStepNo(nextactionId, db);
				if (no != "") {// 判断有无下一步
					int stepNo = Integer.parseInt(no);
					String nextNo = (stepNo + 1) + "";
					String nextStep = bean.getNextStep(flowId, nextNo);
					sql = "UPDATE S_WORKFLOW SET LAST_NO='" + currentid
							+ "', CURRENT_NO='" + nextactionId + "',NEXT_NO='"
							+ nextStep + "',AUDITORID='" + auditorid
							+ "',BOHUI=0 where id=" + id + "";
				} else {// 流程结束
					sql = "UPDATE S_WORKFLOW SET LAST_NO='', CURRENT_NO='',NEXT_NO='',AUDITORID='0' where id="
							+ id + "";
					//修改报账状态 审核最终通过，状态（state）修改为2
					if(tasktype.equals("ELECTRICFEES")){
					    sm.updateBaoZhangState("2",taskId);
//					    sm.addbz_solo("2",taskId);
					    DianbBean dianb = new DianbBean();
					    dianb=  sm.getBzByID(taskId);
					    DecimalFormat df = new DecimalFormat("######0.00");   
					    ArrayList<ChengbBean> chengbList = sm.getCbByID(dianb.getId());
					    for(int c=0;c<chengbList.size();c++){
					        ChengbBean chengb = chengbList.get(c);
					        String money="0";
					        String allmoney="0";
					        String faxmoney="0";
					        String dianliang="0";
					        if(dianb.getAllmoney()!=""){
					            allmoney= df.format(Double.valueOf(dianb.getAllmoney()) * Double.valueOf(chengb.getFtxs()));
                            }
					        if(dianb.getFaxmoney()!=""){
					            faxmoney= df.format(Double.valueOf(dianb.getFaxmoney()) * Double.valueOf(chengb.getFtxs()));
                            }
					        if(dianb.getMoney()!=""){
					            money= df.format(Double.valueOf(allmoney)-Double.valueOf(faxmoney));
					        }
					        if(dianb.getDianliang()!=""){
					            dianliang= df.format(Double.valueOf(dianb.getDianliang()) * Double.valueOf(chengb.getFtxs()));
                            }
					        
					        sm.addbz_solo(dianb.getCompname(), dianb.getDbname(), dianb.getDbcode(), dianb.getZdname(), dianb.getZdcode(), 
					                dianb.getFptype(), dianb.getJffs(), dianb.getStarttime(), dianb.getEndtime(), chengb.getRcname(), chengb.getRccode(), 
					                money, allmoney, faxmoney, dianliang, dianb.getPrice(), 
					                chengb.getYwtype(), chengb.getYwcode(), chengb.getYsname(), chengb.getYscode(),dianb.getShi(),dianb.getShicode(),taskId,dianb.getFTJE());
					    }
					}
					else if(tasktype.equals("DIANBIAO")){
						String dblc_type=req.getParameter("dblc_type");
						String sql2="";
						if(dblc_type.equals("4")){
				        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//修改时间
				        	String ENTRYTIME =sdf.format(new Date());//修改时间
							String danjia_mo = req.getParameter("danjia_mo");
							sql2="UPDATE DIANBIAO SET DANJIA='"+danjia_mo+"',DANJIA_MO='',ENTRYTIME=to_date('"+ENTRYTIME+"', 'YYYY-MM-DD HH24:mi:ss'),SHZT=2,flowid='' where id="+taskId+"";
						}
						else if(dblc_type.equals("2")){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//修改时间
				        	String ENTRYTIME =sdf.format(new Date());//修改时间
							String beilv_mo = req.getParameter("beilv_mo");
							sql2="UPDATE DIANBIAO SET BEILV='"+beilv_mo+"',BEILV_MO='',ENTRYTIME=to_date('"+ENTRYTIME+"', 'YYYY-MM-DD HH24:mi:ss'),SHZT=2,flowid='' where id="+taskId+"";
						}
						else if(dblc_type.equals("3")){
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//修改时间
				        	String ENTRYTIME =sdf.format(new Date());//修改时间
							String dbqyzt_mo = req.getParameter("dbqyzt_mo");
							sql2="UPDATE DIANBIAO SET  DBQYZT='"+dbqyzt_mo+"',DBQYZT_MO='',ENTRYTIME=to_date('"+ENTRYTIME+"', 'YYYY-MM-DD HH24:mi:ss'),SHZT=2,flowid='' where id="+taskId+"";
						}else if(dblc_type.equals("0")) {
							
							sql2="UPDATE DIANBIAO SET  SHZT=2 , flowid='' where id="+taskId+"";
						}else if(dblc_type.equals("1")) {
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//修改时间
				        	String ENTRYTIME =sdf.format(new Date());//修改时间
							sql2="UPDATE DIANBIAO SET  SHZT=2 ,ENTRYTIME=to_date('"+ENTRYTIME+"', 'YYYY-MM-DD HH24:mi:ss'), flowid='' where id="+taskId+"";
							System.out.println("替换电表审批成功之后"+sql2);
						}
						System.out.println("审批成功之后修改字段"+sql2);
						int a =0;
						try {
							db.connectDb();
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							a=db.update(sql2.toString());
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if (tasktype.equals("ZHANDIAN")){
						String sql2="UPDATE ZHANDIAN SET  APPROVE_STATUS='1' where id="+taskId+"";
						int a =0;
						try {
							db.connectDb();
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							a=db.update(sql2.toString());
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else if (type.equals("1")) {// 驳回
				//修改流程信息表S_WORKFLOW，修改电量表（ELECTRICFEES）的state
				System.out.println("id=:"+id);
				if (bohui.equals("0")) {// 发起人
					String nextStep = bean.getNextStep(flowId, "1");
					sql = "UPDATE S_WORKFLOW SET LAST_NO='', CURRENT_NO='0',NEXT_NO='"
							+ nextStep
							+ "',AUDITORID='"
							+ applyUserId
							+ "',BOHUI=1 where id=" + id + "";
				} else {// bohuijd=驳回节点stepId
					//String lastAuditor = bean.getAuditor(id, bohui, db);
					String no = bean.getStepNo(bohuijd, db);
					if (no != "") {
						int stepNo = Integer.parseInt(no);
						String lastNo = (stepNo - 1) + "";
						String lastStep = bean.getNextStep(flowId, lastNo);
						String nextNo = (stepNo + 1) + "";
						String nextStep = bean.getNextStep(flowId, nextNo);
						sql = "UPDATE S_WORKFLOW SET LAST_NO='" + lastStep
								+ "', CURRENT_NO='" + bohuijd + "',NEXT_NO='"
								+ nextStep + "',AUDITORID='" + bohui
								+ "',BOHUI=1 where id=" + id + "";
					} else {
						String nextStep = bean.getNextStep(flowId, "1");
						sql = "UPDATE S_WORKFLOW SET LAST_NO='', CURRENT_NO='0',NEXT_NO='"
								+ nextStep
								+ "',AUDITORID='"
								+ applyUserId
								+ "',BOHUI=1 where id=" + id + "";
					}
				}
			}
			System.out.println("tasktype;"+tasktype);
			if(tasktype.equals("ELECTRICFEES")){
				if(type.equals("1")){
				sqll = "UPDATE ELECTRICFEES SET STATE = 3 WHERE ELECTRICFEE_ID="+ taskId;
				}
				System.out.println("进入tasktype.equals(ELECTRICFEES)");
				url=path + "/web/sdttWeb/baozhang/bzsh.jsp";
			}else if(tasktype.equals("DIANBIAO")){
				url=path + "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp";
			}else if(tasktype.equals("ZHANDIAN")){
				url=path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp";
			}
			System.out.println("sql="+sql);
			System.out.println("sqll="+sqll.toString());
			int a = 0;
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				a = db.update(sql.toString());
				System.out.println("||||"+sqll+"||||");
				if(!sqll.equals("")){
					System.out.println("sqll：进去数据库中进行了操作");
					db.update(sqll.toString());					
				}
			} catch (DbException e) {
				e.printStackTrace();
			}
			if (a == 1) {
				msg = "审核成功";
			} else {
				msg = "审核失败，请联系管理员！";
			}
			log.write(msg, account.getAccountName(), req.getRemoteAddr(),
					"流程审核");
			PrintWriter out = resp.getWriter();
			String ss = "'" + path + "/web/sdttWeb/workFlow/list.jsp'";
			out.print("<script>opener.location=" + ss
					+ ";window.close();</script>");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
			//------------------------
		} else if (action.equals("adjust")) {
			String type = req.getParameter("type");// 审核状态0：同意1：驳回
			String opinion = req.getParameter("opinion");// 审核意见
			String taskId = req.getParameter("taskId");// 业务id
			String id = req.getParameter("workFlowId");// 流程业务 id
			String flowId = req.getParameter("flowId");// 流程 id
			String auditorid = req.getParameter("auditorid");// 下一步审核人
			String currentid = req.getParameter("currentid");// CURRENT_NO 当前节点(s_step_info id)
			String nextactionId = req.getParameter("nextactionId");// NEXT_NO 下一节点(s_step_info id)
			String bohui = req.getParameter("bohui");// 驳回节点0：发起人，1：上一步
			String applyUserId = req.getParameter("applyUserId");// 流程发起人
			String lastaction = req.getParameter("lastaction");// LAST_NO 上一节点(s_step_info id)
			String tasktype = req.getParameter("tasktype");// 业务类型dblc_type
			String no = bean.getStepNo(nextactionId, db);
			String TAX = req.getParameter("TAX");// 税额
			String TAXchange = req.getParameter("TAXchange");// 修改前的税额
			String TAXSTATE = req.getParameter("TAXSTATE");// 税额修改状态
			System.out.println("业务类型："+tasktype);
			if (no != "") {// 判断有无下一步
				int stepNo = Integer.parseInt(no);
				String nextNo = (stepNo + 1) + "";
				String nextStep = bean.getNextStep(flowId, nextNo);
				sql = "UPDATE S_WORKFLOW SET LAST_NO='" + currentid
						+ "', CURRENT_NO='" + nextactionId + "',NEXT_NO='"
						+ nextStep + "',AUDITORID='" + auditorid
						+ "',BOHUI=0 where id=" + id + "";
				String sql8="";
				if(tasktype.equals("DIANBIAO")){
					String dblc_type = req.getParameter("dblc_type");
					if(dblc_type.equals("4")){//4单价
						String danjia_mo = req.getParameter("danjia_mo");
						sql8="UPDATE DIANBIAO SET DANJIA_MO='"+danjia_mo+"' where id="+taskId+"";
						System.out.println("sql8:修改电表单价时："+sql8.toString());
					}else if(dblc_type.equals("2")){//2倍率
						String beilv_mo = req.getParameter("beilv_mo");
						sql8="UPDATE DIANBIAO SET BEILV_MO='"+beilv_mo+"' where id="+taskId+"";
						System.out.println("sql8:修改电表倍率时："+sql8.toString());
					}else if(dblc_type.equals("1")){//1更换
						DateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
			        	String dbname = req.getParameter("dbname");
			        	String wlbm = req.getParameter("wlbm");
			        	String cssysj = req.getParameter("t_sccbsj");
			        	String beilv = req.getParameter("beilv");
			        	String csds = req.getParameter("csds");
			        	String dfzflx = req.getParameter("dfzflx");
			        	String maxds = req.getParameter("maxds");
			        	String dwjslx = req.getParameter("dwjslx");
			        	String isglf = req.getParameter("isglf");
			        	String glbm = req.getParameter("glbm");
			        	String zrr = req.getParameter("zrr");
			        	String bzr = req.getParameter("bzr");
			        	String ydsx = req.getParameter("ydsx");
			        	String ydlx = req.getParameter("ydlx");
			        	String jffs = req.getParameter("jffs");
			        	String jfzq = req.getParameter("jfzq");
			        	String jldw = req.getParameter("jldw");
			        	String yhbh = req.getParameter("yhbh");
			        	String dbqyzt = req.getParameter("dbqyzt");
			        	String bgje = req.getParameter("bgje");
			        	String mtllhd = req.getParameter("mtllhd");
			        	String danjia = req.getParameter("dj");
			        	String gysmc = req.getParameter("gysmc");
			        	String gysbm = req.getParameter("gysbm");
			        	String skfmc = req.getParameter("skfmc");
			        	String yhzh = req.getParameter("yhzh");
			        	String ssyh = req.getParameter("ssyh");
			        	String khyh = req.getParameter("khyh");
			        	String zhsss = req.getParameter("zhsss");
			        	String zhssshi = req.getParameter("zhssshi");
			        	String memo = req.getParameter("memo");
			        	String fplx = req.getParameter("fplx");
			        	String zzsl = req.getParameter("sl");
			        	String dbyt = req.getParameter("dbyt");
			        	System.out.println("dbdeeeeeeeeeeee:"+danjia+beilv+dbqyzt);
			        	//String dbch = req.getParameter("dbch");
			        	String dbch ="";
			        	//String dbscbs = req.getParameter("dbscbs");
			        	String dbscbs ="";
			        	String bz=req.getParameter("bz");
			        	String is_cont=req.getParameter("is_cont");
			        	String cont_type=req.getParameter("cont_type");
			        	String zndb=req.getParameter("zndb");
			        	String isdblx=req.getParameter("isdblx");
			        	String cbzx=req.getParameter("cbzx");
			        	String cbzxbm=req.getParameter("cbzxbm");
			        	String production_prop=req.getParameter("production_prop");
			        	String bur_stand_propertion=req.getParameter("bur_stand_propertion");
			        	String cont_id=req.getParameter("cont_id");
			        	String electric_supply_way=req.getParameter("electric_supply_way");
			        	String total_electricity=req.getParameter("total_electricity");
			        	String zhangqi=req.getParameter("zhangqi");
			        	String zhz=req.getParameter("zhz");
			        	String gszt=req.getParameter("gszt");
			        	String LJID="0"; //动环ID
			        	String dbtype="1"; //录入
			        	Date tt=null;
						sql8="UPDATE DIANBIAO SET is_cont='"+is_cont+"',cont_type='"+cont_type+"',zndb='"+zndb+"',isdblx='"+isdblx+"'," +
        				"cbzx='"+cbzx+"',cbzxbm='"+cbzxbm+"',production_prop='"+production_prop+"',bur_stand_propertion='"+bur_stand_propertion+"',cont_id='"+cont_id+"',electric_supply_way='"+electric_supply_way+"',total_electricity='"+total_electricity+"',zq='"+zhangqi+"',zhz='"+zhz+"'," +
        				"WLBM='"+wlbm+"', DBNAME='"+dbname+"',BEILV='"+beilv+"',CSDS='"+csds+"',DFZFLX='"+dfzflx+"',MAXDS='"+maxds+"',DWJSLX='"+dwjslx+"',ISGLF='"+isglf+"'" +
        				" , GLBM='"+glbm+"', ZRR='"+zrr+"', BZR='"+bzr+"',YDSX='"+ydsx+"',YDLX='"+ydlx+"',JFFS='"+jffs+"',JFZQ='"+jfzq+"',JLDW='"+jldw+"',YHBH='"+yhbh+"',DBQYZT='"+dbqyzt+"',BGJE='"+bgje+"',MTLLHD='"+mtllhd+"',DANJIA='"+danjia+"',GYSMC='"+gysmc+"' ," +
        				"GYSBM='"+gysbm+"', SKFMC='"+skfmc+"',YHZH='"+yhzh+"',SSYH='"+ssyh+"',KHYH='"+khyh+"',ZHSSS='"+zhsss+"',ZHSSSHI='"+zhssshi+"',MEMO='"+memo+"',FPLX='"+fplx+"',ZZSL='"+zzsl+"',DBYT='"+dbyt+"' ,DBCH='"+dbch+"' " +
        						",DBSCBS='"+dbscbs+"', gszt='"+gszt+"' where id="+taskId+"";
						//添加核算单元
						System.out.println("sql8:更换电表时："+sql8.toString());
		            	String[] costCode = req.getParameterValues("costCode");
		            	String[] businessType = req.getParameterValues("businessType");
		            	String[] elecProperty = req.getParameterValues("elecProperty");
		            	String[] dutyCode = req.getParameterValues("dutyCode");
		            	String[] projectCode = req.getParameterValues("projectCode");
		            	String[] isRealSubAmmeter = req.getParameterValues("isRealSubAmmeter");
		            	String[] shareRatio = req.getParameterValues("shareRatio");
		            	AccountingDao dao = new AccountingDao();
		            	String dbcode = id+"";
		            	dao.deleteAccounting(dbcode);
		            	dao.addAccounting(costCode, businessType, elecProperty, dutyCode, projectCode, isRealSubAmmeter, shareRatio, dbcode);
					}else if(dblc_type.equals("3")){//3状态
						String dbqyzt = req.getParameter("dbqyzt");
						sql8="UPDATE DIANBIAO SET DBQYZT_MO='"+dbqyzt+"' where id="+taskId+"";
						System.out.println("sql8:修改电表启用状态时："+sql8.toString());
					}else if(dblc_type.equals("0")){//0：添加
						DateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
			        	String dbname = req.getParameter("dbname");
			        	String wlbm = req.getParameter("wlbm");
			        	String cssysj = req.getParameter("t_sccbsj");
			        	String beilv = req.getParameter("beilv");
			        	String csds = req.getParameter("csds");
			        	String dfzflx = req.getParameter("dfzflx");
			        	String maxds = req.getParameter("maxds");
			        	String dwjslx = req.getParameter("dwjslx");
			        	String isglf = req.getParameter("isglf");
			        	String glbm = req.getParameter("glbm");
			        	String zrr = req.getParameter("zrr");
			        	String bzr = req.getParameter("bzr");
			        	String ydsx = req.getParameter("ydsx");
			        	String ydlx = req.getParameter("ydlx");
			        	String jffs = req.getParameter("jffs");
			        	String jfzq = req.getParameter("jfzq");
			        	String jldw = req.getParameter("jldw");
			        	String yhbh = req.getParameter("yhbh");
			        	String dbqyzt = req.getParameter("dbqyzt");
			        	String bgje = req.getParameter("bgje");
			        	String mtllhd = req.getParameter("mtllhd");
			        	String danjia = req.getParameter("dj");
			        	String gysmc = req.getParameter("gysmc");
			        	String gysbm = req.getParameter("gysbm");
			        	String skfmc = req.getParameter("skfmc");
			        	String yhzh = req.getParameter("yhzh");
			        	String ssyh = req.getParameter("ssyh");
			        	String khyh = req.getParameter("khyh");
			        	String zhsss = req.getParameter("zhsss");
			        	String zhssshi = req.getParameter("zhssshi");
			        	String memo = req.getParameter("memo");
			        	String fplx = req.getParameter("fplx");
			        	String zzsl = req.getParameter("sl");
			        	String dbyt = req.getParameter("dbyt");
			        	//String dbch = req.getParameter("dbch");
			        	String dbch ="";
			        	//String dbscbs = req.getParameter("dbscbs");
			        	String dbscbs ="";
			        	String bz=req.getParameter("bz");
			        	String is_cont=req.getParameter("is_cont");
			        	String cont_type=req.getParameter("cont_type");
			        	String zndb=req.getParameter("zndb");
			        	String isdblx=req.getParameter("isdblx");
			        	String cbzx=req.getParameter("cbzx");
			        	String cbzxbm=req.getParameter("cbzxbm");
			        	String production_prop=req.getParameter("production_prop");
			        	String bur_stand_propertion=req.getParameter("bur_stand_propertion");
			        	String cont_id=req.getParameter("cont_id");
			        	String electric_supply_way=req.getParameter("electric_supply_way");
			        	String total_electricity=req.getParameter("total_electricity");
			        	String zhangqi=req.getParameter("zhangqi");
			        	String zhz=req.getParameter("zhz");
			        	String gszt=req.getParameter("gszt");
			        	String LJID="0"; //动环ID
			        	String dbtype="1"; //录入
			        	System.out.println("aaaaaaaaaaaaaaa"+beilv+dbqyzt+danjia);
			        	Date tt=null;
						sql8="UPDATE DIANBIAO SET is_cont='"+is_cont+"',cont_type='"+cont_type+"',zndb='"+zndb+"',isdblx='"+isdblx+"'," +
        				"cbzx='"+cbzx+"',cbzxbm='"+cbzxbm+"',production_prop='"+production_prop+"',bur_stand_propertion='"+bur_stand_propertion+"',cont_id='"+cont_id+"',electric_supply_way='"+electric_supply_way+"',total_electricity='"+total_electricity+"',zq='"+zhangqi+"',zhz='"+zhz+"'," +
        				"WLBM='"+wlbm+"', DBNAME='"+dbname+"',BEILV='"+beilv+"',CSDS='"+csds+"',DFZFLX='"+dfzflx+"',MAXDS='"+maxds+"',DWJSLX='"+dwjslx+"',ISGLF='"+isglf+"'" +
        				" , GLBM='"+glbm+"', ZRR='"+zrr+"', BZR='"+bzr+"',YDSX='"+ydsx+"',YDLX='"+ydlx+"',JFFS='"+jffs+"',JFZQ='"+jfzq+"',JLDW='"+jldw+"',YHBH='"+yhbh+"',DBQYZT='"+dbqyzt+"',BGJE='"+bgje+"',MTLLHD='"+mtllhd+"',DANJIA='"+danjia+"',GYSMC='"+gysmc+"' ," +
        				"GYSBM='"+gysbm+"', SKFMC='"+skfmc+"',YHZH='"+yhzh+"',SSYH='"+ssyh+"',KHYH='"+khyh+"',ZHSSS='"+zhsss+"',ZHSSSHI='"+zhssshi+"',MEMO='"+memo+"',FPLX='"+fplx+"',ZZSL='"+zzsl+"',DBYT='"+dbyt+"' ,DBCH='"+dbch+"' " +
        						",DBSCBS='"+dbscbs+"', gszt='"+gszt+"' where id="+taskId+"";
						System.out.println("sql8:新增电表时："+sql8.toString());
						//添加核算单元
		            	String[] costCode = req.getParameterValues("costCode");
		            	String[] businessType = req.getParameterValues("businessType");
		            	String[] elecProperty = req.getParameterValues("elecProperty");
		            	String[] dutyCode = req.getParameterValues("dutyCode");
		            	String[] projectCode = req.getParameterValues("projectCode");
		            	String[] isRealSubAmmeter = req.getParameterValues("isRealSubAmmeter");
		            	String[] shareRatio = req.getParameterValues("shareRatio");
		            	AccountingDao dao = new AccountingDao();
		            	String dbcode = id+"";
		            	dao.deleteAccounting(dbcode);
		            	dao.addAccounting(costCode, businessType, elecProperty, dutyCode, projectCode, isRealSubAmmeter, shareRatio, dbcode);

					}
					int a =0;
					try {
						db.connectDb();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						a=db.update(sql8.toString());
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					url=path + "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp";
				}else if(tasktype.equals("ELECTRICFEES")){
					String DIANBIAOID = (req.getParameter("DIANBIAOID")!= null && !"".equals(req.getParameter("DIANBIAOID"))) ? req.getParameter("DIANBIAOID") : "0";
		            String STARTTIME = (req.getParameter("STARTTIME")!= null && !"".equals(req.getParameter("STARTTIME"))) ? req.getParameter("STARTTIME") : null;
		            String ENDTIME = (req.getParameter("ENDTIME")!= null && !"".equals(req.getParameter("ENDTIME"))) ? req.getParameter("ENDTIME") : null;
		            String SQDS = (req.getParameter("SQDS")!= null && !"".equals(req.getParameter("SQDS"))) ? req.getParameter("SQDS") : "0";
		            String BQDS = (req.getParameter("BQDS")!= null && !"".equals(req.getParameter("BQDS"))) ? req.getParameter("BQDS") : "0";
		            String DIANLIANG = (req.getParameter("DIANLIANG")!= null && !"".equals(req.getParameter("DIANLIANG"))) ? req.getParameter("DIANLIANG") : "0";
		            String ALLMONEY = (req.getParameter("ALLMONEY")!= null && !"".equals(req.getParameter("ALLMONEY"))) ? req.getParameter("ALLMONEY") : "0";
		            String DIANSUN = (req.getParameter("DIANSUN")!= null && !"".equals(req.getParameter("DIANSUN"))) ? req.getParameter("DIANSUN") : "0";
		            String SQDJ = (req.getParameter("SQDJ")!= null && !"".equals(req.getParameter("SQDJ"))) ? req.getParameter("SQDJ") : "0";
		            String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
		            String DAYNUM = (req.getParameter("TIANSHU")!= null && !"".equals(req.getParameter("TIANSHU"))) ? req.getParameter("TIANSHU") : "0";
		            String RJYDL = (req.getParameter("RJYDL")!= null && !"".equals(req.getParameter("RJYDL"))) ? req.getParameter("RJYDL") : "0";
		            String SQRJYDL = (req.getParameter("SQRJYDL")!= null && !"".equals(req.getParameter("SQRJYDL"))) ? req.getParameter("SQRJYDL") : "0";
		            String PUEZHI = (req.getParameter("PUEZHI")!= null && !"".equals(req.getParameter("PUEZHI"))) ? req.getParameter("PUEZHI") : "0"; 
		            String BGDL =  (req.getParameter("BGDL")!= null && !"".equals(req.getParameter("BGDL"))) ? req.getParameter("BGDL") : "0";
		            String FTJE =  (req.getParameter("FTJE")!= null && !"".equals(req.getParameter("FTJE"))) ? req.getParameter("FTJE") : "0";
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2018年3月30日-gcl-修改创建时间
		            String ACCTIME =sdf.format(new Date());
		            SiteManage sitebean = new SiteManage();
		            int retsign = sitebean.updateDataBaoZhang(TAXchange,TAXSTATE,TAX,taskId,DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,"1",applyUserId,ACCTIME,PUEZHI, FTJE,BGDL);

		            if (retsign == 1) {
		                msg = "修改报账成功！";
		            } else if (retsign == 2) {
		                msg = "修改失败！报账代码重复！";
		            }
		            CommonBean commonBean = new CommonBean();
		            //修改pue记录 
		            //添加pue记录 
                    List<String> strList = commonBean.getstationByDbId(DIANBIAOID);
                    
                   String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="";
                   if(strList!=null && strList.size()==4){
                   	st_id_str = strList.get(0);
                   	jiaoliu_str = strList.get(1);
                   	zhiliu_str = strList.get(2);
                   	zdid_str = strList.get(3);
                   }
                   List<String> strList2 = commonBean.getSumByDbId(st_id_str,STARTTIME, ENDTIME);
                   String sum_str = "",value_str="",sumDay_str="";
                   if(strList2!=null && strList2.size()==3){
                   	sum_str = strList2.get(0);
                   	value_str = strList2.get(1);
                   	sumDay_str = strList2.get(2);
                   }
                   sitebean.updateAnalysis(taskId, "", "", DIANLIANG,sum_str, DIANBIAOID, zdid_str, STARTTIME, ENDTIME,value_str,jiaoliu_str,zhiliu_str,sumDay_str);                     
		            url=path + "/web/sdttWeb/baozhang/bzsh.jsp";
				}else if(tasktype.equals("ZHANDIAN")){
					StationDao dao = new StationDaoImpl();
					Station station=dao.getOne(Integer.parseInt(taskId));
					station.setShi(StringUtils.trimToEmpty(req.getParameter("shi")));
					station.setXian(StringUtils.trimToEmpty(req.getParameter("xian")));
					station.setXiaoqu(StringUtils.trimToEmpty(req.getParameter("xiaoqu")));
					station.setStationCode(StringUtils.trimToEmpty(req.getParameter("stationCode")));
					station.setJzName(StringUtils.trimToEmpty(req.getParameter("jzName")));
					station.setLongitude(StringUtils.trimToEmpty(req.getParameter("longitude")));
					station.setLatitude(StringUtils.trimToEmpty(req.getParameter("latitude")));
					station.setIssupervisor(StringUtils.trimToEmpty(req.getParameter("issupervisor")));
					station.setPower(StringUtils.trimToEmpty(req.getParameter("power")));
					station.setStationType(StringUtils.trimToEmpty(req.getParameter("stationType")));
					station.setProperty(StringUtils.trimToEmpty(req.getParameter("property")));
					station.setKongtiao(StringUtils.trimToEmpty(req.getParameter("kongtiao")));
					station.setRank(StringUtils.trimToEmpty(req.getParameter("rank")));
					station.setStatus(StringUtils.trimToEmpty(req.getParameter("status")));
					if(!StringUtils.trimToEmpty(req.getParameter("iDCRackNum")).isEmpty()){
						station.setIDCRackNum(Integer.parseInt(req.getParameter("iDCRackNum")));
					}
					station.setIsSharingRent(StringUtils.trimToEmpty(req.getParameter("isSharingRent")));
					station.setAscriptionUnit(StringUtils.trimToEmpty(req.getParameter("ascriptionUnit")));
					if(!StringUtils.trimToEmpty(req.getParameter("sharedNum")).isEmpty()){
						station.setSharedNum(Integer.parseInt(req.getParameter("sharedNum")));
					}
					station.setSharedName(StringUtils.trimToEmpty(req.getParameter("sharedName")));
					station.setContractType(StringUtils.trimToEmpty(req.getParameter("contractType")));
					if(!StringUtils.trimToEmpty(req.getParameter("currentNum")).isEmpty()){
						station.setCurrentNum(Integer.parseInt(req.getParameter("currentNum")));
					}
					station.setCurrentType(StringUtils.trimToEmpty(req.getParameter("currentType")));
					station.setAccountPeriod(StringUtils.trimToEmpty(req.getParameter("accountPeriod")));
					
					station.setBranchBureau(StringUtils.trimToEmpty(req.getParameter("branchBureau")));
					station.setIsIdstlElecApply(StringUtils.trimToEmpty(req.getParameter("isIdstlElecApply")));
					station.setIsApplyIdstlElec(StringUtils.trimToEmpty(req.getParameter("isApplyIdstlElec")));
					station.setIsIdstlElec(StringUtils.trimToEmpty(req.getParameter("isIdstlElec")));
					station.setIsPowerDirectTransaction(StringUtils.trimToEmpty(req.getParameter("isPowerDirectTransaction")));
					if(!StringUtils.trimToEmpty(req.getParameter("idstlElecTopPrice")).isEmpty()){
						station.setIdstlElecTopPrice(Double.parseDouble(req.getParameter("idstlElecTopPrice")));
					}
					if(!StringUtils.trimToEmpty(req.getParameter("idstlElecNormalPrice")).isEmpty()){
						station.setIdstlElecNormalPrice(Double.parseDouble(req.getParameter("idstlElecNormalPrice")));
					}
					if(!StringUtils.trimToEmpty(req.getParameter("idstlElecBottomPrice")).isEmpty()){
						station.setIdstlElecBottomPrice(Double.parseDouble(req.getParameter("idstlElecBottomPrice")));
					}
					if(!StringUtils.trimToEmpty(req.getParameter("geneOtherBusiElecPrice")).isEmpty()){
						station.setGeneOtherBusiElecPrice(Double.parseDouble(req.getParameter("geneOtherBusiElecPrice")));
					}
					station.setTransformerCd(StringUtils.trimToEmpty(req.getParameter("transformerCd")));
					if(!StringUtils.trimToEmpty(req.getParameter("transformerCapacity")).isEmpty()){
						station.setTransformerCapacity(Double.parseDouble(req.getParameter("transformerCapacity")));
					}
					station.setPowerElement(StringUtils.trimToEmpty(req.getParameter("powerElement")));
					station.setCollectMode("1");
					//实体名称
					String stationName = station.getJzName();
					station.setJzName(stationName);
					CommonBean commonBean = new CommonBean();
					String stationFullName = commonBean.getAgName(station.getXiaoqu()) + stationName;
					station.setStationFullName(stationFullName);
					//实体编号
					Code code = new Code();
					String entityCode = "";
					if("StationType18".equalsIgnoreCase(station.getStationType())){
						entityCode = code.getEntityCode("B", code.getNextVal());
					}else if("StationType19".equalsIgnoreCase(station.getStationType())){
						entityCode = code.getEntityCode("Y", code.getNextVal());
					}else{
						entityCode = code.getEntityCode(null);
					}
					dao.modifyStation(station);
					url=path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp";
				}
				
			} else {// 流程结束
				sql = "UPDATE S_WORKFLOW SET LAST_NO='', CURRENT_NO='',NEXT_NO='',AUDITORID='',BOHUI=0 where id="
						+ id + "";
				
			}
			if(tasktype.equals("ELECTRICFEES")){
				url=path + "/web/sdttWeb/baozhang/bzsh.jsp";
			}else if(tasktype.equals("DIANBIAO")){
				url=path + "/web/sdttWeb/jichuInfoManager/dianbiaoSPlist.jsp";
			}else if(tasktype.equals("ZHANDIAN")){
				url=path + "/web/sdttWeb/jichuInfoManager/stationManagerAudit.jsp";
			}
			System.out.println("调整任务=" + sql);
			int a = 0;
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				a = db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			if (a == 1) {
				msg = "调整成功";
			} else {
				msg = "调整失败，请联系管理员！";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
		} 
		//-------------------------
		else if (action.equals("getAuditor")) {
			
			String flowId = req.getParameter("flowId");
			String dbid=req.getParameter("dbid");
			
			String agcode=req.getParameter("agcode");
			System.out.println("agcode="+agcode);
			if(agcode==null||agcode.equals("")){
				DianBiaoBean dbbean = new DianBiaoBean();
				agcode=dbbean.getZDXian(dbid);
			}
			String nextAction = bean.getNextStep(flowId, "1");
			ArrayList list = new ArrayList();
			list = bean.getAccountByActionId2(nextAction,agcode);
			resp.setContentType("text");
			PrintWriter out = resp.getWriter();
			Gson gson = new Gson();
			String json = gson.toJson(list);
			out.write(json);
			out.flush();
			out.close();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}

		} else if (action.equals("getActionName")) {
			String flowId = req.getParameter("flowId");
			String nextAction = bean.getNextStep(flowId, "1");
			String actionname = bean.getActionName(nextAction);
			resp.setContentType("text");
			PrintWriter out = resp.getWriter();

			Gson gson = new Gson();
			String json = gson.toJson(actionname);
			out.write(json);
			out.flush();
			out.close();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}

		} else if (action.equals("plbl")) {// 批量办理
			String n = req.getParameter("n");
			String[] ids = req.getParameterValues("itemSelected");
			String strId = "";
			ResultSet rs = null;
			String nextAction = "";
			String lastAction = "";
			String currentAction = "";
			String sql2 = "";
			String taskId="";
			String tasktype="";
			int coun = 0;
			for (int i = 0; i < ids.length; i++) {

				strId = ids[i];
				sql = "select * from s_workflow where id=" + strId + "";
				try {
					rs = db.queryAll(sql);
					while (rs.next()) {
						nextAction = rs.getString("NEXT_NO");
						currentAction = rs.getString("CURRENT_NO");
						lastAction = rs.getString("LAST_NO");
						tasktype=rs.getString("TASK_TYPE");
						taskId=rs.getString("TASK_ID");
						
					}
					if (n.equals("0")) {// 通过
						// 流程结束
						sql2 = "UPDATE S_WORKFLOW SET LAST_NO='', CURRENT_NO='',NEXT_NO='',AUDITORID='' where id="
								+ strId + "";
						
						//修改报账状态 审核最终通过，状态（state）修改为2
						if(tasktype.equals("ELECTRICFEES")){
						    sm.updateBaoZhangState("2",taskId);
//						    sm.addbz_solo("2",taskId);
						    DianbBean dianb = new DianbBean();
						    dianb=  sm.getBzByID(taskId);
						    DecimalFormat df = new DecimalFormat("######0.00");   
						    ArrayList<ChengbBean> chengbList = sm.getCbByID(dianb.getId());
						    for(int c=0;c<chengbList.size();c++){
						        ChengbBean chengb = chengbList.get(c);
						        String money="0";
						        String allmoney="0";
						        String faxmoney="0";
						        String dianliang="0";
						     
						        if(dianb.getAllmoney()!=""){
						            allmoney= df.format(Double.valueOf(dianb.getAllmoney()) * Double.valueOf(chengb.getFtxs()));
	                            }
						        if(dianb.getFaxmoney()!=""){
						            faxmoney= df.format(Double.valueOf(dianb.getFaxmoney()) * Double.valueOf(chengb.getFtxs()));
	                            }
						        if(dianb.getMoney()!=""){
						            money= df.format(Double.valueOf(dianb.getMoney()) * Double.valueOf(chengb.getFtxs()));
						        }
						        if(dianb.getDianliang()!=""){
						            dianliang= df.format(Double.valueOf(dianb.getDianliang()) * Double.valueOf(chengb.getFtxs()));
	                            }
						        
						        sm.addbz_solo(dianb.getCompname(), dianb.getDbname(), dianb.getDbcode(), dianb.getZdname(), dianb.getZdcode(), 
						                dianb.getFptype(), dianb.getJffs(), dianb.getStarttime(), dianb.getEndtime(), chengb.getRcname(), chengb.getRccode(), 
						                money, allmoney, faxmoney, dianliang, dianb.getPrice(), 
						                chengb.getYwtype(), chengb.getYwcode(), chengb.getYsname(), chengb.getYscode(),dianb.getShi(),dianb.getShicode(),taskId,dianb.getFTJE());
						    }
						    
						}
						url=path + "/web/sdttWeb/baozhang/bzsh.jsp";
						
					} else {// 驳回上一步
						String lastAuditor = bean.getAuditor(strId, lastAction,
								db);
						String no = bean.getStepNo(lastAction, db);
						int stepNo = Integer.parseInt(no);
						String lastNo = (stepNo - 1) + "";
						String lastStep = bean.getNextStep(strId, lastNo);
						sql2 = "UPDATE S_WORKFLOW SET LAST_NO='" + lastStep
								+ "', CURRENT_NO='" + lastAction
								+ "',NEXT_NO='" + currentAction
								+ "',AUDITORID='" + lastAuditor
								+ "',BOHUI=1 where id=" + strId + "";
						
						
						if(tasktype.equals("ELECTRICFEES")){
							url=path + "/web/sdttWeb/baozhang/bzsh.jsp";
						}
					}
					int a = 0;
					try {
						db.connectDb();
					} catch (DbException e) {
						e.printStackTrace();
					}
					try {
						a = db.update(sql2.toString());
					} catch (DbException e) {
						e.printStackTrace();
					}
					if (a == 1) {
						// msg="";
					} else {
						// msg="审核失败，请联系管理员！";
						coun = coun + 1;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			try {
				rs.close();
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
			if (coun == 0) {
				msg = "审核成功";
			} else {
				msg = "有" + coun + "条数据审核失败";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");

		}
		else if("saveWorkFlow".equals(action)){//添加工作流程
			//添加流程
			WorkFlowBean workFlowBean= new WorkFlowBean();
			String flowId=req.getParameter("flowId");
			String auditorid=req.getParameter("auditorid");
			String taskType="DIANBIAO,0";//区分电表流程
			String taskId=req.getParameter("taskId");;
			String currentNo =workFlowBean.getNextStep(flowId, "1");
			String nextNo =workFlowBean.getNextStep(flowId, "2");
			int workId=workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
		 	if(workId>0){
		 		msg="提交成功！";
		 	}else{
		 		msg="提交失败！请重试或与管理员联系";
		 	}
		 	sql="UPDATE DIANBIAO SET SHZT=1, FLOWID='"+workId+"' where id="+taskId+""; 
			System.out.println("sql="+sql);	
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}try {
				db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
		 	
		 	 log.write(msg, account.getAccountName(), req.getRemoteAddr(), "提交审核");
			 session.setAttribute("url", url);
			 session.setAttribute("msg", msg);
			 resp.sendRedirect(path + "/web/msg.jsp");
		}else if("approve".equals(action)){//审核通过
			
		}else if("reject".equals(action)){//驳回
			
		}else if("selflow".equals(action)){//根据属性选择流程
			String type = req.getParameter("type");
			 ArrayList actionlist = new ArrayList();
			FlowBean flowBean = new FlowBean();
			if(type.equals("danjia")){
				 actionlist = flowBean.getAction("flowtype03", "1"); //电表审核 step1
			}else{
				 actionlist = flowBean.getAction("flowtype01", "1"); //电表审核 step1
			}
			PrintWriter out = resp.getWriter();

			Gson gson = new Gson();
			String json = gson.toJson(actionlist);
			out.write(json);
			out.flush();
			out.close();
			
		}else if("selflow2".equals(action)){
			System.out.println("rolename:"+rolename);
			// update by yanshuoshi 20180504
			/*if(rolename.contains("市审核")){
				String flowid = req.getParameter("flowid");
				String ydsx1 = req.getParameter("ydsx1");
				System.out.println("selflow2'ydsx1:"+ydsx1);
				String roleName = "";
				if(ydsx1.equals("ydsx11")){ //办公
					roleName = "%市综合部主任%";
				}else if(ydsx1.equals("ydsx12")){//营业厅
					roleName = "%市销售部主任%";
				}else {//生产
					roleName = "%市运维主任%";
				}
				 ArrayList actionlist = new ArrayList();
				FlowBean flowBean = new FlowBean();
				actionlist = flowBean.getActionByFlowid(flowid, "1",roleName);
				PrintWriter out = resp.getWriter();

				Gson gson = new Gson();
				String json = gson.toJson(actionlist);
				out.write(json);
				out.flush();
				out.close();
			}else{*/
				String flowid = req.getParameter("flowid");
				ArrayList actionlist = new ArrayList();
				FlowBean flowBean = new FlowBean();
				actionlist = flowBean.getActionByFlowid(flowid, "1");
				PrintWriter out = resp.getWriter();
				
				Gson gson = new Gson();
				String json = gson.toJson(actionlist);
				out.write(json);
				out.flush();
				out.close();
			/*}*/
			
		}else if("selAccount".equals(action)){//
			System.out.println("rolenamedddd"+rolename);
			if(rolename.contains("市审核")){
				String flowid = req.getParameter("flowid");
				String ydsx1 = req.getParameter("ydsx1");
				String agcode = req.getParameter("agcode");
				System.out.println("selflow2'ydsx1:"+ydsx1);
				String roleName = "";
				if(ydsx1.equals("ydsx11")){ //办公
					roleName = "%市综合部主任%";
				}else if(ydsx1.equals("ydsx12")){//营业厅
					roleName = "%市销售部主任%";
				}else {//生产
					roleName = "%市运维主任%";
				}
				 ArrayList actionlist = new ArrayList();
				FlowBean flowBean = new FlowBean();
				actionlist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(), agcode,roleName);
				PrintWriter out = resp.getWriter();

				Gson gson = new Gson();
				String json = gson.toJson(actionlist);
				out.write(json);
				out.flush();
				out.close();
			}else{
			String agcode = req.getParameter("agcode");
			String roleId = req.getParameter("roleId");
			FlowBean flowBean = new FlowBean();
			 ArrayList acclist = new ArrayList();
			 acclist = flowBean.getAccountByDeptNoAndRoleId(account.getDeptNo(), agcode,roleId);
			 PrintWriter out = resp.getWriter();

				Gson gson = new Gson();
				String json = gson.toJson(acclist);
				out.write(json);
				out.flush();
				out.close();
		}

	}else if("bangongSela".equals(action)){//办公
		System.out.println("rolenamedddd"+rolename);
		
		if(rolename.contains("市审核")){
			String flowid = req.getParameter("flowid");
			String ydsx1 = req.getParameter("ydsx1");
			System.out.println("ydsx1"+ydsx1);
			String agcode = req.getParameter("agcode");
			System.out.println("selflow2'ydsx1:"+ydsx1);
			String roleName = "";
			if(ydsx1.equals("StationType18")){ //办公
				roleName = "%市综合部主任%";
			}else if(ydsx1.equals("StationType19")){//营业厅
				roleName = "%市销售部主任%";
			}else {//生产
				roleName = "%市运维主任%";
			}
			 ArrayList actionlist = new ArrayList();
			FlowBean flowBean = new FlowBean();
			actionlist = flowBean.getAccountByDeptNoAndRoleIds(account.getDeptNo(), agcode,roleName);
			PrintWriter out = resp.getWriter();

			Gson gson = new Gson();
			String json = gson.toJson(actionlist);
			out.write(json);
			out.flush();
			out.close();
		}else{
		String agcode = req.getParameter("agcode");
		String roleId = req.getParameter("roleId");
		FlowBean flowBean = new FlowBean();
		 ArrayList acclist = new ArrayList();
		 acclist = flowBean.getAccountByDeptNoAndRoleId(account.getDeptNo(), agcode,roleId);
		 PrintWriter out = resp.getWriter();

			Gson gson = new Gson();
			String json = gson.toJson(acclist);
			out.write(json);
			out.flush();
			out.close();
	}

}

}
}