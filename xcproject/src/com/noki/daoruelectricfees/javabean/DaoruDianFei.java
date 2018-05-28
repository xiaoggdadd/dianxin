package com.noki.daoruelectricfees.javabean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.noki.util.CTime;
import com.noki.util.MD5;

public class DaoruDianFei {
	/**
	 * 添加电费电量 以下是要添加的信息1111
	 */
	public synchronized String addFeesDegree(ElectricFeesFormBean formBean,
			String start, String end, List ammeterdegreeid,String bzw,ArrayList fylist) {
		// birthday = birthday.length()>0?birthday:null;
		String msg = "保存账户失败！请重试或与管理员联系！";
		MD5 md = new MD5();
		CTime ctime = new CTime();

		// 自动审核判断
		// 电费电量入录
//		String sysprice = formBean.getJizhanPriceAm(formBean.getAmmererid());
//
//		System.out.println("系统单价:" + sysprice);
//		AutoAuditBean bean = new AutoAuditBean();
//		ArrayList fylist = new ArrayList();
//		fylist = bean.getPageData(1, "");
		String ItemID = "", ItemName = "", ItemValue = "", ItemDescription = "";
		String autoauditstatus = "1", autoauditdescription = "", autoauditdescription1 = "", autoauditdescription2 = "", autoauditdescription3 = "";
		String auditfee1 = "";
		String auditfee2 = "";
		String auditfee3 = "";
		String floatpay = formBean.getFloatpay();
		String memo = formBean.getMemo();
		String af3_bz = "0";
		String floatpay_bz = "";
		String memo_bz = "";
		//判断电量表自动审核
		String ss=formBean.getAutoauditstatus();
		//System.out.println("sssssssssssssssssssssssss"+ss);
		if(ss!=null){
		    if(ss.equals("0")){
			autoauditstatus = "0";
		}
		}
		/*if (floatpay == null || floatpay == "" || floatpay.equals("0")|| floatpay.equals("0.0")|| floatpay.equals("0.00")) {
			floatpay_bz = "0";
		} else {
			floatpay_bz = "1";
		}
		if (memo == null || memo == "") {
			memo_bz = "0";
		} else {
			memo_bz = "1";
		}
		if (memo_bz.equals(floatpay_bz)) {
			af3_bz = "1";
		}*/
		double floatt=0.0;
		if( null!= floatpay && !floatpay.equals("")&& !floatpay.equals(" ")){
			floatt=Double.parseDouble(floatpay);
		}
		else{
			floatpay_bz ="0";
		}
		if(floatt==0.0){
			floatpay_bz ="0";
		}else{
			floatpay_bz ="1";
		}
		if( null!= memo && !memo.equals("")&& !memo.equals(" ")){
			memo_bz = "1";
		}
		else{
			memo_bz = "0";
		}
		if (memo_bz.equals(floatpay_bz)||(floatpay_bz.equals("0")&&memo_bz .equals("1"))) {
			af3_bz = "1";
		}
		if (fylist != null) {
			int fycount = ((Integer) fylist.get(0)).intValue();
			for (int k = fycount; k < fylist.size() - 1; k += fycount) {

				ItemID = (String) fylist.get(k + fylist.indexOf("ITEMID"));
				ItemName = (String) fylist.get(k + fylist.indexOf("ITEMNAME"));
				ItemValue = (String) fylist
						.get(k + fylist.indexOf("ITEMVALUE"));
				ItemDescription = (String) fylist.get(k
						+ fylist.indexOf("ITEMDESCRIPTION"));
				if (ItemName.equals("AuditFee1") && (!ItemValue.equals("0"))
						&& (formBean.getNoteno().equals(""))) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getNoteno());
					autoauditstatus = "0";
					autoauditdescription1 = "票据为空！";
					auditfee1 = "0";
				}

//				if (ItemName.equals("AuditFee2") && (!ItemValue.equals("0"))
//						&& (!formBean.getUnitprice().equals(sysprice))) {
//					System.out.println(ItemName + "*****" + ItemValue + "****"
//							+ formBean.getUnitprice());
//					autoauditstatus = "0";
//					autoauditdescription2 = "本次单价与系统单价不符！";
//					auditfee2 = "0";
//				}

				if (ItemName.equals("AuditFee3") && (!ItemValue.equals("0"))
						&& af3_bz.equals("0")) {
					System.out.println(ItemName + "*****" + ItemValue + "****"
							+ formBean.getUnitprice());
					autoauditstatus = "0";
					autoauditdescription3 = "用电费用调整没有说明！";
					auditfee3 = "0";
				}

			}
			if (auditfee1.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription1 + ";";
			}
//			if (auditfee2.equals("0")) {
//				autoauditdescription = autoauditdescription
//						+ autoauditdescription2 + ";";
//			}
			if (auditfee3.equals("0")) {
				autoauditdescription = autoauditdescription
						+ autoauditdescription3 + ";";
			}
		}
		System.out.println("电费自动审核描述：" + autoauditdescription);
		// 自动审核判断

	//	int startn = Integer.parseInt(start.substring(0, 4));
	//	int starty = Integer.parseInt(start.substring(5, 7));
	//	int endn = Integer.parseInt(end.substring(0, 4));
		//int endy = Integer.parseInt(end.substring(5, 7));
	//	int time = (endn - startn) * 12 + endy - starty + 1;
		
