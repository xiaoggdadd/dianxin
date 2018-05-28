package com.noki.tongjichaxu;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class GuanliChart {

	  public  BufferedImage getChart(String bz,String diqu,List time,List data) {
			DefaultCategoryDataset dataset = getDataset(diqu,time,data);
		    // create the chart...
			String s="";
			BufferedImage chartImage=null;
           if(bz.equals("4")){
				
				s="�����ºĵ���";
			}
			if(bz.equals("3")){
				
				s="��վ�վ��ĵ���";
			}
           if(bz.equals("1")){
				
				s="��վ�ºĵ���";
			}
		    JFreeChart chart = ChartFactory.createLineChart(s, "ʱ��", "��ֵ",dataset,PlotOrientation.VERTICAL,true,false,false);
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
		        chartImage = chart.createBufferedImage(650, 200, info);

		     }
		     catch (Exception e) {
		     	e.printStackTrace();
		     }
		    return chartImage;
		  }

		  private DefaultCategoryDataset getDataset(String diqu,List time,List data) {
		        // categories...
		    // create the dataset...
		    DefaultCategoryDataset linedataset = new DefaultCategoryDataset();	  
		    //  ����������  
		    for(int i = 0;i<time.size();i++){
		    		linedataset.addValue(Double.parseDouble(data.get(i).toString()),diqu,time.get(i).toString()); 
		    	    
		    }
		  return linedataset;  

		  }
		  public  BufferedImage getChart( String zdname,List<Integer> y,List<Integer> m,List<Integer> d,List data1,List data2) {
			  	XYDataset  dataset = getDataset(y,m,d,data1,data2);
			    // create the chart...
				BufferedImage chartImage=null;
			    JFreeChart chart = ChartFactory.createTimeSeriesChart(zdname+"�պĵ�����", "ʱ��", "��ֵ",dataset,true,false,false);
			    // set the background color for the chart...
			    chart.setBackgroundPaint(Color.ORANGE);
			   
			    XYPlot xyplot = (XYPlot) chart.getPlot();  //��ȡ��״ͼ������ͼ�������  

			    DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis(); 

			    xyplot.setNoDataMessage("��������ʾ");//û�����ݵ�ʱ����ʾ������  

			      //�б����  

			     TextTitle txtTitle = null;  

			     txtTitle = chart.getTitle();  

			     txtTitle.setFont(new java.awt.Font("����",Font.BOLD,14));  

			     //ˮƽ�ײ��б�  
			     domainAxis.setLabelFont(new java.awt.Font("����",Font.BOLD,14));  

			     //ˮƽ�ײ�����  

			     domainAxis.setTickLabelFont(new java.awt.Font("����",Font.BOLD,12));  

			     //��ֱ����  

			     ValueAxis rangeAxis=xyplot.getRangeAxis();//��ȡ��״  

			     rangeAxis.setLabelFont(new java.awt.Font("����",Font.BOLD,15));  

			     chart.getLegend().setItemFont(new java.awt.Font("����", Font.BOLD, 15));  

			     //���renderer  

			     ChartRenderingInfo info = null;

			     try {
			       //Create RenderingInfo object
			       info = new ChartRenderingInfo(new StandardEntityCollection());
			        chartImage = chart.createBufferedImage(750, 200, info);

			     }
			     catch (Exception e) {
			     	e.printStackTrace();
			     }
			    return chartImage;
			}

			private XYDataset getDataset(List<Integer> y,List<Integer> m,List<Integer> d,List data1,List data2) {
			TimeSeries timeSeries1 = new TimeSeries("", Day.class);
			TimeSeries timeSeries2 = new TimeSeries("", Day.class);
			    //  ����������  
			    for(int i = 0;i<y.size();i++){
			    	timeSeries1.add(new Day(d.get(i),m.get(i),y.get(i)), Double.parseDouble(data1.get(i).toString())); 
			    	// timeSeries2.add(new Day(d.get(i),m.get(i),y.get(i)), Double.parseDouble(data2.get(i).toString())); 
			    }
			    TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
			    timeseriescollection.addSeries(timeSeries1); 
			   // timeseriescollection.addSeries(timeSeries2); 
			    return timeseriescollection; 
			}
}
