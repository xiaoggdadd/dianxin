package com.ptac.app.checksign.citymanagercheck.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.citymanagercheck.bean.CityMngCheckBean;
import com.ptac.app.checksign.citymanagercheck.dao.CityMngCheckDao;
import com.ptac.app.checksign.citymanagercheck.dao.CityMngCheckDaoImpl;

public class CityMngCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 5364627537549533598L;//serialVersionUID 用来表明类的不同版本间的兼容性

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String command = request.getParameter("command");
		//-----根据command不通选择不通处理方法------
		if("chaxun".equals(command) || "daochu".equals(command)){//查询，导出
			queryexport(request,response);
		}else if("xiangqing".equals(command)){//详情
			detail(request,response);
		}else if("checkcity".equals(command) || "checkcityno11".equals(command) 
				|| "checkcityno2".equals(command) || "checkcity1".equals(command) 
				|| "checkcityno1".equals(command) || "checkcityno".equals(command)){//审核
			check(request,response);
		}	
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @see 查询导出
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String zdname = request.getParameter("zdname");//站点名称
		String property = request.getParameter("property");//站点属性
		String bztime = request.getParameter("bztime");//报账月份
		String cityzrauditstatus = request.getParameter("cityzrauditstatus");//市级主任审核状态
		String getlrbz = request.getParameter("getlrbz");//录入标志
		
		
		StringBuffer whereStr = new StringBuffer();//查询条件
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" and z.shi='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" and z.xian='" + xian + "'"); 
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr.append(" and z.xiaoqu='" + xiaoqu + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" and z.jzname  like '%" + zdname + "%'");
		}
		
		if (bztime != null && bztime != "" && !bztime.equals("0")){
			whereStr.append(" and to_char(e.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'");
		}
		if (cityzrauditstatus != null && !cityzrauditstatus.equals("") && !cityzrauditstatus.equals("-1")){
			whereStr.append(" and e.cityzrauditstatus='" + cityzrauditstatus + "'");	
		}
		
		if (property != null && !property.equals("") && !property.equals("0")){
			whereStr.append(" and z.PROPERTY='" + property + "'");
		}
		//2014-8-13 如果getlrbz==null则两条sql（电费，预付费）均执行；如果getlrbz==1,则sql1执行，sql2不执行（and 1=2），如果getlrbz==2，则sql2执行，sql1不执行（and 1=2）
		String lrbz1="";
		String lrbz2="";
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){//只从电费表查询
				lrbz2="AND 1=2";
			}else if("2".equals(getlrbz)){//只从预付费表查询
				lrbz1="AND 1=2";
			}
		}
		
		//------查询结果集------
		CityMngCheckDao dao = new CityMngCheckDaoImpl();
		List<CityMngCheckBean> list = dao.queryCityMngCheck(whereStr.toString(), loginId,lrbz1,lrbz2);
		//------获得结果集和结果条数------
		int num = list.size();//结果条数
		request.setAttribute("num", num);//传回页面结果条数
		request.setAttribute("list", list);//传回页面结果集
		
		String command = request.getParameter("command");//获取前台按钮标识判断提交方向
		if("chaxun".equals(command)){//传到查询页面 
			request.getRequestDispatcher("/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp").forward(request, response);
		}else if("daochu".equals(command)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/checksign/citymanagercheck/市级主任审核导出.jsp").forward(request, response);
		}
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @see 详细信息页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//条件
		String statusi = request.getParameter("statusi");
		String dbid = request.getParameter("dbid"+statusi);
		String dfzflx = request.getParameter("dfzflx"+statusi);
		String dfbzw = request.getParameter("dfbzw"+statusi);
		String accountmonth = request.getParameter("accountmonth"+statusi);
//		CityMngCheckDao dao = new CityMngCheckDaoImpl();
//		List<CityMngCheckBean> list = dao.getCityMngCheckInfo(dbid,dfzflx,dfbzw,accountmonth);
//		//------获得结果集和结果条数------
//		int num = list.size();//结果条数
//		request.setAttribute("num", num);//传回页面结果条数
//		request.setAttribute("list", list);//传回页面结果集
		
		request.setAttribute("dbid", dbid);
       	request.setAttribute("dfzflx", dfzflx);
       	request.setAttribute("dfbzw", dfbzw);
       	request.setAttribute("bzyf", accountmonth);
       	//跳转页面
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp").forward(request, response);
	}
	
	/**
	 * @author lijing
	 * @param request
	 * @param response
	 * @see 市级主任审核
	 * @throws ServletException
	 * @throws IOException
	 */
	public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		CityMngCheckDao dao = new CityMngCheckDaoImpl();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//审核人员
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();//日志
	    String url = "",msg = "";//路径，提示信息
		String command = request.getParameter("command");
		if("checkcity".equals(command)){//通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); 
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "1";//审核通过
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "市级电费审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity1".equals(command)){
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "1";//审核通过
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "市级电费审核"); 
	        String m="";
	        if(msg=="审核电费信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno11".equals(command)){//审核不通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	  	  	String cityzrauditstatus = "2";//审核不通过
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "市级电费审核");
	        String m="";
	        if(msg=="审核电费信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno2".equals(command)){//不通过部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "2";//审核不通过
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "市级电费审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno1".equals(command)){//取消通过
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	  	  	String cityzrauditstatus = "0";//取消通过
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "市级电费审核");
	        String m="";
	        if(msg=="审核电费信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno".equals(command)){//取消通过
			String chooseIdStr1 = request.getParameter("chooseIdStr1");
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");
	        url = path + "/web/appJSP/checksign/citymanagercheck/citymanacheck.jsp" ;
	      	String cityzrauditstatus = "0";//取消通过
	        msg = dao.CheckCityFees(personnal,cityzrauditstatus,chooseIdStr1,chooseIdStr2,"0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "市级电费审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");

		}
	}

}
