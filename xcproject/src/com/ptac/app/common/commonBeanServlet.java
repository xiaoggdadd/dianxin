package com.ptac.app.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.noki.jizhan.SiteManage;
import com.noki.mobi.common.CommonBean;
import com.noki.mobi.sys.javabean.Per_area;
import com.ptac.app.accounting.AccountingDao;

public class commonBeanServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if("gys".equalsIgnoreCase(action)){//供应商
			CommonBean bean = new CommonBean();
			String code = request.getParameter("code");
			if(code !=null && !code.isEmpty()){
				ArrayList list = bean.getSupplier(code);
				JSONArray json = JSONArray.fromObject(list);
				out.write(json.toString());
			}
		}else if("cbzx".equalsIgnoreCase(action)){//一个电表对应多个成本中心 
		    AccountingDao dao = new AccountingDao();
		    String dbcode = request.getParameter("dbcode");
		    if(dbcode !=null && !dbcode.isEmpty()){
		    	ArrayList list = dao.getAccounting(dbcode);
		    	JSONArray json = JSONArray.fromObject(list);
		    	out.write(json.toString());
		    }
		}else if("agrea".equalsIgnoreCase(action)){//分配负责区域
			Per_area perArea = new Per_area();
			String loginName = request.getParameter("loginName");
			if(loginName !=null && !loginName.isEmpty()){
				ArrayList list = perArea.getPageData(loginName);
				JSONArray json = JSONArray.fromObject(list);
		    	out.write(json.toString());
			}
		}else if("dutyCenter".equalsIgnoreCase(action)){//预算责任中心
			 AccountingDao dao = new AccountingDao();
			String costCode = request.getParameter("costCode");
			if(costCode !=null && !costCode.isEmpty()){
				ArrayList list = dao.getDutyCenterByCode(costCode);
				JSONArray json = JSONArray.fromObject(list);
		    	out.write(json.toString());
			}
		}else if("costCenter".equalsIgnoreCase(action)){//成本中心
			 AccountingDao dao = new AccountingDao();
				String gszt = request.getParameter("gszt");
				if(gszt !=null && !gszt.isEmpty()){
					ArrayList list = dao.getCostCenterBygszt(gszt);
					JSONArray json = JSONArray.fromObject(list);
			    	out.write(json.toString());
				}
		}else if("dept".equalsIgnoreCase(action)){//成本中心
			 SiteManage dao = new SiteManage();
				String shi = request.getParameter("shi");
				String fdeptcode = request.getParameter("fdeptcode");
				if(shi !=null && !shi.isEmpty()){
					String result = dao.getDeptByShi(shi, fdeptcode);
					JSONArray json = JSONArray.fromObject(result);
			    	out.write(json.toString());
				}
		}
		out.flush();
		out.close();
	}

}
