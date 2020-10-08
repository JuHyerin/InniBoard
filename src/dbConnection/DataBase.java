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
	
	public Connection getConn() {
		return conn;
	}

	//private String connectMsg = null;
	
	//public String getConnectMsg() { return connectMsg; }
	  
	//public void setConnectMsg(String connectMsg) { this.connectMsg = connectMsg;}
	 
	
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
			//this.setConnectMsg(conn.toString()+"<br/>"+"localhost:3306/jspdb 연동 성공");
		}
	}

	public void disconnectDataBase() {
		try { 
			conn.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	

	
	
}
