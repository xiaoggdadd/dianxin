package com.ptac.app.electricmanage.bargainbill.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ptac.app.electricmanage.bargainbill.bean.BargainBillSaveBean;
import com.ptac.app.electricmanage.bargainbill.dao.BargainBillMethodsDAO;
import com.ptac.app.electricmanage.bargainbill.dao.Imp.BargainBillMethodsDAOImp;
import com.ptac.app.electricmanage.bargainbill.tool.GetZQ;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanageUtil.Format;

/**
 * ���Ӻ�ͬ����Servlet
 * 
 * @author rock
 * 
 */
@SuppressWarnings("serial")
public class AddBargainServlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if("add".equals(action)){
			resp.setContentType(Content_type);
			String path = req.getContextPath();

			String url = path
					+ "/web//appJSP/electricmanage/bargainbill/addBargainBill.jsp", msg = "";
			HttpSession session = req.getSession();


			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
			String entrytime = df.format(new Date());

			/* ʵ����Javabean����װ */
			BargainBillSaveBean formBean = new BargainBillSaveBean();

			formBean.setStationid(req.getParameter("stationid"));
			formBean.setAccountmonth(req.getParameter("accountmonth"));
			formBean.setHtbianhao(req.getParameter("htbianhao"));
			String pjje = req.getParameter("pjje");
			double d = 0;
			d = Double.parseDouble(pjje);
			formBean.setPjje(d);
			formBean.setDanjia(req.getParameter("danjia"));
			formBean.setFeetypeid(req.getParameter("feetypeid"));// --meiyou
			formBean.setKptime(req.getParameter("kptime"));// --meiyou
			formBean.setMemo(req.getParameter("memo"));
			formBean.setMoney(req.getParameter("money"));// --meiyou
			formBean.setNotetime(req.getParameter("notetime"));
			formBean.setNotetypeid(req.getParameter("notetypeid"));
			formBean.setPrepayment_ammeterid(req.getParameter("ammeteridFk"));
			formBean.setStartmonth(req.getParameter("startmonth"));
			formBean.setEndmonth(req.getParameter("endmonth"));

			formBean.setEntrypensonnel(req.getParameter("accountname"));
			formBean.setEntrytime(entrytime);
			String start = req.getParameter("startmonth");
			String end = req.getParameter("endmonth");

			formBean.setThisdegree(req.getParameter("thisdegree"));
			formBean.setThisdatetime(req.getParameter("thisdatetime"));
			formBean.setLinelosstype(req.getParameter("linelosstype;"));
			formBean.setFloatpay(req.getParameter("floatpay;"));
			formBean.setInputoperator(req.getParameter("inputoperator;"));
			formBean.setPaydatetime(req.getParameter("paydatetime"));
			formBean.setPayoperator(req.getParameter("payoperator"));

			// ��ȡ��̯����Ľ�������Ӫ�ߵ��(����)����Ϣ��֧���ߵ�ѡ������ۺ��ߵ�ѣ��칫�����г�Ӫ���ߵ��(Ӫҵ)������Ͷ���ߵ�ѡ������ѣ�
			formBean.setNetworkdf(req.getParameter("networkdf"));
			formBean.setInformatizationdf(req.getParameter("informatizationdf"));
			formBean.setAdministrativedf(req.getParameter("administrativedf"));
			formBean.setMarketdf(req.getParameter("marketdf"));
			formBean.setBuilddf(req.getParameter("builddf"));
			formBean.setDddf(req.getParameter("dddf"));
			formBean.setQsdbdl(req.getParameter("qsdbdl"));
			formBean.setEdhdl(req.getParameter("edhdl"));
		

			/* �洢�����ж� */
			// ʵ����������
			ElectricTool et = new ElectricTool();
			StringBuffer text = new StringBuffer();//��ͨ��ԭ������

			String dl = req.getParameter("dl");

			/*����ʼ�·ݽ����·�ת��Ϊ����*/
			GetZQ gz = new GetZQ();
			String qsdate = gz.getBeginTime(req.getParameter("startmonth"));
			String jsdate = gz.getEndTime(req.getParameter("endmonth"));
			
