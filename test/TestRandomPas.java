package test;

import SecurityMeasuresLibrary.FormSecurity;
import upload.RandomPassSet;

public class TestRandomPas {

	public static void main(String[] args) {
		RandomPassSet rps = new RandomPassSet();
		System.out.println(rps.Set());
		
		FormSecurity fs = new FormSecurity();
		System.out.println(fs.oneTimeToken(null));
	}

}
