package com.AnkitIndia.FileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtil {

	
	
	
	
	@Transactional
	public static final String fileUpload(@RequestParam("files") MultipartFile files,String fileName,String filefolders) throws IOException
	{
		 
			 
		
		System.out.println("pathspecified :: "+filefolders);
			
				
				
		createDirIfNotExist(filefolders);
			 
		     String  files_name =filefolders+fileName+files.getOriginalFilename();
		     File convertFile = new File(filefolders+fileName+files.getOriginalFilename());
             convertFile.createNewFile();
             FileOutputStream fout = new FileOutputStream(convertFile);
             fout.write(files.getBytes());
             fout.close();
		      
System.out.println(files_name);
		            return files_name;
	
	
	}
	
	
	private static void createDirIfNotExist(String filefolders)
	{
       //create directory to save the files     
		File directory = new File(filefolders);
       if (! directory.exists()){
            directory.mkdir();
        }
    }
	
}
