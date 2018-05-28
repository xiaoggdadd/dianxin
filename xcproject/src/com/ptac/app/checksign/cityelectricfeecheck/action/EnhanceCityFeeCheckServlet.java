package com.ptac.app.checksign.cityelectricfeecheck.action;
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
import com.ptac.app.checksign.cityelectricfeecheck.bean.CityElectricFeeCheck;
import com.ptac.app.checksign.cityelectricfeecheck.dao.EnhanceCityFeeCheckDao;
import com.ptac.app.checksign.cityelectricfeecheck.dao.EnhanceCityFeeCheckDaoImpl;


/**
 * @see �м�������.��ѯ�����������
 * @author WangYiXiao 2014-1-23
 */
public class EnhanceCityFeeCheckServlet extends HttpServlet {
	
	/**
	 * serialVersionUID���ã� ���л�ʱΪ�˱��ְ汾�ļ����ԣ����ڰ汾����ʱ�����л��Ա��ֶ����Ψһ��
	 */
	private static final long serialVersionUID = -5460538533259065862L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		//-----����command��ͨѡ��ͨ������------
		if("chaxun1".equals(command) || "enhancedaochu".equals(command)){//��ѯ������
			queryexport(request,response);
		}else if("xiangqing".equals(command)){//����
			detail(request,response);
		}else if("checkcity6".equals(command) || "checkcityno16".equals(command) 
				|| "checkcityno26".equals(command) || "checkcity16".equals(command) 
				|| "checkcityno17".equals(command) || "checkcityno6".equals(command)){//�������
			
			check1(request,response);
		}	
	}

	/**
	 * @param request
	 * @param response
	 * @see ��ѯ����
	 * @author WangYiXiao 2014-1-23
	 * @throws ServletException
	 * @throws IOException
	 */
	public void queryexport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//------dao------
		EnhanceCityFeeCheckDao dao = new EnhanceCityFeeCheckDaoImpl();
		//------��ȡ��¼�˻���Ϣ------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();
		//------��ȡǰ̨������ʶ------
		String command = request.getParameter("command");
		//------��ȡǰ̨��������------
		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����	
		String xiaoqu = request.getParameter("xiaoqu");//����	
		String zdname = request.getParameter("zdname");//վ������
		String gdfsa = request.getParameter("gdfsa");//���緽ʽ
		String jzproperty = request.getParameter("jzproperty");//վ������
		String cityaudit = request.getParameter("cityaudit");//�м����״̬
		String bztime = request.getParameter("bztime");//�����·�
		String lrrq = request.getParameter("lrrq");//¼������
		String citymanageaudit = request.getParameter("citymanageaudit");//�м��������״̬
		String getlrbz = request.getParameter("getlrbz");//¼���־
		//------���ݻ�ȡ��ֵƴ��sql��ѯ���-----
		StringBuffer whereStr = new StringBuffer();//��ѯ����
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			 whereStr.append(" AND ZD.SHI='" + shi + "'");
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr.append(" AND ZD.XIAN='" + xian + "'"); 
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr.append(" AND ZD.XIAOQU='" + xiaoqu + "'");
		}
		if (zdname != null && !zdname.equals("") && !zdname.equals("0")){
			whereStr.append(" AND ZD.JZNAME  LIKE '%" + zdname + "%'");
		}
		if (gdfsa != null && !gdfsa.equals("") && !gdfsa.equals("0")){
			whereStr.append(" AND ZD.GDFS  = '" + gdfsa + "'");
		}
		if (lrrq != null && !lrrq.equals("") && !lrrq.equals("null")){
			whereStr.append(" AND to_char(EF.ENTRYTIME,'yyyy-mm-dd')  LIKE '%" + lrrq + "%'");
		}
		if (bztime != null && bztime != "" && !bztime.equals("0")){
			whereStr.append(" AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='" + bztime + "'");
		}
		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-1")){
			whereStr.append(" AND EF.CITYAUDIT='" + cityaudit + "'");	
		}
		if (jzproperty != null && !jzproperty.equals("") && !jzproperty.equals("0")){
			whereStr.append(" AND ZD.PROPERTY='" + jzproperty + "'");
		}
		if(citymanageaudit != null && !citymanageaudit.equals("") && !citymanageaudit.equals("-1")){
			whereStr.append(" AND EF.CITYZRAUDITSTATUS='" + citymanageaudit + "'");
		}
		//2014-8-13 ���getlrbz==null������sql����ѣ�Ԥ���ѣ���ִ�У����getlrbz==1,��sql1ִ�У�sql2��ִ�У�and 1=2�������getlrbz==2����sql2ִ�У�sql1��ִ�У�and 1=2��
		String lrbz1="";
		String lrbz2="";
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){//ֻ�ӵ�ѱ��ѯ
				lrbz2="AND 1=2";
			}else if("2".equals(getlrbz)){//ֻ��Ԥ���ѱ��ѯ
				lrbz1="AND 1=2";
			}
		}
		//------��ѯ�����------
		List<CityElectricFeeCheck> list = dao.queryCityFeeCheck(whereStr.toString(), loginId,lrbz1,lrbz2);
		//------��ý�����ͽ������------
		int num = list.size();//�������
		//------��õ����ϼƺ͵�Ѻϼ�------
		String totalelectric = "0.00";
		String totalmoney = "0.00";
		if(list!=null){
			String[] total = dao.total(list);
			totalelectric = total[0];//�����ϼ�	
	 		totalmoney = total[1];//��Ѻϼ�	
		}
		//------��ǰ̨ҳ�洫ֵ------
		request.setAttribute("totalelectric", totalelectric);//�����ϼ�
		request.setAttribute("totalmoney", totalmoney);//��Ѻϼ�
		request.setAttribute("num", num);//�������
		request.setAttribute("list", list);//�����
		//------����ǰ̨��ť��ʶ�ж��ύ����
		if("chaxun1".equals(command)){//���������м�������
			request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp").forward(request, response);
		}else if("enhancedaochu".equals(command)){//���������м������˵���ҳ��
			request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/��ǿ���˹���Ѷ������.jsp").forward(request, response);
		}
	}
	/**
	 * @param request
	 * @param response
	 * @see ��ϸ��Ϣҳ��
	 * @return
	 * @author WangYiXiao 2014-1-24
	 * @throws ServletException
	 * @throws IOException
	 */
	public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����
		String statusi = request.getParameter("statusi");
		String dbid = request.getParameter("dbid"+statusi);
       	String dfzflx = request.getParameter("dfzflx"+statusi);
       	String dfbzw = request.getParameter("dfbzw"+statusi);
       	String accountmonth = request.getParameter("accountmonth"+statusi);
       	
       	request.setAttribute("dbid", dbid);
       	request.setAttribute("dfzflx", dfzflx);
       	request.setAttribute("dfbzw", dfbzw);
       	request.setAttribute("accountmonth", accountmonth);

       	//��תҳ��
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp?width=1200,height=500,status=yes,scrollbars=yes,resizable=yes,left=100,top=100").forward(request, response);
	}

	/**
	 * @author lijing
	 * @see �����м�������ͨ������ͨ����ȡ��ͨ������
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void check1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//------dao------
		EnhanceCityFeeCheckDao dao = new EnhanceCityFeeCheckDaoImpl();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String personnal=account.getAccountName();//�����Ա
		String path = request.getContextPath();//��·��
		PrintWriter out = response.getWriter();
	    DbLog log = new DbLog();//��־
	    String url = "",msg = "";//·������ʾ��Ϣ
		String command = request.getParameter("command");//��ȡҳ�����
		if("checkcity6".equals(command)){//ͨ��
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	        url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "1";//���ͨ��
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"1","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�����м�������"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcity16".equals(command)){//ͨ��,ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1"); //���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "1";//���ͨ��
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"1","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�����м�������"); 
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno16".equals(command)){//��ͨ��ajax����
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2");//Ԥ����uuid 
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	  	  	String cityaudit = "-2";//��˲�ͨ��
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�����м�������");
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno26".equals(command)){//��ͨ������
	    	String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	String cause=request.getParameter("cause") != null ? request.getParameter("cause") : "";
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "-2";//��˲�ͨ��
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0",cause);
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�����м�������"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");
		}else if("checkcityno17".equals(command)){//ȡ��ͨ����ajax����
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	  	  	String cityaudit = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�����м�������");
	        String m="";
	        if(msg=="��˵����Ϣʧ�ܣ�"){
	        	m="0";
	            session.setAttribute("url", url);
	            session.setAttribute("msg", msg);
	        }else{
	        	m="1";
	        }
	        out.print(m);
	        out.close();
		}else if("checkcityno6".equals(command)){//ȡ��ͨ��
			String chooseIdStr1 = request.getParameter("chooseIdStr1");//���uuid
	    	String chooseIdStr2 = request.getParameter("chooseIdStr2"); //Ԥ����uuid
	    	url = path + "/web/appJSP/checksign/cityelectricfeecheck/jncityfeecheck.jsp" ;
	      	String cityaudit = "0";//ȡ��ͨ��
	        msg = dao.CheckCityFees1(personnal,cityaudit,chooseIdStr1,chooseIdStr2,"0","");
	        log.write(msg, account.getName(), request.getRemoteAddr(), "�����м�������"); 
	        session.setAttribute("url", url);
	        session.setAttribute("msg", msg);
	        response.sendRedirect(path + "/web/msg.jsp");

		}
	}
	
}
