package com.df.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.df.model.InGrades;

public class IntoGrades extends Dao{
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String sql = "";
	public int IntoGrades(InGrades ig){
		int res = 0;
		conn = getConnection();
		//sql = "insert into InGrades(sName,sId,groupId)"
		sql = "insert into InGrades(sName,sId,groupId,part,workloads,offer,attitude,cooperate,progress,selfgrades,othergrades,grader) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, ig.getsName());
			psmt.setString(2, ig.getsId());
			psmt.setInt(3, ig.getGroupid());
			psmt.setString(4, ig.getPart());
			psmt.setInt(5, ig.getWorkloads());
			psmt.setInt(6, ig.getOffer());
			psmt.setInt(7, ig.getAttitude());
			psmt.setInt(8, ig.getCooperate());
			psmt.setInt(9, ig.getProgress());
			psmt.setInt(10, ig.getSelfgrades());
			psmt.setInt(11, ig.getOthergrades());
			psmt.setString(12, ig.getGrader());
			res = psmt.executeUpdate();
			System.out.println("成绩提交成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("成绩提交失败");
		}finally{
			closeConnection(conn);
		}
		
		return res;
	}
}
