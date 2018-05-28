package com.noki.dldfgl;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.noki.database.DataBase;
import com.noki.database.DbException;
public class DFDLservlet extends HttpServlet {
	private static final String Content_type = "text/html;charset=UTF-8";

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		resp.setContentType(Content_type);
		String path = req.getContextPath();
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		if(action.equals("modify")){
			String jfqsrq = req.getParameter("jfqsrq");
			String jfzzrq = req.getParameter("jfzzrq");
			String qima = req.getParameter("qima");
			String zhima = req.getParameter("zhima");
			String dianliu = req.getParameter("dianliu");
			String dianliang = req.getParameter("dianliang");
			String sunhao = req.getParameter("sunhao");
			String thiscbsj = req.getParameter("thiscbsj");
			String lastcbsj = req.getParameter("lastcbsj");
			String id = (String) session.getAttribute("id");
			StringBuffer sql2 = new StringBuffer();
			System.out.println(jfqsrq);
			sql2.append("update tbl_tt_wy_dfft set CB_JFQSRQ='"+jfqsrq+"',CB_JFZZRQ='"+jfzzrq+"',CB_QM="+qima+",CB_ZM="+zhima+",CB_DLIU="+dianliu+",CB_DLIANG="+dianliang+",SUNHAO="+sunhao+",LASTCBSJ='"+lastcbsj+"',THISCBSJ='"+thiscbsj+"',CBZT='已抄表' WHERE ID="+id+"");
			
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql2.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}			
			resp.sendRedirect(path +"/web/electricfees/dianfeidan_search.jsp");
		
		}else if(action.equals("update")){

			String jfqsrq = req.getParameter("jfqsrq");
			String jfzzrq = req.getParameter("jfzzrq");
			String qima = req.getParameter("qima");
			String zhima = req.getParameter("zhima");
			String dianliu = req.getParameter("dianliu");
			String dianliang = req.getParameter("dianliang");
			String sunhao = req.getParameter("sunhao");
			String lastcbsj = req.getParameter("lastcbsj");
			String thiscbsj = req.getParameter("thiscbsj");
			String xgr = req.getParameter("xgr");
			String xgyy = req.getParameter("xgyy");
			String xgrq = req.getParameter("xgrq");
			String id = (String) session.getAttribute("id");
			StringBuffer sql2 = new StringBuffer();
			
			sql2.append("update tbl_tt_wy_dfft set XGJFQSRQ='"+jfqsrq+"',XGJFZZRQ='"+jfzzrq+"',XGQM="+qima+",XGZM="+zhima+",XGDLIU="+dianliu+",XGDLIANG="+dianliang+",XGR='"+xgr+"',XGYY='"+xgyy+"',XGRQ='"+xgrq+"',SUNHAO="+sunhao+",LASTCBSJ='"+lastcbsj+"',THISCBSJ='"+thiscbsj+"',CBZT='需修改' WHERE ID="+id+"");
			System.out.println(sql2);
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql2.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}			
			resp.sendRedirect(path +"/web/electricfees/dianfeidan_search.jsp");
		
		}else if(action.equals("shijixiugai")){

			String jfqsrq = req.getParameter("jfqsrq");
			String jfzzrq = req.getParameter("jfzzrq");
			String qima = req.getParameter("qima");
			String zhima = req.getParameter("zhima");
			String dianliu = req.getParameter("dianliu");
			String dianliang = req.getParameter("dianliang");
			String sunhao = req.getParameter("sunhao");
			String lastcbsj = req.getParameter("lastcbsj");
			String thiscbsj = req.getParameter("thiscbsj");
			//String xgr = req.getParameter("xgr");
			//String xgyy = req.getParameter("xgyy");
			//String xgrq = req.getParameter("xgrq");
			String id = (String) session.getAttribute("id");
			StringBuffer sql2 = new StringBuffer();
			
			sql2.append("update tbl_tt_wy_dfft set CB_JFQSRQ='"+jfqsrq+"',CB_JFZZRQ='"+jfzzrq+"',CB_QM="+qima+",CB_ZM="+zhima+",CB_DLIU="+dianliu+",CB_DLIANG="+dianliang+",SUNHAO="+sunhao+",LASTCBSJ='"+lastcbsj+"',THISCBSJ='"+thiscbsj+"',CBZT='已修改' WHERE ID="+id+"");
			System.out.println(sql2);
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql2.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}			
			resp.sendRedirect(path +"/web/electricfees/chaobiao_search.jsp");
		
		}else if(action.equals("queren")){
			//System.out.println("01423456578");
			String id =  req.getParameter("id");
			//System.out.println(id);
			StringBuffer sql = new StringBuffer();
			sql.append("update tbl_tt_wy_dfft set cbzt='已确认' where id="+id+"");
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/chaobiao_search.jsp");
		}else if(action.equals("delsite")){
			String id = req.getParameter("id");
			int sid = Integer.parseInt(id);
			StringBuffer sql = new StringBuffer();
			sql.append("delete from yezhu where id="+sid+"");
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/yezhu_search.jsp");
		}else if(action.equals("delftbl")){
			String id = req.getParameter("id");
			StringBuffer sql = new StringBuffer();
			StringBuffer sql1 = new StringBuffer();
			sql1.append("select jzcode from zhandian where jzname='"+id+"'");
			System.out.println(sql1);
			DataBase db1= new DataBase();
			ResultSet rs = null;
			try {
				db1.connectDb();
			} catch (DbException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				rs=db1.queryAll(sql1.toString());
			} catch (DbException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}			
			String zdid = "";
			try {
				while(rs.next()){
					zdid = rs.getString("jzcode");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql.append("delete from ftblquery where zdid="+zdid+"");
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/ftbl_search.jsp");
		}else if(action.equals("addyezhu")){
			String name = req.getParameter("name");
			String shi = req.getParameter("shi");
			String xian = req.getParameter("xian");
			String xiaoqu = req.getParameter("xiaoqu");
			System.out.println(shi);
			System.out.println(xiaoqu);
			System.out.println(xian);
			StringBuffer sql = new StringBuffer();
			sql.append("insert into yezhu (id,name,shi,xian,xiaoqu) values(Sequences_yezhu.nextval,'"+name+"','"+shi+"','"+xian+"','"+xiaoqu+"')");
			System.out.println(sql);
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/yezhu_search.jsp");
		}else if(action.equals("updateyezhu")){
			int q =   (Integer) session.getAttribute("id");
			//System.out.println(q);
			//int id = Integer.parseInt(q);
			
			String name = req.getParameter("name");
			String shi = req.getParameter("shi");
			String xian = req.getParameter("xian");
			String xiaoqu = req.getParameter("xiaoqu");
			StringBuffer sql = new StringBuffer();
			sql.append("update yezhu set name='"+name+"',shi='"+shi+"',xian='"+xian+"',xiaoqu='"+xiaoqu+"' where id="+q+"");
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/yezhu_search.jsp");
		}else if(action.equals("addftbl")){
			String id = req.getParameter("zdmc");
			String zdid="";
			StringBuffer sql4 = new StringBuffer();
			sql4.append("select jzcode from zhandian where jzname='"+id+"'");
			System.out.println(sql4);
			DataBase db1 = new DataBase();
			ResultSet rs = null;
			try {
				db1.connectDb();
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs = db1.queryAll(sql4.toString());
				while(rs.next()){
					 zdid = rs.getString("jzcode");
				}
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String kehu = req.getParameter("kehu");
			String ftbl = req.getParameter("ftbl");
			String kh = req.getParameter("kh");
			String ftblq = req.getParameter("ftblq");
			String kehuqw = req.getParameter("kehuqw");
			String ftblqw = req.getParameter("ftblqw");
			String dbbm = req.getParameter("dbbm");
			String shi = req.getParameter("shi");
			String xian = req.getParameter("xian");
			String xiaoqu = req.getParameter("xiaoqu");
			StringBuffer sql = new StringBuffer();
			StringBuffer sql2 = new StringBuffer();
			StringBuffer sql3 = new StringBuffer();
			sql.append("insert into ftblquery (id,zdid,kehu,ftbl,dbbm,shi,xian,xiaoqu) values(Squences_ftbl.nextval,'"+zdid+"','"+kehu+"','"+ftbl+"','"+dbbm+"','"+shi+"','"+xian+"','"+xiaoqu+"')");
			sql2.append("insert into ftblquery (id,zdid,kehu,ftbl,dbbm,shi,xian,xiaoqu) values(Squences_ftbl.nextval,'"+zdid+"','"+kh+"','"+ftblq+"','"+dbbm+"','"+shi+"','"+xian+"','"+xiaoqu+"')");
			sql3.append("insert into ftblquery (id,zdid,kehu,ftbl,dbbm,shi,xian,xiaoqu) values(Squences_ftbl.nextval,'"+zdid+"','"+kehuqw+"','"+ftblqw+"','"+dbbm+"','"+shi+"','"+xian+"','"+xiaoqu+"')");
			System.out.println(sql);
			DataBase db = new DataBase();
			try {
				db.connectDb();
			}catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
				db.update(sql2.toString());
				db.update(sql3.toString());
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/ftbl_search.jsp");
		}else if(action.equals("updateftbl")){
			int id = (Integer) session.getAttribute("id");
			int idd = (Integer) session.getAttribute("idd");
			int ids = (Integer) session.getAttribute("ids");
			String kehu = req.getParameter("kehu");
			String ftbl = req.getParameter("ftbl");
			String kehuq = req.getParameter("kehuq");
			String kehuw = req.getParameter("kehuw");
			String ftblq = req.getParameter("ftblq");
			String ftblw = req.getParameter("ftblw");
			StringBuffer sql = new StringBuffer();
			StringBuffer sql2 = new StringBuffer();
			StringBuffer sql3 = new StringBuffer();
			sql.append("update ftblquery set kehu='"+kehu+"',ftbl='"+ftbl+"' where id="+id+"");
			sql2.append("update ftblquery set kehu='"+kehuq+"',ftbl='"+ftblq+"' where id="+idd+"");
			sql3.append("update ftblquery set kehu='"+kehuw+"',ftbl='"+ftblw+"' where id="+ids+"");
			
			DataBase db = new DataBase();
			try {
				db.connectDb();
			} catch (DbException e) {
				e.printStackTrace();
			}
			try {
				db.update(sql.toString());
				db.update(sql2.toString());
				db.update(sql3.toString());
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
			resp.sendRedirect(path +"/web/electricfees/ftbl_search.jsp");
		}
	}


}
