package com.ptac.app.checksign.cityelectricfeecheck.action;
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
import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;
import com.ptac.app.checksign.cityelectricfeecheck.dao.EnhanceCityFeeCheckDao;
import com.ptac.app.checksign.cityelectricfeecheck.dao.EnhanceCityFeeCheckDaoImpl;


/**
 * @see 市级电费审核.查询，导出，审核
 * @author WangYiXiao 2014-1-23
 */
public class EnhanceCityFeeCheckServlet extends HttpServlet {
	
	/**
	 * serialVersionUID作用： 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	 */
	private static final long serialVersionUID = -5460538533259065862L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		//-----根据command不通选择不通处理方法------
		if("chaxun1".equals(command) || "enhancedaochu".equals(command)){//查询，导出
			queryexport(request,response);
		}else if("xiangqing".equals(command)){//详情
			detail(request,response);
		}else if("checkcity6".equals(command) || "checkcityno16".equals(command) 
				|| "checkcityno26".equals(command) || "checkcity16".equals(command) 
				|| "checkcityno17".equals(command) || "checkcityno6".equals(command)){//济宁审核
			
			check1(request,response);
		}	
	}

	/**
	 * @param request
	 * @param response
	 * @see 查询导出
	 * @author WangYiXiao 2014-1-23
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//------dao------
		EnhanceCityFeeCheckDao dao = new EnhanceCityFeeCheckDaoImpl();
		//------获取登录账户信息------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		//------获取前台操作标识------
		String command = request.getParameter("command");
		//------获取前台限制条件------
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String zdname = request.getParameter("zdname");//站点名称
		String gdfsa = request.getParameter("gdfsa");//供电方式
		String jzproperty = request.getParameter("jzproperty");//站点属性
		String cityaudit = request.getParameter("cityaudit");//市级审核状态
		String bztime = request.getParameter("bztime");//报账月份
		String lrrq = request.getParameter("lrrq");//录入日期
		String citymanageaudit = request.getParameter("citymanageaudit");//市级主任审核状态
		String getlrbz = request.getParameter("getlrbz");//录入标志
		//------根据获取的值拼接sql查询语句-----
		StringBuffer whereStr = new StringBuffer();//查询条件
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" AND ZD.SHI='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" AND ZD.XIAN='" + xian + "'"); 
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr.append(" AND ZD.XIAOQU='" + xiaoqu + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" AND ZD.JZNAME  LIKE '%" + zdname + "%'");
		}
		if (gdfsa != null && !gdfsa.equals("") && !gdfsa.equals("0")){
			whereStr.append(" AND ZD.GDFS  = '" + gdfsa + "'");
		}
		if (lrrq != null && !lrrq.equals("") && !lrrq.equals("null")){
			whereStr.append(" AND to_char(EF.ENTRYTIME,'yyyy-mm-dd')  LIKE '%" + lrrq + "%'");
		}
		if (bztime != null && bztime != "" && !bztime.equals("0")){
			whereStr.append(" AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'");
		}
		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-1")){
			whereStr.append(" AND EF.CITYAUDIT='" + cityaudit + "'");	
		}
		if (jzproperty != null && !jzproperty.equals("") && !jzproperty.equals("0")){
			whereStr.append(" AND ZD.PROPERTY='" + jzproperty + "'");
		}
		if(citymanageaudit != null && !citymanageaudit.equals("") && !citymanageaudit.equals("-1")){
			whereStr.append(" AND EF.CITYZRAUDITSTATUS='" + citymanageaudit + "'");
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
		List<CityElectricFeeCheck> list = dao.queryCityFeeCheck(whereStr.toString(), loginId,lrbz1,lrbz2);
		//------获得结果集和结果条数------
		int num = list.size();//结果条数
		//------获得电量合计和电费合计------
		String totalelectric = "0.00";
		String totalmoney = "0.00";
		if(list!=null){
			String[] total = dao.total(list);
			totalelectric = total[0];//电量合计	
	 		totalmoney = total[1];//电费合计	
		}
		//------向前台页面传值------
		request.setAttribute("totalelectric", totalelectric);//电量合计
		request.setAttribute("totalmoney", totalmoney);//电费合计
		request.setAttribute("num", num);//结果条数
		request.setAttribute("list", list);//结果集
		//------根据前台按钮标识判断提交方向
		if("chaxun1".equals(command)){//传到济宁市级电费审核
			request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp").forward(request, response);
		}else if("enhancedaochu".equals(command)){//传到济宁市级电费审核导出页面
			request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/加强版人工电费二级审核.jsp").forward(request, response);
		}
	}
	/**
	 * @param request
	 * @param response
	 * @see 详细信息页面
	 * @return
	 * @author WangYiXiao 2014-1-24
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
       	
       	request.setAttribute("dbid", dbid);
       	request.setAttribute("dfzflx", dfzflx);
       	request.setAttribute("dfbzw", dfbzw);
       	request.setAttribute("accountmonth", accountmonth);

       	//跳转页面
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp?width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100").forward(request, response);
	}

	/**
	 * @author lijing
	 * @see 济宁市级电费审核通过、不通过、取消通过方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void check1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//------dao------
		EnhanceCityFeeCheckDao dao = new EnhanceCityFeeCheckDaoImpl();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//审核人员
		String path = request.getContextPath();//根路径
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();//日志
	    String url = "",msg = "";//路径，提示信息
		String command = request.getParameter("command");//获取页面操作
		if("checkcity6".equals(command)){//通过
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	        url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "1";//审核通过
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"1","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁市级电费审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity16".equals(command)){//通过,ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "1";//审核通过
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"1","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁市级电费审核"); 
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
		}else if("checkcityno16".equals(command)){//不通过ajax部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//预付费uuid 
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	  	  	String cityaudit = "-2";//审核不通过
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁市级电费审核");
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
		}else if("checkcityno26".equals(command)){//不通过部分
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "-2";//审核不通过
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁市级电费审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno17".equals(command)){//取消通过，ajax部分
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	  	  	String cityaudit = "0";//取消通过
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁市级电费审核");
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
		}else if("checkcityno6".equals(command)){//取消通过
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//电费uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //预付费uuid
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "0";//取消通过
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "济宁市级电费审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");

		}
	}
	
}
