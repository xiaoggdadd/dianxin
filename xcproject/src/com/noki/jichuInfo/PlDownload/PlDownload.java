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
			sql.append("cast(ef.bg as decimal(38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay,case ef.manualauditstatus when '1' then 'ͨ��' when '-1' then '��ͨ��' else 'δ���' end zt");
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
			request.setAttribute("head", "���,ͳ���·�,��վ����,�������ȣ�,���±��ֵ���ȣ�,�ܵ�ѣ�Ԫ��,���״̬");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","��ѳ���Ԥ��.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("DFSP")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("select rownum,s.* from (select to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,cast(am.blhdl as decimal (38,2))as blhdl,cast(ef.bg as decimal (38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay,cast(EF.ACTUALPAY_D as decimal (38,2))as ACTUALPAY_D,");
			sql.append(" cast(EF.ACTUALPAY_L as decimal (38,2))as ACTUALPAY_L,cast(EF.ACTUALPAY_Y as decimal (38,2))as ACTUALPAY_Y,cast(EF.ACTUALPAY_O as decimal (38,2))ACTUALPAY_O,case ef.manualauditstatus when '1' then 'ͨ��' when '-1' then '��ͨ��' else 'δ���' end zt");
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
			request.setAttribute("head", "���,ͳ���·�,��վ����,�������ȣ�,���±��ֵ���ȣ�,�ܵ�ѣ�Ԫ��,���ŷ�̯��Ԫ��,��ͨ��̯��Ԫ��,�ƶ���̯��Ԫ��,������̯��Ԫ��,���״̬");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","�����������.xlsx");			
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
			sql.append(" THEN 'δ���'");
			sql.append(" WHEN T.SPZT = '1'");
			sql.append(" THEN '���ͨ��'");
			sql.append(" WHEN T.SPZT = '-1'");
			sql.append(" THEN '��˲�ͨ��'");
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
			request.setAttribute("head", "���,��վ����,ͳ���·�,����ֱ�����ɣ�A��,�յ�ϵ��,�õ�ϵ��,��վ���ֵ���ȣ�,���״̬");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","��վ�������.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("DJBGSP")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ROWNUM,S.* FROM (SELECT ZD.JZNAME,DJ.YEARMONTH,DJ.DBBM,DB.DBNAME,RTNAME(DB.DFZFLX) DFZFLX,DB.BEILV,");
			sql.append("DJ.DANJIA,CASE DJ.SHENHE_FLAG WHEN '1' THEN 'ͨ��' when '-1' then '��ͨ��' else 'δ���' end zt  ");
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
			request.setAttribute("head", "���,��վ����,��������,�����,�������,���֧������,����,��ۣ�Ԫ��,���״̬");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","��۱������.xlsx");			
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
			request.setAttribute("head", "���,���id,ͳ���·�,��վ����,���ʺĵ������ȣ�,���±��ֵ���ȣ�,�ܵ�ѣ�Ԫ��");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","Ԥ���ѹ���.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}else if(bz.equals("DFQUERY")){
			String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
			String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
			String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
			String keyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
			StringBuffer sql = new StringBuffer();
			sql.append("select rownum,s.* from (select to_char(ef.accountmonth, 'yyyy-mm') accountmonth,zd.jzname,cast(am.blhdl as decimal (38,2))as blhdl,cast(ef.bg as decimal (38,2))as bg,cast(ef.actualpay as decimal (38,2))as actualpay,");
			sql.append(" cast(EF.ACTUALPAY_D as decimal (38,2))as ACTUALPAY_D,cast(EF.ACTUALPAY_L as decimal (38,2))as ACTUALPAY_L,cast(EF.ACTUALPAY_Y as decimal (38,2))as ACTUALPAY_Y,cast(EF.ACTUALPAY_O as decimal (38,2))as ACTUALPAY_O,case ef.manualauditstatus when '1' then 'ͨ��' when '-1' then '��ͨ��' else 'δ���' end zt");
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
			request.setAttribute("head", "���,ͳ���·�,��վ����,�������ȣ�,���±��ֵ���ȣ�,�ܵ�ѣ�Ԫ��,���ŷ�̯��Ԫ��,��ͨ��̯��Ԫ��,�ƶ���̯��Ԫ��,������̯��Ԫ��,���״̬");
			request.setAttribute("path", "web/sdttWeb/jichuInfoManager/CbyuanExcel/");
			request.setAttribute("fileName","��Ѳ�ѯͳ��.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
		}
	}
}
