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
	 *            数据库链接
	 * @param ps
	 *            可编译语句
	 * @param stat
	 *            语句
	 * @param rs
	 *            结果集
	 */
	public Connection conn = null;
	private PreparedStatement ps = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private CallableStatement cs = null;// 用于执行 SQL 存储过程的接口
	private DataSource ds = null;
	private static Properties pro = null;

	/**
	 * @author xuzehua 数据库资源构造方法
	 */
	public DataBase() {
		
		pro = new Properties();
		try {
			InputStream is = DataBase.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			pro.load(is);// 从输入流中读取属性列表（键和元素对）。
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
	 * 获得connection对象
	 * 
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
					"获得Connection对象出错，error in getConnection()!\r\n错误是" + e);
		}
		return conn;
	}

	/**
	 * 连接数据库
	 * 
	 * @throws DBException
	 */

	public void connectDb() throws DbException {
		Context context = null;
//		try {
//			context = new InitialContext();
//			//查找数据源
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
			String className = pro.getProperty("ClassName");// 获取键值driver
			Class.forName(className);
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("DBerror=" + e);
			throw new DbException("连接数据库出错，error in connectDb()!\r\n错误是" + e);
		}
		if (conn == null) {//
			System.out.println("conn is null122");
			System.err.print("conn is null");
		}
	}

	/**
	 * 资源释放回收 sta pre实用同一个
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
	 * 查询所有记录
	 * 
	 * @param sqltxt
	 *            查询语句
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
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		} 

		return rs;
	}

	// 更改2012-12-2
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
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		}

		return rs;
	}

	// 更改2012-12-2
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
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		}

		return rs;
	}

	// 更改2012-12-2
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
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		}

		return rs;
	}

	// 更改2013-07-27
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
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		}

		return rs;
	}

	public ResultSet queryAll(String sqltxt, int resultSetType,
			int resultSetConcurrency) throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			stat = conn.createStatement(resultSetType, resultSetConcurrency);// 创建一个
			// Statement
			// 对象，该对象将生成具有给定类型和并发性的
			// ResultSet
			// 对象
			rs = stat.executeQuery(sqltxt);
		} catch (SQLException e) {
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		}

		return rs;
	}

	public ResultSet queryAlls(String sqltxt) throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			stat = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,// 该常量指示可滚动并且通常受
					// ResultSet
					// 底层数据更改影响的
					// ResultSet
					// 对象的类型。
					ResultSet.CONCUR_READ_ONLY);// 该常量指示不可以更新的 ResultSet
			// 对象的并发模式。
			rs = stat.executeQuery(sqltxt);
		} catch (SQLException e) {
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in queryAll()!\r\n错误是" + e);
		}

		return rs;
	}

	/**
	 * 查询指定行数的结果集
	 * 
	 * @param sqltxt
	 *            查询语句
	 * @param maxrow
	 *            返回行数
	 * @return 结果集
	 * @throws DBException
	 */
	public ResultSet query(String sqltxt, int maxrow) throws DbException {
		if (conn == null) {
			this.connectDb();
		}
		try {
			ps = conn.prepareStatement(sqltxt, ResultSet.CONCUR_READ_ONLY,// 创建一个
					// PreparedStatement
					// 对象，该对象将生成具有给定类型和并发性的
					// ResultSet
					// 对象。
					ResultSet.TYPE_SCROLL_INSENSITIVE);
			ps.setMaxRows(maxrow);// 将此 Statement 对象生成的所有 ResultSet
			// 对象可以包含的最大行数限制设置为给定数。如果超过了该限制，则直接撤消多出的行。
			rs = ps.executeQuery();
		} catch (SQLException e) {
			throw new DbException("数据库查询出错(" + e.getErrorCode()
					+ ")，error in query()!\r\n错误是" + e);
		}
		return rs;
	}

	/**
	 * 执行存储过程或数据库操作语句
	 * 
	 * @param sqltxt
	 *            执行语句
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
		String qry = sqltxt.trim();// 返回字符串的副本，忽略前导空白和尾部空白。此方法可用于截去字符串开头和末尾的空白（如上所述）。
		if (qry.substring(0, 7).equals("execute")) {
			try {
				
				ps = conn.prepareStatement(sqltxt.substring(7));
				i = ps.executeUpdate();
			} catch (SQLException e) {
				throw new DbException("存储过程执行出错(" + e.getErrorCode()
						+ ")，error in update()!\r\n错误是" + e);
			}
		} else {
			try {
				stat = conn.createStatement();
				i = stat.executeUpdate(qry);
			} catch (SQLException e) {
				throw new DbException("数据库操作出错(" + e.getErrorCode()
						+ ")，error in update()!\r\n错误是" + e);
			}
		}
		
		return i;
	}

	/***
	 * 新增数据并返回主键序列值
	 * @param sqltxt
	 * @param columnNames 格式 new String[]{"id"}
	 * @return 序列值
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
			throw new DbException("数据库操作出错(" + e.getErrorCode()
					+ ")，error in update()!\r\n错误是" + e);
		}
		return pk;
	}
	
	public void setAutoCommit(boolean b) throws DbException {
		try {
			conn.setAutoCommit(b);
		} catch (SQLException e) {
			throw new DbException("数据库操作出错(" + e.getErrorCode()
					+ ")，error in setAutoCommit()!\r\n错误是" + e);
		}
	}

	public void rollback() throws DbException {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DbException("数据库操作出错(" + e.getErrorCode()
					+ ")，error in rollback()!\r\n错误是" + e);
		}
	}

	public void commit() throws DbException {
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new DbException("数据库操作出错(" + e.getErrorCode()
					+ ")，error in commit()!\r\n错误是" + e);
		}
	}

	/**
	 * 批处理执行语句，用于数据导入
	 * 
	 * @param sqltxt
	 *            执行语句数组
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
				System.out.println("插入数量：" + b);
			} catch (BatchUpdateException e) {
				int[] counts = e.getUpdateCounts();
				throw new DbException("{" + counts.length + "}批处理操作出错("
						+ e.getErrorCode() + ")，error in updateBatch()!\r\n错误是"
						+ e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (java.sql.SQLException ex) {
				throw new DbException("批处理回滚出错(" + ex.getErrorCode()
						+ ")，error in updateBatch()!\r\n错误是" + ex);
			}
			throw new DbException("批处理操作出错(" + e.getErrorCode()
					+ ")，error in updateBatch()!\r\n错误是" + e);
		}

	}

	/**
	 * 批处理执行语句
	 * 
	 * @param sqltxt
	 *            执行语句数组
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
				throw new DbException("{" + counts.length + "}批处理操作出错("
						+ e.getErrorCode() + ")，error in updateBatch()!\r\n错误是"
						+ e);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (java.sql.SQLException ex) {
				throw new DbException("批处理回滚出错(" + ex.getErrorCode()
						+ ")，error in updateBatch()!\r\n错误是" + ex);
			}

			throw new DbException("批处理操作出错(" + e.getErrorCode()
					+ ")，error in updateBatch()!\r\n错误是" + e);
		}
		try {
			conn.commit();
			conn.setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			throw new DbException("批处理提交出错(" + e.getErrorCode()
					+ ")，error in updateBatch()!\r\n错误是" + e);
		}
	}

	/**
	 * 获得可编译语句
	 * 
	 * @param sqltxt
	 *            带问号的sql语句
	 * @return 可编译语句
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
			throw new DbException("获得可编译语句出错(" + e.getErrorCode()
					+ ")，error in getPreparedStatement()!\r\n错误是" + e);
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

			// System.out.println("---------- 数据库关闭！ --------");
		} catch (SQLException e) {
			throw new DbException("关闭数据库连接出错(" + e.getErrorCode()
					+ ")，error in close()!\r\n错误是" + e);
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

			// System.out.println("---------- 数据库关闭！ --------");
		} catch (SQLException e) {
			throw new DbException("关闭数据库连接出错(" + e.getErrorCode()
					+ ")，error in close()!\r\n错误是" + e);
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
	/**
	 * Date: 2018/3/21
	 * name: fuxiuli
	 * content ：用于报表整合查询的DAO方法
	 */
	//Class<T> clazz表示传入某一类型的字节码，就返回该字节码的对象，要封装的对象的类型
	public <T> List<T> Query(String sql,Object[] paramValue, Class<T> clazz){
		//返回的集合
		List<T> list = new ArrayList<T>();
		T t = null;
		//2.创建prepareStatement对象
		try {
			if (conn == null) {
				this.connectDb();
			}
			ps = conn.prepareStatement(sql);
			//3.通过元数据得到sql语句占位符参数的个数
			int count = ps.getParameterMetaData().getParameterCount();
			//4.设置每个参数的值
			if(paramValue!=null&&paramValue.length>0){
				for(int i = 0;i<paramValue.length;i++){
					ps.setObject(i+1,paramValue[i]);
				}
			}
			//5.执行查询
			rs = ps.executeQuery();
			System.out.println("当前执行的查询报表的sql:"+sql);
			//6.获取结果集元数据
			ResultSetMetaData rmd = rs.getMetaData();
			//7.通过元数据获取列的个数
			int rsCount = rmd.getColumnCount();
			//8.遍历结果集
			while(rs.next()){
				//封装的对象
				t = clazz.newInstance();
				//遍历每一行每一列
				for(int i = 0;i<rsCount;i++){
					//通过结果集元数据得到每一列的名称
					String columName = rmd.getColumnName(i+1);
					//通过每一列的名称获取其对应的值
					Object value = rs.getObject(columName);
					//9.封装：得到了属性及其对应值，将其封装到对象中去，封装的对象类型就为传入的字节码的对象类型（这里用beanutil组件进行封装）
					//columName对应实体类属性名称，包括大小写toLowerCase()方法将数据库列转换为小写对应实体类属性，
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
