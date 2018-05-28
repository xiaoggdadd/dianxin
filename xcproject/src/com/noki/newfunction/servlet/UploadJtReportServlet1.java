package com.noki.newfunction.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.newfunction.dao.CbzdQuery;
import com.noki.newfunction.dao.UploadDao;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.Path;

public class UploadJtReportServlet1 extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  String path = request.getContextPath();
		  DbLog log = new DbLog();
		  Account account = new Account();
		  String url = "", msg = "";
		  HttpSession session = request.getSession();
		  account = (Account) session.getAttribute("account");
		  String accountname=account.getAccountName();
		  String loginId=account.getAccountId();
 		  String action = request.getParameter("action");
		  String msg1="";
		  CbzdQuery bean=new CbzdQuery();
		  if("spd".equals(action)){
			    Zdinfo formBean= new Zdinfo();
		    	 String chooseIdStr1 = request.getParameter("chooseIdStr1"); 
		            url = path + "/web/jzcbnewfunction/cbjzcx.jsp" ;
		            msg = bean.spd1(chooseIdStr1,accountname);
		           
		       if("�ɵ��ɹ���".equals(msg)){
		    	   Zdinfo zd=new Zdinfo();
		           List<Zdinfo> ls=bean.seachData1(chooseIdStr1);
		           if(ls!=null){
		    	         msg1=bean.addzdxx(ls);
		        }
		     }
		       if(msg1.equals(msg)){
		    	   msg="�ɵ��ɹ���";
		    	   log.write(msg, account.getName(), request.getRemoteAddr(), "����վ������"); 
		       }else{
		    	   
		    	   msg="�ɵ�ʧ�ܣ�";
		       }
		       session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        response.sendRedirect(path + "/web/msg.jsp");
		  }else if("spdpl".equals(action)){
			  url = path + "/web/jzcbnewfunction/cbjzcx.jsp" ;
			  String id=request.getParameter("chooseIdStr1");
			  
			  msg = bean.spd(id,accountname);
			   if("�ɵ��ɹ���".equals(msg)){
		    	   Zdinfo zd=new Zdinfo();
		           List<Zdinfo> ls=bean.seachData1(id);
		           zd.setId(id);
		           
		           int i=bean.seachData2(id);
		           
		           if(ls!=null){
		        	   for(Zdinfo l:ls){
		        		   Zdinfo z=new Zdinfo();
		        		   z.setId(l.getId());
		        		   z.setZdid(l.getZdid());
		        		   z.setCbsj(l.getCbsj());
		        	   }
		    	       msg1=bean.addzdxx2(ls,i);
		        }
		     }
			   if(msg1.equals(msg)){
		    	   msg="�ɵ��ɹ���";
		    	   log.write(msg, account.getName(), request.getRemoteAddr(), "����վ������"); 
		       }else{
		    	   msg="�ɵ�ʧ�ܣ�";
		       }
			  session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        response.sendRedirect(path + "/web/msg.jsp");
		  }else if("upload".equals(action)){
//			  List<String> statusInfo = new ArrayList<String>();//��ʾ�Ĵ�����Ϣ
			  String confirm = request.getParameter("confirm");
			  String ida = request.getParameter("idcz");
			  List<Zdinfo> ls=bean.seachData1(ida);
			  String filename="";

			  UploadDao dao=new UploadDao(account,ida,"");
			  if(null == confirm){					
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
						sendStatusInfo(request,response,statusInfo); //�Ѵ�����Ϣ ����ǰ̨
						return;
					}
					String tempfile1=tempfile+"\\"+c;
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
								} catch (Exception e) {
									statusInfo.add("�ڲ���������ϵ����Ա��");
									sendStatusInfo(request,response,statusInfo);
									e.printStackTrace();
									return;
								}
								statusInfo.add("�ļ��ϴ��ɹ���");
							}
						}
					}
					sendStatusInfo(request,response,statusInfo);
				}else{//�ж��ļ��Ƿ�����ϴ�
					PrintWriter out = response.getWriter();
					String result = "0";
					if(dao.fileIsUploaded())result = "1";//�жϱ����Ƿ��Ѿ��ϴ�������о͸��ǣ� �������true ��û���ظ����� ����1�����false �����Ѿ��ϴ��˱��·ݵı���
					out.println(result);
					out.flush();
					out.close();
				}
			
	}
	}
	
	
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
		
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo){
		request.setAttribute("statusInfo", statusInfo);
		try {
			request.getRequestDispatcher("/web/jzcbnewfunction/pdtestbewritexx.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
