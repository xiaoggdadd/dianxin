package com.noki.mobi.analysis.javabean;

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
public class Hb_tx {
  private double x_sr;
  private double s_sr;
  private double x_zc;
  private double s_zc;
  public Hb_tx() {
  }

  public synchronized String getChartViewer(HttpServletRequest request,
                                            HttpServletResponse response) {
    DefaultPieDataset dataset = getDataset();
    // create the chart...
    JFreeChart chart = ChartFactory.createPieChart3D(
        "����Ա�", // chart title
        dataset, // data
        true, // include legend
        true,
        false);
    // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setNoDataMessage("No data available");

    // ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
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
        "֧���Ա�", // chart title
        dataset, // data
        true, // include legend
        true,
        false);
    // set the background color for the chart...
    chart.setBackgroundPaint(Color.ORANGE);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setNoDataMessage("No data available");

    // ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
        NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
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

  private DefaultPieDataset getDataset_zc() {
// categories...
    String[] section = new String[] {
        "������λ֧��", "��ҵ��λ֧��"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], x_zc);
    dataset.setValue(section[1], s_zc);

    return dataset;
  }



  private DefaultPieDataset getDataset() {
// categories...
    String[] section = new String[] {
        "������λ����", "��ҵ��λ����"};

    // create the dataset...
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue(section[0], x_sr);
    dataset.setValue(section[1], s_sr);

    return dataset;
  }

  public void set_sr(String x_sr, String s_sr) {
    this.x_sr = Double.parseDouble(x_sr);
    this.s_sr = Double.parseDouble(s_sr);
  }

  public void set_zc(String x_zc, String s_zc) {
    this.x_zc = Double.parseDouble(x_zc);
    this.s_zc = Double.parseDouble(s_zc);
  }

  public void setX_sr(double x_sr) {
    this.x_sr = x_sr;
  }

  public void setS_sr(double s_sr) {
    this.s_sr = s_sr;
  }

  public void setX_zc(double x_zc) {
    this.x_zc = x_zc;
  }

  public void setS_zc(double s_zc) {
    this.s_zc = s_zc;
  }

  public double getX_sr() {
    return x_sr;
  }

  public double getS_sr() {
    return s_sr;
  }

  public double getX_zc() {
    return x_zc;
  }

  public double getS_zc() {
    return s_zc;
  }

}
