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

/** ���޷����������
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
		if("chaxun".equals(action)){//��ѯ
			doChaXun(request, response);
		}else if("tongguo".equals(action) || "tongguoajax".equals(action)//ͨ��
				|| "butongguo".equals(action) || "butongguoajax".equals(action)//��ͨ��
				|| "quxiao".equals(action) || "quxiaoajax".equals(action)){//ȡ�����
			doCheck(request, response);
		}
	}
	/** ��ѯ
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
		String shi = request.getParameter("shi") ;// ����
		String xian = request.getParameter("xian");// ����
		String xiaoqu = request.getParameter("xiaoqu");// ����
		String jzname = request.getParameter("zdbh");// վ������
		String htmc = request.getParameter("htmc");//���޺�ͬ����
		String accountmonth = request.getParameter("accountmonth");//�����·�
		String auditstatus = request.getParameter("shenhestatus");//�м����״̬
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
		int num = list.size();//�������
		request.setAttribute("num", num);//�������
		request.setAttribute("list", list);//�����
		request.getRequestDispatcher("/web/appJSP/lease/audit/leaseFeesCityAudit.jsp")
				.forward(request, response);
	}
	
	
	
	/** ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doCheck(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//�����Ա
		String path = request.getContextPath();//��·��
		String url = "",msg = "";
		PrintWriter out = response.getWriter();
		DbLog log = new DbLog();//��־
		LeaseFeesCityAuditDao dao = new LeaseFeesCityAuditDaoImpl();
		String action = request.getParameter("action");
		if("tongguo".equals(action)){//ͨ����������
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���޷���id
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
		  	String audit = "1";//ͨ��
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"1");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "���޷����ؼ����ͨ��"); 
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    response.sendRedirect(path + "/web/msg.jsp");
		}else if("tongguoajax".equals(action)){//ͨ��ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
			  	String audit = "1";//ͨ��
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"1");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "���޷����ؼ����ͨ��");
		    String m="";
		    if(msg=="������޷�����Ϣʧ�ܣ�"){
		    	m="0";
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		    }else{
		    	m="1";
		    }
		    out.print(m);
		    out.close();
		}else if("butongguo".equals(action)){//��ͨ����������
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���޷���id
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
		  	String audit = "2";//��ͨ��
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"2");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "���޷����ؼ���˲�ͨ��"); 
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    response.sendRedirect(path + "/web/msg.jsp");
		}else if("butongguoajax".equals(action)){//��ͨ��ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
			  	String audit = "2";//��ͨ��
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"2");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "���޷����ؼ���˲�ͨ��");
		    String m="";
		    if(msg=="������޷�����Ϣʧ�ܣ�"){
		    	m="0";
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		    }else{
		    	m="1";
		    }
		    out.print(m);
		    out.close();
		}else if("quxiao".equals(action)){//ȡ�������������
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���޷���id
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
		  	String audit = "0";//ͨ��
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"0");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "���޷����ؼ�ȡ�����"); 
		    session.setAttribute("url", url);
		    session.setAttribute("msg", msg);
		    response.sendRedirect(path + "/web/msg.jsp");
		}else if("quxiaoajax".equals(action)){//ȡ�����ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
			url = path + "/web/appJSP/lease/audit/leaseFeesCityAudit.jsp" ;
			  	String audit = "0";//ͨ��
		    msg = dao.CheckCityFees(personnal,audit,chooseIdStr1,"0");
		    log.write(msg, account.getName(), request.getRemoteAddr(), "���޷����ؼ�ȡ�����");
		    String m="";
		    if(msg=="������޷�����Ϣʧ�ܣ�"){
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
