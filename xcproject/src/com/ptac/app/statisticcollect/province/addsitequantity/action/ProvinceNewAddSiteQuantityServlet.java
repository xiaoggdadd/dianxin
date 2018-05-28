package com.ptac.app.statisticcollect.province.addsitequantity.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.statisticcollect.province.addsitequantity.bean.ProvinceNewAddSiteQuantityBean;
import com.ptac.app.statisticcollect.province.addsitequantity.dao.ProvinceNewAddSiteQuantityDao;
import com.ptac.app.statisticcollect.province.addsitequantity.dao.ProvinceNewAddSiteQuantityDaoImpl;



/**@update WangyiXiao 2014-5-20 导出
 * @author WangYiXiao 2014-2-27
 * @see 省新增站点数量servlet
 */
public class ProvinceNewAddSiteQuantityServlet extends HttpServlet{
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
		addSiteExport(request,response);
}

	/**@see 新增站点查询，导出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSiteExport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//操作
		//-----dao-----
		ProvinceNewAddSiteQuantityDao dao = new ProvinceNewAddSiteQuantityDaoImpl();
		//-----查询条件-----
		String shi = request.getParameter("shi");//市
		String zdsx = request.getParameter("zdsx");//站点属性
		String qyzt = request.getParameter("qyzt");//站点启用状态
		System.out.println("zdsx------"+zdsx);
		String beginyue = request.getParameter("beginyue");//起始月份
		String endyue = request.getParameter("endyue");//结束月份
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		//-----查询-----
		List<ProvinceNewAddSiteQuantityBean> beanlist = dao.quaryExport(shi, beginyue, endyue, zdsx ,qyzt,loginId);
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
		request.setAttribute("qyzt", qyzt);
		request.setAttribute("numcolor", num);//beanlist条数，用于颜色设置
		request.setAttribute("sitesum", sitesum);
		request.setAttribute("newaddnum", newaddnum);
		request.setAttribute("addfeesum", addfeesum);
		request.setAttribute("addelectricsum", addelectricsum);
		request.setAttribute("addfeefusum", addfeefusum);
		
		if("chaxun".equals(command)){//新增站点查询
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/newaddsitequantity/provinceNewAddSiteQuantity.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/newaddsitequantity/新增站点数量导出.jsp").forward(request, response);
		}	
	}

}
