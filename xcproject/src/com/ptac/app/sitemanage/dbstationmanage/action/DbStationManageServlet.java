package com.ptac.app.sitemanage.dbstationmanage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.sitemanage.dbstationmanage.bean.DbStationManageBean;
import com.ptac.app.sitemanage.dbstationmanage.dao.DbStationManageDao;
import com.ptac.app.sitemanage.dbstationmanage.dao.DbStationManageDaoImpl;

public class DbStationManageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("command");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		if("chaxun".equals(action)){//站点信息查询
			String city = request.getParameter("shi");
			String xian = request.getParameter("xian");
			String xiaoqu = request.getParameter("xiaoqu");
			String property = request.getParameter("jzproperty");
			String zdname = request.getParameter("zdname");
			String zdid = request.getParameter("zdid");
			String stationtype = request.getParameter("stationtype");
			StringBuffer whereStr = new StringBuffer();
			if(!"0".equals(city)){
				whereStr.append(" AND ZD.SHI = '"+city+"'");
			}
			if(!"0".equals(xian)){
				whereStr.append(" AND ZD.XIAN = '"+xian+"'");
			}
			if(!"0".equals(xiaoqu)){
				whereStr.append("AND ZD.XIAOQU = '"+xiaoqu+"'");
			}
			if(!"0".equals(property)){
				whereStr.append(" AND ZD.PROPERTY = '"+property+"'");
			}
			if(!"".equals(zdname)){
				whereStr.append(" AND ZD.JZNAME LIKE '%"+zdname+"%'");
			}
			if(!"".equals(zdid)){
				whereStr.append(" AND ZD.ID = '"+zdid.trim()+"'");
			}
			if(!"0".equals(stationtype)){
				whereStr.append(" AND ZD.STATIONTYPE = '"+stationtype+"'");
			}
			DbStationManageDao dao = new DbStationManageDaoImpl();
			List<DbStationManageBean> list = new ArrayList<DbStationManageBean>();
			list = dao.getStationInfo(whereStr.toString(),loginId);
			int num = list.size();//结果条数
			request.setAttribute("num", num);//结果条数
			request.setAttribute("list", list);
			request.getRequestDispatcher("/web/appJSP/sitemanage/dbstationmanage/dbstationmanage.jsp").forward(request, response);
			
		}else if("checkpass".equals(action)||"checkpass1".equals(action) 
				|| "checkquxiao".equals(action)||"checkquxiao1".equals(action)){
			check(request, response);//审核
		}

	}
	public void check(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String url = "",msg = "";//路径，提示信息
		String path = request.getContextPath();
		String action = request.getParameter("command");
		DbStationManageDao dao = new DbStationManageDaoImpl();
		if("checkpass".equals(action)){//通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //站点id
	        url = path + "/web/appJSP/sitemanage/dbstationmanage/dbstationmanage.jsp" ;
	      	String status = "1";//允许
	        msg = dao.checkStatus(chooseIdStr1, status);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkpass1".equals(action)){//通过,ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //站点id
	        url = path + "/web/appJSP/sitemanage/dbstationmanage/dbstationmanage.jsp" ;
	      	String status = "1";//允许
	      	msg = dao.checkStatus(chooseIdStr1, status);
	        String m="";
	        if(msg=="更改多表标识失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.flush();
	        out.close();
		}else if("checkquxiao".equals(action)){//取消多表状态
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //站点id
	        url = path + "/web/appJSP/sitemanage/dbstationmanage/dbstationmanage.jsp" ;
	      	String status = "0";//取消
	        msg = dao.checkStatus(chooseIdStr1, status);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkquxiao1".equals(action)){//取消多表状态,ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //站点id
	        url = path + "/web/appJSP/sitemanage/dbstationmanage/dbstationmanage.jsp" ;
	      	String status = "0";//取消
	      	msg = dao.checkStatus(chooseIdStr1, status);
	        String m="";
	        if(msg=="更改多表标识失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.flush();
	        out.close();
		}
		
	}

}
