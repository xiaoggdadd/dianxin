package com.noki.energy.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.noki.energy.dao.EnergyDAO;
import com.noki.energy.javabean.DisplayBean;
import com.noki.energy.javabean.Energy;

/**
 * ��Դ����ҵ������
 * @author xuwangyu
 *
 */
public class EnergyService {
	
	/**
	 * �õ�ҳ��Ҫչʾ�ı�������ͼ�����ݷ���Map��
	 * @param city(��������)
	 * @param fieldName(��Ҫ��ѯ����Դ����)
	 * @return Map����Դ���ķ���map��
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public  Map toGetDate(String city, String fieldName) throws IllegalArgumentException, SecurityException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
		EnergyDAO energydao = new EnergyDAO();
		List cityList = energydao.toGetCity();
		String are="";
		if(city.equals("red")){
			DisplayBean disbean = (DisplayBean)cityList.get(0);
			are = disbean.getArear();
		}else{
			are=city;
		}
			List list = energydao.toGetDate(are); 
			Map yearMap = new TreeMap();
			Map monthMap=null;
			String value="";
			for(int i=0;i<list.size();i++){
				Energy energys = (Energy)list.get(i);//��Դbean
				String yearMonth = energys.getCon_yearmonth();//�����ֶ�
				String year = yearMonth.substring(0,4);//�õ���
				String citya = energys.getCon_city();//�õ�����
				String month = yearMonth.substring(4);//�õ���
				String stringLetter = fieldName.substring(0, 1).toUpperCase();//����һ����ĸ��д
				//���ҳ��������Դ��,������õ���Ҫ����Դ
				if(fieldName.equals("sum")){
					long diese = Long.parseLong(energys.getCon_diese());
					long gaoline = Long.parseLong(energys.getCon_gaoline());
					long naturalgas=Long.parseLong(energys.getCon_naturalgas());
					long power=Long.parseLong(energys.getCon_power());
					long water=Long.parseLong(energys.getCon_water());
					value = String.valueOf(diese+gaoline+naturalgas+power+water);
				}else{
					Class classType = Energy.class;//����õ�energy��
					// �����Ӧ���Ե�get��������
					String getName = "get" + stringLetter + fieldName.substring(1);
					Method getMethod = classType.getMethod(getName, new Class[] {});//�õ���ִ̬��get����
					value = (String) getMethod.invoke(energys, new Object[] {});
				}
				//���Map�а���������·����µ�map��
				if(yearMap.containsKey(year)){
					monthMap = (Map)yearMap.get(year);
					monthMap.put(month,value);
				}else {
					monthMap = new TreeMap();
					monthMap.put(month,value);
					yearMap.put(year,monthMap);
				}
			}
		return yearMap;
	}
	
	/**
	 * ���췵��ǰ̨�ı��
	 * @param yearMap(��Դmap)
	 * @return String������ǰ̨������ݣ�
	 */
	public String toRetTable(String city, String fieldName){
		long startTime = new Date().getTime();
		Map yearMap=null;
		try{
			 yearMap = toGetDate(city, fieldName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		String are="";
		sb.append("<table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de'>");
		Set keySet = yearMap.keySet();
		Iterator it = keySet.iterator();
		//��ʼ����ͷ
		sb.append("<tr height = '20'>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("����</div>");
		sb.append("</td>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("�·�</div>");
		sb.append("</td>");
		String energy ="";
		while(it.hasNext()){
			String key = it.next().toString();
			sb.append("<td width='10%' height='23' bgcolor='C8E1FB'><div align='center'>");
			if(fieldName.equals("con_water")){
				energy="ˮ������";
			}else if(fieldName.equals("con_power")){
				energy="�ĵ���";
			}else if(fieldName.equals("con_gaoline")){
				energy="����������";
			}else if(fieldName.equals("con_diese")){
				energy="����������";
			}else if(fieldName.equals("con_naturalgas")){
				energy="��Ȼ��������";
			}else{
				energy="�ܺ���";
			}
			sb.append(key+"��"+energy+"(�ֱ�ú)</div>");
            sb.append("</td>");		
		}
		sb.append("</tr>");
		//ȡ12���·ݵ�ֵ
		String bgcor="";
		for(int m=1;m<13;m++){
			if(m%2==0){
				bgcor = "F2F9FF";
			}else{
				bgcor="#FFFFFF";
			}
			sb.append("<tr bgcolor="+bgcor+">");
			sb.append("<td><div align='center'>"+city+"</div></td>");
			sb.append("<td><div align='center'>"+m+"</div></td>");
			Iterator itContent = keySet.iterator();
			while(itContent.hasNext()){
					String key = itContent.next().toString();
					Map monthMapv = (Map)yearMap.get(key);
					String monKey = m<10?"0"+m:m+""; 
					String monValue = monthMapv.containsKey(monKey)==false?"0":monthMapv.get(monKey).toString();
					sb.append("<td><div align='center'>"+monValue+"</div></td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		long endTime = new Date().getTime();
	//	System.out.println("��������ʱ��:"+(endTime-startTime));
		return sb.toString();
	}
	
	/**
	 * ����ͼ������
	 * @param city(����)
	 * @param fieldName(��Դ����)
	 * @return
	 */
	public JFreeChart toRetChart(String city, String fieldName){
		long startTime = new Date().getTime();
		Map yearMap=null;
		try{
			 yearMap = toGetDate(city, fieldName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//ʱ���������ݼ���
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		Set keySet = yearMap.keySet();
	    Iterator it = keySet.iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			TimeSeries timeSeries = new TimeSeries(key+"-��ͳ��", Month.class);
			Map monthMapv = (Map)yearMap.get(key);
			for(int i=1;i<13;i++){
				String monKey = i<10?"0"+i:i+""; 
				String monValue = monthMapv.containsKey(monKey)==false?"0":monthMapv.get(monKey).toString();
				timeSeries.add(new Month(i,2009),Double.parseDouble(monValue));
			}
			lineDataset.addSeries(timeSeries);
		}
		 JFreeChart chart = ChartFactory.createTimeSeriesChart("ͳ��ʱ����", "�·�", "����", lineDataset, true, true, true);
		 chart.setBackgroundPaint(Color.ORANGE);
//	    //��������������
//	 	StandardChartTheme theme = new StandardChartTheme("unicode") {   
//		    public void apply(JFreeChart chart) {   
//		        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
//		                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
//		        super.apply(chart);   
//		    }   
//		};   
//		theme.setExtraLargeFont(new Font("����", Font.PLAIN, 20));   
//		theme.setLargeFont(new Font("����", Font.PLAIN, 14));   
//		theme.setRegularFont(new Font("����", Font.PLAIN, 12));   
//		theme.setSmallFont(new Font("����", Font.PLAIN, 10));   
//		ChartFactory.setChartTheme(theme);  
		 
		 //����ͼ����ʽ
		 XYPlot plot = (XYPlot) chart.getPlot();
		 XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)plot.getRenderer();
		 //�������񱳾���ɫ
		 plot.setBackgroundPaint(Color.white);
		 //��������������ɫ
		 plot.setDomainGridlinePaint(Color.pink);
		 //�������������ɫ
		 plot.setRangeGridlinePaint(Color.pink);
		 //��������ͼ��xy��ľ���
		 plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 10D));
		 //���������Ƿ���ʾ���ݵ�
		 xylineandshaperenderer.setBaseShapesVisible(true);
		 //����������ʾ�����ݵ��ֵ
		 XYItemRenderer xyitem = plot.getRenderer();   
		 xyitem.setBaseItemLabelsVisible(true);   
		 xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		 xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		 xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 14));
		 plot.setRenderer(xyitem);
		//�����ӱ���
		// TextTitle subtitle = new TextTitle("����������Ա�", new Font("����", Font.BOLD, 12));
		 //chart.addSubtitle(subtitle);
		 //����������
//		 chart.setTitle(new TextTitle("����ͬ�ȷ���", new Font("����", Font.ITALIC, 15)));
//		 chart.setAntiAlias(true);
		 long endTime = new Date().getTime();
	//	 System.out.println("��ͼ����ʱ��:"+(endTime-startTime));
		return chart;
	}
}
