package com.noki.newfunction.dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import com.noki.database.DataBase;
import com.noki.database.DbException;
import com.noki.mobi.common.Account;


/**
 * 报表操作的父类，任何报表的导出均须继承此类
 * @author 王海
 *
 */
public class CbzdReportDao1 {
	private String name;//报表文件名
	private String month;//汇总的月份
	private String[] city;//汇总的城市
	
	private String url;//模板的路径
	private List<String> shiCode;
	private List<String> tmpPathList;//临时文件路径（多个临时文件）
	
	public DataBase db;
	public Connection conn;
	public Statement stmt;
	public CbzdReportDao1(){
		db = new DataBase();
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化必要参数
	 */
	public void init(String name,String month,String[] city,String url) {
		this.name = name;
		this.month = month;
		this.city = city;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String[] getCity() {
		return city;
	}
	public void setCity(String[] city) {
		this.city = city;
	}
	public List<String> getShiCode() {
		return shiCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getTmpPathList() {
		return tmpPathList;
	}
	public void setTmpPathList(List<String> tmpPathList) {
		this.tmpPathList = tmpPathList;
	}

	/**
	 * 超标站点管理查询要下载的报表
	 * @return List<String>：把报表信息放到一个临时目录下，返回所有文件临时目录
	 * @throws Exception
	 */
	
	public List<String> execute1(List<String> errors,String id,String filename,String bzw) throws Exception{
		
		List<InputStream> streamList = new ArrayList<InputStream>();
		
		//String sql = "SELECT T.SJFJ FROM CBZDXX T WHERE T.WJID='"+id+"'";
		String sql2 = "SELECT CB.DSFJ FROM CBZDXX CB WHERE CB.WJID ='"+id+"' and CB.DSFJ is not null";
		String sql4="SELECT CB.ZGFJ FROM CBZDXF CB WHERE CB.BWJID ='"+id+"' and cb.zgfj is not null";
		String sql5="SELECT Q.FJNR FROM QSKZ Q WHERE  Q.ID='"+id+"' AND Q.FJNR IS NOT NULL ";
		String sql6="SELECT Q.FJNR FROM QSKZ Q WHERE  Q.ID='"+id+"' AND Q.FJNR IS NOT NULL ";
		ResultSet rs=null;
		String zd="";
		if(bzw.equals("2")){
			filename="区县测试描述附件.xls";
			System.out.println("下载查询2"+sql2);
			zd="DSFJ";
			rs  = db.queryAll(sql2);
			
		}else if(bzw.equals("4")){
			filename="区县整改附件.xls";
			System.out.println("下载查询4"+sql4);
			zd="ZGFJ";
			rs=db.queryAll(sql4);
		}else if(bzw.equals("5")){
			filename="字段修改申请模板.xls";
			System.out.println("下载查询5"+sql5);
			zd="FJNR";
			rs=db.queryAll(sql5);
		}else if(bzw.equals("6")){
			filename="字段修改申请附件.xls";
			System.out.println("下载查询6"+sql6);
			zd="FJNR";
			rs=db.queryAll(sql6);
		}
		while(rs.next()){
			streamList.add(rs.getBinaryStream(zd));
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
			db.close();
			for(WritableWorkbook book:bookList){
				book.write();
				book.close();
			
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
	
	protected void doQuery(List<WritableWorkbook> books) throws Exception{
		throw new Exception("没有覆盖doQuery方法");
	}
	/**
	 * 查询区县附件保存的路径和名称 
	 * @param id:u站点唯一标识wjid
	 */
	public synchronized CbzdReportDao1 getFuJianPath(String id,String bzw){
		String sql = "SELECT CB.QXNAME,CB.QXPATH FROM CBZDXX CB WHERE CB.WJID ='"+id+"'";
		String sql2="SELECT CB.SJNAME,CB.FILEPATH FROM CBZDXX CB WHERE CB.WJID ='"+id+"'";
		String sql3="SELECT CB.SJPATH,CB.FILEPATH FROM CBZDXF CB WHERE CB.BWJID ='"+id+"'";
		String sql4="SELECT CB.QXNAME,CB.QXPATH FROM CBZDXF CB WHERE CB.BWJID ='"+id+"'";
		String sql5="SELECT KZ.FJMC,KZ.FJPATH FROM QSKZ KZ WHERE KZ.ID='"+id+"'";
		
		CbzdReportDao1 cbzdReportDao1 = new CbzdReportDao1();
		DataBase db=new DataBase();
		ResultSet rs=null ;
		try {
			System.out.println("下载的标志位："+bzw);
			db.connectDb();
			if(bzw.equals("1")){
				System.out.println("下载查询"+sql2);
			rs  = db.queryAll(sql2);
			}else if(bzw.equals("2")){
				System.out.println("下载查询"+sql);
				rs  = db.queryAll(sql);
			}else if(bzw.equals("3")){
				System.out.println("下载查询"+sql3);
				rs  = db.queryAll(sql3);
			}else if(bzw.equals("4")){
				System.out.println("下载查询"+sql4);
				rs  = db.queryAll(sql4);
			}else if(bzw.equals("10")){
				System.out.println("下载查询"+sql5);
				rs=db.queryAll(sql5);
			}

			while(rs.next()){
				cbzdReportDao1.setName(rs.getString(1));
				cbzdReportDao1.setUrl(rs.getString(2));
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cbzdReportDao1;
	}
}



