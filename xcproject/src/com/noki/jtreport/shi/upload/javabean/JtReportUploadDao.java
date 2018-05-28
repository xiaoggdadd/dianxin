package com.noki.jtreport.shi.upload.javabean;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import org.apache.commons.fileupload.FileItem;

import com.noki.database.DataBase;
import com.noki.jizhan.daoru.CountForm;
import com.noki.jizhan.daoru.ReadXLS_ZD;
import com.noki.jtreport.servlet.JtReportjz;
import com.noki.mobi.common.Account;

public class JtReportUploadDao {
	private DataBase db;
	private Account account;
	private String month;
	private String reportType;
	private String fileName;
	
	public JtReportUploadDao(Account account,String month,String reportType,String fileName){
		db = new DataBase();
		this.account = account;
		this.month = month;
		this.reportType = reportType;
		this.fileName = fileName;
	}
	
	/**
	 * �жϵ�ǰ�ļ��Ƿ�����ϴ�
	 * @param errors ������Ϣ
	 * @param fileItem ��ǰ�ļ�
	 */
	public void isAvailableToUpload(List<String> errors,FileItem fileItem){
		
		String fileName = fileItem.getName();
		System.out.println("-�ļ���ʽ�ж�name-"+fileName);
		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);	//�ϴ����ļ���
		Boolean flag = Boolean.FALSE;
		if(fileName.indexOf(".")>0){
			String type = fileName.substring(fileName.lastIndexOf("."));		//�ļ�����
			if(!type.equals(".xls"))flag = Boolean.TRUE;
		}
		else flag = Boolean.TRUE;
		if(flag)errors.add("�ϴ����ļ���׺����Ϊ.xls");	//�ļ���׺��ƥ��
	}
	
	/**
	 * �ļ��Ƿ��ϴ���
	 * @return �ϴ�������false����Ϊtrue
	 */
	public Boolean fileIsUploaded(){
		String sql = "select t.* from jt_report_upload t where " +
				"t.shicode='"+account.getShi()+"' and t.reporttype='"+this.reportType+"' " +
				"and t.actualmonth = '"+this.month+"'";
		System.out.println("�ļ��Ƿ��ϴ���"+sql);
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
	public void insertToDB(FileItem fileItem) throws Exception{
		String shicode = account.getShi();
		
		String sqlToDelete = "delete from jt_report_upload t " +
				"where t.shicode='"+shicode+"' and t.reporttype='"+this.reportType+"' " +
						"and t.actualmonth = '"+this.month+"'";
		db.update(sqlToDelete);//���Ѿ��ϴ������ļ������Ƚ���ɾ��
		//�½���Ŀ�ϵ�˳��
		/*String shunxu ="SELECT CODE FROM INDEXS I WHERE I.TYPE='ORDER' " +
						"AND I.NAME=(SELECT D.AGNAME FROM D_AREA_GRADE D WHERE D.AGCODE='"+shicode+"')";
		ResultSet rs = null; 
		String shunxun="";
		try {
			rs = db.queryAll(shunxu);
		if(rs.next()){	
			shunxun=rs.getString(1);
	   
		}		
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//�ϴ�����

		String sql = "insert into jt_report_upload values(?,?,?,empty_blob(),?,?,?)";

		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setString(1, shicode);
		ps.setDate(2, uploadDate);
		ps.setString(3, this.fileName);
		ps.setString(4, this.month);
		ps.setString(5, this.reportType);
		ps.setString(6, shicode);//�õ��б�������
		ps.executeUpdate();
		db.close();
		db.free(null, ps, null);

		//��Ҫ����������ֶΣ�ע��sql�����Ҫ�� for update��Ȼ��ſ��ԶԶ������ļ�����д��
		String sqlForInsertBlob = "select t.content from jt_report_upload t where t.shicode='"+shicode+"' and t.reporttype='"+reportType+"' and t.uploaddate = to_date('"+uploadDate+"','YYYY-MM-DD') and t.actualmonth = '"+this.month+"' for update";
		InsertToBlob(sqlForInsertBlob,fileItem);
	}
	/**
	 * �����ݿ��в���������ļ�
	 * @param ����ѯ��sql
	 * @throws Exception 
	 */
	public void InsertToBlob(String sql,FileItem fileItem) throws Exception{
		Connection conn = db.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement  ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			BLOB tmp = (BLOB) ((OracleResultSet)rs).getBlob(1);
			InputStream is = fileItem.getInputStream();
			OutputStream os = tmp.getBinaryOutputStream();
			
			byte[] buffer = new byte[1024];
			int size = -1;
			while((size=is.read(buffer))!=-1){
				os.write(buffer,0,size);
			}
			is.close();
			os.close();
			conn.commit();
			rs.close();
			ps.close();
			db.close();
		}
	}
}
