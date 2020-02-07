package download;

import java.io.IOException;

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
		File[] list_hai = new File("C:\\Users\\183106\\eclipse-workspace\\passconfirm\\WebContent\\WEB-INF\\uploadFile")
				.listFiles(filter);
		
		request.setAttribute("",list_hai[0]);
		
		HttpSession session = request.getSession();
		session.setAttribute("pass", pass);
		
		response.sendRedirect("/download/inbox");
	}

}
