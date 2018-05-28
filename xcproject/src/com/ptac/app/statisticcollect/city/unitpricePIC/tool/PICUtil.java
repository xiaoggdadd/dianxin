
package com.ptac.app.statisticcollect.city.unitpricePIC.tool;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 类名称: ChartUtil.java
 * 创建者: 曾小杰
 * 创建时间: 2011-6-2 下午02:31:12
 * 描述:  
 */

public class PICUtil {
	 public static String[][] getListCulomn(){
	    	String[][] dataList1=new String[10][2];
			dataList1[0][0] = "成都";
			dataList1[1][0] = "德阳";
			dataList1[2][0] = "达州";
			dataList1[3][0] = "甘孜";
			dataList1[4][0] = "遂宁";
			dataList1[5][0] = "广安";
			dataList1[6][0] = "南充";
			dataList1[7][0] = "阿坝";
			dataList1[8][0] = "自贡";
			dataList1[9][0] = "绵阳";
			
			dataList1[0][1] = "1005";
			dataList1[1][1] = "965";
			dataList1[2][1] = "887";
			dataList1[3][1] = "707";
			dataList1[4][1] = "690";
			dataList1[5][1] = "560";
			dataList1[6][1] = "510";
			dataList1[7][1] = "400";
			dataList1[8][1] = "356";
			dataList1[9][1] = "290";
			return dataList1;
	    	
	    }
	    public static String[][] getListLine(){
	    	String[][] dataList1=new String[10][2];
			dataList1[0][0] = "成都";
			dataList1[1][0] = "德阳";
			dataList1[2][0] = "达州";
			dataList1[3][0] = "甘孜";
			dataList1[4][0] = "遂宁";
			dataList1[5][0] = "广安";
			dataList1[6][0] = "南充";
			dataList1[7][0] = "阿坝";
			dataList1[8][0] = "自贡";
			dataList1[9][0] = "绵阳";
			
			dataList1[0][1] = "1005";
			dataList1[1][1] = "756";
			dataList1[2][1] = "887";
			dataList1[3][1] = "707";
			dataList1[4][1] = "466";
			dataList1[5][1] = "560";
			dataList1[6][1] = "789";
			dataList1[7][1] = "400";
			dataList1[8][1] = "965";
			dataList1[9][1] = "465";
			return dataList1;
	    	
	    }
	    public static String[][] getListMulCulomn(){
	    	String[][] dataList1=new String[6][4];
			dataList1[0][0] = "成都";
			dataList1[1][0] = "德阳";
			dataList1[2][0] = "达州";
			dataList1[3][0] = "甘孜";
			dataList1[4][0] = "遂宁";
			dataList1[5][0] = "广安";
			
			dataList1[0][1] = "233";
			dataList1[1][1] = "448";
			dataList1[2][1] = "321";
			dataList1[3][1] = "555";
			dataList1[4][1] = "234";
			dataList1[5][1] = "408";
			
			dataList1[0][2] = "755";
			dataList1[1][2] = "348";
			dataList1[2][2] = "543";
			dataList1[3][2] = "367";
			dataList1[4][2] = "533";
			dataList1[5][2] = "560";
			
			dataList1[0][3] = "656";
			dataList1[1][3] = "567";
			dataList1[2][3] = "900";
			dataList1[3][3] = "707";
			dataList1[4][3] = "899";
			dataList1[5][3] = "880";
			return dataList1;
	    	
	    }
	    
