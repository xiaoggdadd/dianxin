package com.ptac.app.calibstat.provincesiteanalysis.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.provincesiteanalysis.bean.ProvBean;
import com.ptac.app.calibstat.provincesiteanalysis.dao.ProvDao;
import com.ptac.app.calibstat.provincesiteanalysis.dao.ProvDaoImp;

/**
 * @author 李靖
 * @see 全省站点超标分析
 */
public class ProvServlet extends HttpServlet {

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
		String shi2 ="";//超链接用
		String property = request.getParameter("jzproperty");//站点属性
		String zdlx = request.getParameter("zdlx");//站点类型
		String year = request.getParameter("year") != null ? request.getParameter("year"): "";//年份
		
		String command = request.getParameter("command");
		if("chaxun".equals(command) || "daochu".equals(command)){ 
			//如果是查询  就把shi 这个属性拿出来放进session中 因为查询请求和图表信息请求用到的过虑条件shi(市) 是不一样的
			shi = request.getParameter("shi");
//			session.setAttribute("shi", shi);
		}else if("xiangqing".equals(command)){  
			//如果是图表的请求   就直接调用xiangqing这个方法  得到dataString  前台的图表显示 就是靠这个东西
			 shi2 = request.getParameter("shi2");
			 String dataString = xiangQing(shi2,property,zdlx,year,loginId);
			 request.setAttribute("dataString", dataString );
		}
//		if(session.getAttribute("shi") == null){ 
//			//然后从session中获取shi 如果没有 就从request中获取
//			shi = request.getParameter("shi");
//		}else{
//			shi = session.getAttribute("shi").toString();
//		}
		
		//不管是查询  还是图表请求  都要显示list  所以这个queryExport方法 放在这里执行  获取list
		List<ProvBean> list = queryExport(shi,property,zdlx,year,loginId);
		request.setAttribute("list", list);
		request.setAttribute("num", list.size());
		
		if("chaxun".equals(command) || "xiangqing".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/provsiteanalysis/ProvSiteAnalysis.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/provsiteanalysis/全省站点超标分析.jsp").forward(request, response);
		}
	}

	/**
	 * @see 全省站点超标分析 查询、导出
	 */
	private List<ProvBean> queryExport(String shi, String property, String zdlx, String year, String loginId){
	
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
		
		ProvDao dao = new ProvDaoImp();
		List<ProvBean> list = dao.getQueryExport(str,loginId);
		return list;
	}

	/**
	 * @see 全省站点超标分析具体图表信息
	 */
	private String xiangQing(String shi, String property, String zdlx, String year, String loginId){
	
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
		
		ProvDao dao = new ProvDaoImp();
		ProvBean bean = new ProvBean();
		bean = dao.getXiangXi(str,loginId);
		String dataString = "";
		if(bean != null){
			dataString += "<chart caption='' subcaption='"+bean.getCity()+"' xAxisName='月份' yAxisName='比例' numberSuffix='%' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
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
