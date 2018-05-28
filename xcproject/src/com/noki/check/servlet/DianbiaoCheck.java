package com.noki.check.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DianbiaoCheck extends HttpServlet {

	public DianbiaoCheck() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
             doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        String loginid=request.getParameter("loginid");
        String zdmc= request.getParameter("zdmc");
        System.out.print(loginid+"===================");
        System.out.print(zdmc+"============");
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
