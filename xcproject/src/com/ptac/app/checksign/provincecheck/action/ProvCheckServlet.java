package com.ptac.app.checksign.provincecheck.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.provincecheck.bean.ProvCheckBean;
import com.ptac.app.checksign.provincecheck.dao.ProvCheckDao;
import com.ptac.app.checksign.provincecheck.dao.ProvCheckDaoImp;

/**
 * @author lijing
 * @see ʡ������վ�����
 */
public class ProvCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		//-----����command��ͨѡ��ͨ������------
		if("chaxun".equals(command)){//��ѯ������
			queryexport(request,response);
		}else if("shenhe".equals(command)){//���
			check(request,response);
		}	
	}

	/**
	 * @see ��ѯ
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����	
		String xiaoqu = request.getParameter("xiaoqu");//����	
		String zdname = request.getParameter("zdname");//վ������
		String zdid = request.getParameter("zdid");//վ��ID
		String property = request.getParameter("property");//վ������
		String zdlx = request.getParameter("zdlx");//վ������ 
		
		StringBuffer whereStr = new StringBuffer();//��ѯ����
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" and z.shi='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" and z.xian='" + xian + "'"); 
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr.append(" and z.xiaoqu='" + xiaoqu + "'");
		}
		if (zdid.length() > 0 && zdid != null){
			whereStr.append(" and z.id = '" + zdid + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" and z.jzname  like '%" + zdname + "%'");
		}
		if (property != null && !property.equals("") && !property.equals("0")){
			whereStr.append(" and z.PROPERTY='" + property + "'");
		}
		if (zdlx != null && !zdlx.equals("") && !zdlx.equals("0")) {
			whereStr.append(" and z.STATIONTYPE='" + zdlx + "'");
		}
		
		ProvCheckDao dao = new ProvCheckDaoImp();
		List<ProvCheckBean> list = dao.queryProvince(whereStr,loginId);
		
		int num = list.size();
		request.setAttribute("num", num);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/web/appJSP/checksign/provincecheck/provincecheck.jsp").forward(request, response);
		
	}
	
	/**
	 * @see ʡ������վ�����
	 */
	public void check(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		String accountid = (String) session.getAttribute("loginName");
		Account account = (Account) session.getAttribute("account");
		String personnal = account.getAccountName();//�����Ա
		String path = request.getContextPath();
	    DbLog log = new DbLog();//��־
	    String url = "",msg = "";//·������ʾ��Ϣ
		msg = "ʡ������վ�����ʧ�ܣ�";
		url = path + "/web/appJSP/checksign/provincecheck/provincecheck.jsp";
		String[] ids = request.getParameterValues("itemSelected");
		String shsign = request.getParameter("shsign");
		ProvCheckDao dao = new ProvCheckDaoImp();
		int retsign = dao.checkProvFees(ids, shsign,personnal);
		if (retsign == 1) {
			msg = "ʡ������վ����˳ɹ���";
		}
		log.write(msg, accountid, request.getRemoteAddr(), "ʡ������վ�����");
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
	}
}
