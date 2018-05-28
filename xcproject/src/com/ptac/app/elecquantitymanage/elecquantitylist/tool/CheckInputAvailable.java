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
	String dianbiaoid;//���id
	String dbid;//����id
	
	String dbyt;//�����;
	 
	String lastdegree;//�ϴε�����
	String thisdegree;//���ε�����
	String tzdegree;//�õ�������
	String zshdegree;//������õ���
	String lastdatetime;//�ϴγ���ʱ��
	String thisdatetime;//���γ���ʱ��
	String startmonth;//��ʼ�·�
	String endmonth;//�����·�
	String inputdatetime;//����ʱ�� 
	String beilv,dbydl,scb,edhdl,zlfh,jlfh,property,shi,qsdbdl,stationtype;
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//�����ж�
	//4����    -��/  2����1����
	Pattern pattern2 = Pattern.compile("[0-9]{4}-[0-9]{2}|[0-9]{4}/[0-9]{2}|[0-9]{4}-[0-9]{1}|[0-9]{4}/[0-9]{1}");
	// 4����   2���»�1����    2���ջ�1����   
	Pattern pattern4 = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{2}");
	//�����ж�
	String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"    
        + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
        + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
        + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
        + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
        + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	Pattern pattern5 = Pattern.compile(datePattern2); 
	/**
	 * ���� ����� ��� excel��������ȷ�Լ��� ���������ظ��Լ��
	 */
	public CheckInputAvailable() {

	}

	/**
	 * @param content
	 *            ��������
	 * @param account
	 *            �û�
	 * @return
	 */
	public synchronized Vector<String> inputCheck(Object[] content,
			Account account) {
		String accountname=account.getAccountName();
		Vector<String> row = new Vector<String>();
		// ��������
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
		// ǰ�ڹ�����ɿ�ʼ��ӵ��
		if (row.isEmpty()) {
			row = this.check13(content,accountname);
		}
		return row;
	}
	
	/**
	 * ���id ���
	 * @param content
	 * @return
	 * @author WangYiXiao 2014-4-25
	 */
	synchronized Vector<String> check01(Object[] content){
		Vector<String> row = new Vector<String>();
		//------�������Ƿ����dbidΪcontent[3].toString().trim()������------
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
			  System.out.println("δ�鵽���"+content[9].toString().trim());
			  for(int j=0;j<content.length;j++){
	       		   row.add(content[j].toString().trim());
	       	      }
			  dianbiaoid = content[3].toString().trim();
			  row.add("δ�鵽"+content[0].toString()+content[2].toString()+"���"+content[3].toString().trim());	  
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
	/**ֻ�ܵ����������ĵ���
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
			  row.add("ֻ�ܵ����������ĵ���");
		  }
		  return row;
	}
	/**
	 * ��ĵ�����Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ�ж�ĵ���Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * ��ĵ�����Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��ȫʡ�������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * ֱ�����ɲ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��ֱ������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * �������ɲ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ�н�������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * �����겻Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * վ�����Բ�Ϊ��
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
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "ϵͳ��վ������Ϊ�գ�");
		} 
		return row;
	}
	/**
	 * �ϴε�������Ϊ�գ���ʽ
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check03(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[4] == null || "".equals(content[4].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�ϴε�����Ϊ��");
		} else {
			if (!Format.isNumber(content[4].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�ϴε�������ʽ����ȷ");
			} else {
				lastdegree = content[4].toString().trim();
			}
		}
		 return row;
	}
	/**
	 * ���ε�������Ϊ�գ���ʽ
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check04(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[5] == null || "".equals(content[5].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "���ε�����Ϊ��");
		} else {
			if (!Format.isNumber(content[5].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "���ε�������ʽ����ȷ");
			} else {
				thisdegree = content[5].toString().trim();
			}
		}
		 return row;
	}
	/**
	 * �õ���������Ϊ�գ���ʽ
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check05(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[8] == null || "".equals(content[8].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "�õ�������Ϊ��");
		} else {
			if (!Format.isNumber(content[8].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "�õ���������ʽ����ȷ");
			} else {
				tzdegree = Format.str2(content[8].toString().trim());
			}

		}
		 return row;
	}
	/**������õ��� ��Ϊ�գ���ʽ
	 * @param content
	 * @return
	 */
	synchronized Vector<String> check06(Object[] content){
		Vector<String> row = new Vector<String>();
		if (content[9] == null || "".equals(content[9].toString().trim())) {
			for (int j = 0; j < content.length; j++) {
				row.add(content[j].toString().trim());
			}
			row.add(content[0].toString() + content[2].toString() + "���"
					+ dianbiaoid + "������õ���Ϊ��");
		} else {
			if (!Format.isNumber(content[9].toString().trim())) {
				for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "������õ�����ʽ����ȷ");
			} else {
				zshdegree = Format.str2(content[9].toString().trim());
			}

		}
		 return row;
	}
	/** �ϴγ���ʱ�䲻Ϊ�գ���ʽ
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
			  row.add(content[0].toString()+content[2].toString()+"���"+content[3].toString()+"�ϴγ���ʱ��Ϊ��   ���� ��ʽ����ȷ");
	    }
		 return row;
	}
	/**���γ���ʱ�䲻Ϊ�գ���ʽ
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
				  row.add(content[0].toString()+content[2].toString()+"���"+content[3].toString()+"���γ���ʱ��Ϊ��   ����  ��ʽ����ȷ");
			  }
		 return row;
	}
	
	/**
	 * �ϴγ���ʱ����ڱ��γ���ʱ��
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
				row.add(content[0].toString() + content[2].toString() + "���"
						+ content[3].toString() + "�ϴγ���ʱ����ڱ��γ���ʱ��");
			}

		}
		return row;
	}
	/**��ʼ���²�Ϊ�գ���ʽ
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
			  row.add(content[0].toString()+content[2].toString()+"���"+content[3].toString()+"��ʼ����Ϊ��  ����  ��ʽ����ȷ");
		  }
		 return row;
	}
	/**�����·ݲ�Ϊ�գ���ʽ
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
			  row.add(content[0].toString()+content[2].toString()+"���"+content[3].toString()+"��������Ϊ��  ����  ��ʽ����ȷ");
		  }
		 return row;
	}
	
	/**����ʱ��
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
				  row.add(content[0].toString()+content[2].toString()+"���"+content[3].toString()+"����ʱ��Ϊ�� ���� ��ʽ����ȷ");
			  }
			  }else{
				  inputdatetime=content[13].toString().trim();
			  }
		return row;
	}
	
	//�жϵ���������Ƿ��ظ�
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
		    	  row.add("�����ظ�����!");	    	  
		      }	
	    } catch (Exception e) {
			e.printStackTrace();
		 }finally{
			 ds.free(rs, ps, conn);
		 }
		 return row;
	}
	
	/**��ӵ���
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
	      String dbydl = String.valueOf((Format.str_d(formBean.getThisdegree())-Format.str_d(formBean.getLastdegree()))*Format.str_d(beilv));//����õ��� = (���ε�����-�ϴε�����)*����
	      ElectricTool elecToo = new ElectricTool();
	      String[] cbbl = elecToo.getQsdbdlCbbl(dbydl, formBean.getThisdatetime(), formBean.getLastdatetime(), shi, property, zlfh, jlfh, edhdl, scb, formBean.getAmmeteridFk(),zshdegree,qsdbdl,stationtype);//2014-10-22��ʡ�����,�ϲ�����,��׼ֵ
	  	  String qsdbdlcbbl = cbbl[0];//ȫʡ��������������
	      String hbzq = cbbl[1];//�ϲ�����
	  	  String bzz = cbbl[2];//��׼ֵ
	  	  formBean.setBeilv(beilv);
	  	  formBean.setHbzq(hbzq);
	  	  formBean.setBzz(bzz);
	  	  formBean.setScbbl(qsdbdlcbbl);
	  	  formBean.setScb(scb);
	  	  formBean.setDbydl(dbydl);
		
		 AmmeterDegreeBean abean= new AmmeterDegreeBean(); 
	  	String bzw="0";//��־λ
	   	String  rtmess = abean.addDegree(formBean,bzw);
		 if(!"��ӵ����ɹ���".equals(rtmess)){
	        	for (int j = 0; j < content.length; j++) {
					row.add(content[j].toString().trim());
				}
				row.add(content[0].toString() + content[2].toString() + "���"
						+ dianbiaoid + "Error��");
	        }
			return row;
	}
	
    //�����ж����ڸ�ʽ
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

