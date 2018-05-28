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
 * @see 加强版电费单获取前台条件并返回结果集
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
			String action = request.getParameter("action")!=null?request.getParameter("action"):"";//获取要做的操作
	        	        
			if ("zdName".equals(action)) {
				//电费单站点名称模糊查询
				String loginId = request.getParameter("loginId")!=null?request.getParameter("loginId"):"";
				String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"";//市
				String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"";//区县	
				String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"";//乡镇
				String stationtype = request.getParameter("stationtype")!=null?request.getParameter("stationtype"):"";//站点类型	
				String jzname = (String)request.getParameter("dfmc")!=null?(String)request.getParameter("dfmc"):"";//站点名称
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
					if (!("请选择").equals(stationtype)){
						str = str + " and Z.STATIONTYPE IN('" + stationtype + "')";
					}
				}

				ArrayList zdname = new ArrayList();
				zdname = dao.checkMh(loginId,jzname,str); 
				request.setAttribute("jzname",jzname);
				request.setAttribute("zdname",zdname);
				request.getRequestDispatcher("/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBillAdd.jsp").forward(request, response);
			
			} else if ("zdMessage".equals(action)) {

				//电费单查询页面显示的只读字段
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
				
				ass = dao.ass(dbid);//辅助信息查询
				bas = dao.bas(dbid);//基本信息查询
				elec = dao.elec(dbid);//电费信息查询
				elec1 = dao.elec1(zdcode);//获取最近日期电表读数
				share1 = dao.share1(dbid);//分摊专业占比查询
				share2 = dao.share2(dbid);//分摊专业占比详细查询
				
				//配置常量
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
				//截止本年度当前的该地市的平均单价 
				//改为 标准单价 2015-1-28
				String averageunitprice = dao.getAverageUnitPrice(dbid,str);
				
				//获取电费支付类型，查询流程号
				String zflx = bas.getDfzflx();
				String liuchenghao = null;
				if("预支".equals(zflx)){//预支dfzflx03
					liuchenghao = dao.getLiuchenghao(dbid);
				}
				String htendtime = null;
				if("合同".equals(zflx)){//合同
					htendtime = dao.getHtEndTime(dbid);
				}
				boolean outnoconnct = false;
				if(dao.getOut(dbid)){//是外租回收且未与主站点ID相关联
						outnoconnct = true;
					
				}
				
				//在管理电表的情况下取得本次电表读数和本次抄表时间
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
				String bl = request.getParameter("bl")!=null?Format.str2(request.getParameter("bl")):"";//额定耗电量超标比例
				String qsbl = request.getParameter("qsbl")!=null?Format.str2(request.getParameter("qsbl")):"";//全省定标电量超标比例
				String beilv = request.getParameter("mpower")!=null?Format.str2(request.getParameter("mpower")):"1";//倍率
				String xgbzw = request.getParameter("xgbzw");//修改电表读数的标志位
				//电费单录入电量电费
				 String dbid = request.getParameter("dbid");  
				 String url = path + "/web/appJSP/electricmanage/enhanceelectricitybill/EnhanceEleBillAdd.jsp";
				 String msg = "";
				 DbLog log = new DbLog();
				 ElectricityInfo elecInfo = new ElectricityInfo();
				 Share share = new Share();
		      	 //主键生成
				 long uuid1 = System.currentTimeMillis();
				 String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
				 String uuid = uuid2+Long.toString(uuid1).substring(3);
		         System.out.println("主键生成:"+uuid);
		         String bzw="1";
		        //电量信息
		         String lastdegree = request.getParameter("lastdegree");
		         String thisdegree = request.getParameter("thisdegree");
		         String lastdatetime = request.getParameter("lastdatetime");
		         String thisdatetime = request.getParameter("thisdatetime");//本次抄表时间
		         String floatdegree = request.getParameter("floatdegree");//用电量调整
		         String scdf = request.getParameter("scdf");//上次电费
		         String scydl = request.getParameter("scydl");//上次用电量
		         String lastunitprice = request.getParameter("lastunitprice");//上次单价
		         String lastactualdegree = request.getParameter("lastactualdegree");//上期电表用电量
		         if(Format.str_d(beilv)==0){
		        	 beilv = "1";
		         }
		         String dbydl = request.getParameter("actualdegree");//电表用电量 = (本次电表读数-上次电表读数)*倍率
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
		         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		    	 String entrytime=df.format(new Date());
		         elecInfo.setEntrytime(entrytime);
		         String pjdl = request.getParameter("pjdl")==null?"0":request.getParameter("pjdl");
		         elecInfo.setPjdl(pjdl.trim());
		        //电量的五个分摊
		         share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
		         share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
		         share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
		         share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
		         share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
		         share.setDddl(Format.str_d(request.getParameter("dddfdl")));//代垫电量
		         
		         //供电方式
		         String gdfs = request.getParameter("gdfs");
		         //本次单价
		         String thisunitprice = "";
		         //峰谷平
		         String jfzb = "''";//尖峰占比
		         String bfzb = "''";//波峰占比
		         String bgzb = "''";//波谷占比
		         String bpzb = "''";//波平占比
		         if("直供电".equals(gdfs) || "直供电1（单位用电）".equals(gdfs) || "直供电2（民用电）".equals(gdfs)){
		        	 thisunitprice = request.getParameter("dyjunitprice"); 
		        	 jfzb = request.getParameter("jfzb");//尖峰占比，未除100
			         bfzb = request.getParameter("bfzb");//波峰占比
			         bgzb = request.getParameter("bgzb");//波谷占比
			         bpzb = request.getParameter("bpzb");//波平占比    
		         }else {//if("转供电".equals(gdfs))
		        	 thisunitprice = request.getParameter("unitprice"); 
		         }
		         elecInfo.setUnitprice(thisunitprice);
		         elecInfo.setJfzb(jfzb);
		         elecInfo.setBfzb(bfzb);
		         elecInfo.setBgzb(bgzb);
		         elecInfo.setBpzb(bpzb);

		         //电费信息      
		         String floatpay = request.getParameter("floatpay");//电费调整
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
		         elecInfo.setKptime(request.getParameter("kptime") == null ? "" : request.getParameter("kptime"));//录入时页面没有这个字段
		         elecInfo.setYddf(request.getParameter("yddf"));
		         
		         //2014-10-23
		         String zlfh = request.getParameter("zlfh");//直流负荷
		         String jlfh = request.getParameter("jlfh");//交流负荷
		         String property = request.getParameter("propertycode");//站点属性编码
		         String edhdl = request.getParameter("edhdl");//额定耗电量
		         String scb = request.getParameter("scb");//生产标
		         String qsdbdl = request.getParameter("qsdbdl");//全省定标电量
		         
		         //电费的分摊
		         share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
		         share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
		         share.setMarketdf(Format.str_d(request.getParameter("yydf")));
		         share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
		         share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
		         share.setDddf(Format.str_d(request.getParameter("dddfdf")));//代垫电费
		         
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
		        	 msg="输入信息重复，请核实信息！";
		        }else{
		        	String dfbz = ""; //电费审核状态标志0不通过  1通过
		        	String dfms = "";//电费审核不通过的描述信息
		        	String dlbz = "";//电量审核状态标志0不通过  1通过
		        	String dlms = "";//电量审核不通过的描述信息
		        	String[] dl1 = elecToo.checkFloatDegree(elecInfo);//用电量调整说明判断
		        	String[] dl2 = elecToo.checkDayDegree(dbid, thisdatetime, lastdatetime, blhdl);//上次日耗电量比值
		        	String[] dl3 = elecToo.checkBcdl(blhdl, thisdatetime, lastdatetime, null, dbid, bl, "1");//额定耗电量比值
		        	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtypecode);//2014-10-22超省标比例,合并周期,标准值
		        	String[] dl4 = elecToo.checkBcdl2(cbbl[0]);//超省定标10%
		        	String[] expecthigh = elecToo.checkExceptAndHigh(dbid, actualpay, blhdl, thisdatetime, lastdatetime, String.valueOf(Format.str_d(cbbl[0])/100));//异常及高额
		        	String[] site = elecToo.checckSite(dbid);//是否1.2万个点
		        	String[] adjust1 = elecToo.adjustCheck1(floatpay, floatdegree);//电费，电量调整
		        	String[] adjust2 = elecToo.adjustCheck2(lastunitprice, thisunitprice);//单价调整
		        	String[] chayue = elecToo.chaYue(thisdatetime, accountmonth);//本次抄表时间对应月份-报账月份>=2
		        	String[] thiselecfee = elecToo.checkThisFees(actualpay,shiandxian[0],shiandxian[1]);//本次电费金额大于区县上月平均电费金额
		        	String[] outprice = new String[]{"1",""};//默认 外借电判断为通过（即没有进行该判断）
		        	String[] adjustelec = elecToo.adjustElec(floatdegree,beilv);//如果是5,6,7,8,9,10月份电量调整大于800需要四级审核  否则大于500
		        	String[] adjustfeeandelec1 = elecToo.adjustFeeAndElec1(floatpay, floatdegree);//是否电费正调整大于10并且电量负调整
		        	String[] adjustfeeandelec2 = elecToo.adjustFeeAndElec2(floatdegree, floatpay, thisunitprice);//电量（正）负调整，并且电费（正）负调整：（电量调整*单价-电费调整）/电费调整>1.1 
		        	if("zdsx04".equals(expecthigh[2])){//属性为 其他 则 判断
		        		outprice = elecToo.OutElecUnitPrice(thisunitprice, shiandxian[0], shiandxian[1]);
		        	}
		        		dlms = dlms + dl1[1] + dl2[1] + dl3[1] + dl4[1] + outprice[1] + adjustfeeandelec1[1];
		        	//此处的if()else{} 判断是指如果电量自动审核不通过则 电费的自动审核也不通过，如果电量审核通过则对电费进行判 断
		        	if("1".equals(dl1[0])&&"1".equals(dl2[0])&&"1".equals(dl3[0])&&"1".equals(dl4[0])&&"1".equals(outprice[0])&&"0".equals(adjustfeeandelec1[0])){
		        		dlbz="1";//电量自动审核通过
		        		String[] df5 = elecToo.checkElectric1(elecInfo);//用电费调整说明判断
		        		String[] df6 = elecToo.checkElectric2(pj);//票据金额为空判断
		        			dfms = dfms + df5[1] + df6[1] + outprice[1] + adjustfeeandelec1[1];
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
		        	dfms =dfms + adjust1[1] + adjust1[3] + adjust2[1] + chayue[1] + thiselecfee[1] + adjustelec[1] + adjustfeeandelec1[1] + adjustfeeandelec2[1] + expecthigh[1] + site[1];//描述信息增加到电费表中
		        	String jszq = dl3[4];
		        //---------------------------------------------------------
		        	String edhdl1 = dl3[5];//额定耗电量
		        	String qsdbdl1 = qsdbdl;//全省定标电量
		        	String edhdlcbbl = dl3[3];//额定耗电量超标比例
		        	String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
		        	String hbzq = cbbl[1];//合并周期
		        	String bzz = cbbl[2];//标准值
		        	msg = dao.addDegreeFees(dbid,elecInfo,share,uuid,bzw,dfms,dfbz,dlms,dlbz,edhdlcbbl,qsdbdlcbbl,city,qxzr,cityzr,jszq,edhdl1,qsdbdl1,xgbzw,hbzq,bzz,scb);
			        log.write(msg, accountid, request.getRemoteAddr(), "新增电量电费");
		        }
		        session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
				response.sendRedirect(path + "/web/msg.jsp");
			}else if("getCsdb".equals(action)){
		    	 response.setContentType("text/html; charset=UTF-8");
		         response.setHeader("Cache-Control", "no-cache");
				 String zlfh = request.getParameter("zlfh");//直流负荷
		         String jlfh = request.getParameter("jlfh");//交流负荷
		         String property = request.getParameter("propertycode");//站点属性编码
		         String edhdl = request.getParameter("edhdl");//额定耗电量
		         String scb = request.getParameter("scb");//生产标
		         String dbid = request.getParameter("dbid");//电表id
		         String shi = request.getParameter("shi");//市
		         String thisdatetime = request.getParameter("thisdatetime");//本次抄表时间
		         String lastdatetime = request.getParameter("lastdatetime");//上次抄表时间
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
