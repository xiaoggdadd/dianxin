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
		String action = request.getParameter("action");//前台传来的标识

		if("chaxun".equals(action)||"enhancedaochu".equals(action)){	//根据标识选择方法
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
		
		String action = request.getParameter("action");//前台传来的标识
		//------获取登录账户信息------
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginId = account.getAccountId();

		String shi = request.getParameter("shi");//市
		String xian = request.getParameter("xian");//区县	
		String xiaoqu = request.getParameter("xiaoqu");//乡镇	
		String jzname = request.getParameter("zdname");//站点名称
		String stationtype = request.getParameter("zdlx1");//站点类型	
		String property = request.getParameter("jzproperty");//站点属性
		String accountmonth = request.getParameter("bztime");//报账月份
		String countryheadstatus = request.getParameter("countryheadstatus");//区县主任审核标志
		String cityaudit = request.getParameter("cityaudit");//市审核标志
		String qyzt = request.getParameter("qyzt");//站点启用状态
		String dbqyzt = request.getParameter("dbqyzt");//电表启用状态
		String getlrbz=request.getParameter("getlrbz");//录入标志位
		
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
		if (stationtype != null && !stationtype.equals("") && !stationtype.equals("0") && !("请选择").equals(stationtype)){
			whereStr = whereStr + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
			whereStr1 = whereStr1 + " AND ZD.STATIONTYPE IN(" + stationtype + ")";
		}
		if (property != null && !property.equals("") && !property.equals("0")&&!("请选择").equals(property)) {
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
		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-1") && !("请选择").equals(cityaudit)){
			whereStr = whereStr + " AND EF.CITYAUDIT='" + cityaudit + "'";
			whereStr1 = whereStr1 + " AND EF.CITYAUDIT='" + cityaudit + "'";
		}
//		if (cityaudit != null && !cityaudit.equals("") && !cityaudit.equals("-2") && !("不通过").equals(cityaudit))
//			whereStr = whereStr + " AND EF.COUNTYFLAG='0'";    //由于表设计不合理,此处挪至实现层
		
		if (dbqyzt != null && !dbqyzt.equals("") && !dbqyzt.equals("-1")){
			whereStr = whereStr + " AND DB.DBQYZT='" + dbqyzt + "'";
			whereStr1 = whereStr1 + " AND DB.DBQYZT='" + dbqyzt + "'";
		}
		if (qyzt != null && !qyzt.equals("") && !qyzt.equals("-1")){
			whereStr = whereStr + " AND ZD.QYZT='" + qyzt + "'";
			whereStr1 = whereStr1 + " AND ZD.QYZT='" + qyzt + "'";
		}
		if(!cityaudit.equals("-2") && !("不通过").equals(cityaudit)){
			whereStr = whereStr + "  AND AM.COUNTYFLAG='0'";
			whereStr1 = whereStr1 + "  AND EF.COUNTYFLAG='0'";
		}
		//2014-8-13 如果getlrbz==null则两条sql（电费，预付费）均执行；如果getlrbz==1,则sql1执行，sql2不执行（and 1=2），如果getlrbz==2，则sql2执行，sql1不执行（and 1=2）
		
		if(getlrbz != null && !getlrbz.equals("")&& !getlrbz.equals("0")){
			if("1".equals(getlrbz)){
				whereStr1 = whereStr1 + "AND 1=2";
			}else if("2".equals(getlrbz)){
				whereStr = whereStr + "AND 1=2";
			}
		}
		
		//------获得结果集和结果条数------		
		EnhanceCountryHeadDao dao = new EnhanceCountryHeadDaoImp();
		List<CountryHeadBean> list = new ArrayList<CountryHeadBean>();	
		list = dao.queryInfo(whereStr, cityaudit, loginId,whereStr1);//结果集
		
		int num = list.size();//结果条数
		
		request.setAttribute("num", num);//结果条数
		request.setAttribute("list", list);//结果集

		//------根据前台按钮标识判断提交方向
		if("chaxun".equals(action)){//传到济宁查询页面
			request.getRequestDispatcher("/web/appJSP/checksign/manElectricCheck/jncountyHeadCheck.jsp")
			.forward(request, response);
		}else if("enhancedaochu".equals(action)){//传到济宁导出
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
		
       	//跳转页面
		request.getRequestDispatcher("/web/appJSP/checksign/cityelectricfeecheck/showdfxx.jsp").forward(request, response);
	
	}


	/**
	 * @author lijing
	 * @see 保存济宁电费审核调整申请信息
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
		 String dbid = request.getParameter("dbid"); //电表id
		 String dlid = request.getParameter("dlid"); //电量id
		 String electricfee_id = request.getParameter("electricfee_id"); //电费id
		 
		 bean.setDbid(dbid);
		 bean.setDlid(dlid);
		 bean.setElectricfee_id(electricfee_id);
		 
	     //------获取前台电量信息------
		 String floatdegree = request.getParameter("floatdegree");//电量调整
		 String ammemo = request.getParameter("ammemo");//电量备注
	     String bzydl = request.getParameter("bzydl");//报账用电量
	     String dbydl = request.getParameter("actualdegree");//电表用电量
	     BigDecimal bzz = new BigDecimal(Format.str2(request.getParameter("bzz")));//标准值
	     
	     bean.setFloatdegree(floatdegree);
	     bean.setAmmemo(ammemo);
	     bean.setBlhdl(bzydl);
	     bean.setDbydl(dbydl);
	     
	     //------获取前台电费信息------
	     String floatpay = request.getParameter("floatpay");//电费调整
	     String efmemo = request.getParameter("efmemo");//电费备注
	     String yddf = request.getParameter("yddf");//用电电费
	   	 String bzdf = request.getParameter("bzdf");//报账电费
	   	 
	   	 String beilv = request.getParameter("beilv");//倍率
	   	 String linelossvalue = request.getParameter("linelossvalue");//线损值
	   	 String changevalue = request.getParameter("changevalue");//变损值
	   	 String actuallinelossvalue = request.getParameter("actuallinelossvalue");//实际线损值
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
	   	 
	   	 //额定耗电量，全省定标电量，结算周期,倍率耗电量
	   	 String edhdl = request.getParameter("edhdl");
	   	 String qsdbdl = request.getParameter("qsdbdl");
	   	 String jszq = request.getParameter("jszq");
	   	 String blhdl = bean.getBlhdl();
	   	 String edcbbl1 ;//额定耗电量超标比例
	   	 String csdbbl1;//全省定标电量超标比例
	
	   	double bchdl = Double.parseDouble(blhdl);//本次耗电量
			if(Format.str_d(edhdl)==0){//额定耗电量为空则不计算
				edcbbl1 = "";
			}else{
				double hdl = Double.parseDouble(edhdl) * Format.str_d(jszq);//本地定标电量
				double edcbbl = Format.d2(((bchdl - hdl)/hdl)*100);
				edcbbl1 = Double.toString(edcbbl);
			}
			
			if(Format.str_d(edhdl)==0){//额定耗电量为空则不计算
				edcbbl1 = "";
			}else{
				double hdl = Double.parseDouble(edhdl) * Format.str_d(jszq);//本地定标电量
				double edcbbl = Format.d2(((bchdl - hdl)/hdl)*100);
				edcbbl1 = Double.toString(edcbbl);
			}
			
//			if(Format.str_d(qsdbdl)==0){//全省定标电量为空则不计算
//				csdbbl1 = "";
//			}else{
//				double qsdbdll = Double.parseDouble(qsdbdl) * Format.str_d(jszq);
//				double csdbbl = Format.d2(((bchdl-qsdbdll)/qsdbdll)*100);
//				csdbbl1 = Double.toString(csdbbl);
//			}
			//2015-1-22
			if( ("zdsx06".equals(property) && !"StationType12".equals(zdlx))
					|| ("zdsx01".equals(property) && !"StationType20".equals(zdlx))
					|| "zdsx03".equals(property) ){//非生产用原超标比例公式
				BigDecimal qsdl = new BigDecimal(qsdbdl).multiply(new BigDecimal(jszq));
				BigDecimal bl = (new BigDecimal(blhdl).subtract(qsdl)).divide(qsdl, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));//((blhdl - qsdbdl*jszq)/qsdbdl*jszq)*100
				csdbbl1 = Format.str2(bl.toString());
			}else{
				//（电表用电量-标准值）/标准值*100%
				BigDecimal csdbbla = (new BigDecimal(dbydl).subtract(bzz)).divide(bzz, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
				csdbbl1 = Format.str2(csdbbla.toString());
			}
			bean.setDedhdl(edcbbl1);
			bean.setCsdb(csdbbl1);
	     
	   	 Share share = new Share();//分摊信息bean
	     //------获取前台电量分摊信息------
	     share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
	     share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
	     share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
	     share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
	     share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
	     share.setDddl(Format.str_d(request.getParameter("dddfdl")));
	     
	     //------获取前台电费分摊信息------
	     share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
	     share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
	     share.setMarketdf(Format.str_d(request.getParameter("yydf")));
	     share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
	     share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
	     share.setDddf(Format.str_d(request.getParameter("dddfdf")));
	     
	     msg = dao.save(bean,share);
	     log.write(msg, accountid, request.getRemoteAddr(), "审核调整申请信息");
	   
	     String url = path + "/web/check/close.jsp";
	     session.setAttribute("url", url);
	     session.setAttribute("msg", msg);	//提示信息:是否成功
	     response.sendRedirect(path + "/web/msg.jsp");
	}
	
	/**
	 * @author lijing
	 * @see 济宁电费审核调整申请详细信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void xiangxi(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
			String dlid = request.getParameter("dlid");//电量ID
			String electricfee_id = request.getParameter("electricfee_id");//电费ID
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
			//配置常量
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
