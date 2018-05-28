package com.noki.mobi.schedule;

import java.io.FileInputStream;
import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.noki.database.DbException;

public class MysqlDB {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Statement stat = null;
    private ResultSet rs = null;
    public String path = "";
    private CallableStatement cs = null;
    private String dbip = "";
    private String dbname = "", dbuser = "", dbpassword = "";
    public MysqlDB() {
        Properties pro = new Properties();
        try {
        	//servletConfig.getServletContext().getRealPath("/");
            //ResourceBundle bundle = ResourceBundle.getBundle("property"); //class.com下
        	
            pro.load(new FileInputStream("mysqldb.properties")); //tomcat bin 下
            dbname = pro.getProperty("mysql_dbname");
            dbuser = pro.getProperty("mysql_dbuser");
            dbpassword = pro.getProperty("mysql_dbpass");
            dbip = pro.getProperty("mysql_ip");
            // System.out.println(">>>>" + path);
            System.out.println("dbname>>>>"+dbname);
            System.out.println("dbuser>>>>"+dbuser);
            System.out.println("dbpassword>>>>"+dbpassword);
            System.out.println("dbip>>>>"+dbip);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接数据库
     * @throws DBException
     */

    public void connectDb() throws DbException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://" + dbip +":3306"+ "/" + dbname;
            url="jdbc:mysql://192.168.0.113:3306/zhoujian";
            conn = DriverManager.getConnection(url,dbuser, dbpassword);
            System.out.println("mysql连接"+conn);
        } catch (Exception e) {
            System.out.println(conn+"----------error=" + e);
            throw new DbException("连接数据库出错，error in connectDb()!\r\n错误是" + e);
        }
        if (conn == null) { //
            System.err.print("conn is null");
        }
    }

    /**
     * 查询所有记录
     * @param sqltxt 查询语句
     * @return ResultSet 结果集
     * @throws DBException
     */
    public ResultSet queryAll(String sqltxt) throws DbException {
        if (conn == null) {
            this.connectDb();
        }
        try {
            ps = conn.prepareStatement(sqltxt);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new DbException("数据库查询出错(" + e.getErrorCode() +
                                  ")，error in queryAll()!\r\n错误是" + e);
        }

        return rs;
    }

    public ResultSet queryAll(String sqltxt, int resultSetType,
                              int resultSetConcurrency) throws DbException {
        if (conn == null) {
            this.connectDb();
        }
        try {
            stat = conn.createStatement(resultSetType, resultSetConcurrency);
            rs = stat.executeQuery(sqltxt);
        } catch (SQLException e) {
            throw new DbException("数据库查询出错(" + e.getErrorCode() +
                                  ")，error in queryAll()!\r\n错误是" + e);
        }

        return rs;
    }

    public ResultSet queryAlls(String sqltxt) throws DbException {
        if (conn == null) {
            this.connectDb();
        }
        try {
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
            rs = stat.executeQuery(sqltxt);
        } catch (SQLException e) {
            throw new DbException("数据库查询出错(" + e.getErrorCode() +
                                  ")，error in queryAll()!\r\n错误是" + e);
        }

        return rs;
    }

    /*
       public ResultSet queryAllc(String sqltxt) throws DbException{
       if(conn==null){
         this.connectDb();
       }
       try{
         ps=conn.prepareStatement(sqltxt);
         rs=ps.executeQuery();
       }catch(SQLException e){
         throw new DbException("数据库查询出错("+e.getErrorCode()+")，error in queryAll()!\r\n错误是"+e);
       }

         return rs;
     }
     */

    /**
     * 查询指定行数的结果集
     * @param sqltxt 查询语句
     * @param maxrow 返回行数
     * @return 结果集
     * @throws DBException
     */
    public ResultSet query(String sqltxt, int maxrow) throws DbException {
        if (conn == null) {
            this.connectDb();
        }
        try {
            ps = conn.prepareStatement(sqltxt, ResultSet.CONCUR_READ_ONLY,
                                       ResultSet.TYPE_SCROLL_INSENSITIVE);
            ps.setMaxRows(maxrow);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            throw new DbException("数据库查询出错(" + e.getErrorCode() +
                                  ")，error in query()!\r\n错误是" + e);
        }
        return rs;
    }

    /**
     * 执行存储过程或数据库操作语句
     * @param sqltxt 执行语句
     * @return 修改成功条数，0没有执行
     * @throws DBException
     */
    public int update(String sqltxt) throws DbException {
        int i;
        if (conn == null) {
            this.connectDb();
        }
        if (sqltxt == null) {
            return 0;
        }
        String qry = sqltxt.trim();
        if (qry.substring(0, 7).equals("execute")) {
            try {
                ps = conn.prepareStatement(sqltxt);
                i = ps.executeUpdate();
            } catch (SQLException e) {
                throw new DbException("存储过程执行出错(" + e.getErrorCode() +
                                      ")，error in update()!\r\n错误是" + e);
            }
        } else {
            try {
                stat = conn.createStatement();
                i = stat.executeUpdate(qry);
            } catch (SQLException e) {
                throw new DbException("数据库操作出错(" + e.getErrorCode() +
                                      ")，error in update()!\r\n错误是" + e);
            }
        }
        return i;
    }

