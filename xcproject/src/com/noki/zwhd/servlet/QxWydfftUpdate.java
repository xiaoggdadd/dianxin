package com.noki.zwhd.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.noki.zwhd.manage.WyManage;

public class QxWydfftUpdate extends HttpServlet {
	private String msg;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String zthje = request.getParameter("t_zthje");
		String ztyy = request.getParameter("t_ztyy");
		
		HttpSession session = request.getSession();
		String loginName = (String) session.getAttribute("loginName");
		WyManage wyManage = new WyManage();
		boolean flag = wyManage.updateForQx(id,zthje,ztyy,loginName);
		if(flag){
			msg = "区县修改申请提交成功！";
		}else{
			msg = "区县修改申请提交失败！";
		}
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_qxdfftqr_search.jsp";
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
