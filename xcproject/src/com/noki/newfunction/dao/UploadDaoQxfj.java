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
 * �����ж�
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
	 * �жϵ�ǰ�ļ��Ƿ�����ϴ�
	 * 
	 * @param errors
	 *            ������Ϣ
	 * @param fileItem
	 *            ��ǰ�ļ�
	 */
	public Boolean isAvailableToUpload(List<String> errors, FileItem fileItem) {

		String fileName = fileItem.getName();
		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1); // �ϴ����ļ���
		Boolean flag = Boolean.FALSE;
		if (fileName.indexOf(".") > 0) {
			String type = fileName.substring(fileName.lastIndexOf(".")); // �ļ�����
			if (!type.equals(".xls"))
				flag = Boolean.TRUE;
		} else{
			flag = Boolean.TRUE;
		}
		/*if (flag){
			errors.add("�ϴ����ļ���׺����Ϊ.xls"); // �ļ���׺��ƥ��
			}*/
		
		return flag;
	}
	/**
	 * ��ѵ����� ���жϵ�ǰ�ļ���ʽ�Ƿ�����ϴ�  ��.XLS �޷�����
	 * 
	 * @param fileItem ����ע�����ظ����룩
	 *            ��ǰ�ļ�
	 */
	public Boolean isUpload(String fileItem) {

		fileItem = fileItem.substring(fileItem.lastIndexOf("\\") + 1); // �ϴ����ļ���
		Boolean flag = Boolean.FALSE;
		if (fileItem.indexOf(".") > 0) {
			String type = fileItem.substring(fileItem.lastIndexOf(".")); // �ļ�����
			if (!type.equals(".xls"))
				flag = Boolean.TRUE;
		} else{
			flag = Boolean.TRUE;
		}
		/*if (flag){
			errors.add("�ϴ����ļ���׺����Ϊ.xls"); // �ļ���׺��ƥ��
			}*/
		
		return flag;
	}

	/**
	 * �ļ��Ƿ��ϴ���
	 * 
	 * @return �ϴ�������false����Ϊtrue
	 */
	public Boolean fileIsUploaded() {
		String sql = "select t.* from cbzdxx t where t.dsfj is not null and t.qxname is not null and t.wjid="
				+ this.id;
		System.out.println("�ļ��Ƿ��ϴ���" + sql);
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
	 * ����ǰ�ļ��������ݿ�
	 * 
	 * @param fileItem
	 * @throws Exception
	 */
	public void insertToDB(FileItem fileItem, List<Zdinfo> ls) throws Exception {
		String shicode = account.getShi();

		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// �ϴ�����
		if (ls != null) {
			for (Zdinfo lss : ls) {
				// String sql =
				// "insert into cbzdxx(wjid,zdid,cbsj,dsfj,qxname) values(?,?,?,empty_blob(),?)";
				String sql = "update cbzdxx t set t.dsfj=empty_blob(),t.qxname=? where t.wjid='"
						+ this.id + "'";
				System.out.println("�ϴ������ʼ����" + sql);
				System.out.println("-----------------------              qxname:"+fileItem.getName());
				PreparedStatement ps = db.getPreparedStatement(sql);
				ps.setString(1, fileItem.getName());
				ps.executeUpdate();
				db.close();

				// ��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
				String sqlForInsertBlob = "select t.dsfj from cbzdxx t where t.wjid='"
						+ this.id + "' for update";
				System.out.println("�ϴ����룺" + sqlForInsertBlob);
				InsertToBlob(sqlForInsertBlob, fileItem);
			}
		}
	}

	public void insertToDBB(FileItem fileItem, List<Zdinfo> ls)
			throws Exception {
		String shicode = account.getShi();

		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// �ϴ�����
		if (ls != null) {
			for (Zdinfo lss : ls) {
				// String sql =
				// "insert into cbzdxx(wjid,zdid,cbsj,dsfj,qxname) values(?,?,?,empty_blob(),?)";
				String sql = "update cbzdxx t set t.SJFJ=empty_blob(),t.SJNAME=? where t.wjid='"
						+ this.id + "'";
				System.out.println("�ϴ������ʼ����" + sql);
				PreparedStatement ps = db.getPreparedStatement(sql);
				ps.setString(1, fileItem.getName());
				ps.executeUpdate();
				db.close();

				// ��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
				String sqlForInsertBlob = "select t.SJFJ from cbzdxx t where t.wjid='"
						+ this.id + "' for update";
				System.out.println("�ϴ����룺" + sqlForInsertBlob);
				InsertToBlob(sqlForInsertBlob, fileItem);
			}
		}
	}
	//�������ĸ����ϴ������ݿ���
	public void insertToDBZG(FileItem fileItem, List<Zdinfo> ls) throws Exception {
		String shicode = account.getShi();

		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());// �ϴ�����
		if (ls != null) {
			for (Zdinfo lss : ls) {
				// String sql =
				// "insert into cbzdxx(wjid,zdid,cbsj,dsfj,qxname) values(?,?,?,empty_blob(),?)";
				String sql = "update cbzdxf t set t.zgfj=empty_blob(),t.qxname=? where t.bwjid='"
						+ this.id + "'";
				System.out.println("�ϴ������ʼ����" + sql);
				PreparedStatement ps = db.getPreparedStatement(sql);
				ps.setString(1, fileItem.getName());
				ps.executeUpdate();
				db.close();

				// ��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
				String sqlForInsertBlob = "select t.zgfj from cbzdxf t where t.bwjid='"
						+ this.id + "' for update";
				System.out.println("�ϴ����룺" + sqlForInsertBlob);
				InsertToBlob(sqlForInsertBlob, fileItem);
			}
		}
	}
	/**
	 * �����ݿ��в���������ļ�
	 * 
	 * @param ����ѯ��sql
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
