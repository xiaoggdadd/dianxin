package com.noki.util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.noki.elecconsume.Elecconsume;

public class BuildDate {
	
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
	
	static Map beanMap = new HashMap();
	public static Map buidDate(List dateList,String fileName) throws IllegalArgumentException, SecurityException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
			load(fileName);//���������ļ�
		    Set proSet = getKey();//�õ������ļ���SET
		    Iterator itPro = proSet.iterator();
		    while(itPro.hasNext()){
		    	String fielName="";
				try {
					fielName = new String(itPro.next().toString().getBytes("ISO-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}//��������
				Map yearMap = new HashMap();//ÿ�����Գ�ʼ��һ��yearMap
				String stringLetter = fielName.substring(0, 1).toUpperCase();//����һ����ĸ��д
				String getName = "get" + stringLetter + fielName.substring(1);
				for(int j=0;j<dateList.size();j++){
					Object dateBean = dateList.get(j);//�õ�list �е�bean
					Class classType = dateBean.getClass();//����õ�bean��
					Method getYearMethod = classType.getMethod("getYear", new Class[] {});//�õ���ķ���
					String year =  (String)getYearMethod.invoke(dateBean, new Object[] {});//�õ�bean����
					Method getMethod = classType.getMethod(getName, new Class[] {});//�õ����Ե�get����
					Object value =  getMethod.invoke(dateBean, new Object[] {});//ִ��get�����õ�����
					yearMap.put(year,value);
				}
				beanMap.put(fielName,yearMap);
			}
			return beanMap;
	}

	/**
	 * ���⿪�ŷ���,�õ��������
	 * @param fileName(�����ļ�����)
	 * @param dateList(����List)
	 * @param year(��ѯ���)
	 * @param province(��ѯʡ��)
	 * @return
	 * @throws Exception
	 */
	public static String toRetTable(String fileName,List dateList,String [] year) throws Exception{
		Map beanMap = buidDate(dateList,fileName);
		StringBuffer sb = new StringBuffer();
		sb.append("<table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de' id='bean'>");
		Set beanSet = beanMap.keySet();
		Iterator it = beanSet.iterator();
		//��ͷ
		sb.append("<tr  height = '20'><td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>ʡ��</div></td><td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>ָ��</div></td><td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>��λ</div></td>");
		for(int i=0;i<year.length;i++){
			sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>"+year[i]+"</div></td>");
		}
		sb.append("</tr>");
		while(it.hasNext()){
			String key = (String)it.next();
			Map value = (Map)beanMap.get(key);
			Set yearSet = value.keySet();
			Iterator ity = yearSet.iterator();
			String bgcor = "#FFFFFF";
			sb.append("<tr bgcolor="+bgcor+">");
			String  ziyuan="";
		    try {
			     ziyuan = new String(properties.get(key).toString().getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Object dateBean = dateList.get(0);//�õ�list �е�bean
			Class classType = dateBean.getClass();//����õ�bean��
			Method getYearMethod = classType.getMethod("getProvince", new Class[] {});//��ʡ�ķ���
			String pro =  (String)getYearMethod.invoke(dateBean, new Object[] {});//�õ�bean��ʡ
			sb.append("<td><div align='center'>"+pro+"</div></td>");
			sb.append("<td><div align='center'>"+ziyuan+"</div></td>");
            sb.append("<td><div align='center'>��</div></td>");
           
    		for(int i=0;i<year.length;i++){
				//String v = ity.next().toString();
				String va = value.get(year[i])==null?"0":value.get(year[i]).toString();
				sb.append("<td><div align='center'>"+va+"</div></td>");
		    }
			sb.append("</tr>");
		}
		sb.append("</table>");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * ����ͼ������
	 * @param dataList(����list)
	 * @param fieldName(��Դ����)
	 * @return
	 */
	public static JFreeChart toRetChart(List dataList,String fieldName){
		Map dataMap=null;//��Դmap
		try{
			 dataMap = buidDate(dataList,fieldName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//ʱ���������ݼ���
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		Set keySet = dataMap.keySet();
	    Iterator it = keySet.iterator();
		while(it.hasNext()){
			String key =it.next().toString();
				Map yearMap = (Map)dataMap.get(key);
				Set yearKeySet = yearMap.keySet();
			    Iterator yearit = yearKeySet.iterator();
			    while(yearit.hasNext()){
			    	String yearKey = yearit.next().toString();
			    	String yearValue = yearMap.get(yearKey).toString();
					try {
						defaultcategorydataset.addValue(Double.parseDouble(yearValue), new String(properties.get(key).toString().getBytes("ISO-8859-1"),"UTF-8")+"��Դ",yearKey+"��");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
		}
		 JFreeChart chart = ChartFactory.createLineChart("ͳ��ʱ����", "��", "����", defaultcategorydataset,PlotOrientation.VERTICAL,true, false, false);
		 
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
		 chart.setTitle(new TextTitle("���ܷ���", new Font("����", Font.ITALIC, 15)));
		 chart.setAntiAlias(true);
		 return chart;
	}
	
	
	
/*	
	public static void main(String [] args){
		try {
			ArrayList arr = new ArrayList();
			elecconsume el = new elecconsume();
			el.setBasestage("2");
			el.setChannelhouse("3");
			el.setYear("2009");
			el.setId(1);
			arr.add(el);
			elecconsume e2 = new elecconsume();
			e2.setBasestage("3");
			e2.setYear("2010");
			arr.add(e2);
			//arr.add(new elecconsume(),arr);
			String [] year={"2009","2010"};
			toRetTable("elec.properties",arr,year,"ɽ��");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}*/
}
