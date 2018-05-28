package com.noki.zdqxkz.servlet;

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
import com.noki.newfunction.dao.PdTestBewrite;
import com.noki.newfunction.dao.ShiSignDao;
import com.noki.newfunction.dao.UploadDao;
import com.noki.newfunction.dao.UploadDaoQxfj;
import com.noki.newfunction.dao.ZGShenHeDao;
import com.noki.newfunction.javabean.Zdinfo;
import com.noki.util.Path;
import com.noki.zdqxkz.dao.Zdqxcxxg;
import com.noki.zdqxkz.javabean.Zdqxkz;
import com.noki.zdqxkz.util.ZdqxkzUtil;

public class ZdqxcxxgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");// 设置输入编码格式。
		response.setContentType("text/html;charsetType=utf-8");// 设置输出编码格式。

		String path = request.getContextPath();

		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String loginName = account.getAccountName();
		String loginId = account.getAccountId();
		
	//	System.out.println("dfdfddddddddddd" + idd);
		DbLog log = new DbLog();
		Zdqxcxxg bean = new Zdqxcxxg();

		String action = request.getParameter("action");
		//String zdid = request.getParameter("zdid");
		String id = request.getParameter("id");
		if (action.equals("deleteqx")) {
			String url = path + "/web/zdqxkz/zdqxcxxg.jsp";
			String msg = "";
			String qxid = request.getParameter("zdid");
			msg = bean.delZdqxcxxg(qxid);
			log.write(msg, qxid, request.getRemoteAddr(), "删除权限控制站点信息");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");

		}else if(action.equals("zdqxsjsh")){
			String ggbz = request.getParameter("ggbz");
			String idshisj=request.getParameter("id");
			String qsdb=request.getParameter("qsdb");
			System.out.println("市级审核保存："+idshisj+"全省定标："+qsdb);
			String url = path + "/web/check/close.jsp";
			
			String flgg = "";//分类更改字段
			flgg = bean.queryFlgg(idshisj);
			ZdqxkzUtil util = new ZdqxkzUtil();
			if("2".equals(ggbz)){//改了省标
				flgg = 	util.getBftg(flgg, "9");
			}
			String msg = "";
			msg = bean.zdqxssh(idshisj,qsdb,flgg,ggbz);
			
			log.write(msg, idshisj, request.getRemoteAddr(), "省、市审核保存省定标");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			session.setAttribute("idshisj", idshisj);
			response.sendRedirect(path + "/web/msg.jsp");
		}else if (action.equals("modifyzd")) {
			String msg = "";
			List<String> statusInfo = new ArrayList<String>();// 显示的错误信息
			
			String zggdid = request.getParameter("zggdid");//整改工单id
			String fzbzw = request.getParameter("fzbzw");//赋值标志位 1:赋值  0:不赋值 
			boolean zggdbz = true;
			if("1".equals(fzbzw)){//区县申请更新整改工单直流,交流,额定,生产
				Zdqxcxxg zg=new Zdqxcxxg();
				String zlfh = request.getParameter("zlfhnow");
				String jlfh = request.getParameter("jlfhnow");
				String edhdl = request.getParameter("edhdlnow");
				String scb = request.getParameter("qsdb");
				zggdbz = zg.setZggd(zggdid, zlfh, jlfh, edhdl, scb);
			}
			
			if(zggdbz){
				
			String url = path + "/web/zdqxkz/modifyzdgjxxxgxx.jsp?id=" + id;
			String lg=request.getParameter("lg");
			String confirm = request.getParameter("confirm");
			String ida = request.getParameter("idcz");
			String qid = request.getParameter("id");
			List<Zdinfo> ls = null;
			String filename = "";
			UploadDao dao = new UploadDao(account, ida, "");
			if (null == confirm) {// 文件开始上传
				
				String yflxlast = request.getParameter("yflxlast");//用房类型(老)1
				String yflxnow = request.getParameter("yflxnow");//用房类型(新)
				String zdsxlast = request.getParameter("zdsxlast");//站点属性(老)2
				String zdsxnow = request.getParameter("zdsxnow");//站点属性(新)
				String zdlxlast = request.getParameter("zdlxlast");//站点类型(老)3
				String zdlxnow = request.getParameter("zdlxnow");//站点类型(新)
				
				String gxxxlast = request.getParameter("gxxxlast");//共享信息(老)4
				String gxxxnow = request.getParameter("gxxxnow");//共享信息(新)
				String gdfslast = request.getParameter("gdfslast");//供电方式(老)5
				String gdfsnow = request.getParameter("gdfsnow");//供电方式(新)
				String zgdlast = request.getParameter("zgdlast");//直供电(老)6
				String zgdnow = request.getParameter("zgdnow");//直供电(新)
				
				String zdarealast = request.getParameter("zdarealast");//站点面积(老)7
				String zdareanow = request.getParameter("zdareanow");//站点面积(新)
				String qyztlast = request.getParameter("qyztlast");//站点启用状态(老)8
				String qyztnow = request.getParameter("qyztnow");//站点启用状态(新)
				String oldqsdb = request.getParameter("oldqsdb");//省定标(不含空调)(老)9
				String qsdb = request.getParameter("qsdb");//省定标(不含空调)(新)
				
				String g2last = request.getParameter("g2last");//2G(老)10
				String g2now = request.getParameter("g2now");//2G(新)
				String g3last = request.getParameter("g3last");//3G(老)11
				String g3now = request.getParameter("g3now");//3G(新)
				String zplast = request.getParameter("zplast");//载频(老)12
				String zpnow = request.getParameter("zpnow");//载频(新)
				
				String zslast = request.getParameter("zslast");//载扇(老)13
				String zsnow = request.getParameter("zsnow");//载扇(新)
				String changjialast = request.getParameter("changjialast");//厂家(老)14
				String changjianow = request.getParameter("changjianow");//厂家(新)
				String jztypelast = request.getParameter("jztypelast");//基站类型2(老)15
				String jztypenow = request.getParameter("jztypenow");//基站类型2(新)
				
				String bbulast = request.getParameter("bbulast");//BBU数量(老)16
				String bbunow = request.getParameter("bbunow");//BBU数量(新)
				String rrulast = request.getParameter("rrulast");//RRU数量(老)17
				String rrunow = request.getParameter("rrunow");//RRU数量(新)
				String shebeilast = request.getParameter("shebeilast");//设备编码(老)18
				String shebeinow = request.getParameter("shebeinow");//设备编码(新)
				
				String ydshebeilast = request.getParameter("ydshebeilast");//他网共享数量(老)19
				String ydshebeinow = request.getParameter("ydshebeinow");//他网共享数量(新)
				String gxgwsllast = request.getParameter("gxgwsllast");//共享固网数量(老)20
				String gxgwslnow = request.getParameter("gxgwslnow");//共享固网数量(新)
				
				
				Zdqxkz formBean = new Zdqxkz();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String entrytime = df.format(new Date());

				formBean.setId(request.getParameter("id"));
				formBean.setZdid(request.getParameter("zdid"));
				String zt1 = request.getParameter("zt");// 状态
				String zt2 = "";// 状态
				if (zt1 == "1" || "1".equals(zt1)) {
					zt2 = "增加";
				} else if (zt1 == "2" || "2".equals(zt1)) {
					zt2 = "修改";
				} else if (zt1 == "3" || "3".equals(zt1)) {
					zt2 = "减少";
				}
				formBean.setBiaoti(request.getParameter("diquzdid") + zt2);

				formBean.setNewbgsign(request.getParameter("sfbgnow"));
				formBean.setNewdianfei(request.getParameter("dianjianow"));
				formBean.setNewedhdl(request.getParameter("edhdlnow"));
				formBean.setNewzlfh(request.getParameter("zlfhnow"));
				formBean.setNewjlfh(request.getParameter("jlfhnow"));
				formBean.setNewproperty(zdsxnow);
				formBean.setNewstationtype(zdlxnow);
				formBean.setNewyflx(yflxnow);
				formBean.setNewgxxx(gxxxnow);
				formBean.setNewgdfs(gdfsnow);
				formBean.setNewzgd(zgdnow);
				formBean.setNewarea(zdareanow);
				formBean.setNewqyzt(qyztnow);
				formBean.setNewg2(g2now);
				formBean.setNewg3(g3now);
				formBean.setNewzpsl(zpnow);
				formBean.setNewzssl(zsnow);
				formBean.setNewchangjia(changjianow);
				formBean.setNewjzlx(jztypenow);
				formBean.setNewshebei(shebeinow);
				formBean.setNewbbu(bbunow);
				formBean.setNewrru(rrunow);
				formBean.setNewydshebei(ydshebeinow);
				formBean.setNewgxgwsl(gxgwslnow);
				formBean.setNewqsdbdl(qsdb);
				formBean.setNewdfzflx(request.getParameter("dfzflxnow"));

				formBean.setQxpdbz(request.getParameter("bzw"));

				formBean.setQxtjr(loginName);
				formBean.setQxtjbz("1");
				formBean.setQxtjtime(entrytime);
				
				String dsbzw="0";//电表读数修改标志位  1 为修改  0为未修改
				String dbds = request.getParameter("dbds").trim();
				
				if(dbds==null||dbds==""||"".equals(dbds)){
					dsbzw="0";
				}else if(Double.parseDouble(dbds)>=0){
					dsbzw="1";
				}
				formBean.setZdbzw(request.getParameter("zdbzw"));//站点核实标杆标志位
				//System.out.println("电表读数"+dbds+"空格"+dbds.trim()+" 标志位"+dsbzw);
				formBean.setDbds(dbds);//电表读数
				formBean.setDsbzw(dsbzw);//电表读数修改标志位
				 String zdbzw=request.getParameter("zdbzw");//站点核标标志位 1为是 0为否
				    String djbzw=request.getParameter("bzw");//权限判断标志位 这里要取的为 3、4  3为单纯降标 4为升标

					List<String> oldlist = new ArrayList<String>();
					oldlist.add(yflxlast);oldlist.add(zdsxlast);
					oldlist.add(zdlxlast);oldlist.add(gxxxlast);
					oldlist.add(gdfslast);oldlist.add(zgdlast);
					oldlist.add(zdarealast);oldlist.add(qyztlast);
					oldlist.add(oldqsdb);oldlist.add(g2last);
					oldlist.add(g3last);oldlist.add(zplast);
					oldlist.add(zslast);oldlist.add(changjialast);
					oldlist.add(jztypelast);oldlist.add(bbulast);
					oldlist.add(rrulast);oldlist.add(shebeilast);
					oldlist.add(ydshebeilast);oldlist.add(gxgwsllast);
					List<String> newlist = new ArrayList<String>();
					newlist.add(yflxnow);newlist.add(zdsxnow);
					newlist.add(zdlxnow);newlist.add(gxxxnow);
					newlist.add(gdfsnow);newlist.add(zgdnow);
					newlist.add(zdareanow);newlist.add(qyztnow);
					newlist.add(qsdb);newlist.add(g2now);
					newlist.add(g3now);newlist.add(zpnow);
					newlist.add(zsnow);newlist.add(changjianow);
					newlist.add(jztypenow);newlist.add(bbunow);
					newlist.add(rrunow);newlist.add(shebeinow);
					newlist.add(ydshebeinow);newlist.add(gxgwslnow);
					String str=new ZdqxkzUtil().getFlggZd(oldlist, newlist);//分类更改字段
					formBean.setFlgg(str);
				    
				Zdqxcxxg beana = new Zdqxcxxg();

				File tempfile = new File(System.getProperty("java.io.tmpdir"));// 采用系统临时文件目录
				// 上传报表时
				// 存报表的临时路径
				// 最后删除
					FileItem item = null;
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(4096); // 设置文件大小的
					// 设置是否将上传文件已临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。对应的getSizeThreshold()
					// 方法用来获取此临界值。
					factory.setRepository(tempfile);// 文件以临时文件形式保存在磁盘上的存放目录
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setSizeMax(6291456);// 6291456文件上传大小限制为6M
					List<String> fileItems = new ArrayList<String>();
					String c = "", s = "";
					String nn="",lj="";
					try {
						fileItems = upload.parseRequest(request);
					} catch (FileUploadException e) {
						statusInfo.add("上传文件超出大小限制（6M）");
						sendStatusInfo(request, response, statusInfo, "modifyzd"); // 把错误信息传到前台
						return;
					}
					Iterator iter = fileItems.iterator();
					  while(iter.hasNext()) {
				         FileItem  file = (FileItem)iter.next();
					//for (Object o : fileItems) {
						//FileItem file = (FileItem) o;
						if (file.isFormField()) {
							 String name1 = file.getFieldName(); 
				        	 if("reasonanalyse".equals(name1)){
				        		  nn = file.getString("utf-8");
				        	 }else if("reasonanalyseyj".equals(name1)){
				        		  lj = file.getString("utf-8");
				        	 }
				        	formBean.setXgsm(nn);
							formBean.setXgyj(lj);
						}else{
							String[] aa = file.toString().split(",");
							int ss = aa[1].lastIndexOf("\\") + 1;
							s = aa[1].substring(ss, aa[1].length()); // 截取上传文件名称
							String tempfile1 = tempfile + "\\" + s;
							String name = file.getName();
							System.out.println("name------------ss"+name);
							UploadDao dao1 = new UploadDao(account, id, "");
					// 如果有附件
							if (name != null && !name.equals("")&& !name.equals("null") && name.length() > 0 && name != "") {
								Boolean gs = dao1.isAvailableToUpload1(statusInfo, file);//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
								if (gs) {
										statusInfo.add("保存失败，上传的文件后缀名必须为.xls");
										sendStatusInfo(request, response, statusInfo,"modifyzd");// 把错误信息 传到前台
										return;
								} else {
									Vector biaozhi = this.getContentbiaozhi(tempfile1);// 判断是否是下载的我们的报表第三个sheet里第一个单元格内容是12
									if (biaozhi.isEmpty()) {
										statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
										sendStatusInfo(request, response, statusInfo,"modifyzd");// 把错误信息 传到前台
										return;
									} else {
										Object[] a = biaozhi.toArray();
										String idaa = "";
										for (int i = 0; i < a.length; i++) { // 应该是重0开始循环判断
											Object[] b = ((Vector) a[i]).toArray();
											idaa = b[0].toString().trim();
											if (!idaa.equals("55")) {
												statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
												sendStatusInfo(request, response,statusInfo, "modifyzd");// 把错误信息 传到前台
												return;
											}
										}
									}
									String mm = "";
									if (statusInfo.size() == 1) {
										mm = statusInfo.get(0);
									}
									if (mm == null || statusInfo.size() == 0) {
										try {
											String msg2 = "";
											Zdqxkz form = new Zdqxkz();
											msg2 =beana.modifyZdqxcxxg1(formBean,"",name);
											System.out.println("--------------id-------"+qid);
											if ("修改权限控制站点信息成功！".equals(msg2)) {
												if (name != null && name != ""&& !"".equals(name)) {
													// file.write(new File(name));
												}
												msg = "修改权限控制站点信息成功！";
												System.out.println("有附件上传");
												dao1.insertToDBGL(file, qid);
												
												log.write(msg, formBean.getZdid(),request.getRemoteAddr(),"修改权限控制站点信息");
												if(formBean.getQxpdbz().equals("3")){
												    //如果是降标的话 那么市审核自动通过 省审核自动通过 并且要更改交流负荷和直流负荷、生产标(站点的)  生产标(scb)
													    Zdqxcxxg zdzg=new Zdqxcxxg();
													   String msga= zdzg.updatezdjb(formBean,lg);
													 //  if(msga.equals("自动降标成功！")){
														  // statusInfo.add("自动降标成功！");
													  // }else{
														 //  statusInfo.add("自动降标失败！");
													 //  }
														}
												//单纯升标阶段 不管交直流负荷 只要是单纯的升高标杆、是核标站点即进行以下流程
												//能耗基础值占比：(能耗基础值-分公司定标)/(分公司定标)
												//基础实际值占比：(基础实际值-分公司定标)/(分公司定标)
												//建议生产标占比：(建议生产标-分公司定标)/(分公司定标) 
												//交流电量占比：(交流电量-分公司定标)/(分公司定标)
												//直流电量占比：(直流电量-分公司定标)/(分公司定标)
												System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
												if(formBean.getQxpdbz().equals("4")){
													System.out.println("i am here !!!");
													//各项占比关系
													Zdqxcxxg zg=new Zdqxcxxg();
													int x=0;
													x=zg.zdbgdj(formBean,lg);
												//	if(x>0){
													//	  statusInfo.add("自动判断等级成功！");
												///	}else{
														//  statusInfo.add("自动判断等级失败！");
												//	}
													
												}
											
											} else {
												msg = "修改权限控制站点信息失败！";
											}
											statusInfo.add(msg);
										} catch (Exception e) {
											statusInfo.add("内部错误，请联系管理员！");
											sendStatusInfo(request, response,statusInfo, "modifyzd");
											e.printStackTrace();
											return;
										}
										statusInfo.add("文件上传成功！");
									}
								}
							} else {
					// 如没有上传文件
								System.out.println("没有上传文件");
								String mm = "";
								if (statusInfo.size() == 1) {
									mm = statusInfo.get(0);
								}
								if (mm == null || statusInfo.size() == 0) {
									try {
										String msg2 = "";
										msg2 = beana.modifyZdqxcxxg1(formBean, "", name);
										if ("修改权限控制站点信息成功！".equals(msg2)) {
											if (name != null && name != "" && !"".equals(name)) {
												// file.write(new File(path1+"/"+name));
											}
											//dao1.insertToDBGL(file, qid);
											dao1.UpdateFjnr(qid);
											msg = "修改权限控制站点信息成功！";
										
											log.write(msg, formBean.getZdid(), request.getRemoteAddr(), "修改权限控制站点信息");
											//增加权限控制站点信息成功后 要进行判断降标条件
											if(formBean.getQxpdbz().equals("3")){
											    //如果是降标的话 那么市审核自动通过 省审核自动通过 并且要更改交流负荷和直流负荷、生产标(站点的)  生产标(scb)
											    Zdqxcxxg zg=new Zdqxcxxg();
											    Zdqxkz k=new Zdqxkz();
											  k=zg.maxqskzid(formBean.getZdid());
											   String msga= zg.updatezdjb(k,lg);
											 //  if(msga.equals("自动降标成功！")){
												 //  statusInfo.add("自动降标成功！");
											 //  }else{
												 //  statusInfo.add("自动降标失败！");
											  // }
												}
											//单纯升标阶段 不管交直流负荷 只要是单纯的升高标杆、是核标站点即进行以下流程
											//能耗基础值占比：(能耗基础值-分公司定标)/(分公司定标)
											//基础实际值占比：(基础实际值-分公司定标)/(分公司定标)
											//建议生产标占比：(建议生产标-分公司定标)/(分公司定标) 
											//交流电量占比：(交流电量-分公司定标)/(分公司定标)
											//直流电量占比：(直流电量-分公司定标)/(分公司定标)
											System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
											if(formBean.getQxpdbz().equals("4")){
												System.out.println("i am here !!!");
												//各项占比关系
												Zdqxcxxg zg=new Zdqxcxxg();
												int x=0;
												x=zg.zdbgdj(formBean,lg);
												//if(x>0){
													//  statusInfo.add("自动判断等级成功！");
												//}else{
													//  statusInfo.add("自动判断等级失败！");
											//	}
												
											}
										} else {
											msg = "修改权限控制站点信息失败！";
										}
										statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("内部错误，请联系管理员！");
										sendStatusInfo(request, response, statusInfo,"modifyzd");
										e.printStackTrace();
										return;
									}
									//statusInfo.add("文件上传成功！");
								}
							}
						}
					}
					  
				sendStatusInfo(request, response, statusInfo, "modifyzd");
			} else {// 判断文件是否可以上传
				PrintWriter out = response.getWriter();
				String result = "0";
				if (dao.fileIsUploaded())
					result = "1";
				out.println(result);
				out.flush();
				out.close();
			}
		}else{
			statusInfo.add("更新整改工单直流,交流,额定,生产标失败,请联系管理员!");
			sendStatusInfo(request, response, statusInfo, "modifyzd");
		}
			
		} else if (action.equals("upload")) {
			String bz = "zg";
			String msg = "";
			List<String> statusInfo = new ArrayList<String>();// 显示的错误信息
				
			String zggdid = request.getParameter("zggdid");//整改工单id
			String fzbzw = request.getParameter("fzbzw");//赋值标志位 1:赋值  0:不赋值 
			boolean zggdbz = true;
			if("1".equals(fzbzw)){//区县申请更新整改工单直流,交流,额定,生产
				Zdqxcxxg zg=new Zdqxcxxg();
				String zlfh = request.getParameter("zlfhnow");
				String jlfh = request.getParameter("jlfhnow");
				String edhdl = request.getParameter("edhdlnow");
				String scb = request.getParameter("qsdb");
				zggdbz = zg.setZggd(zggdid, zlfh, jlfh, edhdl, scb);
			}
			if(zggdbz){//区县申请更新整改工单直流,交流,额定,生产成功   或  无需更新
				
				String yflxlast = request.getParameter("yflxlast");//用房类型(老)1
				String yflxnow = request.getParameter("yflxnow");//用房类型(新)
				String zdsxlast = request.getParameter("zdsxlast");//站点属性(老)2
				String zdsxnow = request.getParameter("zdsxnow");//站点属性(新)
				String zdlxlast = request.getParameter("zdlxlast");//站点类型(老)3
				String zdlxnow = request.getParameter("zdlxnow");//站点类型(新)
				
				String gxxxlast = request.getParameter("gxxxlast");//共享信息(老)4
				String gxxxnow = request.getParameter("gxxxnow");//共享信息(新)
				String gdfslast = request.getParameter("gdfslast");//供电方式(老)5
				String gdfsnow = request.getParameter("gdfsnow");//供电方式(新)
				String zgdlast = request.getParameter("zgdlast");//直供电(老)6
				String zgdnow = request.getParameter("zgdnow");//直供电(新)
				
				String zdarealast = request.getParameter("zdarealast");//站点面积(老)7
				String zdareanow = request.getParameter("zdareanow");//站点面积(新)
				String qyztlast = request.getParameter("qyztlast");//站点启用状态(老)8
				String qyztnow = request.getParameter("qyztnow");//站点启用状态(新)
				String oldqsdb = request.getParameter("oldqsdb");//省定标(不含空调)(老)9
				String qsdb = request.getParameter("qsdb");//省定标(不含空调)(新)
				
				String g2last = request.getParameter("g2last");//2G(老)10
				String g2now = request.getParameter("g2now");//2G(新)
				String g3last = request.getParameter("g3last");//3G(老)11
				String g3now = request.getParameter("g3now");//3G(新)
				String zplast = request.getParameter("zplast");//载频(老)12
				String zpnow = request.getParameter("zpnow");//载频(新)
				
				String zslast = request.getParameter("zslast");//载扇(老)13
				String zsnow = request.getParameter("zsnow");//载扇(新)
				String changjialast = request.getParameter("changjialast");//厂家(老)14
				String changjianow = request.getParameter("changjianow");//厂家(新)
				String jztypelast = request.getParameter("jztypelast");//基站类型2(老)15
				String jztypenow = request.getParameter("jztypenow");//基站类型2(新)
				
				String bbulast = request.getParameter("bbulast");//BBU数量(老)16
				String bbunow = request.getParameter("bbunow");//BBU数量(新)
				String rrulast = request.getParameter("rrulast");//RRU数量(老)17
				String rrunow = request.getParameter("rrunow");//RRU数量(新)
				String shebeilast = request.getParameter("shebeilast");//设备编码(老)18
				String shebeinow = request.getParameter("shebeinow");//设备编码(新)
				
				String ydshebeilast = request.getParameter("ydshebeilast");//他网共享数量(老)19
				String ydshebeinow = request.getParameter("ydshebeinow");//他网共享数量(新)
				String gxgwsllast = request.getParameter("gxgwsllast");//共享固网数量(老)20
				String gxgwslnow = request.getParameter("gxgwslnow");//共享固网数量(新)
				

				ArrayList list = new ArrayList();
				Zdqxkz formBean = new Zdqxkz();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String entrytime = df.format(new Date());
				String lg=request.getParameter("lg");
				formBean.setZdid(request.getParameter("zdid"));
				formBean.setDbid(request.getParameter("dbid"));
				String zt1 = request.getParameter("zt");// 状态
				String zt2 = "";// 状态
				if (zt1 == "1" || "1".equals(zt1)) {
					zt2 = "增加";
				} else if (zt1 == "2" || "2".equals(zt1)) {
					zt2 = "修改";
				} else if (zt1 == "3" || "3".equals(zt1)) {
					zt2 = "减少";
				}
				formBean.setBiaoti(request.getParameter("diquzdid") + zt2);

				formBean.setOldbgsign(request.getParameter("sfbglast"));
				formBean.setOlddianfei(request.getParameter("dianjialast"));
				formBean.setOldedhdl(request.getParameter("edhdllast"));
				formBean.setOldzlfh(request.getParameter("zlfhlast"));
				formBean.setOldjlfh(request.getParameter("jlfhlast"));
				formBean.setOldproperty(zdsxlast);
				formBean.setOldstationtype(zdlxlast);
				formBean.setOldyflx(yflxlast);
				formBean.setOldgxxx(gxxxlast);
				formBean.setOldgdfs(gdfslast);
				formBean.setOldzgd(zgdlast);
				formBean.setOldarea(zdarealast);
				formBean.setOldqyzt(qyztlast);
				formBean.setOldg2(g2last);
				formBean.setOldg3(g3last);
				formBean.setOldzpsl(zplast);
				formBean.setOldzssl(zslast);
				formBean.setOldchangjia(changjialast);
				formBean.setOldjzlx(jztypelast);
				formBean.setOldshebei(shebeilast);
				formBean.setOldbbu(bbulast);
				formBean.setOldrru(rrulast);
				formBean.setOldydshebei(ydshebeilast);
				formBean.setOldgxgwsl(gxgwsllast);
				//formBean.setOldqsdbdl(request.getParameter("oldqsdbdl"));
				formBean.setOldqsdbdl(oldqsdb); //现在更新为未更改前省定标(不含空调)即生产标！2013-11-12
				formBean.setOlddfzflx(request.getParameter("dfzflxlast"));
				formBean.setOldqsdb(request.getParameter("oldqsdbdl"));//全省定标电量
				formBean.setOlddbds(request.getParameter("olddbds"));
				formBean.setOldxgbzw(request.getParameter("oldxgbzw"));

				formBean.setNewbgsign(request.getParameter("sfbgnow"));
				formBean.setNewdianfei(request.getParameter("dianjianow"));
				formBean.setNewedhdl(request.getParameter("edhdlnow"));
				formBean.setNewzlfh(request.getParameter("zlfhnow"));
				formBean.setNewjlfh(request.getParameter("jlfhnow"));
				formBean.setNewproperty(zdsxnow);
				formBean.setNewstationtype(zdlxnow);
				formBean.setNewyflx(yflxnow);
				formBean.setNewgxxx(gxxxnow);
				formBean.setNewgdfs(gdfsnow);
				formBean.setNewzgd(zgdnow);
				formBean.setNewarea(zdareanow);
				formBean.setNewqyzt(qyztnow);
				formBean.setNewg2(g2now);
				formBean.setNewg3(g3now);
				formBean.setNewzpsl(zpnow);
				formBean.setNewzssl(zsnow);
				formBean.setNewchangjia(changjianow);
				formBean.setNewjzlx(jztypenow);
				formBean.setNewshebei(shebeinow);
				formBean.setNewbbu(bbunow);
				formBean.setNewrru(rrunow);
				formBean.setNewydshebei(ydshebeinow);
				formBean.setNewgxgwsl(gxgwslnow);
				// formBean.setNewqsdbdl(request.getParameter("newqsdbdl"));
				 formBean.setNewqsdbdl(qsdb);//更新为修改后的省定标电量(生产标)
				 formBean.setNewdfzflx(request.getParameter("dfzflxnow"));

				//formBean.setXgsm(request.getParameter("reasonanalyse"));
				//formBean.setXgyj(request.getParameter("reasonanalyseyj"));
				formBean.setQxpdbz(request.getParameter("bzw"));
				formBean.setZdbzw(request.getParameter("zdbzw"));//站点核实标杆标志位zdbzw
				System.out.println("权限判断标志位："+request.getParameter("bzw"));
				formBean.setQxtjr(loginName);
				formBean.setQxtjbz("1");
				formBean.setQxtjtime(entrytime);
				
			    String zdbzw=request.getParameter("zdbzw");//站点核标标志位 1为是 0为否
			    String djbzw=request.getParameter("bzw");//权限判断标志位 这里要取的为 3、4  3为单纯降标 4为升标
			
				
				String dsbzw="0";//电表读数修改标志位  1 为修改  0为未修改
				String dbds = request.getParameter("dbds").trim();
				
				if(dbds==null||dbds==""||"".equals(dbds)){
					dsbzw="0";
				}else if(Double.parseDouble(dbds)>=0){
					dsbzw="1";
				}
				//System.out.println("电表读数"+dbds+"空格"+dbds.trim()+" 标志位"+dsbzw);
				formBean.setDbds(dbds);//电表读数
				formBean.setDsbzw(dsbzw);//电表读数修改标志位
				


				List<String> oldlist = new ArrayList<String>();
				oldlist.add(yflxlast);oldlist.add(zdsxlast);
				oldlist.add(zdlxlast);oldlist.add(gxxxlast);
				oldlist.add(gdfslast);oldlist.add(zgdlast);
				oldlist.add(zdarealast);oldlist.add(qyztlast);
				oldlist.add(oldqsdb);oldlist.add(g2last);
				oldlist.add(g3last);oldlist.add(zplast);
				oldlist.add(zslast);oldlist.add(changjialast);
				oldlist.add(jztypelast);oldlist.add(bbulast);
				oldlist.add(rrulast);oldlist.add(shebeilast);
				oldlist.add(ydshebeilast);oldlist.add(gxgwsllast);

				List<String> newlist = new ArrayList<String>();
				newlist.add(yflxnow);newlist.add(zdsxnow);
				newlist.add(zdlxnow);newlist.add(gxxxnow);
				newlist.add(gdfsnow);newlist.add(zgdnow);
				newlist.add(zdareanow);newlist.add(qyztnow);
				newlist.add(qsdb);newlist.add(g2now);
				newlist.add(g3now);newlist.add(zpnow);
				newlist.add(zsnow);newlist.add(changjianow);
				newlist.add(jztypenow);newlist.add(bbunow);
				newlist.add(rrunow);newlist.add(shebeinow);
				newlist.add(ydshebeinow);newlist.add(gxgwslnow);

				String str=new ZdqxkzUtil().getFlggZd(oldlist, newlist);//分类更改字段
				formBean.setFlgg(str);

				Zdqxcxxg beana = new Zdqxcxxg();
				File tempfile = new File(System.getProperty("java.io.tmpdir"));// 采用系统临时文件目录
																				// 上传报表时
																				// 存报表的临时路径
																				// 最后删除
				FileItem item = null;
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(4096); // 设置文件大小的
												// 设置是否将上传文件已临时文件的形式保存在磁盘的临界值如果从没有调用该方法设置此临界值，将会采用系统默认值10KB。对应的getSizeThreshold()
												// 方法用来获取此临界值。
				factory.setRepository(tempfile);// 文件以临时文件形式保存在磁盘上的存放目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(6291456);// 6291456文件上传大小限制为6M

				List<String> fileItems = new ArrayList<String>();
				String c = "", s = "";
				String nn="",lj="";
				
				try {
					fileItems = upload.parseRequest(request);
				} catch (FileUploadException e) {
					statusInfo.add("上传文件超出大小限制（6M）");
					sendStatusInfo(request, response, statusInfo, bz); // 把错误信息传到前台
					return;
				}
				 Iterator iter = fileItems.iterator();
				  while(iter.hasNext()) {
			         FileItem  file = (FileItem)iter.next();
				//for (Object o : fileItems) {
				//	FileItem file = (FileItem) o;
					if (file.isFormField()) {
						 String name1 = file.getFieldName(); 
			        	 if("reasonanalyse".equals(name1)){
			        		  nn = file.getString("utf-8");
			        	 }else if("reasonanalyseyj".equals(name1)){
			        		  lj = file.getString("utf-8");
			        	 }
			        	 
			        	formBean.setXgsm(nn);
						formBean.setXgyj(lj);		
					}else{
						String[] aa = file.toString().split(",");
						int ss = aa[1].lastIndexOf("\\") + 1;
						s = aa[1].substring(ss, aa[1].length()); // 截取上传文件名称
						String tempfile1 = tempfile + "\\" + s;
						String name = file.getName();
						System.out.println("文件名称1：" + name + ";file="
								+ file.toString());
						UploadDao dao = new UploadDao(account, id, "");
						// 如果有附件
						if (name != null && !name.equals("")&& !name.equals("null") && name.length() > 0 && name != "") {
							Boolean gs = dao.isAvailableToUpload1(statusInfo, file);//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
							if (gs) {
								statusInfo.add("保存失败，上传的文件后缀名必须为.xls");
								sendStatusInfo(request, response, statusInfo, bz);// 把错误信息 传到前台
								return;
							} else {
								Vector biaozhi = this.getContentbiaozhi(tempfile1);// 判断是否是下载的我们的报表第三个sheet里第一个单元格内容是12
								if (biaozhi.isEmpty()) {
									statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
									sendStatusInfo(request, response, statusInfo,bz);// 把错误信息 传到前台
									return;
								} else {

									Object[] a = biaozhi.toArray();
									String ida = "";
									for (int i = 0; i < a.length; i++) { // 应该是重0开始循环判断
										Object[] b = ((Vector) a[i]).toArray();
										ida = b[0].toString().trim();
										if (!ida.equals("55")) {
											statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
											sendStatusInfo(request, response,statusInfo, bz);// 把错误信息 传到前台
											return;
										}
									}
								}
								String mm = "";
								if (statusInfo.size() == 1) {
									mm = statusInfo.get(0);
								}
								if (mm == null || statusInfo.size() == 0) {
									try {
										String msg2 = "";
										Zdqxkz form = new Zdqxkz();
										msg2 = beana.modifyZdqxcxxg(formBean, "",name);
										form = beana.getDBId(request.getParameter("zdid"), entrytime);
										//if(list!=null){
											//int fycount = ((Integer)list.get(0)).intValue();
											//for( int k = fycount;k<list.size()-1;k+=fycount){
										   // id = (String)list.get(list.indexOf("id"));
											//}
										String qid = form.getId();
										//}
										System.out.println("--------2222222222-------------"+qid);
										if ("增加权限控制站点信息成功！".equals(msg2)) {
											if (name != null && name != ""&& !"".equals(name)) {
												// file.write(new File(name));
											}
											msg = "增加权限控制站点信息成功！";
											System.out.println("有附件上传");
											dao.insertToDBGL(file, qid);
											
											log.write(msg, formBean.getZdid(),request.getRemoteAddr(),"增加权限控制站点信息");
											//---针对多个站点的话 
											
											
											
										//增加权限控制站点信息成功后 要进行判断降标条件
											if(formBean.getQxpdbz().equals("3")){
									    //如果是降标的话 那么市审核自动通过 省审核自动通过 并且要更改交流负荷和直流负荷、生产标(站点的)  生产标(scb)
										    Zdqxcxxg zdzg=new Zdqxcxxg();
										   String msga= zdzg.updatezdjb(formBean,lg);
										   
										   
										 //  if(msga.equals("自动降标成功！")){
											//   statusInfo.add("自动降标成功！");
										  // }else{
											//   statusInfo.add("自动降标失败！");
										//   }
											}
											//单纯升标阶段 不管交直流负荷 只要是单纯的升高标杆、是核标站点即进行以下流程
											//能耗基础值占比：(能耗基础值-分公司定标)/(分公司定标)
											//基础实际值占比：(基础实际值-分公司定标)/(分公司定标)
											//建议生产标占比：(建议生产标-分公司定标)/(分公司定标) 
											//交流电量占比：(交流电量-分公司定标)/(分公司定标)
											//直流电量占比：(直流电量-分公司定标)/(分公司定标)
											System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
											if(formBean.getQxpdbz().equals("4")){
												System.out.println("i am here !!!");
												//各项占比关系
												Zdqxcxxg zg=new Zdqxcxxg();
												int x=0;
												x=zg.zdbgdj(formBean,lg);
											//	if(x>0){
													//  statusInfo.add("自动判断等级成功！");
												//}else{
												//	  statusInfo.add("自动判断等级失败！");
												//}
												
											}
										
										} else {
											msg = "增加权限控制站点信息失败！";
										}
										statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("内部错误，请联系管理员！");
										sendStatusInfo(request, response,statusInfo, bz);
										e.printStackTrace();
										return;
									}
									statusInfo.add("文件上传成功！");
								}
							}
						} else {
							// 如没有上传文件
							System.out.println("没有上传文件");
							String mm = "";
							if (statusInfo.size() == 1) {
								mm = statusInfo.get(0);
							}
							if (mm == null || statusInfo.size() == 0) {
								try {
									//dao.insertToDBGL(file, idd);
									String msg2 = "";
									msg2 = beana.modifyZdqxcxxg(formBean, "", name);
									if ("增加权限控制站点信息成功！".equals(msg2)) {
										if (name != null && name != "" && !"".equals(name)) {
											// file.write(new File(path1+"/"+name));
										}
										msg = "增加权限控制站点信息成功！";
										//增加权限控制站点信息成功后 要进行判断降标条件
										if(formBean.getQxpdbz().equals("3")){
								    //如果是降标的话 那么市审核自动通过 省审核自动通过 并且要更改交流负荷和直流负荷、生产标(站点的)  生产标(scb)
									    Zdqxcxxg zg=new Zdqxcxxg();
									    Zdqxkz k=new Zdqxkz();
									  k=zg.maxqskzid(formBean.getZdid());
									   String msga= zg.updatezdjb(k,lg);
									//   if(msga.equals("自动降标成功！")){
										  // statusInfo.add("自动降标成功！");
									 //  }else{
										  // statusInfo.add("自动降标失败！");
									 //  }
										}
										//单纯升标阶段 不管交直流负荷 只要是单纯的升高标杆、是核标站点即进行以下流程
										//能耗基础值占比：(能耗基础值-分公司定标)/(分公司定标)
										//基础实际值占比：(基础实际值-分公司定标)/(分公司定标)
										//建议生产标占比：(建议生产标-分公司定标)/(分公司定标) 
										//交流电量占比：(交流电量-分公司定标)/(分公司定标)
										//直流电量占比：(直流电量-分公司定标)/(分公司定标)
										System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
										if(formBean.getQxpdbz().equals("4")){
											System.out.println("i am here !!!");
											//各项占比关系
											Zdqxcxxg zg=new Zdqxcxxg();
											int x=0;
											x=zg.zdbgdj(formBean,lg);
										//	if(x>0){
											//	  statusInfo.add("自动判断等级成功！");
											//}else{
											//	  statusInfo.add("自动判断等级失败！");
										//	}
											
										}
										log.write(msg, formBean.getZdid(), request.getRemoteAddr(), "增加权限控制站点信息");
									} else {
										msg = "增加权限控制站点信息失败！";
									}
									statusInfo.add(msg);
								} catch (Exception e) {
									statusInfo.add("内部错误，请联系管理员！");
									sendStatusInfo(request, response, statusInfo,bz);
									e.printStackTrace();
									return;
								}
								//statusInfo.add("文件上传成功！");
							}
						}
					}
				}
				
			}else{
				statusInfo.add("更新整改工单直流,交流,额定,生产标失败,请联系管理员!");
			}

			sendStatusInfo(request, response, statusInfo, bz);
		} else {// 判断文件是否可以上传
			PrintWriter out = response.getWriter();
			String result = "0";
			// if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖）
			// 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
			out.println(result);
			out.flush();
			out.close();
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

	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response, List<?> statusInfo, String bz) {
		request.setAttribute("statusInfo", statusInfo);
		try {
			if ("upload".equals(bz) || bz == "upload") {
				request.getRequestDispatcher("/web/zdqxkz/zdgjxxxgxx.jsp").forward(request, response);
			} else if ("modifyzd".equals(bz) || bz == "modifyzd") {
				request.getRequestDispatcher("/web/zdqxkz/modifyzdgjxxxgxx.jsp").forward(request, response);
			} else if ("zg".equals(bz)) {
				request.setAttribute("statusInfo", statusInfo);
				request.getRequestDispatcher("/web/zdqxkz/zdgjxxxgxx.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 生成随机数100到999
	public int getRandom() {
		int number = 0;
		while (true) {
			number = (int) (Math.random() * 1000);
			if (number >= 100 && number < 1000) {
				break;
			}
		}
		return number;
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
}
