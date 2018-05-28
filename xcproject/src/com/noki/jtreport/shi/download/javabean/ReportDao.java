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
 * ��������ĸ��࣬�κα���ĵ�������̳д���
 * @author ����
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
	 * ��ʼ����Ҫ����
	 * @param name	�ļ���
	 * @param url	�ļ�ģ���ڵ�ǰ��Ŀ�еľ���·��
	 * @param account	��ǰ��¼���û�
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
		System.out.println("��ȡ��ַ"+getUrl());
		InputStream is = new FileInputStream(getUrl());	//��ȡģ����
		String tmpPath = getUrl().substring(0,getUrl().indexOf(".xls"))+System.currentTimeMillis()+".xls";//��ʱ�ļ�·��
		
		WorkbookSettings ws = new WorkbookSettings();
		WritableWorkbook newBook = Workbook.createWorkbook(new File(tmpPath),Workbook.getWorkbook(is),ws);//��ģ�����һ��WorkBook
		is.close();
		try{
			if(isModel.equals("1"))//����Ǵ�����
				doQuery(newBook);//����û�����ݵ����ﶼû��ִ��   ���෽��û��
		}catch (Exception e) {
			e.printStackTrace();
			return "0"+tmpPath;	//�����쳣
		}finally{
			newBook.write();
			newBook.close();
		}
		return "1"+tmpPath;
	}
	
	/**
	 * ɾ����ʱ�ļ�����ģ���ļ������Ŀɱ༭��Excel�ļ���
	 * @param path
	 */
	public void delTempFile(String path){
		File file = new File(path);
		file.delete();
	}
	
	protected void doQuery(WritableWorkbook book) throws Exception{
		throw new Exception("û�и���doQuery����");
	}


}
