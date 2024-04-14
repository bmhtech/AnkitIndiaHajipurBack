package com.AnkitIndia.jwtauthentication.security.services;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AnkitIndia.Utility.Utility;
import com.AnkitIndia.generators.UniqueID;
import com.AnkitIndia.jwtauthentication.model.District_master;
import com.AnkitIndia.jwtauthentication.repository.City_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.District_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.State_masterRepository;
import com.AnkitIndia.jwtauthentication.repository.UserRepository;
import com.AnkitIndia.jwtauthentication.responseDTO.District_masterDTO;

@Service
public class District_masterService_Imp implements District_masterService{
	
	@Autowired
	District_masterRepository district_masterRepository;
	
	@Autowired
	State_masterRepository state_masterRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	City_masterRepository city_masterRepository;
	
	@Transactional
	public District_master save(District_master district_master)
	{
		LocalDateTime ldt = LocalDateTime.now();
		
		long slno=0;String prefix="DST";
		if(district_masterRepository.countId(prefix).isPresent()) {
			slno=Long.parseLong(district_masterRepository.countId(prefix).get());
		}
		String gen_sno=UniqueID.uniqueid(prefix,slno);
		
		if(Utility.isNullOrEmpty(district_master.getState_code())) {
			district_master.setState_name(state_masterRepository.getState(district_master.getState_code()).getState_name());
		}else {district_master.setState_name("None");}
		
		district_master.setDist_code(gen_sno);
		district_master.setModified_type("INSERTED");
		district_master.setInserted_by(userRepository.getUserDetails(district_master.getUsername()).getName());
		district_master.setInserted_on(ldt);
		district_master.setUpdated_by("NA");
		district_master.setUpdated_on(ldt);
		district_master.setDeleted_by("NA");
		district_master.setDeleted_on(ldt);
		
		return district_masterRepository.save(district_master);
	}
	
	@Transactional
	public District_master update(District_master district_master,long id)
	{
		Optional<District_master> op = district_masterRepository.findById(id);
		
		LocalDateTime ldt = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(district_master.getState_code())) {
			district_master.setState_name(state_masterRepository.getState(district_master.getState_code()).getState_name());
		}else {district_master.setState_name("None");}
		
		district_master.setDist_code(op.get().getDist_code());
		district_master.setModified_type("UPDATED");
		district_master.setInserted_by(op.get().getInserted_by());
		district_master.setInserted_on(op.get().getInserted_on());
		district_master.setUpdated_by(userRepository.getUserDetails(district_master.getUsername()).getName());
		district_master.setUpdated_on(ldt);
		district_master.setDeleted_by("NA");
		district_master.setDeleted_on(ldt);
		
		if(op.isPresent()) {
			district_master.setId(id);
		}
		return district_masterRepository.save(district_master);
	}
	
	@Transactional
	public District_master deleteDistrict(District_master district_master,long id)
	{
		Optional<District_master> op = district_masterRepository.findById(id);
		LocalDateTime ldt = LocalDateTime.now();
		District_master dMaster=op.get();
		
		dMaster.setDist_code(op.get().getDist_code());
		dMaster.setInserted_by(op.get().getInserted_by());
		dMaster.setInserted_on(op.get().getInserted_on());
		dMaster.setUpdated_by(op.get().getUpdated_by());
		dMaster.setUpdated_on(op.get().getUpdated_on());
		dMaster.setDeleted_by(userRepository.getUserDetails(district_master.getUsername()).getName());
		dMaster.setDeleted_on(ldt);
		dMaster.setModified_type("DELETED");
		if(op.isPresent()) {
			dMaster.setId(id);
		}
		
		/*if(city_masterRepository.getCityDtlsThruDist(op.get().getDist_code()).isPresent()) {
			return district_master;
		}else {
			
			return district_masterRepository.save(dMaster);
		}*/
		return district_masterRepository.save(dMaster);
	}
	
	public List<District_master> findAll()
	{
		List<District_master> distList=new ArrayList<District_master>();
		distList.addAll(district_masterRepository.findAll());
				
		List<District_master> allData = distList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(District_master::getDist_code).reversed()).limit(50)
			  .collect(Collectors.toList());
		
		return allData;
	}
	
	public District_master findOne(long id)
	{
		Optional<District_master> op=this.district_masterRepository.findById(id);
		return op.get();
	}
	
	public List<District_masterDTO> districtlist(){
		
		List<District_master> distList=new ArrayList<District_master>();
		distList.addAll(district_masterRepository.findAll());
				
		List<District_master> allData = distList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED"))
			  .sorted(Comparator.comparing(District_master::getDist_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<District_masterDTO>>() {}.getType();
		// Convert List of Entity objects to a List of DTOs objects 
		List<District_masterDTO> distMasters= new ModelMapper().map(allData,listType);
		
		return distMasters;
	}
	
	public List<District_masterDTO> districtListByState(String state_Name){
		List<District_master> distList=new ArrayList<District_master>();
		distList.addAll(district_masterRepository.findAll());
				
		List<District_master> allData = distList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getState_name().toLowerCase().equals(state_Name.toLowerCase()))
			  .sorted(Comparator.comparing(District_master::getDist_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<District_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<District_masterDTO> districtMasterList= new ModelMapper().map(allData,listType);
		return districtMasterList;
	}
	
	public List<District_masterDTO> getDistrictThruState(String stateid){
		List<District_master> distList=district_masterRepository.findAll();

		distList.forEach((e)->{
			e.setDist_name(e.getDist_name().toUpperCase());
		});
		
		List<District_master> allData = distList
			  .stream()
			  .filter(c -> !c.getModified_type().equals("DELETED") && c.getState_code().equals(stateid))
			  .sorted(Comparator.comparing(District_master::getDist_name))
			  .collect(Collectors.toList());
		
		// Create Conversion Type
		Type listType = new TypeToken<List<District_masterDTO>>() {}.getType();
		
		// Convert List of Entity objects to a List of DTOs objects 
		List<District_masterDTO> districtMasterList= new ModelMapper().map(allData,listType);
		return districtMasterList;
	}
	
	public List<District_master> findDistricts(String searchtext)
	{
		List<District_master> distList=new ArrayList<District_master>();
		distList.addAll(district_masterRepository.findAll());
		
		if(Utility.isStringNullOrEmpty(searchtext)) {
			List<District_master> allData = distList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED"))
					  .sorted(Comparator.comparing(District_master::getDist_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
		else {
			List<District_master> allData = distList
					  .stream()
					  .filter(c -> !c.getModified_type().equals("DELETED") &&
							  (c.getCountry()+c.getState_name()+c.getDist_name()).toLowerCase().contains(searchtext.toLowerCase()))
					  .sorted(Comparator.comparing(District_master::getDist_name))
					  .collect(Collectors.toList());
			
			return allData;
		}
	}
}