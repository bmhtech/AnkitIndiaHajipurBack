package com.AnkitIndia.jwtauthentication.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.AnkitIndia.jwtauthentication.security.services.UserPrinciple;
import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.User_roles;
import com.AnkitIndia.jwtauthentication.model.RefreshToken;
import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.repository.RoleRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.User_rolesRepository;

import java.util.ArrayList;
import java.util.Date;

@Component
public class JwtProvider {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	User_rolesRepository  user_rolesRepository;
	
	@Autowired
	RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${grokonez.app.jwtSecret}")
    private String jwtSecret;

    @Value("${grokonez.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	//refreshToken(authToken);
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        
        return false;
    }
    
    public boolean validateJwtTokenWithRefreshJwtIfExpired(String authToken) {
    	boolean status=false;
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            status=true;
        }  catch (ExpiredJwtException e) {
        	//refreshToken(authToken);
        	status=false;
            logger.error("Expired JWT token -> Message: {}", e);
        } 
        
        return status;
    }
    
    public String refreshToken(RefreshToken refreshToken) {
		
		String username= null;
		//username=getUserNameFromJwtToken(refreshToken.getUsername()); // retrive username from encrypted jwt token...
		User user=userRepository.checkuser(refreshToken.getUsername()).orElseThrow(() -> new RuntimeException("User is not recognized!!!"));
		String newToken="";
		//System.out.println("status: "+validateJwtToken(refreshToken.getUsername())+",username: "+Jwts.builder().getClass());
		if(user.getUsername() != null)
		{
			newToken=   Jwts.builder()
	                .setSubject(refreshToken.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
		}
		else
		{
			if(validateJwtToken(refreshToken.getUsername()))
			{
				System.out.println("if status: "+validateJwtToken(refreshToken.getUsername())+",username: "+Jwts.builder().getClass());
				newToken=   Jwts.builder()
		                .setSubject(username)
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
			}
			else if(!validateJwtTokenWithRefreshJwtIfExpired(refreshToken.getUsername())) // if token is expired new token will generate
			{
				System.out.println("else if status: "+validateJwtToken(refreshToken.getUsername())+",username: "+Jwts.builder().getClass());
				//validateJwtTokenWithRefreshJwtIfExpired
				newToken=   Jwts.builder()
		                .setSubject(username)
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
				
			}
			else
			{
				System.out.println("else status: "+validateJwtToken(refreshToken.getUsername())+",username: "+Jwts.builder().getClass());
				throw new AuthorizationServiceException("Invalid token claims");
			}
		}
		
		return newToken;
	}
    
    public ArrayList<String> getUserRoles(String user) {
    	
		ArrayList<User_roles> idList=new ArrayList<User_roles>();
		ArrayList<String> RoleList=new ArrayList<String>();

		long user_id=userRepository.getTIdThroughUserName(user);
		idList.addAll(user_rolesRepository.getRoleIdThUIdList(user_id));
		for(int i=0;i<idList.size();i++)
		{
			long role_id=idList.get(i).getRole_id();
			Role role=roleRepository.getRoleIdById(role_id).orElse(null);
			RoleList.add(role.getRole_id());
		}
		return RoleList;
	}
    
    
    
}