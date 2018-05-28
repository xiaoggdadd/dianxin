package com.noki.mobi.cx.chart;

import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.StringTokenizer;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class GetDataSet {
    public GetDataSet() {
    }


    public CategoryDataset getDataset_yf(String dlhj,String fyhj,String yflx) {
// categories...
        
        System.out.println("***"+dlhj+" "+fyhj+" "+yflx+dlhj.split(";")[0]);
        //0;0;0; 0;0;0; 其他;砖混;彩钢板;
        double[][] data =new double[][] {{Double.parseDouble(dlhj.split(";")[0]), Double.parseDouble(dlhj.split(";")[1]), Double.parseDouble(dlhj.split(";")[2])},
        		{Double.parseDouble(fyhj.split(";")[0]), Double.parseDouble(fyhj.split(";")[1]), Double.parseDouble(fyhj.split(";")[2])}}; 
        		String[] rowKeys = {"电量", "电费"}; 
        		String[] columnKeys = yflx.split(";");
      
      CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data); 

        return dataset;

    }
    private synchronized float[][] get_yf_data(String dlhj,String fyhj) {
    	String dl=dlhj!=null?dlhj:"0";
    	String fy=fyhj!=null?fyhj:"0";
        float[][] data = new float[3][2];
        StringTokenizer st1= new StringTokenizer(dl,";") ;
        StringTokenizer st2 = new StringTokenizer(fy,";");
        
        int i = 0;
        while(st1.hasMoreTokens()&&st2.hasMoreTokens()){
            data[i][0]=Float.parseFloat(st1.nextToken());
            data[i][1]=Float.parseFloat(st2.nextToken());
            i++;
        }
        return data;
    }

    public CategoryDataset getDataset_jzdb(String dlhj,String fyhj,String jzmc) {
// categories... 
        System.out.println(dlhj+" "+fyhj+" "+jzmc);
    	CategoryDataset  dataset=null;
    	if(!jzmc.split(";")[0].equals("0")||!jzmc.split(";")[1].equals("0")){
    	 double[][] data =new double[][] {{Double.parseDouble(dlhj.split(";")[0]), Double.parseDouble(dlhj.split(";")[1])},
         		{Double.parseDouble(fyhj.split(";")[0]), Double.parseDouble(fyhj.split(";")[1])}}; 
         		String[] rowKeys = {"电量", "电费"}; 
         		String[] columnKeys = jzmc.split(";");

        dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data); 
        }
         return dataset;
    }

   
    private synchronized float[][] get_jz_data(String dlhj,String fyhj) {
    	String dl=dlhj!=null?dlhj:"0";
    	String fy=fyhj!=null?fyhj:"0";
        float[][] data = new float[2][2];
        StringTokenizer st1= new StringTokenizer(dl,";") ;
        StringTokenizer st2 = new StringTokenizer(fy,";");
        int i = 0;
        while(st1.hasMoreTokens()&&st2.hasMoreTokens()){
            data[i][0]=Float.parseFloat(st1.nextToken());
            data[i][1]=Float.parseFloat(st2.nextToken());
            i++;
        }

        return data;
    }

}
