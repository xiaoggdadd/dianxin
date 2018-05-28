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
	 * 判断当前文件是否可以上传
	 * @param errors 错误信息
	 * @param fileItem 当前文件
	 */
	public void isAvailableToUpload(List<String> errors,FileItem fileItem){
		
		String fileName = fileItem.getName();
		System.out.println("-文件格式判断name-"+fileName);
		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);	//上传的文件名
		Boolean flag = Boolean.FALSE;
		if(fileName.indexOf(".")>0){
			String type = fileName.substring(fileName.lastIndexOf("."));		//文件类型
			if(!type.equals(".xls"))flag = Boolean.TRUE;
		}
		else flag = Boolean.TRUE;
		if(flag)errors.add("上传的文件后缀必须为.xls");	//文件后缀不匹配
	}
	
	/**
	 * 文件是否上传过
	 * @return 上传过返回false否则为true
	 */
	public Boolean fileIsUploaded(){
		String sql = "select t.* from jt_report_upload t where " +
				"t.shicode='"+account.getShi()+"' and t.reporttype='"+this.reportType+"' " +
				"and t.actualmonth = '"+this.month+"'";
		System.out.println("文件是否上传过"+sql);
		try {
			ResultSet rs = db.queryAll(sql);
			if(rs.next())return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 将当前文件插入数据库
	 * @param fileItem
	 * @throws Exception 
	 */
	public void insertToDB(FileItem fileItem) throws Exception{
		String shicode = account.getShi();
		
		String sqlToDelete = "delete from jt_report_upload t " +
				"where t.shicode='"+shicode+"' and t.reporttype='"+this.reportType+"' " +
						"and t.actualmonth = '"+this.month+"'";
		db.update(sqlToDelete);//若已经上传过该文件，则先将其删掉
		//新疆项目上的顺序
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

		java.sql.Date uploadDate = new java.sql.Date(new Date().getTime());//上传日期

		String sql = "insert into jt_report_upload values(?,?,?,empty_blob(),?,?,?)";

		PreparedStatement ps = db.getPreparedStatement(sql);
		ps.setString(1, shicode);
		ps.setDate(2, uploadDate);
		ps.setString(3, this.fileName);
		ps.setString(4, this.month);
		ps.setString(5, this.reportType);
		ps.setString(6, shicode);//用地市编码排序
		ps.executeUpdate();
		db.close();
		db.free(null, ps, null);

		//需要查出二进制字段，注意sql语句需要加 for update，然后才可以对二进制文件进行写入
		String sqlForInsertBlob = "select t.content from jt_report_upload t where t.shicode='"+shicode+"' and t.reporttype='"+reportType+"' and t.uploaddate = to_date('"+uploadDate+"','YYYY-MM-DD') and t.actualmonth = '"+this.month+"' for update";
		InsertToBlob(sqlForInsertBlob,fileItem);
	}
	/**
	 * 向数据库中插入二进制文件
	 * @param 待查询的sql
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
