package com.ptac.app.statisticcollect.city.unitpricePIC.tool;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsx3.net.Request;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class ChartUtil {
	public CategoryDataset getBarData(double[][] data, String[] rowKeys,
			String[] columnKeys) {
		return DatasetUtilities
				.createCategoryDataset(rowKeys, columnKeys, data);

	}

	public JFreeChart createBarChart(CategoryDataset dataset, String xName,
			String yName, String chartTitle,HttpServletResponse response) {
		JFreeChart chart = ChartFactory.createBarChart(chartTitle, // ͼ�����
				xName, // Ŀ¼�����ʾ��ǩ
				yName, // ��ֵ�����ʾ��ǩ
				dataset, // ���ݼ�
				PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
				true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)
				false, // �Ƿ����ɹ���
				false // �Ƿ�����URL����
				);
		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
		/*
		 * VALUE_TEXT_ANTIALIAS_OFF��ʾ�����ֵĿ���ݹر�,
		 * ʹ�õĹرտ���ݺ����御��ѡ��12��14�ŵ�������,���������������ÿ�
		 */
		chart.setTextAntiAlias(false);
		chart.setBackgroundPaint(Color.white);
		// create plot
		CategoryPlot plot = chart.getCategoryPlot();
		// ���ú����߿ɼ�
		plot.setRangeGridlinesVisible(true);
		// ����ɫ��
		plot.setRangeGridlinePaint(Color.gray);

		// �����ᾫ��
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		// vn.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0.00");
		vn.setNumberFormatOverride(df); // ���������ݱ�ǩ����ʾ��ʽ
		// x������
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(labelFont);// �����
		domainAxis.setTickLabelFont(labelFont);// ����ֵ

		// Lable��Math.PI/3.0������б
		// domainAxis.setCategoryLabelPositions(CategoryLabelPositions
		// .createUpRotationLabelPositions(Math.PI / 3.0));

		domainAxis.setMaximumCategoryLabelWidthRatio(0.6f);// �����ϵ� Lable �Ƿ�������ʾ

		// ���þ���ͼƬ��˾���
		domainAxis.setLowerMargin(0.1);
		// ���þ���ͼƬ�Ҷ˾���
		domainAxis.setUpperMargin(0.1);
		// ���� columnKey �Ƿ�����ʾ
		// domainAxis.setSkipCategoryLabelsToFit(true);

		plot.setDomainAxis(domainAxis);
		// ������ͼ����ɫ��ע�⣬ϵͳȡɫ��ʱ��Ҫʹ��16λ��ģʽ���鿴��ɫ���룬�����Ƚ�׼ȷ��
		plot.setBackgroundPaint(new Color(255, 255, 204));

		// y������
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(labelFont);
		rangeAxis.setTickLabelFont(labelFont);
		// ������ߵ�һ�� Item ��ͼƬ���˵ľ���
		rangeAxis.setUpperMargin(0.15);
		// ������͵�һ�� Item ��ͼƬ�׶˵ľ���
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);

		BarRenderer renderer = new BarRenderer();
		// �������ӿ��
		renderer.setMaximumBarWidth(0.05);
		// �������Ӹ߶�
		renderer.setMinimumBarLength(0.2);
		// �������ӱ߿���ɫ
		renderer.setBaseOutlinePaint(Color.BLACK);
		// �������ӱ߿�ɼ�
		renderer.setDrawBarOutline(true);

		// // ����������ɫ
		renderer.setSeriesPaint(0, new Color(204, 255, 255));
		renderer.setSeriesPaint(1, new Color(153, 204, 255));
		renderer.setSeriesPaint(2, new Color(51, 204, 204));

		// ����ÿ��������������ƽ������֮�����
		renderer.setItemMargin(0.0);

		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
		renderer.setIncludeBaseInRange(true);
		renderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);

		plot.setRenderer(renderer);
		// ��������͸����
		plot.setForegroundAlpha(1.0f);

		try {

			OutputStream out = response.getOutputStream();
			response.setContentType("image/png");
//			ChartUtilities.writeChartAsPNG(out, chart, 1200, 600);
			ChartUtilities.writeChartAsPNG(out, chart, 1200, 700, true, 50);
			out.close();//����д��---���������˹���
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chart;
	}
}
