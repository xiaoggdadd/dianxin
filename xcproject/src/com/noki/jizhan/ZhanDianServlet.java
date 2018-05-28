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
   // private static final String jiekouUrl = "http://137.0.30.163:9898/mssproxy";//���Ի���
    private static final String jiekouUrl = "http://137.0.28.162:8090/mssproxy";//��ʽ����
    

    @SuppressWarnings({ "unused", "static-access" })
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        
        req.setCharacterEncoding("utf-8");//������������ʽ��
        resp.setContentType("text/html;charsetType=utf-8");//������������ʽ��
        
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
            msg = "���վ��ʧ�ܣ������Ի������Ա��ϵ��";
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
            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "��";
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
            //��Ϊdopst���Ȳ�������ע����.
            /*JiZhanBean bean = new JiZhanBean();
            int retsign = bean.addData(shi, xian, xiaoqu, jztype, jzproperty,
                    yflx, jflx, jzname, bieming, address, bgsign, jnglmk, gdfs,
                    area, memo, fzr, accountSheng, dianfei, zdcode, jzxlx,
                    accountid, kzform, PUE, zzjgbm, xuni, czzd, gczt, gcxm,
                    zdcq, zdcb, zlfh, jnjsms, czzdid, nhjcdy, ERPbh, dhID,
                    zhwgID, dzywID, edhdl, longitude, latitude, caiji,jrwtype,kongtiao,gsf,entrypensonnel,entrytime,stationtype,signtypenum);

            if (retsign == 1) {
                msg = "���վ��ɹ���";
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�վ������ظ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");*/

        } else if (action.equals("modifyZhanDian")) {
           /* msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
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
            String signtypenum=req.getParameter("signtypenum");//������ͱ��
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
                    .getParameter("jnglmk") : "��";
            String gdfs = req.getParameter("gdfs");
            String area = req.getParameter("area");
            String memo = req.getParameter("memo");
            String fzr = req.getParameter("fzr");
            KZForm kzform = new KZForm();
            // if(jztype.equals("zdlx01")){//IDC����
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
                msg = "�޸�վ��ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸�վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
*/
        }else if(action.equals("modifySitedf")){
            url = path + "/web/jizhan/sitemanagedf.jsp";
            String id=req.getParameter("id");
            String dfwhszj=req.getParameter("dfwhszj")!=null?req.getParameter("dfwhszj"):"";//���µ渶δ�����ʽ�(Ԫ)
            String dfwhszj_byhs=req.getParameter("dfwhszj_byhs")!=null?req.getParameter("dfwhszj_byhs"):"";//���µ渶δ�����ʽ𣬱����ѻؿ�(Ԫ)
            String dhszj=req.getParameter("dhszj")!=null?req.getParameter("dhszj"):"";//�������ʽ�(Ԫ)
            String dhszj_yyshd=req.getParameter("dhszj_yyshd")!=null?req.getParameter("dhszj_yyshd"):"";//�������ʽ��Ѻ���Ӫ�������ϸ�˶�
            String dhszj_ykp=req.getParameter("dhszj_ykp")!=null?req.getParameter("dhszj_ykp"):"";//�������ʽ��Ѿ���Ʊ
            String dfhs_yys=req.getParameter("dfhs_yys")!=null?req.getParameter("dfhs_yys"):"";//��Ӫ��
            String dfhs_hdzb=req.getParameter("dfhs_hdzb")!=null?req.getParameter("dfhs_hdzb"):"";//�Ѻ˶�ռ�ȣ����������ʽ��Ѻ���Ӫ�������ϸ�˶ԡ�/���������ʽ�
            String dfhs_kpzb=req.getParameter("dfhs_kpzb")!=null?req.getParameter("dfhs_kpzb"):"";//�ѿ�Ʊռ�ȣ����������ʽ��ѿ�Ʊ��/���������ʽ�
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
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
                msg = "�޸ĵ�ѵ渶�ɹ�������";
            } else if (retsign == 2) {
                msg = "�޸�ʧ�ܣ�";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸ĵ�ѵ渶");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if (action.equals("del")) {
            url = path + "/web/jizhan/jizhanlist.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��վ��ʧ�ܣ������Ի������Ա��ϵ��";
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "ɾ��վ��ɹ���";
            } else if (retsign == 2) {
                msg = "�й����ĵ����Ϣ��������ɾ��վ��";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("zddfinfo")) {
            url = path + "/web/jizhan/sitemanage.jsp";
            msg = "�޸�վ���ѱ���Ϣʧ�ܣ������Ի������Ա��ϵ��";
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
            //�¼�agreementid,signdate, origindate,stopdate,powerunit,unitlinkman,unitphone,watchcost 
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
                msg = "�޸�վ���ѱ���Ϣ�ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸�վ���ѱ���Ϣ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("baocunbg")) {
            url = path + "/web/jizhan/biaoganjizhan.jsp";
            msg = "�����˻�վʧ�ܣ������Ի������Ա��ϵ";
            String[] id = req.getParameterValues("itemSelected");
            String bgidstr = req.getParameter("bgidstr");
            String allids = req.getParameter("allids");
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.bgjz(id, accountid, allids);
            if (retsign == 1) {
                // msg = "�����˻�վ�ɹ���";
                JzCommBean jz = new JzCommBean();
                msg = jz.modifybgStr(id, bgidstr);
            }
            log.write(msg, accountid, req.getRemoteAddr(), "��˻�վ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        } else if (action.equals("zlinfo")) {
            url = path + "/web/jizhan/sitemanage.jsp";
            msg = "�޸�վ��������Ϣʧ�ܣ������Ի������Ա��ϵ��";
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
                msg = "�޸�վ��������Ϣ�ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "վ��������Ϣ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");*/

        } else if (action.equals("shenhe")) {

            msg = "���վ��ʧ�ܣ�";
            url = path + "/web/sdttWeb/jizan/zhandianshhe.jsp";
            String[] ids = req.getParameterValues("itemSelected");
            String shsign = req.getParameter("shsign");
            String id = req.getParameter("id");
            //�˹�վ������ˡ��˹�վ�����ʱ��
            String entrypensonnelrg=req.getParameter("accountname");
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String entrytimerg=mat.format(new Date());
            JiZhanBean bean = new JiZhanBean();
            int retsign = bean.SHData(id, shsign,entrypensonnelrg,entrytimerg);
            if (retsign == 1) {
                msg = "���վ��ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if(action.equals("addSite")){ //���վ��  ����
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/jizan/sitemanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "���վ��ʧ�ܣ������Ի������Ա��ϵ��";
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
            String jztype = "";//���ű�������
            String jzproperty = req.getParameter("jzproperty");//վ������
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//�÷�����
            String jzname = req.getParameter("jzname");//վ������
            String bieming ="";//������ͣʹ��
            String address = req.getParameter("address");//��ַ
            String bgsign ="1";//�Ƿ�����ͣʹ��  Ĭ�϶��Ǳ��
            String dianfei = req.getParameter("danjia");//����
            String zdcode ="";//��ͣʹ��
            String jzxlx = req.getParameter("jzxlx");//��վ���� ���ڸ�ΪС�����������
            String jflx = "";//�ַ����� ��ͣʹ��
            String jrwtype=req.getParameter("jrwtype");//���������� ��ͣʹ��
            String stationtype=req.getParameter("stationtype");//վ������
            //String signtypenum=req.getParameter("signtypenum");//������ͱ��
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
            String jlfh=req.getParameter("jlfh");//��������
            String sydate = "";//Ͷ��ʹ��ʱ�� ��ͣʹ��
            String qyzt ="1";//վ������״̬ ��ͣʹ�� Ĭ������
            String gsf="0";//������ ��ͣʹ�� 
            //վ�㸽����Ϣ
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "";//��������
            String g2="0";//2g�豸 ��ͣʹ��
            String g3="0";//3g�豸 ��ͣʹ��
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//����豸
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//�����豸
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//��Ƶ������ͣʹ��
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//����������ͣʹ��
            String kdsbsl="";//����豸������ͣʹ��
            String yysbsl="";//�����豸������ͣʹ��
            String kt1= "0";//�յ�1��ͣʹ��
            String kt2= "0";//�յ�2��ͣʹ��
            String kts="0";//�յ�����ͣʹ��
            String ktzgl=req.getParameter("ktzgl") != null ? req.getParameter("ktzgl") : "0";//�յ��ܹ���
            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//ֱ����
            
            String changjia = "";//������ͣʹ��
            String jzlx  = "";//��վ������ͣʹ��jzlx2
            String shebei = "";//�豸������ͣʹ��
            String bbu ="0";//bbu������ͣʹ��
            String rru ="0";//rru������ͣʹ��
            String ydshebei ="0";//�ƶ��豸��ͣʹ��
            String gxgwsl = req.getParameter("gxgwsl") != null ? req.getParameter("gxgwsl") :"0";//���� ��������豸����
            String twgx = "";//����������ͣʹ��
            String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//����
            String g2xqs = "0";//2GС������ͣʹ��
            String g3sqsl ="0";//3G����������ͣʹ��
            String ydgxsbsl = "0";//�ƶ������豸���� ��ͣʹ��
            String dxgxsbsl ="0";//���Ź����豸������ͣʹ��
            String ysjts="0";//��ˮ��̨����ͣʹ��
            String wjts="0";//΢��̨����ͣʹ��
            String yybgkt="0";//Ӫҵ�칫�յ���ͣʹ��
            //------------------�յ�   jfsckt=1+1.5+2+3+5+10+���ܿյ�����
            String jfsckt=req.getParameter("jfsckt")!=null?req.getParameter("jfsckt"):"0";//���������յ�����
            
            String ktyps=req.getParameter("ktyps")!=null?req.getParameter("ktyps"):"0";//�յ�1ƥ����
            String kteps=req.getParameter("kteps")!=null?req.getParameter("kteps"):"0";//�յ�1.5ƥ����
            String ktps=req.getParameter("ktps")!=null?req.getParameter("ktps"):"0";//�յ�2ƥ����
            String ktsps=req.getParameter("ktsps")!=null?req.getParameter("ktsps"):"0";//�յ�3ƥ����
            String ktwps=req.getParameter("ktwps")!=null?req.getParameter("ktwps"):"0";//�յ�5ƥ����
            String ktships=req.getParameter("ktships")!=null?req.getParameter("ktships"):"0";//�յ�10ƥ����
            String ktjmps=req.getParameter("ktjmps")!=null?req.getParameter("ktjmps"):"0";//���ܿյ�����
          //----------------�յ��ܹ���
            String k1=req.getParameter("ktypzgl")!=null?req.getParameter("ktypzgl"):"0";
            String k2=req.getParameter("ktywzgl")!=null?req.getParameter("ktywzgl"):"0";
            String k3=req.getParameter("ktepzgl")!=null?req.getParameter("ktepzgl"):"0";
            String k4=req.getParameter("ktspzgl")!=null?req.getParameter("ktspzgl"):"0";
            String k5=req.getParameter("ktwpzgl")!=null?req.getParameter("ktwpzgl"):"0";
            String k6=req.getParameter("ktshipzgl")!=null?req.getParameter("ktshipzgl"):"0";
            String k7=req.getParameter("ktjmzgl")!=null?req.getParameter("ktjmzgl"):"0";
           //����̯��Ϣ-------
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "0";//����%  
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//�칫%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//Ӫҵ%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//��Ϣ��֧��%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//����Ͷ��%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//������%
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
            String jnglmk ="��";//���ܽ��Ĺ���ģ�� �Ƿ��� ��ͣʹ��
            String gdfs = req.getParameter("gdfs");//���緽ʽ
            String area = req.getParameter("area");//��ַ
            String fzr = req.getParameter("fuzeren");//������
            //String zdcq = req.getParameter("zdcq") != null ? req.getParameter("zdcq") : "0";//վ���Ȩ
            String zlfh = req.getParameter("zlfh") != null ? req.getParameter("zlfh") : "0";//ֱ������
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";//��ĵ���
            
            SiteFieldBean kzform = new SiteFieldBean();
            kzform.setCkkd("");//��ͣʹ��
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
            //-----------�յ�xƥ����
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
            
            
            
            
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//���֧������
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "";//���ʽ
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//��������
            String zbdyhh =req.getParameter("zbdyhh")!=null?req.getParameter("zbdyhh"):"";//����Ա����û�����ͣʹ��
            String watchcost ="";//�ױ�Ʒ���ͣʹ��
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//����
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//��������
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//����ֵ
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//���ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//������
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//�����;   
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//��ʼ����
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//��ʼʹ��ʱ��
            String lyjhjgs = "0";//¥�����������ͣʹ��
            String gxxx =req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "";//������Ϣ��ͣʹ��
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//ԭ���id
            String jskssj= "";//���迪ʼʱ����ͣʹ��
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//�������ʱ��
            String xmh ="";//��Ŀ����ͣʹ��
            String ygd = "0";//Զ������ͣʹ��
            String ysd= "0";//Զ�ܵ���ͣʹ��
            
            String zybyqlx= req.getParameter("zybyqlx") != null ? req.getParameter("zybyqlx") : "";//���б�ѹ������
            String bsdl= req.getParameter("bsdl") != null ? req.getParameter("bsdl") : "0";//�������
            
            
            //2017��11��20�� xiaokang modify
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
                msg = "���վ��ɹ���վ�����ƣ�"+STATION_NAME+"������"+AREA_ID+","+DISTRICT_ID+","+COUNTRY_ID+","+FULL_STATION_CODE;
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�վ������ظ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if(action.equals("addBaozhangtieta")){
        	doPost1(req, resp,1);
        }else if(action.equals("addBaozhang")){ //��ӱ���  ����
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
           SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "��ӱ���ʧ�ܣ������Ի������Ա��ϵ��";
            String PUEZHI = req.getParameter("PUEZHI");
            String BGDL = req.getParameter("BGDL");
            String BGPLL = req.getParameter("BGPLL");
            //2017��11��22�� xiaokang modify
            String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
            //2018��3��9�� - gcl ˰��
            String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//˰��
            String TAXchange = (req.getParameter("TAXchange")!= null && !"".equals(req.getParameter("TAXchange"))) ? req.getParameter("TAXchange") : "0";//�޸�ǰ��˰��
            String TAXSTATE = (req.getParameter("TAXSTATE")!= null && !"".equals(req.getParameter("TAXSTATE"))) ? req.getParameter("TAXSTATE") : "0";//˰��״̬
            //�ж��Ƿ��ת
            String huizhuan = (req.getParameter("hz")!= null && !"".equals(req.getParameter("hz"))) ? req.getParameter("hz") : "0";
            //FuXiuLi 2018/1/16 �����ֻ�������Ϣ¼��
            String STARTTIME_C = (req.getParameter("STARTTIME_C")!= null && !"".equals(req.getParameter("STARTTIME_C"))) ? req.getParameter("STARTTIME_C") : null;	//�ֻ�����ʼ����
            String ENDTIME_C = (req.getParameter("ENDTIME_C")!= null && !"".equals(req.getParameter("ENDTIME_C"))) ? req.getParameter("ENDTIME_C") : null;			//�ֻ������������
            String SQDS_C = (req.getParameter("SQDS_C")!= null && !"".equals(req.getParameter("SQDS_C"))) ? req.getParameter("SQDS_C") : "0";						//�ֻ��������ڶ���
            String BQDS_C = (req.getParameter("BQDS_C")!= null && !"".equals(req.getParameter("BQDS_C"))) ? req.getParameter("BQDS_C") : "0";						//�ֻ������ڶ���
            String DIANLIANG_C = (req.getParameter("DIANLIANG_C")!= null && !"".equals(req.getParameter("DIANLIANG_C"))) ? req.getParameter("DIANLIANG_C") : "0";	//�ֻ��������
            String DAYNUM_C = (req.getParameter("TIANSHU_C")!= null && !"".equals(req.getParameter("TIANSHU_C"))) ? req.getParameter("TIANSHU_C") : "0";			//�ֻ������õ�����
            String RJYDL_C = (req.getParameter("RJYDL_C")!= null && !"".equals(req.getParameter("RJYDL_C"))) ? req.getParameter("RJYDL_C") : "0";					//�վ��õ���
            
            String DianLiangPianLiShu = (req.getParameter("DianLiangPianLiShu")!= null && !"".equals(req.getParameter("DianLiangPianLiShu"))) ? req.getParameter("DianLiangPianLiShu") : "0";	//����ƫ����
            String RiQiPianLiShu = (req.getParameter("RiQiPianLiShu")!= null && !"".equals(req.getParameter("RiQiPianLiShu"))) ? req.getParameter("RiQiPianLiShu") : "0";					//����ƫ����
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��������ʱ��-gcl-2018��3��30��

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
//                    		System.out.println("����ת�޸ĵ����Ӧ�ֶ�-HZSTATE");
                    		int hz = Integer.parseInt(huizhuan);
                    		String sql = "update dianbiao d set d.HZSTATE = "+hz+" where d.id = "+DIANBIAOID;
                    		System.out.println("����ת�޸���Ӧ�ֶ�"+sql.toString());
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
                    	msg = "��ӱ��˳ɹ���";
                    } else  {
                        msg = "���ʧ�ܣ�";
                    }
                    //�������
                  	WorkFlowBean workFlowBean= new WorkFlowBean();
                  	String currentNo =workFlowBean.getNextStep(flowId, "1");
                  	String nextNo =workFlowBean.getNextStep(flowId, "2");
                    String taskId=bzId+"";
                    String taskType="ELECTRICFEES";
                    workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
                 
                    //���pue��¼ 
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
                     msg = "��ʱ���汨�˳ɹ���";
                 } else  {
                     msg = "���ʧ�ܣ����˴����ظ���";
                 }
            }
            
           
            log.write(msg, accountid, req.getRemoteAddr(), "��ӱ���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if(action.equals("tsbz_2")){//�ϲ�����
            //��Ƹ�ϵͳ������Ϣ
            url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            
            //2017��11��22�� xiaokang modify
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
            
             //��ȡ��ѡ��ı�����Ϣ
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
            //���ýӿ�
            //--------------------begin-----------------
            
            BZServiceImpl bzServiceImpl = new BZServiceImpl();
            RequestMessage requestMessage = new RequestMessage();
            /*      requestMessage.setProcessCode("");
                    requestMessage.setProcessSuffixName("�ܺ�ϵͳ����");*/
            
                    BZItem item = new BZItem();
                    //��Χϵͳ����Id ÿ�ζ�����Ψһ
                    String otherSystemMainId = bzServiceImpl.getUUID();
                    item.setOtherSystemMainId(otherSystemMainId);
                    //��½���˺�
                    String accountName = account.getCthrnumber();
                    item.setAccount(accountName);
                    
                    //��ȡ��֯����
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
                    
                    
                    //��˾����?????????????
                    String sapCompayCode =compname;
                    item.setSapCompayCode(compname);
                    //��������
                    item.setEconomicItemCode(jjsx);
                    item.setEconomicItemName("���");
                    //��֧��ʽ
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
                    //���÷�����
                    item.setHappenDate(ACCTIME);
                    // ��������
                    String budgetType="";
                   //ҵ������
                    String bizType="";
                    //�Ƿ�Ա������
                    String isStaffPayment="";
                    //ҵ�񳡾�
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
                    //�����ڼ�
                    item.setBudgetSet(costtime);
                    //�Ƿ�Ա������
                    item.setIsStaffPayment(isStaffPayment);
                    //item.setContractNo("");
                    //item.setContractName("");
                    item.setSum(""+(Double.valueOf(sumMoney).doubleValue() + Double.valueOf(sumFaxmoney).doubleValue()));
                //  item.setDesc("");
                     item.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" �ĵ��");
                    //��Ʊ
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
                    //��Χϵͳ�ӵ�Id
                    String otherSystemDetailId = bzServiceImpl.getUUID();
                    lineItem.setOtherSystemDetailId(otherSystemDetailId);
                    
                    lineItem.setUsageCode(yongtu);
                    //lineItem.setUsageName("");
                    //��������
                    lineItem.setBudgetType(budgetType);
                    //�ӵ����
                    lineItem.setSum(""+sumMoney);
                    //ժҪ
               //    lineItem.setDesc("");
                    lineItem.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" �ĵ��");
                    //Ԥ���Ŀ���� ����
                    lineItem.setBudgetItemCode("CW1000");
                    lineItem.setBudgetItemName("��Դ��");
                    //Ԥ���������ı���
                    lineItem.setBudgetOrgCode(yscode);
                    lineItem.setBudgetOrgName(yscode);
                    //�ɱ����ı�������
