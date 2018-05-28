package com.ptac.app.calibstat.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.mobi.common.Account;
import com.ptac.app.calibstat.bean.OverSite;
import com.ptac.app.calibstat.bean.OverSiteDetial;
import com.ptac.app.calibstat.dao.OverSiteDao;
import com.ptac.app.calibstat.dao.OverSiteDaoImpl;

/**
 * @author WangYiXiao
 * @see ����ͳ�Ʋ�ѯ������վ���ѯ����������ϸ��
 */
public class OverSiteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");//����
		if("chaxun".equals(command) || "daochu".equals(command)){
			addSiteExport(request,response);
		}else if("xiangqing".equals(command)){
			xiangQing(request,response);
		}
}

	/**
	 * @see ����վ�� ��ѯ������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addSiteExport(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String command = request.getParameter("command");//����
		//-----dao-----
		OverSiteDao dao = new OverSiteDaoImpl();
		//-----��ѯ����-----
		String shi = request.getParameter("shi");//��
		String month = request.getParameter("month");//�·�
		String property = request.getParameter("property");//վ������
		String zdlx = request.getParameter("zdlx");//վ������
		String cbmc = request.getParameter("cbmc");//����ǰn��
		String cbbl = request.getParameter("cbbl");//�����������
		String cbjdz = request.getParameter("cbjdz");//�������ֵ
		
		if (property == null || ("").equals(property) || ("0").equals(property)) {
			property = "";
		}
		if (zdlx == null || ("").equals(zdlx) || ("0").equals(zdlx)) {
			zdlx = "";
		}
	
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();

		//-----��ѯ-----
		List<OverSite> beanlist = dao.getOverSite(shi,month,property,zdlx,cbmc.trim(),cbbl.trim(),cbjdz.trim(),loginId);
		//-----�������-----
		int num = beanlist.size();

		request.setAttribute("beanlist", beanlist);
		request.setAttribute("num", num);
		//-----��תҳ��-----
		if("chaxun".equals(command)){//����վ���ѯ, ����
			request.getRequestDispatcher("/web/appJSP/calibstat/overshotSite/overshotSite.jsp").forward(request, response);
		}else if("daochu".equals(command)){
			request.getRequestDispatcher("/web/appJSP/calibstat/overshotSite/����վ���ѯ����.jsp").forward(request, response);
		}	
	}
	public void xiangQing(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//-----dao-----
		OverSiteDao dao = new OverSiteDaoImpl();
		//-----��ѯ����-----
		String zdid = request.getParameter("zdid");//��

	
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
	    String loginId = account.getAccountId();

		//-----��ѯ-----
		OverSiteDetial bean = dao.getOverSiteDetial(zdid);
		OverSiteDetial sdb = dao.getOverSiteSDB(zdid);
		List<OverSiteDetial> other = dao.getOverSiteDL(zdid);
		request.setAttribute("bean", bean);
		request.setAttribute("sdb", sdb);
		request.setAttribute("other", other);
		//-----��תҳ��-----
		request.getRequestDispatcher("/web/appJSP/calibstat/overshotSite/xiangxi.jsp").forward(request, response);
			
	}

}
