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
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.newfunction.dao.ZGShenHeDao;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.Path;

@SuppressWarnings("serial")
public class UploadDaoQxfjServlet extends HttpServlet {
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
		//List<Zdinfo> ls=bean.seachData1(ida);	
		UploadDaoQxfj dao=new UploadDaoQxfj(account,ida,"");
		
		if(actiona.equals("sc")){
			List<String> statusInfo = new ArrayList<String>();//显示的错误信息
			PdTestBewrite beana = new PdTestBewrite();	
			String xgsqid = request.getParameter("xgsqid");//整改工单id
			String fzbzw = request.getParameter("fzbzw");//赋值标志位 1:赋值  0:不赋值 
			boolean xxxgbz = true;
			if("1".equals(fzbzw)){//整改工单更新区县申请直流,交流,额定,生产
				String zlfh = request.getParameter("zlzfh");
				String jlfh = request.getParameter("jlzfh");
				String edhdl = request.getParameter("bdhdl");
				String scb = request.getParameter("qsdb");
				xxxgbz = beana.setXxxg(xgsqid, zlfh, jlfh, edhdl, scb);
			}
		if(xxxgbz){
		if(null == confirm){//文件开始上传
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());
			List<Zdinfo> lstt=bean.seachData1(ida);	
			String iddd="",zdidd="";
			List<Zdinfo> ls=bean.seachData1(ida);	
			
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
			formBean.setG3(request.getParameter("g3now"));
			formBean.setZp(request.getParameter("zpnow"));
			formBean.setZs(request.getParameter("zsnow"));
			formBean.setChangjia("0".equals(request.getParameter("changjianow"))?"":request.getParameter("changjianow"));

			formBean.setJztype("0".equals(request.getParameter("jztypenow"))?"":request.getParameter("jztypenow"));
			formBean.setShebei("0".equals(request.getParameter("shebeinow"))?"":request.getParameter("shebeinow"));
			formBean.setBbu(request.getParameter("bbunow"));
			formBean.setRru(request.getParameter("rrunow"));
			//formBean.setYdshebei(request.getParameter("ydshebeinow"));
			formBean.setGxgwsl(request.getParameter("ydshebeinow"));
			formBean.setCsms(request.getParameter("teststruction"));
			//formBean.setYyfx(request.getParameter("reasonanalyse"));
			//formBean.setCszrr(request.getParameter("testperson"));
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
			formBean.setZlzfh(request.getParameter("zlzfh"));
			formBean.setJlzfh(request.getParameter("jlzfh"));
			formBean.setBdhdl(request.getParameter("bdhdl"));
			formBean.setQsdb(request.getParameter("qsdb"));
			formBean.setZhssdl(request.getParameter("zhssdl"));
			formBean.setYssdbdl(request.getParameter("yssdbdl"));
			
			formBean.setQxlrsj(entrytime);
			
			//上传，数据存入临时文件目录
			/*		File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);								
	        factory.setRepository(tempfile);
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//文件上传大小限制为10M
	        List<String> statusInfo = new ArrayList<String>();//错误信息
	        List<?> fileItems = null;
	       try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				statusInfo.add("上传文件超出大小限制（10M）");
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
							if("修改站点信息成功!".equals(msg2)){
								msg="派单成功！";
							log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"测试描述修改");
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
		else{//判断文件是否可以上传
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";
			out.println(result);
			out.flush();
			out.close();
		}*/
			
			
			
		//上传，数据存入数据库
				
					File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录   上传报表时 存报表的临时路径    最后删除
					FileItem item =  null;
					DiskFileItemFactory factory = new DiskFileItemFactory();
			        factory.setSizeThreshold(4096);	//设置文件大小的		设置是否将上传文件已临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。对应的getSizeThreshold() 方法用来获取此临界值。					
			        factory.setRepository(tempfile);//文件以临时文件形式保存在磁盘上的存放目录
				
			        ServletFileUpload upload = new ServletFileUpload(factory);
			        upload.setSizeMax(6291456);//文件上传大小限制为6M
			        List<String> fileItems = new ArrayList<String>();
			        String c="",s="";
			        String nn="",tper="";
			        try {
						fileItems = upload.parseRequest(request);
					} catch (FileUploadException e) {   
						statusInfo.add("上传文件超出大小限制（6M）");
						sendStatusInfo(request,response,statusInfo,bzw,ida); //把错误信息 传到前台
						return;
					}
					  Iterator iter = fileItems.iterator();
					  while(iter.hasNext()) {
				         FileItem  file = (FileItem)iter.next();
					  //}
					//for(Object o:fileItems){
						//FileItem file = (FileItem)o;
				         if(file.isFormField()) {
				        	 String name1 = file.getFieldName(); 
				        	 if("reasonanalyse".equals(name1)){
				        		  nn = file.getString("utf-8");
				        	 }else if("testperson".equals(name1)){
				        		  tper = file.getString("utf-8");
				        	 }
				        	 
				        	System.out.println("==========="+nn+"----------"+tper);
				     		formBean.setYyfx(nn);
							formBean.setCszrr(tper);
				        	 
				         }else{
							String[] aa= file.toString().split(",");
							int ss=aa[1].lastIndexOf("\\")+1;
							s=aa[1].substring(ss,aa[1].length()); //截取上传文件名称
							
							String tempfile1=tempfile+"\\"+s;
							UploadDaoQxfj dao1=new UploadDaoQxfj(account,ida,file.getName());
							String name=file.getName();
							
							 //如果有附件
							if(name!=null&&!name.equals("")&&!name.equals("null")&&name.length()>0&&name!=""&&name!=" "){
								Boolean gs=dao1.isAvailableToUpload(statusInfo, file);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
								if (gs){
									statusInfo.add("上传失败，上传的文件后缀名必须为.xls");
									sendStatusInfo(request,response,statusInfo,bzw,ida);//把错误信息 传到前台
									return;
								}else{
							Vector biaozhi=this.getContentbiaozhi(tempfile1);//判断是否是  下载的我们的报表   第三个sheet里第一个单元格内容是12
							if(biaozhi.isEmpty()){
								statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
								sendStatusInfo(request,response,statusInfo,bzw,ida);//把错误信息 传到前台
								return;
							}else{
								
							Object[] a=biaozhi.toArray();
							String id="";
							  for(int i=0;i<a.length;i++){ //应该是重0开始循环判断  
								  Object[] b=((Vector)a[i]).toArray();
								  id=b[0].toString().trim();
								  if(!id.equals("55")){
									statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
									sendStatusInfo(request,response,statusInfo,bzw,ida);//把错误信息 传到前台
									return;
								  }
							  }
							}
							String mm="";
							if(statusInfo.size()==1){
								mm=statusInfo.get(0);
							}
							 if(mm==null||statusInfo.size()==0){
								try {
									System.out.println("有附件上传");
									dao1.insertToDB(file,ls);//把数据表存到数据库
									String msg2="";
									msg2 = beana.updatePdTestBewritezdxx(formBean,"",file.getName());
									if("修改站点信息成功!".equals(msg2)){
										msg="派单成功！";
										log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"测试描述修改");
									}
								statusInfo.add(msg);
								} catch (Exception e) {
									statusInfo.add("内部错误，请联系管理员！");
									sendStatusInfo(request,response,statusInfo,bzw,ida);
									e.printStackTrace();
									return;
								}
								statusInfo.add("文件上传成功！");
							    }
						     }
						   }else{
							  
							   String mm="";
								if(statusInfo.size()==1){
									mm=statusInfo.get(0);
								}
								 if(mm==null||statusInfo.size()==0){
									try {
										 System.out.println("没有上传附件");
										//dao1.insertToDB(file,ls);//把数据表存到数据库
										String msg2="";
										msg2 = beana.updatePdTestBewritezdxx(formBean,"",file.getName());
										if("修改站点信息成功!".equals(msg2)){
											msg="派单成功！";
											log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"测试描述修改");
										}
									statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("内部错误，请联系管理员！");
										sendStatusInfo(request,response,statusInfo,bzw,ida);
										e.printStackTrace();
										return;
									}
									statusInfo.add("文件上传成功！");
								    }
							   
						   }
						}
					}
					sendStatusInfo(request,response,statusInfo,bzw,ida);
				}else{//判断文件是否可以上传
					PrintWriter out = response.getWriter();
					String result = "0";
					if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖） 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
					out.println(result);
					out.flush();
					out.close();
				}
		}else{
			statusInfo.add("整改工单更新区县申请直流,交流,额定,生产标失败，请联系管理员！");
			sendStatusInfo(request,response,statusInfo,"1","");
		}
		
		
		}else if("8".equals(actiona)){
			FilesUpload daob = new FilesUpload(account);
			String sjyq=request.getParameter("sjyq");
			String bzw1=request.getParameter("bzw");
			String idb=request.getParameter("id");
			String entrytimeQS=request.getParameter("entrytimeQS");
			System.out.println("标志位L："+bzw);
			if(null == confirm){//文件开始上传
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String entrytime = df.format(new Date());
			//上传，数据存入数据库
					
						File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录   上传报表时 存报表的临时路径    最后删除
						FileItem item =  null;
						DiskFileItemFactory factory = new DiskFileItemFactory();
				        factory.setSizeThreshold(4096);	//设置文件大小的		设置是否将上传文件已临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。对应的getSizeThreshold() 方法用来获取此临界值。					
				        factory.setRepository(tempfile);//文件以临时文件形式保存在磁盘上的存放目录
					
				        ServletFileUpload upload = new ServletFileUpload(factory);
				        upload.setSizeMax(10485760);//文件上传大小限制为10M
				        List<String> statusInfo = new ArrayList<String>();//显示的错误信息
				        List<String> fileItems = new ArrayList<String>();
				        try {
							fileItems = upload.parseRequest(request);				
						} catch (FileUploadException e) {   
							statusInfo.add("上传文件超出大小限制（10M）");
							sendStatusInfo(request,response,statusInfo,bzw1,ida); //把错误信息 传到前台
							return;
						}
						for(Object o:fileItems){
							FileItem file = (FileItem)o;
							if(!file.isFormField()){
								UploadDaoQxfj dao1=new UploadDaoQxfj(account,idb,file.getName());
								dao1.isAvailableToUpload(statusInfo, file);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
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
						    	       if("派单成功！".equals(msg)){
						    	    	     msg2 = bean.spd1(idb,accountname);
						    	       }
						    	       if("派单成功！".equals(msg2)){
						    	    	   
						    	    	   dao1.insertToDBB(file,lst);//把数据表存到数据库
						    	    	   msg="派单成功！";
						    	       }
						    	  
						    	     
										 
										// msg2 = beana.updatePdTestBewritezdxx(formBean);
										if("派单成功！".equals(msg)){
											msg="派单成功！";
										log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"超标站点查询省级整改");
										}
									statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("内部错误，请联系管理员！");
										sendStatusInfo(request,response,statusInfo,bzw,ida);
										e.printStackTrace();
										return;
									}
									statusInfo.add("文件上传成功！");
								}
							}
						}
						sendStatusInfo(request,response,statusInfo,bzw,ida);
					}else{//判断文件是否可以上传
						PrintWriter out = response.getWriter();
						String result = "0";
						if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖） 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
						out.println(result);
						out.flush();
						out.close();
					}
			
			
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
		else if(actiona.equals("chexiao")){//测试描述撤销
			
			String path = request.getContextPath();
			url = path + "/web/jzcbnewfunction/pdtestbewrite.jsp";
			String chooseIdStr = request.getParameter("chooseIdStr");
			PdTestBewrite dao2 = new PdTestBewrite();
			int Rs = dao2.CSupdate(chooseIdStr);
			if(Rs==1){
				msg = "撤销成功！"; 
			}else if(Rs==0){
				msg = "撤销失败,请联系管理员！";
			}
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");
		}
	/*	if(actiona.equals("qxzgwc")){
			bzw="zg";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());
			
			String loginName = account.getAccountName();
			String wcsm = request.getParameter("wcsm");
			String beginTime1 = request.getParameter("beginTime1");
			String zgfzr = request.getParameter("zgfzr");
			String id1 = request.getParameter("cid");
			//FilesUpload dao = new FilesUpload(account);
			System.out.println("------aaaa-");
			PdTestBewrite beana = new PdTestBewrite();	
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录   上传报表时 存报表的临时路径    最后删除
			FileItem item =  null;
			DiskFileItemFactory factory = new DiskFileItemFactory();
	        factory.setSizeThreshold(4096);	//设置文件大小的		设置是否将上传文件已临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。对应的getSizeThreshold() 方法用来获取此临界值。					
	        factory.setRepository(tempfile);//文件以临时文件形式保存在磁盘上的存放目录
		
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setSizeMax(10485760);//文件上传大小限制为10M
	        List<String> statusInfo = new ArrayList<String>();//显示的错误信息
	        List<String> fileItems = new ArrayList<String>();
	        try {
				fileItems = upload.parseRequest(request);				
			} catch (FileUploadException e) {   
				statusInfo.add("上传文件超出大小限制（10M）");
				sendStatusInfo(request,response,statusInfo,bzw); //把错误信息 传到前台
				return;
			}
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				if(!file.isFormField()){
					UploadDaoQxfj dao1=new UploadDaoQxfj(account,ida,file.getName());
					dao1.isAvailableToUpload(statusInfo, file);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
					String mm="";
					if(statusInfo.size()==1){
						mm=statusInfo.get(0);
					}
					if(mm==null||statusInfo.size()==0){
						try {
							
							String msg2="";
							//msg2 = beana.updatePdTestBewritezdxx(formBean);
							ZGShenHeDao dao2 = new ZGShenHeDao();
							dao1.insertToDB(file,ls);//把数据表存到数据库
						//	int rs = dao2.TJSHsave(loginName, wcsm, beginTime1, zgfzr,id1);
							if("修改站点信息成功!".equals(msg2)){
								msg="派单成功！";
							log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"测试描述修改");
							}
							int rs=0;
							if(rs==1){
								msg="保存成功！";
							}else{
								msg="保存失败！";
							}
						statusInfo.add(msg);
						} catch (Exception e) {
							statusInfo.add("内部错误，请联系管理员！");
							sendStatusInfo(request,response,statusInfo,bzw);
							e.printStackTrace();
							return;
						}
						statusInfo.add("文件上传成功！");
					}
				}
			}
			sendStatusInfo(request,response,statusInfo,bzw);
		}else{//判断文件是否可以上传
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖） 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
			out.println(result);
			out.flush();
			out.close();
		}*/
		else if(actiona.equals("qxzgwc")){
			List<Zdinfo> ls=bean.seachData1(ida);	
			bzw="zg";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String entrytime = df.format(new Date());
			
			String loginName = account.getAccountName();
			String beginTime1 = request.getParameter("beginTime1");
			String zhssdl = request.getParameter("zhssdl");
			String id1 = request.getParameter("cid");
			//FilesUpload dao = new FilesUpload(account);
			System.out.println("------aaaa-");
			PdTestBewrite beana = new PdTestBewrite();	
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
	        String wcsm="",zgfzr="";
	        try {
				fileItems = upload.parseRequest(request);				
			} catch (FileUploadException e) {   
				statusInfo.add("上传文件超出大小限制（10M）");
				sendStatusInfo(request,response,statusInfo,bzw,ida); //把错误信息 传到前台
				return;
			}
			Iterator iter = fileItems.iterator();
			  while(iter.hasNext()) {
		         FileItem  file = (FileItem)iter.next();
			//for (Object o : fileItems) {
			//	FileItem file = (FileItem) o;
				if (file.isFormField()) {
					 String name1 = file.getFieldName(); 
		        	 if("wcsm".equals(name1)){
		        		  wcsm = file.getString("utf-8");
		        	 }else if("zgfzr".equals(name1)){
		        		  zgfzr = file.getString("utf-8");
		        	 }
				}else{
					String[] aa= file.toString().split(",");
					int ss=aa[1].lastIndexOf("\\")+1;
					s=aa[1].substring(ss,aa[1].length()); //截取上传文件名称
					String tempfile1=tempfile+"\\"+s;
					String name=file.getName();
					//System.out.println("文件名称1："+name);
					UploadDaoQxfj dao1=new UploadDaoQxfj(account,id1,file.getName());
					
					
				 //如果有附件
				if(name!=null&&!name.equals("")&&!name.equals("null")&&name.length()>0&&name!=""){
					Boolean gs=dao1.isAvailableToUpload(statusInfo, file);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
					if (gs){
						statusInfo.add("保存失败，上传的文件后缀名必须为.xls");
						sendStatusInfo(request,response,statusInfo,bzw,ida);//把错误信息 传到前台
						return;
					}else{
					
						Vector biaozhi=this.getContentbiaozhi(tempfile1);//判断是否是  下载的我们的报表   第三个sheet里第一个单元格内容是12
						if(biaozhi.isEmpty()){
							statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
							sendStatusInfo(request,response,statusInfo,bzw,ida);//把错误信息 传到前台
							return;
						}else{
						
							Object[] a=biaozhi.toArray();
							String id="";
					  for(int i=0;i<a.length;i++){ //应该是重0开始循环判断  
						  Object[] b=((Vector)a[i]).toArray();
						  id=b[0].toString().trim();
						  if(!id.equals("55")){
							statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
							sendStatusInfo(request,response,statusInfo,bzw,ida);//把错误信息 传到前台
							return;
						  		}
					  		}
						}
						
						
						String mm="";
						if(statusInfo.size()==1){
							mm=statusInfo.get(0);
						}
						if(mm==null||statusInfo.size()==0){
							try {
								System.out.println("有附件上传");
								dao1.insertToDBZG(file,ls);
								String msg2=""; 
								//msg2 = beana.updatePdTestBewritezdxx(formBean);
								ZGShenHeDao dao2 = new ZGShenHeDao();
								//dao1.insertToDB(file,ls);//把数据表存到数据库
								int rs = dao2.TJSHSave(loginName, wcsm,zhssdl, beginTime1, zgfzr,id1,"",file.getName());
								/*if("修改站点信息成功!".equals(msg2)){
									msg="派单成功！";
								log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"测试描述修改");
								}*/
								
								if(rs==1){
									msg="保存成功！";
								}else{
									msg="保存失败！";
								}
							statusInfo.add(msg);
							} catch (Exception e) {
								statusInfo.add("内部错误，请联系管理员！");
								sendStatusInfo(request,response,statusInfo,bzw,ida);
								e.printStackTrace();
								return;
							}
							statusInfo.add("文件上传成功！");
						}	
						
						
						
						
						
					}
					
					
				}else{
					//如没有上传文件
					System.out.println("没有上传文件");
					String mm="";
					if(statusInfo.size()==1){
						mm=statusInfo.get(0);
					}
					if(mm==null||statusInfo.size()==0){
						try {
							//dao1.insertToDBZG(file,ls);
							String msg2=""; 
							//msg2 = beana.updatePdTestBewritezdxx(formBean);
							ZGShenHeDao dao2 = new ZGShenHeDao();
							//dao1.insertToDB(file,ls);//把数据表存到数据库
							int rs = dao2.TJSHSave(loginName, wcsm,zhssdl, beginTime1, zgfzr,id1,"",file.getName());
							/*if("修改站点信息成功!".equals(msg2)){
								msg="派单成功！";
							log.write(msg2, formBean.getWjid(), request.getRemoteAddr(),"测试描述修改");
							}*/
							
							if(rs==1){
								msg="保存成功！";
							}else{
								msg="保存失败！";
							}
						statusInfo.add(msg);
						} catch (Exception e) {
							statusInfo.add("内部错误，请联系管理员！");
							sendStatusInfo(request,response,statusInfo,bzw,ida);
							e.printStackTrace();
							return;
						}
						statusInfo.add("文件上传成功！");
					}	
					
					
					
					
					
					
				}
					
					
				
				}
			}
			sendStatusInfo(request,response,statusInfo,bzw,ida);
		}else{//判断文件是否可以上传
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖） 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
			out.println(result);
			out.flush();
			out.close();
		}}
	/**
	 * 将执行信息发送至当前页面
	 * @param request
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo,String bzw,String ida){
	
		try {
			if("1".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/pdtestbewritexx.jsp").forward(request, response);
				
			}else if("2".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/showzg.jsp").forward(request, response);
				
				
			}else if("zg".equals(bzw)){
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/jzcbnewfunction/ZGXX.jsp?id="+ida+"").forward(request, response);
				
				
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
	 * 上传，数据
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
	public Vector getContentbiaozhi(String filename){
		try{
			Sheet sheet=null;
			 Workbook book = Workbook.getWorkbook(new File(filename));
			 int counts = book.getNumberOfSheets();
			 System.out.println("测试获取book里面的sheet的个数"+counts);	
			 Vector content = new Vector();
			 if(counts==3){
			   sheet = book.getSheet(2);//从零开始的   2代表第三个sheet   判断是不是11
				 for (int i = 0; i < sheet.getRows(); i++) {
		                Vector rows = new Vector();
		                boolean isNull = true;
		                for (int j = 0; j < sheet.getColumns(); j++) {
		                    Cell cell = sheet.getCell(0, 0);
		                    String result = cell.getContents();//获取第一个单元格的内容
		                    System.out.println("判断模板值："+result);
		                    rows.add(result);
		                    if (result != null && !result.equals("")) {
		                        isNull = false;
		                    }
		                }
		                if (!isNull) {
		                    content.add(rows);//如果不为空  存到容器里  返回
		                }else{
		                	System.out.println("行空 了  结束了");
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
