package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbConnection.DataBase;

public class UserDao {
	private DataBase db = new DataBase();
	public ResultSet getUserById(String id) {
		db.connectDatabase();
		String sql = "select * from user where id like ?" ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = db.getConn().prepareStatement(sql);
			pstmt.setString(1, );
			rs = pstmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		db.disconnectDataBase();
		return rs;
	}
	
}
