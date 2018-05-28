package com.noki.comm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.comm.bean.SeachItemBean;
import com.noki.comm.bean.SearchBean;
import com.noki.comm.service.SearchService;

public class InquiryServlet extends HttpServlet {
	 private static final String Content_type = "text/html;charset=UTF-8";

	/**
	 * Constructor of the object.
	 */
	public InquiryServlet() {
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
		String tablename = request.getParameter("quiryTaeble");
		String intoFlg = request.getParameter("flg");
		SearchService ser = new SearchService();
		response.setContentType(Content_type);
		//页面初始化
		if (intoFlg!=null && "into".equals(intoFlg)){
			 HttpSession session = request.getSession();
			 String path = request.getContextPath();
			 String url = path + "/web/common/GeneralInquiry.jsp", msg = "";
			SeachItemBean seachBean= new SeachItemBean();
			seachBean.setUserTable(tablename);
			seachBean.setUserId((String)request.getSession().getAttribute("loginName"));
			List<SeachItemBean> intoList =ser.seachData(seachBean);
			if (intoList!=null && intoList.size()>0){
				List serList = ser.toGetDate(tablename);
	            request.setAttribute("serList", serList);
			}
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            request.setAttribute("quiryTaeble", tablename);
            request.setAttribute("intoList", intoList);
            request.getRequestDispatcher("/web/common/GeneralInquiry.jsp").forward(request, response);
		//增加按钮初始化	
		}else{
			PrintWriter out = response.getWriter();
			List serList = ser.toGetDate(tablename);
			StringBuffer sb = new StringBuffer();
			if (serList!=null){
				for(int i=0;i<serList.size();i++){
					SearchBean serb = (SearchBean)serList.get(i);
					sb.append(serb.getColum_name());
					sb.append(",");
					sb.append(serb.getComments());
					sb.append("||");
				}
			}
			
			String ret = sb.toString().substring(0,sb.toString().length()-2);
			out.write(ret);
			out.flush();
			out.close();
		}
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
