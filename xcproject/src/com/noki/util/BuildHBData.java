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
 * 能源消耗业务处理类
 * @author xuwangyu
 *
 */
public class BuildHBData {
	
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
	
	/**
	 * 得到页面要展示的表格和曲线图形数据放入Map中
	 * @param city(城市名称)
	 * @param fieldName(所要查询的能源名称)
	 * @return Map（能源消耗放入map）
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
			Map energyMap = new TreeMap();//存放能源的map 
		    for(int n=0;n<fieldNames.length;n++){
		     Map yearMap = new TreeMap();
		     String fieldName = fieldNames[n];
			 for(int i=0;i<dataList.size();i++){
			    Object dateBean = dataList.get(i);//得到list 中的bean
				Class classType = dateBean.getClass();//反射得到bean类
				Method getYearMethod = classType.getMethod("getCon_yearmonth", new Class[] {});//得到年月的方法
				String yearMonth =  (String)getYearMethod.invoke(dateBean, new Object[] {});//得到bean的年月
				String year = yearMonth.substring(0,4);//得到年
				String month = yearMonth.substring(4);//得到月
				String stringLetterf = fieldNames[n].substring(0, 1).toUpperCase();//将属性第一个字母大写
				//如果页面求总能源数,否则反射得到需要的能源
				if(isHas(selectyear,year)){	
				if(fieldName.equals("sum")){
					Set proSet = getKey();//得到配置文件键SET
				    Iterator itPro = proSet.iterator();
				    value=0l;
				    while(itPro.hasNext()){
				    	String fielName="";
						try {
							fielName = new String(itPro.next().toString().getBytes("ISO-8859-1"),"UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}//属性名称
						if(!"sum".equals(fielName)){
							String stringLetter = fielName.substring(0, 1).toUpperCase();//将第一个字母大写
							String getName = "get" + stringLetter + fielName.substring(1);
							Method getMethod = classType.getMethod(getName, new Class[] {});//得到属性的get方法
							Object values =  getMethod.invoke(dateBean, new Object[] {});//执行get方法得到数据
							value = value+ Long.parseLong(values.toString());
						}
				    }
				}else{
					value = 0l;
					//Class classType = bean.getClass();
					// 获得相应属性的get方法名称
					String getName = "get" + stringLetterf + fieldNames[n].substring(1);
					Method getMethod = classType.getMethod(getName, new Class[] {});//得到动态执行get方法
					value = Long.parseLong(getMethod.invoke(dateBean, new Object[] {}).toString());
				}
				//如果Map中包含此年此月放入月的map中
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
	 * 构造返回前台的表格
	 * @param yearMap(能源map)
	 * @return String（返回前台表格数据）
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
		
	
		//初始化表头
		sb.append("<tr height = '20'>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("地区</div>");
		sb.append("</td>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("月份</div>");
		sb.append("</td>");
		String energy ="";
		
		for(int i=0;i<fieldName.length;i++){
		  Map yearMap = (TreeMap)energyMap.get(fieldName[i]);//年map
		  Set yearSet = yearMap.keySet();
		  Iterator yearit = yearSet.iterator();
		  while(yearit.hasNext()){
			String key = yearit.next().toString();
			sb.append("<td width='10%' height='23' bgcolor='C8E1FB'><div align='center'>");
			String fi = properties.get(fieldName[i]).toString();
			try {
				System.out.println(fieldName[i]);
				sb.append(key+"年"+new String((properties.get(fieldName[i]).toString()).getBytes("ISO-8859-1"),"UTF-8")+"(吨标煤)</div>");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            sb.append("</td>");		
		}
	 }
		  
		sb.append("</tr>");
		//取12个月份的值
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
			Class classType = bean.getClass();//反射得到bean类
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
				}//执行get方法得到数据
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}//得到年月的方法
			sb.append("<td><div align='center'>"+values+"</div></td>");
			sb.append("<td><div align='center'>"+m+"</div></td>");
			for(int k=0;k<fieldName.length;k++){
				String fieldNamey = fieldName[k];
				Map yearMap = (TreeMap)energyMap.get(fieldNamey);//得到年Map
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
	//	System.out.println("构表所有时间:"+(endTime-startTime));
		return sb.toString();
	}
	
	/**
	 * 返回图形数据
	 * @param city(城市)
	 * @param fieldName(能源名称)
	 * @return
	 */
	public static JFreeChart toRetChar(List DataList,String [] fieldName,String fileName,String [] year,String [] month){
		Map energyMap=null;//能源map
		try{
			energyMap = toGetDate(DataList,fieldName,year);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//时间曲线数据集合
	//	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

		Map yearMap = (TreeMap)energyMap.get(fieldName[0]);//得到年map
		Set keySet = yearMap.keySet();
	    Iterator it = keySet.iterator();
		while(it.hasNext()){
			String key =it.next().toString();
			if(isHas(year,key)){
			//	TimeSeries timeSeries = new TimeSeries(key+"-度统计", Month.class);
				Map monthMapv = (Map)yearMap.get(key);
				for(int i=1;i<13;i++){
					if(isHas(month,i+"")){
						String monKey = i<10?"0"+i:i+""; 
						String monValue = monthMapv.containsKey(monKey)==false?"0":monthMapv.get(monKey).toString();
					//	timeSeries.add(new Month(i,2009),Double.parseDouble(monValue));
						 defaultcategorydataset.addValue(Double.parseDouble(monValue),key+"年",monKey+"月");
					}
				}
		//	lineDataset.addSeries(timeSeries);
		  }
	   }
		 JFreeChart chart = ChartFactory.createLineChart("统计时间线", "月份", "耗能", defaultcategorydataset,PlotOrientation.VERTICAL,true, false, false);
		 
/*//	    //中文乱码问题解决
//	 	StandardChartTheme theme = new StandardChartTheme("unicode") {   
//		    public void apply(JFreeChart chart) {   
//		        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,   
//		                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);   
//		        super.apply(chart);   
//		    }   
//		};   
		theme.setExtraLargeFont(new Font("宋体", Font.PLAIN, 20));   
		theme.setLargeFont(new Font("宋体", Font.PLAIN, 14));   
		theme.setRegularFont(new Font("宋体", Font.PLAIN, 12));   
		theme.setSmallFont(new Font("宋体", Font.PLAIN, 10));   
		ChartFactory.setChartTheme(theme); */ 
		 
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
		 chart.setTitle(new TextTitle("耗能同比分析", new Font("隶书", Font.ITALIC, 15)));
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
