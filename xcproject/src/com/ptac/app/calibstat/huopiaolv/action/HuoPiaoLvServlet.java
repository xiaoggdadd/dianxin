package com.ptac.app.calibstat.huopiaolv.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.huopiaolv.bean.HuoPiaoLv;
import com.ptac.app.calibstat.huopiaolv.dao.HuoPiaoLvDao;
import com.ptac.app.calibstat.huopiaolv.dao.HuoPiaoLvDaoImpl;

public class HuoPiaoLvServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		if(null==account){
			response.sendRedirect(request.getContextPath() + "/web/index.jsp");
		}else{
			String loginId = account.getAccountId();
			String sheng = account.getSheng();
			//------获取前台操作标识------
			String command = request.getParameter("command");
			//------获取前台限制条件------
			String shi = request.getParameter("shi");//市
			String begintime = request.getParameter("begintime");
			String endtime = request.getParameter("endtime");
			HuoPiaoLvDao dao = new HuoPiaoLvDaoImpl();
			//------查询结果集------
			List<HuoPiaoLv> list = dao.getHuoPiaoLv(sheng, shi, begintime, endtime, loginId);
			//------获得结果集和结果条数------
			int num = list.size();//结果条数
			BigDecimal sumtax = null;
			BigDecimal sumelecfees = null;
			BigDecimal sumvat = null;
			if(0 != list.size()){
				BigDecimal[] total = dao.total(list);
				sumtax = total[0];
				sumelecfees = total[1];
				sumvat = total[2]; 
			}
			//------向前台页面传值------
			request.setAttribute("sumtax", sumtax);
			request.setAttribute("sumelecfees", sumelecfees);
			request.setAttribute("sumvat", sumvat);
			request.setAttribute("num", num);//结果条数
			request.setAttribute("list", list);//结果集
			//------根据前台按钮标识判断提交方向
			if("chaxun".equals(command)){//传到查询页面 
				request.getRequestDispatcher("/web/appJSP/calibstat/huopiaolv/huoPiaoLv.jsp").forward(request, response);
			}else if("daochu".equals(command)){//传到导出页面
				request.getRequestDispatcher("/web/appJSP/calibstat/huopiaolv/获票率统计导出.jsp").forward(request, response);
			}
		}
	}

}
