package com.ptac.app.electricmanage.enhanceelectricitybill.action;
import java.io.IOException;
import java.io.PrintWriter;
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

import net.sf.json.JSONArray;

import com.noki.log.DbLog;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.BasicInfo;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.enhanceelectricitybill.bean.Configurations;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.enhanceelectricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author WangYiXiao
 * @see ��ǿ���ѵ���ȡǰ̨���������ؽ����
 */
public class ElecBillServlet extends HttpServlet {

	private static final long serialVersionUID = -5836639307794215591L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request,response);
		}
	
		@SuppressWarnings("unchecked")
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			String path = request.getContextPath();
			ElecBillDao dao = new ElecBillDaoImp();
			String action = request.getParameter("action")!=null?request.getParameter("action"):"";//��ȡҪ���Ĳ���
	        	        
			if ("zdName".equals(action)) {
				//��ѵ�վ������ģ����ѯ
				String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//��
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";//����	
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";//����
				String stationtype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"";//վ������	
				String jzname = (String)request.getParameter("dfmc")!=null?(String)request.getParameter("dfmc"):"";//վ������
				String str="";
				if(!shi.equals("")&& !shi.equals("0")){
					str=str+" and Z.shi='"+shi+"'";
				}
				if(!xian.equals("")&& !xian.equals("0")){
					str=str+" and Z.xian='"+xian+"'";
				}
				if(!xiaoqu.equals("")&& !xiaoqu.equals("0")){
					str=str+" and Z.xiaoqu='"+xiaoqu+"'";
				}
				if (!stationtype.equals("") && !stationtype.equals("0")) {
					if (!("��ѡ��").equals(stationtype)){
						str = str + " and Z.STATIONTYPE IN('" + stationtype + "')";
					}
				}

				ArrayList zdname = new ArrayList();
				zdname = dao.checkMh(loginId,jzname,str); 
				request.setAttribute("jzname",jzname);
				request.setAttribute("zdname",zdname);
				request.getRequestDispatcher("/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBillAdd.jsp").forward(request, response);
			
			} else if ("zdMessage".equals(action)) {

				//��ѵ���ѯҳ����ʾ��ֻ���ֶ�
				String stationname=request.getParameter("stationname");
				String dbid="",zdcode="";
		        if(stationname!=null){
		        	if(stationname.contains(",")){
		        		int aa=stationname.indexOf(",");
		        		 dbid=stationname.substring(0,aa);
		        		 zdcode=stationname.substring((aa+1));
		        	}
		        }
				AssistInfo ass = new AssistInfo();
				BasicInfo bas = new BasicInfo();
				ElectricityInfo elec = new ElectricityInfo();
				ElectricityInfo elec1 = new ElectricityInfo();
				ArrayList share1 = new ArrayList();
				ArrayList share2 = new ArrayList();
				
				ass = dao.ass(dbid);//������Ϣ��ѯ
				bas = dao.bas(dbid);//������Ϣ��ѯ
				elec = dao.elec(dbid);//�����Ϣ��ѯ
				elec1 = dao.elec1(zdcode);//��ȡ������ڵ�����
				share1 = dao.share1(dbid);//��̯רҵռ�Ȳ�ѯ
				share2 = dao.share2(dbid);//��̯רҵռ����ϸ��ѯ
				
				//���ó���
				Map<String,String> map = dao.getValue("3");
				String averagefees = map.get("AverageFees");
				String exceptadjust = map.get("ExceptAdjust");
				String exceptlinechangeloss = map.get("ExceptLineChangeLoss");
				String backadjust1 = map.get("BackAdjust1");
				String backadjust2 = map.get("BackAdjust2");
				String backadjust3 = map.get("BackAdjust3"); 
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
				con.setBackadjust3(backadjust3);
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
				
				String property = bas.getPropertycode();
				String zdname = bas.getJzname();
				String zdlx = bas.getStationtypecode();
				String gdfscode = bas.getGdfscode(); 
				ElectricTool elecToo = new ElectricTool();
				String str = elecToo.selectUnitprice(property, zdname, zdlx, gdfscode);
				//��ֹ����ȵ�ǰ�ĸõ��е�ƽ������ 
				//��Ϊ ��׼���� 2015-1-28
				String averageunitprice = dao.getAverageUnitPrice(dbid,str);
				
				//��ȡ���֧�����ͣ���ѯ���̺�
				String zflx = bas.getDfzflx();
				String liuchenghao = null;
				if("Ԥ֧".equals(zflx)){//Ԥ֧dfzflx03
					liuchenghao = dao.getLiuchenghao(dbid);
				}
				String htendtime = null;
				if("��ͬ".equals(zflx)){//��ͬ
					htendtime = dao.getHtEndTime(dbid);
				}
				boolean outnoconnct = false;
				if(dao.getOut(dbid)){//�����������δ����վ��ID�����
						outnoconnct = true;
					
				}
				
				//�ڹ�����������ȡ�ñ��ε������ͱ��γ���ʱ��
				elec.setThisdatetime(elec1.getThisdatetime()!= null ? elec1.getThisdatetime():"");
				elec.setThisdegree(elec1.getThisdegree()!= null ? elec1.getThisdegree():"0.00");
				
				request.setAttribute("ass",ass);
				request.setAttribute("bas",bas);
				request.setAttribute("elec",elec);
				request.setAttribute("elec1", elec1);
				request.setAttribute("share1",share1);
				request.setAttribute("share2",share2);
				request.setAttribute("dbid",dbid);
				request.setAttribute("liuchenghao",liuchenghao);
				request.setAttribute("htendtime",htendtime);
				request.setAttribute("outnoconnct", outnoconnct);
				request.setAttribute("configurations", con);
				request.setAttribute("averageunitprice", averageunitprice);
				request.getRequestDispatcher("/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBillMessage.jsp").forward(request, response);
			
			} else if ("addDf".equals(action)) {
				ElectricTool elecToo = new ElectricTool();
				String bl = request.getParameter("bl")!=null?Format.str2(request.getParameter("bl")):"";//��ĵ����������
				String qsbl = request.getParameter("qsbl")!=null?Format.str2(request.getParameter("qsbl")):"";//ȫʡ��������������
				String beilv = request.getParameter("mpower")!=null?Format.str2(request.getParameter("mpower")):"1";//����
				String xgbzw = request.getParameter("xgbzw");//�޸ĵ������ı�־λ
				//��ѵ�¼��������
				 String dbid = request.getParameter("dbid");  
				 String url = path + "/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBillAdd.jsp";
				 String msg = "";
				 DbLog log = new DbLog();
				 ElectricityInfo elecInfo = new ElectricityInfo();
				 Share share = new Share();
		      	 //��������
				 long uuid1 = System.currentTimeMillis();
				 String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
				 String uuid = uuid2+Long.toString(uuid1).substring(3);
		         System.out.println("��������:"+uuid);
		         String bzw="1";
		        //������Ϣ
		         String lastdegree = request.getParameter("lastdegree");
		         String thisdegree = request.getParameter("thisdegree");
		         String lastdatetime = request.getParameter("lastdatetime");
		         String thisdatetime = request.getParameter("thisdatetime");//���γ���ʱ��
		         String floatdegree = request.getParameter("floatdegree");//�õ�������
		         String scdf = request.getParameter("scdf");//�ϴε��
		         String scydl = request.getParameter("scydl");//�ϴ��õ���
		         String lastunitprice = request.getParameter("lastunitprice");//�ϴε���
		         String lastactualdegree = request.getParameter("lastactualdegree");//���ڵ���õ���
		         if(Format.str_d(beilv)==0){
		        	 beilv = "1";
		         }
		         String dbydl = request.getParameter("actualdegree");//����õ��� = (���ε�����-�ϴε�����)*����
		         elecInfo.setDbydl(dbydl);
		         elecInfo.setBeilv(beilv);
//		         String[] tbtzsqsz = request.getParameterValues("zfbdb");
//		         String tbtzsq = null;
//		         if(tbtzsqsz==null){
//		        	 tbtzsq="0";
//		         }else{
//		        	 tbtzsq = "1".equals(tbtzsqsz[0])?"1":"0";
//		         }  
		         String tbtzsq = "1";
		         elecInfo.setTbtzsq(tbtzsq);
		       
		         elecInfo.setLastdegree(lastdegree);
		         elecInfo.setThisdegree(thisdegree);
		         elecInfo.setLastdatetime(lastdatetime);
		         elecInfo.setThisdatetime(thisdatetime);
		         elecInfo.setFloatdegree("".equals(floatdegree)?"0":floatdegree);
		         elecInfo.setScdf(scdf);
		         elecInfo.setScdl(scydl);
		         elecInfo.setLastunitprice(lastunitprice);
		         String blhdl = request.getParameter("blhdl");
		         elecInfo.setBlhdl(blhdl);
		         elecInfo.setMemo(request.getParameter("memoam"));
		         elecInfo.setEntrypensonnel(request.getParameter("accountid"));
		         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		    	 String entrytime=df.format(new Date());
		         elecInfo.setEntrytime(entrytime);
		         String pjdl = request.getParameter("pjdl")==null?"0":request.getParameter("pjdl");
		         elecInfo.setPjdl(pjdl.trim());
		        //�����������̯
		         share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
		         share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
		         share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
		         share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
		         share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
		         share.setDddl(Format.str_d(request.getParameter("dddfdl")));//�������
		         
		         //���緽ʽ
		         String gdfs = request.getParameter("gdfs");
		         //���ε���
		         String thisunitprice = "";
		         //���ƽ
		         String jfzb = "''";//���ռ��
		         String bfzb = "''";//����ռ��
		         String bgzb = "''";//����ռ��
		         String bpzb = "''";//��ƽռ��
		         if("ֱ����".equals(gdfs) || "ֱ����1����λ�õ磩".equals(gdfs) || "ֱ����2�����õ磩".equals(gdfs)){
		        	 thisunitprice = request.getParameter("dyjunitprice"); 
		        	 jfzb = request.getParameter("jfzb");//���ռ�ȣ�δ��100
			         bfzb = request.getParameter("bfzb");//����ռ��
			         bgzb = request.getParameter("bgzb");//����ռ��
			         bpzb = request.getParameter("bpzb");//��ƽռ��    
		         }else {//if("ת����".equals(gdfs))
		        	 thisunitprice = request.getParameter("unitprice"); 
		         }
		         elecInfo.setUnitprice(thisunitprice);
		         elecInfo.setJfzb(jfzb);
		         elecInfo.setBfzb(bfzb);
		         elecInfo.setBgzb(bgzb);
		         elecInfo.setBpzb(bpzb);

		         //�����Ϣ      
		         String floatpay = request.getParameter("floatpay");//��ѵ���
		         elecInfo.setFloatpay("".equals(floatpay)?"0":floatpay);
		         elecInfo.setMemo1(request.getParameter("memo"));
		         String accountmonth = request.getParameter("accountmonth");
		         elecInfo.setAccountmonth(accountmonth);
		         String actualpay = request.getParameter("actualpay");
		         elecInfo.setActualpay(Format.str_d(actualpay));
		         elecInfo.setNotetypeid(request.getParameter("notetypeid"));
		         elecInfo.setInputoperator(request.getParameter("inputoperator"));
		         elecInfo.setPayoperator(request.getParameter("payoperator"));
		         String pj=request.getParameter("pjje");
		         if("".equals(pj)||null==pj||"null".equals(pj)){
		        	 pj="0.00";
		         }
		         elecInfo.setPjje(Double.parseDouble(pj));
		         elecInfo.setLiuchenghao(request.getParameter("liuchenghao"));
		         elecInfo.setKptime(request.getParameter("kptime") == null ? "" : request.getParameter("kptime"));//¼��ʱҳ��û������ֶ�
		         elecInfo.setYddf(request.getParameter("yddf"));
		         
		         //2014-10-23
		         String zlfh = request.getParameter("zlfh");//ֱ������
		         String jlfh = request.getParameter("jlfh");//��������
		         String property = request.getParameter("propertycode");//վ�����Ա���
		         String edhdl = request.getParameter("edhdl");//��ĵ���
		         String scb = request.getParameter("scb");//������
		         String qsdbdl = request.getParameter("qsdbdl");//ȫʡ�������
		         
		         //��ѵķ�̯
		         share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
		         share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
		         share.setMarketdf(Format.str_d(request.getParameter("yydf")));
		         share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
		         share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
		         share.setDddf(Format.str_d(request.getParameter("dddfdf")));//������
		         
		         //2014-7-17
		         String stationtypecode = request.getParameter("stationtypecode");
		         elecInfo.setStationtypecode(stationtypecode);
		         elecInfo.setPropertycode(request.getParameter("propertycode"));
		         elecInfo.setDfzflxcode(request.getParameter("jztypecode"));
		         elecInfo.setGdfscode(request.getParameter("gdfscode"));
		         elecInfo.setZlfh(request.getParameter("zlfh"));
		         elecInfo.setJlfh(request.getParameter("jlfh"));
		         elecInfo.setChangevalue(request.getParameter("changevalue"));
		         elecInfo.setLinelosstype(request.getParameter("linelosstype"));
		         elecInfo.setLinelossvalue(request.getParameter("linelossvalue1"));
		         elecInfo.setActuallinelossvalue(request.getParameter("linelossvalue"));
		         
		        String accountid=request.getParameter("accountid");
		        String[] shiandxian = elecToo.getShiAndXian(dbid);
		        if(elecToo.checkRepeat(lastdegree, lastdatetime, dbid, accountmonth)){
		        	 msg="������Ϣ�ظ������ʵ��Ϣ��";
		        }else{
		        	String dfbz = ""; //������״̬��־0��ͨ��  1ͨ��
		        	String dfms = "";//�����˲�ͨ����������Ϣ
		        	String dlbz = "";//�������״̬��־0��ͨ��  1ͨ��
		        	String dlms = "";//������˲�ͨ����������Ϣ
		        	String[] dl1 = elecToo.checkFloatDegree(elecInfo);//�õ�������˵���ж�
		        	String[] dl2 = elecToo.checkDayDegree(dbid, thisdatetime, lastdatetime, blhdl);//�ϴ��պĵ�����ֵ
		        	String[] dl3 = elecToo.checkBcdl(blhdl, thisdatetime, lastdatetime, null, dbid, bl, "1");//��ĵ�����ֵ
		        	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtypecode);//2014-10-22��ʡ�����,�ϲ�����,��׼ֵ
		        	String[] dl4 = elecToo.checkBcdl2(cbbl[0]);//��ʡ����10%
		        	String[] expecthigh = elecToo.checkExceptAndHigh(dbid, actualpay, blhdl, thisdatetime, lastdatetime, String.valueOf(Format.str_d(cbbl[0])/100));//�쳣���߶�
		        	String[] site = elecToo.checckSite(dbid);//�Ƿ�1.2�����
		        	String[] adjust1 = elecToo.adjustCheck1(floatpay, floatdegree);//��ѣ���������
		        	String[] adjust2 = elecToo.adjustCheck2(lastunitprice, thisunitprice);//���۵���
		        	String[] chayue = elecToo.chaYue(thisdatetime, accountmonth);//���γ���ʱ���Ӧ�·�-�����·�>=2
		        	String[] thiselecfee = elecToo.checkThisFees(actualpay,shiandxian[0],shiandxian[1]);//���ε�ѽ�������������ƽ����ѽ��
		        	String[] outprice = new String[]{"1",""};//Ĭ�� �����ж�Ϊͨ������û�н��и��жϣ�
		        	String[] adjustelec = elecToo.adjustElec(floatdegree,beilv);//�����5,6,7,8,9,10�·ݵ�����������800��Ҫ�ļ����  �������500
		        	String[] adjustfeeandelec1 = elecToo.adjustFeeAndElec1(floatpay, floatdegree);//�Ƿ�������������10���ҵ���������
		        	String[] adjustfeeandelec2 = elecToo.adjustFeeAndElec2(floatdegree, floatpay, thisunitprice);//���������������������ҵ�ѣ�����������������������*����-��ѵ�����/��ѵ���>1.1 
		        	if("zdsx04".equals(expecthigh[2])){//����Ϊ ���� �� �ж�
		        		outprice = elecToo.OutElecUnitPrice(thisunitprice, shiandxian[0], shiandxian[1]);
		        	}
		        		dlms = dlms + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1] + adjustfeeandelec1[1];
		        	//�˴���if()else{} �ж���ָ��������Զ���˲�ͨ���� ��ѵ��Զ����Ҳ��ͨ��������������ͨ����Ե�ѽ����� ��
		        	if("1".equals(dl1[0])&&"1".equals(dl2[0])&&"1".equals(dl3[0])&&"1".equals(dl4[0])&&"1".equals(outprice[0])&&"0".equals(adjustfeeandelec1[0])){
		        		dlbz="1";//�����Զ����ͨ��
		        		String[] df5 = elecToo.checkElectric1(elecInfo);//�õ�ѵ���˵���ж�
		        		String[] df6 = elecToo.checkElectric2(pj);//Ʊ�ݽ��Ϊ���ж�
		        			dfms = dfms + df5[1] + df6[1] + outprice[1] + adjustfeeandelec1[1];
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
		        		if("1".equals(site[0])){
		        			city = "0";
		        			cityzr = "0";
		        		}else if("0".equals(site[0])){
		        			if("0".equals(dl3[0]) || "0".equals(dl4[0])){ 
		        				city = "0";
		        				dfbz = "0";
		        				dlbz = "0";
		        			}
		        		}
		        	}
		        	dfms =dfms + adjust1[1] + adjust1[3] + adjust2[1] + chayue[1] + thiselecfee[1] + adjustelec[1] + adjustfeeandelec1[1] + adjustfeeandelec2[1] + expecthigh[1] + site[1];//������Ϣ���ӵ���ѱ���
		        	String jszq = dl3[4];
		        //---------------------------------------------------------
		        	String edhdl1 = dl3[5];//��ĵ���
		        	String qsdbdl1 = qsdbdl;//ȫʡ�������
		        	String edhdlcbbl = dl3[3];//��ĵ����������
		        	String qsdbdlcbbl = cbbl[0];//ȫʡ��������������
		        	String hbzq = cbbl[1];//�ϲ�����
		        	String bzz = cbbl[2];//��׼ֵ
		        	msg = dao.addDegreeFees(dbid,elecInfo,share,uuid,bzw,dfms,dfbz,dlms,dlbz,edhdlcbbl,qsdbdlcbbl,city,qxzr,cityzr,jszq,edhdl1,qsdbdl1,xgbzw,hbzq,bzz,scb);
			        log.write(msg, accountid, request.getRemoteAddr(), "�����������");
		        }
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");
			}else if("getCsdb".equals(action)){
		    	 response.setContentType("text/html; charset=UTF-8");
		         response.setHeader("Cache-Control", "no-cache");
				 String zlfh = request.getParameter("zlfh");//ֱ������
		         String jlfh = request.getParameter("jlfh");//��������
		         String property = request.getParameter("propertycode");//վ�����Ա���
		         String edhdl = request.getParameter("edhdl");//��ĵ���
		         String scb = request.getParameter("scb");//������
		         String dbid = request.getParameter("dbid");//���id
		         String shi = request.getParameter("shi");//��
		         String thisdatetime = request.getParameter("thisdatetime");//���γ���ʱ��
		         String lastdatetime = request.getParameter("lastdatetime");//�ϴγ���ʱ��
		         String dbydl = request.getParameter("dbydl");
		         String blhdl = request.getParameter("blhdl");
		         String qsdbdl = request.getParameter("qsdbdl");
		         String stationtype = request.getParameter("stationtype");
		         String bzdj = request.getParameter("bzdj");
		         String bzdf = request.getParameter("bzdf");
				 PrintWriter out = response.getWriter();
				 ElectricTool elecToo = new ElectricTool();
				 String[] cbbl=elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shi, property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);
				 String dfcbbl = elecToo.getOverFeesbl(cbbl[2], bzdj, bzdf);
				 List<String> list = new ArrayList<String>();
				 list.add(Format.str2(cbbl[0])+"%");
				 list.add(dfcbbl+"%");
				 JSONArray jsonArray = JSONArray.fromObject(list);
				 String jsonString = jsonArray.toString();
				 out.print(jsonString);
		         out.close();
//				 out.print(Format.str2(cbbl[0])+"%");
//		         out.close();
			}  
		}
}
