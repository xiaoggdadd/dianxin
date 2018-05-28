package com.ptac.app.businesshall.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.businesshall.bean.Businesshall;
import com.ptac.app.businesshall.dao.BusinesshallDao;
import com.ptac.app.businesshall.dao.BusinesshallDaoImpl;

public class BusinesshallServlet extends HttpServlet {

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
		BusinesshallDao dao = new BusinesshallDaoImpl();
		String path = request.getContextPath();
		String msg = "";
		String url = path + "/web/sdttWeb/jichuInfoManager/businesshallManager.jsp";
		if(action.equals("save")){
			Businesshall businesshall = new Businesshall();
			if(!StringUtils.trimToEmpty(request.getParameter("id")).isEmpty()){
				businesshall.setId(Integer.parseInt(request.getParameter("id")));
			}
			businesshall.setCompany(StringUtils.trimToEmpty(request.getParameter("company")));
			businesshall.setBranchCompany(StringUtils.trimToEmpty(request.getParameter("branchCompany")));
			businesshall.setEntityCode(StringUtils.trimToEmpty(request.getParameter("entityCode")));
			businesshall.setEntityName(StringUtils.trimToEmpty(request.getParameter("entityName")));
			businesshall.setEntityType(StringUtils.trimToEmpty(request.getParameter("entityType")));
			businesshall.setEntitySmallType(StringUtils.trimToEmpty(request.getParameter("entitySmallType")));
			businesshall.setAddress(StringUtils.trimToEmpty(request.getParameter("address")));
			businesshall.setPracticalUse(StringUtils.trimToEmpty(request.getParameter("practicalUse")));
			businesshall.setOwnership(StringUtils.trimToEmpty(request.getParameter("ownership")));
			businesshall.setRightOfUse(StringUtils.trimToEmpty(request.getParameter("rightOfUse")));
			businesshall.setManageDepartment(StringUtils.trimToEmpty(request.getParameter("manageDepartment")));
			businesshall.setPersonLiable(StringUtils.trimToEmpty(request.getParameter("personLiable")));
			if(!StringUtils.trimToEmpty(request.getParameter("builtArea")).isEmpty()){
				businesshall.setBuiltArea(Double.parseDouble(request.getParameter("builtArea")));
			}
			businesshall.setBuildingStructure(StringUtils.trimToEmpty(request.getParameter("buildingStructure")));
			if(!StringUtils.trimToEmpty(request.getParameter("isAttachedEntity")).isEmpty()){
				businesshall.setAttachedEntity(Short.parseShort(request.getParameter("isAttachedEntity")));
			}
			businesshall.setBaseStation(StringUtils.trimToEmpty(request.getParameter("baseStation")));
			if(!StringUtils.trimToEmpty(request.getParameter("status")).isEmpty()){
				businesshall.setStatus(Short.parseShort(request.getParameter("status")));
			}
			businesshall.setOrganization(StringUtils.trimToEmpty(request.getParameter("organization")));
			businesshall.setCertificateNumber(StringUtils.trimToEmpty(request.getParameter("certificateNumber")));
			businesshall.setHouseValue(StringUtils.trimToEmpty(request.getParameter("houseValue")));
			businesshall.setSharedOrganization(StringUtils.trimToEmpty(request.getParameter("sharedOrganization")));
			if(!StringUtils.trimToEmpty(request.getParameter("numberOfEmployees")).isEmpty()){
				businesshall.setNumberOfEmployees(Integer.parseInt(request.getParameter("numberOfEmployees")));
			}
			businesshall.setElectricCurrent(StringUtils.trimToEmpty(request.getParameter("electricCurrent")));
			businesshall.setPeripheralSystemCode(StringUtils.trimToEmpty(request.getParameter("peripheralSystemCode")));
			businesshall.setSharedProperty(StringUtils.trimToEmpty(request.getParameter("sharedProperty")));
			businesshall.setEnvironment(StringUtils.trimToEmpty(request.getParameter("environment")));
			if(!StringUtils.trimToEmpty(request.getParameter("approveStatus")).isEmpty()){
				businesshall.setApproveStatus(Short.parseShort(request.getParameter("approveStatus")));
			}
			businesshall.setCreater(account.getAccountName());
			businesshall.setCreateDate(new Date());
			
			//执行数据库操作
			if(businesshall.getId() !=null){
				int cnt = dao.modifyBusinesshall(businesshall);
				if(cnt > 0){
					msg = "修改实体 " + businesshall.getEntityName() + " 成功！";
				}
			}else{
				int cnt = dao.addBusinesshall(businesshall);
				if(cnt > 0){
					msg = "添加实体 " + businesshall.getEntityName() + " 成功！";
				}
			}
			
			log.write(msg, account.getAccountName(), request.getRemoteAddr(), "实体维护");
			session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            response.sendRedirect(path + "/web/msg.jsp");
		}else if(action.equals("del")){
			String id = request.getParameter("id");
			if(id !=null && !id.isEmpty()){
				int cnt = dao.deleteBusinesshallById(Integer.parseInt(id));
				if(cnt > 0){
					msg = "删除实体成功！";
				}
				log.write(msg, account.getAccountName(), request.getRemoteAddr(), "实体维护");
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");
			}
			
		}else{
			response.sendRedirect(url);
		}
		
	}
	

}