//                    lineItem.setSapCostCenterCode("A370108000");
//                    lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
                    lineItem.setSapCostCenterCode(rccode);
                    lineItem.setSapCostCenterName(rcname);
                    //����
                    lineItem.setCount(""+sumDianliang);
                    //�۸�
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
                    //��λ
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
                        //��Χϵͳ�ӵ�Id
                        String otherSystemDetailId2 = bzServiceImpl.getUUID();
                        lineItem_jxs.setOtherSystemDetailId(otherSystemDetailId2);
                        
                        lineItem_jxs.setUsageCode("U888");
                        //lineItem.setUsageName("");
                        //��������
                        lineItem_jxs.setBudgetType("2");
                        //�ӵ����
                        lineItem_jxs.setSum(""+sumFaxmoney);
                        //ժҪ
                        lineItem_jxs.setDesc("����˰");
                        //Ԥ���Ŀ���� ����
                        lineItem_jxs.setBudgetItemCode("CW1000");
                        lineItem_jxs.setBudgetItemName("��Դ��");
                        //�Ԥ���������
                        lineItem_jxs.setBudgetOrgCode(yscode);
                        lineItem_jxs.setBudgetOrgName(ysname);
                        //�ɱ����ı�������
//                        lineItem.setSapCostCenterCode("A370108000");
//                        lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
                        lineItem_jxs.setSapCostCenterCode(rccode);
                        lineItem_jxs.setSapCostCenterName(rcname);
                        //����
                        lineItem_jxs.setCount(""+sumDianliang);
                        //�۸�
                        lineItem_jxs.setPrice(price);
                        //��λ
                        lineItem_jxs.setUnit("01");
                        lineItem_jxs.setCurrency("CNY");
                        lineItem_jxs.setExchangeRate("1.0");
                        
                        lineItem_jxs.setCurrencySum(""+sumFaxmoney);
                        //lineItem.setChargecode("");
                        
                      
                        lineItems.add(lineItem_jxs);
                    }
                    
                    
                    
                    item.setLineItems(lineItems);
                    
                    RelateSupplier relateSupplier = new RelateSupplier();
                  //���ݹ�Ӧ�̱����ȡ��Ϣ
                    CommonBean commonBean = new CommonBean();
                    GongysBean gongysBean = commonBean.getSupplier2(rscode);
                    String rsnameStr = gongysBean.getName();
                    String rsnameId = gongysBean.getId();
                    List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
                    gongysAccountBeans = commonBean.getGaById(rsnameId);
                    gongysBean.setGongysAccountBeans(gongysAccountBeans);
                    
                  //��Ӧ�̱�������
                    relateSupplier.setSupplierCode(rscode);
                    relateSupplier.setSupplierName(rsnameStr);
                  //��ƿ�Ŀ����--�жϳɱ����ı����Ƿ���G��ͷ,��Ϊ��Ӧ��,��Ϊ�ͻ�
                    if(rscode.startsWith("G")){
                    	relateSupplier.setAccountType("2");
                    }else{
                    	relateSupplier.setAccountType("3");
                    }
                    //��� ˰��
                    relateSupplier.setSum(""+sumMoney);
                    relateSupplier.setInputTaxSum(""+sumFaxmoney);
                    relateSupplier.setInvoiceSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                    
                    List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();
                    relateSuppliers.add(relateSupplier);
                    item.setRelateSuppliers(relateSuppliers);
                    //ҵ�񳡾�
                    item.setPickingMode(pickingMode);
                    //ҵ����ʱ��
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
                    		payMentItem.setProvince("ɽ��");
                    		payMentItem.setCity(ga.getCity());
                    		payMentItem.setCurrency("CN");
                    		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                    		payMentItmes.add(payMentItem);
                    	}
                		
