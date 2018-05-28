package com.noki.jizhan;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.google.gson.Gson;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonBean;
import com.noki.mobi.workflow.javabean.WorkFlowBean;
import com.ptac.app.accounting.AccountingDao;
import com.ptac.app.station.bean.Station;
import com.ptac.app.util.Code;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DianBiaoServlet extends HttpServlet {
    public DianBiaoServlet() {
    }
    private String getString(String input){
        String output = "";
        if(input==null||input.equals("null")){
            output = "";
        }else{
            return input;
        }
        return output;
    }

    private static final String Content_type = "text/html;charset=UTF-8";
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
    	System.out.println("=========");
        resp.setContentType(Content_type);
        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        HttpSession session = req.getSession();
        //String url = path + "/web/jizhan/dianbiaolist.jsp", msg = "";
        String url = path + "/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp"; 
        String	msg = "";
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");
        account = (Account) session.getAttribute("account");
        String action = req.getParameter("action");
        System.out.println("action="+action);
        if (action.equals("addZhanDian")) {
        	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	
            msg = "添加电表失败！请重试或与管理员联系！";
            String zdid = req.getParameter("zdid");  
            String dbid = req.getParameter("dbid");
            String countbzw = req.getParameter("countbzw");
            String sszy = getString(req.getParameter("sszy"));
            String dbyt = req.getParameter("dbyt");
            String dllx = req.getParameter("dllx");
            String ydsb = req.getParameter("ydsb");
            String csds = getString(req.getParameter("csds"));
            String cssytime = getString(req.getParameter("cssytime"));
            String beilv = getString(req.getParameter("beilv"));
            String dbxh = getString(req.getParameter("dbxh"));
            String memo = getString(req.getParameter("memo"));
            String netpoint_name = getString(req.getParameter("netpoint_name"));
            String netpoint_id = getString(req.getParameter("netpoint_id"));
            String yhdl=req.getParameter("yhdl");
            String dfzflx=req.getParameter("dfzflx");
            String dbname=req.getParameter("dbname");
            String linelosstype=req.getParameter("linelosstype");
            String linelossvalue=req.getParameter("linelossvalue")==null?"":req.getParameter("linelossvalue");
            String entrypensonnel=req.getParameter("accountname");
            String dbqyzt=req.getParameter("dbqyzt");//电表启用状态
            String zbdyhh=req.getParameter("zbdyhh");//自报电用户号
            String ydbid=req.getParameter("ydbid");//原电表id
            String danjia=req.getParameter("danjia");//电表单价
            String entrytime=mat.format(new Date());
            String shi =req.getParameter("t_shi");
            String bsdl=req.getParameter("bsdl")==null?"":req.getParameter("bsdl");//变损电量
            String zgdj = req.getParameter("zgdj");//暂估单价
            if(zgdj == null || "".equals(zgdj)){
            	zgdj = "0";
            }
           
            String zzsl1 = req.getParameter("zzsl");//增值税率
            String zzsl = zzsl1.trim();
           
            System.out.println(shi+"市");
            DianBiaoBean bean = new DianBiaoBean();
            int retsign = bean.addData(dbid,zdid, dllx, ydsb,shi, sszy, dbyt, csds,
                                       cssytime, beilv, dbxh, memo, accountid,netpoint_name,netpoint_id,yhdl,dfzflx,dbname,
                                       linelosstype,linelossvalue,entrypensonnel,entrytime,dbqyzt,zbdyhh,ydbid,danjia,bsdl,zgdj,zzsl,countbzw);
            if (retsign == 1) {
                msg = "添加电表成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加电表");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if(action.equals("addDB")){
			String zdid = req.getParameter("zdmc");
        	DateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
        	String dbname = req.getParameter("dbname");
        	String wlbm = req.getParameter("wlbm");
        	String cssysj = req.getParameter("t_sccbsj");
        	String beilv = req.getParameter("beilv");
        	System.out.println("beilv:"+beilv);
        	String csds = req.getParameter("csds");
        	String dfzflx = req.getParameter("dfzflx");
        	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//录入时间
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//修改时间
        	String ENTRYTIMEOLD =mat.format(new Date());//录入时间
        	String ENTRYTIME =sdf.format(new Date());//修改时间
        	String maxds = req.getParameter("maxds");
        	String dwjslx = req.getParameter("dwjslx");
        	String isglf = req.getParameter("isglf");
        	String glbm = req.getParameter("glbm");
        	String zrr = req.getParameter("zrr");
        	String bzr = req.getParameter("bzr");
        	String ydsx = req.getParameter("ydsx");
        	String ydlx = req.getParameter("ydlx");
        	String jffs = req.getParameter("jffs");
        	String jfzq = req.getParameter("jfzq");
        	String jldw = req.getParameter("jldw");
        	String yhbh = req.getParameter("yhbh");
        	String dbqyzt = req.getParameter("dbqyzt");
        	String bgje = req.getParameter("bgje");
        	String mtllhd = req.getParameter("mtllhd");
        	String danjia = req.getParameter("dj");
        	String gysmc = req.getParameter("gysmc");
        	String gysbm = req.getParameter("gysbm");
        	String skfmc = req.getParameter("skfmc");
        	String yhzh = req.getParameter("yhzh");
        	String ssyh = req.getParameter("ssyh");
        	String khyh = req.getParameter("khyh");
        	String zhsss = req.getParameter("zhsss");
        	String zhssshi = req.getParameter("zhssshi");
        	String memo = req.getParameter("memo");
        	String fplx = req.getParameter("fplx");
        	String zzsl = req.getParameter("sl");
        	String dbyt = req.getParameter("dbyt");
        	//String dbch = req.getParameter("dbch");
        	String dbch ="";
        	//String dbscbs = req.getParameter("dbscbs");
        	String dbscbs ="";
        	
        	String bz=req.getParameter("bz");
        	
        	String is_cont=req.getParameter("is_cont");
        	String cont_type=req.getParameter("cont_type");
        	String zndb=req.getParameter("zndb");
        	String isdblx=req.getParameter("isdblx");
        	
        	String cbzx=req.getParameter("cbzx");
        	String cbzxbm=req.getParameter("cbzxbm");
        	String production_prop=req.getParameter("production_prop");
        	String bur_stand_propertion=req.getParameter("bur_stand_propertion");
        	String cont_id=req.getParameter("cont_id");
        	String electric_supply_way=req.getParameter("electric_supply_way");
        	String total_electricity=req.getParameter("total_electricity");
        	String zhangqi=req.getParameter("zhangqi");
        	String zhz=req.getParameter("zhz");
        	String gszt=req.getParameter("gszt");
        	String jiaoliu=req.getParameter("jiaoliu");
        	String ftbl =req.getParameter("ftbl");
        	/*//string 强转double 
        	String ftbl1=req.getParameter("ftbl");
            double ftbl2=Double.valueOf(ftbl1).doubleValue()/100;//存入数据时 分摊比例换算成小数 比如 20% 存入为 0.2
        	String ftbl = Double.toString(ftbl2);
        	System.out.println("ftbl1"+ ftbl1+"ftbl2"+ftbl2+"ftbl"+ftbl);*/
        	
        	String sfyz=req.getParameter("sfyz");
        	String ssf=req.getParameter("ssf");
        	if(jiaoliu.equals("") || jiaoliu==null){
        		jiaoliu="0";
        	}
        	String zhiliu=req.getParameter("zhiliu");
        	if(zhiliu.equals("") || zhiliu==null){
        		zhiliu="0";
        	}
        	  DianBiaoBean beans = new DianBiaoBean();
        	String LJID="0"; //动环ID
        	LJID = beans.getZDStationNo(zdid);
        	String dbtype="1"; //录入
        	
    
        	Date tt=null;
			/*try {
				tt = time.parse(cssysj);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}*/
        	String id = (String) session.getAttribute("dbid");
        	
        	if("1".equals(bz)){       		
        		String sql="UPDATE DIANBIAO SET ENTRYTIME=to_date('"+ENTRYTIME+"', 'YYYY-MM-DD HH24:mi:ss'), is_cont='"+is_cont+"',cont_type='"+cont_type+"',zndb='"+zndb+"',isdblx='"+isdblx+"'," +
        				"cbzx='"+cbzx+"',cbzxbm='"+cbzxbm+"',production_prop='"+production_prop+"',bur_stand_propertion='"+bur_stand_propertion+"',cont_id='"+cont_id+"',electric_supply_way='"+electric_supply_way+"',total_electricity='"+total_electricity+"',zq='"+zhangqi+"',zhz='"+zhz+"'," +
        				"WLBM='"+wlbm+"', ZDID='"+zdid+"', DBNAME='"+dbname+"',BEILV='"+beilv+"',CSDS='"+csds+"',DFZFLX='"+dfzflx+"',MAXDS='"+maxds+"',DWJSLX='"+dwjslx+"',ISGLF='"+isglf+"'" +
        				" , GLBM='"+glbm+"', ZRR='"+zrr+"', BZR='"+bzr+"',YDSX='"+ydsx+"',YDLX='"+ydlx+"',JFFS='"+jffs+"',JFZQ='"+jfzq+"',JLDW='"+jldw+"',YHBH='"+yhbh+"',DBQYZT='"+dbqyzt+"',BGJE='"+bgje+"',MTLLHD='"+mtllhd+"',DANJIA='"+danjia+"',GYSMC='"+gysmc+"' ," +
        				"GYSBM='"+gysbm+"', SKFMC='"+skfmc+"',YHZH='"+yhzh+"',SSYH='"+ssyh+"',KHYH='"+khyh+"',ZHSSS='"+zhsss+"',ZHSSSHI='"+zhssshi+"',MEMO='"+memo+"',FPLX='"+fplx+"',ZZSL='"+zzsl+"',DBYT='"+dbyt+"' ,DBCH='"+dbch+"',DBSCBS='"+dbscbs+"', gszt='"+gszt+"',jiaoliu='"+jiaoliu+"',LJID='"+LJID+"',ssf='"+ssf+"',sfyz='"+sfyz+"',ftbl='"+ftbl+"',zhiliu="+zhiliu+" where id='"+id+"'";
        		System.out.println("修改电表"+sql);
        		DataBase db = new DataBase();
        		int a =0;
        		try {
					db.connectDb();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		try {
					a=db.update(sql.toString());
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		if(a==1){
        			msg="修改电表成功";
        		}else{
        			msg="修改失败，请联系管理员！";
        		}
        		
        		//成本中心修改流程  鉴于修改有困难，先删除 后添加
        		
            	//添加核算单元
            	String[] costCode = req.getParameterValues("costCode");
            	String[] businessType = req.getParameterValues("businessType");
            	String[] elecProperty = req.getParameterValues("elecProperty");
            	String[] dutyCode = req.getParameterValues("dutyCode");
            	String[] projectCode = req.getParameterValues("projectCode");
            	String[] isRealSubAmmeter = req.getParameterValues("isRealSubAmmeter");
            	String[] shareRatio = req.getParameterValues("shareRatio");
            	AccountingDao dao = new AccountingDao();
            	String dbcode = id+"";
            	dao.deleteAccounting(dbcode);
            	dao.addAccounting(costCode, businessType, elecProperty, dutyCode, projectCode, isRealSubAmmeter, shareRatio, dbcode);

        		
        		log.write(msg, accountid, req.getRemoteAddr(), "修改电表");
                session.setAttribute("url", url);
                session.setAttribute("msg", msg);
                resp.sendRedirect(path + "/web/msg.jsp");
        	}else{
        	DianBiaoBean bean = new DianBiaoBean();
        	//电表编码生成规则
        	String dbbm="";
        	String thid="";
        	dbbm = bean.getCustomDbbm(zdid);
        	LJID = bean.getZDStationNo(zdid);
        	
        	int ret = bean.addDB(zdid, dbbm, dbname, cssysj, beilv, csds, dfzflx, wlbm, maxds, 
        			dwjslx, isglf, glbm, zrr, bzr, ydsx, ydlx, jffs, jfzq, jldw, yhbh, dbqyzt, 
        			bgje, mtllhd, danjia, gysmc, gysbm, skfmc, yhzh, ssyh, khyh, zhsss, zhssshi, 
        			memo, fplx, zzsl, dbyt, dbch, dbscbs, ENTRYTIMEOLD, is_cont, cont_type, zndb, 
        			isdblx, cbzx, cbzxbm, production_prop, bur_stand_propertion, cont_id, electric_supply_way, 
        			total_electricity, zhangqi, zhz, gszt, LJID, dbtype, thid, jiaoliu, zhiliu, ssf, ftbl, sfyz);
        	if (ret > 0) {
                msg = "添加电表成功！";
            }else{
    			msg="添加电表失败，请联系管理员！";
    		}
        	
        	
        	
        	//添加核算单元
        	String[] costCode = req.getParameterValues("costCode");
        	String[] businessType = req.getParameterValues("businessType");
        	String[] elecProperty = req.getParameterValues("elecProperty");
        	String[] dutyCode = req.getParameterValues("dutyCode");
        	String[] projectCode = req.getParameterValues("projectCode");
        	String[] isRealSubAmmeter = req.getParameterValues("isRealSubAmmeter");
        	String[] shareRatio = req.getParameterValues("shareRatio");
        	AccountingDao dao = new AccountingDao();
        	String dbcode = ret+"";
        	dao.addAccounting(costCode, businessType, elecProperty, dutyCode, projectCode, isRealSubAmmeter, shareRatio, dbcode);
        	
            log.write(msg, accountid, req.getRemoteAddr(), "添加电表");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        }//撤回报账
        else if(action.equals("chbaozhang")){
       	 url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
         String id = req.getParameter("id");
         msg = "撤回失败！请重试或与管理员联系！";
         SiteManage bean = new SiteManage();
         int retsign = bean.chBaozhang(id);
         if (retsign == 1) {
             msg = "撤回报账信息成功！";
         } else if (retsign == 2) {
             msg = "撤回失败！请重试或与管理员联系！";
         }
         log.write(msg, accountid, req.getRemoteAddr(), "撤回报账");
         session.setAttribute("url", url);
         session.setAttribute("msg", msg);
         resp.sendRedirect(path + "/web/msg.jsp");
    }//撤回电表审批信息
        else if(action.equals("chdianbiao")){
          	 url = path + "/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp";
            String id = req.getParameter("id");
            msg = "撤回失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.chdianbiao(id);
            if (retsign == 1) {
                msg = "撤回信息成功！";
            } else if (retsign == 2) {
                msg = "撤回失败！请重试或与管理员联系！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "撤回电表审批");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
       }//撤回铁塔报账
        else if(action.equals("chttbz")){
          	 url = path + "/web/sdttWeb/baozhang/TietaBaozhang.jsp";
            String id = req.getParameter("id");
            msg = "撤回失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.chttba(id);
            if (retsign == 1) {
                msg = "撤回报账信息成功！";
            } else if (retsign == 2) {
                msg = "撤回失败！请重试或与管理员联系！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "撤回铁塔报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
       }//撤回办公营业审核
        else if(action.equals("chbgyy")){
         	 url = path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp";
           String id = req.getParameter("id");
           msg = "撤回失败！请重试或与管理员联系！";
           SiteManage bean = new SiteManage();
           int retsign = bean.chbg(id);
           if (retsign == 1) {
               msg = "撤回审批信息成功！";
           } else if (retsign == 2) {
               msg = "撤回失败！请重试或与管理员联系！";
           }
           log.write(msg, accountid, req.getRemoteAddr(), "撤回办公营业厅审批信息");
           session.setAttribute("url", url);
           session.setAttribute("msg", msg);
           resp.sendRedirect(path + "/web/msg.jsp");
      }else if(action.equals("addXSManager")){
        	   url = path + "/web/sdttWeb/jichuInfoManager/XSManager.jsp";
               SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                msg = "添加报账失败！请重试或与管理员联系！";
        	String houseType = req.getParameter("houseType");
        	String addTime = req.getParameter("addTime");
        	String minzhi = req.getParameter("minzhi");
        	String maxzhi = req.getParameter("maxzhi");
        	
        	String[] beginzhi = req.getParameterValues("beginzhi");
        	String[] endzhi = req.getParameterValues("endzhi");
     	
	      	CommonBean commonBean = new CommonBean();
      		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
      		String yymmdd =sdf2.format(new Date());
      		int houseid=0;
      		 houseid= commonBean.addXSManager(yymmdd, account.getAccountId(), addTime, maxzhi, minzhi, houseType);
           if(houseid!=0){
        	   commonBean.addXSManager_T(""+houseid, beginzhi, endzhi);
        	   msg = "添加房屋系数成功！";
           }
      		
      		log.write(msg, accountid, req.getRemoteAddr(), "添加房屋系数");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        else if(action.equals("getDbxxById2")){
        	System.out.println(">>>>>>>>>>>getDbxxById2");
            DecimalFormat df1 = new DecimalFormat("0.00");
            @SuppressWarnings("unused")
			String BEILV = req.getParameter("BEILV");//倍率获取
            String dianbiaoId = req.getParameter("dianbiaoId");//电表id
            String SQRJYDL_str = req.getParameter("SQRJYDL_str");//上期日均用电量
            String stime = req.getParameter("stime");//开始时间
            String etime = req.getParameter("etime");//结束时间
            String ts = req.getParameter("ts");//天数
            String bgdl ="";// 标杆电量
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            String addtime =sdf2.format(new Date());//当前年月1
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
            String addtime_YYYY =sdf3.format(new Date());//当前年月1
            CommonBean commonBean = new CommonBean();
            SiteManage bean = new SiteManage();
            
            if(addtime_YYYY.equals("2018")){
            	//如果是2018年，计算标杆电量
            	System.out.println("//如果是2018年，计算标杆电量");
            	  List<String> strList = commonBean.getstationByDbId2(dianbiaoId);//获取电表信息
                  String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="",housetype="",housetype2="";
                  if(strList!=null && strList.size()==5){
                  	st_id_str = strList.get(0);//站点的station_no
                  	jiaoliu_str = strList.get(1);//电表得交流额定功率
                  	zhiliu_str = strList.get(2);//电表得直流额定功率
                  	zdid_str = strList.get(3);//站点id
                  	housetype = strList.get(4);//站点的房屋类型
                  }
                  if(jiaoliu_str==""||jiaoliu_str.equals("")){
                	  jiaoliu_str="0";
                  }
                  if(zhiliu_str==""||zhiliu_str.equals("")){
                	  zhiliu_str="0";
                  }
                  List<String> strList2 = commonBean.getSumByDbId(st_id_str,stime, etime);//获取pue信息
                  String sum_str = "",value_str="",sumDay_str="";//总值,检测的电量平局值，平均天数
                  if(strList2!=null && strList2.size()==3){
                  	sum_str = strList2.get(0);//电量（计算所得）= 电流*53.5/0.9/1000*24
                  	value_str = strList2.get(1);//信号值（电流）
                  	sumDay_str = strList2.get(2);//电流平均值天数
                  }
                  if(value_str.equals("")||value_str==null){
                	  
                	  value_str="14";
                  }
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH) + 1;
                int oldyear = cal.get(Calendar.YEAR)-1;
                String month_s= "";
                if(month<10){
               	 month_s = "0"+ month;
                }else{
               	 month_s=month+"";
                }
                String addtime_old = ""+oldyear+"-"+month_s;//获取去年同月
                
                String shi = bean.getshiById(dianbiaoId);//根据电表id查对应的“市”
                String strs3 = bean.getAnalysis_2017_ByShi(shi,month+"");//全市的去年同期月份的平均PUE
                String oldyearYdl = bean.getRjdl_2017_ByZdid(st_id_str, addtime_old);//本站去年同期日均电量
                
                
                if(housetype.equals("20")){
             	   housetype2="20";
                }else{
             	   housetype2="30";
                }
                List<String> strList3 = commonBean.getXsByDbId(housetype2, addtime);//获取房屋系数的最大值和最小值
                
                String housexsid="",MAXZHI="",MINZHI="";
                if(strList3!=null && strList3.size()==3){
             	   housexsid = strList3.get(0);
             	   MAXZHI = strList3.get(1);
             	   MINZHI = strList3.get(2);
             	   if(MINZHI==null || MINZHI==""){
             		  MINZHI="1";
             	   }
                   }
                
                List<String> strList4 = commonBean.getXs_T_By(housexsid, value_str.replaceAll(" ", ""));//获取房屋系数所在的区间
                String housexsid_t="",MIN_T="",MAX_T="";
                if(strList4!=null && strList4.size()==3){
             	   housexsid_t = strList4.get(0);
             	   MIN_T = strList4.get(1);
             	   MAX_T = strList4.get(2);
                 }
           		String fwxs="";//房屋系数  》》以下为计算房屋系数
           		if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("0")){
           			fwxs=MINZHI;
           		}else if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("9999999999")){
           			fwxs=MAXZHI;
           		}else {
           		
           				//房屋系数计算保留两位小数
           				fwxs=""+df1.format(Double.valueOf(MINZHI).doubleValue()+
           						(Double.valueOf(MAXZHI).doubleValue()-
           								Double.valueOf(MINZHI).doubleValue())
           								*(Double.valueOf(value_str).doubleValue()
           										-Double.valueOf(MIN_T).doubleValue())
           										/(Double.valueOf(MAX_T).doubleValue()
           												-Double.valueOf(MIN_T).doubleValue()));
           			
           			
           		}

                  //计算标杆电量Double.valueOf(jiaoliu_str).doubleValue() +Double.valueOf(value_str).doubleValue()*54 +Double.valueOf(zhiliu_str).doubleValue()
                  if(strs3!=null && !strs3.equals("") && jiaoliu_str!=null && !jiaoliu_str.equals("")
               		   && zhiliu_str!=null && !zhiliu_str.equals("") && value_str!=null && !value_str.equals("")
               		   && oldyearYdl!=null && !oldyearYdl.equals("") && SQRJYDL_str!=null && !SQRJYDL_str.equals("")
               		   && ts!=null && !ts.equals("") && fwxs!=null && !fwxs.equals("") ){
                	  // 主设备功率 Double.valueOf(jiaoliu_str).doubleValue() +Double.valueOf(value_str).doubleValue()*54 +Double.valueOf(zhiliu_str).doubleValue()
               	   /**
               	    * st_id_str//站点的station_no
                  	jiaoliu_str //电表得交流额定功率
                  	zhiliu_str //电表得直流额定功率
                  	zdid_str //站点id
                  	housetype //站点的房屋类型
                  	sum_str //电量（计算所得）= 电流*53.5/0.9/1000*24
                  	value_str //信号值（电流）
                  	sumDay_str //电流平均值天数
                  	MAXZHI 房屋系数最大值
             	    MINZHI 房屋系数最小值
               	    */
                	  String zsbgl = ""+add(add(mul(updouble(value_str),54),updouble(jiaoliu_str)),updouble(zhiliu_str));
                	  bgdl = ""+df1.format(mul(div(mul(mul(0.5,updouble(strs3)),updouble(zsbgl)),add(add(mul(mul(0.9,24),updouble(fwxs)),mul(0.2,updouble(oldyearYdl))),mul(0.3,updouble(SQRJYDL_str))),2),updouble(ts)))   ;                
//                			  bgdl =""+ df1.format((0.5*Double.valueOf(strs3).doubleValue()*(Double.valueOf(jiaoliu_str).doubleValue() +
//                  				Double.valueOf(value_str).doubleValue()*54 +
//                  				Double.valueOf(zhiliu_str).doubleValue())/0.9*24*Double.valueOf(fwxs).doubleValue()+0.2*Double.valueOf(oldyearYdl).doubleValue()+0.3*Double.valueOf(SQRJYDL_str).doubleValue())*Double.valueOf(ts).doubleValue());
                  System.out.println("2018年标杆电量计算："+bgdl);
                  }else if(oldyearYdl==null||oldyearYdl.equals("")&&SQRJYDL_str!=""&&SQRJYDL_str!=null){
                	  String zsbgl = ""+add(add(mul(updouble(value_str),54),updouble(jiaoliu_str)),updouble(zhiliu_str));
                	  bgdl = ""+df1.format(mul(div(mul(mul(0.5,updouble(strs3)),updouble(zsbgl)),add(mul(mul(0.9,24),updouble(fwxs)),mul(0.5,updouble(SQRJYDL_str))),2),updouble(ts)))   ;                
//                 	
                  }else if(SQRJYDL_str==null||SQRJYDL_str.equals("")){
                	  String zsbgl = ""+add(add(mul(updouble(value_str),54),updouble(jiaoliu_str)),updouble(zhiliu_str));
                	  bgdl = ""+df1.format(mul(div(mul(mul(0.5,updouble(strs3)),updouble(zsbgl)),add(add(mul(mul(0.9,24),updouble(fwxs)),mul(0.5,2.0)),mul(0,0)),2),updouble(ts)))   ;                
            			}
                  
                  if(BEILV != "" && BEILV != "0"){
                	  bgdl = "" + df1.format(mul(updouble(bgdl),updouble(BEILV)));
                  }
            }else{
            	//如果不是2018年，计算标杆电量
            	System.out.println("//如果不是2018年，计算标杆电量");
            	 List<String> strList = commonBean.getstationByDbId2(dianbiaoId);//获取电表信息
                 
                 String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="",housetype="",housetype2="";
                 if(strList!=null && strList.size()==5){
                 	st_id_str = strList.get(0);//站点的station_no
                 	jiaoliu_str = strList.get(1);//电表得交流额定功率
                 	zhiliu_str = strList.get(2);//电表得直流额定功率
                 	zdid_str = strList.get(3);//站点id
                 	housetype = strList.get(4);//站点的房屋类型
                 }
                 List<String> strList2 = commonBean.getSumByDbId(st_id_str,stime, etime);//获取pue信息
                 String sum_str = "",value_str="",sumDay_str="";//总值,检测的电量平局值，平均天数
                 if(strList2!=null && strList2.size()==3){
                 	sum_str = strList2.get(0);
                 	value_str = strList2.get(1);
                 	sumDay_str = strList2.get(2);
                 }
               Calendar cal = Calendar.getInstance();
               int month = cal.get(Calendar.MONTH) + 1;
               int oldyear = cal.get(Calendar.YEAR)-1;
               String month_s= "";
               if(month<10){
              	 month_s = "0"+ month;
               }else{
              	 month_s=month+"";
               }
               String addtime_old = ""+oldyear+"-"+month_s;//获取去年同月
               
               String shi = bean.getshiById(dianbiaoId);
               String strs3 = bean.getAnalysisByShi(shi,addtime_old);//全市的去年同期月份的平均PUE
               String oldyearYdl = commonBean.getRjydlByDbId(dianbiaoId, addtime_old);//本站去年同期日均电量
               
               
               if(housetype.equals("20")){
            	   housetype2="20";
               }else{
            	   housetype2="30";
               }
               List<String> strList3 = commonBean.getXsByDbId(housetype2, addtime);//获取房屋系数的最大值和最小值
               
               String housexsid="",MAXZHI="",MINZHI="";
               if(strList3!=null && strList3.size()==3){
            	   housexsid = strList3.get(0);
            	   MAXZHI = strList3.get(1);
            	   MINZHI = strList3.get(2);
                  }
               
               List<String> strList4 = commonBean.getXs_T_By(housexsid, value_str);//获取房屋系数所在的区间
               String housexsid_t="",MIN_T="",MAX_T="";
               if(strList4!=null && strList4.size()==3){
            	   housexsid_t = strList4.get(0);
            	   MIN_T = strList4.get(1);
            	   MAX_T = strList4.get(2);
                }
          		String fwxs="";//房屋系数  》》以下为计算房屋系数
          		if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("0")){
          			fwxs=MINZHI;
          		}else if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("9999999999")){
          			fwxs=MAXZHI;
          		}else {
          			try {
          				fwxs=""+df1.format(Double.valueOf(MINZHI).doubleValue()+
          						(Double.valueOf(MAXZHI).doubleValue()-
          								Double.valueOf(MINZHI).doubleValue())
          								*(Double.valueOf(value_str).doubleValue()
          										-Double.valueOf(MIN_T).doubleValue())
          										/(Double.valueOf(MAX_T).doubleValue()
          												-Double.valueOf(MIN_T).doubleValue()));
          			} catch (Exception e) {
          				fwxs="";
          			}
          			
          		}

                 //计算标杆电量
          		if(strs3!=null && !strs3.equals("") && jiaoliu_str!=null && !jiaoliu_str.equals("")
               		   && zhiliu_str!=null && !zhiliu_str.equals("") && value_str!=null && !value_str.equals("")
               		   && oldyearYdl!=null && !oldyearYdl.equals("") && SQRJYDL_str!=null && !SQRJYDL_str.equals("")
               		   && ts!=null && !ts.equals("") && fwxs!=null && !fwxs.equals("") ){
               	   
               	   
                       bgdl =""+ df1.format((0.5*Double.valueOf(strs3).doubleValue()*(Double.valueOf(jiaoliu_str).doubleValue() +
                  				Double.valueOf(value_str).doubleValue()*54 +
                  				Double.valueOf(zhiliu_str).doubleValue())/0.9*24*Double.valueOf(fwxs).doubleValue()+0.2*Double.valueOf(oldyearYdl).doubleValue()+0.3*Double.valueOf(SQRJYDL_str).doubleValue())*Double.valueOf(ts).doubleValue());
                  }
            }
            
          
                
               //resp.setContentType("text/html;charset=utf-8");
               PrintWriter out = resp.getWriter();  
               Gson gson=new Gson();
               String json=gson.toJson(bgdl);
               out.write(json);
               out.flush();  
               out.close();  
        
    }
        else if(action.equals("updateXSManager")){
     	   url = path + "/web/sdttWeb/jichuInfoManager/XSManager.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             msg = "修改房屋系数失败！请重试或与管理员联系！";
             String id = req.getParameter("id");
     	String houseType = req.getParameter("houseType");
     	String addTime = req.getParameter("addTime");
     	String minzhi = req.getParameter("minzhi");
     	String maxzhi = req.getParameter("maxzhi");
     	
     	String[] beginzhi = req.getParameterValues("beginzhi");
     	String[] endzhi = req.getParameterValues("endzhi");
     	
//    	AccountingDao dao = new AccountingDao();
//     	String dbcode = ret+"";
//     	dao.addAccounting(costCode, businessType, elecProperty, dutyCode, projectCode, isRealSubAmmeter, shareRatio, dbcode);
//     	
  	
	      	CommonBean commonBean = new CommonBean();
   		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
   		String yymmdd =sdf2.format(new Date());
   		int ss=0;
   		 ss= commonBean.updateXSManager(id,yymmdd, account.getAccountId(), addTime, maxzhi, minzhi, houseType);
        if(ss!=0){
           commonBean.delXSManager_T(""+id);
     	   commonBean.addXSManager_T(""+id, beginzhi, endzhi);
     	   msg = "修改房屋系数成功！";
        }
   		
   		log.write(msg, accountid, req.getRemoteAddr(), "修改房屋系数");
         session.setAttribute("url", url);
         session.setAttribute("msg", msg);
         resp.sendRedirect(path + "/web/msg.jsp");
     }
        else if (action.equals("modifydianbiao")) {
        	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "修改电表信息失败！请重试或与管理员联系！";
            String id = req.getParameter("id");
            String dbid = req.getParameter("dbid");
            String sszy = getString(req.getParameter("sszy"));
            String dbyt = req.getParameter("dbyt");
            String dllx = req.getParameter("dllx");
            String ydsb = req.getParameter("ydsb");
            String csds = getString(req.getParameter("csds"));
            String cssytime = getString(req.getParameter("cssytime"));
            String beilv = getString(req.getParameter("beilv"));
            String dbxh = getString(req.getParameter("dbxh"));
            String memo = getString(req.getParameter("memo"));
            String netpoint_name = getString(req.getParameter("netpoint_name"));
            String netpoint_id = getString(req.getParameter("netpoint_id"));
            //String yhdl=req.getParameter("yhdl");
            String dfzflx=req.getParameter("dfzflx");
            if("月结".equals(dfzflx)){
            	dfzflx = "dfzflx01";
            }else if("合同".equals(dfzflx)){
            	dfzflx = "dfzflx02";
            }else if("预支".equals(dfzflx)){
            	dfzflx = "dfzflx03";
            }else if("插卡".equals(dfzflx)){
            	dfzflx = "dfzflx04";
            }
            String dbname=req.getParameter("dbname");
            String linelosstype=req.getParameter("linelosstype");
            String linelossvalue=req.getParameter("linelossvalue")==null?"":req.getParameter("linelossvalue");
            String entrypensonnel=req.getParameter("accountname");
            String dbqyzt=req.getParameter("dbqyzt");//电表启用状态
            String zbdyhh=req.getParameter("zbdyhh");//自报电用户号
            String ydbid=req.getParameter("ydbid");//原电表id
            String danjia = req.getParameter("danjia");//电表单价
            String bsdl = req.getParameter("bsdl")==null?"":req.getParameter("bsdl");//变损电量
            String zgdj = req.getParameter("zgdj");//暂估单价
            if(zgdj == null || "".equals(zgdj)){
            	zgdj = "0";
            }
            String zzsl1 = req.getParameter("zzsl");//增值税率
            String zzsl = zzsl1.trim();
            String entrytime=mat.format(new Date());
            
            String zdid = req.getParameter("zdid"); 
            String countbzw = req.getParameter("countbzw"); //站点表中是否多块表标志，记录该站点可以同时含有多块启用的结算电表（0---1块表；1---多块表）
            String qydb = req.getParameter("qydb");//启用电表总数  
            String gbdb= req.getParameter("gbdb");//关闭电表总数
            String count = req.getParameter("count");//启用和关闭电表总数和
            
            DianBiaoBean bean = new DianBiaoBean();
//            String is_cont,cont_type,zndb,isdblx,cbzx,cbzxbm,production_prop,bur_stand_propertion,cont_id,electric_supply_way,total_electricity,zhangqi,zhz;
        	
            int retsign = bean.modifyData(dbid,id, dllx, ydsb, sszy, dbyt, csds,
                                       cssytime, beilv, dbxh, memo,netpoint_name,netpoint_id,dfzflx,dbname,linelosstype,
                                       linelossvalue,entrypensonnel,entrytime,dbqyzt,zbdyhh,ydbid,danjia,bsdl,zgdj,zzsl,
                                       zdid,countbzw,count,qydb,gbdb);
            System.out.println(retsign);
            if (retsign == 1) {
                msg = "修改电表信息成功！";
            }
            String url1 = path + "/web/jizhan/dianbiaolist.jsp";
            log.write(msg, accountid, req.getRemoteAddr(), "修改电表信息");
            session.setAttribute("url", url1);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
 else if (action.equals("del")) {
            url = path + "/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp";
            String id = req.getParameter("id");
            msg = "删除电表失败！请重试或与管理员联系！";
            DianBiaoBean bean = new DianBiaoBean();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "删除电表成功！";
            }else if(retsign==2){
                msg = "有关联的电量信息，不允许删除！";
            }else if(retsign==3){
            	msg="有关联的预付费信息，不允许删除！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除电表");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
 else if (action.equals("del2")) {
     url = path + "/web/electricfees/dianfeidan_search.jsp";
     String id = req.getParameter("id");
     msg = "删除电表失败！请重试或与管理员联系！";
     DianBiaoBean bean = new DianBiaoBean();
     int retsign = bean.delData1(id);
     if (retsign == 1) {
         msg = "删除电表成功！";
     }else if(retsign==2){
         msg = "有关联的电量信息，不允许删除！";
     }else if(retsign==3){
     	msg="有关联的预付费信息，不允许删除！";
     }
     log.write(msg, accountid, req.getRemoteAddr(), "删除电表");
     session.setAttribute("url", url);
     session.setAttribute("msg", msg);
     resp.sendRedirect(path + "/web/msg.jsp");

 }
 else if (action.equals("getDianbiaoByDbbm")){
	 String dbbm = req.getParameter("dbbm"); 
	 String id = req.getParameter("id"); 
	
	 DianBiaoBean bean = new DianBiaoBean();
	 String strs ="";
	 strs = bean.getDianbiaoByDbbm(dbbm,id);
	 resp.setContentType("text"); 
     PrintWriter out = resp.getWriter();  
     
     Gson gson=new Gson();
     String json=gson.toJson(strs);
     out.write(json);
     out.flush();  
     out.close();  
 }
 else if(action.equals("changeType")){
	 String id = req.getParameter("id"); 
	 String type = req.getParameter("type");
	 String ssf=req.getParameter("ssf");
	    System.out.println("wwwwwdddd"+ssf);
	 int newdbId=0;
	 int n=0;
	 if(type.equals("danjia")){
		 n=4;
	 }
	 else if(type.equals("beilv")){
		 n=2;
	 }
	 else if(type.equals("dbqyzt")){
		 n=3;
	 }
	 else if(type.equals("changedb")){
		 n=1;
		 
		String zdid = req.getParameter("zdmc");
     	DateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
     	String dbname = req.getParameter("dbname2");
     	String wlbm = req.getParameter("wlbm2");
     	String cssysj = req.getParameter("t_sccbsj");
     	String beilv = req.getParameter("beilv2");
     	String csds = req.getParameter("csds");
     	String dfzflx = req.getParameter("dfzflx");
     	
     	String maxds = req.getParameter("maxds");
     	String dwjslx = req.getParameter("dwjslx");
     	String isglf = req.getParameter("isglf");
     	String glbm = req.getParameter("glbm");
     	String zrr = req.getParameter("zrr");
     	String bzr = req.getParameter("bzr");
     	String ydsx = req.getParameter("ydsx");
     	String ydlx = req.getParameter("ydlx");
     	String jffs = req.getParameter("jffs");
     	String jfzq = req.getParameter("jfzq");
     	String jldw = req.getParameter("jldw");
     	String yhbh = req.getParameter("yhbh");
     	String dbqyzt = req.getParameter("dbqyzt2");
     	String bgje = req.getParameter("bgje");
     	String mtllhd = req.getParameter("mtllhd");
     	String danjia = req.getParameter("dj2");
     	String gysmc = req.getParameter("gysmc");
     	String gysbm = req.getParameter("gysbm");
     	String skfmc = req.getParameter("skfmc");
     	String yhzh = req.getParameter("yhzh");
     	String ssyh = req.getParameter("ssyh");
     	String khyh = req.getParameter("khyh");
     	String zhsss = req.getParameter("zhsss");
     	String zhssshi = req.getParameter("zhssshi");
     	String memo = req.getParameter("memo");
     	String fplx = req.getParameter("fplx");
     	String zzsl = req.getParameter("sl");
     	String dbyt = req.getParameter("dbyt");
     	//String dbch = req.getParameter("dbch");
     	String dbch ="";
     	//String dbscbs = req.getParameter("dbscbs");
     	String dbscbs ="";
     	
     	String bz=req.getParameter("bz");
     	
     	String is_cont=req.getParameter("is_cont");
     	String cont_type=req.getParameter("cont_type");
     	String zndb=req.getParameter("zndb");
     	String isdblx=req.getParameter("isdblx");
     	
     	String cbzx=req.getParameter("cbzx");
     	String cbzxbm=req.getParameter("cbzxbm");
     	String production_prop=req.getParameter("production_prop");
     	String bur_stand_propertion=req.getParameter("bur_stand_propertion");
     	String cont_id=req.getParameter("cont_id");
     	String electric_supply_way=req.getParameter("electric_supply_way");
     	String total_electricity=req.getParameter("total_electricity");
     	String zhangqi=req.getParameter("zhangqi");
     	String zhz=req.getParameter("zhz");
     	String gszt=req.getParameter("gszt");
     	String ftbl=req.getParameter("ftbl");
    	String sfyz=req.getParameter("sfyz");
    	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//录入时间
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//修改时间
    	String ENTRYTIMEOLD =mat.format(new Date());//录入时间
    	String ENTRYTIME =sdf.format(new Date());//修改时间
     	String LJID="0"; //动环ID
     	String dbtype="1"; //录入
     	String jiaoliu=req.getParameter("jiaoliu");
    	if(jiaoliu.equals("") || jiaoliu==null){
    		jiaoliu="0";
    	}
    	String zhiliu=req.getParameter("zhiliu");
    	if(zhiliu.equals("") || zhiliu==null){
    		zhiliu="0";
    	}
     	
     	Date tt=null;
     	DianBiaoBean bean = new DianBiaoBean();
    	//电表编码生成规则
    	String dbbm="";
    	String thid=id; // udpate by sgn 2018-4-18 

    	dbbm = bean.getCustomDbbm(zdid);
    	
    	LJID = bean.getZDStationNo(zdid);//获取动环局站ID
    	newdbId = bean.addDB(zdid, dbbm, dbname, cssysj, beilv, csds, dfzflx, wlbm, maxds, dwjslx, isglf, glbm,
    			zrr, bzr, ydsx, ydlx, jffs, jfzq, jldw, yhbh, dbqyzt, bgje, mtllhd, danjia, gysmc, gysbm,
    			skfmc, yhzh, ssyh, khyh, zhsss, zhssshi, memo, fplx, zzsl, dbyt, dbch, dbscbs, ENTRYTIMEOLD,
    			is_cont, cont_type, zndb, isdblx, cbzx, cbzxbm, production_prop, bur_stand_propertion, cont_id, electric_supply_way, total_electricity, zhangqi, zhz, 
    			gszt, LJID, dbtype, thid, jiaoliu, zhiliu, ssf, ftbl, sfyz);
    	
    	
    	
    	//添加核算单元
    	String[] costCode = req.getParameterValues("costCode");
    	String[] businessType = req.getParameterValues("businessType");
    	String[] elecProperty = req.getParameterValues("elecProperty");
    	String[] dutyCode = req.getParameterValues("dutyCode");
    	String[] projectCode = req.getParameterValues("projectCode");
    	String[] isRealSubAmmeter = req.getParameterValues("isRealSubAmmeter");
    	String[] shareRatio = req.getParameterValues("shareRatio");
    	AccountingDao dao = new AccountingDao();
    	String dbcode = newdbId+"";
    	dao.addAccounting(costCode, businessType, elecProperty, dutyCode, projectCode, isRealSubAmmeter, shareRatio, dbcode);
    	
	 }
	//添加流程
 	
 	WorkFlowBean workFlowBean= new WorkFlowBean();
 	String flowId = req.getParameter("flowId"); 
	String auditorid = req.getParameter("auditorid");
 	String taskType="DIANBIAO,"+n+"";//区分电表流程
 	String taskId="";
 	if(type.equals("changedb")){
 		taskId=newdbId+"";
 	}
 	else{
 		taskId=id;
 	}
 	String currentNo =workFlowBean.getNextStep(flowId, "1");
 	String nextNo =workFlowBean.getNextStep(flowId, "2");
 	int ret=workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
 	
 	DataBase db = new DataBase();
 	String sql="";
 	if(type.equals("danjia")){
		 String danjia=req.getParameter("type_se"); 
		 sql="UPDATE DIANBIAO SET DANJIA_MO ='"+danjia+"', ISSP='1' ,SHZT=1, FLOWID='"+ret+"' where id="+id+"";
	 }
	 else if(type.equals("beilv")){
		 String beilv=req.getParameter("type_se");
		 sql="UPDATE DIANBIAO SET BEILV_MO='"+beilv+"', ISSP='1' ,SHZT=1, FLOWID='"+ret+"' where id="+id+"";
	 }
	 else if(type.equals("dbqyzt")){
		 String dbqyzt=req.getParameter("type_se"); 
		 sql="UPDATE DIANBIAO SET DBQYZT_MO='"+dbqyzt+"', ISSP='1' ,SHZT=1, FLOWID='"+ret+"' where id="+id+"";
		// String sql="UPDATE DIANBIAO SET SHZT=1, FLOWID='"+workId+"' where id="+taskId+""; 
	 }
	 else if(type.equals("changedb")){
		 String dbqyzt=req.getParameter("dbqyzt3"); 
		 sql="UPDATE DIANBIAO SET DBQYZT='"+dbqyzt+"' where id="+id+"";
		// String sql="UPDATE DIANBIAO SET SHZT=1, FLOWID='"+workId+"' where id="+taskId+""; 
		 String sql2="UPDATE DIANBIAO SET FLOWID='"+ret+"',SHZT=1 where id="+newdbId+"";
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql2.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
	 }
 	 
		int a =0;
		try {
			db.connectDb();
		} catch (DbException e) {
			e.printStackTrace();
		}
		try {
			a=db.update(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
		}
		if(a==1){
			msg="提交成功";
		}else{
			msg="提交失败，请联系管理员！";
		}
	 log.write(msg, accountid, req.getRemoteAddr(), "修改电表");
	 session.setAttribute("url", url);
	 session.setAttribute("msg", msg);
	 resp.sendRedirect(path + "/web/msg.jsp");
 
 }else if (action.equals("shenhe")){
	 String id = req.getParameter("id"); 
	 String n = req.getParameter("shsign"); 
	 String shishly = req.getParameter("shishly");
	 String danjia_mo = req.getParameter("danjia_mo");
	 String beilv_mo = req.getParameter("beilv_mo");
	 String dbqyzt_mo = req.getParameter("dbqyzt_mo");
	 String danjia = req.getParameter("danjia");
	 String beilv = req.getParameter("beilv");
	 String dbqyzt = req.getParameter("dbqyzt");
	 if(n.equals("0")){
		if(danjia_mo==null||danjia_mo.equals("")){
			danjia_mo=danjia;
		} 
		if(beilv_mo==null||beilv_mo.equals("")){
			beilv_mo=beilv;
		}
		if(dbqyzt_mo==null||dbqyzt_mo.equals("")){
			dbqyzt_mo=dbqyzt;
		}
		String sql="UPDATE DIANBIAO SET DANJIA='"+danjia_mo+"', BEILV='"+beilv_mo+"',DBQYZT='"+dbqyzt_mo+"',DANJIA_MO='',BEILV_MO='',DBQYZT_MO='', ISSP='2' where id="+id+"";
		 System.out.println("sql="+sql);	
		 DataBase db = new DataBase();
			int a =0;
			try {
				db.connectDb();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				a=db.update(sql.toString());
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a==1){
				msg="审核成功";
			}else{
				msg="审核失败，请联系管理员！";
			}
			log.write(msg, accountid, req.getRemoteAddr(), "电表审核");
		 session.setAttribute("url", url);
		 session.setAttribute("msg", msg);
		 resp.sendRedirect(path + "/web/msg.jsp");
	 }else if(n.equals("1")){
		 String sql="UPDATE DIANBIAO SET SHISHLY='"+shishly+"', ISSP='3' where id="+id+""; 
		 System.out.println("sql="+sql);	
		 DataBase db = new DataBase();
			int a =0;
			try {
				db.connectDb();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				a=db.update(sql.toString());
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a==1){
				msg="审核成功";
			}else{
				msg="审核失败，请联系管理员！";
			}
		 log.write(msg, accountid, req.getRemoteAddr(), "电表审核");
		 session.setAttribute("url", url);
		 session.setAttribute("msg", msg);
		 resp.sendRedirect(path + "/web/msg.jsp");
	 }
 }else if(action.equals("getYJData")){
	 DianBiaoBean bean = new DianBiaoBean();
	ArrayList list = new ArrayList();
    list  = bean.getYJData();
    resp.setContentType("text"); 
    PrintWriter out = resp.getWriter();  
    Gson gson=new Gson();
    String json=gson.toJson(list);
    out.write(json);
    out.flush();  
    out.close();
}else if("sub".equals(action)){
	//添加流程
	WorkFlowBean workFlowBean= new WorkFlowBean();
	String flowId=req.getParameter("flowId");
	String auditorid=req.getParameter("auditorid");
	String taskType="DIANBIAO,0";//区分电表流程
	String taskId=req.getParameter("taskId");;
	String currentNo =workFlowBean.getNextStep(flowId, "1");
	String nextNo =workFlowBean.getNextStep(flowId, "2");
	int workId=workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
 	if(workId>0){
 		msg="提交成功！";
 	}else{
 		msg="提交失败！请重试或与管理员联系";
 	}
 	String sql="UPDATE DIANBIAO SET SHZT=1, FLOWID='"+workId+"' where id="+taskId+""; 
	System.out.println("sql="+sql);	
	DataBase db = new DataBase();
	try {
		db.connectDb();
	} catch (DbException e) {
		e.printStackTrace();
	}try {
		db.update(sql.toString());
	} catch (DbException e) {
		e.printStackTrace();
	}
 	
 	 log.write(msg, accountid, req.getRemoteAddr(), "提交审核");
	 session.setAttribute("url", url);
	 session.setAttribute("msg", msg);
	 resp.sendRedirect(path + "/web/msg.jsp");
}else if("savePue".equals(action)){
	//添加电量
	String stationNo = req.getParameter("stationNo");
	String electricity = req.getParameter("electricity");
	
}
}

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            IOException, ServletException {
        resp.setContentType(Content_type);
        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        String url = path + "/web/sys/accountList.jsp", msg = "";
        HttpSession session = req.getSession();
        //account = (Account) session.getAttribute("account");
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        if (action.equals("vdbid")) {

            String dbid = req.getParameter("dbid");
            msg = "验证电表ID重复失败！请重试或与管理员联系！";
            DianBiaoBean bean = new DianBiaoBean();
            String retsign = bean.vdbid(dbid);
            if (retsign.equals("ok")) {
                msg = "ok";
            }else{
                msg=retsign;
            }

            log.write(msg, accountid, req.getRemoteAddr(), "电表ID重复");
            out.println(msg);
            //out.println("</response>");
            out.close();

        }else if (action.equals("vmdbid")) {

            String dbid = req.getParameter("dbid");
            String id = req.getParameter("id");
            msg = "验证电表ID重复失败！请重试或与管理员联系！";
            DianBiaoBean bean = new DianBiaoBean();
            String retsign = bean.vdbid(dbid,id);
            if (retsign.equals("ok")) {
                msg = "ok";
            }else{
                msg=retsign;
            }

            log.write(msg, accountid, req.getRemoteAddr(), "电表ID重复");
            out.println(msg);
            //out.println("</response>");
            out.close();

        }



    }
  //double类型-加减乘除高精度运算
    public static  double updouble(String v1){  
    return Double.valueOf(v1).doubleValue();
    }
    public static  double add(double v1,double v2){  

  	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
  	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
  	        return b1.add(b2).doubleValue();  
  	    }  

  	// 进行减法运算
	
		public static double subtract(double v1,double v2){
  	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
  	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
  	        return b1.subtract(b2).doubleValue();  
  	    }

  	// 进行加法运算

  	    public static double mul(double d1, double d2){        // 进行乘法运算
  	         BigDecimal b1 = new BigDecimal(d1);
  	         BigDecimal b2 = new BigDecimal(d2);
  	        return b1.multiply(b2).doubleValue();
  	     }

  	// 进行除法运算

  	    public static  double div(double d1,double d2,int len) {// 进行除法运算
  	         BigDecimal b1 = new BigDecimal(d1);
  	         BigDecimal b2 = new BigDecimal(d2);
  	        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
  	     }

  	// 进行四舍五入操作

  	    public  double round(double d,int len) {     // 进行四舍五入操作
  	         BigDecimal b1 = new BigDecimal(d);
  	         BigDecimal b2 = new BigDecimal(1);
  	        // 任何一个数字除以1都是原数字
  	        // ROUND_HALF_UP是BigDecimal的一个常量，表示进行四舍五入的操作
  	        return b1.divide(b2, len,BigDecimal.ROUND_HALF_UP).doubleValue();
  	    }

}
