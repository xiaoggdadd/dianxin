package com.noki.jizhan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JilianServlet {
	private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
    	System.out.println("jilian do post");
    	
    }
    public void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException,
            IOException {
		String msg = "����ʧ�ܣ������Ի������Ա��ϵ";
		//doPost(req, resp);
		System.out.println("jilian do get");
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		PrintWriter out = resp.getWriter();
		String action=req.getParameter("action");
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
		}
    }
}
