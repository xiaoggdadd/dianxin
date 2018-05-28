package com.noki.dfdfcx;

import java.util.Comparator;

import com.noki.dfdfcx.bean.dfdfBean;
/**
 * @author GT
 * list ÅÅÐò
 * ¸ßÖµ½µÐò	
 * */
public class ContentComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
	    // TODO Auto-generated method stub
	    dfdfBean c1 = (dfdfBean) o1;
	    dfdfBean c2 = (dfdfBean) o2;
	    
	    if (Double.valueOf(c1.getCha()!=null?c1.getCha():"0") > Double.valueOf(c2.getCha()!=null?c2.getCha():"0")) {
	     return -1;
	    } else {
	     if (Double.valueOf(c1.getCha()!=null?c1.getCha():"0") > Double.valueOf(c2.getCha()!=null?c2.getCha():"0")) {
	      return 0;
	     } else {
	      return 1;
	      
	     }
	    }
	}

}