    public void setAutoCommit(boolean b) throws DbException {
        try {
            conn.setAutoCommit(b);
        } catch (SQLException e) {
            throw new DbException("数据库操作出错(" + e.getErrorCode() +
                                  ")，error in setAutoCommit()!\r\n错误是" + e);
        }
    }

    public void rollback() throws DbException {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new DbException("数据库操作出错(" + e.getErrorCode() +
                                  ")，error in rollback()!\r\n错误是" + e);
        }
    }

    public void commit() throws DbException {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new DbException("数据库操作出错(" + e.getErrorCode() +
                                  ")，error in commit()!\r\n错误是" + e);
        }
    }

    /**
     * 批处理执行语句，用于数据导入
     * @param sqltxt 执行语句数组
     * @throws DBException
     */
    public void updateBatchDr(String[] sqltxt) throws DbException {
        int i;
        try {
            stat = conn.createStatement();
            for (i = 0; i < sqltxt.length; i++) {
                if (sqltxt[i] != null && !(sqltxt[i].trim()).equals("")) {
                    stat.addBatch(sqltxt[i]);
                }
            }
            try {
                stat.executeBatch();
            } catch (BatchUpdateException e) {
                int[] counts = e.getUpdateCounts();
                throw new DbException("{" + counts.length + "}批处理操作出错(" +
                                      e.getErrorCode() +
                                      ")，error in updateBatch()!\r\n错误是" + e);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (java.sql.SQLException ex) {
                throw new DbException("批处理回滚出错(" + ex.getErrorCode() +
                                      ")，error in updateBatch()!\r\n错误是" + ex);
            }
            throw new DbException("批处理操作出错(" + e.getErrorCode() +
                                  ")，error in updateBatch()!\r\n错误是" + e);
        }

    }

    /**
     * 批处理执行语句
     * @param sqltxt 执行语句数组
     * @throws DBException
     */
    public void updateBatch(String[] sqltxt) throws DbException {
        int i;
        if (conn == null) {
            this.connectDb();
        }
        try {
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            for (i = 0; i < sqltxt.length; i++) {
                if (sqltxt[i] != null && !(sqltxt[i].trim()).equals("")) {
                    stat.addBatch(sqltxt[i]);
                }
            }
            try {
                stat.executeBatch();
            } catch (BatchUpdateException e) {
                int[] counts = e.getUpdateCounts();
                /*int j=0;
                         for (j=0;j<counts.length;j++){
                  System.out.println(counts[j]);
                  if (counts[j]==Statement.EXECUTE_FAILED)
                    break;
                         }
                         System.out.println("counts.length=" + counts.length);
                         System.out.println("j=" + j);*/
                throw new DbException("{" + counts.length + "}批处理操作出错(" +
                                      e.getErrorCode() +
                                      ")，error in updateBatch()!\r\n错误是" + e);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (java.sql.SQLException ex) {
                throw new DbException("批处理回滚出错(" + ex.getErrorCode() +
                                      ")，error in updateBatch()!\r\n错误是" + ex);
            }

            throw new DbException("批处理操作出错(" + e.getErrorCode() +
                                  ")，error in updateBatch()!\r\n错误是" + e);
        }
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (java.sql.SQLException e) {
            throw new DbException("批处理提交出错(" + e.getErrorCode() +
                                  ")，error in updateBatch()!\r\n错误是" + e);
        }
    }

    /**
     * 获得可编译语句
     * @param sqltxt 带问号的sql语句
     * @return 可编译语句
     * @throws DBException
     */
    public PreparedStatement getPreparedStatement(String sqltxt) throws
            DbException {
        try {
            if (conn == null) {
                connectDb();
            }
            ps = conn.prepareStatement(sqltxt);

        } catch (SQLException e) {
            throw new DbException("获得可编译语句出错(" + e.getErrorCode() +
                                  ")，error in getPreparedStatement()!\r\n错误是" +
                                  e);
        }
        return ps;
    }

    /**
     * 获得connection对象
     * @return connection对象
     * @throws DBException
     */
    public Connection getConnection() throws DbException {
        try {
            if (conn == null) {
                connectDb();
            }
        } catch (DbException e) {
            throw new DbException(
                    "获得Connection对象出错，error in getConnection()!\r\n错误是" +
                    e);
        }
        return conn;
    }

    public void closer() throws DbException {

        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
                System.out.println("----------state.close-");
            }
            if (cs != null) {
                cs.close();
                cs = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
                System.out.println("----------state.close-");
            }

            //System.out.println("---------- 数据库关闭！ --------");
        } catch (SQLException e) {
            throw new DbException("关闭数据库连接出错(" + e.getErrorCode() +
                                  ")，error in close()!\r\n错误是" + e);
        }
    }

    public void close() throws DbException {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stat != null) {
                stat.close();
                stat = null;
            }
            if (cs != null) {
                cs.close();
                cs = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }

            //System.out.println("---------- 数据库关闭！ --------");
        } catch (SQLException e) {
            throw new DbException("关闭数据库连接出错(" + e.getErrorCode() +
                                  ")，error in close()!\r\n错误是" + e);
        }
    }

    public CallableStatement prepareCall(String call) throws DbException {

        try {
            cs = conn.prepareCall(call);
        } catch (SQLException e) {
            throw new DbException("得到CallableStatement出错" + e);
        }
        return cs;

    }

}
