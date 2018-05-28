package com.ptac.app.electricmanage.bargainbill.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillMessageBean;
import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSelectConditionsBean;
import com.ptac.app.electricmanage.bargainbill.dao.BargainBillMethodsDAO;
import com.ptac.app.electricmanage.bargainbill.dao.Imp.BargainBillMethodsDAOImp;

/**
 * ���к�ͬ����ѯ��Servlet
 * 
 * @author rock
 * 
 */
@SuppressWarnings("serial")
public class BargainBillSelectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��Ա
		Account account = new Account();
		HttpSession session = request.getSession();
		account = (Account) session.getAttribute("account");
		String loginid = account.getAccountId();//�����ID
		

		/* ��ȡ��ѯ���� */
		String shi = request.getParameter("shi");
		String xian = request.getParameter("xian");
		String xiaoqu = request.getParameter("xiaoqu");
		String zdmc = request.getParameter("zdname");
		String qssj = request.getParameter("startmonth");
		String jssj = request.getParameter("endmonth");
		String zdlx = request.getParameter("stationtype");
		String bzyf = request.getParameter("bzyf");
		String qyzt = request.getParameter("qyzt");

		/* ��װJavaBean */
		BargainBillSelectConditionsBean bbscb = new BargainBillSelectConditionsBean(
				shi, xian, xiaoqu, zdmc, qssj, jssj, zdlx, bzyf,qyzt);
		
		/*���в�ѯ*/
		BargainBillMethodsDAO dao = new BargainBillMethodsDAOImp();
		List<BargainBillMessageBean> ls = dao.findBargainBills(bbscb, loginid);
		
		
		request.setAttribute("sum", ls.size());
		request.setAttribute("Info", ls);
		request.setAttribute("bean", bbscb);
		
		request.getRequestDispatcher("/web/appJSP/electricmanage/bargainbill/mainBargainBill.jsp").forward(request, response);

	}

}
