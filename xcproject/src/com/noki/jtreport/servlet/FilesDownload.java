package com.noki.jtreport.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.WritableWorkbook;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.jtreport.sheng.download.javabean.ReportDao;
import com.noki.jtreport.sheng.download.javabean.ReportDaoFactory;
import com.noki.jtreport.shi.FileDownload;
import com.noki.jtreport.shi.JtReportBeanFactory;
import com.noki.log.DbLog;
import com.noki.mobi.common.Account;
import com.noki.page.NPageBean;
import com.noki.util.CTime;
import com.noki.util.Query;

public class FilesDownload extends HttpServlet {

	private static final String Content_type = "application/x-msdownload";
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		DbLog log = new DbLog();
		String path = req.getContextPath();
		Account account = (Account) session.getAttribute("account");
		String url = path + "/web/cx/Jtxiazai.jsp", msg = "";
		String action = req.getParameter("action");//将要导出的报表类型所代表的值
		String filename = req.getParameter("filename");
		String actualmonth = req.getParameter("actualmonth");
		String month = req.getParameter("actualmonth");
		String shi = req.getParameter("shi");
		String modelUrl = getServletContext().getRealPath("/web/cx/"+filename);
		DataBase db = new DataBase();
		if(action.equals("delsite")){
		    try {
		      db.connectDb();

		      String sql1 = " delete from jt_report_upload t where t.shicode = '"+shi+"' " +
		      		"and t.filename = '"+filename+"' and t.actualmonth = '"+actualmonth+"' " ;
		    
		      System.out.println(sql1.toCharArray());
		      //String delNames = getName(accountId, db);
		      msg = "删除失败！";
		      db.update(sql1);
		      msg = "删除成功！";
		    }
		    catch (DbException de) {
		      try {
		        db.rollback();
		      }
		      catch (DbException dee) {
		        dee.printStackTrace();
		      }
		      de.printStackTrace();
		    }
		    finally {
		      try {

		        db.close();
		      }
		      catch (Exception de) {
		        de.printStackTrace();
		      }
		    }
		      session.setAttribute("url", url);
		      session.setAttribute("msg", msg);
		      resp.sendRedirect(path + "/web/msg.jsp");

		}
	}
	private int allPage;
	public void setAllPage(int allpage) {
		this.allPage = allpage;

	}
	public int getAllPage() {
		return this.allPage;
	}
	public synchronized ArrayList getPageData(String shi,String whereStr) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			s2.append("select t.uploaddate, t.filename, t.actualmonth from jt_report_upload t"+
                     " where t.shicode ='"+shi+"'and t.actualmonth='"+whereStr+"'");
            System.out.println("报表"+s2);
           
         
            rs= db.queryAll(s2.toString());
			Query query=new Query();
		    list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} 

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public synchronized ArrayList getPageData3(String loginid,String shi,String whereStr) {
		ArrayList list = new ArrayList();
		DataBase db = new DataBase();
		ResultSet rs = null;
		try {
			db.connectDb();
			StringBuffer s2 = new StringBuffer();
			s2.append("select t.uploaddate, t.filename, t.actualmonth from jt_report_upload t"+
                     " where t.shicode ='"+shi+"'"+whereStr+" order by  t.filename");
            System.out.println("查询报表"+s2);
           
         
            rs= db.queryAll(s2.toString());
			Query query=new Query();
		    list = query.query(rs);
		}

		catch (DbException de) {
			de.printStackTrace();
		} 

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//查询为上传的报表
	public synchronized ArrayList getPageData1(String loginName,String accountmonth) {
		ArrayList list = new ArrayList();
		//Date currentTime = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM"); 
		//String dateString = formatter.format(currentTime);
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql="select j.actualmonth, d.agname, d.agcode,j.orders, count(filename) aa  from d_area_grade d, jt_report_upload j,per_area p "+
                   " where d.agcode = j.shicode(+) and d.agcode=p.agcode and d.agrade = '2'  and actualmonth(+) = '"+accountmonth+"'"+
                   " and p.accountid='"+loginName+"'  group by d.agname, d.agcode, j.orders,j.actualmonth order by j.orders asc";
        System.out.println("已上传报表sql:"+sql);
		try {
			db.connectDb();
			rs= db.queryAll(sql.toString());
			Query query=new Query();
		    list = query.query(rs);
		}

		catch (Exception de) {
			de.printStackTrace();
		} 

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	//未上传的报表
	public synchronized ArrayList getPageData2(String agcode,String accountmonth) {
		ArrayList list = new ArrayList();
		//Date currentTime = new Date();
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM"); 
		//String dateString = formatter.format(currentTime);
		
		DataBase db = new DataBase();
		ResultSet rs = null;
		String sql="select type from indexs i where i.indexs1='ReportName' and i.type not in (select filename from" +
				" jt_report_upload where actualmonth='"+accountmonth+"' and shicode='"+agcode+"') order by name asc";
		try {
			db.connectDb();
			rs= db.queryAll(sql.toString());
			Query query=new Query();
		    list = query.query(rs);
           System.out.println("未上传的报表:"+sql);
		}

		catch (Exception de) {
			de.printStackTrace();
		} 

		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			try {
				db.close();
			} catch (DbException de) {
				de.printStackTrace();
			}

		}

		return list;
	}
	public void write(String path,HttpServletResponse resp){
		File file = new File(path);
		FileInputStream fis = null;
	System.out.println("filefilefile:"+file);	
		try {
			fis = new FileInputStream(file);
			ServletOutputStream servletOS = resp.getOutputStream();
			byte[] buff = new byte[1024];
			
            int readLength;
            while (((readLength = fis.read(buff)) != -1)) {
                servletOS.write(buff, 0, readLength);
            }
            
            servletOS.flush();
            servletOS.close();
			fis.close();
			
			file.delete();		//删除临时文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



