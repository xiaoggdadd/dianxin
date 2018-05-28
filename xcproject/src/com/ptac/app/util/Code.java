package com.ptac.app.util;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.commons.lang.StringUtils;

import com.noki.database.DataBase;
import com.noki.database.DbException;

public class Code {

	public static final String DEFAULT_PREFIX_CODE = "JL3701";

	public String getNextVal(){
		DataBase db = new DataBase();
		ResultSet rs = null;
		String seq = "";
		try {
			db.connectDb();
			String sql = "select code_seq.nextval from dual";
			rs = db.queryAll(sql);
			while(rs.next()){
				seq = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return seq;
	}

	/**
	 * 电表编码
	 * @return
	 */
	public String getMeterNextVal(){
		DataBase db = new DataBase();
		ResultSet rs = null;
		String seq = "";
		try {
			db.connectDb();
			String sql = "select DBBM_SEQ.nextval from dual";
			rs = db.queryAll(sql);
			while(rs.next()){
				seq = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return seq;
	}

	/**
	 * 部门编码
	 * @return
	 */
	public String getDeptNextVal(){
		DataBase db = new DataBase();
		ResultSet rs = null;
		String seq = "";
		try {
			db.connectDb();
			String sql = "select DEPT_CUST_CODE_SEQ.nextval from dual";
			rs = db.queryAll(sql);
			while(rs.next()){
				seq = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (DbException e) {
				e.printStackTrace();
			}
		}
		return seq;
	}

	public String getEntityCode(String code) {
		if (code == null || code.isEmpty()) {
			code = StringUtils.rightPad("-", 14, "9");
		}else {
			code = StringUtils.leftPad(code, 8, "0");
		}
		return code;
	}

	public String getEntityCode(String prefix, String code) {
		if (code == null || code.isEmpty()) {
			code = StringUtils.rightPad("-", 8, "9");
		}else {
			String newCode = StringUtils.leftPad(code, 8, "0");
			code = prefix + newCode;
		}
		return code;
	}

	public String getEntityCode(String prefix, String code, int length) {
		if (code == null || code.isEmpty()) {
			code = StringUtils.rightPad("-", 8, "9");
		}else {
			String newCode = StringUtils.leftPad(code, length-prefix.length(), "0");
			code = prefix + newCode;
		}
		return code;
	}

	public static void main(String[] args) {
		//Code code = new Code();
		//System.out.println(code.getEntityCode("NHDB13707", "2", 14));
		
		  String olddeptcode = "13702033";
	        BigDecimal b = new BigDecimal(olddeptcode);
	        String deptcode = b.add( new BigDecimal(1)).toString();
	        System.out.println(deptcode);
	}

}