	    public static String[][] getCulomnandLine(){
	    	String[][] dataList1=new String[10][3];
	    	dataList1[0][0] = "成都";
			dataList1[1][0] = "德阳";
			dataList1[2][0] = "达州";
			dataList1[3][0] = "甘孜";
			dataList1[4][0] = "遂宁";
			dataList1[5][0] = "广安";
			dataList1[6][0] = "南充";
			dataList1[7][0] = "阿坝";
			dataList1[8][0] = "自贡";
			dataList1[9][0] = "绵阳";
			
			dataList1[0][1] = "877";
			dataList1[1][1] = "644";
			dataList1[2][1] = "655.3";
			dataList1[3][1] = "555";
			dataList1[4][1] = "455";
			dataList1[5][1] = "408";
			dataList1[6][1] = "422";
			dataList1[7][1] = "454";
			dataList1[8][1] = "344";
			dataList1[9][1] = "253";
			
			dataList1[0][2] = "755";
			dataList1[1][2] = "408";
			dataList1[2][2] = "600";
			dataList1[3][2] = "555";
			dataList1[4][2] = "333";
			dataList1[5][2] = "560";
			dataList1[6][2] = "345";
			dataList1[7][2] = "335";
			dataList1[8][2] = "221";
			dataList1[9][2] = "458.8";
			return dataList1;
	    	
	    }
	    //柱状2D或者3D
		public static String getChartCulomn2D(){
			String strXML="";
			String [][] strData=getListCulomn();
		    strXML = "<chart palette='1' canvasBgColor='fffffC' canvasBgAlpha='80'   rotateYAxisName='0'  imageSave='1' imageSaveURL='/FusionChartsDemo/FusionChartsSave.jsp'  caption='绩效指标地市排名' baseFontSize='12' borderThickness='1' baseFont='楷体' xAxisName='地市' outCnvBaseFont='Arial' outCnvBaseFontSize='12' outCnvBaseFontColor='333333' yAxisName='绩效数据' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
		    String strSet = "";
		    String parameter="";
		    for(int i=0;i<strData.length;i++){
		    	try {
					parameter=java.net.URLEncoder.encode(strData[i][0]+":"+strData[i][1], "utf-8");
			    	parameter = parameter.replace('%', '-');
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	strSet+="<set label='"+strData[i][0]+"' value='"+strData[i][1]+"' link='n-/chartDemo/turn.do?value="+parameter+" ' />";
		    }
		    strSet = "<set label='33' value='333.00' /> <set label='22' value='22.00' /> <set label='11' value='11.00' /> <set label='aa' value='100.00' />";
		    String stlye="<styles>"
		      +"<definition>"
	          +"<style name='MyXScaleAnim' type='ANIMATION' duration='1' start='0' param='_xscale'/>"
	         +"<style name='MyYScaleAnim' type='ANIMATION' duration='1' start='0' param='_yscale' /> "
	         +"<style name='myCaptionFont' type='font' font='Arial' size='16' color='666666' bold='1' underline='1'/> "
	    +"</definition>"
	+"<application>" +
			"<apply toObject='Canvas' styles='MyXScaleAnim,MyYScaleAnim' />"
	+"<apply toObject='caption' styles='myCaptionFont' />"
	     +" </application></styles>";
	 

		    strXML=strXML+strSet+stlye+"</chart>";
			return strXML;
		}
		//一条曲线
		public static String getChartLine(){
			String strXML="";
			String [][] strData=getListLine();
		    strXML = "<chart palette='2' caption='绩效指标地市排名' xAxisName='地市' yAxisName='绩效数据' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
		    String strSet = "";
		    String parameter="";
		    for(int i=0;i<strData.length;i++){
		    	try {
					parameter=java.net.URLEncoder.encode(strData[i][0]+":"+strData[i][1], "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parameter = parameter.replace('%', '-');
				strSet+="<set label='"+strData[i][0]+"' value='"+strData[i][1]+"' />";
		    	//strSet+="<set label='"+strData[i][0]+"' value='"+strData[i][1]+"' link='n-/chartDemo/turn.do?value="+parameter+"' />";
		    }
		    strXML=strXML+strSet+"</chart>";
		    System.out.println(strXML);
			return strXML;
		} 
		public static String getChartMulCulomn(){
			String strXML="";
			String categories="<categories>";
			String dataSetmothons="<dataset seriesName='一月'>";
			String dataSetsecond="<dataset seriesName='二月'>";
			String dataSetThree="<dataset seriesName='三月'>";
			String [][] strData=getListMulCulomn();
		    strXML = "<chart palette='2'  baseFontSize='12' caption='各个地市第一季度绩效对比' xAxisName='地市' yAxisName='绩效数据' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
		    String parameter="";
		    for(int i=0;i<strData.length;i++){
		    	parameter=strData[i][0]+"<br>一月绩效:"+strData[i][1]+"<br>二月绩效:"+strData[i][2]+"<br>三月绩效:"+strData[i][3];
		    	try {
					parameter=java.net.URLEncoder.encode(parameter, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parameter = parameter.replace('%', '-');
		    	categories=categories+"<category label='"+strData[i][0]+"'/>"; 
		    	dataSetmothons=dataSetmothons+"<set value='"+strData[i][1]+"' link='P-detailsPopUp,width=600,height=400,toolbar=no,    scrollbars=no,resizable=no-/chartDemo/turn.do?value="+parameter+" '/>";
		    	dataSetsecond=dataSetsecond+"<set value='"+strData[i][2]+"'/>";
		    	dataSetThree=dataSetThree+"<set value='"+strData[i][3]+"'/>";
		    }
		  //  String ling="<trendlines><line startValue='26000' color='91C728' displayValue='Target' showOnTop='1'/></trendlines>";
		    categories=categories+"</categories>";
		    dataSetmothons=dataSetmothons+"</dataset>";
		    dataSetsecond=dataSetsecond+"</dataset>";
		    dataSetThree=dataSetThree+"</dataset>";
		    
		 
		    strXML=strXML+categories +dataSetmothons+ dataSetsecond+ dataSetThree+"</chart>";
	 
			return strXML; 
		}

		public static String getChartCulomAndLine(){
			String strXML="";
			String categories="<categories>";
			String dataSetmothons="<dataset seriesName='一月'>";
			String dataSetsecond="<dataset seriesName='二月' renderAs='Line'>";
			String [][] strData=getCulomnandLine();
		    strXML = "<chart palette='2' caption='各个地市第一季度绩效对比' xAxisName='地市' yAxisName='绩效数据' showValues='0' decimals='0' formatNumberScale='0' useRoundEdges='1'>";
		    for(int i=0;i<strData.length;i++){
		    	 
		    	categories=categories+"<category label='"+strData[i][0]+"'/>"; 
		    	dataSetmothons=dataSetmothons+"<set value='"+strData[i][1]+"' link='n-/chartDemo/turn.do?value="+strData[i][0]+" '/>";
		    	dataSetsecond=dataSetsecond+"<set value='"+strData[i][2]+"' link='n-/chartDemo/turn.do?value="+strData[i][1]+" '/>";
		    }
		    String ling="<trendlines><line startValue='26000' color='91C728' displayValue='Target' showOnTop='1'/></trendlines>";
		    categories=categories+"</categories>";
		    dataSetmothons=dataSetmothons+"</dataset>";
		    dataSetsecond=dataSetsecond+"</dataset>";
		    strXML=strXML+categories +dataSetmothons+ dataSetsecond+ling+"</chart>";
			return strXML; 
		}

		public static String getAngle(){
			String strXML=" <chart bgAlpha='0' bgColor='FFFFFF' lowerLimit='0' upperLimit='100' numberSuffix='%25' showBorder='0' basefontColor='FFFFDD' chartTopMargin='25' chartBottomMargin='25' chartLeftMargin='25' chartRightMargin='25' toolTipBgColor='80A905' gaugeFillMix='{dark-10},FFFFFF,{dark-10}' gaugeFillRatio='3'>"
			 +"<colorRange>"
			 +"<color minValue='0' maxValue='45' code='FF654F' />"
	  +" <color minValue='45' maxValue='80' code='F6BD0F' /> "
	  +"<color minValue='80' maxValue='100' code='8BBA00' /> "
	  +" </colorRange>"
	  +" <dials>"
	 +"<dial value='92' rearExtension='10' /> "
	  +" </dials>"
	  +" <trendpoints>"
	 +"<point value='50' displayValue='Average' fontcolor='FF4400' useMarker='1' dashed='1' dashLen='2' dashGap='2' valueInside='1' />" 
	  +"</trendpoints>"
	  +"<annotations>"
	 +"<annotationGroup id='Grp1' showBelow='1'>"
	  +"<annotation type='rectangle' x='5' y='5' toX='345' toY='195' radius='10' color='009999,333333' showBorder='0' />"
	  +"</annotationGroup>"
	  +" </annotations>"
	  +"<styles>"
	 +"<definition>"
	 +"<style name='RectShadow' type='shadow' strength='3' />"
	  +"</definition>"
	  +"<application>"
	 +"<apply toObject='Grp1' styles='RectShadow' /> "
	  +" </application>"
	  +"</styles>"
	 +"</chart>";
			
			return strXML;
		}
		public static String getWigdate(){
			
			String strXML="  <chart bgColor='FFFFFF' gaugeStartAngle='225' gaugeEndAngle='-45' bgAlpha='100' lowerLimit='0' upperLimit='180' majorTMNumber='8' majorTMThickness='3' majorTMColor='FFFFFF' majorTMHeight='7' minorTMNumber='0' placeValuesInside='1' sgaugeOriginY='160' gaugeOuterRadius='110' gaugeInnerRadius='100' showShadow='0' pivotRadius='20' pivotFillColor='000000,383836' pivotFillType='linear' pivotFillRatio='50,50' pivotFillAngle='240' annRenderDelay='0' gaugeFillMix='' showPivotBorder='1' pivotBorderColor='999999' pivotBorderThickness='2'>"
	 +"<dials>"
	 +" <dial value='50' color='FFFFFF,999999' alpha='100' showBorder='0' baseWidth='3' topWidth='3' radius='100' /> "
	 +" </dials>"
	 +" <annotations autoScale='0'> <annotationGroup id='Grp1' showBelow='1'>"
	 +"  <annotation type='circle' x='150' y='150' color='1C1C1C,AAAAAA,1C1C1C' radius='127' fillPattern='linear' /> "
	 +" <annotation type='circle' x='150' y='150' color='9E9E9E,ECECEC' radius='117' fillPattern='linear' fillAngle='270' /> "
	 +"  <annotation type='circle' x='150' y='150' color='000000,6C6C6C' radius='115' fillPattern='linear' fillAngle='270' />" 
	 +" </annotationGroup>"
	 +" </annotations>"
	 +"<styles>"
	 +"<definition>"
	 +" <style name='TTipFont' type='font' color='FFFFFF' bgColor='333333' borderColor='333333' font='Verdana' size='10' />" 
	 +" <style name='ValueFont' type='font' size='12' color='FFFFFF' bold='1' /> "
	 +" <style name='LimitFont' type='font' size='12' color='70E300' bold='1' /> "
	 +"  </definition>"
	 +"<application>"
	 +"  <apply toObject='TOOLTIP' styles='TTipFont' /> "
	 +"  <apply toObject='TICKVALUES' styles='ValueFont' /> "
	 +"  <apply toObject='LIMITVALUES' styles='LimitFont' /> "
	 +" </application>"
	 +" </styles>"
	 +" </chart>";
			
			return strXML;
		}
		
		public static String getLineGauge(){
			String xml="<chart lowerLimit='0' showShadow='1' gaugeRoundRadius='10' showValue='1' valueBelowPointer='0' upperLimit='9' lowerLimitDisplay='营销案创建' upperLimitDisplay='完成归档' palette='3' numberSuffix='%' chartRightMargin='20'>"
				+" <colorRange>"
			      +" <color minValue='1' maxValue='2' code='F6BD0F' label='未提交状态' />"
			      +" <color minValue='2' maxValue='3' code='e6efeF' label='等待审批'/>"
			      +" <color minValue='3' maxValue='4' code='6ccbff' label='审批通过'/>"
			      +" <color minValue='4' maxValue='5' code='5eec33' label='审批未通过'/>"
			      +" <color minValue='5' maxValue='6' code='8ffeedd' label='执行中'/>"
			      +" <color minValue='6' maxValue='7' code='22FFbb' label='停止执行'/>"
			      +" <color minValue='7' maxValue='8' code='FFEEFF' label='执行完成'/>"
			      +" <color minValue='8' maxValue='9' code='8BBA00' label='总结归档'/>"
			      +" </colorRange>"
			   +" <pointers>"
			   +" <pointer value='5' link=\"JavaScript:myJS('Approval did not pass, 5');\" />"
			      +"</pointers>"
			+" </chart>";
			return xml;

		}
		public static String getAnge(){
			String xml=" <Chart forceTickValueDecimals='1'  bgColor='AEC0CA,FFFFFF' fillAngle='45' upperLimit='90' lowerLimit='20' majorTMNumber='8' showGaugeBorder='0' gaugeOuterRadius='140' gaugeOriginX='205' gaugeOriginY='206' gaugeInnerRadius='2' formatNumberScale='1' numberPrefix='K' displayValueDistance='20' decimalPrecision='1' tickMarkDecimalPrecision='1' pivotRadius='17' showPivotBorder='1' pivotBorderColor='ffffff' pivotBorderThickness='3' pivotFillMix='FFFFFF,000000'>"
	 +" <colorRange>"
	 +" <color minValue='20' maxValue='50' code='5eec33'/>" 
	 +" <color minValue='50' maxValue='70' code='F6BD0F'/>" 
	 +" <color minValue='70' maxValue='90' code='B41527'/>" 
	 +" </colorRange>"
	 +" <dials>"
	 +" <dial value='45' borderAlpha='0' bgColor='000000' baseWidth='5' topWidth='1' radius='130' />" 
	 +" </dials>"
	 
	 +" <annotations>"
	 +" <annotationGroup xPos='205' yPos='207.5'>"
	 +" <annotation type='circle' xPos='0' yPos='2.5' radius='150' startAngle='0' endAngle='180' fillPattern='linear' fillAsGradient='1' fillColor='dddddd,666666' fillAlpha='100,100' fillRatio='50,50' fillDegree='0' showBorder='1' borderColor='444444' borderThickness='2' />" 
	 +" <annotation type='circle' xPos='0' yPos='0' radius='145' startAngle='0' endAngle='180' fillPattern='linear' fillAsGradient='1' fillColor='666666,ffffff' fillAlpha='100,100' fillRatio='50,50' fillDegree='0' />" 
	 +" </annotationGroup>"
	 +" </annotations>"
	 +" </Chart>'";
			return xml;
		}
		
		public static String getPie(){
			String xml = "<chart palette='2' rotateYAxisName='0' caption='奇迹公司部门人员数' xAxisName='部门' refreshInterval='60'"    
                +" yAxisName='人数' showValues='1'>"
                + "<set label='总经理办公室' value='3' />"
                + "<set label='行政部' value='2' />"
                + "<set label='人事部' value='3' />"
                + "<set label='研发部' value='20' />"
                + "<set label='项目部' value='35' />"
                + "<set label='销售部' value='10' />"
                + "</chart>";
			return xml;
		}
		
		public static void main(String[] args) {
			Date date=new Date();
			DecimalFormat df=new DecimalFormat("#.00");
			System.out.println(df.format(date.getHours()+date.getMinutes()/60.00));
			System.out.println(date.getMinutes()/5);
			System.out.println(date.getSeconds());
		}
}
