package com.df.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.df.controller.IsEmpty;
import com.df.model.Students;

public class StudentsOperation extends Dao{
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	//注册 insert
	public int register(Students user)
	{
		int res = 0;
		conn = getConnection();
		sql = "insert into Students values(?,?,?,?,?,?,?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getsId());
			psmt.setString(2, user.getsUserName());
			psmt.setString(3, user.getsPassword());
			psmt.setString(4, user.getsName());
			psmt.setString(5, user.getClassName());
			psmt.setString(6, user.getIsHeadman());
			psmt.setInt(7, 0);
			psmt.setInt(8, -1);
			res = psmt.executeUpdate();
			System.out.println("注册成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("注册失败");
		}finally{
			closeConnection(conn);
		}
		
		return res;
	}
	
	
	//登陆 select 
	public Students login(Students user)
	{
		Students returnUser = null;
		conn = getConnection();
		sql="select * from Students where sUserName=? and sPassword=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getsUserName());
			psmt.setString(2, user.getsPassword());
			rs = psmt.executeQuery();
			if(rs.next())
			{	
				returnUser=new Students();
				returnUser.setsUserName(rs.getString("sUserName"));
				returnUser.setsPassword(rs.getString("sPassword"));
			}
			System.out.println("登录成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("登录失败");
		}finally{
			closeConnection(conn);
		}
		return returnUser;
	}
	
	//判断是否登录
	public Boolean isOnline(Students user)
	{
		String isLoginStr = "";
		Boolean isLoginBool;
		conn = getConnection();
		sql = "select isLogin from Students where sUserName= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getsUserName());
			rs = psmt.executeQuery();
			if(rs.next())
				isLoginStr = rs.getString("isLogin");//获取数据库中列名为isLogin的值
			isLoginBool = isLoginStr.equals("-1");//进行判断其是否已经在线
			return isLoginBool;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closeConnection(conn);
		}
	}
	
	
	//判断是否是组长
	public Boolean isHeadman(Students user)
	{
		String isHeadmanStr = "";
		Boolean isHeadman;
		conn = getConnection();
		sql = "select isHeadman from Students where sUserName= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getsUserName());
			rs = psmt.executeQuery();
			if(rs.next())
				isHeadmanStr = rs.getString("isHeadman");
			isHeadman = isHeadmanStr.equals("-1");//等于-1时该用户为非组长用户
			return isHeadman;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closeConnection(conn);
		}
	}
	
	//判断用户名是否已经存在
	public Boolean isExist(Students user)
	{
		conn = getConnection();
		sql = "select * from Students where sUserName= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getsUserName());
			rs = psmt.executeQuery();
			if(rs.next())
				return true;//表示该用户名已经存在
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}finally{
			closeConnection(conn);
		}
	}
	
	//登录账号时改变Students表中isLogin的状态
	public Boolean changeIsLogin(Students user)
	{
		int res = 0;
		conn = getConnection();
		sql = "update Students set isLogin = " + "1" + " where sUserName= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getsUserName());
			res = psmt.executeUpdate();
			if(res > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closeConnection(conn);
		}
	}
	
	//退出程序时改变Students表中isLogin的状态
	public Boolean changeLoginOut(String user)
	{
		int res = 0;
		conn = getConnection();
		sql = "update Students set isLogin = " + "-1" + " where sUserName= '" + user + "'";
		try {
			psmt = conn.prepareStatement(sql);
			res = psmt.executeUpdate();
			if(res > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closeConnection(conn);
		}
	}
	
	//获取当前Students中最大的groupId
	public int maxGroupId()
	{
		int groupId = 0;
		conn = getConnection();
		sql = "select MAX(groupId) from Students";
		try{
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				groupId = rs.getInt(1); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
		return groupId;
	}

	
	//更改groupId值
	public void setGroupId(int maxGroupId, String sUserName)
	{
		int res = 0;
		maxGroupId++;
		conn = getConnection();
		sql = "update Students set groupId = " + maxGroupId + " where sUserName = " + "'" + sUserName + "'";
		try{
			psmt = conn.prepareStatement(sql);
			res = psmt.executeUpdate();
			if(res > 0)
				System.out.println("更新组号完成");
			else
				System.out.println("更新组号失败");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
	}
	
	
	//更新3个表,存储过程
	public void insertAll(String sUserName)
	{
		conn = getConnection();
		CallableStatement c;
		try {
			c = conn.prepareCall("{call ist_gdname(?)}");
			c.setString(1, sUserName);
			c.execute(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
	}
	
}
