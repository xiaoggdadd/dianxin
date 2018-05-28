package com.ptac.app.electricmanage.liuchenghaobill.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.liuchenghaobill.bean.LiuchBean;
import com.ptac.app.electricmanage.liuchenghaobill.dao.LiuchDao;
import com.ptac.app.electricmanage.liuchenghaobill.dao.LiuchDaoImp;
import com.ptac.app.electricmanageUtil.Format;

public class LiuchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		
		if("chaxun".equals(command)){//查询
			query(request,response);
		}else if("xiangxi".equals(command)){//详细
			xiangxi(request,response);
		}	
	}

	private void query(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute("account");
			String loginId = account.getAccountId();
			
			String shi = request.getParameter("shi");//市
			String xian = request.getParameter("xian");//区县
			String liuch = request.getParameter("liuch");//流程号
			String bztime = request.getParameter("bztime");//报账月份
			String shengzt = request.getParameter("shengzt");//财务审核状态
			
			StringBuffer whereStr = new StringBuffer();//查询条件
			if (shi != null && !shi.equals("") && !shi.equals("0")){
				 whereStr.append(" AND ZD.SHI='" + shi + "'");
			}
			if (xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr.append(" AND ZD.XIAN ='" + xian + "'");
			}
			if (liuch != null && !liuch.equals("")){
				whereStr.append(" AND DY.LIUCHENGHAO = '" + liuch + "'");
			}
			if (bztime != null && !bztime.equals("")) {
				whereStr.append(" and to_char(DY.ACCOUNTMONTH,'yyyy-mm')='" + bztime + "'");
			}
			if("0".equals(shengzt)){
				whereStr.append(" AND DY.MANUALAUDITSTATUS >= '1'");	
			}else if("-1".equals(shengzt)){
				whereStr.append(" AND DY.MANUALAUDITSTATUS = '-1'");	
			}
			
			LiuchDao dao = new LiuchDaoImp();
			List<LiuchBean> list = dao.query(whereStr.toString(),loginId);
			int num = list.size();
			
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			request.setAttribute("whereStr", whereStr.toString());
			request.getRequestDispatcher("/web/appJSP/electricmanage/liuchenghaobill/liuchenghao.jsp").forward(request, response);
	}

	private void xiangxi(HttpServletRequest request,HttpServletResponse response) 
		throws ServletException, IOException {

			String liuch = request.getParameter("liuch");//流程号
			String str = request.getParameter("str");
			StringBuffer whereStr = new StringBuffer();//查询条件
			
			if (liuch != null && !liuch.equals("")){
				whereStr.append(" AND dy.LIUCHENGHAO = '" + liuch + "'");
			}
			
			LiuchDao dao = new LiuchDaoImp();
			List<LiuchBean> list = dao.xiangxi(whereStr.toString(),str);
			int num = list.size();
			String zjesum = "0.00";//总金额sum
			String zsesum = "0.00";//增值税额sum
			if(list!=null){
				String[] sum = dao.getSum(list);
				zjesum = sum[0];
				zsesum = sum[1];
			}
			
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			request.setAttribute("liuch", liuch);
			request.setAttribute("zjesum", zjesum);
			request.setAttribute("zsesum", zsesum);
			request.getRequestDispatcher("/web/appJSP/electricmanage/liuchenghaobill/info.jsp").forward(request, response);
	}
}
