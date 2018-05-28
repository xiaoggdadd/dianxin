package com.noki.hetongguanli.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.hetongguanli.Dao.ShenPiDao;
public class Delete extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");//设置输入编码格式。
		response.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		
		String id = request.getParameter("id");
		
		ShenPiDao dao = new ShenPiDao();
		
		int rs = dao.delete(id);
		if(rs != 0){
			System.out.println("删除成功！ ");
			response.sendRedirect("web/sdttWeb/hetongInfoManager/hetongmanage.jsp");  
		}else{
			System.out.println("删除失败！ ");
		}
	}
	
}
