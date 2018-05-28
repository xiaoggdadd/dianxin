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
import com.noki.mobi.common.Account;
import com.noki.newfunction.javabean.Zdinfo;

/**
 * @author GT
 * 部分判断
 */
public class UploadDaoQxfj {
	private DataBase db;
	private Account account;
	private String id;
	private String fileName;

	public UploadDaoQxfj(Account account, String id, String fileName) {
		db = new DataBase();
		this.account = account;
		this.id = id;
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
	public Boolean isAvailableToUpload(List<String> errors, FileItem fileItem) {

		String fileName = fileItem.getName();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // 上传的文件名
		Boolean flag = Boolean.FALSE;
		if (fileName.indexOf(".") > 0) {
			String type = fileName.substring(fileName.lastIndexOf(".")); // 文件类型
			if (!type.equals(".xls"))
				flag = Boolean.TRUE;
		} else{
			flag = Boolean.TRUE;
		}
		/*if (flag){
			errors.add("上传的文件后缀必须为.xls"); // 文件后缀不匹配
			}*/
		
		return flag;
	}
	/**
	 * 电费单导入 ：判断当前文件格式是否可以上传  非.XLS 无法导入
	 * 
	 * @param fileItem （备注参数重复传入）
	 *            当前文件
	 */
	public Boolean isUpload(String fileItem) {

		fileItem = fileItem.substring(fileItem.lastIndexOf("\\") + 1); // 上传的文件名
		Boolean flag = Boolean.FALSE;
		if (fileItem.indexOf(".") > 0) {
			String type = fileItem.substring(fileItem.lastIndexOf(".")); // 文件类型
			if (!type.equals(".xls"))
				flag = Boolean.TRUE;
		} else{
			flag = Boolean.TRUE;
		}
		/*if (flag){
			errors.add("上传的文件后缀必须为.xls"); // 文件后缀不匹配
			}*/
		
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
				// String sql =
				// "insert into cbzdxx(wjid,zdid,cbsj,dsfj,qxname) values(?,?,?,empty_blob(),?)";
				String sql = "update cbzdxx t set t.dsfj=empty_blob(),t.qxname=? where t.wjid='"
						+ this.id + "'";
				System.out.println("上传插入初始化：" + sql);
				System.out.println("-----------------------              qxname:"+fileItem.getName());
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
				// String sql =
				// "insert into cbzdxx(wjid,zdid,cbsj,dsfj,qxname) values(?,?,?,empty_blob(),?)";
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
	//区县整改附件上传到数据库中
	public void insertToDBZG(FileItem fileItem, List<Zdinfo> ls) throws Exception {
		String shicode = account.getShi();

		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// 上传日期
		if (ls != null) {
			for (Zdinfo lss : ls) {
				// String sql =
				// "insert into cbzdxx(wjid,zdid,cbsj,dsfj,qxname) values(?,?,?,empty_blob(),?)";
				String sql = "update cbzdxf t set t.zgfj=empty_blob(),t.qxname=? where t.bwjid='"
						+ this.id + "'";
				System.out.println("上传插入初始化：" + sql);
				PreparedStatement ps = db.getPreparedStatement(sql);
				ps.setString(1, fileItem.getName());
				ps.executeUpdate();
				db.close();

				// 需要查出二进制字段，注意sql语句需要加 for update，然后才可以对二进制文件进行写入
				String sqlForInsertBlob = "select t.zgfj from cbzdxf t where t.bwjid='"
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
	
}