//                    	payMentItem.setEmployeeBankAccount("6222081613001150000");
//                		payMentItem.setEmployeeName("����");
//                		payMentItem.setBank("�й��������б��ݱ���֧��");
//                		payMentItem.setBankCode("102");
//                		payMentItem.setPayeeCode("G000549854");
//                		payMentItem.setAccountType("1");
//                		payMentItem.setPayeeType("K");
//                		payMentItem.setRowno("102466002453");
//                		payMentItem.setBankAddress("�й��������б��ݱ���֧��");
//                		payMentItem.setProvince("ɽ��");
//                		payMentItem.setCity("����");
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
                    String tsbz_state = "0";//״̬
                    String tsbz_costsum = "";//���˵���
                    String tsbz_appsum = "";//���˵���
                    if(responseMessage.getTYPE().equals("S")){
                        //������ͳɹ�������id��ȡ���˵�״̬ ѡ�-4-�ȴ����ɱ��˵���;-3-���˵�����ʧ�ܣ�-1-���˵�ɾ������2-�˵���2-���ɱ��˵��ɹ���3-������SAPƾ֤
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
                msg = "���ͱ��˳ɹ���";
            } else  {
                msg = "����ʧ�ܣ�";
            }
            
            
            
            log.write(msg, accountid, req.getRemoteAddr(), "���Ƹ����ͱ���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        
        }
        else if(action.equals("tsbz_3")){ //��Ƹ�ϵͳ������Ϣ ���б���
            url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            
            //2017��11��22�� xiaokang modify
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
            
             //��ȡ��ѡ��ı�����Ϣ
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
            //���ýӿ�
            //--------------------begin-----------------
            
            BZServiceImpl bzServiceImpl = new BZServiceImpl();
            RequestMessage requestMessage = new RequestMessage();
            /*      requestMessage.setProcessCode("");
                    requestMessage.setProcessSuffixName("�ܺ�ϵͳ����");*/
            
                    BZItem item = new BZItem();
                    //��Χϵͳ����Id ÿ�ζ�����Ψһ
                    String otherSystemMainId = bzServiceImpl.getUUID();
                    item.setOtherSystemMainId(otherSystemMainId);
                    //��½���˺�
                    String accountName = account.getCthrnumber();
                    item.setAccount(accountName);
                    
                    //��ȡ��֯����
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
                    
                    
                    //��˾����?????????????
                    String sapCompayCode =compname;
                    item.setSapCompayCode(compname);
                    //��������
                    item.setEconomicItemCode(jjsx);
                    item.setEconomicItemName("���");
                    //��֧��ʽ
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
                    //���÷�����
                    item.setHappenDate(ACCTIME);
                    // ��������
                    String budgetType="";
                   //ҵ������
                    String bizType="";
                    //�Ƿ�Ա������
                    String isStaffPayment="";
                    //ҵ�񳡾�
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
                    //�����ڼ�
                    item.setBudgetSet(costtime);
                    //�Ƿ�Ա������
                    item.setIsStaffPayment(isStaffPayment);
                    //item.setContractNo("");
                    //item.setContractName("");
                    item.setSum(""+(Double.valueOf(sumMoney).doubleValue() + Double.valueOf(sumFaxmoney).doubleValue()));
                    item.setDesc(fristRcName+" "+fillInOrgName+"  "+fristDbName+" �ȵĵ��(���ܺ�)");
                    //��Ʊ
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
                            //��Χϵͳ�ӵ�Id
                            String otherSystemDetailId = bzServiceImpl.getUUID();
                            lineItem.setOtherSystemDetailId(otherSystemDetailId);
                            
                            lineItem.setUsageCode(yongtu);
                            //lineItem.setUsageName("");
                            //��������
                            lineItem.setBudgetType(budgetType);
                            //�ӵ����
                            lineItem.setSum(""+money);
                            //ժҪ
                         //  lineItem.setDesc("");
                            lineItem.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" �ĵ��");
                            //Ԥ���Ŀ���� ����
                            lineItem.setBudgetItemCode("CW1000");
                            lineItem.setBudgetItemName("��Դ��");
                            //Ԥ���������ı���
                            lineItem.setBudgetOrgCode(yscode);
                            lineItem.setBudgetOrgName(ysname);
                            //�ɱ����ı�������
//                            lineItem.setSapCostCenterCode("A370108000");
//                            lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
                            lineItem.setSapCostCenterCode(rccode);
                            lineItem.setSapCostCenterName(rcname);
                            //����
                            lineItem.setCount(dianliang);
                            //�۸�
                            lineItem.setPrice(price);
                            //��λ
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
                       //��Χϵͳ�ӵ�Id
                       String otherSystemDetailId2 = bzServiceImpl.getUUID();
                       lineItem.setOtherSystemDetailId(otherSystemDetailId2);
                       
                       lineItem.setUsageCode("U888");
                       //lineItem.setUsageName("");
                       //��������
                       lineItem.setBudgetType("2");
                       //�ӵ����
                       lineItem.setSum(""+sumFaxmoney);
                       //ժҪ
                       lineItem.setDesc("����˰");
                     //Ԥ���Ŀ���� ����
                       lineItem.setBudgetItemCode("CW1000");
                       lineItem.setBudgetItemName("��Դ��");
//                       //Ԥ���Ŀ���� ���� -----��
//                       lineItem.setBudgetItemCode("");
//                       lineItem.setBudgetItemName("");
                     //Ԥ���������ı���
                       lineItem.setBudgetOrgCode(yscode);
                       lineItem.setBudgetOrgName(ysname);
                       //�ɱ����ı�������
//                       lineItem.setSapCostCenterCode("A370108000");
//                       lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
                       lineItem.setSapCostCenterCode(rccode);
                       lineItem.setSapCostCenterName(rcname);
                       //����
                       lineItem.setCount(dianliang);
                       //�۸�
                       lineItem.setPrice(price);
                       //��λ
                       lineItem.setUnit("01");
                       lineItem.setCurrency("CNY");
                       lineItem.setExchangeRate("1.0");
                       
                       lineItem.setCurrencySum(""+sumFaxmoney);
                       //lineItem.setChargecode("");
                       
                     
                       lineItems.add(lineItem);
                   }
                    item.setLineItems(lineItems);
                    
                    RelateSupplier relateSupplier = new RelateSupplier();
                    //���ݹ�Ӧ�̱����ȡ��Ϣ
                    CommonBean commonBean = new CommonBean();
                    GongysBean gongysBean = commonBean.getSupplier2(rscode);
                    String rsnameStr = gongysBean.getName();
                    String rsnameId = gongysBean.getId();
                    List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
                    gongysAccountBeans = commonBean.getGaById(rsnameId);
                    gongysBean.setGongysAccountBeans(gongysAccountBeans);
                  //��Ӧ�̱�������
                    //��Ӧ�̱�������
                    relateSupplier.setSupplierCode(rscode);
                    relateSupplier.setSupplierName(rsnameStr);
                  //��ƿ�Ŀ����--�жϳɱ����ı����Ƿ���G��ͷ,��Ϊ��Ӧ��,��Ϊ�ͻ�
                    if(rscode.startsWith("G")){
                    	relateSupplier.setAccountType("2");
                    }else{
                    	relateSupplier.setAccountType("3");
                    }
                    //��� ˰��
                    relateSupplier.setSum(""+sumMoney);
                    relateSupplier.setInputTaxSum(""+sumFaxmoney);
                    relateSupplier.setInvoiceSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));

                    
                    List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();
                    relateSuppliers.add(relateSupplier);
                    item.setRelateSuppliers(relateSuppliers);
                    //ҵ�񳡾�
                    item.setPickingMode(pickingMode);
                    //ҵ����ʱ��
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
                             		payMentItem.setProvince("ɽ��");
                             		payMentItem.setCity(ga.getCity());
                             		payMentItem.setCurrency("CN");
                             		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                             		payMentItmes.add(payMentItem);
                             	}
                         		
//                             	payMentItem.setEmployeeBankAccount("6222081613001150000");
//                         		payMentItem.setEmployeeName("����");
//                         		payMentItem.setBank("�й��������б��ݱ���֧��");
//                         		payMentItem.setBankCode("102");
//                         		payMentItem.setPayeeCode("G000549854");
//                         		payMentItem.setAccountType("1");
//                         		payMentItem.setPayeeType("K");
//                         		payMentItem.setRowno("102466002453");
//                         		payMentItem.setBankAddress("�й��������б��ݱ���֧��");
//                         		payMentItem.setProvince("ɽ��");
//                         		payMentItem.setCity("����");
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
            
                    
                    String tsbz_state = "0";//״̬
                    String tsbz_costsum = "";//���˵���
                    String tsbz_appsum = "";//���˵���
                    if(responseMessage.getTYPE().equals("S")){
                        //������ͳɹ�������id��ȡ���˵�״̬ ѡ�-4-�ȴ����ɱ��˵���;-3-���˵�����ʧ�ܣ�-1-���˵�ɾ������2-�˵���2-���ɱ��˵��ɹ���3-������SAPƾ֤
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
                msg = "���ͱ��˳ɹ���";
            } else  {
                msg = "����ʧ�ܣ�";
            }
            
            
            
            log.write(msg, accountid, req.getRemoteAddr(), "���Ƹ����ͱ���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        else if(action.equals("tsbz_1")){//��Ƹ�ϵͳ���� ��ͨ����
            url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            
            //2017��11��22�� xiaokang modify
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
            
             //��ȡ��ѡ��ı�����Ϣ
            SiteManage bean = new SiteManage();
          
            
            DecimalFormat df1 = new DecimalFormat("0.00");
            
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            double allMoney_sw=0;
            
            ArrayList fylist = new ArrayList();
            
            fylist = bean.getListByIds(ids);//��ѯ������Ϣ
            
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
                    
                    
                  //���ýӿ�
                    //--------------------begin-----------------
                    
                    BZServiceImpl bzServiceImpl = new BZServiceImpl();
                    RequestMessage requestMessage = new RequestMessage();
                    /*      requestMessage.setProcessCode("");
                            requestMessage.setProcessSuffixName("�ܺ�ϵͳ����");*/
                    
                            BZItem item = new BZItem();
                            //��Χϵͳ����Id ÿ�ζ�����Ψһ
                            String otherSystemMainId = bzServiceImpl.getUUID();
                            item.setOtherSystemMainId(otherSystemMainId);
                            //��½���˺�
                            String accountName = account.getCthrnumber();
                            item.setAccount(accountName);
                            
                            //��ȡ��֯����
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
                            
                            
                            //��˾����?????????????
                            String sapCompayCode =compname;
                            item.setSapCompayCode(compname);
                            //��������
                            item.setEconomicItemCode(jjsx);
                            item.setEconomicItemName("���");
                            //��֧��ʽ
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
                            //���÷�����
                            item.setHappenDate(ACCTIME);
                            // ��������
                            String budgetType="";
                           //ҵ������
                            String bizType="";
                            //�Ƿ�Ա������
                            String isStaffPayment="";
                            //ҵ�񳡾�
                            String pickingMode="";
                            if(bzfs.equals("bzfs_dict_1")){//���˷�ʽ
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
                            //�����ڼ�
                            item.setBudgetSet(costtime);
                            //�Ƿ�Ա������
                            item.setIsStaffPayment(isStaffPayment);
                            //item.setContractNo("");
                            //item.setContractName("");
                            item.setSum(""+(Double.valueOf(money).doubleValue() + Double.valueOf(faxmoney).doubleValue()));
                            item.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" �ȵĵ��(���ܺ�)");
                            //��Ʊ
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
                            //��Χϵͳ�ӵ�Id
                            String otherSystemDetailId = bzServiceImpl.getUUID();
                            lineItem.setOtherSystemDetailId(otherSystemDetailId);
                            
                            lineItem.setUsageCode(yongtu);
                            //lineItem.setUsageName("");
                            //��������
                            lineItem.setBudgetType(budgetType);
                            //�ӵ����
                            lineItem.setSum(""+money);
                            //ժҪ
                         //  lineItem.setDesc("");
                            lineItem.setDesc(rcname+" "+fillInOrgName+"  "+dbname+" �ĵ��");
                          //Ԥ���Ŀ���� ����
                            lineItem.setBudgetItemCode("CW1000");
                            lineItem.setBudgetItemName("��Դ��");
                            /*//Ԥ���Ŀ���� ���� -----�ɵ�
                            lineItem.setBudgetItemCode("");
                            lineItem.setBudgetItemName("");*/
                            //Ԥ���������ı���
                            lineItem.setBudgetOrgCode(yscode);
                            lineItem.setBudgetOrgName(ysname);
                            //�ɱ����ı�������
//                            lineItem.setSapCostCenterCode("A370108000");
//                            lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
                            lineItem.setSapCostCenterCode(rccode);
                            lineItem.setSapCostCenterName(rcname);
                            //����
                            lineItem.setCount(dianliang);
                            //�۸�
                            lineItem.setPrice(price);
                            //��λ
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
                                //��Χϵͳ�ӵ�Id
                                String otherSystemDetailId2 = bzServiceImpl.getUUID();
                                lineItem_.setOtherSystemDetailId(otherSystemDetailId2);
                                
                                lineItem_.setUsageCode("U888");
                                //lineItem.setUsageName("");
                                //��������
                                lineItem_.setBudgetType("2");
                                //�ӵ����
                                lineItem_.setSum(""+sumFaxmoney);
                                //ժҪ
                                lineItem_.setDesc("����˰");
                              //Ԥ���Ŀ���� ����
                                lineItem.setBudgetItemCode("CW1000");
                                lineItem.setBudgetItemName("��Դ��");
                                /*//Ԥ���Ŀ���� ����   -------�ɵ�
                                lineItem_.setBudgetItemCode("");
                                lineItem_.setBudgetItemName("");*/
                              //Ԥ���������ı���
                                lineItem_.setBudgetOrgCode(yscode);
                                lineItem_.setBudgetOrgName(ysname);
                                //�ɱ����ı�������
//                                lineItem.setSapCostCenterCode("A370108000");
//                                lineItem.setSapCostCenterName("�ɷݼ����̺��طֹ�˾�������ɱ���");
                                lineItem_.setSapCostCenterCode(rccode);
                                lineItem_.setSapCostCenterName(rcname);
                                //����
                                lineItem_.setCount(dianliang);
                                //�۸�
                                lineItem_.setPrice(price);
                                //��λ
                                lineItem_.setUnit("01");
                                lineItem_.setCurrency("CNY");
                                lineItem_.setExchangeRate("1.0");
                                
                                lineItem_.setCurrencySum(""+sumFaxmoney);
                                //lineItem.setChargecode("");
                                
                              
                                lineItems.add(lineItem_);
                            }
                             item.setLineItems(lineItems);
                             
                             RelateSupplier relateSupplier = new RelateSupplier();
                             //���ݹ�Ӧ�̱����ȡ��Ϣ
                             CommonBean commonBean = new CommonBean();
                             GongysBean gongysBean = commonBean.getSupplier2(rscode);
                             String rsnameStr = gongysBean.getName();
                             String rsnameId = gongysBean.getId();
                             List<GongysAccountBean> gongysAccountBeans = new ArrayList<GongysAccountBean>();
                             gongysAccountBeans = commonBean.getGaById(rsnameId);
                             gongysBean.setGongysAccountBeans(gongysAccountBeans);
                             
                           //��Ӧ�̱�������
                             relateSupplier.setSupplierCode(rscode);
                             relateSupplier.setSupplierName(rsnameStr);
                             BZLineItem lineItem_;
							//��ƿ�Ŀ����--�жϳɱ����ı����Ƿ���G��ͷ,��Ϊ��Ӧ��,��Ϊ�ͻ�
                             if(rscode.startsWith("G")){
                             	relateSupplier.setAccountType("2");
                             }else{
                             	relateSupplier.setAccountType("3");
                             }
                             //��� ˰��
                             relateSupplier.setSum(""+money);
                             relateSupplier.setInputTaxSum(""+faxmoney);
                             relateSupplier.setInvoiceSum(""+( Double.valueOf(money).doubleValue()+  Double.valueOf(faxmoney).doubleValue()));

                             
                             List<RelateSupplier> relateSuppliers = new ArrayList<RelateSupplier>();
                             relateSuppliers.add(relateSupplier);
                             item.setRelateSuppliers(relateSuppliers);
                             //ҵ�񳡾�
                             item.setPickingMode(pickingMode);
                             //ҵ����ʱ��
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
                                      		payMentItem.setProvince("ɽ��");
                                      		payMentItem.setCity(ga.getCity());
                                      		payMentItem.setCurrency("CN");
                                      		payMentItem.setSum(""+( Double.valueOf(sumMoney).doubleValue()+  Double.valueOf(sumFaxmoney).doubleValue()));
                                      		payMentItmes.add(payMentItem);
                                      	}
                                  		