//		long uuid = System.currentTimeMillis();		
		String uuid =formBean.getDfuuid();
		
		String df = formBean.getActualpay();
		//double dfyu = Double.parseDouble(df)%time;
	//	int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		
		    String aa=formBean.getScdff();
		    String bb=formBean.getBgdf();
		    String cc=formBean.getYydf();
		    String dd=formBean.getXxhdf();
		    String ee=formBean.getJstzdf();
		    String ff=formBean.getDddfdf();//代垫电费
		    if("".equals(aa)||aa==null)aa="0";
		    if("".equals(bb)||bb==null)bb="0";
		    if("".equals(cc)||cc==null)cc="0";
		    if("".equals(dd)||dd==null)dd="0";
		    if("".equals(ee)||ee==null)ee="0";
		    if("".equals(ff)||ff==null)ff="0";//代垫电费
//		
//		//生产电费分到每个月
//		    DecimalFormat dl=new DecimalFormat("0.00");
//		    double sf=0;
//	    double scdffyushu=Double.parseDouble(aa)%time;
//	    int scdff = (int) ((Double.parseDouble(aa)-scdffyushu)/time);
//	    
//	    //办公电费分到每个月
//	    double bgdfyushu=Double.parseDouble(bb)%time;
//	    int bgdf = (int) ((Double.parseDouble(bb)-bgdfyushu)/time);
//	    //营业电费分到每个月
//	    double yydfyushu=Double.parseDouble(cc)%time;
//	    int yydf = (int) ((Double.parseDouble(cc)-yydfyushu)/time);
//	    //信息化电费分到每个月
//	    double xxhdfyushu=Double.parseDouble(dd)%time;
//	    int xxhdf = (int) ((Double.parseDouble(dd)-xxhdfyushu)/time);
//	    //建设投资电费分到每个月
//	    double jstzdfyushu=Double.parseDouble(ee)%time;
//	    int jstzdf = (int) ((Double.parseDouble(ee)-jstzdfyushu)/time);
//	    //代垫电费分到每个月
//	    double dddfdfyushu=Double.parseDouble(ff)%time;
//	    int dddfdf = (int) ((Double.parseDouble(ff)-dddfdfyushu)/time);
//	    
//		
//		List dflist = new ArrayList();
//		 List scdfffentan = new ArrayList();
//    	 List bgdffentan = new ArrayList();
//    	 List yydffentan = new ArrayList();
//    	 List xxhdffentan = new ArrayList();
//    	 List jstzdffentan = new ArrayList();
//    	 List dddfdffentan = new ArrayList();//代垫电费

		//String[] sqlBatch = new String[time];
