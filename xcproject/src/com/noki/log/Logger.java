package com.noki.log;

import com.noki.database.DbException;
import java.util.ArrayList;

public class Logger {

  private int id;
  private String content;
  private String logTime;
  private String grade;
  private String address;
  private String ipAddress;
  private String oper;
  private String title;

  public Logger() {

    this.content = "";
    this.logTime = null;
    this.grade = "0";
    this.address = "";
    this.ipAddress = "";
    this.oper = "0";
    this.title = "";
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setLogTime(String logTime) {
    this.logTime = logTime;
  }

  public String getLogTime() {
    return logTime;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getGrade() {
    return grade;
  }

  public void setOper(String oper) {
    this.oper = oper;
  }

  public String getOper() {
    return oper;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public int write() throws DbException {
    return 0;
  }

  public ArrayList read() throws DbException {
    return null;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
