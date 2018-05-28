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
		           
		       if("派单成功！".equals(msg)){
		    	   Zdinfo zd=new Zdinfo();
		           List<Zdinfo> ls=bean.seachData1(chooseIdStr1);
		           if(ls!=null){
		    	         msg1=bean.addzdxx(ls);
		        }
		     }
		       if(msg1.equals(msg)){
		    	   msg="派单成功！";
		    	   log.write(msg, account.getName(), request.getRemoteAddr(), "超标站点整改"); 
		       }else{
		    	   
		    	   msg="派单失败！";
		       }
		       session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        response.sendRedirect(path + "/web/msg.jsp");
		  }else if("spdpl".equals(action)){
			  url = path + "/web/jzcbnewfunction/cbjzcx.jsp" ;
			  String id=request.getParameter("chooseIdStr1");
			  
			  msg = bean.spd(id,accountname);
			   if("派单成功！".equals(msg)){
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
		    	   msg="派单成功！";
		    	   log.write(msg, account.getName(), request.getRemoteAddr(), "超标站点整改"); 
		       }else{
		    	   msg="派单失败！";
		       }
			  session.setAttribute("url", url);
		        session.setAttribute("msg", msg);
		        response.sendRedirect(path + "/web/msg.jsp");
		  }else if("upload".equals(action)){
//			  List<String> statusInfo = new ArrayList<String>();//显示的错误信息
			  String confirm = request.getParameter("confirm");
			  String ida = request.getParameter("idcz");
			  List<Zdinfo> ls=bean.seachData1(ida);
			  String filename="";

			  UploadDao dao=new UploadDao(account,ida,"");
			  if(null == confirm){					
					File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录   上传报表时 存报表的临时路径    最后删除
					FileItem item =  null;
					DiskFileItemFactory factory = new DiskFileItemFactory();
			        factory.setSizeThreshold(4096);	//设置文件大小的		设置是否将上传文件已临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。对应的getSizeThreshold() 方法用来获取此临界值。					
			        factory.setRepository(tempfile);//文件以临时文件形式保存在磁盘上的存放目录
				
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        upload.setSizeMax(10485760);//2097152文件上传大小限制为2M
			        List<String> statusInfo = new ArrayList<String>();//显示的错误信息
			        List<String> fileItems = new ArrayList<String>();
			        String c="",s="";
			        try {
						fileItems = upload.parseRequest(request);				
					} catch (FileUploadException e) {   
						statusInfo.add("上传文件超出大小限制（10M）");
						sendStatusInfo(request,response,statusInfo); //把错误信息 传到前台
						return;
					}
					String tempfile1=tempfile+"\\"+c;
					for(Object o:fileItems){
						FileItem file = (FileItem)o;
						if(!file.isFormField()){
							UploadDao dao1=new UploadDao(account,ida,file.getName());
							dao1.isAvailableToUpload(statusInfo, file);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
							String mm="";
							if(statusInfo.size()==1){
								mm=statusInfo.get(0);
							}
							if(mm==null||statusInfo.size()==0){
								try {
									dao1.insertToDB(file,ls);//把数据表存到数据库
								} catch (Exception e) {
									statusInfo.add("内部错误，请联系管理员！");
									sendStatusInfo(request,response,statusInfo);
									e.printStackTrace();
									return;
								}
								statusInfo.add("文件上传成功！");
							}
						}
					}
					sendStatusInfo(request,response,statusInfo);
				}else{//判断文件是否可以上传
					PrintWriter out = response.getWriter();
					String result = "0";
					if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖） 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
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
		// 设置允许用户上传文件大小,单位:字节，这里设为2m
		fu.setSizeMax(5 * 1024 * 1024);
		// 设置最多只允许在内存中存储的数据,单位:字节
		fu.setSizeThreshold(4096);
		// 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		Path path = new Path();
		path.servletInitialize(getServletConfig().getServletContext());


		File dir2 = new File(System.getProperty("java.io.tmpdir"));
		
		String dir1 = dir2 + "\\";
		// String dir1 = path.getPhysicalPath("/temp/", 0); // 传参数
		fu.setRepositoryPath(dir1);
		// 开始读取上传信息
		List fileItems = fu.parseRequest(request);
		// 依次处理每个上传的文件
		Iterator iter = fileItems.iterator();
		// 正则匹配，过滤路径取文件名
		String regExp = ".+\\\\(.+)$";
		// 过滤掉的文件类型
		// String[] errorType={".exe",".com",".cgi",".asp"};
		Pattern p = Pattern.compile(regExp);
		String zipname = null;
		int num = 0;
		File file = null;
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// 忽略其他不是文件域的所有表单信息
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
