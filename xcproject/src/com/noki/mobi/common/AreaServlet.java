package com.noki.mobi.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.jizhan.JiZhanBean;

public class AreaServlet extends HttpServlet{

	
	private static final String Content_type = "text/html;charset=UTF-8";
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	      IOException, ServletException {
	    resp.setContentType(Content_type);
	    System.out.println("jilian do post");
	  }
	  
	  public void doGet(HttpServletRequest req,
	            HttpServletResponse resp) throws ServletException,
	            IOException {
			String msg = "操作失败！请重试或与管理员联系";

			System.out.println("jilian do get");
			resp.setContentType("text/xml; charset=UTF-8");
			resp.setHeader("Cache-Control", "no-cache");
			PrintWriter out = resp.getWriter();
			String action=req.getParameter("action");
			System.out.println(action);
			if(action.equals("sheng")){
				String pid = req.getParameter("pid");
				JiZhanBean bean= new JiZhanBean();
				String list = bean.getChildrenArea_shi(pid);
				System.out.println(list);
				out.print(list);
				out.close();
			}else if(action.equals("shi")){
				String pid = req.getParameter("pid");
				JiZhanBean bean= new JiZhanBean();
				String list = bean.getChildrenArea_xian(pid);
				out.print(list);
				out.close();
			}else if(action.equals("fenzu")){
				String pid = req.getParameter("fid");
				JiLianBean bean= new JiLianBean();
				String list = bean.getFenzu_Role(pid);
				out.print(list);
				out.close();
			}
	    }

}
