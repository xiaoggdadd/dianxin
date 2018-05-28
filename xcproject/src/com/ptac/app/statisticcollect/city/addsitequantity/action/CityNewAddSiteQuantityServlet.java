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
 * @see ��������վ������servlet
 * @update WangYiXiao 2014-5-21 ����վ����������
 */
public class CityNewAddSiteQuantityServlet extends HttpServlet{
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
		String command = request.getParameter("command");//����
		if("chaxun".equals(command) || "daochu".equals(command)){//����վ���ѯ, ����
			addSiteExport(request,response);
		}else if("eleandfeeschaxun".equals(command) || "eleandfeesdaochu".equals(command)){//������������Ѳ�ѯ,����
			eleAndFeesQuery(request,response);
		}
}

	/**
	 * @see ����վ������ ��ѯ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSiteExport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//����
		//-----dao-----
		CityNewAddSiteQuantityDao dao = new CityNewAddSiteQuantityDaoImpl();
		//-----��ѯ����-----
		String shi = request.getParameter("shi");//��
		String zdsx = request.getParameter("zdsx");//վ������
		System.out.println("56789------"+zdsx);
		String qyzt = request.getParameter("qyzt");//վ������״̬
		String beginyue = request.getParameter("beginyue");//��ʼ�·�
		String endyue = request.getParameter("endyue");//�����·�
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		//-----��ѯ-----
		List<CityNewAddSiteQuantityBean> beanlist = dao.export(shi, beginyue, endyue, zdsx , qyzt, loginId);
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
		request.setAttribute("qyzt", qyzt);//վ������״̬
		request.setAttribute("numcolor", num);//beanlist������������ɫ����
		request.setAttribute("sitesum", sitesum);
		request.setAttribute("newaddnum", newaddnum);
		request.setAttribute("addfeesum", addfeesum);
		request.setAttribute("addelectricsum", addelectricsum);
		request.setAttribute("addfeefusum", addfeefusum);
		//-----��תҳ��-----
		if("chaxun".equals(command)){//����վ���ѯ, ����
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/cityNewAddSiteQuantity.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/����վ����������.jsp").forward(request, response);
		}	
	}
	public void eleAndFeesQuery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//����
		//-----dao-----
		CityNewAddSiteQuantityDao dao = new CityNewAddSiteQuantityDaoImpl();
		//-----��ѯ����-----
		String shi = request.getParameter("shi");//��
		String zdsx = request.getParameter("zdsx");//վ������
		System.out.println("56789------"+zdsx);
		String qyzt = request.getParameter("qyzt");//վ������״̬
		String beginyue = request.getParameter("beginyue");//��ʼ�·�
		String endyue = request.getParameter("endyue");//�����·�
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();
		//-----��ѯ-----
		List<EleAndFeesBean> beanlist = dao.quaryEleAndFees(shi, beginyue, endyue, zdsx,qyzt, loginId);
		//-----�������-----
		int num = beanlist.size();
		//-----����վ��������,������ѣ������������������Ϊ���ܺ�-----
	
		//-----��ǰ̨���ݵĲ���-----
		request.setAttribute("shi", shi);
		request.setAttribute("zdsx", zdsx);
		request.setAttribute("qyzt", qyzt);//վ������״̬
		request.setAttribute("beanlist", beanlist);
		request.setAttribute("beginyue", beginyue);
		request.setAttribute("endyue", endyue);
		request.setAttribute("numcolor", num);//beanlist������������ɫ����

		//-----��תҳ��-----
		if("eleandfeeschaxun".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/EleAndFees.jsp").forward(request, response);
		}else if("eleandfeesdaochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/statisticscollect/city/newaddsitequantity/����վ�������ѵ���.jsp").forward(request, response);
		}
	}


}
