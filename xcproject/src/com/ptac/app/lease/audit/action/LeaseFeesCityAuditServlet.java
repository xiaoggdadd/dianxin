package com.ptac.app.lease.audit.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.lease.audit.bean.LeasefeesCityAudit;
import com.ptac.app.lease.audit.dao.LeaseFeesCityAuditDao;
import com.ptac.app.lease.audit.dao.LeaseFeesCityAuditDaoImpl;
import com.ptac.app.lease.query.bean.LeasefeesQuery;
import com.ptac.app.lease.query.dao.LeaseFeesQueryDao;
import com.ptac.app.lease.query.dao.LeaseFeesQueryDaoImpl;

/** 租赁费用区县审核
 * @author WangYiXiao 2014-9-24
 *
 */
public class LeaseFeesCityAuditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String action = request.getParameter("action");
		if("chaxun".equals(action)){//查询
			doChaXun(request, response);
		}else if("tongguo".equals(action) || "tongguoajax".equals(action)//通过
				|| "butongguo".equals(action) || "butongguoajax".equals(action)//不通过
				|| "quxiao".equals(action) || "quxiaoajax".equals(action)){//取消审核
			doCheck(request, response);
		}
	}
	/** 查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doChaXun(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LeaseFeesCityAuditDao dao = new LeaseFeesCityAuditDaoImpl();
	    Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String shi = request.getParameter("shi") ;// 城市
		String xian = request.getParameter("xian");// 区县
		String xiaoqu = request.getParameter("xiaoqu");// 乡镇
		String jzname = request.getParameter("zdbh");// 站点名称
		String htmc = request.getParameter("htmc");//租赁合同名称
		String accountmonth = request.getParameter("accountmonth");//报账月份
		String auditstatus = request.getParameter("shenhestatus");//市级审核状态
		String whereStr = "";
		if(shi != null){
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN ='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
		}
		if (jzname != null && jzname != "") {
			whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
		}
		if (htmc != null && htmc != "") {
			whereStr = whereStr + " AND LG.LEASENAME LIKE '%" + htmc + "%'";
		}
		if (accountmonth != null && !accountmonth.equals("")) {
			whereStr = whereStr + " AND LF.ACCOUNTMONTH ='" + accountmonth + "'";
		}
		if(auditstatus !=null &&! "-1".equals(auditstatus)){
			whereStr = whereStr + " AND LF.CITYAUDITSTATUS ='" + auditstatus + "'";
		}
		List<LeasefeesCityAudit> list = new ArrayList<LeasefeesCityAudit>(); 
		list = dao.getCityAuditLeaseFees(whereStr, loginId);
		int num = list.size();//结果条数
		request.setAttribute("num", num);//结果条数
		request.setAttribute("list", list);//结果集
		request.getRequestDispatcher("/web/appJSP/lease/audit/leaseFeesCityAudit.jsp")
				.forward(request, response);
	}
	
	
	
	/** 审核
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doCheck(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//审核人员
		String path = request.getContextPath();//根路径
		String url = "",msg = "";
		PrintWriter out = response.getWriter();
		DbLog log = new DbLog();//日志
		LeaseFeesCityAuditDao dao = new LeaseFeesCityAuditDaoImpl();
		String action = request.getParameter("action");
		if("tongguo".equals(action)){//通过正常部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//租赁费用id
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
		  	String audit = "1";//通过
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"1");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "租赁费用县级审核通过"); 
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    response.sendRedirect(path + "/web/msg.jsp");
		}else if("tongguoajax".equals(action)){//通过ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
			  	String audit = "1";//通过
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"1");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "租赁费用县级审核通过");
		    String m="";
		    if(msg=="审核租赁费用信息失败！"){
		    	m="0";
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		    }else{
		    	m="1";
		    }
		    out.print(m);
		    out.close();
		}else if("butongguo".equals(action)){//不通过正常部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//租赁费用id
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
		  	String audit = "2";//不通过
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"2");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "租赁费用县级审核不通过"); 
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    response.sendRedirect(path + "/web/msg.jsp");
		}else if("butongguoajax".equals(action)){//不通过ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
			  	String audit = "2";//不通过
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"2");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "租赁费用县级审核不通过");
		    String m="";
		    if(msg=="审核租赁费用信息失败！"){
		    	m="0";
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		    }else{
		    	m="1";
		    }
		    out.print(m);
		    out.close();
		}else if("quxiao".equals(action)){//取消审核正常部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//租赁费用id
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
		  	String audit = "0";//通过
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"0");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "租赁费用县级取消审核"); 
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    response.sendRedirect(path + "/web/msg.jsp");
		}else if("quxiaoajax".equals(action)){//取消审核ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
			  	String audit = "0";//通过
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"0");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "租赁费用县级取消审核");
		    String m="";
		    if(msg=="审核租赁费用信息失败！"){
		    	m="0";
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		    }else{
		    	m="1";
		    }
		    out.print(m);
		    out.close();
		}

}
	

}
