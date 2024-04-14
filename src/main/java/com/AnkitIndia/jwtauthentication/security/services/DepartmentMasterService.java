package com.AnkitIndia.jwtauthentication.security.services;

import java.util.List;
import com.AnkitIndia.jwtauthentication.model.Department_master;
import com.AnkitIndia.jwtauthentication.responseDTO.DepartmentMasterDTO;

public interface DepartmentMasterService {
	
	public Department_master save(Department_master department);
	
	public List<Department_master> findAll();
	
	public List<DepartmentMasterDTO> getDepartmentNCList();

	public Department_master findOne(long id);
	
	public Department_master update(Department_master department,long id);
	
	public List<DepartmentMasterDTO> getDeptNameCode();
	
	public Department_master deleteDepartment(Department_master department,long id);
	
	public List<Department_master> findDepartment(String searchtext);
}

