package com.ptac.app.news.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.news.bean.GGBean;
import com.ptac.app.news.dao.NewGongGaoDao;
import com.ptac.app.news.dao.NewGongGaoDaoImpl;

public class NewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//------获取登录账户信息------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if(account==null){
//			request.getRequestDispatcher("/index.jsp")
//			.forward(request, response);
			 String path="";
			path=request.getContextPath();
			response.sendRedirect(path+"/index.jsp");
		}else{
			String accountId=account.getAccountId();
			
			String action = request.getParameter("action");
			NewGongGaoDao dao = new NewGongGaoDaoImpl();
			if("getnews".equals(action)){
//				List<GGBean> list = dao.getNewGongGao(accountId);//获取系统中该用户还没有看过的公告
//				int size = list.size();
//				int length;
//				if(size > 5){//多余5条 只显示5条
//			    	length = 5;
//			    }else{//小于等于5条均显示
//			    	length = size;
//			    }
//			    String alertnr = "";
//			    String ggid = "";
//			    int i;
//			    int b;
//			    for( i=0;i<length;i++){
//			    	b=i+1;
//			   		alertnr = alertnr +"<a href='javascript:dfinfo5("+list.get(i).getId()+","+b+")'>" + (i+1) + "."+list.get(i).getBt()+"</a>&nbsp;&nbsp;"+list.get(i).getGgtime()+"<br/><input id='new"+b+"' type='hidden' value = '0'/>";
//			    }
//			    int ss = size - 1;
//			    for(i=0;i<=ss;i++){
//			    	if(i==ss){
//			   			ggid = ggid + list.get(i).getId();
//			   		}else {
//			   			ggid = ggid + list.get(i).getId()+",";
//			   		}
//			    }
//			    request.setAttribute("alertnr", alertnr);
//			    request.setAttribute("length", length);
//			    request.setAttribute("ggid", ggid);
				request.getRequestDispatcher("/web/appJSP/gonggaoalert/gonggaoAlert.jsp")
				.forward(request, response);
			}else if("insertnews".equals(action)){
				String ggid = request.getParameter("ggid");
				String accountid = request.getParameter("accountId");
				
				String[] ad = ggid.split(",");
				int size = ad.length;
				int i;
				for(i=0;i<size;i++){
					dao.insertNews(ad[i], accountid);
				}
				request.setAttribute("alertnr", "");
				request.getRequestDispatcher("/web/appJSP/gonggaoalert/gonggaoAlert.jsp")
				.forward(request, response);
			}
		}
	
	}

}
