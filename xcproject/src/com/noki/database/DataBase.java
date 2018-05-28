package com.noki.database;

import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

public class DataBase {
	/**
	 * @param conn
	 *            ���ݿ�����
	 * @param ps
	 *            �ɱ������
	 * @param stat
	 *            ���
	 * @param rs
	 *            �����
	 */
	public Connection conn = null;
	private PreparedStatement ps = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private CallableStatement cs = null;// ����ִ�� SQL �洢���̵Ľӿ�
	private DataSource ds = null;
	private static Properties pro = null;

	/**
	 * @author xuzehua ���ݿ���Դ���췽��
	 */
	public DataBase() {
		
		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			pro.load(is);// ���������ж�ȡ�����б�����Ԫ�ضԣ���
			is.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		//bak
		/* InitialContext ctx;
		  try {
		  ctx = new InitialContext();
		  ds = (DataSource) ctx.lookup("java:comp/env/jdbc/unicom92");
		  } catch (NamingException e) {
		  TODO Auto-generated catch block
		  e.printStackTrace();
		  }*/
		
	}

	/**
	 * ���connection����
	 * 
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
					"���Connection�������error in getConnection()!\r\n������" + e);
		}
		return conn;
	}

	/**
	 * �������ݿ�
	 * 
	 * @throws DBException
	 */

	public void connectDb() throws DbException {
		Context context = null;
//		try {
//			context = new InitialContext();
//			//��������Դ
//			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbcnews");
//			  conn = ds.getConnection();
//		} catch (NamingException e) {
//			
//			e.printStackTrace();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}
	
		try {
			//
			//conn = ds.getConnection();
			String className = pro.getProperty("ClassName");// ��ȡ��ֵdriver
			Class.forName(className);
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("DBerror=" + e);
			throw new DbException("�������ݿ����error in connectDb()!\r\n������" + e);
		}
		if (conn == null) {//
			System.out.println("conn is null122");
			System.err.print("conn is null");
		}
	}

	/**
	 * ��Դ�ͷŻ��� sta preʵ��ͬһ��
	 * 
	 * @param res
	 * @param sta
	 * @param connection
	 */
	public void free(ResultSet res, Statement sta, Connection connection) {

		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sta != null) {
					sta.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��ѯ���м�¼
	 * 
	 * @param sqltxt
	 *            ��ѯ���
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
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		} 

