package com.noki.zwhd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.zwhd.manage.WyManage;

public class WydfftDelete extends HttpServlet {

	private String msg; 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pc = request.getParameter("pc");
		WyManage wyManage = new WyManage();
		boolean flag = wyManage.deleteWydfftByPc(pc);
		
		String path = request.getContextPath();
		String url = path + "/web/wyftdfsh/tt_wydfft_search.jsp";
		
		if(flag){
			msg = "删除物业分摊数据成功";
		}else{
			msg = "删除物业分摊数据失败";
		}
		HttpSession session = request.getSession();
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}

}