//		for (int i = 0; i < time; i++) {
//			if (i == time - 1){
//				double d=dfPermonth + dfyu;
//				String s=dl.format(d);
//				d=Double.parseDouble(s);
//				dflist.add(d);
//				double d1=scdff+scdffyushu;
//				String s1=dl.format(d1);
//				d1=Double.parseDouble(s1);
//				scdfffentan.add(d1);
//				double d2=bgdf+bgdfyushu;
//				String s2=dl.format(d2);
//				d2=Double.parseDouble(s2);
//         		bgdffentan.add(d2);
//         		
//         		double d3=yydf+yydfyushu;
//				String s3=dl.format(d3);
//				d3=Double.parseDouble(s3);
//         		yydffentan.add(d3);
//         		
//         		double d4=xxhdf+xxhdfyushu;
//				String s4=dl.format(d4);
//				d4=Double.parseDouble(s4);
//         		xxhdffentan.add(d4);
//         		double d5=jstzdf+jstzdfyushu;
//				String s5=dl.format(d5);
//				d5=Double.parseDouble(s5);
//         		jstzdffentan.add(d5);
//         		
//         		double d6=dddfdf+dddfdfyushu;//代垫电费
//				String s6=dl.format(d6);
//				d6=Double.parseDouble(s6);
//         		dddfdffentan.add(d6);
//			}else{
//				double ddd=dfPermonth;
//				String ss=dl.format(ddd);
//				ddd=Double.parseDouble(ss);
//				dflist.add(ddd);
//				double dp=scdff;
//				String sss=dl.format(dp);
//				dp=Double.parseDouble(sss);
//				scdfffentan.add(dp);
//				
//				double dp1=bgdf;
//				String sss1=dl.format(dp1);
//				dp1=Double.parseDouble(sss1);
//         		bgdffentan.add(dp1);
//         		double dp2=yydf;
//				String sss2=dl.format(dp2);
//				dp2=Double.parseDouble(sss2);
//         		yydffentan.add(dp2);
//         		
//         		double dp3=xxhdf;
//				String sss3=dl.format(dp3);
//				dp3=Double.parseDouble(sss3);
//         		xxhdffentan.add(dp3);
//         		
//         		double dp4=jstzdf;
//				String sss4=dl.format(dp4);
//				dp4=Double.parseDouble(sss4);
//         		jstzdffentan.add(dp4);
//         		
//         		double dp5=dddfdf;
//				String sss5=dl.format(dp5);
//				dp5=Double.parseDouble(sss5);
//         		dddfdffentan.add(dp5);
//			}
//		}
		//String update = "";
		//StringBuffer idStr =new  StringBuffer();
		DataBase db = new DataBase();
		String s="to_date(to_char(sysdate, 'yyyy-mm-dd hh24:MI:SS'),'yyyy-mm-dd hh24:MI:SS')";
		String sqlx="INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,PJJE,FLOATPAY," +
				"ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR," +
				"PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS," +
				"manualauditstatus,AUTOAUDITDESCRIPTION,NOTETIME,DFUUID," +
				"cityaudit,entrypensonnel,entrytime,NETWORKDF," +
				"ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DFBZW,DDDF,LIUCHENGHAO) VALUES("+
		"'"+ammeterdegreeid.get(0)+"','"+formBean.getUnitprice()+"','"+formBean.getPjje()+"','"+formBean.getFloatpay()+"'," +
		"'"+formBean.getActualpay()+"','"+formBean.getNotetypeid()+"','"+formBean.getNoteno()+"','"+formBean.getPayoperator()+"'," +
	    "to_date('"+formBean.getPaydatetime()+"','yyyy-mm-dd'),to_date('"+formBean.getAccountmonth()+"','yyyy-mm'),'"+formBean.getMemo()+"','"+autoauditstatus+"'," +
	    "'0','"+autoauditdescription+"',to_date('"+formBean.getNotetime()+"','yyyy-mm-dd'),'"+uuid+"'," +
	    "'"+formBean.getFlag()+"','"+formBean.getEntrypensonnel()+"',"+s+",'"+Double.parseDouble(aa)+"'," +
	    "'"+Double.parseDouble(bb)+"','"+Double.parseDouble(cc)+"','"+Double.parseDouble(dd)+"','"+Double.parseDouble(ee)+"','"+bzw+"','"+Double.parseDouble(ff)+"','')";
		
		
		
		
		
		//for (int i = 0; i < time; i++) {
		///	System.out.println("电费："+dflist.get(i)+"生产："+scdfffentan.get(i)+"办公："+bgdffentan.get(i)+"营业:"+yydffentan.get(i)+"信息化："+xxhdffentan.get(i)+"建设："+jstzdffentan.get(i)+"代垫电费："+dddfdffentan.get(i));
			// idStr.append(ammeterdegreeid.get(i)+",");
			//update="update ammeterdegrees set feesbz='1',manualauditstatus='1'  where ammeterdegreeid in("+idStr.toString().substring(0,idStr.length()-1)+")";
			//StringBuffer sql = new StringBuffer();
			//sql.append("INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,PJJE,FLOATPAY,ACTUALPAY,NOTETYPEID,NOTENO,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS,manualauditstatus,AUTOAUDITDESCRIPTION,NOTETIME,DFUUID,cityaudit,entrypensonnel,entrytime,NETWORKDF,ADMINISTRATIVEDF,MARKETDF,INFORMATIZATIONDF,BUILDDF,DFBZW,DDDF)");
			////sql.append(" VALUES('" + ammeterdegreeid.get(i) + "'");
			//sql.append(",'" + formBean.getUnitprice() + "','"+formBean.getPjje()+"','"
				//	+ formBean.getFloatpay() + "','" + dflist.get(i) + "','"
				//	+ formBean.getNotetypeid() + "','" + formBean.getNoteno()
				//	+ "'");
			//sql.append(",'" + formBean.getPayoperator() + "','"
				//	+ formBean.getPaydatetime() + "','"
