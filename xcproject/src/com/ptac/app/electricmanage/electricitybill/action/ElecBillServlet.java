package com.ptac.app.electricmanage.electricitybill.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.ptac.app.electricmanage.electricitybill.bean.AssistInfo;
import com.ptac.app.electricmanage.electricitybill.bean.BasicInfo;
import com.ptac.app.electricmanage.electricitybill.bean.ElectricityInfo;
import com.ptac.app.electricmanage.electricitybill.bean.Share;
import com.ptac.app.electricmanage.electricitybill.dao.ElecBillDao;
import com.ptac.app.electricmanage.electricitybill.dao.ElecBillDaoImp;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanage.enhanceelectricitybill.bean.Configurations;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author lijing
 * @see 电费单获取前台条件并返回结果集
 */
public class ElecBillServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;
	
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
				request.getRequestDispatcher("/web/appJSP/electricmanage/electricitybill/eleBillAdd.jsp").forward(request, response);
			
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
				elec1 = dao.elec1(zdcode);//获取管理电表最近日期电表读数
				share1 = dao.share1(dbid);//分摊专业占比查询
				share2 = dao.share2(dbid);//分摊专业占比详细查询
				//配置常量
				Map<String,String> map = dao.getValue("3");
				String averagefees = map.get("AverageFees");
				String averagefeestrueorfalse = map.get("AverageFeesTrueOrFalse");
				String startshi = map.get("StartShi");
				Configurations con = new Configurations();
				con.setAveragefees(averagefees);
				con.setStartshi(startshi);
				con.setAveragefeestrueorfalse(averagefeestrueorfalse);
				
				//获取电费支付类型，查询流程号
				String zflx = bas.getDfzflx();
				String liuchenghao = null;
				if("预支".equals(zflx)){//预支zflx03
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
				request.setAttribute("share1",share1);
				request.setAttribute("share2",share2);
				request.setAttribute("dbid",dbid);
				request.setAttribute("liuchenghao",liuchenghao);
				request.setAttribute("htendtime",htendtime);
				request.setAttribute("outnoconnct", outnoconnct);
				request.setAttribute("configurations", con);
				request.getRequestDispatcher("/web/appJSP/electricmanage/electricitybill/eleBillMessage.jsp").forward(request, response);
			
			} else if ("addDf".equals(action)) {
				ElectricTool elecToo = new ElectricTool();
				String bl = request.getParameter("bl")!=null?Format.str2(request.getParameter("bl")):"";//额定耗电量超标比例
				String qsbl = request.getParameter("qsbl")!=null?Format.str2(request.getParameter("qsbl")):"";//全省定标电量超标比例
				String beilv = request.getParameter("mpower")!=null?Format.str2(request.getParameter("mpower")):"1";//前台倍率
				String xgbzw = request.getParameter("xgbzw");//修改电表读数的标志位
				//电费单录入电量电费
				 String dbid = request.getParameter("dbid");  
				 String url = path + "/web/appJSP/electricmanage/electricitybill/eleBillAdd.jsp";
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
		         //2014-10-21
		         String zlfh = request.getParameter("zlfh");//直流负荷
		         String jlfh = request.getParameter("jlfh");//交流负荷
		         String property = request.getParameter("property");//站点属性编码
		         String edhdl = request.getParameter("edhdl");//额定耗电量
		         String scb = request.getParameter("scb");//生产标
		         String qsdbdl = request.getParameter("qsdbdl");//全省定标电量
		         String stationtype = request.getParameter("stationtypecode");//站点类型code
		         String gdfs = request.getParameter("gdfscode");//供电方式code
		         String dfzflx = request.getParameter("dfzflxcode");//电费支付类型code
		         elecInfo.setZlfh(zlfh);
		         elecInfo.setJlfh(jlfh);
		         elecInfo.setPropertycode(property);
		         elecInfo.setStationtypecode(stationtype);
		         elecInfo.setGdfscode(gdfs);
		         elecInfo.setDfzflxcode(dfzflx);
		         if(Format.str_d(beilv)==0){
		        	 beilv = "1";
		         }
		         String dbydl = String.valueOf((Format.str_d(thisdegree)-Format.str_d(lastdegree))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
		         elecInfo.setDbydl(dbydl);
		         elecInfo.setBeilv(beilv);
		         elecInfo.setLastdegree(lastdegree);
		         elecInfo.setThisdegree(thisdegree);
		         elecInfo.setActualdegree(request.getParameter("actualdegree"));
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
		    	 elecInfo.setEntrypensonnel(request.getParameter("accountid"));
		         elecInfo.setEntrytime(entrytime);
		        //电量的五个分摊
		         share.setNetworkhdl(Format.str_d(request.getParameter("scdl")));
		         share.setAdministrativehdl(Format.str_d(request.getParameter("bgdl")));
		         share.setMarkethdl(Format.str_d(request.getParameter("yydl")));
		         share.setInformatizationhdl(Format.str_d(request.getParameter("xxhdl")));
		         share.setBuildhdl(Format.str_d(request.getParameter("jstzdl")));
		         share.setDddl(Format.str_d(request.getParameter("dddfdl")));//代垫电量
		         
		         //电费信息      
		         String floatpay = request.getParameter("floatpay");//电费调整
		         String thisunitprice = request.getParameter("unitprice");//本次单价
		         elecInfo.setUnitprice(thisunitprice);
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
		         //电费的分摊
		         share.setNetworkdf(Format.str_d(request.getParameter("scdff")));
		         share.setAdministrativedf(Format.str_d(request.getParameter("bgdf")));
		         share.setMarketdf(Format.str_d(request.getParameter("yydf")));
		         share.setInformatizationdf(Format.str_d(request.getParameter("xxhdf")));
		         share.setBuilddf(Format.str_d(request.getParameter("jstzdf")));
		         share.setDddf(Format.str_d(request.getParameter("dddfdf")));//代垫电费
		         
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
		        	String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shiandxian[0], property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);//2014-10-22超省标比例,合并周期,标准值
		        	String[] dl4 = elecToo.checkBcdl2(cbbl[0]);//超省标10%
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
				response.sendRedirect(path + "/web/appJSP/electricmanage/electricitybill/msg.jsp");
			}else if("getCsdb".equals(action)){
		    	 response.setContentType("text/html; charset=UTF-8");
		         response.setHeader("Cache-Control", "no-cache");
				 String zlfh = request.getParameter("zlfh");//直流负荷
		         String jlfh = request.getParameter("jlfh");//交流负荷
		         String property = request.getParameter("property");//站点属性编码
		         String edhdl = request.getParameter("edhdl");//额定耗电量
		         String scb = request.getParameter("scb");//生产标
		         String dbid = request.getParameter("dbid");//电表id
		         String shi = request.getParameter("shi");//市
		         String thisdatetime = request.getParameter("thisdatetime");//本次抄表时间
		         String lastdatetime = request.getParameter("lastdatetime");//上次抄表时间
		         String thisdegree = request.getParameter("thisdegree");//本次读数
		         String lastdegree = request.getParameter("lastdegree");//上次读数
		         String blhdl = request.getParameter("blhdl");//倍率耗电量
		         String qsdbdl = request.getParameter("qsdbdl");//全省定标电量
		         String stationtype = request.getParameter("stationtype");//站点类型
		         String beilv = request.getParameter("beilv");//倍率
		         if(Format.str_d(beilv)==0){
		        	 beilv = "1";
		         }
		         String dbydl = String.valueOf((Format.str_d(thisdegree)-Format.str_d(lastdegree))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
				 PrintWriter out = response.getWriter();
				 ElectricTool elecToo = new ElectricTool();
				 String[] cbbl=elecToo.getQsdbdlCbbl(dbydl, thisdatetime, lastdatetime, shi, property, zlfh, jlfh, edhdl, scb, dbid,blhdl,qsdbdl,stationtype);
		         out.print(Format.str2(cbbl[0])+"%");
		         out.close();
			} 
		}
}
