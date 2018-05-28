package com.ptac.app.noadvicescb.piechart;

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

public class PieChartImp {

	private String cityStr;
	private String dataStr;
	public PieChartImp() {}
	
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
	
	  public synchronized BufferedImage getChartViewer(String city,String data) {
		  dataStr = data;
		  cityStr = city;
	    DefaultPieDataset dataset = getDataset();
	    JFreeChart chart = ChartFactory.createPieChart3D(
	        "ʡ���޽������������ͼ", 
	        dataset,
	        true,
	        true,
	        false);
	    
	    chart.setBackgroundPaint(Color.ORANGE);
	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setNoDataMessage("No data available");

	    // ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
	        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
	  
	    ChartRenderingInfo info = null;
	    info = new ChartRenderingInfo(new StandardEntityCollection());
	    BufferedImage chartImage = chart.createBufferedImage(620, 380, info);
	    return chartImage;
	  }
	  
	  private DefaultPieDataset getDataset() {
		
			String[] data = dataStr.split(";");
			String[] city = cityStr.split(";");
			
		    DefaultPieDataset dataset = new DefaultPieDataset();
		    for(int i = 0;i<city.length;i++){
		    	//dataset.setValue((i+1)+"��"+city[i], Double.parseDouble(data[i]));
		    	dataset.setValue(city[i], Double.parseDouble(data[i]));
		    }
		    return dataset;
	}
	
}
