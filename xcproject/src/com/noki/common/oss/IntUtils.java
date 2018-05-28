package com.noki.common.oss;

/**
 * 整形转换工具类
 * @author guol
 *
 */
public class IntUtils {

	 public static int toInt(String value){
		 int result = 0;
		 try {
			 result = Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println(String.format("from %s (String) to int 转换失败! ", value));
			result = -9999;
		}
		 return result;
	 }
	 
	 public static void main(String[] args) {
		System.out.println(IntUtils.toInt("是"));
	}
}
