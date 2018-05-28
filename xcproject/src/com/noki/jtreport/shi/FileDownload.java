package com.noki.jtreport.shi;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import com.noki.database.DataBase;

public class FileDownload {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	private String path;
	public FileDownload(String id) throws Exception{
		
		DataBase db=new DataBase();
		db.connectDb();
		String sql = "select t.filename,t.filepath from filetable t where t.id='"+id+"'";
		ResultSet rs = db.queryAll(sql);
		System.out.println("---"+sql.toString());
		while(rs.next()){
			this.setName(rs.getString(1));
			this.setPath(rs.getString(2));
		}
	}
}
