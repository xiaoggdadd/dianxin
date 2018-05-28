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
		//���ݵĳ�ʼ��
        CategoryDataset mDataset = GetDataset();      
        
        JFreeChart mBarChart = ChartFactory.createBarChart(  
                "ѧУ��Ա�ֲ�ͼ",  //ͼ�����  
                "����",       //���ᣨĿ¼�ᣩ��ǩ  
                "����",           //���ᣨ��ֵ�ᣩ��ǩ  
                mDataset,       //���ݼ�  
                PlotOrientation.VERTICAL,   //ͼ����  
                true,           //�Ƿ���ʾͼ��  
                true,           //�Ƿ�������ʾ����  
                false);         //�Ƿ�����url����  
        //ͼ���������  
        TextTitle mTextTitle = mBarChart.getTitle();  
        mTextTitle.setFont(new Font("����", Font.BOLD, 20));  
        //mBarChart.setTitle(new TextTitle("ѧУ��Ա�ֲ�ͼ",new Font("����", Font.BOLD, 20)));  
        //ͼ��ͼ������  
        LegendTitle mLegend = mBarChart.getLegend();  
        if(mLegend != null)  
            mLegend.setItemFont(new Font("����", Font.CENTER_BASELINE, 15));  
        //mBarChart.getLegend().setItemFont(new Font("����", Font.CENTER_BASELINE, 15));  
        //������״ͼ��  
        CategoryPlot mPlot = (CategoryPlot)mBarChart.getPlot();  
          
        //x��  
        CategoryAxis mDomainAxis = mPlot.getDomainAxis();  
        //����x����������  
        mDomainAxis.setLabelFont(new Font("����", Font.PLAIN, 15));  
        //����x����������  
        mDomainAxis.setTickLabelFont(new Font("����", Font.PLAIN, 15));  
        //y��  
        ValueAxis mValueAxis = mPlot.getRangeAxis();  
        //����y���������  
        mValueAxis.setLabelFont(new Font("����", Font.PLAIN, 15));  
        //����y����������  
        mValueAxis.setTickLabelFont(new Font("����", Font.PLAIN, 15));  
        //������ʾ��ֵ  
        BarRenderer mRenderer= new BarRenderer();  
        mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        mRenderer.setItemLabelFont(new Font("����", Font.PLAIN, 10));  
        mRenderer.setItemLabelsVisible(true);  
        mPlot.setRenderer(mRenderer);  
          
        ChartFrame mChartFrame = new ChartFrame("ѧУ��Ա�ֲ�ͼ", mBarChart);  
        mChartFrame.pack();  
        mChartFrame.setVisible(true);  
    }  
    public static CategoryDataset GetDataset()  
    {  
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();  
        mDataset.addValue(2000, "�廪��ѧ", "������");  
        mDataset.addValue(2000, "������ѧ", "������");  
        mDataset.addValue(2000, "������ѧ", "������");  
        mDataset.addValue(1500, "�廪��ѧ", "�о���");  
        mDataset.addValue(1500, "������ѧ", "�о���");  
        mDataset.addValue(1500, "������ѧ", "�о���");  
        mDataset.addValue(1000, "�廪��ѧ", "��ʿ��");  
        mDataset.addValue(1000, "������ѧ", "��ʿ��");  
        mDataset.addValue(1000, "������ѧ", "��ʿ��");  
        mDataset.addValue(900, "�廪��ѧ", "��ʦ");  
        mDataset.addValue(900, "������ѧ", "��ʦ");  
        mDataset.addValue(900, "������ѧ", "��ʦ");  
        mDataset.addValue(800, "�廪��ѧ", "������");  
        mDataset.addValue(800, "������ѧ", "������");  
        mDataset.addValue(800, "������ѧ", "������");  
        mDataset.addValue(300, "�廪��ѧ", "����");  
        mDataset.addValue(300, "������ѧ", "����");  
        mDataset.addValue(300, "������ѧ", "����");  
        mDataset.addValue(600, "�廪��ѧ", "������Ա");  
        mDataset.addValue(600, "������ѧ", "������Ա");  
        mDataset.addValue(600, "������ѧ", "������Ա");  
        mDataset.addValue(400, "�廪��ѧ", "������Ա");  
        mDataset.addValue(400, "������ѧ", "������Ա");  
        mDataset.addValue(400, "������ѧ", "������Ա");  
        /* 
        mDataset.addValue(2000, "�廪��ѧ", "������"); 
        mDataset.addValue(1500, "�廪��ѧ", "�о���"); 
        mDataset.addValue(1000, "�廪��ѧ", "��ʿ��"); 
        mDataset.addValue(900, "�廪��ѧ", "��ʦ"); 
        mDataset.addValue(800, "�廪��ѧ", "������"); 
        mDataset.addValue(300, "�廪��ѧ", "����"); 
        mDataset.addValue(600, "�廪��ѧ", "������Ա"); 
        mDataset.addValue(400, "�廪��ѧ", "������Ա"); 
        */  
           
        return mDataset;  
    }  
	
}
