package com.noki.query.tjhzquery.javabean;

import org.jfree.chart.entity.StandardEntityCollection;
import javax.servlet.http.HttpSession;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ChartFactory;
import java.text.DecimalFormat;
import org.jfree.chart.ChartRenderingInfo;
import java.io.PrintWriter;
import java.awt.Color;
import javax.servlet.http.HttpServletResponse;
import org.jfree.data.general.DefaultPieDataset;
import javax.servlet.http.HttpServletRequest;
import org.jfree.chart.ChartUtilities;
import java.text.NumberFormat;
import org.jfree.chart.JFreeChart;
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
public class Hb_nhl {
  private String cityStr;
  private String dataStr;
  public Hb_nhl() {
  }

  public synchronized BufferedImage getChartViewer(String city,String data) {
	  dataStr = data;
	  cityStr = city;
    DefaultPieDataset dataset = getDataset();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "耗电量统计查询", // chart title
        dataset, // data
        true, // include legend
        true,
        false);
    // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setNoDataMessage("No data available");

    // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
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
        "区域专业分摊金额占比饼形图", // chart title
        dataset, // data
        true, // include legend
        true,
        false);
    // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setNoDataMessage("No data available");

    // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
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
    	dataset.setValue((i+1)+"、"+city[i], Double.parseDouble(data[i]));
    }
    
    return dataset;
  }

  //自动审核分析
  public synchronized BufferedImage getChartViewer3(String city,String data) {
	  dataStr = data;
	  cityStr = city;
    DefaultPieDataset dataset = getDataset();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "自动审核分析饼形图", // chart title
        dataset, // data
        true, // include legend
        true,
        false);
    // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setNoDataMessage("No data available");

    // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
    // plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
    //     "{0}={1}({2})"));

    ChartRenderingInfo info = null;
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(400, 310, info);
     
    return chartImage;
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
