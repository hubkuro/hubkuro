package upload;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
@MultipartConfig(maxFileSize = 15728640)
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<Part> imageFile = new LinkedList<Part>();
		int fileSize = 0;
		for (Part part : request.getParts()) {
			if (part.getName().equals("imagedata")) {
				if (Objects.nonNull(ImageIO.read(part.getInputStream()))) {
					imageFile.add(part);
					fileSize += part.getSize();
				}
			}
			if (fileSize > 15728640) {
				return;
			}
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("ImageFile", imageFile);

		if(Objects.nonNull(request.getParameter("auto"))) {
			RandomPassSet rps = new RandomPassSet();
			session.setAttribute("pass", rps.Set());
			response.sendRedirect("/hubkuro/Confirm.html");
			return;
		}

		response.sendRedirect("/hubkuro/passset.html");
	}

}
