package com.noki.zdqxkz.util;

import java.util.List;

public class ZdqxkzUtil {

			/** ��������ֶ�:���緽ʽ5,����״̬8,���9
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
			
			/**�ӱ�־
			 * @param bfstr �ֶ�
			 * @param bz ����ͨ���ֶζ�Ӧ��־λ 5:���緽ʽ,8:����״̬,9:���.....
			 * @return
			 */
			public synchronized String getBftg(String bfstr,String bz){
				bfstr=null==bfstr?"":bfstr;
				String str="";
				str=bfstr.contains(bz)?bfstr:("".equals(bfstr)?bz:(bfstr+","+bz));
				return str;
			}
			
			/** ��(ȥ�����еı�־)
			 * @param bstr
			 * @param bz ����ͨ���ֶζ�Ӧ��־λ 5:���緽ʽ,8:����״̬,9:���.....
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
			/**��?(�Ƿ�bftg����flgg��ÿһ���ַ�)
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
			
			/**�п�
			 * @param checkstr
			 * @return
			 */
			public synchronized boolean checkKong(String checkstr){
				return ("".equals(checkstr) || null == checkstr)?true:false;
			}
			
}
