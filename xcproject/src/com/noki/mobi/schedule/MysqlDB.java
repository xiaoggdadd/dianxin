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
            //ResourceBundle bundle = ResourceBundle.getBundle("property"); //class.com��
        	
            pro.load(new FileInputStream("mysqldb.properties")); //tomcat bin ��
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
     * �������ݿ�
     * @throws DBException
     */

    public void connectDb() throws DbException {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://" + dbip +":3306"+ "/" + dbname;
            url="jdbc:mysql://192.168.0.113:3306/zhoujian";
            conn = DriverManager.getConnection(url,dbuser, dbpassword);
            System.out.println("mysql����"+conn);
        } catch (Exception e) {
            System.out.println(conn+"----------error=" + e);
            throw new DbException("�������ݿ����error in connectDb()!\r\n������" + e);
        }
        if (conn == null) { //
            System.err.print("conn is null");
        }
    }

    /**
     * ��ѯ���м�¼
     * @param sqltxt ��ѯ���
     * @return ResultSet �����
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
            throw new DbException("���ݿ��ѯ����(" + e.getErrorCode() +
                                  ")��error in queryAll()!\r\n������" + e);
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
            throw new DbException("���ݿ��ѯ����(" + e.getErrorCode() +
                                  ")��error in queryAll()!\r\n������" + e);
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
            throw new DbException("���ݿ��ѯ����(" + e.getErrorCode() +
                                  ")��error in queryAll()!\r\n������" + e);
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
         throw new DbException("���ݿ��ѯ����("+e.getErrorCode()+")��error in queryAll()!\r\n������"+e);
       }

         return rs;
     }
     */

    /**
     * ��ѯָ�������Ľ����
     * @param sqltxt ��ѯ���
     * @param maxrow ��������
     * @return �����
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
            throw new DbException("���ݿ��ѯ����(" + e.getErrorCode() +
                                  ")��error in query()!\r\n������" + e);
        }
        return rs;
    }

    /**
     * ִ�д洢���̻����ݿ�������
     * @param sqltxt ִ�����
     * @return �޸ĳɹ�������0û��ִ��
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
                throw new DbException("�洢����ִ�г���(" + e.getErrorCode() +
                                      ")��error in update()!\r\n������" + e);
            }
        } else {
            try {
                stat = conn.createStatement();
                i = stat.executeUpdate(qry);
            } catch (SQLException e) {
                throw new DbException("���ݿ��������(" + e.getErrorCode() +
                                      ")��error in update()!\r\n������" + e);
            }
        }
        return i;
    }

    public void setAutoCommit(boolean b) throws DbException {
        try {
            conn.setAutoCommit(b);
        } catch (SQLException e) {
            throw new DbException("���ݿ��������(" + e.getErrorCode() +
                                  ")��error in setAutoCommit()!\r\n������" + e);
        }
    }

    public void rollback() throws DbException {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new DbException("���ݿ��������(" + e.getErrorCode() +
                                  ")��error in rollback()!\r\n������" + e);
        }
    }

    public void commit() throws DbException {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new DbException("���ݿ��������(" + e.getErrorCode() +
                                  ")��error in commit()!\r\n������" + e);
        }
    }

    /**
     * ������ִ����䣬�������ݵ���
     * @param sqltxt ִ���������
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
                throw new DbException("{" + counts.length + "}�������������(" +
                                      e.getErrorCode() +
                                      ")��error in updateBatch()!\r\n������" + e);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (java.sql.SQLException ex) {
                throw new DbException("������ع�����(" + ex.getErrorCode() +
                                      ")��error in updateBatch()!\r\n������" + ex);
            }
            throw new DbException("�������������(" + e.getErrorCode() +
                                  ")��error in updateBatch()!\r\n������" + e);
        }

    }

    /**
     * ������ִ�����
     * @param sqltxt ִ���������
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
                throw new DbException("{" + counts.length + "}�������������(" +
                                      e.getErrorCode() +
                                      ")��error in updateBatch()!\r\n������" + e);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (java.sql.SQLException ex) {
                throw new DbException("������ع�����(" + ex.getErrorCode() +
                                      ")��error in updateBatch()!\r\n������" + ex);
            }

            throw new DbException("�������������(" + e.getErrorCode() +
                                  ")��error in updateBatch()!\r\n������" + e);
        }
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (java.sql.SQLException e) {
            throw new DbException("�������ύ����(" + e.getErrorCode() +
                                  ")��error in updateBatch()!\r\n������" + e);
        }
    }

    /**
     * ��ÿɱ������
     * @param sqltxt ���ʺŵ�sql���
     * @return �ɱ������
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
            throw new DbException("��ÿɱ���������(" + e.getErrorCode() +
                                  ")��error in getPreparedStatement()!\r\n������" +
                                  e);
        }
        return ps;
    }

    /**
     * ���connection����
     * @return connection����
     * @throws DBException
     */
    public Connection getConnection() throws DbException {
        try {
            if (conn == null) {
                connectDb();
            }
        } catch (DbException e) {
            throw new DbException(
                    "���Connection�������error in getConnection()!\r\n������" +
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

            //System.out.println("---------- ���ݿ�رգ� --------");
        } catch (SQLException e) {
            throw new DbException("�ر����ݿ����ӳ���(" + e.getErrorCode() +
                                  ")��error in close()!\r\n������" + e);
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

            //System.out.println("---------- ���ݿ�رգ� --------");
        } catch (SQLException e) {
            throw new DbException("�ر����ݿ����ӳ���(" + e.getErrorCode() +
                                  ")��error in close()!\r\n������" + e);
        }
    }

    public CallableStatement prepareCall(String call) throws DbException {

        try {
            cs = conn.prepareCall(call);
        } catch (SQLException e) {
            throw new DbException("�õ�CallableStatement����" + e);
        }
        return cs;

    }

}
