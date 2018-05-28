package com.ptac.app.datastatistics.powermode.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.datastatistics.powermode.bean.PowerBean;
import com.ptac.app.datastatistics.powermode.dao.PowerDao;
import com.ptac.app.datastatistics.powermode.dao.PowerDaoImp;

/**
 * @author 李靖
 * @see 供电方式缴费统计（查询、导出）
 */
public class PowerServlet extends HttpServlet {

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
		
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String zdsx = request.getParameter("zdsx");
		String gdfs = request.getParameter("gdfs");//供电方式
		String sjzrsh = request.getParameter("sjzrsh");//市级主任审核状态
		String cwzt = request.getParameter("manauditstatus");//财务审核状态,
		String beginTime = request.getParameter("beginTime");//起始报账月份
		String endTime = request.getParameter("endTime");//结束报账月份
		
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
		if(zdsx != null && !"".equals(zdsx) && !"0".equals(zdsx)){
			whereStr = whereStr+" AND ZD.PROPERTY='"+zdsx+"' ";
		}
		if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")) {
			whereStr = whereStr + " AND ZD.GDFS='" + gdfs + "'";
		}
		if (sjzrsh != null && !sjzrsh.equals("") && !sjzrsh.equals("-1")) {
			whereStr = whereStr + " AND E.CITYZRAUDITSTATUS='" + sjzrsh + "'";
		}
		if (beginTime != null && !beginTime.equals("") && !beginTime.equals("0")) {
			whereStr = whereStr + " AND to_char(E.ACCOUNTMONTH,'yyyy-mm')>='" + beginTime + "'";
		}
		if (endTime != null && !endTime.equals("") && !endTime.equals("0")) {
			whereStr = whereStr + " AND to_char(E.ACCOUNTMONTH,'yyyy-mm')<='" + endTime + "'";
		}
		if (cwzt != null && !cwzt.equals("") && !cwzt.equals("3")) {
			if ("1".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS>='" + cwzt + "'";
			}
			if ("2".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + cwzt + "'";
			}
			if ("0".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + cwzt + "'";
			}
			if ("-2".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS='" + cwzt + "'";
			}
			if ("-1".equals(cwzt)) {
				whereStr = whereStr + " AND E.MANUALAUDITSTATUS<'2' ";
			}
		}
			
		ArrayList<PowerBean> list = new ArrayList<PowerBean>();
		PowerDao dao = new PowerDaoImp();
		list = dao.queryExport(whereStr,loginId);
		int num = list.size();
		
		PowerBean total = new PowerBean();
		Double totalMoney = 0.00;
		if(list != null){
			total = dao.totalMoney(list);
			totalMoney = total.getTotalmoney();
		}
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.setAttribute("total",totalMoney);
			
		String action = request.getParameter("command");//获取要做的操作
		if ("chaxun".equals(action)) {// 传到明细查询页面
			request.getRequestDispatcher("/web/appJSP/datastatistics/powermode/powermode.jsp")
					.forward(request, response);
		}else if ("daochu".equals(request.getParameter("command"))) {// 传到明细导出页面
			request.getRequestDispatcher("/web/appJSP/datastatistics/powermode/供电方式缴费统计导出.jsp")
					.forward(request, response);
		}
	}
}
