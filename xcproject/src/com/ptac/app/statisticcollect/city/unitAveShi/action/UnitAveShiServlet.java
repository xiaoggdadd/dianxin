package com.ptac.app.statisticcollect.city.unitAveShi.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.city.unitAveShi.bean.UnitAveShiBean;
import com.ptac.app.statisticcollect.city.unitAveShi.dao.UnitAveShiDao;
import com.ptac.app.statisticcollect.city.unitAveShi.dao.UnitAveShiDaoImp;

/**
 * @see 市级单价平均与平均单价.Servlet
 * @author ZengJin 2014-3-30 
 */
public class UnitAveShiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ------获取登录账户信息------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		// ------获取前台按钮标识------
		String command = request.getParameter("command");
		// ------获取前台限制条件------
		String shi = request.getParameter("shi");// 市
		String xian = request.getParameter("xian");// 区县
		String gdfs = request.getParameter("gdfs");// 供电方式
		String zdsx = request.getParameter("jzproperty");// 站点属性
		String bzyfstart = request.getParameter("bzyfstart");// 报账月份起
		String bzyfend = request.getParameter("bzyfend");// 报账月份止

		String autoauditstatus = request.getParameter("autoauditstatus");// 自动审核状态
		String manualauditstatus = request.getParameter("manauditstatus");// 人工审核状态
		String countryheadstatus = request.getParameter("countryheadstatus");// 区县审核标志
		String cityaudit = request.getParameter("citystatus");// 市级审核状态
		String cityzrauditstatus = request.getParameter("cityzrauditstatus");// 市级主任审核状态

		// ------根据获取的值拼接sql查询语句-----
		String whereStr = "";
		
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
		}
		
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
		}

		if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")) {
			whereStr = whereStr + " AND ZD.GDFS='" + gdfs + "'";
		}

		if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
			whereStr = whereStr + " AND ZD.PROPERTY='" + zdsx + "'";
		}

		if (autoauditstatus != null && !autoauditstatus.equals("") && !autoauditstatus.equals("5")) {
			whereStr = whereStr + " AND E.AUTOAUDITSTATUS='" + autoauditstatus + "'";
		}

		if (manualauditstatus != null && !manualauditstatus.equals("") && !manualauditstatus.equals("5")) {
			if ("1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS>='" + manualauditstatus + "'";
			}
			if ("2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + manualauditstatus + "'";
			}
			if ("0".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + manualauditstatus + "'";
			}
			if ("-2".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + manualauditstatus + "'";
			}
			if ("-1".equals(manualauditstatus)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS<'2'";
			}
		}
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("5")) {
			whereStr = whereStr + " AND E.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}

		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("5")) {
			whereStr = whereStr + " AND E.CITYAUDIT='" + cityaudit + "'";
		}
		if (cityzrauditstatus != null && !cityzrauditstatus.equals("") && !cityzrauditstatus.equals("5")) {
			whereStr = whereStr + " AND E.CITYZRAUDITSTATUS='" + cityzrauditstatus + "'";
		}

		if (bzyfstart != null && !bzyfstart.equals("") && !bzyfstart.equals("0") 
		 && bzyfend != null && !bzyfend.equals("") && !bzyfend.equals("0")
		 && shi != null && !shi.equals("") && !shi.equals("0")) {

			// ------获得结果集和结果条数------
			UnitAveShiDao dao = new UnitAveShiDaoImp();

			List<UnitAveShiBean> list = new ArrayList<UnitAveShiBean>();

			list = dao.queryElectric(whereStr, loginId, shi, bzyfstart, bzyfend);//结果集
			int num = list.size();//结果条数

			// ------向前台页面传值------

			request.setAttribute("num", num);// 结果条数
			request.setAttribute("list", list);// 结果集
			// ------根据前台按钮标识判断提交方向
			if ("chaxun".equals(command)) {// 传到查询页面
				request.getRequestDispatcher(
						"/web/appJSP/statisticscollect/city/unitAveShi/unitAveShiQuery.jsp")
						.forward(request, response);
			} else if ("daochu".equals(command)) {// 传到导出页面
				request.getRequestDispatcher(
						"/web/appJSP/statisticscollect/city/unitAveShi/unitAveShiExport.jsp")
						.forward(request, response);
			}
		}
	}
}
