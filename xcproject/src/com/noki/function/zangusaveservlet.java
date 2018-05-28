package com.noki.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.equipmentmanage.EquipmentmanageViewBean;
import com.noki.log.DbLog;

public class zangusaveservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public zangusaveservlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    	HttpSession session = req.getSession();
    	DbLog log = new DbLog();
    	String accountid = (String) session.getAttribute("loginName");
        String action = req.getParameter("action");
        String path = req.getContextPath();
        resp.setContentType(Content_type);
        zanguBean bb=new zanguBean();
        //System.out.println("妖怪你出来！"+action);
        if(action.equals("save")){
        	String lrren=req.getParameter("lrren");
        	String shi = req.getParameter("shi1");//市编码
        	String zgmonth = req.getParameter("zgmonth");//暂估月份
        	String bzw = req.getParameter("ff");//本月已经暂估过的标志位  如果为you说明这个月已经有暂估静态数据
        	System.out.println("shi:"+shi+"  zgmonth:"+zgmonth+" bzw:"+bzw);
        	String url = path + "/web/newgn/zangu.jsp"; 
 	    	 ArrayList<CityQueryBean> list=new ArrayList<CityQueryBean>();

        	if(null!=req.getSession().getAttribute("jzname")){
				
				  list=(ArrayList<CityQueryBean>)req.getSession().getAttribute("jzname");
				 // System.out.println("wwwwww"+list.size());
			 }
        	String msg = "";
        	String ret="";
        	 long uuid1=System.currentTimeMillis();
        	 String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
		     String uuid = uuid2+Long.toString(uuid1).substring(3);
        	
			if(list.size()>0){
//				for(CityQueryBean bean:list){
//					ret = bb.insertDate(bean,uuid,lrren);
//					if("1".equals(ret)){
//						msg="暂估静态数据保存失败";
//					}else if("2".equals(ret)){
//						msg="暂估静态数据保存成功";
//						
//					}
//				}
				//更改-----------------------------------------------------------
			   ret=bb.insertDate(uuid, lrren, list,shi,zgmonth,bzw);
				if("1".equals(ret)){
				msg="暂估静态数据保存失败";
				}else if("2".equals(ret)){
				msg="暂估静态数据保存成功";
				}
				
			}
			 log.write(msg, accountid, req.getRemoteAddr(), msg);
			 session.setAttribute("url", url);
		     session.setAttribute("msg", msg);
		     try {
				resp.sendRedirect(path + "/web/msg.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
        }else if(action.equals("savetx")){
        	
        	String lrren=req.getParameter("lrren");
        	String qsyf=req.getParameter("qsyf");
        	//String jsyf=req.getParameter("jsyf");
        	
        	String url = path + "/web/newgn/tanxiao.jsp"; 
	    	 ArrayList<CityQueryBean> list=new ArrayList<CityQueryBean>();

       	if(null!=req.getSession().getAttribute("tanxiao")){
				
				  list=(ArrayList<CityQueryBean>)req.getSession().getAttribute("tanxiao");
				  //System.out.println("wwwwww"+list.size());
			 }
       	String msg = "";
       	String ret="";
       	 long uuid1=System.currentTimeMillis();
       	 String uuid2 = Integer.toString((int)(100+Math.random()*(999-100+1)));
       
		     String uuid = uuid2+Long.toString(uuid1).substring(3);
			// System.out.println("yi--"+uuid1+"--er---"+uuid2+"---san---"+uuid);
			if(list.size()>0){
				//System.out.println("1111");
				for(CityQueryBean bean:list){
					//System.out.println("2");
					ret = bb.InsertTX(bean,uuid,lrren,qsyf);
					if("1".equals(ret)){
						msg="摊销静态数据保存失败";
					}else if("2".equals(ret)){
						msg="摊销静态数据保存成功";
						
					}
				}
			}
			 log.write(msg, accountid, req.getRemoteAddr(), msg);
			 session.setAttribute("url", url);
		     session.setAttribute("msg", msg);
		     try {
				resp.sendRedirect(path + "/web/msg.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        }
        
    }

}
