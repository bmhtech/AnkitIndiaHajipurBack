package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;

import com.AnkitIndia.jwtauthentication.model.Department_master;
import com.AnkitIndia.jwtauthentication.repository.CompanyMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.DepartmentMasterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;

import com.AnkitIndia.jwtauthentication.responseDTO.DepartmentMasterDTO;

@Service
public class DepartmentMasterService_Imp implements DepartmentMasterService {

	@Autowired
	DepartmentMasterRepository departmentMasterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Transactional
	public Department_master save(Department_master department)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="DM";
		if(departmentMasterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(departmentMasterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		department.setDepartment_code(gen_sno);
		department.setModified_type("INSERTED");
		department.setInserted_by(userRepository.getUserDetails(department.getUsername()).getName());
		department.setInserted_on(ldt);
		department.setUpdated_by("NA");
		department.setUpdated_on(ldt);
		department.setDeleted_by("NA");
		department.setDeleted_on(ldt);
		
		return departmentMasterRepository.save(department);
	}
	
	public List<Department_master> findAll()
	{
		List<Department_master> depList=new ArrayList<Department_master>();
		depList.addAll(departmentMasterRepository.findAll());
		
		List<Department_master> allData = depList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Department_master::getDepartment_code).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public List<DepartmentMasterDTO> getDepartmentNCList()
	{
		List<Department_master> depList=departmentMasterRepository.findAll();
		
		depList.forEach((e)->{
			e.setDepartment_name(e.getDepartment_name().toUpperCase());
		});
				
		List<Department_master> allData = depList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Department_master::getDepartment_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<DepartmentMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<DepartmentMasterDTO> deptList= new ModelMapper().map(allData,listType);
		
		return deptList;
	}

	public Department_master findOne(long id)
	{
		Optional<Department_master> op=this.departmentMasterRepository.findById(id);
		return op.get();
	}
	
	@Transactional
	public Department_master update(Department_master department,long id)
	{
		Optional<Department_master> op = departmentMasterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		department.setDepartment_code(op.get().getDepartment_code());
		department.setModified_type("UPDATED");
		department.setInserted_by(op.get().getInserted_by());
		department.setInserted_on(op.get().getInserted_on());
		department.setUpdated_by(userRepository.getUserDetails(department.getUsername()).getName());
		department.setUpdated_on(ldt);
		department.setDeleted_by("NA");
		department.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			department.setId(id);
		}
		return departmentMasterRepository.save(department);
	}
	
	@Transactional
	public Department_master deleteDepartment(Department_master department,long id)
	{
		Optional<Department_master> op = departmentMasterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Department_master deptMaster=op.get();

		deptMaster.setInserted_by(op.get().getInserted_by());
		deptMaster.setInserted_on(op.get().getInserted_on());
		deptMaster.setUpdated_by(op.get().getUpdated_by());
		deptMaster.setUpdated_on(op.get().getUpdated_on());
		deptMaster.setDeleted_by(userRepository.getUserDetails(department.getUsername()).getName());
		deptMaster.setDeleted_on(ldt);
		deptMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			deptMaster.setId(id);
		}
		return departmentMasterRepository.save(deptMaster);
	}
	
	public List<DepartmentMasterDTO> getDeptNameCode() {
		List<Department_master> depList=new ArrayList<Department_master>();
		depList.addAll(departmentMasterRepository.findAll());
				
		List<Department_master> allData = depList
			  .parallelStream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Department_master::getDepartment_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<DepartmentMasterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<DepartmentMasterDTO> deptList= new ModelMapper().map(allData,listType);
		
		return deptList;
	}
	
	public List<Department_master> findDepartment(String searchtext)
	{
		List<Department_master> depList=new ArrayList<Department_master>();
		depList.addAll(departmentMasterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Department_master> allData = depList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Department_master::getDepartment_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Department_master> allData = depList
					  .parallelStream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getDepartment_name()+c.getBusinessunit_name()+c.getDepartment_remarks()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Department_master::getDepartment_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
	
}
