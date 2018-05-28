package com.ptac.app.electricmanage.bargainbill.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptac.app.electricmanage.bargainbill.dao.BargainBillMethodsDAO;
import com.ptac.app.electricmanage.bargainbill.dao.Imp.BargainBillMethodsDAOImp;

/**
 * 删除合同单的Servlet
 * @author rock
 *
 */
@SuppressWarnings("serial")
public class DropBargainBillServlet extends HttpServlet {

	private static final String Content_type = "text/html;charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType(Content_type);
		
		HttpSession session = req.getSession();
		
		String path = req.getContextPath();
		String url = path + "/web/appJSP/electricmanage/bargainbill/mainBargainBill.jsp", msg = "";
		
		String id = req.getParameter("id");

		BargainBillMethodsDAO dao = new BargainBillMethodsDAOImp();
		msg = dao.deleteBargainBills(id);
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		resp.sendRedirect(path + "/web/msg.jsp");

	}

}
