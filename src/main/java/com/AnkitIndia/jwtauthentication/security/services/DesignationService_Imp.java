package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.Designation;



import com.AnkitIndia.jwtauthentication.repository.DesignationRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.DesignationDTO;


@Service
public class DesignationService_Imp implements DesignationService {
	
	@Autowired
	DesignationRepository designationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public Designation save(Designation designation)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;
		String prefix="DEG";
		if(designationRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(designationRepository.countId(prefix).get());
		}
		
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		designation.setDesig_id(gen_sno);
		designation.setModified_type("INSERTED");
		designation.setInserted_by(userRepository.getUserDetails(designation.getUsername()).getName());
		designation.setInserted_on(ldt);
		designation.setUpdated_by("NA");
		designation.setUpdated_on(ldt);
		designation.setDeleted_by("NA");
		designation.setDeleted_on(ldt);

		return designationRepository.save(designation);
	}
	
	public Designation update(Designation designation,long id)
	{
		Optional<Designation> op =designationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		
		designation.setDesig_id(op.get().getDesig_id());
		designation.setModified_type("UPDATED");
		designation.setInserted_by(op.get().getInserted_by());
		designation.setInserted_on(op.get().getInserted_on());
		designation.setUpdated_by(userRepository.getUserDetails(designation.getUsername()).getName());
		designation.setUpdated_on(ldt);
		designation.setDeleted_by("NA");
		designation.setDeleted_on(ldt);
		
		if(op.isPresent())
		{
			designation.setId(id);
		}
		
		return designationRepository.save(designation);
	}
	
	@Transactional
	public Designation deleteDesignation(Designation designation,long id)
	{
		Optional<Designation> op = designationRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		Designation designationMaster=op.get();

		designationMaster.setInserted_by(op.get().getInserted_by());
		designationMaster.setInserted_on(op.get().getInserted_on());
		designationMaster.setUpdated_by(op.get().getUpdated_by());
		designationMaster.setUpdated_on(op.get().getUpdated_on());
		designationMaster.setDeleted_by(userRepository.getUserDetails(designation.getUsername()).getName());
		designationMaster.setDeleted_on(ldt);
		designationMaster.setModified_type("DELETED");
		if(op.isPresent())
		{
			designationMaster.setId(id);
		}
		return designationRepository.save(designationMaster);
	}
	
	public List<Designation> findAll()
	{
		List<Designation> desiList=new ArrayList<Designation>();
		desiList.addAll(designationRepository.findAll());
				
		List<Designation> allData = desiList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Designation::getDesig_id).reversed())
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public Designation findOne(long id)
	{
		Optional<Designation> op=this.designationRepository.findById(id);
		return op.get();
	}
	
	public DesignationDTO getDesigNameToCode(String code) {
		Designation modelList=designationRepository.getDesigNameToCode(code);
		
		// Create Conversion Type
		Type listType = new TypeToken<DesignationDTO>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		DesignationDTO desigList = new ModelMapper().map(modelList,listType);
		
		return desigList;	
	}
	
	public List<DesignationDTO> getDesignation() {
		
		List<Designation> desiList=designationRepository.findAll();
		desiList.forEach((e->{
			e.setDesig_name(e.getDesig_name().toUpperCase());
		}));				
		List<Designation> allData = desiList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(Designation::getDesig_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<DesignationDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<DesignationDTO> desigList= new ModelMapper().map(allData,listType);
		
		return desigList;
		
	}
	
	public List<Map<String,Object>> designationListNew() {
		
		List <Map<String,Object>> designationList = new ArrayList<>();
		
		designationList.addAll(designationRepository.designationListNew());
		
		return designationList;
	}
	
	public List<Designation> findDesignation(String searchtext)
	{
		List<Designation> desigList=new ArrayList<Designation>();
		desigList.addAll(designationRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<Designation> allData = desigList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(Designation::getDesig_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<Designation> allData = desigList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getDesig_name()+c.getDescription()).toLowerCase().contains(Utility.replaceSpecial(searchtext).toLowerCase()))
					  .sorted(Comparator.comparing(Designation::getDesig_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}

}
