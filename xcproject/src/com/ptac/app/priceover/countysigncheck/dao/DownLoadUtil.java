package com.ptac.app.priceover.countysigncheck.dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.noki.database.DataBase;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

public class DownLoadUtil {
	private Logger logger = Logger.getLogger(DownLoadUtil.class);
	private DataBase db;
	private Connection connc;
	private ResultSet rs;
	private Statement st;
	private List<String> tmpPathList;//��ʱ�ļ�·���������ʱ�ļ�
	public List<String> getTmpPathList() {
		return tmpPathList;
	}
	public void setTmpPathList(List<String> tmpPathList) {
		this.tmpPathList = tmpPathList;
	}
	/**
	 * 
	 * @return List<String>���ѱ�����Ϣ�ŵ�һ����ʱĿ¼�£����������ļ���ʱĿ¼
	 * @throws Exception
	 */
	
	public List<String> execute1(String gxzd,String bm,String zj,String zjz,List<String> errors,String filename){
		
		List<InputStream> streamList = new ArrayList<InputStream>();
		
		StringBuffer sql = new StringBuffer("SELECT T.").append(gxzd).append(" FROM ")
		.append(bm).append(" T WHERE T.").append(zj).append(" = '").append(zjz)
		.append("' AND T.").append(gxzd).append("  IS NOT NULL");
			db = new DataBase();
			try {
				connc = db.getConnection();
			st = connc.createStatement();
			logger.info("���ظ�����ѯ:"+sql.toString());
			rs = st.executeQuery(sql.toString());
		while(rs.next()){
			streamList.add(rs.getBinaryStream(gxzd));
		}
		System.out.println("�ļ����ȣ�"+streamList.size());
		if(streamList.size() == 0){
			errors.add("û���ҵ���ǰ��¼��");
			return null;
		}
		List<WritableWorkbook> bookList = new ArrayList<WritableWorkbook>();
		tmpPathList = new ArrayList<String>();
		for(InputStream is:streamList){
			
			String tmpPath = System.getProperty("java.io.tmpdir")+"\\"+System.currentTimeMillis()+filename;//��ʱ�ļ�Ŀ¼
			
			WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is));
			tmpPathList.add(tmpPath);
			bookList.add(newBook);	
			is.close();
		}
			for(WritableWorkbook book:bookList){
				book.write();
				book.close();
		}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("�����ļ�����:"+e.getMessage());
			}finally{
				db.free(rs, st, connc);
			}
		return getTmpPathList();
	}
	
	/**
	 * ɾ����ʱ�ļ�����ģ���ļ������Ŀɱ༭��Excel�ļ���
	 * @param path
	 */
	public void delTempFile(List<String> paths){
		if(null != paths){
			for(String path:paths){
				File file = new File(path);
				file.delete();
			}
		}
	}
}
