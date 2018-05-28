package com.noki.query.paranalysis.javabean;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import javax.servlet.http.HttpSession;

import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
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
import org.jfree.data.general.PieDataset;

import javax.servlet.http.HttpServletRequest;
import org.jfree.chart.ChartUtilities;
import java.text.NumberFormat;
import java.util.ArrayList;

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
public class Hb_hbpar {

  public Hb_hbpar() {
  }

	public  BufferedImage createPieChart3D(ArrayList city,ArrayList data){
		BufferedImage chartImage=null;
		PieDataset dataset = getDataSet(city,data);
		//System.out.println("jieneng2"+jieneng2);
		   
		
		JFreeChart chart = ChartFactory.createPieChart3D(
				"�ĵ����Ա�ͼ",// ͼ�����
				dataset,// data
				true,// include legend
				true, true);
		
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		


		// ָ��ͼƬ��͸����(0.0-1.0)
		plot.setForegroundAlpha(1.0f);
		// ָ����ʾ�ı�ͼ��Բ��(true)����Բ��(false)
		plot.setCircular(true);
		// ����ͼ�Ϸ����ǩlabel�����壬�����������
		plot.setLabelFont(new Font("����", Font.PLAIN, 12));
		// ����ͼ�Ϸ����ǩlabel������ȣ������plot�İٷֱ�
		plot.setMaximumLabelWidth(0.2);
		// ����3D��ͼ��Z��߶ȣ�0.0��1.0��
		plot.setDepthFactor(0.07);
		//������ʼ�Ƕȣ�Ĭ��ֵΪ90����Ϊ0ʱ����ʼֵ��3���ӷ���
		plot.setStartAngle(45);

		// ����ͼ��������壬�����������
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.PLAIN, 20));
		chart.setBackgroundPaint(Color.ORANGE);
		// ����Legend�������壬�����������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
		
		
		ChartRenderingInfo info = null;

	    try {
	      //Create RenderingInfo object
	      info = new ChartRenderingInfo(new StandardEntityCollection());
	       chartImage = chart.createBufferedImage(600, 380, info);

	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		  return chartImage;
		  
	}

	private static PieDataset getDataSet(ArrayList city,ArrayList data) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		 DecimalFormat mat=new DecimalFormat("0.0");
		 for(int i=0;i<city.size();i++){
           float  bate=(Float.parseFloat(data.get(i).toString()))/Float.parseFloat(data.get(city.size()).toString());
			dataset.setValue(city.get(i).toString()+mat.format(bate*100)+"%", Double.parseDouble(data.get(i).toString()));
		 }
		 
		return dataset;
	}


}
