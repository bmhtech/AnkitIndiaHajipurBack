package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AnkitIndia.jwtauthentication.model.Sales_reg_dynamic;
import com.AnkitIndia.jwtauthentication.model.Sales_reg_master;
import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.model.User_roles;
import com.AnkitIndia.jwtauthentication.repository.RoleRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.User_rolesRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.Sales_register_detailsDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.UserDTO;

@Service
public class UserService_Imp implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	User_rolesRepository user_rolesRepository;
	
	 @Autowired
	    PasswordEncoder encoder;
	
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}

	public List<UserDTO> getRolesNameList(String role)
	{
		
		String roleid=roleRepository.getidByRoleName(role);	
		List<Long> userRoleId=new ArrayList<Long>();
		userRoleId.addAll(user_rolesRepository.getUserRolesIdById(Long.valueOf(roleid)));	
				
		List<User> rolesList=new ArrayList<User>();
		for(int i=0;i<userRoleId.size();i++) 
		{
			
			rolesList.addAll(userRepository.getUserRolesNameList(userRoleId.get(i)));
		}
		
		List<User> allData = rolesList
				  .stream()
				  .sorted(Comparator.comparing(User::getId))
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();
		
		List<UserDTO> use_dtolist = new ModelMapper().map(allData,listType);
		
		return use_dtolist;
	}
	
	
	public List<User> getUserEmailDuplicateCheck()
	{
		List<User> userlist=new ArrayList<User>();
		userlist.addAll(userRepository.getUserEmailDuplicateCheck());
		
		return userlist;
	}
	
	public Boolean getUserEmailPswdDuplicateCheck(String username,String password)
	{
		boolean result=false;
		
		
		List<User> usermailpswdlist=new ArrayList<User>();
		usermailpswdlist.addAll(userRepository.getUserEmailPswdDuplicateCheck(username));
		
		System.out.println("pswd/"+encoder.encode(password)+"//"+usermailpswdlist.toString());
		if(usermailpswdlist.contains(encoder.encode(password)))
		{
			result=true;
		}
		else
		{
			result=false;
		}
		 //encoder.encode(
		
		return result;
	}
	
}
