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
 * @author �
 * @see ����ͳ�Ʋ�ѯ����վ�㳬�������ѯ��
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
		String shi = request.getParameter("shi") != null ? request.getParameter("shi") : "";//����
		String xian = request.getParameter("xian") != null ? request.getParameter("xian") : "";//����
		String xiaoqu = request.getParameter("xiaoqu") != null ? request.getParameter("xiaoqu") : "";//С��
		String zdname = request.getParameter("zdname") != null ? request.getParameter("zdname") : "";//վ������
		String zdsx = request.getParameter("zdsx") != null ? request.getParameter("zdsx") : "";//վ������
		String zdlx = request.getParameter("zdlx") != null ? request.getParameter("zdlx") : "";//վ������
		String zdid = request.getParameter("zdid") != null ? request.getParameter("zdid") : "";//վ��ID
		String year = request.getParameter("year") != null ? request.getParameter("year"): "";//���
			
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
			dataString += "<chart caption='' subcaption='' xAxisName='�·�' yAxisName='����' numberSuffix='%' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
			dataString += "<set label='1��' value='"+(bean.getCbbl01()==null?"":bean.getCbbl01().replace("%", ""))+"' />";
			dataString += "<set label='2��' value='"+(bean.getCbbl02()==null?"":bean.getCbbl02().replace("%", ""))+"' />";
			dataString += "<set label='3��' value='"+(bean.getCbbl03()==null?"":bean.getCbbl03().replace("%", ""))+"' />";
			dataString += "<set label='4��' value='"+(bean.getCbbl04()==null?"":bean.getCbbl04().replace("%", ""))+"' />";
			dataString += "<set label='5��' value='"+(bean.getCbbl05()==null?"":bean.getCbbl05().replace("%", ""))+"' />";
			dataString += "<set label='6��' value='"+(bean.getCbbl06()==null?"":bean.getCbbl06().replace("%", ""))+"' />";
			dataString += "<set label='7��' value='"+(bean.getCbbl07()==null?"":bean.getCbbl07().replace("%", ""))+"' />";
			dataString += "<set label='8��' value='"+(bean.getCbbl08()==null?"":bean.getCbbl08().replace("%", ""))+"' />";
			dataString += "<set label='9��' value='"+(bean.getCbbl09()==null?"":bean.getCbbl09().replace("%", ""))+"' />";
			dataString += "<set label='10��' value='"+(bean.getCbbl10()==null?"":bean.getCbbl10().replace("%", ""))+"' />";
			dataString += "<set label='11��' value='"+(bean.getCbbl11()==null?"":bean.getCbbl11().replace("%", ""))+"' />";
			dataString += "<set label='12��' value='"+(bean.getCbbl12()==null?"":bean.getCbbl12().replace("%", ""))+"' />";
			dataString += "</chart>";
			request.setAttribute("dataString", dataString);
	    	request.setAttribute("bean", bean);
	    	request.getRequestDispatcher("/web/appJSP/calibstat/singleSite/singleSite.jsp").forward(request, response);
		}
	}
}
