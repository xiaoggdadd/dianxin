package com.ptac.app.calibstat.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.bean.CalibStatBean;
import com.ptac.app.calibstat.dao.CalibStatDao;
import com.ptac.app.calibstat.dao.CalibStatDaoImp;

/**
 * @author 李靖
 * @see 定标统计查询（单站点超标分析查询）
 */
public class SingleSiteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";//城市
		String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "";//区县
		String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "";//小区
		String zdname = request.getParameter("zdname") != null ? request.getParameter("zdname") : "";//站点名称
		String zdsx = request.getParameter("zdsx") != null ? request.getParameter("zdsx") : "";//站点属性
		String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "";//站点类型
		String zdid = request.getParameter("zdid") != null ? request.getParameter("zdid") : "";//站点ID
		String year = request.getParameter("year") != null ? request.getParameter("year"): "";//年份
			
		String whereStr = "";
		String str = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
			str = str + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
			str = str + " AND ZD.XAIN='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("0")) {
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
			str = str + " AND ZD.XIAOQU='" + xiaoqu + "'";
		}
		if (zdname != null && zdname != "") {
			whereStr = whereStr + " AND ZD.JZNAME ='" + zdname + "'";
			str = str + " AND ZD.ZDNAME ='" + zdname + "'";
		}
		if (zdid != null && zdid != "") {
			whereStr = whereStr + " AND ZD.ID ='" + zdid + "'";
			str = str + " AND ZD.ZDID ='" + zdid + "'";
		}
		if (zdsx != null && !zdsx.equals("") && !zdsx.equals("0")) {
			whereStr = whereStr + " AND ZD.PROPERTY='" + zdsx + "'";
			str = str + " AND ZD.ZDSX='" + zdsx + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			whereStr = whereStr + " AND ZD.STATIONTYPE IN('" + zdlx + "')";
			str = str + " AND ZZ.STATIONTYPE IN('" + zdlx + "')";
		}
		if (year != null && !year.equals("") && !year.equals("0")) {
			whereStr = whereStr + " AND Z.YEAR='" + year + "'";
			str = str + " AND ZD.YEAR='" + year + "'";
		}
			
		CalibStatBean bean = new CalibStatBean();
		CalibStatDao dao = new CalibStatDaoImp();
		bean = dao.querySingleSite(whereStr,str,loginId);
		if(bean != null){
			String dataString = "";
			dataString += "<chart caption='' subcaption='' xAxisName='月份' yAxisName='比例' numberSuffix='%' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
			dataString += "<set label='1月' value='"+(bean.getCbbl01()==null?"":bean.getCbbl01().replace("%", ""))+"' />";
			dataString += "<set label='2月' value='"+(bean.getCbbl02()==null?"":bean.getCbbl02().replace("%", ""))+"' />";
			dataString += "<set label='3月' value='"+(bean.getCbbl03()==null?"":bean.getCbbl03().replace("%", ""))+"' />";
			dataString += "<set label='4月' value='"+(bean.getCbbl04()==null?"":bean.getCbbl04().replace("%", ""))+"' />";
			dataString += "<set label='5月' value='"+(bean.getCbbl05()==null?"":bean.getCbbl05().replace("%", ""))+"' />";
			dataString += "<set label='6月' value='"+(bean.getCbbl06()==null?"":bean.getCbbl06().replace("%", ""))+"' />";
			dataString += "<set label='7月' value='"+(bean.getCbbl07()==null?"":bean.getCbbl07().replace("%", ""))+"' />";
			dataString += "<set label='8月' value='"+(bean.getCbbl08()==null?"":bean.getCbbl08().replace("%", ""))+"' />";
			dataString += "<set label='9月' value='"+(bean.getCbbl09()==null?"":bean.getCbbl09().replace("%", ""))+"' />";
			dataString += "<set label='10月' value='"+(bean.getCbbl10()==null?"":bean.getCbbl10().replace("%", ""))+"' />";
			dataString += "<set label='11月' value='"+(bean.getCbbl11()==null?"":bean.getCbbl11().replace("%", ""))+"' />";
			dataString += "<set label='12月' value='"+(bean.getCbbl12()==null?"":bean.getCbbl12().replace("%", ""))+"' />";
			dataString += "</chart>";
			request.setAttribute("dataString", dataString);
	    	request.setAttribute("bean", bean);
	    	request.getRequestDispatcher("/web/appJSP/calibstat/singleSite/singleSite.jsp").forward(request, response);
		}
	}
}
