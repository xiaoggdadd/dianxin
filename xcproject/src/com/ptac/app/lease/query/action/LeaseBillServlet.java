package com.ptac.app.lease.query.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.lease.query.dao.LeaseBillDao;
import com.ptac.app.lease.query.dao.LeaseBillDaoImp;

/**
 * @author lijing
 * @see 租赁合同维护查询、导出
 */
public class LeaseBillServlet extends HttpServlet {

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
		String leasename = request.getParameter("leasename") != null ? request.getParameter("leasename") : "";// 租赁合同名称
		String leasername = request.getParameter("leasername") != null ? request.getParameter("leasername"): "";// 出租方姓名
		
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
		if (leasername != null && leasername != "") {
			whereStr = whereStr + " and l.LEASERNAME like '%" + leasername + "%'";
		}
		
		ArrayList list = new ArrayList();
		LeaseBillDao dao = new LeaseBillDaoImp();
		list = dao.queryZlMsg(whereStr,loginId);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		
		String command = request.getParameter("command");
		if ("chaxun".equals(command)) {// 传到租赁合同维护查询页面
			request.getRequestDispatcher("/web/appJSP/lease/query/leaseBill.jsp")
					.forward(request, response);
		} //else if ("daochu".equals(request.getParameter("command"))) {// 传到租赁合同维护导出页面
			//request.getRequestDispatcher("")
			//		.forward(request, response);
		//}
	
	}

}
