package com.ptac.app.systemconfiguration.EnhanceEleConfig.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.ptac.app.systemconfiguration.EnhanceEleConfig.bean.EnhanceEleConfigBean;
import com.ptac.app.systemconfiguration.EnhanceEleConfig.dao.EnhanceEleConfigurationDao;
import com.ptac.app.systemconfiguration.EnhanceEleConfig.dao.EnhanceEleConfigurationDaoImpl;

public class EnhanceEleConfigurationServlet extends HttpServlet {

	private static final String Content_type = "text/html;charset=UTF-8";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  	response.setContentType(Content_type);
		    String path = request.getContextPath();
		    DbLog log = new DbLog();
		   EnhanceEleConfigurationDao dao = new EnhanceEleConfigurationDaoImpl();
		    String url = "", msg = "";
		    HttpSession session = request.getSession();
		    EnhanceEleConfigBean bean = new EnhanceEleConfigBean();
		    String action = request.getParameter("action");
		 if ("modify".equals(action)) {
		      String ItemID = request.getParameter("ItemID");  
		      url = path + "/web/appJSP/systemconfiguration/enhanceEleConfig.jsp";
		      bean = dao.getEnhEleConfigInfo( ItemID);
			  bean.setItemValue(request.getParameter("ItemValue"));     
		      msg = dao.modifyAutoAudit(bean);
		      log.write(msg, bean.getItemID(), request.getRemoteAddr(), "º”«ø…Û∫À≈‰÷√");
		      session.setAttribute("url", url);
		      session.setAttribute("msg", msg);
		      response.sendRedirect(path + "/web/msg.jsp");
		    }

	}

}
