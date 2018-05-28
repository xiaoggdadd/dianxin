package com.noki.query.hbanalysisquery.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import com.keypoint.PngEncoder;
import com.noki.query.hbanalysisquery.javabean.HB_shihbanalysis;
import com.noki.query.hbanalysisquery.javabean.HbQsBean;
import com.noki.query.hbanalysisquery.javabean.Hb_hbanalysis;

public class HuanbiServlet_hbanalysis
    extends HttpServlet {
  public HuanbiServlet_hbanalysis() {
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
      IOException {
	 
	 if(request.getParameter("action").equals("getData")){
		 response.setContentType("application/json;charset=utf-8");
		 String[] months=request.getParameter("whereStr").toString().split(",");
		 HbQsBean bean=new HbQsBean();
		 String shistr=request.getParameter("city");
		 
         
		 
		 String[] shi=shistr.split(",");
		 ArrayList shiCode=new ArrayList();
		 ArrayList shiName=new ArrayList();
		 for(int i=0;i<shi.length;i++){
			 if(i%2==0){
				 shiCode.add(shi[i].toString());
			 }
			 else{
				 shiName.add(shi[i].toString());
			 }
		 }
		 StringBuilder json=new StringBuilder();
		 ArrayList list= bean.getData(months,shiCode);
		 System.out.println(list.toString());
		 json.append("{\"data\":[");
		 for(int j=0;j<shiCode.size();j++){
			 String xianStr="";
			 for(int i=0;i<list.size();i++){
				 if (i==list.size()-1&&j==shiCode.size()-1){
					     xianStr+="\""+list.get(i).toString()+"\",";
					     xianStr=xianStr.substring(0,xianStr.length()-1);
						 json.append("{\"city\":\""+shiCode.get(j)+"\",\"monthData\":["+xianStr+"]}");
						 break;
				 }
				 if((i)/months.length==j){
					 xianStr+="\""+list.get(i).toString()+"\",";	
					 continue;
				 }
				 else if (i==months.length*(j+1)){
					 xianStr=xianStr.substring(0,xianStr.length()-1);
					 json.append("{\"city\":\""+shiCode.get(j)+"\",\"monthData\":["+xianStr+"]},");
			         
			         break;
			     }
				
			 }
			 
		 }
		 json.append("]}");
		 PrintWriter out=response.getWriter();
		 System.out.println("--"+json.toString());
		 out.println(json);
		 out.flush();
		 out.close();
	 }
	 else if(request.getParameter("action").toString().equals("getChart")){
	    response.setContentType("image/png");
	    // get the chart from session
		String[] months=request.getParameter("timeStr").toString().split(","); 
		 String shistr=request.getParameter("city");
		 HbQsBean bean=new HbQsBean();
		 String[] shi=shistr.split(",");
		 ArrayList shiCode=new ArrayList();
		 ArrayList shiName=new ArrayList();
		 for(int i=0;i<shi.length;i++){
			 if(i%2==0){
				 shiCode.add(shi[i].toString());
			 }
			 else{
				 shiName.add(shi[i].toString());
			 }
		 }
		 ArrayList list= bean.getData(months,shiCode);
		 //ArrayList list= bean.getShiChartData(xianCode,months);
		 StringBuilder json=new StringBuilder();
		
		 for(int j=0;j<shiName.size();j++){
			 String xianStr="";
			 for(int i=0;i<list.size();i++){
				 if (i==list.size()-1&&j==shiName.size()-1){
					     xianStr+=""+list.get(i).toString()+";";
					     xianStr=xianStr.substring(0,xianStr.length()-1);
					     json.append(""+xianStr+"");
						 break;
				 }
				 if((i)/months.length==j){
					 xianStr+=""+list.get(i).toString()+",";	
					 continue;
				 }
				 else if (i==months.length*(j+1)){
					 xianStr=xianStr.substring(0,xianStr.length()-1);
					 json.append(""+xianStr+";");
			         break;
			     }
				
			 }
			 
		 }

		 System.out.println(json);
		 String[] dataStr=json.toString().split(";");
	    HB_shihbanalysis hbanalysis=new HB_shihbanalysis();
	    BufferedImage chartImage = hbanalysis.getChart(shiName,months, dataStr);
	    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
	    response.getOutputStream().write(encoder.pngEncode());
     }
	 else if(request.getParameter("action").equals("getShiData")){
		 response.setContentType("application/json;charset=utf-8");
		 String[] months=request.getParameter("timeStr").toString().split(",");
		 String xianQu=request.getParameter("cityStr");
		 
		 String[] xian=xianQu.split(",");
		 ArrayList xianCode=new ArrayList();
		 ArrayList xianName=new ArrayList();
		 for(int i=0;i<xian.length;i++){
			 if(i%2==0){
				 xianCode.add(xian[i].toString());
			 }
			 else{
				 xianName.add(xian[i].toString());
			 }
		 }
		 
		 
		 HbQsBean bean=new HbQsBean();
		 ArrayList list= bean.getShiData(xianCode,months);
		 StringBuilder json=new StringBuilder();
		 json.append("{\"data\":[");
		 for(int j=0;j<xianCode.size();j++){
			 String xianStr="";
			 for(int i=0;i<list.size();i++){
				 if (i==list.size()-1&&j==xianCode.size()-1){
					     xianStr+="\""+list.get(i).toString()+"\",";
					     xianStr=xianStr.substring(0,xianStr.length()-1);
						 json.append("{\"city\":\""+xianCode.get(j)+"\",\"monthData\":["+xianStr+"]}");
						 break;
				 }
				 if((i)/months.length==j){
					 xianStr+="\""+list.get(i).toString()+"\",";	
					 continue;
				 }
				 else if (i==months.length*(j+1)){
					 xianStr=xianStr.substring(0,xianStr.length()-1);
					 json.append("{\"city\":\""+xianCode.get(j)+"\",\"monthData\":["+xianStr+"]},");
			         
			         break;
			     }
				
			 }
			 
		 }
		 json.append("]}");

		 PrintWriter out=response.getWriter();
		 
		 out.println(json);
		 out.flush();
		 out.close();
	 }
	 else if(request.getParameter("action").equals("getShiChart")){
		 response.setContentType("image/png");
		 String[] months=request.getParameter("timeStr").toString().split(",");
		 String xianQu=request.getParameter("cityStr");
		// System.out.println(xianQu+"^^^^^^^^^^^^^^"+months.toString());
		 String[] xian=xianQu.split(",");
		 ArrayList xianCode=new ArrayList();
		 ArrayList xianName=new ArrayList();
		 for(int i=0;i<xian.length;i++){
			 if(i%2==0){
				 xianCode.add(xian[i].toString());
			 }
			 else{
				 xianName.add(xian[i].toString());
			 }
		 }
		 
		 
		 HbQsBean bean=new HbQsBean();
		 ArrayList list= bean.getShiChartData(xianCode,months);
		 StringBuilder json=new StringBuilder();
		
		 for(int j=0;j<xianName.size();j++){
			 String xianStr="";
			 for(int i=0;i<list.size();i++){
				 if (i==list.size()-1&&j==xianName.size()-1){
					     xianStr+=""+list.get(i).toString()+";";
					     xianStr=xianStr.substring(0,xianStr.length()-1);
					     json.append(""+xianStr+"");
						 break;
				 }
				 if((i)/months.length==j){
					 xianStr+=""+list.get(i).toString()+",";	
					 continue;
				 }
				 else if (i==months.length*(j+1)){
					 xianStr=xianStr.substring(0,xianStr.length()-1);
					 json.append(""+xianStr+";");
			         break;
			     }
				
			 }
			 
		 }

		 System.out.println(json);
		 String[] dataArray=json.toString().split(";");
		 
		
		    HB_shihbanalysis hbanalysis=new HB_shihbanalysis();
		    BufferedImage chartImage = hbanalysis.getChart(xianName,months, dataArray);
		    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		    response.getOutputStream().write(encoder.pngEncode());
	 }

  }

  //Process the HTTP Post request
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws
      ServletException, IOException {
    doGet(request, response);

  }

  //Process the HTTP Put request
  public void doPut(HttpServletRequest request,
                    HttpServletResponse response) throws
      ServletException, IOException {

  }

  //Clean up resources
  public void destroy() {}

}
