package upload;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class PhotoChoose
 */
@WebServlet("/upload/photo")
@MultipartConfig(maxFileSize=1048576)
public class PhotoChoose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoChoose() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Part> imageFile = new LinkedList();
		
    for (Part part : request.getParts()) {
      if (part.getName().equals("imagedata")) {
      	imageFile.add(part);
      }	
    }
    
    HttpSession session = request.getSession();
    session.setAttribute("ImageFile", imageFile);
    
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
