package com.noki.elecconsume;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.shenenergy.dao.ShenEnergyDAO;

public class GetYearServlet  extends HttpServlet{
	private static final String Content_type = "text/html;charset=UTF-8";
	/**
	 * Constructor of the object.
	 */
	public GetYearServlet() {
		super();
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
		StringBuffer  retYear=new StringBuffer();
		try {
			ElecConsumeDAO yearydao = new ElecConsumeDAO();
			 List<?> yearList = yearydao.getYear();
			 for(int i=0;i<yearList.size();i++){
				 String fenge=i==yearList.size()-1?"":",";
				 retYear.append(yearList.get(i));
				 retYear.append(fenge);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(retYear.toString());
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
