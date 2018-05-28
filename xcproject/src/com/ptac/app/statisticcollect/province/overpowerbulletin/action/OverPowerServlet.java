package com.ptac.app.statisticcollect.province.overpowerbulletin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.province.overpowerbulletin.bean.OverPowerBean;
import com.ptac.app.statisticcollect.province.overpowerbulletin.dao.OverPowerDao;
import com.ptac.app.statisticcollect.province.overpowerbulletin.dao.OverPowerDaoImp;

/**
 * @author lijing
 * @see �������ͨ��
 */
public class OverPowerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String bztime = request.getParameter("bztime");//�����·�
		String property = request.getParameter("property");//վ������
		String status = request.getParameter("status");//���״̬

		StringBuffer whereStr = new StringBuffer();//��ѯ����
		
		if (bztime != null && bztime != "" && !bztime.equals("0")){
			whereStr.append(" AND to_char(ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'");
		}
		if ("1".equals(status)){
			whereStr.append(" AND CITYZRAUDITSTATUS='" + status + "'");	
		}else if("2".equals(status)){
			whereStr.append(" AND EFMANUALAUDITSTATUS='" + status + "'");
		}
		
		if (property != null && !property.equals("") && !property.equals("0")){
			whereStr.append(" AND ZPROPERTY='" + property + "'");
		}
		
		OverPowerDao dao = new OverPowerDaoImp();
		List<OverPowerBean> list = dao.queryExport(whereStr.toString(), loginId);
		int num = list.size();
		String[] sum = dao.getSum(list);
		OverPowerBean sumbean = new OverPowerBean();
		
		sumbean.setZbzdlsum(sum[0]);
		sumbean.setCbdlsum(sum[1]);
		sumbean.setCbzbsum(sum[2]);
		
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.setAttribute("sumbean", sumbean);
		
		String command = request.getParameter("command");
		if("chaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/overpowerbulletin/overpower.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/overpowerbulletin/export.jsp").forward(request, response);
		}
	}
}
