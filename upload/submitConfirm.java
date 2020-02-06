package upload;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.regex.Pattern;

/**
 * Servlet implementation class PassConfirm
 */
@WebServlet("/upload/Confirm")
public class submitConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String str_pass=request.getParameter("pass");
		String str_expiration=request.getParameter("expiration");
		
			
			if(Pattern.matches("^[0-9a-zA-Z]+$", str_pass)) {
				//OK
				HttpSession session = request.getSession();
				session.setAttribute("pass",str_pass );//passをセッションに追加
				session.setAttribute("expiration", str_expiration);//有効期限をセッションに追加
				response.sendRedirect("/upload/Confirm");
				
			}else {
				//ダメ
				response.sendRedirect("/upload/pass");
			}
			
			
	}
}