package com.noki.newfunction.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.CbzdQuery;
import com.noki.newfunction.dao.FilesUpload;
import com.noki.newfunction.dao.PdTestBewrite;
import com.noki.newfunction.dao.UploadDao;
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.newfunction.dao.ZGShenHeDao;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.Path;

@SuppressWarnings("serial")
public class qxfj extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Account account = (Account)(request.getSession().getAttribute("account"));
		String accountname=account.getName();
		String actiona=request.getParameter("action");
		String bb=request.getParameter("bb");
		String msg="",msg1="";
		String confirm = request.getParameter("confirm");
		String bzw=request.getParameter("bzw");
		//FilesUpload dao = new FilesUpload(account);
		Zdinfo formBean = new Zdinfo();
		CbzdQuery bean=new CbzdQuery();
		DbLog log = new DbLog();
		HttpSession session = request.getSession();
		String url = request.getContextPath() + "/web/jzcbnewfunction/pdtestbewrite.jsp";
		
		String ida = request.getParameter("idcz");
		List<Zdinfo> ls=bean.seachData1(ida);	
		UploadDao dao=new UploadDao(account,ida,"");
		
		if(actiona.equals("sc")){
		if(null == confirm){//�ļ���ʼ�ϴ�
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
			String entrytime = df.format(new Date());
			List<Zdinfo> lstt=bean.seachData1(ida);	
			String iddd="",zdidd="";
			if(lstt!=null){
				for(Zdinfo zd:lstt){
					iddd=zd.getId();
					zdidd=zd.getZdid();
				}
				 
			}
			formBean.setId(iddd);
			formBean.setZdid(zdidd);
			formBean.setWjid(request.getParameter("idcz"));
			formBean.setG2(request.getParameter("g2now"));
			formBean.setG3(request.getParameter("g2now"));
			formBean.setZp(request.getParameter("zpnow"));
			formBean.setZs(request.getParameter("zsnow"));
			formBean.setChangjia(request.getParameter("changjianow"));

			formBean.setJztype(request.getParameter("jztypenow"));
			formBean.setShebei(request.getParameter("shebeinow"));
			formBean.setBbu(request.getParameter("bbunow"));
			formBean.setRru(request.getParameter("rrunow"));
			//formBean.setYdshebei(request.getParameter("ydshebeinow"));
			formBean.setGxgwsl(request.getParameter("ydshebeinow"));
			formBean.setCsms(request.getParameter("teststruction"));
			formBean.setYyfx(request.getParameter("reasonanalyse"));
			formBean.setCszrr(request.getParameter("testperson"));
			formBean.setQxlrr(request.getParameter("accountid"));
			formBean.setDbds(request.getParameter("jsdbds"));
			formBean.setKGDYZLFH(request.getParameter("kgzfh"));
			formBean.setYDGXSBZLFH(request.getParameter("ydzlfh"));
			formBean.setDXGXSBZLFH(request.getParameter("dxzlfh"));
			formBean.setGYGXSBZLFH(request.getParameter("gyzlfh"));
			formBean.setZYYGSBZLFH(request.getParameter("zlygfh"));
			formBean.setDxgxsb(request.getParameter("gxgwslnow"));
			formBean.setYdgxsb(request.getParameter("ydgxsb"));
			formBean.setG2xq(request.getParameter("g2xqnow"));
			formBean.setG3sq(request.getParameter("g3sqnow"));
			
			formBean.setQxlrsj(entrytime);
			PdTestBewrite beana = new PdTestBewrite();	
			
			//�ϴ������ݴ�����ʱ�ļ�Ŀ¼
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
							//String path = getServletContext().getRealPath("/qxhsfj");
							String name = file.getName();
							String name1="";
							String path1="";
							String msg2="";
							String path="";
							//name=formBean.getId()+formBean.getZdid()+"ID"+name;
							//file.write(new File(path+"/"+name));
							
							if(!name.equals("")){
								path = getServletContext().getRealPath("/qxhsfj");
								name=formBean.getId()+formBean.getZdid()+"ID"+name;
								System.out.println("name--"+name);
								file.write(new File(path+"/"+name));
							}
							msg2 = beana.updatePdTestBewritezdxx(formBean,path,name);
							if("�޸�վ����Ϣ�ɹ�!".equals(msg2)){
								msg="�ɵ��ɹ���";
							log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"���������޸�");
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
			
			
			
		//�ϴ������ݴ������ݿ�
				/*
					File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼   �ϴ�����ʱ �汨�����ʱ·��    ���ɾ��
					FileItem item =  null;
					DiskFileItemFactory factory = new DiskFileItemFactory();
			        factory.setSizeThreshold(4096);	//�����ļ���С��		�����Ƿ��ϴ��ļ�����ʱ�ļ�����ʽ�����ڴ��̵��ٽ�ֵ�����û�е��ø÷������ô��ٽ�ֵ���������ϵͳĬ��ֵ10KB����Ӧ��getSizeThreshold() ����������ȡ���ٽ�ֵ��					
			        factory.setRepository(tempfile);//�ļ�����ʱ�ļ���ʽ�����ڴ����ϵĴ��Ŀ¼
				
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        upload.setSizeMax(10485760);//2097152�ļ��ϴ���С����Ϊ2M
			        List<String> statusInfo = new ArrayList<String>();//��ʾ�Ĵ�����Ϣ
			        List<String> fileItems = new ArrayList<String>();
			        try {
						fileItems = upload.parseRequest(request);				
					} catch (FileUploadException e) {   
						statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
						sendStatusInfo(request,response,statusInfo,bzw); //�Ѵ�����Ϣ ����ǰ̨
						return;
					}
					for(Object o:fileItems){
						FileItem file = (FileItem)o;
						if(!file.isFormField()){
							UploadDao dao1=new UploadDao(account,ida,file.getName());
							dao1.isAvailableToUpload(statusInfo, file);	//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
							String mm="";
							if(statusInfo.size()==1){
								mm=statusInfo.get(0);
							}
							if(mm==null||statusInfo.size()==0){
								try {
									dao1.insertToDB(file,ls);//�����ݱ�浽���ݿ�
									String msg2="";
									msg2 = beana.updatePdTestBewritezdxx(formBean);
									if("�޸�վ����Ϣ�ɹ�!".equals(msg2)){
										msg="�ɵ��ɹ���";
									log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"���������޸�");
									}
								statusInfo.add(msg);
								} catch (Exception e) {
									statusInfo.add("�ڲ���������ϵ����Ա��");
									sendStatusInfo(request,response,statusInfo,bzw);
									e.printStackTrace();
									return;
								}
								statusInfo.add("�ļ��ϴ��ɹ���");
							}
						}
					}
					sendStatusInfo(request,response,statusInfo,bzw);
				}else{//�ж��ļ��Ƿ�����ϴ�
					PrintWriter out = response.getWriter();
					String result = "0";
					if(dao.fileIsUploaded())result = "1";//�жϱ����Ƿ��Ѿ��ϴ�������о͸��ǣ� �������true ��û���ظ����� ����1�����false �����Ѿ��ϴ��˱��·ݵı���
					out.println(result);
					out.flush();
					out.close();
				}
		}else if("8".equals(actiona)){
			FilesUpload daob = new FilesUpload(account);
			String sjyq=request.getParameter("sjyq");
			String bzw1=request.getParameter("bzw");
			String idb=request.getParameter("id");
			String entrytimeQS=request.getParameter("entrytimeQS");
			System.out.println("��־λL��"+bzw);
			if(null == confirm){//�ļ���ʼ�ϴ�
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
				String entrytime = df.format(new Date());
			//�ϴ������ݴ������ݿ�
					
						File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼   �ϴ�����ʱ �汨�����ʱ·��    ���ɾ��
						FileItem item =  null;
						DiskFileItemFactory factory = new DiskFileItemFactory();
				        factory.setSizeThreshold(4096);	//�����ļ���С��		�����Ƿ��ϴ��ļ�����ʱ�ļ�����ʽ�����ڴ��̵��ٽ�ֵ�����û�е��ø÷������ô��ٽ�ֵ���������ϵͳĬ��ֵ10KB����Ӧ��getSizeThreshold() ����������ȡ���ٽ�ֵ��					
				        factory.setRepository(tempfile);//�ļ�����ʱ�ļ���ʽ�����ڴ����ϵĴ��Ŀ¼
					
				        ServletFileUpload upload = new ServletFileUpload(factory);
				        upload.setSizeMax(10485760);//2097152�ļ��ϴ���С����Ϊ2M
				        List<String> statusInfo = new ArrayList<String>();//��ʾ�Ĵ�����Ϣ
				        List<String> fileItems = new ArrayList<String>();
				        try {
							fileItems = upload.parseRequest(request);				
						} catch (FileUploadException e) {   
							statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
							sendStatusInfo(request,response,statusInfo,bzw1); //�Ѵ�����Ϣ ����ǰ̨
							return;
						}
						for(Object o:fileItems){
							FileItem file = (FileItem)o;
							if(!file.isFormField()){
								UploadDao dao1=new UploadDao(account,idb,file.getName());
								dao1.isAvailableToUpload(statusInfo, file);	//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
								String mm="";
								if(statusInfo.size()==1){
									mm=statusInfo.get(0);
								}
								if(mm==null||statusInfo.size()==0){
									try {
										 CbzdQuery beana=new CbzdQuery();
										 List<Zdinfo> lst=bean.seachData1(idb);
										
										String msg2="";
										
										 int i=bean.seachDataMax();
										 int j=i+1;
										 System.out.println("j:"+j);
						    	     //   msg=daob.insertToDB(file.getName(),lst,sjyq,accountname,j,entrytimeQS);
						    	       if("�ɵ��ɹ���".equals(msg)){
						    	    	     msg2 = bean.spd1(idb,accountname);
						    	       }
						    	       if("�ɵ��ɹ���".equals(msg2)){
						    	    	   
						    	    	   dao1.insertToDBB(file,lst);//�����ݱ�浽���ݿ�
						    	    	   msg="�ɵ��ɹ���";
						    	       }
						    	  
						    	     
										 
										// msg2 = beana.updatePdTestBewritezdxx(formBean);
										if("�ɵ��ɹ���".equals(msg)){
											msg="�ɵ��ɹ���";
										log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"����վ���ѯʡ������");
										}
									statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("�ڲ���������ϵ����Ա��");
										sendStatusInfo(request,response,statusInfo,bzw);
										e.printStackTrace();
										return;
									}
									statusInfo.add("�ļ��ϴ��ɹ���");
								}
							}
						}
						sendStatusInfo(request,response,statusInfo,bzw);
					}else{//�ж��ļ��Ƿ�����ϴ�
						PrintWriter out = response.getWriter();
						String result = "0";
						if(dao.fileIsUploaded())result = "1";//�жϱ����Ƿ��Ѿ��ϴ�������о͸��ǣ� �������true ��û���ظ����� ����1�����false �����Ѿ��ϴ��˱��·ݵı���
						out.println(result);
						out.flush();
						out.close();
					}*/
			
			
		}else if("send".equals(actiona)){
			if("1".equals(bb)){
				String jztypenow=request.getParameter("jztypenow");
				CbzdQuery cd=new CbzdQuery();
				String s=cd.getnameab(jztypenow);
				PrintWriter out = response.getWriter();
				out.println(s.trim());
				out.close();
			}else if("2".equals(bb)){
				String shebeinow=request.getParameter("shebeinow");
				CbzdQuery cd=new CbzdQuery();
				String s=cd.getnameabc(shebeinow);
				PrintWriter out = response.getWriter();
				out.println(s.trim());
				out.close();
				
			}else{
			String cjnow=request.getParameter("cjnow");
			CbzdQuery cd=new CbzdQuery();
			String s=cd.getnamea(cjnow);
			PrintWriter out = response.getWriter();
			out.println(s.trim());
			out.close();
		}
	}
		if(actiona.equals("chexiao")){//������������
			
			String path = request.getContextPath();
			url = path + "/web/jzcbnewfunction/pdtestbewrite.jsp";
			String chooseIdStr = request.getParameter("chooseIdStr");
			PdTestBewrite dao2 = new PdTestBewrite();
			int Rs = dao2.CSupdate(chooseIdStr);
			if(Rs==1){
				msg = "�����ɹ���"; 
			}else if(Rs==0){
				msg = "����ʧ��,����ϵ����Ա��";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
		if(actiona.equals("qxzgwc")){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
			String entrytime = df.format(new Date());
			
			String loginName = account.getAccountName();
			String wcsm = request.getParameter("wcsm");
			String beginTime1 = request.getParameter("beginTime1");
			String zhssdl= request.getParameter("zhssdl");
			String zgfzr = request.getParameter("zgfzr");
			String id1 = request.getParameter("cid");
			//FilesUpload dao = new FilesUpload(account);
			System.out.println("------aaaa-");
			PdTestBewrite beana = new PdTestBewrite();	
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼   �ϴ�����ʱ �汨�����ʱ·��    ���ɾ��
			FileItem item =  null;
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);	//�����ļ���С��		�����Ƿ��ϴ��ļ�����ʱ�ļ�����ʽ�����ڴ��̵��ٽ�ֵ�����û�е��ø÷������ô��ٽ�ֵ���������ϵͳĬ��ֵ10KB����Ӧ��getSizeThreshold() ����������ȡ���ٽ�ֵ��					
	        factory.setRepository(tempfile);//�ļ�����ʱ�ļ���ʽ�����ڴ����ϵĴ��Ŀ¼
		
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//2097152�ļ��ϴ���С����Ϊ2M
	        List<String> statusInfo = new ArrayList<String>();//��ʾ�Ĵ�����Ϣ
	        List<String> fileItems = new ArrayList<String>();
	        String c="",s="";
	        try {
				fileItems = upload.parseRequest(request);				
			} catch (FileUploadException e) {   
				statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
				sendStatusInfo(request,response,statusInfo,bzw); //�Ѵ�����Ϣ ����ǰ̨
				return;
			}
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				if(!file.isFormField()){
					String[] aa= file.toString().split(",");
					int ss=aa[1].lastIndexOf("\\")+1;
					s=aa[1].substring(ss,aa[1].length()); //��ȡ�ϴ��ļ�����
					
					String tempfile1=tempfile+"\\"+s;
					Vector biaozhi=this.getContentbiaozhi(tempfile1);//�ж��Ƿ���  ���ص����ǵı���   ������sheet���һ����Ԫ��������12
					if(biaozhi.isEmpty()){
						statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
						sendStatusInfo(request,response,statusInfo,bzw);//�Ѵ�����Ϣ ����ǰ̨
						return;
					}else{
						
					Object[] a=biaozhi.toArray();
					String id="";
					  for(int i=0;i<a.length;i++){ //Ӧ������0��ʼѭ���ж�  
						  Object[] b=((Vector)a[i]).toArray();
						  id=b[0].toString().trim();
						  if(!id.equals("zdcbgl")){
							statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
							sendStatusInfo(request,response,statusInfo,bzw);//�Ѵ�����Ϣ ����ǰ̨
							return;
						  }
					  }
					}
					//UploadDao dao1=new UploadDao(account,ida,file.getName());
					UploadDaoQxfj dao1=new UploadDaoQxfj(account,ida,file.getName());
					dao1.isAvailableToUpload(statusInfo, file);	//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
					String mm="";
					if(statusInfo.size()==1){
						mm=statusInfo.get(0);
					}
					if(mm==null||statusInfo.size()==0){
						try {
							dao1.insertToDBZG(file,ls);
							String msg2=""; 
							//msg2 = beana.updatePdTestBewritezdxx(formBean);
							ZGShenHeDao dao2 = new ZGShenHeDao();
							//dao1.insertToDB(file,ls);//�����ݱ�浽���ݿ�
							int rs = dao2.TJSHSave(loginName, wcsm,zhssdl, beginTime1, zgfzr,id1,"",file.getName());
							/*if("�޸�վ����Ϣ�ɹ�!".equals(msg2)){
								msg="�ɵ��ɹ���";
							log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"���������޸�");
							}*/
							
							if(rs==1){
								msg="����ɹ���";
							}else{
								msg="����ʧ�ܣ�";
							}
						statusInfo.add(msg);
						} catch (Exception e) {
							statusInfo.add("�ڲ���������ϵ����Ա��");
							sendStatusInfo(request,response,statusInfo,bzw);
							e.printStackTrace();
							return;
						}
						statusInfo.add("�ļ��ϴ��ɹ���");
					}
				}
			}
			sendStatusInfo(request,response,statusInfo,bzw);
		}else{//�ж��ļ��Ƿ�����ϴ�
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";//�жϱ����Ƿ��Ѿ��ϴ�������о͸��ǣ� �������true ��û���ظ����� ����1�����false �����Ѿ��ϴ��˱��·ݵı���
			out.println(result);
			out.flush();
			out.close();
		}}
	/**
	 * ��ִ����Ϣ��������ǰҳ��
	 * @param request
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo,String bzw){
	
		try {
			if("1".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/pdtestbewritexx.jsp").forward(request, response);
				
			}else if("2".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/showzg.jsp").forward(request, response);
				
				
			}else{
				request.setAttribute("statusInfo", statusInfo);
			    request.getRequestDispatcher("/web/jzcbnewfunction/pdtestbewritexx.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ϴ�������
	 */
	public synchronized String upload(HttpServletRequest request,
			HttpServletResponse response, String loginName) throws Exception {
		String filename = "";
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		filename = loginName + System.currentTimeMillis();
		// String store = "";
		ArrayList list = new ArrayList();
		DiskFileUpload fu = new DiskFileUpload();
		fu.setHeaderEncoding("utf-8");
		// ���������û��ϴ��ļ���С,��λ:�ֽڣ�������Ϊ2m
		fu.setSizeMax(5 * 1024 * 1024);
		// �������ֻ�������ڴ��д洢������,��λ:�ֽ�
		fu.setSizeThreshold(4096);
		// ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼
		Path path = new Path();
		path.servletInitialize(getServletConfig().getServletContext());


		File dir2 = new File(System.getProperty("java.io.tmpdir"));
		
		String dir1 = dir2 + "\\";
		// String dir1 = path.getPhysicalPath("/temp/", 0); // ������
		fu.setRepositoryPath(dir1);
		// ��ʼ��ȡ�ϴ���Ϣ
		List fileItems = fu.parseRequest(request);
		// ���δ���ÿ���ϴ����ļ�
		Iterator iter = fileItems.iterator();
		// ����ƥ�䣬����·��ȡ�ļ���
		String regExp = ".+\\\\(.+)$";
		// ���˵����ļ�����
		// String[] errorType={".exe",".com",".cgi",".asp"};
		Pattern p = Pattern.compile(regExp);
		String zipname = null;
		int num = 0;
		File file = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// �������������ļ�������б���Ϣ
			if (!item.isFormField()) {
				String name = item.getName();
				long size = item.getSize();
				if ((name == null || name.equals("")) && size == 0) {
					continue;
		  }
		  }

		}
		return filename;
	}
	
	
	public Vector getContentbiaozhi(String filename){
		try{
			Sheet sheet=null;
			 Workbook book = Workbook.getWorkbook(new File(filename));
			 int counts = book.getNumberOfSheets();
			 System.out.println("���Ի�ȡbook�����sheet�ĸ���"+counts);	
			 Vector content = new Vector();
			 if(counts==3){
			   sheet = book.getSheet(2);//���㿪ʼ��   2���������sheet   �ж��ǲ���11
				 for (int i = 0; i < sheet.getRows(); i++) {
		                Vector rows = new Vector();
		                boolean isNull = true;
		                for (int j = 0; j < sheet.getColumns(); j++) {
		                    Cell cell = sheet.getCell(0, 0);
		                    String result = cell.getContents();//��ȡ��һ����Ԫ�������
		                    System.out.println("�ж�ģ��ֵ��"+result);
		                    rows.add(result);
		                    if (result != null && !result.equals("")) {
		                        isNull = false;
		                    }
		                }
		                if (!isNull) {
		                    content.add(rows);//�����Ϊ��  �浽������  ����
		                }else{
		                	System.out.println("�п� ��  ������");
		                	break;
		                    
		                }
				 }
				 return content;
			 }else{
				 return content;
			 }
		 } catch (Exception e) {
	            e.printStackTrace();
	     }
		 return null;
	}

}
