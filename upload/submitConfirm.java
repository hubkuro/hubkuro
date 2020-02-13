package upload;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Servlet implementation class PassConfirm
 */
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
				String pass=(String)session.getAttribute("pass");
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

				
			}else {
				//ダメ
				response.sendRedirect("/upload/pass");
			}
			
			
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