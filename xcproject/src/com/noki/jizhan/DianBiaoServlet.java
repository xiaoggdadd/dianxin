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
        	
            msg = "��ӵ��ʧ�ܣ������Ի������Ա��ϵ��";
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
            String dbqyzt=req.getParameter("dbqyzt");//�������״̬
            String zbdyhh=req.getParameter("zbdyhh");//�Ա����û���
            String ydbid=req.getParameter("ydbid");//ԭ���id
            String danjia=req.getParameter("danjia");//�����
            String entrytime=mat.format(new Date());
            String shi =req.getParameter("t_shi");
            String bsdl=req.getParameter("bsdl")==null?"":req.getParameter("bsdl");//�������
            String zgdj = req.getParameter("zgdj");//�ݹ�����
            if(zgdj == null || "".equals(zgdj)){
            	zgdj = "0";
            }
           
            String zzsl1 = req.getParameter("zzsl");//��ֵ˰��
            String zzsl = zzsl1.trim();
           
            System.out.println(shi+"��");
            DianBiaoBean bean = new DianBiaoBean();
            int retsign = bean.addData(dbid,zdid, dllx, ydsb,shi, sszy, dbyt, csds,
                                       cssytime, beilv, dbxh, memo, accountid,netpoint_name,netpoint_id,yhdl,dfzflx,dbname,
                                       linelosstype,linelossvalue,entrypensonnel,entrytime,dbqyzt,zbdyhh,ydbid,danjia,bsdl,zgdj,zzsl,countbzw);
            if (retsign == 1) {
                msg = "��ӵ��ɹ���";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "��ӵ��");
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
        	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//¼��ʱ��
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�޸�ʱ��
        	String ENTRYTIMEOLD =mat.format(new Date());//¼��ʱ��
        	String ENTRYTIME =sdf.format(new Date());//�޸�ʱ��
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
        	/*//string ǿתdouble 
        	String ftbl1=req.getParameter("ftbl");
            double ftbl2=Double.valueOf(ftbl1).doubleValue()/100;//��������ʱ ��̯���������С�� ���� 20% ����Ϊ 0.2
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
        	String LJID="0"; //����ID
        	LJID = beans.getZDStationNo(zdid);
        	String dbtype="1"; //¼��
        	
    
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
        		System.out.println("�޸ĵ��"+sql);
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
        			msg="�޸ĵ��ɹ�";
        		}else{
        			msg="�޸�ʧ�ܣ�����ϵ����Ա��";
        		}
        		
        		//�ɱ������޸�����  �����޸������ѣ���ɾ�� �����
        		
            	//��Ӻ��㵥Ԫ
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

        		
        		log.write(msg, accountid, req.getRemoteAddr(), "�޸ĵ��");
                session.setAttribute("url", url);
                session.setAttribute("msg", msg);
                resp.sendRedirect(path + "/web/msg.jsp");
        	}else{
        	DianBiaoBean bean = new DianBiaoBean();
        	//���������ɹ���
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
                msg = "��ӵ��ɹ���";
            }else{
    			msg="��ӵ��ʧ�ܣ�����ϵ����Ա��";
    		}
        	
        	
        	
        	//��Ӻ��㵥Ԫ
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
        	
            log.write(msg, accountid, req.getRemoteAddr(), "��ӵ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        }//���ر���
        else if(action.equals("chbaozhang")){
       	 url = path + "/web/sdttWeb/baozhang/baozhang.jsp";
         String id = req.getParameter("id");
         msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
         SiteManage bean = new SiteManage();
         int retsign = bean.chBaozhang(id);
         if (retsign == 1) {
             msg = "���ر�����Ϣ�ɹ���";
         } else if (retsign == 2) {
             msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
         }
         log.write(msg, accountid, req.getRemoteAddr(), "���ر���");
         session.setAttribute("url", url);
         session.setAttribute("msg", msg);
         resp.sendRedirect(path + "/web/msg.jsp");
    }//���ص��������Ϣ
        else if(action.equals("chdianbiao")){
          	 url = path + "/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp";
            String id = req.getParameter("id");
            msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.chdianbiao(id);
            if (retsign == 1) {
                msg = "������Ϣ�ɹ���";
            } else if (retsign == 2) {
                msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "���ص������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
       }//������������
        else if(action.equals("chttbz")){
          	 url = path + "/web/sdttWeb/baozhang/TietaBaozhang.jsp";
            String id = req.getParameter("id");
            msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            SiteManage bean = new SiteManage();
            int retsign = bean.chttba(id);
            if (retsign == 1) {
                msg = "���ر�����Ϣ�ɹ���";
            } else if (retsign == 2) {
                msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "������������");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
       }//���ذ칫Ӫҵ���
        else if(action.equals("chbgyy")){
         	 url = path + "/web/sdttWeb/jichuInfoManager/stationManager.jsp";
           String id = req.getParameter("id");
           msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
           SiteManage bean = new SiteManage();
           int retsign = bean.chbg(id);
           if (retsign == 1) {
               msg = "����������Ϣ�ɹ���";
           } else if (retsign == 2) {
               msg = "����ʧ�ܣ������Ի������Ա��ϵ��";
           }
           log.write(msg, accountid, req.getRemoteAddr(), "���ذ칫Ӫҵ��������Ϣ");
           session.setAttribute("url", url);
           session.setAttribute("msg", msg);
           resp.sendRedirect(path + "/web/msg.jsp");
      }else if(action.equals("addXSManager")){
        	   url = path + "/web/sdttWeb/jichuInfoManager/XSManager.jsp";
               SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                msg = "��ӱ���ʧ�ܣ������Ի������Ա��ϵ��";
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
        	   msg = "��ӷ���ϵ���ɹ���";
           }
      		
      		log.write(msg, accountid, req.getRemoteAddr(), "��ӷ���ϵ��");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");
        }
        else if(action.equals("getDbxxById2")){
        	System.out.println(">>>>>>>>>>>getDbxxById2");
            DecimalFormat df1 = new DecimalFormat("0.00");
            @SuppressWarnings("unused")
			String BEILV = req.getParameter("BEILV");//���ʻ�ȡ
            String dianbiaoId = req.getParameter("dianbiaoId");//���id
            String SQRJYDL_str = req.getParameter("SQRJYDL_str");//�����վ��õ���
            String stime = req.getParameter("stime");//��ʼʱ��
            String etime = req.getParameter("etime");//����ʱ��
            String ts = req.getParameter("ts");//����
            String bgdl ="";// ��˵���
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            String addtime =sdf2.format(new Date());//��ǰ����1
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
            String addtime_YYYY =sdf3.format(new Date());//��ǰ����1
            CommonBean commonBean = new CommonBean();
            SiteManage bean = new SiteManage();
            
            if(addtime_YYYY.equals("2018")){
            	//�����2018�꣬�����˵���
            	System.out.println("//�����2018�꣬�����˵���");
            	  List<String> strList = commonBean.getstationByDbId2(dianbiaoId);//��ȡ�����Ϣ
                  String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="",housetype="",housetype2="";
                  if(strList!=null && strList.size()==5){
                  	st_id_str = strList.get(0);//վ���station_no
                  	jiaoliu_str = strList.get(1);//���ý��������
                  	zhiliu_str = strList.get(2);//����ֱ�������
                  	zdid_str = strList.get(3);//վ��id
                  	housetype = strList.get(4);//վ��ķ�������
                  }
                  if(jiaoliu_str==""||jiaoliu_str.equals("")){
                	  jiaoliu_str="0";
                  }
                  if(zhiliu_str==""||zhiliu_str.equals("")){
                	  zhiliu_str="0";
                  }
                  List<String> strList2 = commonBean.getSumByDbId(st_id_str,stime, etime);//��ȡpue��Ϣ
                  String sum_str = "",value_str="",sumDay_str="";//��ֵ,���ĵ���ƽ��ֵ��ƽ������
                  if(strList2!=null && strList2.size()==3){
                  	sum_str = strList2.get(0);//�������������ã�= ����*53.5/0.9/1000*24
                  	value_str = strList2.get(1);//�ź�ֵ��������
                  	sumDay_str = strList2.get(2);//����ƽ��ֵ����
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
                String addtime_old = ""+oldyear+"-"+month_s;//��ȡȥ��ͬ��
                
                String shi = bean.getshiById(dianbiaoId);//���ݵ��id���Ӧ�ġ��С�
                String strs3 = bean.getAnalysis_2017_ByShi(shi,month+"");//ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE
                String oldyearYdl = bean.getRjdl_2017_ByZdid(st_id_str, addtime_old);//��վȥ��ͬ���վ�����
                
                
                if(housetype.equals("20")){
             	   housetype2="20";
                }else{
             	   housetype2="30";
                }
                List<String> strList3 = commonBean.getXsByDbId(housetype2, addtime);//��ȡ����ϵ�������ֵ����Сֵ
                
                String housexsid="",MAXZHI="",MINZHI="";
                if(strList3!=null && strList3.size()==3){
             	   housexsid = strList3.get(0);
             	   MAXZHI = strList3.get(1);
             	   MINZHI = strList3.get(2);
             	   if(MINZHI==null || MINZHI==""){
             		  MINZHI="1";
             	   }
                   }
                
                List<String> strList4 = commonBean.getXs_T_By(housexsid, value_str.replaceAll(" ", ""));//��ȡ����ϵ�����ڵ�����
                String housexsid_t="",MIN_T="",MAX_T="";
                if(strList4!=null && strList4.size()==3){
             	   housexsid_t = strList4.get(0);
             	   MIN_T = strList4.get(1);
             	   MAX_T = strList4.get(2);
                 }
           		String fwxs="";//����ϵ��  ��������Ϊ���㷿��ϵ��
           		if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("0")){
           			fwxs=MINZHI;
           		}else if(MIN_T==null ||MIN_T.equals("") || MIN_T.equals("9999999999")){
           			fwxs=MAXZHI;
           		}else {
           		
           				//����ϵ�����㱣����λС��
           				fwxs=""+df1.format(Double.valueOf(MINZHI).doubleValue()+
           						(Double.valueOf(MAXZHI).doubleValue()-
           								Double.valueOf(MINZHI).doubleValue())
           								*(Double.valueOf(value_str).doubleValue()
           										-Double.valueOf(MIN_T).doubleValue())
           										/(Double.valueOf(MAX_T).doubleValue()
           												-Double.valueOf(MIN_T).doubleValue()));
           			
           			
           		}

                  //�����˵���Double.valueOf(jiaoliu_str).doubleValue() +Double.valueOf(value_str).doubleValue()*54 +Double.valueOf(zhiliu_str).doubleValue()
                  if(strs3!=null && !strs3.equals("") && jiaoliu_str!=null && !jiaoliu_str.equals("")
               		   && zhiliu_str!=null && !zhiliu_str.equals("") && value_str!=null && !value_str.equals("")
               		   && oldyearYdl!=null && !oldyearYdl.equals("") && SQRJYDL_str!=null && !SQRJYDL_str.equals("")
               		   && ts!=null && !ts.equals("") && fwxs!=null && !fwxs.equals("") ){
                	  // ���豸���� Double.valueOf(jiaoliu_str).doubleValue() +Double.valueOf(value_str).doubleValue()*54 +Double.valueOf(zhiliu_str).doubleValue()
               	   /**
               	    * st_id_str//վ���station_no
                  	jiaoliu_str //���ý��������
                  	zhiliu_str //����ֱ�������
                  	zdid_str //վ��id
                  	housetype //վ��ķ�������
                  	sum_str //�������������ã�= ����*53.5/0.9/1000*24
                  	value_str //�ź�ֵ��������
                  	sumDay_str //����ƽ��ֵ����
                  	MAXZHI ����ϵ�����ֵ
             	    MINZHI ����ϵ����Сֵ
               	    */
                	  String zsbgl = ""+add(add(mul(updouble(value_str),54),updouble(jiaoliu_str)),updouble(zhiliu_str));
                	  bgdl = ""+df1.format(mul(div(mul(mul(0.5,updouble(strs3)),updouble(zsbgl)),add(add(mul(mul(0.9,24),updouble(fwxs)),mul(0.2,updouble(oldyearYdl))),mul(0.3,updouble(SQRJYDL_str))),2),updouble(ts)))   ;                
