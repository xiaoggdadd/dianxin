package com.noki.query.hnlcomparison.javabean;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import javax.servlet.http.HttpSession;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
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
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import java.awt.image.BufferedImage;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Hb_comparison {
  private String cityStr;
  private String dataStr;
  private String timeStr;
  private String chartTitle;

  public Hb_comparison() {
  }

  public synchronized String getChartViewer(HttpServletRequest request,
                                            HttpServletResponse response) {
	String chartTitle = getChartTitle();
	DefaultCategoryDataset dataset = getDataset();
    // create the chart...
    JFreeChart chart = ChartFactory.createBarChart(chartTitle, "时间", "数值",dataset,PlotOrientation.VERTICAL,true,false,false);
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

    //LineAndShapeRenderer lineAndShapeRenderer =(LineAndShapeRenderer)catplot.getRenderer();  
     BarRenderer3D renderer = new BarRenderer3D();//
     renderer.setBaseOutlinePaint(Color.BLACK);//设置边框颜色为black
     renderer.setWallPaint(Color.gray); //设置wall的颜色为gray
     renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());//设置柱顶数据,API中居然没有StandardCategoryItemLabelGenerator这个类
     //renderer.setItemLabelFont(new Font("黑体",Font.PLAIN,12));//设置柱顶数据字体
     renderer.setItemLabelsVisible(true);//打开ItemLabel开关  
     catplot.setRenderer(renderer);//将修改后的属性值保存到图中
     catplot.setForegroundAlpha(0.6f);//柱的透明度


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
      BufferedImage chartImage = chart.createBufferedImage(500, 350, info);

      session.setAttribute("huanbi_sr", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_sr", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_comparison";
    return chartViewer;
  }

  private DefaultCategoryDataset getDataset() {
// categories...
	  dataStr = getDataStr();
	  cityStr = getCityStr();
	  timeStr = getTimeStr();
	  System.out.println("######"+dataStr+"####"+cityStr);
	 // if(dataStr.length() != 0){
		  String[] data = dataStr.split("/");
	  //}
	  //if(cityStr.length() != 0){
		  String[] city = cityStr.split(";");
		  
	 // }
		  String[] time = timeStr.split(";");
    // create the dataset...
    DefaultCategoryDataset linedataset = new DefaultCategoryDataset();	  
    //  各曲线名称  
    for(int i = 0;i<city.length;i++){
    	String[] datalie = data[i].split(";");
    	for(int j = 0;j<datalie.length;j++){
    		linedataset.addValue(Double.parseDouble(datalie[j]), city[i], time[j]); 
    	}
    }
  
  return linedataset;  

  }

public String getCityStr() {
	return cityStr;
}

public void setCityStr(String cityStr) {
	this.cityStr = cityStr;
}

public String getDataStr() {
	return dataStr;
}

public void setDataStr(String dataStr) {
	this.dataStr = dataStr;
}

public String getTimeStr() {
	return timeStr;
}

public void setTimeStr(String timeStr) {
	this.timeStr = timeStr;
}

public String getChartTitle() {
	return chartTitle;
}

public void setChartTitle(String chartTitle) {
	this.chartTitle = chartTitle;
}


}
