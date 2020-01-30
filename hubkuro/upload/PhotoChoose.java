package upload;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		StringBuilder sb = new StringBuilder();
    for (Part part : request.getParts()) {
      if (part.getName().equals("imagedata")) {
          String name = getFilename(part);
          part.write(name);
          sb.append(name).append("<br>");
      }
    
    part.write(getServletContext().getRealPath(File.separator + "WEB-INF" + File.separator + "uploadFile") + File.separator + name);
		File imageFile;
		
		File image;
		BufferedImage b;
		try {
			b = ImageIO.read(image);
		} catch (IOException e1) {
			return;
		}
		int ww=b.getWidth();
		int hh=b.getHeight();
		
		BufferedImage b2=new BufferedImage(ww,hh,BufferedImage.TYPE_INT_RGB);
		Graphics g2=b2.getGraphics();
		g2.drawImage(b,0,0,null);
		g2.dispose();
		
		try {
			ImageIO.write(b2, extension, resultImage);
		} catch (IOException e) {
			return;
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
