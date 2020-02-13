package mySQLConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PassDAO {
	
	public boolean confirm(String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hubkuro?serverTimezone=JST",
					"root", "root");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT pass FROM share_file WHERE pass = " + pass + ";")) {
				if(rs.next()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBに接続されませんでした。");
		}
		return false;
		
	}


}
