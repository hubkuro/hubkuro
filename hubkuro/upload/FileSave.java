package upload;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

import mySQLConection.UploadDAO;

/**
 * Servlet implementation class FileSave
 */
@WebServlet("/FileSave")
public class FileSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileSave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		/*
		HttpSession session=request.getSession();
		String pass=(String)session.getAttribute("pass");
		String expiration=(String)session.getAttribute(("expiration"));
		*/
		
		String pass="testpass";
		String expiration="3";
		
		
		UploadDAO instA=new UploadDAO();
		System.out.println(expiration);
		instA.add(pass, Calculation(expiration));//有効期限計算＆DBに追加
		StringBuilder fileName = new StringBuilder();
		fileName.append("WEB-INF/uploadFile/");
		fileName.append(pass);
		
		File uploadFile = new File(fileName.toString());
		
		HttpSession session = request.getSession();
		List<Part> imageList = (LinkedList<Part>) session.getAttribute("imagedata");

		BufferedImage b;
		String fname;
		String extension;
		for(Part image : imageList) {
			fname = image.getName();
			fname = fname.split(".",0)[0];
			extension = fname.split(".",0)[1];
			b = ImageIO.read(image.getInputStream());
			int ww=b.getWidth();
			int hh=b.getHeight();
			BufferedImage b2 = new BufferedImage(ww,hh,BufferedImage.TYPE_INT_RGB);
			Graphics g2 = b2.getGraphics();
			g2.drawImage(b,0,0,null);
			g2.dispose();
			try {
				ImageIO.write(b2, extension, new File(fileName.toString() + fname));
			} catch (IOException e) {
				System.out.println("ファイルにアクセスできません。");
			}
		}
		
		File image = new File(pass);
		try {
			b = ImageIO.read(image);
		} catch (IOException e1) {
			return ;
		}
		
		
		
	}
	
	private String Calculation(String expriration) {
		
		// 加算される現在時間の取得(Date型)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();

				// Date型の日時をCalendar型に変換
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				// 日時を加算する
				calendar.add(Calendar.DATE, Integer.parseInt(expriration));

				// Calendar型の日時をDate型に戻す
				Date d1 = calendar.getTime();
				System.out.println(sdf.format(d1));
				String ans=d1.toString();
				
				return ans;
	}

}
