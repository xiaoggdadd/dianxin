package com.noki.electricfees.servlet;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;
import com.noki.database.DataBase;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.mobi.common.CommonBean;
import com.noki.sysconfig.javabean.AutoAuditBean;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Insert {

  StringBuffer sql = new StringBuffer();
  String sqlnote = "";
  Vector wrongContent = new Vector();
  public DataBase db;
  public Insert() {
    try {
      db = new DataBase();
      db.connectDb();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void closeDb() {
    try {
      db.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public synchronized Vector getWrong(Vector content) {
    int m = 0, i = 0;
    for (Iterator it = content.iterator(); it.hasNext(); ) {
      m++;
      i = 0;
      Vector row = (Vector) it.next();
      Iterator iter = row.iterator();
      sql.setLength(0);
      sql.append(
          "INSERT INTO electricfees(AMMETERDEGREEID_FK,UNITPRICE,FLOATPAY,ACTUALPAY,NOTETYPEID," +
          "NOTENO,NOTETIME,KPTIME,PAYOPERATOR,PAYDATETIME,ACCOUNTMONTH,MEMO,AUTOAUDITSTATUS,AUTOAUDITDESCRIPTION) VALUES(");

      String con = "";
      //自动电费审核
      ElectricFeesFormBean formBean = new ElectricFeesFormBean();
      //自动电费审核
      while (iter.hasNext()) {

        i++;
        Object value = iter.next();
        if (value == null) {
          value = "";
        }
        con = value.toString();
        //System.out.println("8888888888"+con);
        //---查询票据类型--
        ArrayList feetypelist = new ArrayList();
        CommonBean commBean = new CommonBean();
     	feetypelist = commBean.getNoteType();
     	if(feetypelist!=null){
     		String sfid="",sfnm="";
     		int scount = ((Integer)feetypelist.get(0)).intValue();
     		for(int j=scount;j<feetypelist.size()-1;j+=scount)
            {
            	sfid=(String)feetypelist.get(j+feetypelist.indexOf("CODE"));
            	sfnm=(String)feetypelist.get(j+feetypelist.indexOf("NAME"));
         		//比较
            	if(con.equals(sfnm)){
                	con = sfid.toString();
                	//System.out.println("8888888888:"+con);
                }
            }
     		
           
     	}
     	//--查询票据类型--
        
     	System.out.println("第"+m+"条第"+i+"个con数据："+con.toString());
     	if(i == 1){
     		formBean.setAmmeterdegreeid(con);
     	}
     	if(i == 2){
     		formBean.setUnitprice(con);
     	}
     	if(i == 3){
     		formBean.setFloatpay(con);
     	}
     	if(i == 6){
     		formBean.setNoteno(con);
     	}
    	if(i == 12){
     		formBean.setMemo(con);
     	}
        if (i == 7||i == 8||i == 10) {
          sql.append("to_char(to_date('" + con + "','dd-mm-yyyy'),'yyyy-mm-dd'),");
        }else if(i == 11){
	          sql.append("to_char(to_date('" + con + "','yyyy-mm'),'yyyy-mm'),");
        }else{
          sql.append("'" + con + "',");
        }
       
      }
    //自动审核判断
      String sysprice = formBean.getJizhanPrice(formBean.getAmmeterdegreeid()).getSysprice();
      System.out.println("系统单价:"+sysprice);
      AutoAuditBean bean = new AutoAuditBean();
    	 ArrayList fylist = new ArrayList();
    	 fylist = bean.getPageData(1,"");
  	String ItemID = "",
  	ItemName = "",
  	ItemValue = "",
  	ItemDescription = "";
  	String autoauditstatus="1",
  	autoauditdescription="",
  	autoauditdescription1="",
  	autoauditdescription2="",
  	autoauditdescription3="";
  	String auditfee1="";
  	String auditfee2="";
  	String auditfee3="";
  	String floatpay = formBean.getFloatpay();
  	String memo = formBean.getMemo();
  	String af3_bz = "0";
  	String floatpay_bz = "";
  	String memo_bz = "";
  	if(floatpay==null||floatpay==""){
  		floatpay_bz = "0";
  	}else{
  		floatpay_bz = "1";
  	}
  	if(memo==null||memo==""){
  		memo_bz = "0";
  	}else{
  		memo_bz = "1";
  	}
  	if(memo_bz.equals(floatpay_bz)){
  		af3_bz = "1";
  	}
  	 if(fylist!=null)
  	{
  		int fycount = ((Integer)fylist.get(0)).intValue();
  		for( int k = fycount;k<fylist.size()-1;k+=fycount){
  	     
  	      ItemID = (String)fylist.get(k+fylist.indexOf("ITEMID"));
  	      ItemName = (String)fylist.get(k+fylist.indexOf("ITEMNAME"));
  	      ItemValue = (String)fylist.get(k+fylist.indexOf("ITEMVALUE"));
  		  ItemDescription = (String)fylist.get(k+fylist.indexOf("ITEMDESCRIPTION"));	
  		  if(ItemName.equals("AuditFee1")&&(!ItemValue.equals("0"))&&(formBean.getNoteno().equals(""))){
  			  System.out.println(ItemName+"*****"+ItemValue+"****"+formBean.getNoteno());
  			  autoauditstatus="0";
  			  autoauditdescription1="票据为空！";
  			  auditfee1 = "0";
  		  }
  		  
  		  if(ItemName.equals("AuditFee2")&&(!ItemValue.equals("0"))&&(!formBean.getUnitprice().equals(sysprice))){
  			  System.out.println(ItemName+"*****"+ItemValue+"****"+formBean.getUnitprice());
  			  autoauditstatus="0";
  			  autoauditdescription2="本次单价与系统单价不符！";
  			  auditfee2 = "0";
  		  }
            
  		  if(ItemName.equals("AuditFee3")&&(!ItemValue.equals("0"))&&af3_bz.equals("0")){
  			  System.out.println(ItemName+"*****"+ItemValue+"****"+formBean.getUnitprice());
  			  autoauditstatus="0";
  			  autoauditdescription3="用电费用调整没有说明！";
  			  auditfee3 = "0";
  		  }
            
  		}
  		if(auditfee1.equals("0")){
  		  autoauditdescription = autoauditdescription + autoauditdescription1 + ";" ;
  		}
  		if(auditfee2.equals("0")){
        	  autoauditdescription = autoauditdescription + autoauditdescription2 + ";" ; 
  		}
  		if(auditfee3.equals("0")){
        	  autoauditdescription = autoauditdescription + autoauditdescription3 + ";" ; 
  		}
  	}
  	 System.out.println("电费自动审核描述："+autoauditdescription);
      //自动审核判断
      
      
      String  sqlstr = sql.substring(0,sql.length()-1);
      sqlstr = sqlstr +",'"+autoauditstatus+"','"+autoauditdescription+"')";
      //sqlstr = sqlstr +")";
      //sql.append(")");
      try {
        System.out.println("第"+m+"条数据："+sqlstr.toString());
        int tt = db.update(sqlstr.toString());

        if (tt == 0) {
          row.add("有错误");
          wrongContent.add(row);
        }

      }
      catch (Exception e) {
        e.printStackTrace();
        row.add(e.toString());
        wrongContent.add(row);
      }

    }
    System.out.println("wrongContent:"+wrongContent);
    return wrongContent;
  }

}
