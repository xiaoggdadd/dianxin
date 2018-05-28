package com.noki.ammeterdegree.javabean;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.electricfees.javabean.ElectricFeesFormBean;
import com.noki.sysconfig.javabean.AutoAuditBean;
import com.noki.util.CTime;
import com.noki.util.MD5;

public class DaoFormBean  implements java.io.Serializable {
	
	 private String thisdegree="";
	 private String lastdegree="";
	 private String lastdatetime="";
	 private String thisdatetime="";
	 private String startmonth="";
	 private String endmonth=""; 
	 private String actualpay="";
	 private String floatdegree="";
	 private String floatpay="";
	 private String actualdegree="";
	 private String unitprice="";
	 private String accountmonth="";
	 private String scdl;
	 private String bgdl;
	 private String yydl;
	 private String xxhdl;
	 private String jstzdl;
	 private String scdf;
	 private String bgdf;
	 private String yydf;
	 private String xxhdf;
	 private String jstzdf;
	 private String ammeteridFk;
	 private String entrypensonnel;
	 private String entrytime;
	 private String dbname;
	 private String jzname;
	 private String dianfei;
	 private String dfzflx;
	 private String aman;
	 private String eman;
	 private String lasttime;
	 
	 
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getJzname() {
		return jzname;
	}
	public void setJzname(String jzname) {
		this.jzname = jzname;
	}
	public String getDianfei() {
		return dianfei;
	}
	public void setDianfei(String dianfei) {
		this.dianfei = dianfei;
	}
	public String getDfzflx() {
		return dfzflx;
	}
	public void setDfzflx(String dfzflx) {
		this.dfzflx = dfzflx;
	}
	public String getAman() {
		return aman;
	}
	public void setAman(String aman) {
		this.aman = aman;
	}
	public String getEman() {
		return eman;
	}
	public void setEman(String eman) {
		this.eman = eman;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}
	public String getScdf() {
		return scdf;
	}
	public void setScdf(String scdf) {
		this.scdf = scdf;
	}
	public String getBgdf() {
		return bgdf;
	}
	public void setBgdf(String bgdf) {
		this.bgdf = bgdf;
	}
	public String getYydf() {
		return yydf;
	}
	public void setYydf(String yydf) {
		this.yydf = yydf;
	}
	public String getXxhdf() {
		return xxhdf;
	}
	public void setXxhdf(String xxhdf) {
		this.xxhdf = xxhdf;
	}
	public String getJstzdf() {
		return jstzdf;
	}
	public void setJstzdf(String jstzdf) {
		this.jstzdf = jstzdf;
	}
	public String getAmmeteridFk() {
		return ammeteridFk;
	}
	public void setAmmeteridFk(String ammeteridFk) {
		this.ammeteridFk = ammeteridFk;
	}
	public String getEntrypensonnel() {
		return entrypensonnel;
	}
	public void setEntrypensonnel(String entrypensonnel) {
		this.entrypensonnel = entrypensonnel;
	}
	public String getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}
	public String getScdl() {
		return scdl;
	}
	public void setScdl(String scdl) {
		this.scdl = scdl;
	}
	public String getBgdl() {
		return bgdl;
	}
	public void setBgdl(String bgdl) {
		this.bgdl = bgdl;
	}
	public String getYydl() {
		return yydl;
	}
	public void setYydl(String yydl) {
		this.yydl = yydl;
	}
	public String getXxhdl() {
		return xxhdl;
	}
	public void setXxhdl(String xxhdl) {
		this.xxhdl = xxhdl;
	}
	public String getJstzdl() {
		return jstzdl;
	}
	public void setJstzdl(String jstzdl) {
		this.jstzdl = jstzdl;
	}
	public String getThisdegree() {
		return thisdegree;
	}
	public void setThisdegree(String thisdegree) {
		this.thisdegree = thisdegree;
	}
	public String getLastdegree() {
		return lastdegree;
	}
	public void setLastdegree(String lastdegree) {
		this.lastdegree = lastdegree;
	}
	public String getLastdatetime() {
		return lastdatetime;
	}
	public void setLastdatetime(String lastdatetime) {
		this.lastdatetime = lastdatetime;
	}
	public String getThisdatetime() {
		return thisdatetime;
	}
	public void setThisdatetime(String thisdatetime) {
		this.thisdatetime = thisdatetime;
	}
	public String getStartmonth() {
		return startmonth;
	}
	public void setStartmonth(String startmonth) {
		this.startmonth = startmonth;
	}
	public String getEndmonth() {
		return endmonth;
	}
	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
	public String getActualpay() {
		return actualpay;
	}
	public void setActualpay(String actualpay) {
		this.actualpay = actualpay;
	}
	public String getFloatdegree() {
		return floatdegree;
	}
	public void setFloatdegree(String floatdegree) {
		this.floatdegree = floatdegree;
	}
	public String getFloatpay() {
		return floatpay;
	}
	public void setFloatpay(String floatpay) {
		this.floatpay = floatpay;
	}
	public String getActualdegree() {
		return actualdegree;
	}
	public void setActualdegree(String actualdegree) {
		this.actualdegree = actualdegree;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getAccountmonth() {
		return accountmonth;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}
	public synchronized String addDegreeFees(DaoFormBean formBean,String amuuid,long uuid,List id) {
		//对上次电表读数lastdegree、本次电表读数thisdegree、本次抄表时间thisdatetime传入数据库 查询判断此电费单是否已经存在；
		System.out.println("=======2012====电费单添加=====");
	    String msg = "保存电费单失败！请重试或与管理员联系！";
	    int flag=1;
	    DataBase db = new DataBase();

	    String start=formBean.getStartmonth();
	    String end=formBean.getEndmonth();
	   // System.out.println(start+"我我我我我我"+end);
	    int startn=Integer.parseInt(start.substring(0,4));
	    int starty=Integer.parseInt(start.substring(5,7));
	    int endn=Integer.parseInt(end.substring(0,4));
	    int endy=Integer.parseInt(end.substring(5,7));
	    int time=(endn-startn)*12+endy-starty+1;
	    System.out.println("--"+startn+"**"+starty+"**"+time);
	    double yushu=Double.parseDouble(formBean.getActualdegree())%time;
	    int dianduPermonth = (int) ((Double.parseDouble(formBean.getActualdegree())-yushu)/time);
	    
	    double yu=Double.parseDouble(formBean.getActualdegree())%time;
	    int dian = (int) ((Double.parseDouble(formBean.getActualdegree())-yu)/time);
	    
	    String aa=formBean.getScdl();
	    String bb=formBean.getBgdl();
	    String cc=formBean.getYydl();
	    String dd=formBean.getXxhdl();
	    String ee=formBean.getJstzdl();
	    if("".equals(aa)||aa==null)aa="0";
	    if("".equals(bb)||bb==null)bb="0";
	    if("".equals(cc)||cc==null)cc="0";
	    if("".equals(dd)||dd==null)dd="0";
	    if("".equals(ee)||ee==null)ee="0";
	    //网络运营线耗电量（生产）电量分到每个月 NETWORKHDL
	    double scdlyushu=Double.parseDouble(aa)%time;
	    int scdl = (int) ((Double.parseDouble(aa)-scdlyushu)/time);
	    //行政综合线耗电量（办公）电量分到每个月   ADMINISTRATIVEHDL 
	    double bgdlyushu=Double.parseDouble(bb)%time;
	    int bgdl = (int) ((Double.parseDouble(bb)-bgdlyushu)/time);
	    //市场营销线耗电量(营业)电量分到每个月 MARKETHDL
	    double yydlyushu=Double.parseDouble(cc)%time;
	    int yydl = (int) ((Double.parseDouble(cc)-yydlyushu)/time);
	    //信息化电量分到每个月 INFORMATIZATIONHDL
	    double xxhdlyushu=Double.parseDouble(dd)%time;
	    int xxhdl = (int) ((Double.parseDouble(dd)-xxhdlyushu)/time);
	    //建设投资电量分到每个月  BUILDHDL
	    double jstzdlyushu=Double.parseDouble(ee)%time;
	    int jstzdl = (int) ((Double.parseDouble(ee)-jstzdlyushu)/time);
	    
	  	    String[] sqlBatch = new String[time];
	  	    String[] sqlBatch2 = new String[time];
	    	 List year_month = new ArrayList();
	    	 List diandu = new ArrayList();//倍率耗电量
	    	 List dianl = new ArrayList();//用电量
	    	 List scdlfentan = new ArrayList();
	    	 List bgdlfentan = new ArrayList();
	    	 List yydlfentan = new ArrayList();
	    	 List xxhdlfentan = new ArrayList();
	    	 List jstzdlfentan = new ArrayList();
	    	 
	         for(int i = 0;i<time;i++){
	         	String yue = String.valueOf(starty);
	         	if(yue.length()==1)yue = "0"+yue;
	         	String year_month_tmp = startn+"-"+yue;
	         	starty ++;
	         	if(starty>12){
	         		starty = 1;
	         		startn ++;
	         	}
	         	year_month.add(year_month_tmp);
	         	if(i==time-1){
	         		diandu.add(dianduPermonth+yushu);
	         		dianl.add(dian+yu);
	         		scdlfentan.add(scdl+scdlyushu);
	         		bgdlfentan.add(bgdl+bgdlyushu);
	         		yydlfentan.add(yydl+yydlyushu);
	         		xxhdlfentan.add(xxhdl+xxhdlyushu);
	         		jstzdlfentan.add(jstzdl+jstzdlyushu);
	         	}else {
	         		diandu.add(dianduPermonth);
	         		dianl.add(dian);
	         		scdlfentan.add(scdl);
	         		bgdlfentan.add(bgdl);
	         		yydlfentan.add(yydl);
	         		xxhdlfentan.add(xxhdl);
	         		jstzdlfentan.add(jstzdl);
	         		
	         	}
	         }
	         String sql12="";
	         ResultSet rs = null; 
	         for(int i = 0;i<time;i++){
	        	 StringBuffer sql = new StringBuffer();
	              sql12="update ammeterdegrees set AMMETERID_FK=(select id from dianbiao where dbid='"+formBean.getAmmeteridFk()+"') where AMMETERDEGREEID=(select AMMETERDEGREEID from ammeterdegrees where AMMETERID_FK='"+formBean.getAmmeteridFk()+"')";

	             sql.append("INSERT INTO AMMETERDEGREES(AMMETERID_FK,LASTDEGREE,THISDEGREE,LASTDATETIME,THISDATETIME," +
	             "FLOATDEGREE,BLHDL,NETWORKHDL,INFORMATIZATIONHDL,ADMINISTRATIVEHDL,MARKETHDL,BUILDHDL,AMUUID," +
	             "UUID,STARTMONTH,ENDMONTH,ENTRYPENSONNEL,ENTRYTIME,MANUALAUDITSTATUS,DLBZW) VALUES(");
		         sql.append("'" + id.get(0) + "','" + formBean.getLastdegree() + "','" 
		        		 + formBean.getThisdegree() + "','" + formBean.getLastdatetime() + "','" + formBean.getThisdatetime() + "','" +
		        		 formBean.getFloatdegree() + "','" + diandu.get(i) + "','" + scdlfentan.get(i) + "','"
		        		 +xxhdlfentan.get(i)+"','"+bgdlfentan.get(i)+"','"+yydlfentan.get(i)+"','"+jstzdlfentan.get(i)+"','"
		        		 +amuuid+"','"+uuid+"','" + year_month.get(i) + "','" + year_month.get(i) +
		        		 "','"+formBean.getEntrypensonnel()+"','"+formBean.getEntrytime()+ "','1','0')");
		         System.out.println("电量导入："+sql.toString());
		         sqlBatch[i] = sql.toString();
		        
	         }
	    
	    
	    try {
	    	
	    	
	      db.connectDb();
	      db.updateBatch(sqlBatch);
	      System.out.println("电量修改"+sql12.toString()); 
	     // db.updateBatch(sqlBatch2);
	     // System.out.println("电量修改"+sql12.toString()); 
	      //rs = db.queryAll(sql12);
      	
	      msg = "添加电量成功!";
	    }
	    catch (DbException de) {
	      try {
	        db.rollback();
	      }
	      catch (DbException dee) {
	        dee.printStackTrace();
	      }
	      de.printStackTrace();
	    }
	    finally {
	      try {

	        db.close();
	      }
	      catch (Exception de) {
	        de.printStackTrace();
	      }
	    }

	    return msg;
	  }
	public synchronized String addFeesDegree(DaoFormBean formBean,
			String start, String end, List ammeterdegreeid,long dfuuid) {
	
		String msg = "保存账户失败！请重试或与管理员联系！";
		MD5 md = new MD5();
		CTime ctime = new CTime();

		
		// 自动审核判断

		int startn = Integer.parseInt(start.substring(0, 4));
		int starty = Integer.parseInt(start.substring(5, 7));
		int endn = Integer.parseInt(end.substring(0, 4));
		int endy = Integer.parseInt(end.substring(5, 7));
		int time = (endn - startn) * 12 + endy - starty + 1;
		long uuid = System.currentTimeMillis();
		String df = formBean.getActualpay();
		double dfyu = Double.parseDouble(df)%time;
		int dfPermonth = (int) (Double.parseDouble(df) - dfyu) / time;
		
		    String aa=formBean.getScdf();
		    String bb=formBean.getBgdf();
		    String cc=formBean.getYydf();
		    String dd=formBean.getXxhdf();
		    String ee=formBean.getJstzdf();
		    if("".equals(aa)||aa==null)aa="0";
		    if("".equals(bb)||bb==null)bb="0";
		    if("".equals(cc)||cc==null)cc="0";
		    if("".equals(dd)||dd==null)dd="0";
		    if("".equals(ee)||ee==null)ee="0";
		
		//生产电费分到每个月
	    double scdffyushu=Double.parseDouble(aa)%time;
	    int scdff = (int) ((Double.parseDouble(aa)-scdffyushu)/time);
	    //办公电费分到每个月
	    double bgdfyushu=Double.parseDouble(bb)%time;
	    int bgdf = (int) ((Double.parseDouble(bb)-bgdfyushu)/time);
	    //营业电费分到每个月
	    double yydfyushu=Double.parseDouble(cc)%time;
	    int yydf = (int) ((Double.parseDouble(cc)-yydfyushu)/time);
	    //信息化电费分到每个月
	    double xxhdfyushu=Double.parseDouble(dd)%time;
	    int xxhdf = (int) ((Double.parseDouble(dd)-xxhdfyushu)/time);
	    //建设投资电费分到每个月
	    double jstzdfyushu=Double.parseDouble(ee)%time;
	    int jstzdf = (int) ((Double.parseDouble(ee)-jstzdfyushu)/time);
	    
		
		List dflist = new ArrayList();
		 List scdfffentan = new ArrayList();
    	 List bgdffentan = new ArrayList();
    	 List yydffentan = new ArrayList();
    	 List xxhdffentan = new ArrayList();
    	 List jstzdffentan = new ArrayList();

		String[] sqlBatch = new String[time];
		String[] sqlBatch2 = new String[time];
		for (int i = 0; i < time; i++) {
			if (i == time - 1){
				dflist.add(dfPermonth + dfyu);
				scdfffentan.add(scdff+scdffyushu);
         		bgdffentan.add(bgdf+bgdfyushu);
         		yydffentan.add(yydf+yydfyushu);
         		xxhdffentan.add(xxhdf+xxhdfyushu);
         		jstzdffentan.add(jstzdf+jstzdfyushu);
			}else{
				dflist.add(dfPermonth);
				scdfffentan.add(scdff);
         		bgdffentan.add(bgdf);
         		yydffentan.add(yydf);
         		xxhdffentan.add(xxhdf);
         		jstzdffentan.add(jstzdf);
			}
		}
		String update = "";
		StringBuffer idStr =new  StringBuffer();
		for (int i = 0; i < time; i++) {
			 idStr.append(ammeterdegreeid.get(i)+",");
			 
			 update="update ammeterdegrees set feesbz='1',manualauditstatus='1'  where ammeterdegreeid in("+idStr.toString().substring(0,idStr.length()-1)+")";

			StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO ELECTRICFEES(AMMETERDEGREEID_FK,UNITPRICE,FLOATPAY,ACTUALPAY," +
            		"NETWORKDF,INFORMATIZATIONDF,ADMINISTRATIVEDF,MARKETDF,BUILDDF," +
            		"DFUUID,ACCOUNTMONTH,ENTRYPENSONNEL,ENTRYTIME,MANUALAUDITSTATUS,CITYAUDIT,DFBZW) VALUES(");
			sql.append("'" + ammeterdegreeid.get(i) + "'");
			sql.append(",'" + formBean.getUnitprice() + "','"+ formBean.getFloatpay() + "','" + dflist.get(i) + "','"
					+ scdfffentan.get(i) + "','"+xxhdffentan.get(i)+"','"+bgdffentan.get(i)+"','"+yydffentan.get(i)+
					"','"+jstzdffentan.get(i)+"','"+dfuuid+"','"+formBean.getAccountmonth() + "','"
					+ formBean.getEntrypensonnel()+"','"+formBean.getEntrytime()+ "','1','1','0')");
			System.out.println("电费导入："+sql.toString());
			sqlBatch[i] = sql.toString();
		}
		DataBase db = new DataBase();
		try {
			db.connectDb();

			db.updateBatch(sqlBatch);
			db.update(update);
			msg = "添加电费成功!";
		} catch (DbException de) {
			try {
				db.rollback();
			} catch (DbException dee) {
				dee.printStackTrace();
			}
			de.printStackTrace();
		} finally {
			try {

				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}

		return msg;
	}
	 
}