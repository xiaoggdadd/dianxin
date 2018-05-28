package com.noki.groupData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import java.util.HashMap;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class GroupDataBean {
	
	public  String getSelData(String shi,String riqi,String path) {
		if (null==riqi || "".equals(riqi)){
			
			SimpleDateFormat ddd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			riqi = ddd.format(new Date());

		}
		String[] dateTemp = riqi.split("-");
		String year1="";
		String year2="";
		String month = "";
		if (dateTemp.length>1){
			year1 = dateTemp[0];
			month = dateTemp[1];
			if (!"".equals(year1)){
				year2 = String.valueOf(Integer.parseInt(year1)-1);
			}
		}
 		StringBuffer ret = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		HashMap<String, GroupDataViewBean> dataMap = new HashMap<String, GroupDataViewBean>();
		ArrayList<String> cityCode = new ArrayList<String>();
		GroupDataViewBean viewBean = new GroupDataViewBean();
        DataBase db = new DataBase();
        ResultSet rs = null;
		try{
			 db.connectDb();
			sql.append(" select  t.shi, (select agname from d_area_grade where agcode=t.shi)as shiname,count(t.jzcode) as zhanshu,sum(k.ljzs) as ljzhanshu, sum(k.Zpsl) as zpgs from zhandian t, zhandiankz k ");
			sql.append(" where t.id = k.zdid  and t.jztype = 'zdlx08' and t.MANUALAUDITSTATUS_STATION = '2'");
			if (null!=shi && !"".equals(shi)){
				sql.append("  and t.shi = '"+shi+"'");
			}
			sql.append("group by t.shi");
			if (sql.length()>0){
				 rs = db.queryAll(sql.toString());
	             while (rs.next()) {
	            	   viewBean = new GroupDataViewBean();
	            	   
	            	   viewBean.setShi(rs.getString("shiname"));
	            	   viewBean.setZhanshu(rs.getString("zhanshu"));
	            	   viewBean.setLjzhanshu(rs.getString("ljzhanshu"));
	            	   viewBean.setZpgs(rs.getString("zpgs"));
	            	   cityCode.add(rs.getString("shi"));
	            	   dataMap.put(rs.getString("shi"), viewBean);
	             }                
	
			}
		   // 各市基站用电量·每载频用电量
			sql = new StringBuffer();
			rs = null;
			ArrayList<CityDataBean> actualdegreeList = new ArrayList<CityDataBean>();
			sql.append(" select z.shi ,substr(t.thisdatetime,0,7) as yearMonth ,sum(t.actualdegree) as sumactualdegree from ammeterdegrees t ,dianbiao b,zhandian z where t.ammeterid_fk(+) = b.dbid and ");
			sql.append("  b.zdid = z.id  and z.jztype = 'zdlx08' and z.MANUALAUDITSTATUS_STATION = '2' and t.MANUALAUDITSTATUS='1' ");
			if (null!=shi && !"".equals(shi)){
				sql.append("  and z.shi = '"+shi+"'");
			}
			if (!"".equals(riqi)){
				sql.append(" and substr(t.thisdatetime,0,4) in('"+year1+"','"+year2+"')");
			}
		    sql.append("  group by z.shi ,substr(t.thisdatetime,0,7)");
			if (sql.length()>0){
				 rs = db.queryAll(sql.toString());
	             while (rs.next()) {
	            	 CityDataBean bean = new CityDataBean();
	            	 bean.setCityCode(rs.getString("shi"));
	            	 bean.setYearMonth(rs.getString("yearMonth"));
	            	 bean.setMonthData(rs.getString("sumactualdegree"));
	            	 actualdegreeList.add(bean);
	             }                
	             //基站用电量
	             //本年累计
	             //本月累计
	             //去年累计
	             //同比
	             //每载频用电量
            	 String shicode ="";
            	 Double sum1 = 0.0;
            	 String shisMonthSum = "0";
            	 String lastMonthSum = "0";
            	 int n = 0;
	             for(CityDataBean b:actualdegreeList){
	            	 n = n + 1;
	            	 b.getCityCode();
	            	 if (!shicode.equals(b.getCityCode())){
	            		 if (sum1 != 0){
	            			 viewBean = new GroupDataViewBean();
	            			 viewBean = (GroupDataViewBean)dataMap.get(shicode);
	            			//本年累计
	            			 viewBean.setDllj(String.valueOf(sum1/10000));
	            			//每载频本年累计
	            			 String zps = viewBean.getZpgs();
	            			 if (!"".equals(zps)){
	            				 viewBean.setZpdllj(String.valueOf(sum1/Double.parseDouble(zps)));
	            			 }else{
	            				 viewBean.setZpdllj(String.valueOf(sum1));
	            			 }
	            			 //同比
	            			 Double dd=0.0;
	            			 if (!"".equals(shisMonthSum)&& null!=shisMonthSum && !"0".equals(shisMonthSum)){
	            				 dd = Double.parseDouble(lastMonthSum)/Double.parseDouble(shisMonthSum)*100;
	            			 }
	            			  
	            			 viewBean.setDltb(String.valueOf(dd));
	            			 viewBean.setZpdltb(String.valueOf(dd));
	            			 viewBean.setDlby(String.valueOf(Long.parseLong(shisMonthSum)/10000));
	            			 viewBean.setZpdlby(String.valueOf(Double.parseDouble(shisMonthSum)/10000/Double.parseDouble(zps)));
	            			 dataMap.put(shicode, viewBean);
	            		 }
	            		 sum1 = 0.0;
	            		 shisMonthSum = "0";
	            		 lastMonthSum = "0";
	            		 shicode = b.getCityCode();
	            	 }
                     if (shicode.equals(b.getCityCode())){
                          String[] strDate = b.getYearMonth().split("-");
                          if ((year1.equals(strDate[0]))&&(Long.parseLong(month)>= Long.parseLong(strDate[1])) ){
                        	  if (!"".equals(b.getMonthData())&&(Long.parseLong(month)> Long.parseLong(strDate[1]))){
                        		  sum1 = sum1 +Long.parseLong(b.getMonthData());
                        	  }else if (month.equals(strDate[1])){
                        		  sum1 = sum1 +Double.parseDouble(b.getMonthData());
                        		  shisMonthSum = b.getMonthData();
                        	  }
                          }
                          if ((year2.equals(strDate[0]))&&(Long.parseLong(month)== Long.parseLong(strDate[1])) ){
                        	  if (!"".equals(b.getMonthData())){
                        		  lastMonthSum = b.getMonthData();
                        	  }
                          }
	            	 }
                     if (n==actualdegreeList.size()){
            			 viewBean = new GroupDataViewBean();
            			 viewBean = (GroupDataViewBean)dataMap.get(shicode);
            			//本年累计
            			 viewBean.setDllj(String.valueOf(sum1/10000));
            			//每载频本年累计
            			 String zps = viewBean.getZpgs();
            			 if (!"".equals(zps)){
            				 viewBean.setZpdllj(String.valueOf(sum1/Double.parseDouble(zps)));
            			 }else{
            				 viewBean.setZpdllj(String.valueOf(sum1));
            			 }
            			 //同比
            			 Double dd=0.0;
            			 if (!"".equals(shisMonthSum)&& null!=shisMonthSum && !"0".equals(shisMonthSum)){
            				 dd = Double.parseDouble(lastMonthSum)/Double.parseDouble(shisMonthSum)*100;
            			 }
            			  
            			 viewBean.setDltb(String.valueOf(dd));
            			 viewBean.setZpdltb(String.valueOf(dd));
            			 viewBean.setDlby(String.valueOf(Long.parseLong(shisMonthSum)/10000));
            			 viewBean.setZpdlby(String.valueOf(Double.parseDouble(shisMonthSum)/10000/Double.parseDouble(zps)));
            			 dataMap.put(shicode, viewBean);
                     }
	             }
			}
			//基站电费总支出·每载频电费支出
			sql = new StringBuffer();
			rs = null;
			ArrayList<CityDataBean> electricfeesList = new ArrayList<CityDataBean>();
			sql.append(" select z.shi ,substr(t.thisdatetime,0,7) as yearMonth ,sum(e.Actualpay)as Actualpaysum from ammeterdegrees t ,dianbiao b,zhandian z ,ELECTRICFEES e where t.ammeterid_fk = b.dbid and  ");
			sql.append("  b.zdid = z.id and e.ammeterdegreeid_fk = t.ammeterdegreeid and z.jztype = 'zdlx08' and z.MANUALAUDITSTATUS_STATION = '2' and t.MANUALAUDITSTATUS='1' and  e.MANUALAUDITSTATUS='1' ");
			if (null!=shi && !"".equals(shi)){
				sql.append("  and z.shi = '"+shi+"'");
			}
			if (!"".equals(riqi)){
				sql.append(" and substr(t.thisdatetime,0,4) in('"+year1+"','"+year2+"')");
			}
		    sql.append("  group by z.shi ,substr(t.thisdatetime,0,7)");
			if (sql.length()>0){
				 rs = db.queryAll(sql.toString());
	             while (rs.next()) {
	            	 CityDataBean bean = new CityDataBean();
	            	 bean.setCityCode(rs.getString("shi"));
	            	 bean.setYearMonth(rs.getString("yearMonth"));
	            	 bean.setMonthData(rs.getString("Actualpaysum"));
	            	 electricfeesList.add(bean);
	             }                
	             //基站电费
	             //本年累计
	             //本月累计
	             //去年累计
	             //同比
	             //每载频电费
            	 String shicode ="";
            	 Double sum1 = 0.0;
            	 String shisMonthSum = "0";
            	 String lastMonthSum = "0";
            	 int i = 0;
	             for(CityDataBean b:electricfeesList){
	            	 i = i+1;
	            	 if (!shicode.equals(b.getCityCode())){
	            		 if (sum1 != 0){
	            			 viewBean = new GroupDataViewBean();
	            			 viewBean = (GroupDataViewBean)dataMap.get(shicode);
	            			//本年累计
	            			 viewBean.setDflj(String.valueOf(sum1/10000));
	            			//每载频本年累计
	            			 String zps = viewBean.getZpgs();
	            			 if (!"".equals(zps)){
	            				 viewBean.setZpdflj(String.valueOf(sum1/Long.parseLong(zps)));
	            			 }else{
	            				 viewBean.setZpdflj(String.valueOf(sum1));
	            			 }
	            			 //同比
	            			 Double dd=0.0;
	            			 if (!"".equals(shisMonthSum)&& null!=shisMonthSum && !"0".equals(shisMonthSum)){
	            				 dd = Double.parseDouble(lastMonthSum)/Double.parseDouble(shisMonthSum)*100;
	            			 }
	            			 viewBean.setDftb(String.valueOf(dd));
	            			 viewBean.setZpdftb(String.valueOf(dd));
	            			 viewBean.setDfby(String.valueOf(Double.parseDouble(shisMonthSum)/10000));
	            			 viewBean.setZpdfby(String.valueOf(Double.parseDouble(shisMonthSum)/Double.parseDouble(zps)));
	            			 dataMap.put(shicode, viewBean);
	            		 }
	            		 sum1 = 0.0;
	            		 shisMonthSum = "0";
	            		 lastMonthSum = "0";
	            		 shicode = b.getCityCode();
	            	 }
                      if (shicode.equals(b.getCityCode())){
                          String[] strDate = b.getYearMonth().split("-");
                          if ((year1.equals(strDate[0]))&&(Long.parseLong(month)>= Long.parseLong(strDate[1])) ){
                        	  if (!"".equals(b.getMonthData())&&(Long.parseLong(month)> Long.parseLong(strDate[1]))){
                        		  sum1 = sum1 +Double.parseDouble(b.getMonthData());
                        	  }else if (month.equals(strDate[1])){
                        		  sum1 = sum1 +Double.parseDouble(b.getMonthData());
                        		  shisMonthSum = b.getMonthData();
                        	  }
                          }
                          if ((year2.equals(strDate[0]))&&(Long.parseLong(month)== Long.parseLong(strDate[1])) ){
                        	  if (!"".equals(b.getMonthData())){
                        		  lastMonthSum = b.getMonthData();
                        	  }
                          }
	            	 }
                      if (i==electricfeesList.size()){
                    	  viewBean = new GroupDataViewBean();
	            			 viewBean = (GroupDataViewBean)dataMap.get(shicode);
	            			//本年累计
	            			 viewBean.setDflj(String.valueOf(sum1/10000));
	            			//每载频本年累计
	            			 String zps = viewBean.getZpgs();
	            			 if (!"".equals(zps)){
	            				 viewBean.setZpdflj(String.valueOf(sum1/Long.parseLong(zps)));
	            			 }else{
	            				 viewBean.setZpdflj(String.valueOf(sum1));
	            			 }
	            			 //同比
	            			 Double dd=0.0;
	            			 if (!"".equals(shisMonthSum)&& null!=shisMonthSum && !"0".equals(shisMonthSum)){
	            				 dd = Double.parseDouble(lastMonthSum)/Double.parseDouble(shisMonthSum)*100;
	            			 }
	            			 viewBean.setDftb(String.valueOf(dd));
	            			 viewBean.setZpdftb(String.valueOf(dd));
	            			 viewBean.setDfby(String.valueOf(Double.parseDouble(shisMonthSum)/10000));
	            			 viewBean.setZpdfby(String.valueOf(Double.parseDouble(shisMonthSum)/Double.parseDouble(zps)));
	            			 dataMap.put(shicode, viewBean);
                      }
	             }
			}
			ret.append(editMap(dataMap,cityCode,path));
		}catch (Exception de) {
            de.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            try {
                db.close();
            } catch (DbException de) {
                de.printStackTrace();
            }

        }
		return ret.toString();
	}
	public String  editMap(HashMap<String, GroupDataViewBean> dataMap,ArrayList<String> cityCodeList,String path){
		//物理站址数
		long sum1 =0;
		//逻辑站数
		long sum2 =0;
		//载频个数
		long sum3 =0;
		//基站用电量·本年累计
		Double sum4 =0.0;
		//基站用电量·同比
		Double sum5 =0.0;
		//基站用电量·本月
		Double sum6 =0.0;
		
		//基站电费·本年累计
		Double sum7 =0.0;
		//基站电费·同比
		Double sum8 =0.0;
		//基站电费·本月
		Double sum9 =0.0;
		
		//每载频用电量·本年累计
		Double sum10 =0.0;
		//每载频用电量·同比
		Double sum11 =0.0;
		//每载频用电量·本月
		Double sum12 =0.0;
		
		//每载频电费·本年累计
		Double sum13 =0.0;
		//每载频电费·同比
		Double sum14 =0.0;
		//每载频电费·本月
		Double sum15 =0.0;
		
		for(Object b:cityCodeList){
			GroupDataViewBean bb =(GroupDataViewBean)dataMap.get(b) ;
			if (!"".equals(bb.getZhanshu())&& null!=bb.getZhanshu()){
				sum1 = sum1+ Long.parseLong(bb.getZhanshu());
			}
			if (!"".equals(bb.getLjzhanshu())&& null!=bb.getLjzhanshu()){
				sum2 = sum2+ Long.parseLong(bb.getLjzhanshu());
			}
            if (!"".equals(bb.getZpgs())&& null!=bb.getZpgs()){
            	sum3 = sum3+ Long.parseLong(bb.getZpgs());
            }
			if (!"".equals(bb.getDllj())&& null!=bb.getDllj()){
				sum4 = sum4+ Double.parseDouble(bb.getDllj());
			}
			if (!"".equals(bb.getDltb())&& null!=bb.getDltb()){
				sum5 = sum5+ Double.parseDouble(bb.getDltb());
			}
			if (!"".equals(bb.getDlby())&& null!=bb.getDlby()){
				sum6 = sum6+ Double.parseDouble(bb.getDlby()); 
			}
			if (!"".equals(bb.getDflj())&& null!=bb.getDflj()){
				sum7 = sum7+ Double.parseDouble(bb.getDflj());
			}
			if (!"".equals(bb.getDflj())&& null!=bb.getDflj()){
				sum8 = sum8+ Double.parseDouble(bb.getDftb());
			}
			if (!"".equals(bb.getDfby())&& null!=bb.getDfby()){
				sum9 = sum9+ Double.parseDouble(bb.getDfby());
			}
			if (!"".equals(bb.getZpdllj())&& null!=bb.getZpdllj()){
				sum10= sum10 + Double.parseDouble(bb.getZpdllj());
			}
			if (!"".equals(bb.getZpdltb())&& null!=bb.getZpdllj()){
				sum11= sum11 + Double.parseDouble(bb.getZpdltb());
			}
			if (!"".equals(bb.getZpdlby())&& null!=bb.getZpdlby()){
				sum12= sum12 + Double.parseDouble(bb.getZpdlby());
			}
			if (!"".equals(bb.getZpdflj())&& null!=bb.getZpdflj()){
				sum13= sum13 + Double.parseDouble(bb.getZpdflj());
			}
			if (!"".equals(bb.getZpdftb())&& null!=bb.getZpdftb()){
				sum14= sum14 + Double.parseDouble(bb.getZpdftb());
			}
			if (!"".equals(bb.getZpdfby())&& null!=bb.getZpdfby()){
				sum15= sum15 + Double.parseDouble(bb.getZpdfby());
			}
		}
		//
		GroupDataViewBean groupDataBean = new GroupDataViewBean();
		groupDataBean.setShi("合计");
		groupDataBean.setZhanshu(String.valueOf(sum1));
		groupDataBean.setLjzhanshu(String.valueOf(sum2));
		groupDataBean.setZpgs(String.valueOf(sum3));
		groupDataBean.setDllj(String.valueOf(sum4));
		groupDataBean.setDltb(String.valueOf(sum5/cityCodeList.size()));
		groupDataBean.setDlby(String.valueOf(sum6));
		groupDataBean.setDflj(String.valueOf(sum7));
		groupDataBean.setDftb(String.valueOf(sum8/cityCodeList.size()));
		groupDataBean.setDfby(String.valueOf(sum9));
		groupDataBean.setZpdllj(String.valueOf(sum10));
		groupDataBean.setZpdltb(String.valueOf(sum11/cityCodeList.size()));
		groupDataBean.setZpdlby(String.valueOf(sum12));	
		groupDataBean.setZpdflj(String.valueOf(sum13));	
		groupDataBean.setZpdftb(String.valueOf(sum14/cityCodeList.size()));	
		groupDataBean.setZpdfby(String.valueOf(sum15));	
		dataMap.put("zongji", groupDataBean);
		cityCodeList.add("zongji");
		Comm comm = new Comm();
		System.out.println(path);
		String exmplePath = path+"/13.xls";
		String copyfile = path+"/基站用电量汇总分析表.xls";
		
		comm.writeFile(dataMap, cityCodeList, exmplePath, copyfile);
		return copyfile;
	}
    //getTxjData
	public  String getTxjData(String shi,String riqi,String path) {
		String  ret = "";
		
		return ret;
	}
}