//                                      	payMentItem.setEmployeeBankAccount("6222081613001150000");
//                                  		payMentItem.setEmployeeName("����");
//                                  		payMentItem.setBank("�й��������б��ݱ���֧��");
//                                  		payMentItem.setBankCode("102");
//                                  		payMentItem.setPayeeCode("G000549854");
//                                  		payMentItem.setAccountType("1");
//                                  		payMentItem.setPayeeType("K");
//                                  		payMentItem.setRowno("102466002453");
//                                  		payMentItem.setBankAddress("�й��������б��ݱ���֧��");
//                                  		payMentItem.setProvince("ɽ��");
//                                  		payMentItem.setCity("����");
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
                             String tsbz_state = "0";//״̬
                             String tsbz_costsum = "";//���˵���
                             String tsbz_appsum = "";//���˵���
                             if(responseMessage.getTYPE().equals("S")){
                                 //������ͳɹ�������id��ȡ���˵�״̬ ѡ�-4-�ȴ����ɱ��˵���;-3-���˵�����ʧ�ܣ�-1-���˵�ɾ������2-�˵���2-���ɱ��˵��ɹ���3-������SAPƾ֤
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
                                 msg = "���ͱ��˳ɹ���";
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
                                 msg = "����ʧ�ܣ�";
                             }
                           
                    
                }
            }
            
                    
                    
            //--------------------end-----------------
            
                    
            
