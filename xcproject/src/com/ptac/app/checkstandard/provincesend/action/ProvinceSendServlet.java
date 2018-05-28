package com.ptac.app.checkstandard.provincesend.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptac.app.checkstandard.provincesend.bean.DetailBean;
import com.ptac.app.checkstandard.provincesend.bean.ProvinceSendBean;
import com.ptac.app.checkstandard.provincesend.dao.ProvinceSendDao;
import com.ptac.app.checkstandard.provincesend.dao.ProvinceSendDaoImpl;

public class ProvinceSendServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();//根路径
		String action = request.getParameter("command");
		ProvinceSendDao dao = new ProvinceSendDaoImpl();
		if("chaxun".equals(action) || "daochu".equals(action)){
			String accountid = request.getParameter("accountid");
			String shi = request.getParameter("shi");
			String xian = request.getParameter("xian");
			String zdsx = request.getParameter("jzproperty");
			String sjzt = request.getParameter("sjzt");
			String bl1 = request.getParameter("bl1");
			String bl2 = request.getParameter("bl2");
			String zdname = request.getParameter("zdname");
			String zdid = request.getParameter("zdid");
			StringBuffer wherestr = new StringBuffer("");
			StringBuffer wherestr2 = new StringBuffer("");
			if(!"0".equals(shi) && null != shi){
				wherestr.append(" AND ZD.SHI='").append(shi).append("'");
			}
			if(!"0".equals(xian) && null != xian){
				wherestr.append(" AND ZD.XIAN='").append(xian).append("'");
			}
			if(!"0".equals(zdsx) && null != zdsx){
				wherestr.append(" AND ZD.PROPERTY='").append(zdsx).append("'");
			}
			if("1".equals(sjzt)){//已更新
				wherestr.append(" AND CS.PROVINCEPD = '0' AND CS.PROVINCEAUDIT = '1'");
			}else if("2".equals(sjzt)){//已派单
				wherestr.append(" AND CS.PROVINCEPD = '1'");
			}else {//0 未操作
				wherestr.append(" AND CS.PROVINCEPD = '0' AND CS.PROVINCEAUDIT = '0'");
			}
			if(!"".equals(zdname) && null != zdname){
				wherestr.append(" AND ZD.JZNAME LIKE '%").append(zdname).append("%'");
			}
			if(!"".equals(zdid) && null != zdid){
				wherestr.append(" AND ZD.ID='").append(zdid).append("'");
			}
			if(!"".equals(bl1) && null != bl1){
				wherestr2.append(" AND AA.BL>=").append(bl1);
			}
			if(!"".equals(bl2) && null != bl2){
				wherestr2.append(" AND AA.BL<=").append(bl2);
			}
			
			List<ProvinceSendBean> list = dao.selectProvinceSend(wherestr.toString(),wherestr2.toString(),accountid);
			int num = list.size();
			request.setAttribute("list", list);
			request.setAttribute("numcolor", num);
			if("chaxun".equals(action)){
				request.getRequestDispatcher("/web/appJSP/checkstandard/provincesend/provincesend.jsp").forward(request, response);
			}else if("daochu".equals(action)){
				request.getRequestDispatcher("/web/appJSP/checkstandard/provincesend/省手动更新及派单导出.jsp").forward(request, response);
			}
		}else if("detail".equals(action)){
			String zdid = request.getParameter("zdid");
			List<DetailBean> list = dao.getDetail(zdid);
			int num = list.size();
			request.setAttribute("list", list);
			request.setAttribute("numcolor", num);
			request.getRequestDispatcher("/web/appJSP/checkstandard/provincesend/detail.jsp").forward(request, response);
		}else if("paidan".equals(action)){
			HttpSession session = request.getSession();
			String personnal = request.getParameter("personnal");
			String[] power=request.getParameterValues("test[]");
//			StringBuffer buffer = new StringBuffer();
//			for (int i = 0; i < power.length; i++) {
//				String string = power[i];
//				buffer.append(string).append(",");
//			}
//			String b = buffer.substring(0, buffer.length()-1);
			String msg = "";
			String  url = path + "/web/appJSP/checkstandard/provincesend/provincesend.jsp" ;
			msg = dao.paiDan(power, personnal);
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}
	}

}
