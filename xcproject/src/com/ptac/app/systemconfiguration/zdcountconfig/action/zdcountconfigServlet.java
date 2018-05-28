package com.ptac.app.systemconfiguration.zdcountconfig.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptac.app.systemconfiguration.zdcountconfig.bean.zdCountConfigBean;
import com.ptac.app.systemconfiguration.zdcountconfig.dao.zdCountConfigurationDao;
import com.ptac.app.systemconfiguration.zdcountconfig.dao.zdCountConfigurationDaoImpl;

public class zdcountconfigServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		zdCountConfigurationDao dao = new zdCountConfigurationDaoImpl();
		String action = request.getParameter("action");
		String itemid = request.getParameter("ItemID");
		if("edit".equals(action)){//编辑
			request.setAttribute("ItemId", itemid);	
		}
		if("modify".equals(action)){//修改
			String itemvalue = request.getParameter("ItemValue");
			String itemvalue2 = request.getParameter("ItemValue2");
			zdCountConfigBean formBean = new zdCountConfigBean();
			formBean.setItemid(itemid);
			formBean.setItemvalue(itemvalue);
			formBean.setItemvalue2(itemvalue2);
			String msg = dao.modifyAutoAudit(formBean);
			//msg = "修改站点数量配置信息成功！".equals(msg)?null:msg;
			request.setAttribute("msg",msg);
//				HttpSession session = request.getSession();
//				String path = request.getContextPath();
//				String url = path + "/web/appJSP/systemconfiguration/zdcountconfig.jsp";
//				session.setAttribute("url", url);
//				session.setAttribute("msg", msg);
//				List<zdCountConfigBean> list = dao.getPageData();
//				session.setAttribute("listcount",list);
//				response.sendRedirect(path + "/web/msg.jsp");
		}//else{//查询,编辑
			List<zdCountConfigBean> list = dao.getPageData();
			request.setAttribute("listcount",list);
			request.getRequestDispatcher("/web/appJSP/systemconfiguration/zdcountconfig.jsp").forward(request, response);
		//}
	}

}
