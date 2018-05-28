package com.noki.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 * ��Դ����ҵ������
 * @author xuwangyu
 *
 */
public class BuildHBData {
	
	private static Properties properties;
	/**
	 * ���������ļ�
	 */
	public static void load(String fileName) {
		try {
			properties = Sys.loadPropertiesResource(fileName);
		} catch (Throwable t) {
		}
	}
	
	//��ȡ�����ļ�key
	public static Set getKey(){
		Set set =  properties.keySet();
		return set;
	}
	
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
	public  static Map toGetDate(List dataList, String [] fieldNames,String [] selectyear) throws IllegalArgumentException, SecurityException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
			Map monthMap=null;
			Long value=0l;
			Map energyMap = new TreeMap();//�����Դ��map 
		    for(int n=0;n<fieldNames.length;n++){
		     Map yearMap = new TreeMap();
		     String fieldName = fieldNames[n];
			 for(int i=0;i<dataList.size();i++){
			    Object dateBean = dataList.get(i);//�õ�list �е�bean
				Class classType = dateBean.getClass();//����õ�bean��
				Method getYearMethod = classType.getMethod("getCon_yearmonth", new Class[] {});//�õ����µķ���
				String yearMonth =  (String)getYearMethod.invoke(dateBean, new Object[] {});//�õ�bean������
				String year = yearMonth.substring(0,4);//�õ���
				String month = yearMonth.substring(4);//�õ���
				String stringLetterf = fieldNames[n].substring(0, 1).toUpperCase();//�����Ե�һ����ĸ��д
				//���ҳ��������Դ��,������õ���Ҫ����Դ
				if(isHas(selectyear,year)){	
				if(fieldName.equals("sum")){
					Set proSet = getKey();//�õ������ļ���SET
				    Iterator itPro = proSet.iterator();
				    value=0l;
				    while(itPro.hasNext()){
				    	String fielName="";
						try {
							fielName = new String(itPro.next().toString().getBytes("ISO-8859-1"),"UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}//��������
						if(!"sum".equals(fielName)){
							String stringLetter = fielName.substring(0, 1).toUpperCase();//����һ����ĸ��д
							String getName = "get" + stringLetter + fielName.substring(1);
							Method getMethod = classType.getMethod(getName, new Class[] {});//�õ����Ե�get����
							Object values =  getMethod.invoke(dateBean, new Object[] {});//ִ��get�����õ�����
							value = value+ Long.parseLong(values.toString());
						}
				    }
				}else{
					value = 0l;
					//Class classType = bean.getClass();
					// �����Ӧ���Ե�get��������
					String getName = "get" + stringLetterf + fieldNames[n].substring(1);
					Method getMethod = classType.getMethod(getName, new Class[] {});//�õ���ִ̬��get����
					value = Long.parseLong(getMethod.invoke(dateBean, new Object[] {}).toString());
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
			 }
			 energyMap.put(fieldName,yearMap);
		    }
		return energyMap;
	}
	
	/**
	 * ���췵��ǰ̨�ı��
	 * @param yearMap(��Դmap)
	 * @return String������ǰ̨������ݣ�
	 */
	public static String toRetTable(List DataList,String [] fieldName,String fileName,String [] year,String [] month){
		long startTime = new Date().getTime();
		Map energyMap=null;
		load(fileName);
		try{
			 energyMap = toGetDate(DataList,fieldName,year);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		String are="";
		sb.append("<table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de' id='energy'>");
		
	
		//��ʼ����ͷ
		sb.append("<tr height = '20'>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("����</div>");
		sb.append("</td>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("�·�</div>");
		sb.append("</td>");
		String energy ="";
		
		for(int i=0;i<fieldName.length;i++){
		  Map yearMap = (TreeMap)energyMap.get(fieldName[i]);//��map
		  Set yearSet = yearMap.keySet();
		  Iterator yearit = yearSet.iterator();
		  while(yearit.hasNext()){
			String key = yearit.next().toString();
			sb.append("<td width='10%' height='23' bgcolor='C8E1FB'><div align='center'>");
			String fi = properties.get(fieldName[i]).toString();
			try {
				System.out.println(fieldName[i]);
				sb.append(key+"��"+new String((properties.get(fieldName[i]).toString()).getBytes("ISO-8859-1"),"UTF-8")+"(�ֱ�ú)</div>");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            sb.append("</td>");		
		}
	 }
		  
		sb.append("</tr>");
		//ȡ12���·ݵ�ֵ
		String bgcor="";
		for(int m=1;m<13;m++){
		  if(isHas(month,String.valueOf(m))){
			if(m%2==0){
				bgcor = "F2F9FF";
			}else{
				bgcor="#FFFFFF";
			}
			sb.append("<tr bgcolor="+bgcor+">");
			Object bean = DataList.get(0);
			Class classType = bean.getClass();//����õ�bean��
			Object values=null;
			try {
				Method getProvinceMethod = classType.getMethod("getProvince", new Class[] {});
				try {
					 values =  getProvinceMethod.invoke(bean, new Object[] {});
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}//ִ��get�����õ�����
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}//�õ����µķ���
			sb.append("<td><div align='center'>"+values+"</div></td>");
			sb.append("<td><div align='center'>"+m+"</div></td>");
			for(int k=0;k<fieldName.length;k++){
				String fieldNamey = fieldName[k];
				Map yearMap = (TreeMap)energyMap.get(fieldNamey);//�õ���Map
				Set keyset = yearMap.keySet();
				Iterator itContent = keyset.iterator();
				while(itContent.hasNext()){
					String key = itContent.next().toString();
					Map monthMapv = (Map)yearMap.get(key);
					String monKey = m<10?"0"+m:m+""; 
					String monValue = monthMapv.containsKey(monKey)==false?"0":monthMapv.get(monKey).toString();
					sb.append("<td><div align='center'>"+monValue+"</div></td>");
				}
		}
			sb.append("</tr>");
		  }
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
	public static JFreeChart toRetChar(List DataList,String [] fieldName,String fileName,String [] year,String [] month){
		Map energyMap=null;//��Դmap
		try{
			energyMap = toGetDate(DataList,fieldName,year);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//ʱ���������ݼ���
	//	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

		Map yearMap = (TreeMap)energyMap.get(fieldName[0]);//�õ���map
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
	
	public static boolean isHas(String [] arr,String value){
		for(int i=0;i<arr.length;i++){
			if(value.equals(arr[i])){
				return true;
			}
		}
		return false;
	}
}
