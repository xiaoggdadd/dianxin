package com.noki.elecconsume;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.shenenergy.service.ShenEnergyService;
import com.noki.util.BuildDate;

public class ElecConsumeServerlet extends HttpServlet {
	  private static final String Content_type = "text/html;charset=UTF-8";
	/**
	 * Constructor of the object.
	 */
	public ElecConsumeServerlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
			{
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
			 {
		response.setContentType(Content_type);
		
		try {
			PrintWriter out = response.getWriter();
			String city = request.getParameter("city");//³ÇÊÐ
			ElecConsumeDAO elecdao = new ElecConsumeDAO();
			String retDate="";
		  	String  years=request.getParameter("year").substring(0,request.getParameter("year").length()-1);
			String [] year = years.split(",");
			List elecList =  elecdao.toGetDate(city, years);
			String data = BuildDate.toRetTable("elec.properties", elecList, year);
			 
		//	System.out.println(data);
			 
			out.print(data);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
