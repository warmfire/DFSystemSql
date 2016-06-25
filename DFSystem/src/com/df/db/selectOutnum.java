package com.df.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectOutnum extends Dao{
	private int count;
	private int num;
	private Connection conn = null;
	private ResultSet rs;
	private PreparedStatement pstm;
	public int selectgn(){
		conn = getConnection();
		String sql = "select COUNT(distinct(groupId)) as sgp from Students";  
		PreparedStatement pstm;
		try {
			count = 0;
			pstm = conn.prepareStatement(sql);
			// ִ�в�ѯ
			rs = pstm.executeQuery();
			while(rs.next()) count = Integer.parseInt(rs.getString("sgp"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int selectonum(String grpid){
		conn = getConnection();
		String sql = "select * from Students where groupId = '"+grpid+"'";  
		try {
			count = 0;
			pstm = conn.prepareStatement(sql);
			// ִ�в�ѯ
			rs = pstm.executeQuery();
			// �����ж�������¼
			while(rs.next()){
				count++;
			}
			// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public Object[][] selectOutnum (String grpid){
		Object[][] info = null;
		try {
			int count1 =  selectonum(grpid);
			info = new Object[count1][9];
			conn = getConnection();
			String sql = "select * from Students where groupId = '"+grpid+"'";  
			count = 0;
			pstm = conn.prepareStatement(sql);
			// ִ�в�ѯ
			rs = pstm.executeQuery();
			// �����ж�������¼
			while(rs.next()){
				info[count][0] = rs.getString("sName");
				info[count][1] = rs.getString("sId");
				info[count][2] = rs.getString("groupId");
				info[count][3] = "0";
				info[count][4] = "0";
				info[count][5] = "0";
				info[count][6] = "0";
				info[count][7] = "0";
				info[count][8] = "0";
				count++;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(conn);
		}
		return info;
	}
}
