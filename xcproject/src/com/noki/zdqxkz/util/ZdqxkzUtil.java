package com.noki.zdqxkz.util;

import java.util.List;

public class ZdqxkzUtil {

			/** 分类更改字段:供电方式5,启用状态8,标杆9
			 * @param old
			 * @param now
			 * @return
			 */
			public synchronized String getFlggZd(List<String> old,List<String> now ){
				int size = old.size();
				String str="";
				StringBuffer gstr = new StringBuffer();
				for(int i=0;i<size;i++){
					gstr.append(old.get(i).equals(now.get(i))?"":(i+1+","));
				}
				str=gstr.toString().equals("")?"":gstr.substring(0, gstr.length()-1);	
				return str;
			}
			
			/**加标志
			 * @param bfstr 字段
			 * @param bz 部分通过字段对应标志位 5:供电方式,8:启用状态,9:标杆.....
			 * @return
			 */
			public synchronized String getBftg(String bfstr,String bz){
				bfstr=null==bfstr?"":bfstr;
				String str="";
				str=bfstr.contains(bz)?bfstr:("".equals(bfstr)?bz:(bfstr+","+bz));
				return str;
			}
			
			/** 减(去掉含有的标志)
			 * @param bstr
			 * @param bz 部分通过字段对应标志位 5:供电方式,8:启用状态,9:标杆.....
			 * @return
			 */
			public synchronized String getFlbz(String bstr,String bz){
				bstr=null==bstr?"":bstr;
				String[] fl = bstr.split(",");
				StringBuffer sb = new StringBuffer();
				int fllength = fl.length;
				for (int i = 0; i < fllength; i++) {
					sb.append(bz.equals(fl[i])?"":(fl[i]+","));
				}
				return (",".equals(sb.toString()) || "".equals(sb.toString()))?"":sb.substring(0, sb.length()-1);
			}
			/**等?(是否bftg含有flgg的每一个字符)
			 * @param flgg
			 * @param bftg
			 * @return
			 */
			public synchronized boolean getShengbz(String flgg,String bftg){
				flgg=null==flgg?"":flgg;
				bftg=null==bftg?"":bftg;
				boolean flag = false;
				String[] fl = flgg.split(",");
				int length = fl.length;
				String[] f2 = bftg.split(",");
				int f2length = f2.length;
				int count = 0;
				for (int i = 0; i < f2length; i++) {
					if(flgg.contains(f2[i])){
						count++;
					}
				}
				flag=length==count?true:false;
				return flag;
			}
			
			/**判空
			 * @param checkstr
			 * @return
			 */
			public synchronized boolean checkKong(String checkstr){
				return ("".equals(checkstr) || null == checkstr)?true:false;
			}
			
}
