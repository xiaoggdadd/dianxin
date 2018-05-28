package com.ptac.app.station.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonBean;
import com.noki.mobi.workflow.javabean.WorkFlowBean;
import com.ptac.app.station.bean.Station;
import com.ptac.app.station.dao.StationDao;
import com.ptac.app.station.dao.StationDaoImpl;
import com.ptac.app.util.Code;

public class StationServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		DbLog log = new DbLog();
		String action = request.getParameter("action");
		String path = request.getContextPath();
		String msg = "";
		String url = path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp";
		StationDao dao = new StationDaoImpl();
		if("save".equals(action)){
			Station station = new Station();
			if(!StringUtils.trimToEmpty(request.getParameter("id")).isEmpty()){
				station.setId(Integer.parseInt(request.getParameter("id")));
			}
			station.setShi(StringUtils.trimToEmpty(request.getParameter("shi")));
			station.setXian(StringUtils.trimToEmpty(request.getParameter("xian")));
			station.setXiaoqu(StringUtils.trimToEmpty(request.getParameter("xiaoqu")));
			station.setStationCode(StringUtils.trimToEmpty(request.getParameter("stationCode")));
			station.setJzName(StringUtils.trimToEmpty(request.getParameter("jzName")));
			station.setLongitude(StringUtils.trimToEmpty(request.getParameter("longitude")));
			station.setLatitude(StringUtils.trimToEmpty(request.getParameter("latitude")));
			station.setIssupervisor(StringUtils.trimToEmpty(request.getParameter("issupervisor")));
			station.setPower(StringUtils.trimToEmpty(request.getParameter("power")));
			station.setStationType(StringUtils.trimToEmpty(request.getParameter("stationType")));
			station.setProperty(StringUtils.trimToEmpty(request.getParameter("property")));
			station.setKongtiao(StringUtils.trimToEmpty(request.getParameter("kongtiao")));
			station.setRank(StringUtils.trimToEmpty(request.getParameter("rank")));
			station.setStatus(StringUtils.trimToEmpty(request.getParameter("status")));
			if(!StringUtils.trimToEmpty(request.getParameter("iDCRackNum")).isEmpty()){
				station.setIDCRackNum(Integer.parseInt(request.getParameter("iDCRackNum")));
			}
			station.setIsSharingRent(StringUtils.trimToEmpty(request.getParameter("isSharingRent")));
			station.setAscriptionUnit(StringUtils.trimToEmpty(request.getParameter("ascriptionUnit")));
			if(!StringUtils.trimToEmpty(request.getParameter("sharedNum")).isEmpty()){
				station.setSharedNum(Integer.parseInt(request.getParameter("sharedNum")));
			}
			station.setSharedName(StringUtils.trimToEmpty(request.getParameter("sharedName")));
			station.setContractType(StringUtils.trimToEmpty(request.getParameter("contractType")));
			if(!StringUtils.trimToEmpty(request.getParameter("currentNum")).isEmpty()){
				station.setCurrentNum(Integer.parseInt(request.getParameter("currentNum")));
			}
			station.setCurrentType(StringUtils.trimToEmpty(request.getParameter("currentType")));
			station.setAccountPeriod(StringUtils.trimToEmpty(request.getParameter("accountPeriod")));
			
			station.setBranchBureau(StringUtils.trimToEmpty(request.getParameter("branchBureau")));
			station.setIsIdstlElecApply(StringUtils.trimToEmpty(request.getParameter("isIdstlElecApply")));
			station.setIsApplyIdstlElec(StringUtils.trimToEmpty(request.getParameter("isApplyIdstlElec")));
			station.setIsIdstlElec(StringUtils.trimToEmpty(request.getParameter("isIdstlElec")));
			station.setIsPowerDirectTransaction(StringUtils.trimToEmpty(request.getParameter("isPowerDirectTransaction")));
			if(!StringUtils.trimToEmpty(request.getParameter("idstlElecTopPrice")).isEmpty()){
				station.setIdstlElecTopPrice(Double.parseDouble(request.getParameter("idstlElecTopPrice")));
			}
			if(!StringUtils.trimToEmpty(request.getParameter("idstlElecNormalPrice")).isEmpty()){
				station.setIdstlElecNormalPrice(Double.parseDouble(request.getParameter("idstlElecNormalPrice")));
			}
			if(!StringUtils.trimToEmpty(request.getParameter("idstlElecBottomPrice")).isEmpty()){
				station.setIdstlElecBottomPrice(Double.parseDouble(request.getParameter("idstlElecBottomPrice")));
			}
			if(!StringUtils.trimToEmpty(request.getParameter("geneOtherBusiElecPrice")).isEmpty()){
				station.setGeneOtherBusiElecPrice(Double.parseDouble(request.getParameter("geneOtherBusiElecPrice")));
			}
			station.setTransformerCd(StringUtils.trimToEmpty(request.getParameter("transformerCd")));
			if(!StringUtils.trimToEmpty(request.getParameter("transformerCapacity")).isEmpty()){
				station.setTransformerCapacity(Double.parseDouble(request.getParameter("transformerCapacity")));
			}
			station.setPowerElement(StringUtils.trimToEmpty(request.getParameter("powerElement")));
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
			station.setStationFullCode(entityCode);
			//执行数据库操作
			if(station.getId() != null){
				String flowId = request.getParameter("flowId"); 
				if(!flowId.equals("0")){
					station.setApproveStatus("0");
				}
				
				int cnt = dao.modifyStation(station);
				if(cnt > 0){
					msg = "修改实体 " + station.getJzName() + " 成功！";
					
					
					
					//添加流程
				 	if(!flowId.equals("0")){
				 		WorkFlowBean workFlowBean= new WorkFlowBean();
					 	//String flowId = request.getParameter("flowId"); 
						String auditorid = request.getParameter("auditorid");
						System.out.println("auditorid2 "+auditorid);
					 	String taskType="ZHANDIAN,0";//区分流程 0修改
					 	String taskId=station.getId()+"";
					 	String currentNo =workFlowBean.getNextStep(flowId, "1");
					 	String nextNo =workFlowBean.getNextStep(flowId, "2");
					 	workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
				 	}
				 	
				}
				
				
				
			}else{
				int cnt = dao.addStation(station);
				if(cnt > 0){
					msg = "添加实体 " + station.getJzName() + " 成功！";
				}
				
			}
			
			log.write(msg, account.getAccountName(), request.getRemoteAddr(), "实体维护");
			session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            response.sendRedirect(path + "/web/msg.jsp");
		}else if("del".equals(action)){
			String id = request.getParameter("id");
			int cnt = 0;
			if(id !=null && !id.isEmpty()){
				cnt = dao.deleteById(Integer.parseInt(id));
			}
			if(cnt > 0){
				msg = "删除实体信息成功";
			}else{
				msg = "删除实体信息失败";
			}
			log.write(msg, account.getAccountName(), request.getRemoteAddr(), "实体维护");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}else if("sub".equals(action)){
			
			//添加流程
		 	WorkFlowBean workFlowBean= new WorkFlowBean();
		 	String flowId = request.getParameter("flowId"); 
			String auditorid = request.getParameter("auditorid");
			System.out.println("auditorid1 "+auditorid);
		 	String taskType="ZHANDIAN,1";//区分流程 0修改
		 	String taskId=request.getParameter("taskId");;
		 	String currentNo =workFlowBean.getNextStep(flowId, "1");
		 	String nextNo =workFlowBean.getNextStep(flowId, "2");
		 	int ret=workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
		 	if(ret>0){
		 		msg="提交成功！";
		 	}else{
		 		msg="提交失败！请重试或与管理员联系";
		 	}
		 	Station station=dao.getOne(Integer.parseInt(taskId));
		 	station.setApproveStatus("0");
		 	dao.modifyStation(station);
		 	
		 	log.write(msg, account.getAccountName(), request.getRemoteAddr(), "提交审核");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		
		
	}

}
