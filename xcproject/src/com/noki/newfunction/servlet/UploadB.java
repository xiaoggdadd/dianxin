package com.noki.newfunction.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.CbzdQuery;
import com.noki.newfunction.dao.FilesUpload;
import com.noki.newfunction.dao.ZGShenHeDao;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.tongjichaxu.javabean.cbJzBean;
@SuppressWarnings("serial")
public class UploadB extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getParameter("action");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Account account = (Account)(request.getSession().getAttribute("account"));
		String loginName = account.getAccountName();
		String sjyq=request.getParameter("sjyq");
		//String id = request.getParameter("id");
	//	String ida=(String)request.getSession().getAttribute("zdid");
		//String id = request.getParameter("id");
		//System.out.println("session���ȡ��id��"+id+"-"+ida);
		if("8".equals(action)||"9".equals(action)){
			String bzw=request.getParameter("bzw");
			String id="";
			if("8".equals(action)){
				id=request.getParameter("id");
			}else{
				id=(String)request.getSession().getAttribute("zdid");
			}
			//System.out.println("վ��id������"+id);
		String entrytimeQS=request.getParameter("entrytimeQS");
		//System.out.println("��־λL��"+bzw);
		String accountname=account.getName();
		String msg="",msg1="";
		String confirm = request.getParameter("confirm");
		
		FilesUpload dao = new FilesUpload(account);
		//System.out.println("confirm:"+confirm);
		if(null == confirm){//�ļ���ʼ�ϴ�
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);								
	        factory.setRepository(tempfile);
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//�ļ��ϴ���С����Ϊ10M
	        List<String> statusInfo = new ArrayList<String>();//������Ϣ
	        List<?> fileItems = null;
	        try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
				sendStatusInfo(request,response,statusInfo,bzw);
				return;
			}
			
			
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				if(!file.isFormField()){
					if(statusInfo.size()==0){
						try {
							
							 CbzdQuery bean=new CbzdQuery();
							 List<Zdinfo> ls=bean.seachData1(id);
							 String idb="",zdidb="";
							 if(ls!=null){
								 for(Zdinfo zd:ls){
                                    idb=zd.getId();
                                    zdidb=zd.getZdid();
								 }
								 
							 }
							 String path="";
							 String name="";
							 name = file.getName();
							//System.out.println("name====="+name);
							if(!name.equals("")){
								if(name.contains("\\")){
									name = name.substring(name.lastIndexOf("\\")+1);
									//System.out.println("11111:"+name);
									
								}
								path = getServletContext().getRealPath("/sjwj");
								name=idb+zdidb+"ID"+name;
								//System.out.println("name--"+name);
								file.write(new File(path+"/"+name));
							}	 
								
								 int i=bean.seachDataMax();
								 int j=i+1;
								// System.out.println("j:"+j);
				    	      msg=dao.insertToDB(path,name,ls,sjyq,accountname,j,entrytimeQS);
								 if("�ɵ��ɹ���".equals(msg)){
								 msg1 = bean.spd1(id,accountname);
								 if(msg1.equals("�ɵ��ɹ���")&&msg.equals("�ɵ��ɹ���")){
									 msg="�ɵ��ɹ���";
								 }
								 }    
									
								} catch (Exception e) {
									e.printStackTrace();
							}
						statusInfo.add(msg);
					}
				}
			}
			
			sendStatusInfo(request,response,statusInfo,bzw);
		}
		else{//�ж��ļ��Ƿ�����ϴ�
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";
			out.println(result);
			out.flush();
			out.close();
		}
		}else if("ejsh".equals(action)){
			//System.out.println("====�������");
			String bzw=request.getParameter("bzw");
			//System.out.println("��־λL��"+bzw);
			String accountname=account.getName();
			String wcsj=request.getParameter("wcsj");
			//System.out.println("wcsj:"+wcsj);
			String msg="",msg1="";
			String confirm = request.getParameter("confirm");
			FilesUpload dao = new FilesUpload(account);
			//System.out.println("confirm:"+confirm);
			String id1 = request.getParameter("id");
			if(null == confirm){//�ļ���ʼ�ϴ�
				File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼
				DiskFileItemFactory factory = new DiskFileItemFactory();
		        factory.setSizeThreshold(4096);								
		        factory.setRepository(tempfile);
		        ServletFileUpload upload = new ServletFileUpload(factory);
		        upload.setSizeMax(10485760);//�ļ��ϴ���С����Ϊ10M
		        List<String> statusInfo = new ArrayList<String>();//������Ϣ
		        List<?> fileItems = null;
		        try {
					fileItems = upload.parseRequest(request);
				} catch (FileUploadException e) {
					statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
					//System.out.println("��������:"+confirm);
					sendStatusInfo(request,response,statusInfo,bzw);
					return;
				}
				
				
				for(Object o:fileItems){
					FileItem file = (FileItem)o;
					if(!file.isFormField()){
						if(statusInfo.size()==0){
							try {
								//String path = getServletContext().getRealPath("/sjwj2");
								// path = "z://aaa";
								String name=""; 
								name = file.getName();
								 CbzdQuery bean=new CbzdQuery();
								 List<Zdinfo> ls=bean.seachData3(id1);
								 String iddda="";
								 if(ls!=null){
									 for(Zdinfo lstt:ls){
										 iddda=lstt.getId();
									 }
								 }
								 List<Zdinfo> lsT=bean.seachData8(id1);
								 String sdm="";
								 if(lsT!=null){
									 for(Zdinfo lstt:lsT){
										 sdm=lstt.getId();
									 }
								 }
								// name=sdm+iddda+"ID"+name;
								//int ii=name.indexOf(".");
								//long time=new Date().getTime();
								//name=name.substring(0,ii)+time+name.substring(ii);
								//System.out.println("name--"+name);
									//file.write(new File(path+"/"+name));
									String path="";
									if(!name.equals("")){
										if(name.contains("\\")){
											name = name.substring(name.lastIndexOf("\\")+1);
											//System.out.println("11111:"+name);
										}
										path = getServletContext().getRealPath("/sjwj2");
										name=sdm+iddda+"ID"+name;
									//	System.out.println("name----"+name);
										file.write(new File(path+"/"+name));
									}	
					    	       msg=dao.insertToDB3(path,name,ls,sjyq,accountname,wcsj,id1);
					    	       
									 if("�ɵ��ɹ���".equals(msg)){
									 msg1 = bean.spd3(id1,accountname);
									 if(msg1.equals("�ɵ��ɹ���")&&msg.equals("�ɵ��ɹ���")){
										 msg="�ɵ��ɹ���";
									 }
									 }    
										
									} catch (Exception e) {
										e.printStackTrace();
								}
							statusInfo.add(msg);
						}
					}
				}
				
				sendStatusInfo(request,response,statusInfo,bzw);
			}
			else{//�ж��ļ��Ƿ�����ϴ�
				PrintWriter out = response.getWriter();
				String result = "0";
				if(dao.fileIsUploaded())result = "1";
				out.println(result);
				out.flush();
				out.close();
		}
	}else if("qxzgwc".equals(action)){
		//System.out.println("====�������");
		String bzw="5";
		//System.out.println("��־λ2222L��"+bzw);
		String msg="";
		String confirm = request.getParameter("confirm");
		
		String wcsm = request.getParameter("wcsm");
		String beginTime1 = request.getParameter("beginTime1");
		String zhssdl= request.getParameter("zhssdl");
		String zgfzr = request.getParameter("zgfzr");
		String id1 = request.getParameter("cid");
		String zdid = request.getParameter("zdid");
		//System.out.println("------"+wcsm+"----"+beginTime1+"---"+zgfzr);
		FilesUpload dao = new FilesUpload(account);
		//System.out.println("confirm:"+confirm);
		if(null == confirm){//�ļ���ʼ�ϴ�
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);								
	        factory.setRepository(tempfile);
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//�ļ��ϴ���С����Ϊ10M
	        List<String> statusInfo = new ArrayList<String>();//������Ϣ
	        List<?> fileItems = null;
	        try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
				//System.out.println("��������:"+confirm);
				sendStatusInfo(request,response,statusInfo,bzw);
				return;
			}
			
			
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				if(!file.isFormField()){
					if(statusInfo.size()==0){
						try {
							
						//	System.out.println("name----------");
							//String path = getServletContext().getRealPath("/qxhsfj2");
							// path = "z://aaa";
							String path="";
							String name = file.getName();
							//int ii=name.indexOf(".");
							//long time=new Date().getTime();
							//name=name.substring(0,ii)+time+name.substring(ii);
						//	System.out.println("name----------"+name);
							if(!name.equals("")){
								path = getServletContext().getRealPath("/qxhsfj2");
								name=id1+zdid+"ID"+name;
							//	System.out.println("name--"+name);
								file.write(new File(path+"/"+name));
							}	 
								
							/*System.out.println("name--"+name);
                                  name=id1+zdid+"ID"+name;
								file.write(new File(path+"/"+name));
								
								System.out.println("name--"+name+"  path:"+path);
							/*	 CbzdQuery bean=new CbzdQuery();
								 List<Zdinfo> ls=bean.seachData3(id);
				    	       msg=dao.insertToDB3(path,name,ls,sjyq,accountname,wcsj);
								 if("�ɵ��ɹ���".equals(msg)){
								 msg1 = bean.spd3(id,accountname);
								 if(msg1.equals("�ɵ��ɹ���")&&msg.equals("�ɵ��ɹ���")){
									 msg="�ɵ��ɹ���";
								 }
								 }*/ 
								
								
								ZGShenHeDao dao1 = new ZGShenHeDao();
							//	System.out.println("-------------------------");
								int Rs = dao1.TJSHsave(loginName, wcsm,zhssdl, beginTime1, zgfzr,id1,path,name,zdid);
								//sSystem.out.println(id+"zzz");
								//int Rs=0;
								if(Rs==1){
									msg = "���ı���ɹ���"; 
								}else if(Rs==0){
									msg = "����ʧ��,����ϵ����Ա��";
								}
									
								} catch (Exception e) {
									e.printStackTrace();
							}
						statusInfo.add(msg);
					}
				}
			}
			
			sendStatusInfo(request,response,statusInfo,bzw);
		}
		else{//�ж��ļ��Ƿ�����ϴ�
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";
			out.println(result);
			out.flush();
			out.close();
		}
	}else if("chehui".equals(action)){
	String wjid = request.getParameter("chooseIdStr");
	CbzdQuery cx=new CbzdQuery();
	String msgg=cx.chehui(wjid);
	String path = request.getContextPath();
	String msg="";
	String url = path + "/web/jzcbnewfunction/cbjzcx.jsp";
	HttpSession session = request.getSession();
	if ("�����ɹ���".equals(msgg)) {
		msg = "�����ɹ���";
	} else {
		msg = "����ʧ�ܣ�����ϵ����Ա��";
	}
	session.setAttribute("url", url);
	session.setAttribute("msg", msg);
	response.sendRedirect(path + "/web/msg.jsp");
		
		
		
	}else if("chehui1".equals(action)){
		//System.out.println("�����˻�2");
		String wjid = request.getParameter("chooseIdStr");
		 CbzdQuery bean=new CbzdQuery();
		 List<Zdinfo> ls=bean.seachData3(wjid);
		 CbzdQuery cx=new CbzdQuery();
		 String msgg=cx.chehui2(ls);
		 String path = request.getContextPath();
			String msg="";
			String url = path + "/web/jzcbnewfunction/cbjzcx1.jsp";
			HttpSession session = request.getSession();
			if ("�����ɹ���".equals(msgg)) {
				msg = "�����ɹ���";
			} else {
				msg = "����ʧ�ܣ�����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		
	}else if("cbzdcx".equals(action)){
		//����վ���ѯ--���泬���վ��
		String zdid = request.getParameter("chooseIdStr");
		String zdsx = request.getParameter("zdsx");
		String bzmonth = request.getParameter("bzmonth");

		CbzdQuery bean=new CbzdQuery();
		String msg = bean.getCbzdxx(zdid,zdsx,bzmonth);
		
		//����վ���ѯ--ɾ������վ���ظ�����
		bean.delCbzdxx(bzmonth);
		
		String path = request.getContextPath();
		String url = path + "/web/jzcbnewfunction/cbjzcx.jsp";
		HttpSession session = request.getSession();
		
		if ("���ɳ���վ�㵥�ݳɹ���".equals(msg)) {
			msg = "���ɳ���վ�㵥�ݳɹ���";
		} else {
			url = path + "/web/query/caijipoint/cbzdcx.jsp";
			msg = "���ɳ���վ�㵥��ʧ�ܣ�����ϵ����Ա��";
		}
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
		
	}else if ("cbzdcx1".equals(action)) {
		//����վ���ѯ--���泬���վ��
	    PrintWriter out = response.getWriter();
	    
		String zdid = request.getParameter("chooseIdStr");
		String zdsx = request.getParameter("zdsx");
		String bzmonth = request.getParameter("bzmonth");
           
		CbzdQuery bean=new CbzdQuery();
		String msg = bean.getCbzdxx(zdid,zdsx,bzmonth);
		String path = request.getContextPath();
		String url = path + "/web/tongjichaxun/cbzdcx.jsp";
		HttpSession session = request.getSession();

        String m="";
        if(msg=="���ɳ���վ�㵥��ʧ�ܣ�"){
        	m="0";
            session.setAttribute("url", url);
            session.setAttribute("msg", msg);
        }else{
        	m="1";
        }
        out.print(m);
        out.close();
    }else if ("jcsjzsx".equals(action)) {
		//����վ���ѯ--���泬���վ��
    	String bzyf = request.getParameter("bzyf");
    	String property = request.getParameter("property");
    	String zdsxcw = request.getParameter("zdsxcw");
    	String scnhft = request.getParameter("scnhft");
    	String mycbgk = request.getParameter("mycbgk");
    	String cbzd = request.getParameter("cbzd");
    	String mycbjscb = request.getParameter("mycbjscb");
    	String lxcb = request.getParameter("lxcb");
    	String lxcbdlzj = request.getParameter("lxcbdlzj");
    	String pld = request.getParameter("pld");
    	String cbbl = request.getParameter("cbbl");
    	String cbjzzg = request.getParameter("cbjzzg");
    	String array = request.getParameter("array");
    	List<cbJzBean> fylist = null;
    	if(null!=request.getSession().getAttribute("fylist")){			
    		fylist=(List<cbJzBean>) request.getSession().getAttribute("fylist");
		 } 
		CbzdQuery bean=new CbzdQuery();
		String msg = bean.setSaveqztz(bzyf,property,zdsxcw,scnhft,mycbgk,cbzd,mycbjscb,lxcb,lxcbdlzj,pld,cbbl,cbjzzg,array,fylist);
		String path = request.getContextPath();
		String url = path + "/web/tongjichaxun/cbjzGlIfram.jsp";
		HttpSession session = request.getSession();
		
		if ("2".equals(msg)) {
			msg = "����ɹ���";
		} else {
			msg = "����ʧ�ܣ�����ϵ����Ա��";
		}
		session.setAttribute("url", url);
		session.setAttribute("msg", msg);
		response.sendRedirect(path + "/web/msg.jsp");
    }
}

	/**
	 * ��ִ����Ϣ��������ǰҳ��
	 * @param request
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo,String bzw){
	
		try {
			if("1".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/showzg.jsp").forward(request, response);
				
			}else if("2".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/showzgpl.jsp").forward(request, response);
			}else if("3".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/cbjzxx.jsp").forward(request, response);
			}else if("4".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/cbjzpl.jsp").forward(request, response);
				
			}else if("5".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/ZGXX.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
