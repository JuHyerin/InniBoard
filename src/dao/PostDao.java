package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	
	public ResultSet getContentsById(int postId) {
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
		
		String sql = "select post_id, title, writer, created_at from post where is_deleted=0 order by created_at desc limit " + startIndex +","+ size;
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
	
	public void	insertPost(String title, String writer, String contents) {
		db.connectDatabase();
		String sql = "insert into post (title, writer, created_at, contents) values (?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(4, contents);
			
			pstmt.executeUpdate();
			System.out.println("insert 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
	}

	public void updatePost(int postId, String title, String contents) {
		db.connectDatabase();
		String sql = "update post set title=?, contents=?, updated_at=? where post_id=?";
		//String sql = "update post set title=?, contents=? where post_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, contents);
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(4, postId);
			
			pstmt.executeUpdate();
			System.out.println("update 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*sql = "update post set updated_at=? where post_id=?";
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.executeUpdate();
			System.out.println("update 시간 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		db.disconnectDataBase();
	
	}

	public void deletePost(int postId) {
		db.connectDatabase();
		//String sql = "delete from post where post_id=?";
		String sql = "update post set deleted_at=?, is_deleted=1 where post_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(2, postId);
			pstmt.executeUpdate();
			System.out.println("delete 완료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
	}
}
