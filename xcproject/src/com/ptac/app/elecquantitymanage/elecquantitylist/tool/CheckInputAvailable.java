package com.ptac.app.elecquantitymanage.elecquantitylist.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.noki.ammeterdegree.javabean.AmmeterDegreeBean;
import com.noki.ammeterdegree.javabean.AmmeterDegreeFormBean;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.ptac.app.electricmanage.electricitybill.tool.ElectricTool;
import com.ptac.app.electricmanageUtil.Format;

/**
 * @author WangYiXiao 2014-4-24
 *
 */
public class CheckInputAvailable {
	String dianbiaoid;//电表id
	String dbid;//电表表id
	
	String dbyt;//电表用途
	 
	String lastdegree;//上次电表读数
	String thisdegree;//本次电表读数
	String tzdegree;//用电量调整
	String zshdegree;//折算后用电量
	String lastdatetime;//上次抄表时间
	String thisdatetime;//本次抄表时间
	String startmonth;//起始月份
	String endmonth;//结束月份
	String inputdatetime;//抄表时间 
	String beilv,dbydl,scb,edhdl,zlfh,jlfh,property,shi,qsdbdl,stationtype;
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//年月判断
	//4个年    -或/  2个或1个月
	Pattern pattern2 = Pattern.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
	// 4个年   2个月或1个月    2个日或1个日   
	Pattern pattern4 = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{2}");
	//日期判断
	String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"    
        + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
        + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
        + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
        + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
        + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	Pattern pattern5 = Pattern.compile(datePattern2); 
	/**
	 * 电量 导入的 检查 excel的数据正确性检查和 电量导入重复性检查
	 */
	public CheckInputAvailable() {

	}

