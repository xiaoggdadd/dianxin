package com.noki.common.oss;

/**
 * ����ת��������
 * @author guol
 *
 */
public class IntUtils {

	 public static int toInt(String value){
		 int result = 0;
		 try {
			 result = Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println(String.format("from %s (String) to int ת��ʧ��! ", value));
			result = -9999;
		}
		 return result;
	 }
	 
	 public static void main(String[] args) {
		System.out.println(IntUtils.toInt("��"));
	}
}
