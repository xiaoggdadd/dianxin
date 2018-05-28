package com.noki.query.hbanalysisquery.javabean;

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
public class Hb_hbanalysis {

  public Hb_hbanalysis() {
  }

  public  BufferedImage getChart(String[] time,String dataStr) {

	BufferedImage chartImage=null;
	DefaultCategoryDataset dataset = this.getDataset(time,dataStr);
    // create the chart...
    JFreeChart chart = ChartFactory.createLineChart("地市环比（趋势）分析", "月份", "数值",dataset,PlotOrientation.VERTICAL,true,false,false);
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

     chart.getLegend().setItemFont(new java.awt.Font("黑体", Font.BOLD, 12));  

     //获得renderer  

     LineAndShapeRenderer lineAndShapeRenderer =(LineAndShapeRenderer)catplot.getRenderer();  

     lineAndShapeRenderer.setShapesVisible(true); //series 点（即数据点）可见  

    /*  

     lineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {  

      10F, 6F  

     }, 0.0F)); //这里是虚线，默认是直线  */

    ChartRenderingInfo info = null;

    try {
      //Create RenderingInfo object
      info = new ChartRenderingInfo(new StandardEntityCollection());
       chartImage = chart.createBufferedImage(960, 380, info);

    }
    catch (Exception e) {
    	e.printStackTrace();
    }
    return chartImage;
  }

  private DefaultCategoryDataset getDataset(String[] time,String dataStr) {
// categories...
	  String cityStr="济南;青岛;淄博;滨州";
	  System.out.println("######"+dataStr+"####"+cityStr);
	 // if(dataStr.length() != 0){
		  String[] data = dataStr.split(";");
	  //}
	  //if(cityStr.length() != 0){
		  String[] city = cityStr.split(";");
		  
	 // }
		//  String[] time = timeStr.split(";");
    // create the dataset...
    DefaultCategoryDataset linedataset = new DefaultCategoryDataset();	  
    //  各曲线名称  
    for(int i = 0;i<city.length;i++){
    	String[] datalie = data[i].split(",");
    	for(int j = 0;j<datalie.length;j++){
    		linedataset.addValue(Double.parseDouble(datalie[j]), city[i], time[j]); 
    	}
    }
  
  return linedataset;  

  }





}
