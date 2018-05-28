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



/**@update WangyiXiao 2014-5-20 ����
 * @author WangYiXiao 2014-2-27
 * @see ʡ����վ������servlet
 */
public class ProvinceNewAddSiteQuantityServlet extends HttpServlet{
	/**
	 * serialVersionUID���ã� ���л�ʱΪ�˱��ְ汾�ļ����ԣ����ڰ汾����ʱ�����л��Ա��ֶ����Ψһ��
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

	/**@see ����վ���ѯ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSiteExport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//����
		//-----dao-----
		ProvinceNewAddSiteQuantityDao dao = new ProvinceNewAddSiteQuantityDaoImpl();
		//-----��ѯ����-----
		String shi = request.getParameter("shi");//��
		String zdsx = request.getParameter("zdsx");//վ������
		String qyzt = request.getParameter("qyzt");//վ������״̬
		System.out.println("zdsx------"+zdsx);
		String beginyue = request.getParameter("beginyue");//��ʼ�·�
		String endyue = request.getParameter("endyue");//�����·�
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		//-----��ѯ-----
		List<ProvinceNewAddSiteQuantityBean> beanlist = dao.quaryExport(shi, beginyue, endyue, zdsx ,qyzt,loginId);
		//-----�������-----
		int num = beanlist.size();
		//-----����վ��������,������ѣ������������������Ϊ���ܺ�-----
		String[] all = dao.addSiteQuantiySum(beanlist);
		String sitesum = all[0];
		String newaddnum = all[1];
		String addfeesum = all[2];
		String addelectricsum = all[3];
		String addfeefusum = all[4];
		//-----��ǰ̨���ݵĲ���-----
		request.setAttribute("shi", shi);
		request.setAttribute("zdsx", zdsx);
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("qyzt", qyzt);
		request.setAttribute("numcolor", num);//beanlist������������ɫ����
		request.setAttribute("sitesum", sitesum);
		request.setAttribute("newaddnum", newaddnum);
		request.setAttribute("addfeesum", addfeesum);
		request.setAttribute("addelectricsum", addelectricsum);
		request.setAttribute("addfeefusum", addfeefusum);
		
		if("chaxun".equals(command)){//����վ���ѯ
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/newaddsitequantity/provinceNewAddSiteQuantity.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/province/newaddsitequantity/����վ����������.jsp").forward(request, response);
		}	
	}

}
