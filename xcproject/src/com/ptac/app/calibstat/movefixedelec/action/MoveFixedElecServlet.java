package com.ptac.app.calibstat.movefixedelec.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.movefixedelec.bean.MoveFixedElec;
import com.ptac.app.calibstat.movefixedelec.dao.MoveFixedElecDao;
import com.ptac.app.calibstat.movefixedelec.dao.MoveFixedElecDaoImpl;
import com.ptac.app.calibstat.movefixedelec.util.DateUtil;
import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;

public class MoveFixedElecServlet extends HttpServlet {
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
			String city= "";
			MoveFixedElecDao dao = new MoveFixedElecDaoImpl();
			
			String[] time = null;
			try {
				time = new DateUtil().getCompare(begintime, endtime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//------查询结果集------
			List<MoveFixedElec> list = dao.getMoveFixedFee(sheng,shi, begintime, endtime, time, loginId);

			//city = list.get(0).getShi();
			city = list.size()==0?"":list.get(0).getShi();

			//------获得结果集和结果条数------
			int num = list.size();//结果条数
			//------获得电量合计和电费合计------
			BigDecimal fixedfee = null;
			BigDecimal movefee = null;
			BigDecimal fee = null;
			BigDecimal fixedelec = null;
			BigDecimal moveelec = null;
			BigDecimal elec = null;
			if(0 !=list.size()){
				BigDecimal[] total = dao.total(list);
				fixedfee = total[0];//	
				movefee = total[1];//
				fee = total[2];
				fixedelec = total[3];
				moveelec = total[4];
				elec = total[5];
			}
			//------向前台页面传值------
			request.setAttribute("city", city);
			request.setAttribute("fixedfee", fixedfee);
			request.setAttribute("movefee", movefee);
			request.setAttribute("fee", fee);
			request.setAttribute("fixedelec", fixedelec);
			request.setAttribute("moveelec", moveelec);
			request.setAttribute("elec", elec);
			request.setAttribute("num", num);//结果条数
			request.setAttribute("list", list);//结果集
			request.setAttribute("by1", begintime);
			request.setAttribute("endyue1", endtime);
			request.setAttribute("beginyue2", time[0]);
			request.setAttribute("endyue2", time[1]);
			//------根据前台按钮标识判断提交方向
			if("chaxun".equals(command)){//传到查询页面 
				request.getRequestDispatcher("/web/appJSP/calibstat/movefixedelec/MoveFixedElec.jsp").forward(request, response);
			}else if("daochu".equals(command)){//传到导出页面
				request.getRequestDispatcher("/web/appJSP/calibstat/movefixedelec/移网固网电量统计导出.jsp").forward(request, response);
			}
		}
	}

}
