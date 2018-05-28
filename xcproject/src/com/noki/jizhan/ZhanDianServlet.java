package com.noki.jizhan;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.noki.common.BZItem;
import com.noki.common.BZLineItem;
import com.noki.common.CodeItem;
import com.noki.common.MenuItem;
import com.noki.common.PayMentItem;
import com.noki.common.RelateSupplier;
import com.noki.common.RequestMessage;
import com.noki.common.RequestMessageCode;
import com.noki.common.RequestMessageMenu;
import com.noki.common.ResponseMessage;
import com.noki.common.ResponseMessageCode;
import com.noki.common.ResponseMessageMenu;
import com.noki.common.util.BZServiceImpl;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.model.AcSoloBean;
import com.noki.jizhan.model.DianbiaoBean;
import com.noki.jizhan.model.ElecBean;
import com.noki.jizhan.model.GongysAccountBean;
import com.noki.jizhan.model.GongysBean;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.mobi.common.CommonBean;
import com.noki.mobi.workflow.javabean.WorkFlowBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.ptac.app.weixin.util.GroupMessage;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class ZhanDianServlet extends HttpServlet {
    public ZhanDianServlet() {
    }

    private static final String Content_type = "text/html;charset=UTF-8";
   // private static final String jiekouUrl = "http://137.0.30.163:9898/mssproxy";//测试环境
    private static final String jiekouUrl = "http://137.0.28.162:8090/mssproxy";//正式环境
    

    @SuppressWarnings({ "unused", "static-access" })
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        
        req.setCharacterEncoding("utf-8");//设置输入编码格式。
        resp.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
        
        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        HttpSession session = req.getSession();
        String url = path + "/web/jizhan/jizhanlist.jsp", msg = "";
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");
        account=(Account)session.getAttribute("account");

        String action = req.getParameter("action");
        if (action.equals("addZhanDian")) {
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "添加站点失败！请重试或与管理员联系！";
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx");
            String jzname = req.getParameter("jzname");
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign");
            String dianfei = req.getParameter("danjia");
            String zdcode = req.getParameter("zdcode");
            String jzxlx = req.getParameter("jzxlx");
            String jflx = req.getParameter("jflx");
            String jrwtype=req.getParameter("jrwtype");
            String stationtype=req.getParameter("stationtype");
            String kongtiao=req.getParameter("kongtiao") != null ? req.getParameter("kongtiao") : "";
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "";
            String entrypensonnel=req.getParameter("accountname");
            String signtypenum=req.getParameter("signtypenum");
            String entrytime=mat.format(new Date());
            if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }
            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "否";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String memo = req.getParameter("memo");
            String fzr = req.getParameter("fzr");
            KZForm kzform = new KZForm();
            kzform.setCkkd(!req.getParameter("ckkd").equals("")
                    && req.getParameter("ckkd") != null ? req
                    .getParameter("ckkd") : "");
            if(req.getParameter("jztype").equals("zdlx01")){
                kzform.setYsymj(req.getParameter("idcysymj") != null ? req.getParameter("idcysymj") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setYsymj(req.getParameter("ysymj") != null ? req.getParameter("ysymj") : "");
            }
            
            kzform.setJggs(!req.getParameter("jggs").equals("")&& req.getParameter("jggs") != null ? req.getParameter("jggs") : "1");
            kzform.setYsygs(!req.getParameter("ysygs").equals("")&& req.getParameter("ysygs") != null ? req.getParameter("ysygs") : "0");
            kzform.setJfgd(!req.getParameter("jfgd").equals("")&& req.getParameter("jfgd") != null ? req.getParameter("jfgd") : "");
            kzform.setSdwd(!req.getParameter("sdwd").equals("")&& req.getParameter("sdwd") != null ? req.getParameter("sdwd") : "");
            kzform.setLyfs(!req.getParameter("lyfs").equals("")&& req.getParameter("lyfs") != null ? req.getParameter("lyfs") : "0");
            kzform.setSffs(!req.getParameter("sffs").equals("")&& req.getParameter("sffs") != null ? req.getParameter("sffs") : "0");
            kzform.setGzqk(!req.getParameter("gzqk").equals("")&& req.getParameter("gzqk") != null ? req.getParameter("gzqk") : "");
            kzform.setNhzb(!req.getParameter("nhzb").equals("")&& req.getParameter("nhzb") != null ? req.getParameter("nhzb") : "");
            kzform.setZpsl(!req.getParameter("zpsl").equals("")&& req.getParameter("zpsl") != null ? req.getParameter("zpsl") : "1");
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setZgry(!req.getParameter("glzgry").equals("")&& req.getParameter("glzgry") != null ? req.getParameter("glzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setZgry(!req.getParameter("qdzgry").equals("")&& req.getParameter("qdzgry") != null ? req.getParameter("qdzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setZgry(!req.getParameter("zgry").equals("")&& req.getParameter("zgry") != null ? req.getParameter("zgry") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setKtsl(!req.getParameter("glktsl").equals("")&& req.getParameter("glktsl") != null ? req.getParameter("glktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setKtsl(!req.getParameter("qdktsl").equals("")&& req.getParameter("qdktsl") != null ? req.getParameter("qdktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setKtsl(!req.getParameter("ktsl").equals("")&& req.getParameter("ktsl") != null ? req.getParameter("ktsl") : "");
            }           
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setPcsl(!req.getParameter("glpcsl").equals("")&& req.getParameter("glpcsl") != null ? req.getParameter("glpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setPcsl(!req.getParameter("qdpcsl").equals("")&& req.getParameter("qdpcsl") != null ? req.getParameter("qdpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setPcsl(!req.getParameter("pcsl").equals("")&& req.getParameter("pcsl") != null ? req.getParameter("pcsl") : "");
            }
                        
            kzform.setRll(!req.getParameter("rll").equals("")&& req.getParameter("rll") != null ? req.getParameter("rll") : "");
            kzform.setLjzs(!req.getParameter("ljzs").equals("")&& req.getParameter("ljzs") != null ? req.getParameter("ljzs") : "1");
            kzform.setTxj(!req.getParameter("txj").equals("")&& req.getParameter("txj") != null ? req.getParameter("txj") : "");
            kzform.setUgs(!req.getParameter("ugs").equals("")&& req.getParameter("ugs")!=null? req.getParameter("ugs"):"");
            kzform.setYsyugs(!req.getParameter("ysyugs").equals("")&& req.getParameter("ysyugs")!=null? req.getParameter("ysyugs"):"");
            kzform.setJnjslx(!req.getParameter("jnjslx").equals("")&& req.getParameter("jnjslx")!=null? req.getParameter("jnjslx"):"");
            kzform.setYdlx(!req.getParameter("ydlx").equals("")&& req.getParameter("ydlx")!=null? req.getParameter("ydlx"):"");
            
            String PUE = req.getParameter("PUE") != null ? req.getParameter("PUE") : "0";
            String zzjgbm = req.getParameter("zzjgbm") != null ? req.getParameter("zzjgbm") : "";
            String xuni = req.getParameter("xuni") != null ? req.getParameter("xuni") : "0";
            if (xuni.equals("on")) {
                xuni = "1";
            } else {
                xuni = "0";
            }
            String czzd = req.getParameter("czzd") != null ? req.getParameter("czzd") : "";
            String gczt = req.getParameter("gczt") != null ? req.getParameter("gczt") : "";
            String gcxm = req.getParameter("gcxm") != null ? req.getParameter("gcxm") : "";
            String zdcq = req.getParameter("zdcq") != null ? req.getParameter("zdcq") : "";
            String zdcb = req.getParameter("zdcb") != null ? req.getParameter("zdcb") : "0";
            String zlfh = req.getParameter("zlfh") != null ? req.getParameter("zlfh") : "0";
            String jnjsms = req.getParameter("jnjsms") != null ? req.getParameter("jnjsms") : "";
            String czzdid = req.getParameter("czzdid") != null ? req.getParameter("czzdid") : "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }
            String nhjcdy = req.getParameter("nhjcdy");
            String ERPbh = req.getParameter("ERPbh") != null ? req.getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req.getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req.getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req.getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req.getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req.getParameter("latitude") : "";
            String caiji = req.getParameter("caiji");

            // }
            //因为dopst长度不够所以注释了.
            /*JiZhanBean bean = new JiZhanBean();
            int retsign = bean.addData(shi, xian, xiaoqu, jztype, jzproperty,
                    yflx, jflx, jzname, bieming, address, bgsign, jnglmk, gdfs,
                    area, memo, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform, PUE, zzjgbm, xuni, czzd, gczt, gcxm,
                    zdcq, zdcb, zlfh, jnjsms, czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, longitude, latitude, caiji,jrwtype,kongtiao,gsf,entrypensonnel,entrytime,stationtype,signtypenum);

            if (retsign == 1) {
                msg = "添加站点成功！";
            } else if (retsign == 2) {
                msg = "添加失败！站点代码重复！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");*/

        } else if (action.equals("modifyZhanDian")) {
           /* msg = "修改站点失败！请重试或与管理员联系！";
            String id = req.getParameter("id");
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx");
            String jzname = req.getParameter("jzname");
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign");
            String dianfei = req.getParameter("dianfei");
            String zdcode = req.getParameter("zdcode");
            String stationtype=req.getParameter("stationtype");
            String signtypenum=req.getParameter("signtypenum");//标杆类型编号
            String kongtiao=req.getParameter("kongtiao")!= null ? req
                    .getParameter("kongtiao") : "";
            String gsf=req.getParameter("gsf")!= null ? req
                    .getParameter("gsf") : "";

            if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }

            String jnglmk = req.getParameter("jnglmk") != null ? req
                    .getParameter("jnglmk") : "否";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String memo = req.getParameter("memo");
            String fzr = req.getParameter("fzr");
            KZForm kzform = new KZForm();
            // if(jztype.equals("zdlx01")){//IDC机房
            String kzid = req.getParameter("kzid");
            kzform.setCkkd(req.getParameter("ckkd") != null ? req
                    .getParameter("ckkd") : "");
            if(req.getParameter("jztype").equals("zdlx01")){
                kzform.setYsymj(req.getParameter("idcysymj") != null ? req
                        .getParameter("idcysymj") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setYsymj(req.getParameter("ysymj") != null ? req
                        .getParameter("ysymj") : "");
            }
            
            kzform.setJggs(req.getParameter("jggs") != null ? req
                    .getParameter("jggs") : "");
            kzform.setYsygs(req.getParameter("ysygs") != null ? req
                    .getParameter("ysygs") : "");
            kzform.setJfgd(req.getParameter("jfgd") != null ? req
                    .getParameter("jfgd") : "");
            kzform.setSdwd(req.getParameter("sdwd") != null ? req
                    .getParameter("sdwd") : "");
            kzform.setSffs(req.getParameter("sffs") != null ? req
                    .getParameter("sffs") : "0");
            kzform.setLyfs(req.getParameter("lyfs") != null ? req
                    .getParameter("lyfs") : "0");
            kzform.setGzqk(req.getParameter("gzqk") != null ? req
                    .getParameter("gzqk") : "");
            kzform.setNhzb(req.getParameter("nhzb") != null ? req
                    .getParameter("nhzb") : "");
            kzform.setZpsl(req.getParameter("zpsl") != null ? req
                    .getParameter("zpsl") : "1");
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setZgry(req.getParameter("glzgry") != null ? req
                        .getParameter("glzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setZgry(req.getParameter("qdzgry") != null ? req
                        .getParameter("qdzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setZgry(req.getParameter("zgry") != null ? req
                        .getParameter("zgry") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setKtsl(req.getParameter("glktsl") != null ? req
                        .getParameter("glktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setKtsl(req.getParameter("qdktsl") != null ? req
                        .getParameter("qdktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setKtsl(req.getParameter("ktsl") != null ? req
                        .getParameter("ktsl") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setPcsl(req.getParameter("glpcsl") != null ? req
                        .getParameter("glpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setPcsl(req.getParameter("qdpcsl") != null ? req
                        .getParameter("qdpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setPcsl(req.getParameter("pcsl") != null ? req
                        .getParameter("pcsl") : "");
            }
            kzform.setRll(req.getParameter("rll") != null ? req
                    .getParameter("rll") : "");
            kzform.setLjzs(req.getParameter("ljzs") != null ? req
                    .getParameter("ljzs") : "1");
            kzform.setTxj(req.getParameter("txj") != null ? req
                    .getParameter("txj") : "");
            kzform.setUgs(req.getParameter("ugs") != null ? req
                    .getParameter("ugs") : "");
            kzform.setYsyugs(req.getParameter("ysyugs") != null ? req
                    .getParameter("ysyugs") : "");
            kzform.setJnjslx(req.getParameter("jnjslx") != null ? req
                    .getParameter("jnjslx") : "");
            kzform.setYdlx(req.getParameter("ydlx")!=null ? req
                    .getParameter("ydlx"):"");
            
            String PUE = req.getParameter("PUE") != null ? req
                    .getParameter("PUE") : "0";
            String zzjgbm = req.getParameter("zzjgbm") != null ? req
                    .getParameter("zzjgbm") : "";
            String xuni = req.getParameter("xuni") != null ? req
                    .getParameter("xuni") : "0";
            if (xuni.equals("on")) {
                xuni = "1";
            } else {
                xuni = "0";
            }
            String czzd = req.getParameter("czzd") != null ? req
                    .getParameter("czzd") : "";
            String gczt = req.getParameter("gczt") != null ? req
                    .getParameter("gczt") : "";
            String gcxm = req.getParameter("gcxm") != null ? req
                    .getParameter("gcxm") : "";
            String zdcq = req.getParameter("zdcq") != null ? req
                    .getParameter("zdcq") : "";
            String zdcb = req.getParameter("zdcb") != null ? req
                    .getParameter("zdcb") : "0";
            String zlfh = req.getParameter("zlfh") != null ? req
                    .getParameter("zlfh") : "0";
            String jnjsms = req.getParameter("jnjsms") != null ? req
                    .getParameter("jnjsms") : "";
            String czzdid = req.getParameter("czzdid") != null ? req
                    .getParameter("czzdid") : "0";
            if (czzdid.equals("null")) {
                czzdid = "";
            }
            String nhjcdy = req.getParameter("nhjcdy")!= null ? req
                    .getParameter("nhjcdy") : "";
            String ERPbh = req.getParameter("ERPbh") != null ? req
                    .getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req
                    .getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req
                    .getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req
                    .getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req
                    .getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req
                    .getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req
                    .getParameter("latitude") : "";
            String caiji = req.getParameter("caiji");
            String jzxlx = req.getParameter("jzxlx") != null ? req
                    .getParameter("jzxlx") : "";
            String jflx = req.getParameter("jflx") != null ? req
                    .getParameter("jflx") : "";
            String jrwtype = req.getParameter("jrwtype") != null ? req
                    .getParameter("jrwtype") : "";
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.midifyData(shi, xian, xiaoqu, jztype,
                    jzproperty, yflx, jzname, bieming, address, bgsign, jnglmk,
                    gdfs, area, memo, fzr, accountSheng, dianfei, zdcode,
                    jzxlx, jflx, id, accountid, kzform, kzid, PUE, zzjgbm,
                    xuni, czzd, gczt, gcxm, zdcq, zdcb, zlfh, jnjsms, czzdid,
                    nhjcdy, ERPbh, dhID, zhwgID, dzywID, longitude, latitude,
                    caiji,edhdl,jrwtype,kongtiao,gsf,stationtype,signtypenum);
            if (retsign == 1) {
                msg = "修改站点成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
*/
        }else if(action.equals("modifySitedf")){
            url = path + "/web/jizhan/sitemanagedf.jsp";
            String id=req.getParameter("id");
            String dfwhszj=req.getParameter("dfwhszj")!=null?req.getParameter("dfwhszj"):"";//上月垫付未回收资金(元)
            String dfwhszj_byhs=req.getParameter("dfwhszj_byhs")!=null?req.getParameter("dfwhszj_byhs"):"";//上月垫付未回收资金，本月已回款(元)
            String dhszj=req.getParameter("dhszj")!=null?req.getParameter("dhszj"):"";//待回收资金(元)
            String dhszj_yyshd=req.getParameter("dhszj_yyshd")!=null?req.getParameter("dhszj_yyshd"):"";//待回收资金：已和运营商完成明细核对
            String dhszj_ykp=req.getParameter("dhszj_ykp")!=null?req.getParameter("dhszj_ykp"):"";//待回收资金：已经开票
            String dfhs_yys=req.getParameter("dfhs_yys")!=null?req.getParameter("dfhs_yys"):"";//运营商
            String dfhs_hdzb=req.getParameter("dfhs_hdzb")!=null?req.getParameter("dfhs_hdzb"):"";//已核对占比：【待回收资金：已和运营商完成明细核对】/【待回收资金】
            String dfhs_kpzb=req.getParameter("dfhs_kpzb")!=null?req.getParameter("dfhs_kpzb"):"";//已开票占比：【待回收资金：已开票】/【待回收资金】
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String shi=req.getParameter("shi")!=null?req.getParameter("shi"):"";
            DianbiaoBean db=new DianbiaoBean();
            
            db.setId(id);
            db.setDfwhszj(dfwhszj);
            db.setDfwhszj_byhs(dfwhszj_byhs);
            db.setDhszj(dhszj);
            db.setDhszj_yyshd(dhszj_yyshd);
            db.setDhszj_ykp(dhszj_ykp);
            db.setDfhs_yys(dfhs_yys);
            db.setDfhs_hdzb(dfhs_hdzb);
            db.setDfhs_kpzb(dfhs_kpzb);
            db.setEntrypensonnel(entrypensonnel);
            db.setShi(shi);
            
            SiteManage bean = new SiteManage();
            
            int  retsign=bean.modifydadf(db);
            if (retsign == 1) {
                msg = "修改电费垫付成功！！！";
            } else if (retsign == 2) {
                msg = "修改失败！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改电费垫付");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if (action.equals("del")) {
            url = path + "/web/jizhan/jizhanlist.jsp";
            String id = req.getParameter("id");
            msg = "删除站点失败！请重试或与管理员联系！";
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "删除站点成功！";
            } else if (retsign == 2) {
                msg = "有关联的电表信息，不允许删除站点";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("zddfinfo")) {
            url = path + "/web/jizhan/sitemanage.jsp";
            msg = "修改站点电费表信息失败！请重试或与管理员联系！";
            ZddfInfoForm form = new ZddfInfoForm();
            //form.setYdfs(req.getParameter("ydfs"));
            form.setGdfs(req.getParameter("gdfs"));
            form.setFkzq(req.getParameter("fkzq"));
            form.setDfdj(req.getParameter("dfdj"));
            //form.setZflx(req.getParameter("zflx"));
            form.setFkfs(req.getParameter("fkfs"));
            form.setSkdwmc(req.getParameter("skdwmc"));
            form.setSkdwzh(req.getParameter("skdwzh"));
            form.setSkdwyh(req.getParameter("skdwyh"));
            form.setZbdyhh(req.getParameter("zbdyhh"));
            form.setScjftime(req.getParameter("scjftime"));
            form.setMemo(req.getParameter("memo"));
            form.setZdid(req.getParameter("zdid"));
            form.setId(req.getParameter("infoid"));
            //新加agreementid,signdate, origindate,stopdate,powerunit,unitlinkman,unitphone,watchcost 
            form.setAgreementid(req.getParameter("agreementid"));
            form.setSigndate(req.getParameter("signdate"));
            form.setOrigindate(req.getParameter("origindate"));
            form.setStopdate(req.getParameter("stopdate"));
            form.setPowerunit(req.getParameter("powerunit"));
            form.setUnitlinkman(req.getParameter("unitlinkman"));
            form.setUnitphone(req.getParameter("unitphone"));
            form.setWatchcost(req.getParameter("watchcost"));
                
            ZddfInfo bean = new ZddfInfo();
            int retsign = bean.optionData(form);
            if (retsign == 1) {
                msg = "修改站点电费表信息成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改站点电费表信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("baocunbg")) {
            url = path + "/web/jizhan/biaoganjizhan.jsp";
            msg = "保存标杆基站失败，请重试或与管理员联系";
            String[] id = req.getParameterValues("itemSelected");
            String bgidstr = req.getParameter("bgidstr");
            String allids = req.getParameter("allids");
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.bgjz(id, accountid, allids);
            if (retsign == 1) {
                // msg = "保存标杆基站成功！";
                JzCommBean jz = new JzCommBean();
                msg = jz.modifybgStr(id, bgidstr);
            }
            log.write(msg, accountid, req.getRemoteAddr(), "标杆基站");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("zlinfo")) {
            url = path + "/web/jizhan/sitemanage.jsp";
            msg = "修改站点租赁信息失败！请重试或与管理员联系！";
            ZLInfoForm form = new ZLInfoForm();
            form.setHtid(req.getParameter("htid") != null ? req
                    .getParameter("htid") : "");
            form.setHtlx(req.getParameter("htlx"));
            form.setJzdz(req.getParameter("jzdz") != null ? req
                    .getParameter("jzdz") : "");
            form.setFwmj(req.getParameter("fwmj") != null ? req
                    .getParameter("fwmj") : "");
            form.setLouc(req.getParameter("louc") != null ? req
                    .getParameter("louc") : "");
            form.setQydw(req.getParameter("qydw"));
            form.setFkfs(req.getParameter("fkfs"));
            form.setSkdwmc(req.getParameter("skdwmc"));
            form.setSkdwzh(req.getParameter("skdwzh"));
            form.setSkdwyh(req.getParameter("skdwyh"));
            form.setLtqyr(req.getParameter("ltqyr"));
            form.setHtlxr(req.getParameter("htlxr"));
            form.setHtlxtel(req.getParameter("htlxtel"));
            form.setFzjsjine(req.getParameter("fzjsjine"));
            String tt = req.getParameter("fzsfkp") != null ? req
                    .getParameter("fzsfkp") : "0";
            if (tt.equals("on")) {
                tt = "1";
            }
            form.setFzsfkp(tt);
            form.setFzzzl(req.getParameter("fzzzl"));
            form.setZhjntime(req.getParameter("zhjntime") != null ? req
                    .getParameter("zhjntime") : "");
            form.setSxtime(req.getParameter("sxtime") != null ? req
                    .getParameter("sxtime") : "");
            form.setXqtime(req.getParameter("xqtime"));
            form.setOfftime(req.getParameter("offtime") != null ? req
                    .getParameter("offtime") : "");
            form.setXqyjz(req.getParameter("xqyjz"));
            form.setFzyjz(req.getParameter("fzyjz"));
            form.setMemo(req.getParameter("memo"));
            form.setZdid(req.getParameter("zdid"));
            form.setQstime(req.getParameter("qstime") != null ? req
                    .getParameter("qstime") : "");
            form.setId(req.getParameter("id"));
            ;

           /* ZdZlInfo bean = new ZdZlInfo();
            int retsign = bean.optionData(form);
            if (retsign == 1) {
                msg = "修改站点租赁信息成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "站点租赁信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");*/

        } else if (action.equals("shenhe")) {

            msg = "审核站点失败！";
            url = path + "/web/sdttWeb/jizan/zhandianshhe.jsp";
            String[] ids = req.getParameterValues("itemSelected");
            String shsign = req.getParameter("shsign");
            String id = req.getParameter("id");
            //人工站点审核人、人工站点审核时间
            String entrypensonnelrg=req.getParameter("accountname");
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String entrytimerg=mat.format(new Date());
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.SHData(id, shsign,entrypensonnelrg,entrytimerg);
            if (retsign == 1) {
                msg = "审核站点成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "审核站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if(action.equals("addSite")){ //添加站点  在用
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/jizan/sitemanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "添加站点失败！请重试或与管理员联系！";
            String shi = req.getParameter("shi");
            String zjcode = req.getParameter("zjcode")!= null ? req.getParameter("zjcode") : "";;
            String zzlx = req.getParameter("zzlx")!= null ? req.getParameter("zzlx") : "";
            String zdcq = req.getParameter("zdcq")!= null ? req.getParameter("zdcq") : "";
            String wlzdbm = req.getParameter("wlzdbm")!= null ? req.getParameter("wlzdbm") : "";
            String ltqx = req.getParameter("ltqx")!= null ? req.getParameter("ltqx") : "";
            String ydqx = req.getParameter("ydqx")!= null ? req.getParameter("ydqx") : "";
            String xtid = req.getParameter("xtid")!= null ? req.getParameter("xtid") : "";
            String jingdu = req.getParameter("jingdu")!= null ? req.getParameter("jingdu") : "";
            String weidu = req.getParameter("weidu")!= null ? req.getParameter("weidu") : "";
            String phone = req.getParameter("phone")!= null ? req.getParameter("phone") : "";
            String gdfname = req.getParameter("gdfname")!= null ? req.getParameter("gdfname") : "";
            String bumen = req.getParameter("bumen")!= null ? req.getParameter("bumen") : "";
            String xian = req.getParameter("xian")!= null ? req.getParameter("xian") : "";
            String xiaoqu = req.getParameter("xiaoqu")!= null ? req.getParameter("xiaoqu") : "";
            String jztype = "";//集团报表类型
            String jzproperty = req.getParameter("jzproperty");//站点属性
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//用房类型
            String jzname = req.getParameter("jzname");//站点名称
            String bieming ="";//别名暂停使用
            String address = req.getParameter("address");//地址
            String bgsign ="1";//是否标杆暂停使用  默认都是标杆
            String dianfei = req.getParameter("danjia");//单价
            String zdcode ="";//暂停使用
            String jzxlx = req.getParameter("jzxlx");//基站类型 现在改为小区网络箱个数
            String jflx = "";//局房类型 暂停使用
            String jrwtype=req.getParameter("jrwtype");//接入网类型 暂停使用
            String stationtype=req.getParameter("stationtype");//站点类型
            //String signtypenum=req.getParameter("signtypenum");//标杆类型标号
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String jlfh=req.getParameter("jlfh");//交流负荷
            String sydate = "";//投入使用时间 暂停使用
            String qyzt ="1";//站点启用状态 暂停使用 默认启用
            String gsf="0";//归属方 暂停使用 
            //站点附属信息
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "";//地域类型
            String g2="0";//2g设备 暂停使用
            String g3="0";//3g设备 暂停使用
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//宽带设备
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//语音设备
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//载频数量暂停使用
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//载扇数量暂停使用
            String kdsbsl="";//宽带设备数量暂停使用
            String yysbsl="";//语音设备数量暂停使用
            String kt1= "0";//空调1暂停使用
            String kt2= "0";//空调2暂停使用
            String kts="0";//空调数暂停使用
            String ktzgl=req.getParameter("ktzgl") != null ? req.getParameter("ktzgl") : "0";//空调总功率
            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//直供电
            
            String changjia = "";//厂家暂停使用
            String jzlx  = "";//基站类型暂停使用jzlx2
            String shebei = "";//设备编码暂停使用
            String bbu ="0";//bbu数量暂停使用
            String rru ="0";//rru数量暂停使用
            String ydshebei ="0";//移动设备暂停使用
            String gxgwsl = req.getParameter("gxgwsl") != null ? req.getParameter("gxgwsl") :"0";//固移 共享固网设备数量
            String twgx = "";//他网共享暂停使用
            String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//部门
            String g2xqs = "0";//2G小区数暂停使用
            String g3sqsl ="0";//3G扇区数量暂停使用
            String ydgxsbsl = "0";//移动共享设备数量 暂停使用
            String dxgxsbsl ="0";//电信共享设备数量暂停使用
            String ysjts="0";//饮水机台数暂停使用
            String wjts="0";//微机台数暂停使用
            String yybgkt="0";//营业办公空调暂停使用
            //------------------空调   jfsckt=1+1.5+2+3+5+10+精密空调数量
            String jfsckt=req.getParameter("jfsckt")!=null?req.getParameter("jfsckt"):"0";//机房生产空调数量
            
            String ktyps=req.getParameter("ktyps")!=null?req.getParameter("ktyps"):"0";//空调1匹数量
            String kteps=req.getParameter("kteps")!=null?req.getParameter("kteps"):"0";//空调1.5匹数量
            String ktps=req.getParameter("ktps")!=null?req.getParameter("ktps"):"0";//空调2匹数量
            String ktsps=req.getParameter("ktsps")!=null?req.getParameter("ktsps"):"0";//空调3匹数量
            String ktwps=req.getParameter("ktwps")!=null?req.getParameter("ktwps"):"0";//空调5匹数量
            String ktships=req.getParameter("ktships")!=null?req.getParameter("ktships"):"0";//空调10匹数量
            String ktjmps=req.getParameter("ktjmps")!=null?req.getParameter("ktjmps"):"0";//精密空调数量
          //----------------空调总功率
            String k1=req.getParameter("ktypzgl")!=null?req.getParameter("ktypzgl"):"0";
            String k2=req.getParameter("ktywzgl")!=null?req.getParameter("ktywzgl"):"0";
            String k3=req.getParameter("ktepzgl")!=null?req.getParameter("ktepzgl"):"0";
            String k4=req.getParameter("ktspzgl")!=null?req.getParameter("ktspzgl"):"0";
            String k5=req.getParameter("ktwpzgl")!=null?req.getParameter("ktwpzgl"):"0";
            String k6=req.getParameter("ktshipzgl")!=null?req.getParameter("ktshipzgl"):"0";
            String k7=req.getParameter("ktjmzgl")!=null?req.getParameter("ktjmzgl"):"0";
           //电表分摊信息-------
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "0";//生成%  
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//办公%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//营业%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//信息化支撑%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//建设投资%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//代垫电费%
            String entrytime=mat.format(new Date());
        /*  if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }*/
            String jnglmk ="否";//节能降耗管理模块 是否有 暂停使用
            String gdfs = req.getParameter("gdfs");//供电方式
            String area = req.getParameter("area");//地址
            String fzr = req.getParameter("fuzeren");//负责人
            //String zdcq = req.getParameter("zdcq") != null ? req.getParameter("zdcq") : "0";//站点产权
            String zlfh = req.getParameter("zlfh") != null ? req.getParameter("zlfh") : "0";//直流负荷
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";//额定耗电量
            
            SiteFieldBean kzform = new SiteFieldBean();
            kzform.setCkkd("");//暂停使用
            kzform.setYsymj("");
            kzform.setJggs("");
            kzform.setYsygs("");
            kzform.setJfgd("");
            kzform.setSdwd("");
            kzform.setLyfs("");
            kzform.setSffs("");
            kzform.setGzqk("");
            kzform.setNhzb("");
            kzform.setZpsl("0");
            kzform.setZgry("");
            kzform.setKtsl("");
            kzform.setPcsl("");
            kzform.setRll("");
            kzform.setLjzs("1");
            kzform.setTxj("");
            kzform.setUgs("");
            kzform.setYsyugs("");
            kzform.setJnjslx("");
            kzform.setYdlx("");
            //-----------空调x匹数量
            kzform.setKtsps(ktsps);
            kzform.setKtwps(ktwps);
            kzform.setKtships(ktships);
            kzform.setKtjmps(ktjmps);
           //------------
            kzform.setKtypzgl(k1);
            kzform.setKtywzgl(k2);
            kzform.setKtepzgl(k3);
            kzform.setKtspzgl(k4);
            kzform.setKtwpzgl(k5);
            kzform.setKtshipzgl(k6);
            kzform.setKtjmzgl(k7);
            
            String zzjgbm ="";
            String gczt ="";
            String gcxm ="";
            String zdcb = "0";
            String czzdid = "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }
            String nhjcdy ="";
            String ERPbh ="";
            String dhID ="";
            String zhwgID ="";
            String dzywID ="";
            
            String longitude ="";
            String latitude ="";
            
            
            
            
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//电费支付类型
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "";//付款方式
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//付款周期
            String zbdyhh =req.getParameter("zbdyhh")!=null?req.getParameter("zbdyhh"):"";//电表自报电用户号暂停使用
            String watchcost ="";//套表计费暂停使用
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//倍率
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//线损类型
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//线损值
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//电表ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//管理电表
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//电表用途   
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//初始读数
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//初始使用时间
            String lyjhjgs = "0";//楼宇交换机个数暂停使用
            String gxxx =req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "";//共享信息暂停使用
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//原电表id
            String jskssj= "";//建设开始时间暂停使用
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//建设结束时间
            String xmh ="";//项目号暂停使用
            String ygd = "0";//远供电暂停使用
            String ysd= "0";//远受电暂停使用
            
            String zybyqlx= req.getParameter("zybyqlx") != null ? req.getParameter("zybyqlx") : "";//自有变压器类型
            String bsdl= req.getParameter("bsdl") != null ? req.getParameter("bsdl") : "0";//变损电量
            
            
            //2017年11月20日 xiaokang modify
            String LSCID = (req.getParameter("LSCID")!= null && !"".equals(req.getParameter("LSCID"))) ? req.getParameter("LSCID") : "0";
            String AREA_ID = (req.getParameter("shi")!= null && !"".equals(req.getParameter("shi"))) ? req.getParameter("shi") : "0";
            String DISTRICT_ID = (req.getParameter("xian")!= null && !"".equals(req.getParameter("xian"))) ? req.getParameter("xian") : "0";
            String COUNTRY_ID = (req.getParameter("xiaoqu")!= null && !"".equals(req.getParameter("xiaoqu"))) ? req.getParameter("xiaoqu") : "0";
            String FULL_STATION_CODE = req.getParameter("FULL_STATION_CODE")!= null ? req.getParameter("FULL_STATION_CODE") : "";
            String STATION_NAME = req.getParameter("STATION_NAME")!= null ? req.getParameter("STATION_NAME") : "";
            String STATION_CODE = req.getParameter("STATION_CODE")!= null ? req.getParameter("STATION_CODE") : "";
            String SERVICE_ID = req.getParameter("SERVICE_ID")!= null ? req.getParameter("SERVICE_ID") : "";
            String RANK_ID = req.getParameter("RANK_ID")!= null ? req.getParameter("RANK_ID") : "";
            String PROPERTY_ID = (req.getParameter("PROPERTY_ID")!= null && !"".equals(req.getParameter("PROPERTY_ID"))) ? req.getParameter("PROPERTY_ID") : "0";
            String MAINTAIN_ID = (req.getParameter("MAINTAIN_ID")!= null && !"".equals(req.getParameter("MAINTAIN_ID"))) ? req.getParameter("MAINTAIN_ID") : "0";
            String ADDRESS = req.getParameter("ADDRESS")!= null  ? req.getParameter("ADDRESS") : "";
            String LONGITUDE = (req.getParameter("LONGITUDE")!= null && !"".equals(req.getParameter("LONGITUDE"))) ? req.getParameter("LONGITUDE") : "0";
            String LATITUDE = (req.getParameter("LATITUDE")!= null && !"".equals(req.getParameter("LATITUDE"))) ? req.getParameter("LATITUDE") : "0";
            String COVERED_AREA = (req.getParameter("COVERED_AREA")!= null && !"".equals(req.getParameter("COVERED_AREA"))) ? req.getParameter("COVERED_AREA") : "0";
            String OCCUPIED_AREA = (req.getParameter("OCCUPIED_AREA")!= null && !"".equals(req.getParameter("OCCUPIED_AREA"))) ? req.getParameter("OCCUPIED_AREA") : "0";
            String RENT = (req.getParameter("RENT")!= null && !"".equals(req.getParameter("RENT"))) ? req.getParameter("RENT") : "0";
            String HIRE_TIME = req.getParameter("HIRE_TIME")!= null ? req.getParameter("HIRE_TIME") : null;
            String PERIOD = (req.getParameter("PERIOD")!= null && !"".equals(req.getParameter("PERIOD"))) ? req.getParameter("PERIOD") : "0";
            String RENT_ID = (req.getParameter("RENT_ID")!= null && !"".equals(req.getParameter("RENT_ID"))) ? req.getParameter("RENT_ID") : "0";
            String LEASE_ID = (req.getParameter("LEASE_ID")!= null && !"".equals(req.getParameter("LEASE_ID"))) ? req.getParameter("LEASE_ID") : "0";
            String LATEST_DATE = req.getParameter("LATEST_DATE")!= null ? req.getParameter("LATEST_DATE") : "";
            String POWER_ID = (req.getParameter("POWER_ID")!= null && !"".equals(req.getParameter("POWER_ID"))) ? req.getParameter("POWER_ID") : "0";
            String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
            String ELECTRICITYRATIO = (req.getParameter("ELECTRICITYRATIO")!= null && !"".equals(req.getParameter("ELECTRICITYRATIO"))) ? req.getParameter("ELECTRICITYRATIO") : "0";
            String ISSUPERVISOR = req.getParameter("ISSUPERVISOR")!= null  ? req.getParameter("ISSUPERVISOR") : "";
            String STATUS = req.getParameter("STATUS")!= null ? req.getParameter("STATUS") : "";
            String BATTERYUSELENGTH = (req.getParameter("BATTERYUSELENGTH")!= null && !"".equals(req.getParameter("BATTERYUSELENGTH"))) ? req.getParameter("BATTERYUSELENGTH") : "0";
            String ISCOUNTRY = req.getParameter("ISCOUNTRY")!= null ? req.getParameter("ISCOUNTRY") : "";
            String SOURCESYSTEMSTATIONTYPEID = (req.getParameter("SOURCESYSTEMSTATIONTYPEID")!= null && !"".equals(req.getParameter("SOURCESYSTEMSTATIONTYPEID"))) ? req.getParameter("SOURCESYSTEMSTATIONTYPEID") : "0";
            String SOURCESYSTEMSTATIONLEVELID = (req.getParameter("SOURCESYSTEMSTATIONLEVELID")!= null && !"".equals(req.getParameter("SOURCESYSTEMSTATIONLEVELID"))) ? req.getParameter("SOURCESYSTEMSTATIONLEVELID") : "0";
            String HOUSETYPEID = (req.getParameter("HOUSETYPEID")!= null && !"".equals(req.getParameter("HOUSETYPEID"))) ? req.getParameter("HOUSETYPEID") : "0";
            String ISUSERCONFIRM = (req.getParameter("ISUSERCONFIRM")!= null && !"".equals(req.getParameter("ISUSERCONFIRM"))) ? req.getParameter("ISUSERCONFIRM") : "";
            String USERSTATIONNAME = req.getParameter("USERSTATIONNAME")!= null ? req.getParameter("USERSTATIONNAME") : "";
            String LEAF_AREAID = (req.getParameter("LEAF_AREAID")!= null && !"".equals(req.getParameter("LEAF_AREAID"))) ? req.getParameter("LEAF_AREAID") : "0";
            String EDITTIME = req.getParameter("EDITTIME")!= null ? req.getParameter("EDITTIME") : null;
            String USEDSPECIALTY = req.getParameter("USEDSPECIALTY")!= null ? req.getParameter("USEDSPECIALTY") : "";
            String GROUPRANK_ID = (req.getParameter("GROUPRANK_ID")!= null && !"".equals(req.getParameter("GROUPRANK_ID"))) ? req.getParameter("GROUPRANK_ID") : "0";
            String CC08_SUPERVISORY = (req.getParameter("CC08_SUPERVISORY")!= null && !"".equals(req.getParameter("CC08_SUPERVISORY"))) ? req.getParameter("CC08_SUPERVISORY") : "";
            String STATION_ID_INSERT_DATE = req.getParameter("STATION_ID_INSERT_DATE")!= null ? req.getParameter("STATION_ID_INSERT_DATE") : null;
            String SUPERVISOR_DATE = req.getParameter("SUPERVISOR_DATE")!= null ? req.getParameter("SUPERVISOR_DATE") : null;
            String SUPERVISOR_TYPE = (req.getParameter("SUPERVISOR_TYPE")!= null && !"".equals(req.getParameter("SUPERVISOR_TYPE"))) ? req.getParameter("SUPERVISOR_TYPE") : "0";
            String TRANS_IP = req.getParameter("TRANS_IP")!= null ? req.getParameter("TRANS_IP") : "";
            String SUPERVISOR_EQU_IP = req.getParameter("SUPERVISOR_EQU_IP")!= null ? req.getParameter("SUPERVISOR_EQU_IP") : "";
            String TRANS_TYPE = (req.getParameter("TRANS_TYPE")!= null && !"".equals(req.getParameter("TRANS_TYPE"))) ? req.getParameter("TRANS_TYPE") : "0";
            String OLT_PORT_ADDRE = req.getParameter("OLT_PORT_ADDRE")!= null ? req.getParameter("OLT_PORT_ADDRE") : "";
            
            
            
            
            
            SiteManage bean = new SiteManage();
            int retsign = bean.addData(AREA_ID, DISTRICT_ID, COUNTRY_ID, jztype, jzproperty,
                    yflx, jflx, STATION_NAME, zjcode,zzlx,zdcq,wlzdbm,ltqx,ydqx,xtid,jingdu,weidu,phone,gdfname,bumen,
                    bieming, address, bgsign, jnglmk, gdfs,
                    area, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform,zzjgbm, gczt, gcxm,
                    zdcq, zdcb, zlfh,  czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, LONGITUDE, LATITUDE,jrwtype,jlfh,gsf,
                    entrypensonnel,entrytime,stationtype,dfzflx,fkfs,fkzq,zbdyhh,
                    watchcost,beilv,linelosstype,linelossvalue,dbid,gldb,dbyt,
                    dytype,g2,g3,kdsb,yysb,zpsl,zssl,kdsbsl,yysbsl,kt1,kt2,zgd,sydate,
                    sc,bg,yy,xxhzc,jstz,csds,cssytime,qyzt,lyjhjgs,gxxx,ydbid,jskssj,
                    jsjssj,xmh,ygd,ysd,dddf,changjia,jzlx,shebei,bbu,rru,ydshebei,gxgwsl,
                    twgx,bm,g2xqs,g3sqsl,ydgxsbsl,dxgxsbsl,kts,ktzgl,ysjts,wjts,yybgkt,
                    jfsckt,ktyps,kteps,ktps,zybyqlx,bsdl,"","","","",
                    LSCID, FULL_STATION_CODE, STATION_CODE,SERVICE_ID, RANK_ID,
                    PROPERTY_ID, MAINTAIN_ID, ADDRESS, COVERED_AREA,OCCUPIED_AREA, 
                    RENT, HIRE_TIME, PERIOD, RENT_ID, LEASE_ID,
                    LATEST_DATE,POWER_ID, PRICE, ELECTRICITYRATIO, ISSUPERVISOR,
                    STATUS, BATTERYUSELENGTH, ISCOUNTRY, SOURCESYSTEMSTATIONTYPEID,SOURCESYSTEMSTATIONLEVELID,
                    HOUSETYPEID, ISUSERCONFIRM, USERSTATIONNAME, LEAF_AREAID, EDITTIME, 
                    USEDSPECIALTY, GROUPRANK_ID, CC08_SUPERVISORY, STATION_ID_INSERT_DATE, SUPERVISOR_DATE,
                    SUPERVISOR_TYPE, TRANS_IP, SUPERVISOR_EQU_IP, TRANS_TYPE, OLT_PORT_ADDRE
            );

            if (retsign == 1) {
                msg = "添加站点成功，站点名称："+STATION_NAME+"地区："+AREA_ID+","+DISTRICT_ID+","+COUNTRY_ID+","+FULL_STATION_CODE;
            } else if (retsign == 2) {
                msg = "添加失败！站点代码重复！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if(action.equals("addBaozhangtieta")){
        	doPost1(req, resp,1);
        }else if(action.equals("addBaozhang")){ //添加报账  在用
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
           SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "添加报账失败！请重试或与管理员联系！";
            String PUEZHI = req.getParameter("PUEZHI");
            String BGDL = req.getParameter("BGDL");
            String BGPLL = req.getParameter("BGPLL");
            //2017年11月22日 xiaokang modify
            String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
            //2018年3月9日 - gcl 税额
            String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//税额
            String TAXchange = (req.getParameter("TAXchange")!= null && !"".equals(req.getParameter("TAXchange"))) ? req.getParameter("TAXchange") : "0";//修改前的税额
            String TAXSTATE = (req.getParameter("TAXSTATE")!= null && !"".equals(req.getParameter("TAXSTATE"))) ? req.getParameter("TAXSTATE") : "0";//税额状态
            //判断是否回转
            String huizhuan = (req.getParameter("hz")!= null && !"".equals(req.getParameter("hz"))) ? req.getParameter("hz") : "0";
            //FuXiuLi 2018/1/16 新添手机抄表信息录入
            String STARTTIME_C = (req.getParameter("STARTTIME_C")!= null && !"".equals(req.getParameter("STARTTIME_C"))) ? req.getParameter("STARTTIME_C") : null;	//手机抄表开始日期
            String ENDTIME_C = (req.getParameter("ENDTIME_C")!= null && !"".equals(req.getParameter("ENDTIME_C"))) ? req.getParameter("ENDTIME_C") : null;			//手机抄表结束日期
            String SQDS_C = (req.getParameter("SQDS_C")!= null && !"".equals(req.getParameter("SQDS_C"))) ? req.getParameter("SQDS_C") : "0";						//手机抄表上期读数
            String BQDS_C = (req.getParameter("BQDS_C")!= null && !"".equals(req.getParameter("BQDS_C"))) ? req.getParameter("BQDS_C") : "0";						//手机抄表本期度数
            String DIANLIANG_C = (req.getParameter("DIANLIANG_C")!= null && !"".equals(req.getParameter("DIANLIANG_C"))) ? req.getParameter("DIANLIANG_C") : "0";	//手机抄表电量
            String DAYNUM_C = (req.getParameter("TIANSHU_C")!= null && !"".equals(req.getParameter("TIANSHU_C"))) ? req.getParameter("TIANSHU_C") : "0";			//手机抄表用电天数
            String RJYDL_C = (req.getParameter("RJYDL_C")!= null && !"".equals(req.getParameter("RJYDL_C"))) ? req.getParameter("RJYDL_C") : "0";					//日均用电量
            
            String DianLiangPianLiShu = (req.getParameter("DianLiangPianLiShu")!= null && !"".equals(req.getParameter("DianLiangPianLiShu"))) ? req.getParameter("DianLiangPianLiShu") : "0";	//电量偏离数
            String RiQiPianLiShu = (req.getParameter("RiQiPianLiShu")!= null && !"".equals(req.getParameter("RiQiPianLiShu"))) ? req.getParameter("RiQiPianLiShu") : "0";					//日期偏离数
            //END	//String STARTTIME_C,String ENDTIME_C,String SQDS_C,String BQDS_C,String DIANLIANG_C,String DIANSUN_C,String RJYDL_C,
            String DIANBIAOID = (req.getParameter("DIANBIAOID")!= null && !"".equals(req.getParameter("DIANBIAOID"))) ? req.getParameter("DIANBIAOID") : "0";
            String STARTTIME = (req.getParameter("STARTTIME")!= null && !"".equals(req.getParameter("STARTTIME"))) ? req.getParameter("STARTTIME") : null;
            String ENDTIME = (req.getParameter("ENDTIME")!= null && !"".equals(req.getParameter("ENDTIME"))) ? req.getParameter("ENDTIME") : null;
            String SQDS = (req.getParameter("SQDS")!= null && !"".equals(req.getParameter("SQDS"))) ? req.getParameter("SQDS") : "0";
            String BQDS = (req.getParameter("BQDS")!= null && !"".equals(req.getParameter("BQDS"))) ? req.getParameter("BQDS") : "0";
            String DIANLIANG = (req.getParameter("DIANLIANG")!= null && !"".equals(req.getParameter("DIANLIANG"))) ? req.getParameter("DIANLIANG") : "0";
            String ALLMONEY = (req.getParameter("ALLMONEY")!= null && !"".equals(req.getParameter("ALLMONEY"))) ? req.getParameter("ALLMONEY") : "0";
            String DIANSUN = (req.getParameter("DIANSUN")!= null && !"".equals(req.getParameter("DIANSUN"))) ? req.getParameter("DIANSUN") : "0";
            String SQDJ = (req.getParameter("SQDJ")!= null && !"".equals(req.getParameter("SQDJ"))) ? req.getParameter("SQDJ") : "0";
            String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
            String DAYNUM = (req.getParameter("TIANSHU")!= null && !"".equals(req.getParameter("TIANSHU"))) ? req.getParameter("TIANSHU") : "0";
            String RJYDL = (req.getParameter("RJYDL")!= null && !"".equals(req.getParameter("RJYDL"))) ? req.getParameter("RJYDL") : "0";
            String SQRJYDL = (req.getParameter("SQRJYDL")!= null && !"".equals(req.getParameter("SQRJYDL"))) ? req.getParameter("SQRJYDL") : "0";
            String BEILV = (req.getParameter("BEILV")!= null && !"".equals(req.getParameter("BEILV"))) ? req.getParameter("BEILV") : "0";
         
            String ACCOUNTID=account.getAccountId();

//            System.out.println("ceshi--------------------------");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//新增报账时间-gcl-2018年3月30日

            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            String ACCTIME =sdf.format(new Date());
         
            String ACCTIMEYYMM =sdf2.format(new Date());
            SiteManage bean = new SiteManage();
            

            if(saveState.equals("0")){
            	
    			String flowId=req.getParameter("flowId");
    			String auditorid=req.getParameter("auditorid");
    			System.out.println("auditorid "+auditorid);
    			if(auditorid !=null && !auditorid.isEmpty()){
                    int bzId = bean.addDataBaoZhang(TAXchange,TAXSTATE,TAX,STARTTIME_C,ENDTIME_C,SQDS_C,BQDS_C,DIANLIANG_C,DAYNUM_C,RJYDL_C,DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,ACCOUNTID,ACCTIME,BEILV,"1",DianLiangPianLiShu,RiQiPianLiShu,PUEZHI,BGDL,BGPLL, "1");
                    if (bzId >0) {
                    	System.out.println("dianbiao huizhuan-------------"+huizhuan);
                    	if(!huizhuan.equals("0")){
//                    		System.out.println("电表回转修改电表相应字段-HZSTATE");
                    		int hz = Integer.parseInt(huizhuan);
                    		String sql = "update dianbiao d set d.HZSTATE = "+hz+" where d.id = "+DIANBIAOID;
                    		System.out.println("电表回转修改相应字段"+sql.toString());
                    		DataBase db = new DataBase();
                    		try {
								try {
									db.update(sql);
									db.commit();
								} catch (DbException e) {
									e.printStackTrace();
								}
							}  finally {
								try {
									if(db != null){
									db.close();
									}
								} catch (Exception de) {
									de.printStackTrace();
								}
							}
                    	}
                    	msg = "添加报账成功！";
                    } else  {
                        msg = "添加失败！";
                    }
                    //添加流程
                  	WorkFlowBean workFlowBean= new WorkFlowBean();
                  	String currentNo =workFlowBean.getNextStep(flowId, "1");
                  	String nextNo =workFlowBean.getNextStep(flowId, "2");
                    String taskId=bzId+"";
                    String taskType="ELECTRICFEES";
                    workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
                 
                    //添加pue记录 
                    CommonBean commonBean = new CommonBean();
                    List<String> strList = commonBean.getstationByDbId(DIANBIAOID);
                    
                   String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="";
                   if(strList!=null && strList.size()==4){
                   	st_id_str = strList.get(0);
                   	jiaoliu_str = strList.get(1);
                   	zhiliu_str = strList.get(2);
                   	zdid_str = strList.get(3);
                   }
                   List<String> strList2 = commonBean.getSumByDbId(st_id_str,STARTTIME, ENDTIME);
                   String sum_str = "",value_str="",sumDay_str="";
                   if(strList2!=null && strList2.size()==3){
                   	sum_str = strList2.get(0);
                   	value_str = strList2.get(1);
                   	sumDay_str = strList2.get(2);
                   }
                   bean.addAnalysis(taskId, ACCTIMEYYMM, ACCTIME, DIANLIANG,
                   		sum_str, DIANBIAOID, zdid_str, STARTTIME, 
                   		ENDTIME,value_str,jiaoliu_str,zhiliu_str,sumDay_str);
    			}	 
            }else{
            	int bzId = bean.addDataBaoZhang(TAXchange,TAXSTATE,TAX,STARTTIME_C,ENDTIME_C,SQDS_C,BQDS_C,DIANLIANG_C,DAYNUM_C,RJYDL_C,DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,ACCOUNTID,ACCTIME,BEILV,"0",DianLiangPianLiShu,RiQiPianLiShu,PUEZHI,BGDL,BGPLL, "1");
            	 if (bzId >0) {
                     msg = "临时保存报账成功！";
                 } else  {
                     msg = "添加失败！报账代码重复！";
                 }
            }
            
           
            log.write(msg, accountid, req.getRemoteAddr(), "添加报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if(action.equals("tsbz_2")){//合并报账
            //向财辅系统推送信息
            url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "推送失败！请重试或与管理员联系！";
            
            //2017年11月22日 xiaokang modify
            String bzfs = (req.getParameter("bzfs")!= null && !"".equals(req.getParameter("bzfs"))) ? req.getParameter("bzfs") : "";
            String costtime = (req.getParameter("costtime")!= null && !"".equals(req.getParameter("costtime"))) ? req.getParameter("costtime") : "";
            String bztype = (req.getParameter("bztype")!= null && !"".equals(req.getParameter("bztype"))) ? req.getParameter("bztype") : "";
            String gangwei = (req.getParameter("gangwei")!= null && !"".equals(req.getParameter("gangwei"))) ? req.getParameter("gangwei") : "";
            String bzywsj = (req.getParameter("bzywsj")!= null && !"".equals(req.getParameter("bzywsj"))) ? req.getParameter("bzywsj") : "";
            String jjsx = (req.getParameter("jjsx")!= null && !"".equals(req.getParameter("jjsx"))) ? req.getParameter("jjsx") : "";
            String yongtu = (req.getParameter("yongtu")!= null && !"".equals(req.getParameter("yongtu"))) ? req.getParameter("yongtu") : "";
            String rsname = (req.getParameter("gysmc")!= null && !"".equals(req.getParameter("gysmc"))) ? req.getParameter("gysmc") : "";
            String rsname1 = (req.getParameter("rsname1")!= null && !"".equals(req.getParameter("rsname1"))) ? req.getParameter("rsname1") : "";
            String rscode = (req.getParameter("gysbm")!= null && !"".equals(req.getParameter("gysbm"))) ? req.getParameter("gysbm") : "";
            String paytype = (req.getParameter("paytype")!= null && !"".equals(req.getParameter("paytype"))) ? req.getParameter("paytype") : "";
            String ids = (req.getParameter("ids")!= null && !"".equals(req.getParameter("ids"))) ? req.getParameter("ids") : "";
            
             //获取所选择的报账信息
            SiteManage bean = new SiteManage();
          
            
            DecimalFormat df1 = new DecimalFormat("0.00");
            DecimalFormat df2 = new DecimalFormat("0.0000");
            
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            double allMoney_sw=0;
            
            ArrayList fylist = new ArrayList();
            fylist = bean.getListByIds(ids);
            String id = "", state = "", compname = "", dbname = "", dbcode = "", zdname = "", zdcode = "";
            String fptype = "", jffs = "", starttime = "", endtime = "", rcname = "", rccode = "",eleid="";
            String money = "", allmoney = "", faxmoney = "", dianliang = "", price = "", isbz = "", isbzname = "";
            String ywname = "", ywcode = "", ysname = "", yscode = "",fpcode="",shiname="",shicode="";
            double sumMoney = 0, sumFaxmoney = 0, sumDianliang = 0;
            if (fylist != null)
            {
                int fycount = 0;
                if (fylist.size() > 0)
                {
                    fycount = ((Integer)fylist.get(0)).intValue();
                    
                }
                
                int intnum = 1;
                for (int k = fycount; k < fylist.size() - 1; k += fycount)
                {
                    
                    id = (String)fylist.get(k + fylist.indexOf("ID"));
                    state = (String)fylist.get(k + fylist.indexOf("STATE"));
                    compname = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
                    dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
                    dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
                    zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
                    zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
                    fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
                    fpcode = (String)fylist.get(k + fylist.indexOf("FPCODE"));
                    jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
                    starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
                    endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
                    rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
                    rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
                    money = (String)fylist.get(k + fylist.indexOf("MONEY"));
                    allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
                    faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
                    dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
                    price = (String)fylist.get(k + fylist.indexOf("PRICE"));
                    isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
                    ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
                    ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
                    ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
                    yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
                    shiname = (String)fylist.get(k + fylist.indexOf("SHINAME"));
                    shicode = (String)fylist.get(k + fylist.indexOf("SHICODE"));
                    eleid = (String)fylist.get(k + fylist.indexOf("ELEID"));
                    
                    if (money != "")
                    {
                        sumMoney = sumMoney + Double.valueOf(money).doubleValue();
                    }
                    else
                    {
                        money = "0.00";
                    }
                    if (faxmoney != "")
                    {
                        sumFaxmoney = sumFaxmoney + Double.valueOf(faxmoney).doubleValue();
                    }
                    else
                    {
                        faxmoney = "0.00";
                    }
                    if (dianliang != "")
                    {
                        sumDianliang = sumDianliang + Double.valueOf(dianliang).doubleValue();
                    }
                    else
                    {
                        dianliang = "0.00";
                    }
                    
                }
            }
            //调用接口
            //--------------------begin-----------------
            
            BZServiceImpl bzServiceImpl = new BZServiceImpl();
            RequestMessage requestMessage = new RequestMessage();
            /*      requestMessage.setProcessCode("");
                    requestMessage.setProcessSuffixName("能耗系统集成");*/
            
                    BZItem item = new BZItem();
                    //外围系统主单Id 每次都必须唯一
                    String otherSystemMainId = bzServiceImpl.getUUID();
                    item.setOtherSystemMainId(otherSystemMainId);
                    //登陆人账号
                    String accountName = account.getCthrnumber();
                    item.setAccount(accountName);
                    
                    //获取组织编码
                    String fillInOrgCode ="";
                    String fillInOrgName ="";
                    RequestMessageCode requestMessage2 = new RequestMessageCode();
                    List<CodeItem> items = new ArrayList<CodeItem>();
                    CodeItem codeItem = new CodeItem();
                    codeItem.setAccount(accountName);
                    items.add(codeItem);
                    requestMessage2.setItems(items);
                    ResponseMessageCode responseMessage2 = new ResponseMessageCode();
                    try
                    {
                        responseMessage2 = bzServiceImpl.GetOrgCode(jiekouUrl,requestMessage2);
                        if(responseMessage2.getErrorMsg().equals("")){
                            fillInOrgCode = responseMessage2.getItems().get(0).getOrgAndRoles().get(0).getOrgCode();
                            fillInOrgName = responseMessage2.getItems().get(0).getPrincipalName();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    item.setFillInName(fillInOrgName);
                    item.setFillInOrgCode(fillInOrgCode);
                    
                    
                    //公司代码?????????????
                    String sapCompayCode =compname;
                    item.setSapCompayCode(compname);
                    //经济事项
                    item.setEconomicItemCode(jjsx);
                    item.setEconomicItemName("电费");
                    //收支方式
                    String paymentType="";
                    if(paytype.equals("paytype_dict_1")){
                        paymentType="1";
                    }else if(paytype.equals("paytype_dict_2")){
                        paymentType="2";
                    }else if(paytype.equals("paytype_dict_3")){
                        paymentType="3";
                    }else if(paytype.equals("paytype_dict_4")){
                        paymentType="4";
                    }else{
                        paymentType="8";
                    }
                    item.setPaymentType(paymentType);
                    //费用发生日
                    item.setHappenDate(ACCTIME);
                    // 列账属性
                    String budgetType="";
                   //业务类型
                    String bizType="";
                    //是否员工代垫
                    String isStaffPayment="";
                    //业务场景
                    String pickingMode="";
                    if(bzfs.equals("bzfs_dict_1")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="1";
                    }else  if(bzfs.equals("bzfs_dict_2")){
                        budgetType="2";
                        bizType="1";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_3")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_4")){
                        budgetType="1";
                        bizType="2";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_5")){
                        budgetType="1";
                        bizType="2";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_6")){
                        budgetType="2";
                        bizType="1";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_7")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_8")){
                    	  budgetType="1";
                          bizType="2";
                          isStaffPayment="0";
                          pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_9")){
                        budgetType="1";
                        bizType="2";
                        isStaffPayment="1";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_10")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="1";
                    }else  if(bzfs.equals("bzfs_dict_11")){
                        budgetType="";
                        bizType="1";
                        isStaffPayment="1";
                        pickingMode="";
                    }
                   item.setBizType(bizType);
                    //报账期间
                    item.setBudgetSet(costtime);
                    //是否员工代垫
                    item.setIsStaffPayment(isStaffPayment);
                    //item.setContractNo("");
                    //item.setContractName("");
                    item.setSum(""+(Double.valueOf(sumMoney).doubleValue() + Double.valueOf(sumFaxmoney).doubleValue()));
                //  item.setDesc("");
                     item.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" 的电费");
                    //发票
                    String invoiceType="";
                    if(fpcode.equals("pjlx01")){
                        invoiceType="1";
                        
                    }else if(fpcode.equals("pjlx02")){
                        invoiceType="2";
                        
                    }else if(fpcode.equals("pjlx03")){
                        invoiceType="3";
                      
                    }else if(fpcode.equals("pjlx04")){
                        invoiceType="4";
                       
                    }else if(fpcode.equals("pjlx05")){
                        invoiceType="5";
                       
                    }else if(fpcode.equals("pjlx06")){
                        invoiceType="6";
                        
                    }else if(fpcode.equals("pjlx08")){
                        invoiceType="8";
                       
                    }else if(fpcode.equals("pjlx09")){
                        invoiceType="9";
                        
                    }else if(fpcode.equals("pjlx10")){
                        invoiceType="10";
                      
                    }else if(fpcode.equals("pjlx11")){
                        invoiceType="11";
                     
                    }else if(fpcode.equals("pjlx12")){
                        invoiceType="12";
                        
                    }
                    item.setInvoiceType(invoiceType);
                   
                   	 item.setIsNeedImage("1");
                  
                    List<BZLineItem> lineItems = new ArrayList<BZLineItem>();
                    BZLineItem lineItem = new BZLineItem();
                    //外围系统子单Id
                    String otherSystemDetailId = bzServiceImpl.getUUID();
                    lineItem.setOtherSystemDetailId(otherSystemDetailId);
                    
                    lineItem.setUsageCode(yongtu);
                    //lineItem.setUsageName("");
                    //列账属性
                    lineItem.setBudgetType(budgetType);
                    //子单金额
                    lineItem.setSum(""+sumMoney);
                    //摘要
               //    lineItem.setDesc("");
                    lineItem.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" 的电费");
                    //预算科目编码 名称
                    lineItem.setBudgetItemCode("CW1000");
                    lineItem.setBudgetItemName("能源费");
                    //预算责任中心编码
                    lineItem.setBudgetOrgCode(yscode);
                    lineItem.setBudgetOrgName(yscode);
                    //成本中心编码名称
//                    lineItem.setSapCostCenterCode("A370108000");
//                    lineItem.setSapCostCenterName("股份济南商河县分公司本部（成本）");
                    lineItem.setSapCostCenterCode(rccode);
                    lineItem.setSapCostCenterName(rcname);
                    //数量
                    lineItem.setCount(""+sumDianliang);
                    //价格
                    String avePrice ="0.00";
                    try
                    {
                        avePrice =df2.format((Double.valueOf(sumMoney).doubleValue()) / sumDianliang);
                    }
                    catch (Exception e)
                    {
                        avePrice = "0.00";
                    }
                    lineItem.setPrice(avePrice);
                    //单位
                    lineItem.setUnit("01");
                    lineItem.setCurrency("CNY");
                    lineItem.setExchangeRate("1.0");
                    
                    lineItem.setCurrencySum(""+sumMoney);
                    //lineItem.setChargecode("");
                    
                  
                    lineItems.add(lineItem);
                    
                    
                    
                    if(invoiceType.equals("1") || invoiceType.equals("4") || 
                            invoiceType.equals("5") || invoiceType.equals("6") || 
                            invoiceType.equals("11")){
                        
                        BZLineItem lineItem_jxs = new BZLineItem();
                        //外围系统子单Id
                        String otherSystemDetailId2 = bzServiceImpl.getUUID();
                        lineItem_jxs.setOtherSystemDetailId(otherSystemDetailId2);
                        
                        lineItem_jxs.setUsageCode("U888");
                        //lineItem.setUsageName("");
                        //列账属性
                        lineItem_jxs.setBudgetType("2");
                        //子单金额
                        lineItem_jxs.setSum(""+sumFaxmoney);
                        //摘要
                        lineItem_jxs.setDesc("进项税");
                        //预算科目编码 名称
                        lineItem_jxs.setBudgetItemCode("CW1000");
                        lineItem_jxs.setBudgetItemName("能源费");
                        //活动预算编码名称
                        lineItem_jxs.setBudgetOrgCode(yscode);
                        lineItem_jxs.setBudgetOrgName(ysname);
                        //成本中心编码名称
//                        lineItem.setSapCostCenterCode("A370108000");
//                        lineItem.setSapCostCenterName("股份济南商河县分公司本部（成本）");
                        lineItem_jxs.setSapCostCenterCode(rccode);
                        lineItem_jxs.setSapCostCenterName(rcname);
                        //数量
                        lineItem_jxs.setCount(""+sumDianliang);
                        //价格
                        lineItem_jxs.setPrice(price);
                        //单位
                        lineItem_jxs.setUnit("01");
                        lineItem_jxs.setCurrency("CNY");
                        lineItem_jxs.setExchangeRate("1.0");
                        
                        lineItem_jxs.setCurrencySum(""+sumFaxmoney);
                        //lineItem.setChargecode("");
                        
                      
                        lineItems.add(lineItem_jxs);
                    }
                    
                    
                    
                    item.setLineItems(lineItems);
                    
                    RelateSupplier relateSupplier = new RelateSupplier();
                  //根据供应商编码获取信息
                    CommonBean commonBean = new CommonBean();
                    GongysBean gongysBean = commonBean.getSupplier2(rscode);
                    String rsnameStr = gongysBean.getName();
                    String rsnameId = gongysBean.getId();
                    List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
                    gongysAccountBeans = commonBean.getGaById(rsnameId);
                    gongysBean.setGongysAccountBeans(gongysAccountBeans);
                    
                  //供应商编码名称
                    relateSupplier.setSupplierCode(rscode);
                    relateSupplier.setSupplierName(rsnameStr);
                  //会计科目类型--判断成本中心编码是否以G开头,是为供应商,否为客户
                    if(rscode.startsWith("G")){
                    	relateSupplier.setAccountType("2");
                    }else{
                    	relateSupplier.setAccountType("3");
                    }
                    //金额 税额
                    relateSupplier.setSum(""+sumMoney);
                    relateSupplier.setInputTaxSum(""+sumFaxmoney);
                    relateSupplier.setInvoiceSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                    
                    List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();
                    relateSuppliers.add(relateSupplier);
                    item.setRelateSuppliers(relateSuppliers);
                    //业务场景
                    item.setPickingMode(pickingMode);
                    //业务发生时点
                    if(bzywsj.equals("bzywsj_dict_1")){
                        item.setBusinessHappenTimeFlag("1");
                    }else if(bzywsj.equals("bzywsj_dict_2")){
                        item.setBusinessHappenTimeFlag("2");
                    }
                    List<PayMentItem> payMentItmes = new ArrayList<PayMentItem>();
                   //paymentType 
                    if(paymentType=="3" || paymentType=="4"){
                		
                    	for(int g=0;g<gongysAccountBeans.size();g++){
                    		GongysAccountBean ga = gongysAccountBeans.get(g);
                    		PayMentItem payMentItem = new PayMentItem();
                    		payMentItem.setEmployeeBankAccount(ga.getAccount_no());
                    		payMentItem.setEmployeeName(ga.getAccountName());
                    		payMentItem.setBank(ga.getBank());
                    		payMentItem.setBankCode(ga.getBank_type());
                    		payMentItem.setPayeeCode(rscode);
                    		payMentItem.setAccountType("1");
                    		payMentItem.setPayeeType("K");
                    		payMentItem.setRowno(ga.getBrnch());
                    		payMentItem.setBankAddress("");
                    		payMentItem.setProvince("山东");
                    		payMentItem.setCity(ga.getCity());
                    		payMentItem.setCurrency("CN");
                    		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                    		payMentItmes.add(payMentItem);
                    	}
                		
//                    	payMentItem.setEmployeeBankAccount("6222081613001150000");
//                		payMentItem.setEmployeeName("韩勇");
//                		payMentItem.setBank("中国工商银行滨州滨南支行");
//                		payMentItem.setBankCode("102");
//                		payMentItem.setPayeeCode("G000549854");
//                		payMentItem.setAccountType("1");
//                		payMentItem.setPayeeType("K");
//                		payMentItem.setRowno("102466002453");
//                		payMentItem.setBankAddress("中国工商银行滨州滨南支行");
//                		payMentItem.setProvince("山东");
//                		payMentItem.setCity("滨州");
//                		payMentItem.setCurrency("CN");
//                		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
//                		payMentItmes.add(payMentItem);
//                		
                    }
                    item.setPayMentItems(payMentItmes);
                    
                    item.setIsRealGift("0");
                    item.setRealGiftTaxSum("0.00");
                    item.setCurrency("CNY");
                    
                    List<BZItem> writeoffItems = new ArrayList<BZItem>();
                    writeoffItems.add(item);
                    
                    requestMessage.setWriteoffItems(writeoffItems);
            
                    ResponseMessage responseMessage = new ResponseMessage();
                    try
                    {
                        responseMessage = bzServiceImpl.baoZhang(jiekouUrl, requestMessage);
                    }
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String tsbz_state = "0";//状态
                    String tsbz_costsum = "";//报账单号
                    String tsbz_appsum = "";//报账单号
                    if(responseMessage.getTYPE().equals("S")){
                        //如果报送成功，根据id获取报账单状态 选项：-4-等待生成报账单中;-3-报账单发起失败；-1-报账单删除，－2-退单；2-生成报账单成功；3-已生成SAP凭证
                        RequestMessageMenu requestMessage3 = new RequestMessageMenu();
                        List<MenuItem> menuItems = new ArrayList<MenuItem>();
                        MenuItem menuItem = new MenuItem();
                        menuItem.setOtherSystemMainId(otherSystemMainId); //8a0c9eb85fb3fb0f015fb45664fa02b7
                        menuItem.setWriteoffInstanceCode("");
                        menuItems.add(menuItem);
                        requestMessage3.setItems(menuItems);
                        ResponseMessageMenu responseMessage3 = new ResponseMessageMenu();
                        try
                        {
                            responseMessage3 = bzServiceImpl.GetMenuStatus(jiekouUrl,requestMessage3);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                  
                         tsbz_state=responseMessage3.getItems().get(0).getStatus();
                         if(tsbz_state.equals("2")){
                             tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                         }
                         if(tsbz_state.equals("3")){
                        	 tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                             tsbz_appsum=responseMessage3.getItems().get(0).getSpCertificateCode();
                         }
                    }
            //--------------------end-----------------
            
                    int acId=0;
                    if(!tsbz_state.equals("0")){
                      acId = bean.tsAllbz(jjsx, tsbz_state, costtime,
                       fillInOrgName, accountName,
                       tsbz_costsum, bztype, tsbz_appsum, rsnameStr, rscode, paytype,
                      bzfs, gangwei, ACCTIME, jjsx, yongtu, ACCTIME,otherSystemMainId,fillInOrgCode);
                    }
                    
            
            if (fylist != null && acId!=0)
            {
                int fycount = 0;
                if (fylist.size() > 0)
                {
                    fycount = ((Integer)fylist.get(0)).intValue();
                    
                }
                
                for (int k = fycount; k < fylist.size() - 1; k += fycount)
                {
                    
                    id = (String)fylist.get(k + fylist.indexOf("ID"));
                    state = (String)fylist.get(k + fylist.indexOf("STATE"));
                    compname = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
                    dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
                    dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
                    zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
                    zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
                    fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
                    fpcode = (String)fylist.get(k + fylist.indexOf("FPCODE"));
                    jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
                    starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
                    endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
                    rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
                    rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
                    money = (String)fylist.get(k + fylist.indexOf("MONEY"));
                    allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
                    faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
                    dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
                    price = (String)fylist.get(k + fylist.indexOf("PRICE"));
                    isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
                    ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
                    ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
                    ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
                    yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
                    shiname = (String)fylist.get(k + fylist.indexOf("SHINAME"));
                    shicode = (String)fylist.get(k + fylist.indexOf("SHICODE"));
                    eleid = (String)fylist.get(k + fylist.indexOf("ELEID"));
                    int asId = bean.tsbz(""+acId, shiname, dbname, dbcode, zdname, 
                            zdcode,  fpcode,fptype, starttime, endtime, 
                            ywname, rcname, rccode, ysname, yscode, 
                            allmoney, faxmoney, compname,id,shicode,dianliang);
                    bean.updateBz_soloState("1",id);
                    if(tsbz_state.equals("3")){
                		int m = bean.getCostBybzid(id);
                		if(m==0){
                			bean.updateBaoZhangState("4",eleid);
                		}
                    	
                    }
                    
                }
            }
            if (acId >0) {
                msg = "推送报账成功！";
            } else  {
                msg = "推送失败！";
            }
            
            
            
            log.write(msg, accountid, req.getRemoteAddr(), "往财辅推送报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        
        }
        else if(action.equals("tsbz_3")){ //向财辅系统推送信息 集中报账
            url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "推送失败！请重试或与管理员联系！";
            
            //2017年11月22日 xiaokang modify
            String bzfs = (req.getParameter("bzfs")!= null && !"".equals(req.getParameter("bzfs"))) ? req.getParameter("bzfs") : "";
            String costtime = (req.getParameter("costtime")!= null && !"".equals(req.getParameter("costtime"))) ? req.getParameter("costtime") : "";
            String bztype = (req.getParameter("bztype")!= null && !"".equals(req.getParameter("bztype"))) ? req.getParameter("bztype") : "";
            String gangwei = (req.getParameter("gangwei")!= null && !"".equals(req.getParameter("gangwei"))) ? req.getParameter("gangwei") : "";
            String bzywsj = (req.getParameter("bzywsj")!= null && !"".equals(req.getParameter("bzywsj"))) ? req.getParameter("bzywsj") : "";
            String jjsx = (req.getParameter("jjsx")!= null && !"".equals(req.getParameter("jjsx"))) ? req.getParameter("jjsx") : "";
            String yongtu = (req.getParameter("yongtu")!= null && !"".equals(req.getParameter("yongtu"))) ? req.getParameter("yongtu") : "";
            String rsname = (req.getParameter("gysmc")!= null && !"".equals(req.getParameter("gysmc"))) ? req.getParameter("gysmc") : "";
            String rsname1 = (req.getParameter("rsname1")!= null && !"".equals(req.getParameter("rsname1"))) ? req.getParameter("rsname1") : "";
            String rscode = (req.getParameter("gysbm")!= null && !"".equals(req.getParameter("gysbm"))) ? req.getParameter("gysbm") : "";
            String paytype = (req.getParameter("paytype")!= null && !"".equals(req.getParameter("paytype"))) ? req.getParameter("paytype") : "";
            String ids = (req.getParameter("ids")!= null && !"".equals(req.getParameter("ids"))) ? req.getParameter("ids") : "";
            
             //获取所选择的报账信息
            SiteManage bean = new SiteManage();
          
            
            DecimalFormat df1 = new DecimalFormat("0.00");
            
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            double allMoney_sw=0;
            
            ArrayList fylist = new ArrayList();
            fylist = bean.getListByIds(ids);
            String id = "", state = "", compname = "", dbname = "", dbcode = "", zdname = "", zdcode = "";
            String fptype = "", jffs = "", starttime = "", endtime = "", rcname = "", rccode = "",eleid;
            String money = "", allmoney = "", faxmoney = "", dianliang = "", price = "", isbz = "", isbzname = "",shicode="";
            String ywname = "", ywcode = "", ysname = "", yscode = "",fpcode="",shiname="",fristDbName="",fristRcName="";
            double sumMoney = 0, sumFaxmoney = 0;
            if (fylist != null)
            {
                int fycount = 0;
                if (fylist.size() > 0)
                {
                    fycount = ((Integer)fylist.get(0)).intValue();
                    
                }
                
                int intnum = 1;
                for (int k = fycount; k < fylist.size() - 1; k += fycount)
                {
                    
                    id = (String)fylist.get(k + fylist.indexOf("ID"));
                    state = (String)fylist.get(k + fylist.indexOf("STATE"));
                    compname = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
                    dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
                    if(intnum == 1){
                        fristDbName=(String)fylist.get(k + fylist.indexOf("DBNAME"));
                        fristRcName = (String)fylist.get(k + fylist.indexOf("RCNAME"));

                    }
                    dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
                    zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
                    zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
                    fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
                    fpcode = (String)fylist.get(k + fylist.indexOf("FPCODE"));
                    jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
                    starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
                    endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
                    rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
                    rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
                    money = (String)fylist.get(k + fylist.indexOf("MONEY"));
                    allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
                    faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
                    dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
                    price = (String)fylist.get(k + fylist.indexOf("PRICE"));
                    isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
                    ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
                    ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
                    ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
                    yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
                    shiname = (String)fylist.get(k + fylist.indexOf("SHINAME"));
                    shicode = (String)fylist.get(k + fylist.indexOf("SHICODE"));
                    eleid = (String)fylist.get(k + fylist.indexOf("ELEID"));
                    if (money != "")
                    {
                        sumMoney = sumMoney + Double.valueOf(money).doubleValue();
                    }
                    else
                    {
                        money = "0.00";
                    }
                    if (faxmoney != "")
                    {
                        sumFaxmoney = sumFaxmoney + Double.valueOf(faxmoney).doubleValue();
                    }
                    else
                    {
                        faxmoney = "0.00";
                    }
                    intnum++;
                }
            }
            //调用接口
            //--------------------begin-----------------
            
            BZServiceImpl bzServiceImpl = new BZServiceImpl();
            RequestMessage requestMessage = new RequestMessage();
            /*      requestMessage.setProcessCode("");
                    requestMessage.setProcessSuffixName("能耗系统集成");*/
            
                    BZItem item = new BZItem();
                    //外围系统主单Id 每次都必须唯一
                    String otherSystemMainId = bzServiceImpl.getUUID();
                    item.setOtherSystemMainId(otherSystemMainId);
                    //登陆人账号
                    String accountName = account.getCthrnumber();
                    item.setAccount(accountName);
                    
                    //获取组织编码
                    String fillInOrgCode ="";
                    String fillInOrgName ="";
                    RequestMessageCode requestMessage2 = new RequestMessageCode();
                    List<CodeItem> items = new ArrayList<CodeItem>();
                    CodeItem codeItem = new CodeItem();
                    codeItem.setAccount(accountName);
                    items.add(codeItem);
                    requestMessage2.setItems(items);
                    ResponseMessageCode responseMessage2 = new ResponseMessageCode();
                    try
                    {
                        responseMessage2 = bzServiceImpl.GetOrgCode(jiekouUrl,requestMessage2);
                        if(responseMessage2.getErrorMsg().equals("")){
                            fillInOrgCode = responseMessage2.getItems().get(0).getOrgAndRoles().get(0).getOrgCode();
                            fillInOrgName = responseMessage2.getItems().get(0).getPrincipalName();
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    
                    item.setFillInName(fillInOrgName);
                    item.setFillInOrgCode(fillInOrgCode);
                    
                    
                    //公司代码?????????????
                    String sapCompayCode =compname;
                    item.setSapCompayCode(compname);
                    //经济事项
                    item.setEconomicItemCode(jjsx);
                    item.setEconomicItemName("电费");
                    //收支方式
                    String paymentType="";
                    if(paytype.equals("paytype_dict_1")){
                        paymentType="1";
                    }else if(paytype.equals("paytype_dict_2")){
                        paymentType="2";
                    }else if(paytype.equals("paytype_dict_3")){
                        paymentType="3";
                    }else if(paytype.equals("paytype_dict_4")){
                        paymentType="4";
                    }else{
                        paymentType="8";
                    }
                    item.setPaymentType(paymentType);
                    //费用发生日
                    item.setHappenDate(ACCTIME);
                    // 列账属性
                    String budgetType="";
                   //业务类型
                    String bizType="";
                    //是否员工代垫
                    String isStaffPayment="";
                    //业务场景
                    String pickingMode="";
                    if(bzfs.equals("bzfs_dict_1")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="1";
                    }else  if(bzfs.equals("bzfs_dict_2")){
                        budgetType="2";
                        bizType="1";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_3")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_4")){
                        budgetType="1";
                        bizType="2";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_5")){
                        budgetType="1";
                        bizType="2";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_6")){
                        budgetType="2";
                        bizType="1";
                        isStaffPayment="0";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_7")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_8")){
                    	  budgetType="1";
                          bizType="2";
                          isStaffPayment="0";
                          pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_9")){
                        budgetType="1";
                        bizType="2";
                        isStaffPayment="1";
                        pickingMode="";
                    }else  if(bzfs.equals("bzfs_dict_10")){
                        budgetType="1";
                        bizType="0";
                        isStaffPayment="";
                        pickingMode="1";
                    }else  if(bzfs.equals("bzfs_dict_11")){
                        budgetType="";
                        bizType="1";
                        isStaffPayment="1";
                        pickingMode="";
                    }
                   item.setBizType(bizType);
                    //报账期间
                    item.setBudgetSet(costtime);
                    //是否员工代垫
                    item.setIsStaffPayment(isStaffPayment);
                    //item.setContractNo("");
                    //item.setContractName("");
                    item.setSum(""+(Double.valueOf(sumMoney).doubleValue() + Double.valueOf(sumFaxmoney).doubleValue()));
                    item.setDesc(fristRcName+" "+fillInOrgName+"  "+fristDbName+" 等的电费(新能耗)");
                    //发票
                    String invoiceType="";
                    if(fpcode.equals("pjlx01")){
                        invoiceType="1";
                    }else if(fpcode.equals("pjlx02")){
                        invoiceType="2";
                    }else if(fpcode.equals("pjlx03")){
                        invoiceType="3";
                    }else if(fpcode.equals("pjlx04")){
                        invoiceType="4";
                    }else if(fpcode.equals("pjlx05")){
                        invoiceType="5";
                    }else if(fpcode.equals("pjlx06")){
                        invoiceType="6";
                    }else if(fpcode.equals("pjlx08")){
                        invoiceType="8";
                    }else if(fpcode.equals("pjlx09")){
                        invoiceType="9";
                    }else if(fpcode.equals("pjlx10")){
                        invoiceType="10";
                    }else if(fpcode.equals("pjlx11")){
                        invoiceType="11";
                    }else if(fpcode.equals("pjlx12")){
                        invoiceType="12";
                        
                    }
                    item.setInvoiceType(invoiceType);
                    
                    	 item.setIsNeedImage("1");
                   
                   
                    List<BZLineItem> lineItems = new ArrayList<BZLineItem>();
                    
                    if (fylist != null)
                    {
                        int fycount = 0;
                        if (fylist.size() > 0)
                        {
                            fycount = ((Integer)fylist.get(0)).intValue();
                            
                        }
                        
                        int intnum = 1;
                        for (int k = fycount; k < fylist.size() - 1; k += fycount)
                        {
                            
                            id = (String)fylist.get(k + fylist.indexOf("ID"));
                            state = (String)fylist.get(k + fylist.indexOf("STATE"));
                            compname = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
                            dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
                            dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
                            zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
                            zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
                            fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
                            fpcode = (String)fylist.get(k + fylist.indexOf("FPCODE"));
                            jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
                            starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
                            endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
                            rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
                            rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
                            money = (String)fylist.get(k + fylist.indexOf("MONEY"));
                            allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
                            faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
                            dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
                            price = (String)fylist.get(k + fylist.indexOf("PRICE"));
                            isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
                            ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
                            ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
                            ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
                            yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
                            shiname = (String)fylist.get(k + fylist.indexOf("SHINAME"));
                            shicode = (String)fylist.get(k + fylist.indexOf("SHICODE"));
                            eleid = (String)fylist.get(k + fylist.indexOf("ELEID"));
                            if (money == "")
                            {
                                money = "0.00";
                            }
                            if (faxmoney == "")
                            {
                                faxmoney = "0.00";
                            }
                            
                            BZLineItem lineItem = new BZLineItem();
                            //外围系统子单Id
                            String otherSystemDetailId = bzServiceImpl.getUUID();
                            lineItem.setOtherSystemDetailId(otherSystemDetailId);
                            
                            lineItem.setUsageCode(yongtu);
                            //lineItem.setUsageName("");
                            //列账属性
                            lineItem.setBudgetType(budgetType);
                            //子单金额
                            lineItem.setSum(""+money);
                            //摘要
                         //  lineItem.setDesc("");
                            lineItem.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" 的电费");
                            //预算科目编码 名称
                            lineItem.setBudgetItemCode("CW1000");
                            lineItem.setBudgetItemName("能源费");
                            //预算责任中心编码
                            lineItem.setBudgetOrgCode(yscode);
                            lineItem.setBudgetOrgName(ysname);
                            //成本中心编码名称
//                            lineItem.setSapCostCenterCode("A370108000");
//                            lineItem.setSapCostCenterName("股份济南商河县分公司本部（成本）");
                            lineItem.setSapCostCenterCode(rccode);
                            lineItem.setSapCostCenterName(rcname);
                            //数量
                            lineItem.setCount(dianliang);
                            //价格
                            lineItem.setPrice(price);
                            //单位
                            lineItem.setUnit("01");
                            lineItem.setCurrency("CNY");
                            lineItem.setExchangeRate("1.0");
                            
                            lineItem.setCurrencySum(""+money);
                            //lineItem.setChargecode("");
                            
                          
                            lineItems.add(lineItem);
                        }
                    }
                    
                   if(invoiceType.equals("1") || invoiceType.equals("4") || 
                           invoiceType.equals("5") || invoiceType.equals("6") || 
                           invoiceType.equals("11")){
                       
                       BZLineItem lineItem = new BZLineItem();
                       //外围系统子单Id
                       String otherSystemDetailId2 = bzServiceImpl.getUUID();
                       lineItem.setOtherSystemDetailId(otherSystemDetailId2);
                       
                       lineItem.setUsageCode("U888");
                       //lineItem.setUsageName("");
                       //列账属性
                       lineItem.setBudgetType("2");
                       //子单金额
                       lineItem.setSum(""+sumFaxmoney);
                       //摘要
                       lineItem.setDesc("进项税");
                     //预算科目编码 名称
                       lineItem.setBudgetItemCode("CW1000");
                       lineItem.setBudgetItemName("能源费");
//                       //预算科目编码 名称 -----旧
//                       lineItem.setBudgetItemCode("");
//                       lineItem.setBudgetItemName("");
                     //预算责任中心编码
                       lineItem.setBudgetOrgCode(yscode);
                       lineItem.setBudgetOrgName(ysname);
                       //成本中心编码名称
//                       lineItem.setSapCostCenterCode("A370108000");
//                       lineItem.setSapCostCenterName("股份济南商河县分公司本部（成本）");
                       lineItem.setSapCostCenterCode(rccode);
                       lineItem.setSapCostCenterName(rcname);
                       //数量
                       lineItem.setCount(dianliang);
                       //价格
                       lineItem.setPrice(price);
                       //单位
                       lineItem.setUnit("01");
                       lineItem.setCurrency("CNY");
                       lineItem.setExchangeRate("1.0");
                       
                       lineItem.setCurrencySum(""+sumFaxmoney);
                       //lineItem.setChargecode("");
                       
                     
                       lineItems.add(lineItem);
                   }
                    item.setLineItems(lineItems);
                    
                    RelateSupplier relateSupplier = new RelateSupplier();
                    //根据供应商编码获取信息
                    CommonBean commonBean = new CommonBean();
                    GongysBean gongysBean = commonBean.getSupplier2(rscode);
                    String rsnameStr = gongysBean.getName();
                    String rsnameId = gongysBean.getId();
                    List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
                    gongysAccountBeans = commonBean.getGaById(rsnameId);
                    gongysBean.setGongysAccountBeans(gongysAccountBeans);
                  //供应商编码名称
                    //供应商编码名称
                    relateSupplier.setSupplierCode(rscode);
                    relateSupplier.setSupplierName(rsnameStr);
                  //会计科目类型--判断成本中心编码是否以G开头,是为供应商,否为客户
                    if(rscode.startsWith("G")){
                    	relateSupplier.setAccountType("2");
                    }else{
                    	relateSupplier.setAccountType("3");
                    }
                    //金额 税额
                    relateSupplier.setSum(""+sumMoney);
                    relateSupplier.setInputTaxSum(""+sumFaxmoney);
                    relateSupplier.setInvoiceSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));

                    
                    List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();
                    relateSuppliers.add(relateSupplier);
                    item.setRelateSuppliers(relateSuppliers);
                    //业务场景
                    item.setPickingMode(pickingMode);
                    //业务发生时点
                    if(bzywsj.equals("bzywsj_dict_1")){
                        item.setBusinessHappenTimeFlag("1");
                    }else if(bzywsj.equals("bzywsj_dict_2")){
                        item.setBusinessHappenTimeFlag("2");
                    }
                   
                    List<PayMentItem> payMentItmes = new ArrayList<PayMentItem>();
                             //paymentType 
                             if(paymentType=="3" || paymentType=="4"){
                         		
                             	for(int g=0;g<gongysAccountBeans.size();g++){
                             		GongysAccountBean ga = gongysAccountBeans.get(g);
                             		PayMentItem payMentItem = new PayMentItem();
                             		payMentItem.setEmployeeBankAccount(ga.getAccount_no());
                             		payMentItem.setEmployeeName(ga.getAccountName());
                             		payMentItem.setBank(ga.getBank());
                             		payMentItem.setBankCode(ga.getBank_type());
                             		payMentItem.setPayeeCode(rscode);
                             		payMentItem.setAccountType("1");
                             		payMentItem.setPayeeType("K");
                             		payMentItem.setRowno(ga.getBrnch());
                             		payMentItem.setBankAddress("");
                             		payMentItem.setProvince("山东");
                             		payMentItem.setCity(ga.getCity());
                             		payMentItem.setCurrency("CN");
                             		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                             		payMentItmes.add(payMentItem);
                             	}
                         		
//                             	payMentItem.setEmployeeBankAccount("6222081613001150000");
//                         		payMentItem.setEmployeeName("韩勇");
//                         		payMentItem.setBank("中国工商银行滨州滨南支行");
//                         		payMentItem.setBankCode("102");
//                         		payMentItem.setPayeeCode("G000549854");
//                         		payMentItem.setAccountType("1");
//                         		payMentItem.setPayeeType("K");
//                         		payMentItem.setRowno("102466002453");
//                         		payMentItem.setBankAddress("中国工商银行滨州滨南支行");
//                         		payMentItem.setProvince("山东");
//                         		payMentItem.setCity("滨州");
//                         		payMentItem.setCurrency("CN");
//                         		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
//                         		payMentItmes.add(payMentItem);
//                         		
                             }
                             item.setPayMentItems(payMentItmes);
                    
                    item.setIsRealGift("0");
                    item.setRealGiftTaxSum("0.00");
                    item.setCurrency("CNY");
                    
                    List<BZItem> writeoffItems = new ArrayList<BZItem>();
                    //-----------------------------------------
                    writeoffItems.add(item);
                    
                    requestMessage.setWriteoffItems(writeoffItems);
            
                    ResponseMessage responseMessage = new ResponseMessage();
                    try
                    {
                        responseMessage = bzServiceImpl.baoZhang(jiekouUrl, requestMessage);
                    }
                    catch (Exception e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
            
                    
                    String tsbz_state = "0";//状态
                    String tsbz_costsum = "";//报账单号
                    String tsbz_appsum = "";//报账单号
                    if(responseMessage.getTYPE().equals("S")){
                        //如果报送成功，根据id获取报账单状态 选项：-4-等待生成报账单中;-3-报账单发起失败；-1-报账单删除，－2-退单；2-生成报账单成功；3-已生成SAP凭证
                        RequestMessageMenu requestMessage3 = new RequestMessageMenu();
                        List<MenuItem> menuItems = new ArrayList<MenuItem>();
                        MenuItem menuItem = new MenuItem();
                        menuItem.setOtherSystemMainId(otherSystemMainId); //8a0c9eb85fb3fb0f015fb45664fa02b7
                        menuItem.setWriteoffInstanceCode("");
                        menuItems.add(menuItem);
                        requestMessage3.setItems(menuItems);
                        ResponseMessageMenu responseMessage3 = new ResponseMessageMenu();
                        try
                        {
                            responseMessage3 = bzServiceImpl.GetMenuStatus(jiekouUrl,requestMessage3);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                  
                         tsbz_state=responseMessage3.getItems().get(0).getStatus();
                         if(tsbz_state.equals("2")){
                             tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                         }
                         if(tsbz_state.equals("3")){
                        	 tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                             tsbz_appsum=responseMessage3.getItems().get(0).getSpCertificateCode();
                         }
                    }
            //--------------------end-----------------
            
                    int acId=0;
                    if(!tsbz_state.equals("0")){
                      acId = bean.tsAllbz(jjsx, tsbz_state, costtime,
                       fillInOrgName, accountName,
                       tsbz_costsum, bztype, tsbz_appsum, rsnameStr, rscode, paytype,
                      bzfs, gangwei, ACCTIME, jjsx, yongtu, ACCTIME,otherSystemMainId,fillInOrgCode);
                    }
                    
            
            if (fylist != null && acId!=0)
            {
                int fycount = 0;
                if (fylist.size() > 0)
                {
                    fycount = ((Integer)fylist.get(0)).intValue();
                    
                }
                
                for (int k = fycount; k < fylist.size() - 1; k += fycount)
                {
                    
                    id = (String)fylist.get(k + fylist.indexOf("ID"));
                    state = (String)fylist.get(k + fylist.indexOf("STATE"));
                    compname = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
                    dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
                    dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
                    zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
                    zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
                    fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
                    fpcode = (String)fylist.get(k + fylist.indexOf("FPCODE"));
                    jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
                    starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
                    endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
                    rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
                    rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
                    money = (String)fylist.get(k + fylist.indexOf("MONEY"));
                    allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
                    faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
                    dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
                    price = (String)fylist.get(k + fylist.indexOf("PRICE"));
                    isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
                    ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
                    ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
                    ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
                    yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
                    shiname = (String)fylist.get(k + fylist.indexOf("SHINAME"));
                    shicode = (String)fylist.get(k + fylist.indexOf("SHICODE"));
                    eleid = (String)fylist.get(k + fylist.indexOf("ELEID"));
                    int asId = bean.tsbz(""+acId, shiname, dbname, dbcode, zdname, 
                            zdcode,  fpcode,fptype, starttime, endtime, 
                            ywname, rcname, rccode, ysname, yscode, 
                            allmoney, faxmoney, compname,id,shicode,dianliang);
                    bean.updateBz_soloState("1",id);
					
                    if(tsbz_state.equals("3")){
                		int m = bean.getCostBybzid(id);
                		if(m==0){
                			bean.updateBaoZhangState("4",eleid);
                		}
                    	
                    }
                }
            }
            if (acId >0) {
                msg = "推送报账成功！";
            } else  {
                msg = "推送失败！";
            }
            
            
            
            log.write(msg, accountid, req.getRemoteAddr(), "往财辅推送报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        else if(action.equals("tsbz_1")){//向财辅系统推送 普通报账
            url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "推送失败！请重试或与管理员联系！";
            
            //2017年11月22日 xiaokang modify
            String bzfs = (req.getParameter("bzfs")!= null && !"".equals(req.getParameter("bzfs"))) ? req.getParameter("bzfs") : "";
            String costtime = (req.getParameter("costtime")!= null && !"".equals(req.getParameter("costtime"))) ? req.getParameter("costtime") : "";
            String bztype = (req.getParameter("bztype")!= null && !"".equals(req.getParameter("bztype"))) ? req.getParameter("bztype") : "";
            String gangwei = (req.getParameter("gangwei")!= null && !"".equals(req.getParameter("gangwei"))) ? req.getParameter("gangwei") : "";
            String bzywsj = (req.getParameter("bzywsj")!= null && !"".equals(req.getParameter("bzywsj"))) ? req.getParameter("bzywsj") : "";
            String jjsx = (req.getParameter("jjsx")!= null && !"".equals(req.getParameter("jjsx"))) ? req.getParameter("jjsx") : "";
            String yongtu = (req.getParameter("yongtu")!= null && !"".equals(req.getParameter("yongtu"))) ? req.getParameter("yongtu") : "";
            String rsname = (req.getParameter("gysmc")!= null && !"".equals(req.getParameter("gysmc"))) ? req.getParameter("gysmc") : "";
            String rsname1 = (req.getParameter("rsname1")!= null && !"".equals(req.getParameter("rsname1"))) ? req.getParameter("rsname1") : "";
            String rscode = (req.getParameter("gysbm")!= null && !"".equals(req.getParameter("gysbm"))) ? req.getParameter("gysbm") : "";
            String paytype = (req.getParameter("paytype")!= null && !"".equals(req.getParameter("paytype"))) ? req.getParameter("paytype") : "";
            String ids = (req.getParameter("ids")!= null && !"".equals(req.getParameter("ids"))) ? req.getParameter("ids") : "";
            
             //获取所选择的报账信息
            SiteManage bean = new SiteManage();
          
            
            DecimalFormat df1 = new DecimalFormat("0.00");
            
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            double allMoney_sw=0;
            
            ArrayList fylist = new ArrayList();
            
            fylist = bean.getListByIds(ids);//查询报账信息
            
            String id = "", state = "", compname = "", dbname = "", dbcode = "", zdname = "", zdcode = "";
            String fptype = "", jffs = "", starttime = "", endtime = "", rcname = "", rccode = "",eleid="";
            String money = "", allmoney = "", faxmoney = "", dianliang = "", price = "", isbz = "", isbzname = "";
            String ywname = "", ywcode = "", ysname = "", yscode = "",fpcode="",shiname="",shicode="",fristDbName="",fristRcName="";
            double sumMoney = 0, sumFaxmoney = 0;
            if (fylist != null)
            {
                int fycount = 0;
                if (fylist.size() > 0)
                {
                    fycount = ((Integer)fylist.get(0)).intValue();
                    
                }
                
                int intnum = 1;
                for (int k = fycount; k < fylist.size() - 1; k += fycount)
                {
                    
                    id = (String)fylist.get(k + fylist.indexOf("ID"));
                    state = (String)fylist.get(k + fylist.indexOf("STATE"));
                    compname = (String)fylist.get(k + fylist.indexOf("COMPCODE"));
                    dbname = (String)fylist.get(k + fylist.indexOf("DBNAME"));
                    if(intnum == 1){
                        fristDbName=(String)fylist.get(k + fylist.indexOf("DBNAME"));
                        fristRcName = (String)fylist.get(k + fylist.indexOf("RCNAME"));

                    }
                    dbcode = (String)fylist.get(k + fylist.indexOf("DBCODE"));
                    zdname = (String)fylist.get(k + fylist.indexOf("ZDNAME"));
                    zdcode = (String)fylist.get(k + fylist.indexOf("ZDCODE"));
                    fptype = (String)fylist.get(k + fylist.indexOf("FPTYPE"));
                    fpcode = (String)fylist.get(k + fylist.indexOf("FPCODE"));
                    jffs = (String)fylist.get(k + fylist.indexOf("JFFS"));
                    starttime = (String)fylist.get(k + fylist.indexOf("STARTTIME"));
                    endtime = (String)fylist.get(k + fylist.indexOf("ENDTIME"));
                    rcname = (String)fylist.get(k + fylist.indexOf("RCNAME"));
                    rccode = (String)fylist.get(k + fylist.indexOf("RCCODE"));
                    money = (String)fylist.get(k + fylist.indexOf("MONEY"));
                    allmoney = (String)fylist.get(k + fylist.indexOf("ALLMONEY"));
                    faxmoney = (String)fylist.get(k + fylist.indexOf("FAXMONEY"));
                    dianliang = (String)fylist.get(k + fylist.indexOf("DIANLIANG"));
                    price = (String)fylist.get(k + fylist.indexOf("PRICE"));
                    isbz = (String)fylist.get(k + fylist.indexOf("ISBZ"));
                    ywname = (String)fylist.get(k + fylist.indexOf("YWTYPE"));
                    ywcode = (String)fylist.get(k + fylist.indexOf("YWCODE"));
                    ysname = (String)fylist.get(k + fylist.indexOf("YSNAME"));
                    yscode = (String)fylist.get(k + fylist.indexOf("YSCODE"));
                    shiname = (String)fylist.get(k + fylist.indexOf("SHINAME"));
                    shicode = (String)fylist.get(k + fylist.indexOf("SHICODE"));
                    eleid = (String)fylist.get(k + fylist.indexOf("ELEID"));
                    if (money != "")
                    {
                        sumMoney = sumMoney + Double.valueOf(money).doubleValue();
                    }
                    else
                    {
                        money = "0.00";
                    }
                    if (faxmoney != "")
                    {
                        sumFaxmoney = sumFaxmoney + Double.valueOf(faxmoney).doubleValue();
                    }
                    else
                    {
                        faxmoney = "0.00";
                    }
                    intnum++;
                    
                    
                  //调用接口
                    //--------------------begin-----------------
                    
                    BZServiceImpl bzServiceImpl = new BZServiceImpl();
                    RequestMessage requestMessage = new RequestMessage();
                    /*      requestMessage.setProcessCode("");
                            requestMessage.setProcessSuffixName("能耗系统集成");*/
                    
                            BZItem item = new BZItem();
                            //外围系统主单Id 每次都必须唯一
                            String otherSystemMainId = bzServiceImpl.getUUID();
                            item.setOtherSystemMainId(otherSystemMainId);
                            //登陆人账号
                            String accountName = account.getCthrnumber();
                            item.setAccount(accountName);
                            
                            //获取组织编码
                            String fillInOrgCode ="";
                            String fillInOrgName ="";
                            RequestMessageCode requestMessage2 = new RequestMessageCode();
                            List<CodeItem> items = new ArrayList<CodeItem>();
                            CodeItem codeItem = new CodeItem();
                            codeItem.setAccount(accountName);
                            items.add(codeItem);
                            requestMessage2.setItems(items);
                            ResponseMessageCode responseMessage2 = new ResponseMessageCode();
                            try
                            {
                                responseMessage2 = bzServiceImpl.GetOrgCode(jiekouUrl,requestMessage2);
                                if(responseMessage2.getErrorMsg().equals("")){
                                    fillInOrgCode = responseMessage2.getItems().get(0).getOrgAndRoles().get(0).getOrgCode();
                                    fillInOrgName = responseMessage2.getItems().get(0).getPrincipalName();
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            
                            item.setFillInName(fillInOrgName);
                            item.setFillInOrgCode(fillInOrgCode);
                            
                            
                            //公司代码?????????????
                            String sapCompayCode =compname;
                            item.setSapCompayCode(compname);
                            //经济事项
                            item.setEconomicItemCode(jjsx);
                            item.setEconomicItemName("电费");
                            //收支方式
                            String paymentType="";
                            if(paytype.equals("paytype_dict_1")){
                                paymentType="1";
                            }else if(paytype.equals("paytype_dict_2")){
                                paymentType="2";
                            }else if(paytype.equals("paytype_dict_3")){
                                paymentType="3";
                            }else if(paytype.equals("paytype_dict_4")){
                                paymentType="4";
                            }else{
                                paymentType="8";
                            }
                            item.setPaymentType(paymentType);
                            //费用发生日
                            item.setHappenDate(ACCTIME);
                            // 列账属性
                            String budgetType="";
                           //业务类型
                            String bizType="";
                            //是否员工代垫
                            String isStaffPayment="";
                            //业务场景
                            String pickingMode="";
                            if(bzfs.equals("bzfs_dict_1")){//报账方式
                                budgetType="1";
                                bizType="0";
                                isStaffPayment="";
                                pickingMode="1";
                            }else  if(bzfs.equals("bzfs_dict_2")){
                                budgetType="2";
                                bizType="1";
                                isStaffPayment="0";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_3")){
                                budgetType="1";
                                bizType="0";
                                isStaffPayment="";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_4")){
                                budgetType="1";
                                bizType="2";
                                isStaffPayment="0";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_5")){
                                budgetType="1";
                                bizType="2";
                                isStaffPayment="0";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_6")){
                                budgetType="2";
                                bizType="1";
                                isStaffPayment="0";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_7")){
                                budgetType="1";
                                bizType="0";
                                isStaffPayment="";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_8")){
                            	  budgetType="1";
                                  bizType="2";
                                  isStaffPayment="0";
                                  pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_9")){
                                budgetType="1";
                                bizType="2";
                                isStaffPayment="1";
                                pickingMode="";
                            }else  if(bzfs.equals("bzfs_dict_10")){
                                budgetType="1";
                                bizType="0";
                                isStaffPayment="";
                                pickingMode="1";
                            }else  if(bzfs.equals("bzfs_dict_11")){
                                budgetType="";
                                bizType="1";
                                isStaffPayment="1";
                                pickingMode="";
                            }
                           item.setBizType(bizType);
                            //报账期间
                            item.setBudgetSet(costtime);
                            //是否员工代垫
                            item.setIsStaffPayment(isStaffPayment);
                            //item.setContractNo("");
                            //item.setContractName("");
                            item.setSum(""+(Double.valueOf(money).doubleValue() + Double.valueOf(faxmoney).doubleValue()));
                            item.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" 等的电费(新能耗)");
                            //发票
                            String invoiceType="";
                            if(fpcode.equals("pjlx01")){
                                invoiceType="1";
                            }else if(fpcode.equals("pjlx02")){
                                invoiceType="2";
                            }else if(fpcode.equals("pjlx03")){
                                invoiceType="3";
                            }else if(fpcode.equals("pjlx04")){
                                invoiceType="4";
                            }else if(fpcode.equals("pjlx05")){
                                invoiceType="5";
                            }else if(fpcode.equals("pjlx06")){
                                invoiceType="6";
                            }else if(fpcode.equals("pjlx08")){
                                invoiceType="8";
                            }else if(fpcode.equals("pjlx09")){
                                invoiceType="9";
                            }else if(fpcode.equals("pjlx10")){
                                invoiceType="10";
                            }else if(fpcode.equals("pjlx11")){
                                invoiceType="11";
                            }else if(fpcode.equals("pjlx12")){
                                invoiceType="12";
                            }
                            item.setInvoiceType(invoiceType);
                            
                            
                           	 item.setIsNeedImage("1");
                          
                            List<BZLineItem> lineItems = new ArrayList<BZLineItem>();
                            BZLineItem lineItem = new BZLineItem();
                            //外围系统子单Id
                            String otherSystemDetailId = bzServiceImpl.getUUID();
                            lineItem.setOtherSystemDetailId(otherSystemDetailId);
                            
                            lineItem.setUsageCode(yongtu);
                            //lineItem.setUsageName("");
                            //列账属性
                            lineItem.setBudgetType(budgetType);
                            //子单金额
                            lineItem.setSum(""+money);
                            //摘要
                         //  lineItem.setDesc("");
                            lineItem.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" 的电费");
                          //预算科目编码 名称
                            lineItem.setBudgetItemCode("CW1000");
                            lineItem.setBudgetItemName("能源费");
                            /*//预算科目编码 名称 -----旧的
                            lineItem.setBudgetItemCode("");
                            lineItem.setBudgetItemName("");*/
                            //预算责任中心编码
                            lineItem.setBudgetOrgCode(yscode);
                            lineItem.setBudgetOrgName(ysname);
                            //成本中心编码名称
//                            lineItem.setSapCostCenterCode("A370108000");
//                            lineItem.setSapCostCenterName("股份济南商河县分公司本部（成本）");
                            lineItem.setSapCostCenterCode(rccode);
                            lineItem.setSapCostCenterName(rcname);
                            //数量
                            lineItem.setCount(dianliang);
                            //价格
                            lineItem.setPrice(price);
                            //单位
                            lineItem.setUnit("01");
                            lineItem.setCurrency("CNY");
                            lineItem.setExchangeRate("1.0");
                            
                            lineItem.setCurrencySum(""+money);
                            //lineItem.setChargecode("");
                            
                          
                            lineItems.add(lineItem);
                            
                            if(invoiceType.equals("1") || invoiceType.equals("4") || 
                                    invoiceType.equals("5") || invoiceType.equals("6") || 
                                    invoiceType.equals("11")){
                                
                                BZLineItem lineItem_ = new BZLineItem();
                                //外围系统子单Id
                                String otherSystemDetailId2 = bzServiceImpl.getUUID();
                                lineItem_.setOtherSystemDetailId(otherSystemDetailId2);
                                
                                lineItem_.setUsageCode("U888");
                                //lineItem.setUsageName("");
                                //列账属性
                                lineItem_.setBudgetType("2");
                                //子单金额
                                lineItem_.setSum(""+sumFaxmoney);
                                //摘要
                                lineItem_.setDesc("进项税");
                              //预算科目编码 名称
                                lineItem.setBudgetItemCode("CW1000");
                                lineItem.setBudgetItemName("能源费");
                                /*//预算科目编码 名称   -------旧的
                                lineItem_.setBudgetItemCode("");
                                lineItem_.setBudgetItemName("");*/
                              //预算责任中心编码
                                lineItem_.setBudgetOrgCode(yscode);
                                lineItem_.setBudgetOrgName(ysname);
                                //成本中心编码名称
//                                lineItem.setSapCostCenterCode("A370108000");
//                                lineItem.setSapCostCenterName("股份济南商河县分公司本部（成本）");
                                lineItem_.setSapCostCenterCode(rccode);
                                lineItem_.setSapCostCenterName(rcname);
                                //数量
                                lineItem_.setCount(dianliang);
                                //价格
                                lineItem_.setPrice(price);
                                //单位
                                lineItem_.setUnit("01");
                                lineItem_.setCurrency("CNY");
                                lineItem_.setExchangeRate("1.0");
                                
                                lineItem_.setCurrencySum(""+sumFaxmoney);
                                //lineItem.setChargecode("");
                                
                              
                                lineItems.add(lineItem_);
                            }
                             item.setLineItems(lineItems);
                             
                             RelateSupplier relateSupplier = new RelateSupplier();
                             //根据供应商编码获取信息
                             CommonBean commonBean = new CommonBean();
                             GongysBean gongysBean = commonBean.getSupplier2(rscode);
                             String rsnameStr = gongysBean.getName();
                             String rsnameId = gongysBean.getId();
                             List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
                             gongysAccountBeans = commonBean.getGaById(rsnameId);
                             gongysBean.setGongysAccountBeans(gongysAccountBeans);
                             
                           //供应商编码名称
                             relateSupplier.setSupplierCode(rscode);
                             relateSupplier.setSupplierName(rsnameStr);
                             BZLineItem lineItem_;
							//会计科目类型--判断成本中心编码是否以G开头,是为供应商,否为客户
                             if(rscode.startsWith("G")){
                             	relateSupplier.setAccountType("2");
                             }else{
                             	relateSupplier.setAccountType("3");
                             }
                             //金额 税额
                             relateSupplier.setSum(""+money);
                             relateSupplier.setInputTaxSum(""+faxmoney);
                             relateSupplier.setInvoiceSum(""+( Double.valueOf(money).doubleValue()+  Double.valueOf(faxmoney).doubleValue()));

                             
                             List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();
                             relateSuppliers.add(relateSupplier);
                             item.setRelateSuppliers(relateSuppliers);
                             //业务场景
                             item.setPickingMode(pickingMode);
                             //业务发生时点
                             if(bzywsj.equals("bzywsj_dict_1")){
                                 item.setBusinessHappenTimeFlag("1");
                             }else if(bzywsj.equals("bzywsj_dict_2")){
                                 item.setBusinessHappenTimeFlag("2");
                             }
                            
                             List<PayMentItem> payMentItmes = new ArrayList<PayMentItem>();
                                      //paymentType 
                                      if(paymentType=="3" || paymentType=="4"){
                                  		
                                      	for(int g=0;g<gongysAccountBeans.size();g++){
                                      		GongysAccountBean ga = gongysAccountBeans.get(g);
                                      		PayMentItem payMentItem = new PayMentItem();
                                      		payMentItem.setEmployeeBankAccount(ga.getAccount_no());
                                      		payMentItem.setEmployeeName(ga.getAccountName());
                                      		payMentItem.setBank(ga.getBank());
                                      		payMentItem.setBankCode(ga.getBank_type());
                                      		payMentItem.setPayeeCode(rscode);
                                      		payMentItem.setAccountType("1");
                                      		payMentItem.setPayeeType("K");
                                      		payMentItem.setRowno(ga.getBrnch());
                                      		payMentItem.setBankAddress("");
                                      		payMentItem.setProvince("山东");
                                      		payMentItem.setCity(ga.getCity());
                                      		payMentItem.setCurrency("CN");
                                      		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                                      		payMentItmes.add(payMentItem);
                                      	}
                                  		
//                                      	payMentItem.setEmployeeBankAccount("6222081613001150000");
//                                  		payMentItem.setEmployeeName("韩勇");
//                                  		payMentItem.setBank("中国工商银行滨州滨南支行");
//                                  		payMentItem.setBankCode("102");
//                                  		payMentItem.setPayeeCode("G000549854");
//                                  		payMentItem.setAccountType("1");
//                                  		payMentItem.setPayeeType("K");
//                                  		payMentItem.setRowno("102466002453");
//                                  		payMentItem.setBankAddress("中国工商银行滨州滨南支行");
//                                  		payMentItem.setProvince("山东");
//                                  		payMentItem.setCity("滨州");
//                                  		payMentItem.setCurrency("CN");
//                                  		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
//                                  		payMentItmes.add(payMentItem);
//                                  		
                                      }
                                      item.setPayMentItems(payMentItmes);
                             
                             item.setIsRealGift("0");
                             item.setRealGiftTaxSum("0.00");
                             item.setCurrency("CNY");
                             
                             List<BZItem> writeoffItems = new ArrayList<BZItem>();
                             writeoffItems.add(item);
                             
                             requestMessage.setWriteoffItems(writeoffItems);
                     
                             ResponseMessage responseMessage = new ResponseMessage();
                             try
                             {
                            	 //
                                 responseMessage = bzServiceImpl.baoZhang(jiekouUrl, requestMessage);
                             }
                             catch (Exception e)
                             {
                                 // TODO Auto-generated catch block
                                 e.printStackTrace();
                             }
                             String tsbz_state = "0";//状态
                             String tsbz_costsum = "";//报账单号
                             String tsbz_appsum = "";//报账单号
                             if(responseMessage.getTYPE().equals("S")){
                                 //如果报送成功，根据id获取报账单状态 选项：-4-等待生成报账单中;-3-报账单发起失败；-1-报账单删除，－2-退单；2-生成报账单成功；3-已生成SAP凭证
                                 RequestMessageMenu requestMessage3 = new RequestMessageMenu();
                                 List<MenuItem> menuItems = new ArrayList<MenuItem>();
                                 MenuItem menuItem = new MenuItem();
                                 menuItem.setOtherSystemMainId(otherSystemMainId); //8a0c9eb85fb3fb0f015fb45664fa02b7
                                 menuItem.setWriteoffInstanceCode("");
                                 menuItems.add(menuItem);
                                 requestMessage3.setItems(menuItems);
                                 ResponseMessageMenu responseMessage3 = new ResponseMessageMenu();
                                 try
                                 {
                                	 //
                                     responseMessage3 = bzServiceImpl.GetMenuStatus(jiekouUrl,requestMessage3);
                                 }
                                 catch (Exception e)
                                 {
                                     e.printStackTrace();
                                 }
//                                 
                                  tsbz_state=responseMessage3.getItems().get(0).getStatus();
                                  if(tsbz_state.equals("2")){
                                      tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                                  }else if(tsbz_state.equals("3")){
                                	  tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                                      tsbz_appsum=responseMessage3.getItems().get(0).getSpCertificateCode();
                                     
                                  }
                             }
                             

                             int acId=0;
                             if(!tsbz_state.equals("0")){
                               acId = bean.tsAllbz(jjsx, tsbz_state, costtime,
                                fillInOrgName, accountName,
                                tsbz_costsum, bztype, tsbz_appsum, rsnameStr, rscode, paytype,
                               bzfs, gangwei, ACCTIME, jjsx, yongtu, ACCTIME,otherSystemMainId,fillInOrgCode);
                             
                             }
                             if(acId>0){
                                 msg = "推送报账成功！";
                                 int asId = bean.tsbz(""+acId, shiname, dbname, dbcode, zdname, 
                                         zdcode,  fpcode,fptype, starttime, endtime, 
                                         ywname, rcname, rccode, ysname, yscode, 
                                         allmoney, faxmoney, compname,id,shicode,dianliang);
                                 bean.updateBz_soloState("1",id);
                                 if(tsbz_state.equals("3")){
                             		int m = bean.getCostBybzid(id);
                             		if(m==0){
                             			bean.updateBaoZhangState("4",eleid);
                             		}
                                 	
                                 }
                                 
                             }else{
                                 msg = "推送失败！";
                             }
                           
                    
                }
            }
            
                    
                    
            //--------------------end-----------------
            
                    
            
//            if (acId >0) {
//                msg = "推送报账成功！";
//            } else  {
//                msg = "推送失败！";
//            }
            
            
            
            log.write(msg, accountid, req.getRemoteAddr(), "往财辅推送报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        
        }
        else if(action.equals("addAllbz")){ //发起报账
            
            String[] ids = req.getParameterValues("itemSelected");
           
            
            url = path + "/web/sdttWeb/baozhang/addbz.jsp";
            
            SiteManage bean = new SiteManage();
            ArrayList listBz = new ArrayList();
         //   listBz = bean.getListByIds(ids);

            log.write(msg, accountid, req.getRemoteAddr(), "添加报账");
            session.setAttribute("listBz", listBz);
            resp.sendRedirect(url);
        }else if(action.equals("updateAllcost")){ //报账信息更新
        	url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            msg = "已报账信息更新失败！请重试或与管理员联系！";
            String[] ids = req.getParameterValues("itemSelected");
            SiteManage sm = new SiteManage();
            BZServiceImpl bzServiceImpl = new BZServiceImpl();
            if(ids!=null){
                for(int i=0;i<ids.length;i++){
                	  AcSoloBean acSoloBean = sm.getAllcostByIs(ids[i]);
                	  String tsbz_state = "0";//状态
                      String tsbz_costsum = "";//报账单号
                      String tsbz_appsum = "";//报账单号
                	if(acSoloBean!=null){
                        //如果报送成功，根据id获取报账单状态 选项：-4-等待生成报账单中;-3-报账单发起失败；-1-报账单删除，－2-退单；2-生成报账单成功；3-已生成SAP凭证
                        RequestMessageMenu requestMessage3 = new RequestMessageMenu();
                        List<MenuItem> menuItems = new ArrayList<MenuItem>();
                        MenuItem menuItem = new MenuItem();
                        menuItem.setOtherSystemMainId(acSoloBean.getOthersystemmainid()); //8a0c9eb85fb3fb0f015fb45664fa02b7
                        menuItem.setWriteoffInstanceCode("");
                        menuItems.add(menuItem);
                        requestMessage3.setItems(menuItems);
                        ResponseMessageMenu responseMessage3 = new ResponseMessageMenu();
                        try
                        {
                        	//接口返回报文
                            responseMessage3 = bzServiceImpl.GetMenuStatus(jiekouUrl,requestMessage3);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                  
                         tsbz_state=responseMessage3.getItems().get(0).getStatus();
                         if(tsbz_state.equals("2")){
                             tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                         }
                         if(tsbz_state.equals("3")){
                        	 tsbz_costsum=responseMessage3.getItems().get(0).getWriteoffInstanceCode();
                             tsbz_appsum=responseMessage3.getItems().get(0).getSpCertificateCode();
                         }
                         //报账单状态   ≠0 时更新成功
                         if(!tsbz_state.equals("0")){
                        	 
                        	int m = sm.updateAllCost(acSoloBean.getAllcostid(),tsbz_state,tsbz_costsum, tsbz_appsum);
	                          if(m==1){
	                        	  msg = "已报账信息更新成功！";
	                          }
	                         //返回为3-已生成SAP凭证时,更新电量表状态为 4-已生成SAP凭证
	                          if(tsbz_state.equals("3")){
	                      		int ms = sm.getCostBybzid(acSoloBean.getBzid());
	                      		if(ms==0){
	                      			String eleid = sm.getELEIDBybzid(acSoloBean.getBzid());
	                      			String mon = tsbz_costsum.substring(18, 20);
	                      			String year = "20"+mon;
	                      			sm.updateBaoZhangStateEle(year,"4",eleid);
	                      		}
	                          }
	                          
                         }
                         
                         
                    }
                	
                	
                }
                
               //报账失败数据查询 add by guol 2018-03-13                        ↓
               ArrayList failList =  sm.getAcSoloFailure(StringUtils.join(ids, ","));
               if(failList !=null){
            	   int count_f = 0;
            	    if(failList.size()>0){
            	    	count_f = ((Integer)failList.get(0)).intValue();
            	    }
            	    String id="", dbname="",dbcode="", allcostid="" ,bzid="";
            	    for(int i=count_f; i<failList.size()-1; i+=count_f){
            	    	id = (String)failList.get(i + failList.indexOf("ID"));
            	    	allcostid = (String)failList.get(i + failList.indexOf("ALLCOSTID"));
            	    	bzid = (String)failList.get(i + failList.indexOf("BZID"));
            	    	dbcode = (String)failList.get(i + failList.indexOf("DBCODE"));
            	    	sm.updateBz_soloState("0", bzid);//重新报账
            	    }
               }
             //-------------------------------------------------------                
                
            }
         
           
            log.write(msg, accountid, req.getRemoteAddr(), "更新报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("updateBaozhangtieta")){
        	doPost1(req, resp,2);
        }
        else if(action.equals("updateBaozhang")){ //添加站点  在用
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "修改报账失败！请重试或与管理员联系！";
            
            //判断是否回转
            String huizhuan = (req.getParameter("hz")!= null && !"".equals(req.getParameter("hz"))) ? req.getParameter("hz") : "0";
            
            //2017年11月22日 xiaokang modify
            String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
            //2018年3月9日 - gcl 税额
            String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//税额
            String TAXchange = (req.getParameter("TAXchange")!= null && !"".equals(req.getParameter("TAXchange"))) ? req.getParameter("TAXchange") : "0";//修改前的税额
            String TAXSTATE = (req.getParameter("TAXSTATE")!= null && !"".equals(req.getParameter("TAXSTATE"))) ? req.getParameter("TAXSTATE") : "0";//税额状态
           
            String ID = (req.getParameter("ID")!= null && !"".equals(req.getParameter("ID"))) ? req.getParameter("ID") : "0";
            String DIANBIAOID = (req.getParameter("DIANBIAOID")!= null && !"".equals(req.getParameter("DIANBIAOID"))) ? req.getParameter("DIANBIAOID") : "0";
            String STARTTIME = (req.getParameter("STARTTIME")!= null && !"".equals(req.getParameter("STARTTIME"))) ? req.getParameter("STARTTIME") : null;
            String ENDTIME = (req.getParameter("ENDTIME")!= null && !"".equals(req.getParameter("ENDTIME"))) ? req.getParameter("ENDTIME") : null;
            String SQDS = (req.getParameter("SQDS")!= null && !"".equals(req.getParameter("SQDS"))) ? req.getParameter("SQDS") : "0";
            String BQDS = (req.getParameter("BQDS")!= null && !"".equals(req.getParameter("BQDS"))) ? req.getParameter("BQDS") : "0";
            String DIANLIANG = (req.getParameter("DIANLIANG")!= null && !"".equals(req.getParameter("DIANLIANG"))) ? req.getParameter("DIANLIANG") : "0";
            String ALLMONEY = (req.getParameter("ALLMONEY")!= null && !"".equals(req.getParameter("ALLMONEY"))) ? req.getParameter("ALLMONEY") : "0";
            String DIANSUN = (req.getParameter("DIANSUN")!= null && !"".equals(req.getParameter("DIANSUN"))) ? req.getParameter("DIANSUN") : "0";
            String SQDJ = (req.getParameter("SQDJ")!= null && !"".equals(req.getParameter("SQDJ"))) ? req.getParameter("SQDJ") : "0";
            String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
            String DAYNUM = (req.getParameter("TIANSHU")!= null && !"".equals(req.getParameter("TIANSHU"))) ? req.getParameter("TIANSHU") : "0";
            String RJYDL = (req.getParameter("RJYDL")!= null && !"".equals(req.getParameter("RJYDL"))) ? req.getParameter("RJYDL") : "0";
            String SQRJYDL = (req.getParameter("SQRJYDL")!= null && !"".equals(req.getParameter("SQRJYDL"))) ? req.getParameter("SQRJYDL") : "0";
            String PUEZHI = (req.getParameter("PUEZHI")!= null && !"".equals(req.getParameter("PUEZHI"))) ? req.getParameter("PUEZHI") : "0";
            String BGDL = (req.getParameter("BGDL")!= null && !"".equals(req.getParameter("BGDL"))) ? req.getParameter("BGDL") : "0";
            String FTJE = (req.getParameter("FTJE")!= null && !"".equals(req.getParameter("FTJE"))) ? req.getParameter("FTJE") : "0";
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2018年3月30日-gcl-修改创建时间
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            String ACCTIMEYYMM =sdf2.format(new Date());
            SiteManage bean = new SiteManage();
            CommonBean commonBean = new CommonBean();
            
            if(saveState.equals("0")){
            	
            	
            	String flowId=req.getParameter("flowId");
    			String auditorid=req.getParameter("auditorid");
    			
				if (auditorid != null && !auditorid.isEmpty()) {
					int retsign = bean.updateDataBaoZhang(TAXchange,TAXSTATE,TAX,ID, DIANBIAOID,
							STARTTIME, ENDTIME, SQDS, BQDS, DIANLIANG,
							ALLMONEY, DIANSUN, SQDJ, PRICE, DAYNUM, RJYDL,
							SQRJYDL, "1", ACCOUNTID, ACCTIME, PUEZHI,FTJE, BGDL);
					if (retsign == 1) {
						System.out.println("dianbiao huizhuan-------------0"+huizhuan);
                    	if(!huizhuan.equals("0")){
//                    		System.out.println("电表回转修改电表相应字段-HZSTATE");
                    		String sql = "update dianbiao d set d.HZSTATE = "+huizhuan+" where d.id = "+DIANBIAOID;
                    		System.out.println("x修改报账电表回转修改相应字段"+sql.toString());
                    		DataBase db = new DataBase();
                    		try {
								try {
									db.update(sql);
									db.commit();
								} catch (DbException e) {
									e.printStackTrace();
								}
							}  finally {
								try {
									if(db != null){
									db.close();
									}
								} catch (Exception de) {
									de.printStackTrace();
								}
							}
                    	}
						msg = "提交报账成功！";
					} else {
						msg = "提交失败！报账代码重复！";
					}
					// 添加流程
					WorkFlowBean workFlowBean = new WorkFlowBean();
					String currentNo = workFlowBean.getNextStep(flowId, "1");
					String nextNo = workFlowBean.getNextStep(flowId, "2");
					String taskId = ID + "";
					String taskType = "ELECTRICFEES";
					workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo,
							account.getAccountId(), taskType, auditorid);
					
					//添加pue记录 
                    List<String> strList = commonBean.getstationByDbId(DIANBIAOID);
                    
                   String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="";
                   if(strList!=null && strList.size()==4){
                   	st_id_str = strList.get(0);
                   	jiaoliu_str = strList.get(1);
                   	zhiliu_str = strList.get(2);
                   	zdid_str = strList.get(3);
                   }
                   List<String> strList2 = commonBean.getSumByDbId(st_id_str,STARTTIME, ENDTIME);
                   String sum_str = "",value_str="",sumDay_str="";
                   if(strList2!=null && strList2.size()==3){
                   	sum_str = strList2.get(0);
                   	value_str = strList2.get(1);
                   	sumDay_str = strList2.get(2);
                   }
                   bean.addAnalysis(taskId, ACCTIMEYYMM, ACCTIME, DIANLIANG,
                   		sum_str, DIANBIAOID, zdid_str, STARTTIME, 
                   		ENDTIME,value_str,jiaoliu_str,zhiliu_str,sumDay_str);

				}
            	
            	
            	
            	
            	//判断有无执行人
                /*WorkFlowBean workFlowBean= new WorkFlowBean();
                String flowId=workFlowBean.getFlowId("报账审核");
                String currentNo =workFlowBean.getNextStep(flowId, "1");
                String nextNo =workFlowBean.getNextStep(flowId, "2");
                ArrayList l = new ArrayList();
                DianBiaoBean dianbiaoBean= new DianBiaoBean();
                String XIAN =  dianbiaoBean.getZDXian(DIANBIAOID);
                l=workFlowBean.getAccountByActionId2(currentNo, XIAN);
                String auditorid="";
                if (l != null) {
                    int cscount = ((Integer) l.get(0)).intValue();
                    for (int i = cscount; i < l.size() - 1; i += cscount) {
                        auditorid = (String) l.get(i + l.indexOf("ACCOUNTID"));
                    }
                    if(auditorid.equals("")){
                    	msg = "未找到执行人！请确认后再添加！！";
                    	//return;
                    }else {
                    	   
                        int retsign = bean.updateDataBaoZhang(ID,DIANBIAOID,
                        		STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,
                        		ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,
                        		SQRJYDL,"1",ACCOUNTID,ACCTIME,PUEZHI);

                        if (retsign == 1) {
                             msg = "提交报账成功！";
                         } else  {
                             msg = "提交失败！报账代码重复！";
                         }
                         
                         
                         //添加流程
                        
                         String taskId=ID+"";
                         String taskType="ELECTRICFEES";
                         workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
                         
                         //添加pue记录 
                         List<String> strList = commonBean.getstationByDbId(DIANBIAOID);
                         
                        String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="";
                        if(strList!=null && strList.size()==4){
                        	st_id_str = strList.get(0);
                        	jiaoliu_str = strList.get(1);
                        	zhiliu_str = strList.get(2);
                        	zdid_str = strList.get(3);
                        }
                        List<String> strList2 = commonBean.getSumByDbId(st_id_str,STARTTIME, ENDTIME);
                        String sum_str = "",value_str="",sumDay_str="";
                        if(strList2!=null && strList2.size()==3){
                        	sum_str = strList2.get(0);
                        	value_str = strList2.get(1);
                        	sumDay_str = strList2.get(2);
                        }
                        bean.addAnalysis(taskId, ACCTIMEYYMM, ACCTIME, DIANLIANG,
                        		sum_str, DIANBIAOID, zdid_str, STARTTIME, 
                        		ENDTIME,value_str,jiaoliu_str,zhiliu_str,sumDay_str);
                    }
                }*/
                	 
            }else{
            	   
                int retsign = bean.updateDataBaoZhang(TAXchange,TAXSTATE,TAX,ID,DIANBIAOID,
                		STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,
                		ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,
                		RJYDL,SQRJYDL,"0",ACCOUNTID,ACCTIME,PUEZHI,FTJE,BGDL);

                if (retsign == 1) {
                    msg = "修改报账成功！";
                } else if (retsign == 2) {
                    msg = "修改失败！报账代码重复！";
                }
            }
            
            
         
            log.write(msg, accountid, req.getRemoteAddr(), "修改报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        else if(action.equals("addSite_new")){ //添加站点  在用
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/jizan/sitemanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "添加站点失败！请重试或与管理员联系！";
            
            String LSCID = (req.getParameter("LSCID")!= null && !"".equals(req.getParameter("LSCID"))) ? req.getParameter("LSCID") : "0";
            String AREA_ID = (req.getParameter("shi")!= null && !"".equals(req.getParameter("shi"))) ? req.getParameter("shi") : "0";
            String DISTRICT_ID = (req.getParameter("xian")!= null && !"".equals(req.getParameter("xian"))) ? req.getParameter("xian") : "0";
            String COUNTRY_ID = (req.getParameter("xiaoqu")!= null && !"".equals(req.getParameter("xiaoqu"))) ? req.getParameter("xiaoqu") : "0";
            String FULL_STATION_CODE = req.getParameter("FULL_STATION_CODE")!= null ? req.getParameter("FULL_STATION_CODE") : "";
            String STATION_NAME = req.getParameter("STATION_NAME")!= null ? req.getParameter("STATION_NAME") : "";
            String STATION_CODE = req.getParameter("STATION_CODE")!= null ? req.getParameter("STATION_CODE") : "";
            String SERVICE_ID = req.getParameter("SERVICE_ID")!= null ? req.getParameter("SERVICE_ID") : "";
            String RANK_ID = req.getParameter("RANK_ID")!= null ? req.getParameter("RANK_ID") : "";
            String PROPERTY_ID = (req.getParameter("PROPERTY_ID")!= null && !"".equals(req.getParameter("PROPERTY_ID"))) ? req.getParameter("PROPERTY_ID") : "0";
            String MAINTAIN_ID = (req.getParameter("MAINTAIN_ID")!= null && !"".equals(req.getParameter("MAINTAIN_ID"))) ? req.getParameter("MAINTAIN_ID") : "0";
            String ADDRESS = req.getParameter("ADDRESS")!= null  ? req.getParameter("ADDRESS") : "";
            String LONGITUDE = (req.getParameter("LONGITUDE")!= null && !"".equals(req.getParameter("LONGITUDE"))) ? req.getParameter("LONGITUDE") : "0";
            String LATITUDE = (req.getParameter("LATITUDE")!= null && !"".equals(req.getParameter("LATITUDE"))) ? req.getParameter("LATITUDE") : "0";
            String COVERED_AREA = (req.getParameter("COVERED_AREA")!= null && !"".equals(req.getParameter("COVERED_AREA"))) ? req.getParameter("COVERED_AREA") : "0";
            String OCCUPIED_AREA = (req.getParameter("OCCUPIED_AREA")!= null && !"".equals(req.getParameter("OCCUPIED_AREA"))) ? req.getParameter("OCCUPIED_AREA") : "0";
            String RENT = (req.getParameter("RENT")!= null && !"".equals(req.getParameter("RENT"))) ? req.getParameter("RENT") : "0";
            String HIRE_TIME = req.getParameter("HIRE_TIME")!= null ? req.getParameter("HIRE_TIME") : null;
            String PERIOD = (req.getParameter("PERIOD")!= null && !"".equals(req.getParameter("PERIOD"))) ? req.getParameter("PERIOD") : "0";
            String RENT_ID = (req.getParameter("RENT_ID")!= null && !"".equals(req.getParameter("RENT_ID"))) ? req.getParameter("RENT_ID") : "0";
            String LEASE_ID = (req.getParameter("LEASE_ID")!= null && !"".equals(req.getParameter("LEASE_ID"))) ? req.getParameter("LEASE_ID") : "0";
            String LATEST_DATE = req.getParameter("LATEST_DATE")!= null ? req.getParameter("LATEST_DATE") : "";
            String POWER_ID = (req.getParameter("POWER_ID")!= null && !"".equals(req.getParameter("POWER_ID"))) ? req.getParameter("POWER_ID") : "0";
            String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
            String ELECTRICITYRATIO = (req.getParameter("ELECTRICITYRATIO")!= null && !"".equals(req.getParameter("ELECTRICITYRATIO"))) ? req.getParameter("ELECTRICITYRATIO") : "0";
            String ISSUPERVISOR = req.getParameter("ISSUPERVISOR")!= null  ? req.getParameter("ISSUPERVISOR") : "";
            String STATUS = req.getParameter("STATUS")!= null ? req.getParameter("STATUS") : "";
            String BATTERYUSELENGTH = (req.getParameter("BATTERYUSELENGTH")!= null && !"".equals(req.getParameter("BATTERYUSELENGTH"))) ? req.getParameter("BATTERYUSELENGTH") : "0";
            String ISCOUNTRY = req.getParameter("ISCOUNTRY")!= null ? req.getParameter("ISCOUNTRY") : "";
            String SOURCESYSTEMSTATIONTYPEID = (req.getParameter("SOURCESYSTEMSTATIONTYPEID")!= null && !"".equals(req.getParameter("SOURCESYSTEMSTATIONTYPEID"))) ? req.getParameter("SOURCESYSTEMSTATIONTYPEID") : "0";
            String SOURCESYSTEMSTATIONLEVELID = (req.getParameter("SOURCESYSTEMSTATIONLEVELID")!= null && !"".equals(req.getParameter("SOURCESYSTEMSTATIONLEVELID"))) ? req.getParameter("SOURCESYSTEMSTATIONLEVELID") : "0";
            String HOUSETYPEID = (req.getParameter("HOUSETYPEID")!= null && !"".equals(req.getParameter("HOUSETYPEID"))) ? req.getParameter("HOUSETYPEID") : "0";
            String ISUSERCONFIRM = (req.getParameter("ISUSERCONFIRM")!= null && !"".equals(req.getParameter("ISUSERCONFIRM"))) ? req.getParameter("ISUSERCONFIRM") : "";
            String USERSTATIONNAME = req.getParameter("USERSTATIONNAME")!= null ? req.getParameter("USERSTATIONNAME") : "";
            String LEAF_AREAID = (req.getParameter("LEAF_AREAID")!= null && !"".equals(req.getParameter("LEAF_AREAID"))) ? req.getParameter("LEAF_AREAID") : "0";
            String EDITTIME = req.getParameter("EDITTIME")!= null ? req.getParameter("EDITTIME") : null;
            String USEDSPECIALTY = req.getParameter("USEDSPECIALTY")!= null ? req.getParameter("USEDSPECIALTY") : "";
            String GROUPRANK_ID = (req.getParameter("GROUPRANK_ID")!= null && !"".equals(req.getParameter("GROUPRANK_ID"))) ? req.getParameter("GROUPRANK_ID") : "0";
            String CC08_SUPERVISORY = (req.getParameter("CC08_SUPERVISORY")!= null && !"".equals(req.getParameter("CC08_SUPERVISORY"))) ? req.getParameter("CC08_SUPERVISORY") : "";
            String STATION_ID_INSERT_DATE = req.getParameter("STATION_ID_INSERT_DATE")!= null ? req.getParameter("STATION_ID_INSERT_DATE") : null;
            String SUPERVISOR_DATE = req.getParameter("SUPERVISOR_DATE")!= null ? req.getParameter("SUPERVISOR_DATE") : null;
            String SUPERVISOR_TYPE = (req.getParameter("SUPERVISOR_TYPE")!= null && !"".equals(req.getParameter("SUPERVISOR_TYPE"))) ? req.getParameter("SUPERVISOR_TYPE") : "0";
            String TRANS_IP = req.getParameter("TRANS_IP")!= null ? req.getParameter("TRANS_IP") : "";
            String SUPERVISOR_EQU_IP = req.getParameter("SUPERVISOR_EQU_IP")!= null ? req.getParameter("SUPERVISOR_EQU_IP") : "";
            String TRANS_TYPE = (req.getParameter("TRANS_TYPE")!= null && !"".equals(req.getParameter("TRANS_TYPE"))) ? req.getParameter("TRANS_TYPE") : "0";
            String OLT_PORT_ADDRE = req.getParameter("OLT_PORT_ADDRE")!= null ? req.getParameter("OLT_PORT_ADDRE") : "";
            
            
            
            
            
            
            SiteManage bean = new SiteManage();
            int retsign = bean.addData_new(LSCID, AREA_ID, DISTRICT_ID, COUNTRY_ID, FULL_STATION_CODE, STATION_NAME, STATION_CODE,
                    SERVICE_ID, RANK_ID, PROPERTY_ID, MAINTAIN_ID, ADDRESS, LONGITUDE, LATITUDE, COVERED_AREA,
                    OCCUPIED_AREA, RENT, HIRE_TIME, PERIOD, RENT_ID, LEASE_ID, LATEST_DATE, POWER_ID, PRICE,
                    ELECTRICITYRATIO, ISSUPERVISOR, STATUS, BATTERYUSELENGTH, ISCOUNTRY, SOURCESYSTEMSTATIONTYPEID,
                    SOURCESYSTEMSTATIONLEVELID, HOUSETYPEID, ISUSERCONFIRM, USERSTATIONNAME, LEAF_AREAID,
                    EDITTIME, USEDSPECIALTY, GROUPRANK_ID, CC08_SUPERVISORY, STATION_ID_INSERT_DATE, 
                    SUPERVISOR_DATE, SUPERVISOR_TYPE, TRANS_IP, SUPERVISOR_EQU_IP, TRANS_TYPE, OLT_PORT_ADDRE);

            if (retsign == 1) {
                msg = "添加站点成功，站点名称："+STATION_NAME+"编码："+FULL_STATION_CODE;
            } else if (retsign == 2) {
                msg = "添加失败！站点代码重复！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("newaddSite")){ //添加站点
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/jizhan/Newaddsite.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "添加站点失败！请重试或与管理员联系！";
              String changjia = req.getParameter("cj") != null ? req.getParameter("cj") :"";//厂家
                String jzlx  = req.getParameter("jzlx2") != null ? req.getParameter("jzlx2") :"";//基站类型
                String shebei = req.getParameter("sblx") != null ? req.getParameter("sblx") :"";//设备编码
                String bbu = req.getParameter("bbu") != null ? req.getParameter("bbu") :"0";//bbu数量
                String rru = req.getParameter("rru") != null ? req.getParameter("rru") :"0";//rru数量
                String ydshebei = req.getParameter("ydshebei") != null ? req.getParameter("ydshebei") :"0";//移动设备
                String gxgwsl = req.getParameter("gxgwsl") != null ? req.getParameter("gxgwsl") :"0";//共享固网设备数量
                String twgx = req.getParameter("twgx") !=null ? req.getParameter("twgx")  : "";//他网共享
                String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//部门
                String g2xqs = req.getParameter("g2xqs") !=null ? req.getParameter("g2xqs")  : "0";//2G小区数
                String g3sqsl = req.getParameter("g3sqsl") !=null ? req.getParameter("g3sqsl")  : "0";//3G扇区数量
                String ydgxsbsl = req.getParameter("ydgxsbsl") !=null ? req.getParameter("ydgxsbsl")  : "0";
                String dxgxsbsl = req.getParameter("dxgxsbsl") !=null ? req.getParameter("dxgxsbsl")  : "0";
                String ysjts=req.getParameter("ysjts")!=null?req.getParameter("ysjts"):"0";
                String wjts=req.getParameter("wjts")!=null?req.getParameter("wjts"):"0";
                String yybgkt=req.getParameter("yybgkt")!=null?req.getParameter("yybgkt"):"0";
                String jfsckt=req.getParameter("jfsckt")!=null?req.getParameter("jfsckt"):"0";
                
                String ktyps=req.getParameter("ktyps")!=null?req.getParameter("ktyps"):"0";
                String kteps=req.getParameter("kteps")!=null?req.getParameter("kteps"):"0";
                String ktps=req.getParameter("ktps")!=null?req.getParameter("ktps"):"0";
                
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");//集团报表类型
            String jzproperty = req.getParameter("jzproperty");//站点属性
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//用房类型
            String jzname = req.getParameter("jzname");//站点名称
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign") != null ? req.getParameter("bgsign") : "0";//是否标杆
            String dianfei = req.getParameter("danjia");//单价
            String zdcode = req.getParameter("zdcode");
            String jzxlx = req.getParameter("jzxlx");//基站类型
            String jflx = req.getParameter("jflx");//局房类型
            String jrwtype=req.getParameter("jrwtype");//接入网类型
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");//标杆类型标号
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String jlfh=req.getParameter("jlfh");//交流负荷
            String sydate = req.getParameter("sydate");//投入使用时间
            String qyzt = req.getParameter("qyzt") != null ? req.getParameter("qyzt") : "1";//站点启用状态
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "0";//归属方
            //站点附属信息
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "0";//地域类型
            String g2=req.getParameter("g2") != null ? req.getParameter("g2") : "0";//2g设备
            String g3=req.getParameter("g3") != null ? req.getParameter("g3") : "0";//3g设备
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//宽带设备
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//语音设备
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//载频数量
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//载扇数量
            String kdsbsl=req.getParameter("kdsbsl") != null ? req.getParameter("kdsbsl") : "";//宽带设备数量
            String yysbsl=req.getParameter("yysbsl") != null ? req.getParameter("yysbsl") : "";//语音设备数量
            String kt1=req.getParameter("kt1") != null ? req.getParameter("kt1") : "0";//空调1
            String kt2=req.getParameter("kt2") != null ? req.getParameter("kt2") : "0";//空调2
            String kts = req.getParameter("kts") != null ? req.getParameter("kts") : "0";//空调数
            String ktzgl = req.getParameter("ktzgl") != null ? req.getParameter("ktzgl") : "0";//空调总功率

            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//直供电
            
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "0";//生成%
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//办公%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//营业%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//信息化支撑%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//建设投资%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//代垫电费%
            String entrytime=mat.format(new Date());
        /*  if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }*/
            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "否";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String fzr = req.getParameter("fuzeren");
            SiteFieldBean kzform = new SiteFieldBean();
            kzform.setCkkd(!req.getParameter("ckkd").equals("")
                    && req.getParameter("ckkd") != null ? req
                    .getParameter("ckkd") : "");

            if(req.getParameter("jztype").equals("zdlx01")){
                kzform.setYsymj(req.getParameter("idcysymj") != null ? req.getParameter("idcysymj") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setYsymj(req.getParameter("ysymj") != null ? req.getParameter("ysymj") : "");
            }
            
            kzform.setJggs(!req.getParameter("jggs").equals("")&& req.getParameter("jggs") != null ? req.getParameter("jggs") : "");
            kzform.setYsygs(!req.getParameter("ysygs").equals("")&& req.getParameter("ysygs") != null ? req.getParameter("ysygs") : "");
            kzform.setJfgd(!req.getParameter("jfgd").equals("")&& req.getParameter("jfgd") != null ? req.getParameter("jfgd") : "");
            kzform.setSdwd(!req.getParameter("sdwd").equals("")&& req.getParameter("sdwd") != null ? req.getParameter("sdwd") : "");
            kzform.setLyfs(!req.getParameter("lyfs").equals("")&& req.getParameter("lyfs") != null ? req.getParameter("lyfs") : "0");
            kzform.setSffs(!req.getParameter("sffs").equals("")&& req.getParameter("sffs") != null ? req.getParameter("sffs") : "0");
            kzform.setGzqk(!req.getParameter("gzqk").equals("")&& req.getParameter("gzqk") != null ? req.getParameter("gzqk") : "");
            kzform.setNhzb(!req.getParameter("nhzb").equals("")&& req.getParameter("nhzb") != null ? req.getParameter("nhzb") : "");
            kzform.setZpsl(!req.getParameter("zpsl").equals("")&& req.getParameter("zpsl") != null ? req.getParameter("zpsl") : "1");
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setZgry(!req.getParameter("glzgry").equals("")&& req.getParameter("glzgry") != null ? req.getParameter("glzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setZgry(!req.getParameter("qdzgry").equals("")&& req.getParameter("qdzgry") != null ? req.getParameter("qdzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setZgry(!req.getParameter("zgry").equals("")&& req.getParameter("zgry") != null ? req.getParameter("zgry") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setKtsl(!req.getParameter("glktsl").equals("")&& req.getParameter("glktsl") != null ? req.getParameter("glktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setKtsl(!req.getParameter("qdktsl").equals("")&& req.getParameter("qdktsl") != null ? req.getParameter("qdktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setKtsl(!req.getParameter("ktsl").equals("")&& req.getParameter("ktsl") != null ? req.getParameter("ktsl") : "");
            }           
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setPcsl(!req.getParameter("glpcsl").equals("")&& req.getParameter("glpcsl") != null ? req.getParameter("glpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setPcsl(!req.getParameter("qdpcsl").equals("")&& req.getParameter("qdpcsl") != null ? req.getParameter("qdpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setPcsl(!req.getParameter("pcsl").equals("")&& req.getParameter("pcsl") != null ? req.getParameter("pcsl") : "");
            }
                        
            kzform.setRll(!req.getParameter("rll").equals("")&& req.getParameter("rll") != null ? req.getParameter("rll") : "");
            kzform.setLjzs(!req.getParameter("ljzs").equals("")&& req.getParameter("ljzs") != null ? req.getParameter("ljzs") : "1");
            kzform.setTxj(!req.getParameter("txj").equals("")&& req.getParameter("txj") != null ? req.getParameter("txj") : "");
            kzform.setUgs(!req.getParameter("ugs").equals("")&& req.getParameter("ugs")!=null? req.getParameter("ugs"):"");
            kzform.setYsyugs(!req.getParameter("ysyugs").equals("")&& req.getParameter("ysyugs")!=null? req.getParameter("ysyugs"):"");
            kzform.setJnjslx(!req.getParameter("jnjslx").equals("")&& req.getParameter("jnjslx")!=null? req.getParameter("jnjslx"):"");
            kzform.setYdlx(!req.getParameter("ydlx").equals("")&& req.getParameter("ydlx")!=null? req.getParameter("ydlx"):"");
            
            String zzjgbm = req.getParameter("zzjgbm") != null ? req.getParameter("zzjgbm") : "";
            String gczt = req.getParameter("gczt") != null ? req.getParameter("gczt") : "";
            String gcxm = req.getParameter("gcxm") != null ? req.getParameter("gcxm") : "";
            //String zdcq = req.getParameter("zdcq") != null ? req.getParameter("zdcq") : "0";
            String zdcb = req.getParameter("zdcb") != null ? req.getParameter("zdcb") : "0";
            String zlfh = req.getParameter("zlfh") != null ? req.getParameter("zlfh") : "0";
            String czzdid = req.getParameter("czzdid") != null ? req.getParameter("czzdid") : "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }
            String nhjcdy = req.getParameter("nhjcdy");
            String ERPbh = req.getParameter("ERPbh") != null ? req.getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req.getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req.getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req.getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req.getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req.getParameter("latitude") : "";
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//电费支付类型
            
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "0";//付款方式
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//付款周期
            String zbdyhh = req.getParameter("zbdyhh") != null ? req.getParameter("zbdyhh") : "";//电表自报电用户号
            String watchcost = req.getParameter("watchcost") != null ? req.getParameter("watchcost") : "0";//套表计费
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//倍率
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//线损类型
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//线损值
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//电表ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//管理电表
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//电表用途   
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//初始读数
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//初始使用时间
            String lyjhjgs = req.getParameter("lyjhjgs") != null ? req.getParameter("lyjhjgs") : "0";//楼宇交换机个数
            String gxxx = req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "0";//共享信息
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//原电表id
            String jskssj= req.getParameter("jskssj") != null ? req.getParameter("jskssj") : "";//建设开始时间
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//建设结束时间
            String xmh = req.getParameter("xmh") != null ? req.getParameter("xmh") : "";//项目号
            String ygd = req.getParameter("ygd") != null ? req.getParameter("ygd") : "0";//远供电
            String ysd= req.getParameter("ysd") != null ? req.getParameter("ysd") : "0";//远受电
            String zjcode = req.getParameter("zjcode");
            String zzlx = req.getParameter("zzlx");
            String zdcq = req.getParameter("zdcq");
            String wlzdbm = req.getParameter("wlzdbm");
            String ltqx = req.getParameter("ltqx");
            String ydqx = req.getParameter("ydqx");
            String xtid = req.getParameter("xtid");
            String jingdu = req.getParameter("jingdu");
            String weidu = req.getParameter("weidu");
            String phone = req.getParameter("phone");
            String gdfname = req.getParameter("gdfname");
            String bumen = req.getParameter("bumen");
            
            
            
            //2017年11月20日 xiaokang modify
            String LSCID = (req.getParameter("LSCID")!= null && !"".equals(req.getParameter("LSCID"))) ? req.getParameter("LSCID") : "0";
            String AREA_ID = (req.getParameter("shi")!= null && !"".equals(req.getParameter("shi"))) ? req.getParameter("shi") : "0";
            String DISTRICT_ID = (req.getParameter("xian")!= null && !"".equals(req.getParameter("xian"))) ? req.getParameter("xian") : "0";
            String COUNTRY_ID = (req.getParameter("xiaoqu")!= null && !"".equals(req.getParameter("xiaoqu"))) ? req.getParameter("xiaoqu") : "0";
            String FULL_STATION_CODE = req.getParameter("FULL_STATION_CODE")!= null ? req.getParameter("FULL_STATION_CODE") : "";
            String STATION_NAME = req.getParameter("STATION_NAME")!= null ? req.getParameter("STATION_NAME") : "";
            String STATION_CODE = req.getParameter("STATION_CODE")!= null ? req.getParameter("STATION_CODE") : "";
            String SERVICE_ID = req.getParameter("SERVICE_ID")!= null ? req.getParameter("SERVICE_ID") : "";
            String RANK_ID = req.getParameter("RANK_ID")!= null ? req.getParameter("RANK_ID") : "";
            String PROPERTY_ID = (req.getParameter("PROPERTY_ID")!= null && !"".equals(req.getParameter("PROPERTY_ID"))) ? req.getParameter("PROPERTY_ID") : "0";
            String MAINTAIN_ID = (req.getParameter("MAINTAIN_ID")!= null && !"".equals(req.getParameter("MAINTAIN_ID"))) ? req.getParameter("MAINTAIN_ID") : "0";
            String ADDRESS = req.getParameter("ADDRESS")!= null  ? req.getParameter("ADDRESS") : "";
            String LONGITUDE = (req.getParameter("LONGITUDE")!= null && !"".equals(req.getParameter("LONGITUDE"))) ? req.getParameter("LONGITUDE") : "0";
            String LATITUDE = (req.getParameter("LATITUDE")!= null && !"".equals(req.getParameter("LATITUDE"))) ? req.getParameter("LATITUDE") : "0";
            String COVERED_AREA = (req.getParameter("COVERED_AREA")!= null && !"".equals(req.getParameter("COVERED_AREA"))) ? req.getParameter("COVERED_AREA") : "0";
            String OCCUPIED_AREA = (req.getParameter("OCCUPIED_AREA")!= null && !"".equals(req.getParameter("OCCUPIED_AREA"))) ? req.getParameter("OCCUPIED_AREA") : "0";
            String RENT = (req.getParameter("RENT")!= null && !"".equals(req.getParameter("RENT"))) ? req.getParameter("RENT") : "0";
            String HIRE_TIME = req.getParameter("HIRE_TIME")!= null ? req.getParameter("HIRE_TIME") : null;
            String PERIOD = (req.getParameter("PERIOD")!= null && !"".equals(req.getParameter("PERIOD"))) ? req.getParameter("PERIOD") : "0";
            String RENT_ID = (req.getParameter("RENT_ID")!= null && !"".equals(req.getParameter("RENT_ID"))) ? req.getParameter("RENT_ID") : "0";
            String LEASE_ID = (req.getParameter("LEASE_ID")!= null && !"".equals(req.getParameter("LEASE_ID"))) ? req.getParameter("LEASE_ID") : "0";
            String LATEST_DATE = req.getParameter("LATEST_DATE")!= null ? req.getParameter("LATEST_DATE") : "";
            String POWER_ID = (req.getParameter("POWER_ID")!= null && !"".equals(req.getParameter("POWER_ID"))) ? req.getParameter("POWER_ID") : "0";
            String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
            String ELECTRICITYRATIO = (req.getParameter("ELECTRICITYRATIO")!= null && !"".equals(req.getParameter("ELECTRICITYRATIO"))) ? req.getParameter("ELECTRICITYRATIO") : "0";
            String ISSUPERVISOR = req.getParameter("ISSUPERVISOR")!= null  ? req.getParameter("ISSUPERVISOR") : "";
            String STATUS = req.getParameter("STATUS")!= null ? req.getParameter("STATUS") : "";
            String BATTERYUSELENGTH = (req.getParameter("BATTERYUSELENGTH")!= null && !"".equals(req.getParameter("BATTERYUSELENGTH"))) ? req.getParameter("BATTERYUSELENGTH") : "0";
            String ISCOUNTRY = req.getParameter("ISCOUNTRY")!= null ? req.getParameter("ISCOUNTRY") : "";
            String SOURCESYSTEMSTATIONTYPEID = (req.getParameter("SOURCESYSTEMSTATIONTYPEID")!= null && !"".equals(req.getParameter("SOURCESYSTEMSTATIONTYPEID"))) ? req.getParameter("SOURCESYSTEMSTATIONTYPEID") : "0";
            String SOURCESYSTEMSTATIONLEVELID = (req.getParameter("SOURCESYSTEMSTATIONLEVELID")!= null && !"".equals(req.getParameter("SOURCESYSTEMSTATIONLEVELID"))) ? req.getParameter("SOURCESYSTEMSTATIONLEVELID") : "0";
            String HOUSETYPEID = (req.getParameter("HOUSETYPEID")!= null && !"".equals(req.getParameter("HOUSETYPEID"))) ? req.getParameter("HOUSETYPEID") : "0";
            String ISUSERCONFIRM = (req.getParameter("ISUSERCONFIRM")!= null && !"".equals(req.getParameter("ISUSERCONFIRM"))) ? req.getParameter("ISUSERCONFIRM") : "";
            String USERSTATIONNAME = req.getParameter("USERSTATIONNAME")!= null ? req.getParameter("USERSTATIONNAME") : "";
            String LEAF_AREAID = (req.getParameter("LEAF_AREAID")!= null && !"".equals(req.getParameter("LEAF_AREAID"))) ? req.getParameter("LEAF_AREAID") : "0";
            String EDITTIME = req.getParameter("EDITTIME")!= null ? req.getParameter("EDITTIME") : null;
            String USEDSPECIALTY = req.getParameter("USEDSPECIALTY")!= null ? req.getParameter("USEDSPECIALTY") : "";
            String GROUPRANK_ID = (req.getParameter("GROUPRANK_ID")!= null && !"".equals(req.getParameter("GROUPRANK_ID"))) ? req.getParameter("GROUPRANK_ID") : "0";
            String CC08_SUPERVISORY = (req.getParameter("CC08_SUPERVISORY")!= null && !"".equals(req.getParameter("CC08_SUPERVISORY"))) ? req.getParameter("CC08_SUPERVISORY") : "";
            String STATION_ID_INSERT_DATE = req.getParameter("STATION_ID_INSERT_DATE")!= null ? req.getParameter("STATION_ID_INSERT_DATE") : null;
            String SUPERVISOR_DATE = req.getParameter("SUPERVISOR_DATE")!= null ? req.getParameter("SUPERVISOR_DATE") : null;
            String SUPERVISOR_TYPE = (req.getParameter("SUPERVISOR_TYPE")!= null && !"".equals(req.getParameter("SUPERVISOR_TYPE"))) ? req.getParameter("SUPERVISOR_TYPE") : "0";
            String TRANS_IP = req.getParameter("TRANS_IP")!= null ? req.getParameter("TRANS_IP") : "";
            String SUPERVISOR_EQU_IP = req.getParameter("SUPERVISOR_EQU_IP")!= null ? req.getParameter("SUPERVISOR_EQU_IP") : "";
            String TRANS_TYPE = (req.getParameter("TRANS_TYPE")!= null && !"".equals(req.getParameter("TRANS_TYPE"))) ? req.getParameter("TRANS_TYPE") : "0";
            String OLT_PORT_ADDRE = req.getParameter("OLT_PORT_ADDRE")!= null ? req.getParameter("OLT_PORT_ADDRE") : "";
            
            SiteManage bean = new SiteManage();
            
            int retsign = bean.addData(shi, xian, xiaoqu, jztype, jzproperty,
                    yflx, jflx, jzname,zjcode,zzlx, zdcq,wlzdbm,ltqx,ydqx,xtid,jingdu,weidu,phone,gdfname,bumen,bieming, address, bgsign, jnglmk, gdfs,
                    area, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform,zzjgbm, gczt, gcxm,
                    zdcq, zdcb, zlfh,  czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, longitude, latitude,jrwtype,jlfh,gsf,
                    entrypensonnel,entrytime,stationtype,dfzflx,fkfs,fkzq,zbdyhh,
                    watchcost,beilv,linelosstype,linelossvalue,dbid,gldb,dbyt,
                    dytype,g2,g3,kdsb,yysb,zpsl,zssl,kdsbsl,yysbsl,kt1,kt2,zgd,sydate,sc,bg,yy,xxhzc,jstz,csds,cssytime,
                    qyzt,lyjhjgs,gxxx,ydbid,jskssj,jsjssj,xmh,ygd,ysd,dddf,changjia,jzlx,shebei,bbu,rru,ydshebei,gxgwsl,
                    twgx,bm,g2xqs,g3sqsl,ydgxsbsl,dxgxsbsl,kts,ktzgl,ysjts,wjts,yybgkt,jfsckt,ktyps,kteps,ktps,"","","","","","",
                    LSCID, FULL_STATION_CODE, STATION_CODE,SERVICE_ID, RANK_ID,
                    PROPERTY_ID, MAINTAIN_ID, ADDRESS, COVERED_AREA,OCCUPIED_AREA, 
                    RENT, HIRE_TIME, PERIOD, RENT_ID, LEASE_ID,
                    LATEST_DATE,POWER_ID, PRICE, ELECTRICITYRATIO, ISSUPERVISOR,
                    STATUS, BATTERYUSELENGTH, ISCOUNTRY, SOURCESYSTEMSTATIONTYPEID,SOURCESYSTEMSTATIONLEVELID,
                    HOUSETYPEID, ISUSERCONFIRM, USERSTATIONNAME, LEAF_AREAID, EDITTIME, 
                    USEDSPECIALTY, GROUPRANK_ID, CC08_SUPERVISORY, STATION_ID_INSERT_DATE, SUPERVISOR_DATE,
                    SUPERVISOR_TYPE, TRANS_IP, SUPERVISOR_EQU_IP, TRANS_TYPE, OLT_PORT_ADDRE);

            if (retsign == 1) {
                msg = "添加站点成功站点名称："+jzname+"地区："+shi+","+xian+","+xiaoqu;
            } else if (retsign == 2) {
                msg = "添加失败！站点代码重复！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if (action.equals("modifySite")) {
            String nums = req.getParameter("nums");
            //int numb=Integer.parseInt(nums)-1;
            String id = req.getParameter("id");
            String dbid1 = "";
            url = path + "/web/sdttWeb/jizan/addSite.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "修改站点失败！请重试或与管理员联系！";
            
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String gxxx =req.getParameter("gxxx");
            String jztype="";
            String zjcode = req.getParameter("jzcode");
            String zzlx = req.getParameter("zzlx");
            String zdcq = req.getParameter("zdcq");
            String wlzdbm = req.getParameter("wlzdbm");
            String ltqx = req.getParameter("ltqx");
            String ydqx = req.getParameter("ydqx");
            String xtid = req.getParameter("xtid");
            String jingdu = req.getParameter("jingdu");
            String weidu = req.getParameter("weidu");
            String phone = req.getParameter("phone");
            String gdfname = req.getParameter("gdfname");
            String bumen = req.getParameter("bumen");
            String ycq=req.getParameter("ycq");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx");//用房类型
            String jzname = req.getParameter("jzname");
            String bieming = "";
            String address = req.getParameter("address");
            String bgsign ="0";//是否标杆
            String dianfei = "0";
            String zdcode = req.getParameter("zdcode");//页面已经没有 
            String jzxlx ="";
            String jflx = "";
            String jrwtype="";
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String sydate = "";//投入使用时间
            String qyzt = req.getParameter("qyzt")!= null ? req.getParameter("qyzt") : "1";//站点启用状态
            String jlfh= "";
            String gsf= "0";//归属方
            //站点附属信息
            String dytype="0";//地域类型
            String g2="0";//2g设备
            String g3="0";//3g设备
            String kdsb="0";//宽带设备
            String yysb="0";//语音设备
            String zpsl="";//载频数量
            String zssl="";//载扇数量
            String kdsbsl="";//宽带设备数量
            String yysbsl="";//语音设备数量
            String kt1="0";//空调1
            String kt2="0";//空调2
            String zgd="0";//直供电
            //分摊
            String sc="0";//生成%
            String bg="0";//办公%
            String yy="0";//营业%
            String xxhzc="0";//信息化支撑%
            String jstz="0";//建设投资%
            String dddf="0";//代垫电费%
            
            
            String changjia = "";//厂家
            String jzlx ="";//基站类型
            String shebei ="";//设备编码
            String bbu = "0";//bbu数量
            String rru ="0";//rru数量
            String ydshebei ="0";//移动设备数量
            String gxgwsl = "0";//共享固网设备数量
            String twgx ="";//他网共享
            String bm ="";//部门
            String g2xqs ="0";
            String g3sqsl = "0";
            String ydgxsbsl = "0";
            String dxgxsbsl = "0";
            String  ysjts= "0";//饮水机台数
            String  wjts= "0";//微机台数
            String  yybgkt="0";//营业办公空调
            String  jfsckt="0";//机房生产空调
            
            String ktyps="0";
            String kteps="0";
            String ktps="0";
            
            
            String entrytime=mat.format(new Date());
        /*  if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }*/

            String jnglmk = "否";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String fzr = req.getParameter("fuzeren");
            SiteFieldBean kzform = new SiteFieldBean();
            kzform.setCkkd("");
            kzform.setYsymj("");
            
        
            kzform.setJggs("");
            kzform.setYsygs("");
            kzform.setJfgd("");
            kzform.setSdwd("");
            kzform.setSffs("0");
            kzform.setLyfs("0");
            kzform.setGzqk("");
            kzform.setNhzb("");
            kzform.setZpsl("1");
            kzform.setZgry("");
            kzform.setKtsl("");
            kzform.setPcsl("");
            kzform.setRll("");
            kzform.setLjzs("1");
            kzform.setTxj("");
            kzform.setUgs("");
            kzform.setYsyugs("");
            kzform.setJnjslx("");
            kzform.setYdlx("");
            
            String zzjgbm ="";
            String gczt = "";
            String gcxm ="";
            //String zdcq = "";
            String zdcb = "0";
            String zlfh = "0";//直流电流
            String zldy =  "0";//直流电压
            AutoAuditBean   ad=new AutoAuditBean();
            String as=ad.getPowerPoleXiShu();
            
            String powerpole=Double.parseDouble(zlfh)*Double.parseDouble(zldy)*Double.parseDouble(as)+"";
            
            String czzdid = "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }       
            String nhjcdy = req.getParameter("nhjcdy");//页面没有  不用 
            String ERPbh ="";
            String dhID ="";
            String zhwgID = "";
            String dzywID ="";
            String edhdl ="";
            String longitude = "";
            String latitude = "";       
            String dfzflx =  "0";//电费支付类型
            if("月结".equals(dfzflx)){
                dfzflx = "dfzflx01";
            }else if("合同".equals(dfzflx)){
                dfzflx = "dfzflx02";
            }else if("预支".equals(dfzflx)){
                dfzflx = "dfzflx03";
            }else if("插卡".equals(dfzflx)){
                dfzflx = "dfzflx04";
            }
            
            String fkfs = "0";//付款方式
            String fkzq = "0";//付款周期
            String zbdyhh = "";//自报电用户号
            String watchcost = "0";//套表计费
            String beilv = "1";//倍率
            String linelosstype = "0";//线损类型
            String linelossvalue ="";//线损值
            String dbid = "";//电表ID
            String gldb ="";//管理电表  页面没有不用
            String dbyt ="";//电表用途
            String csds = "";//初始读数
            String cssytime =  "";//初始使用时间
            String kzid = "";
            String dbidft ="";//电表id
            String lyjhjgs = "0";//楼宇交换机个数
            //String gxxx = "0";//共享信息
            String ydbid = "";//原电表id
            String jskssj= "";//建设开始时间
            String jsjssj =  "";//建设结束时间
            String xmh = "";//项目号
            String ygd =  "0";//远供电
            String ysd=  "0";//远受电s
            
            String zybyqlx =  "";//自有变压器类型
            String bsdl ="0";//变损电量
            
            String kts = "0";//空调数
            String ktzgl = "0";//空调总功率
            
            SiteManage bean = new SiteManage();
            //删除的字段memo kzid PUE xuni czzd jnjsms caiji
            int retsign = bean.midifyData(shi, xian, xiaoqu,ycq, jztype, jzproperty,
                    yflx, jflx, jzname,zjcode,zzlx,zdcq,wlzdbm,ltqx,ydqx,xtid,jingdu,weidu,phone,gdfname,bumen,
                    bieming, address, bgsign, jnglmk, gdfs,
                    area, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform,zzjgbm, gczt, gcxm,
                    zdcq, zdcb, zlfh,  czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, longitude, latitude,jrwtype,jlfh,gsf,
                    entrypensonnel,entrytime,stationtype,dfzflx,fkfs,fkzq,zbdyhh,watchcost,
                    beilv,linelosstype,linelossvalue,dbid,dbid1,gldb,dbyt,id,kzid,dytype,
                    g2,g3,kdsb,yysb,zpsl,zssl,kdsbsl,yysbsl,kt1,kt2,zgd,sydate,sc,bg,yy,xxhzc,jstz,dbidft,csds,cssytime,
                    qyzt,lyjhjgs,gxxx,ydbid,jskssj,jsjssj,xmh,ygd,ysd,dddf,changjia,jzlx,shebei,bbu,rru,ydshebei,gxgwsl,
                    twgx,bm,g2xqs,g3sqsl,ydgxsbsl,dxgxsbsl,ysjts,wjts,yybgkt,jfsckt,ktyps,kteps,ktps,zybyqlx,bsdl,kts,ktzgl,zldy,powerpole);
            if (retsign == 1) {
                msg = "修改站点成功     站点名称："+jzname+" 地区："+shi+","+xian+","+xiaoqu;
            }else {
                msg = "修改站点失败！请重试或与管理员联系！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("newmodifySite")) {
            String nums = req.getParameter("nums");
            String shi1 = req.getParameter("shi1");
            String xian1 = req.getParameter("xian1");
            String xiaoqu1 = req.getParameter("xiaoqu1");
            String jztype1 = req.getParameter("jztype1");
            String bgsign1 = req.getParameter("bgsign1");
            String sname1 = req.getParameter("sname1");
            String sname2="";
            String szdcode1= req.getParameter("szdcode1");
            String jzproperty1= req.getParameter("jzproperty1");
            String qyzt1 = req.getParameter("qyzt1");
            String sitetype1 = req.getParameter("sitetype1");
            String rgsh2 = req.getParameter("rgsh2");
            String rgsh1 = req.getParameter("rgsh1");
            String caijid1=req.getParameter("caijid1");
            int numb=Integer.parseInt(nums)-1;
            String id = req.getParameter("id");
            url = path + "/web/jizhan/Newmodifysite.jsp?id="+id+"&nums="+numb+"&shi1="+shi1+"&xian1="+xian1+"&xiaoqu1="+xiaoqu1+"&jztype1="+jztype1+"&bgsign1="+bgsign1+"&sname1="+sname2+"&szdcode1="+szdcode1+"&jzproperty1="+jzproperty1+"&qyzt1="+qyzt1+"&sitetype1="+sitetype1+"&rgsh2="+rgsh2+"&rgsh1="+rgsh1+"&caijid1="+caijid1;
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "修改站点失败！请重试或与管理员联系！";
            
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//用房类型
            String jzname = req.getParameter("jzname");
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign") != null ? req.getParameter("bgsign") : "0";//是否标杆
            String dianfei = req.getParameter("danjia");
            String zdcode = req.getParameter("zdcode");//页面已经没有 
            String jzxlx = req.getParameter("jzxlx");
            String jflx = req.getParameter("jflx");
            String jrwtype=req.getParameter("jrwtype");
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String sydate = req.getParameter("sydate");//投入使用时间
            String qyzt = req.getParameter("qyzt")!= null ? req.getParameter("qyzt") : "1";//站点启用状态
            String jlfh=req.getParameter("jlfh") != null ? req.getParameter("jlfh") : "";
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "0";//归属方
            //站点附属信息
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "0";//地域类型
            String g2=req.getParameter("g2") != null ? req.getParameter("g2") : "0";//2g设备
            String g3=req.getParameter("g3") != null ? req.getParameter("g3") : "0";//3g设备
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//宽带设备
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//语音设备
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//载频数量
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//载扇数量
            String kdsbsl=req.getParameter("kdsbsl") != null ? req.getParameter("kdsbsl") : "";//宽带设备数量
            String yysbsl=req.getParameter("yysbsl") != null ? req.getParameter("yysbsl") : "";//语音设备数量
            String kt1=req.getParameter("kt1") != null ? req.getParameter("kt1") : "0";//空调1
            String kt2=req.getParameter("kt2") != null ? req.getParameter("kt2") : "0";//空调2
            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//直供电
            //分摊
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "0";//生成%
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//办公%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//营业%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//信息化支撑%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//建设投资%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//代垫电费%
            
            String changjia = req.getParameter("changjia") !=null ? req.getParameter("changjia") : "";//厂家
            String jzlx = req.getParameter("jzlx") !=null ? req.getParameter("jzlx") : "";//基站类型
            String shebei = req.getParameter("shebei") !=null ? req.getParameter("shebei") : "";//设备编码
            String bbu = req.getParameter("bbu") !=null ? req.getParameter("bbu") : "0";//bbu数量
            String rru = req.getParameter("rru") !=null ? req.getParameter("rru") : "0";//rru数量
            String ydshebei = req.getParameter("ydshebei") !=null ? req.getParameter("ydshebei") :"0";//移动设备数量
            String gxgwsl = req.getParameter("gxgwsl") !=null ? req.getParameter("gxgwsl") : "0";//共享固网设备数量
            String twgx = req.getParameter("twgx") !=null ? req.getParameter("twgx")  : "";//他网共享
            String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//部门
            String g2xqs = req.getParameter("g2xqs") !=null ? req.getParameter("g2xqs") : "0";
            String g3sqsl = req.getParameter("g3sqsl") !=null ? req.getParameter("g3sqsl") : "0";
            String ydgxsbsl = req.getParameter("ydgxsbsl") !=null ? req.getParameter("ydgxsbsl")  : "0";
            String dxgxsbsl = req.getParameter("dxgxsbsl") !=null ? req.getParameter("dxgxsbsl")  : "0";
            String  ysjts= req.getParameter("ysjts")!=null?req.getParameter("ysjts"):"0";//饮水机台数
            String  wjts= req.getParameter("wjts")!=null?req.getParameter("wjts"):"0";//微机台数
            String  yybgkt= req.getParameter("yybgkt")!=null?req.getParameter("yybgkt"):"0";//营业办公空调
            String  jfsckt= req.getParameter("jfsckt")!=null?req.getParameter("jfsckt"):"0";//机房生产空调
            
            String ktyps=req.getParameter("ktyps")!=null?req.getParameter("ktyps"):"0";
            String kteps=req.getParameter("kteps")!=null?req.getParameter("kteps"):"0";
            String ktps=req.getParameter("ktps")!=null?req.getParameter("ktps"):"0";
            

           
            String entrytime=mat.format(new Date());
        /*  if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }*/
            
            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "否";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String fzr = req.getParameter("fuzeren");
            SiteFieldBean kzform = new SiteFieldBean();
            
            kzform.setCkkd(req.getParameter("ckkd") != null ? req
                    .getParameter("ckkd") : "");
            if(req.getParameter("jztype").equals("zdlx01")){
                kzform.setYsymj(req.getParameter("idcysymj") != null ? req
                        .getParameter("idcysymj") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setYsymj(req.getParameter("ysymj") != null ? req
                        .getParameter("ysymj") : "");
            }
            
        
            kzform.setJggs(req.getParameter("jggs") != null ? req
                    .getParameter("jggs") : "");
            kzform.setYsygs(req.getParameter("ysygs") != null ? req
                    .getParameter("ysygs") : "");
            kzform.setJfgd(req.getParameter("jfgd") != null ? req
                    .getParameter("jfgd") : "");
            kzform.setSdwd(req.getParameter("sdwd") != null ? req
                    .getParameter("sdwd") : "");
            kzform.setSffs(req.getParameter("sffs") != null ? req
                    .getParameter("sffs") : "0");
            kzform.setLyfs(req.getParameter("lyfs") != null ? req
                    .getParameter("lyfs") : "0");
            kzform.setGzqk(req.getParameter("gzqk") != null ? req
                    .getParameter("gzqk") : "");
            kzform.setNhzb(req.getParameter("nhzb") != null ? req
                    .getParameter("nhzb") : "");
            kzform.setZpsl(req.getParameter("zpsl") != null ? req
                    .getParameter("zpsl") : "1");
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setZgry(req.getParameter("glzgry") != null ? req
                        .getParameter("glzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setZgry(req.getParameter("qdzgry") != null ? req
                        .getParameter("qdzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setZgry(req.getParameter("zgry") != null ? req
                        .getParameter("zgry") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setKtsl(req.getParameter("glktsl") != null ? req
                        .getParameter("glktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setKtsl(req.getParameter("qdktsl") != null ? req
                        .getParameter("qdktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setKtsl(req.getParameter("ktsl") != null ? req
                        .getParameter("ktsl") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setPcsl(req.getParameter("glpcsl") != null ? req
                        .getParameter("glpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setPcsl(req.getParameter("qdpcsl") != null ? req
                        .getParameter("qdpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setPcsl(req.getParameter("pcsl") != null ? req
                        .getParameter("pcsl") : "");
            }
            kzform.setRll(req.getParameter("rll") != null ? req
                    .getParameter("rll") : "");
            kzform.setLjzs(req.getParameter("ljzs") != null ? req
                    .getParameter("ljzs") : "1");
            kzform.setTxj(req.getParameter("txj") != null ? req
                    .getParameter("txj") : "");
            kzform.setUgs(req.getParameter("ugs") != null ? req
                    .getParameter("ugs") : "");
            kzform.setYsyugs(req.getParameter("ysyugs") != null ? req
                    .getParameter("ysyugs") : "");
            kzform.setJnjslx(req.getParameter("jnjslx") != null ? req
                    .getParameter("jnjslx") : "");
            kzform.setYdlx(req.getParameter("ydlx")!=null ? req
                    .getParameter("ydlx"):"");
            
            String zzjgbm = req.getParameter("zzjgbm") != null ? req.getParameter("zzjgbm") : "";
            String gczt = req.getParameter("gczt") != null ? req.getParameter("gczt") : "";
            String gcxm = req.getParameter("gcxm") != null ? req.getParameter("gcxm") : "";
            //String zdcq = req.getParameter("zdcq") != null ? req.getParameter("zdcq") : "";
            String zdcb = req.getParameter("zdcb") != null ? req.getParameter("zdcb") : "0";
            String zlfh = req.getParameter("zlfh") != null ? req.getParameter("zlfh") : "0";
            String czzdid = req.getParameter("czzdid") != null ? req.getParameter("czzdid") : "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }       
            String nhjcdy = req.getParameter("nhjcdy");//页面没有  不用 
            String ERPbh = req.getParameter("ERPbh") != null ? req.getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req.getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req.getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req.getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req.getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req.getParameter("latitude") : "";     
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//电费支付类型
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "0";//付款方式
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//付款周期
            String zbdyhh = req.getParameter("zbdyhh") != null ? req.getParameter("zbdyhh") : "";//自报电用户号
            String watchcost = req.getParameter("watchcost") != null ? req.getParameter("watchcost") : "0";//套表计费
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//倍率
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//线损类型
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//线损值
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//电表ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//管理电表  页面没有不用
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//电表用途
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//初始读数
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//初始使用时间
            String kzid = req.getParameter("kzid");
            String dbidft = req.getParameter("dbid");//电表id
            String lyjhjgs = req.getParameter("lyjhjgs") != null ? req.getParameter("lyjhjgs") : "0";//楼宇交换机个数
            String gxxx = req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "0";//共享信息
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//原电表id
            String jskssj= req.getParameter("jskssj") != null ? req.getParameter("jskssj") : "";//建设开始时间
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//建设结束时间
            String xmh = req.getParameter("xmh") != null ? req.getParameter("xmh") : "";//项目号
            String ygd = req.getParameter("ygd") != null ? req.getParameter("ygd") : "0";//远供电
            String ysd= req.getParameter("ysd") != null ? req.getParameter("ysd") : "0";//远受电
            String zjcode = req.getParameter("jzcode");
            String zzlx = req.getParameter("zzlx");
            String zdcq = req.getParameter("zdcq");
            String wlzdbm = req.getParameter("wlzdbm");
            String ltqx = req.getParameter("ltqx");
            String ydqx = req.getParameter("ydqx");
            String xtid = req.getParameter("xtid");
            String jingdu = req.getParameter("jingdu");
            String weidu = req.getParameter("weidu");
            String phone = req.getParameter("phone");
            String gdfname = req.getParameter("changjia");
            String bumen = req.getParameter("bumen");
            String ycq=req.getParameter("ycq");
            
            SiteManage bean = new SiteManage();
            //删除的字段memo kzid PUE xuni czzd jnjsms caiji
            int retsign = bean.midifyData(shi, xian, xiaoqu, ycq,jztype, jzproperty,
                    yflx, jflx, jzname,zjcode,zzlx,zdcq,wlzdbm,ltqx,ydqx,xtid,jingdu,weidu,phone,gdfname,bumen,
                    bieming, address, bgsign, jnglmk, gdfs,
                    area, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform,zzjgbm, gczt, gcxm,
                    zdcq, zdcb, zlfh,  czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, longitude, latitude,jrwtype,jlfh,gsf,
                    entrypensonnel,entrytime,stationtype,dfzflx,fkfs,fkzq,zbdyhh,watchcost,
                    beilv,linelosstype,linelossvalue,dbid,gldb,dbyt,id,kzid,dytype,
                    g2,g3,kdsb,yysb,zpsl,zssl,kdsbsl,yysbsl,kt1,kt2,zgd,sydate,sc,bg,yy,xxhzc,jstz,dbidft,csds,cssytime,qyzt,
                    lyjhjgs,gxxx,ydbid,jskssj,jsjssj,xmh,ygd,ysd,dddf,changjia,jzlx,shebei,bbu,rru,ydshebei,gxgwsl,twgx,bm,g2xqs,g3sqsl,ydgxsbsl,dxgxsbsl,ysjts,wjts,yybgkt,jfsckt,ktyps,kteps,ktps,"","","","","",null,null);
            if (retsign == 1) {
                msg = "修改站点成功站点名称："+jzname+"地区："+shi+","+xian+","+xiaoqu;
            }else {
                msg = "修改站点失败！请重试或与管理员联系！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            session.setAttribute("sname1", sname1);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if (action.equals("shenhemodifySite")) {//站点审核 点击链接 修改站点页面
        
            //shi1="+shi+"&xian1="+xian+"&xiaoqu1="+xiaoqu+"&sname1="+sname+"&szdcode1="+szdcode+"&stationtype1="+stationtype+"&jzproperty1="+jzproperty+"&jztype1="+jztype+"&lrrq1="+lrrq+"&id="+zdcode

            String id = req.getParameter("id");
            
            String shi2 = req.getParameter("shi2");//把传过来的条件传到站点审核
            String xian2 = req.getParameter("xian2");
            String xiaoqu2 = req.getParameter("xiaoqu2");
            String sname2 = req.getParameter("sname2");
            String szdcode2 = req.getParameter("szdcode2");
            String stationtype2 = req.getParameter("stationtype2");
            String jzproperty2 = req.getParameter("jzproperty2");
            String jztype2 = req.getParameter("jztype2");
            String lrrq2 = req.getParameter("lrrq2");
            String bzwsh ="2";
            String command="chaxun";
            url = path + "/web/jizhan/jzshenhe.jsp?shi2="+shi2+"&xian2="+xian2+"&xiaoqu2="+xiaoqu2+"&sname2="+sname2+"&szdcode2="+szdcode2+"&stationtype2="+stationtype2+"&jzproperty2="+jzproperty2+"&jztype2="+jztype2+"&bzwsh="+bzwsh+"&command="+command+"&lrrq2="+lrrq2;
            
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "修改站点失败！请重试或与管理员联系！";
            
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//用房类型
            String jzname = req.getParameter("jzname");
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign") != null ? req.getParameter("bgsign") : "0";//是否标杆
            String dianfei = req.getParameter("danjia");
            String zdcode = req.getParameter("zdcode");//页面已经没有 
            String jzxlx = req.getParameter("jzxlx");
            String jflx = req.getParameter("jflx");
            String jrwtype=req.getParameter("jrwtype");
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String sydate = req.getParameter("sydate");//投入使用时间
            String qyzt = req.getParameter("qyzt")!= null ? req.getParameter("qyzt") : "1";//站点启用状态
            String jlfh=req.getParameter("jlfh") != null ? req.getParameter("jlfh") : "";
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "0";//归属方
            //站点附属信息
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "0";//地域类型
            String g2=req.getParameter("g2") != null ? req.getParameter("g2") : "0";//2g设备
            String g3=req.getParameter("g3") != null ? req.getParameter("g3") : "0";//3g设备
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//宽带设备
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//语音设备
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//载频数量
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//载扇数量
            String kdsbsl=req.getParameter("kdsbsl") != null ? req.getParameter("kdsbsl") : "";//宽带设备数量
            String yysbsl=req.getParameter("yysbsl") != null ? req.getParameter("yysbsl") : "";//语音设备数量
            String kt1=req.getParameter("kt1") != null ? req.getParameter("kt1") : "0";//空调1
            String kt2=req.getParameter("kt2") != null ? req.getParameter("kt2") : "0";//空调2
            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//直供电
            //分摊
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "100";//生成%
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//办公%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//营业%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//信息化支撑%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//建设投资%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//代垫电费%
            
            
            String changjia = req.getParameter("cj") !=null ? req.getParameter("cj") : "";//厂家
            String jzlx = req.getParameter("jzlx2") !=null ? req.getParameter("jzlx2") : "";//基站类型
            String shebei = req.getParameter("sblx") !=null ? req.getParameter("sblx") : "";//设备编码
            String bbu = req.getParameter("bbu") !=null ? req.getParameter("bbu") : "0";//bbu数量
            String rru = req.getParameter("rru") !=null ? req.getParameter("rru") : "0";//rru数量
            String ydshebei = req.getParameter("ydshebei") !=null ? req.getParameter("ydshebei") :"0";//移动设备数量
            String gxgwsl = req.getParameter("gxgwsl") !=null ? req.getParameter("gxgwsl") : "0";//共享固网设备数量
            String twgx = req.getParameter("twgx") !=null ? req.getParameter("twgx")  : "";//他网共享
            String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//部门
            String g2xqs = req.getParameter("g2xqs") !=null ? req.getParameter("g2xqs") : "0";
            String g3sqsl = req.getParameter("g3sqsl") !=null ? req.getParameter("g3sqsl") : "0";
            String ydgxsbsl = req.getParameter("ydgxsbsl") !=null ? req.getParameter("ydgxsbsl")  : "0";
            String dxgxsbsl = req.getParameter("dxgxsbsl") !=null ? req.getParameter("dxgxsbsl")  : "0";
            String ysjts=req.getParameter("ysjts")!=null?req.getParameter("ysjts"):"0";
            String wjts=req.getParameter("wjts")!=null?req.getParameter("wjts"):"0";
            String yybgkt=req.getParameter("yybgkt")!=null?req.getParameter("yybgkt"):"0";
            String jfsckt=req.getParameter("jfsckt")!=null?req.getParameter("jfsckt"):"0";
            
            String ktyps=req.getParameter("ktyps")!=null?req.getParameter("ktyps"):"0";
            String kteps=req.getParameter("kteps")!=null?req.getParameter("kteps"):"0";
            String ktps=req.getParameter("ktps")!=null?req.getParameter("ktps"):"0";
            
            String entrytime=mat.format(new Date());
        /*  if (bgsign == null) {
                bgsign = "0";
            } else {
                if (bgsign.equals("on")) {
                    bgsign = "1";
                } else {
                    bgsign = "0";
                }
            }*/

            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "否";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String fzr = req.getParameter("fuzeren");
            SiteFieldBean kzform = new SiteFieldBean();
            
            kzform.setCkkd(req.getParameter("ckkd") != null ? req
                    .getParameter("ckkd") : "");
            if(req.getParameter("jztype").equals("zdlx01")){
                kzform.setYsymj(req.getParameter("idcysymj") != null ? req
                        .getParameter("idcysymj") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setYsymj(req.getParameter("ysymj") != null ? req
                        .getParameter("ysymj") : "");
            }
            
            
        
            kzform.setJggs(req.getParameter("jggs") != null ? req
                    .getParameter("jggs") : "");
            kzform.setYsygs(req.getParameter("ysygs") != null ? req
                    .getParameter("ysygs") : "");
            kzform.setJfgd(req.getParameter("jfgd") != null ? req
                    .getParameter("jfgd") : "");
            kzform.setSdwd(req.getParameter("sdwd") != null ? req
                    .getParameter("sdwd") : "");
            kzform.setSffs(req.getParameter("sffs") != null ? req
                    .getParameter("sffs") : "0");
            kzform.setLyfs(req.getParameter("lyfs") != null ? req
                    .getParameter("lyfs") : "0");
            kzform.setGzqk(req.getParameter("gzqk") != null ? req
                    .getParameter("gzqk") : "");
            kzform.setNhzb(req.getParameter("nhzb") != null ? req
                    .getParameter("nhzb") : "");
            kzform.setZpsl(req.getParameter("zpsl") != null ? req
                    .getParameter("zpsl") : "1");
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setZgry(req.getParameter("glzgry") != null ? req
                        .getParameter("glzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setZgry(req.getParameter("qdzgry") != null ? req
                        .getParameter("qdzgry") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setZgry(req.getParameter("zgry") != null ? req
                        .getParameter("zgry") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setKtsl(req.getParameter("glktsl") != null ? req
                        .getParameter("glktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setKtsl(req.getParameter("qdktsl") != null ? req
                        .getParameter("qdktsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setKtsl(req.getParameter("ktsl") != null ? req
                        .getParameter("ktsl") : "");
            }
            
            if(req.getParameter("jztype").equals("zdlx10")){
                kzform.setPcsl(req.getParameter("glpcsl") != null ? req
                        .getParameter("glpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx11")){
                kzform.setPcsl(req.getParameter("qdpcsl") != null ? req
                        .getParameter("qdpcsl") : "");
            }else if(req.getParameter("jztype").equals("zdlx12")){
                kzform.setPcsl(req.getParameter("pcsl") != null ? req
                        .getParameter("pcsl") : "");
            }
            kzform.setRll(req.getParameter("rll") != null ? req
                    .getParameter("rll") : "");
            kzform.setLjzs(req.getParameter("ljzs") != null ? req
                    .getParameter("ljzs") : "1");
            kzform.setTxj(req.getParameter("txj") != null ? req
                    .getParameter("txj") : "");
            kzform.setUgs(req.getParameter("ugs") != null ? req
                    .getParameter("ugs") : "");
            kzform.setYsyugs(req.getParameter("ysyugs") != null ? req
                    .getParameter("ysyugs") : "");
            kzform.setJnjslx(req.getParameter("jnjslx") != null ? req
                    .getParameter("jnjslx") : "");
            kzform.setYdlx(req.getParameter("ydlx")!=null ? req
                    .getParameter("ydlx"):"");
            
            String zzjgbm = req.getParameter("zzjgbm") != null ? req.getParameter("zzjgbm") : "";
            String gczt = req.getParameter("gczt") != null ? req.getParameter("gczt") : "";
            String gcxm = req.getParameter("gcxm") != null ? req.getParameter("gcxm") : "";
            //String zdcq = req.getParameter("zdcq") != null ? req.getParameter("zdcq") : "";
            String zdcb = req.getParameter("zdcb") != null ? req.getParameter("zdcb") : "0";
            String zlfh = req.getParameter("zlfh") != null ? req.getParameter("zlfh") : "0";
            String czzdid = req.getParameter("czzdid") != null ? req.getParameter("czzdid") : "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }       
            String ycq=req.getParameter("ycq");
            String nhjcdy = req.getParameter("nhjcdy");//页面没有  不用 
            String ERPbh = req.getParameter("ERPbh") != null ? req.getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req.getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req.getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req.getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req.getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req.getParameter("latitude") : "";     
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//电费支付类型
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "0";//付款方式
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//付款周期
            String zbdyhh = req.getParameter("zbdyhh") != null ? req.getParameter("zbdyhh") : "";//自报电用户号
            String watchcost = req.getParameter("watchcost") != null ? req.getParameter("watchcost") : "0";//套表计费
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//倍率
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//线损类型
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//线损值
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//电表ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//管理电表  页面没有不用
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//电表用途
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//初始读数
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//初始使用时间
            String kzid = req.getParameter("kzid");
            String dbidft = req.getParameter("dbid");//电表id
            String lyjhjgs = req.getParameter("lyjhjgs") != null ? req.getParameter("lyjhjgs") : "0";//楼宇交换机个数
            String gxxx = req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "0";//共享信息
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//原电表id
            String jskssj= req.getParameter("jskssj") != null ? req.getParameter("jskssj") : "";//建设开始时间
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//建设结束时间
            String xmh = req.getParameter("xmh") != null ? req.getParameter("xmh") : "";//项目号
            String ygd = req.getParameter("ygd") != null ? req.getParameter("ygd") : "0";//远供电
            String ysd= req.getParameter("ysd") != null ? req.getParameter("ysd") : "0";//远受电
            String zjcode = req.getParameter("jzcode");
            String zzlx = req.getParameter("zzlx");
            String zdcq = req.getParameter("zdcq");
            String wlzdbm = req.getParameter("wlzdbm");
            String ltqx = req.getParameter("ltqx");
            String ydqx = req.getParameter("ydqx");
            String xtid = req.getParameter("xtid");
            String jingdu = req.getParameter("jingdu");
            String weidu = req.getParameter("weidu");
            String phone = req.getParameter("phone");
            String gdfname = req.getParameter("changjia");
            String bumen = req.getParameter("bumen");
            SiteManage bean = new SiteManage();
            //删除的字段memo kzid PUE xuni czzd jnjsms caiji
            int retsign = bean.midifyData(shi, xian, xiaoqu, ycq,jztype, jzproperty,
                    yflx, jflx, jzname, zjcode,zzlx,zdcq,wlzdbm,ltqx,ydqx,xtid,jingdu,weidu,phone,gdfname,bumen,
                    bieming, address, bgsign, jnglmk, gdfs,
                    area, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform,zzjgbm, gczt, gcxm,
                    zdcq, zdcb, zlfh,  czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, longitude, latitude,jrwtype,jlfh,gsf,
                    entrypensonnel,entrytime,stationtype,dfzflx,fkfs,fkzq,zbdyhh,watchcost,
                    beilv,linelosstype,linelossvalue,dbid,gldb,dbyt,id,kzid,dytype,
                    g2,g3,kdsb,yysb,zpsl,zssl,kdsbsl,yysbsl,kt1,kt2,zgd,sydate,sc,bg,yy,
                    xxhzc,jstz,dbidft,csds,cssytime,qyzt,lyjhjgs,gxxx,ydbid,jskssj,jsjssj,xmh,ygd,ysd,dddf,changjia,jzlx,shebei,bbu,rru,ydshebei,gxgwsl,twgx,bm,g2xqs,g3sqsl,ydgxsbsl,dxgxsbsl,ysjts,wjts,yybgkt,jfsckt,ktyps,kteps,ktps,"","","","","",null,null);
            if (retsign == 1) {
                msg = "修改站点成功！";
            }else {
                msg = "修改站点失败！请重试或与管理员联系！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            //session.setAttribute("sname1", sname1);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
        else if (action.equals("deledatequyu")) {
            url = path + "/web/sdttWeb/systemManager/zoneManager.jsp";
            String id = req.getParameter("id");
            msg = "删除区域信息失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData1(id);
            if (retsign == 1) {
                msg = "删除区域信息成功！";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "删除区域信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");  
            }//删除部门管理
        else if (action.equals("deledatebumen")) {
            url = path + "/web/sdttWeb/systemManager/department.jsp";
            String id = req.getParameter("id");
            msg = "删除区域信息失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDatabumenguanli(id);
            if (retsign == 1) {
                msg = "删除区域信息成功！";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "删除区域信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");  
            }
        else if (action.equals("deleteDept")) {
        	url = path + "/web/sdttWeb/systemManager/department_pro.jsp";
        	String id = req.getParameter("id");
        	msg = "删除部门信息失败！请重试或与管理员联系！";
        	SiteManage bean = new SiteManage();
        	int retsign = bean.delDatabumenguanli(id);
        	if (retsign == 1) {
        		msg = "删除部门信息成功！";
        	} 
        	log.write(msg, accountid, req.getRemoteAddr(), "删除部门信息");
        	session.setAttribute("url", url);
        	session.setAttribute("msg", msg);
        	resp.sendRedirect(path + "/web/msg.jsp");  
        }
        else if (action.equals("delsiteXiaoxi")) {
            url = path + "/web/sdttWeb/systemManager/msgManager.jsp";
            String id = req.getParameter("id");
            msg = "删除公告信息失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData2(id);
            if (retsign == 1) {
                msg = "删除公告信息成功！";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "删除公告信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");  
            }
        
        
        else if (action.equals("delsite")) {
            url = path + "/web/sdttWeb/jizan/sitemanage.jsp";
            String id = req.getParameter("id");
            msg = "删除站点失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "删除站点成功！";
            } else if (retsign == 2) {
                msg = "有关联的电表信息或电费信息，不允许删除站点，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }   else if (action.equals("delbaozhang")) {
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
            String id = req.getParameter("id");
            msg = "删除报账信息失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            String res = bean.selectHZSTATE(id);
            if(!res.equals("0") && res != null && !res.equals("")){
            	String bqds = bean.selectBQDS(id);
            	String sqds = bean.selectSQDS(id);
            	int bq = Integer.parseInt(bqds);
            	int sq = Integer.parseInt(sqds);
            	System.out.println("bq+sq"+bq+",,,"+sq);
            	int ere = Integer.parseInt(res);
            	ere = ere - 1;
            	if(bq<sq){
            		System.out.println("本期读数小于上期读数进行回转");
            		bean.updateHZSTATE(id, ere);
            	}
            }
            int retsign = bean.delDataBaozhang(id);
            if (retsign == 1) {
                msg = "删除报账信息成功！";
            } else if (retsign == 2) {
                msg = "有关联的电表信息或电费信息，不允许删除站点，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }  
        else if (action.equals("delttbaozhang")) {
            url = path + "/web/sdttWeb/baozhang/TietaBaozhang.jsp";
            String id = req.getParameter("id");
            msg = "删除铁塔报账信息失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDataBaozhang(id);
            if (retsign == 1) {
                msg = "删除铁塔报账信息成功！";
            } else if (retsign == 2) {
                msg = "有关联的电表信息或电费信息，不允许删除站点，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        } else if (action.equals("tijiaobaozhang")) {
        	  msg = "提交报账信息失败！请重试或与管理员联系！";
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
            String id = req.getParameter("id");
            String DIANBIAOID = req.getParameter("dianbiaoid");
            if(DIANBIAOID==null || DIANBIAOID.equals("")){
            	DIANBIAOID="0";
            	  msg = "提交报账信息失败！请重试或与管理员联系！";
            }
            
            
          
//            SiteManage bean = new SiteManage();
//            int retsign = bean.tijiaoDataBaozhang(id);
            
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            SiteManage bean = new SiteManage();
            	//判断有无执行人
                WorkFlowBean workFlowBean= new WorkFlowBean();
                String flowId=workFlowBean.getFlowId("报账审核");
                String currentNo =workFlowBean.getNextStep(flowId, "1");
                String nextNo =workFlowBean.getNextStep(flowId, "2");
                ArrayList l = new ArrayList();
                DianBiaoBean dianbiaoBean= new DianBiaoBean();
                String XIAN =  dianbiaoBean.getZDXian(DIANBIAOID);
                l=workFlowBean.getAccountByActionId2(currentNo, XIAN);
                String auditorid="";
                if (l != null) {
                    int cscount = ((Integer) l.get(0)).intValue();
                    for (int i = cscount; i < l.size() - 1; i += cscount) {
                        auditorid = (String) l.get(i + l.indexOf("ACCOUNTID"));
                    }
                    if(auditorid.equals("")){
                    	msg = "未找到执行人！请确认后再添加！！";
                    	//return;
                    }else {
                    	 //int bzId = bean.addDataBaoZhang(DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,ACCOUNTID,ACCTIME,BEILV,"1");
//                    	 int retsign = bean.tijiaoDataBaozhang(id);
                    	 int retsign = bean.updateBaoZhangState("1",id);
                    	 if (retsign == 1) {
                             msg = "提交报账信息成功！";
                         } else {
                             msg = "提交报账信息失败！请重试或与管理员联系！";
                         }
                         
                         
                         //添加流程
                        
                         String taskId=id+"";
                         String taskType="ELECTRICFEES";
                         workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
                         
                    }
                }
                	 
            
            
           
            log.write(msg, accountid, req.getRemoteAddr(), "提交报账");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if(action.equals("delsitedf")){

            url = path + "/web/jizhan/sitemanagedf.jsp";
            String id = req.getParameter("id");
            msg = "删除站点失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDatadf(id);
            if (retsign == 1) {
                msg = "删除站点成功！";
            } else if (retsign == 2) {
                msg = "有关联的电表信息或电费信息，不允许删除站点，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if (action.equals("newdelsite")) {
            url = path + "/web/jizhan/Newsitemanage.jsp";
            String id = req.getParameter("id");
            msg = "删除站点失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "删除站点成功！";
            } else if (retsign == 2) {
                msg = "有关联的电表信息或电费信息，不允许删除站点，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除站点");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if (action.equals("addsign")){
            
            url = path + "/web/sightcing/signmanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "添加标杆类型失败！请重试或与管理员联系！";
            String name = req.getParameter("signname");//标杆类型名称
            
            String type = req.getParameter("stationtype");//站点类型
            String olt = req.getParameter("oltg");//OLTG
            String g2 = req.getParameter("g2");//2g
            String g3 = req.getParameter("g3");//3g
            String ktnum = req.getParameter("ktnum");//空调数量
            String dytype = req.getParameter("dytype");//地域类型
            SiteManage bean = new SiteManage();
            int retsign = bean.addSign(name,type,olt,g2,g3,ktnum,dytype);
            if (retsign == 1) {
                msg = "添加标杆类型成功！";
            } else if (retsign == 2) {
                msg = "添加失败！标杆类型代码重复！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加标杆类型");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if (action.equals("modifySign")){
            url = path + "/web/sightcing/signmanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "修改标杆类型失败！请重试或与管理员联系！";
            String id = req.getParameter("id");//标杆类型编号
            String name = req.getParameter("signname");//标杆类型名称
            String type = req.getParameter("stationtype");//站点类型
            String olt = req.getParameter("oltg");//OLTG
            String g2 = req.getParameter("g2");//2g
            String g3 = req.getParameter("g3");//3g
            String ktnum = req.getParameter("ktnum");//空调数量
            String dytype = req.getParameter("dytype");//地域类型
            SiteManage bean = new SiteManage();
            int retsign = bean.modifySign(id,name,type,olt,g2,g3,ktnum,dytype);
            if (retsign == 1) {
                msg = "修改标杆成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改标杆");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if (action.equals("delsign")){
            url = path + "/web/sightcing/signmanage.jsp";
            String id = req.getParameter("id");
            msg = "删除标杆类型失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delSign(id);
            if (retsign == 1) {
                msg = "删除标杆成功！";
            } else if (retsign == 2) {
                msg = "有关联的信息，不允许删除，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除标杆类型");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
            
        }
        //新增区域管理
    else if (action.equals("addquyu1")){
            
            url = path + "/web/sdttWeb/systemManager/addzoneManager.jsp";
            msg = "添加信息失败！";
            
        String quyu = req.getParameter("quyu")!= null ? req.getParameter("quyu") : "";//编号
        String mingcheng = req.getParameter("mingcheng")!= null ? req.getParameter("mingcheng") : "";//名称
        String id=req.getParameter("id")!=null?req.getParameter("id"):"";//修改
        String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//修改
            
            SiteManage bean = new SiteManage();
            int retsign=0;
            if(!"1".equals(bz)){
            retsign= bean.addquyuguanli1(quyu,mingcheng);
            }
            if (retsign == 1) {
            int result = GroupMessage.sendWX(quyu+"\n"+mingcheng);
                if(0 == result){
                    msg = "添加信息失败！";
            }else{
                    msg = "添加信息成功！";
            
        
            }
            }
        if("1".equals(bz)){
        retsign= bean.updatequyu(quyu,mingcheng,id);
                if (retsign == 1) {
                    int result = GroupMessage.sendWX(quyu+"\n"+mingcheng);
                    if(0 == result){
                        msg = "修改信息成功！";
                    }else{
                        msg = "修改信息成功！";
                    }
                } else if (retsign == 2) {
                    msg = "修改失败！";
            }
        }
            
            log.write(msg, accountid, req.getRemoteAddr(), "添加区域信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        
        }//添加部门管理
    else if (action.equals("saveDept")){
    	url = path + "/web/sdttWeb/systemManager/addDepartment_pro.jsp";
        msg = "添加信息失败！";
        //接受页面参数
    	String shi = req.getParameter("shi")!= null ? req.getParameter("shi") : "";//市
    	String deptcode = req.getParameter("deptcode")!= null ? req.getParameter("deptcode") : "";//编码
    	String deptname = req.getParameter("deptname")!= null ? req.getParameter("deptname") : "";//名称
        String fdeptcode = req.getParameter("fdeptcode")!= null ? req.getParameter("fdeptcode") : "";//分公司
        SiteManage bean = new SiteManage();
        //自定义部门编码及分公司编码
        if(deptcode.isEmpty()){
    	    String olddeptcode = bean.getMaxDeptcode(shi, fdeptcode);
            if(olddeptcode != null && !olddeptcode.isEmpty()){
           	    BigDecimal b = new BigDecimal(olddeptcode);
          	    deptcode = b.add( new BigDecimal(1)).toString();
            }else{
          	    deptcode = shi;
            }
        }
        String id=req.getParameter("id")!=null?req.getParameter("id"):"";//修改
        //数据库操作
        int cnt = 0;
        if(id !=null && !id.isEmpty()){//修改
        	cnt = bean.updateDept(deptname, id);
        	if(cnt > 0){
        		 msg = "添加信息成功！";
        	}else{
        		 msg = "添加信息失败！";
        	}
        }else{//新增
        	cnt = bean.addDept(shi, deptcode, deptname, fdeptcode);
        	if(cnt > 0){
        		msg = "修改信息成功！";
	       	}else{
	       		 msg = "修改信息失败！";
	       	}
        }
        log.write(msg, accountid, req.getRemoteAddr(), "添加部门信息");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");
    	
    }
        else if (action.equals("addbumen1")){
                
                url = path + "/web/sdttWeb/systemManager/addDepartment.jsp";
                msg = "添加信息失败！";
                
            String bumen = req.getParameter("bumen")!= null ? req.getParameter("bumen") : "";//编号
            String mingcheng = req.getParameter("mingcheng")!= null ? req.getParameter("mingcheng") : "";//名称
            String bianma=req.getParameter("bianma")!=null?req.getParameter("bianma"):"";
            String fglength=req.getParameter("fglength")!=null?req.getParameter("fglength"):"";
            
            String id=req.getParameter("id")!=null?req.getParameter("id"):"";//修改
            String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//修改
                SiteManage bean = new SiteManage();
                int retsign=0;
                if(!"1".equals(bz)){
                retsign= bean.addbumenguanli1(bumen,mingcheng,bianma,fglength);
                }
                if (retsign == 1) {
                int result = GroupMessage.sendWX(bumen+"\n"+mingcheng);
                    if(0 == result){
                        msg = "添加信息失败！";
                }else{
                        msg = "添加信息成功！";
                
            
                }
                }
            if("1".equals(bz)){
            retsign= bean.updatebumen(mingcheng,id);
                    if (retsign == 1) {
                        int result = GroupMessage.sendWX(mingcheng);
                        if(0 == result){
                            msg = "修改信息成功！";
                        }else{
                            msg = "修改信息成功！";
                        }
                    } else if (retsign == 2) {
                        msg = "修改失败！";
                }
            }
                
                log.write(msg, accountid, req.getRemoteAddr(), "添加部门信息");
                session.setAttribute("url", url);
                session.setAttribute("msg", msg);
                resp.sendRedirect(path + "/web/msg.jsp");
            
            }
                
                //添加公告
            else if (action.equals("addggao")){
                
                url = path + "/web/sdttWeb/systemManager/addmsgManager.jsp";
                msg = "添加公告失败！请重试或与管理员联系！";
                
                
                String xxlx = req.getParameter("xxlx")!= null ? req.getParameter("xxlx") : "0";//信息类型
                String bt = req.getParameter("dbname")!= null ? req.getParameter("dbname") : "";//标题
                String nr = req.getParameter("nr")!= null ? req.getParameter("nr") : "";//内容
                String id=req.getParameter("id")!=null?req.getParameter("id"):"";//修改
                String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//修改
                SiteManage bean = new SiteManage();
                
                int retsign=0;
                if(!"1".equals(bz)){
                retsign= bean.addGgao(xxlx,bt,nr);
                if (retsign == 1) {
                    int result = GroupMessage.sendWX(bt+"\n"+nr);
                    if(0 == result){
                        msg = "添加公告成功！公告已群发！";
                    }else{
                        msg = "添加公告成功！公告群发失败！";
                    }
                } else if (retsign == 2) {
                    msg = "添加失败！公告代码重复！";
                }
                }
                if("1".equals(bz)){
                    retsign= bean.addGgaoxg(xxlx,bt,nr,id);
                    if (retsign == 1) {
                        int result = GroupMessage.sendWX(bt+"\n"+nr);
                        if(0 == result){
                            msg = "修改公告成功！";
                        }else{
                            msg = "修改公告成功！";
                        }
                    } else if (retsign == 2) {
                        msg = "添加失败！公告代码重复！";
                    }
                }
                
                log.write(msg, accountid, req.getRemoteAddr(), "添加公告");
                session.setAttribute("url", url);
                session.setAttribute("msg", msg);
                resp.sendRedirect(path + "/web/msg.jsp");
                
            }
            
            //添加公告
        else if (action.equals("addggao")){
            
            url = path + "/web/sdttWeb/systemManager/addmsgManager.jsp";
            msg = "添加公告失败！请重试或与管理员联系！";
            
            
            String xxlx = req.getParameter("xxlx")!= null ? req.getParameter("xxlx") : "0";//信息类型
            String bt = req.getParameter("dbname")!= null ? req.getParameter("dbname") : "";//标题
            String nr = req.getParameter("nr")!= null ? req.getParameter("nr") : "";//内容
            String id=req.getParameter("id")!=null?req.getParameter("id"):"";//修改
            String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//修改
            SiteManage bean = new SiteManage();
            
            int retsign=0;
            if(!"1".equals(bz)){
            retsign= bean.addGgao(xxlx,bt,nr);
            if (retsign == 1) {
                int result = GroupMessage.sendWX(bt+"\n"+nr);
                if(0 == result){
                    msg = "添加公告成功！公告已群发！";
                }else{
                    msg = "添加公告成功！公告群发失败！";
                }
            } else if (retsign == 2) {
                msg = "添加失败！公告代码重复！";
            }
            }
            if("1".equals(bz)){
                retsign= bean.addGgaoxg(xxlx,bt,nr,id);
                if (retsign == 1) {
                    int result = GroupMessage.sendWX(bt+"\n"+nr);
                    if(0 == result){
                        msg = "修改公告成功！";
                    }else{
                        msg = "修改公告成功！";
                    }
                } else if (retsign == 2) {
                    msg = "添加失败！公告代码重复！";
                }
            }
            
            log.write(msg, accountid, req.getRemoteAddr(), "添加公告");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if (action.equals("modifyggao")){
            url = path + "/web/sys/allggao.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lrr=req.getParameter("accountname")!= null ? req.getParameter("accountname") : "";//录入人
            String lrrId=req.getParameter("accountId")!= null ? req.getParameter("accountId") : "";//录入人id
            String dqtime=mat.format(new Date());//系统当前时间
            msg = "修改公告失败！请重试或与管理员联系！";
            String id = req.getParameter("id");//公告id
            String xxtype = req.getParameter("xxtype");//信息类型
            String ggtime = req.getParameter("ggtime");//公告时间
            String bt = req.getParameter("bt");//标题
            String nr = req.getParameter("nr");//内容
            
            SiteManage bean = new SiteManage();
            int retsign = bean.modifyGgao(id,xxtype,ggtime,dqtime,bt,nr,lrr,lrrId);
            if (retsign == 1) {
                msg = "修改公告成功！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "修改公告");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if (action.equals("delggao")){
            url = path + "/web/sys/allggao.jsp";
            String id = req.getParameter("id");
            msg = "删除公告失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delggao(id);
            if (retsign == 1) {
                msg = "删除公告成功！";
            } else if (retsign == 2) {
                msg = "你的权限有限，不允许删除，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除公告");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("dlUpdate")){//全省定标电量更新
            url = path + "/web/tongjichaxun/dlUpdate.jsp";//BeginTime
            String month = req.getParameter("BeginTime") != null ? req.getParameter("BeginTime") : "";//时间
            SiteManage bean = new SiteManage();
            int rS = bean.dlUpdate(month);
            if (rS == 1) {
                msg = "修改成功！";
            } else if (rS == 0) {
                msg = "你的权限有限或者修改失败，请联系管理员";
            }
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("nhupdate")){//全省定标电量更新
            url = path + "/web/tongjichaxun/nhupdate.jsp";//BeginTime
            String month = req.getParameter("currentmonth") != null ? req.getParameter("currentmonth") : "";//时间
            SiteManage bean = new SiteManage();
            bean.nhdel(month);
            int rs = bean.nhupdate(month);
            if (rs == 1) {
                msg = "更新成功！";
            } else if (rs == 0) {
                msg = "你的权限有限或者修改失败，请联系管理员";
            }
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("addSitedf")){
            url = path + "/web/jizhan/addsitedf.jsp";
            String id="";
            String dfwhszj=req.getParameter("dfwhszj")!=null?req.getParameter("dfwhszj"):"";//上月垫付未回收资金(元)
            String dfwhszj_byhs=req.getParameter("dfwhszj_byhs")!=null?req.getParameter("dfwhszj_byhs"):"";//上月垫付未回收资金，本月已回款(元)
            String dhszj=req.getParameter("dhszj")!=null?req.getParameter("dhszj"):"";//待回收资金(元)
            String dhszj_yyshd=req.getParameter("dhszj_yyshd")!=null?req.getParameter("dhszj_yyshd"):"";//待回收资金：已和运营商完成明细核对
            String dhszj_ykp=req.getParameter("dhszj_ykp")!=null?req.getParameter("dhszj_ykp"):"";//待回收资金：已经开票
            String dfhs_yys=req.getParameter("dfhs_yys")!=null?req.getParameter("dfhs_yys"):"";//运营商
            String dfhs_hdzb=req.getParameter("dfhs_hdzb")!=null?req.getParameter("dfhs_hdzb"):"";//已核对占比：【待回收资金：已和运营商完成明细核对】/【待回收资金】
            String dfhs_kpzb=req.getParameter("dfhs_kpzb")!=null?req.getParameter("dfhs_kpzb"):"";//已开票占比：【待回收资金：已开票】/【待回收资金】
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String shi=req.getParameter("shi")!=null?req.getParameter("shi"):"";
            DianbiaoBean db=new DianbiaoBean();
            db.setId(id);
            db.setDfwhszj(dfwhszj);
            db.setDfwhszj_byhs(dfwhszj_byhs);
            db.setDhszj(dhszj);
            db.setDhszj_yyshd(dhszj_yyshd);
            db.setDhszj_ykp(dhszj_ykp);
            db.setDfhs_yys(dfhs_yys);
            db.setDfhs_hdzb(dfhs_hdzb);
            db.setDfhs_kpzb(dfhs_kpzb);
            db.setEntrypensonnel(entrypensonnel);
            db.setShi(shi);
            SiteManage bean = new SiteManage();
            int  retsign=bean.adddatadf(db);
            if (retsign == 1) {
                msg = "添加电费垫付成功！！！";
            } else if (retsign == 2) {
                msg = "添加失败！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加电费垫付");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("addSitezdxx")){
            url = path + "/web/sdttWeb/sys/addSite.jsp";
            String xmbm=req.getParameter("xmbm");
            String xmname=req.getParameter("xmname");
            String xmlx=req.getParameter("xmlx");
            String xmlxsm=req.getParameter("xmlxsm");
            String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";
            String entrypensonnel=req.getParameter("accountname");//录入人员
            String shi=req.getParameter("shi")!=null?req.getParameter("shi"):"";
            String id=req.getParameter("idzd")!=null?req.getParameter("idzd"):"";
            DianbiaoBean db=new DianbiaoBean();
            db.setXmbm(xmbm);
            db.setXmname(xmname);
            db.setXmlx(xmlx);
            db.setXmlxsm(xmlxsm);
            db.setEntrypensonnel(entrypensonnel);
            db.setShi(shi);
            db.setId(id);
            SiteManage bean = new SiteManage();
            int  retsign=0;
            if ("1".equals(bz)){
                retsign=bean.adddatadfxgzd(db);
                if (retsign == 1) {
                    msg = "修改字典信息成功！！！";
                } else if (retsign == 2) {
                    msg = "修改失败！";
                }
                log.write(msg, accountid, req.getRemoteAddr(), "修改字典信息");
            }
            if(!"1".equals(bz)){
                retsign=bean.adddatadfzd(db);
            if (retsign == 1) {
                msg = "添加字典信息成功！！！";
            } else if (retsign == 2) {
                msg = "添加失败！";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加字典信息");
            }
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("delsiteZD")){
            url = path + "/web/sdttWeb/sys/dictList.jsp";
            String id = req.getParameter("id");
            msg = "删除字典信息失败！请重试或与管理员联系！";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDatazd(id);
            if (retsign == 1) {
                msg = "删除字典信息成功！";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "删除字典信息");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("getDianbiaoById")){
                String dianbiaoId = req.getParameter("dianbiaoId");  
                String shi = "";  
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
                String addtime =sdf2.format(new Date());
//                Calendar cal = Calendar.getInstance();
//                int month = cal.get(Calendar.MONTH) + 1;
//                int year = cal.get(Calendar.YEAR)-1;
//                String addtime_old = ""+year+"-"+month;
                SiteManage bean = new SiteManage();
                String strs ="";
                String strs2 ="";
                String strs3 ="";
                shi = bean.getshiById(dianbiaoId);
                strs = bean.getDianbiaoById(dianbiaoId);
                strs2 = bean.getBaozhangByDianbiaoId(dianbiaoId);
                strs3 = bean.getAnalysisByShi(shi,addtime);
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();  
                Gson gson=new Gson();
                String json=gson.toJson(strs+"*_*"+strs2+"*_*"+strs3);
                out.write(json);
                out.flush();  
                out.close();  
        }else if(action.equals("getDbxxById")){
            String dianbiaoId = req.getParameter("dianbiaoId");
            String stime = req.getParameter("stime");
            String etime = req.getParameter("etime");
            String dl = req.getParameter("dl");
            String ts = req.getParameter("ts");
            String pan = "0";
            String dbPUE ="";
            String dbPUE1 ="";
            String dbPUE2 ="";
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            String addtime =sdf2.format(new Date());
            CommonBean commonBean = new CommonBean();
            //添加pue记录 
            List<String> strList = commonBean.getstationByDbId(dianbiaoId);
           String st_id_str = "",jiaoliu_str="0",zhiliu_str="0",zdid_str="";
           if(strList!=null && strList.size()==4){
           	st_id_str = strList.get(0);
           	jiaoliu_str = strList.get(1);
           	zhiliu_str = strList.get(2);
           	zdid_str = strList.get(3);
           }
           if(jiaoliu_str==null||jiaoliu_str.equals("")){
        	   jiaoliu_str="0";
           }
           if(zhiliu_str==null||zhiliu_str.equals("")){
        	   zhiliu_str="0";
           }
           List<String> strList2 = commonBean.getSumByDbId(st_id_str,stime, etime);
           String sum_str = "",value_str="",sumDay_str="";
           if(strList2!=null && strList2.size()==3){
           	sum_str = strList2.get(0);
           	value_str = strList2.get(1);
           	sumDay_str = strList2.get(2);
           }
           if(sumDay_str==""||sumDay_str.equals("0")){
        	   sumDay_str=ts;
        	   pan = "1";
           }
           if(value_str==null||value_str.equals("")){
        	  value_str=commonBean.getdianliupue(st_id_str);
        	  if(value_str==null||value_str.equals("")){
        		  value_str="14";
        	  }
           }
           DecimalFormat df1 = new DecimalFormat("0.00");
           /**
            * 抄表用电量/[（直流主设备电流*54V?+未监控的交流主设备功率+未监控的直流主设备功率）/0.9 *天数*24小时]÷1000
            * jiaoliu_str  未监控的交流主设备功率
            * zhiliu_str   未监控的直流主设备功率
            * dl   抄表用电量
            * value_str   直流主设备电流
            * sumDay_str   天数
            */
            if(ts!=null && !ts.equals("")){
        	   dbPUE1=""+df1.format(add(add(mul(updouble(value_str.replaceAll(" ", "")),54),updouble(jiaoliu_str.replaceAll(" ", ""))),updouble(zhiliu_str.replaceAll(" ", ""))));
        	   dbPUE1=""+df1.format(div(updouble(dbPUE1.replaceAll(" ", "")), 0.9, 2));
        	   dbPUE2=""+df1.format(mul(updouble(dbPUE1.replaceAll(" ", "")),updouble(ts.replaceAll(" ", ""))));
        	   dbPUE2=""+df1.format(mul(updouble(dbPUE2.replaceAll(" ", "")),24));
        	   dbPUE1=""+df1.format(div(updouble(dbPUE2.replaceAll(" ", "")),1000,2));
        	   dbPUE=""+df1.format(div(updouble(dl.replaceAll(" ", "")),updouble(dbPUE1.replaceAll(" ", "")),2));
           }
	           //resp.setContentType("text/html;charset=utf-8");
               PrintWriter out = resp.getWriter();
               Gson gson=new Gson();
               String json=gson.toJson(dbPUE);
               out.write(json);
               out.flush();
               out.close();
    }//验证是否抄过表
        else if(action.equals("validateElectricfees")){
            String dianbiaoId = req.getParameter("dbid");  
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            String yymm =sdf2.format(new Date());
            SiteManage bean = new SiteManage();
            int i =0;
            i = bean.validateElectricfees(dianbiaoId,yymm);
            int k =0;
            int  x = 0;
            k = bean.geteledianbiao(dianbiaoId);
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();  
            Gson gson=new Gson();
            if(k==0 && i==0){
            	x = 0;
            }else if(i==0 && k!=0){
            	x = 1;
            }
            String json=gson.toJson(""+x);
            out.write(json);
            out.flush();  
            out.close();  
        }else if(action.equals("addAdvance")){
        	doPost1(req, resp,3);
        	
        }
    }
    public void doPost1(HttpServletRequest req, HttpServletResponse resp,int i)
            throws IOException, ServletException {
if(i==1){
        req.setCharacterEncoding("utf-8");//设置输入编码格式。
        resp.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
        
        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        
        HttpSession session = req.getSession();
        String url = path + "/web/jizhan/jizhanlist.jsp", msg = "";
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");
        account=(Account)session.getAttribute("account");

        String action = req.getParameter("action");
    	//url = path + "/web/jizhan/sitemanage.jsp";
        url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
       SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        msg = "添加报账失败！请重试或与管理员联系！";
        String PUEZHI = req.getParameter("PUEZHI");
        //2018年3月22号刘硕 铁塔电表新增字段
        String BGPLL = req.getParameter("BGPLL");
        String FTDL = req.getParameter("FTDL");
        String FTJE = req.getParameter("FTJE");
        String SFYZ = req.getParameter("SFYZ");
        String FTBL = req.getParameter("FTBL");
        String BZQJ = req.getParameter("BZQJ");
        String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
        String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//税额
        String STARTTIME_C = (req.getParameter("STARTTIME_C")!= null && !"".equals(req.getParameter("STARTTIME_C"))) ? req.getParameter("STARTTIME_C") : null;	//手机抄表开始日期
        String ENDTIME_C = (req.getParameter("ENDTIME_C")!= null && !"".equals(req.getParameter("ENDTIME_C"))) ? req.getParameter("ENDTIME_C") : null;			//手机抄表结束日期
        String SQDS_C = (req.getParameter("SQDS_C")!= null && !"".equals(req.getParameter("SQDS_C"))) ? req.getParameter("SQDS_C") : "0";						//手机抄表上期读数
        String BQDS_C = (req.getParameter("BQDS_C")!= null && !"".equals(req.getParameter("BQDS_C"))) ? req.getParameter("BQDS_C") : "0";						//手机抄表本期度数
        String DIANLIANG_C = (req.getParameter("DIANLIANG_C")!= null && !"".equals(req.getParameter("DIANLIANG_C"))) ? req.getParameter("DIANLIANG_C") : "0";	//手机抄表电量
        String DAYNUM_C = (req.getParameter("TIANSHU_C")!= null && !"".equals(req.getParameter("TIANSHU_C"))) ? req.getParameter("TIANSHU_C") : "0";			//手机抄表用电天数
        String RJYDL_C = (req.getParameter("RJYDL_C")!= null && !"".equals(req.getParameter("RJYDL_C"))) ? req.getParameter("RJYDL_C") : "0";					//日均用电量
        String DianLiangPianLiShu = (req.getParameter("DianLiangPianLiShu")!= null && !"".equals(req.getParameter("DianLiangPianLiShu"))) ? req.getParameter("DianLiangPianLiShu") : "0";	//电量偏离数
        String RiQiPianLiShu = (req.getParameter("RiQiPianLiShu")!= null && !"".equals(req.getParameter("RiQiPianLiShu"))) ? req.getParameter("RiQiPianLiShu") : "0";					//日期偏离数
        //END	//String STARTTIME_C,String ENDTIME_C,String SQDS_C,String BQDS_C,String DIANLIANG_C,String DIANSUN_C,String RJYDL_C,
        String DIANBIAOID = (req.getParameter("DIANBIAOID")!= null && !"".equals(req.getParameter("DIANBIAOID"))) ? req.getParameter("DIANBIAOID") : "0";
        String STARTTIME = (req.getParameter("STARTTIME")!= null && !"".equals(req.getParameter("STARTTIME"))) ? req.getParameter("STARTTIME") : null;
        String ENDTIME = (req.getParameter("ENDTIME")!= null && !"".equals(req.getParameter("ENDTIME"))) ? req.getParameter("ENDTIME") : null;
        String SQDS = (req.getParameter("SQDS")!= null && !"".equals(req.getParameter("SQDS"))) ? req.getParameter("SQDS") : "0";
        String BQDS = (req.getParameter("BQDS")!= null && !"".equals(req.getParameter("BQDS"))) ? req.getParameter("BQDS") : "0";
        String DIANLIANG = (req.getParameter("DIANLIANG")!= null && !"".equals(req.getParameter("DIANLIANG"))) ? req.getParameter("DIANLIANG") : "0";
        String ALLMONEY = (req.getParameter("ALLMONEY")!= null && !"".equals(req.getParameter("ALLMONEY"))) ? req.getParameter("ALLMONEY") : "0";
        String DIANSUN = (req.getParameter("DIANSUN")!= null && !"".equals(req.getParameter("DIANSUN"))) ? req.getParameter("DIANSUN") : "0";
        String SQDJ = (req.getParameter("SQDJ")!= null && !"".equals(req.getParameter("SQDJ"))) ? req.getParameter("SQDJ") : "0";
        String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
        String DAYNUM = (req.getParameter("TIANSHU")!= null && !"".equals(req.getParameter("TIANSHU"))) ? req.getParameter("TIANSHU") : "0";
        String RJYDL = (req.getParameter("RJYDL")!= null && !"".equals(req.getParameter("RJYDL"))) ? req.getParameter("RJYDL") : "0";
        String SQRJYDL = (req.getParameter("SQRJYDL")!= null && !"".equals(req.getParameter("SQRJYDL"))) ? req.getParameter("SQRJYDL") : "0";
        String BEILV = (req.getParameter("BEILV")!= null && !"".equals(req.getParameter("BEILV"))) ? req.getParameter("BEILV") : "0";
        @SuppressWarnings("unused")
		String BGDL = (req.getParameter("BGDL")!= null && !"".equals(req.getParameter("BGDL"))) ? req.getParameter("BGDL") : "0";
        String ACCOUNTID=account.getAccountId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        String ACCTIME =sdf.format(new Date());
        System.out.println("ACCTIME:"+ACCTIME);
        String ACCTIMEYYMM =sdf2.format(new Date());
        SiteManage bean = new SiteManage();
        if(saveState.equals("0")){
        	
			String flowId=req.getParameter("flowId");
			String auditorid=req.getParameter("auditorid");
			
			if(auditorid !=null && !auditorid.isEmpty()){
                int bzId = bean.addDataBaoZhangtieta(TAX,STARTTIME_C,ENDTIME_C,SQDS_C,BQDS_C,DIANLIANG_C,DAYNUM_C,RJYDL_C,DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,ACCOUNTID,ACCTIME,BEILV,"1",DianLiangPianLiShu,RiQiPianLiShu,PUEZHI,FTJE,SFYZ,FTBL,BZQJ,BGPLL,BGDL);
                if (bzId >0) {
                    msg = "添加报账成功！";
                } else  {
                    msg = "添加失败！";
                }
                //添加流程
              	WorkFlowBean workFlowBean= new WorkFlowBean();
              	String currentNo =workFlowBean.getNextStep(flowId, "1");
              	String nextNo =workFlowBean.getNextStep(flowId, "2");
                String taskId=bzId+"";  
                String taskType="ELECTRICFEES";
                workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
             
                //添加pue记录 
                CommonBean commonBean = new CommonBean();
                List<String> strList = commonBean.getstationByDbId(DIANBIAOID);
                
               String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="";
               if(strList!=null && strList.size()==4){
               	st_id_str = strList.get(0);
               	jiaoliu_str = strList.get(1);
               	zhiliu_str = strList.get(2);
               	zdid_str = strList.get(3);
               }
               List<String> strList2 = commonBean.getSumByDbId(st_id_str,STARTTIME, ENDTIME);
               String sum_str = "",value_str="",sumDay_str="";
               if(strList2!=null && strList2.size()==3){
               	sum_str = strList2.get(0);
               	value_str = strList2.get(1);
               	sumDay_str = strList2.get(2);
               }
               bean.addAnalysis(taskId, ACCTIMEYYMM, ACCTIME, DIANLIANG,
               		sum_str, DIANBIAOID, zdid_str, STARTTIME, 
               		ENDTIME,value_str,jiaoliu_str,zhiliu_str,sumDay_str);
			}	 
        }else{
        	int bzId = bean.addDataBaoZhangtieta(TAX,STARTTIME_C,ENDTIME_C,SQDS_C,BQDS_C,DIANLIANG_C,DAYNUM_C,RJYDL_C,DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,"","",BEILV,"0",DianLiangPianLiShu,RiQiPianLiShu,PUEZHI,FTJE,SFYZ,FTBL,BZQJ,BGPLL,BGDL);
        	 if (bzId >0) {
                 msg = "临时保存报账成功！";
             } else  {
                 msg = "添加失败！报账代码重复！";
             }
        }
        
       
        log.write(msg, accountid, req.getRemoteAddr(), "添加报账");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");
    }
else if(i==2){
	req.setCharacterEncoding("utf-8");//设置输入编码格式。
    resp.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
    
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    
    HttpSession session = req.getSession();
    String url = path + "/web/jizhan/jizhanlist.jsp", msg = "";
    String accountid = (String) session.getAttribute("loginName");
    String accountSheng = (String) session.getAttribute("accountSheng");
    account=(Account)session.getAttribute("account");
    String action = req.getParameter("action");
	//url = path + "/web/jizhan/sitemanage.jsp";
    url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
    SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    msg = "修改报账失败！请重试或与管理员联系！";
    
    //2017年11月22日 xiaokang modify
    String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
    //2018年3月9日 - gcl 税额
    String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//税额
    
    String ID = (req.getParameter("ID")!= null && !"".equals(req.getParameter("ID"))) ? req.getParameter("ID") : "0";
    String DIANBIAOID = (req.getParameter("DIANBIAOID")!= null && !"".equals(req.getParameter("DIANBIAOID"))) ? req.getParameter("DIANBIAOID") : "0";
    String STARTTIME = (req.getParameter("STARTTIME")!= null && !"".equals(req.getParameter("STARTTIME"))) ? req.getParameter("STARTTIME") : null;
    String ENDTIME = (req.getParameter("ENDTIME")!= null && !"".equals(req.getParameter("ENDTIME"))) ? req.getParameter("ENDTIME") : null;
    String SQDS = (req.getParameter("SQDS")!= null && !"".equals(req.getParameter("SQDS"))) ? req.getParameter("SQDS") : "0";
    String BQDS = (req.getParameter("BQDS")!= null && !"".equals(req.getParameter("BQDS"))) ? req.getParameter("BQDS") : "0";
    String DIANLIANG = (req.getParameter("DIANLIANG")!= null && !"".equals(req.getParameter("DIANLIANG"))) ? req.getParameter("DIANLIANG") : "0";
    String ALLMONEY = (req.getParameter("ALLMONEY")!= null && !"".equals(req.getParameter("ALLMONEY"))) ? req.getParameter("ALLMONEY") : "0";
    String DIANSUN = (req.getParameter("DIANSUN")!= null && !"".equals(req.getParameter("DIANSUN"))) ? req.getParameter("DIANSUN") : "0";
    String SQDJ = (req.getParameter("SQDJ")!= null && !"".equals(req.getParameter("SQDJ"))) ? req.getParameter("SQDJ") : "0";
    String PRICE = (req.getParameter("PRICE")!= null && !"".equals(req.getParameter("PRICE"))) ? req.getParameter("PRICE") : "0";
    String DAYNUM = (req.getParameter("TIANSHU")!= null && !"".equals(req.getParameter("TIANSHU"))) ? req.getParameter("TIANSHU") : "0";
    String RJYDL = (req.getParameter("RJYDL")!= null && !"".equals(req.getParameter("RJYDL"))) ? req.getParameter("RJYDL") : "0";
    String SQRJYDL = (req.getParameter("SQRJYDL")!= null && !"".equals(req.getParameter("SQRJYDL"))) ? req.getParameter("SQRJYDL") : "0";
    String PUEZHI = (req.getParameter("PUEZHI")!= null && !"".equals(req.getParameter("PUEZHI"))) ? req.getParameter("PUEZHI") : "0";
    String FTBL = (req.getParameter("FTBL")!= null && !"".equals(req.getParameter("FTBL"))) ? req.getParameter("FTBL") : "0";
    String SFYZ = (req.getParameter("SFYZ")!= null && !"".equals(req.getParameter("SFYZ"))) ? req.getParameter("SFYZ") : "0";
    String BZQJ = (req.getParameter("BZQJ")!= null && !"".equals(req.getParameter("BZQJ"))) ? req.getParameter("BZQJ") : "0";
    String FTJE = (req.getParameter("FTJE")!= null && !"".equals(req.getParameter("FTJE"))) ? req.getParameter("FTJE") : "0";
    String BGDL = (req.getParameter("BGDL")!= null && !"".equals(req.getParameter("BGDL"))) ? req.getParameter("BGDL") : "0";

    String ACCOUNTID=account.getAccountId();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//录入时间-gcl-2018年3月30日
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    String ACCTIME =sdf.format(new Date());
    String ACCTIMEYYMM =sdf2.format(new Date());
    SiteManage bean = new SiteManage();
    CommonBean commonBean = new CommonBean();
    
    if(saveState.equals("0")){
    	
    	
    	String flowId=req.getParameter("flowId");
		String auditorid=req.getParameter("auditorid");
		
		if (auditorid != null && !auditorid.isEmpty()) {
			int retsign = bean.updateDataBaoZhang("0","0",TAX,ID, DIANBIAOID,
					STARTTIME, ENDTIME, SQDS, BQDS, DIANLIANG,
					ALLMONEY, DIANSUN, SQDJ, PRICE, DAYNUM, RJYDL,
					SQRJYDL, "1", ACCOUNTID, ACCTIME, PUEZHI,FTJE,BGDL);
			if (retsign == 1) {
				msg = "提交报账成功！";
			} else {
				msg = "提交失败！报账代码重复！";
			}
			// 添加流程
			WorkFlowBean workFlowBean = new WorkFlowBean();
			String currentNo = workFlowBean.getNextStep(flowId, "1");
			String nextNo = workFlowBean.getNextStep(flowId, "2");
			String taskId = ID + "";
			String taskType = "ELECTRICFEES";
			workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo,
					account.getAccountId(), taskType, auditorid);
			
			//添加pue记录 
            List<String> strList = commonBean.getstationByDbId(DIANBIAOID);
            
           String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="";
           if(strList!=null && strList.size()==4){
           	st_id_str = strList.get(0);
           	jiaoliu_str = strList.get(1);
           	zhiliu_str = strList.get(2);
           	zdid_str = strList.get(3);
           }
           List<String> strList2 = commonBean.getSumByDbId(st_id_str,STARTTIME, ENDTIME);
           String sum_str = "",value_str="",sumDay_str="";
           if(strList2!=null && strList2.size()==3){
           	sum_str = strList2.get(0);
           	value_str = strList2.get(1);
           	sumDay_str = strList2.get(2);
           }
           bean.addAnalysis(taskId, ACCTIMEYYMM, ACCTIME, DIANLIANG,
           		sum_str, DIANBIAOID, zdid_str, STARTTIME, 
           		ENDTIME,value_str,jiaoliu_str,zhiliu_str,sumDay_str);

		}
        	 
    }else{
    	   
        int retsign = bean.updateDataBaoZhang("0","0",TAX,ID,DIANBIAOID,
        		STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,
        		ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,
        		RJYDL,SQRJYDL,"0","","",PUEZHI,FTJE,BGDL);

        if (retsign == 1) {
            msg = "修改报账成功！";
        } else if (retsign == 2) {
            msg = "修改失败！报账代码重复！";
        }
    }
    
    
 
    log.write(msg, accountid, req.getRemoteAddr(), "修改报账");
    session.setAttribute("url", url);
    session.setAttribute("msg", msg);
    resp.sendRedirect(path + "/web/msg.jsp");
}else if(i==3){

    req.setCharacterEncoding("utf-8");//设置输入编码格式。
    resp.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
    
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    
    HttpSession session = req.getSession();
    String url = path + "/web/jizhan/jizhanlist.jsp", msg = "";
    String accountid = (String) session.getAttribute("loginName");
    String accountSheng = (String) session.getAttribute("accountSheng");
    account=(Account)session.getAttribute("account");

    String action = req.getParameter("action");
	//添加预提金额
    int re=0;
    String shi_s = req.getParameter("shi_s");  
    String xian_s = req.getParameter("xian_s");  
    String xiaoqu_s = req.getParameter("xiaoqu_s");  
    String zdmc_s = req.getParameter("zdmc_s");  
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    String nowDate =sdf2.format(new Date());
    String accId=account.getAccountId();
    SiteManage bean = new SiteManage();
    ArrayList list = new ArrayList();
    list = bean.getDianbiaoList(shi_s,xian_s,xiaoqu_s,zdmc_s);
    if(list !=null && list.size()>0){
    	for(int i1=0;i1<list.size();i1++){
    		String dbid = list.get(i1)+"";
    		//根据电表id获取最近两次报账信息   已生成sap编号的
    		 List<ElecBean> elecBeanList = bean.getBzByDbid(dbid);
    		 if(elecBeanList!=null && elecBeanList.size()==2){
    			 ElecBean elecBean1 = elecBeanList.get(0);
    			 ElecBean elecBean2 = elecBeanList.get(1);  
    			 String dayNum = ""+bean.DateDays(nowDate, elecBean1.getEndtime());
    			 String moneyAdv= "" + (((Double.valueOf(elecBean1.getDianliang()).doubleValue() +
    					 Double.valueOf(elecBean1.getDianliang()).doubleValue() -
    					 Double.valueOf(elecBean2.getDianliang()).doubleValue()) ) *
    					 Double.valueOf(elecBean1.getPrice()).doubleValue() *
    					 Double.valueOf(dayNum).doubleValue());
    			 bean.addAdvance(dbid, nowDate, elecBean1.getElecid(), elecBean2.getElecid(),
    					 moneyAdv, elecBean1.getPrice(), dayNum, accId);
    			 
    		 }
    	}
    	re=0; 
    }else{
    	re=1; 
    }
    resp.setContentType("text/html;charset=utf-8");
    PrintWriter out = resp.getWriter();  
    Gson gson=new Gson();
    String json=gson.toJson(""+re);
    out.write(json);
    out.flush();  
    out.close();   
}
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        resp.setContentType(Content_type);
        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        PrintWriter out = resp.getWriter();

        String path = req.getContextPath();
        DbLog log = new DbLog();
        Account account = new Account();
        String url = path + "/web/sys/accountList.jsp", msg = "";
        HttpSession session = req.getSession();
        String accountid = (String) session.getAttribute("loginName");
        String accountSheng = (String) session.getAttribute("accountSheng");
        String action = req.getParameter("action");
        if (action.equals("modifydanjia")) {
            url = path + "/web/jizhan/dianjiatiaozheng.jsp";
            String id = req.getParameter("id");
            String ndianfei = req.getParameter("ndianfei");
            msg = "修改站点单价失败！请重试或与管理员联系！";
            JiZhanBean bean = new JiZhanBean();
            String retsign = bean.modifyDanJia(id, ndianfei);
            log.write(retsign, accountid, req.getRemoteAddr(), "修改站点单价");
            out.println(retsign);
            out.close();

        } 

    }
    private static  double updouble(String v1){  
        return Double.valueOf(v1).doubleValue();
        }
      	 private static  double add(double v1,double v2){  
      	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
      	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
      	        return b1.add(b2).doubleValue();  
      	    }  
      	// 进行减法运算
      	    private static double subtract(double v1,double v2){
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
