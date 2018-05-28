package com.ptac.app.checkstandard.countyverify.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.mobi.common.Account;
import com.ptac.app.checkstandard.countyverify.bean.CountyVerifyBean;
import com.ptac.app.checkstandard.countyverify.dao.CountyVerifyDao;
import com.ptac.app.checkstandard.countyverify.dao.CountyVerifyDaoImp;

/**
 * @author lijing
 * @see ����ǩ�ռ���ʵ��Ϣ���Ʋ�
 */
public class CountyVerifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		Account account =(Account)session.getAttribute("account");
		String loginName = account.getAccountName();
		String loginId = account.getAccountId();
		String command = request.getParameter("command");
		
		if("chaxun".equals(command)){
			//����ǩ�ռ���ʵ��Ϣ��ѯ
			String shi = request.getParameter("shi");//��
			String xian = request.getParameter("xian");//����	
			String property = request.getParameter("property");//վ������
			String state = request.getParameter("state");//״̬
			String zdmc = request.getParameter("zdmc");//վ������
			String zdid = request.getParameter("zdid");//վ��ID	
			String beginbl = request.getParameter("beginbl");//����
			String endbl = request.getParameter("endbl");//����
			
			StringBuffer whereStr = new StringBuffer();//��ѯ����
			if (shi != null && !shi.equals("") && !shi.equals("0")){
				 whereStr.append(" and z.shi='" + shi + "'");
			}
			if (xian != null && !xian.equals("") && !xian.equals("0")){
				whereStr.append(" and z.xian='" + xian + "'"); 
			}
			if (property != null && !property.equals("") && !property.equals("0")){
				whereStr.append(" and z.PROPERTY='" + property + "'");
			}
			if (state != null && !state.equals("") && !state.equals("-1")){
				whereStr.append(" and C.COUNTYSIGN='" + state + "'");
			}
			if (zdmc != null && !zdmc.equals("") && !zdmc.equals("0")){
				whereStr.append(" and z.jzname  like '%" + zdmc + "%'");
			}
			if (zdid != null && !zdid.equals("")){
				whereStr.append(" and z.id='" + zdid + "'");	
			}
			
			CountyVerifyDao dao = new CountyVerifyDaoImp();
			List<CountyVerifyBean> list = dao.queryCountyVerify(whereStr.toString(), loginId,beginbl,endbl);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/web/appJSP/checkstandard/countyverify/countysign.jsp").forward(request, response);
		}else if("tuidan".equals(command)){
			//�����˵�
			String url = path+ "/web/appJSP/checkstandard/countyverify/countysign.jsp",msg="";
			CountyVerifyDao dao = new CountyVerifyDaoImp();
			String id = request.getParameter("chooseIdStr");
			int rs = dao.TDUpdate(id, loginName);
			if(rs==1){
				msg="�˵��ɹ�";
			}else if(rs==0){
				msg="�˵�ʧ��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path+"/web/msg.jsp");
		}else if("verify".equals(command)){
			//���غ�ʵ
			String zdid = request.getParameter("zdid");
			CountyVerifyDao dao = new CountyVerifyDaoImp();
			List<CountyVerifyBean> list = dao.getInfo(zdid);
			int num = list.size();
			request.setAttribute("num", num);
			request.setAttribute("list",list);
			request.getRequestDispatcher("/web/appJSP/checkstandard/countyverify/countyverify.jsp").forward(request, response);
		}else if("commit".equals(command)){
			CountyVerifyDao dao = new CountyVerifyDaoImp();
			CountyVerifyBean bean = new CountyVerifyBean();
			String pid = request.getParameter("pid").trim();
			String zlfhold = request.getParameter("zlfhold").trim();//ֱ������
			String jlfhold = request.getParameter("jlfhold").trim();//��������
			String edhdlold = request.getParameter("edhdlold").trim();//���ر�
			String scbold = request.getParameter("scbold").trim();//������
			String zlfhnew = request.getParameter("zlfhnew").trim();//��ʵ��ֱ��
			String jlfhnew = request.getParameter("jlfhnew").trim();//��ʵ����
			String edhdlnew = request.getParameter("edhdlnew").trim();//��ʵ�󱾵ر�
			String scbnew = request.getParameter("scbnew").trim();//��ʵ��������
			
			bean.setZlfhold(zlfhold);
			bean.setJlfhold(jlfhold);
			bean.setEdhdlold(edhdlold);
			bean.setScbold(scbold);
			bean.setZlfhnew(zlfhnew);
			bean.setJlfhnew(jlfhnew);
			bean.setEdhdlnew(edhdlnew);
			bean.setScbnew(scbnew);
			bean.setId(pid);
			
			String msg = dao.commit(bean,loginName);
		}
	}
}