		return rs;
	}

	// ����2012-12-2
	public ResultSet queryAll003(String sqltxt, String ammeterid)
			throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, ammeterid);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		}

		return rs;
	}

	// ����2012-12-2
	public ResultSet queryAll002(String sqltxt, Properties pro)
			throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, pro.getProperty("1"));
			ps.setString(2, pro.getProperty("2"));
			ps.setString(3, pro.getProperty("3"));
			ps.setString(4, pro.getProperty("4"));
			ps.setString(5, pro.getProperty("5"));
			ps.setString(6, pro.getProperty("6"));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		}

		return rs;
	}

	// ����2012-12-2
	public ResultSet queryAll001(String sqltxt, Properties pro)
			throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, pro.getProperty("1"));
			ps.setString(2, pro.getProperty("2"));

			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		}

		return rs;
	}

	// ����2013-07-27
	public ResultSet queryAllyf003(String sqltxt, Properties pro)
			throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			ps = conn.prepareStatement(sqltxt);
			ps.setString(1, pro.getProperty("1"));
			ps.setString(2, pro.getProperty("2"));
			ps.setString(3, pro.getProperty("3"));

			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		}

		return rs;
	}

	public ResultSet queryAll(String sqltxt, int resultSetType,
			int resultSetConcurrency) throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			stat = conn.createStatement(resultSetType, resultSetConcurrency);// ����һ��
			// Statement
			// ���󣬸ö������ɾ��и������ͺͲ����Ե�
			// ResultSet
			// ����
			rs = stat.executeQuery(sqltxt);
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		}

		return rs;
	}

	public ResultSet queryAlls(String sqltxt) throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,// �ó���ָʾ�ɹ�������ͨ����
					// ResultSet
					// �ײ����ݸ���Ӱ���
					// ResultSet
					// ��������͡�
					ResultSet.CONCUR_READ_ONLY);// �ó���ָʾ�����Ը��µ� ResultSet
			// ����Ĳ���ģʽ��
			rs = stat.executeQuery(sqltxt);
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in queryAll()!\r\n������" + e);
		}

		return rs;
	}

	/**
	 * ��ѯָ�������Ľ����
	 * 
	 * @param sqltxt
	 *            ��ѯ���
	 * @param maxrow
	 *            ��������
	 * @return �����
	 * @throws DBException
	 */
	public ResultSet query(String sqltxt, int maxrow) throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			ps = conn.prepareStatement(sqltxt, ResultSet.CONCUR_READ_ONLY,// ����һ��
					// PreparedStatement
					// ���󣬸ö������ɾ��и������ͺͲ����Ե�
					// ResultSet
					// ����
					ResultSet.TYPE_SCROLL_INSENSITIVE);
			ps.setMaxRows(maxrow);// ���� Statement �������ɵ����� ResultSet
			// ������԰��������������������Ϊ����������������˸����ƣ���ֱ�ӳ���������С�
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new DbException("���ݿ��ѯ����(" + e.getErrorCode()
					+ ")��error in query()!\r\n������" + e);
		}
		return rs;
	}

	/**
	 * ִ�д洢���̻����ݿ�������
	 * 
	 * @param sqltxt
	 *            ִ�����
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
		String qry = sqltxt.trim();// �����ַ����ĸ���������ǰ���հ׺�β���հס��˷��������ڽ�ȥ�ַ�����ͷ��ĩβ�Ŀհף�������������
		if (qry.substring(0, 7).equals("execute")) {
			try {
				
				ps = conn.prepareStatement(sqltxt.substring(7));
				i = ps.executeUpdate();
			} catch (SQLException e) {
				throw new DbException("�洢����ִ�г���(" + e.getErrorCode()
						+ ")��error in update()!\r\n������" + e);
			}
		} else {
			try {
				stat = conn.createStatement();
				i = stat.executeUpdate(qry);
			} catch (SQLException e) {
				throw new DbException("���ݿ��������(" + e.getErrorCode()
						+ ")��error in update()!\r\n������" + e);
			}
		}
		
		return i;
	}

	/***
	 * �������ݲ�������������ֵ
	 * @param sqltxt
	 * @param columnNames ��ʽ new String[]{"id"}
	 * @return ����ֵ
	 * @throws DbException
	 */
	public int update(String sqltxt, String... columnNames) throws DbException{
	
		if(columnNames == null || columnNames.length== 0){
			return update(sqltxt);
		}
		int pk = 0;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sqltxt, columnNames);
			rs = stat.getGeneratedKeys();
			if(rs.next()){
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DbException("���ݿ��������(" + e.getErrorCode()
					+ ")��error in update()!\r\n������" + e);
		}
		return pk;
	}
	
	public void setAutoCommit(boolean b) throws DbException {
		try {
			conn.setAutoCommit(b);
		} catch (SQLException e) {
			throw new DbException("���ݿ��������(" + e.getErrorCode()
					+ ")��error in setAutoCommit()!\r\n������" + e);
		}
	}

	public void rollback() throws DbException {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DbException("���ݿ��������(" + e.getErrorCode()
					+ ")��error in rollback()!\r\n������" + e);
		}
	}

	public void commit() throws DbException {
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new DbException("���ݿ��������(" + e.getErrorCode()
					+ ")��error in commit()!\r\n������" + e);
		}
	}

	/**
	 * ������ִ����䣬�������ݵ���
	 * 
	 * @param sqltxt
	 *            ִ���������
	 * @throws DBException
	 */
	public void updateBatchDr(String[] sqltxt) throws DbException {
		int i;
		try {
			stat = conn.createStatement();
			int b = 0;
			for (i = 0; i < sqltxt.length; i++) {
				b++;
				if (sqltxt[i] != null && !(sqltxt[i].trim()).equals("")) {
					stat.addBatch(sqltxt[i]);
				}
			}
			try {
				stat.executeBatch();
				System.out.println("����������" + b);
			} catch (BatchUpdateException e) {
				int[] counts = e.getUpdateCounts();
				throw new DbException("{" + counts.length + "}�������������("
						+ e.getErrorCode() + ")��error in updateBatch()!\r\n������"
						+ e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (java.sql.SQLException ex) {
				throw new DbException("������ع�����(" + ex.getErrorCode()
						+ ")��error in updateBatch()!\r\n������" + ex);
			}
			throw new DbException("�������������(" + e.getErrorCode()
					+ ")��error in updateBatch()!\r\n������" + e);
		}

	}

	/**
	 * ������ִ�����
	 * 
	 * @param sqltxt
	 *            ִ���������
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
				/*
				 * int j=0; for (j=0;j<counts.length;j++){
				 * System.out.println(counts[j]); if
				 * (counts[j]==Statement.EXECUTE_FAILED) break; }
				 * System.out.println("counts.length=" + counts.length);
				 * System.out.println("j=" + j);
				 */
				throw new DbException("{" + counts.length + "}�������������("
						+ e.getErrorCode() + ")��error in updateBatch()!\r\n������"
						+ e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (java.sql.SQLException ex) {
				throw new DbException("������ع�����(" + ex.getErrorCode()
						+ ")��error in updateBatch()!\r\n������" + ex);
			}

			throw new DbException("�������������(" + e.getErrorCode()
					+ ")��error in updateBatch()!\r\n������" + e);
		}
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			throw new DbException("�������ύ����(" + e.getErrorCode()
					+ ")��error in updateBatch()!\r\n������" + e);
		}
	}

	/**
	 * ��ÿɱ������
	 * 
	 * @param sqltxt
	 *            ���ʺŵ�sql���
	 * @return �ɱ������
	 * @throws DBException
	 */
	public PreparedStatement getPreparedStatement(String sqltxt)
			throws DbException {
		try {
			if (conn == null) {
				connectDb();
			}
			ps = conn.prepareStatement(sqltxt);

		} catch (SQLException e) {
			throw new DbException("��ÿɱ���������(" + e.getErrorCode()
					+ ")��error in getPreparedStatement()!\r\n������" + e);
		}
		return ps;
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

			// System.out.println("---------- ���ݿ�رգ� --------");
		} catch (SQLException e) {
			throw new DbException("�ر����ݿ����ӳ���(" + e.getErrorCode()
					+ ")��error in close()!\r\n������" + e);
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

			// System.out.println("---------- ���ݿ�رգ� --------");
		} catch (SQLException e) {
			throw new DbException("�ر����ݿ����ӳ���(" + e.getErrorCode()
					+ ")��error in close()!\r\n������" + e);
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
	/**
	 * Date: 2018/3/21
	 * name: fuxiuli
	 * content �����ڱ������ϲ�ѯ��DAO����
	 */
	//Class<T> clazz��ʾ����ĳһ���͵��ֽ��룬�ͷ��ظ��ֽ���Ķ���Ҫ��װ�Ķ��������
	public <T> List<T> Query(String sql,Object[] paramValue, Class<T> clazz){
		//���صļ���
		List<T> list = new ArrayList<T>();
		T t = null;
		//2.����prepareStatement����
		try {
			if (conn == null) {
				this.connectDb();
			}
			ps = conn.prepareStatement(sql);
			//3.ͨ��Ԫ���ݵõ�sql���ռλ�������ĸ���
			int count = ps.getParameterMetaData().getParameterCount();
			//4.����ÿ��������ֵ
			if(paramValue!=null&&paramValue.length>0){
				for(int i = 0;i<paramValue.length;i++){
					ps.setObject(i+1,paramValue[i]);
				}
			}
			//5.ִ�в�ѯ
			rs = ps.executeQuery();
			System.out.println("��ǰִ�еĲ�ѯ�����sql:"+sql);
			//6.��ȡ�����Ԫ����
			ResultSetMetaData rmd = rs.getMetaData();
			//7.ͨ��Ԫ���ݻ�ȡ�еĸ���
			int rsCount = rmd.getColumnCount();
			//8.���������
			while(rs.next()){
				//��װ�Ķ���
				t = clazz.newInstance();
				//����ÿһ��ÿһ��
				for(int i = 0;i<rsCount;i++){
					//ͨ�������Ԫ���ݵõ�ÿһ�е�����
					String columName = rmd.getColumnName(i+1);
					//ͨ��ÿһ�е����ƻ�ȡ���Ӧ��ֵ
					Object value = rs.getObject(columName);
					//9.��װ���õ������Լ����Ӧֵ�������װ��������ȥ����װ�Ķ������;�Ϊ������ֽ���Ķ������ͣ�������beanutil������з�װ��
					//columName��Ӧʵ�����������ƣ�������СдtoLowerCase()���������ݿ���ת��ΪСд��Ӧʵ�������ԣ�
					BeanUtils.copyProperty(t, columName.toLowerCase(), value);
				}
				list.add(t);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return list;
	}
}