//                			  bgdl =""+ df1.format((0.5*Double.valueOf(strs3).doubleValue()*(Double.valueOf(jiaoliu_str).doubleValue() +
//                  				Double.valueOf(value_str).doubleValue()*54 +
//                  				Double.valueOf(zhiliu_str).doubleValue())/0.9*24*Double.valueOf(fwxs).doubleValue()+0.2*Double.valueOf(oldyearYdl).doubleValue()+0.3*Double.valueOf(SQRJYDL_str).doubleValue())*Double.valueOf(ts).doubleValue());
                  System.out.println("2018���˵������㣺"+bgdl);
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
            	//�������2018�꣬�����˵���
            	System.out.println("//�������2018�꣬�����˵���");
            	 List<String> strList = commonBean.getstationByDbId2(dianbiaoId);//��ȡ�����Ϣ
                 
                 String st_id_str = "",jiaoliu_str="",zhiliu_str="",zdid_str="",housetype="",housetype2="";
                 if(strList!=null && strList.size()==5){
                 	st_id_str = strList.get(0);//վ���station_no
                 	jiaoliu_str = strList.get(1);//���ý��������
                 	zhiliu_str = strList.get(2);//����ֱ�������
                 	zdid_str = strList.get(3);//վ��id
                 	housetype = strList.get(4);//վ��ķ�������
                 }
                 List<String> strList2 = commonBean.getSumByDbId(st_id_str,stime, etime);//��ȡpue��Ϣ
                 String sum_str = "",value_str="",sumDay_str="";//��ֵ,���ĵ���ƽ��ֵ��ƽ������
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
               String addtime_old = ""+oldyear+"-"+month_s;//��ȡȥ��ͬ��
               
               String shi = bean.getshiById(dianbiaoId);
               String strs3 = bean.getAnalysisByShi(shi,addtime_old);//ȫ�е�ȥ��ͬ���·ݵ�ƽ��PUE
               String oldyearYdl = commonBean.getRjydlByDbId(dianbiaoId, addtime_old);//��վȥ��ͬ���վ�����
               
               
               if(housetype.equals("20")){
            	   housetype2="20";
               }else{
            	   housetype2="30";
               }
               List<String> strList3 = commonBean.getXsByDbId(housetype2, addtime);//��ȡ����ϵ�������ֵ����Сֵ
               
               String housexsid="",MAXZHI="",MINZHI="";
               if(strList3!=null && strList3.size()==3){
            	   housexsid = strList3.get(0);
            	   MAXZHI = strList3.get(1);
            	   MINZHI = strList3.get(2);
                  }
               
               List<String> strList4 = commonBean.getXs_T_By(housexsid, value_str);//��ȡ����ϵ�����ڵ�����
               String housexsid_t="",MIN_T="",MAX_T="";
               if(strList4!=null && strList4.size()==3){
            	   housexsid_t = strList4.get(0);
            	   MIN_T = strList4.get(1);
            	   MAX_T = strList4.get(2);
                }
          		String fwxs="";//����ϵ��  ��������Ϊ���㷿��ϵ��
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

                 //�����˵���
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
             msg = "�޸ķ���ϵ��ʧ�ܣ������Ի������Ա��ϵ��";
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
     	   msg = "�޸ķ���ϵ���ɹ���";
        }
   		
   		log.write(msg, accountid, req.getRemoteAddr(), "�޸ķ���ϵ��");
         session.setAttribute("url", url);
         session.setAttribute("msg", msg);
         resp.sendRedirect(path + "/web/msg.jsp");
     }
        else if (action.equals("modifydianbiao")) {
        	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            msg = "�޸ĵ����Ϣʧ�ܣ������Ի������Ա��ϵ��";
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
            if("�½�".equals(dfzflx)){
            	dfzflx = "dfzflx01";
            }else if("��ͬ".equals(dfzflx)){
            	dfzflx = "dfzflx02";
            }else if("Ԥ֧".equals(dfzflx)){
            	dfzflx = "dfzflx03";
            }else if("�忨".equals(dfzflx)){
            	dfzflx = "dfzflx04";
            }
            String dbname=req.getParameter("dbname");
            String linelosstype=req.getParameter("linelosstype");
            String linelossvalue=req.getParameter("linelossvalue")==null?"":req.getParameter("linelossvalue");
            String entrypensonnel=req.getParameter("accountname");
            String dbqyzt=req.getParameter("dbqyzt");//�������״̬
            String zbdyhh=req.getParameter("zbdyhh");//�Ա����û���
            String ydbid=req.getParameter("ydbid");//ԭ���id
            String danjia = req.getParameter("danjia");//�����
            String bsdl = req.getParameter("bsdl")==null?"":req.getParameter("bsdl");//�������
            String zgdj = req.getParameter("zgdj");//�ݹ�����
            if(zgdj == null || "".equals(zgdj)){
            	zgdj = "0";
            }
            String zzsl1 = req.getParameter("zzsl");//��ֵ˰��
            String zzsl = zzsl1.trim();
            String entrytime=mat.format(new Date());
            
            String zdid = req.getParameter("zdid"); 
            String countbzw = req.getParameter("countbzw"); //վ������Ƿ�����־����¼��վ�����ͬʱ���ж�����õĽ�����0---1���1---����
            String qydb = req.getParameter("qydb");//���õ������  
            String gbdb= req.getParameter("gbdb");//�رյ������
            String count = req.getParameter("count");//���ú͹رյ��������
            
            DianBiaoBean bean = new DianBiaoBean();
//            String is_cont,cont_type,zndb,isdblx,cbzx,cbzxbm,production_prop,bur_stand_propertion,cont_id,electric_supply_way,total_electricity,zhangqi,zhz;
        	
            int retsign = bean.modifyData(dbid,id, dllx, ydsb, sszy, dbyt, csds,
                                       cssytime, beilv, dbxh, memo,netpoint_name,netpoint_id,dfzflx,dbname,linelosstype,
                                       linelossvalue,entrypensonnel,entrytime,dbqyzt,zbdyhh,ydbid,danjia,bsdl,zgdj,zzsl,
                                       zdid,countbzw,count,qydb,gbdb);
            System.out.println(retsign);
            if (retsign == 1) {
                msg = "�޸ĵ����Ϣ�ɹ���";
            }
            String url1 = path + "/web/jizhan/dianbiaolist.jsp";
            log.write(msg, accountid, req.getRemoteAddr(), "�޸ĵ����Ϣ");
            session.setAttribute("url", url1);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
 else if (action.equals("del")) {
            url = path + "/web/sdttWeb/jichuInfoManager/dianbiaolist.jsp";
            String id = req.getParameter("id");
            msg = "ɾ�����ʧ�ܣ������Ի������Ա��ϵ��";
            DianBiaoBean bean = new DianBiaoBean();
            int retsign = bean.delData(id);
            if (retsign == 1) {
                msg = "ɾ�����ɹ���";
            }else if(retsign==2){
                msg = "�й����ĵ�����Ϣ��������ɾ����";
            }else if(retsign==3){
            	msg="�й�����Ԥ������Ϣ��������ɾ����";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "ɾ�����");
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
            resp.sendRedirect(path + "/web/msg.jsp");

        }
 else if (action.equals("del2")) {
     url = path + "/web/electricfees/dianfeidan_search.jsp";
     String id = req.getParameter("id");
     msg = "ɾ�����ʧ�ܣ������Ի������Ա��ϵ��";
     DianBiaoBean bean = new DianBiaoBean();
     int retsign = bean.delData1(id);
     if (retsign == 1) {
         msg = "ɾ�����ɹ���";
     }else if(retsign==2){
         msg = "�й����ĵ�����Ϣ��������ɾ����";
     }else if(retsign==3){
     	msg="�й�����Ԥ������Ϣ��������ɾ����";
     }
     log.write(msg, accountid, req.getRemoteAddr(), "ɾ�����");
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
    	SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//¼��ʱ��
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�޸�ʱ��
    	String ENTRYTIMEOLD =mat.format(new Date());//¼��ʱ��
    	String ENTRYTIME =sdf.format(new Date());//�޸�ʱ��
     	String LJID="0"; //����ID
     	String dbtype="1"; //¼��
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
    	//���������ɹ���
    	String dbbm="";
    	String thid=id; // udpate by sgn 2018-4-18 

    	dbbm = bean.getCustomDbbm(zdid);
    	
    	LJID = bean.getZDStationNo(zdid);//��ȡ������վID
    	newdbId = bean.addDB(zdid, dbbm, dbname, cssysj, beilv, csds, dfzflx, wlbm, maxds, dwjslx, isglf, glbm,
    			zrr, bzr, ydsx, ydlx, jffs, jfzq, jldw, yhbh, dbqyzt, bgje, mtllhd, danjia, gysmc, gysbm,
    			skfmc, yhzh, ssyh, khyh, zhsss, zhssshi, memo, fplx, zzsl, dbyt, dbch, dbscbs, ENTRYTIMEOLD,
    			is_cont, cont_type, zndb, isdblx, cbzx, cbzxbm, production_prop, bur_stand_propertion, cont_id, electric_supply_way, total_electricity, zhangqi, zhz, 
    			gszt, LJID, dbtype, thid, jiaoliu, zhiliu, ssf, ftbl, sfyz);
    	
    	
    	
    	//��Ӻ��㵥Ԫ
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
	//�������
 	
 	WorkFlowBean workFlowBean= new WorkFlowBean();
 	String flowId = req.getParameter("flowId"); 
	String auditorid = req.getParameter("auditorid");
 	String taskType="DIANBIAO,"+n+"";//���ֵ������
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
			msg="�ύ�ɹ�";
		}else{
			msg="�ύʧ�ܣ�����ϵ����Ա��";
		}
	 log.write(msg, accountid, req.getRemoteAddr(), "�޸ĵ��");
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
				msg="��˳ɹ�";
			}else{
				msg="���ʧ�ܣ�����ϵ����Ա��";
			}
			log.write(msg, accountid, req.getRemoteAddr(), "������");
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
				msg="��˳ɹ�";
			}else{
				msg="���ʧ�ܣ�����ϵ����Ա��";
			}
		 log.write(msg, accountid, req.getRemoteAddr(), "������");
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
	//�������
	WorkFlowBean workFlowBean= new WorkFlowBean();
	String flowId=req.getParameter("flowId");
	String auditorid=req.getParameter("auditorid");
	String taskType="DIANBIAO,0";//���ֵ������
	String taskId=req.getParameter("taskId");;
	String currentNo =workFlowBean.getNextStep(flowId, "1");
	String nextNo =workFlowBean.getNextStep(flowId, "2");
	int workId=workFlowBean.addWorkFlow(flowId, taskId, currentNo, nextNo, account.getAccountId(), taskType, auditorid);
 	if(workId>0){
 		msg="�ύ�ɹ���";
 	}else{
 		msg="�ύʧ�ܣ������Ի������Ա��ϵ";
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
 	
 	 log.write(msg, accountid, req.getRemoteAddr(), "�ύ���");
	 session.setAttribute("url", url);
	 session.setAttribute("msg", msg);
	 resp.sendRedirect(path + "/web/msg.jsp");
}else if("savePue".equals(action)){
	//��ӵ���
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
            msg = "��֤���ID�ظ�ʧ�ܣ������Ի������Ա��ϵ��";
            DianBiaoBean bean = new DianBiaoBean();
            String retsign = bean.vdbid(dbid);
            if (retsign.equals("ok")) {
                msg = "ok";
            }else{
                msg=retsign;
            }

            log.write(msg, accountid, req.getRemoteAddr(), "���ID�ظ�");
            out.println(msg);
            //out.println("</response>");
            out.close();

        }else if (action.equals("vmdbid")) {

            String dbid = req.getParameter("dbid");
            String id = req.getParameter("id");
            msg = "��֤���ID�ظ�ʧ�ܣ������Ի������Ա��ϵ��";
            DianBiaoBean bean = new DianBiaoBean();
            String retsign = bean.vdbid(dbid,id);
            if (retsign.equals("ok")) {
                msg = "ok";
            }else{
                msg=retsign;
            }

            log.write(msg, accountid, req.getRemoteAddr(), "���ID�ظ�");
            out.println(msg);
            //out.println("</response>");
            out.close();

        }



    }
  //double����-�Ӽ��˳��߾�������
    public static  double updouble(String v1){  
    return Double.valueOf(v1).doubleValue();
    }
    public static  double add(double v1,double v2){  

  	        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
  	        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
  	        return b1.add(b2).doubleValue();  
  	    }  

  	// ���м�������
	
		public static double subtract(double v1,double v2){
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
