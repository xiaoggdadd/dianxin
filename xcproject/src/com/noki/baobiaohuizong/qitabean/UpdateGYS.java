package com.noki.baobiaohuizong.qitabean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.SiteManage;

public class UpdateGYS extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//获取要修改的内容
		String DBBM  = (String)request.getParameter("DBBM");
		String gysbmm  = (String)request.getParameter("gysbmm");
		String rsname  = (String)request.getParameter("rsname");
		
		//获取DAO
		SiteManage dao = new SiteManage();
		
		//获取修改反馈
		int jieguo = dao.UPDATEGYS(DBBM,gysbmm,rsname);
		
	    if(jieguo > 0){
	    	System.out.println("供应商修改完成");
	    }else{
	    	System.out.println("供应商修改失败");
	    }
	    out.close();
	}

}
