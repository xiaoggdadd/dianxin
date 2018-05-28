package com.noki.newfunction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonAccountBean;

public class ScMessage extends HttpServlet {

   private static final String Content_type = "text/html;charset=GB2312";
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
           IOException, ServletException {
       resp.setContentType(Content_type);
       String path = req.getContextPath();
       
       
     //获取登录用户
   	Account account = new Account();     
       HttpSession session = req.getSession();
       account = (Account) session.getAttribute("account");
       String userid = account.getAccountId();
       
       String action= req.getParameter("action");
       PrintWriter out = resp.getWriter();
    if("dishi".equals(action)){
         ScMessageDao dao=new ScMessageDao();
           int count1=dao.getCount1(userid);
           int count2=dao.getCount2(userid);
          String count=count1+"@"+count2;
           out.print(count);
           out.close();
    }else  if("dishi2".equals(action)){
        ScMessageDao dao=new ScMessageDao();
        int count1=dao.getCount1(userid);
        int count2=dao.getCount2(userid);
        int count3=dao.getCount4(userid);
       String count=count1+"@"+count2+"@"+count3;
        out.print(count);
        out.close();
    }else if("quxian".equals(action)){
    	ScMessageDao dao=new ScMessageDao();
        int count1=dao.getCount3(userid);
       String count=count1+"";
        out.print(count);
        out.close();
    }else if("quxian2".equals(action)){
    	ScMessageDao dao=new ScMessageDao();
        int count1=dao.getCount3(userid);
        int count2=dao.getCount5(userid);
       String count=count1+"@"+count2;
        out.print(count);
        out.close();
    }
   }

   public void doGet(HttpServletRequest req,
                     HttpServletResponse resp) throws ServletException,
           IOException {
	   
   }
}
