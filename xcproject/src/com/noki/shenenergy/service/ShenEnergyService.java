package com.noki.shenenergy.service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.noki.shenenergy.dao.ShenEnergyDAO;
import com.noki.shenenergy.javabean.ShenDisplayBean;
import com.noki.shenenergy.javabean.ShenEnergy;

/**
 * ��Դ����ҵ������
 * @author xuwangyu
 *
 */
public class ShenEnergyService {
	
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
	public  Map toGetDate(String city, String [] fieldNames,String [] selectyear) throws IllegalArgumentException, SecurityException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
		ShenEnergyDAO energydao = new ShenEnergyDAO();
		List cityList = energydao.toGetCity();
		String are="";
		if(city.equals("red")){
			ShenDisplayBean disbean = (ShenDisplayBean)cityList.get(0);
			are = disbean.getArear();
		}else{
			are=city;
		}
	
		Map energyMap = new TreeMap();//�����Դ��map 	
	    for(int n=0;n<fieldNames.length;n++){
	    	List list = energydao.toGetDate(are); 
			Map yearMap = new TreeMap();//�������Ϣ��map
			Map monthMap=null;
			String value="";
	    	String fieldName = fieldNames[n];
			for(int i=0;i<list.size();i++){
				ShenEnergy energys = (ShenEnergy)list.get(i);//��Դbean
				String yearMonth = energys.getCon_yearmonth();//�����ֶ�
				String year = yearMonth.substring(0,4);//�õ���
				String citya = energys.getCon_city();//�õ�����
				String month = yearMonth.substring(4);//�õ���
				String stringLetter = fieldName.substring(0, 1).toUpperCase();//����һ����ĸ��д
				//���ҳ��������Դ��,������õ���Ҫ����Դ
				if(isHas(selectyear,year)){		
					if(fieldName.equals("sum")){
						long diese = Long.parseLong(energys.getCon_diese());
						long gaoline = Long.parseLong(energys.getCon_gaoline());
						long naturalgas=Long.parseLong(energys.getCon_naturalgas());
						long power=Long.parseLong(energys.getCon_power());
						long water=Long.parseLong(energys.getCon_water());
						value = String.valueOf(diese+gaoline+naturalgas+power+water);
					}else{
						Class classType = ShenEnergy.class;//����õ�energy��
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
				energyMap.put(fieldName,yearMap);
			}
	    }
		return energyMap;
	}
	
	/**
	 * ���췵��ǰ̨�ı��
	 * @param yearMap(��Դmap)
	 * @return String������ǰ̨������ݣ�
	 */
	public String toRetTable(String city, String [] fieldNames,String [] year,String [] month){
		Map energyMap=null;
		try{
			 energyMap = toGetDate(city, fieldNames,year);//��Դmap
		}catch(Exception ex){
			ex.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		String are="";
		sb.append("<table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de' id='energy'>");
		Set keySet = energyMap.keySet();
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
		//������Դ
		for(int i=0;i<fieldNames.length;i++){
			String fieldName = fieldNames[i];//��Դ����
			//������Դmap
		//	while(it.hasNext()){
		//		String key = it.next().toString();//��Դ��
				Map yearMap = (TreeMap)energyMap.get(fieldName);//��map
				Set yearSet = yearMap.keySet();
				Iterator yearit = yearSet.iterator();
		//		String key = yearit.next().toString();
				//������map
				while(yearit.hasNext()){
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
					String key = yearit.next().toString();
					sb.append(key+"��"+energy+"(�ֱ�ú)</div>");
		            sb.append("</td>");	
				}
	//		}
		}
		sb.append("</tr>");
		//ȡ12���·ݵ�ֵ
		String bgcor="";
		//����������Դ
			for(int m=1;m<13;m++){
			  if(isHas(month,String.valueOf(m))){
				if(m%2==0){
					bgcor = "F2F9FF";
				}else{
					bgcor="#FFFFFF";
				}
				sb.append("<tr bgcolor="+bgcor+">");
				sb.append("<td><div align='center'>"+city+"</div></td>");
				sb.append("<td><div align='center'>"+m+"</div></td>");
				for(int k=0;k<fieldNames.length;k++){
					String fieldName = fieldNames[k];
					Map yearMaps = (TreeMap)energyMap.get(fieldName);//�õ���Map
					Set keyset = yearMaps.keySet();
					Iterator itContent = keyset.iterator();
					long monthTotal=0;
					while(itContent.hasNext()){
						String key = itContent.next().toString();
						Map monthMapv = (Map)yearMaps.get(key);
						String monKey = m<10?"0"+m:m+""; 
						String monValue = monthMapv.containsKey(monKey)==false?"0":monthMapv.get(monKey).toString();
						monthTotal += Long.parseLong(monValue); 
						sb.append("<td><div align='center'>"+monValue+"</div></td>");
					}
				}
				sb.append("</tr>");
			}
		}
			sb.append("</table>");
			return sb.toString();
	}
	
	/**
	 * ����ͼ������
	 * @param city(����)
	 * @param fieldName(��Դ����)
	 * @return
	 */
	public JFreeChart toRetChart(String city, String [] fieldNames,String [] year,String [] month){
		Map energyMap=null;//��Դmap
		try{
			 energyMap = toGetDate(city,fieldNames,year);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//ʱ���������ݼ���
	//	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

		Map yearMap = (TreeMap)energyMap.get(fieldNames[0]);//�õ���map
		Set keySet = yearMap.keySet();
	    Iterator it = keySet.iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			if(isHas(year,key)){
			//	TimeSeries timeSeries = new TimeSeries(key+"-��ͳ��", Month.class);
				Map monthMapv = (Map)yearMap.get(key);
				for(int i=1;i<13;i++){
					if(isHas(month,i+"")){
						String monKey = i<10?"0"+i:i+""; 
						String monValue = monthMapv.containsKey(monKey)==false?"0":monthMapv.get(monKey).toString();
					//	timeSeries.add(new Month(i,2009),Double.parseDouble(monValue));
						 defaultcategorydataset.addValue(Double.parseDouble(monValue),key+"��",monKey+"��");
					}
				}
		//	lineDataset.addSeries(timeSeries);
		  }
	   }
		 JFreeChart chart = ChartFactory.createLineChart("ͳ��ʱ����", "�·�", "����", defaultcategorydataset,PlotOrientation.VERTICAL,true, false, false);
		 
/*//	    //��������������
//	 	StandardChartTheme theme = new StandardChartTheme("unicode") {   
//		    public void apply(JFreeChart chart) {   
//		        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
//		                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
//		        super.apply(chart);   
//		    }   
//		};   
		theme.setExtraLargeFont(new Font("����", Font.PLAIN, 20));   
		theme.setLargeFont(new Font("����", Font.PLAIN, 14));   
		theme.setRegularFont(new Font("����", Font.PLAIN, 12));   
		theme.setSmallFont(new Font("����", Font.PLAIN, 10));   
		ChartFactory.setChartTheme(theme); */ 
		 
		 //����ͼ����ʽ
		 CategoryPlot plot=chart.getCategoryPlot(); 
         plot.setBackgroundPaint(Color.WHITE);
         plot.setRangeGridlinePaint(Color.BLUE);//�����������ɫ
         plot.setDomainGridlinePaint(Color.BLACK);//�����������ɫ
         plot.setDomainGridlinesVisible(true);//��ʾ���������
         plot.setRangeGridlinesVisible(true);//��ʾ���������
         
                     
         LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
         DecimalFormat decimalformat1 = new DecimalFormat("##.##");//���ݵ���ʾ����ֵ�ĸ�ʽ
         renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
                                           //��������������������ǩ��������
         renderer.setItemLabelsVisible(true);//�������ǩ��ʾ
         renderer.setBaseItemLabelsVisible(true);//�������ǩ��ʾ
                  //�����⼸��;��������ݵ㰴���趨�ĸ�ʽ��ʾ����ֵ  
         renderer.setShapesFilled(Boolean.TRUE);//�����ݵ���ʾʵ�ĵ�Сͼ��
         renderer.setShapesVisible(true);//������ʾСͼ��


		//�����ӱ���
		// TextTitle subtitle = new TextTitle("����������Ա�", new Font("����", Font.BOLD, 12));
		 //chart.addSubtitle(subtitle);
		 //����������
		 chart.setTitle(new TextTitle("����ͬ�ȷ���", new Font("����", Font.ITALIC, 15)));
		 chart.setAntiAlias(true);
		return chart;
	}
	
	public boolean isHas(String [] arr,String value){
		for(int i=0;i<arr.length;i++){
			if(value.equals(arr[i])){
				return true;
			}
		}
		return false;
	}
	
}
