package com.noki.tongjichaxu;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keypoint.PngEncoder;
import com.noki.tongjichaxu.GuanliChart;

public class Yhdl extends HttpServlet {


	public Yhdl() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/png");
		GuanliChart gl=new GuanliChart();
		String bz=request.getParameter("bz");
		String[] data = null;
		if(bz=="1"||"1".equals(bz)){
			String str=request.getParameter("fylist");	
			List<String> months = new ArrayList<String>();
			List<String> dataArray = new ArrayList<String>();
			
			data = str.split(";");
			String diqu=data[data.length-1];
			for(int i=0;i<data.length;i++){
				months.add(data[i].split(",")[0]);
				dataArray.add(data[i].split(",")[1]);
			}		
			BufferedImage chartImage = gl.getChart(bz,diqu,months, dataArray);
			PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
			response.getOutputStream().write(encoder.pngEncode());
		}else if(bz=="3"||"3".equals(bz)){
			String str=request.getParameter("fylist");	
			List<String> months = new ArrayList<String>();
			List<String> dataArray = new ArrayList<String>();
			
			data = str.split(";");
			String diqu=data[data.length-1];
			for(int i=0;i<data.length;i++){
				months.add(data[i].split(",")[0]);
				dataArray.add(data[i].split(",")[1]);
			}		
			BufferedImage chartImage = gl.getChart(bz,diqu,months, dataArray);
			PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
			response.getOutputStream().write(encoder.pngEncode());
		}
		else if(bz=="4"||"4".equals(bz)){
			String str=request.getParameter("fylist");	
			List<String> months = new ArrayList<String>();
			List<String> dataArray = new ArrayList<String>();
			
			data = str.split(";");
			String diqu=data[data.length-1];
			for(int i=0;i<data.length;i++){
				months.add(data[i].split(",")[0]);
				dataArray.add(data[i].split(",")[1]);
			}		
			BufferedImage chartImage = gl.getChart(bz,diqu,months, dataArray);
			PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
			response.getOutputStream().write(encoder.pngEncode());
		}
		
		else if(bz=="2"||"2".equals(bz)){
			String str1 = request.getParameter("fylist1");
			List<Integer> y = new ArrayList<Integer>();
			List<Integer> m = new ArrayList<Integer>();
			List<Integer> d = new ArrayList<Integer>();
			List<String> dataArray1 = new ArrayList<String>();
			List<String> dataArray2 = new ArrayList<String>();	
			
			data = str1.split(";");
			String zdname=data[data.length-1];
			for(int i=0;i<data.length-1;i++){
				String[] date = data[i].split(",")[0].split("-");
				y.add(Integer.parseInt(date[0]));
				m.add(Integer.parseInt(date[1]));
				d.add(Integer.parseInt(date[2]));
				dataArray1.add(data[i].split(",")[1]);
				dataArray2.add(data[i].split(",")[2]);
			}		
			BufferedImage chartImage = gl.getChart(zdname,y,m,d,dataArray1,dataArray2);
			PngEncoder encoder = new PngEncoder(chartImage, false, 0, 9);
			response.getOutputStream().write(encoder.pngEncode());
		}
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
