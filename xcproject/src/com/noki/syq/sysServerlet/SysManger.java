package com.noki.syq.sysServerlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysManger extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String shi = request.getParameter("shi")!=null?request.getParameter("shi"):"0";
		String xian = request.getParameter("xian")!=null?request.getParameter("xian"):"0";
		String xiaoqu = request.getParameter("xiaoqu")!=null?request.getParameter("xiaoqu"):"0";
		String bz="";
		bz=request.getParameter("bz")!=null?request.getParameter("bz"):"";
		
		//�û�������
		if("yhgl".equals(bz)){
			String role = request.getParameter("role")!=null?request.getParameter("role"):"0";
		String txtKeyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"0";
		String bumen = request.getParameter("bumen")!=null?request.getParameter("bumen"):"0";

		String whereStr = "";
		if (shi != null && !shi.equals("") && !shi.equals("0")) {
			whereStr = whereStr + " and a.shi='" + shi + "'";
		}
		if (xian != null && !xian.equals("") && !xian.equals("0")) {
			whereStr = whereStr + " and a.xian='" + xian + "'";
		}
		if (xiaoqu != null && !xiaoqu.equals("") && !xiaoqu.equals("0")) {
			whereStr = whereStr + " and a.xiaoqu='" + xiaoqu + "'";
		}
		if (txtKeyword != null && !txtKeyword.equals("") && !txtKeyword.equals("0")) {
			whereStr = whereStr + " and (a.accountname like '%" + txtKeyword + "%' or a.name like '%" + txtKeyword + "%')";
		}
		if (role != null && !role.equals("") && !role.equals("0")) {
			whereStr = whereStr + " and a.roleid = '" + role + "'";
		}
		if (bumen != null && !bumen.equals("") && !bumen.equals("0")) {
			whereStr = whereStr + " and a.bumen = '" + bumen + "'";
		}
		   StringBuffer sql = new StringBuffer();
	        sql.append("SELECT ACCOUNTNAME,NAME ");
	        sql.append(",(case when a.sheng is not null then (select agname from d_area_grade where agcode=a.sheng) else '' end)");
	        sql.append("||(case when a.shi is not null then (select agname from d_area_grade where agcode=a.shi) else '' end)");
	        sql.append("||(case when a.xian!='0' then (select agname from d_area_grade where agcode=a.xian) else '' end)");
	        sql.append("||(case when a.xiaoqu!='0' then (select agname from d_area_grade where agcode=a.xiaoqu) else '' end) as SZDQ");
	        sql.append(",MOBILE,TEL,ROLENAME, ");
	        sql.append("d.deptname as BUMEN");
	        sql.append(" FROM ACCOUNT A,DEPARTMENT d  WHERE  A.bumen=d.deptcode AND DELSIGN=1 AND ACCOUNTNAME!='admin' ");        
	        if(whereStr!=null && !whereStr.equals("")){
				sql.append(whereStr);
			}
	        sql.append(" ORDER BY NAME");
	
		request.setAttribute("beanType", "com.noki.syq.Sysbean.YhMangerBean");
		request.setAttribute("sql", sql.toString());
		request.setAttribute("head", "�˺�,����,����,�ֻ�����,����,��ɫ,����");
		request.setAttribute("path", "web/sdttWeb/sys/Daochu/");
		request.setAttribute("fileName","�û�����.xlsx");
		
		request.getRequestDispatcher("ExcelDownload").forward(request, response);
		//���������
		}else if("qygl".equals(bz)){
			String agname = request.getParameter("agname")!=null?request.getParameter("agname"):"";
			String whereStr = "";
			
			
			if (agname != null && !agname.equals("") && !agname.equals("0")) {
				whereStr = whereStr + " AND A.AGNAME LIKE '%" + agname + "%'";
				
				
			}
			
			
			   StringBuffer sql = new StringBuffer();
		        sql.append(" SELECT AGCODE,AGNAME FROM D_AREA_GRADE A WHERE 1=1 "+whereStr+"  ");
		       
		
			request.setAttribute("beanType", "com.noki.syq.Sysbean.QyManagerBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "������,��������");
			request.setAttribute("path", "web/sdttWeb/sys/Daochu/");
			request.setAttribute("fileName","�����Ź���.xlsx");
			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
			
		}else if("bmgl".equals(bz)){
			String txtKeyword = request.getParameter("agname")!=null?request.getParameter("agname"):"";
			String whereStr = "";
			    String gongsi=request.getParameter("gongsi")!=null?request.getParameter("gongsi"):"0";
			    String bumen=request.getParameter("bumen")!=null?request.getParameter("bumen"):"0";
			    String banzu=request.getParameter("banzu")!=null?request.getParameter("banzu"):"0";
			    if (gongsi != null && !gongsi.equals("") && !gongsi.equals("0")) {
					whereStr = whereStr + " and a.fdeptcode='" + gongsi + "'";
				}
				if(gongsi != null && !gongsi.equals("") && !gongsi.equals("0")&&bumen != null && !bumen.equals("") && !bumen.equals("0"))
				{
				 whereStr="";
				whereStr = whereStr + " and a.fdeptcode='" + bumen + "'";
				}
				if(gongsi != null && !gongsi.equals("") && !gongsi.equals("0")&&bumen != null && !bumen.equals("") && !bumen.equals("0")&&banzu != null && !banzu.equals("") && !banzu.equals("0"))
				{
				 whereStr="";
				whereStr = whereStr + " and a.fdeptcode='" + banzu + "'";
				}
				String name=request.getParameter("name")!=null?request.getParameter("name"):"";
				if(name!=null&&!"".equals(name)){
					whereStr = whereStr + " AND DEPTNAME LIKE '%" + name + "%'";
				}
			    
			
			   StringBuffer sql = new StringBuffer();
			   sql.append("SELECT DEPTCODE,DEPTNAME FROM DEPARTMENT a where 1=1 "+whereStr);
		       
		
			request.setAttribute("beanType", "com.noki.syq.Sysbean.BmManagerBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "���ű��,��������");
			request.setAttribute("path", "web/sdttWeb/sys/Daochu/");
			request.setAttribute("fileName","���Ź���.xlsx");
			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//ϵͳ��Ϣ����
			
		}else if("xtxx".equals(bz)){
			String txtKeyword = request.getParameter("xxlx")!=null?request.getParameter("xxlx"):"";
			String whereStr = "";
			  if(txtKeyword!=null&&txtKeyword!=""&&!txtKeyword.equals("0")&&!txtKeyword.equals("")){
	            	
				  whereStr=whereStr+" AND G.XXTYPE='"+txtKeyword+"'";
	            
			  }
			
			   StringBuffer sql = new StringBuffer();
			   sql.append("SELECT G.XXTYPE,I.NAME,G.BT,G.NR FROM GONGGAO G,INDEXS I WHERE G.XXTYPE=I.CODE AND 1=1 "+whereStr);
		
			request.setAttribute("beanType", "com.noki.syq.Sysbean.XxlxManagerBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "���,��Ϣ����,����,����");
			request.setAttribute("path", "web/sdttWeb/sys/Daochu/");
			request.setAttribute("fileName","ϵͳ��Ϣ����.xlsx");
			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
				//ϵͳ�ֵ����			
		}else if("zdgl".equals(bz)){
			String txtKeyword = request.getParameter("txtKeyword")!=null?request.getParameter("txtKeyword"):"";
			String whereStr = "";
			  if(txtKeyword!=null&&txtKeyword!=""&&!txtKeyword.equals("0")&&!txtKeyword.equals("")){	            	
				  whereStr=whereStr+" AND I.INDEXS1 like '%" + txtKeyword + "%'";	            
			  }			
			   StringBuffer sql = new StringBuffer();
			   sql.append("SELECT I.CODE,I.NAME,I.TYPE,I.INDEXS1 FROM INDEXS I WHERE 1=1 "+whereStr+" ");		
			request.setAttribute("beanType", "com.noki.syq.Sysbean.ZdManagerBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "��Ŀ����,��Ŀ����,��Ŀ����,��Ŀ����˵��");
			request.setAttribute("path", "web/sdttWeb/sys/Daochu/");
			request.setAttribute("fileName","ϵͳ�ֵ����.xlsx");			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);							
		}else if("jsgl".equals(bz)){
			String whereStr = "";
			
			
			   StringBuffer sql = new StringBuffer();
			   sql.append("SELECT R.NAME,R.MEMO,F.NAME AS FZNAME FROM ROLE R,FENZU F WHERE R.FENZU=F.ID ");
			   if(whereStr!=null && !whereStr.equals("")){
					sql.append(whereStr);
				}
			request.setAttribute("beanType", "com.noki.syq.Sysbean.JsManagerBean");
			request.setAttribute("sql", sql.toString());
			request.setAttribute("head", "��ɫ����,��ɫ˵��,����");
			request.setAttribute("path", "web/sdttWeb/sys/Daochu/");
			request.setAttribute("fileName","��ɫ����.xlsx");
			
			request.getRequestDispatcher("ExcelDownload").forward(request, response);
				
			
		}
		
	}

}
