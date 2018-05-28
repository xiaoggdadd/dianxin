package com.noki.util;

import java.math.BigDecimal;
//加法
public class Add {
	public static double add(String v1,String v2){

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.add(b2).doubleValue();
        

    }
	//乘法
	public static double mul(String v1,String v2){

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).doubleValue();
        

    }
	//除法
	public static double devide(String v1,String v2){

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.divide(b2,3,BigDecimal.ROUND_HALF_UP).doubleValue();

    }
	public double getNumber(String s)
	{
		double num=0.0;
		int index=s.indexOf(".");
		String intpart=s.substring(0,index);
		String per=s.substring(index+1,s.length());
		double temp=devide(per,"100");
		num=add(intpart,String.valueOf(temp));
		return num;
	}
	public static void main(String[] a)
	{
		Add add=new Add();
		System.out.println(add.devide("0","1")*100);
	}


}
