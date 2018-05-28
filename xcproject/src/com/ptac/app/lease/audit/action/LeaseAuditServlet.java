package com.ptac.app.lease.audit.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.lease.audit.dao.LeaseAuditDao;
import com.ptac.app.lease.audit.dao.LeaseAuditDaoImp;

/**
 * @author lijing
 * @see 租赁合同审核查询
 */
public class LeaseAuditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
	    Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";// 城市
		String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "";// 区县
		String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "";// 乡镇
		String leasename = request.getParameter("leasename") != null ? request.getParameter("leasename") : "";// 合同名称
		String zdname = request.getParameter("zdname") != null ? request.getParameter("zdname"): "";// 站点名称
		
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " and zd.shi='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " and zd.xian='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " and zd.xiaoqu='" + xiaoqu + "'";
		}
		if (leasename != null && leasename != "") {
			whereStr = whereStr + " and l.LEASENAME like '%" + leasename + "%'";
		}
		if (zdname != null && zdname != "") {
			whereStr = whereStr + " and zd.JZNAME like '%" + zdname + "%'";
		}
		
		ArrayList list = new ArrayList();
		LeaseAuditDao dao = new LeaseAuditDaoImp();
		list = dao.queryZlMsg(whereStr,loginId);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		
		String command = request.getParameter("command");
		if ("chaxun".equals(command)) {// 传到租赁合同审核查询页面
			request.getRequestDispatcher("/web/appJSP/lease/audit/leaseAudit.jsp")
					.forward(request, response);
		}
	}

}
