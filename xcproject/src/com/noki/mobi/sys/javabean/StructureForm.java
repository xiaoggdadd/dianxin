package com.noki.mobi.sys.javabean;

import com.noki.database.DataBase;
import java.sql.SQLException;
import com.noki.database.DbException;
import java.sql.ResultSet;

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
public class StructureForm {
    public StructureForm() {
    }
    private String dwqc;
   private String dwjc;
   private String fzr;
   private String superno;
   private String tel;
   private String fax;
   private String email;
   private String address;
   private String zipcode;
   private String jgsz;
   private String depinfo;
   private String classid;


   public void setDwqc(String dwqc) {

       this.dwqc = dwqc;

   }

   public void setDwjc(String dwjc) {

       this.dwjc = dwjc;

   }

   public void setFzr(String fzr) {

       this.fzr = fzr;

   }

   public void setSuperno(String superno) {

       this.superno = superno;

   }

   public void setTel(String tel) {

       this.tel = tel;

   }

   public void setFax(String fax) {

       this.fax = fax;

   }

   public void setEmail(String email) {

       this.email = email;

   }

   public void setAddress(String address) {

       this.address = address;

   }

   public void setZipcode(String zipcode) {

       this.zipcode = zipcode;

   }

   public void setJgsz(String jgsz) {

       this.jgsz = jgsz;

   }

   public void setDepinfo(String depinfo) {

       this.depinfo = depinfo;

   }

   public void setClassid(String classid) {
       this.classid = classid;
   }

   public String getDwqc() {
       return dwqc;
   }

   public String getDwjc() {
       return dwjc;
   }

   public String getFzr() {
       return fzr;
   }

   public String getSuperno() {
       return superno;
   }

   public String getTel() {
       return tel;
   }

   public String getFax() {
       return fax;
   }

   public String getEmail() {
       return email;
   }

   public String getAddress() {
       return address;
   }

   public String getZipcode() {
       return zipcode;
   }

   public String getJgsz() {
       return jgsz;
   }

   public String getDepinfo() {
       return depinfo;
   }

   public String getClassid() {
       return classid;
   }

   public synchronized StructureForm getStructureInfo(String classid) {

       DataBase db = new DataBase();
       ResultSet rs = null;
       String sql = "select full_name,classname,principal,super_no,phone,fax,email,address,postalcode,department,readme from structure  where classid=" +
                    classid;
       try {
           db.connectDb();
           rs = db.queryAll(sql);
           if (rs.next()) {
               this.setDwqc(rs.getString(1) != null ? rs.getString(1) : "");
               this.setDwjc(rs.getString(2) != null ? rs.getString(2) : "");
               this.setFzr(rs.getString(3) != null ? rs.getString(3) : "");
               this.setSuperno(rs.getString(4) != null ? rs.getString(4) : "");
               this.setTel(rs.getString(5) != null ? rs.getString(5) : "");
               this.setFax(rs.getString(6) != null ? rs.getString(6) : "");
               this.setEmail(rs.getString(7) != null ? rs.getString(7) : "");
               this.setAddress(rs.getString(8) != null ? rs.getString(8) : "");
               this.setZipcode(rs.getString(9) != null ? rs.getString(9) : "");
               this.setJgsz(rs.getString(10) != null ? rs.getString(10) : "");
               this.setDepinfo(rs.getString(11) != null ? rs.getString(11) :
                               "");
           }
           if(this.getEmail()==null||this.getEmail().equals("null")){
               this.setEmail("");
           }
           if(this.getZipcode()==null||this.getZipcode().equals("null")){
               this.setZipcode("");
           }
       } catch (SQLException se) {
           se.printStackTrace();
       } catch (DbException de) {
           de.printStackTrace();
       }

       finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (SQLException se) {
                   se.printStackTrace();
               }
           }
           try {
               db.close();
           } catch (DbException de) {
               de.printStackTrace();
           }

       }

       return this;
   }

   public synchronized StructureForm getStructureInfo_csbm(String classid) {

       DataBase db = new DataBase();
       ResultSet rs = null;
       String sql = "select full_name,classname,principal,super_no,phone,fax,email,address,postalcode,department,readme from structure_csbm  where classid=" +
                    classid;
       try {
           db.connectDb();
           rs = db.queryAll(sql);
           if (rs.next()) {
               this.setDwqc(rs.getString(1) != null ? rs.getString(1) : "");
               this.setDwjc(rs.getString(2) != null ? rs.getString(2) : "");
               this.setFzr(rs.getString(3) != null ? rs.getString(3) : "");
               this.setSuperno(rs.getString(4) != null ? rs.getString(4) : "");
               this.setTel(rs.getString(5) != null ? rs.getString(5) : "");
               this.setFax(rs.getString(6) != null ? rs.getString(6) : "");
               this.setEmail(rs.getString(7) != null ? rs.getString(7) : "");
               this.setAddress(rs.getString(8) != null ? rs.getString(8) : "");
               this.setZipcode(rs.getString(9) != null ? rs.getString(9) : "");
               this.setJgsz(rs.getString(10) != null ? rs.getString(10) : "");
               this.setDepinfo(rs.getString(11) != null ? rs.getString(11) :
                               "");
           }
           if(this.getEmail()==null||this.getEmail().equals("null")){
               this.setEmail("");
           }
           if(this.getZipcode()==null||this.getZipcode().equals("null")){
               this.setZipcode("");
           }
       } catch (SQLException se) {
           se.printStackTrace();
       } catch (DbException de) {
           de.printStackTrace();
       }

       finally {
           if (rs != null) {
               try {
                   rs.close();
               } catch (SQLException se) {
                   se.printStackTrace();
               }
           }
           try {
               db.close();
           } catch (DbException de) {
               de.printStackTrace();
           }

       }

       return this;
   }

}