//            if (acId >0) {
//                msg = "���ͱ��˳ɹ���";
//            } else  {
//                msg = "����ʧ�ܣ�";
//            }
            
            
            
            log.write(msg, accountid, req.getRemoteAddr(), "���Ƹ����ͱ���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        
        }
        else if(action.equals("addAllbz")){ //������
            
            String[] ids = req.getParameterValues("itemSelected");
           
            
            url = path + "/web/sdttWeb/baozhang/addbz.jsp";
            
            SiteManage bean = new SiteManage();
            ArrayList listBz = new ArrayList();
         //   listBz = bean.getListByIds(ids);

            log.write(msg, accountid, req.getRemoteAddr(), "��ӱ���");
            session.setAttribute("listBz", listBz);
            resp.sendRedirect(url);
        }else if(action.equals("updateAllcost")){ //������Ϣ����
        	url = path + "/web/sdttWeb/baozhang/allcost.jsp";
            msg = "�ѱ�����Ϣ����ʧ�ܣ������Ի������Ա��ϵ��";
            String[] ids = req.getParameterValues("itemSelected");
            SiteManage sm = new SiteManage();
            BZServiceImpl bzServiceImpl = new BZServiceImpl();
            if(ids!=null){
                for(int i=0;i<ids.length;i++){
                	  AcSoloBean acSoloBean = sm.getAllcostByIs(ids[i]);
                	  String tsbz_state = "0";//״̬
                      String tsbz_costsum = "";//���˵���
                      String tsbz_appsum = "";//���˵���
                	if(acSoloBean!=null){
                        //������ͳɹ�������id��ȡ���˵�״̬ ѡ�-4-�ȴ����ɱ��˵���;-3-���˵�����ʧ�ܣ�-1-���˵�ɾ������2-�˵���2-���ɱ��˵��ɹ���3-������SAPƾ֤
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
                        	//�ӿڷ��ر���
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
                         //���˵�״̬   ��0 ʱ���³ɹ�
                         if(!tsbz_state.equals("0")){
                        	 
                        	int m = sm.updateAllCost(acSoloBean.getAllcostid(),tsbz_state,tsbz_costsum, tsbz_appsum);
	                          if(m==1){
	                        	  msg = "�ѱ�����Ϣ���³ɹ���";
	                          }
	                         //����Ϊ3-������SAPƾ֤ʱ,���µ�����״̬Ϊ 4-������SAPƾ֤
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
                
               //����ʧ�����ݲ�ѯ add by guol 2018-03-13                        ��
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
            	    	sm.updateBz_soloState("0", bzid);//���±���
            	    }
               }
             //-------------------------------------------------------                
                
            }
         
           
            log.write(msg, accountid, req.getRemoteAddr(), "���±���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("updateBaozhangtieta")){
        	doPost1(req, resp,2);
        }
        else if(action.equals("updateBaozhang")){ //���վ��  ����
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "�޸ı���ʧ�ܣ������Ի������Ա��ϵ��";
            
            //�ж��Ƿ��ת
            String huizhuan = (req.getParameter("hz")!= null && !"".equals(req.getParameter("hz"))) ? req.getParameter("hz") : "0";
            
            //2017��11��22�� xiaokang modify
            String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
            //2018��3��9�� - gcl ˰��
            String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//˰��
            String TAXchange = (req.getParameter("TAXchange")!= null && !"".equals(req.getParameter("TAXchange"))) ? req.getParameter("TAXchange") : "0";//�޸�ǰ��˰��
            String TAXSTATE = (req.getParameter("TAXSTATE")!= null && !"".equals(req.getParameter("TAXSTATE"))) ? req.getParameter("TAXSTATE") : "0";//˰��״̬
           
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//2018��3��30��-gcl-�޸Ĵ���ʱ��
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
//                    		System.out.println("����ת�޸ĵ����Ӧ�ֶ�-HZSTATE");
                    		String sql = "update dianbiao d set d.HZSTATE = "+huizhuan+" where d.id = "+DIANBIAOID;
                    		System.out.println("x�޸ı��˵���ת�޸���Ӧ�ֶ�"+sql.toString());
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
						msg = "�ύ���˳ɹ���";
					} else {
						msg = "�ύʧ�ܣ����˴����ظ���";
					}
					// �������
					WorkFlowBean workFlowBean = new WorkFlowBean();
					String currentNo = workFlowBean.getNextStep(flowId, "1");
					String nextNo = workFlowBean.getNextStep(flowId, "2");
					String taskId = ID + "";
					String taskType = "ELECTRICFEES";
					workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo,
							account.getAccountId(), taskType, auditorid);
					
					//���pue��¼ 
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
            	
            	
            	
            	
            	//�ж�����ִ����
                /*WorkFlowBean workFlowBean= new WorkFlowBean();
                String flowId=workFlowBean.getFlowId("�������");
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
                    	msg = "δ�ҵ�ִ���ˣ���ȷ�Ϻ�����ӣ���";
                    	//return;
                    }else {
                    	   
                        int retsign = bean.updateDataBaoZhang(ID,DIANBIAOID,
                        		STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,
                        		ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,
                        		SQRJYDL,"1",ACCOUNTID,ACCTIME,PUEZHI);

                        if (retsign == 1) {
                             msg = "�ύ���˳ɹ���";
                         } else  {
                             msg = "�ύʧ�ܣ����˴����ظ���";
                         }
                         
                         
                         //�������
                        
                         String taskId=ID+"";
                         String taskType="ELECTRICFEES";
                         workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
                         
                         //���pue��¼ 
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
                    msg = "�޸ı��˳ɹ���";
                } else if (retsign == 2) {
                    msg = "�޸�ʧ�ܣ����˴����ظ���";
                }
            }
            
            
         
            log.write(msg, accountid, req.getRemoteAddr(), "�޸ı���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        else if(action.equals("addSite_new")){ //���վ��  ����
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/sdttWeb/jizan/sitemanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "���վ��ʧ�ܣ������Ի������Ա��ϵ��";
            
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
                msg = "���վ��ɹ���վ�����ƣ�"+STATION_NAME+"���룺"+FULL_STATION_CODE;
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�վ������ظ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("newaddSite")){ //���վ��
            //url = path + "/web/jizhan/sitemanage.jsp";
            url = path + "/web/jizhan/Newaddsite.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "���վ��ʧ�ܣ������Ի������Ա��ϵ��";
              String changjia = req.getParameter("cj") != null ? req.getParameter("cj") :"";//����
                String jzlx  = req.getParameter("jzlx2") != null ? req.getParameter("jzlx2") :"";//��վ����
                String shebei = req.getParameter("sblx") != null ? req.getParameter("sblx") :"";//�豸����
                String bbu = req.getParameter("bbu") != null ? req.getParameter("bbu") :"0";//bbu����
                String rru = req.getParameter("rru") != null ? req.getParameter("rru") :"0";//rru����
                String ydshebei = req.getParameter("ydshebei") != null ? req.getParameter("ydshebei") :"0";//�ƶ��豸
                String gxgwsl = req.getParameter("gxgwsl") != null ? req.getParameter("gxgwsl") :"0";//��������豸����
                String twgx = req.getParameter("twgx") !=null ? req.getParameter("twgx")  : "";//��������
                String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//����
                String g2xqs = req.getParameter("g2xqs") !=null ? req.getParameter("g2xqs")  : "0";//2GС����
                String g3sqsl = req.getParameter("g3sqsl") !=null ? req.getParameter("g3sqsl")  : "0";//3G��������
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
            String jztype = req.getParameter("jztype");//���ű�������
            String jzproperty = req.getParameter("jzproperty");//վ������
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//�÷�����
            String jzname = req.getParameter("jzname");//վ������
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign") != null ? req.getParameter("bgsign") : "0";//�Ƿ���
            String dianfei = req.getParameter("danjia");//����
            String zdcode = req.getParameter("zdcode");
            String jzxlx = req.getParameter("jzxlx");//��վ����
            String jflx = req.getParameter("jflx");//�ַ�����
            String jrwtype=req.getParameter("jrwtype");//����������
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");//������ͱ��
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
            String jlfh=req.getParameter("jlfh");//��������
            String sydate = req.getParameter("sydate");//Ͷ��ʹ��ʱ��
            String qyzt = req.getParameter("qyzt") != null ? req.getParameter("qyzt") : "1";//վ������״̬
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "0";//������
            //վ�㸽����Ϣ
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "0";//��������
            String g2=req.getParameter("g2") != null ? req.getParameter("g2") : "0";//2g�豸
            String g3=req.getParameter("g3") != null ? req.getParameter("g3") : "0";//3g�豸
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//����豸
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//�����豸
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//��Ƶ����
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//��������
            String kdsbsl=req.getParameter("kdsbsl") != null ? req.getParameter("kdsbsl") : "";//����豸����
            String yysbsl=req.getParameter("yysbsl") != null ? req.getParameter("yysbsl") : "";//�����豸����
            String kt1=req.getParameter("kt1") != null ? req.getParameter("kt1") : "0";//�յ�1
            String kt2=req.getParameter("kt2") != null ? req.getParameter("kt2") : "0";//�յ�2
            String kts = req.getParameter("kts") != null ? req.getParameter("kts") : "0";//�յ���
            String ktzgl = req.getParameter("ktzgl") != null ? req.getParameter("ktzgl") : "0";//�յ��ܹ���

            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//ֱ����
            
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "0";//����%
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//�칫%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//Ӫҵ%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//��Ϣ��֧��%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//����Ͷ��%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//������%
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
            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "��";
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
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//���֧������
            
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "0";//���ʽ
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//��������
            String zbdyhh = req.getParameter("zbdyhh") != null ? req.getParameter("zbdyhh") : "";//����Ա����û���
            String watchcost = req.getParameter("watchcost") != null ? req.getParameter("watchcost") : "0";//�ױ�Ʒ�
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//����
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//��������
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//����ֵ
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//���ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//������
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//�����;   
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//��ʼ����
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//��ʼʹ��ʱ��
            String lyjhjgs = req.getParameter("lyjhjgs") != null ? req.getParameter("lyjhjgs") : "0";//¥���������
            String gxxx = req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "0";//������Ϣ
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//ԭ���id
            String jskssj= req.getParameter("jskssj") != null ? req.getParameter("jskssj") : "";//���迪ʼʱ��
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//�������ʱ��
            String xmh = req.getParameter("xmh") != null ? req.getParameter("xmh") : "";//��Ŀ��
            String ygd = req.getParameter("ygd") != null ? req.getParameter("ygd") : "0";//Զ����
            String ysd= req.getParameter("ysd") != null ? req.getParameter("ysd") : "0";//Զ�ܵ�
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
            
            
            
            //2017��11��20�� xiaokang modify
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
                msg = "���վ��ɹ�վ�����ƣ�"+jzname+"������"+shi+","+xian+","+xiaoqu;
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�վ������ظ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���վ��");
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
            msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
            
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
            String yflx = req.getParameter("yflx");//�÷�����
            String jzname = req.getParameter("jzname");
            String bieming = "";
            String address = req.getParameter("address");
            String bgsign ="0";//�Ƿ���
            String dianfei = "0";
            String zdcode = req.getParameter("zdcode");//ҳ���Ѿ�û�� 
            String jzxlx ="";
            String jflx = "";
            String jrwtype="";
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
            String sydate = "";//Ͷ��ʹ��ʱ��
            String qyzt = req.getParameter("qyzt")!= null ? req.getParameter("qyzt") : "1";//վ������״̬
            String jlfh= "";
            String gsf= "0";//������
            //վ�㸽����Ϣ
            String dytype="0";//��������
            String g2="0";//2g�豸
            String g3="0";//3g�豸
            String kdsb="0";//����豸
            String yysb="0";//�����豸
            String zpsl="";//��Ƶ����
            String zssl="";//��������
            String kdsbsl="";//����豸����
            String yysbsl="";//�����豸����
            String kt1="0";//�յ�1
            String kt2="0";//�յ�2
            String zgd="0";//ֱ����
            //��̯
            String sc="0";//����%
            String bg="0";//�칫%
            String yy="0";//Ӫҵ%
            String xxhzc="0";//��Ϣ��֧��%
            String jstz="0";//����Ͷ��%
            String dddf="0";//������%
            
            
            String changjia = "";//����
            String jzlx ="";//��վ����
            String shebei ="";//�豸����
            String bbu = "0";//bbu����
            String rru ="0";//rru����
            String ydshebei ="0";//�ƶ��豸����
            String gxgwsl = "0";//��������豸����
            String twgx ="";//��������
            String bm ="";//����
            String g2xqs ="0";
            String g3sqsl = "0";
            String ydgxsbsl = "0";
            String dxgxsbsl = "0";
            String  ysjts= "0";//��ˮ��̨��
            String  wjts= "0";//΢��̨��
            String  yybgkt="0";//Ӫҵ�칫�յ�
            String  jfsckt="0";//���������յ�
            
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

            String jnglmk = "��";
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
            String zlfh = "0";//ֱ������
            String zldy =  "0";//ֱ����ѹ
            AutoAuditBean   ad=new AutoAuditBean();
            String as=ad.getPowerPoleXiShu();
            
            String powerpole=Double.parseDouble(zlfh)*Double.parseDouble(zldy)*Double.parseDouble(as)+"";
            
            String czzdid = "";
            if (czzdid.equals("null")) {
                czzdid = "";
            }       
            String nhjcdy = req.getParameter("nhjcdy");//ҳ��û��  ���� 
            String ERPbh ="";
            String dhID ="";
            String zhwgID = "";
            String dzywID ="";
            String edhdl ="";
            String longitude = "";
            String latitude = "";       
            String dfzflx =  "0";//���֧������
            if("�½�".equals(dfzflx)){
                dfzflx = "dfzflx01";
            }else if("��ͬ".equals(dfzflx)){
                dfzflx = "dfzflx02";
            }else if("Ԥ֧".equals(dfzflx)){
                dfzflx = "dfzflx03";
            }else if("�忨".equals(dfzflx)){
                dfzflx = "dfzflx04";
            }
            
            String fkfs = "0";//���ʽ
            String fkzq = "0";//��������
            String zbdyhh = "";//�Ա����û���
            String watchcost = "0";//�ױ�Ʒ�
            String beilv = "1";//����
            String linelosstype = "0";//��������
            String linelossvalue ="";//����ֵ
            String dbid = "";//���ID
            String gldb ="";//������  ҳ��û�в���
            String dbyt ="";//�����;
            String csds = "";//��ʼ����
            String cssytime =  "";//��ʼʹ��ʱ��
            String kzid = "";
            String dbidft ="";//���id
            String lyjhjgs = "0";//¥���������
            //String gxxx = "0";//������Ϣ
            String ydbid = "";//ԭ���id
            String jskssj= "";//���迪ʼʱ��
            String jsjssj =  "";//�������ʱ��
            String xmh = "";//��Ŀ��
            String ygd =  "0";//Զ����
            String ysd=  "0";//Զ�ܵ�s
            
            String zybyqlx =  "";//���б�ѹ������
            String bsdl ="0";//�������
            
            String kts = "0";//�յ���
            String ktzgl = "0";//�յ��ܹ���
            
            SiteManage bean = new SiteManage();
            //ɾ�����ֶ�memo kzid PUE xuni czzd jnjsms caiji
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
                msg = "�޸�վ��ɹ�     վ�����ƣ�"+jzname+" ������"+shi+","+xian+","+xiaoqu;
            }else {
                msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸�վ��");
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
            msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
            
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//�÷�����
            String jzname = req.getParameter("jzname");
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign") != null ? req.getParameter("bgsign") : "0";//�Ƿ���
            String dianfei = req.getParameter("danjia");
            String zdcode = req.getParameter("zdcode");//ҳ���Ѿ�û�� 
            String jzxlx = req.getParameter("jzxlx");
            String jflx = req.getParameter("jflx");
            String jrwtype=req.getParameter("jrwtype");
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
            String sydate = req.getParameter("sydate");//Ͷ��ʹ��ʱ��
            String qyzt = req.getParameter("qyzt")!= null ? req.getParameter("qyzt") : "1";//վ������״̬
            String jlfh=req.getParameter("jlfh") != null ? req.getParameter("jlfh") : "";
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "0";//������
            //վ�㸽����Ϣ
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "0";//��������
            String g2=req.getParameter("g2") != null ? req.getParameter("g2") : "0";//2g�豸
            String g3=req.getParameter("g3") != null ? req.getParameter("g3") : "0";//3g�豸
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//����豸
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//�����豸
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//��Ƶ����
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//��������
            String kdsbsl=req.getParameter("kdsbsl") != null ? req.getParameter("kdsbsl") : "";//����豸����
            String yysbsl=req.getParameter("yysbsl") != null ? req.getParameter("yysbsl") : "";//�����豸����
            String kt1=req.getParameter("kt1") != null ? req.getParameter("kt1") : "0";//�յ�1
            String kt2=req.getParameter("kt2") != null ? req.getParameter("kt2") : "0";//�յ�2
            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//ֱ����
            //��̯
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "0";//����%
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//�칫%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//Ӫҵ%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//��Ϣ��֧��%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//����Ͷ��%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//������%
            
            String changjia = req.getParameter("changjia") !=null ? req.getParameter("changjia") : "";//����
            String jzlx = req.getParameter("jzlx") !=null ? req.getParameter("jzlx") : "";//��վ����
            String shebei = req.getParameter("shebei") !=null ? req.getParameter("shebei") : "";//�豸����
            String bbu = req.getParameter("bbu") !=null ? req.getParameter("bbu") : "0";//bbu����
            String rru = req.getParameter("rru") !=null ? req.getParameter("rru") : "0";//rru����
            String ydshebei = req.getParameter("ydshebei") !=null ? req.getParameter("ydshebei") :"0";//�ƶ��豸����
            String gxgwsl = req.getParameter("gxgwsl") !=null ? req.getParameter("gxgwsl") : "0";//��������豸����
            String twgx = req.getParameter("twgx") !=null ? req.getParameter("twgx")  : "";//��������
            String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//����
            String g2xqs = req.getParameter("g2xqs") !=null ? req.getParameter("g2xqs") : "0";
            String g3sqsl = req.getParameter("g3sqsl") !=null ? req.getParameter("g3sqsl") : "0";
            String ydgxsbsl = req.getParameter("ydgxsbsl") !=null ? req.getParameter("ydgxsbsl")  : "0";
            String dxgxsbsl = req.getParameter("dxgxsbsl") !=null ? req.getParameter("dxgxsbsl")  : "0";
            String  ysjts= req.getParameter("ysjts")!=null?req.getParameter("ysjts"):"0";//��ˮ��̨��
            String  wjts= req.getParameter("wjts")!=null?req.getParameter("wjts"):"0";//΢��̨��
            String  yybgkt= req.getParameter("yybgkt")!=null?req.getParameter("yybgkt"):"0";//Ӫҵ�칫�յ�
            String  jfsckt= req.getParameter("jfsckt")!=null?req.getParameter("jfsckt"):"0";//���������յ�
            
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
            
            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "��";
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
            String nhjcdy = req.getParameter("nhjcdy");//ҳ��û��  ���� 
            String ERPbh = req.getParameter("ERPbh") != null ? req.getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req.getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req.getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req.getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req.getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req.getParameter("latitude") : "";     
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//���֧������
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "0";//���ʽ
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//��������
            String zbdyhh = req.getParameter("zbdyhh") != null ? req.getParameter("zbdyhh") : "";//�Ա����û���
            String watchcost = req.getParameter("watchcost") != null ? req.getParameter("watchcost") : "0";//�ױ�Ʒ�
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//����
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//��������
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//����ֵ
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//���ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//������  ҳ��û�в���
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//�����;
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//��ʼ����
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//��ʼʹ��ʱ��
            String kzid = req.getParameter("kzid");
            String dbidft = req.getParameter("dbid");//���id
            String lyjhjgs = req.getParameter("lyjhjgs") != null ? req.getParameter("lyjhjgs") : "0";//¥���������
            String gxxx = req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "0";//������Ϣ
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//ԭ���id
            String jskssj= req.getParameter("jskssj") != null ? req.getParameter("jskssj") : "";//���迪ʼʱ��
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//�������ʱ��
            String xmh = req.getParameter("xmh") != null ? req.getParameter("xmh") : "";//��Ŀ��
            String ygd = req.getParameter("ygd") != null ? req.getParameter("ygd") : "0";//Զ����
            String ysd= req.getParameter("ysd") != null ? req.getParameter("ysd") : "0";//Զ�ܵ�
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
            //ɾ�����ֶ�memo kzid PUE xuni czzd jnjsms caiji
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
                msg = "�޸�վ��ɹ�վ�����ƣ�"+jzname+"������"+shi+","+xian+","+xiaoqu;
            }else {
                msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸�վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            session.setAttribute("sname1", sname1);
            resp.sendRedirect(path + "/web/msg.jsp");

        }else if (action.equals("shenhemodifySite")) {//վ����� ������� �޸�վ��ҳ��
        
            //shi1="+shi+"&xian1="+xian+"&xiaoqu1="+xiaoqu+"&sname1="+sname+"&szdcode1="+szdcode+"&stationtype1="+stationtype+"&jzproperty1="+jzproperty+"&jztype1="+jztype+"&lrrq1="+lrrq+"&id="+zdcode

            String id = req.getParameter("id");
            
            String shi2 = req.getParameter("shi2");//�Ѵ���������������վ�����
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
            msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
            
            String shi = req.getParameter("shi");
            String xian = req.getParameter("xian");
            String xiaoqu = req.getParameter("xiaoqu");
            String jztype = req.getParameter("jztype");
            String jzproperty = req.getParameter("jzproperty");
            String yflx = req.getParameter("yflx") != null ? req.getParameter("yflx") : "0";//�÷�����
            String jzname = req.getParameter("jzname");
            String bieming = req.getParameter("bieming");
            String address = req.getParameter("address");
            String bgsign = req.getParameter("bgsign") != null ? req.getParameter("bgsign") : "0";//�Ƿ���
            String dianfei = req.getParameter("danjia");
            String zdcode = req.getParameter("zdcode");//ҳ���Ѿ�û�� 
            String jzxlx = req.getParameter("jzxlx");
            String jflx = req.getParameter("jflx");
            String jrwtype=req.getParameter("jrwtype");
            String stationtype=req.getParameter("stationtype");
            //String signtypenum=req.getParameter("signtypenum");
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
            String sydate = req.getParameter("sydate");//Ͷ��ʹ��ʱ��
            String qyzt = req.getParameter("qyzt")!= null ? req.getParameter("qyzt") : "1";//վ������״̬
            String jlfh=req.getParameter("jlfh") != null ? req.getParameter("jlfh") : "";
            String gsf=req.getParameter("gsf") != null ? req.getParameter("gsf") : "0";//������
            //վ�㸽����Ϣ
            String dytype=req.getParameter("dytype") != null ? req.getParameter("dytype") : "0";//��������
            String g2=req.getParameter("g2") != null ? req.getParameter("g2") : "0";//2g�豸
            String g3=req.getParameter("g3") != null ? req.getParameter("g3") : "0";//3g�豸
            String kdsb=req.getParameter("kdsb") != null ? req.getParameter("kdsb") : "0";//����豸
            String yysb=req.getParameter("yysb") != null ? req.getParameter("yysb") : "0";//�����豸
            String zpsl=req.getParameter("zpslzd") != null ? req.getParameter("zpslzd") : "";//��Ƶ����
            String zssl=req.getParameter("zssl") != null ? req.getParameter("zssl") : "";//��������
            String kdsbsl=req.getParameter("kdsbsl") != null ? req.getParameter("kdsbsl") : "";//����豸����
            String yysbsl=req.getParameter("yysbsl") != null ? req.getParameter("yysbsl") : "";//�����豸����
            String kt1=req.getParameter("kt1") != null ? req.getParameter("kt1") : "0";//�յ�1
            String kt2=req.getParameter("kt2") != null ? req.getParameter("kt2") : "0";//�յ�2
            String zgd=req.getParameter("zgd") != null ? req.getParameter("zgd") : "0";//ֱ����
            //��̯
            String sc=req.getParameter("sc") != null ? req.getParameter("sc") : "100";//����%
            String bg=req.getParameter("bg") != null ? req.getParameter("bg") : "0";//�칫%
            String yy=req.getParameter("yy") != null ? req.getParameter("yy") : "0";//Ӫҵ%
            String xxhzc=req.getParameter("xxhzc") != null ? req.getParameter("xxhzc") : "0";//��Ϣ��֧��%
            String jstz=req.getParameter("jstz") != null ? req.getParameter("jstz") : "0";//����Ͷ��%
            String dddf=req.getParameter("dddf") != null ? req.getParameter("dddf") : "0";//������%
            
            
            String changjia = req.getParameter("cj") !=null ? req.getParameter("cj") : "";//����
            String jzlx = req.getParameter("jzlx2") !=null ? req.getParameter("jzlx2") : "";//��վ����
            String shebei = req.getParameter("sblx") !=null ? req.getParameter("sblx") : "";//�豸����
            String bbu = req.getParameter("bbu") !=null ? req.getParameter("bbu") : "0";//bbu����
            String rru = req.getParameter("rru") !=null ? req.getParameter("rru") : "0";//rru����
            String ydshebei = req.getParameter("ydshebei") !=null ? req.getParameter("ydshebei") :"0";//�ƶ��豸����
            String gxgwsl = req.getParameter("gxgwsl") !=null ? req.getParameter("gxgwsl") : "0";//��������豸����
            String twgx = req.getParameter("twgx") !=null ? req.getParameter("twgx")  : "";//��������
            String bm = req.getParameter("bm") !=null ? req.getParameter("bm")  : "";//����
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

            String jnglmk = req.getParameter("jnglmk") != null ? req.getParameter("jnglmk") : "��";
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
            String nhjcdy = req.getParameter("nhjcdy");//ҳ��û��  ���� 
            String ERPbh = req.getParameter("ERPbh") != null ? req.getParameter("ERPbh") : "";
            String dhID = req.getParameter("dhID") != null ? req.getParameter("dhID") : "";
            String zhwgID = req.getParameter("zhwgID") != null ? req.getParameter("zhwgID") : "";
            String dzywID = req.getParameter("dzywID") != null ? req.getParameter("dzywID") : "";
            String edhdl = req.getParameter("edhdl") != null ? req.getParameter("edhdl") : "";
            String longitude = req.getParameter("longitude") != null ? req.getParameter("longitude") : "";
            String latitude = req.getParameter("latitude") != null ? req.getParameter("latitude") : "";     
            String dfzflx = req.getParameter("dfzflx") != null ? req.getParameter("dfzflx") : "0";//���֧������
            String fkfs = req.getParameter("fkfs") != null ? req.getParameter("fkfs") : "0";//���ʽ
            String fkzq = req.getParameter("fkzq") != null ? req.getParameter("fkzq") : "0";//��������
            String zbdyhh = req.getParameter("zbdyhh") != null ? req.getParameter("zbdyhh") : "";//�Ա����û���
            String watchcost = req.getParameter("watchcost") != null ? req.getParameter("watchcost") : "0";//�ױ�Ʒ�
            String beilv = req.getParameter("beilv") != null ? req.getParameter("beilv") : "1";//����
            String linelosstype = req.getParameter("linelosstype") != null ? req.getParameter("linelosstype") : "0";//��������
            String linelossvalue = req.getParameter("linelossvalue") != null ? req.getParameter("linelossvalue") : "";//����ֵ
            String dbid = req.getParameter("dbid") != null ? req.getParameter("dbid") : "";//���ID
            String gldb = req.getParameter("gldb") != null ? req.getParameter("gldb") : "";//������  ҳ��û�в���
            String dbyt = req.getParameter("dbyt") != null ? req.getParameter("dbyt") : "";//�����;
            String csds = req.getParameter("csds") != null ? req.getParameter("csds") : "";//��ʼ����
            String cssytime = req.getParameter("cssytime") != null ? req.getParameter("cssytime") : "";//��ʼʹ��ʱ��
            String kzid = req.getParameter("kzid");
            String dbidft = req.getParameter("dbid");//���id
            String lyjhjgs = req.getParameter("lyjhjgs") != null ? req.getParameter("lyjhjgs") : "0";//¥���������
            String gxxx = req.getParameter("gxxx") != null ? req.getParameter("gxxx") : "0";//������Ϣ
            String ydbid = req.getParameter("ydbid") != null ? req.getParameter("ydbid") : "";//ԭ���id
            String jskssj= req.getParameter("jskssj") != null ? req.getParameter("jskssj") : "";//���迪ʼʱ��
            String jsjssj = req.getParameter("jsjssj") != null ? req.getParameter("jsjssj") : "";//�������ʱ��
            String xmh = req.getParameter("xmh") != null ? req.getParameter("xmh") : "";//��Ŀ��
            String ygd = req.getParameter("ygd") != null ? req.getParameter("ygd") : "0";//Զ����
            String ysd= req.getParameter("ysd") != null ? req.getParameter("ysd") : "0";//Զ�ܵ�
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
            //ɾ�����ֶ�memo kzid PUE xuni czzd jnjsms caiji
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
                msg = "�޸�վ��ɹ���";
            }else {
                msg = "�޸�վ��ʧ�ܣ������Ի������Ա��ϵ��";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸�վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            //session.setAttribute("sname1", sname1);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
        else if (action.equals("deledatequyu")) {
            url = path + "/web/sdttWeb/systemManager/zoneManager.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��������Ϣʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData1(id);
            if (retsign == 1) {
                msg = "ɾ��������Ϣ�ɹ���";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��������Ϣ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");  
            }//ɾ�����Ź���
        else if (action.equals("deledatebumen")) {
            url = path + "/web/sdttWeb/systemManager/department.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��������Ϣʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDatabumenguanli(id);
            if (retsign == 1) {
                msg = "ɾ��������Ϣ�ɹ���";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��������Ϣ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");  
            }
        else if (action.equals("deleteDept")) {
        	url = path + "/web/sdttWeb/systemManager/department_pro.jsp";
        	String id = req.getParameter("id");
        	msg = "ɾ��������Ϣʧ�ܣ������Ի������Ա��ϵ��";
        	SiteManage bean = new SiteManage();
        	int retsign = bean.delDatabumenguanli(id);
        	if (retsign == 1) {
        		msg = "ɾ��������Ϣ�ɹ���";
        	} 
        	log.write(msg, accountid, req.getRemoteAddr(), "ɾ��������Ϣ");
        	session.setAttribute("url", url);
        	session.setAttribute("msg", msg);
        	resp.sendRedirect(path + "/web/msg.jsp");  
        }
        else if (action.equals("delsiteXiaoxi")) {
            url = path + "/web/sdttWeb/systemManager/msgManager.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��������Ϣʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData2(id);
            if (retsign == 1) {
                msg = "ɾ��������Ϣ�ɹ���";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��������Ϣ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");  
            }
        
        
        else if (action.equals("delsite")) {
            url = path + "/web/sdttWeb/jizan/sitemanage.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��վ��ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "ɾ��վ��ɹ���";
            } else if (retsign == 2) {
                msg = "�й����ĵ����Ϣ������Ϣ��������ɾ��վ�㣬����ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }   else if (action.equals("delbaozhang")) {
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��������Ϣʧ�ܣ������Ի������Ա��ϵ��";
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
            		System.out.println("���ڶ���С�����ڶ������л�ת");
            		bean.updateHZSTATE(id, ere);
            	}
            }
            int retsign = bean.delDataBaozhang(id);
            if (retsign == 1) {
                msg = "ɾ��������Ϣ�ɹ���";
            } else if (retsign == 2) {
                msg = "�й����ĵ����Ϣ������Ϣ��������ɾ��վ�㣬����ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }  
        else if (action.equals("delttbaozhang")) {
            url = path + "/web/sdttWeb/baozhang/TietaBaozhang.jsp";
            String id = req.getParameter("id");
            msg = "ɾ������������Ϣʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDataBaozhang(id);
            if (retsign == 1) {
                msg = "ɾ������������Ϣ�ɹ���";
            } else if (retsign == 2) {
                msg = "�й����ĵ����Ϣ������Ϣ��������ɾ��վ�㣬����ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        } else if (action.equals("tijiaobaozhang")) {
        	  msg = "�ύ������Ϣʧ�ܣ������Ի������Ա��ϵ��";
            url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
            String id = req.getParameter("id");
            String DIANBIAOID = req.getParameter("dianbiaoid");
            if(DIANBIAOID==null || DIANBIAOID.equals("")){
            	DIANBIAOID="0";
            	  msg = "�ύ������Ϣʧ�ܣ������Ի������Ա��ϵ��";
            }
            
            
          
//            SiteManage bean = new SiteManage();
//            int retsign = bean.tijiaoDataBaozhang(id);
            
            String ACCOUNTID=account.getAccountId();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ACCTIME =sdf.format(new Date());
            SiteManage bean = new SiteManage();
            	//�ж�����ִ����
                WorkFlowBean workFlowBean= new WorkFlowBean();
                String flowId=workFlowBean.getFlowId("�������");
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
                    	msg = "δ�ҵ�ִ���ˣ���ȷ�Ϻ�����ӣ���";
                    	//return;
                    }else {
                    	 //int bzId = bean.addDataBaoZhang(DIANBIAOID,STARTTIME,ENDTIME,SQDS,BQDS,DIANLIANG,ALLMONEY,DIANSUN,SQDJ,PRICE,DAYNUM,RJYDL,SQRJYDL,ACCOUNTID,ACCTIME,BEILV,"1");
//                    	 int retsign = bean.tijiaoDataBaozhang(id);
                    	 int retsign = bean.updateBaoZhangState("1",id);
                    	 if (retsign == 1) {
                             msg = "�ύ������Ϣ�ɹ���";
                         } else {
                             msg = "�ύ������Ϣʧ�ܣ������Ի������Ա��ϵ��";
                         }
                         
                         
                         //�������
                        
                         String taskId=id+"";
                         String taskType="ELECTRICFEES";
                         workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
                         
                    }
                }
                	 
            
            
           
            log.write(msg, accountid, req.getRemoteAddr(), "�ύ����");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if(action.equals("delsitedf")){

            url = path + "/web/jizhan/sitemanagedf.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��վ��ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDatadf(id);
            if (retsign == 1) {
                msg = "ɾ��վ��ɹ���";
            } else if (retsign == 2) {
                msg = "�й����ĵ����Ϣ������Ϣ��������ɾ��վ�㣬����ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        } else if (action.equals("newdelsite")) {
            url = path + "/web/jizhan/Newsitemanage.jsp";
            String id = req.getParameter("id");
            msg = "ɾ��վ��ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "ɾ��վ��ɹ���";
            } else if (retsign == 2) {
                msg = "�й����ĵ����Ϣ������Ϣ��������ɾ��վ�㣬����ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ��վ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if (action.equals("addsign")){
            
            url = path + "/web/sightcing/signmanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "��ӱ������ʧ�ܣ������Ի������Ա��ϵ��";
            String name = req.getParameter("signname");//�����������
            
            String type = req.getParameter("stationtype");//վ������
            String olt = req.getParameter("oltg");//OLTG
            String g2 = req.getParameter("g2");//2g
            String g3 = req.getParameter("g3");//3g
            String ktnum = req.getParameter("ktnum");//�յ�����
            String dytype = req.getParameter("dytype");//��������
            SiteManage bean = new SiteManage();
            int retsign = bean.addSign(name,type,olt,g2,g3,ktnum,dytype);
            if (retsign == 1) {
                msg = "��ӱ�����ͳɹ���";
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�������ʹ����ظ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "��ӱ������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if (action.equals("modifySign")){
            url = path + "/web/sightcing/signmanage.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "�޸ı������ʧ�ܣ������Ի������Ա��ϵ��";
            String id = req.getParameter("id");//������ͱ��
            String name = req.getParameter("signname");//�����������
            String type = req.getParameter("stationtype");//վ������
            String olt = req.getParameter("oltg");//OLTG
            String g2 = req.getParameter("g2");//2g
            String g3 = req.getParameter("g3");//3g
            String ktnum = req.getParameter("ktnum");//�յ�����
            String dytype = req.getParameter("dytype");//��������
            SiteManage bean = new SiteManage();
            int retsign = bean.modifySign(id,name,type,olt,g2,g3,ktnum,dytype);
            if (retsign == 1) {
                msg = "�޸ı�˳ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸ı��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if (action.equals("delsign")){
            url = path + "/web/sightcing/signmanage.jsp";
            String id = req.getParameter("id");
            msg = "ɾ���������ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delSign(id);
            if (retsign == 1) {
                msg = "ɾ����˳ɹ���";
            } else if (retsign == 2) {
                msg = "�й�������Ϣ��������ɾ��������ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ���������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
            
        }
        //�����������
    else if (action.equals("addquyu1")){
            
            url = path + "/web/sdttWeb/systemManager/addzoneManager.jsp";
            msg = "�����Ϣʧ�ܣ�";
            
        String quyu = req.getParameter("quyu")!= null ? req.getParameter("quyu") : "";//���
        String mingcheng = req.getParameter("mingcheng")!= null ? req.getParameter("mingcheng") : "";//����
        String id=req.getParameter("id")!=null?req.getParameter("id"):"";//�޸�
        String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//�޸�
            
            SiteManage bean = new SiteManage();
            int retsign=0;
            if(!"1".equals(bz)){
            retsign= bean.addquyuguanli1(quyu,mingcheng);
            }
            if (retsign == 1) {
            int result = GroupMessage.sendWX(quyu+"\n"+mingcheng);
                if(0 == result){
                    msg = "�����Ϣʧ�ܣ�";
            }else{
                    msg = "�����Ϣ�ɹ���";
            
        
            }
            }
        if("1".equals(bz)){
        retsign= bean.updatequyu(quyu,mingcheng,id);
                if (retsign == 1) {
                    int result = GroupMessage.sendWX(quyu+"\n"+mingcheng);
                    if(0 == result){
                        msg = "�޸���Ϣ�ɹ���";
                    }else{
                        msg = "�޸���Ϣ�ɹ���";
                    }
                } else if (retsign == 2) {
                    msg = "�޸�ʧ�ܣ�";
            }
        }
            
            log.write(msg, accountid, req.getRemoteAddr(), "���������Ϣ");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        
        }//��Ӳ��Ź���
    else if (action.equals("saveDept")){
    	url = path + "/web/sdttWeb/systemManager/addDepartment_pro.jsp";
        msg = "�����Ϣʧ�ܣ�";
        //����ҳ�����
    	String shi = req.getParameter("shi")!= null ? req.getParameter("shi") : "";//��
    	String deptcode = req.getParameter("deptcode")!= null ? req.getParameter("deptcode") : "";//����
    	String deptname = req.getParameter("deptname")!= null ? req.getParameter("deptname") : "";//����
        String fdeptcode = req.getParameter("fdeptcode")!= null ? req.getParameter("fdeptcode") : "";//�ֹ�˾
        SiteManage bean = new SiteManage();
        //�Զ��岿�ű��뼰�ֹ�˾����
        if(deptcode.isEmpty()){
    	    String olddeptcode = bean.getMaxDeptcode(shi, fdeptcode);
            if(olddeptcode != null && !olddeptcode.isEmpty()){
           	    BigDecimal b = new BigDecimal(olddeptcode);
          	    deptcode = b.add( new BigDecimal(1)).toString();
            }else{
          	    deptcode = shi;
            }
        }
        String id=req.getParameter("id")!=null?req.getParameter("id"):"";//�޸�
        //���ݿ����
        int cnt = 0;
        if(id !=null && !id.isEmpty()){//�޸�
        	cnt = bean.updateDept(deptname, id);
        	if(cnt > 0){
        		 msg = "�����Ϣ�ɹ���";
        	}else{
        		 msg = "�����Ϣʧ�ܣ�";
        	}
        }else{//����
        	cnt = bean.addDept(shi, deptcode, deptname, fdeptcode);
        	if(cnt > 0){
        		msg = "�޸���Ϣ�ɹ���";
	       	}else{
	       		 msg = "�޸���Ϣʧ�ܣ�";
	       	}
        }
        log.write(msg, accountid, req.getRemoteAddr(), "��Ӳ�����Ϣ");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");
    	
    }
        else if (action.equals("addbumen1")){
                
                url = path + "/web/sdttWeb/systemManager/addDepartment.jsp";
                msg = "�����Ϣʧ�ܣ�";
                
            String bumen = req.getParameter("bumen")!= null ? req.getParameter("bumen") : "";//���
            String mingcheng = req.getParameter("mingcheng")!= null ? req.getParameter("mingcheng") : "";//����
            String bianma=req.getParameter("bianma")!=null?req.getParameter("bianma"):"";
            String fglength=req.getParameter("fglength")!=null?req.getParameter("fglength"):"";
            
            String id=req.getParameter("id")!=null?req.getParameter("id"):"";//�޸�
            String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//�޸�
                SiteManage bean = new SiteManage();
                int retsign=0;
                if(!"1".equals(bz)){
                retsign= bean.addbumenguanli1(bumen,mingcheng,bianma,fglength);
                }
                if (retsign == 1) {
                int result = GroupMessage.sendWX(bumen+"\n"+mingcheng);
                    if(0 == result){
                        msg = "�����Ϣʧ�ܣ�";
                }else{
                        msg = "�����Ϣ�ɹ���";
                
            
                }
                }
            if("1".equals(bz)){
            retsign= bean.updatebumen(mingcheng,id);
                    if (retsign == 1) {
                        int result = GroupMessage.sendWX(mingcheng);
                        if(0 == result){
                            msg = "�޸���Ϣ�ɹ���";
                        }else{
                            msg = "�޸���Ϣ�ɹ���";
                        }
                    } else if (retsign == 2) {
                        msg = "�޸�ʧ�ܣ�";
                }
            }
                
                log.write(msg, accountid, req.getRemoteAddr(), "��Ӳ�����Ϣ");
                session.setAttribute("url", url);
                session.setAttribute("msg", msg);
                resp.sendRedirect(path + "/web/msg.jsp");
            
            }
                
                //��ӹ���
            else if (action.equals("addggao")){
                
                url = path + "/web/sdttWeb/systemManager/addmsgManager.jsp";
                msg = "��ӹ���ʧ�ܣ������Ի������Ա��ϵ��";
                
                
                String xxlx = req.getParameter("xxlx")!= null ? req.getParameter("xxlx") : "0";//��Ϣ����
                String bt = req.getParameter("dbname")!= null ? req.getParameter("dbname") : "";//����
                String nr = req.getParameter("nr")!= null ? req.getParameter("nr") : "";//����
                String id=req.getParameter("id")!=null?req.getParameter("id"):"";//�޸�
                String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//�޸�
                SiteManage bean = new SiteManage();
                
                int retsign=0;
                if(!"1".equals(bz)){
                retsign= bean.addGgao(xxlx,bt,nr);
                if (retsign == 1) {
                    int result = GroupMessage.sendWX(bt+"\n"+nr);
                    if(0 == result){
                        msg = "��ӹ���ɹ���������Ⱥ����";
                    }else{
                        msg = "��ӹ���ɹ�������Ⱥ��ʧ�ܣ�";
                    }
                } else if (retsign == 2) {
                    msg = "���ʧ�ܣ���������ظ���";
                }
                }
                if("1".equals(bz)){
                    retsign= bean.addGgaoxg(xxlx,bt,nr,id);
                    if (retsign == 1) {
                        int result = GroupMessage.sendWX(bt+"\n"+nr);
                        if(0 == result){
                            msg = "�޸Ĺ���ɹ���";
                        }else{
                            msg = "�޸Ĺ���ɹ���";
                        }
                    } else if (retsign == 2) {
                        msg = "���ʧ�ܣ���������ظ���";
                    }
                }
                
                log.write(msg, accountid, req.getRemoteAddr(), "��ӹ���");
                session.setAttribute("url", url);
                session.setAttribute("msg", msg);
                resp.sendRedirect(path + "/web/msg.jsp");
                
            }
            
            //��ӹ���
        else if (action.equals("addggao")){
            
            url = path + "/web/sdttWeb/systemManager/addmsgManager.jsp";
            msg = "��ӹ���ʧ�ܣ������Ի������Ա��ϵ��";
            
            
            String xxlx = req.getParameter("xxlx")!= null ? req.getParameter("xxlx") : "0";//��Ϣ����
            String bt = req.getParameter("dbname")!= null ? req.getParameter("dbname") : "";//����
            String nr = req.getParameter("nr")!= null ? req.getParameter("nr") : "";//����
            String id=req.getParameter("id")!=null?req.getParameter("id"):"";//�޸�
            String bz=req.getParameter("bz")!=null?req.getParameter("bz"):"";//�޸�
            SiteManage bean = new SiteManage();
            
            int retsign=0;
            if(!"1".equals(bz)){
            retsign= bean.addGgao(xxlx,bt,nr);
            if (retsign == 1) {
                int result = GroupMessage.sendWX(bt+"\n"+nr);
                if(0 == result){
                    msg = "��ӹ���ɹ���������Ⱥ����";
                }else{
                    msg = "��ӹ���ɹ�������Ⱥ��ʧ�ܣ�";
                }
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ���������ظ���";
            }
            }
            if("1".equals(bz)){
                retsign= bean.addGgaoxg(xxlx,bt,nr,id);
                if (retsign == 1) {
                    int result = GroupMessage.sendWX(bt+"\n"+nr);
                    if(0 == result){
                        msg = "�޸Ĺ���ɹ���";
                    }else{
                        msg = "�޸Ĺ���ɹ���";
                    }
                } else if (retsign == 2) {
                    msg = "���ʧ�ܣ���������ظ���";
                }
            }
            
            log.write(msg, accountid, req.getRemoteAddr(), "��ӹ���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
            
        }else if (action.equals("modifyggao")){
            url = path + "/web/sys/allggao.jsp";
            SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lrr=req.getParameter("accountname")!= null ? req.getParameter("accountname") : "";//¼����
            String lrrId=req.getParameter("accountId")!= null ? req.getParameter("accountId") : "";//¼����id
            String dqtime=mat.format(new Date());//ϵͳ��ǰʱ��
            msg = "�޸Ĺ���ʧ�ܣ������Ի������Ա��ϵ��";
            String id = req.getParameter("id");//����id
            String xxtype = req.getParameter("xxtype");//��Ϣ����
            String ggtime = req.getParameter("ggtime");//����ʱ��
            String bt = req.getParameter("bt");//����
            String nr = req.getParameter("nr");//����
            
            SiteManage bean = new SiteManage();
            int retsign = bean.modifyGgao(id,xxtype,ggtime,dqtime,bt,nr,lrr,lrrId);
            if (retsign == 1) {
                msg = "�޸Ĺ���ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "�޸Ĺ���");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if (action.equals("delggao")){
            url = path + "/web/sys/allggao.jsp";
            String id = req.getParameter("id");
            msg = "ɾ������ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delggao(id);
            if (retsign == 1) {
                msg = "ɾ������ɹ���";
            } else if (retsign == 2) {
                msg = "���Ȩ�����ޣ�������ɾ��������ϵ����Ա";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("dlUpdate")){//ȫʡ�����������
            url = path + "/web/tongjichaxun/dlUpdate.jsp";//BeginTime
            String month = req.getParameter("BeginTime") != null ? req.getParameter("BeginTime") : "";//ʱ��
            SiteManage bean = new SiteManage();
            int rS = bean.dlUpdate(month);
            if (rS == 1) {
                msg = "�޸ĳɹ���";
            } else if (rS == 0) {
                msg = "���Ȩ�����޻����޸�ʧ�ܣ�����ϵ����Ա";
            }
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("nhupdate")){//ȫʡ�����������
            url = path + "/web/tongjichaxun/nhupdate.jsp";//BeginTime
            String month = req.getParameter("currentmonth") != null ? req.getParameter("currentmonth") : "";//ʱ��
            SiteManage bean = new SiteManage();
            bean.nhdel(month);
            int rs = bean.nhupdate(month);
            if (rs == 1) {
                msg = "���³ɹ���";
            } else if (rs == 0) {
                msg = "���Ȩ�����޻����޸�ʧ�ܣ�����ϵ����Ա";
            }
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("addSitedf")){
            url = path + "/web/jizhan/addsitedf.jsp";
            String id="";
            String dfwhszj=req.getParameter("dfwhszj")!=null?req.getParameter("dfwhszj"):"";//���µ渶δ�����ʽ�(Ԫ)
            String dfwhszj_byhs=req.getParameter("dfwhszj_byhs")!=null?req.getParameter("dfwhszj_byhs"):"";//���µ渶δ�����ʽ𣬱����ѻؿ�(Ԫ)
            String dhszj=req.getParameter("dhszj")!=null?req.getParameter("dhszj"):"";//�������ʽ�(Ԫ)
            String dhszj_yyshd=req.getParameter("dhszj_yyshd")!=null?req.getParameter("dhszj_yyshd"):"";//�������ʽ��Ѻ���Ӫ�������ϸ�˶�
            String dhszj_ykp=req.getParameter("dhszj_ykp")!=null?req.getParameter("dhszj_ykp"):"";//�������ʽ��Ѿ���Ʊ
            String dfhs_yys=req.getParameter("dfhs_yys")!=null?req.getParameter("dfhs_yys"):"";//��Ӫ��
            String dfhs_hdzb=req.getParameter("dfhs_hdzb")!=null?req.getParameter("dfhs_hdzb"):"";//�Ѻ˶�ռ�ȣ����������ʽ��Ѻ���Ӫ�������ϸ�˶ԡ�/���������ʽ�
            String dfhs_kpzb=req.getParameter("dfhs_kpzb")!=null?req.getParameter("dfhs_kpzb"):"";//�ѿ�Ʊռ�ȣ����������ʽ��ѿ�Ʊ��/���������ʽ�
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
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
                msg = "��ӵ�ѵ渶�ɹ�������";
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "��ӵ�ѵ渶");
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
            String entrypensonnel=req.getParameter("accountname");//¼����Ա
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
                    msg = "�޸��ֵ���Ϣ�ɹ�������";
                } else if (retsign == 2) {
                    msg = "�޸�ʧ�ܣ�";
                }
                log.write(msg, accountid, req.getRemoteAddr(), "�޸��ֵ���Ϣ");
            }
            if(!"1".equals(bz)){
                retsign=bean.adddatadfzd(db);
            if (retsign == 1) {
                msg = "����ֵ���Ϣ�ɹ�������";
            } else if (retsign == 2) {
                msg = "���ʧ�ܣ�";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "����ֵ���Ϣ");
            }
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }else if(action.equals("delsiteZD")){
            url = path + "/web/sdttWeb/sys/dictList.jsp";
            String id = req.getParameter("id");
            msg = "ɾ���ֵ���Ϣʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.delDatazd(id);
            if (retsign == 1) {
                msg = "ɾ���ֵ���Ϣ�ɹ���";
            } 
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ���ֵ���Ϣ");
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
            //���pue��¼ 
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
            * �����õ���/[��ֱ�����豸����*54V?+δ��صĽ������豸����+δ��ص�ֱ�����豸���ʣ�/0.9 *����*24Сʱ]��1000
            * jiaoliu_str  δ��صĽ������豸����
            * zhiliu_str   δ��ص�ֱ�����豸����
            * dl   �����õ���
            * value_str   ֱ�����豸����
            * sumDay_str   ����
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
    }//��֤�Ƿ񳭹���
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
        req.setCharacterEncoding("utf-8");//������������ʽ��
        resp.setContentType("text/html;charsetType=utf-8");//������������ʽ��
        
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
        msg = "��ӱ���ʧ�ܣ������Ի������Ա��ϵ��";
        String PUEZHI = req.getParameter("PUEZHI");
        //2018��3��22����˶ ������������ֶ�
        String BGPLL = req.getParameter("BGPLL");
        String FTDL = req.getParameter("FTDL");
        String FTJE = req.getParameter("FTJE");
        String SFYZ = req.getParameter("SFYZ");
        String FTBL = req.getParameter("FTBL");
        String BZQJ = req.getParameter("BZQJ");
        String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
        String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//˰��
        String STARTTIME_C = (req.getParameter("STARTTIME_C")!= null && !"".equals(req.getParameter("STARTTIME_C"))) ? req.getParameter("STARTTIME_C") : null;	//�ֻ�����ʼ����
        String ENDTIME_C = (req.getParameter("ENDTIME_C")!= null && !"".equals(req.getParameter("ENDTIME_C"))) ? req.getParameter("ENDTIME_C") : null;			//�ֻ������������
        String SQDS_C = (req.getParameter("SQDS_C")!= null && !"".equals(req.getParameter("SQDS_C"))) ? req.getParameter("SQDS_C") : "0";						//�ֻ��������ڶ���
        String BQDS_C = (req.getParameter("BQDS_C")!= null && !"".equals(req.getParameter("BQDS_C"))) ? req.getParameter("BQDS_C") : "0";						//�ֻ������ڶ���
        String DIANLIANG_C = (req.getParameter("DIANLIANG_C")!= null && !"".equals(req.getParameter("DIANLIANG_C"))) ? req.getParameter("DIANLIANG_C") : "0";	//�ֻ��������
        String DAYNUM_C = (req.getParameter("TIANSHU_C")!= null && !"".equals(req.getParameter("TIANSHU_C"))) ? req.getParameter("TIANSHU_C") : "0";			//�ֻ������õ�����
        String RJYDL_C = (req.getParameter("RJYDL_C")!= null && !"".equals(req.getParameter("RJYDL_C"))) ? req.getParameter("RJYDL_C") : "0";					//�վ��õ���
        String DianLiangPianLiShu = (req.getParameter("DianLiangPianLiShu")!= null && !"".equals(req.getParameter("DianLiangPianLiShu"))) ? req.getParameter("DianLiangPianLiShu") : "0";	//����ƫ����
        String RiQiPianLiShu = (req.getParameter("RiQiPianLiShu")!= null && !"".equals(req.getParameter("RiQiPianLiShu"))) ? req.getParameter("RiQiPianLiShu") : "0";					//����ƫ����
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
                    msg = "��ӱ��˳ɹ���";
                } else  {
                    msg = "���ʧ�ܣ�";
                }
                //�������
              	WorkFlowBean workFlowBean= new WorkFlowBean();
              	String currentNo =workFlowBean.getNextStep(flowId, "1");
              	String nextNo =workFlowBean.getNextStep(flowId, "2");
                String taskId=bzId+"";  
                String taskType="ELECTRICFEES";
                workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
             
                //���pue��¼ 
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
                 msg = "��ʱ���汨�˳ɹ���";
             } else  {
                 msg = "���ʧ�ܣ����˴����ظ���";
             }
        }
        
       
        log.write(msg, accountid, req.getRemoteAddr(), "��ӱ���");
        session.setAttribute("url", url);
        session.setAttribute("msg", msg);
        resp.sendRedirect(path + "/web/msg.jsp");
    }
else if(i==2){
	req.setCharacterEncoding("utf-8");//������������ʽ��
    resp.setContentType("text/html;charsetType=utf-8");//������������ʽ��
    
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
    msg = "�޸ı���ʧ�ܣ������Ի������Ա��ϵ��";
    
    //2017��11��22�� xiaokang modify
    String saveState = (req.getParameter("saveState")!= null && !"".equals(req.getParameter("saveState"))) ? req.getParameter("saveState") : "";
    //2018��3��9�� - gcl ˰��
    String TAX = (req.getParameter("TAX")!= null && !"".equals(req.getParameter("TAX"))) ? req.getParameter("TAX") : "0";//˰��
    
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//¼��ʱ��-gcl-2018��3��30��
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
				msg = "�ύ���˳ɹ���";
			} else {
				msg = "�ύʧ�ܣ����˴����ظ���";
			}
			// �������
			WorkFlowBean workFlowBean = new WorkFlowBean();
			String currentNo = workFlowBean.getNextStep(flowId, "1");
			String nextNo = workFlowBean.getNextStep(flowId, "2");
			String taskId = ID + "";
			String taskType = "ELECTRICFEES";
			workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo,
					account.getAccountId(), taskType, auditorid);
			
			//���pue��¼ 
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
            msg = "�޸ı��˳ɹ���";
        } else if (retsign == 2) {
            msg = "�޸�ʧ�ܣ����˴����ظ���";
        }
    }
    
    
 
    log.write(msg, accountid, req.getRemoteAddr(), "�޸ı���");
    session.setAttribute("url", url);
    session.setAttribute("msg", msg);
    resp.sendRedirect(path + "/web/msg.jsp");
}else if(i==3){

    req.setCharacterEncoding("utf-8");//������������ʽ��
    resp.setContentType("text/html;charsetType=utf-8");//������������ʽ��
    
    String path = req.getContextPath();
    DbLog log = new DbLog();
    Account account = new Account();
    
    HttpSession session = req.getSession();
    String url = path + "/web/jizhan/jizhanlist.jsp", msg = "";
    String accountid = (String) session.getAttribute("loginName");
    String accountSheng = (String) session.getAttribute("accountSheng");
    account=(Account)session.getAttribute("account");

    String action = req.getParameter("action");
	//���Ԥ����
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
    		//���ݵ��id��ȡ������α�����Ϣ   ������sap��ŵ�
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
            msg = "�޸�վ�㵥��ʧ�ܣ������Ի������Ա��ϵ��";
            JiZhanBean bean = new JiZhanBean();
            String retsign = bean.modifyDanJia(id, ndianfei);
            log.write(retsign, accountid, req.getRemoteAddr(), "�޸�վ�㵥��");
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
      	// ���м�������
      	    private static double subtract(double v1,double v2){
      	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
      	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
      	        return b1.subtract(b2).doubleValue();  
      	    }
      	// ���мӷ�����
      	    public static double mul(double d1, double d2){        // ���г˷�����
      	         BigDecimal b1 = new BigDecimal(d1);
      	         BigDecimal b2 = new BigDecimal(d2);
      	        return b1.multiply(b2).doubleValue();
      	     }
      	// ���г�������
      	    public static  double div(double d1,double d2,int len) {// ���г�������
      	         BigDecimal b1 = new BigDecimal(d1);
      	         BigDecimal b2 = new BigDecimal(d2);
      	        return b1.divide(b2,len,BigDecimal.ROUND_HALF_UP).doubleValue();
      	     }
      	// ���������������
      	    public  double round(double d,int len) {     // ���������������
      	         BigDecimal b1 = new BigDecimal(d);
      	         BigDecimal b2 = new BigDecimal(1);
      	        // �κ�һ�����ֳ���1����ԭ����
      	        // ROUND_HALF_UP��BigDecimal��һ����������ʾ������������Ĳ���
      	        return b1.divide(b2, len,BigDecimal.ROUND_HALF_UP).doubleValue();
      	    }
}
