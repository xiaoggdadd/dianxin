package com.noki.mobi.cx.chart;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.servlet.http.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class YongFangLX {
    private String yflx;
    private String dlhj;
    private String fyhj;
    private String jzmc;
    public YongFangLX() {
    }

    public String getBarChartViewer_zc(HttpServletRequest request,
                                       HttpServletResponse response) {
        GetDataSet yfset = new GetDataSet();
        CategoryDataset dataset = yfset.getDataset_yf(this.getDlhj(),
                this.getFyhj(),this.getYflx());
        // create the chart...
        JFreeChart chart = createChart_zc(dataset);
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.YELLOW);

        ChartRenderingInfo info = null;
        HttpSession session = request.getSession();

        try {
            //Create RenderingInfo object
            response.setContentType("text/html");
            info = new ChartRenderingInfo(new StandardEntityCollection());
            BufferedImage chartImage = chart.createBufferedImage(500, 350, info);
            // putting chart as BufferedImage in session,
            // thus making it available for the image reading action Action.
            session.setAttribute("image_yflx", chartImage);
            PrintWriter writer = new PrintWriter(response.getWriter());
            ChartUtilities.writeImageMap(writer, "image_yflx", info, true);
            writer.flush();
        } catch (Exception e) {}

        String pathInfo = "http://";
        pathInfo += request.getServerName();
        int port = request.getServerPort();
        pathInfo += ":" + String.valueOf(port);
        pathInfo += request.getContextPath();
        String chartViewer = pathInfo + "/servlet/chart_yflx";
        return chartViewer;
    }

    private JFreeChart createChart_zc(final CategoryDataset dataset) {

    final JFreeChart chart = ChartFactory.createBarChart3D(
        "用房类型能耗统计", // chart title
        "", // domain axis label
        "", // range axis label
        dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips
        false // urls
        );

    final CategoryPlot plot = chart.getCategoryPlot();
    final CategoryAxis axis = plot.getDomainAxis();
    BarRenderer renderer1 = new BarRenderer();
    renderer1.setItemLabelGenerator(new   StandardCategoryItemLabelGenerator());     
    renderer1.setItemLabelFont(new   Font("黑体",Font.PLAIN,12));     
    renderer1.setItemLabelsVisible(true);     

    axis.setCategoryLabelPositions(
        CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
        );
    plot.setRenderer(renderer1);
    axis.setTickLabelFont(new Font("SansSerif", Font.TRUETYPE_FONT, 14));
    final CategoryItemRenderer renderer = plot.getRenderer();
    //renderer.setItemLabelsVisible(true);
    final BarRenderer r = (BarRenderer) renderer;
    r.setMaximumBarWidth(0.5);

    return chart;

  }
  public String getBarChartViewer_jzdb(HttpServletRequest request,
                                       HttpServletResponse response) {
        GetDataSet yfset = new GetDataSet();
        CategoryDataset dataset = yfset.getDataset_jzdb(this.getDlhj(),
                this.getFyhj(),this.getJzmc());
        // create the chart...
        JFreeChart chart = createChart_jzdb(dataset);
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.YELLOW);

        ChartRenderingInfo info = null;
        HttpSession session = request.getSession();

        try {
            //Create RenderingInfo object
            response.setContentType("text/html");
            info = new ChartRenderingInfo(new StandardEntityCollection());
            BufferedImage chartImage = chart.createBufferedImage(500, 350, info);
            // putting chart as BufferedImage in session,
            // thus making it available for the image reading action Action.
            session.setAttribute("image_jzdb", chartImage);
            PrintWriter writer = new PrintWriter(response.getWriter());
            ChartUtilities.writeImageMap(writer, "image_jzdb", info, true);
            writer.flush();
        } catch (Exception e) {}

        String pathInfo = "http://";
        pathInfo += request.getServerName();
        int port = request.getServerPort();
        pathInfo += ":" + String.valueOf(port);
        pathInfo += request.getContextPath();
        String chartViewer = pathInfo + "/servlet/chart_jzdb";
        return chartViewer;
    }

    private JFreeChart createChart_jzdb(final CategoryDataset dataset) {

    final JFreeChart chart = ChartFactory.createBarChart3D(
        "站点对比分析", // chart title
        "", // domain axis label
        "", // range axis label
        dataset, // data
        PlotOrientation.VERTICAL, // orientation
        true, // include legend
        true, // tooltips
        false // urls
        );

    final CategoryPlot plot = chart.getCategoryPlot();
    final CategoryAxis axis = plot.getDomainAxis();
    axis.setCategoryLabelPositions(
        CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
        );

    final CategoryItemRenderer renderer = plot.getRenderer();
    //renderer.setItemLabelsVisible(true);
    final BarRenderer r = (BarRenderer) renderer;
    r.setMaximumBarWidth(0.5);

    return chart;

  }




    public void setYflx(String yflx) {
        this.yflx = yflx;
    }

    public void setDlhj(String dlhj) {
        this.dlhj = dlhj;
    }

    public void setFyhj(String fyhj) {
        this.fyhj = fyhj;
    }

    public void setJzmc(String jzmc) {
        this.jzmc = jzmc;
    }

    public String getYflx() {
        return yflx;
    }

    public String getDlhj() {
        return dlhj;
    }

    public String getFyhj() {
        return fyhj;
    }

    public String getJzmc() {
        return jzmc;
    }

}
