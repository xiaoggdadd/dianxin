package com.ptac.app.provinceapply.gdfs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.provinceapply.gdfs.bean.GdfBean;
import com.ptac.app.provinceapply.gdfs.dao.GdfDao;
import com.ptac.app.provinceapply.gdfs.dao.GdfDaoImp;

/**
 * @author lijing
 * @see 省申请供电方式审核
 */
public class GdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		//-----根据command不通选择不通处理方法------
		if("chaxun".equals(command)){//查询
			query(request,response);
		}else if("tongguo1".equals(command) || "tongguo2".equals(command) 
				|| "butongguo1".equals(command) || "butongguo2".equals(command) 
				|| "chehui1".equals(command) || "chehui2".equals(command)){//审核
			check(request,response);
		}
	}


	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县
		String sqnr = request.getParameter("sqnr");//申请内容
		String shengzt = request.getParameter("shengzt");//审核状态
		String zdname = request.getParameter("zdname");//站点名称
		String zdid = request.getParameter("zdid");//站点ID
		String property = request.getParameter("zdsx1");//站点属性
		String zdlx = request.getParameter("zdlx1");//站点类型
		
		StringBuffer whereStr = new StringBuffer();//查询条件
		if (zdid != null && !zdid.equals("")){
			whereStr.append(" AND Z.ID = '" + zdid + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" AND Z.JZNAME LIKE '%" + zdname + "%'");
		}
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" AND Z.SHI='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			 whereStr.append(" AND Z.XIAN='" + xian + "'");
		}
		if("0".equals(sqnr)){
			if (sqnr != null && !sqnr.equals("") && !sqnr.equals("-1")){
				whereStr.append(" AND (Q.OLDGDFS = 'gdfs06' or Q.OLDGDFS = 'gdfs07') AND (Q.NEWGDFS = 'gdfs05' or Q.NEWGDFS = 'gdfs08')"); 
			}
		}else if("1".equals(sqnr)){
			if (sqnr != null && !sqnr.equals("") && !sqnr.equals("-1")){
				whereStr.append(" AND (Q.OLDGDFS = 'gdfs05' or Q.OLDGDFS = 'gdfs08') AND (Q.NEWGDFS = 'gdfs06' or Q.NEWGDFS = 'gdfs07')"); 
			}
		}
		if("0".equals(shengzt)){
			whereStr.append(" AND (Q.BFTG NOT LIKE '%5%' OR Q.BFTG IS NULL) AND (Q.BFBTG NOT LIKE '%5%' OR Q.BFBTG IS NULL)");	
		}else if("1".equals(shengzt)){
			whereStr.append(" AND Q.BFTG LIKE '%5%'");	
		}
		if (property != null && !property.equals("") && !property.equals("0")&&!property.equals("请选择")){
			whereStr.append(" AND Q.NEWPROPERTY in(" + property + ")");
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")&&!zdlx.equals("请选择")){
			whereStr.append(" AND Q.NEWSTATIONTYPE in(" + zdlx + ")");
		}
		
		GdfDao dao = new GdfDaoImp();
		List<GdfBean> list = dao.query(whereStr.toString(),loginId,shengzt);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/web/zdqxkz/gdfsshenhe.jsp").forward(request, response);
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		GdfDao dao = new GdfDaoImp();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//审核人员
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();//日志
	    String url = "",msg = "";//路径，提示信息
		String command = request.getParameter("command");
		if("tongguo1".equals(command)){//通过
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "省申请供电方式审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("tongguo2".equals(command)){
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "省申请供电方式审核"); 
	        String m="";
	        if(msg=="供电方式审核信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("butongguo2".equals(command)){//审核不通过
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
	        msg = dao.check(list,personnal,cause,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "省申请供电方式审核");
	        String m="";
	        if(msg=="供电方式审核信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("butongguo1".equals(command)){//不通过部分
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
	        msg = dao.check(list,personnal,cause,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "省申请供电方式审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("chehui2".equals(command)){//取消通过
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "省申请供电方式审核");
	        String m="";
	        if(msg=="供电方式审核信息失败！"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("chehui1".equals(command)){//取消通过
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "省申请供电方式审核"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}
	}
}
