package com.ptac.app.electricmanage.enhanceelectricitybill.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.log.DbLog;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.enhanceelectricitybill.bean.Configurations;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecModifyDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecModifyDaoImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;

import com.ptac.app.electricmanageUtil.Format;
/**
 * @see ��ǿ���ѵ��޸�.Servlet
 * @author WangYiXiao 2014-4-15
 */
public class ElecModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 8703411724438983959L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");//ǰ̨�����ı�ʶ
		if("getInfo".equals(action)){	//���ݱ�ʶѡ�񷽷�
			getInfo(request,response);
		}else if("modify".equals(action)){
			modify(request,response);
		}
	}
	
	/**
	 * @see ��ѯ��Ϣ�����ֵ�ҳ��
	 * @author ZengJin 2014-2-15
	 */
 	@SuppressWarnings("unchecked")
	public void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	 	//------��û�����Ϣ�����------
		String degreeid = request.getParameter("degreeid");//���id
		ElecModifyDao dao = new ElecModifyDaoImp();
		List list = new ArrayList();	
		list = dao.getElectricFeesInfo(degreeid);
		
		//------��������Ϣ���ำ������bean------
		ElectricityInfo elec = new ElectricityInfo();
		ElectricityInfo elec1 = new ElectricityInfo();
		AssistInfo ass = new AssistInfo();
		elec = (ElectricityInfo) list.get(0);
		Share share = new Share();
		share = (Share) list.get(1);
		
		//------��ø�����Ϣ�����------
		ArrayList list2 = new ArrayList();
		list2 = dao.getOtherInfo(elec.getDbid());
		elec1 = dao.elec1(elec.getDbid());//��ȡ������ڵ�����
		ass = dao.lastInfo(elec.getDbid());//���ڵ��������ڵ�ѣ����ڵ���
		
		//���ó���
		ElecBillDao dao1 = new ElecBillDaoImp();
		Map<String,String> map = dao1.getValue("3");
		String averagefees = map.get("AverageFees");
		String exceptadjust = map.get("ExceptAdjust");
		String exceptlinechangeloss = map.get("ExceptLineChangeLoss");
		String backadjust1 = map.get("BackAdjust1");
		String backadjust2 = map.get("BackAdjust2");
		String sightvirtualheight1 = map.get("SightVirtualHeight1");
		String sightvirtualheight2 = map.get("SightVirtualHeight2");
		String escapeaudit1 = map.get("EscapeAudit1");
		String escapeaudit2 = map.get("EscapeAudit2");
		String beilvexcept = map.get("BeilvExcept");
		String feesheight = map.get("FeesHeight");
		String gapoversize1 = map.get("GapOversize1");
		String gapoversize2 = map.get("GapOversize2");
		String feesadjustexcept = map.get("FeesAdjustExcept");
		String elecoverproof = map.get("ElecOverProof");
		String unitpriceexcept = map.get("UnitPriceExcept");
		String elefeesbl = map.get("EleFeesBl");
		
		Configurations con = new Configurations();
		con.setAveragefees(averagefees);
		con.setExceptadjust(exceptadjust);
		con.setExceptlinechangeloss(exceptlinechangeloss);
		con.setBackadjust1(backadjust1);
		con.setBackadjust2(backadjust2);
		con.setSightvirtualheight1(sightvirtualheight1);
		con.setSightvirtualheight2(sightvirtualheight2);
		con.setEscapeaudit1(escapeaudit1);
		con.setEscapeaudit2(escapeaudit2);
		con.setBeilvexcept(beilvexcept);
		con.setFeesheight(feesheight);
		con.setGapoversize1(gapoversize1);
		con.setGapoversize2(gapoversize2);
		con.setFeesadjustexcept(feesadjustexcept);
		con.setElecoverproof(elecoverproof);
		con.setUnitpriceexcept(unitpriceexcept);
		con.setElefeesbl(elefeesbl);
		
		String property = elec.getPropertycode();
		String zdname = elec.getZdname();
		String zdlx = elec.getStationtypecode();
		String gdfscode = elec.getGdfscode(); 
		ElectricTool elecToo = new ElectricTool();
		String str = elecToo.selectUnitprice(property, zdname, zdlx, gdfscode);
		//��ֹ����ȵ�ǰ�ĸõ��е�ƽ������
		String averageunitprice = dao1.getAverageUnitPrice(elec.getDbid(),str);; 
		
		//------��ǰ̨ҳ�洫ֵ------
    	request.setAttribute("degreeid", degreeid);
    	request.setAttribute("elec", elec);
    	request.setAttribute("elec1", elec1);
    	request.setAttribute("ass", ass);
    	request.setAttribute("share", share);
    	request.setAttribute("list2", list2);
    	request.setAttribute("configurations", con);
		request.setAttribute("averageunitprice", averageunitprice);
    	request.getRequestDispatcher("/web/appJSP/electricmanage/enhanceelectricitybill/eleBillModify.jsp").forward(request, response);

	}

 	/**
	 * @see ��ȡҳ����Ϣ������
	 * @author WangYiXiao 2014-4-15
	 */
	public void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ElectricTool elecToo = new ElectricTool();
		 ElectricityInfo elec = new ElectricityInfo();//�������bean
		 ElectricityInfo elecInfo = new ElectricityInfo();//����˵���õ�����
		 DbLog log = new DbLog();
		 HttpSession session = request.getSession();
		 String path = request.getContextPath();
		 String accountid=request.getParameter("accountid");
		 ElecModifyDao dao = new ElecModifyDaoImp();
		     
	     //------��ȡǰ̨������Ϣ------
	     String dbid = request.getParameter("ammeteridFk"); //���id
	     String lastdegree = request.getParameter("lastdegree"); //�ϴζ���
	     String thisdegree = request.getParameter("thisdegree"); //���ζ���
	     String lastdatetime = request.getParameter("lastdatetime"); //�ϴγ���ʱ��
	     String thisdatetime = request.getParameter("thisdatetime"); //���γ���ʱ��
	     String beilv = request.getParameter("beilv"); //����
	   
	     String blhdl = request.getParameter("blhdl"); //
	     String floatpay = (!request.getParameter("floatpay").equals("")?request.getParameter("floatpay"):"0");//��ѵ���
		 String memo1 = request.getParameter("memo");//(���)��ע
		 String floatdegree =  (!request.getParameter("floatdegree").equals("")?request.getParameter("floatdegree"):"0");//�õ�������
		 String memo = request.getParameter("memoam");//(����)��ע
		 String accountmonth = request.getParameter("accountmonth");//
		 elecInfo.setFloatpay(floatpay);
		 elecInfo.setMemo1(memo1);
		 elecInfo.setFloatdegree(floatdegree);
		 elecInfo.setMemo(memo);

         String dbydl = request.getParameter("actualdegree");//����õ��� = (���ε�����-�ϴε�����)*����
	     //2014-4-19
		// String[] tbtzsqsz = request.getParameterValues("zfbdb");
         String tbtzsq = "1";
