package com.AnkitIndia.jwtauthentication.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AnkitIndia.jwtauthentication.message.request.LoginForm;
import com.AnkitIndia.jwtauthentication.message.request.SignUpForm;
import com.AnkitIndia.jwtauthentication.message.response.JwtResponse;
import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.repository.RoleRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.security.jwt.JwtProvider;
import com.AnkitIndia.jwtauthentication.model.RefreshToken;
import com.AnkitIndia.jwtauthentication.model.User_role_id;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/AnkitIndiaHajipur")
//@RequestMapping("/AnkitIndia")
//@RequestMapping("/AnkitIndiaTesting")
//@RequestMapping("/MeghnaBidi")
//@RequestMapping("/MeghanaPanMasala")
//@RequestMapping("/IndianSpices")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    /*@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
    	
    	System.out.println("sign in");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }*/
    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
    	System.out.println("signin");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        //String username=jwtProvider.getUserNameFromJwtToken(jwt);
		
		JwtResponse abc=new JwtResponse(jwt);
		
		return ResponseEntity.ok(abc);
    }

    @PostMapping("/signup")
    public User registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
    
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        
        Set<User_role_id> strRoles = signUpRequest.getRole();
        
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
        	
        	Role adminRole = roleRepository.getRoleNamePId(role.getUser_role_id())
	                .orElseThrow(() -> new RuntimeException("admin Fail! -> Cause: User Role not find."));
	    			roles.add(adminRole);        	
        });
        
        user.setRoles(roles);
        return userRepository.save(user);
        //return ResponseEntity.ok().body("User registered successfully!");
    }
    
    @PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshToken refreshToken)
	{
		String gottoken=jwtProvider.refreshToken(refreshToken);
		
		JwtResponse abc=new JwtResponse(gottoken);
		
		return ResponseEntity.ok(abc);
	}
    
}