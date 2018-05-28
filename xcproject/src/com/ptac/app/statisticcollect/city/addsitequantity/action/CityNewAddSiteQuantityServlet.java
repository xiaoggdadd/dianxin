package com.ptac.app.statisticcollect.city.addsitequantity.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.city.addsitequantity.bean.CityNewAddSiteQuantityBean;
import com.ptac.app.statisticcollect.city.addsitequantity.bean.EleAndFeesBean;
import com.ptac.app.statisticcollect.city.addsitequantity.dao.CityNewAddSiteQuantityDao;
import com.ptac.app.statisticcollect.city.addsitequantity.dao.CityNewAddSiteQuantityDaoImpl;


/**
 * @author WangYiXiao 2014-2-14
 * @see 地市新增站点数量servlet
 * @update WangYiXiao 2014-5-21 新增站点数量导出
 */
public class CityNewAddSiteQuantityServlet extends HttpServlet{
	/**
	 * serialVersionUID作用： 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性
	 */
	private static final long serialVersionUID = 4387357330372475854L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		if("chaxun".equals(command) || "daochu".equals(command)){//新增站点查询, 导出
			addSiteExport(request,response);
		}else if("eleandfeeschaxun".equals(command) || "eleandfeesdaochu".equals(command)){//新增电量，电费查询,导出
			eleAndFeesQuery(request,response);
		}
}

	/**
	 * @see 新增站点数量 查询，导出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSiteExport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		//-----dao-----
		CityNewAddSiteQuantityDao dao = new CityNewAddSiteQuantityDaoImpl();
		//-----查询条件-----
		String shi = request.getParameter("shi");//市
		String zdsx = request.getParameter("zdsx");//站点属性
		System.out.println("56789------"+zdsx);
		String qyzt = request.getParameter("qyzt");//站点启用状态
		String beginyue = request.getParameter("beginyue");//起始月份
		String endyue = request.getParameter("endyue");//结束月份
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		//-----查询-----
		List<CityNewAddSiteQuantityBean> beanlist = dao.export(shi, beginyue, endyue, zdsx , qyzt, loginId);
		//-----结果条数-----
		int num = beanlist.size();
		//-----新增站点总数量,新增电费，新增电量，新增电费为负总和-----
		String[] all = dao.addSiteQuantiySum(beanlist);
		String sitesum = all[0];
		String newaddnum = all[1];
		String addfeesum = all[2];
		String addelectricsum = all[3];
		String addfeefusum = all[4];
		//-----往前台传递的参数-----
		request.setAttribute("shi", shi);
		request.setAttribute("zdsx", zdsx);
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("qyzt", qyzt);//站点启用状态
		request.setAttribute("numcolor", num);//beanlist条数，用于颜色设置
		request.setAttribute("sitesum", sitesum);
		request.setAttribute("newaddnum", newaddnum);
		request.setAttribute("addfeesum", addfeesum);
		request.setAttribute("addelectricsum", addelectricsum);
		request.setAttribute("addfeefusum", addfeefusum);
		//-----跳转页面-----
		if("chaxun".equals(command)){//新增站点查询, 导出
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/cityNewAddSiteQuantity.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/新增站点数量导出.jsp").forward(request, response);
		}	
	}
	public void eleAndFeesQuery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		//-----dao-----
		CityNewAddSiteQuantityDao dao = new CityNewAddSiteQuantityDaoImpl();
		//-----查询条件-----
		String shi = request.getParameter("shi");//市
		String zdsx = request.getParameter("zdsx");//站点属性
		System.out.println("56789------"+zdsx);
		String qyzt = request.getParameter("qyzt");//站点启用状态
		String beginyue = request.getParameter("beginyue");//起始月份
		String endyue = request.getParameter("endyue");//结束月份
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		//-----查询-----
		List<EleAndFeesBean> beanlist = dao.quaryEleAndFees(shi, beginyue, endyue, zdsx,qyzt, loginId);
		//-----结果条数-----
		int num = beanlist.size();
		//-----新增站点总数量,新增电费，新增电量，新增电费为负总和-----
	
		//-----往前台传递的参数-----
		request.setAttribute("shi", shi);
		request.setAttribute("zdsx", zdsx);
		request.setAttribute("qyzt", qyzt);//站点启用状态
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("numcolor", num);//beanlist条数，用于颜色设置

		//-----跳转页面-----
		if("eleandfeeschaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/EleAndFees.jsp").forward(request, response);
		}else if("eleandfeesdaochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/新增站点电量电费导出.jsp").forward(request, response);
		}
	}


}
