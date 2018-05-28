package com.noki.query.jcanalysis.javabean;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.noki.mobi.cx.chart.GetDataSet;

public class Jienengsheibeibili {
	private  String jieneng;
	private  String fjieneng;

	
	public  String getJieneng() {
		return jieneng;
	}
	public  void setJieneng(String jieneng) {
		this.jieneng = jieneng;
	}
	public  String getFjieneng() {
		return fjieneng;
	}
	public  void setFjieneng(String fjieneng) {
		this.fjieneng = fjieneng;
	}
	private static PieDataset getDataSet(String jieneng) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		double jieneng1=Double.parseDouble(jieneng);
		double fjieneng1=100-jieneng1;
		 DecimalFormat mat=new DecimalFormat("0.00");
		dataset.setValue(" ����վ��"+mat.format(jieneng1)+"%", jieneng1);
		dataset.setValue(" �ǽ���վ��"+mat.format(fjieneng1)+"%", fjieneng1);
		
		return dataset;
	}
	public  JFreeChart createPieChart3D(String jieneng2){
		
		PieDataset dataset = getDataSet(jieneng2);
		JFreeChart chart = ChartFactory.createPieChart3D(
				" �����豸Ӧ�ñ���",// ͼ�����
				dataset,// data
				true,// include legend
				true, true);
		chart.setBackgroundPaint(Color.ORANGE);
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
		// ���ñ���ɫΪ��ɫ
		//chart.setBackgroundPaint(Color.white);
		// ����Legend�������壬�����������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
		
		  return chart;
		  
	}
	
	

}
