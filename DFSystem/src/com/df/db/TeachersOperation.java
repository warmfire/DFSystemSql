package com.df.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.df.model.Teachers;

public class TeachersOperation extends Dao{

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	//×¢²á insert
	public int register(Teachers user)
	{
		int res = 0;
		conn = getConnection();
		sql = "insert into Teachers values(?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.gettUserName());
			psmt.setString(2, user.gettPassword());
			res = psmt.executeUpdate();
			System.out.println("×¢²á³É¹¦");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("×¢²áÊ§°Ü");
		}finally{
			closeConnection(conn);
		}
		
		return res;
	}
	
	
	//µÇÂ½ select 
	public Teachers login(Teachers user)
	{
		Teachers returnUser = null;
		conn = getConnection();
		sql="select * from Teachers where tName=? and tPassword=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.gettUserName());
			psmt.setString(2, user.gettPassword());
			rs = psmt.executeQuery();
			if(rs.next())
			{	
				returnUser=new Teachers();
				returnUser.settUserName(rs.getString(1));
				returnUser.settPassword(rs.getString(2));
			}
			System.out.println("µÇÂ¼³É¹¦");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("µÇÂ¼Ê§°Ü");
		}finally{
			closeConnection(conn);
		}
		return returnUser;
	}
	
}
