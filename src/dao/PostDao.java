package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbConnection.DataBase;

public class PostDao {
	private DataBase db = new DataBase();
	
	public ResultSet getAllPost() {
		db.connectDatabase();
		
		String sql = "select post_id, title, writer, created_at from post order by created_at desc;";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = db.getConn().createStatement();
			rs = stmt.executeQuery(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
		
		return rs;
	}
	
	public ResultSet getContentsBtId(int postId) {
		db.connectDatabase();
		
		String sql = "select post_id, title, contents, writer, created_at, updated_at from post where post_id=" + postId;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = db.getConn().createStatement();
			rs = stmt.executeQuery(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
		
		return rs;
	}
	
	public int countAllPost() {
		db.connectDatabase();
		
		String sql = "select count(*) from post";
		Statement stmt = null;
		int count = 0;
        ResultSet rs;

		try {
			stmt = db.getConn().createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) count = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        //System.out.println("Total rows : " + count);
        
		db.disconnectDataBase();
		
		return count;
		
	}

	public ResultSet getPagedPost(int startIndex, int size) {
		db.connectDatabase();
		
		String sql = "select post_id, title, writer, created_at from post order by created_at desc limit " + startIndex +","+ size;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = db.getConn().createStatement();
			rs = stmt.executeQuery(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
		
		return rs;
	}
}
