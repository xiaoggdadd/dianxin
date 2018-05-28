package com.ptac.app.systemmanage.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.systemmanage.bean.CountUsersInfo;
import com.ptac.app.systemmanage.dao.CountUsersDao;
import com.ptac.app.systemmanage.dao.CountUsersDaoImpl;

public class CountUsersInfoServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		doPost(request,response);
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		
		String shi = request.getParameter("shi");
		String zhiwu = request.getParameter("zhiwu");
		String zwshi = request.getParameter("zwshi");
		
		String whereStr = "";
		if (shi == null || "".equals(shi) || " ".equals(shi)|| "0".equals(shi)) {
			whereStr = whereStr + " and (a.shi='0' or a.shi='' or a.shi=' ' or a.shi is null) ";
		}else{
			whereStr = whereStr + " and a.shi='" + shi + "' ";
		}
		
		if(zhiwu !=null && !"".equals(zhiwu)&& !" ".equals(zhiwu) && !"全部".equals(zhiwu)&& !"0".equals(zhiwu)){
			whereStr = whereStr + " and a.rolename like '%" + zhiwu +"%'";
		}
		
		CountUsersDao dao = new CountUsersDaoImpl();
		List<CountUsersInfo> list = new ArrayList<CountUsersInfo>();
		list = dao.queryInfo(whereStr);
		
		int num = list.size();
				
		request.setAttribute("num", num);
		request.setAttribute("zhiwu", zhiwu);
		request.setAttribute("zwshi", zwshi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/web/sys/countUsersInfo.jsp")
		.forward(request, response);
		
		
	}
}
