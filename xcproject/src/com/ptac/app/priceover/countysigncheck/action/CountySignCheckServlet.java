package com.ptac.app.priceover.countysigncheck.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.mobi.common.Account;
import com.ptac.app.electricmanageUtil.Format;
import com.ptac.app.priceover.countysigncheck.bean.ChaXunBean;
import com.ptac.app.priceover.countysigncheck.bean.CheckBean;
import com.ptac.app.priceover.countysigncheck.dao.CountySignCheckDao;
import com.ptac.app.priceover.countysigncheck.dao.CountySignCheckDaoImpl;
import com.ptac.app.priceover.countysigncheck.dao.UpLoadUtil;

public class CountySignCheckServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();//根路径
		CountySignCheckDao dao = new CountySignCheckDaoImpl();
		UpLoadUtil dao1 = new UpLoadUtil();
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if("sign".equals(action)){
			Account account = (Account)session.getAttribute("account");
			String loginid = account.getAccountId();
			boolean flag = dao.selectNoSign(loginid);//是否有未签收单子
			if(flag){
				response.sendRedirect(path + "/web/appJSP/priceover/countysigncheck/sign.jsp");
			}else{
				response.sendRedirect(path + "/web/appJSP/priceover/countysigncheck/chaxun.jsp");
			}
		}else if("signed".equals(action)){
			String loginid = request.getParameter("loginid");
			String loginName = request.getParameter("loginName");
			boolean flg = dao.sign(loginid, loginName);//签收
			if(flg){//更新成功
				response.sendRedirect(path + "/web/appJSP/priceover/countysigncheck/chaxun.jsp");
			}else{
				String  url = path + "/web/appJSP/priceover/countysigncheck/sign.jsp" ;
				 session.setAttribute("url", url);
			     session.setAttribute("msg", "签收失败,请重试!");
			     response.sendRedirect(path + "/web/msg.jsp");
			}	
		}else if("chaxun".equals(action) || "daochu".equals(action)){
			String loginid = request.getParameter("accountid");
			String shi = request.getParameter("shi");
			String xian = request.getParameter("xian");
			String zdsx = request.getParameter("jzproperty");
			String sjzt = request.getParameter("sjzt");//状态(区县)
			String bzyf = request.getParameter("beginyue");//报账月份
			String bl1 = request.getParameter("bl1");
			String bl2 = request.getParameter("bl2");
			String zdname = request.getParameter("zdname");
			String zdid = request.getParameter("zdid");
			String gdfs = request.getParameter("gdfsa");
			String state = request.getParameter("state");//市审核
			StringBuffer wherestr = new StringBuffer();
			if(!"0".equals(shi) && null != shi){
				wherestr.append(" AND PE.SHI='").append(shi).append("'");
			}
			if(!"0".equals(xian) && null != xian){
				wherestr.append(" AND PE.XIAN='").append(xian).append("'");
			}
			if(!"0".equals(zdsx) && null != zdsx){
				wherestr.append(" AND PE.PROPERTY='").append(zdsx).append("'");
			}
			if(!"".equals(sjzt)&& null != sjzt){
				wherestr.append(" AND PE.COUNTYCHECK = '").append(sjzt).append("'");
			}
			if(!"".equals(bzyf)&& null != bzyf){
				wherestr.append(" AND TO_CHAR(PE.ACCOUNTMONTH, 'YYYY-MM') = '").append(bzyf).append("'");
			}
			if(!"".equals(bl1)&& null != bl1){
				wherestr.append(" AND PE.PLD >=").append(bl1);
			}
			if(!"".equals(bl2)&& null != bl2){
				wherestr.append(" AND PE.PLD <=").append(bl2);
			}
			if(!"".equals(zdname) && null != zdname){
				wherestr.append(" AND PE.JZNAME LIKE '%").append(zdname).append("%'");
			}
			if(!"".equals(zdid) && null != zdid){
				wherestr.append(" AND PE.ZDID='").append(zdid).append("'");
			}
			if(!"0".equals(gdfs) && null != gdfs){
				wherestr.append(" AND PE.GDFS='").append(gdfs).append("'");
			}
			if (state != null && !state.equals("") && !state.equals("-1")){
				wherestr.append(" AND PE.CITYAUDIT='" + state + "'");
			}
			
			List<ChaXunBean> list =	dao.chaxun(wherestr.toString(), loginid);
			request.setAttribute("list", list);
			request.setAttribute("numcolor", list.size());
			if("daochu".equals(action)){
				request.getRequestDispatcher("/web/appJSP/priceover/countysigncheck/daochu.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/web/appJSP/priceover/countysigncheck/chaxun.jsp").forward(request, response);
			}
		
		}else if("check".equals(action)){
			String pid = request.getParameter("pid");
			CheckBean bean = dao.check(pid);
			boolean f1 = dao1.CheckFj("YDHTFJ", "PRICEPROCEDURE", "ID", pid);
			boolean f2 = dao1.CheckFj("MSZYYJ", "PRICEPROCEDURE", "ID", pid);
			boolean f3 = dao1.CheckFj("YZJFDLMX", "PRICEPROCEDURE", "ID", pid);
			request.setAttribute("bean", bean);
			request.setAttribute("f1", f1);
			request.setAttribute("f2", f2);
			request.setAttribute("f3", f3);
			request.getRequestDispatcher("/web/appJSP/priceover/countysigncheck/check.jsp").forward(request, response);
		}else if("commit".equals(action)){
			List<String> statusInfo = new ArrayList<String>();//显示的错误信息
			String peid = request.getParameter("peid");
			Map<String,String> map = new HashMap<String, String>();
			File tempfile = new File(System.getProperty("java.io.tmpdir"));// 采用系统临时文件目录
			// 判断enctype属性是否为multipart/form-data  
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
			List<String> items = new ArrayList<String>();
			try {
			// Create a factory for disk-based file items  
			DiskFileItemFactory factory = new DiskFileItemFactory();  
			  
			// 当上传文件太大时，因为虚拟机能使用的内存是有限的，所以此时要通过临时文件来实现上传文件的保存  
			// 此方法是设置是否使用临时文件的临界值（单位：字节）
			// 如果文件大小大于SizeThreshold，则保存到临时目录  
			factory.setSizeThreshold(4096);  
			  
			// 与上一个结合使用，设置临时文件的路径（绝对路径）  
			factory.setRepository(tempfile);  
			  
			// Create a new file upload handler  
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(3*1024*1024);//设置单文件上传大小
			upload.setSizeMax(6*1024*1024);//设置总文件上传大小
			upload.setHeaderEncoding(request.getCharacterEncoding());
			// Parse the request  
				items = upload.parseRequest(request);
			}catch(FileUploadBase.FileSizeLimitExceededException e1) {
				 String  url = path + "/web/check/close.jsp" ;
				 session.setAttribute("url", url);
			     session.setAttribute("msg", "单个上传文件大小不能超过3M,请重试!");
			     response.sendRedirect(path + "/web/msg.jsp");
				return;
			}catch(FileUploadBase.SizeLimitExceededException e2){
				 String  url = path + "/web/check/close.jsp" ;
				 session.setAttribute("url", url);
			     session.setAttribute("msg", "上传文件总大小不能超过6M,请重试!");
			     response.sendRedirect(path + "/web/msg.jsp");
				return;
			}catch (FileUploadException e3) {
				e3.printStackTrace();
				statusInfo.add("解析请求内容时遇到了错误,请联系管理员!");
				sendStatusInfo(request, response, statusInfo,peid);// 把错误信息 传到前台
				return;
			}
			  
			Iterator iter = items.iterator();  
			CheckBean bean = new CheckBean();
			while (iter.hasNext()) {  
			    FileItem item = (FileItem) iter.next();  
			  
			    if (item.isFormField()) {  
			        //如果是普通表单字段  
			        String name = item.getFieldName();  
			        map.put(name, item.getString(request.getCharacterEncoding()).trim());
			    } else {  
			        //如果是文件字段  
			        String fieldName = item.getFieldName();  
			            String fileName = item.getName();  
						String[] aa= item.toString().split(",");
						int ss=aa[1].lastIndexOf("\\")+1;
						String s=aa[1].substring(ss,aa[1].length()); //截取上传文件名称
						//文件url
						String tempfile1=tempfile+"\\"+s;
						System.out.println(tempfile1);
					
						//----------------------------------------------------
						//如果有附件
						if(fileName!=null&&!fileName.equals("")&&!fileName.equals("null")&&fileName.length()>0&&fileName!=""&&fileName!=" "){
							Boolean gs=dao1.isAvailableToUpload(item);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
							if (gs){
								if("fileUp1".equals(fieldName)){
									statusInfo.add("上传失败，用电合同附件上传的文件后缀名必须为.xls");
								}else if("fileUp2".equals(fieldName)){
									statusInfo.add("上传失败，忙时增益依据上传的文件后缀名必须为.xls");
								}else if("fileUp3".equals(fieldName)){
									statusInfo.add("上传失败，业主尖峰电量明细上传的文件后缀名必须为.xls");
								}
								sendStatusInfo(request,response,statusInfo,peid);//把错误信息 传到前台
								return;
							}else{
								Vector biaozhi = this.getContentbiaozhi(tempfile1);// 判断是否是下载的我们的报表第三个sheet里第一个单元格内容是88
								if (biaozhi.isEmpty()) {
									if("fileUp1".equals(fieldName)){
										statusInfo.add("上传失败，因为您上传的用电合同附件模版不是在系统中下载的！请先下载再上传！！");
									}else if("fileUp2".equals(fieldName)){
										statusInfo.add("上传失败，因为您上传的忙时增益依据模版不是在系统中下载的！请先下载再上传！！");
									}else if("fileUp3".equals(fieldName)){
										statusInfo.add("上传失败，因为您上传的业主尖峰电量明细模版不是在系统中下载的！请先下载再上传！！");
									}
									sendStatusInfo(request, response, statusInfo,peid);// 把错误信息 传到前台
									return;
								} else {

									Object[] a = biaozhi.toArray();
									String ida = "";
									for (int i = 0; i < a.length; i++) { // 应该是重0开始循环判断
										Object[] b = ((Vector) a[i]).toArray();
										ida = b[0].toString().trim();
										if (!ida.equals("88")) {
											if("fileUp1".equals(fieldName)){
												statusInfo.add("上传失败，因为您上传的用电合同附件模版不是在系统中下载的！请先下载再上传！！");
											}else if("fileUp2".equals(fieldName)){
												statusInfo.add("上传失败，因为您上传的忙时增益依据模版不是在系统中下载的！请先下载再上传！！");
											}else if("fileUp3".equals(fieldName)){
												statusInfo.add("上传失败，因为您上传的业主尖峰电量明细模版不是在系统中下载的！请先下载再上传！！");
											}
											sendStatusInfo(request, response,statusInfo,peid);// 把错误信息 传到前台
											return;
										}
									}
								}

							try {
								if("fileUp1".equals(fieldName)){
									dao1.insertToDB("YDHTFJ", "PRICEPROCEDURE", "ID", peid, item);//把数据表存到数据库
								}else if("fileUp2".equals(fieldName)){
									dao1.insertToDB("MSZYYJ", "PRICEPROCEDURE", "ID", peid, item);//把数据表存到数据库
								}else if("fileUp3".equals(fieldName)){
									dao1.insertToDB("YZJFDLMX", "PRICEPROCEDURE", "ID", peid, item);//把数据表存到数据库
								}

							} catch (Exception e) {
								e.printStackTrace();
								statusInfo.add("内部错误，请联系管理员！");
								sendStatusInfo(request, response,statusInfo,peid);// 把错误信息 传到前台
								return;
							}
							if("fileUp1".equals(fieldName)){
								statusInfo.add("用电合同附件上传成功！");
							}else if("fileUp2".equals(fieldName)){
								statusInfo.add("忙时增益依据上传成功！");
							}else if("fileUp3".equals(fieldName)){
								statusInfo.add("业主尖峰电量明细上传成功！");
							}
					     }
					   } 
			    }  
			}  
			String accountid = map.get("accountid");
			String personnal = map.get("personnal");
			String yzydlx = map.get("yzydlx").trim();//业主用电类型
			String yzhdjcdj = map.get("yzhdjcdj").trim();//业主获点基础单价
			String hzprice = map.get("hzprice").trim();//核准单价
			String jfdlzb = map.get("jfdlzb").trim();//尖峰电量占比
			String pdlzb = map.get("pdlzb").trim();//平电量占比
			String zgdjcdj = map.get("zgdjcdj").trim();//直供电基础单价
			String gfdlzb = map.get("gfdlzb").trim();//高峰电量占比
			String gdlzb = map.get("gdlzb").trim();//谷电量占比
			String byqrl = map.get("byqrl").trim();//变压器容量
			String beilv = map.get("beilv").trim();//倍率
			String ydsx = map.get("ydsx").trim();//用电属性
			String xbsl = map.get("xbsl").trim();//线变损率
			String xbsdl = map.get("xbsdl").trim();//线变损电量
			String glfsfxs = map.get("glfsfxs").trim();//管理费上浮系数
			String mssfxs = map.get("mssfxs").trim();//忙时上浮系数
			peid = map.get("peid")==null?"":map.get("peid");
			bean.setPeid(peid);
			bean.setYzydlx(yzydlx);
			bean.setYzhdjcdj("".equals(yzhdjcdj)?"":Format.str4(yzhdjcdj));
			bean.setHzprice("".equals(hzprice)?"":Format.str4(hzprice));
			bean.setJfdlzb(jfdlzb);
			bean.setPdlzb(pdlzb);
			bean.setZgdjcdj("".equals(zgdjcdj)?"":Format.str4(zgdjcdj));
			bean.setGfdlzb(gfdlzb);
			bean.setGdlzb(gdlzb);
			bean.setByqrl("".equals(byqrl)?"":Format.str2(byqrl));
			bean.setBeilv("".equals(beilv)?"":Format.str2(beilv));
			bean.setYdsx(ydsx);
			bean.setXbsl(Format.str2(xbsl));
			bean.setXbsdl("".equals(xbsdl)?"":Format.str2(xbsdl));
			bean.setGlfsfxs(Format.str2(glfsfxs));
			bean.setMssfxs(Format.str2(mssfxs));
		
			String msg = dao.commit(bean, accountid, personnal);
			
			//if("单价核实信息提交成功!".equals(msg)){
//			String  url = path + "/web/check/close.jsp" ;
//			 session.setAttribute("url", url);
//		     session.setAttribute("msg", msg);
//		     response.sendRedirect(path + "/web/msg.jsp");
			//}else{
				statusInfo.add(msg);
				sendStatusInfo(request, response,statusInfo,peid);// 把信息 传到前台
			//}
		}
	
	}
	public Vector getContentbiaozhi(String filename) {
		try {
			Sheet sheet = null;
			Workbook book = Workbook.getWorkbook(new File(filename));
			int counts = book.getNumberOfSheets();
			System.out.println("测试获取book里面的sheet的个数" + counts);
			Vector content = new Vector();
			if (counts == 3) {
				sheet = book.getSheet(2);// 从零开始的 2代表第三个sheet 判断是不是11
				for (int i = 0; i < sheet.getRows(); i++) {
					Vector rows = new Vector();
					boolean isNull = true;
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(0, 0);
						String result = cell.getContents();// 获取第一个单元格的内容
						System.out.println("判断模板值：" + result);
						rows.add(result);
						if (result != null && !result.equals("")) {
							isNull = false;
						}
					}
					if (!isNull) {
						content.add(rows);// 如果不为空 存到容器里 返回
					} else {
						System.out.println("行空 了  结束了");
						break;

					}
				}
				return content;
			} else {
				return content;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response, List<?> statusInfo,String peid) throws ServletException, IOException {
		CountySignCheckDao dao = new CountySignCheckDaoImpl();
		UpLoadUtil dao1 = new UpLoadUtil();
		request.setAttribute("statusInfo", statusInfo);
		CheckBean b = dao.check(peid);
		boolean f1 = dao1.CheckFj("YDHTFJ", "PRICEPROCEDURE", "ID", peid);
		boolean f2 = dao1.CheckFj("MSZYYJ", "PRICEPROCEDURE", "ID", peid);
		boolean f3 = dao1.CheckFj("YZJFDLMX", "PRICEPROCEDURE", "ID", peid);
		request.setAttribute("bean", b);
		request.setAttribute("f1", f1);
		request.setAttribute("f2", f2);
		request.setAttribute("f3", f3);
		request.getRequestDispatcher("/web/appJSP/priceover/countysigncheck/check.jsp").forward(request, response);

	}

}
