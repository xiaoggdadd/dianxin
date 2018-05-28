package com.noki.mobi.cx.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

public class ammeterContrastAnalysisChart {
	private String dbid;
    private String dlhj;
    private String fyhj;
    private String time;
    private String dbid2;
    private String dlhj2;
    private String fyhj2;
    private String time2;
    
    public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getDlhj() {
		return dlhj;
	}
	public void setDlhj(String dlhj) {
		this.dlhj = dlhj;
	}
	public String getFyhj() {
		return fyhj;
	}
	public void setFyhj(String fyhj) {
		this.fyhj = fyhj;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDbid2() {
		return dbid2;
	}
	public void setDbid2(String dbid2) {
		this.dbid2 = dbid2;
	}
	public String getDlhj2() {
		return dlhj2;
	}
	public void setDlhj2(String dlhj2) {
		this.dlhj2 = dlhj2;
	}
	public String getFyhj2() {
		return fyhj2;
	}
	public void setFyhj2(String fyhj2) {
		this.fyhj2 = fyhj2;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	
	public String getChart(HttpServletRequest request,HttpServletResponse response ){
		
       
        // create the chart...
        JFreeChart chart = getJFreeChart(this.getDbid(),this.getDlhj(),this.getFyhj(),this.getTime(),
        		this.getDbid2(),this.getDlhj2(),this.getFyhj2(),this.getTime2());
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.YELLOW);

        ChartRenderingInfo info = null;
        HttpSession session = request.getSession();

        try {
            //Create RenderingInfo object
            response.setContentType("text/html");
            info = new ChartRenderingInfo(new StandardEntityCollection());
            BufferedImage chartImage = chart.createBufferedImage(500, 350, info);
            // putting chart as BufferedImage in session,
            // thus making it available for the image reading action Action.
            session.setAttribute("image_jzdb", chartImage);
            PrintWriter writer = new PrintWriter(response.getWriter());
            ChartUtilities.writeImageMap(writer, "image_jzdb", info, true);
            writer.flush();
        } catch (Exception e) {}

        String pathInfo = "http://";
        pathInfo += request.getServerName();
        int port = request.getServerPort();
        pathInfo += ":" + String.valueOf(port);
        pathInfo += request.getContextPath();
        String chartViewer = pathInfo + "/servlet/chart_jzdb";
        return chartViewer;
		
	}
	private JFreeChart getJFreeChart(String dbid,String dlhj,String fyhj,String time,String dbid2,String dlhj2,String fyhj2,String time2) {
		  DefaultCategoryDataset dataset = getDataset_jzdb(dbid,dlhj,time,dbid2,dlhj2,time2);
		  DefaultCategoryDataset dataset1 = getDatasetdf(dbid,fyhj,time,dbid2,fyhj2,time2);
		 
		  //��������ͼ��Ⱦ
		  LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
			//��������ͼ��Ⱦ
		  BarRenderer renderer2 = new BarRenderer();
			
			renderer2.setSeriesPaint(0,Color.red);//���ӵ���ɫΪ��ɫ
			renderer2.setSeriesPaint(1, new Color(0, 255, 255));//���ӵ���ɫΪ��ɫ




			//����X��
			CategoryAxis domainAxis = new CategoryAxis("�·�");
			//����Y��
			NumberAxis rangeAxis = new NumberAxis("�����Ա�");
			NumberAxis rangeAxis1 = new NumberAxis("��ѶԱ�");
			//����ͼ��
			CategoryPlot plot1 = new CategoryPlot(dataset, domainAxis, rangeAxis,
					renderer1);
			CategoryPlot plot2 = new CategoryPlot(dataset1, domainAxis, rangeAxis1,
					renderer2);
			//�������Ϸ���ͼ��
			final CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(
					domainAxis);
			plot.add(plot1);
			plot.add(plot2);
			JFreeChart chart = new JFreeChart("���Աȷ���", plot);
		    // set the background color for the chart...
		    chart.setBackgroundPaint(Color.ORANGE);
		    CategoryPlot catplot=chart.getCategoryPlot();  //��ȡ��״ͼ������ͼ�������  
		    catplot.setNoDataMessage("��������ʾ");//û�����ݵ�ʱ����ʾ������  
		      //�б����  
		     TextTitle txtTitle = null;  
		     txtTitle = chart.getTitle();  
		     txtTitle.setFont(new java.awt.Font("����",Font.BOLD,14));  
		     //ˮƽ�ײ��б�  
		     domainAxis.setLabelFont(new java.awt.Font("����",Font.BOLD,14));  
		     //ˮƽ�ײ�����  
		     domainAxis.setTickLabelFont(new java.awt.Font("����",Font.BOLD,12));  
		     //��ֱ����  
		     rangeAxis.setLabelFont(new java.awt.Font("����",Font.BOLD,15));  
		     rangeAxis1.setLabelFont(new java.awt.Font("����",Font.BOLD,15));  

		     chart.getLegend().setItemFont(new java.awt.Font("����", Font.BOLD, 15));  




		
		return chart;
	}
	public DefaultCategoryDataset getDataset_jzdb(String dbid,String dlhj,String time,String dbid2,String dlhj2,String time2) {
		        DefaultCategoryDataset  dataset=new DefaultCategoryDataset();
		      
		        String[] month=time.split(";");
		        String[] month2=time2.split(";");
		        String[] dl=dlhj.split(";");
		        String[] dl2=dlhj2.split(";");
		       String[][] hdl=new String[2][]; 
		       hdl[0]=dl;
		       hdl[1]=dl2;
		       String[][] monthtime=new String[2][]; 
		       monthtime[0]=month;
		       monthtime[1]=month2;
		        List list=new ArrayList();
		        list.add(dbid);
		        list.add(dbid2);
		        for(int i=0;i<list.size();i++){
		        	System.out.println(list.size());
		        	String[] value=hdl[i];
		        	
		        	String[] monthvalue=monthtime[i];
		        	for(int j=0;j<value.length;j++){
		        		System.out.println(value.length);
		        		System.out.println(Double.parseDouble(value[j])+"*"+list.get(i).toString()+"-"+monthvalue[j]);
		        		dataset.addValue(Double.parseDouble(value[j]), list.get(i).toString(), monthvalue[j]);
		        	}
		        	
		        }
		        
		         return dataset;
		         
		         
		    }
	public DefaultCategoryDataset getDatasetdf(String dbid,String fyhj,String time,String dbid2,String fyhj2,String time2) {
        DefaultCategoryDataset  dataset=new DefaultCategoryDataset();
      
        String[] month=time.split(";");
        String[] month2=time2.split(";");
        String[] dl=fyhj.split(";");
        String[] dl2=fyhj2.split(";");
       String[][] hdl=new String[2][]; 
       hdl[0]=dl;
       hdl[1]=dl2;
       String[][] monthtime=new String[2][]; 
       monthtime[0]=month;
       monthtime[1]=month2;
        List list=new ArrayList();
        list.add(dbid);
        list.add(dbid2);
        for(int i=0;i<list.size();i++){
        	System.out.println(list.size());
        	String[] value=hdl[i];
        	
        	String[] monthvalue=monthtime[i];
        	for(int j=0;j<value.length;j++){
        		System.out.println(value.length);
        		System.out.println(Double.parseDouble(value[j])+"*"+list.get(i).toString()+"-"+monthvalue[j]);
        		dataset.addValue(Double.parseDouble(value[j]), list.get(i).toString(), monthvalue[j]);
        	}
        	
        }
        
         return dataset;
	}
}
