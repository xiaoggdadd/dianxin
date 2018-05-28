package com.noki.htnygl.dlsjbd.tool;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * @author GT
 * 
 * */
public class StandarChartMap {

	public static void main(String[] args) {  
		//数据的初始化
        CategoryDataset mDataset = GetDataset();      
        
        JFreeChart mBarChart = ChartFactory.createBarChart(  
                "学校人员分布图",  //图表标题  
                "类型",       //横轴（目录轴）标签  
                "数量",           //纵轴（数值轴）标签  
                mDataset,       //数据集  
                PlotOrientation.VERTICAL,   //图表方向  
                true,           //是否显示图例  
                true,           //是否生成提示工具  
                false);         //是否生成url连接  
        //图表标题设置  
        TextTitle mTextTitle = mBarChart.getTitle();  
        mTextTitle.setFont(new Font("黑体", Font.BOLD, 20));  
        //mBarChart.setTitle(new TextTitle("学校人员分布图",new Font("黑体", Font.BOLD, 20)));  
        //图表图例设置  
        LegendTitle mLegend = mBarChart.getLegend();  
        if(mLegend != null)  
            mLegend.setItemFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
        //mBarChart.getLegend().setItemFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
        //设置柱状图轴  
        CategoryPlot mPlot = (CategoryPlot)mBarChart.getPlot();  
          
        //x轴  
        CategoryAxis mDomainAxis = mPlot.getDomainAxis();  
        //设置x轴标题的字体  
        mDomainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));  
        //设置x轴坐标字体  
        mDomainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 15));  
        //y轴  
        ValueAxis mValueAxis = mPlot.getRangeAxis();  
        //设置y轴标题字体  
        mValueAxis.setLabelFont(new Font("宋体", Font.PLAIN, 15));  
        //设置y轴坐标字体  
        mValueAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 15));  
        //柱体显示数值  
        BarRenderer mRenderer= new BarRenderer();  
        mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        mRenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 10));  
        mRenderer.setItemLabelsVisible(true);  
        mPlot.setRenderer(mRenderer);  
          
        ChartFrame mChartFrame = new ChartFrame("学校人员分布图", mBarChart);  
        mChartFrame.pack();  
        mChartFrame.setVisible(true);  
    }  
    public static CategoryDataset GetDataset()  
    {  
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();  
        mDataset.addValue(2000, "清华大学", "本科生");  
        mDataset.addValue(2000, "北京大学", "本科生");  
        mDataset.addValue(2000, "复旦大学", "本科生");  
        mDataset.addValue(1500, "清华大学", "研究生");  
        mDataset.addValue(1500, "北京大学", "研究生");  
        mDataset.addValue(1500, "复旦大学", "研究生");  
        mDataset.addValue(1000, "清华大学", "博士生");  
        mDataset.addValue(1000, "北京大学", "博士生");  
        mDataset.addValue(1000, "复旦大学", "博士生");  
        mDataset.addValue(900, "清华大学", "讲师");  
        mDataset.addValue(900, "北京大学", "讲师");  
        mDataset.addValue(900, "复旦大学", "讲师");  
        mDataset.addValue(800, "清华大学", "副教授");  
        mDataset.addValue(800, "北京大学", "副教授");  
        mDataset.addValue(800, "复旦大学", "副教授");  
        mDataset.addValue(300, "清华大学", "教授");  
        mDataset.addValue(300, "北京大学", "教授");  
        mDataset.addValue(300, "复旦大学", "教授");  
        mDataset.addValue(600, "清华大学", "行政人员");  
        mDataset.addValue(600, "北京大学", "行政人员");  
        mDataset.addValue(600, "复旦大学", "行政人员");  
        mDataset.addValue(400, "清华大学", "管理人员");  
        mDataset.addValue(400, "北京大学", "管理人员");  
        mDataset.addValue(400, "复旦大学", "管理人员");  
        /* 
        mDataset.addValue(2000, "清华大学", "本科生"); 
        mDataset.addValue(1500, "清华大学", "研究生"); 
        mDataset.addValue(1000, "清华大学", "博士生"); 
        mDataset.addValue(900, "清华大学", "讲师"); 
        mDataset.addValue(800, "清华大学", "副教授"); 
        mDataset.addValue(300, "清华大学", "教授"); 
        mDataset.addValue(600, "清华大学", "行政人员"); 
        mDataset.addValue(400, "清华大学", "管理人员"); 
        */  
           
        return mDataset;  
    }  
	
}
