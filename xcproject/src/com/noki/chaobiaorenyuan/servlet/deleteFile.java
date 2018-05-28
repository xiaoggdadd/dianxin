package com.noki.chaobiaorenyuan.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.chaobiaorenyuan.Dao.uploadDao;

public class deleteFile extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    //调用DAO方法
	    uploadDao dao = new uploadDao();
	    
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//获取要删除的文件名称和ID
		String wjid  = (String)request.getParameter("wenjianid");
		String wjName  = (String)request.getParameter("wenjianname");
		System.out.println("获取删除文件的主键ID"+wjid);
		String savePath = dao.filebaocun();	//从数据库取出路径
		
		String s = savePath+"\\"+wjName;// 文件的绝对路径
		File file = new File(s);
		System.out.println(s);
		if (file.exists()) {
			boolean d = file.delete();
			if (d) {
				System.out.print("文件删除成功！");
				//删除数据库字段
				int jieguo = dao.delect(wjid);
			    if(jieguo > 0){
			    	System.out.println("数据库字段删除成功");
			    }else{
			    	System.out.println("数据库字段删除失败");
			    }
			} else {
				System.out.print("删除失败！");
			}
		}
	}

}
