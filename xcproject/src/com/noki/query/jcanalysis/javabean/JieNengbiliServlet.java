package com.noki.query.jcanalysis.javabean;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;

import com.keypoint.PngEncoder;

public class JieNengbiliServlet extends HttpServlet {

	public JieNengbiliServlet() {
		super();
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   
       
		response.setContentType("image/jpeg");
		
		 
		String jieneng=request.getParameter("datStr");
		System.out.println(jieneng+"----jieneng---");
		Jienengsheibeibili bili=new Jienengsheibeibili();
		JFreeChart chart=bili.createPieChart3D(jieneng);
		//ChartUtilities.writeChartAsJPEG(response.getOutputStream(),   
		//		100,chart,400,300,null);   
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
