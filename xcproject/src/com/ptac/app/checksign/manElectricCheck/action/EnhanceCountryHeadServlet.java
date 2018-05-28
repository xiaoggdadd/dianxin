package com.ptac.app.checksign.manElectricCheck.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.ptac.app.checksign.manElectricCheck.bean.CountryHeadBean;
import com.ptac.app.checksign.manElectricCheck.dao.EnhanceCountryHeadDao;
import com.ptac.app.checksign.manElectricCheck.dao.EnhanceCountryHeadDaoImp;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.enhanceelectricitybill.bean.Configurations;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanageUtil.Format;

public class EnhanceCountryHeadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");//ǰ̨�����ı�ʶ

		if("chaxun".equals(action)||"enhancedaochu".equals(action)){	//���ݱ�ʶѡ�񷽷�
			getInfo(request,response);
		}else if("getMoreInfo".equals(action)){
			getMoreInfo(request,response);
		}else if("save".equals(action)){
			save(request,response);
		}else if("xiangxi".equals(action)){
			xiangxi(request,response);
		}
	}

	private void getInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");//ǰ̨�����ı�ʶ
		//------��ȡ��¼�˻���Ϣ------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();

		String shi = request.getParameter("shi");//��
		String xian = request.getParameter("xian");//����	
		String xiaoqu = request.getParameter("xiaoqu");//����	
		String jzname = request.getParameter("zdname");//վ������
		String stationtype = request.getParameter("zdlx1");//վ������	
		String property = request.getParameter("jzproperty");//վ������
		String accountmonth = request.getParameter("bztime");//�����·�
		String countryheadstatus = request.getParameter("countryheadstatus");//����������˱�־
		String cityaudit = request.getParameter("cityaudit");//����˱�־
		String qyzt = request.getParameter("qyzt");//վ������״̬
		String dbqyzt = request.getParameter("dbqyzt");//�������״̬
		String getlrbz=request.getParameter("getlrbz");//¼���־λ
		
		String whereStr = "";
		String whereStr1 = "";
		
		if (shi != null && !shi.equals("") && !shi.equals("0")){
			whereStr = whereStr + " AND ZD.SHI='" + shi + "'";
			whereStr1 = whereStr1 + " AND ZD.SHI='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")){
			whereStr = whereStr + " AND ZD.XIAN='" + xian + "'";
			whereStr1 = whereStr1 + " AND ZD.XIAN='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")){
			whereStr = whereStr + " AND ZD.XIAOQU='" + xiaoqu + "'";
			whereStr1 = whereStr1 + " AND ZD.XIAOQU='" + xiaoqu + "'";
		}
		if (jzname != null && !jzname.equals("")){
			whereStr = whereStr + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
			whereStr1 = whereStr1 + " AND ZD.JZNAME LIKE '%" + jzname + "%'";
		}
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0") && !("��ѡ��").equals(stationtype)){
			whereStr = whereStr + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
			whereStr1 = whereStr1 + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
		}
		if (property != null && !property.equals("") && !property.equals("0")&&!("��ѡ��").equals(property)) {
			whereStr = whereStr + " AND ZD.property = '" + property + "'";
			whereStr1 = whereStr1 + " AND ZD.property = '" + property + "'";
		}
		if (accountmonth != null && accountmonth != "" && !accountmonth.equals("0")){
			whereStr = whereStr + " AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'";
			whereStr1 = whereStr1 + " AND to_char(EF.ACCOUNTMONTH,'yyyy-mm') ='" + accountmonth + "'";
		}
		if (countryheadstatus != null && !countryheadstatus.equals("") && !countryheadstatus.equals("-1")){
			whereStr = whereStr + " AND EF.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
			whereStr1 = whereStr1 + " AND EF.COUNTYAUDITSTATUS='" + countryheadstatus + "'";
		}
		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-1") && !("��ѡ��").equals(cityaudit)){
			whereStr = whereStr + " AND EF.CITYAUDIT='" + cityaudit + "'";
			whereStr1 = whereStr1 + " AND EF.CITYAUDIT='" + cityaudit + "'";
		}
