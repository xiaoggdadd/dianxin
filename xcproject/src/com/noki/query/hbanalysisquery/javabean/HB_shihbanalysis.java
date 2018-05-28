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
import java.util.List;

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
public class HB_shihbanalysis {
  private String cityStr;
  private String dataStr;
  private String timeStr;

  public HB_shihbanalysis() {
  }

  public  BufferedImage getChart(List city, String[] time,String data[]) {
	DefaultCategoryDataset dataset = getDataset(city,time,data);
    // create the chart...
	BufferedImage chartImage=null;
    JFreeChart chart = ChartFactory.createLineChart("���ȣ����ƣ�����", "ʱ��", "��ֵ",dataset,PlotOrientation.VERTICAL,true,false,false);
    // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
   
    CategoryPlot catplot=chart.getCategoryPlot();  //��ȡ��״ͼ������ͼ�������  

    CategoryAxis domainAxis=catplot.getDomainAxis();  

    catplot.setNoDataMessage("��������ʾ");//û�����ݵ�ʱ����ʾ������  

      //�б����  

     TextTitle txtTitle = null;  

     txtTitle = chart.getTitle();  

     txtTitle.setFont(new java.awt.Font("����",Font.BOLD,14));  

     //ˮƽ�ײ��б�  

     domainAxis.setLabelFont(new java.awt.Font("����",Font.BOLD,14));  

     //ˮƽ�ײ�����  

     domainAxis.setTickLabelFont(new java.awt.Font("����",Font.BOLD,12));  

     //��ֱ����  

     ValueAxis rangeAxis=catplot.getRangeAxis();//��ȡ��״  

     rangeAxis.setLabelFont(new java.awt.Font("����",Font.BOLD,15));  

     chart.getLegend().setItemFont(new java.awt.Font("����", Font.BOLD, 15));  

     //���renderer  

     LineAndShapeRenderer lineAndShapeRenderer =(LineAndShapeRenderer)catplot.getRenderer();  

     lineAndShapeRenderer.setShapesVisible(true); //series �㣨�����ݵ㣩�ɼ�  

    /*  

     lineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] {  

      10F, 6F  

     }, 0.0F)); //���������ߣ�Ĭ����ֱ��  */

     ChartRenderingInfo info = null;

     try {
       //Create RenderingInfo object
       info = new ChartRenderingInfo(new StandardEntityCollection());
        chartImage = chart.createBufferedImage(750, 380, info);

     }
     catch (Exception e) {
     	e.printStackTrace();
     }
    return chartImage;
  }

  private DefaultCategoryDataset getDataset(List city, String[] time,String data[]) {
        // categories...
    // create the dataset...
    DefaultCategoryDataset linedataset = new DefaultCategoryDataset();	  
    //  ����������  
    for(int i = 0;i<city.size();i++){
    	String[] datalie = data[i].split(",");
    	for(int j = 0;j<datalie.length;j++){
    		linedataset.addValue(Double.parseDouble(datalie[j]), city.get(i).toString(), time[j]); 
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


}