//         if(tbtzsqsz==null){
//        	 tbtzsq="0";
//         }else{
//        	 tbtzsq = "1".equals(tbtzsqsz[0])?"1":"0";
//         }  
         elec.setTbtzsq(tbtzsq);
         String pjdl = request.getParameter("pjdl")==null?"0":request.getParameter("pjdl");
         elec.setPjdl(pjdl.trim());
         elec.setInputoperator(request.getParameter("inputoperator"));
	     
	     
	     elec.setDbid(dbid);//��ȡǰ̨���id
	     elec.setAmmeterdegree_id(request.getParameter("ammeterdegree_id"));
	     elec.setLastdegree(lastdegree);
	     elec.setThisdegree(thisdegree);
	     elec.setLastdatetime(lastdatetime);
	     elec.setThisdatetime(thisdatetime); 
	     elec.setFloatdegree(!floatdegree.equals("")?floatdegree:"0");
	     elec.setDbydl(request.getParameter("actualdegree"));//����õ���
	     elec.setBlhdl(blhdl);
	     elec.setMemo(memo);
	     elec.setEntrypensonnel(request.getParameter("enterpersonnel"));
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	     elec.setEntrytime(df.format(new Date()));//��ϵͳʱ�丳��"¼��ʱ��"
	     
	     //------��ȡǰ̨�����Ϣ------
	     elec.setElectricfee_id(request.getParameter("electricfee_id"));
	     String unitprice = request.getParameter("unitprice");
	   	 elec.setUnitprice(unitprice);
	   	 String actualpay = request.getParameter("actualpay");//ʵ�ʵ�ѽ��
	     elec.setActualpay(Format.str_d(actualpay));
	     elec.setFloatpay(!floatpay.equals("")?floatpay:"0");
	     elec.setNotetypeid(request.getParameter("notetypeid"));
	     elec.setAccountmonth(accountmonth);
	     elec.setPayoperator(request.getParameter("payoperator"));
	     elec.setMemo1(memo1);
	     String pj=request.getParameter("pjje");
	     if("".equals(pj)||null==pj||"null".equals(pj))pj="0.00";
	     elec.setPjje(Double.parseDouble(pj));
	     elec.setLiuchenghao(request.getParameter("liuchenghao"));
	     //2014-4-19
	     elec.setYddf(request.getParameter("yddf"));
	     
         //2014-10-22
         String zlfh = request.getParameter("zlfh");//ֱ������
         String jlfh = request.getParameter("jlfh");//��������
         String property = request.getParameter("propertycode");//վ�����Ա���
         String edhdl = request.getParameter("edhdl");//��ĵ���
         String scb = request.getParameter("scb");//������
         String qsdbdl = request.getParameter("qsdbdl");//ȫʡ�������
         String stationtype = request.getParameter("stationtypecode");
         String dfzflx = request.getParameter("dfzflxcode");
         String gdfs = request.getParameter("gdfscode");
         elec.setScb(scb);
         elec.setBeilv(beilv);
         elec.setPropertycode(property);
         elec.setStationtypecode(stationtype);
         elec.setDfzflxcode(dfzflx);
         elec.setGdfscode(gdfs);
         
	     Share share = new Share();//��̯��Ϣbean
	     //------��ȡǰ̨������̯��Ϣ------
	     share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
	     share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
	     share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
	     share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
	     share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
	     share.setDddl(Format.str_d(request.getParameter("dddfdl")));//�������
	     
	     //------��ȡǰ̨��ѷ�̯��Ϣ------
	     share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
	     share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
	     share.setMarketdf(Format.str_d(request.getParameter("yydf")));
	     share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
	     share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
	     share.setDddf(Format.str_d(request.getParameter("dddfdf")));//������
	     
	    String edhdlbz=request.getParameter("kongbai3");
	    String qsdbdlbz=request.getParameter("kongbai2");
		ElecBillDao dao2 = new ElecBillDaoImp();
		String lastunitprice = dao2.getLast(dbid);//�ϴε���
	    String msg="";
	    String[] shiandxian = elecToo.getShiAndXian(dbid);
	       // if(elecToo.checkRepeat(thisdegree, thisdatetime, lastdegree, lastdatetime, dbid, accountmonth)){
	        	// msg="������Ϣ�ظ������ʵ��Ϣ��";
	       // }else{
	        	//-----------------------------------------------------
		     	String dfbz = ""; //������״̬��־0��ͨ��  1ͨ��
		    	String dfms = "";//�����˲�ͨ����������Ϣ
		    	String dlbz = "";//�������״̬��־0��ͨ��  1ͨ��
		    	String dlms = "";//������˲�ͨ����������Ϣ
	        	String[] dl1 = elecToo.checkFloatDegree(elecInfo);//�õ�������˵���ж�
	        	String[] dl2 = elecToo.checkDayDegree(dbid, thisdatetime, lastdatetime, blhdl);//�ϴ��պĵ�����ֵ
	        	String[] dl3 = elecToo.checkBcdl(blhdl, thisdatetime, lastdatetime, null, dbid, edhdlbz, "1");//��ĵ�����ֵ
	        	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);//2014-10-23��ʡ�����,�ϲ�����,��׼ֵ
	        	String[] dl4 = elecToo.checkBcdl2(cbbl[0]);//ʡ���곬��10%
	        	String[] expecthigh = elecToo.checkExceptAndHigh(dbid, actualpay, blhdl, thisdatetime, lastdatetime, String.valueOf(Format.str_d(cbbl[0])/100));//�쳣���߶�
	        	String[] site = elecToo.checckSite(dbid);//�Ƿ�1.2�����
	        	String[] adjust1 = elecToo.adjustCheck1(floatpay, floatdegree);//��ѣ���������
	        	String[] adjust2 = elecToo.adjustCheck2(lastunitprice, unitprice);//���۵���
	        	String[] chayue = elecToo.chaYue(thisdatetime , accountmonth);//���γ���ʱ���Ӧ�·�-�����·�>=2
	        	String[] thiselecfee = elecToo.checkThisFees(actualpay,shiandxian[0],shiandxian[1]);//���ε�ѽ�������������ƽ����ѽ��
	        	String[] outprice = new String[]{"1",""};//Ĭ�� �����ж�Ϊͨ������û�н��и��жϣ�
	        	String[] adjustelec = elecToo.adjustElec(floatdegree,beilv);//�����5,6,7,8,9,10�·ݵ�����������800��Ҫ�ļ����  �������500
	        	String[] adjustfeeandelec1 = elecToo.adjustFeeAndElec1(floatpay, floatdegree);//�Ƿ�������������10���ҵ���������
	        	String[] adjustfeeandelec2 = elecToo.adjustFeeAndElec2(floatdegree, floatpay, unitprice);//���������������������ҵ�ѣ�����������������������*����-��ѵ�����/��ѵ���>1.1 
	        	if("zdsx04".equals(expecthigh[2])){//����Ϊ ���� �� �ж�
	        		outprice = elecToo.OutElecUnitPrice(unitprice, shiandxian[0], shiandxian[1]);
	        	}
	        		dlms = dlms + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1] + adjustfeeandelec1[1];
	        	//�˴���if()else{} �ж���ָ��������Զ���˲�ͨ���� ��ѵ��Զ����Ҳ��ͨ��������������ͨ����Ե�ѽ����� ��
	        	if("1".equals(dl1[0])&&"1".equals(dl2[0])&&"1".equals(dl3[0])&&"1".equals(dl4[0])&&"1".equals(outprice[0])&&"0".equals(adjustfeeandelec1[0])){
	        		dlbz="1";//�����Զ����ͨ��
	        		String[] df5 = elecToo.checkElectric1(elecInfo);//�õ�ѵ���˵���ж�
	        		String[] df6 = elecToo.checkElectric2(pj);//Ʊ�ݽ��Ϊ���ж�
	        			dfms= dfms + df5[1] + df6[1]  + outprice[1] + adjustfeeandelec1[1];
	        		if("1".equals(df5[0])&&"1".equals(df6[0])&&"1".equals(outprice[0])&&"0".equals(adjustfeeandelec1[0])){
	        			dfbz = "1";//����Զ����ͨ��
	        		}else{
	        			dfbz = "0";//����Զ���˲�ͨ��
	        		}
	        	}else{
	        		dlbz="0";//�����Զ���˲�ͨ��
	        		dfbz="0";//����Զ���˲�ͨ��
	        	}
	        	
	        	String qxzr = "1";//�����������״̬ 0����Ҫ���,1:����Ҫ��ˣ���˱�־    0��δ��ˣ�1�����ͨ����2��˲�ͨ��
	        	String city = "1";//�м����״̬����˱�־
	        	String cityzr = "1";//�м��������״̬����˱�־		        
	        	
	        	if("1".equals(adjust1[2]) || "1".equals(adjust2[0]) || "1".equals(chayue[0]) || "1".equals(thiselecfee[0])
	        			|| "1".equals(adjustelec[0]) || "1".equals(adjustfeeandelec1[0]) || "1".equals(adjustfeeandelec2[0])){
	        		qxzr = "0";
	        		city = "0";
	        		cityzr = "0";
	        	}else if("1".equals(adjust1[0])){
	        		qxzr = "0";
	        		city = "0";
	        	}
	        	if("1".equals(expecthigh[0])){
	        		qxzr = "0";
	        		city = "0";
	        		cityzr = "0";
	        	}else if("0".equals(expecthigh[0])){
	        		//qxzr = "1";
	        		if("1".equals(site[0])){
	        			city = "0";
	        			cityzr = "0";
	        		}else if("0".equals(site[0])){
	        			//cityzr = "1";
	        			if("0".equals(dl3[0]) || "0".equals(dl4[0])){
	        				city = "0";
	        				dfbz = "0";
	        				dlbz = "0";
	        			}
	        		}
	        	}
	        	String edhdl1 = dl3[5];//��ĵ���
	        	String qsdbdl1 = qsdbdl;//ȫʡ�������
	        	String edhdlcbbl = dl3[3];//��ĵ����������
	        	String qsdbdlcbbl = cbbl[0];//ȫʡ��������������
	        	String hbzq = cbbl[1];//�ϲ�����
	        	String bzz = cbbl[2];//��׼ֵ
	        	String  jszq = dl3[4];//��������
	        	dfms = dfms + adjust1[1] + adjust1[3] + adjust2[1] + chayue[1] + thiselecfee[1] + adjustelec[1] + adjustfeeandelec1[1] + adjustfeeandelec2[1] + expecthigh[1] + site[1];//������Ϣ���ӵ���ѱ���
	        	msg = dao.modifyElectricFees(elec, share,dfms,dfbz,dlms,dlbz,city,edhdlcbbl,qsdbdlcbbl,qxzr,cityzr,jszq,edhdl1,qsdbdl1,hbzq,bzz);
	     	     log.write(msg, accountid, request.getRemoteAddr(), "�޸ĵ�ѵ�");//д��־
	     	     
	        
	   
	     String url = path + "/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBill.jsp";
	     session.setAttribute("url", url);
	     session.setAttribute("msg", msg);	//��ʾ��Ϣ:�Ƿ�ɹ�
	     response.sendRedirect(path+"/web/msg.jsp");
	}
}
