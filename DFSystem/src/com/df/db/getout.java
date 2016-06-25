package com.df.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class getout extends Dao {
	
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	public ArrayList setContent()
	{
		conn = getConnection();
		sql = "select sName,sId,groupId,AVG(cast(workloads as decimal(5,2))),"
				+ "AVG(cast(original as decimal(5,2))),AVG(cast(technology as decimal(5,2))),"
				+ "AVG(cast(beauty as decimal(5,2))),AVG(cast(express as decimal(5,2))),"
				+ "AVG(cast(allgrades as decimal(5,2))) "
				+ "from OutGrades group by sName,sId,groupId";
		ArrayList<String> list = null;
		ArrayList<ArrayList<String>> listAll = new ArrayList<ArrayList<String>>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				list = new ArrayList<String>();
				for(int j = 0; j < 9; j++){
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
