package com.ptac.app.electricmanage.bargainbill.action;

import java.io.IOException;
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
 * 合同单修改的Servlet
 * 
 * @author rock
 * 
 */
@SuppressWarnings("serial")
public class UpdateBargainBillServlet extends HttpServlet {
	
	private static final String Content_type = "text/html;charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		
		String url = path
		+ "/web//appJSP/electricmanage/bargainbill/mainBargainBill.jsp", msg = "";
		HttpSession session = req.getSession();
		String id = req.getParameter("id");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String entrytime = df.format(new Date());

		BargainBillSaveBean formBean = new BargainBillSaveBean();
		

		formBean.setPrepayment_ammeterid(req.getParameter("ammeteridFk"));
		formBean.setDanjia(req.getParameter("danjia"));
		formBean.setNotetypeid(req.getParameter("notetypeid"));
		formBean.setNotetime(req.getParameter("notetime"));
		formBean.setNoteno(req.getParameter("noteno"));
		formBean.setStationid(req.getParameter("zdid"));
		formBean.setAccountmonth(req.getParameter("accountmonth"));
		formBean.setMemo(req.getParameter("memo"));
		formBean.setKptime(req.getParameter("kptime"));
		formBean.setEndmonth(req.getParameter("endmonth"));
		formBean.setStartmonth(req.getParameter("startmonth"));
		formBean.setMemo(req.getParameter("memo"));
		formBean.setMoney(req.getParameter("money"));
		formBean.setHtbianhao(req.getParameter("htbianhao"));
		String pj = req.getParameter("pjje");
		if ("".equals(pj) || null == pj || "null".equals(pj)) {
			pj = "0";
		}
		formBean.setPjje(Double.parseDouble(pj));
		// formBean.setAmmeterdegreeid_fk(req.getParameter("ammeterdegreeidFk"));

		formBean.setEntrypensonnel(req.getParameter("accountname"));
		formBean.setEntrytime(entrytime);

		// 获取分摊后算的金额（网络运营线电费(生产)、信息化支撑线电费、行政综合线电费（办公）、市场营销线电费(营业)、建设投资线电费）
		formBean.setNetworkdf(req.getParameter("networkdf"));
		formBean.setInformatizationdf(req.getParameter("informatizationdf"));
		formBean.setAdministrativedf(req.getParameter("administrativedf"));
		formBean.setMarketdf(req.getParameter("marketdf"));
		formBean.setBuilddf(req.getParameter("builddf"));
		formBean.setDddf(req.getParameter("dddf"));

		String start = req.getParameter("startmonth");
		String end = req.getParameter("endmonth");
		
		/* 存储各级判断 */
		// 实例化工具类
		ElectricTool et = new ElectricTool();
		StringBuffer text = new StringBuffer();//不通过原因描述

		String dl = req.getParameter("dl");
		
		GetZQ gz = new GetZQ();
		String qsdate = gz.getBeginTime(req.getParameter("startmonth"));
		String jsdate = gz.getEndTime(req.getParameter("endmonth"));
		
		String beilv = req.getParameter("beilv");//倍率
		String shicode = req.getParameter("shicode");//市
        String zlfh = req.getParameter("zlfh");//直流负荷
        String jlfh = req.getParameter("jlfh");//交流负荷
        String property = req.getParameter("property");//站点属性编码
        String stationtypecode = req.getParameter("stationtypecode");
        String dfzflxcode = req.getParameter("dfzflxcode");
        String gdfscode = req.getParameter("gdfscode");
        String scb = req.getParameter("scb");//生产标
		 if(Format.str_d(beilv)==0){
        	 beilv = "1";
         }
         String dbydl = dl;//电表用电量 = (本次电表读数-上次电表读数)*倍率

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
		formBean.setEdhdl(eding);
		formBean.setQsdbdl(quanSheng);
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
		// 判断异常及高额电费
//		String cShengdb = req.getParameter("hiddcsb");
//		if("".equals(cShengdb)||cShengdb==null){
//			cShengdb="0";
//		}
//		String cShidb = req.getParameter("hiddcity");
		String eah[] = et.checkExceptAndHigh(formBean.getPrepayment_ammeterid(), formBean.getMoney(), dl, jsdate, qsdate, String.valueOf(Format.str_d(cbbl[0])/100));
		
		text.append(eah[1]);//添加不通过信息

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
		
		String cSheng = cbbl[0];
		String cCity = ed[3];
		
		text.append(" "+ed[1]);
		text.append(" "+qs[1]);
		if ("0".equals(ed[2])) {
			edhdl = false;
		} else if ("1".equals(ed[2])) {
			edhdl = true;
		}
		if ("0".equals(qs[1])) {
			sdb = false;
		} else if ("1".equals(qs[1])) {
			sdb = true;
		}

		// 判断1.2万个点
		String onePointTwo[] = et.checckSite(formBean.getPrepayment_ammeterid());
		text.append(" "+onePointTwo[1]);
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
		
		
		/*将起始月份结束月份转换为日期*/
		formBean.setStartdate(qsdate);
		formBean.setEnddate(jsdate);
		
		formBean.setCITYAUDIT(cityAudit);
		
		/*自动审核状态和描述*/
		String autoauditstatus = cityAudit;// 自动审核状态
		String autoauditdescripthion = text.toString();// 自动审核描述
		
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
    	formBean.setStationtypecode(stationtypecode);
    	formBean.setPropertycode(property);
    	formBean.setDfzflxcode(dfzflxcode);
    	formBean.setGdfscode(gdfscode);
		
		BargainBillMethodsDAO dao = new BargainBillMethodsDAOImp();
		
		msg = dao.updateBargainBills(formBean, id, start, end);
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		resp.sendRedirect(path + "/web/msg.jsp");

	}

}
