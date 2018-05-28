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
    	JFreeChart chart=ChartFactory.createBarChart3D("用电排名",
                null,
                null,
                dataset,PlotOrientation.VERTICAL,true,false,false);
    	chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,12)); 
    	//设置标题字体 
    	TextTitle textTitle = chart.getTitle(); 
    	textTitle.setFont(new Font("黑体", Font.PLAIN, 20)); 
    	chart.setBackgroundPaint(Color.ORANGE);
    	//textTitle.setBackgroundPaint(Color.LIGHT_GRAY);//标题背景色 
    	//textTitle.setPaint(Color.cyan);//标题字体颜色 
    	//textTitle.setText("类型统计图");//标题内容 
    	CategoryPlot plot = chart.getCategoryPlot();//设置图的高级属性 
    	BarRenderer3D renderer = new BarRenderer3D();//3D属性修改 
    	CategoryAxis domainAxis = plot.getDomainAxis();//对X轴做操作 
    	ValueAxis rAxis = plot.getRangeAxis();//对Y轴做操作 
    	
    	/** 
    	* renderer设置 柱子相关属性设置 
    	*/renderer.setBaseOutlinePaint(Color.ORANGE); //边框颜色 
    	renderer.setDrawBarOutline(true); 
    	renderer.setWallPaint(Color.gray);//设置墙体颜色 
    	renderer.setMaximumBarWidth(0.08); //设置柱子宽度 
    	renderer.setMinimumBarLength(0.1); //设置柱子高度 
    	renderer.setSeriesPaint(0,Color.ORANGE); //设置柱的颜色 
    	renderer.setItemMargin(0); //设置每个地区所包含的平行柱的之间距离 
    	//在柱子上显示相应信息 
    	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
    	renderer.setBaseItemLabelsVisible(true); 
    	renderer.setBaseItemLabelPaint(Color.BLACK);//设置数值颜色，默认黑色 
    	//搭配ItemLabelAnchor TextAnchor 这两项能达到不同的效果，但是ItemLabelAnchor最好选OUTSIDE，因为INSIDE显示不出来 
    	renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT)); 
    	//下面可以进一步调整数值的位置，但是得根据ItemLabelAnchor选择情况. 
    	renderer.setItemLabelAnchorOffset(10); 
    	/** 
    	* plot 设置 
    	***/ 
    	
    	//图片背景色 
    	plot.setBackgroundPaint(Color.LIGHT_GRAY); 
    	plot.setOutlineVisible(true); 
    	//图边框颜色 
    	plot.setOutlinePaint(Color.magenta); 
    	//设置柱的透明度 
    	plot.setForegroundAlpha(1.0f); 
    	//将类型放到上面 
    	plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT); 
    	//将默认放到左边的数值放到右边 
    	plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT); 
    	plot.setRenderer(renderer);//将修改后的属性值保存到图中 


    	
    	return chart;
    }
}
