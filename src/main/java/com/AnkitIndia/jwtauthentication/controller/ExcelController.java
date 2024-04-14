package com.AnkitIndia.jwtauthentication.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.AnkitIndia.ExcelImport.ExcelHelper;
import com.AnkitIndia.ExcelImport.ResponseMessage;
import com.AnkitIndia.jwtauthentication.security.services.State_masterService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")



public class ExcelController
{
	  @Autowired
	  State_masterService fileService;
	
	  @PostMapping("/uploadstatemasterexcel")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(required=false,value="files") MultipartFile file) {
	    String message = "";
       // System.out.println("tuhin here  "+file);
	    try 
	      {
		    if (ExcelHelper.hasExcelFormat(file)) 
		    {
		      try 
		      {
		    	  System.out.println("in try catch ");
		    	  fileService.importexcel(file);
	
		        message = "Uploaded the file successfully: " + file.getOriginalFilename();
		        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		      } 
		      catch (Exception e) 
		      {
		        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		      }
		    }
		    else 
		    {
		    	System.out.println("somethng wrong ");
		    }
	
		    message = "Please upload an excel file!";
	      }	
		  catch (Exception e) 
		  {
			  message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		  }
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }

}
