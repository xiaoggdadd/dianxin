package com.noki.log;

import com.noki.database.DbException;
import com.noki.database.DataBase;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.noki.util.CTime;
import java.util.ArrayList;
import java.sql.ResultSet;

public class DbLog
    extends Logger {

  private String userName;
  private String title;
  public void setUserName(String username) {
    this.userName = username;
  }

  public String getUserName() {
    return userName;
  }

  /**
   * д��־
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write() throws DbException {
    int result = 0;
    DataBase db = null;
    try {
      db = new DataBase();
      db.connectDb();
      /*
      PreparedStatement ps = db.getPreparedStatement(
          "insert into LOGS(content,logtime,"
          + "address,accountid,ipaddress,title) values (?,to_date('" + CTime.formatWholeDate() + "','yyyy-mm-dd hh24:mi:ss'),?,?,?,?)");
      System.out.println();
      ps.setString(1, getContent());
      ps.setString(2, getAddress());
      ps.setString(3, getOper());
      ps.setString(4, getIpAddress());
      ps.setString(5, getTitle());
      result = ps.executeUpdate();
      */
     StringBuffer sql = new StringBuffer();
     sql.append("insert into LOGS(content,logtime,address,accountid,ipaddress,title) ");
     sql.append("values('"+getContent()+"',to_date('" + CTime.formatWholeDate() + "','yyyy-mm-dd hh24:mi:ss')");
     sql.append(",'"+getAddress()+"','"+getOper()+"','"+getIpAddress()+"','"+getTitle()+"')");
     System.out.println("��־");
     result = db.update(sql.toString());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (db != null) {
          db.close();
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return result;

  }

  /**
   * д��־
   * @param content ��־����
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write(String content) {
    int result = 0;
    try {
      setContent(content);
      result = this.write();
    }
    catch (Exception e) {

      // throw new DbException("ϵͳ��־�������error in write" + e);
    }
    return result;

  }

  /**
   * д��־
   * @param content ��־����
   * @param oper ������
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write(String content, String oper) {
    int result = 0;
    try {
      setContent(content);
      setOper(oper);
      result = this.write();
    }
    catch (Exception e) {
      //throw new DbException("ϵͳ��־�������error in write" + e);
    }
    return result;

  }

  /**
   * д��־
   * @param content ��־����
   * @param oper ������
   * @param ipaddress ip��ַ
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write(String content, String oper, String ipAddress) {
    int result = 0;
    try {
      setContent(content);
      setOper(oper);
      setIpAddress(ipAddress);
      result = this.write();
    }
    catch (Exception e) {
      //throw new DbException("ϵͳ��־�������error in write" + e);
    }
    return result;

  }

  /**
   * д��־
   * @param content ��־����
   * @param oper ������
   * @param ipaddress ip��ַ
   * @param title ��Ŀ
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write(String content, String oper, String ipAddress, String title) {
    int result = 0;
    try {
      setTitle(title);
      setContent(content);
      setOper(oper);
      setIpAddress(ipAddress);
      result = this.write();
    }
    catch (Exception e) {
      //throw new DbException("ϵͳ��־�������error in write" + e);
    }
    return result;

  }

  /**
   * д��־
   * @param content ��־����
   * @param address д��־�ĳ�����
   * @param oper ������
   * @param ipaddress ip��ַ
   * @param title ��Ŀ
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write(String content, String oper, String ipAddress,
                   String address, String title) {
    int result = 0;
    try {
      setTitle(title);
      setContent(content);
      setAddress(address);
      setOper(oper);
      setIpAddress(ipAddress);
      result = this.write();
    }
    catch (Exception e) {
      //throw new DbException("ϵͳ��־�������error in write" + e);
    }
    return result;

  }

  /**
   * д��־
   * @param content ��־����
   * @param grade ��־����. 0��info���� 1 error����
   * @param address д��־�ĳ�����
   * @param oper ������
   * @param ipaddress ip��ַ
   * @param title ��Ŀ
   * @return 0���ɹ���1�ɹ�
   * @throws DbException
   */
  public int write(String content, String oper, String ipAddress,
                   String address, String grade, String title) {
    int result = 0;
    try {
      setTitle(title);
      setContent(content);
      setGrade(grade);
      setAddress(address);
      setOper(oper);
      setIpAddress(ipAddress);
      result = this.write();
    }
    catch (Exception e) {
      e.printStackTrace();

      //throw new DbException("ϵͳ��־�������error in write" + e);
    }
    return result;
  }

  /**
   * ��ѯ�û�������
   * @param userid
   * @return
   * @throws DbException
   */
  public static ArrayList selectAll() {

    ArrayList list = new ArrayList();
    DataBase db = null;
    ResultSet rs = null;
    try {
      DbLog temp = null;
      db = new DataBase();
      db.connectDb();
      String sql = "select * from logs order by logtime desc";
      rs = db.queryAll(sql);
      if (rs != null) {
        while (rs.next()) {
          temp = new DbLog();
          temp.setId(rs.getInt("id"));
          temp.setAddress(rs.getString("address"));
          temp.setContent(rs.getString("content"));
          temp.setGrade(rs.getString("grade"));
          temp.setIpAddress(rs.getString("ipaddress"));
          temp.setLogTime(rs.getString("logtime"));
          temp.setOper(rs.getString("oper"));
          temp.setUserName(rs.getString("username"));
          temp.setTitle(rs.getString("title"));
          list.add(temp);
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
    	  if(rs != null){
    		  rs.close();
    	  }
        if (db != null) {
          db.close();
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return list;
  }

  /**
   * ��ѯ�û�������
   * @param userid
   * @return
   * @throws DbException
   */
  /*
   public static ArrayList selectByOper(String oper) {

    ArrayList list = new ArrayList();
    DataBase db = null;
    ResultSet rs;
    try{
      DbLog temp = null;
      db = new DataBase();
      db.connectMobilebussDb();
      String sql = "select logs.*,tuser.username from "
                      +"logs,tuser where oper="+oper
                  +" and tuser.userid="+oper+" order by logtime desc";
      rs = db.queryAll(sql);
      if (rs!=null){
          while (rs.next()){
          temp = new DbLog();
          temp.setId(rs.getInt("id"));
          temp.setAddress(rs.getString("address"));
          temp.setContent(rs.getString("content"));
          temp.setGrade(rs.getString("grade"));
          temp.setIpAddress(rs.getString("ipaddress"));
          temp.setLogTime(rs.getString("logtime"));
          temp.setOper(rs.getInt("oper"));
          temp.setUserName(rs.getString("username"));
          list.add(temp);
          }
        }
    }catch (Exception e){
          e.printStackTrace();
    }finally{
      try {
                     if(db!=null)db.close();
            }catch (Exception ex) {
                    ex.printStackTrace();
            }
    }
    return list;
   }*/
  /**
     ɾ����־
   * @param contactid ��ϵ��id��
   * @return int 0-ɾ��ʧ�� 1-ɾ���ɹ�
   * @throws DbException
   */
  /*
    public int delete(String startdate,String enddate,int userid) throws DbException {
       int result = 0;
       DataBase db = null;
       try{
         db = new DataBase();
         db.connectMobilebussDb();
         StringBuffer sql = new StringBuffer();
         sql.append("delete from logs where logtime>="+startdate);
         sql.append(" 01:01:01 and logtime<="+enddate);
         sql.append(" 23:59:59");
         result = db.update(sql.toString());
    }
    catch (Exception e){
     throw new DbException("ɾ����ϵ����Ϣ����error in delete!" + e);
    }
    finally{
      db.close();
    }
    return result;
    }*/
  /**
     ɾ����־
   * @param contactid ��ϵ��id��
   * @return int 0-ɾ��ʧ�� 1-ɾ���ɹ�
   * @throws DbException
   */
  public int delete(String startdate, String enddate) throws DbException {
    int result = 0;
    DataBase db = null;
    ResultSet rs = null;
    try {
      db = new DataBase();
      db.connectDb();
      StringBuffer sql = new StringBuffer();
      sql.append("select * from logs where logtime>=to_date('" + startdate +
                 " 01:01:01','YYYY-MM-DD HH24:MI:SS')");
      sql.append(" and logtime<=to_date('" + enddate +
                 " 23:59:59','YYYY-MM-DD HH24:MI:SS')");
      System.out.println(sql);
       rs = db.queryAll(sql.toString());
      if (rs != null) {
        if (!rs.next()) {
          return 1;
        }
      }
      sql.setLength(0);
      sql.append("delete from logs where logtime>=to_date('" + startdate +
                 " 01:01:01','YYYY-MM-DD HH24:MI:SS')");
      sql.append(" and logtime<=to_date('" + enddate +
                 " 23:59:59','YYYY-MM-DD HH24:MI:SS')");
      System.out.println("sql:" + sql.toString());
      result = db.update(sql.toString());
    }
    catch (Exception e) {
      throw new DbException("ɾ����ϵ����Ϣ����error in delete!" + e);
    }
    finally {
    	 if (rs != null) {
    	        try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	      }
      if (db != null) {
        db.close();
      }
    }
    return result;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
