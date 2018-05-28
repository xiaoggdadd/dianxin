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
 * 能源消耗业务处理类
 * @author xuwangyu
 *
 */
public class ShenEnergyService {
	
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
	
		Map energyMap = new TreeMap();//存放能源的map 	
	    for(int n=0;n<fieldNames.length;n++){
	    	List list = energydao.toGetDate(are); 
			Map yearMap = new TreeMap();//存放年信息的map
			Map monthMap=null;
			String value="";
	    	String fieldName = fieldNames[n];
			for(int i=0;i<list.size();i++){
				ShenEnergy energys = (ShenEnergy)list.get(i);//能源bean
				String yearMonth = energys.getCon_yearmonth();//年月字段
				String year = yearMonth.substring(0,4);//得到年
				String citya = energys.getCon_city();//得到城市
				String month = yearMonth.substring(4);//得到月
				String stringLetter = fieldName.substring(0, 1).toUpperCase();//将第一个字母大写
				//如果页面求总能源数,否则反射得到需要的能源
				if(isHas(selectyear,year)){		
					if(fieldName.equals("sum")){
						long diese = Long.parseLong(energys.getCon_diese());
						long gaoline = Long.parseLong(energys.getCon_gaoline());
						long naturalgas=Long.parseLong(energys.getCon_naturalgas());
						long power=Long.parseLong(energys.getCon_power());
						long water=Long.parseLong(energys.getCon_water());
						value = String.valueOf(diese+gaoline+naturalgas+power+water);
					}else{
						Class classType = ShenEnergy.class;//反射得到energy类
						// 获得相应属性的get方法名称
						String getName = "get" + stringLetter + fieldName.substring(1);
						Method getMethod = classType.getMethod(getName, new Class[] {});//得到动态执行get方法
						value = (String) getMethod.invoke(energys, new Object[] {});
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
				energyMap.put(fieldName,yearMap);
			}
	    }
		return energyMap;
	}
	
	/**
	 * 构造返回前台的表格
	 * @param yearMap(能源map)
	 * @return String（返回前台表格数据）
	 */
	public String toRetTable(String city, String [] fieldNames,String [] year,String [] month){
		Map energyMap=null;
		try{
			 energyMap = toGetDate(city, fieldNames,year);//能源map
		}catch(Exception ex){
			ex.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		String are="";
		sb.append("<table width='100%' border='0' cellspacing='1' cellpadding='1' bgcolor='#cbd5de' id='energy'>");
		Set keySet = energyMap.keySet();
		Iterator it = keySet.iterator();
		//初始化表头
		sb.append("<tr height = '20'>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("地区</div>");
		sb.append("</td>");
		sb.append("<td width='5%' height='23' bgcolor='C8E1FB'><div align='center'>");
		sb.append("月份</div>");
		sb.append("</td>");
		String energy ="";
		//遍历能源
		for(int i=0;i<fieldNames.length;i++){
			String fieldName = fieldNames[i];//能源名称
			//遍历能源map
		//	while(it.hasNext()){
		//		String key = it.next().toString();//能源键
				Map yearMap = (TreeMap)energyMap.get(fieldName);//年map
				Set yearSet = yearMap.keySet();
				Iterator yearit = yearSet.iterator();
		//		String key = yearit.next().toString();
				//遍历年map
				while(yearit.hasNext()){
					sb.append("<td width='10%' height='23' bgcolor='C8E1FB'><div align='center'>");
					if(fieldName.equals("con_water")){
						energy="水消耗量";
					}else if(fieldName.equals("con_power")){
						energy="耗电量";
					}else if(fieldName.equals("con_gaoline")){
						energy="汽油消耗量";
					}else if(fieldName.equals("con_diese")){
						energy="柴油消耗量";
					}else if(fieldName.equals("con_naturalgas")){
						energy="天然汽消耗量";
					}else{
						energy="总耗能";
					}
					String key = yearit.next().toString();
					sb.append(key+"年"+energy+"(吨标煤)</div>");
		            sb.append("</td>");	
				}
	//		}
		}
		sb.append("</tr>");
		//取12个月份的值
		String bgcor="";
		//遍历所有能源
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
					Map yearMaps = (TreeMap)energyMap.get(fieldName);//得到年Map
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
	 * 返回图形数据
	 * @param city(城市)
	 * @param fieldName(能源名称)
	 * @return
	 */
	public JFreeChart toRetChart(String city, String [] fieldNames,String [] year,String [] month){
		Map energyMap=null;//能源map
		try{
			 energyMap = toGetDate(city,fieldNames,year);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//时间曲线数据集合
	//	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

		Map yearMap = (TreeMap)energyMap.get(fieldNames[0]);//得到年map
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
	
	public boolean isHas(String [] arr,String value){
		for(int i=0;i<arr.length;i++){
			if(value.equals(arr[i])){
				return true;
			}
		}
		return false;
	}
	
}
