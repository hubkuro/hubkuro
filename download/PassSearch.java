package download;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mySQLConection.DownloadDAO;
import java.io.File;
import java.io.FilenameFilter;

/**
 * Servlet implementation class PassSearch
 */
@WebServlet("/download/pass/search")
public class PassSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PassSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String str_pass = request.getParameter("pass");

		DownloadDAO inst = new DownloadDAO();
		String pass = inst.extraction(str_pass);

		/* 検索 */

		// フィルタを作成する
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File file, String str) {

				// 指定文字列でフィルタする
				if (str.indexOf(pass) != -1) {
					return true;
				} else {
					return false;
				}
			}
		};

		// listFilesメソッドを使用して一覧を取得する
		LinkedList<String> list_hai = new LinkedList<String>();
		Arrays.stream(new File("WEB-INF" + File.pathSeparator + "uploadFile").listFiles(filter)).forEach(s -> list_hai.add("WEB-INF" + File.pathSeparator + "uploadFile" + s.getName()));
		
		request.setAttribute("imageList",list_hai.get(0));
		
		session.setAttribute("pass", pass);
		
		response.sendRedirect("/download/inbox");
	}

}
