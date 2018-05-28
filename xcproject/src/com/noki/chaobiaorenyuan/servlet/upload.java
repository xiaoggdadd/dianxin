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
	    //����DAO����
	    uploadDao dao = new uploadDao();
	    
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		//��ȡ��Ҫд�����ݿ������
		HttpSession session = request.getSession();	//����session
		Account account = (Account) session.getAttribute("account");	//��¼�û�ʵ����
		String accountid = account.getAccountId();//��¼�û���ID���ϴ���ID��
		String accountname = account.getAccountName();//��¼�û������ƣ��ϴ��˵�¼����
	    String dbID = "";//���ID���ļ���Ӧ�ĵ��ID��
		String dbName = "";//������ƣ��ļ���Ӧ�������ƣ�
		String filename = "";//�ϴ��ļ�������
		String saveFilename = "";//�ϴ��ļ��ı�������
		String realSavePath = "";//�ļ��ı���Ŀ¼
		String liuchengId = "";
		String liuchengName ="";
		//�ϴ�ʱ��
    	Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
    	String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		//��װ�ļ��ϴ�����
		Map<String, String> params = new HashMap<String, String>();
		//����ҳ�������
		PrintWriter out = response.getWriter();
		//String savePath = "D:\\File"; 	//�ļ�����·����д����
		String savePath = dao.filebaocun();	//�����ݿ�ȡ��·��
		//��ȡ����Ŀ¼
        File file = new File(savePath);
        //�ж��ϴ��ļ��ı���ı���Ŀ¼�Ƿ����
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"Ŀ¼�����ڣ���Ҫ����");
            //����Ŀ¼
            file.mkdirs();
        }
        //��ʱ�ļ�����Ŀ¼
		String tempPath = this.getServletContext().getRealPath("/WEB-INF/linshi");
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// ������ʱĿ¼
			tmpFile.mkdir();
		}

        //�ж�
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart == true) {
			try {
				// ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
				// 1������һ��DiskFileItemFactory����
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// ���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
				factory.setSizeThreshold(1024 * 100);// ���û������Ĵ�СΪ100KB�������ָ������ô�������Ĵ�СĬ����10KB
				// �����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
				factory.setRepository(tmpFile);
				// 2������һ���ļ��ϴ�������
				ServletFileUpload upload = new ServletFileUpload(factory);
				// �����ļ��ϴ�����
				upload.setProgressListener(new ProgressListener() {
					public void update(long pBytesRead, long pContentLength,
							int arg2) {
						//System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���"+ pBytesRead);
						/**
						 * �ļ���СΪ��14608,��ǰ�Ѵ���4096 �ļ���СΪ��14608,��ǰ�Ѵ���7367
						 * �ļ���СΪ��14608,��ǰ�Ѵ���11419 �ļ���СΪ��14608,��ǰ�Ѵ���14608
						 */
					}
				});
				// ����ϴ��ļ�������������
				upload.setHeaderEncoding("UTF-8");
				// 3���ж��ύ�����������Ƿ����ϴ���������
				if (!ServletFileUpload.isMultipartContent(request)) {
					// ���մ�ͳ��ʽ��ȡ����
					return;
				}
				// �����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
				upload.setFileSizeMax(1024 * 1024 * 10);	//�޸��ϴ��ļ���С��֤�����������Ϊ10M
				// �����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
				upload.setSizeMax(1024 * 1024 * 10);
				// 4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem item : list) {
					// ���fileitem�з�װ������ͨ�����������
					if (item.isFormField()) {
						String name = item.getFieldName();
						// �����ͨ����������ݵ�������������
						String value = item.getString("UTF-8");
						// value = new String(value.getBytes("iso8859-1"),"UTF-8");
						//System.out.println(name + "=" + value);
						params.put(name, value); //��map��ֵ
					} else {// ���fileitem�з�װ�����ϴ��ļ�
							// �õ��ϴ����ļ����ƣ�
						filename = item.getName();
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺
						// c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
						// �����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						// �õ��ϴ��ļ�����չ��
						String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
						// �����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
						//System.out.println("�ϴ����ļ�����չ���ǣ�" + fileExtName);
						// ��ȡitem�е��ϴ��ļ���������
						InputStream in = item.getInputStream();
						// �õ��ļ����������
						saveFilename = makeFileName(filename);
						// �õ��ļ��ı���Ŀ¼
						realSavePath = makePath(saveFilename, savePath);
						// ����һ���ļ������
						FileOutputStream o = new FileOutputStream(realSavePath + "\\" + saveFilename);
						// ����һ��������
						byte buffer[] = new byte[1024];
						// �ж��������е������Ƿ��Ѿ�����ı�ʶ
						int len = 0;
						// ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
						while ((len = in.read(buffer)) > 0) {
							// ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\"
							// + filename)����
							o.write(buffer, 0, len);
						}
						// �ر�������
						in.close();
						// �ر������
						o.close();
						// ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
						item.delete();
						out.println("�ļ��ϴ��ɹ���");
						System.out.println("�ļ��ϴ��ɹ���");
					}
				}
				 dbID = params.get("dbID");		//�ļ���Ӧ���ID
				 dbName = params.get("dbName");	//�ļ���Ӧ�������
				 liuchengId = params.get("liuchengId");	//�ļ���Ӧ������ID
				 if(liuchengId == "0" || liuchengId.equals("0")){
					 liuchengName = "���������";
				 }else{
					 liuchengName = dao.fileliucheng(liuchengId);
				 }
				//db���ݿ�
			    System.out.println("�ϴ���ID"+accountid);
			    System.out.println("�ϴ��˵�¼��"+accountname);
			    System.out.println("���ID���ļ���Ӧ�ĵ��ID��"+dbID);
			    System.out.println("������ƣ��ļ���Ӧ�������ƣ�"+dbName);
			    System.out.println("�ļ���ԭ����"+filename);
			    System.out.println("�ļ��ı�������"+saveFilename);
			    System.out.println("�ļ��ı���Ŀ¼"+realSavePath);
			    System.out.println("�ϴ�ʱ��"+str);
			    System.out.println("�ϴ��ļ���Ӧ����ID"+liuchengId);
			    System.out.println("�ϴ��ļ���Ӧ��������"+liuchengName);
			    //��ȡʵ���ಢ��ֵ
			    WenJian wj = new WenJian(accountid,accountname,str,realSavePath,filename,saveFilename,liuchengId,liuchengName,dbID,dbName);
			    //����DAO����
			    int jieguo = dao.addWJ(wj);
			    if(jieguo > 0){
			    	System.out.println("���ݿ�д��ɹ�");
			    }else{
			    	System.out.println("���ݿ�д��ʧ��");
			    }
			} catch (FileUploadBase.FileSizeLimitExceededException e) {
				//e.printStackTrace();
				out.println("�����ļ��������ֵ������");
				System.out.println("�����ļ��������ֵ������");
				return;
			} catch (FileUploadBase.SizeLimitExceededException e) {
				//e.printStackTrace();
				out.println("�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");
				System.out.println("�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				out.println("�ļ��ϴ�ʧ�ܣ��뼰ʱ��ϵ����Ա");
				System.out.println("�ļ��ϴ�ʧ�ܣ��뼰ʱ��ϵ����Ա");
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
	 * @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
	 * @param filename �ļ���ԭʼ����
	 * @return uuid+"_"+�ļ���ԭʼ����
	 */
	private String makeFileName(String filename) { 
		// Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
		return UUID.randomUUID().toString() + "_" + filename;
	}
	/**
	 * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
	 * @Method: makePath
	 * @Description:
	 * @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
	 * @param savePath �ļ��洢·��
	 * @return �µĴ洢Ŀ¼
	 */
	private String makePath(String filename, String savePath) {
		// �õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// �����µı���Ŀ¼
		//String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
																// upload\3\5
		String dir = savePath; //δ�������ļ���									
		// File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
		File file = new File(dir);
		// ���Ŀ¼������
		if (!file.exists()) {
			// ����Ŀ¼
			file.mkdirs();
		}
		return dir;
	}
}
