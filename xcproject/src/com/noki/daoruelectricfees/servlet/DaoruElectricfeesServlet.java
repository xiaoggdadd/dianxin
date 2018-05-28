package com.noki.daoruelectricfees.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.noki.daoruelectricfees.javabean.DaoruElectricfees;
import com.noki.electricfees.servlet.ReadFile;
import com.noki.electricfees.servlet.ReadFileFactory;
import com.noki.jizhan.daoru.CountForm;
import com.noki.mobi.common.Account;
import com.noki.util.Path;
import com.noki.util.WriteExcle;
import com.ptac.app.util.FileUploadUtil;
import com.ptac.app.util.ImportExcel;

public class DaoruElectricfeesServlet extends HttpServlet {

	 public DaoruElectricfeesServlet() {
	  }
	  private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 public void init() throws ServletException {}

	 private String msg;
	 private String url;
	 private String sendUrl;
	 private String filepath;

	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws
	     ServletException, IOException {
	   response.setContentType(CONTENT_TYPE);
	   HttpSession session = request.getSession();
	   Account account = (Account)session.getAttribute("account");
	   String accountname=account.getAccountName();
	   String loginId=account.getAccountId();
	   String path = request.getContextPath();
	   url = path + "/web/msgdr.jsp";
	   msg = "电量电费批量导入失败！";
	   String action =(String) request.getAttribute("action");
	   if(null==action||"".equals(action)){
		   action=request.getParameter("action");
	   }
	   String filename =(String) request.getAttribute("filename");
	   System.out.println(action);//////////////
	   if(action.equals("indata")){
	     try {
	       Path pa=new Path();
	       Path ppath = new Path();
	    ppath.servletInitialize(getServletConfig().getServletContext());
	    String dir1 = ppath.getPhysicalPath("/indata/", 0); // 传参数
	    ReadFile temp = new ReadFileFactory().getReadFile(filename);
	      //Vector head=temp.getColumns(filename);
	      Vector content = temp.getContent(filename);
	      DaoruElectricfees sellin = new DaoruElectricfees();
	      //InsertDegreeFee sellin = new InsertDegreeFee();
	  	  //主键生成
	  	  CountForm cform=new CountForm();
	      //Vector wrong = sellin.getWrong(content,cform,accountname,loginId);
	  	  long da=new Date().getTime();
	  	  System.out.println("-1-"+new Date().getTime());
	      Vector wrong = sellin.getWrong(content,cform,accountname,loginId); 
	      long da1=new Date().getTime();
	      System.out.println("-2-"+da+"--"+da1);
	       da1=da1-da;
	       System.out.println("-3-"+da1+"--");
	      sellin.closeDb();
	      //System.out.println("000000:"+wrong.size());
	      if (!wrong.isEmpty()) {
	          msg = "共 " + cform.getZg() + "  条。成功导入 " + cform.getCg() +
	                " 条！有 " + cform.getBcg() + "  条数据未导入！";
	          WriteExcle wr = new WriteExcle();
	          String dir2 = ppath.getPhysicalPath("/wrongdata/", 0); // 传参数
	          wr.Write(wrong, account.getAccountName() + "电费单导入不成功的数据.xls", "电费单导入不成功的数据",
	                   "电费单导入不成功数据", 32, dir2);
	      } else {
	          msg = "全部导入成功！共导入 " + cform.getCg() + " 条！";
	      }

	    }catch (Exception e) {
	      e.printStackTrace();
	    }
	    session.setAttribute("msg", msg);
	    session.setAttribute("url", path+"/web/electricfees/newelectricFeesInput.jsp");
	    session.setAttribute("wfile", path + "/wrongdata/" + account.getAccountName() +"电费单导入不成功的数据.xls");
	   
	    
	    response.sendRedirect(url);
	   }
	   
	 //ElectricFees
	if(action.equals("downloadTemp")){
       	String templetname = "电量电费批量导入模板2018.xls";
     	String filepath = getServletContext().getRealPath("/exceltemplet/" + templetname);
     	
     	response.setContentType("text/html;charset=UTF-8");
     	response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(templetname, "UTF-8"));
     	 try{
          	IOUtils.copy(new FileInputStream(new File(filepath)), response.getOutputStream());  
          }catch (FileNotFoundException e) {
  			System.out.println("模板文件：" + filepath + "未找到");
  		}
     }else if(action.equals("import")){ //导入
     	url = path + "/web/sdttWeb/baozhang/importExcel.jsp";
     	ArrayList<String> errMsgList = new ArrayList<String>();
     	DaoruElectricfees def = new DaoruElectricfees();
     	try {
     		FileUploadUtil uploadUtil = new FileUploadUtil();
			File file = uploadUtil.upload(request, response);
			if(file !=null && file.exists()){
				long start = System.currentTimeMillis();
				//读取Excel文件
				ImportExcel ie = new ImportExcel();
				ArrayList dataList  = ie.readExcel(file);
				//校验合法性 
				int successNum = 0;
				int errorNum = 0;
				def.setDaoruRen(account.getAccountId());//导入人员
				for (Iterator iterator = dataList.iterator(); iterator
						.hasNext();) {
					ArrayList<Object>  data = (ArrayList<Object>) iterator.next();
					String dbbm = String.valueOf(data.get(0));
					if(dbbm.isEmpty()){
						iterator.remove();
						continue;
					}
					String errMsg = def.checkElectricfees(data);
					if(!errMsg.isEmpty()){
						errMsgList.add(errMsg);
						errorNum++;
						continue;
					}
					successNum++;
				}
				msg  = "共 " + dataList.size() + "  条。成功导入 " + successNum +
		                " 条！有 " + errorNum + "  条数据未导入！";
				long end = System.currentTimeMillis();
				
				System.out.println(String.format("电费数据导入完毕, 耗时 %s秒", (end-start)/1000));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("报账记录批量导入-本地文件上传到服务器失败");
		}finally{
			def.closeDb();
		}
     	session.setAttribute("errMsg", errMsgList);
     	session.setAttribute("msg", msg);
     	response.sendRedirect(url);
     	
     }
	 //==============================================================================

	}
	 public void doGet(HttpServletRequest request,
	                   HttpServletResponse response) throws ServletException,
	     IOException {
	   doPost(request, response);
	 }

}
