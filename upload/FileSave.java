package upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mySQLConection.UploadDAO;

/**
 * Servlet implementation class FileSave
 */
@WebServlet("/upload/save")
public class FileSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileSave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		HttpSession session=request.getSession();
		String pass=(String)session.getAttribute("pass");
		String expiration=(String)session.getAttribute(("periodo"));

		UploadDAO instA=new UploadDAO();
		System.out.println(expiration);
		instA.add(pass, Calculation(expiration));//有効期限計算＆DBに追加
		session.invalidate();

		StringBuilder upFileName = new StringBuilder();
		upFileName.append("WEB-INF");
		upFileName.append(File.separator);
		upFileName.append("uploadFile");
		upFileName.append(File.separator);
		upFileName.append(pass);

		StringBuilder tpFileName = new StringBuilder();
		tpFileName.append("WEB-INF");
		tpFileName.append(File.separator);
		tpFileName.append("tempFile");
		tpFileName.append(File.separator);
		tpFileName.append(pass);

		File srcFile = new File(upFileName.toString());
		srcFile.renameTo(new File(tpFileName.toString()));

		response.sendRedirect("/upload/submit/completed");
	}

	private String Calculation(String expriration) {

		// 加算される現在時間の取得(Date型)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();

				// Date型の日時をCalendar型に変換
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				// 日時を加算する
				calendar.add(Calendar.DATE, Integer.parseInt(expriration));

				// Calendar型の日時をDate型に戻す
				Date d1 = calendar.getTime();
				System.out.println(sdf.format(d1));
				String ans=d1.toString();

				return ans;
	}



}
