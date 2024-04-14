package com.AnkitIndia.jwtauthentication.security.services;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
@Service
public class FileService_Imp implements FileService{
	
	private enum ResourceType {
		FILE_SYSTEM,
		CLASSPATH
	}
	
	
	
	public Resource getFileSystem(String filename, HttpServletResponse response) {
		return getResource(filename, response, ResourceType.FILE_SYSTEM);
	}
	
	private Resource getResource(String filename, HttpServletResponse response, ResourceType resourceType) {
		response.setContentType("text/csv; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
	//	response.setHeader("filename", filename);
 
		Resource resource = null;
		
		resource = new FileSystemResource(filename);
		
			
		
 
		return resource;
	}
 
}
