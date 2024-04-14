package com.AnkitIndia.Utility;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDriverUtil {
	

	private FileDriverUtil() {
	    // restrict instantiation
	  }

	
	  //public static final String folderPath =  "D:/AnkitIndiaDriverDocuments/";  
	  public static final String folderPath =  "/usr/ankitindiahajipur/documents/driverdocs/";	//online
	  
	  public static final Path filePath = Paths.get(folderPath);

}
