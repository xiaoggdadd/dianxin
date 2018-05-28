package com.noki.tongjichaxu.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;

import com.keypoint.PngEncoder;
import com.noki.query.jcanalysis.javabean.Yongdianpaiming;

public class MapjzeleServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MapjzeleServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		response.setContentType("image/png");
		 request.setCharacterEncoding("UTF-8"); 
		List allList=new ArrayList();
		List shiList=new ArrayList();
		List dataList=new ArrayList();
		 JFreeChart chart=null;
		String[] hStr=request.getParameter("hStr").toString().split(";"); 
		String[] sstr=request.getParameter("sstr").toString().split(";"); 
		System.out.println(request.getParameter("hStr"));
		System.out.println(request.getParameter("sstr"));
		
	    MapUtiljz ydpm=new MapUtiljz();

	    for(int i =0;i<hStr.length;i++){
	    	shiList.add(hStr[i].toString());
	    }
	    for (int i=0;i<sstr.length;i++){
	    	dataList.add(sstr[i].toString());
	    }
	    System.out.println(shiList.toString());
	    System.out.println(dataList.toString());
//	    for(int i=0;i<paiming.length;i++){
//	        if(i%2==0){
//	        	allList.add(paiming[i].toString());
//	        	System.out.println("all----:"+allList.toString());
//	        }
//	    }
//	    for(int i=0;i<allList.size();i++){
//	        if(i%2==0){
//	        	shiList.add(allList.get(i).toString());
//	        	System.out.println(allList.get(i).toString()+"shi----");
//	        }
//	        else {
//	        	dataList.add(allList.get(i).toString());
//	        	System.out.println(allList.get(i).toString()+"data---");
//	        }
//	        
//		    
//	    }
	    chart=ydpm.getchart(shiList,dataList);
	    
	    BufferedImage chartImage=null;
		 ChartRenderingInfo info = null;
		 info = new ChartRenderingInfo(new StandardEntityCollection());
	     chartImage = chart.createBufferedImage(500, 380, info);
	     PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		 response.getOutputStream().write(encoder.pngEncode());
		//String paiming=request.getParameter("fylist");
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

		response.setContentType("text/html");
		doGet(request, response);
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
