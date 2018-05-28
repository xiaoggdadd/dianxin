package com.noki.mobi.sys.javabean;


import java.sql.ResultSet;
import java.sql.SQLException;
import com.noki.database.DataBase;
import com.noki.database.DbException;


public class NoticeFormBean {
  private String title;
  private String content;
  private String createTime;
  private String creator;
  public NoticeFormBean() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }



  public synchronized NoticeFormBean getOneNotice(String noticeId) {
    NoticeFormBean bean = new NoticeFormBean();
    DataBase db = new DataBase();
    ResultSet rs = null;
    String sql = "SELECT N.TITLE,N.CONTENT,N.CREATIME,N.CREATOR FROM NOTICE N WHERE N.NOTICEID=" +
        noticeId;
    try {
      db.connectDb();
      rs = db.queryAll(sql);
      while (rs.next()) {
        bean.setTitle(rs.getString(1));
        bean.setContent(rs.getString(2));
        bean.setCreateTime(rs.getString(3).substring(0,10));
        bean.setCreator(rs.getString(4));
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
    }
    catch (DbException de) {
      de.printStackTrace();
    }

    finally {
      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException se) {
          se.printStackTrace();
        }
      }
      try {
        db.close();
      }
      catch (DbException de) {
        de.printStackTrace();
      }

    }

    return bean;
  }

}
