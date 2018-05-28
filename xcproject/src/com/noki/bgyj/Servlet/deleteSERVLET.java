package com.noki.bgyj.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import com.noki.database.DataBase;
import com.noki.jizhan.SiteManage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class deleteSERVLET extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//获取参数
		System.out.println("get");
		String action = request.getParameter("action");
		DataBase dataBase=new DataBase();
		if("del".equals(action)){
			try {
				int  yjid=Integer.parseInt( request.getParameter("yjid"));
				System.out.println(yjid);
				String sql="update bgyj  set state=0 where id='"+yjid+"'";
				System.out.println(">>>>>>>>>>>"+sql);
				int d=dataBase.update(sql);
				dataBase.commit();
				String msg = "";
				if(d==0){
					msg = "删除失败";
				}else{
					msg = "删除成功";
				}
				String path = request.getContextPath();
				String url = path +"/web/sdttWeb/GaojingManager/jzbgYj.jsp";
				
				HttpSession session = request.getSession();
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");  

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
