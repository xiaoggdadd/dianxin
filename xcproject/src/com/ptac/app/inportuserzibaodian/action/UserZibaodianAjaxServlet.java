package com.ptac.app.inportuserzibaodian.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class UserZibaodianAjaxServlet extends HttpServlet {

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
		doPost(request, response);
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Object inputdata = session.getAttribute("inputdata");
		Object inputcount = session.getAttribute("inputcount");
		Object inputerror = session.getAttribute("inputerror");
		Object inputlast = session.getAttribute("inputlast");
		
		if(inputdata!=null&&inputcount!=null&&inputerror!=null&&inputlast!=null){
			out.write("总共:"+inputdata+"条<br>已导入："+inputcount+"条，错误："+inputerror+"条， 剩余："+inputlast+":条");
		}
		out.close();
		out.flush();
		out.close();
	}

}
