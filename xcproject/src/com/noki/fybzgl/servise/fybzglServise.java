package com.noki.fybzgl.servise;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.noki.fybzgl.fybzglMap;
import com.noki.fybzgl.bean.fybzglBean;
import com.noki.fybzgl.dao.fybzglDao;

/***
 * @author GT
 * 
 * */
public class fybzglServise {
	//得到总的费用信息
	public synchronized fybzglBean getAall(String whereStr,String str,String feiyongtype,String zflx){
		fybzglDao dao = new fybzglDao();
		DecimalFormat mat=new DecimalFormat("0.00");
		ArrayList<fybzglBean> list = dao.getAall(whereStr, str,feiyongtype,zflx);
		for(fybzglBean bean : list){
			bean.setCwbili(mat.format(Double.valueOf(bean.getCwmonerycount()!=null?bean.getCwmonerycount():"0")/Double.valueOf(bean.getMonerycount())*100)+"%");
			double yj01 = Double.valueOf(bean.getYj01()!=null?bean.getYj01():"0");
			double yj02 = Double.valueOf(bean.getYj02()!=null?bean.getYj02():"0");
			double yj03 = Double.valueOf(bean.getYj03()!=null?bean.getYj03():"0");
			double yj04 = Double.valueOf(bean.getYj04()!=null?bean.getYj04():"0");
			double yj05 = Double.valueOf(bean.getYj05()!=null?bean.getYj05():"0");
			double yj06 = Double.valueOf(bean.getYj06()!=null?bean.getYj06():"0");
			bean.setYj01(mat.format(yj01));
			bean.setYj02(mat.format(yj02));
			bean.setYj03(mat.format(yj03));
			bean.setYj04(mat.format(yj04));
			bean.setYj05(mat.format(yj05));
			bean.setYj06(mat.format(yj06));
			double yj = yj01+yj02+yj03+yj04+yj05+yj06;
			bean.setYjbili(mat.format((yj/Double.valueOf(bean.getMonerycount())*100))+"%");
			double erj01 = Double.valueOf(bean.getErj01()!=null?bean.getErj01():"0");
			double erj02 = Double.valueOf(bean.getErj02()!=null?bean.getErj02():"0");
			double erj03 = Double.valueOf(bean.getErj03()!=null?bean.getErj03():"0");
			double erj04 = Double.valueOf(bean.getErj04()!=null?bean.getErj04():"0");
			double erj05 = Double.valueOf(bean.getErj05()!=null?bean.getErj05():"0");
			double erj06 = Double.valueOf(bean.getErj06()!=null?bean.getErj06():"0");
			bean.setErj01(mat.format(erj01));
			bean.setErj02(mat.format(erj02));
			bean.setErj03(mat.format(erj03));
			bean.setErj04(mat.format(erj04));
			bean.setErj05(mat.format(erj05));
			bean.setErj06(mat.format(erj06));
			String monery = mat.format(Double.valueOf(bean.getMonerycount()!=null?bean.getMonerycount():"0"));
			String cwmonery = mat.format(Double.valueOf(bean.getCwmonerycount()!=null?bean.getCwmonerycount():"0"));
			bean.setMonerycount(monery);
			bean.setCwmonerycount(cwmonery);
			double er = erj01+erj02+erj03+erj04+erj05+erj06;
			bean.setErjbili(mat.format((er/Double.valueOf(bean.getMonerycount())*100))+"%");
			
			
		}
		fybzglBean fybzall = new fybzglBean(); 
		if(list.size()>1){
		double count = 0;
		double monery = 0;
		double cwmonery =0;
		double yj01 =0;
		double yj02 =0;
		double yj03 =0;
		double yj04 =0;
		double yj05 =0;
		double yj06 =0;
		double yj =0;
		double erj01=0;
		double erj02=0;
		double erj03=0;
		double erj04=0;
		double erj05=0;
		double erj06=0;
		double erj=0;
		for(fybzglBean beana : list ){
			count+=Double.valueOf(beana.getCount());
			monery+=Double.valueOf(beana.getMonerycount());
			cwmonery+=Double.valueOf(beana.getCwmonerycount());
			yj01+=Double.valueOf(beana.getYj01());
			yj02+=Double.valueOf(beana.getYj02());
			yj03+=Double.valueOf(beana.getYj03());
			yj04+=Double.valueOf(beana.getYj04());
			yj05+=Double.valueOf(beana.getYj05());
			yj06+=Double.valueOf(beana.getYj06());
			double temp=yj01+yj02+yj03+yj04+yj05+yj06;
			yj=+temp;
			System.out.println(yj+"                 ddd"+beana.getShi());
			erj01+=Double.valueOf(beana.getErj01());
			erj02+=Double.valueOf(beana.getErj02());
			erj03+=Double.valueOf(beana.getErj03());
			erj04+=Double.valueOf(beana.getErj04());
			erj05+=Double.valueOf(beana.getErj05());
			erj06+=Double.valueOf(beana.getErj06());
			erj=+erj01+erj02+erj03+erj04+erj05+erj06;
		}
		fybzall.setShi("山东省");
		fybzall.setCount(String.valueOf(count));
		fybzall.setCwmonerycount(mat.format(cwmonery));
		fybzall.setCwbili(mat.format((cwmonery/monery)*100)+"%");
		fybzall.setMonerycount(mat.format(monery));
		fybzall.setYj01(mat.format(yj01));
		fybzall.setYj02(mat.format(yj02));
		fybzall.setYj03(mat.format(yj03));
		fybzall.setYj04(mat.format(yj04));
		fybzall.setYj05(mat.format(yj05));
		fybzall.setYj06(mat.format(yj06));
		fybzall.setYjbili(mat.format((yj/monery)*100)+"%");
		fybzall.setErj01(mat.format(erj01));
		fybzall.setErj02(mat.format(erj02));
		fybzall.setErj03(mat.format(erj03));
		fybzall.setErj04(mat.format(erj04));
		fybzall.setErj05(mat.format(erj05));
		fybzall.setErj06(mat.format(erj06));
		fybzall.setErjbili(mat.format((erj/monery)*100)+"%");}
		else{
			for(fybzglBean beant :list){
				fybzall = beant;
			}
		}
		return fybzall;
	}
	
