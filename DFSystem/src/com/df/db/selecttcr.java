package com.df.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.df.model.TGrades;

public class selecttcr extends Dao{
	private int count;
	private int num;
	private PreparedStatement psmt = null;
	private Connection conn = null;
	public Object[][] selecttcr (){
		Object[][] info = null;
		try {
			conn = getConnection();
			String sql = "select * from TGrades";  
			PreparedStatement pstm = conn.prepareStatement(sql);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			// 计算有多少条记录
			while(rs.next()){
				count++;
			}
			rs = pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			info = new Object[count][9];
			count = 0;
			while(rs.next()){
				info[count][0] = rs.getString("sName");
				info[count][1] = rs.getString("sId");
				info[count][2] = Integer.valueOf(rs.getInt("groupId"));
				info[count][3] = Integer.valueOf(rs.getInt("allGrades"));
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
		return info;
	}
	public void deleteTgrades(){
		conn = getConnection();
		String sql = "update TGrades set allGrades = 0";  
		PreparedStatement pstm;
		try {
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			closeConnection(conn);
		}
	}
	public void insertTgrades(TGrades tg){
		conn = getConnection();
		String sql = "update TGrades set allGrades = ?,grader=? where sName = ?";  
		PreparedStatement pstm;
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, tg.getGrades());
			psmt.setString(2, tg.getGrader());
			psmt.setString(3, tg.getsName());
			psmt.executeUpdate();
			System.out.println("成绩提交成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("成绩提交失败");
		}finally{
			closeConnection(conn);
		}
	}
	public int selecttnum(){
		conn = getConnection();
		ResultSet rs;
		PreparedStatement pstm;
		String sql = "select * from TGrades";  
		try {
			count = 0;
			pstm = conn.prepareStatement(sql);
			// 执行查询
			rs = pstm.executeQuery();
			// 计算有多少条记录
			while(rs.next()){
				count++;
			}
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
