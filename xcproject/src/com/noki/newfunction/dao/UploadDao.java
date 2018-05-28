package com.noki.newfunction.dao;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.commons.fileupload.FileItem;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;
import com.noki.newfunction.javabean.Zdinfo;

public class UploadDao {
	private DataBase db;
	private Account account;
	private String id;
	private String fileName;

	public UploadDao(Account account, String id, String fileName) {
		db = new DataBase();
		this.account = account;
		this.id = id;
		this.fileName = fileName;
	}
     public UploadDao(Account account, String fileName) {
		// TODO Auto-generated constructor stub
    	 db = new DataBase();
 		this.account = account;
 		this.fileName = fileName;
	}
	/**
	 * 判断当前文件是否可以上传
	 * 
	 * @param errors
	 *            错误信息
	 * @param fileItem
	 *            当前文件
	 */
	public void isAvailableToUpload(List<String> errors, FileItem fileItem) {

		String fileName = fileItem.getName();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 上传的文件名
		Boolean flag = Boolean.FALSE;
		if (fileName.indexOf(".") > 0) {
			String type = fileName.substring(fileName.lastIndexOf(".")); // 文件类型
		} else
			flag = Boolean.TRUE;
		if (flag)
			errors.add("文件不能為空"); // 文件后缀不匹配
	}

	/**
	 * @author xuzehua
	 * @param errors  list<string>
	 * @param fileItem  FileItem
	 * @return
	 */
	public Boolean isAvailableToUpload1(List<String> errors, FileItem fileItem) {
		String fileName = fileItem.getName();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 上传的文件名
		Boolean flag = Boolean.FALSE;
		if (fileName.indexOf(".") > 0) {
			String type = fileName.substring(fileName.lastIndexOf(".")); // 文件类型
			if (!type.equals(".xls"))
				flag = Boolean.TRUE;
		} else {
			flag = Boolean.TRUE;
		}
		/*
		 * if (flag){ errors.add("上传的文件后缀必须为.xls"); // 文件后缀不匹配 }
		 */
		return flag;
	}

	/**
	 * 文件是否上传过
	 * 
	 * @return 上传过返回false否则为true
	 */
	public Boolean fileIsUploaded() {
		String sql = "select t.* from cbzdxx t where t.dsfj is not null and t.qxname is not null and t.wjid="
				+ this.id;
		System.out.println("文件是否上传过" + sql);
		try {
			ResultSet rs = db.queryAll(sql);
			if (rs.next())
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 将当前文件插入数据库
	 * 
	 * @param fileItem
	 * @throws Exception
	 */
	public void insertToDB(FileItem fileItem, List<Zdinfo> ls) throws Exception {
		String shicode = account.getShi();
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// 上传日期
		if (ls != null) {
			for (Zdinfo lss : ls) {
				String sql = "update cbzdxx t set t.dsfj=empty_blob(),t.qxname=? where t.wjid='"
						+ this.id + "'";
				System.out.println("上传插入初始化：" + sql);
				PreparedStatement ps = db.getPreparedStatement(sql);
				ps.setString(1, fileItem.getName());
				ps.executeUpdate();
				db.close();
				// 需要查出二进制字段，注意sql语句需要加 for update，然后才可以对二进制文件进行写入
				String sqlForInsertBlob = "select t.dsfj from cbzdxx t where t.wjid='"
						+ this.id + "' for update";
				System.out.println("上传插入：" + sqlForInsertBlob);
				InsertToBlob(sqlForInsertBlob, fileItem);
			}
		}
	}

	public void insertToDBB(FileItem fileItem, List<Zdinfo> ls)
			throws Exception {
		String shicode = account.getShi();
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// 上传日期
		if (ls != null) {
			for (Zdinfo lss : ls) {
				String sql = "update cbzdxx t set t.SJFJ=empty_blob(),t.SJNAME=? where t.wjid='"
						+ this.id + "'";
				System.out.println("上传插入初始化：" + sql);
				PreparedStatement ps = db.getPreparedStatement(sql);
				ps.setString(1, fileItem.getName());
				ps.executeUpdate();
				db.close();
				// 需要查出二进制字段，注意sql语句需要加 for update，然后才可以对二进制文件进行写入
				String sqlForInsertBlob = "select t.SJFJ from cbzdxx t where t.wjid='"
						+ this.id + "' for update";
				System.out.println("上传插入：" + sqlForInsertBlob);
				InsertToBlob(sqlForInsertBlob, fileItem);
			}
		}
	}

	/**
	 * 向数据库中插入二进制文件
	 * 
	 * @param 待查询的sql
	 * @throws Exception
	 */
	public void InsertToBlob(String sql, FileItem fileItem) throws Exception {
		Connection conn = db.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			BLOB tmp = (BLOB) ((OracleResultSet) rs).getBlob(1);
			InputStream is = fileItem.getInputStream();
			OutputStream os = tmp.getBinaryOutputStream();
			byte[] buffer = new byte[1024];
			int size = -1;
			while ((size = is.read(buffer)) != -1) {
				os.write(buffer, 0, size);
			}
			is.close();
			os.close();
			conn.commit();
			rs.close();
			ps.close();
		}
	}

	public void insertToDBGL(FileItem fileItem, String id) throws Exception {// 对标管理区县申请模板
		String shicode = account.getShi();
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// 上传日期
		String sql = "update qskz t set t.fjnr=empty_blob(),t.fjmc=? where t.id='"
				+ id + "'";
		System.out.println("上传插入初始化：" + sql);
		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setString(1, fileItem.getName());
		ps.executeUpdate();
		db.close();
		// 需要查出二进制字段，注意sql语句需要加 for update，然后才可以对二进制文件进行写入
		String sqlForInsertBlob = "select t.fjnr from qskz t where t.id='" + id
				+ "' for update";
		System.out.println("上传插入：" + sqlForInsertBlob);
		InsertToBlob(sqlForInsertBlob, fileItem);
	}

	public synchronized void UpdateFjnr(String Id) {//
		StringBuffer sql = new StringBuffer();
		sql.append("update qskz z set z.fjnr=null where z.id='" + id + "'");
		DataBase db = new DataBase();
		System.out.println("区县申请附件内容修改：" + sql.toString());
		try {
			db.connectDb();
			db.update(sql.toString());
			db.commit();
		} catch (DbException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close();
			} catch (Exception de) {
				de.printStackTrace();
			}
		}
	}
}
