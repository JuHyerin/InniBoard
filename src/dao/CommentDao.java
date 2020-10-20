package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dbConnection.DataBase;

public class CommentDao {
	private DataBase db = new DataBase();
	public void	insertComment(int postId, String writer, String comment) {
		db.connectDatabase();
		String sql = "insert into comment (post_id, writer, created_at, updated_at, comment) values (?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.setString(2, writer);
			pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setString(5, comment);
			
			pstmt.executeUpdate();
			System.out.println("insert comment 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
	}
	
	public ResultSet getCommentByPostid(int postId) {
		db.connectDatabase();
		
		String sql = "select writer, updated_at, comment from comment where post_id=" + postId;
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
	
	public int countAllComment(int postId) {
		db.connectDatabase();
		
		String sql = "select count(*) from comment where is_deleted=0 and post_id="+postId;
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
		db.disconnectDataBase();
		System.out.println(count);
		return count;
	}
	
	public ResultSet getPagedComments(int postId, int startIndex, int size) {
		db.connectDatabase();
		
		String sql = "select comment_id, writer, updated_at, comment "
				+ "from comment "
				+ "where is_deleted=0 and post_id=? "
				+ "order by updated_at desc limit ?,?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setInt(1, postId);
			pstmt.setInt(2, startIndex);
			pstmt.setInt(3, size);
		
			rs = pstmt.executeQuery();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
		
		return rs;
	}

	public void deleteCommentById(int commentId) {
		db.connectDatabase();
		String sql = "update comment set deleted_at=?, is_deleted=1 where comment_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			pstmt.setInt(2, commentId);
			pstmt.executeUpdate();
			System.out.println("delete 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.disconnectDataBase();
		
	}
}
