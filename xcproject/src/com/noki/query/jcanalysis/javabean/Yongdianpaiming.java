package com.noki.query.jcanalysis.javabean;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;



public class Yongdianpaiming {
       private String shi;
       private String zdnum;
       private String haodiannum;
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getZdnum() {
		return zdnum;
	}
	public void setZdnum(String zdnum) {
		this.zdnum = zdnum;
	}
	public String getHaodiannum() {
		return haodiannum;
	}
	public void setHaodiannum(String haodiannum) {
		this.haodiannum = haodiannum;
	}
    public DefaultCategoryDataset getDataset(List shiList,List dataList){
    	DefaultCategoryDataset dataset=new DefaultCategoryDataset();
    	List shilist=shiList;
    	List datalist=dataList;
    	System.out.println("list:"+shilist.toString());
    	for(int i=0;i<shilist.size();i++){
    		dataset.setValue(Double.parseDouble((String) datalist.get(i)), (String)shilist.get(i), (String)shilist.get(i));
    	}
    	
    	return dataset;
    }
    public JFreeChart getchart(List shiList,List dataList){
    	CategoryDataset dataset=getDataset(shiList,dataList);
    	JFreeChart chart=ChartFactory.createBarChart3D("�õ�����",
                null,
                null,
                dataset,PlotOrientation.VERTICAL,true,false,false);
    	chart.getLegend().setItemFont(new Font("����",Font.PLAIN,12)); 
    	//���ñ������� 
    	TextTitle textTitle = chart.getTitle(); 
    	textTitle.setFont(new Font("����", Font.PLAIN, 20)); 
    	chart.setBackgroundPaint(Color.ORANGE);
    	//textTitle.setBackgroundPaint(Color.LIGHT_GRAY);//���ⱳ��ɫ 
    	//textTitle.setPaint(Color.cyan);//����������ɫ 
    	//textTitle.setText("����ͳ��ͼ");//�������� 
    	CategoryPlot plot = chart.getCategoryPlot();//����ͼ�ĸ߼����� 
    	BarRenderer3D renderer = new BarRenderer3D();//3D�����޸� 
    	CategoryAxis domainAxis = plot.getDomainAxis();//��X�������� 
    	ValueAxis rAxis = plot.getRangeAxis();//��Y�������� 
    	
    	/** 
    	* renderer���� ��������������� 
    	*/renderer.setBaseOutlinePaint(Color.ORANGE); //�߿���ɫ 
    	renderer.setDrawBarOutline(true); 
    	renderer.setWallPaint(Color.gray);//����ǽ����ɫ 
    	renderer.setMaximumBarWidth(0.08); //�������ӿ�� 
    	renderer.setMinimumBarLength(0.1); //�������Ӹ߶� 
    	renderer.setSeriesPaint(0,Color.ORANGE); //����������ɫ 
    	renderer.setItemMargin(0); //����ÿ��������������ƽ������֮����� 
    	//����������ʾ��Ӧ��Ϣ 
    	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
    	renderer.setBaseItemLabelsVisible(true); 
    	renderer.setBaseItemLabelPaint(Color.BLACK);//������ֵ��ɫ��Ĭ�Ϻ�ɫ 
    	//����ItemLabelAnchor TextAnchor �������ܴﵽ��ͬ��Ч��������ItemLabelAnchor���ѡOUTSIDE����ΪINSIDE��ʾ������ 
    	renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT)); 
    	//������Խ�һ��������ֵ��λ�ã����ǵø���ItemLabelAnchorѡ�����. 
    	renderer.setItemLabelAnchorOffset(10); 
    	/** 
    	* plot ���� 
    	***/ 
    	
    	//ͼƬ����ɫ 
    	plot.setBackgroundPaint(Color.LIGHT_GRAY); 
    	plot.setOutlineVisible(true); 
    	//ͼ�߿���ɫ 
    	plot.setOutlinePaint(Color.magenta); 
    	//��������͸���� 
    	plot.setForegroundAlpha(1.0f); 
    	//�����ͷŵ����� 
    	plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT); 
    	//��Ĭ�Ϸŵ���ߵ���ֵ�ŵ��ұ� 
    	plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT); 
    	plot.setRenderer(renderer);//���޸ĺ������ֵ���浽ͼ�� 


    	
    	return chart;
    }
}
