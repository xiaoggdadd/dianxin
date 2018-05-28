package com.ptac.app.statisticcollect.province.pricecriticism.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptac.app.statisticcollect.province.accountwithout.bean.AccountWithoutBean;
import com.ptac.app.statisticcollect.province.pricecriticism.bean.PriceCriticismBean;
import com.ptac.app.statisticcollect.province.pricecriticism.dao.PriceCriticismDao;
import com.ptac.app.statisticcollect.province.pricecriticism.dao.PriceCriticismDaoImpl;
/**
 * 
 * @author zx 2015-03-23
 * @see 电价超标站点通报
 */

public class PriceCriticismServlet extends HttpServlet {
	
	//serialVersionUID作用： 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	private static final long serialVersionUID = 4387357330372475854L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		String action = request.getParameter("action");
		if("price".equals(action)){
			String command = request.getParameter("command");
			String bzyf = request.getParameter("bzyf")!=null?request.getParameter("bzyf"):"";
			String dfshzt = request.getParameter("dfshzt")!=null?request.getParameter("dfshzt"):"";
			String cbbly = request.getParameter("cbbly")!=null?request.getParameter("cbbly"):"";
			String cbble = request.getParameter("cbble")!=null?request.getParameter("cbble"):"";
			String jzproperty = request.getParameter("jzproperty")!=null?request.getParameter("jzproperty"):"";
			String wherestr ="";
			if(bzyf!=null&&!bzyf.equals("")){
				wherestr = wherestr+" AND TO_CHAR(E.ACCOUNTMONTH,'YYYY-MM')='"+bzyf+"' ";
			}
			if(dfshzt!=null&&!dfshzt.equals("")){
				if(dfshzt.equals("2")){
				wherestr = wherestr+" AND E.MANUALAUDITSTATUS='2' ";//财务审核
				}else{
					wherestr = wherestr+" AND E.CITYZRAUDITSTATUS='1' ";//业务审核
				}
			}
			if(jzproperty!=null&&!jzproperty.equals("")&&!jzproperty.equals("0")){
					wherestr = wherestr+" AND Z.PROPERTY='"+jzproperty+"' ";
			}
			
			PriceCriticismDao dao = new PriceCriticismDaoImpl();
			List<PriceCriticismBean> list = dao.checkPrice(wherestr, cbbly, cbble);
			int num = list.size();
			String[] sum = dao.getSum(list);
			PriceCriticismBean sumbean = new PriceCriticismBean();
			
			sumbean.setBzzdshj(sum[0]);//报账站点数合计
			sumbean.setCbyhj(sum[1]);//超标20%站点数合计
			sumbean.setCbehj(sum[2]);//超标50%站点数合计
			sumbean.setCbzbyhj(sum[3]);//超标20%占比站点数合计
			sumbean.setCbzbehj(sum[4]);//超标50%占比站点数合计
			
			request.setAttribute("num", num);
			request.setAttribute("sumbean", sumbean);
			request.setAttribute("list", list);
			request.setAttribute("cbbly", cbbly);
			request.setAttribute("cbble", cbble);
			
			
			if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/statisticscollect/province/pricecriticism/pricecriticism.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("/web/appJSP/statisticscollect/province/pricecriticism/priceexport.jsp").forward(request, response);
			}
		}
	}
	
	

}
