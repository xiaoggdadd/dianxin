package com.noki.query.paranalysis.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import com.keypoint.PngEncoder;
import com.noki.query.hbanalysisquery.javabean.HB_shihbanalysis;
import com.noki.query.hbanalysisquery.javabean.HbQsBean;
import com.noki.query.hbanalysisquery.javabean.Hb_hbanalysis;
import com.noki.query.paranalysis.javabean.Hb_hbpar;
import com.noki.query.paranalysis.javabean.ParAnalysisBean;

public class HuanbiServlet_par
    extends HttpServlet {
  public HuanbiServlet_par() {
  }

  @SuppressWarnings("unchecked")
public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws ServletException,
      IOException {
    // get the chart from session
	  if(request.getParameter("action").equals("getData")){
			 response.setContentType("html/text;charset=utf-8");
			 
			 System.out.println(request.getParameter("timeStr")+"^^^^^^^^^^^^^^");
			 String time=request.getParameter("timeStr");
			 String[] shi=request.getParameter("cityStr").toString().split(",");
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
			 ParAnalysisBean bean=new ParAnalysisBean();
			 ArrayList dataList= bean.getShiData(shiCode, time);
			
			 StringBuffer json=new StringBuffer();
			 for(int i=0;i<dataList.size();i++){
				 json.append(""+dataList.get(i)+",");
				 
			 }
			 json=new StringBuffer(json.substring(0, json.length()-1));
			 PrintWriter out=response.getWriter();
			 out.println(json);
			 out.flush();
			 out.close();
		 }
		 else if(request.getParameter("action").toString().equals("getChart")){
		    response.setContentType("image/png");
		    // get the chart from session
			 String time=request.getParameter("timeStr");
			 String[] shi=request.getParameter("cityStr").toString().split(",");
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
			 ParAnalysisBean bean=new ParAnalysisBean();
			 ArrayList dataList= bean.getShiData(shiCode, time);
			 float count=0;
			 for(int i=0;i<dataList.size();i++){
				 count+=Float.parseFloat(dataList.get(i).toString());
			 }
			 dataList.add(count);
			 Hb_hbpar hbpar=new Hb_hbpar();
			 BufferedImage chartImage=hbpar.createPieChart3D(shiName, dataList);
		    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		    response.getOutputStream().write(encoder.pngEncode());
	     }
		 else if(request.getParameter("action").equals("getShiData")){
			 response.setContentType("html/text;charset=utf-8");
			
			 String time=request.getParameter("timeStr");
			 String[] xian=request.getParameter("cityStr").toString().split(",");
			 ArrayList xianCode=new ArrayList();
			 ArrayList xianName=new ArrayList();
			 for(int i=0;i<xian.length;i++){
				 if(i%2==0){
					 xianCode.add(xian[i].toString());
					 System.out.println(xian[i].toString()+"-----------"+i);
				 }
				 else{
					 xianName.add(xian[i].toString());
					 System.out.println(xian[i].toString()+"++++++++++++"+i);
				 }
			 }
			 
			 
		     ParAnalysisBean bean=new ParAnalysisBean();
			 ArrayList dataList= bean.getXianData(xianCode,time);
	         System.out.println(dataList.toString()+"list0000ÏØ");
	    
			 StringBuffer json=new StringBuffer();
			 for(int i=0;i<dataList.size();i++){
				 json.append(""+dataList.get(i)+",");
				 
			 }
			 json=new StringBuffer(json.substring(0, json.length()-1));
			 PrintWriter out=response.getWriter();
			 out.println(json);
			 out.flush();
			 out.close();
		 }
		 else if(request.getParameter("action").equals("getShiChart")){
			 String time=request.getParameter("timeStr");
			 String[] xian=request.getParameter("cityStr").toString().split(",");
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
			 ParAnalysisBean bean=new ParAnalysisBean();
			 ArrayList dataList= bean.getXianData(xianCode, time);
			 float count=0;
			 for(int i=0;i<dataList.size();i++){
				 count+=Float.parseFloat(dataList.get(i).toString());
			 }
			 dataList.add(count);
			 Hb_hbpar hbpar=new Hb_hbpar();
			 BufferedImage chartImage=hbpar.createPieChart3D(xianName, dataList);
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
