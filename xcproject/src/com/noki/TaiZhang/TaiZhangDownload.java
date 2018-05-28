package com.noki.TaiZhang;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaiZhangDownload extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bz = request.getParameter("bz");
		if(bz.equals("quyu")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";    
		    String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		    String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
		    String keyword = request.getParameter("yearmonth")!=null?request.getParameter("yearmonth"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT rownum, R.* FROM (SELECT RNDIQU(ZD.SHI) AS SHI,RNDIQU(ZD.XIAN) AS XIAN,RNDIQU(ZD.XIAOQU) AS XIAOQU,ZD.JZNAME, ZD.JZCODE,RTNAME(ZD.GDFS) GDFS, '抄表结算' JSFS,");
			sql.append("AM.LASTDEGREE,AM.THISDEGREE,CAST(AM.BLHDL AS DECIMAL (38,2))AS BLHDL,CAST(EF.UNITPRICE AS DECIMAL (38,4))AS UNITPRICE,CAST(EF.ACTUALPAY AS DECIMAL (38,2))AS ACTUALPAY, AM.THISDATE,RTNAME(EF.NOTETYPEID) PJLX,ZD.GDFNAME,EF.FTBL_Y,");
			sql.append("(CASE WHEN EF.NOTETYPEID = 'pjlx05' THEN 0.1964 ELSE 0.00 END) YDSFYZ,CAST(EF.ACTUALPAY_Y AS DECIMAL (38,2))AS ACTUALPAY_Y,EF.FTBL_L,(CASE WHEN EF.NOTETYPEID = 'pjlx05' THEN 0.1964 ELSE 0.00 END) LTSFYZ,");
			sql.append("CAST(EF.ACTUALPAY_L AS DECIMAL (38,2))AS ACTUALPAY_L,EF.FTBL_D,(CASE WHEN EF.NOTETYPEID = 'pjlx05' THEN 0.1964 ELSE 0.00 END) DXSFYZ,CAST(EF.ACTUALPAY_D AS DECIMAL (38,2)) AS  ACTUALPAY_D");
			sql.append(" FROM ZHANDIAN ZD, DIANBIAO DB, AMMETERDEGREES AM, ELECTRICFEES EF WHERE ZD.ID = DB.ZDID AND DB.DBID = AM.AMMETERID_FK AND AM.AMMETERDEGREEID = EF.AMMETERDEGREEID_FK");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='" + xiaoqu + "'");
			    } else if (!xian.equals("0")) {
				sql.append(" and z.xian='" + xian + "'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='" + shi + "'");
			    }
			if (keyword != "0"&&keyword!=""&&!keyword.equals("0")&&!keyword.equals("")) {
				sql.append(" and ef.accountmonth =to_date('"+keyword+"','yyyy-mm')");
			}
			sql.append(")R");
			request.setAttribute("beanType", "com.noki.TaiZhang.QuyuDFBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,城市,区县,乡镇,站点名称,站点编码,供电方式,结算方式,起码,止码,电量（度）,电费单价（元/度）,缴费金额,缴费日期,缴费票据类型,供电方/业主名称,移动分摊比例,移动税负因子,移动分摊金额,联通分摊比例,联通税负因子,联通分摊金额,电信分摊比例,电信税负因子,电信分摊金额");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","区域电费信息确认表.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}
	}

}
