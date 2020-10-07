package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Post;

public class DataBase {
	private Connection conn = null;
	private String connectMsg = null;
	
	
	public String getConnectMsg() {
		return connectMsg;
	}

	public void setConnectMsg(String connectMsg) {
		this.connectMsg = connectMsg;
	}
	
	public void connectDatabase() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		  
		String url = "jdbc:mariadb://localhost:3306/jspdb";
		String user = "jspdb"; 
		String password = "0000"; 
		try { 
			conn = DriverManager.getConnection(url, user, password); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.setConnectMsg(conn.toString()+"<br/>"+"localhost:3306/jspdb 연동 성공");
		}
	}

	public void disconnectDataBase() {
		try { 
			conn.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public List<Post> getAllPost() {
		String sql = "select * from post";
		Statement stmt = null;
		ResultSet rs = null;
		List<Post> posts = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getInt("post_id"));
				post.setTitle(rs.getString("title"));
				post.setContents(rs.getString("contents"));
				post.setCreatedAt(rs.getDate("created_at"));
				post.setUpdatedAt(rs.getDate("updated_at"));
				post.setDeletedAt(rs.getDate("deleted_at"));
				post.setIsDeleted(rs.getBoolean("is_deleted"));
				
				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}
	
	
}
