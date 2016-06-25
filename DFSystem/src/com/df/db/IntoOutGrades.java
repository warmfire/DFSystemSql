package com.df.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.df.model.InGrades;
import com.df.model.OutGrades;

public class IntoOutGrades extends Dao {
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String sql = "";
	public int IntoOutGrades(OutGrades og){
		int res = 0;
		conn = getConnection();
		sql = "insert into OutGrades(sName,sId,groupId,workloads,original,technology,beauty,express,allGrades,grader) values(?,?,?,?,?,?,?,?,?,?)";
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, og.getsName());
			psmt.setString(2, og.getsId());
			psmt.setInt(3, og.getGroupId());
			psmt.setInt(4, og.getWorkloads());
			psmt.setInt(5, og.getOriginal());
			psmt.setInt(6, og.getTechnology());
			psmt.setInt(7, og.getBeauty());
			psmt.setInt(8, og.getExpress());
			psmt.setInt(9, og.getAllGrades());
			psmt.setString(10, og.getGrader());
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
