package com.noki.query.jcanalysis.javabean;

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

public class YongdianpaimingServlet extends HttpServlet {

	public YongdianpaimingServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 response.setContentType("image/png");
		 request.setCharacterEncoding("UTF-8"); 
		List allList=new ArrayList();
		List shiList=new ArrayList();
		List dataList=new ArrayList();
		 JFreeChart chart=null;
		String[] paiming=request.getParameter("fylist").toString().split(";"); 
		
	    Yongdianpaiming ydpm=new Yongdianpaiming();
	    
	    for(int i=0;i<paiming.length;i++){
	        if(i%2==0){
	        	allList.add(paiming[i].toString());
	        	System.out.println("all----:"+allList.toString());
	        }
	    }
	    for(int i=0;i<allList.size();i++){
	        if(i%2==0){
	        	shiList.add(allList.get(i).toString());
	        	System.out.println(allList.get(i).toString()+"shi----");
	        }
	        else {
	        	dataList.add(allList.get(i).toString());
	        	System.out.println(allList.get(i).toString()+"data---");
	        }
	        
		    
	    }
	    chart=ydpm.getchart(shiList,dataList);
	    
	    BufferedImage chartImage=null;
		 ChartRenderingInfo info = null;
		 info = new ChartRenderingInfo(new StandardEntityCollection());
	     chartImage = chart.createBufferedImage(500, 380, info);
	     PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		 response.getOutputStream().write(encoder.pngEncode());
		//String paiming=request.getParameter("fylist");
		System.out.println("paiming:"+paiming.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	}
	public void init() throws ServletException {
	}

}
