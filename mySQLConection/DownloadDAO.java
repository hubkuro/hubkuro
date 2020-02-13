package mySQLConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DownloadDAO {

	public String extraction(String pass) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();

		// Date型の日時をCalendar型に変換
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// Calendar型の日時をDate型に戻す
		Date d1 = calendar.getTime();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/hubkuro?serverTimezone=JST",
					"root", "root");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT  pass FROM  share_file WHERE pass = &pass" + pass + ", expiration_date > " + sdf.format(d1) + ", status = 0;")) {
				if(rs.next()) {
					return rs.getString("pass");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DBに接続されませんでした。");
		}
		return null;
		
	}

}
