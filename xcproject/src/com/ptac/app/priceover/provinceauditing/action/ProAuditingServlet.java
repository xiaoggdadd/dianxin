package com.ptac.app.priceover.provinceauditing.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.priceover.citycheck.bean.CityCheckBean;
import com.ptac.app.priceover.citycheck.dao.CityCheckDao;
import com.ptac.app.priceover.citycheck.dao.CityCheckDaoImp;
import com.ptac.app.priceover.provinceauditing.bean.ProAuditingBean;
import com.ptac.app.priceover.provinceauditing.dao.ProAuditingDao;
import com.ptac.app.priceover.provinceauditing.dao.ProAuditingDaoImp;

/**
 * @author lijing
 * @see ���۳��������
 */
public class ProAuditingServlet extends HttpServlet {

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
		//PrintWriter out = response.getWriter();
		
		if("chaxun".equals(command) || "daochu".equals(command)){
			//����˲�ѯ������
			String shi = request.getParameter("shi");//��
			String xian = request.getParameter("xian");//����	
			String property = request.getParameter("property");//վ������
			String state = request.getParameter("state");//ʡ�����״̬
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
				whereStr.append(" and PROVINCEAUDIT='" + state + "'");
			}
			if (accountmonth != null && accountmonth != "" && !accountmonth.equals("0")){
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
			
			ProAuditingDao dao = new ProAuditingDaoImp();
			List<ProAuditingBean> list = dao.queryExport(whereStr.toString(),loginId,beginbl,endbl);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			
			if("chaxun".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/provinceauditing/provinceauditing.jsp").forward(request, response);
			}else if("daochu".equals(command)){
				request.getRequestDispatcher("/web/appJSP/priceover/provinceauditing/provinceauditingExport.jsp").forward(request, response);
			}
		}else if("info".equals(command)){
			//�м� ��ʡ�������ϸ��Ϣ
			String id = request.getParameter("id");
			String bzyf = request.getParameter("bzyf");
			CityCheckDao dao = new CityCheckDaoImp();
			CityCheckBean bean = dao.getInfo(id,bzyf);
			request.setAttribute("bean",bean);
			request.getRequestDispatcher("/web/appJSP/priceover/citycheck/info.jsp").forward(request, response);
		}else if("tuidan".equals(command)){
			//ʡ������˵�
			String url = path+ "/web/appJSP/priceover/provinceauditing/provinceauditing.jsp",msg="";
			ProAuditingDao dao = new ProAuditingDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tdUpdate(id, loginName);
			if(rs==1){
				msg="�˵��ɹ�!";
			}else if(rs==0){
				msg="�˵�ʧ��!";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("tuidan1".equals(command)){ //ajaxִ�в���
			PrintWriter out = response.getWriter();
			//ʡ������˵�
			String url = path+ "/web/appJSP/priceover/provinceauditing/provinceauditing.jsp",msg="";
			ProAuditingDao dao = new ProAuditingDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tdUpdate(id, loginName);
			  if(rs==0){
			    	msg="�˵�ʧ��!";
		            session.setAttribute("url", url);
		            session.setAttribute("msg", msg);
		        }
		        out.print(rs);
		        out.flush();
		        out.close();
		}else if("tongguo".equals(command)){
			//ʡ�����ͨ��
			String url = path+ "/web/appJSP/priceover/provinceauditing/provinceauditing.jsp",msg="";
			ProAuditingDao dao = new ProAuditingDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tgUpdate(id, loginName);
			if(rs==1){
				msg="ʡ����˳ɹ�!";
			}else if(rs==0){
				msg="ʡ�����ʧ��!";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("tongguo1".equals(command)){//ajaxִ�в���
			PrintWriter out = response.getWriter();
			//ʡ�����ͨ��
			String url = path+ "/web/appJSP/priceover/provinceauditing/provinceauditing.jsp",msg="";
			ProAuditingDao dao = new ProAuditingDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.tgUpdate(id, loginName);
		    if(rs==0){
		    	msg="ʡ�����ʧ��!";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }
		    
	        out.print(rs);
	        out.flush();
	        out.close();
		}
	}

}
