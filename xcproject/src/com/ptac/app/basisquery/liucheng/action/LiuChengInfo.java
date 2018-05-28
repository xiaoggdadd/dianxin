package com.ptac.app.basisquery.liucheng.action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ptac.app.basisquery.liucheng.bean.LiuCheng;
import com.ptac.app.basisquery.liucheng.dao.LiuChengDao;
import com.ptac.app.basisquery.liucheng.dao.LiuChengDaoImpl;


/**
 * @author lijing
 * @see 流程查询
 */
public class LiuChengInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
//		String dbid = request.getParameter("dbid");
		String statusi = request.getParameter("statusi");
		String dfid = request.getParameter("dfid"+statusi);
	      	 
	    String whereStr = "";   	 
//	   	if (dbid != null && !dbid.equals("") && !dbid.equals("0")) {
//			whereStr = whereStr + " AND D.DBID='" + dbid + "'";
//		}
	   	if (dfid != null && !dfid.equals("") && !dfid.equals("0")) {
			whereStr = whereStr + "WHERE E.ELECTRICFEE_ID='" + dfid + "'";
		}
	
		LiuChengDao dao = new LiuChengDaoImpl();
		LiuCheng bean = new LiuCheng();	
		bean = dao.getLiuChengInfo(whereStr);
		
		request.setAttribute("bean", bean);//结果集
		
		if("liuchenginfo".equals(command)){
			
			request.getRequestDispatcher("/web/appJSP/basisquery/electricdetail/liucheng.jsp")
			.forward(request, response);
		}
	}
}