//					+ formBean.getAccountmonth() + "'" + ",'"
//					+ formBean.getMemo() + "','" + autoauditstatus + "','0','"
//					+ autoauditdescription + "','" + formBean.getNotetime()
//					+"','" + uuid + "','"+formBean.getFlag()+"','"
//					+formBean.getEntrypensonnel()+"','"+formBean.getEntrytime()+"','"+scdfffentan.get(i)+"','"+bgdffentan.get(i)+"','"+yydffentan.get(i)+"','"+
//					xxhdffentan.get(i)+"','"+jstzdffentan.get(i)+"','"+bzw+"','"+dddfdffentan.get(i)+"') ");
	System.out.println("导入电费单："+sqlx.toString());
//			//sqlBatch[i] = sql.toString();
			  try {
					db.connectDb();
					db.update(sqlx.toString());
					msg = "添加电费成功!";
				} catch (DbException e) {
					try{
						db.rollback();
					}catch (Exception dee) {
						// TODO: handle exception
						dee.printStackTrace();
					}
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try{
						db.close();
					}catch(Exception a){
						a.printStackTrace();
					}
				}
			
		// }
		
//		try {
//			db.connectDb();
//			db.updateBatch(sqlBatch);
//			//db.update(update);
//			msg = "添加电费成功!";
//		} catch (DbException de) {
//			try {
//				db.rollback();
//			} catch (DbException dee) {
//				dee.printStackTrace();
//			}
//			de.printStackTrace();
//		} finally {
//			try {
//				db.close();
//			} catch (Exception de) {
//				de.printStackTrace();
//			}
//		}
		
		return msg;
	}
}
