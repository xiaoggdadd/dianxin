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
 * @author �
 * @see ������������������ԱȲ�ѯ����ϸ��ѯ��������
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
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";// ����
		String zdsx = request.getParameter("jzproperty") != null ? request.getParameter("jzproperty") : "";//վ������
		String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "";//վ������
		String beginScb = request.getParameter("beginScb") != null?request.getParameter("beginScb") : "";//��ʼ������
		String endScb = request.getParameter("endScb") != null?request.getParameter("endScb") : "";//����������
		String beginJyscb = request.getParameter("beginJyscb") != null?request.getParameter("beginJyscb") : "";//��ʼ����������
		String endJyscb = request.getParameter("endJyscb") != null?request.getParameter("endJyscb") : "";//��������������
		String xcbl = request.getParameter("xcbl") != null?request.getParameter("xcbl") : "";//������
			
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
		if ("chaxun".equals(action)) {//��ѯҳ��
			request.getRequestDispatcher("/web/appJSP/calibstat/compared/compared.jsp")
					.forward(request, response);
		}else if ("daochu".equals(request.getParameter("command"))) {//����ҳ��
			request.getRequestDispatcher("/web/appJSP/calibstat/compared/�ԱȲ�ѯ.jsp")
					.forward(request, response);
		}
	}
}
