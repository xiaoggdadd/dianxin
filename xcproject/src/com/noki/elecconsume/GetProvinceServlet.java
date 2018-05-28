package com.noki.elecconsume;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.shenenergy.dao.ShenEnergyDAO;
import com.noki.shenenergy.javabean.ShenDisplayBean;
import com.noki.shenenergy.service.ShenEnergyService;

public class GetProvinceServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";
	/**
	 * Constructor of the object.
	 */
	public GetProvinceServlet() {
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
		response.setContentType(Content_type);
		PrintWriter out = response.getWriter();
		String retDate="";
		try {
			ElecConsumeDAO elecdao = new ElecConsumeDAO();
		    List cityList = elecdao.toGetProvince();
			 for(int i=0;i<cityList.size();i++){
				 String fenge=i==cityList.size()-1?"":",";
				 Elecconsume disbean = (Elecconsume)cityList.get(i);
				 retDate =	retDate+disbean.getProvince()+"||"+disbean.getAgcode()+fenge;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(retDate);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
