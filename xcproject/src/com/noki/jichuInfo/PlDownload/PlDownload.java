package com.noki.jichuInfo.PlDownload;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlDownload extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bz = request.getParameter("bz");
		if(bz.equals("DFCB")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("select rownum,s.* from (select to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname, cast(am.blhdl as decimal(38,2))as blhdl,");
			sql.append("cast(ef.bg as decimal(38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay,case ef.manualauditstatus when '1' then '通过' when '-1' then '不通过' else '未审核' end zt");
			sql.append(" from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef where zd.id = db.zdid AND EF.CB_ALERT='1' and db.dbid = am.ammeterid_fk  and am.ammeterdegreeid = ef.ammeterdegreeid_fk");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='"+xiaoqu+"'");
			    } else if (!xian.equals("0")) {
				sql.append(" and zd.xian='"+xian+"'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='"+shi+"'");
			    }
			if (keyword.length() > 0 && keyword != null) {
				sql.append(" and zd.jzname like '%" + keyword + "%'");
			}
			sql.append(") s ");
			request.setAttribute("beanType", "com.noki.jichuInfo.PlDownload.DfcbBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,统计月份,基站名称,电量（度）,当月标杆值（度）,总电费（元）,审核状态");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","电费超标预警.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("DFSP")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("select rownum,s.* from (select to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,cast(am.blhdl as decimal (38,2))as blhdl,cast(ef.bg as decimal (38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay,cast(EF.ACTUALPAY_D as decimal (38,2))as ACTUALPAY_D,");
			sql.append(" cast(EF.ACTUALPAY_L as decimal (38,2))as ACTUALPAY_L,cast(EF.ACTUALPAY_Y as decimal (38,2))as ACTUALPAY_Y,cast(EF.ACTUALPAY_O as decimal (38,2))ACTUALPAY_O,case ef.manualauditstatus when '1' then '通过' when '-1' then '不通过' else '未审核' end zt");
			sql.append(" from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='"+xiaoqu+"'");
			    } else if (!xian.equals("0")) {
				sql.append(" and zd.xian='"+xian+"'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='"+shi+"'");
			    }
			if (keyword.length() > 0 && keyword != null) {
				sql.append(" and zd.jzname like '%" + keyword + "%'");
			}
			sql.append(") s ");
			request.setAttribute("beanType", "com.noki.jichuInfo.PlDownload.DfspBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,统计月份,基站名称,电量（度）,当月标杆值（度）,总电费（元）,电信分摊（元）,联通分摊（元）,移动分摊（元）,其他分摊（元）,审核状态");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","电费数据审批.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("JZBGSP")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ROWNUM,S.* FROM (");
			sql.append(" SELECT ZD.JZNAME,");
			sql.append(" T.YEARMONTH,");
			sql.append(" T.ZLFH,");
			sql.append(" T.KTXS,");
			sql.append(" T.YDXS,");
			sql.append(" T.BIAOGANVALUE,");
			sql.append(" (CASE");
			sql.append(" WHEN T.SPZT = '0'");
			sql.append(" THEN '未审核'");
			sql.append(" WHEN T.SPZT = '1'");
			sql.append(" THEN '审核通过'");
			sql.append(" WHEN T.SPZT = '-1'");
			sql.append(" THEN '审核不通过'");
			sql.append(" END) AS SPZT");
			sql.append(" FROM TBL_TT_BIAOGAN_LISHI T,");
			sql.append(" ZHANDIAN ZD");
			sql.append(" WHERE zd.id = t.zdid");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='"+xiaoqu+"'");
			    } else if (!xian.equals("0")) {
				sql.append(" and zd.xian='"+xian+"'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='"+shi+"'");
			    }
			if (keyword.length() > 0 && keyword != null) {
				sql.append(" and zd.jzname like '%" + keyword + "%'");
			}
			sql.append(") s ");
			System.out.println(sql);
			
			request.setAttribute("beanType", "com.noki.jichuInfo.PlDownload.JzbgspBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,基站名称,统计月份,单日直流负荷（A）,空调系数,用电系数,基站标杆值（度）,审核状态");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","基站标杆审批.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("DJBGSP")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ROWNUM,S.* FROM (SELECT ZD.JZNAME,DJ.YEARMONTH,DJ.DBBM,DB.DBNAME,RTNAME(DB.DFZFLX) DFZFLX,DB.BEILV,");
			sql.append("DJ.DANJIA,CASE DJ.SHENHE_FLAG WHEN '1' THEN '通过' when '-1' then '不通过' else '未审核' end zt  ");
			sql.append("FROM ZHANDIAN ZD, DIANBIAO DB, DIANJIA DJ WHERE ZD.ID = DB.ZDID AND DB.DBBM = DJ.DBBM AND ZD.JZCODE = DJ.ZDBM");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='"+xiaoqu+"'");
			    } else if (!xian.equals("0")) {
				sql.append(" and zd.xian='"+xian+"'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='"+shi+"'");
			    }
			if (keyword.length() > 0 && keyword != null) {
				sql.append(" and zd.jzname like '%" + keyword + "%'");
			}
			sql.append(") s ");
			request.setAttribute("beanType", "com.noki.jichuInfo.PlDownload.DjbgspBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,基站名称,适用年月,电表编号,电表名称,电费支付类型,倍率,电价（元）,审核状态");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","电价变更审批.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("YFF")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("select rownum,s.* from (select ef.electricfee_id,to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,");
			sql.append("cast(am.blhdl as decimal (38,2))as blhdl,cast(ef.bg as decimal (38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='"+xiaoqu+"'");
			    } else if (!xian.equals("0")) {
				sql.append(" and zd.xian='"+xian+"'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='"+shi+"'");
			    }
			if (keyword.length() > 0 && keyword != null) {
				sql.append(" and zd.jzname like '%" + keyword + "%'");
			}
			sql.append(") s ");
			request.setAttribute("beanType", "com.noki.jichuInfo.PlDownload.YffBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,电费id,统计月份,基站名称,倍率耗电量（度）,当月标杆值（度）,总电费（元）");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","预付费管理.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("DFQUERY")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("select rownum,s.* from (select to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,cast(am.blhdl as decimal (38,2))as blhdl,cast(ef.bg as decimal (38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay,");
			sql.append(" cast(EF.ACTUALPAY_D as decimal (38,2))as ACTUALPAY_D,cast(EF.ACTUALPAY_L as decimal (38,2))as ACTUALPAY_L,cast(EF.ACTUALPAY_Y as decimal (38,2))as ACTUALPAY_Y,cast(EF.ACTUALPAY_O as decimal (38,2))as ACTUALPAY_O,case ef.manualauditstatus when '1' then '通过' when '-1' then '不通过' else '未审核' end zt");
			sql.append(" from zhandian zd, dianbiao db, ammeterdegrees am, electricfees ef where zd.id = db.zdid and db.dbid = am.ammeterid_fk and am.ammeterdegreeid = ef.ammeterdegreeid_fk " +
					"");
			if (!xiaoqu.equals("0")) {
				sql.append(" and zd.xiaoqu='"+xiaoqu+"'");
			    } else if (!xian.equals("0")) {
				sql.append(" and zd.xian='"+xian+"'");
			    } else if (!shi.equals("0")) {
				sql.append(" and zd.shi='"+shi+"'");
			    }
			if (keyword.length() > 0 && keyword != null) {
				sql.append(" and zd.jzname like '%" + keyword + "%'");
			}
			sql.append(") s ");
			request.setAttribute("beanType", "com.noki.jichuInfo.PlDownload.DfqueryBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "序号,统计月份,基站名称,电量（度）,当月标杆值（度）,总电费（元）,电信分摊（元）,联通分摊（元）,移动分摊（元）,其他分摊（元）,审核状态");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","电费查询统计.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}
	}
}
