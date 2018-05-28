package com.noki.jtreport.shi;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.commons.fileupload.FileItem;

import com.noki.database.DataBase;
import com.noki.mobi.common.Account;

public class FilesUpload {
	private DataBase db;
	private Account account;
	private String month;
	
	
	public FilesUpload(Account account,String month){
		db = new DataBase();
		this.account = account;
		this.month = month;
		
	}
	
	/**
	 * �ļ��Ƿ��ϴ���
	 * @return �ϴ�������false����Ϊtrue
	 */
	public Boolean fileIsUploaded(){
		String sql = "";

		System.out.println(sql+"--------------");
		try {
			ResultSet rs = db.queryAll(sql);
			if(rs.next())return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * ����ǰ�ļ��������ݿ�
	 * @param fileItem
	 * @throws Exception 
	 */
	public void insertToDB(String path,String name) throws Exception{
		String loginId = account.getAccountId();
		
		//System.out.println("filename"+filename);
		//int ii=filename.indexOf(".");
		//filename=filename.substring(0,ii);
		path=path+"\\"+name; 
		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//�ϴ�����
		System.out.println("--"+uploadDate);
		String sql = "insert into filetable values('','"+name+"','"+uploadDate+"','"+loginId+"','"+path+"','"+loginId+"')";
		System.out.println("--"+sql.toString());
		db.update(sql);
		db.close();
		
		//��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
		//String sqlForInsertBlob = "select t.filecontent from filetable t where t.filename='"+filename+"' and t.uploaddate=TO_DATE(('"+uploadDate+"'), 'yyyy-mm-dd')  for update";
		//InsertToBlob(sqlForInsertBlob,fileItem);
	}
	
	/**
	 * �����ݿ��в���������ļ�
	 * @param ����ѯ��sql
	 * @throws Exception 
	 */
//	public void InsertToBlob(String sql,FileItem fileItem) throws Exception{
//		Connection conn = db.getConnection();
//		conn.setAutoCommit(false);
//		PreparedStatement  ps = conn.prepareStatement(sql);
//		System.out.println("--"+sql.toString());
//		ResultSet rs = ps.executeQuery();
//		if(rs.next()){
//			BLOB tmp = (BLOB) ((OracleResultSet)rs).getBlob(1);
//			InputStream is = fileItem.getInputStream();
//			OutputStream os = tmp.getBinaryOutputStream();
//			
//			byte[] buffer = new byte[1024];
//			int size = -1;
//			while((size=is.read(buffer))!=-1){
//				os.write(buffer,0,size);
//			}
//			is.close();
//			os.close();
//			conn.commit();
//			rs.close();
//			ps.close();
//		}
//	}
}
