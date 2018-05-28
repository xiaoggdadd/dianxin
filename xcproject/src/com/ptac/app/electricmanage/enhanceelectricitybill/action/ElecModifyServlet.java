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
 * @see 增强版电费单修改.Servlet
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
		String action = request.getParameter("action");//前台传来的标识
		if("getInfo".equals(action)){	//根据标识选择方法
			getInfo(request,response);
		}else if("modify".equals(action)){
			modify(request,response);
		}
	}
	
	/**
	 * @see 查询信息并呈现到页面
	 * @author ZengJin 2014-2-15
	 */
 	@SuppressWarnings("unchecked")
	public void getInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	 	//------获得基本信息结果集------
		String degreeid = request.getParameter("degreeid");//电费id
		ElecModifyDao dao = new ElecModifyDaoImp();
		List list = new ArrayList();	
		list = dao.getElectricFeesInfo(degreeid);
		
		//------将基本信息归类赋予两个bean------
		ElectricityInfo elec = new ElectricityInfo();
		ElectricityInfo elec1 = new ElectricityInfo();
		AssistInfo ass = new AssistInfo();
		elec = (ElectricityInfo) list.get(0);
		Share share = new Share();
		share = (Share) list.get(1);
		
		//------获得附加信息结果集------
		ArrayList list2 = new ArrayList();
		list2 = dao.getOtherInfo(elec.getDbid());
		elec1 = dao.elec1(elec.getDbid());//获取最近日期电表读数
		ass = dao.lastInfo(elec.getDbid());//上期电量，上期电费，上期单价
		
		//配置常量
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
		//截止本年度当前的该地市的平均单价
		String averageunitprice = dao1.getAverageUnitPrice(elec.getDbid(),str);; 
		
		//------向前台页面传值------
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
	 * @see 获取页面信息并保存
	 * @author WangYiXiao 2014-4-15
	 */
	public void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ElectricTool elecToo = new ElectricTool();
		 ElectricityInfo elec = new ElectricityInfo();//电量电费bean
		 ElectricityInfo elecInfo = new ElectricityInfo();//调整说明用到的类
		 DbLog log = new DbLog();
		 HttpSession session = request.getSession();
		 String path = request.getContextPath();
		 String accountid=request.getParameter("accountid");
		 ElecModifyDao dao = new ElecModifyDaoImp();
		     
	     //------获取前台电量信息------
	     String dbid = request.getParameter("ammeteridFk"); //电表id
	     String lastdegree = request.getParameter("lastdegree"); //上次读数
	     String thisdegree = request.getParameter("thisdegree"); //本次读数
	     String lastdatetime = request.getParameter("lastdatetime"); //上次抄表时间
	     String thisdatetime = request.getParameter("thisdatetime"); //本次抄表时间
	     String beilv = request.getParameter("beilv"); //倍率
	   
	     String blhdl = request.getParameter("blhdl"); //
	     String floatpay = (!request.getParameter("floatpay").equals("")?request.getParameter("floatpay"):"0");//电费调整
		 String memo1 = request.getParameter("memo");//(电费)备注
		 String floatdegree =  (!request.getParameter("floatdegree").equals("")?request.getParameter("floatdegree"):"0");//用电量调整
		 String memo = request.getParameter("memoam");//(电量)备注
		 String accountmonth = request.getParameter("accountmonth");//
		 elecInfo.setFloatpay(floatpay);
		 elecInfo.setMemo1(memo1);
		 elecInfo.setFloatdegree(floatdegree);
		 elecInfo.setMemo(memo);

         String dbydl = request.getParameter("actualdegree");//电表用电量 = (本次电表读数-上次电表读数)*倍率
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
	     
	     
	     elec.setDbid(dbid);//获取前台电表id
	     elec.setAmmeterdegree_id(request.getParameter("ammeterdegree_id"));
	     elec.setLastdegree(lastdegree);
	     elec.setThisdegree(thisdegree);
	     elec.setLastdatetime(lastdatetime);
	     elec.setThisdatetime(thisdatetime); 
	     elec.setFloatdegree(!floatdegree.equals("")?floatdegree:"0");
	     elec.setDbydl(request.getParameter("actualdegree"));//电表用电量
	     elec.setBlhdl(blhdl);
	     elec.setMemo(memo);
	     elec.setEntrypensonnel(request.getParameter("enterpersonnel"));
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	     elec.setEntrytime(df.format(new Date()));//将系统时间赋予"录入时间"
	     
	     //------获取前台电费信息------
	     elec.setElectricfee_id(request.getParameter("electricfee_id"));
	     String unitprice = request.getParameter("unitprice");
	   	 elec.setUnitprice(unitprice);
	   	 String actualpay = request.getParameter("actualpay");//实际电费金额
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
         String zlfh = request.getParameter("zlfh");//直流负荷
         String jlfh = request.getParameter("jlfh");//交流负荷
         String property = request.getParameter("propertycode");//站点属性编码
         String edhdl = request.getParameter("edhdl");//额定耗电量
         String scb = request.getParameter("scb");//生产标
         String qsdbdl = request.getParameter("qsdbdl");//全省定标电量
         String stationtype = request.getParameter("stationtypecode");
         String dfzflx = request.getParameter("dfzflxcode");
         String gdfs = request.getParameter("gdfscode");
         elec.setScb(scb);
         elec.setBeilv(beilv);
         elec.setPropertycode(property);
         elec.setStationtypecode(stationtype);
         elec.setDfzflxcode(dfzflx);
         elec.setGdfscode(gdfs);
         
	     Share share = new Share();//分摊信息bean
	     //------获取前台电量分摊信息------
	     share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
	     share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
	     share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
	     share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
	     share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
	     share.setDddl(Format.str_d(request.getParameter("dddfdl")));//代垫电量
	     
	     //------获取前台电费分摊信息------
	     share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
	     share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
	     share.setMarketdf(Format.str_d(request.getParameter("yydf")));
	     share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
	     share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
	     share.setDddf(Format.str_d(request.getParameter("dddfdf")));//代垫电费
	     
	    String edhdlbz=request.getParameter("kongbai3");
	    String qsdbdlbz=request.getParameter("kongbai2");
		ElecBillDao dao2 = new ElecBillDaoImp();
		String lastunitprice = dao2.getLast(dbid);//上次单价
	    String msg="";
	    String[] shiandxian = elecToo.getShiAndXian(dbid);
	       // if(elecToo.checkRepeat(thisdegree, thisdatetime, lastdegree, lastdatetime, dbid, accountmonth)){
	        	// msg="输入信息重复，请核实信息！";
	       // }else{
	        	//-----------------------------------------------------
		     	String dfbz = ""; //电费审核状态标志0不通过  1通过
		    	String dfms = "";//电费审核不通过的描述信息
		    	String dlbz = "";//电量审核状态标志0不通过  1通过
		    	String dlms = "";//电量审核不通过的描述信息
	        	String[] dl1 = elecToo.checkFloatDegree(elecInfo);//用电量调整说明判断
	        	String[] dl2 = elecToo.checkDayDegree(dbid, thisdatetime, lastdatetime, blhdl);//上次日耗电量比值
	        	String[] dl3 = elecToo.checkBcdl(blhdl, thisdatetime, lastdatetime, null, dbid, edhdlbz, "1");//额定耗电量比值
	        	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);//2014-10-23超省标比例,合并周期,标准值
	        	String[] dl4 = elecToo.checkBcdl2(cbbl[0]);//省定标超标10%
	        	String[] expecthigh = elecToo.checkExceptAndHigh(dbid, actualpay, blhdl, thisdatetime, lastdatetime, String.valueOf(Format.str_d(cbbl[0])/100));//异常及高额
	        	String[] site = elecToo.checckSite(dbid);//是否1.2万个点
	        	String[] adjust1 = elecToo.adjustCheck1(floatpay, floatdegree);//电费，电量调整
	        	String[] adjust2 = elecToo.adjustCheck2(lastunitprice, unitprice);//单价调整
	        	String[] chayue = elecToo.chaYue(thisdatetime , accountmonth);//本次抄表时间对应月份-报账月份>=2
	        	String[] thiselecfee = elecToo.checkThisFees(actualpay,shiandxian[0],shiandxian[1]);//本次电费金额大于区县上月平均电费金额
	        	String[] outprice = new String[]{"1",""};//默认 外借电判断为通过（即没有进行该判断）
	        	String[] adjustelec = elecToo.adjustElec(floatdegree,beilv);//如果是5,6,7,8,9,10月份电量调整大于800需要四级审核  否则大于500
	        	String[] adjustfeeandelec1 = elecToo.adjustFeeAndElec1(floatpay, floatdegree);//是否电费正调整大于10并且电量负调整
	        	String[] adjustfeeandelec2 = elecToo.adjustFeeAndElec2(floatdegree, floatpay, unitprice);//电量（正）负调整，并且电费（正）负调整：（电量调整*单价-电费调整）/电费调整>1.1 
	        	if("zdsx04".equals(expecthigh[2])){//属性为 其他 则 判断
	        		outprice = elecToo.OutElecUnitPrice(unitprice, shiandxian[0], shiandxian[1]);
	        	}
	        		dlms = dlms + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1] + adjustfeeandelec1[1];
	        	//此处的if()else{} 判断是指如果电量自动审核不通过则 电费的自动审核也不通过，如果电量审核通过则对电费进行判 断
	        	if("1".equals(dl1[0])&&"1".equals(dl2[0])&&"1".equals(dl3[0])&&"1".equals(dl4[0])&&"1".equals(outprice[0])&&"0".equals(adjustfeeandelec1[0])){
	        		dlbz="1";//电量自动审核通过
	        		String[] df5 = elecToo.checkElectric1(elecInfo);//用电费调整说明判断
	        		String[] df6 = elecToo.checkElectric2(pj);//票据金额为空判断
	        			dfms= dfms + df5[1] + df6[1]  + outprice[1] + adjustfeeandelec1[1];
	        		if("1".equals(df5[0])&&"1".equals(df6[0])&&"1".equals(outprice[0])&&"0".equals(adjustfeeandelec1[0])){
	        			dfbz = "1";//电费自动审核通过
	        		}else{
	        			dfbz = "0";//电费自动审核不通过
	        		}
	        	}else{
	        		dlbz="0";//电量自动审核不通过
	        		dfbz="0";//电费自动审核不通过
	        	}
	        	
	        	String qxzr = "1";//区县主任审核状态 0：需要审核,1:不需要审核；审核标志    0：未审核，1：审核通过，2审核不通过
	        	String city = "1";//市级审核状态；审核标志
	        	String cityzr = "1";//市级主任审核状态；审核标志		        
	        	
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
	        	String edhdl1 = dl3[5];//额定耗电量
	        	String qsdbdl1 = qsdbdl;//全省定标电量
	        	String edhdlcbbl = dl3[3];//额定耗电量超标比例
	        	String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
	        	String hbzq = cbbl[1];//合并周期
	        	String bzz = cbbl[2];//标准值
	        	String  jszq = dl3[4];//结算周期
	        	dfms = dfms + adjust1[1] + adjust1[3] + adjust2[1] + chayue[1] + thiselecfee[1] + adjustelec[1] + adjustfeeandelec1[1] + adjustfeeandelec2[1] + expecthigh[1] + site[1];//描述信息增加到电费表中
	        	msg = dao.modifyElectricFees(elec, share,dfms,dfbz,dlms,dlbz,city,edhdlcbbl,qsdbdlcbbl,qxzr,cityzr,jszq,edhdl1,qsdbdl1,hbzq,bzz);
	     	     log.write(msg, accountid, request.getRemoteAddr(), "修改电费单");//写日志
	     	     
	        
	   
	     String url = path + "/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBill.jsp";
	     session.setAttribute("url", url);
	     session.setAttribute("msg", msg);	//提示信息:是否成功
	     response.sendRedirect(path+"/web/msg.jsp");
	}
}
