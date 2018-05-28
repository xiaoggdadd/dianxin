package com.ptac.app.datastatistics.monitoringshare.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.datastatistics.monitoringshare.bean.MonShareBean;
import com.ptac.app.datastatistics.monitoringshare.dao.MonShareDao;
import com.ptac.app.datastatistics.monitoringshare.dao.MonShareDaoImp;

/**
 * @author 李靖
 * @see 监测点分摊（查询、导出）
 */
public class MonShareServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");	
		String xiaoqu = request.getParameter("xiaoqu");	
		String zdname = request.getParameter("zdname");
		String zdsx = request.getParameter("zdsx");
		String zdlx = request.getParameter("zdlx");
		String qyzt = request.getParameter("qyzt");
		String dbqyzt = request.getParameter("dbqyzt");
		
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
		}
		if (zdname != null && zdname != "") {
			whereStr = whereStr + " AND ZD.JZNAME like '%" + zdname + "%'";
		}
		if(zdsx != null && !"".equals(zdsx) && !"0".equals(zdsx)){
			whereStr = whereStr+" AND ZD.PROPERTY='"+zdsx+"' ";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			whereStr = whereStr + " AND ZD.STATIONTYPE IN('" + zdlx + "')";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")) {
			whereStr = whereStr + " AND ZD.QYZT='" + qyzt + "'";
		}
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")) {
			whereStr = whereStr + " AND D.DBQYZT='" + dbqyzt + "'";
		}
			
		ArrayList<MonShareBean> list = new ArrayList<MonShareBean>();
		MonShareDao dao = new MonShareDaoImp();
		list = dao.queryExport(whereStr,loginId);
		int num = list.size();
		
		request.setAttribute("num", num);
		request.setAttribute("list", list);
			
		String action = request.getParameter("command");//获取要做的操作
		if ("chaxun".equals(action)) {// 传到明细查询页面
			request.getRequestDispatcher("/web/appJSP/datastatistics/monitoringshare/monitoringshare.jsp")
					.forward(request, response);
		}else if ("daochu".equals(request.getParameter("command"))) {// 传到明细导出页面
			request.getRequestDispatcher("/web/appJSP/datastatistics/monitoringshare/监测点分摊导出.jsp")
					.forward(request, response);
		}
	}

}
