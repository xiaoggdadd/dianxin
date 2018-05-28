package com.noki.zwhd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.CwManage;

public class CwybxDelete extends HttpServlet {

	private String msg; 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pc = request.getParameter("pc");
		CwManage cwManage = new CwManage();
		boolean flag = cwManage.deleteCwybxByPc(pc);
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_cwybx_search.jsp";
		
		if(flag){
			msg = "删除财务已报销数据成功";
		}else{
			msg = "删除财务已报销数据失败";
		}
		HttpSession session = request.getSession();
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
