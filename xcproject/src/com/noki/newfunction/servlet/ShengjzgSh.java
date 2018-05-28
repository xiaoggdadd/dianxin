package com.noki.newfunction.servlet;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.noki.mobi.common.Account;

import com.noki.newfunction.dao.ZGShenHeDao;


@SuppressWarnings("serial")
public class ShengjzgSh extends HttpServlet {
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action=request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Account account = (Account)(request.getSession().getAttribute("account"));
		String loginName = account.getAccountName();
		
 if("cssave".equals(action)){
		System.out.println("33333333333333:"+action);
		String bzw="8";
		String msg="保存失败,请联系管理员！";
		
		System.out.println("*********************************");

		String jlzfh = request.getParameter("jlzfh");
		String zlzfh = request.getParameter("zlzfh");
		String qsdb = request.getParameter("qsdb");
		String zhssdl = request.getParameter("zhssdl");
		String zhdlwcsj = request.getParameter("zhdlwcsj");
		String yssdbdl = request.getParameter("yssdbdl");
		String id1 = request.getParameter("cid");
		//String zdid = request.getParameter("zdid");
		List<String> statusInfo = new ArrayList<String>();//错误信息

		ZGShenHeDao dao1 = new ZGShenHeDao();
		int Rs = dao1.TJSHsave1(loginName, jlzfh,zlzfh, qsdb, zhssdl,zhdlwcsj,yssdbdl,id1);
		if(Rs==1){
			msg = "保存成功！"; 
		}
		statusInfo.add(msg);
		sendStatusInfo(request,response,statusInfo,bzw);
		
	}

}

	/**
	 * 将执行信息发送至当前页面
	 * @param request
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo,String bzw){
	
		try {
			if("8".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/shengshenhexx.jsp").forward(request, response);
				
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
