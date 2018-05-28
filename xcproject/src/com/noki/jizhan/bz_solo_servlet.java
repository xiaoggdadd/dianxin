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
 * 2018年3月26日
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
		 	req.setCharacterEncoding("utf-8");//设置输入编码格式。
	        resp.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
	        
	        String path = req.getContextPath();
	        DbLog log = new DbLog();
	        HttpSession session = req.getSession();
	        String  msg = "";

	        String action = req.getParameter("action");
	        int sum = 0;
	        
	        if ("kbztd".equals(action)){//退单
	        	DataBase db = new DataBase();
	        	
	        	String id = req.getParameter("id");
	        	String [] strbz_id = id.split(",");
	        	System.out.println("退单id-"+id+"-lllllllllllllllll");
	        	for(int i = 0;i<strbz_id.length;i++){//将选择的id循环操作
	        		String bz_id = strbz_id[i];
	        		//System.out.println(i+":"+strbz_id[i]);
	        		
	        		//根据bzid查询elID,删除bzid数据,修改EL表的状态为未上报,删除PUE表ANALYSIS相应数据
	        		String sql  = "select b.eleid from bz_solo b where b.id="+bz_id;//查询el_id
	        		String sql1 = "delete bz_solo where id = "+bz_id;//删除bz_solo数据
	        		String sql2 = "delete ANALYSIS a where a.bzid = ("+sql.toString()+")";//删除PUE
	        		String sql3 = "update electricfees e set e.STATE = 0 where e.ELECTRICFEE_ID=("+sql.toString()+")";//修改el表
	        		
	        		System.out.println("查询el_id-sql:"+sql.toString());
	        		System.out.println("删除bz_solo-sql:"+sql1.toString());
	        		System.out.println("删除PUE-sql:"+sql2.toString());
	        		System.out.println("修改el-sql:"+sql3.toString());
	        		
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
	        	System.out.println("和:"+strbz_id.length +"sum:"+sum);
	        	if(sum == strbz_id.length){
	        		msg = "退单成功!";
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
	        		msg = "退单失败!";
	        		String url = path+"/web/sdttWeb/baozhang/allbz.jsp";
	        		session.setAttribute("url", url);
	                session.setAttribute("msg", msg);
	                resp.sendRedirect(path + "/web/msg.jsp");
	        	}
	        }
	        if("uppass".equals(action)){
	        	DataBase db = new DataBase();
	        	System.out.println("修改初始密码的的后台");
	        	String userid = req.getParameter("userid");
	        	String pass = req.getParameter("pass");
	        	MD5 md5 = new MD5(); 
	        	String pwd = md5.getMD5ofStr(pass);
	        	String sql = "update account a set a.password = '"+pwd+"' where  a.accountid = "+userid+"";
	        	System.out.println("修改初始密码:"+sql.toString());
	        	try {
					int i = db.update(sql);
					if(i!=0){
						msg="初始密码修改成功！";
						String url = path + "index.jsp";
		        		session.setAttribute("url", url);
		                session.setAttribute("msg", msg);
		                resp.sendRedirect(path+"/web/msg.jsp");
		        		db.commit();
		        		
		        	}else{
						db.rollback();
		        		msg = "初始密码修改失败!";
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
	    		
	        	System.out.println("添加预算方法");
	        	String shi = req.getParameter("shi");		//市
	        	String xian = req.getParameter("xian");		//县
	        	String xiaoqu = req.getParameter("xiaoqu");	//小区
	        	String month = req.getParameter("month");	//月份
	        	String money = req.getParameter("money");	//预算金额
	        	String loginName = req.getParameter("loginName");//录入人姓名
	        	String AccountID = req.getParameter("AccountID");	//录入人ID
	        	String AccountName = req.getParameter("AccountName");//录入人登陆名
	    		//录入时间
	        	Date date= new Date();//创建一个时间对象，获取到当前的时间
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
	        	String str = sdf.format(date);//将当前时间格式化为需要的类型
	        	System.out.println("市:"+shi);
	        	System.out.println("月份:"+month);
	        	System.out.println("预算金额:"+money);
	        	System.out.println("录入人名称:"+loginName);
	        	System.out.println("录入人ID:"+AccountID);
	        	System.out.println("录入人登陆名:"+AccountName);
	        	System.out.println("录入时间"+str);
	        	//执行DAO层添加方法
	        	YspzClass YC = new YspzClass(shi,xian,xiaoqu,month,money,str,str,AccountID);	//实体类
	        	YspzDao Ydao = new YspzDao();
	        	int rs = Ydao.addYC(YC);
	        	if(rs == 1){
	        		msg = "添加成功";
				}else{
					msg = "添加失败";
				}
	    		session.setAttribute("url", url1);
	    		session.setAttribute("msg", msg1);
	    		resp.sendRedirect(path + "/web/nomsg.jsp");
	        }
	        if("delectyspz".equals(action)){
	        	
	    		String path1 = req.getContextPath();
	    		HttpSession session1 = req.getSession();
	    		String url1 = path+"/web/sdttWeb/jichuInfoManager/Yspz.jsp",msg1="";
	    		
	        	System.out.println("删除预算方法");
	        	String id = req.getParameter("id");//录入人姓名
	        	//执行DAO层添加方法
	        	YspzDao Ydao = new YspzDao();
	        	int rs = Ydao.delectYC(id);
	        	
	        	if(rs == 1){
	        		msg = "删除成功";
				}else{
					msg = "删除失败";
				}
	    		session.setAttribute("url", url1);
	    		session.setAttribute("msg", msg1);
	    		resp.sendRedirect(path + "/web/sdttWeb/jichuInfoManager/Yspz.jsp");
	        }
	        if("updateyspz".equals(action)){
	        	
	    		String path1 = req.getContextPath();
	    		HttpSession session1 = req.getSession();
	    		String url1 = path+"/web/sdttWeb/jichuInfoManager/Yspz.jsp",msg1="";
	    		
	        	System.out.println("修改预算方法");
	        	String Yid = req.getParameter("Yid");		//市
	        	String shi = req.getParameter("shi");		//市
	        	String xian = req.getParameter("xian");		//县
	        	String xiaoqu = req.getParameter("xiaoqu");	//小区
	        	String month = req.getParameter("month");	//月份
	        	String money = req.getParameter("money");	//预算金额
	        	String loginName = req.getParameter("loginName");//录入人姓名
	        	String AccountID = req.getParameter("AccountID");	//录入人ID
	        	String AccountName = req.getParameter("AccountName");//录入人登陆名
	        	System.out.println("YidYidYidYidYidYidYidYidYidYidYidYidYidYid:"+Yid);
	    		//修改时间
	        	Date date= new Date();//创建一个时间对象，获取到当前的时间
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
	        	String str = sdf.format(date);//将当前时间格式化为需要的类型
	        	//执行DAO层添加方法
	        	YspzDao Ydao = new YspzDao();
	        	YspzClass YC = new YspzClass(shi,xian,xiaoqu,month,money,str,str,AccountID);	//实体类
	        	int rs = Ydao.updateYC(YC, Yid);
	        	if(rs == 1){
	        		msg = "修改成功";
				}else{
					msg = "修改失败";
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
