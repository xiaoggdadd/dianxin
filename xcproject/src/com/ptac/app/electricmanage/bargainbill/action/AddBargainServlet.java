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
 * 增加合同单的Servlet
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


			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());

			/* 实例化Javabean并封装 */
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

			// 获取分摊后算的金额（网络运营线电费(生产)、信息化支撑线电费、行政综合线电费（办公）、市场营销线电费(营业)、建设投资线电费、代垫电费）
			formBean.setNetworkdf(req.getParameter("networkdf"));
			formBean.setInformatizationdf(req.getParameter("informatizationdf"));
			formBean.setAdministrativedf(req.getParameter("administrativedf"));
			formBean.setMarketdf(req.getParameter("marketdf"));
			formBean.setBuilddf(req.getParameter("builddf"));
			formBean.setDddf(req.getParameter("dddf"));
			formBean.setQsdbdl(req.getParameter("qsdbdl"));
			formBean.setEdhdl(req.getParameter("edhdl"));
		

			/* 存储各级判断 */
			// 实例化工具类
			ElectricTool et = new ElectricTool();
			StringBuffer text = new StringBuffer();//不通过原因描述

			String dl = req.getParameter("dl");

			/*将起始月份结束月份转换为日期*/
			GetZQ gz = new GetZQ();
			String qsdate = gz.getBeginTime(req.getParameter("startmonth"));
			String jsdate = gz.getEndTime(req.getParameter("endmonth"));
			
			formBean.setStartdate(qsdate);
			formBean.setEnddate(jsdate);
			String beilv = req.getParameter("beilv");//倍率
			String shicode = req.getParameter("shicode");//市
	        String zlfh = req.getParameter("zlfh");//直流负荷
	        String jlfh = req.getParameter("jlfh");//交流负荷
	        String property = req.getParameter("property");//站点属性编码
	        String scb = req.getParameter("scb");//生产标
	        String dfzflxcode = req.getParameter("dfzflxcode");//电费支付类型编码
	        String gdfscode =req.getParameter("gdfscode");//供电方式编码
	        String stationtypecode = req.getParameter("stationtypecode");//站点类型编码
			 if(Format.str_d(beilv)==0){
	        	 beilv = "1";
	         }
	         String dbydl = dl;//电表用电量 = (本次电表读数-上次电表读数)*倍率
			
	         if(et.checkRepeat2(formBean.getPrepayment_ammeterid(), formBean.getAccountmonth(),start, end)){
	        	 msg="输入信息重复，请核实信息！";
	         }else{
	         
			// 因为不做判断，所以默认通过

			String manualauditname = "";// 人工审核人
			String manualauditstatus = "0";// 人工审核状态
			String manualauditdatetime = null;// 人工审核时间

			boolean ycgedf = false;// 异常高额电费
			boolean sdb = false;// 大于省定标
			boolean edhdl = false;// 小于额定耗电量
			boolean zhandian = false;// 1.2万个点

			String zq = req.getParameter("jszq");//获取周期
			String eding =  req.getParameter("edhdl");
			String quanSheng =  req.getParameter("qsdbdl");
			
			if("".equals(eding)||eding==null){
				eding="0";
			}
			if("".equals(quanSheng)||quanSheng==null){
				quanSheng="0";
			}
			String[] cbbl = et.getQsdbdlCbbl(dbydl, jsdate, qsdate, shicode, property, zlfh, jlfh, eding, scb, formBean.getPrepayment_ammeterid(),dl,quanSheng,stationtypecode);//2014-10-28超省标比例,合并周期,标准值
			// 判断额定耗电量和省定标
			String ed[] = et.checkBcdl(dl, jsdate, qsdate,eding,formBean.getPrepayment_ammeterid(), "", "1");// 额定耗电量
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
			// 判断异常及高额电费
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

			String countyauditstatus = qxsh;// 区县主人审核状态标志位
			String countyauditname = "";// 区县主任审核人
			String countyaudittime = "";// 区县主任审核时间



			// 判断1.2万个点
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

			String cityzrauditstatus = szrBzw;// 市主任审核状态标志位
			String cityzrauditname = "";// 市主任审核人
			String cityzraudittime = "";// 市主任审核时间

			String cityAudit = "0";
			if(ycgedf==false||edhdl==false||sdb==false||zhandian==false){
				cityAudit="0";
			}else{
				cityAudit="1";
			}
			
			
			text.append(eah[1]);//添加不通过信息
			text.append(" "+ed[1]);
			text.append(" "+qs[1]);
			text.append(" "+onePointTwo[1]);
			
			/*自动审核状态和描述*/
			String autoauditstatus = cityAudit;// 自动审核状态
			String autoauditdescripthion = text.toString();// 自动审核描述
			
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
			
	    	String hbzq = cbbl[1];//合并周期
	    	String bzz = cbbl[2];//标准值
	    	formBean.setDbydl(dbydl);//电表用电量
	    	formBean.setBeilv(beilv);//倍率
	    	formBean.setHbzq(hbzq);
	    	formBean.setBzz(bzz);
	    	formBean.setScb(scb);//生产标
	    	formBean.setZlfh(zlfh);//直流负荷
	    	formBean.setJlfh(jlfh);//交流负荷
	    	formBean.setPropertycode(property);
	    	formBean.setStationtypecode(stationtypecode);
	    	formBean.setDfzflxcode(dfzflxcode);
	    	formBean.setGdfscode(gdfscode);

			// 开始存储
			BargainBillMethodsDAO dao = new BargainBillMethodsDAOImp();
			msg = dao.addBargainBill(formBean, start, end);
	         }
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			resp.sendRedirect(path + "/web/msg.jsp");
		}else if("getCsdb".equals(action)){
	    	 resp.setContentType("text/html; charset=UTF-8");
	         resp.setHeader("Cache-Control", "no-cache");
			 String zlfh = req.getParameter("zlfh");//直流负荷
	         String jlfh = req.getParameter("jlfh");//交流负荷
	         String property = req.getParameter("property");//站点属性编码
	         String edhdl = req.getParameter("edhdl");//额定耗电量
	         String scb = req.getParameter("scb");//生产标
	         String dbid = req.getParameter("ammeteridFk");//电表id
	         String shi = req.getParameter("shicode");//市
			 GetZQ gz = new GetZQ();
			 String qsdate = gz.getBeginTime(req.getParameter("startmonth"));
			 String jsdate = gz.getEndTime(req.getParameter("endmonth"));
	         String dbydl = req.getParameter("dl");//电表用电量 /电量
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
