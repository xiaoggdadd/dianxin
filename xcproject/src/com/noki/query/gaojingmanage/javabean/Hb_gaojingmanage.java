package com.noki.query.gaojingmanage.javabean;

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
public class Hb_gaojingmanage {
  private String cityStr;
  private String dataStr;
  private String timeStr;

  public Hb_gaojingmanage() {
  }

  public synchronized String getChartViewer(HttpServletRequest request,
                                            HttpServletResponse response) {
	DefaultCategoryDataset dataset = getDataset();
    // create the chart...
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
    String chartViewer = pathInfo + "/servlet/huanbi_gaojingmanage";
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
    //  ����������  
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


}
