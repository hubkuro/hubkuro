package upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		HttpSession session=request.getSession();
		String pass=(String)session.getAttribute("pass");
		String expiration=(String)session.getAttribute(("periodo"));

		UploadDAO instA=new UploadDAO();
		System.out.println(expiration);
		instA.add(pass, Calculation(expiration));//有効期限計算＆DBに追加
		StringBuilder fileName = new StringBuilder();
		fileName.append("WEB-INF/uploadFile/");
		fileName.append(pass);


		List<Part> imageList = (LinkedList<Part>) session.getAttribute("imagedata");

		for(Part image : imageList) {

			try {
				image.write(fileName.toString() + File.pathSeparator + getFilename(image));
			} catch (IOException e) {
				System.out.println("ファイルにアクセスできません。");
			}
		}

		session.invalidate();
		

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

	private String getFilename(Part part) {
	    for (String cd : part.getHeader("Content-Disposition").split(";")) {
	      if (cd.trim().startsWith("filename")) {
	          return cd.substring(cd.indexOf('=') + 1).trim()
	                  .replace("\"", "");
	      }
	  }

	  return null;
	}

}
