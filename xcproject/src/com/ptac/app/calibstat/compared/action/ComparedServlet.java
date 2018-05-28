package com.ptac.app.calibstat.compared.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.compared.bean.ComparedBean;
import com.ptac.app.calibstat.compared.dao.ComparedDao;
import com.ptac.app.calibstat.compared.dao.ComparedDaoImp;

/**
 * @author 李靖
 * @see 建议生产标与生产标对比查询（明细查询、导出）
 */
public class ComparedServlet extends HttpServlet {

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
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";// 城市
		String zdsx = request.getParameter("jzproperty") != null ? request.getParameter("jzproperty") : "";//站点属性
		String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "";//站点类型
		String beginScb = request.getParameter("beginScb") != null?request.getParameter("beginScb") : "";//起始生产标
		String endScb = request.getParameter("endScb") != null?request.getParameter("endScb") : "";//结束生产标
		String beginJyscb = request.getParameter("beginJyscb") != null?request.getParameter("beginJyscb") : "";//起始建议生产标
		String endJyscb = request.getParameter("endJyscb") != null?request.getParameter("endJyscb") : "";//结束建议生产标
		String xcbl = request.getParameter("xcbl") != null?request.getParameter("xcbl") : "";//相差比例
			
		String str = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			str = str + " AND ZD.SHI='" + shi + "'";
		}
		if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
			str = str + " AND ZD.ZDSX='" + zdsx + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			str = str + " AND Z.STATIONTYPE IN('" + zdlx + "')";
		}
		if (beginScb != null && !beginScb.equals("") && !beginScb.equals("0")) {
			str = str + " AND ZD.SCB >= '" + beginScb + "'";
		}
		if (endScb != null && !endScb.equals("") && !endScb.equals("0")) {
			str = str + " AND ZD.SCB <= '" + endScb + "'";
		}
		if (beginJyscb != null && !beginJyscb.equals("") && !beginJyscb.equals("0")) {
			str = str + " AND ZD.JYSCB >= '" + beginJyscb + "'";
		}
		if (endJyscb != null && !endJyscb.equals("") && !endJyscb.equals("0")) {
			str = str + " AND ZD.JYSCB <= '" + endJyscb + "'";
		}
		if (xcbl != null && !xcbl.equals("")) {
			double xcbl1 = Double.parseDouble(xcbl);
			str = str + " AND (ZD.SCB-ZD.JYSCB)/ZD.JYSCB*100 <= " + xcbl1;
		}
		
		List<ComparedBean> list = new ArrayList<ComparedBean>();
		ComparedDao dao = new ComparedDaoImp();
		list = dao.queryExport(str,loginId);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
			
		String action = request.getParameter("command");
		if ("chaxun".equals(action)) {//查询页面
			request.getRequestDispatcher("/web/appJSP/calibstat/compared/compared.jsp")
					.forward(request, response);
		}else if ("daochu".equals(request.getParameter("command"))) {//导出页面
			request.getRequestDispatcher("/web/appJSP/calibstat/compared/对比查询.jsp")
					.forward(request, response);
		}
	}
}
