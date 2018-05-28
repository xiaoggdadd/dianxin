package com.ptac.app.statisticcollect.province.elecfeesanalyse.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.elecfeesanalyse.bean.ElecFeesAnalyseBean;
import com.ptac.app.statisticcollect.province.elecfeesanalyse.dao.ElecfeesAnalyseDao;
import com.ptac.app.statisticcollect.province.elecfeesanalyse.dao.ElecfeesAnalyseDaoImpl;
import com.ptac.app.statisticcollect.util.YearYueUtil;

public class ElecfeesAnalyseServlet extends HttpServlet {

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

		String action = request.getParameter("command");
		System.out.println("action:"+action);
		String accountmonth = request.getParameter("beginbztime");//报账月份
		String property = request.getParameter("property");//站点属性
		String status = request.getParameter("status");//审核状态
		String zzs = request.getParameter("zzs");//是否包含增值税
		
		YearYueUtil util = new YearYueUtil();
		//上年同月
		String[] lastsameyue = util.lastSameYue(accountmonth);
		String[] last1 = lastsameyue[0].split("-");
		String nian1 = last1[0];
		Integer yue1 = Integer.valueOf(last1[1]);
		//上个月
		String lastyue = util.lastYue(accountmonth);
		String[] last2 = lastyue.split("-");
		String nian2 = last2[0];
		Integer yue2 = Integer.valueOf(last2[1]);
		//当年当月
		String[] bzyf = accountmonth.split("-");
		String nian3 = bzyf[0];
		Integer yue3 = Integer.valueOf(bzyf[1]);
		//上年一月
		String[] lastone = lastsameyue[1].split("-");
		String nian4 = lastone[0];
		Integer yue4 = Integer.valueOf(lastone[1]);
		//当年一月
		String oneyue = util.OneYue(accountmonth);
		//暂估差相关年月
		//去年同月的上个月
		String zan1 = util.lastYue(lastsameyue[0]);
		//报账月份上个月的上个月
		String zan2 = util.lastYue(lastyue);
		//上年1月的上个月
		String zan3 = util.lastYue(lastsameyue[1]);
		//当年1月的上个月
		String zan4 = util.lastYue(oneyue);
		
		String[] yearyue = new String[9];
		yearyue[0] =  lastsameyue[0];//上年同月
		yearyue[1] =  lastyue;//报账月份上个月
		yearyue[2] = accountmonth;//报账月份
		yearyue[3] = lastsameyue[1];//上年一月
		yearyue[4] = oneyue;//当年一月
		yearyue[5] = zan1;//去年同月的上个月
		yearyue[6] = zan2;//报账月份上个月的上个月
		yearyue[7] = zan3;//上年1月的上个月
		yearyue[8] = zan4;//当年1月的上个月
		
		ElecfeesAnalyseDao dao = new ElecfeesAnalyseDaoImpl();
		List<ElecFeesAnalyseBean> list = new ArrayList<ElecFeesAnalyseBean>();
		list = dao.queryDetails(yearyue, property, status, zzs);
		int num = list.size();
		String[] total = new String[8];
		total = dao.total(list);
		
		request.setAttribute("list", list);
		request.setAttribute("num", num);
		request.setAttribute("total1", total[0]);
		request.setAttribute("total2", total[1]);
		request.setAttribute("total3", total[2]);
		request.setAttribute("total4", total[3]+"%");
		request.setAttribute("total5", total[4]+"%");
		request.setAttribute("total6", total[5]);
		request.setAttribute("total7", total[6]);
		request.setAttribute("total8", total[7]+"%");
		request.setAttribute("nian1", nian1);
		request.setAttribute("yue1", yue1);
		request.setAttribute("nian2", nian2);
		request.setAttribute("yue2", yue2);
		request.setAttribute("nian3", nian3);
		request.setAttribute("yue3", yue3);
		request.setAttribute("nian4", nian4);
		request.setAttribute("yue4", yue4);
		if("chaxun".equals(action)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/elecfeesanalyse/elecfeesanalyse.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/elecfeesanalyse/elecfeesanalyseExport.jsp").forward(request, response);
		}
	}

}
