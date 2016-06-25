package com.df.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	
	private static String dbName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbUrl = "jdbc:sqlserver://10.144.244.17:1433;DatabaseName=DFSystem";
	private static String dbUserName = "sa";
	private static String dbUserPsw = "123456";
	private Connection conn = null;

	public Connection getConnection()
	{
		try {
			Class.forName(dbName);
			System.out.println("数据库加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(dbUrl, dbUserName, dbUserPsw);
			System.out.println("数据库连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}



	public void closeConnection(Connection connection)
	{
		try {
			if(connection != null)
				connection.close();
			System.out.println("连接已关闭");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("连接关闭失败");
		}
	}
	
}
