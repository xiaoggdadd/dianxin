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
	private List<String> tmpPathList;//临时文件路径（多个临时文件
	public List<String> getTmpPathList() {
		return tmpPathList;
	}
	public void setTmpPathList(List<String> tmpPathList) {
		this.tmpPathList = tmpPathList;
	}
	/**
	 * 
	 * @return List<String>：把报表信息放到一个临时目录下，返回所有文件临时目录
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
			logger.info("下载附件查询:"+sql.toString());
			rs = st.executeQuery(sql.toString());
		while(rs.next()){
			streamList.add(rs.getBinaryStream(gxzd));
		}
		System.out.println("文件长度："+streamList.size());
		if(streamList.size() == 0){
			errors.add("没有找到当前记录！");
			return null;
		}
		List<WritableWorkbook> bookList = new ArrayList<WritableWorkbook>();
		tmpPathList = new ArrayList<String>();
		for(InputStream is:streamList){
			
			String tmpPath = System.getProperty("java.io.tmpdir")+"\\"+System.currentTimeMillis()+filename;//临时文件目录
			
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
				logger.error("下载文件出错:"+e.getMessage());
			}finally{
				db.free(rs, st, connc);
			}
		return getTmpPathList();
	}
	
	/**
	 * 删除临时文件（由模板文件创建的可编辑的Excel文件）
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
