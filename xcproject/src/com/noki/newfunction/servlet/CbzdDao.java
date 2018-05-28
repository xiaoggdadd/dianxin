package com.noki.newfunction.servlet;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import com.noki.database.DataBase;


/**
 * 报表操作的父类，任何报表的导出均须继承此类
 * @author 王海
 *
 */
public class CbzdDao {
	private String name;//报表文件名
	private String month;//汇总的月份
	private String[] city;//汇总的城市
	
	private String url;//模板的路径
	private List<String> shiCode;
	private List<String> tmpPathList;//临时文件路径（多个临时文件）
	
	public DataBase db;
	public Connection conn;
	public Statement stmt;
	public CbzdDao(){
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
	//public void init(String name,String month,String[] city,String url) {
	public void init(String name) {
		this.name = name;
		//this.month = month;
		//this.city = city;
		//this.url = url;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public String getMonth() {
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	*/
	public List<String> getShiCode() {
		return shiCode;
	}
	
	public List<String> getTmpPathList() {
		return tmpPathList;
	}
	public void setTmpPathList(List<String> tmpPathList) {
		this.tmpPathList = tmpPathList;
	}

	/**
	 * 执行报表汇总的主要操作
	 * @return 把所有要汇总的城市报表放到一个临时目录下，返回所有文件临时目录
	 * @throws Exception
	 */
	
	public List<String> execute1(List<String> errors) throws Exception{
		
		//String cityStr = Arrays.toString(getCity());
		//System.out.println("城市"+cityStr);
		//cityStr = cityStr.substring(1, cityStr.length()-1);
		List<InputStream> streamList = new ArrayList<InputStream>();
		shiCode = new ArrayList<String>();
		
		String sql = "select t.content,t.shicode from jt_report_upload t where t.shicode in and t.actualmonth = and t.filename = '"+getName()+"'";
		ResultSet rs = db.queryAll(sql);
		System.out.println("查询要下载的附加"+sql.toString());
		while(rs.next()){
			System.out.println("--"+rs.getBinaryStream(1));
			streamList.add(rs.getBinaryStream(1));
			shiCode.add(rs.getString(2));
		}		
		List<WritableWorkbook> bookList = new ArrayList<WritableWorkbook>();
		tmpPathList = new ArrayList<String>();
		for(InputStream is:streamList){
			String tmpPath = System.getProperty("java.io.tmpdir")+"\\"+System.currentTimeMillis()+getName();//临时文件目录
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

}



