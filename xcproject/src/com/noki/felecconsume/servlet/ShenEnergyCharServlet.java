package com.noki.felecconsume.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;

import com.keypoint.PngEncoder;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

import com.noki.felecconsume.dao.ShenEnergyDAO;
import com.noki.shenenergy.service.ShenEnergyService;
import com.noki.util.BuildHBData;

public class ShenEnergyCharServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShenEnergyCharServlet() {
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

		this.doPost(request, response);
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
		response.setContentType("image/png");
		HttpSession session = request.getSession();
	//	ShenEnergyService energyservice = new ShenEnergyService();
		String city = request.getParameter("city"); //³ÇÊÐ
		String  years=request.getParameter("year").substring(0,request.getParameter("year").length()-1);
		String [] year = years.split(",");
		String  months=request.getParameter("month").substring(0,request.getParameter("month").length()-1);
		String energys = request.getParameter("energy");
		String [] energy = {energys};
		String [] month = months.split(",");
		JFreeChart chart=null;
		try{
			 //chart = energyservice.toRetChart(city,energy,year,month);
			 ShenEnergyDAO endao = new ShenEnergyDAO();
			 List enList = endao.toGetDate(city);
			 chart = BuildHBData.toRetChar(enList,energy,"felec.properties",year,month);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		ServletUtilities.setTempFilePrefix("public-jfreechart-");
		BufferedImage chartImage = chart.createBufferedImage(1024,400,info);
	    PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
	    response.getOutputStream().write(encoder.pngEncode());
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
