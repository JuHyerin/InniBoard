package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbConnection.DataBase;

public class UserDao {
	private DataBase db = new DataBase();
	public ResultSet getUserById(String id,String pwd ) {
		db.connectDatabase();
		String sql = "select * from user where id = '" + id + "' and pwd = '" + pwd + "'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = db.getConn().createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		db.disconnectDataBase();
		return rs;
	}
	
}
