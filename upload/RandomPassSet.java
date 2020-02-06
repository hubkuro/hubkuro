package upload;

import java.util.Random;

public class RandomPassSet {

	public String Set() {
		Random r = new Random();
		int c;
		StringBuilder buf = new StringBuilder();
		char moji = ' ';
		for(int i = 0 ; i < 10 ; i++){
			
			c = r.nextInt(4);
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
				case 3:
					moji = '_';
					break;
        		}
			buf.append(moji);
		}
		return buf.toString();
	}

}