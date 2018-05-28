package com.noki.jizhan;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jizhan.daoru.YspzDao;
import com.noki.jizhan.model.YspzClass;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.util.MD5;
/**
 * 2018��3��26��
 * @author Administrator
 *
 */
public class bz_solo_servlet extends HttpServlet {
	public bz_solo_servlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 	req.setCharacterEncoding("utf-8");//������������ʽ��
	        resp.setContentType("text/html;charsetType=utf-8");//������������ʽ��
	        
	        String path = req.getContextPath();
	        DbLog log = new DbLog();
	        HttpSession session = req.getSession();
	        String  msg = "";

	        String action = req.getParameter("action");
	        int sum = 0;
	        
	        if ("kbztd".equals(action)){//�˵�
	        	DataBase db = new DataBase();
	        	
	        	String id = req.getParameter("id");
	        	String [] strbz_id = id.split(",");
	        	System.out.println("�˵�id-"+id+"-lllllllllllllllll");
	        	for(int i = 0;i<strbz_id.length;i++){//��ѡ���idѭ������
	        		String bz_id = strbz_id[i];
	        		//System.out.println(i+":"+strbz_id[i]);
	        		
	        		//����bzid��ѯelID,ɾ��bzid����,�޸�EL���״̬Ϊδ�ϱ�,ɾ��PUE��ANALYSIS��Ӧ����
	        		String sql  = "select b.eleid from bz_solo b where b.id="+bz_id;//��ѯel_id
	        		String sql1 = "delete bz_solo where id = "+bz_id;//ɾ��bz_solo����
	        		String sql2 = "delete ANALYSIS a where a.bzid = ("+sql.toString()+")";//ɾ��PUE
	        		String sql3 = "update electricfees e set e.STATE = 0 where e.ELECTRICFEE_ID=("+sql.toString()+")";//�޸�el��
	        		
	        		System.out.println("��ѯel_id-sql:"+sql.toString());
	        		System.out.println("ɾ��bz_solo-sql:"+sql1.toString());
	        		System.out.println("ɾ��PUE-sql:"+sql2.toString());
	        		System.out.println("�޸�el-sql:"+sql3.toString());
	        		
	        		try {
	        			int l = db.update(sql3);
	        			int k = db.update(sql2);
	        			int j = db.update(sql1);
	        			if(j != 0 && k != 0 && l != 0){
	        				sum++;
	        			}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	        		
	        	}//for end
	        	System.out.println("��:"+strbz_id.length +"sum:"+sum);
	        	if(sum == strbz_id.length){
	        		msg = "�˵��ɹ�!";
	        		String url = path + "/web/sdttWeb/baozhang/allbz.jsp";
	        		session.setAttribute("url", url);
	                session.setAttribute("msg", msg);
	                resp.sendRedirect(path+"/web/msg.jsp");
	        		try {
						db.commit();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally{
						try {
							if(db != null){
								db.close();
								}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
	        	}else{
	        		try {
						db.rollback();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		msg = "�˵�ʧ��!";
	        		String url = path+"/web/sdttWeb/baozhang/allbz.jsp";
	        		session.setAttribute("url", url);
	                session.setAttribute("msg", msg);
	                resp.sendRedirect(path + "/web/msg.jsp");
	        	}
	        }
	        if("uppass".equals(action)){
	        	DataBase db = new DataBase();
	        	System.out.println("�޸ĳ�ʼ����ĵĺ�̨");
	        	String userid = req.getParameter("userid");
	        	String pass = req.getParameter("pass");
	        	MD5 md5 = new MD5(); 
	        	String pwd = md5.getMD5ofStr(pass);
	        	String sql = "update account a set a.password = '"+pwd+"' where  a.accountid = "+userid+"";
	        	System.out.println("�޸ĳ�ʼ����:"+sql.toString());
	        	try {
					int i = db.update(sql);
					if(i!=0){
						msg="��ʼ�����޸ĳɹ���";
						String url = path + "index.jsp";
		        		session.setAttribute("url", url);
		                session.setAttribute("msg", msg);
		                resp.sendRedirect(path+"/web/msg.jsp");
		        		db.commit();
		        		
		        	}else{
						db.rollback();
		        		msg = "��ʼ�����޸�ʧ��!";
		        		String url = path + "index.jsp";
		        		session.setAttribute("url", url);
		                session.setAttribute("msg", msg);
		                resp.sendRedirect(path + "/web/msg.jsp");
		        	}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					try {
						if(db == null){
							db.close();
							}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	        }
	        if("addyspz".equals(action)){
	        	
	    		String path1 = req.getContextPath();
	    		HttpSession session1 = req.getSession();
	    		String url1 = path+"/web/sdttWeb/jichuInfoManager/Yspz.jsp",msg1="";
	    		
	        	System.out.println("���Ԥ�㷽��");
	        	String shi = req.getParameter("shi");		//��
	        	String xian = req.getParameter("xian");		//��
	        	String xiaoqu = req.getParameter("xiaoqu");	//С��
	        	String month = req.getParameter("month");	//�·�
	        	String money = req.getParameter("money");	//Ԥ����
	        	String loginName = req.getParameter("loginName");//¼��������
	        	String AccountID = req.getParameter("AccountID");	//¼����ID
	        	String AccountName = req.getParameter("AccountName");//¼���˵�½��
	    		//¼��ʱ��
	        	Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
	        	String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
	        	System.out.println("��:"+shi);
	        	System.out.println("�·�:"+month);
	        	System.out.println("Ԥ����:"+money);
	        	System.out.println("¼��������:"+loginName);
	        	System.out.println("¼����ID:"+AccountID);
	        	System.out.println("¼���˵�½��:"+AccountName);
	        	System.out.println("¼��ʱ��"+str);
	        	//ִ��DAO����ӷ���
	        	YspzClass YC = new YspzClass(shi,xian,xiaoqu,month,money,str,str,AccountID);	//ʵ����
	        	YspzDao Ydao = new YspzDao();
	        	int rs = Ydao.addYC(YC);
	        	if(rs == 1){
	        		msg = "��ӳɹ�";
				}else{
					msg = "���ʧ��";
				}
	    		session.setAttribute("url", url1);
	    		session.setAttribute("msg", msg1);
	    		resp.sendRedirect(path + "/web/nomsg.jsp");
	        }
	        if("delectyspz".equals(action)){
	        	
	    		String path1 = req.getContextPath();
	    		HttpSession session1 = req.getSession();
	    		String url1 = path+"/web/sdttWeb/jichuInfoManager/Yspz.jsp",msg1="";
	    		
	        	System.out.println("ɾ��Ԥ�㷽��");
	        	String id = req.getParameter("id");//¼��������
	        	//ִ��DAO����ӷ���
	        	YspzDao Ydao = new YspzDao();
	        	int rs = Ydao.delectYC(id);
	        	
	        	if(rs == 1){
	        		msg = "ɾ���ɹ�";
				}else{
					msg = "ɾ��ʧ��";
				}
	    		session.setAttribute("url", url1);
	    		session.setAttribute("msg", msg1);
	    		resp.sendRedirect(path + "/web/sdttWeb/jichuInfoManager/Yspz.jsp");
	        }
	        if("updateyspz".equals(action)){
	        	
	    		String path1 = req.getContextPath();
	    		HttpSession session1 = req.getSession();
	    		String url1 = path+"/web/sdttWeb/jichuInfoManager/Yspz.jsp",msg1="";
	    		
	        	System.out.println("�޸�Ԥ�㷽��");
	        	String Yid = req.getParameter("Yid");		//��
	        	String shi = req.getParameter("shi");		//��
	        	String xian = req.getParameter("xian");		//��
	        	String xiaoqu = req.getParameter("xiaoqu");	//С��
	        	String month = req.getParameter("month");	//�·�
	        	String money = req.getParameter("money");	//Ԥ����
	        	String loginName = req.getParameter("loginName");//¼��������
	        	String AccountID = req.getParameter("AccountID");	//¼����ID
	        	String AccountName = req.getParameter("AccountName");//¼���˵�½��
	        	System.out.println("YidYidYidYidYidYidYidYidYidYidYidYidYidYid:"+Yid);
	    		//�޸�ʱ��
	        	Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
	        	String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
	        	//ִ��DAO����ӷ���
	        	YspzDao Ydao = new YspzDao();
	        	YspzClass YC = new YspzClass(shi,xian,xiaoqu,month,money,str,str,AccountID);	//ʵ����
	        	int rs = Ydao.updateYC(YC, Yid);
	        	if(rs == 1){
	        		msg = "�޸ĳɹ�";
				}else{
					msg = "�޸�ʧ��";
				}
	    		session.setAttribute("url", url1);
	    		session.setAttribute("msg", msg1);
	    		resp.sendRedirect(path + "/web/nomsg.jsp");
	        }
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
