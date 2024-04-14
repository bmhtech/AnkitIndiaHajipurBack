package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Area_master;
import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.User_roles;
import com.AnkitIndia.jwtauthentication.model.Userroles;
import com.AnkitIndia.jwtauthentication.repository.RoleRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.User_rolesRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.StatusDTO;

@Service
public class RoleService_Imp implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	User_rolesRepository user_rolesRepository;
	
	public Role save(Role role)
	{
		long slno=0;
		if(roleRepository.countId() != null ) {
			slno=Long.parseLong(roleRepository.countId());
		}
		String prefix="RL";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		role.setRole_id(gen_sno);
		role.setParent_role(role.getName()); 
		role.setParent_role_id(role.getRole_id());
		
		return roleRepository.save(role);
	}
	
	public List<Role> getRoles()
	{
		return roleRepository.findAll();
	}
	
	public List<Role> getRolesThruUserId(String user){
		List<Role> roleList=new ArrayList<Role>();
		List<User_roles> roles=new ArrayList<User_roles>();
		
		roles.addAll(user_rolesRepository.getRoleIdThUIdList(userRepository.getUserDetails(user).getId()));
		
		for(User_roles role : roles)
		{
			roleList.add(roleRepository.getRoleIdByIdWOOP(role.getRole_id()));
		}
		return roleList;
	}
	
	@Transactional
	public StatusDTO updateUserRoles(Userroles uRoles){
		String result="";
		Role role=new Role();
		long slno=0;
		if(roleRepository.countId() != null ) {
			slno=Long.parseLong(roleRepository.countId());
		}
		String prefix="RL";
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		role.setRole_id(gen_sno);
		role.setName(uRoles.getRole_id());
		role.setDescription(role.getName());
		role.setRoleaccessjson(uRoles.getRoleaccessjson());
		if(uRoles.getParent_role_id().compareTo("0")==0) {
			role.setParent_role_id(gen_sno);
			role.setParent_role(role.getName());
		}
		else {
			role.setParent_role_id(uRoles.getParent_role_id());
			role.setParent_role(roleRepository.getRoleDtls(uRoles.getParent_role_id()).getName());
		}
		Role x=roleRepository.save(role);
		System.err.println("Got Val :"+x.getRole_id());
		//int x=user_rolesRepository.updateUserRoles(userRepository.getUserDetails(uRoles.getUser_id()).getId(),roleRepository.getRoleDtls(uRoles.getRole_id()).getId(),uRoles.getRoleaccessjson());
		
		if(x.getRole_id().compareTo("") == 0) {result="Role Update Successfully !!!";}
		else {result="Update failed !!!";}
		
		Type listType = new TypeToken<StatusDTO>() {}.getType();
		StatusDTO grpStatus = new ModelMapper().map(result,listType);
		grpStatus.setStatus(result);
		
		return grpStatus;
	}
	
	public User_roles getUserRoles(String user,String role){
		User_roles uRoles=user_rolesRepository.getUserRoles(userRepository.getUserDetails(user).getId(),roleRepository.getRoleDtls(role).getId());
		return uRoles;
	}
}
