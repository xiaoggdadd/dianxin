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
		dataset.setValue(" 节能站点"+mat.format(jieneng1)+"%", jieneng1);
		dataset.setValue(" 非节能站点"+mat.format(fjieneng1)+"%", fjieneng1);
		
		return dataset;
	}
	public  JFreeChart createPieChart3D(String jieneng2){
		
		PieDataset dataset = getDataSet(jieneng2);
		JFreeChart chart = ChartFactory.createPieChart3D(
				" 节能设备应用比例",// 图表标题
				dataset,// data
				true,// include legend
				true, true);
		chart.setBackgroundPaint(Color.ORANGE);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 指定图片的透明度(0.0-1.0)
		plot.setForegroundAlpha(1.0f);
		// 指定显示的饼图上圆形(true)还椭圆形(false)
		plot.setCircular(true);
		// 设置图上分类标签label的字体，解决中文乱码
		plot.setLabelFont(new Font("黑体", Font.PLAIN, 12));
		// 设置图上分类标签label的最大宽度，相对与plot的百分比
		plot.setMaximumLabelWidth(0.2);
		// 设置3D饼图的Z轴高度（0.0～1.0）
		plot.setDepthFactor(0.07);
		//设置起始角度，默认值为90，当为0时，起始值在3点钟方向
		plot.setStartAngle(45);
		// 设置图标题的字体，解决中文乱码
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		// 设置背景色为白色
		//chart.setBackgroundPaint(Color.white);
		// 设置Legend部分字体，解决中文乱码
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
		
		  return chart;
		  
	}
	
	

}
