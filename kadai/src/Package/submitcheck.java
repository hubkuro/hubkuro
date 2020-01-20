package Package;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class submitcheck
 */
@WebServlet("/submitcheck")
public class submitcheck extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("auto") != null) {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title></title>");
			out.println("");
			out.println("</head>");
			out.println("<body>");
			out.println("</body>");
			out.println("</html>");
			out.println("");
		} else if (request.getParameter("manual") != null) {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title></title>");
			out.println("bbb");
			out.println("</head>");
			out.println("<body>");
			out.println("</body>");
			out.println("</html>");
			out.println("");
		}
	}

}
