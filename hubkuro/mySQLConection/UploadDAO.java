package mySQLConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UploadDAO {

	public boolean add(String pass, String expiration) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sampledb?serverTimezone=JST",
					"root", "root");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("INSERT INTO  share_file (pass, expiration_date) VALUES (" + pass + ", " + expiration + ");")) {
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
