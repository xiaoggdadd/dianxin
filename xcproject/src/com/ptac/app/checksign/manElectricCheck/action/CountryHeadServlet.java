package com.ptac.app.checksign.manElectricCheck.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.manElectricCheck.bean.CountryHeadBean;
import com.ptac.app.checksign.manElectricCheck.dao.CountryHeadDao;
import com.ptac.app.checksign.manElectricCheck.dao.CountryHeadDaoImp;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.enhanceelectricitybill.bean.Configurations;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanageUtil.Format;

public class CountryHeadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");//前台传来的标识

		if("query".equals(action)||"daochu".equals(action)){	//根据标识选择方法
			getInfo(request,response);
		}else if("getMoreInfo".equals(action)){
			getMoreInfo(request,response);
		}
	}

	private void getInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");//前台传来的标识
		//------获取登录账户信息------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();

		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String jzname = request.getParameter("zdname");//站点名称
		String stationtype = request.getParameter("zdlx1");//站点类型	
		String property = request.getParameter("jzproperty");//站点属性
		String accountmonth = request.getParameter("bztime");//报账月份
		String countryheadstatus = request.getParameter("countryheadstatus");//区县主任审核标志
		String cityaudit = request.getParameter("cityaudit");//市审核标志
		String qyzt = request.getParameter("qyzt");//站点启用状态
		String dbqyzt = request.getParameter("dbqyzt");//电表启用状态
		String getlrbz=request.getParameter("getlrbz");//录入标志位
		
		String whereStr = "";
		String whereStr1 = "";
		
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
			whereStr1 = whereStr1 + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
			whereStr1 = whereStr1 + " AND ZD.XIAN='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
			whereStr1 = whereStr1 + " AND ZD.XIAOQU='" + xiaoqu + "'";
		}
		if (jzname != null && !jzname.equals("")){
			whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
			whereStr1 = whereStr1 + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
		}
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0") && !("请选择").equals(stationtype)){
			whereStr = whereStr + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
			whereStr1 = whereStr1 + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
		}
		if (property != null && !property.equals("") && !property.equals("0")&&!("请选择").equals(property)) {
			whereStr = whereStr + " AND ZD.property = '" + property + "'";
			whereStr1 = whereStr1 + " AND ZD.property = '" + property + "'";
		}
		if (accountmonth != null && accountmonth != "" && !accountmonth.equals("0")){
			whereStr = whereStr + " AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'";
			whereStr1 = whereStr1 + " AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'";
		}
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("-1")){
			whereStr = whereStr + " AND EF.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
			whereStr1 = whereStr1 + " AND EF.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}
		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-1") && !("请选择").equals(cityaudit)){
			whereStr = whereStr + " AND EF.CITYAUDIT='" + cityaudit + "'";
			whereStr1 = whereStr1 + " AND EF.CITYAUDIT='" + cityaudit + "'";
		}
//		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-2") && !("不通过").equals(cityaudit))
//			whereStr = whereStr + " AND EF.COUNTYFLAG='0'";    //由于表设计不合理,此处挪至实现层
		
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")){
			whereStr = whereStr + " AND DB.DBQYZT='" + dbqyzt + "'";
			whereStr1 = whereStr1 + " AND DB.DBQYZT='" + dbqyzt + "'";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")){
			whereStr = whereStr + " AND ZD.QYZT='" + qyzt + "'";
			whereStr1 = whereStr1 + " AND ZD.QYZT='" + qyzt + "'";
		}
		if(!cityaudit.equals("-2") && !("不通过").equals(cityaudit)){
			whereStr = whereStr + "  AND AM.COUNTYFLAG='0'";
			whereStr1 = whereStr1 + "  AND EF.COUNTYFLAG='0'";
		}
		//2014-8-13 如果getlrbz==null则两条sql（电费，预付费）均执行；如果getlrbz==1,则sql1执行，sql2不执行（and 1=2），如果getlrbz==2，则sql2执行，sql1不执行（and 1=2）
		
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){
				whereStr1 = whereStr1 + "AND 1=2";
			}else if("2".equals(getlrbz)){
				whereStr = whereStr + "AND 1=2";
			}
		}
		//------获得结果集和结果条数------		
		CountryHeadDao dao = new CountryHeadDaoImp();
		List<CountryHeadBean> list = new ArrayList<CountryHeadBean>();	
		list = dao.queryInfo(whereStr, cityaudit, loginId,whereStr1);//结果集
		
		int num = list.size();//结果条数
		
		request.setAttribute("num", num);//结果条数
		request.setAttribute("list", list);//结果集

		//------根据前台按钮标识判断提交方向
		if("query".equals(action)){//传到查询页面
			request.getRequestDispatcher("/web/appJSP/checksign/manElectricCheck/countyHeadCheck.jsp")
			.forward(request, response);
		}else if("daochu".equals(action)){//传到导出页面
			request.getRequestDispatcher("/web/appJSP/checksign/manElectricCheck/export.jsp")
			.forward(request, response);
		}
		
	}
	
	private void getMoreInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dbid = request.getParameter("dbid");
		String biaozhi = request.getParameter("biaozhi");
		String dfzflx = request.getParameter("dfzflx");
		String accountmonth = request.getParameter("accountmonth");
		
		request.setAttribute("dbid", dbid);
       	request.setAttribute("dfzflx", dfzflx);
       	request.setAttribute("dfbzw", biaozhi);
       	request.setAttribute("bzyf", accountmonth);
		
       	//跳转页面
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp").forward(request, response);
	
	}
			

}