//		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-2") && !("��ͨ��").equals(cityaudit))
//			whereStr = whereStr + " AND EF.COUNTYFLAG='0'";    //���ڱ���Ʋ�����,�˴�Ų��ʵ�ֲ�
		
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")){
			whereStr = whereStr + " AND DB.DBQYZT='" + dbqyzt + "'";
			whereStr1 = whereStr1 + " AND DB.DBQYZT='" + dbqyzt + "'";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")){
			whereStr = whereStr + " AND ZD.QYZT='" + qyzt + "'";
			whereStr1 = whereStr1 + " AND ZD.QYZT='" + qyzt + "'";
		}
		if(!cityaudit.equals("-2") && !("��ͨ��").equals(cityaudit)){
			whereStr = whereStr + "  AND AM.COUNTYFLAG='0'";
			whereStr1 = whereStr1 + "  AND EF.COUNTYFLAG='0'";
		}
		//2014-8-13 ���getlrbz==null������sql����ѣ�Ԥ���ѣ���ִ�У����getlrbz==1,��sql1ִ�У�sql2��ִ�У�and 1=2�������getlrbz==2����sql2ִ�У�sql1��ִ�У�and 1=2��
		
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){
				whereStr1 = whereStr1 + "AND 1=2";
			}else if("2".equals(getlrbz)){
				whereStr = whereStr + "AND 1=2";
			}
		}
		
		//------��ý�����ͽ������------		
		EnhanceCountryHeadDao dao = new EnhanceCountryHeadDaoImp();
		List<CountryHeadBean> list = new ArrayList<CountryHeadBean>();	
		list = dao.queryInfo(whereStr, cityaudit, loginId,whereStr1);//�����
		
		int num = list.size();//�������
		
		request.setAttribute("num", num);//�������
		request.setAttribute("list", list);//�����

		//------����ǰ̨��ť��ʶ�ж��ύ����
		if("chaxun".equals(action)){//����������ѯҳ��
			request.getRequestDispatcher("/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp")
			.forward(request, response);
		}else if("enhancedaochu".equals(action)){//������������
			request.getRequestDispatcher("/web/appJSP/checksign/manElectricCheck/enhanceexport.jsp")
			.forward(request, response);
		}
		
	}
	
	private void getMoreInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String dbid = request.getParameter("dbid");
		String biaozhi = request.getParameter("biaozhi");
		String dfzflx = request.getParameter("dfzflx");
		String accountmonth = request.getParameter("accountmonth");
		
		request.setAttribute("dbid", dbid);
       	request.setAttribute("dfzflx", dfzflx);
       	request.setAttribute("dfbzw", biaozhi);
       	request.setAttribute("bzyf", accountmonth);
		
       	//��תҳ��
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp").forward(request, response);
	
	}


	/**
	 * @author lijing
	 * @see ������������˵���������Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {

		 DbLog log = new DbLog();
		 String msg="";
		 HttpSession session = request.getSession();
		 String path = request.getContextPath();
		 String accountid=request.getParameter("accountid");
		 CountryHeadBean bean = new CountryHeadBean();	
		 EnhanceCountryHeadDao dao = new EnhanceCountryHeadDaoImp();
		 String dbid = request.getParameter("dbid"); //���id
		 String dlid = request.getParameter("dlid"); //����id
		 String electricfee_id = request.getParameter("electricfee_id"); //���id
		 
		 bean.setDbid(dbid);
		 bean.setDlid(dlid);
		 bean.setElectricfee_id(electricfee_id);
		 
	     //------��ȡǰ̨������Ϣ------
		 String floatdegree = request.getParameter("floatdegree");//��������
		 String ammemo = request.getParameter("ammemo");//������ע
	     String bzydl = request.getParameter("bzydl");//�����õ���
	     String dbydl = request.getParameter("actualdegree");//����õ���
	     BigDecimal bzz = new BigDecimal(Format.str2(request.getParameter("bzz")));//��׼ֵ
	     
	     bean.setFloatdegree(floatdegree);
	     bean.setAmmemo(ammemo);
	     bean.setBlhdl(bzydl);
	     bean.setDbydl(dbydl);
	     
	     //------��ȡǰ̨�����Ϣ------
	     String floatpay = request.getParameter("floatpay");//��ѵ���
	     String efmemo = request.getParameter("efmemo");//��ѱ�ע
	     String yddf = request.getParameter("yddf");//�õ���
	   	 String bzdf = request.getParameter("bzdf");//���˵��
	   	 
	   	 String beilv = request.getParameter("beilv");//����
	   	 String linelossvalue = request.getParameter("linelossvalue");//����ֵ
	   	 String changevalue = request.getParameter("changevalue");//����ֵ
	   	 String actuallinelossvalue = request.getParameter("actuallinelossvalue");//ʵ������ֵ
	   	 String property = request.getParameter("property");
	   	 String zdlx = request.getParameter("stationtype");
	   	 
	   	 bean.setFloatpay(floatpay);
	   	 bean.setEfmemo(efmemo);
	   	 bean.setYddf(yddf);
	   	 bean.setActualpay(bzdf);
	   	 
	   	 bean.setBeilv(beilv);
	   	 bean.setLinelossvalue(linelossvalue);
	   	 bean.setChangevalue(changevalue);
	   	 bean.setActuallinelossvalue(actuallinelossvalue);
	   	 
	   	 //��ĵ�����ȫʡ�����������������,���ʺĵ���
	   	 String edhdl = request.getParameter("edhdl");
	   	 String qsdbdl = request.getParameter("qsdbdl");
	   	 String jszq = request.getParameter("jszq");
	   	 String blhdl = bean.getBlhdl();
	   	 String edcbbl1 ;//��ĵ����������
	   	 String csdbbl1;//ȫʡ��������������
	
	   	double bchdl = Double.parseDouble(blhdl);//���κĵ���
			if(Format.str_d(edhdl)==0){//��ĵ���Ϊ���򲻼���
				edcbbl1 = "";
			}else{
				double hdl = Double.parseDouble(edhdl) * Format.str_d(jszq);//���ض������
				double edcbbl = Format.d2(((bchdl - hdl)/hdl)*100);
				edcbbl1 = Double.toString(edcbbl);
			}
			
			if(Format.str_d(edhdl)==0){//��ĵ���Ϊ���򲻼���
				edcbbl1 = "";
			}else{
				double hdl = Double.parseDouble(edhdl) * Format.str_d(jszq);//���ض������
				double edcbbl = Format.d2(((bchdl - hdl)/hdl)*100);
				edcbbl1 = Double.toString(edcbbl);
			}
			
//			if(Format.str_d(qsdbdl)==0){//ȫʡ�������Ϊ���򲻼���
//				csdbbl1 = "";
//			}else{
//				double qsdbdll = Double.parseDouble(qsdbdl) * Format.str_d(jszq);
//				double csdbbl = Format.d2(((bchdl-qsdbdll)/qsdbdll)*100);
//				csdbbl1 = Double.toString(csdbbl);
//			}
			//2015-1-22
			if( ("zdsx06".equals(property) && !"StationType12".equals(zdlx))
					|| ("zdsx01".equals(property) && !"StationType20".equals(zdlx))
					|| "zdsx03".equals(property) ){//��������ԭ���������ʽ
				BigDecimal qsdl = new BigDecimal(qsdbdl).multiply(new BigDecimal(jszq));
				BigDecimal bl = (new BigDecimal(blhdl).subtract(qsdl)).divide(qsdl, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));//((blhdl - qsdbdl*jszq)/qsdbdl*jszq)*100
				csdbbl1 = Format.str2(bl.toString());
			}else{
				//������õ���-��׼ֵ��/��׼ֵ*100%
				BigDecimal csdbbla = (new BigDecimal(dbydl).subtract(bzz)).divide(bzz, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
				csdbbl1 = Format.str2(csdbbla.toString());
			}
			bean.setDedhdl(edcbbl1);
			bean.setCsdb(csdbbl1);
	     
	   	 Share share = new Share();//��̯��Ϣbean
	     //------��ȡǰ̨������̯��Ϣ------
	     share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
	     share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
	     share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
	     share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
	     share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
	     share.setDddl(Format.str_d(request.getParameter("dddfdl")));
	     
	     //------��ȡǰ̨��ѷ�̯��Ϣ------
	     share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
	     share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
	     share.setMarketdf(Format.str_d(request.getParameter("yydf")));
	     share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
	     share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
	     share.setDddf(Format.str_d(request.getParameter("dddfdf")));
	     
	     msg = dao.save(bean,share);
	     log.write(msg, accountid, request.getRemoteAddr(), "��˵���������Ϣ");
	   
	     String url = path + "/web/check/close.jsp";
	     session.setAttribute("url", url);
	     session.setAttribute("msg", msg);	//��ʾ��Ϣ:�Ƿ�ɹ�
	     response.sendRedirect(path + "/web/msg.jsp");
	}
	
	/**
	 * @author lijing
	 * @see ���������˵���������ϸ��Ϣ
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void xiangxi(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
			String dlid = request.getParameter("dlid");//����ID
			String electricfee_id = request.getParameter("electricfee_id");//���ID
			String sjtzbz = request.getParameter("sjtzbz");
			
			String whereStr = "";
		
			if (dlid != null && !dlid.equals("") && !dlid.equals("0")){
				whereStr = whereStr + " AND A.AMMETERDEGREEID='" + dlid + "'";
			}
			if (electricfee_id != null && !electricfee_id.equals("") && !electricfee_id.equals("0")){
				whereStr = whereStr + " AND E.ELECTRICFEE_ID='" + electricfee_id + "'";
			}

			EnhanceCountryHeadDao dao = new EnhanceCountryHeadDaoImp();
			CountryHeadBean bean = new CountryHeadBean();
			Share share = new Share();
			
			share = dao.ftInfo(dlid,electricfee_id);
			bean = dao.xiangxi(whereStr);
			//���ó���
			ElecBillDao dao1 = new ElecBillDaoImp();
			Map<String,String> map = dao1.getValue("3");
			String beilvexcept = map.get("BeilvExcept");
			Configurations con = new Configurations();
			con.setBeilvexcept(beilvexcept);
			
			request.setAttribute("share", share);
			request.setAttribute("bean", bean);
			request.setAttribute("configurations", con);
			request.setAttribute("sjtzbz", sjtzbz);
			
			request.getRequestDispatcher("/web/check/jnrgtzsq.jsp").forward(request, response);
	}
			

}
