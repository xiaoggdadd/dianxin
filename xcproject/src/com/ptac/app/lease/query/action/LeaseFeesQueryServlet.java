package com.ptac.app.lease.query.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.lease.query.bean.Leasebargainfees;
import com.ptac.app.lease.query.bean.LeasefeesQuery;
import com.ptac.app.lease.query.dao.LeaseFeesQueryDao;
import com.ptac.app.lease.query.dao.LeaseFeesQueryDaoImpl;

public class LeaseFeesQueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LeaseFeesQueryDao dao = new LeaseFeesQueryDaoImpl();
		String action = request.getParameter("action");
		if("chaxun".equals(action) || "daochu".equals(action)){
		    Account account = (Account)session.getAttribute("account");
			String loginId = account.getAccountId();
			String shi = request.getParameter("shi") ;// ����
			String xian = request.getParameter("xian");// ����
			String xiaoqu = request.getParameter("xiaoqu");// ����
			String jzname = request.getParameter("zdbh");// վ������
			String htmc = request.getParameter("htmc");//���޺�ͬ����
			String accountmonth = request.getParameter("accountmonth");//�����·�
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
			List<LeasefeesQuery> list = new ArrayList<LeasefeesQuery>(); 
			list = dao.getLeasefeesQuery(whereStr, loginId);
			int num = list.size();//�������
			request.setAttribute("num", num);//�������
			request.setAttribute("list", list);//�����
			if ("chaxun".equals(action)) {// �������޷��ò�ѯҳ��
				request.getRequestDispatcher("/web/appJSP/lease/query/leaseFeesQuery.jsp")
						.forward(request, response);
			} else if ("daochu".equals(action)) {// �������޷��õ���ҳ��
				request.getRequestDispatcher("/web/appJSP/lease/query/���޷�����ϸ����.jsp")
						.forward(request, response);
			}
		}
	}

}
