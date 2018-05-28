package com.ptac.app.priceover.countysigncheck.dao;


import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.noki.database.DataBase;

public class UpLoadUtil {
	private Logger logger = Logger.getLogger(UpLoadUtil.class);
	private DataBase db;
	private Connection connc;
	private ResultSet rs;
	private Statement st;

	/**
	 * �жϵ�ǰ�ļ��Ƿ�����ϴ�
	 * 
	 * @param fileItem
	 *            ��ǰ�ļ�
	 */
	public Boolean isAvailableToUpload( FileItem fileItem) {

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
		return flag;
	}
	  public synchronized boolean CheckFj(String gxzd,String bm,String zj,String zjz){//
		  boolean flag = false;
			StringBuffer sql = new StringBuffer("SELECT T.").append(gxzd).append(" FROM ")
			.append(bm).append(" T WHERE T.").append(zj).append(" = '").append(zjz)
			.append("' AND T.").append(gxzd).append("  IS NOT NULL");
			 db = new DataBase();
			try {
				connc = db.getConnection();
				st = connc.createStatement();
				logger.info("�ж��Ƿ���ڸ���:"+sql.toString());
				rs = st.executeQuery(sql.toString());
				flag = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("�ж��Ƿ���ڸ���ʧ��:"+e.getMessage());
			}finally{
			db.free(rs, st, connc);
			}
			return flag;		
			
		}
	
	public void insertToDB(String gxzd,String bm,String zj,String zjz,FileItem fileItem) throws Exception {
		//��ո����ֶ�
		StringBuffer sql = new StringBuffer("UPDATE ").append(bm).append(" T SET T.")
		.append(gxzd).append(" = EMPTY_BLOB() WHERE T.").append(zj).append(" = '").append(zjz).append("'");
		db = new DataBase();
			connc = db.getConnection();
			st = connc.createStatement();
			logger.info("���븽��ǰ����ո���:"+sql.toString());
			st.executeUpdate(sql.toString());
			db.free(null, st, connc);
		InsertToBlob(gxzd,bm,zj,zjz,fileItem);

	}

	/** �����ݿ��в���������ļ�
	 * @param gxzd Ҫ���µ��ֶ���
	 * @param bm ����
	 * @param zj ������
	 * @param zjz ����ֵ
	 * @param fileItem �ļ�
	 * @throws Exception
	 */
	public void InsertToBlob(String gxzd,String bm,String zj,String zjz, FileItem fileItem) throws Exception {
		InputStream is = null ;
		OutputStream os = null;
		// ��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
		StringBuffer sql = new StringBuffer("SELECT T.").append(gxzd).append(" FROM ")
		.append(bm).append(" T WHERE T.").append(zj).append(" = '").append(zjz).append("' FOR UPDATE");
		db = new DataBase();

			connc = db.getConnection();
			connc.setAutoCommit(false);
			st = connc.createStatement();
			logger.info("�����������ļ�д��:"+sql.toString());
			rs = st.executeQuery(sql.toString());
			if (rs.next()) {
				BLOB tmp = (BLOB) ((OracleResultSet) rs).getBlob(1);
				is = fileItem.getInputStream();
				os = tmp.getBinaryOutputStream();
				byte[] buffer = new byte[1024];
				int size = -1;
				while ((size = is.read(buffer)) != -1) {
					os.write(buffer, 0, size);
				}
			}
			if (is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
			connc.setAutoCommit(true);
			db.free(rs, st, connc);
		
	}
	
}
