package com.noki.jtreport.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.ammeterdegree.servlet.DaoruServlet;
import com.noki.jizhan.daoru.CountForm;
import com.noki.jizhan.daoru.ReadXLS_ZD;
import com.noki.jtreport.shi.JtReportBeanFactory;
import com.noki.jtreport.shi.upload.javabean.JtReportUploadDao;
import com.noki.mobi.common.Account;
import com.noki.util.Path;

@SuppressWarnings("serial")
public class UploadJtReportServlet extends HttpServlet {
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setContentType(CONTENT_TYPE);
		 request.setCharacterEncoding("utf-8");
		 HttpSession session = request.getSession();
		Account account = (Account)(request.getSession().getAttribute("account"));
		JtReportBeanFactory reportFactory = new JtReportBeanFactory();//���ñ�������ƺͱ�־ֵ
		 String loginName = (String) session.getAttribute("loginName");
		// String accountname=account.getAccountName();
		String reportType = request.getParameter("reportType");
		String month = request.getParameter("month");
		String yue=month.substring(5,7);
		System.out.println("monthmonthmonth"+yue);
		String confirm = request.getParameter("confirm");
		String fileName = reportFactory.getReportNameByReportType(reportType);
        System.out.println("fileNamefileName"+fileName);
		JtReportUploadDao dao = new JtReportUploadDao(account,month,reportType,fileName);
		String filename="";
		//�ļ���ʼ�ϴ�  
		if(null == confirm){
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//����ϵͳ��ʱ�ļ�Ŀ¼   �ϴ�����ʱ �汨�����ʱ·��    ���ɾ��
			 //System.out.println("��ʱ�����ϴ�����Ϣ"+filename);
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
				String[] aa= fileItems.toString().split(",");
				int ss=aa[4].lastIndexOf("\\")+1;
				s=aa[4].substring(ss,aa[4].length()); //��ȡ�ϴ��ļ�����
				//System.out.println("s:"+s+"ss:"+ss);
				//System.out.println("��������"+s.equals("FieldName=daochu1"));
				
				//����ȵ����ر���  �ٵ� �ϴ� ����    �ϴ����ļ����᳤�������һ����  
				//���ֱ�ӵ��ϴ�����  �ϴ����ļ����ƻ��Щ    ���ݲ�ͬ����������ϴ��ļ����Ľ�ȡ
				if(s.equals(" FieldName=daochu1")){ 
					int sss=aa[11].lastIndexOf("\\")+1;
					c=aa[11].substring(sss,aa[11].length());
					//System.out.println("cc������ʲô��������Ǯ��"+c);
				}else{
					int bb = aa[6].lastIndexOf("\\")+1;
					c = aa[6].substring(bb,aa[6].length());
					//System.out.println("���½�������Ǯ��"+c);
				}
			
				
			} catch (FileUploadException e) {   
				statusInfo.add("�ϴ��ļ�������С���ƣ�10M��");
				sendStatusInfo(request,response,statusInfo); //�Ѵ�����Ϣ ����ǰ̨
				return;
			}
		//	System.out.println("2222222���ԣ�"+fileItems.toString());
			//System.out.println("33333333���ԣ�"+fileName);
		        //Vector head=temp.getColumns(filename);
			String tempfile1=tempfile+"\\"+c;
			Vector biaozhi=this.getContentbiaozhi(tempfile1);//�ж��Ƿ���  ���ص����ǵı���   ������sheet���һ����Ԫ��������12
			if(biaozhi.isEmpty()){
				statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
				sendStatusInfo(request,response,statusInfo);//�Ѵ�����Ϣ ����ǰ̨
				return;
			}else{
				
			Object[] a=biaozhi.toArray();
			String id="";
			//System.out.println("���ȣ�"+a.length);
			  for(int i=0;i<a.length;i++){ //Ӧ������0��ʼѭ���ж�  
				   Object[] b=((Vector)a[i]).toArray();
				  id=b[0].toString().trim();
				 // System.out.println("11ģ���׼��"+id);
				  if(!id.equals("12")){
						statusInfo.add("�ϴ�ʧ�ܣ���Ϊ���ϴ��ı���ģ�治����ϵͳ�����صģ������������ϴ�����");
						sendStatusInfo(request,response,statusInfo);//�Ѵ�����Ϣ ����ǰ̨
						return;
					}
			  }
			}
		  /*   if(fileName.equals("����1�����л�վ�õ�����Ϣ���ܱ�.xls")){
		    	     Vector content = this.getContent(tempfile1);//s�ѱ������ݶ�����   
	        	     JtReportjz sellin = new JtReportjz();
		        	 CountForm form = new CountForm();
		             String wrong = sellin.getWrong(content,form);
		             statusInfo.add(wrong);
		 		}
		       if(fileName.equals("����2����վ�õ������ܷ�����.xls")){
		    	   Vector content = this.getContent(tempfile1);
		    	   JtReportjz2 sellin = new JtReportjz2();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form);
                   statusInfo.add(wrong);
		 		}
		       if(fileName.equals("����7��ͨ�žַ��õ������ܱ�.xls")){
		    	   Vector content = this.getContent1(tempfile1);
	 			   JtReportjz3 sellin = new JtReportjz3();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form,yue);
                   statusInfo.add(wrong);
		 		}
		       if(fileName.equals("����6��IDC�õ������ܱ�.xls")){
		    	   Vector content = this.getContent(tempfile1);
	 			   JtReportjz4 sellin = new JtReportjz4();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form);
                   statusInfo.add(wrong);
		 		}
		       if(fileName.equals("����4�����н����������õ�����Ϣ����.xls")){
		    	   Vector content = this.getContent(tempfile1);
	 			   JtReportjz5 sellin = new JtReportjz5();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form);
                   statusInfo.add(wrong);
		 		}
			*/
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				//System.out.println("999999��"+file.isFormField());
				if(!file.isFormField()){
				//	System.out.println("33333333333333333333333333333333"+tempfile1);
					dao.isAvailableToUpload(statusInfo, file);	//��⵱ǰ�ļ��Ƿ�����ϴ��������ļ���С����ʽ���Ƿ��ϴ�����������
				
					
					//System.out.println("������ϢstatusInfo.size()"+statusInfo.size());
					String mm="";
					if(statusInfo.size()==1){
						mm=statusInfo.get(0);
					}
					if(mm==null||statusInfo.size()==0){
						try {
							dao.insertToDB(file);//�����ݱ�浽���ݿ�
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
		}
		else{//�ж��ļ��Ƿ�����ϴ�
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";//�жϱ����Ƿ��Ѿ��ϴ�������о͸��ǣ� �������true ��û���ظ����� ����1�����false �����Ѿ��ϴ��˱��·ݵı���
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
		System.out.println("dir2dir2dir2dir2"+dir1);
		// String dir1 = path.getPhysicalPath("/temp/", 0); // ������
		System.out.println("dirl==" + dir1);
		fu.setRepositoryPath(dir1);
		// ��ʼ��ȡ�ϴ���Ϣ
		List fileItems = fu.parseRequest(request);
		// ���δ���ÿ���ϴ����ļ�
		Iterator iter = fileItems.iterator();
		System.out.println("dirl 22 ==" + dir1);
		// ����ƥ�䣬����·��ȡ�ļ���
		String regExp = ".+\\\\(.+)$";
		// ���˵����ļ�����
		// String[] errorType={".exe",".com",".cgi",".asp"};
		Pattern p = Pattern.compile(regExp);
		String zipname = null;
		int num = 0;
		File file = null;
		while (iter.hasNext()) {
			System.out.println("dirl 33 ==" + dir1);
			FileItem item = (FileItem) iter.next();
			// �������������ļ�������б���Ϣ
			if (!item.isFormField()) {
				System.out.println("dirl 44 ==" + dir1);
				String name = item.getName();
				long size = item.getSize();
				System.out.println("name==" + name);
				if ((name == null || name.equals("")) && size == 0) {
					continue;
				}
				System.out.println("dirl 5 ==" + dir1);
				Matcher m = p.matcher(name);
				System.out.println("dirl 6 ==" + dir1);
				boolean result = true; // m.find();
				System.out.println("dirl 7 ==" + result);
				if (result) {
					System.out.println("dirl 55 ==" + dir1);
					try {
						// �����ϴ����ļ���ָ����Ŀ¼
						String fileType = ".tmp";
						zipname = filename + fileType;
						String zz=dir1 +zipname;
						session.setAttribute("filename", zz);
						System.out.println("�����ϴ����ļ���ָ����Ŀ¼" + zz);
						file = new File(dir1 + zipname);
						item.write(file);
					} catch (Exception e) {
						// out.println(e);
						e.printStackTrace();
					}

				} else {
					throw new IOException("fail to upload");
				}
			}
		}
		return filename;
	}
	public Vector getContent(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel����=="+sheet.getRows());
            for (int i = 3; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==5){  //������ı��ľͲ���ת��  id
                    	System.out.println("-111-"+result);
                    }else if(j==8){
                    	System.out.println("-111-"+result);
                    }else if(j==11){
                    	System.out.println("-111-"+result);
                    }else if(j==14){
                    	System.out.println("-111-"+result);
                    }else{
                    	 if(!"".equals(result)&&result!=null){
                         	  if(cell.getType().equals(CellType.NUMBER)){
                         		  
                             	  NumberCell numberCell = (NumberCell)sheet.getCell(j, i);
                                   double namberValue = numberCell.getValue();
                                   result=namberValue+"";
                               }
                           }
                    }
                   
                   
                  
                    System.out.println(result);
                    rows.add(result);
                    if (result != null && !result.equals("")&& !result.equals(" ")) {
                        isNull = false;
                    }
                }
                if (!isNull) {
                    content.add(rows);
                }
                else{
                	System.out.println("�п� ��  ������");
                	break;
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
	public Vector getContentbiaozhi(String filename){
		try{
			Sheet sheet=null;
			 Workbook book = Workbook.getWorkbook(new File(filename));
			 //��ȡbook�����sheet�ĸ���  ����һ
			 //Sheet[] sheets=book.getSheets();
			 //System.out.println("��ȡbook�����sheet�ĸ���:"+sheets.length);
			 //��ȡbook�����sheet�ĸ���  ������
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
	//ͨ�Ż���
	public Vector getContent1(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel����=="+sheet.getRows());
            for (int i = 4; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==3){
                    	
                    }else{
                    	 if(!"".equals(result)&&result!=null){
                         	  if(cell.getType().equals(CellType.NUMBER)){
                         		  
                             	  NumberCell numberCell = (NumberCell)sheet.getCell(j, i);
                                   double namberValue = numberCell.getValue();
                                   result=namberValue+"";
                               }
                           }
                    }
                   
                   
                  
                    System.out.println("-"+result);
                    rows.add(result);
                    if (result != null && !result.equals("")) {
                        isNull = false;
                    }
                }
                if (!isNull) {
                    content.add(rows);
                }
                else{
                	System.out.println("�п� ��  ������");
                	break;
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

	/**
	 * ��ִ����Ϣ��������ǰҳ��
	 * @param request
	 */
	public void sendStatusInfo(HttpServletRequest request,HttpServletResponse response,List<?> statusInfo){
		request.setAttribute("statusInfo", statusInfo);
		try {
			request.getRequestDispatcher("/web/cx/groupdata_shi.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
