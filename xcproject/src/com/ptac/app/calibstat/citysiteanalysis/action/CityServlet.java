package com.ptac.app.calibstat.citysiteanalysis.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.citysiteanalysis.bean.CityBean;
import com.ptac.app.calibstat.citysiteanalysis.dao.CityDao;
import com.ptac.app.calibstat.citysiteanalysis.dao.CityDaoImp;

/**
 * @author 李靖
 * @see 地市站点超标分析
 */
public class CityServlet extends HttpServlet {

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
		
		String shi = "";//市
		String xian ="";//超链接用
		String property = request.getParameter("jzproperty");//站点属性
		String zdlx = request.getParameter("zdlx");//站点类型
		String year = request.getParameter("year");//年份
		
		String command = request.getParameter("command");
		if("chaxun".equals(command) || "daochu".equals(command)){ 
			shi = request.getParameter("shi");
		}else if("xiangqing".equals(command)){  
			 xian = request.getParameter("xian");
			 String dataString = xiangQing(xian,property,zdlx,year,loginId);
			 request.setAttribute("dataString", dataString );
		}

		List<CityBean> list = queryExport(shi,property,zdlx,year,loginId);
		request.setAttribute("list", list);
		request.setAttribute("num", list.size());
		
		if("chaxun".equals(command) || "xiangqing".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/citysiteanalysis/CitySiteAnalysis.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/citysiteanalysis/地市站点超标分析.jsp").forward(request, response);
		}
	}

	/**
	 * @see 地市站点超标分析 查询、导出
	 */
	private List<CityBean> queryExport(String shi, String property, String zdlx, String year, String loginId){
	
		String str = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			str = str + " AND ZD.SHI='" + shi + "'";
		}
		if (property != null && !property.equals("") && !property.equals("0")) {
			str = str + " AND ZD.ZDSX='" + property + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			str = str + " AND ZZ.STATIONTYPE IN('" + zdlx + "')";
		}
		if (year != null && !year.equals("") && !year.equals("0")) {
			str = str + " AND ZD.YEAR='" + year + "'";
		}
		
		CityDao dao = new CityDaoImp();
		List<CityBean> list = dao.getQueryExport(str,loginId);
		return list;
	}

	/**
	 * @see 地市站点超标分析具体图表信息
	 */
	private String xiangQing(String xian, String property, String zdlx, String year, String loginId){
	
		String str = "";
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			str = str + " AND ZD.XAIN='" + xian + "'";
		}
		if (property != null && !property.equals("") && !property.equals("0")) {
			str = str + " AND ZD.ZDSX='" + property + "'";
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			str = str + " AND ZZ.STATIONTYPE IN('" + zdlx + "')";
		}
		if (year != null && !year.equals("") && !year.equals("0")) {
			str = str + " AND ZD.YEAR='" + year + "'";
		}
		
		CityDao dao = new CityDaoImp();
		CityBean bean = new CityBean();
		bean = dao.getXiangXi(str,loginId);
		String dataString = "";
		if(bean != null){
			dataString += "<chart caption='' subcaption='"+bean.getCounty()+"' xAxisName='月份' yAxisName='比例' numberSuffix='%' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
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
		}
		return dataString;
	}
}
