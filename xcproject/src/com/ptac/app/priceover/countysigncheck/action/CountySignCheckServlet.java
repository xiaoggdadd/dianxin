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
		String path = request.getContextPath();//��·��
		CountySignCheckDao dao = new CountySignCheckDaoImpl();
		UpLoadUtil dao1 = new UpLoadUtil();
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if("sign".equals(action)){
			Account account = (Account)session.getAttribute("account");
			String loginid = account.getAccountId();
			boolean flag = dao.selectNoSign(loginid);//�Ƿ���δǩ�յ���
			if(flag){
				response.sendRedirect(path + "/web/appJSP/priceover/countysigncheck/sign.jsp");
			}else{
				response.sendRedirect(path + "/web/appJSP/priceover/countysigncheck/chaxun.jsp");
			}
		}else if("signed".equals(action)){
			String loginid = request.getParameter("loginid");
			String loginName = request.getParameter("loginName");
			boolean flg = dao.sign(loginid, loginName);//ǩ��
			if(flg){//���³ɹ�
				response.sendRedirect(path + "/web/appJSP/priceover/countysigncheck/chaxun.jsp");
			}else{
				String  url = path + "/web/appJSP/priceover/countysigncheck/sign.jsp" ;
				 session.setAttribute("url", url);
			     session.setAttribute("msg", "ǩ��ʧ��,������!");
			     response.sendRedirect(path + "/web/msg.jsp");
			}	
		}else if("chaxun".equals(action) || "daochu".equals(action)){
			String loginid = request.getParameter("accountid");
			String shi = request.getParameter("shi");
			String xian = request.getParameter("xian");
			String zdsx = request.getParameter("jzproperty");
			String sjzt = request.getParameter("sjzt");//״̬(����)
			String bzyf = request.getParameter("beginyue");//�����·�
			String bl1 = request.getParameter("bl1");
			String bl2 = request.getParameter("bl2");
			String zdname = request.getParameter("zdname");
			String zdid = request.getParameter("zdid");
			String gdfs = request.getParameter("gdfsa");
			String state = request.getParameter("state");//�����
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
			List<String> statusInfo = new ArrayList<String>();//��ʾ�Ĵ�����Ϣ
			String peid = request.getParameter("peid");
			Map<String,String> map = new HashMap<String, String>();
			File tempfile = new File(System.getProperty("java.io.tmpdir"));// ����ϵͳ��ʱ�ļ�Ŀ¼
			// �ж�enctype�����Ƿ�Ϊmultipart/form-data  
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
			List<String> items = new ArrayList<String>();
			try {
			// Create a factory for disk-based file items  
			DiskFileItemFactory factory = new DiskFileItemFactory();  
			  
			// ���ϴ��ļ�̫��ʱ����Ϊ�������ʹ�õ��ڴ������޵ģ����Դ�ʱҪͨ����ʱ�ļ���ʵ���ϴ��ļ��ı���  
			// �˷����������Ƿ�ʹ����ʱ�ļ����ٽ�ֵ����λ���ֽڣ�
			// ����ļ���С����SizeThreshold���򱣴浽��ʱĿ¼  
			factory.setSizeThreshold(4096);  
			  
			// ����һ�����ʹ�ã�������ʱ�ļ���·��������·����  
			factory.setRepository(tempfile);  
			  
			// Create a new file upload handler  
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(3*1024*1024);//���õ��ļ��ϴ���С
			upload.setSizeMax(6*1024*1024);//�������ļ��ϴ���С
			upload.setHeaderEncoding(request.getCharacterEncoding());
			// Parse the request  
				items = upload.parseRequest(request);
			}catch(FileUploadBase.FileSizeLimitExceededException e1) {
				 String  url = path + "/web/check/close.jsp" ;
				 session.setAttribute("url", url);
			     session.setAttribute("msg", "�����ϴ��ļ���С���ܳ���3M,������!");
			     response.sendRedirect(path + "/web/msg.jsp");
				return;
			}catch(FileUploadBase.SizeLimitExceededException e2){
				 String  url = path + "/web/check/close.jsp" ;
				 session.setAttribute("url", url);
			     session.setAttribute("msg", "�ϴ��ļ��ܴ�С���ܳ���6M,������!");
			     response.sendRedirect(path + "/web/msg.jsp");
				return;
			}catch (FileUploadException e3) {
				e3.printStackTrace();
				statusInfo.add("������������ʱ�����˴���,����ϵ����Ա!");
				sendStatusInfo(request, response, statusInfo,peid);// �Ѵ�����Ϣ ����ǰ̨
				return;
			}
			  
			Iterator iter = items.iterator();  
			CheckBean bean = new CheckBean();
			while (iter.hasNext()) {  
			    FileItem item = (FileItem) iter.next();  
			  
			    if (item.isFormField()) {  
			        //�������ͨ���ֶ�  
			        String name = item.getFieldName();  
			        map.put(name, item.getString(request.getCharacterEncoding()).trim());
			    } else {  
			        //������ļ��ֶ�  
			        String fieldName = item.getFieldName();  
			            String fileName = item.getName();  
						String[] aa= item.toString().split(",");
						int ss=aa[1].lastIndexOf("\\")+1;
						String s=aa[1].substring(ss,aa[1].length()); //��ȡ�ϴ��ļ�����
						//�ļ�url
						String tempfile1=tempfile+"\\"+s;
						System.out.println(tempfile1);
					
						//----------------------------------------------------
						//����и���
						if(fileName!=null&&!fileName.equals("")&&!fileName.equals("null")&&fileName.length()>0&&fileName!=""&&fileName!=" "){
							Boolean gs=dao1.isAvailableToUpload(item);	//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
							if (gs){
								if("fileUp1".equals(fieldName)){
									statusInfo.add("�ϴ�ʧ�ܣ��õ��ͬ�����ϴ����ļ���׺������Ϊ.xls");
								}else if("fileUp2".equals(fieldName)){
									statusInfo.add("�ϴ�ʧ�ܣ�æʱ���������ϴ����ļ���׺������Ϊ.xls");
								}else if("fileUp3".equals(fieldName)){
									statusInfo.add("�ϴ�ʧ�ܣ�ҵ����������ϸ�ϴ����ļ���׺������Ϊ.xls");
								}
								sendStatusInfo(request,response,statusInfo,peid);//�Ѵ�����Ϣ ����ǰ̨
								return;
							}else{
								Vector biaozhi = this.getContentbiaozhi(tempfile1);// �ж��Ƿ������ص����ǵı��������sheet���һ����Ԫ��������88
								if (biaozhi.isEmpty()) {
									if("fileUp1".equals(fieldName)){
										statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ����õ��ͬ����ģ�治����ϵͳ�����صģ������������ϴ�����");
									}else if("fileUp2".equals(fieldName)){
										statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ���æʱ��������ģ�治����ϵͳ�����صģ������������ϴ�����");
									}else if("fileUp3".equals(fieldName)){
										statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ���ҵ����������ϸģ�治����ϵͳ�����صģ������������ϴ�����");
									}
									sendStatusInfo(request, response, statusInfo,peid);// �Ѵ�����Ϣ ����ǰ̨
									return;
								} else {

									Object[] a = biaozhi.toArray();
									String ida = "";
									for (int i = 0; i < a.length; i++) { // Ӧ������0��ʼѭ���ж�
										Object[] b = ((Vector) a[i]).toArray();
										ida = b[0].toString().trim();
										if (!ida.equals("88")) {
											if("fileUp1".equals(fieldName)){
												statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ����õ��ͬ����ģ�治����ϵͳ�����صģ������������ϴ�����");
											}else if("fileUp2".equals(fieldName)){
												statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ���æʱ��������ģ�治����ϵͳ�����صģ������������ϴ�����");
											}else if("fileUp3".equals(fieldName)){
												statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ���ҵ����������ϸģ�治����ϵͳ�����صģ������������ϴ�����");
											}
											sendStatusInfo(request, response,statusInfo,peid);// �Ѵ�����Ϣ ����ǰ̨
											return;
										}
									}
								}

							try {
								if("fileUp1".equals(fieldName)){
									dao1.insertToDB("YDHTFJ", "PRICEPROCEDURE", "ID", peid, item);//�����ݱ�浽���ݿ�
								}else if("fileUp2".equals(fieldName)){
									dao1.insertToDB("MSZYYJ", "PRICEPROCEDURE", "ID", peid, item);//�����ݱ�浽���ݿ�
								}else if("fileUp3".equals(fieldName)){
									dao1.insertToDB("YZJFDLMX", "PRICEPROCEDURE", "ID", peid, item);//�����ݱ�浽���ݿ�
								}

							} catch (Exception e) {
								e.printStackTrace();
								statusInfo.add("�ڲ���������ϵ����Ա��");
								sendStatusInfo(request, response,statusInfo,peid);// �Ѵ�����Ϣ ����ǰ̨
								return;
							}
							if("fileUp1".equals(fieldName)){
								statusInfo.add("�õ��ͬ�����ϴ��ɹ���");
							}else if("fileUp2".equals(fieldName)){
								statusInfo.add("æʱ���������ϴ��ɹ���");
							}else if("fileUp3".equals(fieldName)){
								statusInfo.add("ҵ����������ϸ�ϴ��ɹ���");
							}
					     }
					   } 
			    }  
			}  
			String accountid = map.get("accountid");
			String personnal = map.get("personnal");
			String yzydlx = map.get("yzydlx").trim();//ҵ���õ�����
			String yzhdjcdj = map.get("yzhdjcdj").trim();//ҵ������������
			String hzprice = map.get("hzprice").trim();//��׼����
			String jfdlzb = map.get("jfdlzb").trim();//������ռ��
			String pdlzb = map.get("pdlzb").trim();//ƽ����ռ��
			String zgdjcdj = map.get("zgdjcdj").trim();//ֱ�����������
			String gfdlzb = map.get("gfdlzb").trim();//�߷����ռ��
			String gdlzb = map.get("gdlzb").trim();//�ȵ���ռ��
			String byqrl = map.get("byqrl").trim();//��ѹ������
			String beilv = map.get("beilv").trim();//����
			String ydsx = map.get("ydsx").trim();//�õ�����
			String xbsl = map.get("xbsl").trim();//�߱�����
			String xbsdl = map.get("xbsdl").trim();//�߱������
			String glfsfxs = map.get("glfsfxs").trim();//������ϸ�ϵ��
			String mssfxs = map.get("mssfxs").trim();//æʱ�ϸ�ϵ��
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
			
			//if("���ۺ�ʵ��Ϣ�ύ�ɹ�!".equals(msg)){
//			String  url = path + "/web/check/close.jsp" ;
//			 session.setAttribute("url", url);
//		     session.setAttribute("msg", msg);
//		     response.sendRedirect(path + "/web/msg.jsp");
			//}else{
				statusInfo.add(msg);
				sendStatusInfo(request, response,statusInfo,peid);// ����Ϣ ����ǰ̨
			//}
		}
	
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
