package com.ptac.app.collectanalysis.onesitedb.util;

import java.util.List;

import com.ptac.app.collectanalysis.onesitedb.bean.OneSiteDb;



public class UtilIneSiteDb {
	public long[] getSum(List<OneSiteDb> list){
		long[] sum = new long[5];
		long zds = 0;
		long yzyb = 0;
		long yzdb = 0;
		long hsyzdb = 0;
		long yzlb = 0;
		if(list.size()!=0){
			for(OneSiteDb inte:list){
				zds+=Long.parseLong(inte.getZds());
				yzyb+=Long.parseLong(inte.getYzyb());
				yzdb+=Long.parseLong(inte.getYzdb());
				hsyzdb+=Long.parseLong(inte.getFhsyzdb());
				yzlb+=Long.parseLong(inte.getYzlb());
			}
		}
		sum[0] = zds;
		sum[1] = yzyb;
		sum[2] = yzdb;
		sum[3] = hsyzdb;
		sum[4] = yzlb;
		return sum;
	}
}
