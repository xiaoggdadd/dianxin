package com.ptac.app.systemmanage.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.systemmanage.bean.LuRuLiangBean;
import com.ptac.app.systemmanage.dao.LuRuLiangDao;
import com.ptac.app.systemmanage.dao.LuRuLiangDaoImpl;
public class LuRuLiangServlet extends HttpServlet {
	/**
	 * 
	 */
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
		String shi = request.getParameter("shi");
		String startyear = request.getParameter("startyear");
		String endyear = request.getParameter("endyear");
		String lx = request.getParameter("lx");
		
		if(lx.equals("dbyt02")){
			lx="dbyt01";
		}
		List<LuRuLiangBean> list = null;
		if("chaxun".equals(command)|| "daochu".equals(command)){
			if(lx.equals("dbyt01")){
				list = queryLuRuCountjs(shi,startyear,endyear);
			}else if(lx.equals("dbyt03")){
				System.out.println("管理");
				list = queryLuRuCountgl(shi,startyear,endyear);
			}
		}
		long sum =0;
		if(lx.equals("dbyt01")){
			sum = sumCount(shi,startyear,endyear);
		}else if(lx.equals("dbyt03")){
			sum = sumCountgl(shi,startyear,endyear);
		}
		request.setAttribute("sum", sum);
		request.setAttribute("list", list);
		 if("xiangqing".equals(command)){  
			 if(lx.equals("dbyt01")){
				 String dataString = xiangQing(shi,startyear,endyear);
				 request.setAttribute("dataString", dataString );
			 }else if(lx.equals("dbyt03")){
				 
				 String dataString = xiangQinggl(shi,startyear,endyear);
				 request.setAttribute("dataString", dataString );
			 }
		}
		if("chaxun".equals(command) || "xiangqing".equals(command)){
			request.getRequestDispatcher("/web/sys/LuRuLiang.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/sys/录入量.jsp").forward(request, response);
		}
		
	}
	private String xiangQinggl(String shi, String startyear, String endyear) {
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		LuRuLiangBean bean = new LuRuLiangBean();
		List<LuRuLiangBean> list = dao.getXiangXigl(shi,startyear,endyear);
		int length = list.size();
	    String dataString = "";
	    for(int i=0;i<length;i++){
	    	bean = list.get(i);
	    	if(bean != null){
	    		 dataString += "<chart caption='' subcaption='' xAxisName='年月日' yAxisName='录入量' numberSuffix='' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
	    		 dataString += "<set label='"+bean.getYear()+"' value='"+(bean.getLcount())+"' />";
	    		 dataString += "</chart>";
	    	}
	    }
	    System.out.println(dataString);
		return dataString;
	}
	private  Long sumCount(String shi, String startyear, String endyear) {
		long sum = 0;
		String sumss;
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		LuRuLiangBean bean = new LuRuLiangBean();
		List<LuRuLiangBean> list = dao.getXiangXi(shi,startyear,endyear);
		int length = list.size();
		for(int i=0;i<length;i++){
	    	bean = list.get(i);
	    	sumss = bean.getLcount();
	    	sum = sum+Long.parseLong(sumss);
	    }
		return sum;
	}
	private  Long sumCountgl(String shi, String startyear, String endyear) {
		long sum = 0;
		String sumss;
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		LuRuLiangBean bean = new LuRuLiangBean();
		List<LuRuLiangBean> list = dao.getXiangXigl(shi,startyear,endyear);
		int length = list.size();
		for(int i=0;i<length;i++){
	    	bean = list.get(i);
	    	sumss = bean.getLcount();
	    	sum = sum+Long.parseLong(sumss);
	    }
		return sum;
	}
	private List<LuRuLiangBean> queryLuRuCountjs(String shi, String startyear,
			String endyear) {
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		List<LuRuLiangBean> list = dao.queryLuRuCountjs(shi,startyear,endyear);
		return list;
		
	}
	private List<LuRuLiangBean> queryLuRuCountgl(String shi, String startyear,
			String endyear) {
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		List<LuRuLiangBean> list = dao.queryLuRuCountgl(shi,startyear,endyear);
		return list;
	}
	private String xiangQing(String shi, String startyear, String endyear){
		LuRuLiangDao dao = new LuRuLiangDaoImpl();
		LuRuLiangBean bean = new LuRuLiangBean();
		List<LuRuLiangBean> list = dao.getXiangXi(shi,startyear,endyear);
		int length = list.size();
	    String dataString = "";
	    for(int i=0;i<length;i++){
	    	bean = list.get(i);
	    	if(bean != null){
	    		 dataString += "<chart caption='' subcaption='' xAxisName='年月日' yAxisName='录入量' numberSuffix='' showLabels='1' showColumnShadow='1' animation='1' showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' canvasBorderColor='666666' baseFontColor='666666' lineColor='FF5904' lineAlpha='85' showValues='1' rotateValues='0' valuePosition='auto'>";
	    		 dataString += "<set label='"+bean.getYear()+"' value='"+(bean.getLcount())+"' />";
	    		 dataString += "</chart>";
	    	}
	    }
	    System.out.println("--------------------"+dataString+"------------------");
		return dataString;
	}
}
