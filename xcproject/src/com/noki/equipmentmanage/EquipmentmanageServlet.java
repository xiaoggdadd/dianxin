package com.noki.equipmentmanage;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.log.DbLog;
import com.noki.mobi.common.CommonBean;

public class EquipmentmanageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Content_type = "text/html;charset=UTF-8";
	public EquipmentmanageServlet() {
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    	HttpSession session2 = req.getSession();
    	DbLog log = new DbLog();
    	String accountid = (String) session2.getAttribute("loginName");
        String action = req.getParameter("action");
        resp.setContentType(Content_type);
        EquipmentmanageBean bean = new EquipmentmanageBean();
        EquipmentmanageViewBean beanView = new EquipmentmanageViewBean();
        if ("getSelData".equals(action)){
        	
        }else if ("detail".equals(action)){
	    	 String sheiebanid = req.getParameter("sheiebanid");
	      
        }else if ("selDianBiaoId".equals(action)){
        	 String ret = "";
        	try {
	    		ret = bean.SelDianBiaoId();         	
	        	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
	        		ret=ret.substring(0,ret.length()-1);
	        	
	        	}
	        	PrintWriter out =  resp.getWriter();
	        	out.write(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if ("SelPermissionCconfiguration".equals(action)){
       	 String ret = "";
     	try {
	    		ret = bean.SelPermissionCconfiguration1("SSZY","");  
	    		//System.out.println(ret);
	        	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
	        		ret=ret.substring(0,ret.length()-1);
	        	
	        	}
	        	
	        	PrintWriter out =  resp.getWriter();
	        	out.write(ret);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else if ("SelPermissionCconfiguration1".equals(action)){
         	 String ret = "";
           	try {
      	    		ret = bean.SelPermissionCconfiguration1("ZYMX","");     	
      	        	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
      	        		ret=ret.substring(0,ret.length()-1);
      	        	
      	        	}
      	        	
      	        	PrintWriter out =  resp.getWriter();
      	        	out.write(ret);
      			} catch (Exception e) {
      				e.printStackTrace();
      			}
              }
           else if ("updataSession".equals(action)){
        	  EquipmentmanageViewBean viewbean=new EquipmentmanageViewBean();
	    	 String dianbiaoid = req.getParameter("dianbiaoid");
	    	 System.out.println(dianbiaoid+"ss");//////////////////////////
	    	 String ret = "";
	    	 ArrayList<EquipmentmanageViewBean> list=new ArrayList<EquipmentmanageViewBean>();
				if(null!=req.getSession().getAttribute(dianbiaoid)){
				
				  list=(ArrayList<EquipmentmanageViewBean>)req.getSession().getAttribute(dianbiaoid);
			 }
				String sheiebanid = req.getParameter("sheiebanid");
	  	    	 String mingcheng = req.getParameter("mingcheng");
	  	    	 String guige = req.getParameter("guige");
	  	    	 String shuoshuzhuanye = req.getParameter("shuoshuzhuanye");
	  	    	 String shuoshuwangyuan = req.getParameter("shuoshuwangyuan");
	  	    	 String kjkmcode = req.getParameter("kjkmcode");
	  	    	 String qcbcode = req.getParameter("qcbcode");
	  	    	
	  	    	 String bili = req.getParameter("bili");
	  	    	 String sccj = req.getParameter("sccj");
	  	    	 String zcbh = req.getParameter("zcbh");
	  	    	 String bccd = req.getParameter("bccd");
	  	    	 String beizhu = req.getParameter("beizhu");
	  	    	 String zymxcode = req.getParameter("zymxcode");
			    viewbean.setDianbiaoid(dianbiaoid);
			    viewbean.setSheiebanid(sheiebanid);
			    viewbean.setMingcheng(mingcheng);
				viewbean.setGuige(guige);
//				viewbean.setShuoshuzhuanye(shuoshuzhuanye);
//				String sszy=bean.SelPermissionCconfiguration("SSZY",shuoshuzhuanye);
				viewbean.setShuoshuzhuanye(shuoshuzhuanye);
				String sszy=bean.SelPermissionCconfiguration12(shuoshuzhuanye);
				viewbean.setSszy(sszy);
				viewbean.setShuoshuwangyuan(shuoshuwangyuan);
				viewbean.setKjkmcode(kjkmcode);
				String kjkm=bean.getKJKM12(kjkmcode);
				viewbean.setKjkm(kjkm);
				viewbean.setQcbcode(qcbcode);
				String qcb=bean.getQCB12(qcbcode);
				viewbean.setQcb(qcb);
				viewbean.setZymxcode(zymxcode);
				String zymx=bean.SelPermissionCconfiguration("ZYMX",zymxcode);
				viewbean.setZymx(zymx);
				viewbean.setBili(bili);
				viewbean.setSccj(sccj);
				viewbean.setZcbh(zcbh);
				viewbean.setBccd(bccd);
				viewbean.setBeizhu(beizhu);
				EquipmentmanageViewBean ebean=new EquipmentmanageViewBean();
				ArrayList newlist=new ArrayList();
				if(list.size()>0){
					for(EquipmentmanageViewBean viewBean:list){
						System.out.println("daolelll---"+viewBean.sheiebanid);
						if(viewbean.sheiebanid.equals(viewBean.sheiebanid)){
						    continue;	
						}
						else{
							newlist.add(viewBean);
						}
						
						
					}
					newlist.add(viewbean);
					req.getSession().removeAttribute(dianbiaoid);
					req.getSession().setAttribute(dianbiaoid, newlist);
				}
				else{
					list.add(viewbean);
					req.getSession().setAttribute(dianbiaoid, list);
					System.out.println("daolelll4444---"+viewbean.sheiebanid);
				}
				try {
		    		        	
		        	PrintWriter out =  resp.getWriter();
		        	out.write(ret);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				 String msg="�����޸ļ��㣬���idΪ"+dianbiaoid+"  �¼�����idΪ��"+sheiebanid;
	  			 log.write(msg, accountid, req.getRemoteAddr(), "�����޸ļ���");
				
        }
           else if ("save".equals(action)){//���㱣��
  	    	 String dianbiaoid = req.getParameter("dianBiaoId");
  	    	 String sc= req.getParameter("sc");//����
  	    	 String yy = req.getParameter("yy");//Ӫҵ
  	    	 String bg = req.getParameter("bg");//�칫
  	    	 String xxhzc = req.getParameter("xxhzc");//��Ϣ��֧��
  	    	 String jstz = req.getParameter("jstz");//����Ͷ��
  	    	String dddf = req.getParameter("dddf");//������
  	    	String accountname = req.getParameter("accountname");//��½�û�
  	    	 System.out.println("�û���"+accountname);
  	    	String msg="�����������ݵļ��㣬���idΪ��"+dianbiaoid;
			log.write(msg, accountid, req.getRemoteAddr(), "�����������ݵļ���");
  	    	 bean.delData(dianbiaoid);
  	    	 ArrayList<EquipmentmanageViewBean> list=new ArrayList<EquipmentmanageViewBean>();
				if(null!=req.getSession().getAttribute(dianbiaoid)){
				
				  list=(ArrayList<EquipmentmanageViewBean>)req.getSession().getAttribute(dianbiaoid);
			 }
				String ret = "";
				 //bean.delData(dianbiaoid);�Ժ�����   �ֲ�ˢ�£�������
				if(list.size()>0){
					for(EquipmentmanageViewBean viewbean:list){
						ret = bean.insertData(viewbean,sc,yy,bg,xxhzc,jstz,accountname,dddf);
					}
				}
  		       
  		        try {
  		        	PrintWriter out =  resp.getWriter();
  		        	out.write(ret);
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
  				
           }else if ("del".equals(action)){
	    	 String sheiebanid = req.getParameter("sheiebanid");
	            String ret = "";
	            try {
	        		ret = bean.delData(sheiebanid); 
	        		
	            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
	            		ret=ret.substring(0,ret.length()-1);
	            	}
	            	PrintWriter out =  resp.getWriter();
	            	out.write(ret);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    	
	    }else if ("delSession".equals(action)){
	    	 String sheiebanid = req.getParameter("sheiebanid");
	    	 String dianbiaoid = req.getParameter("dianbiaoid");
	    	 System.out.println("@888"+sheiebanid);
	    	ArrayList<EquipmentmanageViewBean> dellist=new ArrayList<EquipmentmanageViewBean>();//����װ��Ҫɾ����Ԫ��
	    	EquipmentmanageViewBean ben=new EquipmentmanageViewBean();
	    	    //bean.delData(sheiebanid);
	    	    String msg="ɾ�����㣬���idΪ��"+dianbiaoid+"�¼�����idΪ"+sheiebanid;
	    		System.out.println("1122222222222222222221");
  				log.write(msg, accountid, req.getRemoteAddr(), "ɾ������");
	    	 ArrayList<EquipmentmanageViewBean> list=new ArrayList<EquipmentmanageViewBean>();
				if(null!=req.getSession().getAttribute(dianbiaoid)){
				
				  list=(ArrayList<EquipmentmanageViewBean>)req.getSession().getAttribute(dianbiaoid);
			 }
				String ret = "";
				if(list.size()>0){
					for(EquipmentmanageViewBean viewbean:list){
						if(sheiebanid.equals(viewbean.sheiebanid)){
							
							//list.remove(viewbean);//�ڶ�һ��List���б�����ʱ�����е�Ԫ��ɾ����
							 //dellist.add(viewbean);
							ben=viewbean;
							System.out.println("yichu"+sheiebanid);
						}
						 
					}
					 list.remove(ben);//����������remove��
				}
			
				req.getSession().removeAttribute(dianbiaoid);
				req.getSession().setAttribute(dianbiaoid, list);
				
			try {
	            	PrintWriter out =  resp.getWriter();
	            	out.write(ret);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    }
           else if ("getRole".equals(action)){
	    	CommonBean roleBean = new CommonBean();
	    	HttpSession session = req.getSession();
	    	String roleId = (String)session.getAttribute("accountRole");
	    	String rightid = req.getParameter("rightid");
	    	String ret = "";
            try {
        		ret = roleBean.getPermissionRight(roleId, rightid) ;      	
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }else if ("selzhan".equals(action)){
	    	 
	            String ret = "";
	            try {
	            	String dianBiaoId = req.getParameter("dianBiaoId");
	        		ret = bean.getzhandianshuxing(dianBiaoId);     	
	            	if (ret!=null &&!"".equals(ret) &&",".equals(ret.substring(ret.length()-1)) ){
	            		ret=ret.substring(0,ret.length()-1);
	            	}
	            	PrintWriter out =  resp.getWriter();
	            	out.write(ret);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
	    }else if ("chekId".equals(action)){
            String ret = "";
            try {
            	String sheiebanid = req.getParameter("sheiebanid");
        		ret = bean.chekId(sheiebanid);     	
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }else if ("chekBili".equals(action)){
            String ret = "";
            try {//
            	String sheiebanid = req.getParameter("dianbiaoid");
            	String inbili = req.getParameter("inbili");
            	String shebeiId = req.getParameter("shebeiId");
            	String flg = req.getParameter("flg");
        		ret = bean.chekBili(sheiebanid,flg,inbili,shebeiId);  
        		System.out.println("�ύ��ѯ"+ret);
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }else if ("chekname".equals(action)){
            String ret = "";
            try {
            	String sheiebanid = req.getParameter("mingcheng");
        		ret = bean.chekname(sheiebanid);     	
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }else if ("yulan".equals(action)){
	    	 String ret = "";
	            String dianBiaoId = req.getParameter("dianBiaoId");
	            ArrayList<EquipmentmanageViewBean> list=new ArrayList<EquipmentmanageViewBean>();
	            Float countBili=new Float(0);
				 if(null!=req.getSession().getAttribute(dianBiaoId)){
				
				  list=(ArrayList<EquipmentmanageViewBean>)req.getSession().getAttribute(dianBiaoId);
				  
				  
				  for(EquipmentmanageViewBean viewbean:list ){
					  countBili +=Float.parseFloat(viewbean.bili);
				  }
			    }
	            try {
	            	ret=countBili.toString();
	            	PrintWriter out =  resp.getWriter();
	            	out.write(ret);
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
		    }
	    else if ("yulanSession".equals(action)){
            String ret = "";
            String dianBiaoId = req.getParameter("dianBiaoId");
            ArrayList<EquipmentmanageViewBean> list=new ArrayList<EquipmentmanageViewBean>();
            Float countBili=new Float(0);
			 if(null!=req.getSession().getAttribute(dianBiaoId)){
			
			  list=(ArrayList<EquipmentmanageViewBean>)req.getSession().getAttribute(dianBiaoId);
			  
			  
			  for(EquipmentmanageViewBean viewbean:list ){
				  countBili +=Float.parseFloat(viewbean.bili);
			  }
		    }
            try {
            	System.out.println(countBili+"countBili");
            	if(countBili==100){
            		ret = "100";  
            	}
            	PrintWriter out =  resp.getWriter();
            	out.write(ret);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    }
    }
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
    	this.doPost(req, resp);
    }
}
