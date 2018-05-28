package com.noki.mobi.analysis.javabean;

import java.io.PrintWriter;
import java.awt.image.BufferedImage;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.entity.StandardEntityCollection;
import javax.servlet.http.HttpSession;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import java.awt.Color;
import org.jfree.chart.ChartUtilities;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

public class GraphHB_SR {
  private double x_sr = 0;
  private double s_sr = 0;
  private double x_zc = 0;
  private double s_zc = 0;
  private double hx_sr = 0;
  private double hx_bc = 0;
  private double hx_yw = 0;
  private double hx_qt = 0;
  private double hx_zc = 0;
  private double hx_jf = 0;
  private double hx_bj = 0;
  private double hx_jz = 0;

  private double hs_brzk = 0;
  private double hs_cb = 0;
  private double hs_sb = 0;
  private double hs_dj = 0;
  private double hs_ss = 0;
  private double hs_jy = 0;
  private double hs_qt = 0;

  private double hs_zkzc = 0;
  private double hs_bcjf = 0;
  private double hs_bczk = 0;
  private double hs_sjsjzc = 0;
  private double hs_fsbz = 0;
  private double hs_syzc = 0;
  private double hs_jyzc = 0;
  private double hs_zcjj = 0;

  public GraphHB_SR() {
  }

  public void set_hs_zc(String hs_zkzc, String hs_bcjf, String hs_bczk,
                        String hs_sjsjzc, String hs_fsbz, String hs_syzc,
                        String hs_jyzc, String hs_zcjj) {
    this.hs_zkzc = Double.parseDouble(hs_zkzc);
    this.hs_bcjf = Double.parseDouble(hs_bcjf);
    this.hs_bczk = Double.parseDouble(hs_bczk);
    this.hs_sjsjzc = Double.parseDouble(hs_sjsjzc);
    this.hs_fsbz = Double.parseDouble(hs_fsbz);
    this.hs_syzc = Double.parseDouble(hs_syzc);
    this.hs_jyzc = Double.parseDouble(hs_jyzc);
    this.hs_zcjj = Double.parseDouble(hs_zcjj);
  }

  public void set_hs_sr(String hs_brzk, String hs_cb, String hs_sb,
                        String hs_dj, String hs_ss, String hs_jy, String hs_qt) {
    this.hs_brzk = Double.parseDouble(hs_brzk);
    this.hs_cb = Double.parseDouble(hs_cb);
    this.hs_sb = Double.parseDouble(hs_sb);
    this.hs_dj = Double.parseDouble(hs_dj);
    this.hs_ss = Double.parseDouble(hs_ss);
    this.hs_jy = Double.parseDouble(hs_jy);
    this.hs_qt = Double.parseDouble(hs_qt);

  }

  public void set_sr(String x_sr, String s_sr) {
    this.x_sr = Double.parseDouble(x_sr);
    this.s_sr = Double.parseDouble(s_sr);
  }

  public void set_zc(String x_zc, String s_zc) {
    this.x_zc = Double.parseDouble(x_zc);
    this.s_zc = Double.parseDouble(s_zc);
  }

  public void set_hx_sr(String hx_bc, String hx_yw, String hx_qt) {
    this.hx_sr = Double.parseDouble(hx_bc);
    this.hx_bc = Double.parseDouble(hx_bc);
    this.hx_yw = Double.parseDouble(hx_yw);
  }

  public void set_hx_zc(String hx_bc, String hx_yw, String hx_qt) {
    this.hx_jf = Double.parseDouble(hx_bc);
    this.hx_bj = Double.parseDouble(hx_yw);
    this.hx_jz = Double.parseDouble(hx_qt);
  }

