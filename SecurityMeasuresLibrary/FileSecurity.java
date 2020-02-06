package SecurityMeasuresLibrary;

import java.io.File;

public class FileSecurity {
	
	public String getFileFomat(File checkFile) {
		
		String name = checkFile.getName();//ファイル名をStringに
				
		String extension = name.substring(name.lastIndexOf("."));//拡張子を取得
		
		
		return extension;
		
	
		
	}

}
