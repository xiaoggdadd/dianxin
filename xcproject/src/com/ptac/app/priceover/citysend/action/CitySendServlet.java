package com.ptac.app.priceover.citysend.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.priceover.citysend.bean.CitySendBean;
import com.ptac.app.priceover.citysend.dao.CitySendDao;
import com.ptac.app.priceover.citysend.dao.CitySendDaoImp;

/**
 * @author lijing
 * @see ����ǩ�ռ��ɵ�
 */
public class CitySendServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		String loginId = account.getAccountId();
		String loginName = account.getAccountName();
		String path = request.getContextPath();
		String command = request.getParameter("command");
		
		if("chaxun".equals(command) || "daochu".equals(command)){
			//����ǩ�ռ��ɵ���ѯ������
			String shi = request.getParameter("shi");//��
			String xian = request.getParameter("xian");//����	
			String property = request.getParameter("property");//վ������
			String state = request.getParameter("state");//ʡ��״̬
			String accountmonth = request.getParameter("accountmonth");//�����·�
			String beginbl = request.getParameter("beginbl");//����
			String endbl = request.getParameter("endbl");//����
			String zdmc = request.getParameter("zdmc");//վ������
			String zdid = request.getParameter("zdid");//վ��ID	
			String gdfs = request.getParameter("gdfs");//���緽ʽ
			
			StringBuffer whereStr = new StringBuffer();//��ѯ����
			if (shi != null && !shi.equals("") && !shi.equals("0")){
				 whereStr.append(" and shi='" + shi + "'");
			}
			if (xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr.append(" and xian='" + xian + "'"); 
			}
			if (property != null && !property.equals("") && !property.equals("0")){
				whereStr.append(" and PROPERTY='" + property + "'");
			}
			if (state != null && !state.equals("") && !state.equals("-1")){
				whereStr.append(" and CITYPD='" + state + "'");
			}
			if (accountmonth != null && !accountmonth.equals("") && !accountmonth.equals("0")){
				whereStr.append(" and to_char(ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'");
			}
			if (zdmc != null && !zdmc.equals("")){
				whereStr.append(" and jzname  like '%" + zdmc + "%'");
			}
			if (zdid != null && !zdid.equals("")){
				whereStr.append(" and ZDID='" + zdid + "'");	
			}
			if (gdfs != null && !gdfs.equals("") && !gdfs.equals("0")){
				whereStr.append(" and GDFS='" + gdfs + "'");	
			}
			
			CitySendDao dao = new CitySendDaoImp();
			List<CitySendBean> list = dao.queryExport(whereStr.toString(),loginId,beginbl,endbl);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			
			if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/citysend/citysend.jsp").forward(request, response);
			}else if("daochu".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/citysend/citySendExport.jsp").forward(request, response);
			}
		}else if("paidan".equals(command)){
			//�����ɵ�
			String url = path+ "/web/appJSP/priceover/citysend/citysend.jsp",msg="";
			CitySendDao dao = new CitySendDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.pdUpdate(id, loginName);
			if(rs==1){
				msg="�����ɵ��ɹ�!";
			}else if(rs==0){
				msg="�����ɵ�ʧ��!";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("paidan1".equals(command)){//ajaxִ�в���
			//�����ɵ�
			String url = path+ "/web/appJSP/priceover/citysend/citysend.jsp",msg="";
			PrintWriter out = response.getWriter();
			CitySendDao dao = new CitySendDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.pdUpdate(id, loginName);
		    if(rs==0){
		    	msg="�����ɵ�ʧ��!";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }
	        out.print(rs);
	        out.flush();
	        out.close();
		}else if("info".equals(command)){
			//����ǩ�ռ��ɵ���ϸ��Ϣ
			String zdid = request.getParameter("zdid");
			String bzyf = request.getParameter("bzyf");
			CitySendDao dao = new CitySendDaoImp();
			List<CitySendBean> list = dao.getInfo(zdid,bzyf);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list",list);
			request.getRequestDispatcher("/web/appJSP/priceover/citysend/info.jsp").forward(request, response);
		}
	}

}
