package com.noki.dataedit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.dataedit.bean.zhandianbean;

public class DeleteId extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
                 HttpSession se=request.getSession();
                  Map<String, zhandianbean> map=(HashMap)se.getAttribute("lst");//new HashMap<String,zhandianbean>();
  Iterator iter = map.entrySet().iterator();
while (iter.hasNext()) {
    		Map.Entry entry = (Map.Entry) iter.next();
    	 	String key =(String)entry.getKey();
    	 	zhandianbean zd = (zhandianbean)entry.getValue();
    	 	System.out.println("map里面的id："+key);
}
	String id=request.getParameter("zdid");
	String path = request.getContextPath();
	 String  url = path + "/web/dataedit/stationPointQuerya.jsp";
	 String  url1 = path + "/web/dataedit/jyjf.jsp";
		System.out.println("站点id:"+id);
		map.remove(id);
		String action=request.getParameter("action");
		if("del".equals(action)){
			// se.removeAttribute((HashMap)request.getSession().getAttribute("lst"));
			se.setAttribute("lst", null);
			  response.sendRedirect(url);
		}
		if("deljyjf".equals(action)){
			se.setAttribute("lstjyjf", null);
			response.sendRedirect(url1);
		}
	}

}
