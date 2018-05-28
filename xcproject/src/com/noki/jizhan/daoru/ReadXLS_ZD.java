package com.noki.jizhan.daoru;

import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;
import java.io.File;
import jxl.Cell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Pattern;

import com.noki.database.DataBase;
import com.noki.database.DbException;

import jxl.Workbook;

/**
 * <p>Title:读取Excel表格数据 </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ReadXLS_ZD implements ReadFile_ZD{
    public ReadXLS_ZD() {
    }

    public Vector getColumns(String filename) {
        Vector columns = new Vector();
        try {
            Workbook book = Workbook.getWorkbook(new File(filename));
            //获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            //得到第一列第一行的单元格
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, 0);
                String result = cell.getContents();
                columns.add(result);
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("得到第一行的单元格"+columns.toString());
        return columns;
    }

    public Vector getContent(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel行数=="+sheet.getRows());
            for (int i = 2; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==3){
                    	 System.out.println("i=="+result+"//j=="+j);
                    }else{
                    	 if(!"".equals(result)&&result!=null){
                         	  if(cell.getType().equals(CellType.NUMBER)){
                         		  
                             	  NumberCell numberCell = (NumberCell)sheet.getCell(j, i);
                                   double namberValue = numberCell.getValue();
                                   result=namberValue+"";
                               }
                           }
                    }
                   
                   
                  
                    //System.out.println(result);
                    rows.add(result);
                    if (result != null && !result.equals("")) {
                        isNull = false;
                    }
                }
                if (!isNull) {
                    content.add(rows);
                }
                else{
                	System.out.println("行空 了  结束了");
                	break;
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    public Vector getContent3(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel行数=="+sheet.getRows());
            for (int i = 2; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==3){
                    }else if(j==4){
                    }else if(j==5){}
                    else if(j==6){}
                    else{
                    	 if(!"".equals(result)&&result!=null){
                         	  if(cell.getType().equals(CellType.NUMBER)){
                         		  
                             	  NumberCell numberCell = (NumberCell)sheet.getCell(j, i);
                                   double namberValue = numberCell.getValue();
                                   result=namberValue+"";
                               }
                           }
                    }
                   
                   
                  
                    //System.out.println(result);
                    rows.add(result);
                    if (result != null && !result.equals("")) {
                        isNull = false;
                    }
                }
                if (!isNull) {
                    content.add(rows);
                }
                else{
                	System.out.println("行空 了  结束了");
                	break;
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    public Vector getContent4(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel行数=="+sheet.getRows());
            for (int i = 2; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==3){
                    }else if(j==4){
                    }else if(j==5){}
                    else if(j==6){}
                    else if(j==42){}
                    else{
                    	 if(!"".equals(result)&&result!=null){
                         	  if(cell.getType().equals(CellType.NUMBER)){
                         		  
                             	  NumberCell numberCell = (NumberCell)sheet.getCell(j, i);
                                   double namberValue = numberCell.getValue();
                                   result=namberValue+"";
                               }
                           }
                    }
                   
                   
                  
                    //System.out.println(result);
                    rows.add(result);
                    if (result != null && !result.equals("")) {
                        isNull = false;
                    }
                }
                if (!isNull) {
                    content.add(rows);
                }
                else{
                	System.out.println("行空 了  结束了");
                	break;
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    public Vector getContent5(String filename) {
        Vector content = new Vector();
        try {
        	System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            System.out.println("rows  Excel行数=="+sheet.getRows());
            for (int i = 2; i < sheet.getRows(); i++) {
                Vector rows = new Vector();
                boolean isNull = true;
                //System.out.println("coloumns==" + sheet.getColumns());
                for (int j = 0; j < sheet.getColumns(); j++) {
                    // System.out.println("i=="+i+"//j=="+j);
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if(j==3){
                    }else if(j==4){
                    }else if(j==5){}
                    else if(j==6){}
                    else if(j==24){}
                    else if(j==25){}
                    else{
                    	 if(!"".equals(result)&&result!=null){
                         	  if(cell.getType().equals(CellType.NUMBER)){
                         		  
                             	  NumberCell numberCell = (NumberCell)sheet.getCell(j, i);
                                   double namberValue = numberCell.getValue();
                                   result=namberValue+"";
                               }
                           }
                    }
                   
                   
                  
                    //System.out.println(result);
                    rows.add(result);
                    if (result != null && !result.equals("")) {
                        isNull = false;
                    }
                }
                if (!isNull) {
                    content.add(rows);
                }
                else{
                	System.out.println("行空 了  结束了");
                	break;
                }
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    public int getContent100(String filename) {
    	int row=0;
        try {
        	//System.out.println("-------"+filename);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);
            ///System.out.println("rows  Excel行数=="+sheet.getRows());
            row=sheet.getRows();
            for (int i = 103; i < sheet.getRows(); i++) {
               // System.out.println("rows=="+i);

                Vector rows = new Vector();
                boolean isNull = true;
                for (int j = 0; j < sheet.getColumns(); j++) {
                   // System.out.println("Excel行数=="+i);

                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();
                    if (result != null && !result.equals("")) {
                        isNull =false ;
                    }
                }
                if(!isNull){
                	row=i;
                	System.out.println("想回家了"+i);
                	  break;
                }else{
                	row=0;
                	break;
                }
              
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }         
        return row;
    }
    public String getShijian(){
    	DataBase db = new DataBase();
    	ResultSet rs = null;
    	PreparedStatement ps = null;
    	Connection conn = null;
    	String sql="select to_char(sysdate,'yyyymmdd hh24miss') from dual";
    	try {
			db.connectDb();
			conn = db.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			 if (rs.next()) {
		       return rs.getString(1);
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		db.free(rs, ps, conn);
		}
    	return "";
    }

}
