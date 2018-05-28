package com.noki.chaobiaorenyuan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.chaobiaorenyuan.Dao.uploadDao;

public class YanZhengWenJianServlet extends HttpServlet {

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
	    //调用DAO方法
	    uploadDao dao = new uploadDao();
	    
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//接收需要验证的信息
		String SCRID  = (String)request.getParameter("scrid");
		String DianBiaoID  = (String)request.getParameter("dianbiaoid");
		String LiuChengID  = (String)request.getParameter("liuchengid");
		//验证时间
    	Date date= new Date();//创建一个时间对象，获取到当前的时间
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置时间显示格式
    	String SCTIME = sdf.format(date);//将当前时间格式化为需要的类型
		System.out.println("验证日期："+SCTIME);
		boolean result = dao.Whether(SCRID, DianBiaoID, SCTIME, LiuChengID);
		
		if(result){
			System.out.println("返回值为true，已经上传文件，可以提交修改");
			out.write("1");
		}else{
			System.out.println("返回值为false,为上传文件，无法提交");
			out.write("0");
		}
		
	}

}
