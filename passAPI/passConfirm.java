package passAPI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mySQLConection.PassDAO;

/**
 * Servlet implementation class passConfirm
 */
@WebServlet("/api/upload/pass/Confirm")
public class PassConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("pass") instanceof String) {
			String ress = null;
			String pass = request.getParameter("pass");
			Map<String, String> resMap = new HashMap<>();
			if(pass.matches("[a-zA-Z0-9_]{6,10}")) {
				PassDAO pdao = new PassDAO();
				String existence;
				if(pdao.confirm(pass)) {
					existence = "True";
				}else {
					existence = "False";
				}
				resMap.put("pass", pass);
				resMap.put("ret", existence);
				ress = this.mapToJson(resMap);
			}else {
				resMap.put("pass", pass);
				resMap.put("ret", "False");
				ress = this.mapToJson(resMap);
			}
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(ress);
		}
		
	}
	
	
	private String mapToJson(Map<String, String> resMap) {
		StringBuilder buf1 = new StringBuilder();
		buf1.append('{');
		for(Map.Entry<String, String> entry : resMap.entrySet()){
			buf1.append('\"');
			buf1.append(entry.getKey());
			buf1.append("\":\"");
			buf1.append(entry.getValue());
			buf1.append('\"');
			buf1.append(',');
			}
		String json = buf1.toString();
		StringBuilder buf2 = new StringBuilder();
		buf2.append(json.substring(0, json.length() - 1));
		buf2.append('}');
		return buf2.toString();
	}

}
