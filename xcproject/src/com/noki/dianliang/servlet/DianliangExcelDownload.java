package com.noki.dianliang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

public class DianliangExcelDownload extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String yearmonth = request.getParameter("yearmonth")!=null?request.getParameter("yearmonth"):"0";
		String bgState = request.getParameter("bgState")!=null?request.getParameter("bgState"):"0";
		
		StringBuffer sql = new StringBuffer();
		sql.append("select to_char(b.ACCOUNTMONTH,'yyyy-mm') as ACCOUNTMONTH,d.JZNAME,a.BLHDL,b.BG,b.UNITPRICE,b.CREATEDATE"
				+ " from ammeterdegrees a,electricfees b,dianbiao c,zhandian d"
				+ " where b.AMMETERDEGREEID_FK = a.AMMETERDEGREEID and a.AMMETERID_FK = c.dbid and c.zdid = d.id");
		if(shi!=null&&!shi.equals("0")&&shi!=""){
			sql.append(" and d.SHI = '"+shi+"' ");
		}
		
		if(xian!=null&&!xian.equals("0")){
			sql.append(" and d.XIAN = '"+xian+"' ");
		}
		
		if(yearmonth!=null&&!yearmonth.equals("0")){
			sql.append(" and b.ACCOUNTMONTH = to_date('"+yearmonth+"','yyyy-mm') ");
		}
		
		if(bgState!=null&&!bgState.equals("0")&&!bgState.equals("2")){
			sql.append(" and b.CB_ALERT = '"+bgState+"'");
		}
		
		Log.info("[电量批量导入导出Excel]"+sql.toString());
		
		request.setAttribute("beanType", "com.noki.dianliang.model.DianliangExcelBean");
		request.setAttribute("sql", sql.toString());
		request.setAttribute("head", "年度月份,基站名称,电量(度),标杆值(度),电价(元),导入日期");
		request.setAttribute("path", "web/sdttWeb/BiaoganManager/tempExcels/");
		request.setAttribute("fileName","电量批量导入.xlsx");
		
		request.getRequestDispatcher("ExcelDownload").forward(request, response);
	}

}
