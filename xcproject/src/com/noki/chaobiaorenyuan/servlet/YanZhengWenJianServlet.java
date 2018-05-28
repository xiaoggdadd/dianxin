package com.noki.chaobiaorenyuan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noki.chaobiaorenyuan.Dao.uploadDao;

public class YanZhengWenJianServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		//������Ҫ��֤����Ϣ
		String SCRID  = (String)request.getParameter("scrid");
		String DianBiaoID  = (String)request.getParameter("dianbiaoid");
		String LiuChengID  = (String)request.getParameter("liuchengid");
		//��֤ʱ��
    	Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//����ʱ����ʾ��ʽ
    	String SCTIME = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		System.out.println("��֤���ڣ�"+SCTIME);
		boolean result = dao.Whether(SCRID, DianBiaoID, SCTIME, LiuChengID);
		
		if(result){
			System.out.println("����ֵΪtrue���Ѿ��ϴ��ļ��������ύ�޸�");
			out.write("1");
		}else{
			System.out.println("����ֵΪfalse,Ϊ�ϴ��ļ����޷��ύ");
			out.write("0");
		}
		
	}

}
