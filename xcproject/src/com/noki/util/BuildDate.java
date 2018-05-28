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
	 * 载入配置文件
	 */
	public static void load(String fileName) {
		try {
			properties = Sys.loadPropertiesResource(fileName);
		} catch (Throwable t) {
		}
	}
	
	//读取配置文件key
	public static Set getKey(){
		Set set =  properties.keySet();
		return set;
	}
	
	static Map beanMap = new HashMap();
	public static Map buidDate(List dateList,String fileName) throws IllegalArgumentException, SecurityException,
	InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException{
			load(fileName);//载入配置文件
		    Set proSet = getKey();//得到配置文件键SET
		    Iterator itPro = proSet.iterator();
		    while(itPro.hasNext()){
		    	String fielName="";
				try {
					fielName = new String(itPro.next().toString().getBytes("ISO-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}//属性名称
				Map yearMap = new HashMap();//每个属性初始化一个yearMap
				String stringLetter = fielName.substring(0, 1).toUpperCase();//将第一个字母大写
				String getName = "get" + stringLetter + fielName.substring(1);
				for(int j=0;j<dateList.size();j++){
					Object dateBean = dateList.get(j);//得到list 中的bean
					Class classType = dateBean.getClass();//反射得到bean类
					Method getYearMethod = classType.getMethod("getYear", new Class[] {});//得到年的方法
					String year =  (String)getYearMethod.invoke(dateBean, new Object[] {});//得到bean的年
					Method getMethod = classType.getMethod(getName, new Class[] {});//得到属性的get方法
					Object value =  getMethod.invoke(dateBean, new Object[] {});//执行get方法得到数据
					yearMap.put(year,value);
				}
				beanMap.put(fielName,yearMap);
			}
			return beanMap;
	}

	/**
	 * 对外开放方法,得到表格数据
	 * @param fileName(配置文件名称)
	 * @param dateList(数据List)
	 * @param year(查询年份)
	 * @param province(查询省份)
	 * @return
	 * @throws Exception
	 */
	public static String toRetTable(String fileName,List dateList,String [] year) throws Exception{
		Map beanMap = buidDate(dateList,fileName);
		StringBuffer sb = new StringBuffer();
		sb.append("<table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de' id='bean'>");
		Set beanSet = beanMap.keySet();
		Iterator it = beanSet.iterator();
		//表头
		sb.append("<tr  height = '20'><td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>省份</div></td><td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>指标</div></td><td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>单位</div></td>");
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
			Object dateBean = dateList.get(0);//得到list 中的bean
			Class classType = dateBean.getClass();//反射得到bean类
			Method getYearMethod = classType.getMethod("getProvince", new Class[] {});//得省的方法
			String pro =  (String)getYearMethod.invoke(dateBean, new Object[] {});//得到bean的省
			sb.append("<td><div align='center'>"+pro+"</div></td>");
			sb.append("<td><div align='center'>"+ziyuan+"</div></td>");
            sb.append("<td><div align='center'>度</div></td>");
           
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
	 * 返回图形数据
	 * @param dataList(数据list)
	 * @param fieldName(能源名称)
	 * @return
	 */
	public static JFreeChart toRetChart(List dataList,String fieldName){
		Map dataMap=null;//能源map
		try{
			 dataMap = buidDate(dataList,fieldName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//时间曲线数据集合
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
						defaultcategorydataset.addValue(Double.parseDouble(yearValue), new String(properties.get(key).toString().getBytes("ISO-8859-1"),"UTF-8")+"能源",yearKey+"年");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
		}
		 JFreeChart chart = ChartFactory.createLineChart("统计时间线", "年", "耗能", defaultcategorydataset,PlotOrientation.VERTICAL,true, false, false);
		 
		 //设置图形样式
		 CategoryPlot plot=chart.getCategoryPlot(); 
         plot.setBackgroundPaint(Color.WHITE);
         plot.setRangeGridlinePaint(Color.BLUE);//纵坐标格线颜色
         plot.setDomainGridlinePaint(Color.BLACK);//横坐标格线颜色
         plot.setDomainGridlinesVisible(true);//显示横坐标格线
         plot.setRangeGridlinesVisible(true);//显示纵坐标格线
         
                     
         LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
         DecimalFormat decimalformat1 = new DecimalFormat("##.##");//数据点显示数据值的格式
         renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
                                           //上面这句是设置数据项标签的生成器
         renderer.setItemLabelsVisible(true);//设置项标签显示
         renderer.setBaseItemLabelsVisible(true);//基本项标签显示
                  //上面这几句就决定了数据点按照设定的格式显示数据值  
         renderer.setShapesFilled(Boolean.TRUE);//在数据点显示实心的小图标
         renderer.setShapesVisible(true);//设置显示小图标


		//设置子标题
		// TextTitle subtitle = new TextTitle("各年耗能量对比", new Font("黑体", Font.BOLD, 12));
		 //chart.addSubtitle(subtitle);
		 //设置主标题
		 chart.setTitle(new TextTitle("耗能分析", new Font("隶书", Font.ITALIC, 15)));
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
			toRetTable("elec.properties",arr,year,"山东");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}*/
}
