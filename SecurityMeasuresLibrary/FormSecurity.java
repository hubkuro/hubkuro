package SecurityMeasuresLibrary;

import java.util.Random;

import javax.servlet.http.HttpSession;

public class FormSecurity {

	public String oneTimeToken(HttpSession session) {
		Random r = new Random();
		int c;
		StringBuilder buf = new StringBuilder();
		char moji = ' ';
		for (int i = 0; i < 30; i++) {

			c = r.nextInt(3);
			switch (c) {
			case 0:
				moji = '0';
				moji += r.nextInt(9);
				break;
			case 1:
				moji = 'a';
				moji += r.nextInt(25);
				break;
			case 2:
				moji = 'A';
				moji += r.nextInt(25);
				break;
			}
			buf.append(moji);
		}
		session.setAttribute("token", buf.toString());
		return buf.toString();
	}

	public boolean tokenCheck(String token, HttpSession session) {
		return token.equals(session.getAttribute("token"));
	}

}