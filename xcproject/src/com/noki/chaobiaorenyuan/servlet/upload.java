package com.noki.chaobiaorenyuan.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.noki.chaobiaorenyuan.Dao.uploadDao;
import com.noki.chaobiaorenyuan.bean.WenJian;
import com.noki.mobi.common.Account;

public class upload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public upload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    //调用DAO方法
	    uploadDao dao = new uploadDao();
	    
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		//获取需要写入数据库的内容
		HttpSession session = request.getSession();	//创建session
		Account account = (Account) session.getAttribute("account");	//登录用户实体类
		String accountid = account.getAccountId();//登录用户的ID（上传人ID）
		String accountname = account.getAccountName();//登录用户的名称（上传人登录名）
	    String dbID = "";//电表ID（文件对应的电表ID）
		String dbName = "";//电表名称（文件对应电表的名称）
		String filename = "";//上传文件的名称
		String saveFilename = "";//上传文件的保存名称
		String realSavePath = "";//文件的保存目录
		String liuchengId = "";
		String liuchengName ="";
		//上传时间
    	Date date= new Date();//创建一个时间对象，获取到当前的时间
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
    	String str = sdf.format(date);//将当前时间格式化为需要的类型
		//封装文件上传参数
		Map<String, String> params = new HashMap<String, String>();
		//创建页面输出流
		PrintWriter out = response.getWriter();
		//String savePath = "D:\\File"; 	//文件保存路径（写死）
		String savePath = dao.filebaocun();	//从数据库取出路径
		//获取本地目录
        File file = new File(savePath);
        //判断上传文件的保存的本地目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdirs();
        }
        //临时文件保存目录
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/linshi");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// 创建临时目录
			tmpFile.mkdir();
		}

        //判断
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart == true) {
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
				factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
				// 设置上传时生成的临时文件的保存目录
				factory.setRepository(tmpFile);
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 监听文件上传进度
				upload.setProgressListener(new ProgressListener() {
					public void update(long pBytesRead, long pContentLength,
							int arg2) {
						//System.out.println("文件大小为：" + pContentLength + ",当前已处理："+ pBytesRead);
						/**
						 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
						 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
						 */
					}
				});
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (!ServletFileUpload.isMultipartContent(request)) {
					// 按照传统方式获取数据
					return;
				}
				// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
				upload.setFileSizeMax(1024 * 1024 * 10);	//修改上传文件大小验证，现最大限制为10M
				// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
				upload.setSizeMax(1024 * 1024 * 10);
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {
					// 如果fileitem中封装的是普通输入项的数据
					if (item.isFormField()) {
						String name = item.getFieldName();
						// 解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						// value = new String(value.getBytes("iso8859-1"),"UTF-8");
						//System.out.println(name + "=" + value);
						params.put(name, value); //给map赋值
					} else {// 如果fileitem中封装的是上传文件
							// 得到上传的文件名称，
						filename = item.getName();
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						// 得到上传文件的扩展名
						String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
						// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
						//System.out.println("上传的文件的扩展名是：" + fileExtName);
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 得到文件保存的名称
						saveFilename = makeFileName(filename);
						// 得到文件的保存目录
						realSavePath = makePath(saveFilename, savePath);
						// 创建一个文件输出流
						FileOutputStream o = new FileOutputStream(realSavePath + "\\" + saveFilename);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
							// + filename)当中
							o.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						o.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						out.println("文件上传成功！");
						System.out.println("文件上传成功！");
					}
				}
				 dbID = params.get("dbID");		//文件对应电表ID
				 dbName = params.get("dbName");	//文件对应电表名称
				 liuchengId = params.get("liuchengId");	//文件对应的流程ID
				 if(liuchengId == "0" || liuchengId.equals("0")){
					 liuchengName = "电表新添附件";
				 }else{
					 liuchengName = dao.fileliucheng(liuchengId);
				 }
				//db数据库
			    System.out.println("上传人ID"+accountid);
			    System.out.println("上传人登录名"+accountname);
			    System.out.println("电表ID（文件对应的电表ID）"+dbID);
			    System.out.println("电表名称（文件对应电表的名称）"+dbName);
			    System.out.println("文件的原名称"+filename);
			    System.out.println("文件的保存名称"+saveFilename);
			    System.out.println("文件的保存目录"+realSavePath);
			    System.out.println("上传时间"+str);
			    System.out.println("上传文件对应流程ID"+liuchengId);
			    System.out.println("上传文件对应流程名称"+liuchengName);
			    //获取实体类并赋值
			    WenJian wj = new WenJian(accountid,accountname,str,realSavePath,filename,saveFilename,liuchengId,liuchengName,dbID,dbName);
			    //调用DAO方法
			    int jieguo = dao.addWJ(wj);
			    if(jieguo > 0){
			    	System.out.println("数据库写入成功");
			    }else{
			    	System.out.println("数据库写入失败");
			    }
			} catch (FileUploadBase.FileSizeLimitExceededException e) {
				//e.printStackTrace();
				out.println("单个文件超出最大值！！！");
				System.out.println("单个文件超出最大值！！！");
				return;
			} catch (FileUploadBase.SizeLimitExceededException e) {
				//e.printStackTrace();
				out.println("上传文件的总的大小超出限制的最大值！！！");
				System.out.println("上传文件的总的大小超出限制的最大值！！！");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				out.println("文件上传失败！请及时联系管理员");
				System.out.println("文件上传失败！请及时联系管理员");
			}
		} else {
			out.println("the enctype must be multipart/form-data");
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @param filename 文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename) { 
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * @Method: makePath
	 * @Description:
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */
	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		//String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
																// upload\3\5
		String dir = savePath; //未定义子文件夹									
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}
}
