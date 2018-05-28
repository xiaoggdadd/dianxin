package com.noki.query.caijipoint.javabean;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import javax.servlet.http.HttpSession;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ChartFactory;
import java.text.DecimalFormat;
import org.jfree.chart.ChartRenderingInfo;
import java.io.PrintWriter;
import java.awt.Color;
import java.awt.Font;

import javax.servlet.http.HttpServletResponse;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.servlet.http.HttpServletRequest;
import org.jfree.chart.ChartUtilities;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import java.awt.image.BufferedImage;

public class GuanLiDB_Hdl {
  private ArrayList list;

  public GuanLiDB_Hdl() {
  }

  public synchronized String getChartViewer(HttpServletRequest request,
                                            HttpServletResponse response) {
	  DefaultCategoryDataset dataset = getDataset();
    // create the chart...
    JFreeChart chart = ChartFactory.createLineChart("管理类电表日耗电趋势", "时间", "耗电(度)", dataset,PlotOrientation.VERTICAL,true,false,false);
   // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
   
    CategoryPlot catplot=chart.getCategoryPlot();  //获取柱状图、折线图区域对象。  

    CategoryAxis domainAxis=catplot.getDomainAxis();  

    catplot.setNoDataMessage("无数据显示");//没有数据的时候显示的内容  

      //列表标题  

     TextTitle txtTitle = null;  

     txtTitle = chart.getTitle();  

     txtTitle.setFont(new java.awt.Font("黑体",Font.BOLD,14));  

     //水平底部列表  

     domainAxis.setLabelFont(new java.awt.Font("黑体",Font.BOLD,14));  

     //水平底部标题  

     domainAxis.setTickLabelFont(new java.awt.Font("宋体",Font.BOLD,12));  

     //垂直标题  

     ValueAxis rangeAxis=catplot.getRangeAxis();//获取柱状  

     rangeAxis.setLabelFont(new java.awt.Font("黑体",Font.BOLD,15));  

     chart.getLegend().setItemFont(new java.awt.Font("黑体", Font.BOLD, 15));  

     //获得renderer  

     LineAndShapeRenderer lineAndShapeRenderer =(LineAndShapeRenderer)catplot.getRenderer();  

     lineAndShapeRenderer.setShapesVisible(true); //series 点（即数据点）可见  

    /*  

     lineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {  

      10F, 6F  

     }, 0.0F)); //这里是虚线，默认是直线  */

     ChartRenderingInfo info = null;
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(600, 250, info);

      session.setAttribute("gldb_hdl", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "gldb_hdl", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/GuanLiDBServlet_Hdl";
    return chartViewer;
  }

  private DefaultCategoryDataset getDataset() {
// categories...
	  list=getList();
	//String[]  datastr="";
	  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    for(int i = 0;i<list.size();i++){
    	dataset.setValue(Double.parseDouble(list.get(i).toString().split(",")[1]),"日耗电趋势",list.get(i).toString().split(",")[0]);
    }
    
    return dataset;
  }

public ArrayList getList() {
	return list;
}

public void setList(ArrayList list) {
	this.list = list;
}




}
