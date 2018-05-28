package com.ptac.app.provinceapply.gdfs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.provinceapply.gdfs.bean.GdfBean;
import com.ptac.app.provinceapply.gdfs.dao.GdfDao;
import com.ptac.app.provinceapply.gdfs.dao.GdfDaoImp;

/**
 * @author lijing
 * @see ʡ���빩�緽ʽ���
 */
public class GdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		//-----����command��ͨѡ��ͨ������------
		if("chaxun".equals(command)){//��ѯ
			query(request,response);
		}else if("tongguo1".equals(command) || "tongguo2".equals(command) 
				|| "butongguo1".equals(command) || "butongguo2".equals(command) 
				|| "chehui1".equals(command) || "chehui2".equals(command)){//���
			check(request,response);
		}
	}


	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����
		String sqnr = request.getParameter("sqnr");//��������
		String shengzt = request.getParameter("shengzt");//���״̬
		String zdname = request.getParameter("zdname");//վ������
		String zdid = request.getParameter("zdid");//վ��ID
		String property = request.getParameter("zdsx1");//վ������
		String zdlx = request.getParameter("zdlx1");//վ������
		
		StringBuffer whereStr = new StringBuffer();//��ѯ����
		if (zdid != null && !zdid.equals("")){
			whereStr.append(" AND Z.ID = '" + zdid + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" AND Z.JZNAME LIKE '%" + zdname + "%'");
		}
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" AND Z.SHI='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			 whereStr.append(" AND Z.XIAN='" + xian + "'");
		}
		if("0".equals(sqnr)){
			if (sqnr != null && !sqnr.equals("") && !sqnr.equals("-1")){
				whereStr.append(" AND (Q.OLDGDFS = 'gdfs06' or Q.OLDGDFS = 'gdfs07') AND (Q.NEWGDFS = 'gdfs05' or Q.NEWGDFS = 'gdfs08')"); 
			}
		}else if("1".equals(sqnr)){
			if (sqnr != null && !sqnr.equals("") && !sqnr.equals("-1")){
				whereStr.append(" AND (Q.OLDGDFS = 'gdfs05' or Q.OLDGDFS = 'gdfs08') AND (Q.NEWGDFS = 'gdfs06' or Q.NEWGDFS = 'gdfs07')"); 
			}
		}
		if("0".equals(shengzt)){
			whereStr.append(" AND (Q.BFTG NOT LIKE '%5%' OR Q.BFTG IS NULL) AND (Q.BFBTG NOT LIKE '%5%' OR Q.BFBTG IS NULL)");	
		}else if("1".equals(shengzt)){
			whereStr.append(" AND Q.BFTG LIKE '%5%'");	
		}
		if (property != null && !property.equals("") && !property.equals("0")&&!property.equals("��ѡ��")){
			whereStr.append(" AND Q.NEWPROPERTY in(" + property + ")");
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")&&!zdlx.equals("��ѡ��")){
			whereStr.append(" AND Q.NEWSTATIONTYPE in(" + zdlx + ")");
		}
		
		GdfDao dao = new GdfDaoImp();
		List<GdfBean> list = dao.query(whereStr.toString(),loginId,shengzt);
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/web/zdqxkz/gdfsshenhe.jsp").forward(request, response);
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		GdfDao dao = new GdfDaoImp();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//�����Ա
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();//��־
	    String url = "",msg = "";//·������ʾ��Ϣ
		String command = request.getParameter("command");
		if("tongguo1".equals(command)){//ͨ��
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "ʡ���빩�緽ʽ���"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("tongguo2".equals(command)){
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","1");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "ʡ���빩�緽ʽ���"); 
	        String m="";
	        if(msg=="���緽ʽ�����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("butongguo2".equals(command)){//��˲�ͨ��
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
	        msg = dao.check(list,personnal,cause,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "ʡ���빩�緽ʽ���");
	        String m="";
	        if(msg=="���緽ʽ�����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("butongguo1".equals(command)){//��ͨ������
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        String cause=request.getParameter("cause")==null?"":request.getParameter("cause");
	        msg = dao.check(list,personnal,cause,"2");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "ʡ���빩�緽ʽ���"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("chehui2".equals(command)){//ȡ��ͨ��
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "ʡ���빩�緽ʽ���");
	        String m="";
	        if(msg=="���緽ʽ�����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("chehui1".equals(command)){//ȡ��ͨ��
			String id=request.getParameter("chooseIdStr");
			String zdid=request.getParameter("chooseIdStr1");
			List<GdfBean> list = dao.queryCheck(id);
	        url = path + "/web/zdqxkz/qyztshenhe.jsp" ;
	        msg = dao.check(list,personnal,"","0");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "ʡ���빩�緽ʽ���"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}
	}
}