  private DefaultPieDataset getDataset_hx_zc() {
// categories...
    String[] section = new String[] {
        "经费支出", "拨出经费", "结转自筹基建"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], hx_jf);
    dataset.setValue(section[1], hx_bj);
    dataset.setValue(section[2], hx_jz);
    return dataset;
  }

  private DefaultPieDataset getDataset_hx_sr() {
// categories...
    String[] section = new String[] {
        "拨入经费", "预算外资金收入", "其他收入"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], hx_sr);
    dataset.setValue(section[1], hx_bc);
    dataset.setValue(section[2], hx_yw);
    return dataset;
  }

  private DefaultPieDataset getDataset() {
// categories...
    String[] section = new String[] {
        "行政单位收入", "事业单位收入"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], x_sr);
    dataset.setValue(section[1], s_sr);

    return dataset;
  }

  private DefaultPieDataset getDataset_zc() {
// categories...
    String[] section = new String[] {
        "行政单位支出", "事业单位支出"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], x_zc);
    dataset.setValue(section[1], s_zc);

    return dataset;
  }

  private DefaultPieDataset getDataset_hs_sr() {
// categories...
    String[] section = new String[] {
        "拨入专款", "财政补助收入", "上级补助收入", "单位缴款", "事业收入", "经营收入", "其他收入"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], hs_brzk);
    dataset.setValue(section[1], hs_cb);
    dataset.setValue(section[2], hs_sb);
    dataset.setValue(section[3], hs_dj);
    dataset.setValue(section[4], hs_ss);
    dataset.setValue(section[5], hs_jy);
    dataset.setValue(section[6], hs_qt);

    return dataset;
  }

  private DefaultPieDataset getDataset_hs_zc() {
// categories...
    String[] section = new String[] {
        "专款支出", "拨出经费", "拨出专款", "上缴上级支出", "对附属单位补助", "事业支出", "经营支出", "结转自筹基建"};

    // create the dataset...String , String , String ,

    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], hs_zkzc);
    dataset.setValue(section[1], hs_bcjf);
    dataset.setValue(section[2], hs_bczk);
    dataset.setValue(section[3], hs_sjsjzc);
    dataset.setValue(section[4], hs_fsbz);
    dataset.setValue(section[5], hs_syzc);
    dataset.setValue(section[6], hs_jyzc);
    dataset.setValue(section[7], hs_zcjj);
    return dataset;
  }

  public synchronized String getChartViewer_hs_zc(HttpServletRequest request,
                                                  HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset_hs_zc();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "支出对比", // chart title
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
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(400, 350, info);

      session.setAttribute("huanbi_hs_zc", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_hs_zc", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_hs_zc";
    return chartViewer;
  }

  public synchronized String getChartViewer_hs_sr(HttpServletRequest request,
                                                  HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset_hs_sr();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "收入对比", // chart title
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
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(400, 350, info);

      session.setAttribute("huanbi_hs_sr", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_hs_sr", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_hs_sr";
    return chartViewer;
  }

  public synchronized String getChartViewer_hx_zc(HttpServletRequest request,
                                                  HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset_hx_zc();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "支出对比", // chart title
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
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(350, 300, info);

      session.setAttribute("huanbi_x_zc", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_x_zc", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_x_zc";
    return chartViewer;
  }

  public synchronized String getChartViewer_hx_sr(HttpServletRequest request,
                                                  HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset_hx_sr();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "收入对比", // chart title
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
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(350, 300, info);

      session.setAttribute("huanbi_x_sr", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_x_sr", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_x_sr";
    return chartViewer;
  }

  public synchronized String getChartViewer(HttpServletRequest request,
                                            HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "收入对比", // chart title
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
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(300, 300, info);

      session.setAttribute("huanbi_sr", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_sr", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_sr";
    return chartViewer;
  }

  public synchronized String getChartViewer_zc(HttpServletRequest request,
                                               HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset_zc();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "支出对比", // chart title
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
    HttpSession session = request.getSession();

    try {
      //Create RenderingInfo object
      response.setContentType("text/html");
      info = new ChartRenderingInfo(new StandardEntityCollection());
      BufferedImage chartImage = chart.createBufferedImage(300, 300, info);

      session.setAttribute("huanbi_zc", chartImage);
      PrintWriter writer = new PrintWriter(response.getWriter());
      ChartUtilities.writeImageMap(writer, "huanbi_zc", info, true);
      writer.flush();
    }
    catch (Exception e) {}

    String pathInfo = "http://";
    pathInfo += request.getServerName();
    int port = request.getServerPort();
    pathInfo += ":" + String.valueOf(port);
    pathInfo += request.getContextPath();
    String chartViewer = pathInfo + "/servlet/huanbi_zc";
    return chartViewer;
  }

}
