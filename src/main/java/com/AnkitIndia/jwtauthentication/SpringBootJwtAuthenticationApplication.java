
package com.AnkitIndia.jwtauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootJwtAuthenticationApplication extends SpringBootServletInitializer {

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootJwtAuthenticationApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootJwtAuthenticationApplication.class);
	}
	
}