	/**
	 * @param content
	 *            单行数据
	 * @param account
	 *            用户
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		String accountname=account.getAccountName();
		Vector<String> row = new Vector<String>();
		// 必填项检查
		row = this.check01(content);

		if (row.isEmpty()) {
			row = this.check02(content);
		}
		if (row.isEmpty()) {
			row = this.checke(content);
		}
		if (row.isEmpty()) {
			row = this.checkq(content);
		}
		if (row.isEmpty()) {
			row = this.checkzl(content);
		}
		if (row.isEmpty()) {
			row = this.checkjl(content);
		}
		if (row.isEmpty()) {
			row = this.checkscb(content);
		}
		if (row.isEmpty()) {
			row = this.checkpro(content);
		}
		if (row.isEmpty()) {
			row = this.check03(content);
		}
		if (row.isEmpty()) {
			row = this.check04(content);
		}
		if (row.isEmpty()) {
			row = this.check05(content);
		}
		if (row.isEmpty()) {
			row = this.check06(content);
		}
		if (row.isEmpty()) {
			row = this.check07(content);
		}
		if (row.isEmpty()) {
			row = this.check08(content);
		}
		if (row.isEmpty()) {
			row = this.checkbs(content);
		}
		if (row.isEmpty()) {
			row = this.check09(content);
		}
		if (row.isEmpty()) {
			row = this.check10(content);
		}
		if (row.isEmpty()) {
			row = this.check11(content);
		}
		if (row.isEmpty()) {
			row = this.check12(content);
		}
		// 前期工作完成开始添加电费
		if (row.isEmpty()) {
			row = this.check13(content,accountname);
		}
		return row;
	}
	
	/**
	 * 电表id 检查
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-4-25
	 */
	synchronized Vector<String> check01(Object[] content){
		Vector<String> row = new Vector<String>();
		//------电表表中是否存在dbid为content[3].toString().trim()的数据------
		String sql = "SELECT ID FROM DIANBIAO WHERE DBID=?";
		DataBase ds = new DataBase();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, content[3].toString().trim());
			rs = ps.executeQuery();
			if(rs.next()){
				dbid = rs.getString("id");
			}
		 if(dbid == null || "".equals(dbid)){
			  System.out.println("未查到电表"+content[9].toString().trim());
			  for(int j=0;j<content.length;j++){
	       		   row.add(content[j].toString().trim());
	       	      }
			  dianbiaoid = content[3].toString().trim();
			  row.add("未查到"+content[0].toString()+content[2].toString()+"电表"+content[3].toString().trim());	  
		  }else{
			  String sqla = "SELECT DB.BEILV,ZD.ZLFH,ZD.JLFH,ZD.PROPERTY,ZD.EDHDL,ZD.SCB,ZD.SHI,ZD.QSDBDL,ZD.STATIONTYPE FROM DIANBIAO DB, ZHANDIAN ZD WHERE DB.ZDID = ZD.ID AND DB.DBID =?";
				ps = conn.prepareStatement(sqla);
				ps.setString(1, content[3].toString().trim());
				rs = ps.executeQuery();
				if(rs.next()){
					beilv = rs.getString("beilv");
					zlfh = rs.getString("zlfh");
					jlfh = rs.getString("jlfh");
					property = rs.getString("property");
					edhdl = rs.getString("edhdl");
					scb = rs.getString("scb");
					shi = rs.getString("shi");
					qsdbdl = rs.getString("qsdbdl");
					stationtype = rs.getString("stationtype");
				}
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ds.free(rs, ps, conn);
		}
		 dianbiaoid = content[3].toString().trim();
		return row;
	} 
	/**只能导入管理类电表的电量
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check02(Object[] content){
		Vector<String> row = new Vector<String>();
		String sql ="SELECT DBYT FROM DIANBIAO WHERE DBID=?";
		DataBase ds = new DataBase();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, content[3].toString().trim());
			rs = ps.executeQuery();
			if(rs.next()){
				dbyt = rs.getString("dbyt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ds.free(rs, ps, conn);
		}
		  if(!dbyt.equals("dbyt03")){
        	  for(int j=0;j<content.length;j++){
	       		   row.add(content[j].toString().trim());
	       	      }
			  row.add("只能导入管理类电表的电量");
		  }
		  return row;
	}
	/**
	 * 额定耗电量不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checke(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(edhdl)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中额定耗电量为空！");
		} 
		return row;
	}
	/**
	 * 额定耗电量不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkq(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(qsdbdl)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中全省定标电量为空！");
		} 
		return row;
	}
	/**
	 * 直流负荷不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkzl(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(zlfh)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中直流负荷为空！");
		} 
		return row;
	}
	/**
	 * 交流负荷不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkjl(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(jlfh)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中交流负荷为空！");
		} 
		return row;
	}
	/**
	 * 生产标不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkscb(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (Format.str_d(scb)==0) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中生产标为空！");
		} 
		return row;
	}
	/**
	 * 站点属性不为空
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkpro(Object[] content) {
		Vector<String> row = new Vector<String>();
		if ("".equals(property) || "null".equals(property) || null == property) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "系统中站点属性为空！");
		} 
		return row;
	}
	/**
	 * 上次电表读数不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check03(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[4] == null || "".equals(content[4].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "上次电表读数为空");
		} else {
			if (!Format.isNumber(content[4].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "上次电表读数格式不正确");
			} else {
				lastdegree = content[4].toString().trim();
			}
		}
		 return row;
	}
	/**
	 * 本次电表读数不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check04(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "本次电表读数为空");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "本次电表读数格式不正确");
			} else {
				thisdegree = content[5].toString().trim();
			}
		}
		 return row;
	}
	/**
	 * 用电量调整不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check05(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "用电量调整为空");
		} else {
			if (!Format.isNumber(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "用电量调整格式不正确");
			} else {
				tzdegree = Format.str2(content[8].toString().trim());
			}

		}
		 return row;
	}
	/**折算后用电量 不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check06(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[9] == null || "".equals(content[9].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "电表"
					+ dianbiaoid + "折算后用电量为空");
		} else {
			if (!Format.isNumber(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "折算后用电量格式不正确");
			} else {
				zshdegree = Format.str2(content[9].toString().trim());
			}

		}
		 return row;
	}
	/** 上次抄表时间不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check07(Object[] content){
		Vector<String> row = new Vector<String>();
		  if((content[6].toString().trim().contains("/")&&pattern4.matcher(content[6].toString().trim()).matches()==true)||content[6].toString().trim().contains("-")&&pattern5.matcher(content[6].toString().trim()).matches()==true&&isValidDate(content[6].toString().trim())==true){
			  if(content[6].toString().trim().contains("/")){
	          try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        	Date date =sdf.parse(content[6].toString().trim());
	        	 lastdatetime=sdf1.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			  }else{
				  lastdatetime=content[6].toString().trim();
			  }
	    }else{
	    	 for(int j=0;j<content.length;j++){
	       		   row.add(content[j].toString().trim());
	       	      }
			  row.add(content[0].toString()+content[2].toString()+"电表"+content[3].toString()+"上次抄表时间为空   或者 格式不正确");
	    }
		 return row;
	}
	/**本次抄表时间不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check08(Object[] content){
		Vector<String> row = new Vector<String>();
		 if((content[7].toString().trim().contains("/")&&pattern4.matcher(content[7].toString().trim()).matches()==true)||content[7].toString().trim().contains("-")&&pattern5.matcher(content[7].toString().trim()).matches()==true&&isValidDate(content[7].toString().trim())==true){
			  if(content[7].toString().trim().contains("/")){
		          try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date =sdf.parse(content[7].toString().trim());
		        	 thisdatetime=sdf1.format(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				  }else{
					  thisdatetime=content[7].toString().trim();
				  }
			  }else{
				  for(int j=0;j<content.length;j++){
		       		   row.add(content[j].toString().trim());
		       	      }
				  row.add(content[0].toString()+content[2].toString()+"电表"+content[3].toString()+"本次抄表时间为空   或者  格式不正确");
			  }
		 return row;
	}
	
	/**
	 * 上次抄表时间大于本次抄表时间
	 * 
	 * @param content
	 * @param row
	 * @return
	 */
	synchronized Vector<String> checkbs(Object[] content) {
		Vector<String> row = new Vector<String>();
		if (lastdatetime != null && !"".equals(lastdatetime.trim())
				&& thisdatetime != null
				&& !"".equals(thisdatetime.trim())
				&& Format.isTime(thisdatetime.trim())
				&& Format.isTime(lastdatetime.trim())) {
			Date lasttime = Format.String2Time(lastdatetime.trim());
			Date thistime = Format.String2Time(thisdatetime.trim());
			if (lasttime.getTime() > thistime.getTime()) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ content[3].toString() + "上次抄表时间大于本次抄表时间");
			}

		}
		return row;
	}
	/**起始年月不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check09(Object[] content){
		Vector<String> row = new Vector<String>();
		String sh=content[10].toString().trim();
		  if((pattern2.matcher(sh).matches()==true&&(sh.contains("/")&&sh.length()<=7))||(pattern2.matcher(sh).matches()==true&&(sh.contains("-")&&sh.length()<=7))){
	         if(sh.contains("/")){
			  try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	        	Date date =sdf.parse(sh);
	        	 startmonth=sdf1.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	         }else{
	        	 startmonth=sh;
			  } 
		  }else{
			  for(int j=0;j<content.length;j++){
	       		   row.add(content[j].toString().trim());
	       	      }
			  row.add(content[0].toString()+content[2].toString()+"电表"+content[3].toString()+"起始年月为空  或者  格式不正确");
		  }
		 return row;
	}
	/**结束月份不为空，格式
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check10(Object[] content){
		Vector<String> row = new Vector<String>();
		  String eh=content[11].toString().trim();
		  if((pattern2.matcher(eh).matches()==true&&(eh.contains("/")&&eh.length()<=7))||(pattern2.matcher(eh).matches()==true&&(eh.contains("-")&&eh.length()<=7))){
			  if(content[11].toString().trim().contains("/")){
			  try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
	        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	        	Date date =sdf.parse(content[11].toString().trim());
	        	 endmonth=sdf1.format(date);
	        	
			} catch (ParseException e) {
				e.printStackTrace();
			}
			  }else{
				 
				  endmonth=content[11].toString().trim();
			  } 
			  
		  }else{
			  for(int j=0;j<content.length;j++){
	       		   row.add(content[j].toString().trim());
	       	      }
			  row.add(content[0].toString()+content[2].toString()+"电表"+content[3].toString()+"结束年月为空  或者  格式不正确");
		  }
		 return row;
	}
	
	/**抄表时间
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check11(Object[] content){
		Vector<String> row = new Vector<String>();
		if(!content[13].toString().trim().equals("")){
			  if((content[13].toString().trim().contains("/")&&pattern5.matcher(content[13].toString().trim()).matches()==true)||(content[13].toString().trim().contains("-")&&pattern5.matcher(content[13].toString().trim()).matches()==true)){
			  if(content[13].toString().trim().contains("/")){
		          try {
		        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		        	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date date =sdf.parse(content[13].toString().trim());
		        	 inputdatetime=sdf1.format(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				  }else{
					  inputdatetime=content[13].toString().trim();
				  } 
			  }else{
				  for(int j=0;j<content.length;j++){
		       		   row.add(content[j].toString().trim());
		       	      }
				  row.add(content[0].toString()+content[2].toString()+"电表"+content[3].toString()+"抄表时间为空 或者 格式不正确");
			  }
			  }else{
				  inputdatetime=content[13].toString().trim();
			  }
		return row;
	}
	
	//判断导入的数据是否重复
	synchronized Vector<String> check12(Object[] content){
		Vector<String> row = new Vector<String>();
		String sql ="SELECT * FROM DIANDUVIEW T,DIANBIAO DB WHERE DB.DBID=T.AMMETERID_FK AND DB.DBID=? AND T.LASTDEGREE=? AND T.THISDEGREE=? AND TO_CHAR(T.LASTDATETIME,'YYYY-MM-DD')=? AND TO_CHAR(T.THISDATETIME,'YYYY-MM-DD')=?";    
		ArrayList<String> listzd=new ArrayList<String>() ;
		DataBase ds = new DataBase();
	    try {  	 
			conn = ds.getConnection();	
			ps = conn.prepareStatement(sql);
			ps.setString(1,content[3].toString().trim());
			ps.setString(2,content[4].toString().trim());
			ps.setString(3,content[5].toString().trim());
			ps.setString(4,lastdatetime);
			ps.setString(5,thisdatetime);
			rs = ps.executeQuery();
			 if(rs.next()){		
			  	  for(int j=0;j<content.length;j++){
			     		   row.add(content[j].toString().trim());
			  	  }
		    	  row.add("不能重复导入!");	    	  
		      }	
	    } catch (Exception e) {
			e.printStackTrace();
		 }finally{
			 ds.free(rs, ps, conn);
		 }
		 return row;
	}
	
	/**添加电量
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check13(Object[] content,String accountname) {
		Vector<String> row = new Vector<String>();
		  SimpleDateFormat mat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  AmmeterDegreeFormBean formBean = new AmmeterDegreeFormBean();
		  formBean.setAmmeteridFk(dianbiaoid);
		  formBean.setLastdegree(lastdegree);
	      formBean.setThisdegree(thisdegree);
	      formBean.setLastdatetime(lastdatetime);
	      formBean.setThisdatetime(thisdatetime);	      	    
	      formBean.setFloatdegree(tzdegree);
	      formBean.setActualdegree(zshdegree);
	      formBean.setBlhdl(zshdegree);	 
	      formBean.setStartmonth(startmonth);
	      formBean.setEndmonth(endmonth);
	      formBean.setInputoperator(content[12].toString().trim());
	      formBean.setInputdatetime(inputdatetime);
	      formBean.setMemo(content[14].toString().trim());
	      formBean.setEntrypensonnel(accountname);
	      formBean.setEntrytime(mat.format(new Date()));
	      
	      //2014-10-28
	      if(Format.str_d(beilv)==0){
	     	 beilv = "1";
	      }
	      String dbydl = String.valueOf((Format.str_d(formBean.getThisdegree())-Format.str_d(formBean.getLastdegree()))*Format.str_d(beilv));//电表用电量 = (本次电表读数-上次电表读数)*倍率
	      ElectricTool elecToo = new ElectricTool();
	      String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, formBean.getThisdatetime(), formBean.getLastdatetime(), shi, property, zlfh, jlfh, edhdl, scb, formBean.getAmmeteridFk(),zshdegree,qsdbdl,stationtype);//2014-10-22超省标比例,合并周期,标准值
	  	  String qsdbdlcbbl = cbbl[0];//全省定标电量超标比例
	      String hbzq = cbbl[1];//合并周期
	  	  String bzz = cbbl[2];//标准值
	  	  formBean.setBeilv(beilv);
	  	  formBean.setHbzq(hbzq);
	  	  formBean.setBzz(bzz);
	  	  formBean.setScbbl(qsdbdlcbbl);
	  	  formBean.setScb(scb);
	  	  formBean.setDbydl(dbydl);
		
		 AmmeterDegreeBean abean= new AmmeterDegreeBean(); 
	  	String bzw="0";//标志位
	   	String  rtmess = abean.addDegree(formBean,bzw);
		 if(!"添加电量成功！".equals(rtmess)){
	        	for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "电表"
						+ dianbiaoid + "Error！");
	        }
			return row;
	}
	
    //导入判断日期格式
	 public static boolean isValidDate(String sDate) {    
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";   
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"       
			+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|" 
			+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?" 
			+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?(" 
	         + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	         + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"; 
		if ((sDate != null)) { 
			Pattern pattern = Pattern.compile(datePattern1); 
			Matcher match = pattern.matcher(sDate); 
			if (match.matches()) { 
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate); 
				return match.matches();
				} else {  
					return false;   
					}    
			}    
		return false; 
	}
	
}

