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

		request.setCharacterEncoding("utf-8");// ������������ʽ��
		response.setContentType("text/html;charsetType=utf-8");// ������������ʽ��

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
			log.write(msg, qxid, request.getRemoteAddr(), "ɾ��Ȩ�޿���վ����Ϣ");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");

		}else if(action.equals("zdqxsjsh")){
			String ggbz = request.getParameter("ggbz");
			String idshisj=request.getParameter("id");
			String qsdb=request.getParameter("qsdb");
			System.out.println("�м���˱��棺"+idshisj+"ȫʡ���꣺"+qsdb);
			String url = path + "/web/check/close.jsp";
			
			String flgg = "";//��������ֶ�
			flgg = bean.queryFlgg(idshisj);
			ZdqxkzUtil util = new ZdqxkzUtil();
			if("2".equals(ggbz)){//����ʡ��
				flgg = 	util.getBftg(flgg, "9");
			}
			String msg = "";
			msg = bean.zdqxssh(idshisj,qsdb,flgg,ggbz);
			
			log.write(msg, idshisj, request.getRemoteAddr(), "ʡ������˱���ʡ����");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			session.setAttribute("idshisj", idshisj);
			response.sendRedirect(path + "/web/msg.jsp");
		}else if (action.equals("modifyzd")) {
			String msg = "";
			List<String> statusInfo = new ArrayList<String>();// ��ʾ�Ĵ�����Ϣ
			
			String zggdid = request.getParameter("zggdid");//���Ĺ���id
			String fzbzw = request.getParameter("fzbzw");//��ֵ��־λ 1:��ֵ  0:����ֵ 
			boolean zggdbz = true;
			if("1".equals(fzbzw)){//��������������Ĺ���ֱ��,����,�,����
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
			if (null == confirm) {// �ļ���ʼ�ϴ�
				
				String yflxlast = request.getParameter("yflxlast");//�÷�����(��)1
				String yflxnow = request.getParameter("yflxnow");//�÷�����(��)
				String zdsxlast = request.getParameter("zdsxlast");//վ������(��)2
				String zdsxnow = request.getParameter("zdsxnow");//վ������(��)
				String zdlxlast = request.getParameter("zdlxlast");//վ������(��)3
				String zdlxnow = request.getParameter("zdlxnow");//վ������(��)
				
				String gxxxlast = request.getParameter("gxxxlast");//������Ϣ(��)4
				String gxxxnow = request.getParameter("gxxxnow");//������Ϣ(��)
				String gdfslast = request.getParameter("gdfslast");//���緽ʽ(��)5
				String gdfsnow = request.getParameter("gdfsnow");//���緽ʽ(��)
				String zgdlast = request.getParameter("zgdlast");//ֱ����(��)6
				String zgdnow = request.getParameter("zgdnow");//ֱ����(��)
				
				String zdarealast = request.getParameter("zdarealast");//վ�����(��)7
				String zdareanow = request.getParameter("zdareanow");//վ�����(��)
				String qyztlast = request.getParameter("qyztlast");//վ������״̬(��)8
				String qyztnow = request.getParameter("qyztnow");//վ������״̬(��)
				String oldqsdb = request.getParameter("oldqsdb");//ʡ����(�����յ�)(��)9
				String qsdb = request.getParameter("qsdb");//ʡ����(�����յ�)(��)
				
				String g2last = request.getParameter("g2last");//2G(��)10
				String g2now = request.getParameter("g2now");//2G(��)
				String g3last = request.getParameter("g3last");//3G(��)11
				String g3now = request.getParameter("g3now");//3G(��)
				String zplast = request.getParameter("zplast");//��Ƶ(��)12
				String zpnow = request.getParameter("zpnow");//��Ƶ(��)
				
				String zslast = request.getParameter("zslast");//����(��)13
				String zsnow = request.getParameter("zsnow");//����(��)
				String changjialast = request.getParameter("changjialast");//����(��)14
				String changjianow = request.getParameter("changjianow");//����(��)
				String jztypelast = request.getParameter("jztypelast");//��վ����2(��)15
				String jztypenow = request.getParameter("jztypenow");//��վ����2(��)
				
				String bbulast = request.getParameter("bbulast");//BBU����(��)16
				String bbunow = request.getParameter("bbunow");//BBU����(��)
				String rrulast = request.getParameter("rrulast");//RRU����(��)17
				String rrunow = request.getParameter("rrunow");//RRU����(��)
				String shebeilast = request.getParameter("shebeilast");//�豸����(��)18
				String shebeinow = request.getParameter("shebeinow");//�豸����(��)
				
				String ydshebeilast = request.getParameter("ydshebeilast");//������������(��)19
				String ydshebeinow = request.getParameter("ydshebeinow");//������������(��)
				String gxgwsllast = request.getParameter("gxgwsllast");//�����������(��)20
				String gxgwslnow = request.getParameter("gxgwslnow");//�����������(��)
				
				
				Zdqxkz formBean = new Zdqxkz();
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
				String entrytime = df.format(new Date());

				formBean.setId(request.getParameter("id"));
				formBean.setZdid(request.getParameter("zdid"));
				String zt1 = request.getParameter("zt");// ״̬
				String zt2 = "";// ״̬
				if (zt1 == "1" || "1".equals(zt1)) {
					zt2 = "����";
				} else if (zt1 == "2" || "2".equals(zt1)) {
					zt2 = "�޸�";
				} else if (zt1 == "3" || "3".equals(zt1)) {
					zt2 = "����";
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
				
				String dsbzw="0";//�������޸ı�־λ  1 Ϊ�޸�  0Ϊδ�޸�
				String dbds = request.getParameter("dbds").trim();
				
				if(dbds==null||dbds==""||"".equals(dbds)){
					dsbzw="0";
				}else if(Double.parseDouble(dbds)>=0){
					dsbzw="1";
				}
				formBean.setZdbzw(request.getParameter("zdbzw"));//վ���ʵ��˱�־λ
				//System.out.println("������"+dbds+"�ո�"+dbds.trim()+" ��־λ"+dsbzw);
				formBean.setDbds(dbds);//������
				formBean.setDsbzw(dsbzw);//�������޸ı�־λ
				 String zdbzw=request.getParameter("zdbzw");//վ��˱��־λ 1Ϊ�� 0Ϊ��
				    String djbzw=request.getParameter("bzw");//Ȩ���жϱ�־λ ����Ҫȡ��Ϊ 3��4  3Ϊ�������� 4Ϊ����

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
					String str=new ZdqxkzUtil().getFlggZd(oldlist, newlist);//��������ֶ�
					formBean.setFlgg(str);
				    
				Zdqxcxxg beana = new Zdqxcxxg();

				File tempfile = new File(System.getProperty("java.io.tmpdir"));// ����ϵͳ��ʱ�ļ�Ŀ¼
				// �ϴ�����ʱ
				// �汨�����ʱ·��
				// ���ɾ��
					FileItem item = null;
					DiskFileItemFactory factory = new DiskFileItemFactory();
					factory.setSizeThreshold(4096); // �����ļ���С��
					// �����Ƿ��ϴ��ļ�����ʱ�ļ�����ʽ�����ڴ��̵��ٽ�ֵ�����û�е��ø÷������ô��ٽ�ֵ���������ϵͳĬ��ֵ10KB����Ӧ��getSizeThreshold()
					// ����������ȡ���ٽ�ֵ��
					factory.setRepository(tempfile);// �ļ�����ʱ�ļ���ʽ�����ڴ����ϵĴ��Ŀ¼
					ServletFileUpload upload = new ServletFileUpload(factory);
					upload.setSizeMax(6291456);// 6291456�ļ��ϴ���С����Ϊ6M
					List<String> fileItems = new ArrayList<String>();
					String c = "", s = "";
					String nn="",lj="";
					try {
						fileItems = upload.parseRequest(request);
					} catch (FileUploadException e) {
						statusInfo.add("�ϴ��ļ�������С���ƣ�6M��");
						sendStatusInfo(request, response, statusInfo, "modifyzd"); // �Ѵ�����Ϣ����ǰ̨
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
							s = aa[1].substring(ss, aa[1].length()); // ��ȡ�ϴ��ļ�����
							String tempfile1 = tempfile + "\\" + s;
							String name = file.getName();
							System.out.println("name------------ss"+name);
							UploadDao dao1 = new UploadDao(account, id, "");
					// ����и���
							if (name != null && !name.equals("")&& !name.equals("null") && name.length() > 0 && name != "") {
								Boolean gs = dao1.isAvailableToUpload1(statusInfo, file);//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
								if (gs) {
										statusInfo.add("����ʧ�ܣ��ϴ����ļ���׺������Ϊ.xls");
										sendStatusInfo(request, response, statusInfo,"modifyzd");// �Ѵ�����Ϣ ����ǰ̨
										return;
								} else {
									Vector biaozhi = this.getContentbiaozhi(tempfile1);// �ж��Ƿ������ص����ǵı��������sheet���һ����Ԫ��������12
									if (biaozhi.isEmpty()) {
										statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
										sendStatusInfo(request, response, statusInfo,"modifyzd");// �Ѵ�����Ϣ ����ǰ̨
										return;
									} else {
										Object[] a = biaozhi.toArray();
										String idaa = "";
										for (int i = 0; i < a.length; i++) { // Ӧ������0��ʼѭ���ж�
											Object[] b = ((Vector) a[i]).toArray();
											idaa = b[0].toString().trim();
											if (!idaa.equals("55")) {
												statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
												sendStatusInfo(request, response,statusInfo, "modifyzd");// �Ѵ�����Ϣ ����ǰ̨
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
											if ("�޸�Ȩ�޿���վ����Ϣ�ɹ���".equals(msg2)) {
												if (name != null && name != ""&& !"".equals(name)) {
													// file.write(new File(name));
												}
												msg = "�޸�Ȩ�޿���վ����Ϣ�ɹ���";
												System.out.println("�и����ϴ�");
												dao1.insertToDBGL(file, qid);
												
												log.write(msg, formBean.getZdid(),request.getRemoteAddr(),"�޸�Ȩ�޿���վ����Ϣ");
												if(formBean.getQxpdbz().equals("3")){
												    //����ǽ���Ļ� ��ô������Զ�ͨ�� ʡ����Զ�ͨ�� ����Ҫ���Ľ������ɺ�ֱ�����ɡ�������(վ���)  ������(scb)
													    Zdqxcxxg zdzg=new Zdqxcxxg();
													   String msga= zdzg.updatezdjb(formBean,lg);
													 //  if(msga.equals("�Զ�����ɹ���")){
														  // statusInfo.add("�Զ�����ɹ���");
													  // }else{
														 //  statusInfo.add("�Զ�����ʧ�ܣ�");
													 //  }
														}
												//��������׶� ���ܽ�ֱ������ ֻҪ�ǵ��������߱�ˡ��Ǻ˱�վ�㼴������������
												//�ܺĻ���ֵռ�ȣ�(�ܺĻ���ֵ-�ֹ�˾����)/(�ֹ�˾����)
												//����ʵ��ֵռ�ȣ�(����ʵ��ֵ-�ֹ�˾����)/(�ֹ�˾����)
												//����������ռ�ȣ�(����������-�ֹ�˾����)/(�ֹ�˾����) 
												//��������ռ�ȣ�(��������-�ֹ�˾����)/(�ֹ�˾����)
												//ֱ������ռ�ȣ�(ֱ������-�ֹ�˾����)/(�ֹ�˾����)
												System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
												if(formBean.getQxpdbz().equals("4")){
													System.out.println("i am here !!!");
													//����ռ�ȹ�ϵ
													Zdqxcxxg zg=new Zdqxcxxg();
													int x=0;
													x=zg.zdbgdj(formBean,lg);
												//	if(x>0){
													//	  statusInfo.add("�Զ��жϵȼ��ɹ���");
												///	}else{
														//  statusInfo.add("�Զ��жϵȼ�ʧ�ܣ�");
												//	}
													
												}
											
											} else {
												msg = "�޸�Ȩ�޿���վ����Ϣʧ�ܣ�";
											}
											statusInfo.add(msg);
										} catch (Exception e) {
											statusInfo.add("�ڲ���������ϵ����Ա��");
											sendStatusInfo(request, response,statusInfo, "modifyzd");
											e.printStackTrace();
											return;
										}
										statusInfo.add("�ļ��ϴ��ɹ���");
									}
								}
							} else {
					// ��û���ϴ��ļ�
								System.out.println("û���ϴ��ļ�");
								String mm = "";
								if (statusInfo.size() == 1) {
									mm = statusInfo.get(0);
								}
								if (mm == null || statusInfo.size() == 0) {
									try {
										String msg2 = "";
										msg2 = beana.modifyZdqxcxxg1(formBean, "", name);
										if ("�޸�Ȩ�޿���վ����Ϣ�ɹ���".equals(msg2)) {
											if (name != null && name != "" && !"".equals(name)) {
												// file.write(new File(path1+"/"+name));
											}
											//dao1.insertToDBGL(file, qid);
											dao1.UpdateFjnr(qid);
											msg = "�޸�Ȩ�޿���վ����Ϣ�ɹ���";
										
											log.write(msg, formBean.getZdid(), request.getRemoteAddr(), "�޸�Ȩ�޿���վ����Ϣ");
											//����Ȩ�޿���վ����Ϣ�ɹ��� Ҫ�����жϽ�������
											if(formBean.getQxpdbz().equals("3")){
											    //����ǽ���Ļ� ��ô������Զ�ͨ�� ʡ����Զ�ͨ�� ����Ҫ���Ľ������ɺ�ֱ�����ɡ�������(վ���)  ������(scb)
											    Zdqxcxxg zg=new Zdqxcxxg();
											    Zdqxkz k=new Zdqxkz();
											  k=zg.maxqskzid(formBean.getZdid());
											   String msga= zg.updatezdjb(k,lg);
											 //  if(msga.equals("�Զ�����ɹ���")){
												 //  statusInfo.add("�Զ�����ɹ���");
											 //  }else{
												 //  statusInfo.add("�Զ�����ʧ�ܣ�");
											  // }
												}
											//��������׶� ���ܽ�ֱ������ ֻҪ�ǵ��������߱�ˡ��Ǻ˱�վ�㼴������������
											//�ܺĻ���ֵռ�ȣ�(�ܺĻ���ֵ-�ֹ�˾����)/(�ֹ�˾����)
											//����ʵ��ֵռ�ȣ�(����ʵ��ֵ-�ֹ�˾����)/(�ֹ�˾����)
											//����������ռ�ȣ�(����������-�ֹ�˾����)/(�ֹ�˾����) 
											//��������ռ�ȣ�(��������-�ֹ�˾����)/(�ֹ�˾����)
											//ֱ������ռ�ȣ�(ֱ������-�ֹ�˾����)/(�ֹ�˾����)
											System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
											if(formBean.getQxpdbz().equals("4")){
												System.out.println("i am here !!!");
												//����ռ�ȹ�ϵ
												Zdqxcxxg zg=new Zdqxcxxg();
												int x=0;
												x=zg.zdbgdj(formBean,lg);
												//if(x>0){
													//  statusInfo.add("�Զ��жϵȼ��ɹ���");
												//}else{
													//  statusInfo.add("�Զ��жϵȼ�ʧ�ܣ�");
											//	}
												
											}
										} else {
											msg = "�޸�Ȩ�޿���վ����Ϣʧ�ܣ�";
										}
										statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("�ڲ���������ϵ����Ա��");
										sendStatusInfo(request, response, statusInfo,"modifyzd");
										e.printStackTrace();
										return;
									}
									//statusInfo.add("�ļ��ϴ��ɹ���");
								}
							}
						}
					}
					  
				sendStatusInfo(request, response, statusInfo, "modifyzd");
			} else {// �ж��ļ��Ƿ�����ϴ�
				PrintWriter out = response.getWriter();
				String result = "0";
				if (dao.fileIsUploaded())
					result = "1";
				out.println(result);
				out.flush();
				out.close();
			}
		}else{
			statusInfo.add("�������Ĺ���ֱ��,����,�,������ʧ��,����ϵ����Ա!");
			sendStatusInfo(request, response, statusInfo, "modifyzd");
		}
			
		} else if (action.equals("upload")) {
			String bz = "zg";
			String msg = "";
			List<String> statusInfo = new ArrayList<String>();// ��ʾ�Ĵ�����Ϣ
				
			String zggdid = request.getParameter("zggdid");//���Ĺ���id
			String fzbzw = request.getParameter("fzbzw");//��ֵ��־λ 1:��ֵ  0:����ֵ 
			boolean zggdbz = true;
			if("1".equals(fzbzw)){//��������������Ĺ���ֱ��,����,�,����
				Zdqxcxxg zg=new Zdqxcxxg();
				String zlfh = request.getParameter("zlfhnow");
				String jlfh = request.getParameter("jlfhnow");
				String edhdl = request.getParameter("edhdlnow");
				String scb = request.getParameter("qsdb");
				zggdbz = zg.setZggd(zggdid, zlfh, jlfh, edhdl, scb);
			}
			if(zggdbz){//��������������Ĺ���ֱ��,����,�,�����ɹ�   ��  �������
				
				String yflxlast = request.getParameter("yflxlast");//�÷�����(��)1
				String yflxnow = request.getParameter("yflxnow");//�÷�����(��)
				String zdsxlast = request.getParameter("zdsxlast");//վ������(��)2
				String zdsxnow = request.getParameter("zdsxnow");//վ������(��)
				String zdlxlast = request.getParameter("zdlxlast");//վ������(��)3
				String zdlxnow = request.getParameter("zdlxnow");//վ������(��)
				
				String gxxxlast = request.getParameter("gxxxlast");//������Ϣ(��)4
				String gxxxnow = request.getParameter("gxxxnow");//������Ϣ(��)
				String gdfslast = request.getParameter("gdfslast");//���緽ʽ(��)5
				String gdfsnow = request.getParameter("gdfsnow");//���緽ʽ(��)
				String zgdlast = request.getParameter("zgdlast");//ֱ����(��)6
				String zgdnow = request.getParameter("zgdnow");//ֱ����(��)
				
				String zdarealast = request.getParameter("zdarealast");//վ�����(��)7
				String zdareanow = request.getParameter("zdareanow");//վ�����(��)
				String qyztlast = request.getParameter("qyztlast");//վ������״̬(��)8
				String qyztnow = request.getParameter("qyztnow");//վ������״̬(��)
				String oldqsdb = request.getParameter("oldqsdb");//ʡ����(�����յ�)(��)9
				String qsdb = request.getParameter("qsdb");//ʡ����(�����յ�)(��)
				
				String g2last = request.getParameter("g2last");//2G(��)10
				String g2now = request.getParameter("g2now");//2G(��)
				String g3last = request.getParameter("g3last");//3G(��)11
				String g3now = request.getParameter("g3now");//3G(��)
				String zplast = request.getParameter("zplast");//��Ƶ(��)12
				String zpnow = request.getParameter("zpnow");//��Ƶ(��)
				
				String zslast = request.getParameter("zslast");//����(��)13
				String zsnow = request.getParameter("zsnow");//����(��)
				String changjialast = request.getParameter("changjialast");//����(��)14
				String changjianow = request.getParameter("changjianow");//����(��)
				String jztypelast = request.getParameter("jztypelast");//��վ����2(��)15
				String jztypenow = request.getParameter("jztypenow");//��վ����2(��)
				
				String bbulast = request.getParameter("bbulast");//BBU����(��)16
				String bbunow = request.getParameter("bbunow");//BBU����(��)
				String rrulast = request.getParameter("rrulast");//RRU����(��)17
				String rrunow = request.getParameter("rrunow");//RRU����(��)
				String shebeilast = request.getParameter("shebeilast");//�豸����(��)18
				String shebeinow = request.getParameter("shebeinow");//�豸����(��)
				
				String ydshebeilast = request.getParameter("ydshebeilast");//������������(��)19
				String ydshebeinow = request.getParameter("ydshebeinow");//������������(��)
				String gxgwsllast = request.getParameter("gxgwsllast");//�����������(��)20
				String gxgwslnow = request.getParameter("gxgwslnow");//�����������(��)
				

				ArrayList list = new ArrayList();
				Zdqxkz formBean = new Zdqxkz();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
				String entrytime = df.format(new Date());
				String lg=request.getParameter("lg");
				formBean.setZdid(request.getParameter("zdid"));
				formBean.setDbid(request.getParameter("dbid"));
				String zt1 = request.getParameter("zt");// ״̬
				String zt2 = "";// ״̬
				if (zt1 == "1" || "1".equals(zt1)) {
					zt2 = "����";
				} else if (zt1 == "2" || "2".equals(zt1)) {
					zt2 = "�޸�";
				} else if (zt1 == "3" || "3".equals(zt1)) {
					zt2 = "����";
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
				formBean.setOldqsdbdl(oldqsdb); //���ڸ���Ϊδ����ǰʡ����(�����յ�)�������꣡2013-11-12
				formBean.setOlddfzflx(request.getParameter("dfzflxlast"));
				formBean.setOldqsdb(request.getParameter("oldqsdbdl"));//ȫʡ�������
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
				 formBean.setNewqsdbdl(qsdb);//����Ϊ�޸ĺ��ʡ�������(������)
				 formBean.setNewdfzflx(request.getParameter("dfzflxnow"));

				//formBean.setXgsm(request.getParameter("reasonanalyse"));
				//formBean.setXgyj(request.getParameter("reasonanalyseyj"));
				formBean.setQxpdbz(request.getParameter("bzw"));
				formBean.setZdbzw(request.getParameter("zdbzw"));//վ���ʵ��˱�־λzdbzw
				System.out.println("Ȩ���жϱ�־λ��"+request.getParameter("bzw"));
				formBean.setQxtjr(loginName);
				formBean.setQxtjbz("1");
				formBean.setQxtjtime(entrytime);
				
			    String zdbzw=request.getParameter("zdbzw");//վ��˱��־λ 1Ϊ�� 0Ϊ��
			    String djbzw=request.getParameter("bzw");//Ȩ���жϱ�־λ ����Ҫȡ��Ϊ 3��4  3Ϊ�������� 4Ϊ����
			
				
				String dsbzw="0";//�������޸ı�־λ  1 Ϊ�޸�  0Ϊδ�޸�
				String dbds = request.getParameter("dbds").trim();
				
				if(dbds==null||dbds==""||"".equals(dbds)){
					dsbzw="0";
				}else if(Double.parseDouble(dbds)>=0){
					dsbzw="1";
				}
				//System.out.println("������"+dbds+"�ո�"+dbds.trim()+" ��־λ"+dsbzw);
				formBean.setDbds(dbds);//������
				formBean.setDsbzw(dsbzw);//�������޸ı�־λ
				


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

				String str=new ZdqxkzUtil().getFlggZd(oldlist, newlist);//��������ֶ�
				formBean.setFlgg(str);

				Zdqxcxxg beana = new Zdqxcxxg();
				File tempfile = new File(System.getProperty("java.io.tmpdir"));// ����ϵͳ��ʱ�ļ�Ŀ¼
																				// �ϴ�����ʱ
																				// �汨�����ʱ·��
																				// ���ɾ��
				FileItem item = null;
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(4096); // �����ļ���С��
												// �����Ƿ��ϴ��ļ�����ʱ�ļ�����ʽ�����ڴ��̵��ٽ�ֵ�����û�е��ø÷������ô��ٽ�ֵ���������ϵͳĬ��ֵ10KB����Ӧ��getSizeThreshold()
												// ����������ȡ���ٽ�ֵ��
				factory.setRepository(tempfile);// �ļ�����ʱ�ļ���ʽ�����ڴ����ϵĴ��Ŀ¼
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(6291456);// 6291456�ļ��ϴ���С����Ϊ6M

				List<String> fileItems = new ArrayList<String>();
				String c = "", s = "";
				String nn="",lj="";
				
				try {
					fileItems = upload.parseRequest(request);
				} catch (FileUploadException e) {
					statusInfo.add("�ϴ��ļ�������С���ƣ�6M��");
					sendStatusInfo(request, response, statusInfo, bz); // �Ѵ�����Ϣ����ǰ̨
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
						s = aa[1].substring(ss, aa[1].length()); // ��ȡ�ϴ��ļ�����
						String tempfile1 = tempfile + "\\" + s;
						String name = file.getName();
						System.out.println("�ļ�����1��" + name + ";file="
								+ file.toString());
						UploadDao dao = new UploadDao(account, id, "");
						// ����и���
						if (name != null && !name.equals("")&& !name.equals("null") && name.length() > 0 && name != "") {
							Boolean gs = dao.isAvailableToUpload1(statusInfo, file);//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
							if (gs) {
								statusInfo.add("����ʧ�ܣ��ϴ����ļ���׺������Ϊ.xls");
								sendStatusInfo(request, response, statusInfo, bz);// �Ѵ�����Ϣ ����ǰ̨
								return;
							} else {
								Vector biaozhi = this.getContentbiaozhi(tempfile1);// �ж��Ƿ������ص����ǵı��������sheet���һ����Ԫ��������12
								if (biaozhi.isEmpty()) {
									statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
									sendStatusInfo(request, response, statusInfo,bz);// �Ѵ�����Ϣ ����ǰ̨
									return;
								} else {

									Object[] a = biaozhi.toArray();
									String ida = "";
									for (int i = 0; i < a.length; i++) { // Ӧ������0��ʼѭ���ж�
										Object[] b = ((Vector) a[i]).toArray();
										ida = b[0].toString().trim();
										if (!ida.equals("55")) {
											statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
											sendStatusInfo(request, response,statusInfo, bz);// �Ѵ�����Ϣ ����ǰ̨
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
										if ("����Ȩ�޿���վ����Ϣ�ɹ���".equals(msg2)) {
											if (name != null && name != ""&& !"".equals(name)) {
												// file.write(new File(name));
											}
											msg = "����Ȩ�޿���վ����Ϣ�ɹ���";
											System.out.println("�и����ϴ�");
											dao.insertToDBGL(file, qid);
											
											log.write(msg, formBean.getZdid(),request.getRemoteAddr(),"����Ȩ�޿���վ����Ϣ");
											//---��Զ��վ��Ļ� 
											
											
											
										//����Ȩ�޿���վ����Ϣ�ɹ��� Ҫ�����жϽ�������
											if(formBean.getQxpdbz().equals("3")){
									    //����ǽ���Ļ� ��ô������Զ�ͨ�� ʡ����Զ�ͨ�� ����Ҫ���Ľ������ɺ�ֱ�����ɡ�������(վ���)  ������(scb)
										    Zdqxcxxg zdzg=new Zdqxcxxg();
										   String msga= zdzg.updatezdjb(formBean,lg);
										   
										   
										 //  if(msga.equals("�Զ�����ɹ���")){
											//   statusInfo.add("�Զ�����ɹ���");
										  // }else{
											//   statusInfo.add("�Զ�����ʧ�ܣ�");
										//   }
											}
											//��������׶� ���ܽ�ֱ������ ֻҪ�ǵ��������߱�ˡ��Ǻ˱�վ�㼴������������
											//�ܺĻ���ֵռ�ȣ�(�ܺĻ���ֵ-�ֹ�˾����)/(�ֹ�˾����)
											//����ʵ��ֵռ�ȣ�(����ʵ��ֵ-�ֹ�˾����)/(�ֹ�˾����)
											//����������ռ�ȣ�(����������-�ֹ�˾����)/(�ֹ�˾����) 
											//��������ռ�ȣ�(��������-�ֹ�˾����)/(�ֹ�˾����)
											//ֱ������ռ�ȣ�(ֱ������-�ֹ�˾����)/(�ֹ�˾����)
											System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
											if(formBean.getQxpdbz().equals("4")){
												System.out.println("i am here !!!");
												//����ռ�ȹ�ϵ
												Zdqxcxxg zg=new Zdqxcxxg();
												int x=0;
												x=zg.zdbgdj(formBean,lg);
											//	if(x>0){
													//  statusInfo.add("�Զ��жϵȼ��ɹ���");
												//}else{
												//	  statusInfo.add("�Զ��жϵȼ�ʧ�ܣ�");
												//}
												
											}
										
										} else {
											msg = "����Ȩ�޿���վ����Ϣʧ�ܣ�";
										}
										statusInfo.add(msg);
									} catch (Exception e) {
										statusInfo.add("�ڲ���������ϵ����Ա��");
										sendStatusInfo(request, response,statusInfo, bz);
										e.printStackTrace();
										return;
									}
									statusInfo.add("�ļ��ϴ��ɹ���");
								}
							}
						} else {
							// ��û���ϴ��ļ�
							System.out.println("û���ϴ��ļ�");
							String mm = "";
							if (statusInfo.size() == 1) {
								mm = statusInfo.get(0);
							}
							if (mm == null || statusInfo.size() == 0) {
								try {
									//dao.insertToDBGL(file, idd);
									String msg2 = "";
									msg2 = beana.modifyZdqxcxxg(formBean, "", name);
									if ("����Ȩ�޿���վ����Ϣ�ɹ���".equals(msg2)) {
										if (name != null && name != "" && !"".equals(name)) {
											// file.write(new File(path1+"/"+name));
										}
										msg = "����Ȩ�޿���վ����Ϣ�ɹ���";
										//����Ȩ�޿���վ����Ϣ�ɹ��� Ҫ�����жϽ�������
										if(formBean.getQxpdbz().equals("3")){
								    //����ǽ���Ļ� ��ô������Զ�ͨ�� ʡ����Զ�ͨ�� ����Ҫ���Ľ������ɺ�ֱ�����ɡ�������(վ���)  ������(scb)
									    Zdqxcxxg zg=new Zdqxcxxg();
									    Zdqxkz k=new Zdqxkz();
									  k=zg.maxqskzid(formBean.getZdid());
									   String msga= zg.updatezdjb(k,lg);
									//   if(msga.equals("�Զ�����ɹ���")){
										  // statusInfo.add("�Զ�����ɹ���");
									 //  }else{
										  // statusInfo.add("�Զ�����ʧ�ܣ�");
									 //  }
										}
										//��������׶� ���ܽ�ֱ������ ֻҪ�ǵ��������߱�ˡ��Ǻ˱�վ�㼴������������
										//�ܺĻ���ֵռ�ȣ�(�ܺĻ���ֵ-�ֹ�˾����)/(�ֹ�˾����)
										//����ʵ��ֵռ�ȣ�(����ʵ��ֵ-�ֹ�˾����)/(�ֹ�˾����)
										//����������ռ�ȣ�(����������-�ֹ�˾����)/(�ֹ�˾����) 
										//��������ռ�ȣ�(��������-�ֹ�˾����)/(�ֹ�˾����)
										//ֱ������ռ�ȣ�(ֱ������-�ֹ�˾����)/(�ֹ�˾����)
										System.out.println("qxpdbz:"+formBean.getQxpdbz()+" zdbzw:"+formBean.getZdbzw());
										if(formBean.getQxpdbz().equals("4")){
											System.out.println("i am here !!!");
											//����ռ�ȹ�ϵ
											Zdqxcxxg zg=new Zdqxcxxg();
											int x=0;
											x=zg.zdbgdj(formBean,lg);
										//	if(x>0){
											//	  statusInfo.add("�Զ��жϵȼ��ɹ���");
											//}else{
											//	  statusInfo.add("�Զ��жϵȼ�ʧ�ܣ�");
										//	}
											
										}
										log.write(msg, formBean.getZdid(), request.getRemoteAddr(), "����Ȩ�޿���վ����Ϣ");
									} else {
										msg = "����Ȩ�޿���վ����Ϣʧ�ܣ�";
									}
									statusInfo.add(msg);
								} catch (Exception e) {
									statusInfo.add("�ڲ���������ϵ����Ա��");
									sendStatusInfo(request, response, statusInfo,bz);
									e.printStackTrace();
									return;
								}
								//statusInfo.add("�ļ��ϴ��ɹ���");
							}
						}
					}
				}
				
			}else{
				statusInfo.add("�������Ĺ���ֱ��,����,�,������ʧ��,����ϵ����Ա!");
			}

			sendStatusInfo(request, response, statusInfo, bz);
		} else {// �ж��ļ��Ƿ�����ϴ�
			PrintWriter out = response.getWriter();
			String result = "0";
			// if(dao.fileIsUploaded())result = "1";//�жϱ����Ƿ��Ѿ��ϴ�������о͸��ǣ�
			// �������true ��û���ظ����� ����1�����false �����Ѿ��ϴ��˱��·ݵı���
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

	// ���������100��999
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
			System.out.println("���Ի�ȡbook�����sheet�ĸ���" + counts);
			Vector content = new Vector();
			if (counts == 3) {
				sheet = book.getSheet(2);// ���㿪ʼ�� 2���������sheet �ж��ǲ���11
				for (int i = 0; i < sheet.getRows(); i++) {
					Vector rows = new Vector();
					boolean isNull = true;
					for (int j = 0; j < sheet.getColumns(); j++) {
						Cell cell = sheet.getCell(0, 0);
						String result = cell.getContents();// ��ȡ��һ����Ԫ�������
						System.out.println("�ж�ģ��ֵ��" + result);
						rows.add(result);
						if (result != null && !result.equals("")) {
							isNull = false;
						}
					}
					if (!isNull) {
						content.add(rows);// �����Ϊ�� �浽������ ����
					} else {
						System.out.println("�п� ��  ������");
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
