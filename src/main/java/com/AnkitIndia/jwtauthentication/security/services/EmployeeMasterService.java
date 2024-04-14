package com.AnkitIndia.jwtauthentication.security.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.generators.UniqueIDTransaction;
import com.AnkitIndia.jwtauthentication.model.Bin_master;
import com.AnkitIndia.jwtauthentication.model.Employee_master;
import com.AnkitIndia.jwtauthentication.model.Role;
import com.AnkitIndia.jwtauthentication.model.User;
import com.AnkitIndia.jwtauthentication.model.User_role_id;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.EmployeeMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.RoleRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.repository.User_rolesRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.EmployeeMasterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.EmployeeMasterDTOC;
import com.AnkitIndia.jwtauthentication.responseDTO.Employee_masterDTO;
import com.AnkitIndia.jwtauthentication.responseDTO.SequenceIdDTO;
import com.AnkitIndia.jwtauthentication.security.jwt.JwtProvider;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

@Service
public class EmployeeMasterService {

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
	User_rolesRepository user_rolesRepository;
    
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Transactional
	public Employee_master save(Employee_master employee)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="EMP";
		if(employeeMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(employeeMasterRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		employee.setEmp_id(gen_sno);
		employee.setModified_type("INSERTED");
		employee.setInserted_by(userRepository.getUserDetails(employee.getUsername()).getName());
		employee.setInserted_on(ldt);
		employee.setUpdated_by("NA");
		employee.setUpdated_on(ldt);
		employee.setDeleted_by("NA");
		employee.setDeleted_on(ldt);
		employee.setPreference(employee.getPreference());
		//System.out.println("user name//"+employee.getEmp_username());
		//For Signup
		String email="";
		if(Utility.isNullOrEmpty(employee.getEmail_id())) 
		{
			
			email=employee.getEmail_id();
		}
		else 
		{
			email="abcd@gmail.com";
			
		}
		
		User user = new User(employee.getEmp_name(), employee.getEmp_username(),
			//	employee.getEmail_id(), encoder.encode(employee.getPassword()));
				email, encoder.encode(employee.getPassword()));
        
		
        Set<User_role_id> strRoles = employee.getRole();
        
        Set<Role> roles = new HashSet<>();
        String roleId="",jsonData="";
        strRoles.forEach(role -> {
        	
        	Role adminRole = roleRepository.getRoleNamePId(role.getUser_role_id())
	                .orElseThrow(() -> new RuntimeException("admin Fail! -> Cause: User Role not find."));
        	System.err.println("Get Roles:>>> "+adminRole.getRole_id()+" ,Json: "+adminRole.getRoleaccessjson());
	    	roles.add(adminRole);
	    	
        });
        System.err.println("Got Roles:"+roles); 
        user.setRoles(roles);
        userRepository.save(user);
        
        for(User_role_id uRole_id : strRoles)
		{
        	int x=user_rolesRepository.updateUserRoles(userRepository.getUserDetails(employee.getEmp_username()).getId(),roleRepository.getRoleDtls(uRole_id.getUser_role_id()).getId(),roleRepository.getRoleDtls(uRole_id.getUser_role_id()).getRoleaccessjson());
		}
        
		return employeeMasterRepository.save(employee);
	}
	
	public List<Employee_masterDTO> findAll()
	{
		List<Employee_master> empList=new ArrayList<Employee_master>();
		empList.addAll(employeeMasterRepository.getEmployeeName());
		
		Type listType = new TypeToken<List<Employee_masterDTO>>() {}.getType();
		List<Employee_masterDTO> emps = new ModelMapper().map(empList,listType);
		
	/*	emps.forEach((emp) -> {
			System.err.println(emp.getEmail_id());
			
			userRepository.getUserDetailsThruEmail(emp.getEmail_id());
			
		});
		*/	
		return emps;
	}

	public Employee_master findOne(long id)
	{
		Optional<Employee_master> op=this.employeeMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Employee_master update(Employee_master employee,long id)
	{
		Optional<Employee_master> op = employeeMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		employee.setEmp_id(op.get().getEmp_id());
		employee.setModified_type("INSERTED");
		employee.setInserted_by(op.get().getInserted_by());
		employee.setInserted_on(op.get().getInserted_on());
		employee.setUpdated_by(userRepository.getUserDetails(employee.getUsername()).getName());
		employee.setUpdated_on(ldt);
		employee.setDeleted_by("NA");
		employee.setDeleted_on(ldt);
		
		
		//User user = new User(op.get().getEmp_name(), op.get().getEmp_username(),
		//		op.get().getEmail_id(), encoder.encode(employee.getPassword()));
        //System.out.println("pswd"+encoder.encode(employee.getPassword())+": usename:"+op.get().getEmp_username());
        userRepository.updateUserPswd(encoder.encode(employee.getPassword()),op.get().getEmp_username());
        
		if(op.isPresent())
		{
			employee.setId(id);
		}
		return employeeMasterRepository.save(employee);
	}
	
	@Transactional
	public Employee_master deleteEmployee(Employee_master emp,long id)
	{
		Optional<Employee_master> op = employeeMasterRepository.findById(id);
		Employee_master employee=employeeMasterRepository.getEmployeeDtls(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		employee.setEmp_id(op.get().getEmp_id());
		employee.setModified_type("DELETED");
		employee.setInserted_by(op.get().getInserted_by());
		employee.setInserted_on(op.get().getInserted_on());
		employee.setUpdated_by(op.get().getUpdated_by());
		employee.setUpdated_on(op.get().getUpdated_on());
		employee.setDeleted_by(userRepository.getUserDetails(employee.getUsername()).getName());
		employee.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			employee.setId(id);
		}
		return employeeMasterRepository.save(employee);
	}
	
	public List<EmployeeMasterDTO> getEmployeeName(String company){
		List<Employee_master> empList=employeeMasterRepository.getEmployeeName();
		empList.forEach((e->{
			e.setEmp_name(e.getEmp_name().toUpperCase());
		}));
		List<Employee_master> modelList = empList
				  .stream()
				  .filter(c -> !c.getModified_type().equals("DELETED") && c.getCompany_id().equals(company))
				  .sorted(Comparator.comparing(Employee_master::getEmp_name))
				  .collect(Collectors.toList());
		
		Type listType = new TypeToken<List<EmployeeMasterDTO>>() {}.getType();
		List<EmployeeMasterDTO> stempList = new ModelMapper().map(modelList,listType);
		
		return stempList;	
	}
	
	public SequenceIdDTO getEmployeeSequenceId(String prefix,String company) {
		Long slno=0L;String fprefix="";
		String code=companyMasterRepository.getCompanyDetails(company).getComp_prefix();
		fprefix=prefix+"/"+code+"/";
		System.err.println("Code: > "+fprefix);
		String gen_sno=employeeMasterRepository.getEmployeeSequenceId(fprefix,company);
		System.err.println("No: > "+gen_sno);
		
		if(gen_sno != null ) {
			slno=Long.parseLong(gen_sno);
		}
		
		String gen_slno=UniqueIDTransaction.uniqueId5(fprefix,slno);
		
		Type listType = new TypeToken<SequenceIdDTO>() {}.getType();
		
		SequenceIdDTO genCode = new ModelMapper().map(gen_slno,listType);
		
		genCode.setSequenceid(gen_slno);
		
		return genCode;
	}
	public List<EmployeeMasterDTOC> getEmployeeNamenew(String company){
		
		List<Object[]> empnames=new ArrayList<Object[]>();
		empnames.addAll(employeeMasterRepository.getemployees(company));
		
		EmployeeMasterDTOC defaultr= new EmployeeMasterDTOC("0", "Choose an Option");
		
		
		 List<EmployeeMasterDTOC> list = new ArrayList<EmployeeMasterDTOC>();     
		 list.add(defaultr);
		    for(final Object o : empnames)
		    {
		        Object[] obj = (Object[]) o;
		        list.add(new EmployeeMasterDTOC(obj[0].toString(), obj[1].toString()));
		    }
			  
		return list;
				
	}
	
	public List<Map<String, Object>> employeeAdminNamesList(String company)
	{
		return employeeMasterRepository.employeeAdminNamesList(company);
	}
	
}