			formBean.setStartdate(qsdate);
			formBean.setEnddate(jsdate);
			String beilv = req.getParameter("beilv");//����
			String shicode = req.getParameter("shicode");//��
	        String zlfh = req.getParameter("zlfh");//ֱ������
	        String jlfh = req.getParameter("jlfh");//��������
	        String property = req.getParameter("property");//վ�����Ա���
	        String scb = req.getParameter("scb");//������
	        String dfzflxcode = req.getParameter("dfzflxcode");//���֧�����ͱ���
	        String gdfscode =req.getParameter("gdfscode");//���緽ʽ����
	        String stationtypecode = req.getParameter("stationtypecode");//վ�����ͱ���
			 if(Format.str_d(beilv)==0){
	        	 beilv = "1";
	         }
	         String dbydl = dl;//����õ��� = (���ε�����-�ϴε�����)*����
			
	         if(et.checkRepeat2(formBean.getPrepayment_ammeterid(), formBean.getAccountmonth(),start, end)){
	        	 msg="������Ϣ�ظ������ʵ��Ϣ��";
	         }else{
	         
			// ��Ϊ�����жϣ�����Ĭ��ͨ��

			String manualauditname = "";// �˹������
			String manualauditstatus = "0";// �˹����״̬
			String manualauditdatetime = null;// �˹����ʱ��

			boolean ycgedf = false;// �쳣�߶���
			boolean sdb = false;// ����ʡ����
			boolean edhdl = false;// С�ڶ�ĵ���
			boolean zhandian = false;// 1.2�����

			String zq = req.getParameter("jszq");//��ȡ����
			String eding =  req.getParameter("edhdl");
			String quanSheng =  req.getParameter("qsdbdl");
			
			if("".equals(eding)||eding==null){
				eding="0";
			}
			if("".equals(quanSheng)||quanSheng==null){
				quanSheng="0";
			}
			String[] cbbl = et.getQsdbdlCbbl(dbydl, jsdate, qsdate, shicode, property, zlfh, jlfh, eding, scb, formBean.getPrepayment_ammeterid(),dl,quanSheng,stationtypecode);//2014-10-28��ʡ�����,�ϲ�����,��׼ֵ
			// �ж϶�ĵ�����ʡ����
			String ed[] = et.checkBcdl(dl, jsdate, qsdate,eding,formBean.getPrepayment_ammeterid(), "", "1");// ��ĵ���
			String qs[] = et.checkBcdl2(cbbl[0]);
			String cSheng = cbbl[0];
			String cCity = ed[3];
			if ("0".equals(ed[2])) {
				edhdl = false;
			} else if ("1".equals(ed[2])) {
				edhdl = true;
			}
			if ("0".equals(qs[0])) {
				sdb = false;
			} else if ("1".equals(qs[0])) {
				sdb = true;
			}
			// �ж��쳣���߶���
//			String cShengdb = req.getParameter("hiddcsb");
//			if("".equals(cShengdb)||cShengdb==null){
//				cShengdb="0";
//			}
//			String cShidb = req.getParameter("hiddcity");
			String eah[] = et.checkExceptAndHigh(formBean.getPrepayment_ammeterid(), formBean.getMoney(), dl, jsdate, qsdate, String.valueOf(Format.str_d(cbbl[0])/100));
			if ("1".equals(eah[0])) {
				ycgedf = false;
			} else if ("0".equals(eah[0])) {
				ycgedf = true;
			}

			String qxsh = "0";
			if (ycgedf == false) {
				qxsh = "0";
			} else {
				qxsh = "1";
			}

			String countyauditstatus = qxsh;// �����������״̬��־λ
			String countyauditname = "";// �������������
			String countyaudittime = "";// �����������ʱ��



			// �ж�1.2�����
			String onePointTwo[] = et.checckSite(formBean.getPrepayment_ammeterid());
			if ("1".equals(onePointTwo[0])) {
				zhandian = false;
			} else if ("0".equals(onePointTwo[0])) {
				zhandian = true;
			}

			String szrBzw = "0";
			if (ycgedf == false || zhandian == false) {
				szrBzw = "0";
			} else {
				szrBzw = "1";
			}

			String cityzrauditstatus = szrBzw;// ���������״̬��־λ
			String cityzrauditname = "";// �����������
			String cityzraudittime = "";// ���������ʱ��

			String cityAudit = "0";
			if(ycgedf==false||edhdl==false||sdb==false||zhandian==false){
				cityAudit="0";
			}else{
				cityAudit="1";
			}
			
			
			text.append(eah[1]);//��Ӳ�ͨ����Ϣ
			text.append(" "+ed[1]);
			text.append(" "+qs[1]);
			text.append(" "+onePointTwo[1]);
			
			/*�Զ����״̬������*/
			String autoauditstatus = cityAudit;// �Զ����״̬
			String autoauditdescripthion = text.toString();// �Զ��������
			
			formBean.setCITYAUDIT(cityAudit);
			
			
			formBean.setJszq(zq);
			
			formBean.setDianliang(dl);
			formBean.setAutoauditstatus(autoauditstatus);
			formBean.setAutoauditdescripthion(autoauditdescripthion);
			formBean.setManualauditstatus(manualauditstatus);
			formBean.setManualauditname(manualauditname);
			formBean.setManualauditdatetime(manualauditdatetime);
			formBean.setCountyauditname(countyauditname);
			formBean.setCountyauditstatus(countyauditstatus);
			formBean.setCountyaudittime(countyaudittime);
			formBean.setCityzrauditname(cityzrauditname);
			formBean.setCityzrauditstatus(cityzrauditstatus);
			formBean.setCityzraudittime(cityzraudittime);
			formBean.setCsdb(cSheng);
			formBean.setDedhdl(cCity);
			formBean.setFlag(cityAudit);
			formBean.setCountyflag(countyauditstatus);
			formBean.setCityflag(cityzrauditstatus);
			
	    	String hbzq = cbbl[1];//�ϲ�����
	    	String bzz = cbbl[2];//��׼ֵ
	    	formBean.setDbydl(dbydl);//����õ���
	    	formBean.setBeilv(beilv);//����
	    	formBean.setHbzq(hbzq);
	    	formBean.setBzz(bzz);
	    	formBean.setScb(scb);//������
	    	formBean.setZlfh(zlfh);//ֱ������
	    	formBean.setJlfh(jlfh);//��������
	    	formBean.setPropertycode(property);
	    	formBean.setStationtypecode(stationtypecode);
	    	formBean.setDfzflxcode(dfzflxcode);
	    	formBean.setGdfscode(gdfscode);

			// ��ʼ�洢
			BargainBillMethodsDAO dao = new BargainBillMethodsDAOImp();
			msg = dao.addBargainBill(formBean, start, end);
	         }
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
		}else if("getCsdb".equals(action)){
	    	 resp.setContentType("text/html; charset=UTF-8");
	         resp.setHeader("Cache-Control", "no-cache");
			 String zlfh = req.getParameter("zlfh");//ֱ������
	         String jlfh = req.getParameter("jlfh");//��������
	         String property = req.getParameter("property");//վ�����Ա���
	         String edhdl = req.getParameter("edhdl");//��ĵ���
	         String scb = req.getParameter("scb");//������
	         String dbid = req.getParameter("ammeteridFk");//���id
	         String shi = req.getParameter("shicode");//��
			 GetZQ gz = new GetZQ();
			 String qsdate = gz.getBeginTime(req.getParameter("startmonth"));
			 String jsdate = gz.getEndTime(req.getParameter("endmonth"));
	         String dbydl = req.getParameter("dl");//����õ��� /����
	         String qsdbdl = req.getParameter("qsdbdl");
	         String stationtype = req.getParameter("stationtype");
			 PrintWriter out = resp.getWriter();
			 ElectricTool elecToo = new ElectricTool();
			 String[] cbbl=elecToo.getQsdbdlCbbl(dbydl, jsdate, qsdate, shi, property, zlfh, jlfh, edhdl, scb, dbid,dbydl,qsdbdl,stationtype);
	         out.print(Format.str2(cbbl[0])+"%");
	         out.close();
		}

	

	}

}
