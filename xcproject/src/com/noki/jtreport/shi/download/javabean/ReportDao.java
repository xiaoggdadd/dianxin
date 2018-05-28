package com.noki.jtreport.shi.download.javabean;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableWorkbook;

import com.noki.database.DataBase;
import com.noki.mobi.common.Account;


/**
 * 报表操作的父类，任何报表的导出均须继承此类
 * @author 王海
 *
 */
public class ReportDao {
	private String name;
	private String url;
	private Account account;
	private String month;
	private String accountmonth;
	public DataBase db;
	public Connection conn;
	public Statement stmt;
	public ReportDao(){
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
	 * @param name	文件名
	 * @param url	文件模板在当前项目中的绝对路径
	 * @param account	当前登录的用户
	 */
	public void init(String name, String url, Account account, String month,String accountmonth) {
		this.name = name;
		this.url = url;
		this.account = account;
		this.month = month;
		this.accountmonth=accountmonth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setAccountmonth(String accountmonth) {
		this.accountmonth = accountmonth;
	}

	public String getAccountmonth() {
		return accountmonth;
	}

	public String execute(String isModel) throws Exception{
		System.out.println("获取地址"+getUrl());
		InputStream is = new FileInputStream(getUrl());	//获取模板流
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//临时文件路径
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//从模板深复制一个WorkBook
		is.close();
		try{
			if(isModel.equals("1"))//如果是带数据
				doQuery(newBook);//导出没带数据的这里都没有执行   子类方法没改
		}catch (Exception e) {
			e.printStackTrace();
			return "0"+tmpPath;	//发生异常
		}finally{
			newBook.write();
			newBook.close();
		}
		return "1"+tmpPath;
	}
	
	/**
	 * 删除临时文件（由模板文件创建的可编辑的Excel文件）
	 * @param path
	 */
	public void delTempFile(String path){
		File file = new File(path);
		file.delete();
	}
	
	protected void doQuery(WritableWorkbook book) throws Exception{
		throw new Exception("没有覆盖doQuery方法");
	}


}
