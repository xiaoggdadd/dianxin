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
		JtReportBeanFactory reportFactory = new JtReportBeanFactory();//设置报表的名称和标志值
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
		//文件开始上传  
		if(null == confirm){
			File tempfile = new File(System.getProperty("java.io.tmpdir"));//采用系统临时文件目录   上传报表时 存报表的临时路径    最后删除
			 //System.out.println("暂时保存上传的信息"+filename);
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
				String[] aa= fileItems.toString().split(",");
				int ss=aa[4].lastIndexOf("\\")+1;
				s=aa[4].substring(ss,aa[4].length()); //截取上传文件名称
				//System.out.println("s:"+s+"ss:"+ss);
				//System.out.println("进不进啊"+s.equals("FieldName=daochu1"));
				
				//如果先点下载报表  再点 上传 报表    上传的文件名会长，多出来一部分  
				//如果直接点上传报表  上传的文件名称会短些    根据不同的情况进行上传文件名的截取
				if(s.equals(" FieldName=daochu1")){ 
					int sss=aa[11].lastIndexOf("\\")+1;
					c=aa[11].substring(sss,aa[11].length());
					//System.out.println("cc到底是什么，给不给钱啊"+c);
				}else{
					int bb = aa[6].lastIndexOf("\\")+1;
					c = aa[6].substring(bb,aa[6].length());
					//System.out.println("破新疆给不给钱啊"+c);
				}
			
				
			} catch (FileUploadException e) {   
				statusInfo.add("上传文件超出大小限制（10M）");
				sendStatusInfo(request,response,statusInfo); //把错误信息 传到前台
				return;
			}
		//	System.out.println("2222222测试："+fileItems.toString());
			//System.out.println("33333333测试："+fileName);
		        //Vector head=temp.getColumns(filename);
			String tempfile1=tempfile+"\\"+c;
			Vector biaozhi=this.getContentbiaozhi(tempfile1);//判断是否是  下载的我们的报表   第三个sheet里第一个单元格内容是12
			if(biaozhi.isEmpty()){
				statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
				sendStatusInfo(request,response,statusInfo);//把错误信息 传到前台
				return;
			}else{
				
			Object[] a=biaozhi.toArray();
			String id="";
			//System.out.println("长度："+a.length);
			  for(int i=0;i<a.length;i++){ //应该是重0开始循环判断  
				   Object[] b=((Vector)a[i]).toArray();
				  id=b[0].toString().trim();
				 // System.out.println("11模板标准："+id);
				  if(!id.equals("12")){
						statusInfo.add("上传失败，因为您上传的报表模版不是在系统中下载的！请先下载再上传！！");
						sendStatusInfo(request,response,statusInfo);//把错误信息 传到前台
						return;
					}
			  }
			}
		  /*   if(fileName.equals("附件1：地市基站用电量信息汇总表.xls")){
		    	     Vector content = this.getContent(tempfile1);//s把报表数据读出来   
	        	     JtReportjz sellin = new JtReportjz();
		        	 CountForm form = new CountForm();
		             String wrong = sellin.getWrong(content,form);
		             statusInfo.add(wrong);
		 		}
		       if(fileName.equals("附件2：基站用电量汇总分析表.xls")){
		    	   Vector content = this.getContent(tempfile1);
		    	   JtReportjz2 sellin = new JtReportjz2();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form);
                   statusInfo.add(wrong);
		 		}
		       if(fileName.equals("附件7：通信局房用电量汇总表.xls")){
		    	   Vector content = this.getContent1(tempfile1);
	 			   JtReportjz3 sellin = new JtReportjz3();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form,yue);
                   statusInfo.add(wrong);
		 		}
		       if(fileName.equals("附件6：IDC用电量汇总表.xls")){
		    	   Vector content = this.getContent(tempfile1);
	 			   JtReportjz4 sellin = new JtReportjz4();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form);
                   statusInfo.add(wrong);
		 		}
		       if(fileName.equals("附件4：地市接入网机房用电量信息汇总.xls")){
		    	   Vector content = this.getContent(tempfile1);
	 			   JtReportjz5 sellin = new JtReportjz5();
       	           CountForm form = new CountForm();
                   String wrong = sellin.getWrong(content,form);
                   statusInfo.add(wrong);
		 		}
			*/
			for(Object o:fileItems){
				FileItem file = (FileItem)o;
				//System.out.println("999999："+file.isFormField());
				if(!file.isFormField()){
				//	System.out.println("33333333333333333333333333333333"+tempfile1);
					dao.isAvailableToUpload(statusInfo, file);	//检测当前文件是否可以上传（满足文件大小、格式、是否上传过等条件）
				
					
					//System.out.println("错误信息statusInfo.size()"+statusInfo.size());
					String mm="";
					if(statusInfo.size()==1){
						mm=statusInfo.get(0);
					}
					if(mm==null||statusInfo.size()==0){
						try {
							dao.insertToDB(file);//把数据表存到数据库
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
		}
		else{//判断文件是否可以上传
			PrintWriter out = response.getWriter();
			String result = "0";
			if(dao.fileIsUploaded())result = "1";//判断报表是否已经上传（如果有就覆盖） 如果返回true 就没有重复数据 返回1，如果false 就是已经上传了本月份的报表
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
		System.out.println("dir2dir2dir2dir2"+dir1);
		// String dir1 = path.getPhysicalPath("/temp/", 0); // 传参数
		System.out.println("dirl==" + dir1);
		fu.setRepositoryPath(dir1);
		// 开始读取上传信息
		List fileItems = fu.parseRequest(request);
		// 依次处理每个上传的文件
		Iterator iter = fileItems.iterator();
		System.out.println("dirl 22 ==" + dir1);
		// 正则匹配，过滤路径取文件名
		String regExp = ".+\\\\(.+)$";
		// 过滤掉的文件类型
		// String[] errorType={".exe",".com",".cgi",".asp"};
		Pattern p = Pattern.compile(regExp);
		String zipname = null;
		int num = 0;
		File file = null;
		while (iter.hasNext()) {
			System.out.println("dirl 33 ==" + dir1);
			FileItem item = (FileItem) iter.next();
			// 忽略其他不是文件域的所有表单信息
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
						// 保存上传的文件到指定的目录
						String fileType = ".tmp";
						zipname = filename + fileType;
						String zz=dir1 +zipname;
						session.setAttribute("filename", zz);
						System.out.println("保存上传的文件到指定的目录" + zz);
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
            System.out.println("rows  Excel行数=="+sheet.getRows());
            for (int i = 3; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==5){  //如果是文本的就不用转换  id
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
                	System.out.println("行空 了  结束了");
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
			 //获取book里面的sheet的个数  方法一
			 //Sheet[] sheets=book.getSheets();
			 //System.out.println("获取book里面的sheet的个数:"+sheets.length);
			 //获取book里面的sheet的个数  方法二
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
	//通信机房
	public Vector getContent1(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel行数=="+sheet.getRows());
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
                	System.out.println("行空 了  结束了");
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
	 * 将执行信息发送至当前页面
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
