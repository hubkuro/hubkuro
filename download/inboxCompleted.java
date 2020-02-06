package download;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InBox
 */
@WebServlet("/download/Completed")
public class inboxCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public inboxCompleted() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		HttpSession session = request.getSession();
		
		response.setContentType("application/octet-stream;charset=shift_jis");
		response.setHeader("Content-Disposition", "attachment; filename=" +  URLEncoder.encode(File.pathSeparator + "Web-INF" + File.separator + "uploadFile" + session.getAttribute("pass"), "UTF-8"));
		response.setHeader("Content-Transfer-Encoding", "binary");

		response.sendRedirect("/download/Completed");

	}

}
