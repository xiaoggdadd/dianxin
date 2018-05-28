package com.noki.comm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.comm.bean.SeachItemBean;
import com.noki.comm.service.SearchService;

public class InitInquiryServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";
	/**
	 * Constructor of the object.
	 */
	public InitInquiryServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
		response.setHeader("Charset","GB2312");
		response.setContentType(Content_type);
		
		PrintWriter out = response.getWriter();
		String tablename = request.getParameter("quiryTaeble").trim();
		
		SeachItemBean seachBean= new SeachItemBean();
		seachBean.setUserTable(tablename);
		seachBean.setUserId((String)request.getSession().getAttribute("loginName"));
		SearchService ser = new SearchService();
		List<SeachItemBean> intoList =ser.seachData(seachBean);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<intoList.size();i++){
			SeachItemBean serInfo = intoList.get(i);
			sb.append(serInfo.getLeftId());
			sb.append(",");
			sb.append(serInfo.getItem());
			sb.append(",");
			sb.append(serInfo.getCompareItem());
			sb.append(",");
			sb.append(serInfo.getCompareData());
			sb.append(",");
			sb.append(serInfo.getRightId());
			sb.append(",");
			sb.append(serInfo.getConettion());
			sb.append("||");
		}
		if(intoList.size()>0){
			out.write(sb.toString().substring(0,(sb.toString().length())-2));
		}else{
			out.write("");
		}
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
