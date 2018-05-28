package com.noki.jichuInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.log.DbLog;

public class Cbjihuaservlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//设置输入编码格式。
		res.setContentType("text/html;charsetType=utf-8");//设置输出编码格式。
		String action = req.getParameter("action");
		String path = req.getContextPath();
		DbLog log = new DbLog();
		String entrypensonnelrg=req.getParameter("accountname");
		SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String entrytimerg=mat.format(new Date());
		HttpSession session = req.getSession();
		String accountid = (String) session.getAttribute("loginName");
		
		if(action.equals("addCbjihua")){
			String url = path+"/web/sdttWeb/jichuInfoManager/addCbjihua.jsp",msg="";
			String zdbm = req.getParameter("zdmc");
			String cbjhrq = req.getParameter("cbjhrq");
			String userid = req.getParameter("username");
			String bz = req.getParameter("bz");
			String dbbm = req.getParameter("dbbm");			
			String id = (String) session.getAttribute("cbjihua")!=null?(String) session.getAttribute("cbjihua"):"";
			if(bz.equals("1")){
				String sqll="UPDATE CBJH SET ZDBM=(SELECT JZCODE FROM ZHANDIAN WHERE ID='"+zdbm+"'),DBID=(SELECT DBID FROM DIANBIAO WHERE DBBM='"+dbbm+"'), DBBM='"+dbbm+"', USERID='"+userid+"',ZDID='"+zdbm+"',CBJHRQ=to_date('"+cbjhrq+"','yyyy-mm-dd') where id="+id+"";
				System.out.println(sqll);
				DataBase db1 = new DataBase();
				int rs1=0;
				try {
					db1.connectDb();
				} catch(DbException e){
					e.printStackTrace();
				}
				try {
					db1.setAutoCommit(false);
					rs1=db1.update(sqll.toString());
					db1.commit();
					//db1.close();
				} catch (DbException e) {
					e.printStackTrace();
				}
				if(rs1==1){
	            	msg="抄表计划信息修改成功";
	            }else {
	            	msg="修改失败，请联系管理员";
	            }
				log.write(msg, accountid, req.getRemoteAddr(), "修改抄表计划信息");
				session.setAttribute("url", url);
				session.setAttribute("msg", msg);
				res.sendRedirect(path + "/web/msg.jsp");
			}
			if(!bz.equals("1")){

			
			String sql = "INSERT INTO CBJH (USERID,ZDID,ZDBM,DBBM,DBID,CREATE_USERID,CREATE_DATE,CBJHRQ) VALUES ('"+userid+"','"+zdbm+"',(SELECT JZCODE FROM ZHANDIAN WHERE ID='"+zdbm+"'),'"+dbbm+"',(SELECT DBID FROM DIANBIAO WHERE DBBM='"+dbbm+"'),'"+entrypensonnelrg+"',to_date('"+entrytimerg+"','yyyy-mm-dd hh24:mi:ss'),to_date('"+cbjhrq+"','yyyy-mm-dd hh24:mi:ss'))";

			System.out.println(sql);
			DataBase db = new DataBase();
			int rs = 0;
            try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
            try {
            	 db.setAutoCommit(false);
				 rs = db.update(sql.toString());
				 db.commit();
				// db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
            if(rs==1){
            	msg="抄表计划信息添加成功";
            }else {
            	msg="添加失败，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "添加抄表員信息");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
			}
            
		}else if(action.equals("delete")){
			String url = path+"/web/sdttWeb/jichuInfoManager/cbjihua.jsp",msg="";
			String id = req.getParameter("id");
			String sql = "DELETE FROM CBJH WHERE ID = "+id+" ";
			DataBase db = new DataBase();
			System.out.println("语句："+sql);
			int a=0;
			try {
				db.connectDb();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				a=db.update(sql.toString());
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(a==1){
            	msg="抄表计划信息删除成功";
            }else {
            	msg="添加失败，请联系管理员";
            }
            log.write(msg, accountid, req.getRemoteAddr(), "删除抄表计划");
			session.setAttribute("url", url);
			session.setAttribute("msg", msg);
			res.sendRedirect(path + "/web/msg.jsp");
		}
	}
}
