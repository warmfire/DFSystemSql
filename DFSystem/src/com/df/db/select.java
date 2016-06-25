package com.df.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class select extends Dao {
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	public ArrayList setContent()
	{
		conn = getConnection();
		sql = "select sName,sId,groupId,COUNT(part),AVG(cast(workloads as decimal(5,2))),AVG(cast(offer as decimal(5,2))),"
				+ "AVG(cast(attitude as decimal(5,2))),AVG(cast(cooperate as decimal(5,2))),AVG(cast(progress as decimal(5,2))),"
				+ "AVG(cast(selfgrades as decimal(5,2))),AVG(cast(othergrades as decimal(5,2))) "
				+ "from InGrades"
				+ " group by sName,sId,groupId";
		ArrayList<String> list = null;
		ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				list = new ArrayList<String>();
				for(int j = 0; j < 11; j++){
					list.add(rs.getString(j+1));
				}
				listAll.add(list);
			}
			return listAll;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("成绩导出成功");
			return null;
		}finally{
			closeConnection(conn);
		}
		
	}
	
	
	
}
