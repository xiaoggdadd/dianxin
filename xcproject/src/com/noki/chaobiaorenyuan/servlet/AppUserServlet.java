package com.noki.chaobiaorenyuan.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.chaobiaorenyuan.Dao.GuanLiQuYuDao;

public class AppUserServlet extends HttpServlet {

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
		//�޸ı���
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		/**
		 * �����˰󶨵���Servlet
		 */
		//����ǰ̨����
		String dbid = request.getParameter("dbid");	//��ȡ���ID
		String uuid = request.getParameter("uuid");//������ID
		String caozuo = request.getParameter("caozuo");//�ж��ǰ󶨻���ɾ��
		//��ȡDao�� 
		GuanLiQuYuDao dao = new GuanLiQuYuDao(); 
		
		HttpSession session = request.getSession();
		//url���յ���ת��ַ
		String path = request.getContextPath();
		String url = path+"/web/sdttWeb/chaobiaorenManager/chaobiaorenyuanguanli_new.jsp",msg="";
		session.setAttribute("url", url);
		//msgҪ��ʾ�Ľ������
		session.setAttribute("msg", msg);
		
		//��ת��תҳ��
		if (uuid != null && !uuid.equals("") && !uuid.equals("0")) {
			if (dbid != null && !dbid.equals("") && !dbid.equals("0")) {
				if(caozuo.equals("bangding")){
					
				int rs = dao.addBangDing(uuid, dbid); //ִ�������󶨷���
				if (rs > 0) {
					System.out.println(uuid);
					msg="�󶨳ɹ�������������ҳ��";
				} else {
					msg="��ʧ�ܣ��������Ա��ϵ";
				}
				session.setAttribute("url", url);
				//msgҪ��ʾ�Ľ������
				session.setAttribute("msg", msg);
				//��ת��תҳ��
				response.sendRedirect(path + "/web/nomsg.jsp");
			}else if(caozuo.equals("jiebang")){
				int rs = dao.deleteBangDing(uuid, dbid); //ִ�н�󷽷�
				if (rs > 0) {
					msg="���ɹ�������������ҳ��";
				} else {
					msg="���ʧ�ܣ��������Ա��ϵ";
				}
				session.setAttribute("url", url);
				//msgҪ��ʾ�Ľ������
				session.setAttribute("msg", msg);
				//��ת��תҳ��
				response.sendRedirect(path + "/web/nomsg.jsp");
				//������
			}else if(caozuo.equals("bangdingALL")){
			
				if(dbid.contains(",")){
					int rs = 0;
					String [] dbidArr = dbid.split(",");   //����ַ���
					
					for (int i = 0; i < dbidArr.length; i++) {
						
						 rs = dao.addBangDing(uuid,dbidArr[i]); //ִ�������󶨷���
					}
					if (rs > 0) {
						System.out.println(uuid);
						msg="�󶨳ɹ�������������ҳ��";
					} else {
						msg="��ʧ�ܣ���鿴�Ƿ����ѱ��󶨵ĵ��������Ա��ϵ";
					}
					session.setAttribute("url", url);
					//msgҪ��ʾ�Ľ������
					session.setAttribute("msg", msg);
					//��ת��תҳ��
					response.sendRedirect(path + "/web/nomsg.jsp");
				}else{
					System.out.println("һ�����ݵ��һ����ʱ");
					int rs = dao.addBangDing(uuid, dbid); //ִ�������󶨷���
					if (rs > 0) {
						System.out.println(uuid);
						msg="�󶨳ɹ�������������ҳ��";
					} else {
						msg="��ʧ�ܣ��������Ա��ϵ";
					}
					session.setAttribute("url", url);
					//msgҪ��ʾ�Ľ������
					session.setAttribute("msg", msg);
					//��ת��תҳ��
					response.sendRedirect(path + "/web/nomsg.jsp");
				}
			}else if(caozuo.equals("bangdingALLNO")){
				if(dbid.contains(",")){
					int rs = 0;
					String [] dbidArr = dbid.split(",");   //����ַ���
					
					for (int i = 0; i < dbidArr.length; i++) {
						
						 rs = dao.deleteBangDing(uuid,dbidArr[i]); //ִ�н�󷽷�
					}
					if (rs > 0) {
						System.out.println(uuid);
						msg="���ɹ�������������ҳ��";
					} else {
						msg="���ʧ�ܣ���鿴�Ƿ���δ���󶨵ĵ��������Ա��ϵ";
					}
					session.setAttribute("url", url);
					//msgҪ��ʾ�Ľ������
					session.setAttribute("msg", msg);
					//��ת��תҳ��
					response.sendRedirect(path + "/web/nomsg.jsp");
				}else{
					System.out.println("һ�����ݵ��һ�����ʱ");
					int rs = dao.deleteBangDing(uuid, dbid); //ִ�н�󷽷�
					if (rs > 0) {
						msg="���ɹ�������������ҳ��";
					} else {
						msg="���ʧ�ܣ��������Ա��ϵ";
					}
					session.setAttribute("url", url);
					//msgҪ��ʾ�Ľ������
					session.setAttribute("msg", msg);
					//��ת��תҳ��
					response.sendRedirect(path + "/web/nomsg.jsp");
				}
			}
		}
		}
	}

}
