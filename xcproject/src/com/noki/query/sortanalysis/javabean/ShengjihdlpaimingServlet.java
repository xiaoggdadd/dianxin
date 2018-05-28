package com.noki.query.sortanalysis.javabean;

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

public class ShengjihdlpaimingServlet extends HttpServlet {

	public ShengjihdlpaimingServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      
		 response.setContentType("image/png");
		 request.setCharacterEncoding("UTF-8"); 
		List allList=new ArrayList();
		List shiList=new ArrayList();
		List dataList=new ArrayList();
		 JFreeChart chart=null;
		String[] paiming=request.getParameter("fylist").toString().substring(0, request.getParameter("fylist").toString().length()-1).split(";"); 
		
	    ShengjihdlChart sj=new ShengjihdlChart();
	    
	    for(int i=0;i<paiming.length;i++){
	    	String[] newPaiming= paiming[i].toString().split(",");
	    	for(int j=0;j<newPaiming.length;j++){
	    		if(j==2){
	    			shiList.add(newPaiming[j]);
	    		}
	    		else if(j==3){
	    			dataList.add(newPaiming[j]);
	    		}
	    		
	    	}
	    }
	    chart=sj.getchart(shiList,dataList);
	    
	    BufferedImage chartImage=null;
		 ChartRenderingInfo info = null;
		 info = new ChartRenderingInfo(new StandardEntityCollection());
	     chartImage = chart.createBufferedImage(500, 380, info);
	     PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
		 response.getOutputStream().write(encoder.pngEncode());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.flush();
		out.close();
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
