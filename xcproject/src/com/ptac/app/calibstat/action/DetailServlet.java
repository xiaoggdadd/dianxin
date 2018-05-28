package com.ptac.app.calibstat.action;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.dao.CalibStatDao;
import com.ptac.app.calibstat.dao.CalibStatDaoImp;

/**
 * @author �
 * @see ����ͳ�Ʋ�ѯ����ϸ��ѯ��������
 */
public class DetailServlet extends HttpServlet {

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
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";// ����
		String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "";// ����
		String zdsx = request.getParameter("zdsx") != null ? request.getParameter("zdsx") : "";//վ������
		String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "";//վ������
		String year = request.getParameter("year") != null ? request.getParameter("year"): "";//���
			
		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " and ZD.shi='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " and ZD.xain='" + xian + "'";
		}
		if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
			whereStr = whereStr + " and ZD.zdsx='" + zdsx + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			whereStr = whereStr + " AND Z.STATIONTYPE IN('" + zdlx + "')";
		}
		if (year != null && !year.equals("") && !year.equals("0")) {
			whereStr = whereStr + " and ZD.year='" + year + "'";
		}
			
		ArrayList list = new ArrayList();
		CalibStatDao dao = new CalibStatDaoImp();
		list = dao.queryMingXi(whereStr,loginId);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
			
		String action = request.getParameter("command");//��ȡҪ���Ĳ���
		if ("chaxun".equals(action)) {// ������ϸ��ѯҳ��
			request.getRequestDispatcher("/web/appJSP/calibstat/detail/detail.jsp")
					.forward(request, response);
		}else if ("daochu".equals(request.getParameter("command"))) {// ������ϸ����ҳ��
			request.getRequestDispatcher("/web/appJSP/calibstat/detail/��ϸ��ѯ����.jsp")
					.forward(request, response);
		}
	}
}
