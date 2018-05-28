package com.noki.tongjichaxu.servlet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class MapUtilO {
	 private String cityStr;
	  private String dataStr;
	  public MapUtilO() {
	  }

	  public synchronized BufferedImage getChartViewer(String city,String data) {
		  dataStr = data;
		  cityStr = city;
	    DefaultPieDataset dataset = getDataset();
	    // create the chart...
	    JFreeChart chart = ChartFactory.createPieChart3D(
	        "�ĵ���ͳ�Ʋ�ѯ", // chart title
	        dataset, // data
	        true, // include legend
	        true,
	        false);
	    // set the background color for the chart...
	    chart.setBackgroundPaint(Color.ORANGE);
	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setNoDataMessage("No data available");

	    // ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
	        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
	// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
	    // plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
	    //     "{0}={1}({2})"));

	    ChartRenderingInfo info = null;
	      info = new ChartRenderingInfo(new StandardEntityCollection());
	      BufferedImage chartImage = chart.createBufferedImage(500, 350, info);
	     
	    return chartImage;
	  }
	  public synchronized BufferedImage getChartViewer1(String city,String data) {
		  dataStr = data;
		  cityStr = city;
	    DefaultPieDataset dataset = getDataset();
	    // create the chart...
	    JFreeChart chart = ChartFactory.createPieChart3D(
	        "", // chart title
	        dataset, // data
	        true, // include legend
	        true,
	        false);
	    // set the background color for the chart...
	    chart.setBackgroundPaint(Color.ORANGE);
	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setNoDataMessage("No data available");

	    // ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
	        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
	// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
	    // plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
	    //     "{0}={1}({2})"));

	    ChartRenderingInfo info = null;
	      info = new ChartRenderingInfo(new StandardEntityCollection());
	      BufferedImage chartImage = chart.createBufferedImage(400, 310, info);
	     
	    return chartImage;
	  }

	  private DefaultPieDataset getDataset() {
	// categories...
		 
		  System.out.println("######"+dataStr+"####"+cityStr);
		 // if(dataStr.length() != 0){
			  String[] data = dataStr.split(";");
		  //}
		  //if(cityStr.length() != 0){
			  String[] city = cityStr.split(";");
			  
		 // }
	      //int length = city.length;
	    // create the dataset...
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    for(int i = 0;i<city.length;i++){
	    	dataset.setValue((i+1)+"��"+city[i], Double.parseDouble(data[i]));
	    }
	    
	    return dataset;
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

}
