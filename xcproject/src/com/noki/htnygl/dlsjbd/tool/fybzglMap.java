package com.noki.htnygl.dlsjbd.tool;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;


/**
 * @author GT
 * 
 * */
public class fybzglMap {

	private static final String CHART_PATH = "E:/Apache_tomcat/tomcat2/webapps/energy/web/Map/";


	public PieDataset getPieDataSet(String[] keys, double[] data) {

		if (keys != null && data != null) {
			if (keys.length == data.length) {
				DefaultPieDataset dataset = new DefaultPieDataset();
				for (int i = 0; i < keys.length; i++) {
					dataset.setValue(keys[i], data[i]);
				}
				return dataset;
			}

		}
		return null;
	}

	public String createValidityComparePimChar(PieDataset dataset,
			String chartTitle, String charName, String[] pieKeys) {
		// ���ù�����������3D��ͼ
		JFreeChart chart = ChartFactory.createPieChart3D(chartTitle, dataset,
				true, true, false);

		// ʹ��˵����ǩ��������,ȥ���������
		// chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);��Ч��
		chart.setTextAntiAlias(false);
		// ͼƬ����ɫ
		chart.setBackgroundPaint(Color.white);

		// ����ͼ�����������������title(������Щ�汾Title���������)
		chart.getTitle().setFont((new Font("����", Font.CENTER_BASELINE, 20)));

		// ����ͼ��(Legend)�ϵ�����(//�ײ�������)
		chart.getLegend().setItemFont(new Font("����", Font.CENTER_BASELINE, 15));

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// ͼƬ����ʾ�ٷֱ�:Ĭ�Ϸ�ʽ

		// ָ����ͼ�����ߵ���ɫ
		plot.setBaseSectionOutlinePaint(Color.BLACK);
		plot.setBaseSectionPaint(Color.BLACK);

		// ����������ʱ����Ϣ
		plot.setNoDataMessage("�޶�Ӧ�����ݣ������²�ѯ��");

		// ����������ʱ����Ϣ��ʾ��ɫ
		plot.setNoDataMessagePaint(Color.red);

		// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0}({2})"));

		plot.setLabelFont(new Font("����", Font.TRUETYPE_FONT, 12));

		// ָ��ͼƬ��͸����(0.0-1.0)
		plot.setForegroundAlpha(0.65f);
		// ָ����ʾ�ı�ͼ��Բ��(false)����Բ��(true)
		plot.setCircular(false, true);

		// ���õ�һ������section �Ŀ�ʼλ�ã�Ĭ����12���ӷ���
		plot.setStartAngle(90);

		// ���÷ֱ���ɫ(�����������Լ�����)
		// plot.setSectionPaint(pieKeys[0], new Color(244, 194, 144));
		// plot.setSectionPaint(pieKeys[1], new Color(144, 233, 144));

		// �ѱ�ͼ����ͼƬ
		FileOutputStream fos_jpg = null;
		try {
			// �ļ��в������򴴽�
			isChartPathExist(CHART_PATH);
			String chartName = CHART_PATH + charName;
			fos_jpg = new FileOutputStream(chartName);
			// �߿������Ӱ����Բ��ͼ����״
			ChartUtilities.writeChartAsPNG(fos_jpg, chart, 700, 400);

			return chartName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				fos_jpg.close();
				System.out.println("create pie-chart.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void isChartPathExist(String chartPath) {
		File file = new File(chartPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

}