	//得到站点属性的分类
	public synchronized ArrayList<fybzglBean> getZdsxMonery(String whereStr,String feiyongtype,String zflx){
		fybzglDao dao = new fybzglDao();
		ArrayList<fybzglBean> list = dao.getZdsxMonery(whereStr,feiyongtype,zflx);
		for(fybzglBean bean : list){
			if("zdsx01".equals(bean.getZdsx())){bean.setZdsx("局用机房");bean.setProperty("zdsx01");}
			if("zdsx02".equals(bean.getZdsx())){bean.setZdsx("基站");bean.setProperty("zdsx02");}
			if("zdsx03".equals(bean.getZdsx())){bean.setZdsx("营业网点");bean.setProperty("zdsx03");}
			if("zdsx04".equals(bean.getZdsx())){bean.setZdsx("接入网");bean.setProperty("zdsx04");}
			if("zdsx05".equals(bean.getZdsx())){bean.setZdsx("其他");bean.setProperty("zdsx05");}
			if("zdsx06".equals(bean.getZdsx())){bean.setZdsx("乡镇支局");bean.setProperty("zdsx06");}
		}
		
//		Iterator<fybzglBean> iterator = list.iterator();
//        while(iterator.hasNext()) {
//            fybzglBean str = iterator.next();
//             if("null".equals(str.getZdsx())) {
//                 iterator.remove();
//          }
//        }
		int temp = list.size();
		String[] str = new String[temp];
		double[] dor = new double[temp];
		for(int i =0;i<list.size();i++){
			String st = list.get(i).getZdsx();
			Double dtt = Double.valueOf(list.get(i).getZdsxmonery()!=null?list.get(i).getZdsxmonery():"0");
			
			str[i]=st;
			dor[i] = dtt;
		}
		System.out.println(list);
		fybzglMap map = new fybzglMap();
		map.createValidityComparePimChar(map.getPieDataSet(str, dor),
				"山东联通能源管理系统站点属性统计报告", "zdsx.png", str);
		return list;
	}
	
	//得到供电方式的分类4444
	public synchronized ArrayList<fybzglBean> getGdfsMonery(String whereStr,String feiyongtype,String zflx){
		fybzglDao dao = new fybzglDao();
		ArrayList<fybzglBean> list = dao.getGdfsMonery(whereStr,feiyongtype,zflx);
		double gdfs04 = 0.0000;
		double gdfs03 = 0.0000;
		
		fybzglBean temp = new fybzglBean();
		for(fybzglBean bean1 :list){
			if("gdfs04".equals(bean1.getGdfs())){
				gdfs04 = Double.valueOf(bean1.getGdfsmonery()!=null?bean1.getGdfsmonery():"0");
				temp = bean1;
			}
			
			if("gdfs03".equals(bean1.getGdfs())){
				gdfs03 = Double.valueOf(bean1.getGdfsmonery()!=null?bean1.getGdfsmonery():"0");
			}
		}
		for(fybzglBean bean : list){
			if("gdfs01".equals(bean.getGdfs())){bean.setGdfs("供电局");}
			if("gdfs02".equals(bean.getGdfs())){bean.setGdfs("业主");}
			if("gdfs03".equals(bean.getGdfs())){bean.setGdfs("其他");bean.setGdfsmonery(String.valueOf((gdfs03+gdfs04)));}
		}
		list.remove(temp);
		int temp1 = list.size();
		String[] str = new String[temp1];
		double[] dor = new double[temp1];
		for(int i =0;i<list.size();i++){
			str[i]=list.get(i).getGdfs();
			dor[i] = Double.valueOf(list.get(i).getGdfsmonery()!=null?list.get(i).getGdfsmonery():"0");
		}
		System.out.println("ddd");
		fybzglMap map = new fybzglMap();
		map.createValidityComparePimChar(map.getPieDataSet(str, dor),
				"山东联通能源管理系统供电方式统计报告", "gdfs.png", str);
		return list;
	  
	}
	
	public static void main(String[] args) {
		fybzglServise s = new fybzglServise();
//		fybzglBean bean = s.getAall(" AND e.accountmonth >= '2013-05' AND e.accountmonth <= '2013-05' and z.shi='13701'","AND ee.accountmonth >= '2013-05' AND ee.accountmonth <= '2013-05'and zz.shi='13701'");
//		System.out.println(bean);
//			System.out.println(bean.getShi()+" "+bean.getCount()+" "+bean.getMonerycount()+" "+bean.getCwmonerycount()+" "+bean.getCwbili()
//					+" "+bean.getYj01()+" "+bean.getYj02()+" "+bean.getYj03()+" "+bean.getYj04()+" "+bean.getYj05()+" "+bean.getYj06()		
//			        +" "+bean.getErj01()+" "+bean.getErj02()+" "+bean.getErj03()+" "+bean.getErj04()+" "+bean.getErj05()+" "+bean.getErj06()
//			        +" "+bean.getYjbili()+" "+bean.getErjbili());
//		ArrayList<fybzglBean> bean = s.getZdsxMonery(" AND e.accountmonth >= '2013-05' AND e.accountmonth <= '2013-05' and z.shi='13701'");
//		for(fybzglBean f: bean){
//			System.out.println(f.getZdsxmonery());
//		}
		
	}
}
