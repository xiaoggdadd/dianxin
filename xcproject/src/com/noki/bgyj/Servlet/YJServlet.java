package com.noki.bgyj.Servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.database.DataBase;
public class YJServlet extends HttpServlet {

	
	public YJServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET");
		this.doPost(request, response);
	}



		public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			//��ServletΪ����Servlet����deleteSERVLET.java������ͬ��
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	//��ȡ����
	System.out.println("get");
	String action = request.getParameter("action");
	DataBase dataBase=new DataBase();
	if("del".equals(action)){
		try {
			int  yjid=Integer.parseInt( request.getParameter("yjid"));
			System.out.println(yjid);
			String sql="update bgyj  set state=0 where id='"+yjid+"'";
			System.out.println("���Ԥ����ɾ���������ݣ�"+sql);
			int d=dataBase.update(sql);
			dataBase.commit();
			String msg = "";
			if(d==0){
				msg = "ɾ��ʧ�ܣ�";
			}else{
				msg = "ɾ���ɹ���";
			}
			String path = request.getContextPath();
			String url = path +"/web/sdttWeb/GaojingManager/jzbgYj.jsp";
			
			HttpSession session = request.getSession();
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			response.sendRedirect(path + "/web/msg.jsp");  

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
}

