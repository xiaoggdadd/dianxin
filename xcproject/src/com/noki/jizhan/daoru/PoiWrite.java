package com.noki.jizhan.daoru;

import java.io.FileInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import com.noki.jizhan.DataComm;
import java.sql.ResultSet;
import com.noki.database.DataBase;
import com.noki.database.DbException;
import java.sql.SQLException;

/**
 * <p>Title: </p>
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
public class PoiWrite {
    public PoiWrite() {
    }
//电表导入模板里的数据
    public void writeExcel(String filepath,
                           String loginName) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
                    filepath));

            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            DataComm dataComm = new DataComm();
            DataBase db = new DataBase();
            ResultSet rs = null;
            db.connectDb();
            String loginId="";//fzarea = "", fzzdid = "" ,
            //fzarea = dataComm.getFuzeArea(db, loginName);

            //fzzdid = dataComm.getFuzeZdid(db, loginName, fzarea, "0");
            loginId=dataComm.getLoginId(db, loginName);
            StringBuffer s2 = new StringBuffer();

            s2.append("select (case when z.xian is not null then (select distinct agname from d_area_grade where agcode=z.xian and fagcode=z.shi) else '' end) as xianname");
            s2.append(",(case when z.xiaoqu is not null then (select distinct agname from d_area_grade where agcode=z.xiaoqu and fagcode=z.xian) else '' end) as xiaoquname");
            s2.append( ",z.jzname,z.id as zdcode,z.jzcode,z.shi,z.xian,z.xiaoqu from zhandian z where 1=1 and z.qyzt='1' and z.SHSIGN='1' and z.PROVAUDITSTATUS='1' ");
            s2.append(" and z.xiaoqu in (select t.agcode from per_area t where t.accountid = '"+loginId+"') ");
            System.out.println("电表导入模板里的数据:"+s2.toString());////////
            rs = db.queryAll(s2.toString());
            int rownum = 2;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(rownum++);
                HSSFCell cell = row.createCell(0);
                cell.setCellValue(rs.getString(1));
                HSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(rs.getString(2));
                HSSFCell cell3 = row.createCell(2);
                cell3.setCellValue(rs.getString(3));
                HSSFCell cell4 = row.createCell(3);
                cell4.setCellValue(rs.getString(4));
                HSSFCell cell5=row.createCell(4);
                cell5.setCellValue(rs.getString(5));

            }

            FileOutputStream fileout = new FileOutputStream(new File(filepath));
            wb.write(fileout);
            fileout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DbException de) {
            de.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
