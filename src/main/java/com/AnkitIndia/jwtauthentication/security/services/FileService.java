package com.AnkitIndia.jwtauthentication.security.services;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;

public interface FileService {
	public Resource getFileSystem(String filename, HttpServletResponse response);

}
