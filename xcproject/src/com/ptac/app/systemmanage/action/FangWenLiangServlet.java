package com.ptac.app.systemmanage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.systemmanage.bean.FangWenLiangBean;
import com.ptac.app.systemmanage.bean.LuRuLiangBean;
import com.ptac.app.systemmanage.dao.LuRuLiangDao;
import com.ptac.app.systemmanage.dao.LuRuLiangDaoImpl;

public class FangWenLiangServlet extends HttpServlet {
	private static final long serialVersionUID = 5432412261347357662L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String command = request.getParameter("command");
		String startyear = request.getParameter("startyear");
		String endyear = request.getParameter("endyear");
		 if("fangwenliangmx".equals(command)){  
			 String dataString = fangwenliangmx(startyear,endyear);
			 request.setAttribute("dataString", dataString );
		}
		List<FangWenLiangBean> list =  queryFangWenCount(startyear,endyear);
		long sum = sumCount(startyear,endyear);
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		request.setAttribute("num", list.size());
		if("chaxun".equals(command) || "fangwenliangmx".equals(command)){
			request.getRequestDispatcher("/web/sys/FangWenLiang.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/citysiteanalysis/访问量.jsp").forward(request, response);
		}
		
	}
	private List<FangWenLiangBean> queryFangWenCount( String startyear,
			String endyear) {
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		List<FangWenLiangBean> list = dao.getFangWenLiang(startyear,endyear);
		return list;
	}
	private  Long sumCount( String startyear, String endyear) {
		long sum = 0;
		String sumss;
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		FangWenLiangBean bean = new FangWenLiangBean();
		List<FangWenLiangBean> list = dao.getFangWenLiangmx(startyear,endyear);
		int length = list.size();
		for(int i=0;i<length;i++){
	    	bean = list.get(i);
	    	sumss = bean.getFcount();
	    	sum = sum+Long.parseLong(sumss);
	    }
		return sum;
	}
	private String fangwenliangmx( String startyear, String endyear){
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		FangWenLiangBean bean = new FangWenLiangBean();
		List<FangWenLiangBean> list = dao.getFangWenLiangmx(startyear,endyear);
		int length = list.size();
	    String dataString = "";
	    for(int i=0;i<length;i++){
	    	bean = list.get(i);
	    	if(bean != null){
	    		 dataString += "<chart caption='' subcaption='' xAxisName='年月日' yAxisName='访问量' numberSuffix='' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
	    		 dataString += "<set label='"+bean.getYear()+"' value='"+(bean.getFcount())+"' />";
	    		 dataString += "</chart>";
	    	}
	    }
	    System.out.println(dataString);
		return dataString;
	}
}
