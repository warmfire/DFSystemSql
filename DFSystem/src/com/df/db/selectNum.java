package com.df.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectNum extends Dao{
	private int count;
	private int num;
	private Connection conn = null;
	private PreparedStatement pstm;
	private ResultSet rs;
	public int selectGrpid(String name){
		try {
			conn = getConnection();
			String sql = "select groupId from Students where sUserName = '"+name+"'";  
			PreparedStatement pstm = conn.prepareStatement(sql);
			// 执行查询
			ResultSet rs = pstm.executeQuery();
			// 计算有多少条记录
			if(rs.next()){
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
		return num;
	}
	public int selectinum(int grpid){
		conn = getConnection();
		String sql = "select * from Students where groupId = " + grpid;  
		
		try {
			pstm = conn.prepareStatement(sql);
			// 执行查询
			rs = pstm.executeQuery();
			// 计算有多少条记录
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public Object[][] selectNum (int grpid){
		Object[][] info = null;
		try {
			conn = getConnection();
			selectinum(grpid);
			rs = pstm.executeQuery();
			// 将查询获得的记录数据，转换成适合生成JTable的数据形式
			info = new Object[count][11];
			count = 0;
			while(rs.next()){
				info[count][0] = rs.getString("sName");
				info[count][1] = rs.getString("sId");
				info[count][2] = Integer.valueOf(rs.getInt("groupId"));
				info[count][3] = "";
				info[count][4] = "0";
				info[count][5] = "0";
				info[count][6] = "0";
				info[count][7] = "0";
				info[count][8] = "0";
				info[count][9] = "0";
				info[count][10] = "0";
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
}
